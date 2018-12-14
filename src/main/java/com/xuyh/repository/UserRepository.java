package com.xuyh.repository;

import com.xuyh.model.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;


/**
 * @Author: XUYH
 * @Description: 使用jpa处理数据操作
 * @Date: 2018/12/12
 * @Version:
 */

public interface UserRepository extends JpaRepository<UserModel, String>{

    /**
     * @Author: xuyh
     * @Description: jpql查询
     * @Date: 13:55 2018/12/14
     */
    @Query("SELECT USER FROM UserModel USER WHERE USER.userId = :UserId")
    UserModel getUserById4JPQL(@Param("UserId")String UserId);

    /**
     * @Author: xuyh
     * @Description: sql查询
     * @Date: 14:06 2018/12/14
     */
    @Query(value = "SELECT * FROM USER WHERE USER.UserId = :UserId", nativeQuery = true)
    UserModel getUserById4SQL(@Param("UserId")String UserId);

    /**
     * @Author: xuyh
     * @Description: 分页查询
     * @Date: 14:07 2018/12/14
     */
    @Override
    Page<UserModel> findAll(@PageableDefault(page = 1,size = 20,sort = {"UserId","UserName"},direction = Sort.Direction.DESC) Pageable pageable);
}
