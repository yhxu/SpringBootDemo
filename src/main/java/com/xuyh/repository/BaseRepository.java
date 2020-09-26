package com.xuyh.repository;

import com.xuyh.dao.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<T, ID> extends JpaRepository<T, ID>, BaseDao {
}
