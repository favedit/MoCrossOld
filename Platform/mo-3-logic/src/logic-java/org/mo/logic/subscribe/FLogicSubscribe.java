/*
 * @(#)FLogicSubscribe.java
 *
 * Copyright 2008 microbject. All Rights Reserved.
 *
 */
package org.mo.logic.subscribe;

import org.mo.com.lang.FAttributes;
import org.mo.com.lang.type.TInteger;

/**
 * <T>订阅结构类实体</T>
 * <P>该类定义了订阅的基本字段，并定义了各个字段的存取函数</P>
 * <P>通过设置好的字段创建订阅</P>
 * 
 * @author YJHUA
 * @version 1.00 - 2008/11/03
 */
public class FLogicSubscribe
      implements
         ILogicSubscribe
{
   // 用户标识
   private TInteger _userId = new TInteger();

   // 类型标识
   private TInteger _typeId = new TInteger();

   // 关联标识
   private TInteger _linkId = new TInteger();

   // 类型动作标识
   private TInteger _actionId = new TInteger();

   // 类型事件标识
   private TInteger _eventId = new TInteger();

   // 打包字符串
   @SuppressWarnings("unused")
   private final FAttributes _pack = new FAttributes();

   /** 
    * <T>设置订阅用户标识</T>
    * 
    * @param userId 订阅用户标识
    */
   public void setUserId(TInteger userId){
      _userId = userId;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.subscribe.ILogicSubscribe#getUserId()
    */
   @Override
   public TInteger userId(){
      return _userId;
   }

   /** 
    * <T>设置订阅类型标识</T>
    * 
    * @param typeId 订阅类型标识
    */
   public void setTypeId(TInteger typeId){
      _typeId = typeId;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.subscribe.ILogicSubscribe#getTypeId()
    */
   @Override
   public TInteger typeId(){
      return _typeId;
   }

   /** 
    * <T>设置订阅关联标识</T>
    * 
    * @param linkId 订阅关联标识
    */
   public void setLinkId(TInteger linkId){
      _linkId = linkId;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.subscribe.ILogicSubscribe#getLinkId()
    */
   @Override
   public TInteger linkId(){
      return _linkId;
   }

   /** 
    * <T>设置订阅类型动作标识</T>
    * 
    * @param actionId 订阅类型动作标识
    */
   public void setActionId(TInteger actionId){
      _actionId = actionId;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.subscribe.ILogicSubscribe#getActionId()
    */
   @Override
   public TInteger actionId(){
      return _actionId;
   }

   /** 
    * <T>设置订阅类型事件标识</T>
    * 
    * @param eventId 订阅类型事件标识
    */
   public void setEventId(TInteger eventId){
      _eventId = eventId;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.subscribe.ILogicSubscribe#getEventId()
    */
   @Override
   public TInteger eventId(){
      return _eventId;
   }
}
