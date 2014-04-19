/*
 * @(#)FIconTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.jfa.web.tag;

import org.mo.com.lang.RString;
import org.mo.web.tag.base.FBaseImgTag;

/**
 * <p>图像标签</p>
 *
 * @author ALEX
 */
public class FIconTag
      extends FBaseImgTag
{

   protected String _source;

   @Override
   public int onEnd(){
      return EVAL_PAGE;
   }

   /**
    * <p>开始标签逻辑</p>
    *
    * @return 逻辑执行后的状态
    */
   @Override
   public int onStart(){
      _writer.append("<IMG");
      if(RString.isNotEmpty(_source)){
         // Icon
         String source = _context.parseString(_source);
         source = RString.replace(source, ".", "/");
         source = "/system/ars/icon/" + source + ".gif";
         _writer.append(" class='Tag_Icon'");
         _writer.append(" src=\"");
         _writer.append(_context.contextPath(source));
         _writer.append("\" align='absmiddle'");
      }else{
         _writer.append(" src=\"");
         _writer.append(_context.contextPath("/ars/icon/n.gif"));
         _writer.append("\"");
      }
      appendHtml();
      _writer.append(">");
      _writer.flush();
      return EVAL_BODY_INCLUDE;
   }

   public void setSource(String source){
      _source = source;
   }

}
