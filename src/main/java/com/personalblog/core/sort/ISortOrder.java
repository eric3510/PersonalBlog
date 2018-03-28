package com.personalblog.core.sort;

/**
 * 结果集排列方式接口
 *
 * @author wangruijie
 * @version 1.0.0
 * @since 2.3.0
 */
public interface ISortOrder {

    Boolean isDescOrder();

    String getSortColumn();

}
