package com.pole.logresolve.model;

import com.core.annotation.FieldName;
import com.core.pojo.BaseDO;
import lombok.Data;

import java.util.Date;

/***
 * @author 王强 Email : 
 * @version 创建时间：2018/4/4
 * UrlStatisticsDO
 */
@Data
public class UrlStatisticsDO extends BaseDO{
    /***
     * 此条记录的统计时间的整形表达形式
     */
    @FieldName("new_time")
    private Integer newTime;

    /***
     * 访问数量
     */
    @FieldName("increase_num")
    private Long increaseNum;

    /***
     * 当前总访问数量
     */
    @FieldName("current_num")
    private Long currentNum;

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
}
