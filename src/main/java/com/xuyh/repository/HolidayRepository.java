package com.xuyh.repository;

import com.xuyh.model.HolidayModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;


public interface HolidayRepository extends JpaRepository<HolidayModel, String>{

    /**
     * @Author: xuyh
     * @Description: jpql查询 注意查询条件“HOLIDAY.date”字段名为数据库字段名，并不是实体参数名
     * @Date: 13:55 2018/12/14
     */
    @Query("SELECT HOLIDAY FROM HolidayModel HOLIDAY WHERE HOLIDAY.date = :Date")
    HolidayModel getHolidayByDate(@Param("Date") String Date);


    /**
     * @Author: xuyh
     * @Description: 分页查询
     * @Date: 14:07 2018/12/14
     */
    @Override
    Page<HolidayModel> findAll(@PageableDefault(page = 1, size = 20, sort = {"Date"}, direction = Sort.Direction.DESC) Pageable pageable);

    /**
     * @Author: xuyh
     * @Description: 自定义更新语句
     * @Date: 16:54 2018/12/14
     */
    @Transactional //用于提交事务，若没有带上这句，会报事务异常提示。
    @Modifying(flushAutomatically = true) // 自动刷新实体里保存的数据。
    @Query(value = "UPDATE HOLIDAY SET Disable = '1' WHERE Date = :Date", nativeQuery = true)
    int disableDate(@Param("Date") String Date);

    /**
     * @Author: xuyh
     * @Description: 自定义删除语句
     * @Date: 16:54 2018/12/14
     */
    @Transactional //用于提交事务，若没有带上这句，会报事务异常提示。
    @Modifying(clearAutomatically = true) // 自动删除实体里保存的数据。
    @Query(value = "DELETE FROM HOLIDAY WHERE Date = ?1", nativeQuery = true)
    int deleteDate(String Date);

}
