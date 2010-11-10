package net.lintasarta.permohonan.dao;

import net.lintasarta.permohonan.model.THoliday;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Fachrul-Rozi
 * Date: Nov 10, 2010
 * Time: 11:46:11 AM
 * To change this template use File | Settings | File Templates.
 */
public interface THolidayDAO {

    int getCountAllTHoliday();
    int getAllTHolidayBetween(String start_date, String end_date);
    List<THoliday> getAllTHoliday();

}