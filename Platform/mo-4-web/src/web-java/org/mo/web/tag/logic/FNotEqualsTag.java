/*
 * @(#)FNotEqualsTag.java
 *
 * Copyright 2008 Java Frame Studio Corporation.
 * All Rights Reserved.
 *
 */
package org.mo.web.tag.logic;

import org.mo.com.lang.RString;
import org.mo.web.tag.base.FBaseNotEqualsTag;

/**
 * <T>不相等标签</T>
 * <P>判断数据是否和指定的值相等</P>
 * 
 * @author MAOCY
 * @version 1.00 - 2008/08/05 CREATE BY MAOCY
 * @version 1.01 - 2009/01/05 UPDATE BY YANGH
 */
public class FNotEqualsTag
      extends FBaseNotEqualsTag
{

   /// 定义分割字符标识
   public final static char SPLIT_CHAR = '|';

   @Override
   public int onEnd(){
      /// 返回逻辑执行结果
      return EVAL_PAGE;
   }

   @Override
   public int onStart(){
      /// 获得被比较内容
      String value = null;
      /// 是否忽略大小写
      boolean ignoreCase = false;
      /// 忽略大小写
      if(RString.isNotEmpty(_value)){
         ignoreCase = true;
         value = _context.parseString(_value);
         /// 不忽略大小写
      }else if(RString.isNotEmpty(_casevalue)){
         ignoreCase = false;
         value = _context.parseString(_casevalue);
      }
      /// 比较数据
      String item = _context.parseString(_source);
      if(RString.isNotEmpty(value, item)){
         if(ignoreCase && RString.inRangeIgnoreCase(item, value, SPLIT_CHAR)){
            return SKIP_BODY;
         }else if(!ignoreCase && RString.inRange(item, value, SPLIT_CHAR)){
            return SKIP_BODY;
         }
      }
      return EVAL_BODY_INCLUDE;
   }
}
