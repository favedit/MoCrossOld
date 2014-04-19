/*
 * @(#)FPropertyService.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.jfa.face.logic.property;

import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectService;
import org.mo.logic.property.ILogicPropertyConsole;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

/**
 * <T>属性操作服务实体类。</T>
 * <P>提供对属性进行增删改等各种操作的实现函数。</P>
 * 
 * @author HYKUN
 * @version 1.00 - 2008/12/06
 */
public class FPropertyService
      extends FAbsXmlObjectService<IXmlObject>
      implements
         IPropertyService
{

   @ALink
   protected ILogicPropertyConsole _propertyConsole;

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.property.IPropertyService#catalog(org.mo.web.protocol.context.IWebContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   @Override
   public void catalog(IWebContext context,
                       IWebInput input,
                       IWebOutput output){
      catalog(_propertyConsole, context, input, output);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.property.IPropertyService#delete(org.mo.web.protocol.context.IWebContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   @Override
   public void delete(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      delete(_propertyConsole, context, input, output);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.property.IPropertyService#insert(org.mo.web.protocol.context.IWebContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   @Override
   public void insert(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      insert(_propertyConsole, context, input, output);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.property.IPropertyService#list(org.mo.web.protocol.context.IWebContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   @Override
   public void list(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      list(_propertyConsole, context, input, output);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.property.IPropertyService#sort(org.mo.web.protocol.context.IWebContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   @Override
   public void sort(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      sort(_propertyConsole, context, input, output);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.property.IPropertyService#update(org.mo.web.protocol.context.IWebContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   @Override
   public void update(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      update(_propertyConsole, context, input, output);
   }
}
