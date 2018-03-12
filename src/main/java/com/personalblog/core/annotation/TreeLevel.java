package com.personalblog.core.annotation;

import java.lang.annotation.*;

/***
 * @author 王强 Email : wangqiang@hushijie.com.cn
 * @version 创建时间：2018/2/2
 * 树结构层级标识
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface TreeLevel{
    String value() default "";
}
