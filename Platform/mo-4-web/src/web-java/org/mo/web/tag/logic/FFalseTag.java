/*
 * @(#)FFalseTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.tag.logic;

import org.mo.com.lang.RBoolean;
import org.mo.web.tag.base.FBaseFalseTag;

/**
 * <T>判断是假标签</T>
 * <P>判断给定的数据是否是假值</P>
 * 
 * @author MAOCY
 * @version 1.00 - 2008/08/05 CREATE BY MAOCY
 * @version 1.01 - 2009/01/05 UPDATE BY YANGH
 */
public class FFalseTag
      extends FBaseFalseTag
{

   @Override
   public int onEnd(){
      /// 返回逻辑执行结果
      return EVAL_PAGE;
   }

   @Override
   public int onStart(){
      /// 返回判断的结果
      return !RBoolean.parse(_context.parseString(_source)) ? EVAL_BODY_INCLUDE : SKIP_BODY;
   }

}
