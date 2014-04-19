package org.mo.jfa.face.platform.server;

import org.mo.core.aop.face.ALink;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectService;
import org.mo.platform.server.IServerConsole;
import org.mo.platform.server.common.XServer;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

//============================================================
// <T>服务器服务。</T>
//============================================================
public class FServerService
      extends FAbsXmlObjectService<XServer>
      implements
         IServerService
{
   // 服务器控制台
   @ALink
   protected IServerConsole _serverConsole;

   //============================================================
   // <T>服务器服务。</T>
   //============================================================
   @Override
   public void catalog(IWebContext context,
                       IWebInput input,
                       IWebOutput output){
	   System.out.println("-----------------------------");
      catalog(_serverConsole, context, input, output);
   }
   @Override
   public void delete(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      delete(_serverConsole, context, input, output);
   }

   @Override
   public void insert(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      insert(_serverConsole, context, input, output);
   }

   @Override
   public void list(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      list(_serverConsole, context, input, output);
   }

   @Override
   public void sort(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      sort(_serverConsole, context, input, output);
   }

   @Override
   public void update(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      update(_serverConsole, context, input, output);
   }
}
