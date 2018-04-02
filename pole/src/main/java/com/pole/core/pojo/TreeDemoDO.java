package com.pole.core.pojo;

import com.pole.core.annotation.TreeLevel;
import com.pole.core.annotation.TreeParentId;
import com.pole.core.annotation.FieldName;
import com.pole.core.annotation.TableName;
import lombok.Data;

/**
 * @author eric E-mail:
 * @version 创建时间：2018/2/2 上午11:08
 * TreeDemoDO对象
 */
@Data
@TableName("tree_demo")
public class TreeDemoDO extends BaseDO{
    /***
     * 主键
     */
    private Long id;
    /***
     * 姓名
     */
    private String name;
    /***
     * 年龄
     */
    private Integer age;
    /***
     * 父节点
     */
    @TreeParentId
    @FieldName("parent_id")
    private Long parentId;
    /***
     * 层级
     */
    @TreeLevel
    private Integer level;

    public TreeDemoDO(){}

    public TreeDemoDO(Long id, String name, Integer age, Long parentId, Integer level){
        this.id = id;
        this.name = name;
        this.age = age;
        this.parentId = parentId;
        this.level = level;
    }
}
