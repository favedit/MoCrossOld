/*
 * @(#)EActionResult.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.core.action;

/**
 * <T>页面命令处理结果类型。</T>
 * 
 * @author MAOCY
 * @version 1.00 - 2008/12/19
 */
public enum EActionResult{

   /**
    * <T>逻辑错误。</T>
    */
   Error,

   /**
    * <T>系统错误。</T>
    */
   Fatal,

   /**
    * <T>提示信息。</T>
    */
   Info,

   /**
    * <T>用户超时错误。</T>
    */
   Timeout,

   /**
    * <T>警告信息。</T>
    */
   Warn

}
