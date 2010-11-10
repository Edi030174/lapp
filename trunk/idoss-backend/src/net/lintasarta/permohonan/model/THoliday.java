package net.lintasarta.permohonan.model;

import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: Fachrul-Rozi
 * Date: Nov 10, 2010
 * Time: 11:39:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class THoliday {
    int p_order_prob_holiday_date_id;
    String holiday_date;
    String description;
    Timestamp update_date;
    String update_by;
    Timestamp create_date;
    String create_by;
    String start_date;
    String end_date;

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public THoliday() {


    }

    public int getP_order_prob_holiday_date_id() {
        return p_order_prob_holiday_date_id;
    }

    public void setP_order_prob_holiday_date_id(int p_order_prob_holiday_date_id) {
        this.p_order_prob_holiday_date_id = p_order_prob_holiday_date_id;
    }

    public String getHoliday_date() {
        return holiday_date;
    }

    public void setHoliday_date(String holiday_date) {
        this.holiday_date = holiday_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Timestamp update_date) {
        this.update_date = update_date;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }

    public Timestamp getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Timestamp create_date) {
        this.create_date = create_date;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

}
