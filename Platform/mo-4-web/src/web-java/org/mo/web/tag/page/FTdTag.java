/*
 * @(#)FTdTag.java
 *
 * Copyright 2004 Java Frame Studio Corporation.
 * All Rights Reserved.
 *
 */

package org.mo.web.tag.page;

import org.mo.com.lang.FError;
import org.mo.com.lang.RString;
import org.mo.web.tag.common.FAbstractTag;

/**
 * <T>输出文本内容</T>
 * <P>当格式化方式为指定时,格式化为HTML代码</P>
 * 
 * @author MAOCY
 * @version 1.00 - 2008/08/05 CREATE BY MAOCY
 * @version 1.01 - 2009/01/05 UPDATE BY YANGH
 */
public class FTdTag
      extends FAbstractTag
{

   /// 指定宽度
   private String m_sWidth = null;

   /// 指定背景
   private String m_sBackground = null;

   /**
    * <T>获得背景对象</T>
    * <P>create date:2008/12/31</P>
    *
    * @retrun 背景对象
    */
   public String background(){
      return m_sBackground;
   }

   /**
    * <T>结束标签的逻辑</T>
    * <P>create date:2008/12/31</P>
    *
    * @param oContext 环境对象
    * @return 逻辑执行后的状态
    * @exception FError 逻辑例外
    */
   @Override
   public int onEnd(){
      _writer.append("</TD>");
      _writer.flush();
      return EVAL_PAGE;
   }

   /**
    * <T>开始标签的逻辑</T>
    * <P>create date:2008/12/31</P>
    *
    * @param oContext 环境对象
    * @return 逻辑执行后的状态
    * @exception FError 逻辑例外
    */
   @Override
   public int onStart(){
      _writer.append("<TD");
      if(!RString.isEmpty(width())){
         _writer.append(" width=\"" + width() + "\"");
      }
      if(!RString.isEmpty(background())){
         _writer.append(" background=\"");
         _writer.append(_context.contextPath());
         _writer.append(background());
         _writer.append("\"");
      }
      _writer.append(">");
      _writer.flush();
      return EVAL_BODY_INCLUDE;
   }

   /**
    * <T>设置背景对象</T>
    * <P>create date:2008/12/31</P>
    *
    * @param sValue 背景对象
    */
   public void setBackground(String sValue){
      m_sBackground = sValue;
   }

   /**
    * <T>设置宽度对象</T>
    * <P>create date:2008/12/31</P>
    *
    * @param sValue 宽度对象
    */
   public void setWidth(String sValue){
      m_sWidth = sValue;
   }

   /**
    * <T>获得宽度对象</T>
    * <P>create date:2008/12/31</P>
    *
    * @retrun 宽度对象
    */
   public String width(){
      return m_sWidth;
   }
}
