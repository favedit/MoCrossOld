package org.mo.jfa.face.design.webtree;

import org.mo.com.lang.RBoolean;
import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.RAop;
import org.mo.core.aop.face.ALink;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectService;
import org.mo.web.core.webtree.IWebTreeConsole;
import org.mo.web.core.webtree.common.XTreeView;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public class FWebTreeService
      extends FAbsXmlObjectService<XTreeView>
      implements
         IWebTreeService
{

   @ALink
   protected IWebTreeConsole _webtreeConsole;

   public void catalog(IWebContext context,
                       IWebInput input,
                       IWebOutput output){
      catalog(_webtreeConsole, context, input, output);
   }

   @Override
   public void delete(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      delete(_webtreeConsole, context, input, output);
   }

   @Override
   public void insert(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      insert(_webtreeConsole, context, input, output);
   }

   @Override
   public void list(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      list(_webtreeConsole, context, input, output);
   }

   @Override
   public void loadDefine(IWebContext context,
                          IWebInput input,
                          IWebOutput output){
      FXmlNode configNode = input.config().findNode("WebTree");
      String name = configNode.get("name");
      String permission = input.get("permission");
      IWebTreeConsole console = RAop.find(IWebTreeConsole.class);
      FXmlNode config = null;
      if(RBoolean.parse(permission)){
         String userId = context.session().user().userId();
         config = console.buildSimpleWithPermission(name, userId);
      }else{
         config = console.buildSimple(name);
         config.set("ID", name);
      }
      if(null != config){
         output.config().assign(config);
      }
   }

   @Override
   public void sort(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      sort(_webtreeConsole, context, input, output);
   }

   @Override
   public void update(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      update(_webtreeConsole, context, input, output);
   }

}
