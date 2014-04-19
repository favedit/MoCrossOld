/*
 * @(#)FMemoTag.java
 *
 * Copyright 2009 Java Frame Studio Corporation.
 * All Rights Reserved.
 *
 */

package org.mo.web.tag.page;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RString;
import org.mo.web.core.format.RHtmlFormat;
import org.mo.web.protocol.context.SPageItem;
import org.mo.web.tag.base.FBaseMemoTag;

/**
 * <T>多行文本编辑框</T>
 * <P>当格式化方式为指定时,格式化为HTML代码</P>
 * 
 * @author MAOCY
 */
public class FMemoTag
      extends FBaseMemoTag
{

   @Override
   public int onEnd(){
      _writer.append("</TEXTAREA>");
      /// 刷新字符流
      _writer.flush();
      return EVAL_PAGE;
   }

   @Override
   public int onStart(){
      /// 获得数据源的值
      SPageItem item = _context.parseItem(_source);
      String name = RString.nvl(_name, item.name);
      String value = RString.nvl(_value, item.valueString());
      if(RString.isEmpty(name)){
         throw new FFatalError("Item name is null. (source={0})", _source);
      }
      /// 添加name的属性
      _writer.append("<TEXTAREA");
      if(null != item){
         _writer.append(" name=\"");
         _writer.append(name);
         _writer.append('"');
      }
      // 值不为空的情况
      if(RString.isNotEmpty(value)){
         _writer.append(" value=\"");
         _writer.append(RHtmlFormat.textToInput(value));
         _writer.append("\"");
      }
      /// 将宽度和高度等属性添加到样式里
      // 宽度
      String width = _context.parseString(_width);
      if(RString.isNotEmpty(width)){
         appendStyle("width", width);
      }
      // 高度
      String height = _context.parseString(_height);
      if(RString.isNotEmpty(height)){
         appendStyle("height", height);
      }
      /// 调用父类方法
      appendHtml();
      _writer.append(">");
      /// 刷新字符流
      _writer.flush();
      return EVAL_BODY_INCLUDE;
   }
}
