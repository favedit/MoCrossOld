#ifdef _MO_WINDOWS
#include <time.h>
#else
#include <sys/time.h>
#endif
#include <sys/timeb.h>
#include <stdlib.h>
#include <time.h>
#include "MoCmLanguage.h"

MO_NAMESPACE_BEGIN

//============================================================
TCharC* RDateTime::DefaultFormat = TC("%Y%m%d %H%M%S");
TCharC* RDateTime::DefaultFormatT = TC("%Y%m%d%H%M%S");

//============================================================
// <T>获得当前时间戳。</T>
//
// @return 时间戳
//============================================================
TDateTime RDateTime::Current(){
#if defined(_MO_WINDOWS) || defined(_MO_LINUX) || defined(_MO_ANDROID)
   // 获得时间
   struct timeb time;
   ftime(&time);
   // 合成时间
   TDateTime second = time.time;
   TDateTime millisecond = MO_TP_DATE_MILLISECONDS_PER_SECONDE * time.millitm;
   return (MO_TP_DATE_MICROSECONDS_PER_SECONDE * second) + millisecond;
#endif // _MO_WINDOWS | _MO_LINUX
#ifdef _MO_FLASCC
   struct timeval time;
   gettimeofday(&time, NULL);
   TDateTime result = (TDateTime)time.tv_sec * 1000000LL + (TDateTime)time.tv_usec;
   return result;
#endif // _MO_FLASCC
}

//============================================================
// <T>解时间。</T>
//
// @param pValue 字符串
// @return 时间
//============================================================
TDateTime RDateTime::Parse(TCharC* pValue){
   return Parse(pValue, DefaultFormat);
}

//============================================================
// <T>根据格式化的格式解析字符串时间。</T>
// <P>%Y 4位年   (1900-2999)</P>
// <P>%y 2位年   (00-99)</P>
// <P>%m 2位月份 (01-12)</P>
// <P>%d 2位天   (00-31)</P>
// <P>%H 2位时   (00-24)</P>
// <P>%M 2位分   (00-60)</P>
// <P>%S 2位秒   (00-60)</P>
// <P>%s 3位毫秒 (00-999)</P>
//
// @param pValue 字符串时间
// @param pFormat 格式
// @return 时间戳
//============================================================
TDateTime RDateTime::Parse(TCharC* pValue, TCharC* pFormat){
   MO_ASSERT(pValue);
   MO_ASSERT(pFormat);
   // 初始化时间结构
   struct tm data;
   memset(&data, 0, sizeof(tm));
   // 分解时间数据
   TChar buffer[5];
   TCharC* pValueReader = pValue;
   TCharC* pFormatReader = pFormat;
   TInt length = RString::Length(pFormat);
   MO_ASSERT(length > 0);
   TInt millisecond = 0;
   while(--length >= 0){
      if(*pFormatReader == '%'){
         pFormatReader++;
         TChar formatCd = *pFormatReader;
         switch(formatCd){
            case 'Y':{
               // 格式化为4位年
               buffer[0] = pValueReader[0];
               buffer[1] = pValueReader[1];
               buffer[2] = pValueReader[2];
               buffer[3] = pValueReader[3];
               buffer[4] = '\0';
               TInt year = RInt::Parse(buffer);
               data.tm_year = year - 1900;
               pValueReader = pValueReader + 4;
               break;
            }
            case 'y':{
               // 格式为2位年
               buffer[0] = pValueReader[0];
               buffer[1] = pValueReader[1];
               buffer[2] = '\0';
               TInt year = RInt::Parse(buffer);
               if(year >= 80){
                  data.tm_year = year;
               }else{
                  data.tm_year = year + 100;
               }
               pValueReader = pValueReader + 2;
               break;
            }
            case 'm':{
               // 格式为月
               buffer[0] = pValueReader[0];
               buffer[1] = pValueReader[1];
               buffer[2] = '\0';
               TInt month = RInt::Parse(buffer);
               MO_ASSERT_RANGE(month, 0, 13);
               data.tm_mon = month - 1;
               pValueReader = pValueReader + 2;
               break;
            }
            case 'd':{
               // 格式为天（日）
               buffer[0] = pValueReader[0];
               buffer[1] = pValueReader[1];
               buffer[2] = '\0';
               TInt day = RInt::Parse(buffer);
               MO_ASSERT_RANGE(day, 0, 32);
               data.tm_mday = day;
               pValueReader = pValueReader + 2;
               break;
            }
            case 'H':{
               // 格式为24小时制小时
               buffer[0] = pValueReader[0];
               buffer[1] = pValueReader[1];
               buffer[2] = '\0';
               TInt hour = RInt::Parse(buffer);
               MO_ASSERT_RANGE(hour, 0, 24);
               data.tm_hour = hour;
               pValueReader = pValueReader + 2;
               break;
            }
            case 'M':{
               // 格式为分钟
               buffer[0] = pValueReader[0];
               buffer[1] = pValueReader[1];
               buffer[2] = '\0';
               TInt minute = RInt::Parse(buffer);
               MO_ASSERT_RANGE(minute, 0, 60);
               data.tm_min = minute;
               pValueReader = pValueReader + 2;
               break;
            }
            case 'S':{
               // 格式为秒
               buffer[0] = pValueReader[0];
               buffer[1] = pValueReader[1];
               buffer[2] = '\0';
               TInt second = RInt::Parse(buffer);
               MO_ASSERT_RANGE(second, 0, 60);
               data.tm_sec = second;
               pValueReader = pValueReader + 2;
               break;
            }
            case 's':{
               // 格式为毫秒
               buffer[0] = pValueReader[0];
               buffer[1] = pValueReader[1];
               buffer[2] = pValueReader[2];
               buffer[3] = '\0';
               millisecond = RInt::Parse(buffer);
               pValueReader = pValueReader + 3;
               break;
            }
            default:{
               MO_STATIC_FATAL(TC("Unknown date code. (code=%d)"), formatCd);
            }
         }
         length--;
      }else{
         pValueReader++;
      }
      pFormatReader++;
   }
   // 合成时间
   TDateTime result = mktime(&data);
   return (result * 1000000) + (millisecond * 1000);
}

