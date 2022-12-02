package com.yinhe.ec.cpps.sys.mapper;

import com.yinhe.ec.core.base.BaseMapper;
import com.yinhe.ec.cpps.sys.model.ConfList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 参数配置表
 *
 * @author zhanglei
 * @date 2020-02-19 13:40:13
 */
public interface ConfListMapper extends BaseMapper<ConfList> {
    /**
     * 查询参数配置表分页列表
     *
     * @return
     */
    List<ConfList> queryConfListList(@Param("confList") ConfList confList);
    /**
     * 修改confList
     * @return
     */
    Integer updateConfList(@Param("confList") ConfList confList);
}
