package org.mo.com.lang.type;

import java.util.Calendar;
import java.util.Date;
import org.mo.com.lang.RDateTime;
import org.mo.com.lang.RInteger;
import org.mo.com.lang.RString;

//============================================================
// <T>日期时间数据类型。</T>
// <P>该时间类型属于完整的时间类型(包含日期和时间两部分)。</P>
//
// @history 130221 创建
//============================================================
public class TDateTime
      extends MType
{
   // 年
   protected int _year;

   // 月
   protected int _month;

   // 日
   protected int _day;

   // 时
   protected int _hour;

   // 分
   protected int _minute;

   // 秒
   protected int _second;

   // 毫秒
   protected int _millisecond;

   // 内容
   protected long _value;

   //============================================================
   // <T>构造日期时间数据类型。</T>
   //============================================================
   public TDateTime(){
   }

   //============================================================
   // <T>构造日期时间数据类型。</T>
   //
   // @param value 数据内容
   //============================================================
   public TDateTime(TDateTime value){
      _value = value._value;
   }

   //============================================================
   // <T>构造日期时间数据类型。</T>
   //
   // @param value 数据内容
   //============================================================
   public TDateTime(long value){
      _value = value;
   }

   //============================================================
   // <T>构造日期时间数据类型。</T>
   //
   // @param value 数据内容
   //============================================================
   public TDateTime(Date value){
      _value = value.getTime();
   }

   //============================================================
   // <T>构造日期时间数据类型。</T>
   //
   // @param value 数据内容
   //============================================================
   public TDateTime(String value){
      parse(value);
   }

   //============================================================
   // <T>构造日期时间数据类型。</T>
   //
   // @param value 数据内容
   // @param format 格式内容
   //============================================================
   public TDateTime(String value,
                    String format){
      parse(value, format);
   }

   //============================================================
   // <T>构造日期时间数据类型。</T>
   //
   // @param value 数据内容
   //============================================================
   public TDateTime(int year,
                    int month,
                    int day){
      _year = year;
      _month = month;
      _day = day;
   }

   //============================================================
   // <T>构造日期时间数据类型。</T>
   //
   // @param value 数据内容
   //============================================================
   public TDateTime(int year,
                    int month,
                    int day,
                    int hour,
                    int minute,
                    int second,
                    int millisecond){
      _year = year;
      _month = month;
      _day = day;
      _hour = hour;
      _minute = minute;
      _second = second;
      _millisecond = millisecond;
   }

   //============================================================
   // <T>获得是否为空。</T>
   //
   // @return 是否为空
   //============================================================
   public boolean isEmpty(){
      return (0 == _value);
   }

   //============================================================
   // <T>获得是否相等。</T>
   //
   // @param value 内容
   // @return 是否相等
   //============================================================
   public boolean equals(TDateTime value){
      if(null == value){
         return false;
      }
      return _value == value._value;
   }

   //============================================================
   // <T>获得是否相等。</T>
   //
   // @param value 内容
   // @return 是否相等
   //============================================================
   public boolean equals(TDateTime value,
                         long span){
      if(null == value){
         return false;
      }
      long sourceValue = _value - (_value % span);
      long targetValue = value._value - (value._value % span);
      return sourceValue == targetValue;
   }

   //============================================================
   // <T>获得日期部分是否相等。</T>
   //
   // @param value 内容
   // @return 是否相等
   //============================================================
   public boolean equalsDate(TDateTime value){
      if(null == value){
         return false;
      }else{
         // 解析数据
         parse();
         value.parse();
      }
      // 比较年月日
      if(_year != value._year){
         return false;
      }
      if(_month != value._month){
         return false;
      }
      if(_day != value._day){
         return false;
      }
      return true;
   }

   //============================================================
   // <T>比较当前日期时间是否早于指定日期时间。</T>
   //
   // @param value 指定日期时间
   //============================================================
   public boolean isBefore(TDateTime value){
      return _value < value.get();
   }

   //============================================================
   // <T>比较当前日期时间是否晚于指定日期时间。</T>
   //
   // @param value 指定日期时间
   //============================================================
   public boolean isAfter(TDateTime value){
      return _value > value.get();
   }

   //============================================================
   // <T>比较该对象当前代表的时间是否处于两个时间之间。</T>
   // <P>该比较是左闭右开比较。</P>
   //
   // @param start 开始日期时间
   // @param end 结束日期时间
   //============================================================
   public boolean inRange(TDateTime start,
                          TDateTime end){
      return (_value >= start.get() && _value < end.get());
   }

   //============================================================
   // <T>比较该对象当前代表的时间是否处于两个时间之间。</T>
   // <P>该比较是全闭比较。</P>
   //
   // @param start 开始日期时间
   // @param end 结束日期时间
   //============================================================
   public boolean isBetween(TDateTime start,
                            TDateTime end){
      return (_value >= start.get() && _value <= end.get());
   }

   //============================================================
   // <T>获得年。</T>
   //
   // @return 年
   //============================================================
   public int year(){
      return _year;
   }

   //============================================================
   // <T>设置年。</T>
   //
   // @param year 年
   //============================================================
   public void setYear(int year){
      _year = year;
   }

   //============================================================
   // <T>获得月。</T>
   //
   // @return 月
   //============================================================
   public int month(){
      return _month;
   }

   //============================================================
   // <T>设置月。</T>
   //
   // @param month 月
   //============================================================
   public void setMonth(int month){
      _month = month;
   }

   //============================================================
   // <T>获得日。</T>
   //
   // @return 日
   //============================================================
   public int day(){
      return _day;
   }

   //============================================================
   // <T>设置日。</T>
   //
   // @param day 日
   //============================================================
   public void setDay(int day){
      _day = day;
   }

   //============================================================
   // <T>获得时。</T>
   //
   // @return 时
   //============================================================
   public int hour(){
      return _hour;
   }

   //============================================================
   // <T>设置时。</T>
   //
   // @param hour 时
   //============================================================
   public void setHour(int hour){
      _hour = hour;
   }

   //============================================================
   // <T>获得分。</T>
   //
   // @return 分
   //============================================================
   public int minute(){
      return _minute;
   }

   //============================================================
   // <T>设置分。</T>
   //
   // @param minute 分
   //============================================================
   public void setMinute(int minute){
      _minute = minute;
   }

   //============================================================
   // <T>获得秒。</T>
   //
   // @return 秒
   //============================================================
   public int second(){
      return _second;
   }

   //============================================================
   // <T>设置秒。</T>
   //
   // @param second 秒
   //============================================================
   public void setSecond(int second){
      _second = second;
   }

   //============================================================
   // <T>获得毫秒。</T>
   //
   // @return 毫秒
   //============================================================
   public int millisecond(){
      return _millisecond;
   }

   //============================================================
   // <T>设置毫秒。</T>
   //
   // @param millisecond 毫秒
   //============================================================
   public void setMillisecond(int millisecond){
      _millisecond = millisecond;
   }

   //============================================================
   // <T>给当前时间加上一个毫秒内容。</T>
   //
   // @param value 毫秒数
   //============================================================
   public void add(long value){
      _value += value;
   }

   //============================================================
   // <T>给当前时间加上一个时间。</T>
   //
   // @param value 时间
   //============================================================
   public void add(TTime time){
      _value = _value + time.get();
   }

   //============================================================
   // <T>给当前时间加上一个日期。</T>
   //
   // @param value 日期
   //============================================================
   public void add(TDate date){
      _value = _value + date.get();
   }

   //============================================================
   // <T>给当前时间加上一个时间段。</T>
   //
   // @param value 时间段
   //============================================================
   public void add(TTimeSpan span){
      _value = _value + span.get();
   }

   //============================================================
   // <T>给当前时间加上指定天数。</T>
   //
   // @param value 天数
   //============================================================
   public void addDay(int value){
      _value += 1000 * 60 * 60 * 24 * value;
   }

   //============================================================
   // <T>给当前时间加上指定小时数。</T>
   //
   // @param value 小时数
   //============================================================
   public void addHour(int value){
      _value += 1000 * 60 * 60 * value;
   }

   //============================================================
   // <T>给当前时间加上指定分钟数。</T>
   //
   // @param value 分钟数
   //============================================================
   public void addMinute(int value){
      _value += 1000 * 60 * value;
   }

   //============================================================
   // <T>给当前时间加上指定秒数。</T>
   //
   // @param value 秒数
   //============================================================
   public void addSecond(int value){
      _value += 1000 * value;
   }

   //============================================================
   // <T>获得数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long get(){
      return _value;
   }

   //============================================================
   // <T>设置数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void set(long value){
      _value = value;
   }

   //============================================================
   // <T>设置数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void set(Date date){
      _value = date.getTime();
   }

   //============================================================
   // <T>设置数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void set(TDateTime dateTime){
      _value = dateTime.get();
   }

   //============================================================
   // <T>得到时间部分。</T>
   //
   // @return 时间部分
   //============================================================
   public TTime time(){
      return new TTime(_value % RDateTime.TicksPerDay);
   }

   //============================================================
   // <T>得到日期部分。</T>
   //
   // @return 日期部分
   //============================================================
   public TDate date(){
      return new TDate(_value - (_value % RDateTime.TicksPerDay));
   }

   //============================================================
   // <T>获得当前时间和指定时间之间的时间段。</T>
   //
   // @return 时间段
   //============================================================
   public TTimeSpan sub(TDateTime value){
      return new TTimeSpan(_value - value.get());
   }

   //============================================================
   // <T>截取指定单位的内容。</T>
   //
   // @param value 内容
   //============================================================
   public void truncate(long value){
      _value -= _value % value;
   }

   //============================================================
   // <T>截取单位到分钟。</T>
   //
   // @param minute 分钟
   //============================================================
   public void truncateMinute(){
      _value -= _value % RDateTime.TicksPerMinute;
   }

   //============================================================
   // <T>截取单位到小时。</T>
   //
   // @param hour 小时
   //============================================================
   public void truncateHour(){
      _value -= _value % RDateTime.TicksPerHour;
   }

   //============================================================
   // <T>截取单位到天。</T>
   //
   // @param day 天
   //============================================================
   public void truncateDay(){
      Calendar calendar = Calendar.getInstance();
      calendar.setTimeZone(RDateTime.DEFAULT_TIMEZONE);
      calendar.setTimeInMillis(_value);
      int year = calendar.get(Calendar.YEAR);
      int month = calendar.get(Calendar.MONTH);
      int day = calendar.get(Calendar.DAY_OF_MONTH);
      calendar.set(year, month, day, 0, 0, 0);
      _value = calendar.getTimeInMillis();
   }

   //============================================================
   // <T>截取单位到周。</T>
   //
   // @param week 周
   //============================================================
   public void truncateWeek(){
      Calendar calendar = Calendar.getInstance();
      calendar.setTimeZone(RDateTime.DEFAULT_TIMEZONE);
      calendar.setTimeInMillis(_value);
      int year = calendar.get(Calendar.YEAR);
      int month = calendar.get(Calendar.MONTH);
      int monthDay = calendar.get(Calendar.DAY_OF_MONTH);
      int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
      calendar.set(year, month, monthDay - weekDay + 1, 0, 0, 0);
      _value = calendar.getTimeInMillis();
   }

   //============================================================
   // <T>截取单位到周。</T>
   //
   // @param week 周
   //============================================================
   public void truncateMonth(){
      //      // 创建日期内容
      //      Calendar calendar = Calendar.getInstance();
      //      calendar.setTimeZone(RDateTime.DEFAULT_TIMEZONE);
      //      calendar.setTimeInMillis(_value);
      //      int monthDay = calendar.get(Calendar.DAY_OF_MONTH) - 1;
      //      _value -= RDateTime.TicksPerDay * monthDay;
      //      if((_value % RDateTime.TicksPerDay) != 0){
      //         _value -= _value % RDateTime.TicksPerDay;
      //         _value -= RDateTime.DEFAULT_TIMEZONE_VALUE;
      //      }
      Calendar calendar = Calendar.getInstance();
      calendar.setTimeZone(RDateTime.DEFAULT_TIMEZONE);
      calendar.setTimeInMillis(_value);
      int year = calendar.get(Calendar.YEAR);
      int month = calendar.get(Calendar.MONTH);
      calendar.set(year, month, 1, 0, 0, 0);
      _value = calendar.getTimeInMillis();
   }

   //============================================================
   // <T>接收内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void assign(TDateTime value){
      _isNull = value._isNull;
      _year = value._year;
      _month = value._month;
      _day = value._day;
      _hour = value._hour;
      _minute = value._minute;
      _second = value._second;
      _value = value._value;
   }

   //============================================================
   // <T>合并时间部分称为数字。</T>
   //
   // @return 是否成功
   //============================================================
   public boolean merge(){
      Calendar calendar = Calendar.getInstance();
      calendar.setTimeZone(RDateTime.DEFAULT_TIMEZONE);
      if(_day > 0){
         _month--;
      }
      calendar.set(_year, _month, _day, _hour, _minute, _second);
      calendar.set(Calendar.MILLISECOND, _millisecond);
      _value = calendar.getTime().getTime();
      _isNull = false;
      return true;
   }

   //============================================================
   // <T>格式化时间为指定格式。</T>
   //
   // @return 格式化字符串
   //============================================================
   public String format(){
      return format("YYYYMMDDHH24MISS", '0');
   }

   //============================================================
   // <T>格式化时间为指定格式。</T>
   //
   // @param format 格式
   // @return 格式化字符串
   //============================================================
   public String format(String format){
      return format(format, '0');
   }

   //============================================================
   // <T>格式化时间为指定格式。</T>
   //
   // @param format 格式
   // @param split 分隔符
   // @return 格式化字符串
   //============================================================
   public String format(String format,
                        char split){
      // 检查数据
      if(0 == _value){
         return "";
      }
      // 设置日历
      Calendar calendar = Calendar.getInstance();
      calendar.setTimeZone(RDateTime.DEFAULT_TIMEZONE);
      calendar.setTime(new Date(_value));
      format = (null != format) ? format : RDateTime.DEFAULT_FORMAT;
      format = format.toLowerCase();
      if(0 == split){
         split = RDateTime.DEFAULT_FORMAT_CHAR;
      }
      int nYear = calendar.get(Calendar.YEAR);
      // 格式化字符串
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
   // <T>分解内容成为部分时间。</T>
   //
   // @return 处理结果
   //============================================================
   public void parse(){
      // 创建日期内容
      Calendar calendar = Calendar.getInstance();
      calendar.setTimeZone(RDateTime.DEFAULT_TIMEZONE);
      calendar.setTimeInMillis(_value);
      _year = calendar.get(Calendar.YEAR);
      _month = calendar.get(Calendar.MONTH) + 1;
      _day = calendar.get(Calendar.DAY_OF_MONTH);
      _hour = calendar.get(Calendar.HOUR_OF_DAY);
      _minute = calendar.get(Calendar.MINUTE);
      _second = calendar.get(Calendar.SECOND);
      _millisecond = calendar.get(Calendar.MILLISECOND);
   }

   //============================================================
   // <T>分解日期内容成为部分时间。</T>
   //
   // @return 处理结果
   //============================================================
   public void parseDate(){
      // 创建日期内容
      Calendar calendar = Calendar.getInstance();
      calendar.setTimeZone(RDateTime.DEFAULT_TIMEZONE);
      calendar.setTimeInMillis(_value);
      _year = calendar.get(Calendar.YEAR);
      _month = calendar.get(Calendar.MONTH) + 1;
      _day = calendar.get(Calendar.DAY_OF_MONTH);
   }

   //============================================================
   // <T>分解字符串为指定格式的时间类型。</T>
   //
   // @param value 字符串
   // @return 时间
   //============================================================
   public boolean parse(String value){
      return parse(value, "YYYYMMDDHH24MISS");
   }

   //============================================================
   // <T>分解字符串为指定格式的时间类型。</T>
   //
   // @param value 字符串
   // @param format 指定格式
   // @return 时间
   //============================================================
   public boolean parse(String value,
                        String format){
      // 清除内容
      _isNull = true;
      _year = 0;
      _month = 0;
      _day = 0;
      _hour = 0;
      _minute = 0;
      _second = 0;
      _value = 0;
      // 参数检查
      if((null == value) || (null == format)){
         return false;
      }
      if((0 == value.length()) || (0 == format.length())){
         return false;
      }
      // 分解时间部分
      int millisecond = 0;
      int valuePosition = 0;
      boolean isDate = false;
      boolean isTime = false;
      String temp = "";
      char[] chars = format.toLowerCase().toCharArray();
      int length = chars.length;
      for(int n = 0; n < length; n++){
         temp += chars[n];
         if(temp.equals("yyyy")){
            isDate = true;
            _year = RInteger.parse(value.substring(valuePosition, valuePosition + 4));
            valuePosition += 4;
            temp = "";
         }else if(temp.equals("mm")){
            _month = RInteger.parse(value.substring(valuePosition, valuePosition + 2));
            valuePosition += 2;
            temp = "";
         }else if(temp.equals("dd")){
            _day = RInteger.parse(value.substring(valuePosition, valuePosition + 2));
            valuePosition += 2;
            temp = "";
         }else if(temp.equals("hh24")){
            isTime = true;
            _hour = RInteger.parse(value.substring(valuePosition, valuePosition + 2));
            valuePosition += 2;
            temp = "";
         }else if(temp.equals("mi")){
            _minute = RInteger.parse(value.substring(valuePosition, valuePosition + 2));
            valuePosition += 2;
            temp = "";
         }else if(temp.equals("ss")){
            _second = RInteger.parse(value.substring(valuePosition, valuePosition + 2));
            valuePosition += 2;
            temp = "";
         }else if(temp.equals("ms")){
            millisecond = RInteger.parse(value.substring(valuePosition, valuePosition + 3));
            valuePosition += 3;
            temp = "";
         }else{
            if(temp.length() == 1){
               // 清除掉多余字符
               if(RDateTime.DATE_CHARS.indexOf(temp) == -1){
                  valuePosition++;
                  temp = "";
               }
            }
         }
      }
      // 创建日期内容
      Calendar calendar = Calendar.getInstance();
      calendar.setTimeZone(RDateTime.DEFAULT_TIMEZONE);
      if(_day > 0){
         _month--;
      }
      if(isDate && isTime){
         calendar.set(_year, _month, _day, _hour, _minute, _second);
         calendar.set(Calendar.MILLISECOND, millisecond);
         _value = calendar.getTime().getTime();
      }else if(isDate){
         calendar.set(_year, _month, _day, 0, 0, 0);
         _value = calendar.getTime().getTime();
      }else if(isTime){
         calendar.set(0, 0, 0, _hour, _minute, _second);
         calendar.set(Calendar.MILLISECOND, millisecond);
         _value = calendar.getTime().getTime();
      }
      _isNull = false;
      return true;
   }

   //============================================================
   // <T>复制自己的实例。</T>
   //
   // @return 实例;
   //============================================================
   @Override
   public TDateTime clone(){
      TDateTime datetime = new TDateTime();
      datetime.assign(this);
      return datetime;
   }

   //============================================================
   // <T>获得内容字符串。</T>
   //
   // @return 字符串
   //============================================================
   @Override
   public String toString(){
      return format();
   }
}
