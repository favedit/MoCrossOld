package org.mo.eng.template.tag;

import org.mo.com.lang.FString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.MString;
import org.mo.eng.template.FTemplateContext;

public class FTplTrueTag
      extends FAbstractTplTag
{
   public static String NAME = "True";

   private String _source;

   private String _value;

   private String _elseValue;

   @Override
   public void construct(FTemplateContext context){
      _nowrap = "r";
      super.construct(context);
   }

   @Override
   public void onDump(MString dump){
      dump.append(NAME);
      dump.append(" [ source=", _source);
      dump.append(" ]");
   }

   @Override
   public int onStart(){
      Object source = _context.parse(_source);
      if(null == source){
         return STOP;
      }
      if(source instanceof String){
         if(RBoolean.parse(((String)source).trim())){
            if(!RString.isBlank(_value)){
               _context.append(_context.parseString(_value));
            }
            return CONTINUE;
         }else{
            if(!RString.isBlank(_elseValue)){
               _context.append(_context.parseString(_elseValue));
            }
            return STOP;
         }
      }
      if(source instanceof FString){
         if(RBoolean.parse(((FString)source).toString().trim())){
            if(!RString.isBlank(_value)){
               _context.append(_context.parseString(_value));
            }
            return CONTINUE;
         }else{
            if(!RString.isBlank(_elseValue)){
               _context.append(_context.parseString(_elseValue));
            }
            return STOP;
         }
      }
      return CONTINUE;
   }

   public void setSource(String source){
      _source = source;
   }

   public void setValue(String value){
      _value = value;
   }

   public void setElseValue(String elseValue){
      _elseValue = elseValue;
   }
}
