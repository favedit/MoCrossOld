/*
 * @(#)FWriteTag.java
 *
 * Copyright 2008 Java Frame Studio Corporation.
 * All Rights Reserved.
 *
 */
package org.mo.web.tag.common;

import org.mo.com.lang.FString;
import org.mo.com.lang.RString;
import org.mo.web.core.format.RHtmlFormat;
import org.mo.web.tag.base.FBaseWriteTag;

/**
 * <T>输出标签</T>
 * <P>输出数据对应的值</P>
 *
 * @author MAOCY
 * @version 1.00 - 2008/08/05 CREATE BY MAOCY
 * @version 1.01 - 2009/01/05 UPDATE BY YANGH
 */
public class FWriteTag
      extends FBaseWriteTag
{

   @Override
   public int onEnd(){
      /// 返回逻辑执行结果
      return EVAL_PAGE;
   }

   @Override
   public int onStart(){
      // String source = RString.nvl(_source, _item);
      String source = _source;
      /// 解析数据
      String value = _context.parseString(source);
      /// 值为空的情况下
      if(RString.isEmpty(value)){
         value = _context.parseString(_default);
      }
      /// 值不为空的情况下
      if(!RString.isEmpty(value)){
         /// 需要格式化后输出
         if(RString.isEmpty(_format)){
            _writer.append(RHtmlFormat.textToHtml(new FString(value)));
            /// 文本形式输出
         }else if(_format.equalsIgnoreCase("text")){
            _writer.append(value);
         }else if(_format.equalsIgnoreCase("script.string")){
            _writer.append(RHtmlFormat.textToScriptString(value));
         }else{
            // value = FStringUtil.parse(value, "context_path", _context.contextPath());
            _writer.append(value);
         }
      }
      /// 刷新数据流
      _writer.flush();
      return SKIP_BODY;
   }
}
