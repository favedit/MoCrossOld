package org.mo.eng.template.tag;

import org.mo.com.lang.RString;
import org.mo.com.lang.generic.MString;
import org.mo.eng.template.FTemplateContext;

public class FTplContainsTag
      extends FAbstractTplTag
{
   public static String NAME = "Contains";

   protected String _source;

   protected String _value;

   @Override
   public void construct(FTemplateContext context){
      _nowrap = "r";
      super.construct(context);
   }

   @Override
   public void onDump(MString dump){
      dump.append(NAME, " [");
      dump.append(" source=", _source);
      dump.append(" value=", _value);
      dump.append(" ]");
   }

   @Override
   public int onStart(){
      String source = RString.nvl(_context.parseString(_source));
      String value = RString.nvl(_context.parseString(_value));
      if(!RString.isEmpty(source, value)){
         source = "," + source + ",";
         value = "," + value + ",";
         return (source.indexOf(value) != -1) ? CONTINUE : STOP;
      }
      return STOP;
   }

   public void setSource(String source){
      _source = source;
   }

   public void setValue(String value){
      _value = value;
   }
}
