package com.fun.today.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by joy on 2017/12/12.
 */

public class DateAndTimeUtils
{
	
	public static String getStringCurrentDay()
	{
		return new SimpleDateFormat( "yyyy/MM/dd" ).format( new Date() );
	}
	
	/**
	 * 获取当前月份
	 */
	public static int getCurrentMonth()
	{
		return Calendar.getInstance().get( Calendar.MONTH ) + 1;
	}
	
	/*获取当前日期*/
	public static int getCurrentDay()
	{
		return Calendar.getInstance().get( Calendar.DAY_OF_MONTH );
	}
	
	/**
	 * 获取当前年份
	 */
	public static int getCurrentYear()
	{
		return Calendar.getInstance().get( Calendar.YEAR );
	}
}
