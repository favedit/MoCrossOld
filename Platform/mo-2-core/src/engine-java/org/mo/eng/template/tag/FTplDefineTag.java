package org.mo.eng.template.tag;

import org.mo.com.lang.RString;
import org.mo.com.lang.generic.MString;

public class FTplDefineTag
      extends FAbstractTplTag
{
   public static String NAME = "Define";

   protected String _source;

   protected String _alias;

   @Override
   public void onDump(MString dump){
      dump.append("Convert ");
      dump.append("[ source=", _source);
      dump.append(", alias=", _alias);
      dump.append(" ]");
   }

   @Override
   public int onStart(){
      String alias = _context.parseString(_alias);
      if(!RString.isEmpty(alias)){
         Object source = _context.parse(_source);
         _context.define(alias, source);
      }
      return STOP;
   }

   public void setAlias(String alias){
      _alias = alias;
   }

   public void setSource(String source){
      _source = source;
   }
}
