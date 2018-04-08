package com.pole.demo;

import com.pole.core.annotation.TableName;
import com.pole.core.pojo.BaseDO;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

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

    public static void main(String[] args){

    }
}
