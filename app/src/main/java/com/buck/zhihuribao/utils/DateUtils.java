package com.buck.zhihuribao.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Buck on 2016/12/8.
 */

public class DateUtils {

    private static String parseWeek(int w) {
        String week="";
        switch (w) {
            case Calendar.SUNDAY:week ="星期日"; break;
            case Calendar.MONDAY: week ="星期一"; break;
            case Calendar.TUESDAY: week ="星期二"; break;
            case Calendar.WEDNESDAY: week ="星期三"; break;
            case Calendar.THURSDAY: week ="星期四"; break;
            case Calendar.FRIDAY: week ="星期五"; break;
            case Calendar.SATURDAY: week ="星期六"; break;
        }
        return week;
    }

    public static String parseDate(String date) {
        Calendar calendar = Calendar.getInstance();
        Date nowTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String today = dateFormat.format(nowTime);
        if (date.equals(today)) {
            return "今日热文";
        } else {
            try {
                calendar.setTime(dateFormat.parse(date));
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH)+1;
                int day = calendar.get(Calendar.DATE);
                String week = parseWeek(calendar.get(Calendar.DAY_OF_WEEK));
                return month+"月"+day+"日"+"  "+week;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
