package com.sales.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static Long getCurrentMillis(){
        long millis = new java.util.Date().getTime();
        return millis;
    }


    public static String getMillisToDate(Long millis){
        DateFormat format = new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS Z");
        Date date = new Date(millis);
        return format.format((date));
    }


}
