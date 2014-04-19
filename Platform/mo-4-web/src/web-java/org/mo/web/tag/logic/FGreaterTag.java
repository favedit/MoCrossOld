/*
 * @(#)FGreaterTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.tag.logic;

import org.mo.com.lang.RFloat;
import org.mo.com.lang.RInteger;
import org.mo.com.lang.RString;
import org.mo.web.tag.base.FBaseGreaterTag;

/**
 * <T>大于标签</T>
 * <P>判断数据是否比给的那个的值大</P>
 * 
 * @author MAOCY
 * @version 1.00 - 2008/08/05 CREATE BY MAOCY
 * @version 1.01 - 2009/01/05 UPDATE BY YANGH
 */
public class FGreaterTag
      extends FBaseGreaterTag
{

   @Override
   public int onEnd(){
      /// 返回逻辑执行结果
      return EVAL_PAGE;
   }

   @Override
   public int onStart(){
      /// 获得被比较内容
      /// 如果是字符串
      String value = null;
      boolean ignoreCase = false;
      if(RString.isNotEmpty(_value)){
         ignoreCase = true;
         value = _context.parseString(_value);
      }
      /// 比较数据
      String item = _context.parseString(_source);
      if("String".equalsIgnoreCase(_type)){
         /// 如果是字符串
         if(RString.isNotEmpty(value, item)){
            if(ignoreCase && RString.compare(item, value, false) > 1){
               return EVAL_BODY_INCLUDE;
            }else if(!ignoreCase){
               return EVAL_BODY_INCLUDE;
            }
         }
      }else if("Integer".equalsIgnoreCase(_type)){
         /// 如果是整型
         int nValue = RInteger.parse(value);
         int nItem = RInteger.parse(item);
         if(nItem > nValue){
            return EVAL_BODY_INCLUDE;
         }
      }else if("Float".equalsIgnoreCase(_type)){
         /// 如果是浮点型
         float nValue = RFloat.parse(value);
         float nItem = RFloat.parse(item);
         if(nItem > nValue){
            return EVAL_BODY_INCLUDE;
         }
      }
      return SKIP_BODY;
   }
}
