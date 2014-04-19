package org.mo.eng.template.tag;

import org.mo.com.lang.RString;
import org.mo.com.lang.generic.MString;
import org.mo.core.aop.RAop;
import org.mo.eng.template.ITemplateConsole;
import org.mo.eng.template.ITemplateParser;

public class FTplIncludeTag
      extends FAbstractTplTag
{
   public static String NAME = "Include";

   protected String _part;

   protected String _source;

   @Override
   public void onDump(MString dump){
      dump.append("Convert ");
      dump.append("[ source=", _source);
      dump.append(", part=", _part);
      dump.append(" ]");
   }

   @Override
   public int onStart(){
      String source = _context.parseString(_source);
      if(!RString.isEmpty(source)){
         ITemplateConsole tplConsole = RAop.find(ITemplateConsole.class);
         ITemplateParser parser = tplConsole.findParser(source);
         parser.attach(_context.parser());
         parser.parse(source);
      }
      return STOP;
   }

   public void setPart(String part){
      _part = part;
   }

   public void setSource(String value){
      _source = value;
   }
}
