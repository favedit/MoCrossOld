package org.mo.jfa.face.monitor.thread;

import org.mo.com.xml.FXmlNodes;
import org.mo.core.aop.face.ALink;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectService;
import org.mo.web.core.webtools.IWebToolsConsole;
import org.mo.web.core.webtools.common.XToolBar;
import org.mo.web.core.webtree.common.XTreeNode;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public class FThreadService
      extends FAbsXmlObjectService<XToolBar>
      implements
         IThreadService
{

   @ALink
   protected IWebToolsConsole _toolsConsole;

   /* (non-Javadoc)
    * @see org.mo.jfa.face.design.webtools.IWebToolsService#catalog(org.mo.web.protocol.context.IWebContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   @Override
   public void catalog(IWebContext context,
                       IWebInput input,
                       IWebOutput output){
      FXmlNodes outputNodes = output.config().nodes();
      // 获得所有线程
      int count = Thread.activeCount();
      Thread[] threads = new Thread[count];
      Thread.enumerate(threads);
      // 建立树目录
      for(Thread thread : threads){
         // 建立树节点
         XTreeNode xnode = new XTreeNode();
         xnode.setType("Thread");
         xnode.setLabel(Long.toString(thread.getId()));
         xnode.setChild(false);
         xnode.set("description", thread.getName());
         outputNodes.push(xnode.toSimpleNode());
      }
      outputNodes.sortByAttribute("label");
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.design.webtools.IWebToolsService#insertComponent(org.mo.web.protocol.context.IWebContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   @Override
   public void insertComponent(IWebContext context,
                               IWebInput input,
                               IWebOutput output){
      insertComponent(_toolsConsole, context, input, output);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.design.webtools.IWebToolsService#insertToolBar(org.mo.web.protocol.context.IWebContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   @Override
   public void insertToolBar(IWebContext context,
                             IWebInput input,
                             IWebOutput output){
      insertCollection(_toolsConsole, context, input, output);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.design.webtools.IWebToolsService#listComponent(org.mo.web.protocol.context.IWebContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   @Override
   public void listComponent(IWebContext context,
                             IWebInput input,
                             IWebOutput output){
      list(_toolsConsole, context, input, output);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.design.webtools.IWebToolsService#saveComponent(org.mo.web.protocol.context.IWebContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   @Override
   public void saveComponent(IWebContext context,
                             IWebInput input,
                             IWebOutput output){
      saveComponent(_toolsConsole, context, input, output);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.design.webtools.IWebToolsService#saveControlOrder(org.mo.web.protocol.context.IWebContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   @Override
   public void saveControlOrder(IWebContext context,
                                IWebInput input,
                                IWebOutput output){
      sort(_toolsConsole, context, input, output);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.design.webtools.IWebToolsService#saveOrder(org.mo.web.protocol.context.IWebContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   @Override
   public void sort(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      sort(_toolsConsole, context, input, output);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.design.webtools.IWebToolsService#saveToolBar(org.mo.web.protocol.context.IWebContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   @Override
   public void saveToolBar(IWebContext context,
                           IWebInput input,
                           IWebOutput output){
      saveCollection(_toolsConsole, context, input, output);
   }

}
