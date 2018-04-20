package com.zwh.common.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * Created by a on 15-4-15.
 */
public class DateUtil {

    private static DateFormat LOG_DATE_FORMATTER_BEGIN = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
    private static DateFormat GET_CURRENT_HOUR = new SimpleDateFormat("HH");

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    public static String formatDateTime(Date date) {
        if (date == null) {
            return "";
        } else {
            return formatDate(date, "yyyy-MM-dd HH:mm:ss");
        }
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        } else {
            return formatDate(date, "yyyy-MM-dd");
        }
    }

    /**
     * 得到截取到月份，转换格式（yyyy-MM）
     */
    public static String formatMonth(Date date) {
        return formatDate(date, "yyyy-MM");
    }

    /**
     * 获取当天0点0时0分0秒的时间
     *
     * @return
     */
    public static Timestamp getTodayZeroClockTime() {
        Calendar rightNow = Calendar.getInstance();
        rightNow.clear();
        rightNow.setTime(new Date());
        Date infoDate = rightNow.getTime();
        String time = LOG_DATE_FORMATTER_BEGIN.format(infoDate);
        return Timestamp.valueOf(time);
    }

    public static String getCurrentHour() {
        Date currentTime = new Date();
        String hourString = GET_CURRENT_HOUR.format(currentTime);
        return hourString;
    }

    public static boolean isStrOutBetween(String start, String end, String cur) {
        if (cur.compareTo(start) < 0 || cur.compareTo(end) >= 0) {
            return true;
        }
        return false;
    }

    public static Date stringConvertDate(String dateString) {
        if (StringUtils.isEmpty(dateString)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sdf.parse(dateString);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }


    }

    /**
     * 获取指定日期是星期几
     * 参数为null时表示获取当前日期是星期几
     *
     * @param date
     * @return
     */
    public static int getWeekOfDate(Date date) {
        int[] weekOfDays = {7, 1, 2, 3, 4, 5, 6};
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekOfDays[w];
    }


    public static Date getNextDay(Date date, int dayCount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, +dayCount);// 时间+N天后的时间
        date = calendar.getTime();
        return date;
    }

    /**
     * 得到当天的日期，转换格式（yyyy-MM-dd）
     */
    public static String formatDay(Date date) {
        return formatDate(date, "yyyy-MM-dd");
    }

	/**
	 * (已知时间-N天前的时间)
	 */
	public static Date getBeforeDay(Date date,int dayCount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -dayCount);//时间+N天后的时间
		date = calendar.getTime();
		return date;
	}
	
	/**
	 * (已知时间-N小时前的时间)
	 */
	public static Date getBeforeHour(Date date,int hourCount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, -hourCount);//时间+N天后的时间
		date = calendar.getTime();
		return date;
	}
	
	/**
	 * (已知时间-N小时后的时间)
	 */
	public static Date getAfterHour(Date date,int hourCount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, +hourCount);//时间+N天后的时间
		date = calendar.getTime();
		return date;
	}
	
	/**
	 * (已知时间-N分钟前的时间)
	 */
	public static Date getBeforeMINUTE(Date date,int hourCount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, -hourCount);//时间+N天后的时间
		date = calendar.getTime();
		return date;
	}
	
	/**
	 * (已知时间-N分钟后的时间)
	 */
	public static Date getAfterMINUTE(Date date,int hourCount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, +hourCount);//时间+N天后的时间
		date = calendar.getTime();
		return date;
	}
	
	/**
	 * getStrDate:(时间转换为类似“N分钟以前”的数据)
	 * @param date 需要转换的时间
	 * @param msg N分钟以前 +需要显示的消息
	 */
	public   static String getStrDate(Date date,String msg)
	{
		String str="";
		Date now=new Date();
		long seconds = (now.getTime() - date.getTime())/1000;
		long minutes = seconds/60;
		long hours = minutes/60;
		long days = hours/24;
		
		if(days >= 365){
			str=days/365 + "年前"+msg;
		} else {
			if(days >= 30 && days < 365){
				str=days/30 + "月前"+msg;
			} else {
				if(days >= 1 && days < 30){
					str=days + "天前"+msg;
				} else {
					if(hours >= 1 && hours < 24){
						str=hours + "小时前"+msg;
					} else {
						if(minutes >= 1 && minutes < 60){
							str=minutes + "分钟前"+msg;
						} else {
							str=minutes + "秒前"+msg;
						}
					}
				}
			}
		}
		return str;
	}
	
	/**
	 * 获取Unix时间戳
	 */
	public static long getUnixtimeStamp(){
		return System.currentTimeMillis()/1000L;
	}
}
