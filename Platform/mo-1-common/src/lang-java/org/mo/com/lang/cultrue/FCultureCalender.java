package org.mo.com.lang.cultrue;

import java.util.Calendar;
import org.mo.com.lang.EDatePart;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObject;
import org.mo.com.lang.type.TDate;
import org.mo.com.lang.type.TDateTime;

//============================================================
// <T>日历格式。</T>
//============================================================
public class FCultureCalender
      extends FObject
{
   //============================================================
   // <T>构造日历格式。</T>
   //============================================================
   public FCultureCalender(){
   }

   //============================================================
   // <T>接收日历格式。</T>
   //
   // @param cultureCalender 日历格式
   //============================================================
   public void assign(FCultureCalender cultureCalender){
   }

   //============================================================
   // <T>获得时间的部分内容。</T>
   //
   // @param value 内容
   // @param partCd 部分类型
   // @return 部分内容
   //============================================================
   public int get(long value,
                  EDatePart partCd){
      Calendar calendar = Calendar.getInstance();
      calendar.setTimeInMillis(value);
      switch(partCd){
         case Year:
            return calendar.get(Calendar.YEAR);
         case Month:
            return calendar.get(Calendar.MONTH) + 1;
         case MonthWeek:
            return calendar.get(Calendar.WEEK_OF_MONTH);
         case YearDay:
            return calendar.get(Calendar.DAY_OF_YEAR);
         case Day:
            return calendar.get(Calendar.DAY_OF_MONTH);
         case WeekDay:
            return calendar.get(Calendar.DAY_OF_WEEK) - 1;
         case Hour:
            return calendar.get(Calendar.HOUR_OF_DAY);
         case Minute:
            return calendar.get(Calendar.MINUTE);
         case Second:
            return calendar.get(Calendar.SECOND);
         case MilliSecond:
            return calendar.get(Calendar.MILLISECOND);
      }
      throw new FFatalError("Invalid data part.");
   }

   //============================================================
   // <T>获得时间的部分内容。</T>
   //
   // @param date 日期类型
   // @param partCd 部分类型
   // @return 部分内容
   //============================================================
   public int get(TDate date,
                  EDatePart partCd){
      return get(date.get(), partCd);
   }

   //============================================================
   // <T>获得时间的部分内容。</T>
   //
   // @param date 日期时间类型
   // @param partCd 部分类型
   // @return 部分内容
   //============================================================
   public int get(TDateTime datetime,
                  EDatePart partCd){
      return get(datetime.get(), partCd);
   }
   //   public void assign(FStringList attributes){
   //   }
   //
   //   public void construct(FXmlNode config){
   //   }
   //
   //   public long makeTick(int year, int month, int day){
   //      Calendar calendar = Calendar.getInstance();
   //      calendar.clear();
   //      calendar.set(Calendar.YEAR, year);
   //      calendar.set(Calendar.MONTH, month - 1);
   //      calendar.set(Calendar.DAY_OF_MONTH, day);
   //      return calendar.getTimeInMillis();
   //   }
   //
   //   public long makeTick(int year, int month, int day, int hour, int minute, int second, int millisecond){
   //      Calendar calendar = Calendar.getInstance();
   //      calendar.clear();
   //      calendar.set(Calendar.YEAR, year);
   //      calendar.set(Calendar.MONTH, month - 1);
   //      calendar.set(Calendar.DAY_OF_MONTH, day);
   //      calendar.set(Calendar.HOUR_OF_DAY, hour);
   //      calendar.set(Calendar.MINUTE, minute);
   //      calendar.set(Calendar.SECOND, second);
   //      calendar.set(Calendar.MILLISECOND, millisecond);
   //      return calendar.getTimeInMillis();
   //   }
}