//============================================================
TDateTime RDateTime::ParseNvl(TCharC* pValue){
   if(NULL == pValue){
      return 0;
   }
   return Parse(pValue);
}

//============================================================
TDateTime RDateTime::ParseNvl(TCharC* pValue, TCharC* pFormat){
   if(NULL == pValue){
      return 0;
   }
   return Parse(pValue, pFormat);
}

//============================================================
// <T>格式化时间为指定方式字符串。</T>
//
// @param pBuffer 格式化后的时间
// @param length  pBuffer长度
// @param pFormat 格式
// @param dateTime 时间戳
// @return 格式化后的时间
//============================================================
TCharC* RDateTime::ToString(TChar* pBuffer, TSize length, TDateTime dateTime, TCharC* pFormat){
   // 获得时间
   struct tm data;
   time_t time = dateTime / 1000000;
#ifdef _MO_SYS_WINDOWS
   MO_ASSERT_ERRNO(localtime_s(&data, &time));
#endif // _MO_SYS_WINDOWS
#ifdef _MO_SYS_LINUX
   localtime_r(&time, &data);
#endif // _MO_SYS_LINUX
   // 格式化内容
   TChar buffer[8];
   TSize valuePosition = 0;
   TSize formatPosition = 0;
   TSize formatLength = RString::Length(pFormat);
   TChar* pWrite = pBuffer;
   while((formatPosition < formatLength) && (valuePosition < length)){
      if(*pFormat == '%'){       
         pFormat++;
         switch(*pFormat++){
            case 'Y':{
               // 格式化为4位年（如：2005）
               if(dateTime == 0){
                  *pWrite++ = '0';
                  *pWrite++ = '0';
                  *pWrite++ = '0';
                  *pWrite++ = '0';
               }else{
                  TInt year = data.tm_year + 1900;
                  RInt::ToString(year, buffer, 5);
                  if(year < 10){
                     *pWrite++ = '0';
                     *pWrite++ = '0';
                     *pWrite++ = '0';
                     *pWrite++ = buffer[0];
                  }else if(year < 100){
                     *pWrite++ = '0';
                     *pWrite++ = '0';
                     *pWrite++ = buffer[0];
                     *pWrite++ = buffer[1];
                  }else if(year < 1000){
                     *pWrite++ = '0';
                     *pWrite++ = buffer[0];
                     *pWrite++ = buffer[1];
                     *pWrite++ = buffer[2];
                  }else{
                     *pWrite++ = buffer[0];
                     *pWrite++ = buffer[1];
                     *pWrite++ = buffer[2];
                     *pWrite++ = buffer[3];
                  }
                  formatPosition++;
                  valuePosition = valuePosition + 4;
               }
               break;
            }
            case 'y':{
               // 格式化为2位年（如05）
               if(dateTime == 0){
                  *pWrite++ = '0';
                  *pWrite++ = '0';
               }else{
                  TInt year = data.tm_year % 100;
                  RInt::ToString(year, buffer, 3);
                  if(year < 10){
                     *pWrite++ = '0';
                     *pWrite++ = buffer[0];
                  }else{
                     *pWrite++ = buffer[0];
                     *pWrite++ = buffer[1];
                  }
               }
               formatPosition++;
               valuePosition = valuePosition + 2;
               break;
            }
            case 'm':{
               // 格式化为月
               if(dateTime == 0){
                  *pWrite++ = '0';
                  *pWrite++ = '0';
               }else{
                  TInt month = data.tm_mon + 1;
                  RInt::ToString(month, buffer, 3);
                  if(month < 10){
                     *pWrite++ = '0';
                     *pWrite++ = buffer[0];
                  }else{
                     *pWrite++ = buffer[0];
                     *pWrite++ = buffer[1];
                  }
               }
               formatPosition++;
               valuePosition = valuePosition + 2;
               break;
            }
            case 'd':{
               // 格式化为天（日）
               if(dateTime == 0){
                  *pWrite++ = '0';
                  *pWrite++ = '0';
               }else{
                  TInt day = data.tm_mday;
                  RInt::ToString(day, buffer, 3);
                  if(day < 10){
                     *pWrite++ = '0';
                     *pWrite++ = buffer[0];
                  }else{
                     *pWrite++ = buffer[0];
                     *pWrite++ = buffer[1];
                  }
               }
               formatPosition++;
               valuePosition = valuePosition + 2;
               break;
            }
            case 'H':{
               // 格式化为24小时制小时
               if(dateTime == 0){
                  *pWrite++ = '0';
                  *pWrite++ = '0';
               }else{
                  TInt hour = data.tm_hour;
                  RInt::ToString(hour, buffer, 3);
                  if(hour < 10){
                     *pWrite++ = '0';
                     *pWrite++ = buffer[0];
                  }else{
                     *pWrite++ = buffer[0];
                     *pWrite++ = buffer[1];
                  }
               }
               formatPosition++;
               valuePosition = valuePosition + 2;
               break;
            }
            case 'h':{
               // 格式化为12小时制小时
               if(dateTime == 0){
                  *pWrite++ = '0';
                  *pWrite++ = '0';
               }else{
                  TInt hour = data.tm_hour % 12;
                  RInt::ToString(hour, buffer, 3);
                  if(hour < 10){
                     *pWrite++ = '0';
                     *pWrite++ = buffer[0];
                  }else{
                     *pWrite++ = buffer[0];
                     *pWrite++ = buffer[1];
                  }
               }
               formatPosition++;
               valuePosition = valuePosition + 2;
               break;
            }
            case 'M':{
               // 格式化为分
               if(dateTime == 0){
                  *pWrite++ = '0';
                  *pWrite++ = '0';
               }else{
                  TInt minute = data.tm_min;
                  RInt::ToString(minute, buffer, 3);
                  if(minute < 10){
                     *pWrite++ = '0';
                     *pWrite++ = buffer[0];
                  }else{
                     *pWrite++ = buffer[0];
                     *pWrite++ = buffer[1];
                  }
               }
               formatPosition++;
               valuePosition = valuePosition + 2;
               break;
            }
            case 'S':{
               // 格式化为秒
               if(dateTime == 0){
                  *pWrite++ = '0';
                  *pWrite++ = '0';
               }else{
                  TInt second = data.tm_sec;
                  RInt::ToString(second, buffer, 3);
                  if(second < 10){
                     *pWrite++ = '0';
                     *pWrite++ = buffer[0];
                  }else{
                     *pWrite++ = buffer[0];
                     *pWrite++ = buffer[1];
                  }
               }
               formatPosition++;
               valuePosition = valuePosition + 2;
               break;
            }
            case 's':{
               // 格式化为毫秒
               if(dateTime == 0){
                  *pWrite++ = '0';
                  *pWrite++ = '0';
                  *pWrite++ = '0';
               }else{
                  TInt millisecond = (dateTime % 1000000) / 1000;
                  RInt::ToString(millisecond, buffer, 4);
                  if(millisecond < 10){
                     *pWrite++ = '0';
                     *pWrite++ = '0';
                     *pWrite++ = buffer[0];
                  }else if(millisecond < 100){
                     *pWrite++ = '0';
                     *pWrite++ = buffer[0];
                     *pWrite++ = buffer[1];
                  }else{
                     *pWrite++ = buffer[0];
                     *pWrite++ = buffer[1];
                     *pWrite++ = buffer[2];
                  }
               }
               formatPosition++;
               valuePosition = valuePosition + 3;
               break;
            }
            default:{
               MO_STATIC_FATAL(TC("Unknown date format. (format=%s)"), pFormat);
            }
         }
      }else{
         *pWrite++ = *pFormat++;
         valuePosition++;
      }
      formatPosition++;
   }
   *pWrite = '\0';
   return pBuffer;
}

