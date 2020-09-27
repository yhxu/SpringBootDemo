package com.xuyh.dao;

import com.xuyh.dao.impl.BaseDaoImpl;

import java.util.List;

public interface BaseDao<T> {
    List<Object> executeCall(final String sql, final BaseDaoImpl.ProcedureParam[] args);

    List<T> queryList(String sql, Object[] args);

    Class<T> getGenericClazz();
}
