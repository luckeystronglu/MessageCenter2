package com.luckey.util;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

@SuppressLint("SimpleDateFormat")
public class DateUtils {
	
	private static Calendar calendar = Calendar.getInstance();
	
	public static String formatDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String result = sdf.format(date);
        return result;
    }
	
	public static Date formatDate2String(String dateString, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date result = sdf.parse(dateString);
        return result;
    }
	/**
	   * 获取时间字符串
	   *
	   * @return 返回字符串类型 yyyy-MM-dd
	   */
	public static String getStringShortFromDate(Date date) {
        return formatDate(date, "yyyy-MM-dd");
    }
	/**
	   * 获取时间字符串
	   *
	   * @return 返回字符串类型 yyyy-MM-dd
	   */
	public static String getStringYearAndMonthFromDate(Date date) {
      return formatDate(date, "yyyy-MM");
  }
	/**
	   * 获取时间字符串
	   *
	   * @return 返回字符串类型 yyyy-MM-dd
	   */
	public static String getStringMonthAndDayFromDate(Date date) {
      return formatDate(date, "MM-dd");
  }
	public static String getStringMonthAndDayFromDate(Date date,String format) {
	      return formatDate(date, format);
	  }
	/**
	   * 获取时间字符串
	   *
	   * @return 返回字符串类型 yyyy-MM-dd
	   */
	public static String getStringYearFromDate(Date date) {
  return formatDate(date, "yyyy");
}
	/**
	   * 获取时间字符串
	   *
	   * @return 返回字符串类型 yyyy-MM-dd HH:mm:ss
	   */
    public static String getStringLongFromDate(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }
	/**
	   * 获取时间字符串
	   *
	   * @return 返回字符串类型 yyyy-MM-dd HH:00:00
	   */
    public static String getStringFromDateWithHour(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:00:00");
    }
    public static String formatTime(Date date) {
        return formatDate(date, "HH:mm:ss");
    }
    
    public static Date formatDate(String dateString) throws ParseException {
        return formatDate2String(dateString, "yyyy-MM-dd");
    }
    public static Date formatTime(String dateString) throws ParseException {
        return formatDate2String(dateString, "HH:mm:ss");
    }
	

    public static Date getDateFromString(String dateString) throws ParseException {
        return formatDate2String(dateString, "yyyy-MM-dd HH:mm:ss");
    }

    public static String getCurrentDateShort() {
        return getStringShortFromDate(new Date());
    }   
    
    public static String getCurrentDateTime() {
        return getStringLongFromDate(new Date());
    }
    public static Long getCurrentDateHour() {
        return getLongTime(getStringFromDateWithHour(new Date()));
    }
    public static Long getCurrentDateLong() {
        return getLongTime(formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
    }
    public static String getStringFromLong(long longtime,String dateFormat) {
        return formatDate(getDateFromLong(longtime), dateFormat);
    }
    public static Date addHour(Date date, int hours) {
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }

    public static Date addDay(Date date, int days) {
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    public static Date addMonth(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }
    public static Date addYear(Date date, int years) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, years);
        return cal.getTime();
    }
    
    public static String getWeekStartDate(Date date){
    	calendar.setTime(date);
    	calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    	return getStringLongFromDate(calendar.getTime());
    }
    public static String getWeekEndDate(Date date){
    	calendar.setTime(date);
    	calendar.setFirstDayOfWeek(Calendar.MONDAY);
    	calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    	calendar.add(Calendar.DAY_OF_WEEK, 6);
    	return getStringLongFromDate(calendar.getTime());
    }
    
    public static Date getMonthStartDate(Date date){
    	calendar.setTime(date);
    	calendar.set(Calendar.DAY_OF_MONTH, 1);
    	return calendar.getTime();
    }
 
    public static Date getMonthEndDate(Date date){
    	calendar.setTime(date);
    	calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
    	return calendar.getTime();
    }
    public static String getQuarterStartDate(Date date){
    	calendar.setTime(date);
    	int month = getQuarterInMonth(calendar.get(Calendar.MONTH), true);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
    	return getStringLongFromDate(calendar.getTime());
    }
    public static String getQuarterEndDate(Date date){
    	calendar.setTime(date);
		int month = getQuarterInMonth(calendar.get(Calendar.MONTH), false);
		calendar.set(Calendar.MONTH, month + 1);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
    	return getStringLongFromDate(calendar.getTime());
    }
    
    public static Date getYearStartDate(Date date){
    	calendar.setTime(date);
    	calendar.set(Calendar.MONTH, 0);
    	calendar.set(Calendar.DAY_OF_YEAR, 1);
    	return calendar.getTime();
    }
    public static Date getYearEndDate(Date date){
    	calendar.setTime(date);
    	calendar.set(Calendar.MONTH, 11);
    	calendar.set(Calendar.DAY_OF_MONTH, 31);
    	return calendar.getTime();
    }
    
    // 返回第几个月份，不是几月
 	// 季度一年四季， 第一季度：1月-3月， 第二季度：4月-6月， 第三季度：7月-9月， 第四季度：10月-12月
 	private static int getQuarterInMonth(int month, boolean isQuarterStart) {
 		int months[] = { 0, 3, 6, 9 };
 		if (!isQuarterStart) {
 			months = new int[] { 2, 5, 8, 11 };
 		}
 		if (month >= 0 && month <= 2)
 			return months[0];
 		else if (month >= 3 && month <= 5)
 			return months[1];
 		else if (month >= 6 && month <= 8)
 			return months[2];
 		else
 			return months[3];
 	}
 	
	public static String changeTime(int total_time,int unit){
		int h=total_time/(unit*60*60);
		int min=(total_time%(unit*60*60))/(unit*60);
		int end=(total_time/unit)%60;
		String str="";
		if(h<10){
			str+="0"+h+":";
		}else{
			str+=h+":";
		}
		if(min<10){
			str+="0"+min+":";
		}else{
			str+=min+":";
		}
		if(end<10){
			str+="0"+end;
		}else{
			str+=end;
		}
		return str;
	}
	public static long getLongTime(String strSource)
	{
		SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date;
			date = simpleDateFormat .parse(strSource);
			long timeStemp = date.getTime();
			return timeStemp;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	     

	}

	public static Date getDateFromLong( long time )
    {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis( time );
        return c.getTime();
    }
	public static long getLongFromDateWithHour( Date dtSource )
    {
		 return getLongTime(formatDate(dtSource, "yyyy-MM-dd HH:00:00"));
    }
	public static long getLongFromDateWithStart( Date dtSource )
    {
		 return getLongTime(formatDate(dtSource, "yyyy-MM-dd 00:00:00"));
    }
	public static long getLongFromDateWithEnd( Date dtSource )
    {
		 return getLongTime(formatDate(dtSource, "yyyy-MM-dd 23:59:59"));
    }
	public static long getLongFromDate( Date dtSource )
    {
		 return getLongTime(formatDate(dtSource, "yyyy-MM-dd HH:mm:ss"));
    }
	
	
	
	/**
	   * 获取现在时间
	   *
	   * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
	   */
	  public static Date getNowDate() {
	    Date currentTime = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String dateString = formatter.format(currentTime);
	    ParsePosition pos = new ParsePosition(8);
	    Date currentTime_2 = formatter.parse(dateString, pos);
	    return currentTime_2;
	  }
	 
	  /**
	   * 获取现在时间
	   *
	   * @return返回短时间格式 yyyy-MM-dd
	   */
	  public static Date getNowDateShort() {
	    Date currentTime = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    String dateString = formatter.format(currentTime);
	    ParsePosition pos = new ParsePosition(8);
	    Date currentTime_2 = formatter.parse(dateString, pos);
	    return currentTime_2;
	  }
	 
	  /**
	   * 获取现在时间
	   *
	   * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
	   */
	  public static String getStringDateNow() {
	    Date currentTime = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String dateString = formatter.format(currentTime);
	    return dateString;
	  }
	 
	  /**
	   * 获取现在时间
	   *
	   * @return 返回短时间字符串格式yyyy-MM-dd
	   */
	  public static String getStringDateNowShort() {
	    Date currentTime = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    String dateString = formatter.format(currentTime);
	    return dateString;
	  }
	 
	  /**
	   * 获取时间 小时:分;秒 HH:mm:ss
	   *
	   * @return
	   */
	  public static String getStringTimeNowShort() {
	    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
	    Date currentTime = new Date();
	    String dateString = formatter.format(currentTime);
	    return dateString;
	  }
	 
	  /**
	   * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
	   *
	   * @param strDate
	   * @return
	   */
	  public static Date strToDateLong(String strDate) {
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    ParsePosition pos = new ParsePosition(0);
	    Date strtodate = formatter.parse(strDate, pos);
	    return strtodate;
	  }
	 
	  /**
	   * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
	   *
	   * @param dateDate
	   * @return
	   */
	  public static String dateToStrLong(Date dateDate) {
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String dateString = formatter.format(dateDate);
	    return dateString;
	  }

	  /**
	   * 将短时间格式时间转换为字符串 yyyy-MM-dd
	   *
	   * @param dateDate
	   * @return
	   */
	  public static String dateToStr(Date dateDate) {
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    String dateString = formatter.format(dateDate);
	    return dateString;
	  }

	  /**
	   * 将短时间格式字符串转换为时间 yyyy-MM-dd
	   *
	   * @param strDate
	   * @return
	   */
	  public static Date strToDate(String strDate) {
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    ParsePosition pos = new ParsePosition(0);
	    Date strtodate = formatter.parse(strDate, pos);
	    return strtodate;
	  }

	  /**
	   * 得到现在时间
	   *
	   * @return
	   */
	  public static Date getNow() {
	    Date currentTime = new Date();
	    return currentTime;
	  }

	  /**
	   * 提取一个月中的最后一天
	   *
	   * @param day
	   * @return
	   */
	  public static Date getLastDate(long day) {
	    Date date = new Date();
	    long date_3_hm = date.getTime() - 3600000 * 34 * day;
	    Date date_3_hm_date = new Date(date_3_hm);
	    return date_3_hm_date;
	  }

	  /**
	   * 得到现在时间
	   *
	   * @return 字符串 yyyyMMdd HHmmss
	   */
	  public static String getStringToday() {
	    Date currentTime = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");
	    String dateString = formatter.format(currentTime);
	    return dateString;
	  }
	  /**
	   * 得到现在小时
	   */
	  public static int getYear(Date sourceDate) {
//	    Date currentTime = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String dateString = formatter.format(sourceDate);
	    String hour;
	    hour = dateString.substring(0, 4);
	    return Integer.valueOf(hour);
	  }
	  /**
	   * 得到现在小时
	   */
	  public static int getMonth(Date sourceDate) {
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String dateString = formatter.format(sourceDate);
	    String hour;
	    hour = dateString.substring(5, 7);
	    return Integer.valueOf(hour);
	  }
	  public static int getLongMonth(Date sourceDate) {
		    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String dateString = formatter.format(sourceDate);
		    String hour;
		    hour =dateString.substring(0, 4)+ dateString.substring(5, 7);
		    return Integer.valueOf(hour);
		  }
	  /**
	   * 得到现在小时
	   */
	  public static int getDay(Date sourceDate) {
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String dateString = formatter.format(sourceDate);
	    String hour;
	    hour = dateString.substring(8, 10);
	    return Integer.valueOf(hour);
	  }
	  public static int getLongDay(Date sourceDate) {
		    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String dateString = formatter.format(sourceDate);
		    String hour;
		    hour =dateString.substring(0, 4)+ dateString.substring(5, 7)+ dateString.substring(8, 10);
		    return Integer.valueOf(hour);
		  }
	  /**
	   * 得到现在小时
	   */
	  public static int getHour(Date sourceDate) {
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String dateString = formatter.format(sourceDate);
	    String hour;
	    hour = dateString.substring(11, 13);
	    return Integer.valueOf(hour);
	  }
	  public static int getLongHour(Date sourceDate) {
		    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String dateString = formatter.format(sourceDate);
		    String hour;
		    hour = dateString.substring(0, 4)+ dateString.substring(5, 7)+ dateString.substring(8, 10)+dateString.substring(11, 13);
		    return Integer.valueOf(hour);
		  }
	  /**
	   * 得到现在分钟
	   *
	   * @return
	   */
	  public static int getTime(Date sourceDate) {
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String dateString = formatter.format(sourceDate);
	    String min;
	    min = dateString.substring(14, 16);
	    return Integer.valueOf(min);
	  }

	  /**
	   * 根据用户传入的时间表示格式，返回当前时间的格式 如果是yyyyMMdd，注意字母y不能大写。
	   *
	   * @param sformat yyyyMMddhhmmss
	   * @return
	   */
	  public static String getUserDate(String sformat) {
	    Date currentTime = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat(sformat);
	    String dateString = formatter.format(currentTime);
	    return dateString;
	  }

	  /**
	   * 二个小时时间间的差值,必须保证二个时间都是"HH:MM"的格式，返回字符型的分钟
	   */
	  public static String getTwoHour(String st1, String st2) {
	    String[] kk = null;
	    String[] jj = null;
	    kk = st1.split(":");
	    jj = st2.split(":");
	    if (Integer.parseInt(kk[0]) < Integer.parseInt(jj[0]))
	      return "0";
	    else {
	      double y = Double.parseDouble(kk[0]) + Double.parseDouble(kk[1]) / 60;
	      double u = Double.parseDouble(jj[0]) + Double.parseDouble(jj[1]) / 60;
	      if ((y - u) > 0)
	        return y - u + "";
	      else
	        return "0";
	    }
	  }

	  /**
	   * 得到二个日期间的间隔天数
	   */
	  public static String getTwoDay(String sj1, String sj2) {
	    SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
	    long day = 0;
	    try {
	      Date date = myFormatter.parse(sj1);
	      Date mydate = myFormatter.parse(sj2);
	      day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
	    } catch (Exception e) {
	      return "";
	    }
	    return day + "";
	  }
	 
	  /**
	   * 时间前推或后推分钟,其中JJ表示分钟.
	   */
	  public static String getPreTime(String sj1, String jj) {
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String mydate1 = "";
	    try {
	      Date date1 = format.parse(sj1);
	      long Time = (date1.getTime() / 1000) + Integer.parseInt(jj) * 60;
	      date1.setTime(Time * 1000);
	      mydate1 = format.format(date1);
	    } catch (Exception e) {
	    }
	    return mydate1;
	  }
	 
	  /**
	   * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
	   */
	  public static String getNextDay(String nowdate, String delay) {
	    try {
	      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	      String mdate = "";
	      Date d = strToDate(nowdate);
	      long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24 * 60 * 60;
	      d.setTime(myTime * 1000);
	      mdate = format.format(d);
	      return mdate;
	    } catch (Exception e) {
	      return "";
	    }
	  }
	 
	  /**
	   * 判断是否润年
	   *
	   * @param ddate
	   * @return
	   */
	  public static boolean isLeapYear(String ddate) {
	 
	    /**
	     * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
	     * 3.能被4整除同时能被100整除则不是闰年
	     */
	    Date d = strToDate(ddate);
	    GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
	    gc.setTime(d);
	    int year = gc.get(Calendar.YEAR);
	    if ((year % 400) == 0)
	      return true;
	    else if ((year % 4) == 0) {
	      if ((year % 100) == 0)
	        return false;
	      else
	        return true;
	    } else
	      return false;
	  }
	 
	  /**
	   * 返回美国时间格式 26 Apr 2006
	   *
	   * @param str
	   * @return
	   */
	  public static String getEDate(String str) {
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    ParsePosition pos = new ParsePosition(0);
	    Date strtodate = formatter.parse(str, pos);
	    String j = strtodate.toString();
	    String[] k = j.split(" ");
	    return k[2] + k[1].toUpperCase() + k[5].substring(2, 4);
	  }
	 
	  /**
	   * 获取一个月的最后一天
	   *
	   * @param dat
	   * @return
	   */
	  public static String getEndDateOfMonth(String dat) {// yyyy-MM-dd
	    String str = dat.substring(0, 8);
	    String month = dat.substring(5, 7);
	    int mon = Integer.parseInt(month);
	    if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8 || mon == 10 || mon == 12) {
	      str += "31";
	    } else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
	      str += "30";
	    } else {
	      if (isLeapYear(dat)) {
	        str += "29";
	      } else {
	        str += "28";
	      }
	    }
	    return str;
	  }
	 
	  /**
	   * 判断二个时间是否在同一个周
	   *
	   * @param date1
	   * @param date2
	   * @return
	   */
