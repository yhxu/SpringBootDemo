package com.xuyh.service;

import com.xuyh.model.UserModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        List resultList = jdbcTemplate. queryForList(querySql);

        try {
            Connection c = jdbcTemplate.getDataSource().getConnection();
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery(querySql);
            while(r.next()){
                UserModel userModel = new UserModel();
                userModel.setUserId(r.getString("UserId"));
                resultList.add(userModel);
            }
            r.close();
            s.close();
            c.close();
            return resultList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
