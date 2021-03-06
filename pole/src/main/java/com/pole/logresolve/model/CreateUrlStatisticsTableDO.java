package com.pole.logresolve.model;

import com.core.pojo.BaseDO;
import lombok.Data;

/***
 * @author 王强 Email : 
 * @version 创建时间：2018/4/4
 * CreateUrlStatisticsTableDO
 */
@Data
public class CreateUrlStatisticsTableDO extends BaseDO{

    private String tableName;

    public CreateUrlStatisticsTableDO(String tableName){
        this.tableName = tableName;
    }

    public String getCreateTableSql(){
        String createTableSql = String.format(
                    "CREATE TABLE `%s` (\n" +
                    "  `id` varchar(100) NOT NULL,\n" +
                    "  `new_time` int NOT NULL COMMENT '此条记录的统计时间的整形表达形式',\n" +
                    "  `increase_num` bigint(20) NOT NULL COMMENT '访问数量',\n" +
                    "  `current_num` bigint(20) NOT NULL COMMENT '当前总访问数量',\n" +
                    "  `create_time` datetime NOT NULL COMMENT '创建时间',\n" +
                    "  `update_time` datetime NOT NULL COMMENT '更新时间',\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  UNIQUE KEY `new_time` (`new_time`) USING BTREE\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4", this.getTableName());
        return createTableSql;
    }
}
