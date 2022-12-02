package com.yinhe.ec.core.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.yinhe.ec.core.exception.BusinessException;
import com.yinhe.ec.core.exception.ValidateException;
import com.yinhe.ec.core.support.Pagination;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
public interface BaseService<T extends BaseModel>
{
	/**
	 * 修改
	 * @param record
	 * @return
	 */
	T update(T record) throws BusinessException, ValidateException;

	/**
	 * 逻辑删除批量
	 * @param ids
	 * @param userId
	 */
	void del(List<Long> ids, Long userId) throws BusinessException, ValidateException;

	/**
	 * 逻辑删除单条
	 * @param id
	 * @param userId
	 */
	void del(Long id, Long userId) throws BusinessException, ValidateException;

	/**
	 * 物理删除
	 * @param id
	 */
	void delete(Long id) throws BusinessException, ValidateException;

	/**
	 * 物理删除
	 * @param t
	 * @return
	 */
	Integer deleteByEntity(T t) throws BusinessException, ValidateException;

	/**
	 * 物理删除
	 * @param columnMap
	 * @return
	 */
	Integer deleteByMap(Map<String, Object> columnMap) throws BusinessException, ValidateException;

	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	T queryById(Long id);

	/**
	 * 根据传入的数组进行分页
	 * @param list
	 * @param params
	 * @return
	 */
	Pagination<T> getPage(List<T> list, Map<String, Object> params);

	/**
	 * 分页查询
	 * @param params
	 * @return
	 */
	Pagination<T> query(Map<String, Object> params);

	/**
	 * @param entity
	 * @param rowBounds
	 * @return
	 */
	Pagination<T> query(T entity, Pagination<T> rowBounds);

	/**
	 * @param params
	 * @return
	 */
	List<T> queryList(Map<String, Object> params);

	/**
	 * @param ids
	 * @return
	 */
	List<T> queryList(List<Long> ids);

	/**
	 * @param ids
	 * @param cls
	 * @return
	 */
	<K> List<K> queryList(List<Long> ids, Class<K> cls);

	/**
	 * @param entity
	 * @return
	 */
	List<T> queryList(T entity);

	/**
	 * @param params
	 * @return
	 */
	Pagination<T> queryFromDB(Map<String, Object> params);

	/**
	 * @param entity
	 * @param rowBounds
	 * @return
	 */
	Pagination<T> queryFromDB(T entity, Pagination<T> rowBounds);

	/**
	 * @param params
	 * @return
	 */
	List<T> queryListFromDB(Map<String, Object> params);

	/**
	 * @param entity
	 * @return
	 */
	List<T> queryListFromDB(T entity);

	/**
	 * @param entity
	 * @return
	 */
	T selectOne(T entity);

	/**
	 * @param entity
	 * @return
	 * @throws BusinessException
	 * @throws ValidateException
	 */
	Integer count(T entity) throws BusinessException, ValidateException;

	/**
	 * @param param
	 * @return
	 */
	Integer count(Map<String, Object> param);

	/**
	 * @param entityList
	 * @return
	 * @throws BusinessException
	 * @throws ValidateException
	 */
	boolean updateBatch(List<T> entityList) throws BusinessException, ValidateException;

	/**
	 * @param entityList
	 * @param batchSize
	 * @return
	 * @throws BusinessException
	 * @throws ValidateException
	 */
	boolean updateBatch(List<T> entityList, int batchSize) throws BusinessException, ValidateException;

    /**
     * 批量保存
     * @param entityList 集合
     * @param batchSize
     */
    void insertBatchSomeColumn(List<T> entityList, int batchSize);
    
    /**
     * 根据 ID 查询
     *
     * @param id 主键ID
     */
    T selectById(Serializable id);

    /**
     * 查询（根据ID 批量查询）
     *
     * @param idList 主键ID列表(不能为 null 以及 empty)
     */
    List<T> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

    /**
     * 查询（根据 columnMap 条件）
     *
     * @param columnMap 表字段 map 对象
     */
    List<T> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

    /**
     * 根据 entity 条件，删除记录
     *
     * @param wrapper 实体对象封装操作类（可以为 null）
     */
    int delete(@Param(Constants.WRAPPER) Wrapper<T> wrapper);

    /**
     * 删除（根据ID 批量删除）
     */
    int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

    /**
     * 根据 whereEntity 条件，更新记录
     *
     * @param entity        实体对象 (set 条件值,可以为 null)
     * @param updateWrapper 实体对象封装操作类（可以为 null,里面的 entity 用于生成 where 语句）
     */
    int update(@Param(Constants.ENTITY) T entity, @Param(Constants.WRAPPER) Wrapper<T> updateWrapper);
    
    /**
     * 根据 entity 条件，查询一条记录
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    T selectOne(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据 Wrapper 条件，查询总记录数
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    Integer selectCount(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据 entity 条件，查询全部记录
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    List<T> selectList(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据 Wrapper 条件，查询全部记录
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据 Wrapper 条件，查询全部记录
     * <p>注意： 只返回第一个字段的值</p>
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据 entity 条件，查询全部记录（并翻页）
     *
     * @param page         分页查询条件（可以为 RowBounds.DEFAULT）
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    <E extends IPage<T>> E selectPage(E page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据 Wrapper 条件，查询全部记录（并翻页）
     *
     * @param page         分页查询条件
     * @param queryWrapper 实体对象封装操作类
     */
    <E extends IPage<Map<String, Object>>> E selectMapsPage(E page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
}
