/*
 * @(#)FRadioButtonTag.java
 *
 * Copyright 2008 Java Frame Studio Corporation.
 * All Rights Reserved.
 *
 */
package org.mo.web.tag.page;

import org.mo.com.lang.RString;
import org.mo.web.protocol.context.SPageItem;
import org.mo.web.tag.base.FBaseRadioTag;

/**
 * <T>下拉列表标签</T>
 * <P>当格式化方式为指定时,格式化为HTML代码</P>
 * 
 * @author MAOCY
 * @version 1.00 - 2008/08/05 CREATE BY MAOCY
 * @version 1.01 - 2009/01/05 UPDATE BY YANGH
 */
public class FRadioTag
      extends FBaseRadioTag
{

   //指定选定时图片路径
   /**
    * <T>按键起来的事件</T>
    */
   public final static String IMG_CHECKED = "/res/img/sys/radio_sel2.gif";

   //指定未选定时图片路径
   /**
    * <T>按键起来的事件</T>
    */
   public final static String IMG_NOCHECK = "/res/img/sys/radio2.gif";

   /**
    * <T>按键起来的事件</T>
    */
   public static String STATUS_DISPLAY = "display";

   /**
    * <T>按键起来的事件</T>
    */
   public static String STATUS_EDIT = "edit";

   @Override
   public int onEnd(){
      return EVAL_PAGE;
   }

   /**
    * <p>开始标签的逻辑</p>
    *
    * @return 逻辑执行后的状态
    */
   @Override
   public int onStart(){
      SPageItem item = _context.parseItem(_source);
      String name = item.name;
      String itemValue = item.valueString();
      if(RString.isEmpty(itemValue)){
         itemValue = _context.parseString(_default);
      }
      String value = null;
      if(!RString.isEmpty(_value)){
         value = _context.parseString(_value);
      }
      boolean result = RString.equalsIgnoreCase(itemValue, value);
      String status = _context.parseString(_status);
      if(STATUS_DISPLAY.equalsIgnoreCase(status)){
         if(result){
            _writer.append("<IMG src='");
            _writer.append(_context.contextPath());
            _writer.append(IMG_CHECKED);
            _writer.append("' align='absmiddle'>");
         }else{
            _writer.append("<IMG src='");
            _writer.append(_context.contextPath());
            _writer.append(IMG_NOCHECK);
            _writer.append("' align='absmiddle'>");
         }
      }else{
         _writer.append("<INPUT type='radio' name='");
         _writer.append(name);
         _writer.append("' value='");
         _writer.append(value);
         _writer.append("'");
         if(result){
            _writer.append(" checked");
         }
         if(RString.isEmpty(_css)){
            _css = "Tag_Radio";
         }
         appendHtml();
         _writer.append(">");
      }
      // ------------------------------------------------------------
      _writer.flush();
      return SKIP_BODY;
   }

}
