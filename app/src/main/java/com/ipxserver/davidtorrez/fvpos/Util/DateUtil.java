package com.ipxserver.davidtorrez.fvpos.Util;

import java.util.Calendar;

/**
 * a class to process date&time to be a string like. (21 Jun 2012 9:36am)  or (9:36 am 21/06/2012)
 *
 *
 * @author Zheng Zhida (zhida.zheng@nbbse.com)
 *
 */

public final class DateUtil {

    /**
     * Month array.
     */
    private static String[] monthArray = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct",
            "Nov", "Dec" };

    /**
     * Logger.
     */


    /**
     * Default constructor.
     */
    private DateUtil() {
        super();
    }

    /**
     * Date to string function.
     *
     * @return a date in String format.
     */
    public static String dateToString() {
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        // int weekDay = calendar.get((Calendar.DAY_OF_WEEK));
        int amPm = calendar.get(Calendar.AM_PM);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        // int second = calendar.get(Calendar.SECOND);

        StringBuffer dateStr = new StringBuffer();
        if (1 <= day & day < 10) {
            dateStr.append("0");
        }
        dateStr.append(day).append("  ").append(monthArray[month]).append("  ").append(year).append("    ")
                .append(hour).append(":");
        if (minute < 10) {
            dateStr.append("0");
        }
        dateStr.append(minute);
        if (amPm == Calendar.AM) {
            dateStr.append("am");
        } else {
            dateStr.append("pm");
        }

        return dateStr.toString();
    }

    /**
     * Date to String function 2.
     *
     * @return a date in string format
     */
    public static String dateToString1() {
        Calendar calendar = Calendar.getInstance();

//		int year = calendar.get(Calendar.YEAR);
//		int month = calendar.get(Calendar.MONTH);
//		int day = calendar.get(Calendar.DATE);
        // int weekDay = calendar.get((Calendar.DAY_OF_WEEK));
        int amPm = calendar.get(Calendar.AM_PM);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        // int second = calendar.get(Calendar.SECOND);

        StringBuffer dateStr = new StringBuffer();
        if (hour < 10) {
            dateStr.append("0");
        }
        dateStr.append(hour).append(":");
        if (minute < 10) {
            dateStr.append("0");
        }
        dateStr.append(minute).append(" ");
        if (amPm == Calendar.AM) {
            dateStr.append("am");
        } else {
            dateStr.append("pm");
        }
//		dateStr.append("   ").append(day).append("/");
//		if (month < 10) {
//			dateStr.append("0");
//		}
//		dateStr.append(month + 1).append("/").append(year);

        return dateStr.toString();
    }
}