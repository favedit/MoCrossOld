/*
 * @(#)FURITag.java
 *
 * Copyright 2008 Java Frame Studio Corporation.
 * All Rights Reserved.
 *
 */

package org.mo.web.tag.page;

import org.mo.web.tag.common.FAbstractTag;

/**
 * <T>处理URL链接标签</T>
 * <P>对URL标签进行必要的设置</P>
 * 
 * @author MAOCY
 * @version 1.00 - 2008/08/05 CREATE BY MAOCY
 * @version 1.01 - 2009/01/05 UPDATE BY YANGH
 */
public class FURITag
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
