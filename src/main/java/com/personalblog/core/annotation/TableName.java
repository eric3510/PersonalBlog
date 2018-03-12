package com.personalblog.core.annotation;

import java.lang.annotation.*;

/***
 * @author 王强 Email : wangqiang@hushijie.com.cn
 * @version 创建时间：2017/9/8
 * 在执行save操作时标有此注解的类会优先获取注解上的表名
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface TableName{
    String value() default "";
}
