/*
 * @(#)FTextTag.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.web.tag.page;

import org.mo.web.tag.common.FAbstractTag;

/**
 * <T>输出文本内容</T>
 * <P>当格式化方式为指定时,格式化为HTML代码</P>
 * 
 * @author MAOCY
 * @version 1.00 - 2008/08/05 CREATE BY MAOCY
 * @version 1.01 - 2009/01/05 UPDATE BY YANGH
 */
public class FTextTag
      extends FAbstractTag
{

   @Override
   public int onEnd(){
      /// 逻辑结束执行后的状态
      return EVAL_PAGE;
   }

   @Override
   public int onStart(){
      /// 逻辑开始执行后的状态
      return SKIP_BODY;
   }
}
