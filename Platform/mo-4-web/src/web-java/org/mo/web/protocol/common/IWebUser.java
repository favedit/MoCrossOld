/*
 * @(#)IWebUser.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.protocol.common;

import org.mo.com.lang.IAttributes;

/**
 * <T>登录WEB系统的用户信息接口。</T>
 * 
 * @author maocy
 * @version 1.00 - 2008/11/19
 */
public interface IWebUser
{

   /**
    * <T>获得设置信息。</T>
    *
    * @return 设置信息
    */
   IAttributes configuration();

   /**
    * <T>判断当前用户是否登录。</T>
    *
    * @return TRUE：是<B/>FALSE：否
    */
   boolean isLogin();

   /**
    * <T>获得参数信息。</T>
    *
    * @return 参数信息
    */
   IAttributes parameters();

   /**
    * <T>获得属性信息。</T>
    *
    * @return 属性信息
    */
   IAttributes properties();

   void setUserId(String userId);

   String userId();

}
