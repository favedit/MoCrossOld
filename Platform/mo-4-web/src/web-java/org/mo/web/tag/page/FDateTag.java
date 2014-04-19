/*
 * @(#)FEditTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.tag.page;

import org.mo.com.lang.RDateTime;
import org.mo.com.lang.RString;
import org.mo.com.lang.type.TDateTime;
import org.mo.core.aop.RAop;
import org.mo.data.date.IDataDateConsole;
import org.mo.web.tag.base.FBaseDateTag;

/**
 * <T>编辑控件标签</T>
 * <P>当格式化方式为指定时,格式化为HTML代码</P>
 * 
 * @author MAOCY
 */
public class FDateTag
      extends FBaseDateTag
{

   /**
    * <T>定义编辑控件类型</T>
    */
   protected String _type;

   @Override
   public int onEnd(){
      /// 返回执行结果
      return EVAL_PAGE;
   }

   @Override
   public int onStart(){
      /// 获得数据源的值
      if("@sql.date".equalsIgnoreCase(_source)){
         IDataDateConsole dateConsole = RAop.find(IDataDateConsole.class);
         TDateTime date = dateConsole.currentDate();
         _writer.append(RDateTime.format(date.get()));
      }else{
         String source = _context.parseString(_source);
         if(!RString.isEmpty(source)){
            String input = RString.nvl(_input, RDateTime.DEFAULT_FORMAT);
            String format = RString.nvl(_format, RDateTime.DEFAULT_FORMAT);
            String result = RDateTime.format(source, input, format);
            _writer.append(result);
         }
      }
      /// 刷新字符流
      _writer.flush();
      return SKIP_BODY;
   }
}
