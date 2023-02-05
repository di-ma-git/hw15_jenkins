package org.example;

import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Calendar;

import static org.example.DateUtils.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateUtilsTest extends DefaultTest {

    @Test
    public void buildSqlDateTest() {
        Date date = buildSqlDate(12, 3, 2000);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        assertThat(2000, equalTo(cal.get(Calendar.YEAR)));
        assertThat(3, equalTo(cal.get(Calendar.MONTH) + 1));
        assertThat(12, equalTo(cal.get(Calendar.DAY_OF_MONTH)));
    }

    @Test
    public void convertDateIntoDDMMYYYYFormatTest() {
        LocalDate localDate = LocalDate.of(2000, 3, 12);
        Date date = Date.valueOf(localDate);
        String dateInDDMMYYYY = convertDateIntoDDMMYYYYFormat(date);

        assertThat("12032000", equalTo(dateInDDMMYYYY));
    }

    @Test
    public void buildDateTimeTest() {
        DateTime time = buildDateTime(12, 3, 2000);
        DateTime expected = DateTime.parse("2000-03-12");

        assertThat(time, equalTo(expected));
    }

    @Test
    public void buildTimestampTest() {
        Date nowSqlDate = new Date(System.currentTimeMillis());
        Timestamp timestamp = convertTimeStampFromSqlDate(nowSqlDate);
        Date afterConversionDate = convertFromTimeStampToSqlDate(timestamp);
        assertEquals(nowSqlDate, afterConversionDate);
    }

}
