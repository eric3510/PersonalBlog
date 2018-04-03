package com.pole.config.model;

import com.pole.core.annotation.TableName;
import com.pole.core.pojo.BaseDO;
import lombok.Data;

import java.util.UUID;

/***
 * @author 王强 Email : 
 * @version 创建时间：2018/4/3
 * UrlHotSpotsDO
 */
@Data
@TableName("url_hot_spot")
public class UrlHotSpotsDO extends BaseDO{
    /***
     * 名称
     */
    private String name;

    /***
     * 需要统计的url
     */
    private String url;

    public UrlHotSpotsDO(String name, String url){
        this.name = name;
        this.url = url;
    }

    public UrlHotSpotsDO(){}

    public static void main(String[] args){
        for(int i = 0; i < 100; i++){
            System.out.println(UUID.randomUUID().toString());
        }
    }
}
