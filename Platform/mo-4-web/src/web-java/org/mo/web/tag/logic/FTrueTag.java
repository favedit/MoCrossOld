/*
 * @(#)FTrueTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.tag.logic;

import org.mo.com.lang.RBoolean;
import org.mo.web.tag.base.FBaseTrueTag;

/**
 * <T>判断是否为真标签</T>
 * <P>主要用于页面数据的逻辑判断</P>
 * 
 * @author MAOCY
 * @version 1.00 - 2008/08/05 CREATE BY MAOCY
 * @version 1.01 - 2009/01/05 UPDATE BY YANGH
 */
public class FTrueTag
      extends FBaseTrueTag
{

   @Override
   public int onEnd(){
      /// 返回执行结果状态
      return EVAL_PAGE;
   }

   @Override
   public int onStart(){
      /// 返回数据的判断结果
      return RBoolean.parse(_context.parseString(_source)) ? EVAL_BODY_INCLUDE : SKIP_BODY;
   }

}
