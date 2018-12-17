package com.xuyh.repository;

import com.xuyh.model.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;


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
    /**
     * @Author: xuyh
     * @Description: 自定义更新语句
     * @Date: 16:54 2018/12/14
     */
    @Transactional //用于提交事务，若没有带上这句，会报事务异常提示。
    @Modifying(flushAutomatically = true) // 自动刷新实体里保存的数据。
    @Query(value = "UPDATE USER SET UserName = :UserName WHERE UserId = :UserId", nativeQuery = true)
    int updateUserNameById4SQL(@Param("UserName") String UserName, @Param("UserId") String UserId);

    /**
     * @Author: xuyh
     * @Description: 自定义删除语句
     * @Date: 16:54 2018/12/14
     */
    @Transactional //用于提交事务，若没有带上这句，会报事务异常提示。
    @Modifying(clearAutomatically = true) // 自动删除实体里保存的数据。
    @Query(value = "DELETE FROM USER WHERE UserId = ?1", nativeQuery = true)
    int deleteUserById4SQL(String UserId);

}
