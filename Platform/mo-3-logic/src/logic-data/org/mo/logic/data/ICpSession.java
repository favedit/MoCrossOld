/*
 * @(#)ICpSession.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.data;

import org.mo.com.data.FSqlFunction;
import org.mo.com.data.FSqlProcedure;

/**
 * <T>数据库逻辑包(CP_SESSION)的代理对象接口</T>
 *
 * @author MAOCY
 * @version 1.0.1
 */
public interface ICpSession
{
   /**
    * <T>建立当前线程的版本信息。</T>
    * <P>格式为：修改操作标识(1位)+日期时间(8+6=14位)+用户标识(8位)。</P>
    *
    * @param action 修改操作标识
    * @return 版本信息
    */
   FSqlFunction build(Object action);

   /**
    * <T>根据记录的编号删除一条记录</T>
    *
    * @param logic_ 业务逻辑
    * @param params_ 打包字符串
    */
   FSqlProcedure doDelete(Object logic,
                          Object params);

   /**
    * <T>在数据集内新建一条记录</T>
    *
    * @param logic_ 业务逻辑
    * @param params_ 打包字符串
    */
   FSqlProcedure doInsert(Object logic,
                          Object params);

   /**
    * <T>根据记录的编号和数据集同步一条记录</T>
    * <UL>
    * <L>如果指定的记录不存在，进行新建处理。</L>
    * <L>如果指定的记录存在，进行更新处理。</L>
    * </UL>
    *
    * @param logic_ 业务逻辑
    * @param params_ 打包字符串
    */
   FSqlProcedure doSync(Object logic,
                        Object params);

   /**
    * <T>根据记录的编号修改一条记录</T>
    *
    * @param logic_ 业务逻辑
    * @param params_ 打包字符串
    */
   FSqlProcedure doUpdate(Object logic,
                          Object params);

   /**
    *
    * @param name
    * @return
    */
   FSqlFunction getParameter(Object name);

   /**
    * <T>判断指定用户当前是否在线。</T>
    *
    * @param userId 用户标识　
    * @return
    */
   FSqlFunction isActiveUser(Object userId);

   /**
    * <T>获得当前用户语言标识。</T>
    *
    * @return 语言标识
    */
   FSqlFunction languageId();

   /**
    * <T>使用线程标识关联当前系统。</T>
    *
    * @param logic 逻辑信息
    * @param parameters 参数信息
    */
   FSqlProcedure link(Object logic,
                      Object parameters);

   /**
    * <T>登录当前系统。</T>
    * <P>参数信息中必须含有用户通行证信息<C>Passport</C>和用户密码<C>Password</C>信息。</P>
    * <P>如果已经登录过，如果用户标识相同则关联后直接返回。</P>
    * <P>如果登录成功，则返回登录后信息。并自动关联当前线程</P>
    *
    * @param logic 逻辑信息
    * @param parameters 参数信息
    * @param configuration
    * @param properties
    * @param catalogPack
    * @param componentPack
    * @param notifyPack
    * @param friendPack
    * @param visitPack
    */
   FSqlProcedure login(Object logic,
                       Object parameters,
                       Object configuration,
                       Object properties,
                       Object catalogPack,
                       Object componentPack,
                       Object notifyPack,
                       Object friendPack,
                       Object visitPack);

   /**
    * <T>直接登录当前系统。</T>
    * <P>参数中只有<C>LOGIN_ID</C>是必须的，登录用户的标识。</P>
    *
    * @param logic 逻辑信息
    * @param parameters 参数信息
    * @param configuration
    * @param properties
    * @param catalogPack
    * @param componentPack
    * @param notifyPack
    * @param friendPack
    * @param visitPack
    */
   FSqlProcedure loginDirect(Object logic,
                             Object parameters,
                             Object configuration,
                             Object properties,
                             Object catalogPack,
                             Object componentPack,
                             Object notifyPack,
                             Object friendPack,
                             Object visitPack);

   /**
    * <T>记录登记系统信息。</T>
    * <P>参数信息中必须含有用户通行证信息<C>Passport</C>和用户密码<C>Password</C>信息。</P>
    *
    * @param logic 逻辑信息
    * @param parameters 参数信息
    * @param configuration
    * @param properties
    * @param catalogPack
    * @param componentPack
    * @param notifyPack
    * @param friendPack
    * @param visitPack
    */
   FSqlProcedure LoginUserHistory(Object logic,
                                  Object parameters);

   /**
    * <T>登出当前系统。</T>
    * <P>如果登出成功，则不允许当前线程再次进行关联。</P>
    *
    * @param logic 逻辑信息
    * @param parameters 参数信息
    */
   FSqlProcedure logout(Object logic,
                        Object parameters);

   /**
    * <T>获得当前线程标识。</T>
    *
    * @return 线程标识
    */
   FSqlFunction sessionId();

   /**
    * <T>使用线程标识取消当前线程的关联。</T>
    * <P>可以取回记录操作中产生的一些信息。</P>
    *
    * @param logic 逻辑信息
    * @param parameters 参数信息
    * @param logger
    */
   FSqlProcedure unlink(Object logic,
                        Object parameters,
                        Object logger);

   /**
    * <T>获得当前用户标识。</T>
    *
    * @return 用户标识
    */
   FSqlFunction userId();
}
