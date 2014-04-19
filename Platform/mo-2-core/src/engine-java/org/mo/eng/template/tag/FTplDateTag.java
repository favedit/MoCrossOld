package org.mo.eng.template.tag;

import org.mo.com.lang.generic.MString;

public class FTplDateTag
      extends FAbstractTplTag
{
   public static String NAME = "Date";

   protected String _format;

   public void onDump(MString dump){
      dump.append("Date ");
      dump.append("[ format=", _format);
      dump.append(" ]");
   }

   public void setFormat(String format){
      _format = format;
   }
}
