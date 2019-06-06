package com.xuyh.controller;

import com.xuyh.model.HolidayModel;
import com.xuyh.service.HolidayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class HolidayController {
    @Autowired
    private HolidayService mHolidayService;

    @PostMapping(value = "getAllHolidays")
    public List<HolidayModel> getAll(@RequestParam("QueryType") String queryType, @RequestParam("CurrentPage")String currentPage){
        List<HolidayModel> holidays;
        if("PAGE".equals(queryType)){
            holidays = mHolidayService.getAll4Page(Integer.parseInt(currentPage));
        } else {
            holidays = mHolidayService.getAll();
        }
        return holidays;
    }

    @PostMapping(value = "getHolidayByDate", params = "Date")
    public HolidayModel getUserById(String Date){
        return mHolidayService.getHolidayById(Date);
    }

    @RequestMapping(value = "getHolidayTypeById", params = {"Date"}, method = RequestMethod.POST)
    public String getHolidayTypeById(String Date){
        return mHolidayService.getHolidayTypeById(Date);
    }

    @PostMapping(value = "disableHoliday", params = {"Date"})
    public int disableHolidayById(String Date){
        return mHolidayService.disableHolidayById(Date);
    }

    @PostMapping(value = "deleteHoliday", params = {"Date"})
    public int deleteHolidayById(String Date){
        return mHolidayService.deleteHolidayById(Date);
    }

    @PostMapping(value = "saveHoliday")
    public HolidayModel save(@RequestBody HolidayModel holiday){
        return mHolidayService.save(holiday);
    }

    @PostMapping(value = "saveHolidays")
    public List<HolidayModel> saveAll(@RequestBody List<HolidayModel> holidays){
        return mHolidayService.saveAll(holidays);
    }
}
