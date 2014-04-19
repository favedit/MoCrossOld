/*
 * @(#)FNotEmptyTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.tag.logic;

import org.mo.com.lang.FObjects;
import org.mo.com.lang.FString;
import org.mo.com.lang.RString;
import org.mo.com.lang.temp.FContainer;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlNodes;
import org.mo.web.tag.base.FBaseNotEmptyTag;

/**
 * <T>不为空标签</T>
 * <P>判断数据是否为空</P>
 * 
 * @author MAOCY
 * @version 1.00 - 2008/08/05 CREATE BY MAOCY
 * @version 1.01 - 2009/01/05 UPDATE BY YANGH
 */
public class FNotEmptyTag
      extends FBaseNotEmptyTag
{

   @Override
   public int onEnd(){
      /// 返回逻辑执行结果
      return EVAL_PAGE;
   }

   @Override
   public int onStart(){
      boolean result = false;
      Object item = _context.parse(_source);
      /// 如果类型为String
      if(item instanceof String){
         result = !RString.isEmpty((String)item);
         /// 如果类型为FString 
      }else if(item instanceof FString){
         result = !((FString)item).isEmpty();
         /// 如果类型为FContainer 
      }else if(item instanceof FContainer){
         result = !((FContainer)item).isEmpty();
         /// 如果类型为FObjects 
      }else if(item instanceof FObjects){
         result = !((FObjects<?>)item).isEmpty();
         /// 如果类型为FXmlNodes 
      }else if(item instanceof FXmlNodes){
         result = !((FXmlNodes)item).isEmpty();
         /// 如果类型为FXmlNode 
      }else if(item instanceof FXmlNode){
         result = ((FXmlNode)item).hasNode();
         /// 如果不为空 
      }else if(item != null){
         result = true;
      }
      return result ? EVAL_BODY_INCLUDE : SKIP_BODY;
   }
}
