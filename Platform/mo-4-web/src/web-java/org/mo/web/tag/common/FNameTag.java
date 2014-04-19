/*
 * @(#)FNameTag.java
 *
 * Copyright 2008 Java Frame Studio Corporation.
 * All Rights Reserved.
 *
 */
package org.mo.web.tag.common;

import org.mo.com.lang.RString;
import org.mo.web.protocol.context.SPageItem;
import org.mo.web.tag.base.FBaseNameTag;

/**
 * <T>查找名字标签</T>
 * <P>根据值查找对应的名字</P>
 *
 * @author MAOCY
 * @version 1.00 - 2008/08/05 CREATE BY MAOCY
 * @version 1.01 - 2009/01/05 UPDATE BY YANGH
 */
public class FNameTag
      extends FBaseNameTag
{

   @Override
   public int onEnd(){
      /// 返回逻辑执行结果
      return EVAL_PAGE;
   }

   @Override
   public int onStart(){
      if(RString.isEmpty(_name)){
         SPageItem item = _context.parseItem(_source);
         _writer.append(item.name);
      }else{
         _writer.append(_name);
      }
      _writer.flush();
      return SKIP_BODY;
   }

}
