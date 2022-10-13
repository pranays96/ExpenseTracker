package com.pranaysahu.expensetracker.util;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DateTimeUtil {

    public static String convertDateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }

}
