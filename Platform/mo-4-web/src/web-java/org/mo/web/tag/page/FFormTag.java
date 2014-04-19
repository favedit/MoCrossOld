/*
 * @(#)FFormTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.tag.page;

import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.web.tag.base.FBaseFormTag;

public class FFormTag
      extends FBaseFormTag
{

   /**
    * <T>按键起来的事件</T>
    */
   public final static String INSTANCE = "instance.web.form";

   @Override
   public int onEnd(){
      _writer.append("</FORM>");
      _writer.flush();
      _context.undefine(INSTANCE);
      return EVAL_PAGE;
   }

   @Override
   public int onStart(){
      _writer.append("<FORM method='POST'");
      // name attribute
      if(RString.isEmpty(_name)){
         _name = "fmMain";
      }
      _writer.appendAttribute("name", _name);
      // enctype attribute
      if(!RString.isEmpty(_multipart)){
         if(RBoolean.parse(_context.parseString(_multipart))){
            _writer.appendAttribute("enctype", "multipart/form-data");
         }
      }else if(!RString.isEmpty(_enctype)){
         _writer.appendAttribute("enctype", _enctype);
      }
      _writer.appendLine(">");
      // action attribute
      _writer.appendLine("<INPUT type='hidden' name='_environment'></INPUT>");
      _writer.flush();
      _context.define(INSTANCE, this);
      return EVAL_BODY_INCLUDE;
   }

}
