/*
 * @(#)FCssTag.java
 *
 * Copyright 2008 Java Frame Studio Corporation.
 * All Rights Reserved.
 *
 */
package org.mo.jfa.web.tag;

import org.mo.web.tag.base.FBaseCssTag;

public class FCssTag
      extends FBaseCssTag
{

   @Override
   public int onEnd(){
      return EVAL_PAGE;
   }

   /**
    * <p>开始标签的逻辑</p>
    *
    * @return 逻辑执行后的状态
    */
   @Override
   public int onStart(){
      _writer.append("<LINK rel='stylesheet' href='");
      _writer.append(_context.contextPath("/acs/control.css"));
      _writer.appendLine("' type='text/css'>");
      // ---
      _writer.append("<LINK rel='stylesheet' href='");
      _writer.append(_context.contextPath("#/acs/control.css"));
      _writer.appendLine("' type='text/css'>");
      // ---
      _writer.append("<LINK rel='stylesheet' href='");
      String language = _context.session().culture().country().language();
      String src = "#/acs/lang_" + language.toLowerCase() + ".css";
      _writer.append(_context.contextPath(src));
      _writer.appendLine("' type='text/css'>");
      // ---
      _writer.append("<LINK rel='stylesheet' href='");
      src = "#/acs/site_" + language.toLowerCase() + ".css";
      _writer.append(_context.contextPath(src));
      _writer.append("' type='text/css'>");
      // ---
      _writer.flush();
      return SKIP_BODY;
   }
}
