package com.xuyh.utils;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.stereotype.Component;

/**
 * @Author: XUYH
 * @Description: 生成表时表名大写
 * @Date: 2018/12/12
 * @Version:
 */
@Component
public class MySQLToPhysicalUpperCaseStrategy extends PhysicalNamingStrategyStandardImpl{
    private static final long serialVersionUID = 1383021413247872469L;

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        String tableName = name.getText().toUpperCase();
        return name.toIdentifier(tableName);
    }

}
