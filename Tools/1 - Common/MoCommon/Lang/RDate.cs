using System;

namespace MO.Common.Lang
{
   //============================================================
   // <T>时间工具类。</T>
   //
   // @history 110125 MAOCY 创建
   //============================================================
   public class RDate
   {
      // 默认格式
      public const string DEFAULT_FORMAT = "yyyymmddhh24miss";

      // 默认格式填充字符
      public const char DEFAULT_FORMAT_CHAR = '0';

      // 月份总数
      public const int MONTHS = 12;

      // 周天数
      public const int WEEK_DAYS = 7;

      //============================================================
      // <T>分割格式字符串。</T>
      //
      // @param value 格式字符串
      // @return 字符串数组
      //============================================================
      protected static String[] Split(String format) {
         FStrings items = new FStrings();
         FString buffer = new FString();
         char[] chars = format.ToLower().ToCharArray();
         int length = chars.Length;
         for (int n = 0; n < length; n++) {
            String sub = null;
            if (n <= (length - 4)) {
               if (('y' == chars[n]) && ('y' == chars[n + 1]) && ('y' == chars[n + 2]) && ('y' == chars[n + 3])) {
                  sub = "yyyy";
               } else if (('h' == chars[n]) && ('h' == chars[n + 1]) && ('2' == chars[n + 2]) && ('4' == chars[n + 3])) {
                  sub = "hh24";
               }
            }
            if (sub == null && n <= (length - 2)) {
               if (('y' == chars[n]) && ('y' == chars[n + 1])) {
                  sub = "yy";
               } else if (('m' == chars[n]) && ('m' == chars[n + 1])) {
                  sub = "mm";
               } else if (('d' == chars[n]) && ('d' == chars[n + 1])) {
                  sub = "dd";
               } else if (('m' == chars[n]) && ('i' == chars[n + 1])) {
                  sub = "mi";
               } else if (('s' == chars[n]) && ('s' == chars[n + 1])) {
                  sub = "ss";
               }
            }
            if (sub != null) {
               if (!buffer.IsEmpty) {
                  items.Push(buffer.Flush());
               }
               items.Push(sub);
               n += sub.Length - 1;
            } else {
               buffer.Append(format[n]);
            }
         }
         if (!buffer.IsEmpty) {
            items.Push(buffer.Flush());
         }
         return items.ToArray();
      }

      //============================================================
      // <T>格式化当前日期时间对象为默认格式的字符串。</T>
      //
      // @return 字符串
      //============================================================
      public static string Format() {
         return Format(DateTime.Now, DEFAULT_FORMAT, true);
      }

      //============================================================
      // <T>格式化当前日期时间对象为指定默认格式的字符串。</T>
      //
      // @param format 格式字符串
      // @return 字符串
      //============================================================
      public static string Format(string format) {
         return Format(DateTime.Now, format, true);
      }

      //============================================================
      // <T>格式化日期时间对象为默认格式的字符串。</T>
      //
      // @param datetime 日期时间对象
      // @return 字符串
      //============================================================
      public static string Format(DateTime datetime) {
         return Format(datetime, DEFAULT_FORMAT, true);
      }

      //============================================================
      // <T>格式化日期时间对象为指定格式的字符串。</T>
      //
      // @param datetime 日期时间对象
      // @param format 格式字符串
      // @return 字符串
      //============================================================
      public static string Format(DateTime datetime,
                                  String format) {
         return Format(datetime, format, true);
      }

      //============================================================
      // <T>格式化日期时间对象为指定格式的字符串。</T>
      //
      // @param datetime 日期时间对象
      // @param format 格式字符串
      // @param align 是否对齐
      // @return 字符串
      //============================================================
      public static string Format(DateTime datetime,
                                  String format,
                                  bool align) {
         String[] items = Split(RString.Nvl(format, DEFAULT_FORMAT));
         FString result = new FString();
         foreach (string item in items) {
            if ("yyyy" == item) {
               result.Append(datetime.Year.ToString("D4"));
            } else if ("yy" == item) {
               result.Append((datetime.Year % 100).ToString("D2"));
            } else if ("mm" == item) {
               result.Append(datetime.Month.ToString("D2"));
            } else if ("dd" == item) {
               result.Append(datetime.Day.ToString("D2"));
            } else if ("hh24" == item) {
               result.Append(datetime.Hour.ToString("D2"));
            } else if ("hh" == item) {
               result.Append((datetime.Hour).ToString("D2"));
            } else if ("mi" == item) {
               result.Append(datetime.Minute.ToString("D2"));
            } else if ("ss" == item) {
               result.Append(datetime.Second.ToString("D2"));
            } else if ("ms" == item) {
               result.Append(datetime.Millisecond.ToString("D3"));
            } else {
               result.Append(item);
            }
         }
         return result.ToString();
      }

      //============================================================
      // <T>解析字符串为日期时间对象。</T>
      //
      // @param value 字符串
      // @return 日期时间对象
      //============================================================
      public static DateTime Parse(string value) {
         if (value != null) {
            int length = value.Length;
            switch (length) {
               case 14:
                  return DateTime.ParseExact(value, "yyyyMMddHHmmss", null);
               case 8:
                  return DateTime.ParseExact(value, "yyyyMMdd", null);
               case 6:
                  return DateTime.ParseExact(value, "HHmmss", null);
            }
         }
         return default(DateTime);
      }

      //============================================================
      // <T>解析字符串为日期时间对象。</T>
      //
      // @param value 字符串
      // @param format 格式化字符串
      // @return 日期时间对象
      //============================================================
      public static DateTime Parse(string value, string format) {
         return DateTime.ParseExact(value, format, null);
      }
 
      //============================================================
      // <T>获得日期时间的当月第一天的日期时间。</T>
      //
      // @param date 日期时间对象
      // @return 日期时间对象
      //============================================================
      public static DateTime FirstDateForMonth(DateTime date) {
         return new DateTime(date.Year, date.Month, 1);
      }

      //============================================================
      // <T>获得日期时间的当月最后一天的日期时间。</T>
      //
      // @param date 日期时间对象
      // @return 日期时间对象
      //============================================================
      public static DateTime LastDateForMonth(DateTime date) {
         int year = date.Year;
         int month = date.Month;
         return new DateTime(year, month, DateTime.DaysInMonth(year, month));
      }
   }
}
