package org.mo.jfa.face.system.deploy;

import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectService;
import org.mo.logic.deploy.IDeployConsole;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public class FDeployService
      extends FAbsXmlObjectService<IXmlObject>
      implements
         IDeployService
{

   @ALink
   protected IDeployConsole _deployConsole;

   @Override
   public void catalog(IWebContext context,
                       IWebInput input,
                       IWebOutput output){
      System.out.println(this + "Catalog----" + "调用了Seriver=====Catalog");
      catalog(_deployConsole, context, input, output);
   }

   @Override
   public void delete(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      delete(_deployConsole, context, input, output);
   }

   @Override
   public void insert(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      System.out.println(this + "  insert()函数！！！！！！！！！！！！！！！！！！！");
      insert(_deployConsole, context, input, output);
   }

   @Override
   public void list(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      list(_deployConsole, context, input, output);
   }

   @Override
   public void sort(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      sort(_deployConsole, context, input, output);
   }

   @Override
   public void update(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      System.out.println(this + "  update()函数！！！！！！！！！！！！！！！！！！！");
      update(_deployConsole, context, input, output);
   }

}
