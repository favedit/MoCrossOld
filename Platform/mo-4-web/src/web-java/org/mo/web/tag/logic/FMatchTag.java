/*
 * @(#)FMatchTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.tag.logic;

import org.mo.com.lang.RInteger;
import org.mo.com.lang.RString;
import org.mo.web.tag.base.FBaseMatchTag;

/**
 * <T>匹配标签</T>
 * <P>判断数据与给定的值是否匹配</P>
 * 
 * @author MAOCY
 * @version 1.00 - 2008/08/05 CREATE BY MAOCY
 * @version 1.01 - 2009/01/05 UPDATE BY YANGH
 */
public class FMatchTag
      extends FBaseMatchTag
{

   @Override
   public int onEnd(){
      /// 返回逻辑执行结果
      return EVAL_PAGE;
   }

   @SuppressWarnings("unused")
   @Override
   public int onStart(){
      /// 获得被比较内容
      String value = null;
      value = _context.parseString(_value);
      /// 判断是否和指定的字符串匹配
      String item = _context.parseString(_source);
      /// 索引开始的位置
      int beginIndex = RInteger.parse(RString.nvl(_begin, 0));
      /// 索引结束的位置
      int endIndex = RInteger.parse(RString.nvl(_end, item.length()));
      /// 截取后的数据
      item = item.substring(beginIndex, endIndex);
      /// 匹配的位数
      int length = RInteger.parse(_length);
      /// 数据不为空的情况下
      if(RString.isNotEmpty(value, item)){
         if(RString.matchString(item, value)){
            return EVAL_BODY_INCLUDE;
         }
      }
      return SKIP_BODY;
   }
}
