/*
 * @(#)FFrameTag.java
 *
 * Copyright 2008 Java Frame Studio Corporation.
 * All Rights Reserved.
 *
 */
package org.mo.web.tag.page;

import org.mo.web.tag.base.FBaseFrameTag;
import org.mo.web.tag.common.RTag;

/**
 * <T>下拉列表标签</T>
 * <P>当格式化方式为指定时,格式化为HTML代码</P>
 * 
 * @author MAOCY
 * @version 1.00 - 2008/08/05 CREATE BY MAOCY
 * @version 1.01 - 2009/01/05 UPDATE BY YANGH
 */
public class FFrameTag
      extends FBaseFrameTag
{

   @Override
   public int onEnd(){
      return EVAL_PAGE;
   }

   @Override
   public int onStart(){
      _writer.append("<FRAME");
      if(_src != null){
         _writer.append(" src=\"");
         RTag.makeUri(_writer, _context, _src);
         _writer.append("\"");
      }
      appendHtml();
      _writer.append(">");
      _writer.flush();
      return EVAL_BODY_INCLUDE;
   }
}
