/*
 * @(#)FIncludeTag
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.tag.common;

import org.mo.com.lang.FFatalError;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.web.tag.base.FBaseIncludeTag;

/**
 * <T>包含页面标签</T>
 * <P>根据指定的内容信息，在指定位置包含其它页面</P>
 *
 * @author MAOCY
 * @version 1.00 - 2008/08/05 CREATE BY MAOCY
 * @version 1.01 - 2009/01/05 UPDATE BY YANGH
 */
public class FIncludeTag
      extends FBaseIncludeTag
{

   /// 定义日志器
   private static ILogger _logger = RLogger.find(FIncludeTag.class);

   @Override
   public int onEnd(){
      /// 返回逻辑执行结果
      return EVAL_PAGE;
   }

   /**
    * <P>开始标签的逻辑</P>
    *
    * @return 逻辑执行后的状态
    */
   @Override
   public int onStart(){
      try{
         String src = _context.parseString(_src);
         if(_logger.debugAble()){
            _logger.debug(this, "onStart", "Include page: {0}", src);
         }
         pageContext().include(src);
      }catch(Throwable t){
         throw new FFatalError(t);
      }
      return EVAL_PAGE;
   }
}
