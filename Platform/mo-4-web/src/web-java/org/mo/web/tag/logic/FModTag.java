/*
 * @(#)FModTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.tag.logic;

import org.mo.com.lang.RInteger;
import org.mo.web.tag.base.FBaseModTag;

/**
 * <T>求余标签</T>
 * <P>对数据进行求余，与给定的值比较</P>
 * 
 * @author MAOCY
 * @version 1.00 - 2008/08/05 CREATE BY MAOCY
 * @version 1.01 - 2009/01/05 UPDATE BY YANGH
 */
public class FModTag
      extends FBaseModTag
{

   @Override
   public int onEnd(){
      /// 返回逻辑执行结果
      return EVAL_PAGE;
   }

   @Override
   public int onStart(){
      int nItem = RInteger.parse(_context.parseString(_source));
      int nValue = RInteger.parse(_context.parseString(_value));
      int nEquals = RInteger.parse(_context.parseString(_equals));
      // 取余数判断和指定值是否相等
      if(nItem % nValue == nEquals){
         return EVAL_BODY_INCLUDE;
      }
      return SKIP_BODY;
   }

}
