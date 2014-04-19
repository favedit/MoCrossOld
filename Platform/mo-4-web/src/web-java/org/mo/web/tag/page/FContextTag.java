/*
 * @(#)FContextTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.tag.page;

import org.mo.com.lang.RString;
import org.mo.web.tag.base.FBaseContextTag;

/**
 * <T>下拉列表标签</T>
 * <P>当格式化方式为指定时,格式化为HTML代码</P>
 * 
 * @author MAOCY
 * @version 1.00 - 2008/08/05 CREATE BY MAOCY
 * @version 1.01 - 2009/01/05 UPDATE BY YANGH
 */
public class FContextTag
      extends FBaseContextTag
{

   /**
    * <T>按键起来的事件</T>
    */
   private String _path;

   @Override
   public int onEnd(){
      return EVAL_PAGE;
   }

   @Override
   public int onStart(){
      _writer.append(_context.contextPath());
      String path = _context.parseString(_path);
      if(RString.isNotEmpty(path)){
         if(_path.startsWith("#")){
            _writer.append(_context.contextTag());
            _writer.append(path.substring(1));
         }else{
            _writer.append(path);
         }
      }else{
         _writer.append(_context.contextTag());
      }
      _writer.flush();
      return SKIP_BODY;
   }

   @Override
   public void setPath(String path){
      _path = path;
   }

}
