package org.mo.web.tag.control;

import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.RAop;
import org.mo.web.core.webtools.IWebToolsConsole;

public class FToolBarTag
      extends FBaseToolBarTag
{

   @Override
   public int onEnd(){
      return EVAL_PAGE;
   }

   @Override
   public int onStart(){
      String name = _context.parseString(_name);
      String source = _context.parseString(_source);
      _writer.append("<XML id='", name, "'>\n");
      IWebToolsConsole console = RAop.find(IWebToolsConsole.class);
      FXmlNode config = console.buildSimple(source);
      if(null != config){
         _writer.append(config.xml());
      }
      _writer.append("</XML>");
      _writer.flush();
      return SKIP_BODY;
   }

}
