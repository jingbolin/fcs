/**
 *
 */
package com.yinhe.ec.core.base;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author ShenHuaJie
 * @version 2016年6月3日 下午2:30:14
 */
public interface BaseMapper<T extends BaseModel> extends com.baomidou.mybatisplus.core.mapper.BaseMapper<T>
{
	List<Long> selectIdPage(@Param("cm") T params);

	List<Long> selectIdPage(@Param("cm") Map<String, Object> params);

	List<Long> selectIdPage(Page<Long> page, @Param("cm") Map<String, Object> params);

	List<Long> selectIdPage(Page<Long> page, @Param("cm") T params);

	List<T> selectPage(Page<Long> page, @Param("cm") Map<String, Object> params);

	Integer selectCount(@Param("cm") Map<String, Object> params);
	
    /**
     * 批量插入 仅适用于mysql
     * <li>注意: 这是自选字段 insert !!,如果个别字段在 entity 里为 null 但是数据库中有配置默认值, insert 后数据库字段是为 null 而不是默认值</li>
     * @param entityList 实体列表
     * @return 影响行数
     */
    Integer insertBatchSomeColumn(List<T> entityList);
}
