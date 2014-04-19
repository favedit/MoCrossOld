/*
 * @(#)FCalendarTag.java
 *
 * Copyright 2008 Java Frame Studio Corporation.
 * All Rights Reserved.
 *
 */

package org.mo.web.tag.page;

import java.util.Calendar;
import java.util.Date;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RDateTime;
import org.mo.com.lang.RString;
import org.mo.web.tag.base.FBaseCalendarTag;

/**
 * <T>日历标签</T>
 * <P>添加与日历有关的属性设置</P>
 * 
 * @author MAOCY
 * @version 1.00 - 2008/08/05 CREATE BY MAOCY
 * @version 1.01 - 2009/01/05 UPDATE BY YANGH
 */
public class FCalendarTag
      extends FBaseCalendarTag
{

   @Override
   public int onEnd(){
      /// 返回执行结束后状态
      return EVAL_PAGE;
   }

   @Override
   public int onStart(){
      /// 获得控件显示状态
      boolean bDisplayOnly = RBoolean.parse(_context.parseString(_display));
      /// 获得数据源的值
      String value = _context.parseString(_source);
      /// 在值为空的情况下
      if(RString.isEmpty(value)){
         String sDefault = _context.parseString(_default);
         /// 在默认值不为空的情况下
         if(!RString.isEmpty(sDefault)){
            /// 时间是否是当前时间
            if(RString.equalsIgnoreCase(sDefault, "SYSDATE") || RString.equalsIgnoreCase(sDefault, "NOW")){
               value = RDateTime.format();
            }else{
               /// 不是当前时间的情况
               value = sDefault;
            }
         }
      }
      /// 如果是显示状态
      if(bDisplayOnly){
         /// 值不为空的情况下
         if(!RString.isEmpty(value)){
            String sFormat = _context.parseString(_format);
            /// 输入的时间不为空的情况下
            if(!RString.isEmpty(_input)){
               _writer.append(RDateTime.format(value, _input, sFormat));
               /// 输入的时间是空的情况下
            }else{
               _writer.append(RDateTime.format(value, sFormat));
            }
         }
      }else{
         /// 定义年
         int nYear = 0;
         /// 定义月
         int nMonth = 0;
         /// 定义天
         int nDay = 0;
         /// 定义时
         int nHour = 0;
         /// 定义分
         int nMinute = 0;
         /// 定义秒
         int nSecond = 0;
         /// 格式化时间
         Date oDate = RDateTime.parse(value);
         if(oDate != null){
            Calendar oCalendar = Calendar.getInstance();
            oCalendar.setTime(oDate);
            nYear = oCalendar.get(Calendar.YEAR);
            nMonth = oCalendar.get(Calendar.MONTH) + 1;
            nDay = oCalendar.get(Calendar.DAY_OF_MONTH);
            nHour = oCalendar.get(Calendar.HOUR_OF_DAY);
            nMinute = oCalendar.get(Calendar.MINUTE);
            nSecond = oCalendar.get(Calendar.SECOND);
         }
         // make date year
         String sYear = (nYear > 0) ? Integer.toString(nYear) : "";
         String sItemName = null;
         StringBuffer oYearString = new StringBuffer();
         /// 将年月日添加到字符流
         oYearString.append("<INPUT name='" + sItemName + "_y' size='4' maxlength='4' class='comEditNumber' value='" + sYear + "' onchange='obj.html.setdate(\"" + sItemName + "\")'>");
         // make date month
         StringBuffer oMonthString = new StringBuffer();
         oMonthString.append("<SELECT name='" + sItemName + "_m' class='comComboBox' onchange='obj.html.setdate(\"" + sItemName + "\")'>");
         oMonthString.append("<OPTION value=''></OPTION>");
         for(int n = 1; n <= 12; n++){
            oMonthString.append("<OPTION value='" + RString.leftPad(Integer.toString(n), 2, "0") + "'");
            if(nMonth == n){
               oMonthString.append(" selected");
            }
            oMonthString.append(">" + n + "</OPTION>");
         }
         oMonthString.append("</SELECT>");
         // make date day
         StringBuffer oDayString = new StringBuffer();
         /// 将天添加到字符流  
         oDayString.append("<SELECT name='" + sItemName + "_d' class='comComboBox'onchange='obj.html.setdate(\"" + sItemName + "\")'>");
         oDayString.append("<OPTION value=''></OPTION>");
         for(int n = 1; n <= 31; n++){
            oDayString.append("<OPTION value='" + RString.leftPad(Integer.toString(n), 2, "0") + "'");
            if(nDay == n){
               oDayString.append(" selected");
            }
            oDayString.append(">" + n + "</OPTION>");
         }
         oDayString.append("</SELECT>");
         // make date hour
         StringBuffer oHourString = new StringBuffer();
         /// 将时添加到字符流
         oHourString.append("<SELECT name='" + sItemName + "_h' class='comComboBox'onchange='obj.html.setdate(\"" + sItemName + "\")'>");
         oHourString.append("<OPTION value=''></OPTION>");
         for(int n = 0; n < 24; n++){
            oHourString.append("<OPTION value='" + RString.leftPad(Integer.toString(n), 2, "0") + "'");
            if(nHour == n){
               oHourString.append(" selected");
            }
            oHourString.append(">" + n + "</OPTION>");
         }
         oHourString.append("</SELECT>");
         // make date Minute
         StringBuffer oMinuteString = new StringBuffer();
         /// 将分添加到字符流
         oMinuteString.append("<SELECT name='" + sItemName + "_i' class='comComboBox'onchange='obj.html.setdate(\"" + sItemName + "\")'>");
         oMinuteString.append("<OPTION value=''></OPTION>");
         for(int n = 0; n < 60; n++){
            oMinuteString.append("<OPTION value='" + RString.leftPad(Integer.toString(n), 2, "0") + "'");
            if(nMinute == n){
               oMinuteString.append(" selected");
            }
            oMinuteString.append(">" + n + "</OPTION>");
         }
         oMinuteString.append("</SELECT>");
         // make date second
         StringBuffer oSecondString = new StringBuffer();
         /// 将秒添加到字符流
         oSecondString.append("<SELECT name='" + sItemName + "_s' class='comComboBox'onchange='obj.html.setdate(\"" + sItemName + "\")'>");
         oSecondString.append("<OPTION value=''></OPTION>");
         for(int n = 0; n < 60; n++){
            oSecondString.append("<OPTION value='" + RString.leftPad(Integer.toString(n), 2, "0") + "'");
            if(nSecond == n){
               oSecondString.append(" selected");
            }
            oSecondString.append(">" + n + "</OPTION>");
         }
         oSecondString.append("</SELECT>");
         String sFormat = _context.parseString(_format);
         if(RString.isEmpty(sFormat)){
            sFormat = RDateTime.DEFAULT_FORMAT;
         }
         /// 格式化时间
         String sDatetimeInput = RDateTime.replace(sFormat, oYearString.toString(), oMonthString.toString(), oDayString.toString(), oHourString.toString(), oMinuteString.toString(), oSecondString.toString());
         sDatetimeInput += "<INPUT type='hidden' name='" + sItemName + "' value='" + value + "'>";
         _writer.append(sDatetimeInput);
      }
      /// 刷新字符流内容
      _writer.flush();
      return EVAL_BODY_INCLUDE;
   }

}
