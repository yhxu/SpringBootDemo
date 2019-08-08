package com.xuyh.service;

import com.xuyh.model.UserModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @Author: XUYH
 * @Description:
 * @Date: 2018/12/17
 * @Version:
 */
@Service
public class JdbcService {

    @Transactional
    public List getUsers4Jdbc(JdbcTemplate jdbcTemplate){

        String querySql = "SELECT * FROM USER";
        List resultList = jdbcTemplate.queryForList(querySql);

        Connection c = null;
        Statement s = null;
        ResultSet r = null;
        DataSource d = null;
        try {
            d = jdbcTemplate.getDataSource();
            if(null == d){
               return resultList;
            }
            c = d.getConnection();
            s = c.createStatement();
            r = s.executeQuery(querySql);
            while(r.next()){
                UserModel userModel = new UserModel();
                userModel.setUserId(r.getString("UserId"));
                resultList.add(userModel);
            }
            return resultList;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeResultSet(r);
            JdbcUtils.closeStatement(s);
            JdbcUtils.closeConnection(c);
        }
        return null;
    }
}
