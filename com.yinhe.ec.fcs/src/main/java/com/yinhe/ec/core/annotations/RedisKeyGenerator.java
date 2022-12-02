package com.yinhe.ec.core.annotations;

import java.lang.annotation.*;

/**
 * 用于使用缓存KeyGenerator时key的组装
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisKeyGenerator {
    String value() default "id";
}
