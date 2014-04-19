package org.mo.eng.template.tag;

import org.mo.com.lang.RDateTime;
import org.mo.com.lang.RInteger;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.MString;

public class FTplTimeSpanTag
      extends FAbstractTplTag
{
   public static String NAME = "Item";

   public static final String _year = "年";

   public static final String _month = "月";

   public static final String _day = "天";

   public static final String _hour = "小时";

   public static final String _minute = "分钟";

   public static final String _second = "秒";

   protected String _default;

   protected String _type;

   protected String _format;

   protected String _removeFind;

   protected String _removeFirst;

   protected String _removeLast;

   protected String _source;

   protected String _last;

   protected String _lpad;

   protected String _rpad;

   public String lpad(){
      return _lpad;
   }

   @Override
   public void onDump(MString dump){
      dump.append("Item ");
      dump.append("[ format=", _format);
      dump.append(", source=", _source);
      dump.append(", default=", _default);
      dump.append(" ]");
   }

   @Override
   public int onStart(){
      String source = _context.parseString(_source);
      if(RString.isEmpty(source)){
         source = _context.parseString(_default);
      }
      source = RString.nvl(source);
      if(!"".equals(source)){
         if("DATE".equals(_type.toUpperCase())){
            source = RDateTime.format(source, _format);
         }
         if("SPAN".equals(_type.toUpperCase()) && 14 == source.length()){
            source = source.replace("-", "");
            int year = RInteger.parse(source.substring(0, 4));
            int month = RInteger.parse(source.substring(4, 6));
            int day = RInteger.parse(source.substring(6, 8));
            int hour = RInteger.parse(source.substring(8, 10));
            int minute = RInteger.parse(source.substring(10, 12));
            int second = RInteger.parse(source.substring(12));
            source = "";
            if(0 != year){
               source = year + _year;
            }
            if(0 != month){
               source += month + _month;
            }
            if(0 != day){
               source += day + _day;
            }
            if(0 != hour){
               source += hour + _hour;
            }
            if(0 != minute){
               source += minute + _minute;
            }
            if(0 != second){
               source += second + _second;
            }
         }
      }
      _context.append(source);
      return STOP;
   }

   public String rpad(){
      return _rpad;
   }

   public void setDefault(String value){
      _default = value;
   }

   public void setFormat(String value){
      _format = value;
   }

   public void setLast(String value){
      _last = value;
   }

   public void setLpad(String lpad){
      _lpad = lpad;
   }

   public void setRemoveFind(String value){
      _removeFind = value;
   }

   public void setRemoveFirst(String value){
      _removeFirst = value;
   }

   public void setRemoveLast(String value){
      _removeLast = value;
   }

   public void setRpad(String rpad){
      _rpad = rpad;
   }

   public void setSource(String value){
      _source = value;
   }

   public void setType(String value){
      _type = value;
   }
}
