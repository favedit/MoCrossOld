/*
 * @(#)FTitleTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.tag.page;

import org.mo.web.tag.base.FBaseTitleTag;

/**
 * <T>设置标题标签</T>
 * <P>在标签处理开始时，对标签的属性进行必要的设置</P>
 * <P>在标签处理结束后，返回逻辑执行后的状态</P>
 *
 * @author MAOCY
 * @version 1.00 - 2008/08/05 CREATE BY MAOCY
 * @version 1.01 - 2009/01/05 UPDATE BY YANGH
 */
public class FTitleTag
      extends FBaseTitleTag
{

   @Override
   public int onEnd(){
      /// 逻辑执行后的状态
      return EVAL_PAGE;
   }

   @Override
   public int onStart(){
      /// 将脚本加入到字符串里
      _writer.append("<SCRIPT>top.document.title=\"");
      /// 标题设置
      _writer.append(_context.parseString(_caption));
      _writer.append("\"</SCRIPT>");
      /// 将所有已缓冲输出写入底层流来刷新此流
      _writer.flush();
      return SKIP_BODY;
   }

}
