/*
 * @(#)FAbstractHtmlTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.tag.common;

/**
 * <T>HTML标签基类</T>
 *
 * @author MAOCY
 * @version 1.00 - 2008/08/05 CREATE BY MAOCY
 * @version 1.01 - 2009/01/05 UPDATE BY YANGH
 */
public abstract class FAbstractHtmlTag
      extends FAbstractTag
{

   /// 定义样式
   protected String _style;

   /**
    * <P>增加HTML对象</P>
    *
    * @return 逻辑执行后的状态
    */
   public abstract void appendHtml();

   /**
    * <P>增加字体对象</P>
    *
    * @param name 字体对象
    * @param value 字体对象
    * @return 是否增加字体对象成功
    */
   public void appendStyle(String name,
                           String value){
      String style = name.trim() + ":" + value.trim();
      if(null == _style){
         _style = style;
      }else{
         if(!_style.endsWith(";")){
            _style += ";";
         }
         _style += style;
      }
   }

   /**
    * <P>设置样式</P>
    *
    * @param value 样式
    */
   public void setStyle(String value){
      _style = value;
   }

}
