/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zwh.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * @author ThinkGem
 * @version 2014-4-15
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	
	private static String[] parsePatterns = {
		"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
		"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}
	
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}
	
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
	
	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}
	
	public static String formatDateMinuter(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}
	
	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
	 *   "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null){
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(24*60*60*1000);
	}

	/**
	 * 获取过去的小时
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*60*1000);
	}
	
	/**
	 * 获取过去的分钟
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*1000);
	}
	
	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * @param timeMillis
	 * @return
	 */
    public static String formatDateTime(long timeMillis){
		long day = timeMillis/(24*60*60*1000);
		long hour = (timeMillis/(60*60*1000)-day*24);
		long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
		long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
		long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
		return (day>0?day+",":"")+hour+":"+min+":"+s+"."+sss;
    }
	
	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}
	
	/**
	 * getTwoTime:(计算两个时间差，单位：毫秒)
	 * @Author airufei
	 * @param d1
	 * @param d2
	 * @return 
	 */
	public  static long  getTwoTime(Date d1 ,Date d2)
	{
	    long diff =0;
		try
		{
		     diff = d1.getTime() - d2.getTime();
		}
		catch (Exception e)
		{
		}
		
		return diff;
	}
	
	/**
	* 字符串转换成日期
	* @param str
	* @return date
	*/
	public static Date StrToDate(String str) {
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   Date date = null;
	   try {
		   if(str!=null&&str.length()>0)
		   {
			   date = format.parse(str);
		   }else
		   {
			   date=new Date();
		   }
	   
	   } catch (ParseException e) {
	   System.out.println("时间格式化异常");
	   }
	   return date;
	}
	
	/**
	* 字符串转换成日期 yyyy-mm-dd格式
	* @param str
	* @return date
	*/
	public static Date StrToDate2(String str) {
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	   Date date = null;
	   try {
		   if(str!=null&&str.length()>0)
		   {
			   date = format.parse(str);
		   }else
		   {
			   date=new Date();
		   }
	   
	   } catch (ParseException e) {
	   System.out.println("时间格式化异常");
	   }
	   return date;
	}
	/**
	 * getNextDay:(已知时间+N天后的时间)
	 * @Author airufei
	 * @param date
	 * @param dayCount
	 * @return
	 */
	public static Date getNextDay(Date date,int dayCount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, +dayCount);//时间+N天后的时间
		date = calendar.getTime();
		return date;
	}
	
	/**
	 * (已知时间-N天前的时间)
	 * @param date
	 * @param dayCount
	 * @return
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
	 * @param date
	 * @param hourCount
	 * @return
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
	 * @param date
	 * @param hourCount
	 * @return
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
	 * @param date
	 * @param hourCount
	 * @return
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
	 * @param date
	 * @param hourCount
	 * @return
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
	 * @Author airufei
	 * @param date 需要转换的时间
	 * @param msg N分钟以前 +需要显示的消息
	 * @return
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
	 * @return
	 */
	public static long getUnixtimeStamp(){
		return System.currentTimeMillis()/1000L;
	}
	/**
	 * @param args
	 * @throws ParseException
	 */
//	public static void main(String[] args) throws ParseException {
////		System.out.println(formatDate(parseDate("2010/3/6")));
////		System.out.println(getDate("yyyy年MM月dd日 E"));
////		long time = new Date().getTime()-parseDate("2012-11-19").getTime();
////		System.out.println(getBeforeMINUTE(new Date(), 30));
//	}
}
