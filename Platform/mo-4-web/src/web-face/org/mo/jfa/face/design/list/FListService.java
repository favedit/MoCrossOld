package org.mo.jfa.face.design.list;

import org.mo.core.aop.face.ALink;
import org.mo.eng.list.IListConsole;
import org.mo.eng.list.common.XList;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectService;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public class FListService
      extends FAbsXmlObjectService<XList>
      implements
         IListService
{

   @ALink
   protected IListConsole _listConsole;

   @Override
   public void catalog(IWebContext context,
                       IWebInput input,
                       IWebOutput output){
      catalog(_listConsole, context, input, output);
   }

   @Override
   public void delete(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      delete(_listConsole, context, input, output);
   }

   @Override
   public void insert(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      insert(_listConsole, context, input, output);
   }

   @Override
   public void list(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      list(_listConsole, context, input, output);
   }

   @Override
   public void sort(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      sort(_listConsole, context, input, output);
   }

   @Override
   public void update(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      update(_listConsole, context, input, output);
   }
}
