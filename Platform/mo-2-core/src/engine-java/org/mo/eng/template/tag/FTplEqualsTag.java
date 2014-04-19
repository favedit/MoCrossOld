package org.mo.eng.template.tag;

import org.mo.com.lang.RString;
import org.mo.com.lang.generic.MString;
import org.mo.eng.template.FTemplateContext;

public class FTplEqualsTag
      extends FAbstractTplTag
{
   public static String NAME = "Equals";

   protected String _source;

   protected String _value;

   public void construct(FTemplateContext context){
      _nowrap = "r";
      super.construct(context);
   }

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
      return RString.inRangeIgnoreCase(source, value, '|') ? CONTINUE : STOP;
   }

   public void setSource(String source){
      _source = source;
   }

   public void setValue(String value){
      _value = value;
   }
}
