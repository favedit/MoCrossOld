/*
 * @(#)FLogicSubscribeEvent.java
 *
 * Copyright 2008 microbject. All Rights Reserved.
 *
 */
package org.mo.logic.subscribe;

import org.mo.com.lang.FAttributes;
import org.mo.com.lang.type.TInteger;

/**
 * <T>订阅事件打包类实例</T>
 * <P>该类是一个打包容器，具有存到容器和取得打包结果</P>
 * 
 * @author YJHUA
 * @version 1.00 - 2008/11/03
 */
public class FLogicSubscribeEvent
      implements
         ILogicSubscribeEvent
{
   // 订阅事件包
   protected FAttributes _eventAttribute = new FAttributes();

   /* (non-Javadoc)
    * @see org.mo.logic.subscribe.ILogicSubscribeEvent#push(org.mo.com.lang.TInteger, java.lang.String)
    */
   @Override
   public void push(TInteger eventId,
                    String parameters){
      _eventAttribute.set(String.valueOf(eventId), parameters);
   }

   /* (non-Javadoc)
    * @see org.mo.logic.subscribe.ILogicSubscribeEvent#pack()
    */
   @Override
   public String pack(){
      return _eventAttribute.pack().toString();
   }

   /* (non-Javadoc)
    * @see org.mo.logic.subscribe.ILogicSubscribeEvent#isEmpty()
    */
   @Override
   public boolean isEmpty(){
      if(_eventAttribute.isEmpty()){
         return true;
      }
      return false;
   }
}
