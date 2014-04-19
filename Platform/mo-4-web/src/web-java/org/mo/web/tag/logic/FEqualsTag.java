/*
 * @(#)FEqualsTag.java
 *
 * Copyright 2008 Java Frame Studio Corporation.
 * All Rights Reserved.
 *
 */
package org.mo.web.tag.logic;

import org.mo.com.lang.RString;
import org.mo.web.tag.base.FBaseEqualsTag;

/**
 * <T>相等标签</T>
 * <P>判断数据与给定的值是否相等</P>
 * 
 * @author MAOCY
 * @version 1.00 - 2008/08/05 CREATE BY MAOCY
 * @version 1.01 - 2009/01/05 UPDATE BY YANGH
 */
public class FEqualsTag
      extends FBaseEqualsTag
{

   /// 分割标识符
   public final static char SPLIT_CHAR = '|';

   @Override
   public int onEnd(){
      return EVAL_PAGE;
   }

   @Override
   public int onStart(){
      /// 获得被比较内容
      String value = null;
      boolean ignoreCase = false;
      /// 值不为空的情况下
      if(RString.isNotEmpty(_value)){
         /// 忽略大小写
         ignoreCase = true;
         value = _context.parseString(_value);
         /// 
      }else if(RString.isNotEmpty(_casevalue)){
         /// 不忽略大小写
         ignoreCase = false;
         value = _context.parseString(_casevalue);
      }
      /// 比较数据
      String item = _context.parseString(_source);
      if(RString.isNotEmpty(value, item)){
         if(ignoreCase && RString.inRangeIgnoreCase(item, value, SPLIT_CHAR)){
            return EVAL_BODY_INCLUDE;
         }else if(!ignoreCase && RString.inRange(item, value, SPLIT_CHAR)){
            return EVAL_BODY_INCLUDE;
         }
      }
      return SKIP_BODY;
   }
}
