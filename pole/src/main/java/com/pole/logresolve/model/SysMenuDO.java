package com.pole.logresolve.model;

import com.core.annotation.TableName;
import com.core.pojo.BaseDO;
import lombok.Data;

import java.util.Date;

/***
 * @author 王强 Email : 
 * @version 创建时间：2018/4/12
 * SysMenuDO
 */
@Data
@TableName("sys_menu")
public class SysMenuDO extends BaseDO{
    public static String URL_STATISTICS = "/url/statistics/line?tableName=";
    private String parent_id; //父级编号',
    private String parent_ids; //所有父级编号',
    private String name; //名称',
    private String sort; //排序',
    private String href; //链接',
    private String target; //目标',
    private String icon; //图标',
    private String is_show; //是否在菜单中显示',
    private String permission; //权限标识',
    private String create_by; //创建者',
    private String update_by; //更新者',
    private String remarks; //备注信息',
    private String del_flag; //删除标记',
    private Date update_date; //更新时间',
    private Date create_date; //创建时间',

    public SysMenuDO(String name, String parent_id){
        this.parent_id = "1";
        this.is_show = "1";
        this.create_by = "1";
        Date date = new Date();
        this.create_date = date;
        this.update_date = date;
        this.del_flag = "0";
        this.update_by = "1";
        this.name = name;
        this.parent_id = parent_id;
        this.parent_ids = "0,1," + parent_id;
        this.sort = "1";
    }

    public SysMenuDO(){}
}
