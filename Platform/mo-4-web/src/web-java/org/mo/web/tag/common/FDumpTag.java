/*
 * @(#)FDumpTag.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.web.tag.common;

import org.mo.com.lang.FError;
import org.mo.com.lang.RDump;
import org.mo.com.lang.generic.MString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.web.core.format.RHtmlFormat;

/**
 * <T>别称定义标签</T>
 * <P>将指定的内容（别称、对象、源描述信息）定一个别称</P>
 *
 * @author MAOCY
 * @version 1.00 - 2008/08/05 CREATE BY MAOCY
 * @version 1.01 - 2009/01/05 UPDATE BY YANGH
 */
public class FDumpTag
      extends FAbstractTag
{

   /// 定义日志
   private static ILogger _logger = RLogger.find(FDumpTag.class);

   /// 指定内容
   private String _item = null;

   @Override
   public int onEnd(){
      /// 返回逻辑执行结果
      return EVAL_PAGE;
   }

   /**
    * <P>开始标签的逻辑</P>
    * <P>create date:2008/08/05</P>
    * <P>update date:2009/01/05</P>
    *
    * @param oContext 环境对象
    * @return 逻辑执行后的状态
    * @exception FError 逻辑例外
    */
   @Override
   public int onStart(){
      MString dump = RDump.dump(_context.parse(_item));
      if(_logger.debugAble()){
         _logger.debug(this, "onStart", dump.toString());
      }
      _writer.append(RHtmlFormat.textToHtml(dump.toString()));
      return EVAL_PAGE;
   }

   /**
    * <P>释放标签所使用的资源</P>
    * <P>create date:2008/08/05</P>
    * <P>update date:2009/01/05</P>
    *
    */
   @Override
   public void release(){
      _item = null;
      super.release();
   }

   /**
    * <P>设置指定内容</P>
    * <P>create date:2008/08/05</P>
    * <P>update date:2009/01/05</P>
    *
    * @param sItem 指定内容
    */
   public void setItem(String sItem){
      _item = sItem;
   }
}
