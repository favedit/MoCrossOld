/*
 * @(#)FWebEncoding.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.web.protocol.common;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FWebEncoding
{

   public static String languageEncoding(String sLanguage){
      if(sLanguage.equalsIgnoreCase("zh")){
         return "GB2312";
      }else if(sLanguage.equalsIgnoreCase("jp")){
         return "Shift-JIS";
      }
      return "UTF-8";
   }

}
