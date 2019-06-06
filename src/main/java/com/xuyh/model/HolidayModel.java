package com.xuyh.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;


/**
 * @Author: XUYH
 * @Description: 实体模型
 * @Date: 2018/12/12
 * @Version:
 */
@Table(name = "HOLIDAY")
@Entity
public class HolidayModel implements Serializable{

    @Id
    private String Date     = "";
    private String Type     = "1"; // 1假期，0工作日，2周末，3工作日的周末
    private String Disable  = "0";

    @Override
    public String toString() {
        return "HolidayModel{" +
                "Date='" + Date + '\'' +
                ", Type='" + Type + '\'' +
                ", Disable='" + Disable + '\'' +
                '}';
    }

    @Id
    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getDisable() {
        return Disable;
    }

    public void setDisable(String disable) {
        Disable = disable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HolidayModel that = (HolidayModel) o;
        return Date.equals(that.Date) &&
                Objects.equals(Type, that.Type) &&
                Disable.equals(that.Disable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Date, Type, Disable);
    }
}
