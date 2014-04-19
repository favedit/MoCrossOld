package org.mo.eng.template.tag;

import org.mo.com.lang.FString;
import org.mo.com.lang.generic.MString;
import org.mo.eng.template.FTemplateContext;

public class FTplNotEmptyTag
      extends FAbstractTplTag
{
   public static String NAME = "NotEmpty";

   private String _source;

   public void construct(FTemplateContext context){
      _nowrap = "r";
      super.construct(context);
   }

   public void onDump(MString dump){
      dump.append("NotEmpty ");
      dump.append("[ source=", _source);
      dump.append(" ]");
   }

   @Override
   public int onStart(){
      Object source = _context.parse(_source);
      if(null == source){
         return STOP;
      }
      if(source instanceof String){
         return (((String)source).trim().length() > 0) ? CONTINUE : STOP;
      }
      if(source instanceof FString){
         return (((FString)source).toString().trim().length() > 0) ? CONTINUE : STOP;
      }
      return CONTINUE;
   }

   public void setSource(String source){
      _source = source;
   }
}