//============================================================
TUint32 RDateTime::To32(TDateTime dateTime){
   TUint64 to = dateTime / 1000000L;
   TUint32 tot = (TUint32)to;
   return tot;
}

//============================================================
TUint64 RDateTime::To64(TUint32 dateTime){
   TUint64 to = dateTime;
   to *= 1000000L;
   return to;
}

//============================================================
void RDateTime::Split(TDateTime current, SDateTime& dateTime){
   struct tm data;
   time_t time = current / 1000000;
#ifdef _MO_SYS_WINDOWS
   MO_ASSERT_ERRNO(localtime_s(&data, &time));
#endif // _MO_SYS_WINDOWS
#ifdef _MO_SYS_LINUX
   localtime_r(&time, &data);
#endif // _MO_SYS_LINUX
   dateTime.second = data.tm_sec;
   dateTime.minute = data.tm_min;
   dateTime.hour = data.tm_hour;
   dateTime.day = data.tm_mday;
   dateTime.month = data.tm_mon + 1;
   dateTime.year = data.tm_year + 1900;
   dateTime.dayWeek = data.tm_wday;
   dateTime.dayYear = data.tm_yday;
}

//============================================================
TDateTime RDateTime::Mktime(SDateTime& dateTime){
   struct tm data;
   data.tm_isdst = 0;
   data.tm_sec = dateTime.second;
   data.tm_min = dateTime.minute;
   data.tm_hour = dateTime.hour;
   data.tm_mday = dateTime.day;
   data.tm_mon = dateTime.month - 1;
   data.tm_year = dateTime.year - 1900;
   data.tm_wday = dateTime.dayWeek;
   data.tm_yday = dateTime.dayYear;
   return mktime(&data);
}

