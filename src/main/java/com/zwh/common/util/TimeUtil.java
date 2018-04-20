package com.zwh.common.util;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author HP340G4
 *
 */
public final class TimeUtil {

	/**
	 * 获取默认时间格式: yyyy-MM-dd HH:mm:ss
	 */
	private static final DateTimeFormatter DEFAULT_DATETIME_FORMATTER = TimeFormat.LONG_DATE_PATTERN_LINE.formatter;

	private TimeUtil() {
		// no construct function
	}

	/**
	 * String 转时间
	 *
	 * @param timeStr
	 * @return
	 */
	public static LocalDateTime parseTime(String timeStr) {
		return LocalDateTime.parse(timeStr, DEFAULT_DATETIME_FORMATTER);
	}

	/**
	 * String 转时间
	 *
	 * @param timeStr
	 * @param format
	 *            时间格式
	 * @return
	 */
	public static LocalDateTime parseTime(String timeStr, TimeFormat format) {
		return LocalDateTime.parse(timeStr, format.formatter);
	}

	/**
	 * date 转 字符串
	 * 
	 * @param dt
	 * @return
	 */
	public static String ConvertDateToStr(Date dt) {
		return FromUnixStamp(dt.getTime());
	}

	/**
	 * yyyy-MM-dd HH:mm:ss 转long
	 * 
	 * @param timeStr
	 * @return
	 */
	public static long UnixStampTime(String timeStr) {
		return parseTime(timeStr).toEpochSecond(ZoneOffset.ofHours(8));
	}

	/**
	 * long 转 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param milliSeconds
	 * @return
	 */
	public static String FromUnixStamp(long milliSeconds) {
		return parseTime(ConvertToLDT(milliSeconds));
	}

	public static LocalDateTime ConvertToLDT(long milliSeconds) {
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(milliSeconds), TimeZone.getDefault().toZoneId());
	}

	public static LocalDateTime ConvertToLDT(Timestamp ts) {
		return ConvertToLDT(ts.getTime());
	}

	public static LocalDateTime ConvertToLDT(Date dt) {
		return ConvertToLDT(dt.getTime());
	}

	public static Timestamp TimeStamp(String timeStr) {
		return Timestamp.valueOf(timeStr);
	}

	public static Timestamp ConvertToTimeStamp(LocalDateTime dt) {
		return Timestamp.valueOf(parseTime(dt));
	}

	/**
	 * 时间转 String
	 *
	 * @param time
	 * @return
	 */
	public static String parseTime(LocalDateTime time) {
		return DEFAULT_DATETIME_FORMATTER.format(time);
	}

	/**
	 * 时间转 String
	 *
	 * @param time
	 * @param format
	 *            时间格式
	 * @return
	 */
	public static String parseTime(LocalDateTime time, TimeFormat format) {
		return format.formatter.format(time);
	}

	/**
	 * 获取当前时间
	 *
	 * @return
	 */
	public static String getCurrentDatetime() {
		return DEFAULT_DATETIME_FORMATTER.format(LocalDateTime.now());
	}

	/**
	 * 今天日志
	 * 
	 * @return yyyy-MM-dd 格式的字符串
	 */
	public static String Today() {
		return TimeFormat.SHORT_DATE_PATTERN_LINE.formatter.format(LocalDate.now().atStartOfDay());
	}

	/**
	 * 今天加上days天的日期
	 * 
	 * @param days
	 * @return yyyy-MM-dd 格式的字符串
	 */
	public static String addDays(int days) {
		return DEFAULT_DATETIME_FORMATTER.format(LocalDateTime.now().plusDays(days));
	}

	/**
	 * 字符串时间加days 后的日期
	 * 
	 * @param dtimeStr
	 *            yyyy-MM-dd HH:mm:ss 格式的字符串
	 * @param days
	 * @return yyyy-MM-dd HH:mm:ss 格式的字符串
	 */
	public static String addDays(final String dtimeStr, int days) {
		return DEFAULT_DATETIME_FORMATTER.format(parseTime(dtimeStr).plusDays(days));
	}

	public static String addDays(final Date dt, int days) {
		return DEFAULT_DATETIME_FORMATTER.format(ConvertToLDT(dt).plusDays(days));
	}

	/**
	 * 获取当前时间
	 *
	 * @param format
	 *            时间格式
	 * @return
	 */
	public static String getCurrentDatetime(TimeFormat format) {
		return format.formatter.format(LocalDateTime.now());
	}

	public static void main(String[] args) {
		System.out.println(addDays(1));
		System.out.println(addDays(-1));
		System.out.println(addDays(0));
		System.out.println(addDays("2018-07-07 00:11:33", 1));

		System.out.println(parseTime("2018-07-07 00:11:33"));
		System.out.println(Today());

		System.out.println(UnixStampTime("2018-07-07 00:11:33"));
	}

	/**
	 * 时间格式
	 */
	public enum TimeFormat {

		/**
		 * 短时间格式
		 */
		SHORT_DATE_PATTERN_LINE("yyyy-MM-dd"), SHORT_DATE_PATTERN_SLASH("yyyy/MM/dd"), SHORT_DATE_PATTERN_DOUBLE_SLASH(
				"yyyy\\MM\\dd"), SHORT_DATE_PATTERN_NONE("yyyyMMdd"),

		/**
		 * 长时间格式
		 */
		LONG_DATE_PATTERN_LINE("yyyy-MM-dd HH:mm:ss"), LONG_DATE_PATTERN_SLASH(
				"yyyy/MM/dd HH:mm:ss"), LONG_DATE_PATTERN_DOUBLE_SLASH(
						"yyyy\\MM\\dd HH:mm:ss"), LONG_DATE_PATTERN_NONE("yyyyMMdd HH:mm:ss"),

		/**
		 * 长时间格式 带毫秒
		 */
		LONG_DATE_PATTERN_WITH_MILSEC_LINE("yyyy-MM-dd HH:mm:ss.SSS"), LONG_DATE_PATTERN_WITH_MILSEC_SLASH(
				"yyyy/MM/dd HH:mm:ss.SSS"), LONG_DATE_PATTERN_WITH_MILSEC_DOUBLE_SLASH(
						"yyyy\\MM\\dd HH:mm:ss.SSS"), LONG_DATE_PATTERN_WITH_MILSEC_NONE("yyyyMMdd HH:mm:ss.SSS");

		private transient DateTimeFormatter formatter;

		TimeFormat(String pattern) {
			formatter = DateTimeFormatter.ofPattern(pattern);
		}
	}
}
