/*
 * @(#)FEventService.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.jfa.face.logic.event;

import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectService;
import org.mo.logic.event.ILogicEventTypeConsole;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

/**
 * <T> 业务事件树目录各种操作服务接口实例。</T>
 * <P>提供对业务事件和业务事件用树目录形式进行增删改各种操作的接口实例。</P>
 * 
 * @author YJHUA
 * @version 1.00 - 2008/12/017
 */
public class FEventService
      extends FAbsXmlObjectService<IXmlObject>
      implements
         IEventService
{

   @ALink
   protected ILogicEventTypeConsole _eventTypeConsole;

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventService#catalog(org.mo.web.protocol.context.IWebContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   @Override
   public void catalog(IWebContext context,
                       IWebInput input,
                       IWebOutput output){
      catalog(_eventTypeConsole, context, input, output);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventService#delete(org.mo.web.protocol.context.IWebContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   @Override
   public void delete(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      delete(_eventTypeConsole, context, input, output);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventService#insert(org.mo.web.protocol.context.IWebContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   @Override
   public void insert(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      insert(_eventTypeConsole, context, input, output);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventService#list(org.mo.web.protocol.context.IWebContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   @Override
   public void list(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      list(_eventTypeConsole, context, input, output);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventService#sort(org.mo.web.protocol.context.IWebContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   @Override
   public void sort(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      sort(_eventTypeConsole, context, input, output);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventService#update(org.mo.web.protocol.context.IWebContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   @Override
   public void update(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      update(_eventTypeConsole, context, input, output);
   }

}