//============================================================
TBool RDateTime::IsTheNextDay( TDateTime dayEarly, TDateTime dayLate ){
   SDateTime last_day;
   SDateTime today;
   Split(dayEarly, last_day);
   Split(dayLate, today);
   last_day.hour = 0;
   last_day.minute = 0;
   last_day.second = 0;
   today.hour = 0;
   today.minute = 0;
   today.second = 0;
   dayEarly = Mktime(last_day);
   dayLate = Mktime(today);
   return ((dayLate - dayEarly) >= 86398 && (dayLate - dayEarly) <= 86402 ); // 加点容差
}

//============================================================ 
// <T>获取当前时间在本年中是第几周。</T> 
// 
// @param dateTime 时间 
// @return 第几周 
//============================================================
TInt RDateTime::GetNumberOfWeek(SDateTime& dateTime){
   SDateTime firstDate;
   memcpy(&firstDate, &dateTime, sizeof(dateTime));
   firstDate.month = 1;
   firstDate.day = 1;
   RDateTime::Split(RDateTime::Mktime(firstDate), firstDate);
   TInt offset = firstDate.dayWeek;
   if(offset == 0){
      offset = 7;
   }
   TInt curDay = dateTime.dayYear;
   curDay = curDay - (7 - offset + 1);
   if(curDay <= 0){
      return 1;
   }else{
      if(curDay % 7 == 0){
         return curDay / 7 + 1;
      }else{
         return curDay / 7 + 2;
      }
   }
   return 0;
}

MO_NAMESPACE_END
