package com.xuyh.service;

import com.xuyh.model.HolidayModel;
import com.xuyh.repository.HolidayRepository;
import com.xuyh.utils.PageableImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @Author: xuyh
 * @Description:
 * @Date: 14:30 2019/6/6
 */
@Slf4j
@Service
public class HolidayService {
    @Autowired
    private HolidayRepository mHolidayRepository;

    @Transactional
    public List<HolidayModel> getAll(){
        return mHolidayRepository.findAll();
    }

    @Transactional
    @Cacheable(value = "holiday", keyGenerator = "wiselyKeyGenerator")
    public HolidayModel getHolidayById(String date){
        Optional<HolidayModel> optional = mHolidayRepository.findById(date);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @Transactional
    @Cacheable(value = "holiday", keyGenerator = "wiselyKeyGenerator")
    public String getHolidayTypeById(String date){
        HolidayModel holidayModel = mHolidayRepository.getHolidayByDate(date);
        return "1".equals(holidayModel.getDisable()) ? "-1": holidayModel.getType();
    }

    @Transactional
    public List<HolidayModel> getAll4Page(int currentPage){
        PageableImpl pageable = new PageableImpl();
        pageable.setPageNumber(currentPage);
        // 以下属性可以设置也可以使用接口定义中的默认值
        pageable.setPageSize(2);
        pageable.setSort(new Sort(Sort.Direction.DESC, "Date"));
        return mHolidayRepository.findAll(pageable).getContent();
    }

    @Transactional
    public int disableHolidayById(String date){
        return mHolidayRepository.disableDate(date);
    }

    @Transactional
    public int deleteHolidayById(String date){
        return mHolidayRepository.deleteDate(date);
    }

    @Transactional
    public HolidayModel save(HolidayModel holiday){
        return mHolidayRepository.save(holiday);
    }

    @Transactional
    public List<HolidayModel> saveAll(List<HolidayModel> holidays){
        return mHolidayRepository.saveAll(holidays);
    }
}
