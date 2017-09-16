package com.sxbo.favoritesserver.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author xiaobo GG [https://github.com/sxbo]
 * @Date 2017/9/614:12
 */
public class DateUtils {
    //以毫秒为基础
    private final static long minute = 60*1000; //1分钟
    private final static long hour = minute*60; //i小时
    private final static long day = 24*hour; //1天
    private final static long month = 30*day; //1月
    private final static long year = 12*month; //一年

    public final static String YYYYMMDDHHMMSS = "yyyyMMddHHmmssSSS";

    public static String getDateSequence(){
        return new SimpleDateFormat(YYYYMMDDHHMMSS).format(new Date());
    }

    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public static String getTimeFormatText(Long date){
        if (date ==null){
            return null;
        }
        long diff = new Date().getTime() - date;
        long r = 0;
        if (diff > year) {
            r = (diff / year);
            return r + "年前";
        }
        if (diff > month) {
            r = (diff / month);
            return r + "个月前";
        }
        if (diff > day) {
            r = (diff / day);
            return r + "天前";
        }
        if (diff > hour) {
            r = (diff / hour);
            return r + "个小时前";
        }
        if (diff > minute) {
            r = (diff / minute);
            return r + "分钟前";
        }
        return "刚刚";
    }

    /**
     * 将时间戳转换成当天0点
     * @param timestamp
     * @return
     */
    public static long getDayBegin(long timestamp) {
        String format = "yyyy-MM-DD";
        String toDayString = new SimpleDateFormat(format).format(new Date(timestamp));
        Date toDay = null;
        try {
            toDay = org.apache.commons.lang3.time.DateUtils.parseDate(toDayString, new String[]{format});

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return toDay.getTime();
    }

    /**
     * 获取一个月之前的时间戳
     * @return
     */
    public static long getLastMonthTime() {
        return getDayBegin(getCurrentTime())-86400000l*30;
    }
}
