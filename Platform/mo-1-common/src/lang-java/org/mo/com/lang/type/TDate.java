package org.mo.com.lang.type;

import java.util.Date;
import org.mo.com.lang.RDateTime;

//============================================================
// <T>日期数据类型。</T>
// <P>该类型保存数据的日期部分。</P>
//
// @history 130221 创建
//============================================================
public class TDate
      extends MType
{
   // 存储内容
   protected long _value;

   //============================================================
   // <T>构造日期数据类型。</T>
   //============================================================
   public TDate(){
   }

   //============================================================
   // <T>构造日期数据类型。</T>
   //
   // @param value 1970.1.1起至今的毫秒数。
   //============================================================
   public TDate(long value){
      _value = value;
   }

   //============================================================
   // <T>构造日期数据类型。</T>
   //
   // @param value 日期类型
   //============================================================
   public TDate(Date value){
      _value = value.getTime() - (value.getTime() / RDateTime.TicksPerDay);
   }

   //============================================================
   // <T>比较该对象当前代表的时间是否晚于比较对象。</T>
   //
   // @return 是否晚于
   //============================================================
   public boolean isAfter(TDate date){
      return _value > date.get();
   }

   //============================================================
   // <T>比较该对象当前代表的时间是否早于比较对象。</T>
   //
   // @return 是否早于
   //============================================================
   public boolean isBefore(TDate date){
      return _value < date.get();
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
      _value = (value / RDateTime.TicksPerDay) * RDateTime.TicksPerDay;
   }

   //============================================================
   // <T>设置数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void set(Date date){
      if(null != date){
         _value = (date.getTime() / RDateTime.TicksPerDay) * RDateTime.TicksPerDay;
      }else{
         //_isNotNull = false;
      }
   }
   //   /**
   //    * <T>给当前时间加上一个long值.</T>
   //    *
   //    * @param amount 日期
   //    */
   //   public void add(long amount){
   //      _value += amount;
   //   }
   //
   //   /**
   //    * <T>得到时间类型的日历对象。</T>
   //    */
   //   //   protected FCultureCalender calendar(){
   //   //      if(null == _calendar){
   //   //         _calendar = RCulture.culture().calender();
   //   //      }
   //   //      return _calendar;
   //   //   }
   //
   //   /**
   //    * <T>得到当前是一个月的第几天。</T>
   //    * 
   //    * @return 今天是第几天
   //    */
   //   //   public int day(){
   //   //      return calendar().get(_value, EDataPart.Day);
   //   //   }
   //
   //
   //   /**
   //    * <T>比较该对象当前代表的时间是在两者之间。</T>
   //    * <P>该比较是全闭比较。</P>
   //    *
   //    * @return 真或假
   //    *     <L value='true'>在两者之间</L>
   //    *     <L value='false'>不在两者之间</L>
   //    */
   //   public boolean isBetween(TDate start,
   //                            TDate end){
   //      return (_value >= start.get() && _value <= end.get());
   //   }
   //
   //   /**
   //    * <T>比较该对象当前代表的时间是否处于两个时间之间。</T>
   //    * <P>该比较是左闭右开比较。</P>
   //    *
   //    * @return 真或假
   //    *     <L value='true'>在两者之间</L>
   //    *     <L value='false'>不在两者之间</L>
   //    */
   //   public boolean isGaEoL(TDate start,
   //                          TDate end){
   //      return (_value >= start.get() && _value < end.get());
   //   }
   //
   //   //   /**
   //   //    * <T>得到当前日历的月份。</T>
   //   //    * 
   //   //    * @return 当前月份
   //   //    */
   //   //   public int month(){
   //   //      return calendar().get(_value, EDataPart.Month);
   //   //   }
   //   //
   //   //   /**
   //   //    * <T>得到当前日历是一个月的第几周。</T>
   //   //    * 
   //   //    * @return 当前月周数
   //   //    */
   //   //   public int monthWeek(){
   //   //      return calendar().get(_value, EDataPart.MonthWeek);
   //   //   }
   //
   //
   //
   //   /**
   //    * <T>获得当前时间和制定时间之间的各项差值。</T>
   //    * 
   //    * @param date 指定比较对象
   //    * @return
   //    */
   //   public TTimeSpan sub(TDate date){
   //      return new TTimeSpan(_value - date.get());
   //   }
   //
   //   //   /**
   //   //    * <T>获得当前日历是一周的第几天。</T>
   //   //    * 
   //   //    * @return 当前周的第几天
   //   //    */
   //   //   public int weekDay(){
   //   //      return calendar().get(_value, EDataPart.WeekDay);
   //   //   }
   //   //
   //   //   /**
   //   //    * <T>获得当前日历的年。</T>
   //   //    * 
   //   //    * @return 当前年
   //   //    */
   //   //   public int year(){
   //   //      return calendar().get(_value, EDataPart.Year);
   //   //   }
   //   //
   //   //   /**
   //   //    * <T>获得当前日历的是该年的第几天。</T>
   //   //    * 
   //   //    * @return 当前年的第几天
   //   //    */
   //   //   public int yearDay(){
   //   //      return calendar().get(_value, EDataPart.YearDay);
   //   //   }
   //   // 日历对象
   //   //private FCultureCalender _calendar;
}
