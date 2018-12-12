package com.xuyh.repository;

import com.xuyh.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: XUYH
 * @Description: 使用jpa处理数据操作
 * @Date: 2018/12/12
 * @Version:
 */

public interface UserRepository extends JpaRepository<UserModel, String>{
}
