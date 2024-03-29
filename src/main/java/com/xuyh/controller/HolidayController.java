package com.xuyh.controller;

import com.xuyh.model.HolidayModel;
import com.xuyh.service.HolidayService;
import com.xuyh.utils.PageableImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class HolidayController {
    @Autowired
    private HolidayService mHolidayService;

    @PostMapping(value = "getAllHolidays")
    public List<HolidayModel> getAll(@RequestParam("QueryType") String queryType, @RequestParam("CurrentPage")String currentPage){
        List<HolidayModel> holidays;
        if(PageableImpl.QUERY_TYPE_PAGE.equals(queryType)){
            holidays = mHolidayService.getAll4Page(Integer.parseInt(currentPage));
        } else {
            holidays = mHolidayService.getAll();
        }
        return holidays;
    }

    @PostMapping(value = "getHolidayByDate", params = "Date")
    public HolidayModel getUserById(String date){
        return mHolidayService.getHolidayById(date);
    }

    @RequestMapping(value = "getHolidayTypeById", params = {"Date"}, method = RequestMethod.POST)
    public String getHolidayTypeById(String date){
        return mHolidayService.getHolidayTypeById(date);
    }

    @PostMapping(value = "disableHoliday", params = {"Date"})
    public int disableHolidayById(String date){
        return mHolidayService.disableHolidayById(date);
    }

    @PostMapping(value = "deleteHoliday", params = {"Date"})
    public int deleteHolidayById(String date){
        return mHolidayService.deleteHolidayById(date);
    }

    @PostMapping(value = "saveHoliday")
    public HolidayModel save(@RequestBody HolidayModel holiday){
        return mHolidayService.save(holiday);
    }

    @PostMapping(value = "saveHolidays")
    public List<HolidayModel> saveAll(@RequestBody List<HolidayModel> holidays){
        return mHolidayService.saveAll(holidays);
    }
    @PostMapping(value = "init", params = {"year"})
    public List<HolidayModel> init(int year){
        List<HolidayModel> holidays = new ArrayList<>();
        LocalDate firstDayOfYear = LocalDate.of(year, 1, 1);
        for (int i = 1; i < firstDayOfYear.lengthOfYear(); i++, firstDayOfYear = firstDayOfYear.plusDays(1)) {
            String date = firstDayOfYear.toString();
            int dayOfWeek = firstDayOfYear.getDayOfWeek().getValue();
            HolidayModel holiday = new HolidayModel();
            holiday.setDate(date);
            holiday.setDisable("0");
            holiday.setType((dayOfWeek == 6 || dayOfWeek == 7) ? "1" : "0");
            holidays.add(holiday);
        }
        return mHolidayService.saveAll(holidays);
    }
}
