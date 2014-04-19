/*
 * @(#)FAbstractLogicEventResult.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.event;

import org.mo.com.lang.FAttributes;
import org.mo.com.lang.IAttributes;

/**
 * <T>事件结果基础类</T>
 * <P>该类是所有<R link='jv:org.mo.logic.event|ELogicEventResult'>事件结果</R>
 * 的基础类，提供了对事件结果的基本操作</P>
 * 
 * @author HYKUN
 * @version 1.00 - 2008/10/27
 */
public abstract class FAbstractLogicEventResult
      implements
         ILogicEventResult
{
   // 结果code号
   protected String _code;

   // 结果描述
   private String _description;

   // 结果备注
   private String _note;

   // 埋入参数
   protected IAttributes _parameters;

   /* (non-Javadoc)
    * @see org.mo.logic.event.ILogicEventResult#code()
    */
   @Override
   public String code(){
      return _code;
   }

   /** 
    * <T>设置结果描述</T>
    * 
    * @param note 描述
    */
   @Override
   public String description(){
      return _description;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.event.ILogicEventResult#hasParameter()
    */
   @Override
   public boolean hasParameter(){
      return (null != _parameters) ? !_parameters.isEmpty() : false;
   }

   public String note(){
      return _note;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.event.ILogicEventResult#parameters()
    */
   @Override
   public IAttributes parameters(){
      if(null == _parameters){
         _parameters = new FAttributes();
      }
      return _parameters;
   }

   /** 
    * <T>设置结果代码</T>
    * 
    * @param code 结果代码
    */
   public void setCode(String code){
      _code = code;
   }

   /** 
    * <T>设置结果描述</T>
    * 
    * @param description 描述
    */
   public void setDescription(String description){
      _description = description;
   }

   /** 
    * <T>设置结果描述</T>
    * 
    * @param note 描述
    */
   public void setNote(String note){
      _note = note;
   }
}
