package com.xuyh.dao.impl;

import com.xuyh.dao.BaseDao;
import org.springframework.core.ResolvableType;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.*;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class BaseDaoImpl<T> implements BaseDao<T> {
    private Class<T> clazz;

    @Resource
    JdbcTemplate jdbcTemplate;

    /**
     * entityManager
     */
    @Resource
    private EntityManager entityManager;

    /**
     * 安全查询创建工厂
     */
    private CriteriaBuilder criteriaBuilder;

    /**
     * 安全查询主语句
     */
    private CriteriaQuery<T> criteriaQuery;

    /**
     * Root 定义查询的From子句中能出现的类型
     */
    private Root<T> register;

    public BaseDaoImpl() {
        ResolvableType type = ResolvableType.forClass(this.getClass());
        ResolvableType generics = type.getSuperType().getGeneric(0);
        Class<?> resolve = generics.resolve();
        this.clazz = (Class<T>) resolve;
        this.criteriaBuilder = this.entityManager.getCriteriaBuilder();
        this.criteriaQuery = this.criteriaBuilder.createQuery(clazz);
        this.register = this.criteriaQuery.from(clazz);
    }

    public List<Object> executeCall(final String sql, final ProcedureParam[] args) {
        CallableStatementCallback<List<Object>> action = cs -> {
            final List<Object> result = new ArrayList<>();
            for(int i=1; i<=args.length; i++){
                ProcedureParam p = args[i-1];
                if(p instanceof InProcedureParam){
                    cs.setObject(i, p.value);
                }else if(p instanceof OutProcedureParam){
                    cs.registerOutParameter(i, p.type);
                }
            }
            cs.execute();
            for(int i=1; i<=args.length; i++){
                ProcedureParam p = args[i-1];
                if(p instanceof OutProcedureParam){
                    result.add(cs.getString(i));
                }else{
                    result.add(null);
                }
            }
            return result;
        };
        return jdbcTemplate.execute(sql,action);
    }

    @Override
    public List<T> queryList(String sql, Object[] args) {
        return jdbcTemplate.query(sql, new ArgumentPreparedStatementSetter(args), new RowMapperResultSetExtractor<>(new BeanPropertyRowMapper<>(clazz)));
    }

    @Override
    public List<T> queryList(Predicate[] predicates, Order order) {
        this.criteriaQuery.where(predicates);
        if(order != null){
            this.criteriaQuery.orderBy(order);
        }
        TypedQuery<T> typedQuery = this.entityManager.createQuery(this.criteriaQuery);
        return typedQuery.getResultList();
    }

    @Override
    public List<T> queryListPage(Predicate[] predicates, Pageable pageable) {
        this.criteriaQuery.where(predicates);
        TypedQuery<T> typedQuery = this.entityManager.createQuery(this.criteriaQuery);
        typedQuery.setFirstResult((int)(pageable.getOffset())).setMaxResults(pageable.getPageSize());
        return typedQuery.getResultList();
    }

    public CriteriaBuilder getCriteriaBuilder() {
        return criteriaBuilder;
    }

    public Root<T> getRegister() {
        return register;
    }

    public Class<T> getGenericClazz(){
        return clazz;
    }

    public static abstract class ProcedureParam{
        /**
         * {@link java.sql.Types}
         */
        int type;
        Object value;

        public ProcedureParam (int type,Object value){
            this.type = type;
            this.value = value;
        }

        public int getType() {
            return type;
        }
        public void setType(int type) {
            this.type = type;
        }
        public Object getValue() {
            return value;
        }
        public void setValue(Object value) {
            this.value = value;
        }
    }

    public static class InProcedureParam extends ProcedureParam{
        public InProcedureParam(Object value){
            super(Types.LONGVARCHAR, value);
        }
    }

    public static class OutProcedureParam extends ProcedureParam{
        public OutProcedureParam(int type){
            super(type,null);
        }
    }


}
