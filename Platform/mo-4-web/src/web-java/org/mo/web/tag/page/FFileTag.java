/*
 * @(#)FFileTag.java
 *
 * Copyright 2004 Java Frame Studio Corporation.
 * All Rights Reserved.
 *
 */

package org.mo.web.tag.page;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RString;
import org.mo.web.protocol.context.SPageItem;
import org.mo.web.tag.base.FBaseFileTag;

/**
 * <T>下拉列表标签</T>
 * <P>当格式化方式为指定时,格式化为HTML代码</P>
 * 
 * @author MAOCY
 * @version 1.00 - 2008/08/05 CREATE BY MAOCY
 * @version 1.01 - 2009/01/05 UPDATE BY YANGH
 */
public class FFileTag
      extends FBaseFileTag
{

   @Override
   public int onEnd(){
      /// 返回执行结果
      return EVAL_PAGE;
   }

   /**
    * <T>开始标签逻辑。</T>
    *
    * @return 逻辑执行后的状态
    */
   @Override
   public int onStart(){
      /// 获得数据源的值
      SPageItem item = _context.parseItem(_source);
      String name = RString.nvl(_name, item.name);
      if(RString.isEmpty(name)){
         throw new FFatalError("Item name is null. (source={0})", _source);
      }
      // 生成内容
      _writer.append("<INPUT type='FILE'");
      _writer.append(" name=\"", name, "\"");
      if(!RString.isEmpty(_size)){
         _writer.append(" size=\"", _context.parseString(_size), "\"");
      }
      if(!RString.isEmpty(_width)){
         appendStyle("width", _width);
      }
      if(!RString.isEmpty(_height)){
         appendStyle("height", _height);
      }
      appendHtml();
      _writer.append(">");
      _writer.flush();
      return 0;
   }

}
