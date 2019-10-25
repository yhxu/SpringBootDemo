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
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PageableImpl implements Pageable{
    private int  PageNumber  = 0;
    private int  PageSize    = 0;
    private Sort sort        = null;

    public void setPageNumber(int pageNumber) {
        PageNumber = pageNumber;
    }

    public void setPageSize(int pageSize) {
        PageSize = pageSize;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    @Override
    public int getPageNumber() {
        return this.PageNumber;
    }

    @Override
    public int getPageSize() {
        return this.PageSize;
    }

    @Override
    public long getOffset() {
        return (this.PageNumber - 1) * this.PageSize;
    }

    @Override
    public Sort getSort() {
        return this.sort;
    }

    @Override
    public Pageable next() {
        this.PageNumber ++;
        return this;
    }

    @Override
    public Pageable previousOrFirst() {
        this.PageNumber = 0 < this.PageNumber ? this.PageNumber - 1 : 1;
        return this;
    }

    @Override
    public Pageable first() {
        this.PageNumber = 1;
        return this;
    }

    @Override
    public boolean hasPrevious() {
        return this.PageNumber > 1;
    }
}
