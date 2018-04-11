package com.pole.demo;

import com.core.annotation.TableName;
import com.core.pojo.BaseDO;
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

    public static void main(String[] args){
        System.out.println((152940 +  50980));
    }
}
