/*
 * @(#)IWebContentType.java
 *
 * Copyright 2004 Java Frame Studio Corporation.
 * All Rights Reserved.
 *
 */
package org.mo.web.protocol.common;

/**
 * <p>WEB应用程序类型标识</p>
 *
 * @author FEDT
 * @version 1.00 - 2005/02/18
 */
public interface IWebContentType
{

   /**
    * <p>HTML</p>
    */
   public final static String HTML = "text/html;charset=UTF-8";

   /**
    * <p>WML</p>
    */
   public final static String WML = "text/vnd.wap.wml;charset=UTF-8";

   /**
    * <p>XML</p>
    */
   public final static String XML = "text/xml";

   public final static String PDF = "application/pdf";

   public final static String STREAM = "application/octet-stream";
}
