package com.xuyh.dao;

import com.xuyh.dao.impl.BaseDaoImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public interface BaseDao<T> {
    List<Object> executeCall(final String sql, final BaseDaoImpl.ProcedureParam[] args);

    List<T> queryList(String sql, Object[] args);

    List<T> queryList(Predicate[] predicates, Order order);

    List<T> queryListPage(Predicate[] predicates, Pageable pageable);

    CriteriaBuilder getCriteriaBuilder();

    Root<T> getRegister();

    Class<T> getGenericClazz();
}
