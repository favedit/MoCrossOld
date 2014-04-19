/*
 * @(#)FLogicSubscribeAction.java
 *
 * Copyright 2008 microbject. All Rights Reserved.
 *
 */
package org.mo.logic.subscribe;

import org.mo.com.lang.FAttributes;
import org.mo.com.lang.type.TInteger;

/**
 * <T>订阅动作打包类实例</T>
 * <P>该类是一个打包容器，具有存到容器和取得打包结果</P>
 * 
 * @author YJHUA
 * @version 1.00 - 2008/11/03
 */
public class FLogicSubscribeAction
      implements
         ILogicSubscribeAction
{
   // 订阅动作包
   protected FAttributes _ationAttribute = new FAttributes();

   /* (non-Javadoc)
    * @see org.mo.logic.subscribe.ILogicSubscribeAction#push(org.mo.com.lang.TInteger, org.mo.logic.subscribe.ILogicSubscribeEvent)
    */
   @Override
   public void push(TInteger actionId,
                    ILogicSubscribeEvent event){
      _ationAttribute.set(String.valueOf(actionId), event.pack().toString());
   }

   /* (non-Javadoc)
    * @see org.mo.logic.subscribe.ILogicSubscribeAction#pack()
    */
   @Override
   public String pack(){
      return _ationAttribute.pack().toString();
   }

   /* (non-Javadoc)
    * @see org.mo.logic.subscribe.ILogicSubscribeAction#isEmpty()
    */
   @Override
   public boolean isEmpty(){
      if(_ationAttribute.isEmpty()){
         return true;
      }
      return false;
   }
}
