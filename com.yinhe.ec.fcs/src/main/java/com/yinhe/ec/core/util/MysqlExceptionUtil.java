package com.yinhe.ec.core.util;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

import com.yinhe.ec.core.exception.BusinessException;
import org.springframework.core.NestedExceptionUtils;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import com.yinhe.ec.core.base.BaseModel;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;

/**
 * @author aexis
 * @date 2022/12/29
 */
public class MysqlExceptionUtil {
    
    private static Map<String, String> fieldNameLabelMap = new HashMap<>();

    /**
     * @param e 异常
     * @param clazz 实体类型
     */
    public static void doHandle(Exception e, Class<? extends BaseModel> clazz) {
        Throwable throwable = NestedExceptionUtils.getRootCause(e);
        if (throwable instanceof MysqlDataTruncation) {
            String rootCauseMessage = throwable.getMessage();
            String fieldName = StrUtil.toCamelCase(ReUtil.getGroup0("(?<=Data too long for column ')(.*)(?=')", rootCauseMessage));
            String fieldNameLabel = getLabel(clazz, fieldName);
            throw StrUtil.isNotBlank(fieldNameLabel) ? 
                new BusinessException(StrUtil.format("{}超过允许的长度", fieldNameLabel)) :
                new BusinessException(rootCauseMessage);
        }
        if (throwable instanceof SQLIntegrityConstraintViolationException) {
            String rootCauseMessage = throwable.getMessage();
            String fieldName = StrUtil.toCamelCase(ReUtil.getGroup0("(?<=UK_)(.*)(?=')", rootCauseMessage));
            String fieldNameLabel = getLabel(clazz, fieldName);
            throw StrUtil.isNotBlank(fieldNameLabel) ? 
                new BusinessException(StrUtil.format("{}不能重复", fieldNameLabel)) :
                new BusinessException(rootCauseMessage);
        }
    }

    private static String getLabel(Class<? extends BaseModel> clazz, String fieldName) {
        if (MapUtil.isEmpty(fieldNameLabelMap)) {
            initfieldNameLabelMap();
        }
        return MapUtil.get(fieldNameLabelMap, clazz.getSimpleName() + "_" + fieldName, String.class);
    }

    private static void initfieldNameLabelMap() {
        fieldNameLabelMap = MapUtil.<String, String>builder()
            .put("AstModelBoxTransformer_model", "箱变型号")
            .put("AstModelDaq_model", "数采型号")
            .put("AstModelInverter_model", "逆变器型号")
            .put("MonVirtualPoint_name", "测点名称")
            .put("MonVirtualPoint_identification", "测点标识")
            .put("MonVirtualPointFormula_name", "公式名称")
            .put("MonVirtualPointFormula_monVirtualPointId", "结果对象")
            .put("MonFrozenValue_name", "冻结值名称")
            .put("MonEnumValue_monMeasurementId", "测点")
            .put("MonInverterStatusFormula_astModelInverterId&StateType", "逆变器型号的公式")
            .build();
    }

}
