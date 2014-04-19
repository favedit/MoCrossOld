package org.mo.eng.template.tag;

import org.mo.com.lang.RInteger;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.MString;

//============================================================
// <T>模板项目标签。</T>
//============================================================
public class FTplItemTag
      extends FAbstractTplTag
{
   public static String NAME = "Item";

   protected String _default;

   protected String _format;

   protected String _removeFind;

   protected String _removeFirst;

   protected String _removeLast;

   protected String _source;

   protected String _last;

   protected String _lpad;

   protected String _rpad;

   protected String _begin;

   protected String _end;

   public String lpad(){
      return _lpad;
   }

   public String rpad(){
      return _rpad;
   }

   public void setBegin(String value){
      _begin = value;
   }

   public void setEnd(String value){
      _end = value;
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

   @Override
   public int onStart(){
      String source = _context.parseString(_source);
      if(RString.isEmpty(source)){
         source = _context.parseString(_default);
      }
      source = RString.nvl(source);
      // 移除前面和后面部分字符串
      if(null != _removeFirst && source.toLowerCase().startsWith(_removeFirst.toLowerCase())){
         source = source.substring(_removeFirst.length());
      }
      if(null != _removeLast && source.toLowerCase().endsWith(_removeLast.toLowerCase())){
         source = source.substring(0, source.length() - _removeLast.length());
      }
      if(null != _removeFind){
         int find = source.indexOf(_removeFind);
         if(find != -1){
            source = source.substring(find + _removeFind.length());
         }
      }
      // 删除前后长度
      int begin = 0;
      if(null != _begin){
         begin = Integer.parseInt(_begin);
      }
      int end = source.length();
      if(null != _end){
         end = Integer.parseInt(_end);
      }
      source = source.substring(begin, end);
      source = RTplTag.format(_format, source);
      // 设置结束字符
      if(!RString.isEmpty(_last)){
         source += _last;
      }
      // 格式化长度
      if(!RString.isEmpty(_rpad)){
         int rpad = RInteger.parse(_rpad);
         source = RString.rightPad(source, rpad);
      }
      if(!RString.isEmpty(_lpad)){
         int lpad = RInteger.parse(_lpad);
         source = RString.rightPad(source, lpad);
      }
      _context.append(source);
      return STOP;
   }

   @Override
   public void onDump(MString dump){
      dump.append("Item ");
      dump.append("[ format=", _format);
      dump.append(", source=", _source);
      dump.append(", default=", _default);
      dump.append(" ]");
   }
}
