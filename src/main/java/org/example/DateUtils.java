package org.example;

import org.joda.time.DateTime;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DateUtils {

    public static Date buildSqlDate(int day, int month, int year) {
        String dateString = year + "-" + month + "-" + day;
        return Date.valueOf(dateString);
    }

    public static DateTime buildDateTime(int day, int month, int year) {
        String dateString = year + "-" + month + "-" + day;
        return DateTime.parse(dateString);
    }

    public static String convertDateIntoDDMMYYYYFormat(Date date) {
        SimpleDateFormat simpleDate = new SimpleDateFormat("ddMMyyyy");
        return simpleDate.format(date);
    }

    public static Timestamp convertTimeStampFromSqlDate(Date sqlDate) {
        return new Timestamp(sqlDate.getTime());
    }

    public static Date convertFromTimeStampToSqlDate(Timestamp sqlDate) {
        return new Date(sqlDate.getTime());
    }

}
