package org.mo.jfa.core.page;

import org.mo.com.lang.RString;
import org.mo.com.xml.FXmlNodes;
import org.mo.core.aop.face.ALink;
import org.mo.eng.data.common.ISqlContext;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public class FPageService
      implements
         IPageService
{

   @ALink
   private IPageConsole _console;

   public void list(IWebContext webContext,
                    ISqlContext sqlContext,
                    IWebInput input,
                    IWebOutput output){
      String type = input.type();
      if(!RString.isEmpty(type)){
         FXmlNodes pageNodes = _console.list(type);
         output.config().nodes().append(pageNodes);
      }
   }
}
