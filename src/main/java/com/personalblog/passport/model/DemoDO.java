package com.personalblog.passport.model;

import com.personalblog.core.annotation.TableName;
import com.personalblog.core.pojo.BaseDO;
import lombok.Data;

/***
 * @author 王强 Email : eric3510@foxmail.com
 * @version 创建时间：2018/3/28
 * DemoDO
 */
@Data
@TableName("demo")
public class DemoDO extends BaseDO{
    private String id;
    private String name;
}
