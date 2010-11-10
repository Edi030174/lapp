package net.lintasarta.permohonan.service.impl;

import net.lintasarta.permohonan.dao.THolidayDAO;
import net.lintasarta.permohonan.model.THoliday;
import net.lintasarta.permohonan.service.HolidayService;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Fachrul-Rozi
 * Date: Nov 10, 2010
 * Time: 11:53:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class HolidayServiceImpl implements HolidayService{

    private THolidayDAO tHolidayDAO;

    public THolidayDAO gettHolidayDAO() {
        return tHolidayDAO;
    }

    public void settHolidayDAO(THolidayDAO tHolidayDAO) {
        this.tHolidayDAO = tHolidayDAO;
    }

    @Override
    public int getCountAllTHoliday() {
        return tHolidayDAO.getCountAllTHoliday();
    }

    @Override
    public List getAllTHolidayBetween(String start_date, String end_date) {
        return tHolidayDAO.getAllTHolidayBetween(start_date,end_date);
    }

    @Override
    public List<THoliday> getAllTHoliday() {
        return tHolidayDAO.getAllTHoliday();
    }

}
