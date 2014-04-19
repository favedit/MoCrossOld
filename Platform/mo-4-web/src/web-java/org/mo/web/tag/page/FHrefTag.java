/*
 * @(#)FHrefTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.tag.page;

import org.mo.com.lang.RString;
import org.mo.web.tag.base.FBaseHrefTag;

/**
 * <T>链接标签</T>
 * <P>添加与链接标签有关的属性设置</P>
 * 
 * @author MAOCY
 * @version 1.00 - 2008/08/05 CREATE BY MAOCY
 * @version 1.01 - 2009/01/05 UPDATE BY YANGH
 */
public class FHrefTag
      extends FBaseHrefTag
{

   @Override
   public int onEnd(){
      /// 标签结束标识
      _writer.append("</A>");
      /// 刷新字符流
      _writer.flush();
      /// 返回逻辑执行结果后状态
      return EVAL_PAGE;
   }

   @Override
   public int onStart(){
      /// 将相关属性添加到字符流
      _writer.append("<A");
      /// 标签类型
      if(RString.isNotEmpty(_target)){
         _writer.append(" target=\"", _target, "\"");
      }
      /// 链接内容
      if(RString.isNotEmpty(_href)){
         String href = _context.parseString(_href);
         _writer.append(" href=\"", _context.contextPath(href), "\"");
      }
      /// 链接页面
      if(RString.isNotEmpty(_page)){
         _writer.append(" href=\"", _context.contextPath(_page), "\"");
      }
      /// 提示标签
      if(RString.isNotEmpty(_title)){
         _title = RString.replace(_title, "\\n", "\n");
         _writer.append(" title=\"", _title, "\"");
      }
      appendHtml();
      _writer.append(">");
      /// 刷新字符流
      _writer.flush();
      return EVAL_BODY_INCLUDE;
   }

}
