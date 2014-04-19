/*
 * @(#)FSqlPackage.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.eng.data.common;

import org.mo.com.data.FSqlFunction;
import org.mo.com.data.FSqlProcedure;
import org.mo.com.data.ISqlConnect;
import org.mo.com.data.MSqlConnect;
import org.mo.com.lang.generic.IStringNamed;
import org.mo.com.message.FMessages;

/**
 * <p>数据库包处理对象</p>
 * 
 * @author maocy
 * @version 1.00 - 2008/08/10
 */
public class FSqlPackage
      extends MSqlConnect
      implements
         IStringNamed
{
   // 消息列表
   private FMessages _messages;

   // 包名称
   private final String _name;

   /**
    * <p>创建数据库包处理对象的实例</p>
    * 
    * @param connect 可获得链接的接口
    * @param messages 消息列表
    * @param packageName 包名称
    */
   public FSqlPackage(ISqlConnect connect,
                      FMessages messages,
                      String packageName){
      super(connect);
      _name = packageName;
      _messages = messages;
   }

   /**
    * <p>创建数据库包处理对象的实例</p>
    * 
    * @param connect 可获得链接的接口
    * @param packageName 包名称
    */
   public FSqlPackage(ISqlConnect connect,
                      String packageName){
      super(connect);
      _name = packageName;
   }

   /**
    * 执行一个数据库包内的函数
    * 
    * @param sqlFunction 函数
    */
   public void execute(FSqlFunction sqlFunction){
      sqlFunction.setLogicName(_name);
      activeConnection().execute(sqlFunction);
   }

   /**
    * 执行一个数据库包内的存储过程
    * 
    * @param sqlProcedure 存储过程
    */
   public void execute(FSqlProcedure sqlProcedure){
      sqlProcedure.setLogicName(_name);
      activeConnection().execute(sqlProcedure);
   }

   /**
    * <p>获得消息列表</p>
    * 
    * @return 消息列表
    */
   public FMessages messages(){
      return _messages;
   }

   /**
    * <p>获得数据库包名称</p>
    * 
    * @return 名称
    */
   @Override
   public String name(){
      return _name;
   }
}
