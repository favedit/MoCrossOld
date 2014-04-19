/*
 * @(#)FIFrameTag.java
 *
 * Copyright 2008 Java Frame Studio Corporation.
 * All Rights Reserved.
 *
 */

package org.mo.web.tag.page;

import org.mo.com.lang.FError;
import org.mo.com.lang.RString;
import org.mo.web.tag.common.FAbstractHtmlTag;

/**
 * <T>下拉列表标签</T>
 * <P>当格式化方式为指定时,格式化为HTML代码</P>
 * 
 * @author MAOCY
 * @version 1.00 - 2008/08/05 CREATE BY MAOCY
 * @version 1.01 - 2009/01/05 UPDATE BY YANGH
 */
public class FIFrameTag
      extends FAbstractHtmlTag
{

   //边宽度
   /**
    * <T>按键起来的事件</T>
    */
   private String m_sBorder = null;

   //框架边宽度
   /**
    * <T>按键起来的事件</T>
    */
   private String m_sFrameBorder = null;

   //设置高度对象
   /**
    * <T>按键起来的事件</T>
    */
   private String m_sHeight = null;

   //定义内容对象
   /**
    * <T>按键起来的事件</T>
    */
   @SuppressWarnings("unused")
   private String m_sItem = null;

   //定义名称对象
   /**
    * <T>按键起来的事件</T>
    */
   private String m_sName = null;

   //图片链接
   /**
    * <T>按键起来的事件</T>
    */
   private String m_sSrc = null;

   //定义宽度对象
   /**
    * <T>按键起来的事件</T>
    */
   private String m_sWidth = null;

   @Override
   public void appendHtml(){
      // TODO Auto-generated method stub

   }

   /**
    * <p>获得边宽度对象</p>
    * <p>create date:2005/02/18</p>
    *
    * @retrun 边宽度对象
    */
   public String border(){
      return m_sBorder;
   }

   /**
    * <T>开始标签逻辑</T>
    * <P>create date:2008/12/31</P>
    *
    * @param oContext 环境对象
    * @return 逻辑执行后的状态
    * @exception FError 逻辑例外
    */

   @Override
   public int doStartTag(){
      _writer.append("<IFRAME");
      // name attribute
      if(!RString.isEmpty(name())){
         _writer.append(" name=\"" + name() + "\"");
      }
      // width attribute
      if(!RString.isEmpty(width())){
         _writer.append(" width=\"" + width() + "\"");
      }
      // height attribute
      if(!RString.isEmpty(height())){
         _writer.append(" height=\"" + height() + "\"");
      }
      // frameborder attribute
      if(!RString.isEmpty(frameBorder())){
         _writer.append(" frameborder=\"" + frameBorder() + "\"");
      }
      // border attribute
      if(!RString.isEmpty(border())){
         _writer.append(" border=\"" + border() + "\"");
      }
      // src attribute
      if(!RString.isEmpty(getSrc())){
         _writer.append(" src=\"" + _context.contextPath() + getSrc() + "\"");
      }
      _writer.append("></IFRAME>");
      _writer.flush();
      return SKIP_BODY;
   }

   /**
    * <T>获得框架边宽度对象</T>
    * <P>create date:2008/12/31</P>
    *
    * @retrun 框架边宽度对象
    */
   public String frameBorder(){
      return m_sFrameBorder;
   }

   /**
    * <T>获得图片链接对象</T>
    * <P>create date:2008/12/31</P>
    *
    * @retrun 图片链接对象
    */
   public String getSrc(){
      return m_sSrc;
   }

   /**
    * <T>获得高度对象</T>
    * <P>create date:2008/12/31</P>
    *
    * @retrun 高度对象
    */
   public String height(){
      return m_sHeight;
   }

   /**
    * <T>获得名称对象</T>
    * <P>create date:2008/12/31</P>
    *
    * @retrun 名称对象
    */
   public String name(){
      return m_sName;
   }

   @Override
   public int onEnd(){
      // TODO Auto-generated method stub
      return 0;
   }

   @Override
   public int onStart(){
      // TODO Auto-generated method stub
      return 0;
   }

   /**
    * <T>设置边宽度对象</T>
    * <P>create date:2008/12/31</P>
    *
    * @param sValue 边宽度对象
    */
   public void setBorder(String sValue){
      m_sBorder = sValue;
   }

   /**
    * <T>设置框架边宽度对象</T>
    * <P>create date:2008/12/31</P>
    *
    * @param sValue 框架边宽度对象
    */
   public void setFrameBorder(String sValue){
      m_sFrameBorder = sValue;
   }

   /**
    * <T>设置高度对象</T>
    * <P>create date:2008/12/31</P>
    *
    * @param sValue 高度对象
    */
   public void setHeight(String sValue){
      m_sHeight = sValue;
   }

   /**
    * <T>设置名称对象</T>
    * <P>create date:2008/12/31</P>
    *
    * @param sValue 名称对象
    */
   public void setName(String sValue){
      m_sName = sValue;
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
    * <T>设置图片链接对象</T>
    * <P>create date:2008/12/31</P>
    *
    * @param sValue 图片链接对象
    */
   public void src(String sValue){
      m_sSrc = sValue;
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
