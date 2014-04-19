package org.mo.eng.template.tag;

import org.mo.com.lang.FString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.generic.MString;
import org.mo.eng.template.FTemplateContext;

public class FTplFalseTag
      extends FAbstractTplTag
{
   public static String NAME = "False";

   private String _source;

   public void construct(FTemplateContext context){
      _nowrap = "r";
      super.construct(context);
   }

   public void onDump(MString dump){
      dump.append(NAME);
      dump.append(" [ source=", _source);
      dump.append(" ]");
   }

   @Override
   public int onStart(){
      Object source = _context.parse(_source);
      if(null == source){
         return CONTINUE;
      }
      if(source instanceof String){
         return RBoolean.parse(((String)source).trim()) ? STOP : CONTINUE;
      }
      if(source instanceof FString){
         return RBoolean.parse(((FString)source).toString().trim()) ? STOP : CONTINUE;
      }
      return STOP;
   }

   public void setSource(String source){
      _source = source;
   }
}
