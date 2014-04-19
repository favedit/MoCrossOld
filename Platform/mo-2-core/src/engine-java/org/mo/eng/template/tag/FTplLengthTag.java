package org.mo.eng.template.tag;

import org.mo.com.lang.RInteger;
import org.mo.com.lang.RString;

public class FTplLengthTag
      extends FTplItemTag
{
   public static String NAME = "Item";

   private String _add;

   @Override
   public int onStart(){
      String source = _context.parseString(_source);
      if(RString.isEmpty(source)){
         source = _context.parseString(_default);
      }
      if(!RString.isEmpty(source)){
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
         source = RTplTag.format(_format, source);
         int length = source.length();
         if(!RString.isEmpty(_add)){
            length += RInteger.parse(_add);
         }
         _context.append(Integer.toString(length));
      }
      return STOP;
   }

   public void setAdd(String add){
      _add = add;
   }
}
