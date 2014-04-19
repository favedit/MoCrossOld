package org.mo.com.lang.type;

import java.util.Date;
import org.mo.com.lang.RDateTime;

//============================================================
// <T>时间数据类型。</T>
// <P>该类型只包含时间部分。</P>
//
// @history 130221 创建
//============================================================
public class TTime
      extends MType
{
   // 存储内容
   protected long _value;

   //============================================================
   // <T>构造时间数据类型。</T>
   //============================================================
   public TTime(){
   }

   //============================================================
   // <T>构造时间数据类型。</T>
   //
   // @param tick 1970.1.1起至今的毫秒数。
   //============================================================
   public TTime(long tick){
      _value = tick % RDateTime.TicksPerDay;
   }

   //============================================================
   // <T>构造时间数据类型。</T>
   //
   // @param value date类型日期
   //============================================================
   public TTime(Date value){
      _value = value.getTime() % RDateTime.TicksPerDay;
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
   public void set(Date value){
      _value = value.getTime();
   }

   //============================================================
   // <T>设置数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void set(TTime value){
      _value = value.get();
   }
   //   /**
   //    * <T>给当前时间加上一个long值.</T>
   //    *
   //    * @param amount 毫秒数 
   //    */
   //   public long add(long amount){
   //      long temp = _value + amount;
   //      return temp;
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
   //    * <T>获得当前时间是一天的第几个小时。</T>
   //    *
   //    * @return 当前时第几小时
   //    */
   //   //   public int hour(){
   //   //      return calendar().get(_value, EDataPart.Hour);
   //   //   }
   //
   //   /**
   //    * <T>比较该对象当前代表的时间是否晚于比较对象。</T>
   //    *
   //    * @return 真或假
   //    *     <L value='true'>晚于比较对象</L>
   //    *     <L value='false'>早于或等于比较对象</L>
   //    */
   //   public boolean isAfter(TTime date){
   //      return _value > date.get();
   //   }
   //
   //   /**
   //    * <T>比较该对象当前代表的时间是否早于比较对象。</T>
   //    *
   //    * @return 真或假
   //    *     <L value='true'>早于比较对象</L>
   //    *     <L value='false'>晚于或等于比较对象</L>
   //    */
   //   public boolean isBefore(TTime date){
   //      return _value < date.get();
   //   }
   //
   //   /**
   //    * <T>比较该对象当前代表的时间是在两者之间。</T>
   //    * <P>该比较是全闭比较。</P>
   //    *
   //    * @return 真或假
   //    *     <L value='true'>在两者之间</L>
   //    *     <L value='false'>不在两者之间</L>
   //    */
   //   public boolean isBetween(TTime start,
   //                            TTime end){
   //      return (_value >= start.get() && _value <= end.get());
   //   }
   //
   //   /**
   //    * <T>比较该对象当前代表的时间是否处于两个时间之间。</Tp>
   //    * <P>该比较是左闭右开比较。</P>
   //    *
   //    * @return 真或假
   //    *     <L value='true'>在两者之间</L>
   //    *     <L value='false'>不在两者之间</L>
   //    */
   //   public boolean isGaEoL(TTime start,
   //                          TTime end){
   //      return (_value >= start.get() && _value < end.get());
   //   }
   //
   //   //   /**
   //   //    * <T>得到当前日历的毫秒数。</T>
   //   //    * 
   //   //    * @return 当前毫秒数
   //   //    */
   //   //   public int milliSecond(){
   //   //      return calendar().get(_value, EDataPart.MilliSecond);
   //   //   }
   //   //
   //   //   /**
   //   //    * <T>得到当前日历的分钟数。</T>
   //   //    * 
   //   //    * @return 当前分钟数
   //   //    */
   //   //   public int minute(){
   //   //      return calendar().get(_value, EDataPart.Minute);
   //   //   }
   //   //
   //   //   /**
   //   //    * <T>得到当前日历的秒数。</T>
   //   //    * 
   //   //    * @return 当前秒数
   //   //    */
   //   //   public int second(){
   //   //      return calendar().get(_value, EDataPart.Second);
   //   //   }
   //   /**
   //    * <T>获得当前时间和制定时间之间的各项差值。</T>
   //    * 
   //    * @param date 指定比较对象
   //    * @return
   //    */
   //   public TTimeSpan sub(TTime date){
   //      return new TTimeSpan(_value - date.get());
   //   }
   //
   //   // 日历对象
   //   //private FCultureCalender _calendar;
}
