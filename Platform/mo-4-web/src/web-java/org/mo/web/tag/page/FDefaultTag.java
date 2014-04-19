/*
 * @(#)FDefaultTag.java
 *
 * Copyright 2008 Java Frame Studio Corporation.
 * All Rights Reserved.
 *
 */

package org.mo.web.tag.page;

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
public class FDefaultTag
      extends FAbstractHtmlTag
{

   //定义动作对象
   /**
    * <T>按键起来的事件</T>
    */
   private String _action;

   //焦点
   /**
    * <T>按键起来的事件</T>
    */
   private String _foucs;

   //焦点名称
   /**
    * <T>按键起来的事件</T>
    */
   private String _foucsItem;

   //焦点链接
   /**
    * <T>按键起来的事件</T>
    */
   private String _foucsLink;

   //定义格式对象
   /**
    * <T>按键起来的事件</T>
    */
   private String _jsForm;

   //页动作
   /**
    * <T>按键起来的事件</T>
    */
   private String _pageAction;

   @Override
   public void appendHtml(){
   }

   @Override
   public int onEnd(){
      return 0;
   }

   @SuppressWarnings("unused")
   @Override
   public int onStart(){
      String sAction = null;
      // action attribute
      if(!RString.isEmpty(_pageAction)){
         if(RString.isEmpty(_jsForm)){
            sAction = "var oForm = obj.html.getDefaultForm();\n";
         }else{
            sAction = "var oForm = " + _jsForm + ";\n";
         }
         sAction += "oForm.action=\"" + _context.contextPath() + _pageAction + "\";\n" + "oForm.submit();\n";
         //FBodyTag.appendOnkeypress(this, sAction);
      }else if(!RString.isEmpty(_action)){
         sAction = _action;
         //FBodyTag.appendOnkeypress(this, sAction);
      }
      // ------------------------------------------------------------
      if(!RString.isEmpty(_foucs)){
         if(RString.isEmpty(_jsForm)){
            sAction = "var oForm = obj.html.getDefaultForm();\n";
         }else{
            sAction = "var oForm = " + _jsForm + ";\n";
         }
         sAction += "var oFocus = obj.html.findItem(oForm, \"" + _foucs + "\");\n";
         sAction += "if(oFocus){oFocus.focus();}\n";
         //FBodyTag.appendOnLoadScript(this, sAction);
      }
      // ------------------------------------------------------------
      if(!RString.isEmpty(_foucsLink) || !RString.isEmpty(_foucsItem)){
      }
      // ------------------------------------------------------------
      return SKIP_BODY;
   }

   /**
    * <T>设置格动作对象</T>
    * <p>create date:2005/02/18</p>
    *
    * @param sValue 动作对象
    */
   public void setAction(String sValue){
      _action = sValue;
   }

   /**
    * <T>设置格焦点对象</T>
    * <p>create date:2005/02/18</p>
    *
    * @param sValue 焦点对象
    */
   public void setFocus(String sValue){
      _foucs = sValue;
   }

   /**
    * <T>设置格焦点名称对象</T>
    * <p>create date:2005/02/18</p>
    *
    * @param sValue 焦点名称对象
    */
   public void setFocusItem(String sValue){
      _foucsItem = sValue;
   }

   /**
    * <T>设置格焦点链接对象</T>
    * <p>create date:2005/02/18</p>
    *
    * @param sValue 焦点链接对象
    */
   public void setFocusLink(String sValue){
      _foucsLink = sValue;
   }

   /**
    * <T>设置格式式对象</T>
    * <p>create date:2005/02/18</p>
    *
    * @param sValue 格式对象
    */
   public void setJsForm(String sValue){
      _jsForm = sValue;
   }

   /**
    * <T>设置格页动作对象</T>
    * <p>create date:2005/02/18</p>
    *
    * @param sValue 页动作对象
    */
   public void setPageAction(String sValue){
      _pageAction = sValue;
   }
}
