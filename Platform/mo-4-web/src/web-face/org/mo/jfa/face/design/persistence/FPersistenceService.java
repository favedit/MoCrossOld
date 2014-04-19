package org.mo.jfa.face.design.persistence;

import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.face.ALink;
import org.mo.eng.persistence.IPersistenceConsole;
import org.mo.eng.persistence.common.XPersistence;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectService;
import org.mo.web.core.webtree.common.XTreeNode;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public class FPersistenceService
      extends FAbsXmlObjectService<XPersistence>
      implements
         IPersistenceService
{

   @ALink
   protected IPersistenceConsole _persistenceConsole;

   @Override
   public void catalog(IWebContext context,
                       IWebInput input,
                       IWebOutput output){
      catalog(_persistenceConsole, context, input, output);
   }

   @Override
   public void delete(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      delete(_persistenceConsole, context, input, output);
   }

   @Override
   public void insert(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      insert(_persistenceConsole, context, input, output);
   }

   @Override
   public void list(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      list(_persistenceConsole, context, input, output);
      // 根据条件进行排序
      FXmlNode selectNode = getSelectNode(input);
      String type = selectNode.get(XTreeNode.PTY_TYPE);
      if("persistence".equals(type)){
         output.config().sort("label");
      }
   }

   @Override
   public void sort(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      sort(_persistenceConsole, context, input, output);
   }

   @Override
   public void update(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      update(_persistenceConsole, context, input, output);
   }

}
