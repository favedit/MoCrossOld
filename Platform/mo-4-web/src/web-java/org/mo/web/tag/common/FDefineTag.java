/*
 * @(#)FDefineTag.java
 *
 * Copyright 2004 Java Frame Studio Corporation.
 * All Rights Reserved.
 *
 */

package org.mo.web.tag.common;

import org.mo.com.lang.FError;
import org.mo.com.lang.RInteger;
import org.mo.com.lang.RString;
import org.mo.web.tag.base.FBaseDefineTag;

/**
 * <T>别称定义标签</T>
 * <P>将指定的内容（别称、对象、源描述信息）定一个别称</P>
 *
 * @author MAOCY
 * @version 1.00 - 2008/08/05 CREATE BY MAOCY
 * @version 1.01 - 2009/01/05 UPDATE BY YANGH
 */
public class FDefineTag
      extends FBaseDefineTag
{

   /// 别称字符串
   private String _alias;

   /// 指定内容
   private String _source;

   /// 数组对象
   private Object[] _objects;

   /** 循环对象别称的定义 */
   protected String _looper;

   @Override
   public int onEnd(){
      return EVAL_PAGE;
   }

   /**
    * <P>开始标签的逻辑</P>
    * <P>create date:2008/08/05</P>
    * <P>update date:2009/01/05</P>
    *
    * @param oContext 环境对象
    * @return 逻辑执行后的状态
    * @exception FError 逻辑例外
    */
   @Override
   public int onStart(){
      if(RString.isNotBlank(_source, _alias)){
         /// 如果索引为空的情况下
         if(RString.isBlank(_index)){
            Object source = _context.parse(_source);
            _context.define(_alias, source);
            /// 如果索引不为空的情况下
         }else{
            _objects = _context.parseObjects(_source);
            _context.define(_alias, _objects[RInteger.parse(_index)]);
         }

      }
      return EVAL_PAGE;
   }

   /**
    * <P>释放标签所使用的资源</P>
    * <P>create date:2008/08/05</P>
    * <P>update date:2009/01/05</P>
    *
    */
   @Override
   public void release(){
      _source = null;
      _alias = null;
      super.release();
   }

   /**
    * <P>设置别称字符串</P>
    * <P>create date:2008/08/05</P>
    * <P>update date:2009/01/05</P>
    *
    * @param sItem 别称字符串
    */
   @Override
   public void setAlias(String value){
      _alias = value;
   }

   /**
    * <P>设置指定内容</P>
    * <P>create date:2008/08/05</P>
    * <P>update date:2009/01/05</P>
    *
    * @param value 指定内容
    */
   @Override
   public void setSource(String value){
      _source = value;
   }
}
