/*
 * @(#)FLineTag.java
 *
 * Copyright 2004 Java Frame Studio Corporation.
 * All Rights Reserved.
 *
 */

package org.mo.web.tag.page;

import org.mo.com.lang.FError;
import org.mo.web.tag.common.FAbstractTag;

/**
 * <T>下拉列表标签</T>
 * <P>当格式化方式为指定时,格式化为HTML代码</P>
 * 
 * @author MAOCY
 * @version 1.00 - 2008/08/05 CREATE BY MAOCY
 * @version 1.01 - 2009/01/05 UPDATE BY YANGH
 */
public class FLineTag
      extends FAbstractTag
{

   //指定颜色对象
   /**
    * <T>按键起来的事件</T>
    */
   private String _color = null;

   //指定高度对象
   /**
    * <T>按键起来的事件</T>
    */
   private String _height = null;

   @Override
   public int onEnd(){
      return EVAL_PAGE;
   }

   /**
    * <T>开始标签的逻辑</T>
    * <P>create date:2008/12/31</P>
    *
    * @param _context 环境对象
    * @return 逻辑执行后的状态
    * @exception FError 逻辑例外
    */
   @Override
   public int onStart() throws FError{
      if(_color == null){
         _color = "#FFFFFF";
      }
      if(_height == null){
         _height = "1";
      }
      _writer.append("<TABLE width=\"100%\" bgcolor=\'");
      _writer.append(_color);
      _writer.append("\' border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"");
      _writer.append(_height);
      _writer.append("\">");
      _writer.flush();
      return SKIP_BODY;
   }

   /**
    * <T>设置高度对象</T>
    * <P>create date:2008/12/31</P>
    *
    * @param sValue 高度对象
    */
   public void setColor(String sValue){
      _color = sValue;
   }

   /**
    * <T>获得高度对象</T>
    * <P>create date:2008/12/31</P>
    *
    * @retrun 高度对象
    */
   public void setHeight(String sValue){
      _height = sValue;
   }
}
