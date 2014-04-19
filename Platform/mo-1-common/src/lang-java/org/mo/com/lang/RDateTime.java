/*
 * @(#)FDateUtil.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.com.lang;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.mo.com.lang.type.TDate;
import org.mo.com.lang.type.TDateTime;
import org.mo.com.lang.type.TTime;

//============================================================
// <T>时间数据类型工具类。</T>
//
// @history 081020 MAOCY 创建
//============================================================
public class RDateTime
{
   // 默认时间
   public static long DEFAULT_TIMEZONE_VALUE;

   // 默认时区
   public final static TimeZone TIMEZONE_ZERO = TimeZone.getTimeZone("GMT+0:00");

   // 默认时区
   public final static TimeZone DEFAULT_TIMEZONE = TimeZone.getTimeZone("Asia/Shanghai");

   //public final static TimeZone DEFAULT_TIMEZONE = TimeZone.getTimeZone("GMT+0:00");
   // 6长度格式化器
   private final static SimpleDateFormat _format6 = new SimpleDateFormat("HHmmss");

   // 8长度格式化器
   private final static SimpleDateFormat _format8 = new SimpleDateFormat("yyyyMMdd");

   // 10长度格式化器
   private final static SimpleDateFormat _format10 = new SimpleDateFormat("yyyyMMddHH");

   // 14长度格式化器
   private final static SimpleDateFormat _format14 = new SimpleDateFormat("yyyyMMddHHmmss");

   // 时间描述符中用到的字符
   public final static String DATE_CHARS = "ymdh24is";

   // 默认时间格式
   public final static String DEFAULT_FORMAT = "YYYYMMDDHH24MISS";

   // 默认时间补齐字符
   public final static char DEFAULT_FORMAT_CHAR = '0';

   /// 表示一天中的刻度数
   public final static long TicksPerDay = 86400000L;

   /// 表示 1 小时的刻度数
   public final static long TicksPerHour = 3600000L;

   /// 表示 1 分钟的刻度数
   public final static long TicksPerMinute = 60000L;

   /// 表示 1 秒的刻度数
   public final static long TicksPerSecond = 1000L;

   /// 表示一周中的刻度数
   public final static long TicksPerWeek = 604800000L;

   /// 当前所在时区的毫秒数
   public final static long TicksZone = 3600000L * (23 - Calendar.ZONE_OFFSET);
   //============================================================
   static{
      Calendar calendar = Calendar.getInstance();
      calendar.setTimeZone(TimeZone.getDefault());
      calendar.setTime(new Date(0));
      int hour = calendar.get(Calendar.HOUR_OF_DAY);
      DEFAULT_TIMEZONE_VALUE = 1000 * 60 * 60 * hour;
   }

   //============================================================
   // <T>获得日历。</T>
   //
   // @return 日历
   //============================================================
   public static Calendar getCalendar(){
      Calendar calendar = Calendar.getInstance();
      calendar.setTimeZone(DEFAULT_TIMEZONE);
      return calendar;
   }

   //============================================================
   // <T>判断当前字符串是否指定时间格式。</T>
   //
   // @param value 内容
   // @param format 格式
   // @return 是否为指定时间格式
   //============================================================
   public static boolean isDate(String value,
                                String format){
      return (null != parse(value, format));
   }

   //============================================================
   // <T>检查当前时间格式化为指定格式后，是否与指定字符串匹配。</T>
   // <P>指定字符串中含有"X"的字符表示可以为任意字符</P>
   //
   // @param format 指定格式
   // @param partten 匹配字符串
   // @return 是否匹配
   //============================================================
   public static boolean isPartten(String format,
                                   String partten){
      String datetime = format(format);
      int length = partten.length();
      for(int n = 0; n < length; n++){
         if(!(partten.charAt(n) == datetime.charAt(n) || partten.charAt(n) == 'X')){
            return false;
         }
      }
      return true;
   }

   //============================================================
   // <T>获得当前日期。</T>
   //
   // @return 日期
   //============================================================
   public static TDate currentDate(){
      long value = System.currentTimeMillis() / TicksPerDay;
      return new TDate(TicksPerDay * value);
   }

   //============================================================
   // <T>获得当前时间。</T>
   //
   // @return 时间
   //============================================================
   public static TTime currentTime(){
      long value = System.currentTimeMillis() % TicksPerDay;
      return new TTime(value);
   }

   //============================================================
   // <T>获得当前日期时间。</T>
   //
   // @return 日期时间
   //============================================================
   public static TDateTime currentDateTime(){
      return new TDateTime(System.currentTimeMillis());
   }

   //============================================================
   // <T>分解字符串为默认格式的时间类型。</T>
   //
   // @param value 字符串
   // @return 时间
   //============================================================
   public static Date parse(String value){
      if(null != value){
         int length = value.length();
         SimpleDateFormat formater = null;
         if(14 == length){
            formater = _format14;
         }else if(10 == length){
            formater = _format10;
         }else if(8 == length){
            formater = _format8;
         }else if(6 == length){
            formater = _format6;
         }
         if(null != formater){
            try{
               return formater.parse(value);
            }catch(Exception e){
               throw new FFatalError(e);
            }
         }
      }
      return null;
   }

   //============================================================
   // <T>分解字符串为指定格式的时间类型。</T>
   //
   // @param value 字符串
   // @param format 指定格式
   // @return 时间
   //============================================================
   public static Date parse(String value,
                            String format){
      // 参数检查
      if((null == value) || (null == format)){
         return null;
      }
      char[] chars = format.toLowerCase().toCharArray();
      int length = chars.length;
      // 分解时间部分
      int year = 0;
      int month = 0;
      int day = 0;
      int hour = 0;
      int minute = 0;
      int second = 0;
      int millisecond = 0;
      int valuePosition = 0;
      boolean isDate = false;
      boolean isTime = false;
      String temp = "";
      for(int n = 0; n < length; n++){
         temp += chars[n];
         if(temp.equals("yyyy")){
            isDate = true;
            year = RInteger.parse(value.substring(valuePosition, valuePosition + 4));
            valuePosition += 4;
            temp = "";
         }else if(temp.equals("mm")){
            month = RInteger.parse(value.substring(valuePosition, valuePosition + 2));
            valuePosition += 2;
            temp = "";
         }else if(temp.equals("dd")){
            day = RInteger.parse(value.substring(valuePosition, valuePosition + 2));
            valuePosition += 2;
            temp = "";
         }else if(temp.equals("hh24")){
            isTime = true;
            hour = RInteger.parse(value.substring(valuePosition, valuePosition + 2));
            valuePosition += 2;
            temp = "";
         }else if(temp.equals("mi")){
            minute = RInteger.parse(value.substring(valuePosition, valuePosition + 2));
            valuePosition += 2;
            temp = "";
         }else if(temp.equals("ss")){
            second = RInteger.parse(value.substring(valuePosition, valuePosition + 2));
            valuePosition += 2;
            temp = "";
         }else if(temp.equals("ms")){
            millisecond = RInteger.parse(value.substring(valuePosition, valuePosition + 3));
            valuePosition += 3;
            temp = "";
         }else{
            if(temp.length() == 1){
               // 清除掉多余字符
               if(DATE_CHARS.indexOf(temp) == -1){
                  valuePosition++;
                  temp = "";
               }
            }
         }
      }
      // 创建日期内容
      Calendar calendar = Calendar.getInstance();
      calendar.setTimeZone(DEFAULT_TIMEZONE);
      if(day > 0){
         month--;
      }
      if(isDate && isTime){
         calendar.set(year, month, day, hour, minute, second);
         calendar.set(Calendar.MILLISECOND, millisecond);
         return calendar.getTime();
      }else if(isDate){
         calendar.set(year, month, day, 0, 0, 0);
         return calendar.getTime();
      }else if(isTime){
         calendar.set(0, 0, 0, hour, minute, second);
         calendar.set(Calendar.MILLISECOND, millisecond);
         return calendar.getTime();
      }
      return null;
   }

   //============================================================
   // <T>将一个指定对象转换为日期时间对象。</T>
   //
   // @param item 对象
   // @return 日期时间对象
   //============================================================
   public static Date parse(Object item){
      if(null != item){
         // 检查格式
         if(item instanceof Date){
            return (Date)item;
         }else if(item instanceof Long){
            return new Date(((Long)item).longValue());
         }
         // 转换为字符串
         String value = null;
         if(item instanceof String){
            value = (String)item;
         }else{
            value = item.toString();
         }
         value = value.trim();
         if(value.length() > 0){
            return parse(value);
         }
      }
      return null;
   }

   //============================================================
   // <T>格式化当前时间为默认格式。</T>
   //
   // @return 格式化字符串
   //============================================================
   public static String format(){
      return format(Calendar.getInstance().getTime(), DEFAULT_FORMAT, DEFAULT_FORMAT_CHAR);
   }

   //============================================================
   // <T>格式化时间为默认格式。</T>
   //
   // @param date 时间数据
   // @param format 格式
   // @param split 分隔符
   // @return 格式化字符串
   //============================================================
   public static String format(long date){
      return format(new Date(date), DEFAULT_FORMAT, DEFAULT_FORMAT_CHAR);
   }

   //============================================================
   // <T>格式化时间为指定格式。</T>
   //
   // @param date 时间数据
   // @param format 格式
   // @param split 分隔符
   // @return 格式化字符串
   //============================================================
   public static String format(long date,
                               String format){
      return format(new Date(date), format, DEFAULT_FORMAT_CHAR);
   }

   //============================================================
   // <T>格式化时间为指定格式。</T>
   //
   // @param date 时间数据
   // @param format 格式
   // @param split 分隔符
   // @return 格式化字符串
   //============================================================
   public static String format(long date,
                               String format,
                               char split){
      return format(new Date(date), format, split);
   }

   //============================================================
   // <T>格式化时间为默认格式。</T>
   //
   // @param date 时间
   // @return 格式化字符串
   //============================================================
   public static String format(Date date){
      return format(date, DEFAULT_FORMAT, DEFAULT_FORMAT_CHAR);
   }

   //============================================================
   // <T>格式化时间为指定格式。</T>
   //
   // @param date 时间
   // @param format 格式
   // @return 格式化字符串
   //============================================================
   public static String format(Date date,
                               String format){
      return format(date, format, DEFAULT_FORMAT_CHAR);
   }

   //============================================================
   // <T>格式化时间为指定格式。</T>
   //
   // @param date 时间
   // @param format 格式
   // @param split 分隔符
   // @return 格式化字符串
   //============================================================
   public static String format(Date date,
                               String format,
                               char split){
      if(date == null){
         return "";
      }
      Calendar calendar = Calendar.getInstance();
      calendar.setTimeZone(DEFAULT_TIMEZONE);
      calendar.setTime(date);
      format = (format != null) ? format : DEFAULT_FORMAT;
      format = format.toLowerCase();
      if(split == 0){
         split = DEFAULT_FORMAT_CHAR;
      }
      int nYear = calendar.get(Calendar.YEAR);
      format = format.replaceAll("yyyy", RString.leftPad(Integer.toString(nYear), 4, split));
      format = format.replaceAll("yy", RString.leftPad(RString.right(Integer.toString(nYear), 2), 2, split));
      format = format.replaceAll("mm", RString.leftPad(Integer.toString(calendar.get(Calendar.MONTH) + 1), 2, split));
      format = format.replaceAll("dd", RString.leftPad(Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)), 2, split));
      format = format.replaceAll("hh24", RString.leftPad(Integer.toString(calendar.get(Calendar.HOUR_OF_DAY)), 2, split));
      format = format.replaceAll("hh", RString.leftPad(Integer.toString(calendar.get(Calendar.HOUR_OF_DAY)), 2, split));
      format = format.replaceAll("mi", RString.leftPad(Integer.toString(calendar.get(Calendar.MINUTE)), 2, split));
      format = format.replaceAll("ss", RString.leftPad(Integer.toString(calendar.get(Calendar.SECOND)), 2, split));
      format = format.replaceAll("ms", RString.leftPad(Integer.toString(calendar.get(Calendar.MILLISECOND)), 3, split));
      return format;
   }

   //============================================================
   // <T>格式化当前时间为默认格式。</T>
   //
   // @param date 时间
   // @param format 格式
   // @return 格式化字符串
   //============================================================
   public static String format(String format){
      return format(Calendar.getInstance().getTime(), format, DEFAULT_FORMAT_CHAR);
   }

   //============================================================
   // <T>格式化指定时间字符串为指定格式。</T>
   //
   // @param value 时间字符串
   // @param format 格式
   // @return 格式化字符串
   //============================================================
   public static String format(String value,
                               String format){
      return format(parse(value), format);
   }

   //============================================================
   // <T>格式化指定时间字符串为指定格式。</T>
   //
   // @param value 时间字符串
   // @param parse 解析格式
   // @param format 格式
   // @return 格式化字符串
   //============================================================
   public static String format(String value,
                               String parse,
                               String format){
      return format(parse(value, parse), format);
   }

   //============================================================
   // <T>分割时间格式为字符串数组。</T>
   //
   // @param format 指定格式
   // @return 字符串数组
   //============================================================
   public static String[] split(String format){
      FStrings items = new FStrings();
      FString buffer = new FString();
      char[] chars = format.toLowerCase().toCharArray();
      int length = chars.length;
      for(int n = 0; n < length; n++){
         String sub = null;
         if(n <= (length - 4)){
            char ch1 = chars[n];
            char ch2 = chars[n + 1];
            char ch3 = chars[n + 2];
            char ch4 = chars[n + 3];
            if((ch1 == 'y') && (ch2 == 'y') && (ch3 == 'y') && (ch4 == 'y')){
               sub = "yyyy";
            }else if((ch1 == 'h') && (ch2 == 'h') && (ch3 == '2') && (ch4 == '4')){
               sub = "hh24";
            }
         }
         if(n <= (length - 2)){
            char ch1 = chars[n];
            char ch2 = chars[n + 1];
            if((ch1 == 'y') && (ch2 == 'y')){
               sub = "yy";
            }else if((ch1 == 'm') && (ch2 == 'm')){
               sub = "mm";
            }else if((ch1 == 'd') && (ch2 == 'd')){
               sub = "dd";
            }else if((ch1 == 'm') && (ch2 == 'i')){
               sub = "mi";
            }else if((ch1 == 's') && (ch2 == 's')){
               sub = "ss";
            }
         }
         if(sub != null){
            if(buffer.length() > 0){
               items.push(buffer.toString());
               buffer.clear();
            }
            items.push(sub);
            n += sub.length() - 1;
         }else{
            buffer.append(chars[n]);
         }
      }
      if(buffer.length() > 0){
         items.push(buffer.toString());
      }
      return items.toObjects();
   }

   //============================================================
   // <T>替换指定时间格式为字符串。</T>
   //
   // @param format 指定格式
   // @param year 年字符串
   // @param month 月字符串
   // @param day 天字符串
   // @param hour 小时字符串
   // @param minute 月字符串
   // @param second 秒字符串
   // @return 字符串
   //============================================================
   public static String replace(String format,
                                String year,
                                String month,
                                String day,
                                String hour,
                                String minute,
                                String second){
      FString result = new FString();
      String[] formats = split(format);
      int count = formats.length;
      for(int n = 0; n < count; n++){
         String item = formats[n];
         if((year != null) && item.equals("yyyy")){
            result.append(year);
         }else if((month != null) && item.equals("mm")){
            result.append(month);
         }else if((day != null) && item.equals("dd")){
            result.append(day);
         }else if((hour != null) && item.equals("hh24")){
            result.append(hour);
         }else if((minute != null) && item.equals("mi")){
            result.append(minute);
         }else if((second != null) && item.equals("ss")){
            result.append(second);
         }else{
            result.append(item);
         }
      }
      return result.toString();
   }
}
