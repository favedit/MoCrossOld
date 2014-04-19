/*
 * @(#)FContainsTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.tag.logic;

import org.mo.com.lang.IObjects;
import org.mo.com.lang.RString;
import org.mo.web.tag.base.FBaseContainsTag;

/**
 * <T>包含标签</T>
 * <P>判断数据是否包含给定的值</P>
 * 
 * @author MAOCY
 * @version 1.00 - 2008/08/05 CREATE BY MAOCY
 * @version 1.01 - 2009/01/05 UPDATE BY YANGH
 */
public class FContainsTag
      extends FBaseContainsTag
{

   @Override
   public int onEnd(){
      /// 返回逻辑执行结果
      return EVAL_PAGE;
   }

   @Override
   public int onStart(){
      boolean result = false;
      /// 解析数据
      Object item = _context.parse(_source);
      /// 如果类型为String
      if(item instanceof String){
         result = !RString.isEmpty((String)item);
         /// 如果类型为IObjects
      }else if(item instanceof IObjects<?>){
         result = ((IObjects<?>)item).toObjects().length > 0;
      }
      return result ? EVAL_BODY_INCLUDE : SKIP_BODY;
   }
}
