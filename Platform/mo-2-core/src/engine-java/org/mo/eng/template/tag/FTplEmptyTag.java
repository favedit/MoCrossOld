package org.mo.eng.template.tag;

import org.mo.com.lang.FString;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.MString;
import org.mo.eng.template.FTemplateContext;

public class FTplEmptyTag
      extends FAbstractTplTag
{
   public static String NAME = "Empty";

   private String _source;

   protected String _value;

   protected String _format;

   protected String _elseValue;

   @Override
   public void construct(FTemplateContext context){
      _nowrap = "r";
      super.construct(context);
   }

   @Override
   public void onDump(MString dump){
      dump.append("Empty ");
      dump.append("[ source=", _source);
      dump.append(" ]");
   }

   @Override
   public int onStart(){
      int result = CONTINUE;
      Object source = _context.parse(_source);
      if(null != source){
         if(source instanceof String){
            if(((String)source).trim().length() > 0){
               result = STOP;
            }
         }else if(source instanceof FString){
            if(((FString)source).toString().trim().length() > 0){
               result = STOP;
            }
         }
      }
      if(CONTINUE == result && !RString.isEmpty(_value)){
         String value = RTplTag.format(_format, _context.parseString(_value));
         _context.append(value);
      }else if(STOP == result && !RString.isEmpty(_elseValue)){
         String elseValue = RTplTag.format(_format, _context.parseString(_elseValue));
         _context.append(elseValue);
      }
      return result;
   }

   public void setElseValue(String elseValue){
      _elseValue = elseValue;
   }

   public void setFormat(String format){
      _format = format;
   }

   public void setSource(String source){
      _source = source;
   }

   public void setValue(String value){
      _value = value;
   }
}
