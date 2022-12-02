package com.yinhe.ec.core.base;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.yinhe.ec.core.support.generator.Sequence;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.yinhe.ec.core.support.Pagination;
import com.yinhe.ec.core.support.cache.CacheKey;
import com.yinhe.ec.core.support.context.ApplicationContextHolder;
import com.yinhe.ec.core.support.dbcp.HandleDataSource;
import com.yinhe.ec.core.util.CacheUtil;
import com.yinhe.ec.core.util.DataUtil;
import com.yinhe.ec.core.util.ExceptionUtil;
import com.yinhe.ec.core.util.InstanceUtil;
import com.yinhe.ec.core.util.MysqlExceptionUtil;
import com.yinhe.ec.core.util.PageUtil;
import com.yinhe.ec.core.util.ThreadUtil;

import cn.hutool.core.collection.CollUtil;

/**
 * @param <T>
 * @param <M>
 * @author ShenHuaJie
 * @since 2018年5月24日 下午6:18:41
 */
public class BaseServiceImpl<T extends BaseModel, M extends BaseMapper<T>> implements BaseService<T>, InitializingBean {
    protected Logger logger = LogManager.getLogger();
    @Autowired
    protected M mapper;

    @Override
    public void afterPropertiesSet() throws Exception {
        Field[] fields = getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object v = field.get(this);
                Class<?> cls = field.getType();
                if (v == null && cls.getSimpleName().toLowerCase().contains("service")) {
                    v = ApplicationContextHolder.getService(cls);
                    if (v != null) {
                        field.set(this, v);
                    }
                }
                field.setAccessible(false);
            }
        } catch (Exception e) {
            logger.error("", e);
            ThreadUtil.sleep(1000, 5000);
            afterPropertiesSet();
        }
    }

    /**
     * 逻辑批量删除
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void del(List<Long> ids, Long userId) {
        ids.forEach(id -> del(id, userId));
    }

    /**
     * 逻辑删除
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void del(Long id, Long userId) {
        try {
            T record = this.getById(id);
            record.setEnable(true);
            record.setUpdateTime(new Date());
            record.setUpdateBy(userId);
            mapper.updateById(record);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 物理删除
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        try {
            mapper.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 物理删除
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteByEntity(T t) {
        Wrapper<T> wrapper = new UpdateWrapper<T>(t);
        return mapper.delete(wrapper);
    }

    /**
     * 物理删除
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteByMap(Map<String, Object> columnMap) {
        return mapper.deleteByMap(columnMap);
    }

    /**
     * 根据Id查询(默认类型Map)
     */
    public Pagination<Map<String, Object>> getPageMap(final Page<Long> ids) {
        if (ids != null) {
            Pagination<Map<String, Object>> page = new Pagination<Map<String, Object>>(ids.getCurrent(), ids.getSize());
            page.setTotal(ids.getTotal());
            final List<Map<String, Object>> records = InstanceUtil.newArrayList();
            final String datasource = HandleDataSource.getDataSource();
            IntStream.range(0, ids.getRecords().size()).forEach(i -> records.add(null));
            IntStream.range(0, ids.getRecords().size()).parallel().forEach(i -> {
                HandleDataSource.putDataSource(datasource);
                records.set(i, InstanceUtil.transBean2Map(getById(ids.getRecords().get(i))));
            });
            page.setRecords(records);
            return page;
        }
        return new Pagination<Map<String, Object>>();
    }

    /**
     * 根据数据进行分页处理
     */
    @Override
    public Pagination<T> getPage(List<T> list, Map<String, Object> params) {
        Page<Long> page = PageUtil.getPage(params);
        List<T> pageRecords = new ArrayList<T>();
        long total = list.size();
        for (int i = 0; i < list.size(); i++) {
            if (i >= page.getSize() * (page.getCurrent() - 1) && i < page.getSize() * page.getCurrent()) {
                pageRecords.add(list.get(i));
            }
        }
        Pagination<T> result = new Pagination<T>(page.getCurrent(), page.getSize());
        result.setRecords(pageRecords);
        result.setTotal(total);
        return result;
    }

    /**
     * 根据参数分页查询
     */
    @Override
    public Pagination<T> query(Map<String, Object> params) {
        Page<Long> page = PageUtil.getPage(params);
        page.setRecords(mapper.selectIdPage(page, params));
        return getPage(page);
    }

    /**
     * 根据实体参数分页查询
     */
    @Override
    public Pagination<T> query(T entity, Pagination<T> rowBounds) {
        Page<Long> page = new Page<Long>();
        try {
            BeanUtils.copyProperties(rowBounds, page);
        } catch (Exception e) {
            logger.error(ExceptionUtil.getStackTraceAsString(e));
        }
        List<Long> ids = mapper.selectIdPage(page, entity);
        page.setRecords(ids);
        return getPage(page);
    }

    /**
     * 根据实体参数分页查询
     */
    @Override
    public Pagination<T> queryFromDB(T entity, Pagination<T> rowBounds) {
        Page<T> page = new Page<T>();
        try {
            BeanUtils.copyProperties(rowBounds, page);
        } catch (Exception e) {
            logger.error(ExceptionUtil.getStackTraceAsString(e));
        }
        Wrapper<T> wrapper = new QueryWrapper<T>(entity);
        IPage<T> iPage = mapper.selectPage(page, wrapper);
        Pagination<T> pager = new Pagination<T>(page.getCurrent(), page.getSize());
        pager.setRecords(iPage.getRecords());
        pager.setTotal(page.getTotal());
        return pager;
    }

    @Override
    public Pagination<T> queryFromDB(Map<String, Object> params) {
        Page<Long> page = PageUtil.getPage(params);
        List<T> list = mapper.selectPage(page, params);
        Pagination<T> pager = new Pagination<T>(page.getCurrent(), page.getSize());
        pager.setRecords(list);
        pager.setTotal(page.getTotal());
        return pager;
    }

    @Override
    public List<T> queryListFromDB(Map<String, Object> params) {
        return mapper.selectByMap(params);
    }

    @Override
    public List<T> queryListFromDB(T entity) {
        Wrapper<T> wrapper = new QueryWrapper<T>(entity);
        return mapper.selectList(wrapper);
    }

    @Override
    public Integer count(T entity) {
        Wrapper<T> wrapper = new QueryWrapper<T>(entity);
        return mapper.selectCount(wrapper);
    }

    @Override
    public Integer count(Map<String, Object> params) {
        return mapper.selectCount(params);
    }

    @Override
    /** 根据id查询实体 */
    public T queryById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    /** 根据参数查询 */
    public List<T> queryList(Map<String, Object> params) {
        if (DataUtil.isEmpty(params.get("orderBy"))) {
            params.put("orderBy", "id");
        }
        List<Long> ids = mapper.selectIdPage(params);
        List<T> list = queryList(ids);
        return list;
    }

    @Override
    /** 根据实体参数查询 */
    public List<T> queryList(T params) {
        List<Long> ids = mapper.selectIdPage(params);
        List<T> list = queryList(ids);
        return list;
    }

    /**
     * 根据Id查询(默认类型T)
     */
    @Override
    public List<T> queryList(final List<Long> ids) {
        final List<T> list = InstanceUtil.newArrayList();
        if (ids != null) {
            final String datasource = HandleDataSource.getDataSource();
            IntStream.range(0, ids.size()).forEach(i -> list.add(null));
            IntStream.range(0, ids.size()).parallel().forEach(i -> {
                HandleDataSource.putDataSource(datasource);
                list.set(i, getById(ids.get(i)));
            });
        }
        return list;
    }

    /**
     * 根据Id查询(cls返回类型Class)
     */
    @Override
    public <K> List<K> queryList(final List<Long> ids, final Class<K> cls) {
        final List<K> list = InstanceUtil.newArrayList();
        if (ids != null) {
            final String datasource = HandleDataSource.getDataSource();
            IntStream.range(0, ids.size()).forEach(i -> list.add(null));
            IntStream.range(0, ids.size()).parallel().forEach(i -> {
                HandleDataSource.putDataSource(datasource);
                T t = getById(ids.get(i));
                K k = InstanceUtil.to(t, cls);
                list.set(i, k);
            });
        }
        return list;
    }

    @Override
    /** 根据实体参数查询一条记录 */
    public T selectOne(T entity) {
        Wrapper<T> wrapper = new QueryWrapper<T>(entity);
        T t = mapper.selectOne(wrapper);
        return t;
    }

    /**
     * 修改/新增
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public T update(T record) {
        try {
            record.setUpdateTime(new Date());
            if (record.getId() == null || record.getId() <= 0) {
                record.setCreateTime(new Date());
                mapper.insert(record);
                record = mapper.selectById(record.getId());
            } else {
                String requestId = Sequence.next().toString();
                String lockKey = getLockKey(record.getId());
                if (CacheUtil.getLock(lockKey, "更新", requestId)) {
                    try {
                        mapper.updateById(record);
                        record = mapper.selectById(record.getId());
                    } finally {
                        CacheUtil.unLock(lockKey, requestId);
                    }
                } else {
                    throw new RuntimeException("数据不一致!请刷新页面重新编辑!");
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            MysqlExceptionUtil.doHandle(e, record.getClass());
            throw e;
        }
        return record;
    }

    /**
     * 批量修改/新增
     */
    @Override
    public boolean updateBatch(List<T> entityList) {
        return updateBatch(entityList, 30);
    }

    protected Class<?> currentModelClass() {
        return ReflectionKit.getSuperClassGenericType(getClass(), 0);
    }

    /**
     * 获取缓存键值
     * @param id
     * @return
     */
    protected String getLockKey(Object id) {
        CacheKey cacheKey = CacheKey.getInstance(getClass());
        StringBuilder sb = new StringBuilder();
        if (cacheKey == null) {
            sb.append(getClass().getName());
        } else {
            sb.append(cacheKey.getValue());
        }
        return sb.append(":LOCK:").append(id).toString();
    }

    /**
     * @param params
     * @param cls
     * @return
     */
    protected <P> Pagination<P> query(Map<String, Object> params, Class<P> cls) {
        Page<Long> page = PageUtil.getPage(params);
        page.setRecords(mapper.selectIdPage(page, params));
        return getPage(page, cls);
    }

    /**
     * <p>
     * 批量操作 SqlSession
     * </p>
     */
    protected SqlSession sqlSessionBatch() {
        return SqlHelper.sqlSessionBatch(currentModelClass());
    }

    /**
     * 获取SqlStatement
     * @param sqlMethod
     * @return
     */
    protected String sqlStatement(SqlMethod sqlMethod) {
        return SqlHelper.table(currentModelClass()).getSqlStatement(sqlMethod.getMethod());
    }

    /**
     * 根据Id查询(默认类型T)
     */
    private T getById(Long id) {
        return queryById(id);
    }

    /**
     * 根据Id批量查询(默认类型T)
     */
    protected Pagination<T> getPage(final Page<Long> ids) {
        if (ids != null) {
            Pagination<T> page = new Pagination<T>(ids.getCurrent(), ids.getSize());
            page.setTotal(ids.getTotal());
            final List<T> records = InstanceUtil.newArrayList();
            final String datasource = HandleDataSource.getDataSource();
            IntStream.range(0, ids.getRecords().size()).forEach(i -> records.add(null));
            IntStream.range(0, ids.getRecords().size()).parallel().forEach(i -> {
                HandleDataSource.putDataSource(datasource);
                records.set(i, getById(ids.getRecords().get(i)));
            });
            page.setRecords(records);
            return page;
        }
        return new Pagination<T>();
    }

    /**
     * 根据Id查询(cls返回类型Class)
     */
    private <K> Pagination<K> getPage(final Page<Long> ids, final Class<K> cls) {
        if (ids != null) {
            Pagination<K> page = new Pagination<K>(ids.getCurrent(), ids.getSize());
            page.setTotal(ids.getTotal());
            final List<K> records = InstanceUtil.newArrayList();
            final String datasource = HandleDataSource.getDataSource();
            IntStream.range(0, ids.getRecords().size()).forEach(i -> records.add(null));
            IntStream.range(0, ids.getRecords().size()).parallel().forEach(i -> {
                HandleDataSource.putDataSource(datasource);
                records.set(i, InstanceUtil.to(getById(ids.getRecords().get(i)), cls));
            });
            page.setRecords(records);
            return page;
        }
        return new Pagination<K>();
    }

    /**
     * 批量修改/新增
     */
    @Override
    public boolean updateBatch(List<T> entityList, int batchSize) {
        if (CollectionUtils.isEmpty(entityList)) {
            throw new IllegalArgumentException("Error: entityList must not be empty");
        }
        try (SqlSession batchSqlSession = sqlSessionBatch()) {
            IntStream.range(0, entityList.size()).forEach(i -> {
                update(entityList.get(i));
                if (i >= 1 && i % batchSize == 0) {
                    batchSqlSession.flushStatements();
                }
            });
            batchSqlSession.flushStatements();
        } catch (Throwable e) {
            throw e;
        }
        return true;
    }

    /**
     * @param entityList 实体集合
     * @param batchSize 每次保存数量
     */
    @Override
    public void insertBatchSomeColumn(List<T> entityList, int batchSize) {
        if (!entityList.isEmpty()) {
            IntStream.range(0, (entityList.size() - 1) / batchSize + 1).forEach(i -> {
                mapper.insertBatchSomeColumn(
                    entityList.stream().skip(i * batchSize).limit(batchSize).collect(Collectors.toList())
                );
            });
        }
    }

    @Override
    public T selectById(Serializable id) {
        return mapper.selectById(id);
    }

    @Override
    public List<T> selectBatchIds(Collection<? extends Serializable> idList) {
        return mapper.selectBatchIds(idList);
    }

    @Override
    public List<T> selectByMap(Map<String, Object> columnMap) {
        return mapper.selectByMap(columnMap);
    }

    @Override
    public int delete(Wrapper<T> wrapper) {
        return mapper.delete(wrapper);
    }

    @Override
    public int deleteBatchIds(Collection<? extends Serializable> idList) {
        if (CollUtil.isEmpty(idList)) return 0;
        return mapper.deleteBatchIds(idList);
    }

    @Override
    public int update(T entity, Wrapper<T> updateWrapper) {
        return mapper.update(entity, updateWrapper);
    }

    @Override
    public T selectOne(Wrapper<T> queryWrapper) {
        return mapper.selectOne(queryWrapper);
    }

    @Override
    public Integer selectCount(Wrapper<T> queryWrapper) {
        return mapper.selectCount(queryWrapper);
    }

    @Override
    public List<T> selectList(Wrapper<T> queryWrapper) {
        return mapper.selectList(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> selectMaps(Wrapper<T> queryWrapper) {
        return mapper.selectMaps(queryWrapper);
    }

    @Override
    public List<Object> selectObjs(Wrapper<T> queryWrapper) {
        return mapper.selectObjs(queryWrapper);
    }

    @Override
    public <E extends IPage<T>> E selectPage(E page, Wrapper<T> queryWrapper) {
        return mapper.selectPage(page, queryWrapper);
    }

    @Override
    public <E extends IPage<Map<String, Object>>> E selectMapsPage(E page, Wrapper<T> queryWrapper) {
        return mapper.selectMapsPage(page, queryWrapper);
    }
}
