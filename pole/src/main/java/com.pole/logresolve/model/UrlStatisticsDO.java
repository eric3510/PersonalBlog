package com.pole.logresolve.model;

import com.pole.core.annotation.FieldName;
import com.pole.core.annotation.TableName;
import com.pole.core.pojo.BaseDO;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

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
    @FieldName("statistics_time")
    private Integer statisticsTime;

    /***
     * 访问数量
     */
    @FieldName("access_number")
    private Long accessNumber;

    /***
     * 当前总访问数量
     */
    @FieldName("total_access_number")
    private Long totalAccessNumber;

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
