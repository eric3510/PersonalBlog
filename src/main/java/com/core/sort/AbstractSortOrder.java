package com.core.sort;

/**
 * ISortOrder抽象实现
 *
 * @author wangruijie
 * @version 1.0.0
 * @since 2.3.0
 */
public abstract class AbstractSortOrder implements ISortOrder {

    private Boolean descOrder;

    private String sortColumn;

    public Boolean isDescOrder() {
        return descOrder;
    }

    public void setDescOrder(Boolean descOrder) {
        this.descOrder = descOrder;
    }

    @Override
    public String getSortColumn() {
        return sortColumn;
    }

    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }
}
