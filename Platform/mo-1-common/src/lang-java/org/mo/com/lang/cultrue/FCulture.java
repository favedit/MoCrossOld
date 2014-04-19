package org.mo.com.lang.cultrue;

import org.mo.com.lang.FObject;
import org.mo.com.lang.RDump;
import org.mo.com.lang.generic.TDumpInfo;

//============================================================
// <T>文化定义。</T>
//============================================================
public class FCulture
      extends FObject
{
   // 线程编号
   protected long _threadId;

   // 数字格式
   protected FCultureNumber _number = new FCultureNumber();

   // 日历格式
   protected FCultureCalender _calender = new FCultureCalender();

   // 国家格式
   protected FCultureCountry _country = new FCultureCountry();

   // 货币格式
   protected FCultureCurency _curency = new FCultureCurency();

   // 编码格式
   protected String _encoding = "UTF-8";

   //============================================================
   // <T>构造文化定义。</T>
   //============================================================
   public FCulture(){
   }

   //============================================================
   // <T>获得线程编号。</T>
   //
   // @return 线程编号
   //============================================================
   public long threadId(){
      return _threadId;
   }

   //============================================================
   // <T>设置线程编号。</T>
   //
   // @param threadId 线程编号
   //============================================================
   public void setThreadId(long threadId){
      _threadId = threadId;
   }

   //============================================================
   // <T>获得数字格式。</T>
   //
   // @return 数字格式
   //============================================================
   public FCultureNumber number(){
      return _number;
   }

   //============================================================
   // <T>获得日历格式。</T>
   //
   // @return 日历格式
   //============================================================
   public FCultureCalender calender(){
      return _calender;
   }

   //============================================================
   // <T>获得国家格式。</T>
   //
   // @return 国家格式
   //============================================================
   public FCultureCountry country(){
      return _country;
   }

   //============================================================
   // <T>获得国家语言。</T>
   //
   // @return 国家语言
   //============================================================
   public String countryLanguage(){
      return _country.language();
   }

   //============================================================
   // <T>获得国家编码。</T>
   //
   // @return 国家编码
   //============================================================
   public String countryEncoding(){
      return _country.encoding();
   }

   //============================================================
   // <T>获得货币格式。</T>
   //
   // @return 货币格式
   //============================================================
   public FCultureCurency curency(){
      return _curency;
   }

   //============================================================
   // <T>接收文化定义。</T>
   //
   // @param culture 文化定义
   //============================================================
   public void assign(FCulture culture){
      _number.assign(culture.number());
      _calender.assign(culture.calender());
      _country.assign(culture.country());
      _curency.assign(culture.curency());
   }

   //============================================================
   // <T>生成运行信息。</T>
   //
   // @param info 运行信息
   // @return 运行信息
   //============================================================
   @Override
   public TDumpInfo dump(TDumpInfo info){
      RDump.dump(info, this);
      info.append("\n   Country : ", country().dump());
      info.append("\n   Calender: ", calender().dump());
      info.append("\n   Curency : ", curency().dump());
      info.append("\n   Number  : ", number().dump());
      return info;
   }
}
