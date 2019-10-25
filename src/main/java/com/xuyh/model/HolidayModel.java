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
    private String date     = "";
    /**
     * 1假期，0工作日，2周末，3工作日的周末
      */
    private String type     = "1";
    private String disable  = "0";

    @Override
    public String toString() {
        return "HolidayModel{" +
                "Date='" + date + '\'' +
                ", Type='" + type + '\'' +
                ", Disable='" + disable + '\'' +
                '}';
    }

    @Id
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDisable() {
        return disable;
    }

    public void setDisable(String disable) {
        this.disable = disable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HolidayModel that = (HolidayModel) o;
        return date.equals(that.date) &&
                Objects.equals(type, that.type) &&
                disable.equals(that.disable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, type, disable);
    }
}
