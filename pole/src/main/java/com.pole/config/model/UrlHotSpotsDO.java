package com.pole.config.model;

import com.pole.core.annotation.FieldName;
import com.pole.core.annotation.TableName;
import com.pole.core.pojo.BaseDO;
import com.pole.logresolve.model.CreateUrlStatisticsTableDO;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

/***
 * @author 王强 Email : 
 * @version 创建时间：2018/4/3
 * UrlHotSpotsDO
 */
@Data
@TableName("url_hot_spot")
public class UrlHotSpotsDO extends BaseDO{
    private static final String URL_STATISTICS = "url_statistics_";

    /***
     * 名称
     */
    private String name;

    /***
     * 需要统计的url
     */
    private String url;

    /***
     * 自动创建的表名
     */
    @FieldName("table_name")
    private String tableName;

    /***
     * 创建时间
     */
    @FieldName("create_time")
    private Date createTime;

    /***
     * 更新时间
     */
    @FieldName("update_time")
    private Date updateTime;

    public UrlHotSpotsDO(String name, String url){
        this.name = name;
        this.url = url;
    }

    public String getPrefixTableName(){
        return URL_STATISTICS + this.tableName;
    }

    public UrlHotSpotsDO(){}

    public static void main(String[] args){
        for(int i = 0; i < 100; i++){
            System.out.println(UUID.randomUUID().toString());
        }
    }
}
