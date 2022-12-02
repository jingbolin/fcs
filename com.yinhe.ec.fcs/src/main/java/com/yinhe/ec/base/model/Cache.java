package com.yinhe.ec.base.model;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.yinhe.ec.core.base.BaseModel;
import com.yinhe.ec.core.base.BaseService;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;

/**
 * 实体缓存类，使用时不要更改缓存中的元素
 * @author wangshilei
 * @date 2022/01/07
 */
public abstract class Cache<T extends BaseModel> implements InitializingBean {
    
    protected Map<Long, T> map = MapUtil.newConcurrentHashMap();
    
    @Autowired
    protected ThreadPoolTaskExecutor taskExecutor;

    @Autowired(required = false)
    private BaseService<T> service;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.taskExecutor.execute(this::init);
    }

    /**
     * 初始化缓存中的list和map集合
     */
    public void init() {
//        List<T> list = this.service.selectList(null);
//        this.map = CollUtil.toMap(list, MapUtil.newConcurrentHashMap(), T::getId);
    }
    
    /**
     * 清空缓存中的list和map集合
     */
    public void clear() {
        this.map.clear();
    }

    /**
     * 删除缓存中的对象
     */
    public void delete(Long... ids) {
        this.delete(CollUtil.toList(ids));
    }

    /**
     * 删除缓存中的对象
     */
    public void delete(List<Long> ids) {
        if (ids == null) return;
        for (Long id : ids) {
            this.delete(id);
        }
    }

    /**
     * 更新缓存中的对象
     * <p><B>注意：更新的对象必须有id</B>
     */
    @SuppressWarnings("unchecked")
    public void update(T... records) {
        this.update(CollUtil.toList(records));
    }
    
    /**
     * 更新缓存中的对象
     * <p><B>注意：更新的对象必须有id</B>
     */
    public void update(List<T> records) {
        CollUtil.forEach(records, (record, i) -> {
            this.update(record);
        });
    }
    
    /**
     * 过滤符合条件的元素
     * <p><B>注意：使用时不要更改缓存中的元素</B>
     * @param predicate
     * @return
     */
    public Collection<T> filter(Predicate<? super T> predicate) {
        final Set<T> set = new TreeSet<>();
        for (T t : this.map.values()) {
            if (predicate.test(t)) {
                set.add(t);
            }
        }
        return set;
    }
    
    /**
     * 过滤符合条件的第一个元素
     * <p><B>注意：使用时不要更改缓存中的元素</B>
     * @param predicate
     * @return
     */
    public T filterOne(Predicate<? super T> predicate) {
        for (T t : this.map.values()) {
            if (predicate.test(t)) {
                return t;
            }
        }
        return null;
    }
    
    /**
     * 通过func自定义的规则，生成新的集合，返回null的元素将被丢弃
     * <p><B>注意：使用时不要更改缓存中的元素</B>
     * @param func
     * @return
     */
    public <R> Collection<R> map(Function<? super T, ? extends R> func) {
        final Set<R> set = new TreeSet<>();
        for (T t : this.map.values()) {
            if (null == t) {
                continue;
            }
            R value = func.apply(t);
            if (null == value) {
                continue;
            }
            set.add(value);
        }
        return set;
    }
    
    /**
     * 给缓存中添加新对象
     * <p><B>注意：新增的对象必须有id</B>
     */
    protected void add(T record) {
        Optional.ofNullable(record).map(T::getId).ifPresent(id -> {
            if (!map.containsKey(id)) {
                this.map.put(id, record);
            }
        });
    }

    /**
     * 删除缓存中的对象
     */
    protected void delete(Long id) {
        Optional.ofNullable(id).ifPresent(map::remove);
    }

    /**
     * 更新缓存中的对象，如果没有旧对象则添加，如果有旧对象就给旧对象拷贝属性
     * <p><B>注意：更新的对象必须有id</B>
     */
    protected void update(T record) {
        Optional.ofNullable(record).map(T::getId).ifPresent(id -> {
            T old = this.map.get(id);
            if (old == null) {
                this.add(record);
            } else {
                BeanUtil.copyProperties(record, old, CopyOptions.create().setIgnoreNullValue(true));
            }
        });
    }

    public T get(Long id) {
        return this.map.get(id);
    }

    public T get(Long id, Supplier<T> supplier) {
        T value = this.get(id);
        return value != null ? value : supplier.get();
    }
    
    public T getOrDefault(Long id, T defaultValue) {
        T value = this.get(id);
        return value != null ? value : defaultValue;
    }

    public Collection<T> getCollection() {
        return Collections.unmodifiableCollection(this.map.values());
    }

    public Map<Long, T> getMap() {
        return Collections.unmodifiableMap(this.map);
    }

}
