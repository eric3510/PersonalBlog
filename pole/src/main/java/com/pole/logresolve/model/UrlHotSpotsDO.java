package com.pole.logresolve.model;

import com.core.annotation.FieldName;
import com.core.annotation.TableName;
import com.core.pojo.BaseDO;
import lombok.Data;

import java.sql.Timestamp;
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

    /***
     * 是否立即进行统计[true:是][false:否]
     */
    private String immediately;

    /***
     * 构造方法
     * @param name
     * @param url
     */
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
