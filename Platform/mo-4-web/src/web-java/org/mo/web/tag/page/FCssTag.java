/*
 * @(#)FCssTag.java
 *
 * Copyright 2008 Java Frame Studio Corporation.
 * All Rights Reserved.
 *
 */
package org.mo.web.tag.page;

import org.mo.com.lang.RString;
import org.mo.web.tag.base.FBaseCssTag;

/**
 * <T>样式标签</T>
 * <P>添加与样式有关的属性和设置</P>
 * 
 * @author MAOCY
 * @version 1.00 - 2008/08/05 CREATE BY MAOCY
 * @version 1.01 - 2009/01/05 UPDATE BY YANGH
 */
public class FCssTag
      extends FBaseCssTag
{

   @Override
   public int onEnd(){
      /// 返回执行结果
      return EVAL_PAGE;
   }

   @Override
   public int onStart(){
      /// 源地址为空的情况下
      if(RString.isEmpty(_src)){
         String language = _context.session().culture().country().language().toLowerCase();
         /// 将对应的路径添加到字符流里
         /// 控制部分绝对路径样式
         _writer.append("<LINK rel='stylesheet' href='");
         _writer.append(_context.contextPath("/acs/control.css"));
         _writer.appendLine("' type='text/css'>");
         /// 控制部分相对路径样式
         _writer.append("<LINK rel='stylesheet' href='");
         _writer.append(_context.contextPath("#/acs/control.css"));
         _writer.appendLine("' type='text/css'>");
         /// lang类型样式路径
         _writer.append("<LINK rel='stylesheet' href='");
         _writer.append(_context.contextPath("#/acs/lang_" + language + ".css"));
         _writer.appendLine("' type='text/css'>");
         /// 站点类型路径样式
         _writer.append("<LINK rel='stylesheet' href='");
         _writer.append(_context.contextPath("#/acs/site_" + language + ".css"));
         _writer.append("' type='text/css'>");
         /// 不为空的情况下
      }else{
         _writer.append("<LINK rel='stylesheet' href='");
         _writer.append(_context.contextPath(_src));
         _writer.append("' type='text/css'>");
      }
      /// 刷新字符流
      _writer.flush();
      return SKIP_BODY;
   }
}
