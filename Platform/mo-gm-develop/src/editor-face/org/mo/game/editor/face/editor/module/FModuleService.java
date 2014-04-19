package org.mo.game.editor.face.editor.module;

import org.mo.com.lang.RString;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.game.editor.core.module.IModuleConsole;
import org.mo.game.editor.core.module.common.XEntityGroup;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectService;
import org.mo.web.core.webtree.common.XTreeNode;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;


public class FModuleService
      extends FAbsXmlObjectService<XEntityGroup>
      implements
         IModuleService{

   @ALink
   protected IModuleConsole _entityConsole;

   /* (non-Javadoc)
    * @see org.mo.jfa.face.design.webtools.IWebToolsService#catalog(org.mo.web.protocol.context.IWebContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   public void catalog(IWebContext context,
                       IWebInput input,
                       IWebOutput output){
      catalog(_entityConsole, context, input, output);
      // 设置排序
      //orderNode(output);
   }

   @Override
   public void delete(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      delete(_entityConsole, context, input, output);
   }

   @Override
   public void insert(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      insert(_entityConsole, context, input, output);
   }

   @Override
   public void list(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      list(_entityConsole, context, input, output);
   }

   //============================================================
   @Override
   protected void onBuildTreeNode(XTreeNode xnode,
                                  IXmlObject xobject){
      if("Property".equals(xobject.name())){
         String value = xobject.innerGet("name");
         String typeName = xobject.innerGet("type_name");
         if(RString.isBlank(typeName)){
            typeName = xobject.innerGet("type");
         }
         String display = value + " <FONT color='gray'>[" + typeName + "]</FONT>";
         xnode.setLabel(display);
      }
   }

   @Override
   public void sort(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      sort(_entityConsole, context, input, output);
   }

   @Override
   public void update(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      update(_entityConsole, context, input, output);
   }
}
