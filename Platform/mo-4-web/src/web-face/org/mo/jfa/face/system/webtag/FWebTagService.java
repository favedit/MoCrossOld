package org.mo.jfa.face.system.webtag;

import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectService;
import org.mo.jfa.core.webtag.IWebTagConsole;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public class FWebTagService
      extends FAbsXmlObjectService<IXmlObject>
      implements
         IWebTagService
{

   @ALink
   protected IWebTagConsole _webTagConsole;

   @Override
   public void catalog(IWebContext context,
                       IWebInput input,
                       IWebOutput output){
      System.out.println(this + "Catalog----" + "调用了Seriver=====Catalog");
      catalog(_webTagConsole, context, input, output);
   }

   @Override
   public void delete(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      delete(_webTagConsole, context, input, output);
   }

   @Override
   public void insert(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      System.out.println(this + "  insert()函数！！！！！！！！！！！！！！！！！！！");
      insert(_webTagConsole, context, input, output);
   }

   @Override
   public void list(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      list(_webTagConsole, context, input, output);
   }

   @Override
   public void sort(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      sort(_webTagConsole, context, input, output);
   }

   @Override
   public void update(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      System.out.println(this + "  update()函数！！！！！！！！！！！！！！！！！！！");
      update(_webTagConsole, context, input, output);
   }

}