//	  public static boolean isSameWeekDates(Date date1, Date date2) {
//	    Calendar cal1 = Calendar.getInstance();
//	    Calendar cal2 = Calendar.getInstance();
//	    cal1.setTime(date1);
//	    cal2.setTime(date2);
//	    int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
//	    if (0 == subYear) {
//	      if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
//	        return true;
//	    } else if (1 == subYear && cal2.get(Calendar.MONTH)==Calendar.NOVEMBER) {
//	      // 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
//	      if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
//	        return true;
//	    } else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
//	      if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
//	        return true;
//	    }
//	    return false;
//	  }
	 
	  /**
	   * 产生周序列,即得到当前时间所在的年度是第几周
	   *
	   * @return
	   */
	  public static String getSeqWeek() {
	    Calendar c = Calendar.getInstance(Locale.CHINA);
	    String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
	    if (week.length() == 1)
	      week = "0" + week;
	    String year = Integer.toString(c.get(Calendar.YEAR));
	    return year + week;
	  }
	 
	  /** 
	     * 计算两个日期之间相差的天数 
	     * @param smdate 较小的时间
	     * @param bdate  较大的时间
	     * @return 相差天数
		 * @throws ParseException 
	     */  
	    public static int daysBetween(Date smdate,Date bdate) 
	    {  
//	    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	    	smdate=sdf.parse(sdf.format(smdate));
//	    	bdate=sdf.parse(sdf.format(bdate));
	        Calendar cal = Calendar.getInstance();  
	        cal.setTime(smdate);  
	        long time1 = cal.getTimeInMillis();               
	        cal.setTime(bdate);  
	        long time2 = cal.getTimeInMillis();       
	        long between_days=(time2-time1)/(1000*3600*24);
	          
	       return Integer.parseInt(String.valueOf(between_days));         
	    }
	
	    public static int daysBetween(long smdate,long bdate) 
	    {  
//	    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	    	smdate=sdf.parse(sdf.format(smdate));
//	    	bdate=sdf.parse(sdf.format(bdate));
//	        Calendar cal = Calendar.getInstance();  
//	        cal.setTime(smdate);  
//	        long time1 = cal.getTimeInMillis();               
//	        cal.setTime(bdate);  
//	        long time2 = cal.getTimeInMillis();       
	        long between_days=(bdate-smdate)/(1000*3600*24);
	          
	       return Integer.parseInt(String.valueOf(between_days));         
	    }
	    
	    public static int hoursBetween(Date smdate,Date bdate)   
	    {  
	        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数   
	        long nh = 1000 * 60 * 60;// 一小时的毫秒数   
//	    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	    	smdate=sdf.parse(sdf.format(smdate));
//	    	bdate=sdf.parse(sdf.format(bdate));
	        Calendar cal = Calendar.getInstance();  
	        cal.setTime(smdate);  
	        long time1 = cal.getTimeInMillis();               
	        cal.setTime(bdate);  
	        long time2 = cal.getTimeInMillis();    
	        long diff=(time2-time1);
	        long days=diff/nd;
	        long hours=0;
	        if(diff % nd % nh>0)
	        {
	        	  hours =  diff % nd / nh+1 + days * 24; 
	        }
	        else
	        {
	        	 hours =  diff % nd / nh + days * 24; 
	        }
	        return (int) hours;         
	    }
	    public static int hoursBetween(long smdate,long bdate)   
	    {  
	        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数   
	        long nh = 1000 * 60 * 60;// 一小时的毫秒数   
//	    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	    	smdate=sdf.parse(sdf.format(smdate));
//	    	bdate=sdf.parse(sdf.format(bdate));
//	        Calendar cal = Calendar.getInstance();  
	          
	        long time1 = smdate;               
	         
	        long time2 = bdate;    
	        long diff=(time2-time1);
	        long days=diff/nd;
	        long hours=0;
	        if(diff % nd % nh>0)
	        {
	        	  hours =  diff % nd / nh+1 + days * 24; 
	        }
	        else
	        {
	        	 hours =  diff % nd / nh + days * 24; 
	        }
	        return (int) hours;         
	    }
		  /** 
	     * 计算两个日期之间相差的天数 
	     * @return 相差天数
		 * @throws ParseException 
	     */  
	    public static int monthsBetween(Date date1, Date date2) {
	    	  Calendar cal1 = new GregorianCalendar();
	    	  cal1.setTime(date1);
	    	  Calendar cal2 = new GregorianCalendar();
	    	  cal2.setTime(date2);
	    	  int c =
	    	   (cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR)) * 12 + cal1.get(Calendar.MONTH)
	    	    - cal2.get(Calendar.MONTH);
	    	  return c;
	    	 }
	    public static int yearsBetween(Date date1, Date date2) {
	    	  Calendar cal1 = new GregorianCalendar();
	    	  cal1.setTime(date1);
	    	  Calendar cal2 = new GregorianCalendar();
	    	  cal2.setTime(date2);
	    	  int c =
	    	   (cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR));
	    	  return c;
	    	 }
	    
	 // a integer to xx:xx:xx
	    public static String secToTime(int time ) {
	        String timeStr = null;
	        int hour = 0;
	        int minute = 0;
	        int second = 0;
	        if (time <= 0)
	            return "00:00:00";
	        else {
	            minute = time / 60;
	            if (minute < 60) {
	                second = time % 60;
	                timeStr = "00:"+unitFormat(minute) + ":" + unitFormat(second);
	            } else {
	                hour = minute / 60;
//	                if (hour > 99)
//	                    return "99:59:59";
	                minute = minute % 60;
	                second = time - hour * 3600 - minute * 60;
	               
	                	timeStr =Integer.toString(hour)  + ":" + unitFormat(minute) + ":" + unitFormat(second);	
	               
	                
	            }
	        }
	        return timeStr;
	    }

	    public static String unitFormat(int i) {
	        String retStr = null;
	        if (i >= 0 && i < 10)
	            retStr = "0" + Integer.toString(i);
	        else
	            retStr = "" + i;
	        return retStr;
	    }
	    
}
