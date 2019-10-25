package com.xuyh.utils;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

/**
 * @Author: XUYH
 * @Description: 分页实现
 * @Date: 2018/12/14
 * @Version:
 */
@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PageableImpl implements Pageable{
    public static final String QUERY_TYPE_PAGE = "PAGE";
    public static final String QUERY_TYPE_SQL = "SQL";
    private int pageNumber = 0;
    private int pageSize = 0;
    private Sort sort = null;

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    @Override
    public int getPageNumber() {
        return this.pageNumber;
    }

    @Override
    public int getPageSize() {
        return this.pageSize;
    }

    @Override
    public long getOffset() {
        return (this.pageNumber - 1) * this.pageSize;
    }

    @Override
    public Sort getSort() {
        return this.sort;
    }

    @Override
    public Pageable next() {
        this.pageNumber++;
        return this;
    }

    @Override
    public Pageable previousOrFirst() {
        this.pageNumber = 0 < this.pageNumber ? this.pageNumber - 1 : 1;
        return this;
    }

    @Override
    public Pageable first() {
        this.pageNumber = 1;
        return this;
    }

    @Override
    public boolean hasPrevious() {
        return this.pageNumber > 1;
    }
}
