/*
 * @(#)FListOfViewTag.java
 *
 * Copyright 2004 Java Frame Studio Corporation.
 * All Rights Reserved.
 *
 */

package org.mo.web.tag.control;

import org.mo.web.tag.common.FAbstractHtmlTag;

/**
 * <p></p>
 * <p>
 * <br>
 *
 * </p>
 *
 * @author FEDT
 */
public class FListOfViewTag
      extends FAbstractHtmlTag
{

   @Override
   public void appendHtml(){
      // TODO Auto-generated method stub

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

   //   // 控件名称
   //   private String m_sName = null;
   //
   //   /**
   //    * <p>获得控件名称</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @retrun 控件名称
   //    */
   //   public String getName() {
   //      return m_sName;
   //   }
   //
   //   /**
   //    * <p>设置控件名称</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sValue 控件名称
   //    */
   //   public void setName(String sValue) {
   //      m_sName = sValue;
   //   }
   //
   //   //指定链接
   //   private String m_sLink = null;
   //
   //   /**
   //    * <p>获得链接</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @retrun 链接
   //    */
   //   public String getLink() {
   //      return m_sLink;
   //   }
   //
   //   /**
   //    * <p>设置链接</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sValue 链接
   //    */
   //   public void setLink(String sValue) {
   //      m_sLink = sValue;
   //   }
   //
   //   //指定内容
   //   private String m_sItem = null;
   //
   //   /**
   //    * <p>获得指定内容</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @retrun 指定内容
   //    */
   //   public String getItem() {
   //      return m_sItem;
   //   }
   //
   //   /**
   //    * <p>设置指定内容</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sValue 指定内容
   //    */
   //   public void setItem(String sValue) {
   //      m_sItem = sValue;
   //   }
   //
   //   //指定类型
   //   private String m_sType = null;
   //
   //   /**
   //    * <p>获得指定类型</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @retrun 指定类型
   //    */
   //   public String getType() {
   //      return m_sType;
   //   }
   //
   //   /**
   //    * <p>设置指定类型</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sValue 指定类型
   //    */
   //   public void setType(String sValue) {
   //      m_sType = sValue;
   //   }
   //
   //   //指定大小
   //   private String m_sSize = null;
   //
   //   /**
   //    * <p>获得指定大小</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @retrun 指定大小
   //    */
   //   public String getSize() {
   //      return m_sSize;
   //   }
   //
   //   /**
   //    * <p>设置指定大小</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sValue 指定大小
   //    */
   //   public void setSize(String sValue) {
   //      m_sSize = sValue;
   //   }
   //
   //   //指定显示类型
   //   private String m_bDisplayType = null;
   //
   //   /**
   //    * <p>获得指定显示类型</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @retrun 指定显示类型
   //    */
   //   public String getDisplayType() {
   //      return m_bDisplayType;
   //   }
   //
   //   /**
   //    * <p>设置指定显示类型</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sValue 指定显示类型
   //    */
   //   public void setDisplayType(String sValue) {
   //      if (sValue != null) {
   //         m_bDisplayType = sValue.toLowerCase();
   //      }
   //   }
   //
   //   //指定最大长度
   //   private String m_sMaxlength = null;
   //
   //   /**
   //    * <p>获得指定最大长度</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @retrun 指定最大长度
   //    */
   //   public String getMaxlength() {
   //      return m_sMaxlength;
   //   }
   //
   //   /**
   //    * <p>设置指定最大长度</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param value 指定最大长度
   //    */
   //   public void setMaxlength(String value) {
   //      m_sMaxlength = value;
   //   }
   //
   //   // 指定值
   //   private String m_sValue = null;
   //
   //   /**
   //    * <p>获得指定值</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @retrun 指定值
   //    */
   //   public String getValue() {
   //      return m_sValue;
   //   }
   //
   //   /**
   //    * <p>设置指定值</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param value 指定值
   //    */
   //   public void setValue(String value) {
   //      m_sValue = value;
   //   }
   //
   //   //指定宽度
   //   private String m_sWidth = null;
   //
   //   /**
   //    * <p>获得宽度</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @retrun 宽度
   //    */
   //   public String getWidth() {
   //      return m_sWidth;
   //   }
   //
   //   /**
   //    * <p>设置宽度</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sValue 宽度
   //    */
   //   public void setWidth(String sValue) {
   //      m_sWidth = sValue;
   //   }
   //
   //   //指定高度
   //   private String m_sHeight = null;
   //
   //   /**
   //    * <p>获得高度</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @retrun 高度
   //    */
   //   public String getHeight() {
   //      return m_sHeight;
   //   }
   //
   //   /**
   //    * <p>设置高度</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sValue 高度
   //    */
   //   public void setHeight(String sValue) {
   //      m_sHeight = sValue;
   //   }
   //
   //   //指定资源对象
   //   private String m_sSource = null;
   //
   //   /**
   //    * <p>获得资源</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @retrun 资源
   //    */
   //   public String getSource() {
   //      return m_sSource;
   //   }
   //
   //   /**
   //    * <p>设置资源</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sValue 资源
   //    */
   //   public void setSource(String sValue) {
   //      m_sSource = sValue;
   //   }
   //
   //   //列表的宽度
   //   private String m_sLovWidth = null;
   //
   //   /**
   //    * <p>获得列表的宽度</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @retrun 列表的宽度
   //    */
   //   public String getLovWidth() {
   //      return m_sLovWidth;
   //   }
   //
   //   /**
   //    * <p>设置列表的宽度</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sValue 列表的宽度
   //    */
   //   public void setLovWidth(String sValue) {
   //      m_sLovWidth = sValue;
   //   }
   //
   //   //列表高度
   //   private String m_sLovHeight = null;
   //
   //   /**
   //    * <p>获得列表高度</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @retrun 列表高度
   //    */
   //   public String getLovHeight() {
   //      return m_sLovHeight;
   //   }
   //
   //   /**
   //    * <p>设置列表高度</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sValue 列表高度
   //    */
   //   public void setLovHeight(String sValue) {
   //      m_sLovHeight = sValue;
   //   }
   //
   //   //列表模式
   //   private String m_sLovMethod = null;
   //
   //   /**
   //    * <p>获得列表模式</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @retrun 列表模式
   //    */
   //   public String getLovMethod() {
   //      return m_sLovMethod;
   //   }
   //
   //   /**
   //    * <p>设置列表模式</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sValue 列表模式
   //    */
   //   public void setLovMethod(String sValue) {
   //      m_sLovMethod = sValue;
   //   }
   //
   //   /**
   //    * <p>开始标签的逻辑</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param oContext 环境对象
   //    * @return 逻辑执行后的状态
   //    * @exception FError 逻辑例外
   //    */
   //   public int doStartTag(FWebContext oContext) throws FError {
   //      //      String sItem = oContext.getLinkItemName(getItem());
   //      //      String sSource = oContext.parseSource(getSource());
   //      //      String sURL = oContext.getContextPath()
   //      //            + "/inc/ctl/lov/ListOfView.action?source=" + sSource;
   //      //      String sWidth = oContext.parseSource(getLovWidth());
   //      //      String sHeight = oContext.parseSource(getLovHeight());
   //      //      oContext.write("<IMG src='" + oContext.getContextPath()
   //      //            + "/res/img/sys/lov.gif' align='absmiddle' "
   //      //            + "onclick='obj.html.lov(" + "\"" + sItem + "\"," + "\"" + sURL
   //      //            + "\"," + "" + sWidth + "," + sHeight + ")' style='cursor:hand'>");
   //      return SKIP_BODY;
   //   }
}
