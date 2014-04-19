/*
 * @(#)FJsTag.java
 *
 * Copyright 2008 Java Frame Studio Corporation.
 * All Rights Reserved.
 *
 */
package org.mo.web.tag.page;

import org.mo.com.lang.RString;
import org.mo.web.tag.base.FBaseJsTag;

/**
 * <T>JS标签</T>
 * <P>通过标签将脚本植入到页面中</P>
 * 
 * @author MAOCY
 * @version 1.00 - 2008/08/05 CREATE BY MAOCY
 * @version 1.01 - 2009/01/05 UPDATE BY YANGH
 */
public class FJsTag
      extends FBaseJsTag
{

   @Override
   public int onEnd(){
      /// 返回逻辑执行后的状态
      return EVAL_PAGE;
   }

   @Override
   public int onStart(){
      /// 源地址不是空的情况下
      if(RString.isNotEmpty(_src)){
         _writer.append("<SCRIPT src=\"");
         _writer.append(_context.contextPath(_src));
         _writer.append("\"></SCRIPT>");
      }else{
         /// 源地址是空的情况下
         _writer.append("<SCRIPT src=\"");
         /// 类型是“server”的情况下
         if("server".equalsIgnoreCase(_type)){
            _writer.append(_context.contextPath(), "/ajs/mobj.js");
            /// 类型不是“server”的情况下
         }else{
            _writer.append(_context.contextPath(), "/ajs/client.js");
         }
         // 获得语言标识
         String languageId = _context.session().languageId();
         // 获得主题标识
         String themeId = _context.session().themeId();
         // 设置JS工作环境
         _writer.append("\"></SCRIPT>\n");
         _writer.append("<SCRIPT>RGlobal.initialize(");
         _writer.append("'", _context.contextPath(), "',");
         _writer.append("'", _context.contextTag(), "',");
         _writer.append("'", languageId, "',");
         _writer.append("'", themeId, "');");
         _writer.append("</SCRIPT>");
      }
      /// 刷新字符流
      _writer.flush();
      return EVAL_BODY_INCLUDE;
   }
}
