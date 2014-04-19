package org.mo.jfa.face.logic.project;

import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectService;
import org.mo.logic.project.ILogicProjectConsole;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public class FProjectService
      extends FAbsXmlObjectService<IXmlObject>
      implements
         IProjectService
{

   @ALink
   protected ILogicProjectConsole _projectConsole;

   @Override
   public void catalog(IWebContext context,
                       IWebInput input,
                       IWebOutput output){
      System.out.println(this + "Catalog----" + "调用了Seriver=====Catalog");
      catalog(_projectConsole, context, input, output);
   }

   @Override
   public void delete(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      delete(_projectConsole, context, input, output);
   }

   @Override
   public void insert(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      System.out.println(this + "  insert()函数！！！！！！！！！！！！！！！！！！！");
      insert(_projectConsole, context, input, output);
   }

   @Override
   public void list(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      list(_projectConsole, context, input, output);
   }

   @Override
   public void sort(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      sort(_projectConsole, context, input, output);
   }

   @Override
   public void update(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      System.out.println(this + "  update()函数！！！！！！！！！！！！！！！！！！！");
      update(_projectConsole, context, input, output);
   }

}
