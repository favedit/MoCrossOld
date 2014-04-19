/*
 * @(#)FWebUser.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.protocol.common;

import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FObjectId;
import org.mo.com.lang.IAttributes;

/**
 * <T>设置登录WEB系统的用户</T>
 * 
 * @author maocy
 * @version 1.00 - 2008/11/19
 */
public class FWebUser
      extends FObjectId
      implements
         IWebUser
{

   // 设置信息
   private FAttributes _configuration;

   // 参数信息
   private FAttributes _parameters;

   // 属性信息
   private FAttributes _properties;

   private String _userId;

   /* (non-Javadoc)
    * @see org.mo.web.protocol.common.IWebUser#configuration()
    */
   public IAttributes configuration(){
      if(null == _configuration){
         _configuration = new FAttributes();
      }
      return _configuration;
   }

   /* (non-Javadoc)
    * @see org.mo.web.protocol.common.IWebUser#isLogin()
    */
   public boolean isLogin(){
      if(null != _userId){
         if("0".equals(_userId)){
            return false;
         }
         return _userId.length() > 0;
      }
      return false;
   }

   /* (non-Javadoc)
    * @see org.mo.web.protocol.common.IWebUser#parameters()
    */
   public IAttributes parameters(){
      if(null == _parameters){
         _parameters = new FAttributes();
      }
      return _parameters;
   }

   /* (non-Javadoc)
    * @see org.mo.web.protocol.common.IWebUser#properties()
    */
   public IAttributes properties(){
      if(null == _properties){
         _properties = new FAttributes();
      }
      return _properties;
   }

   public void setUserId(String userId){
      _userId = userId;
   }

   public String userId(){
      return _userId;
   }

}
