/*
 * @(#)IWebHeaderType.java
 *
 * Copyright 2004 Java Frame Studio Corporation.
 * All Rights Reserved.
 *
 */
package org.mo.web.protocol.common;

/**
 * <p>WEB应用程序文件头定义</p>
 *
 * @author FEDT
 * @version 1.00 - 2005/02/18
 */
public interface IWebHeaderType
{

   /**
    * <p>PRAGMA</p>
    */
   public final static String PRAGMA = "Pragma";

   /**
    * <p>不需要缓冲</p>
    */
   public final static String NO_CACHE = "No-cache";

   /**
    * <p>缓冲控制</p>
    */
   public final static String CACHE_CONTROL = "Cache-Control";

   /**
    * <p>失效</p>
    */
   public final static String EXPIRES = "Expires";
}
