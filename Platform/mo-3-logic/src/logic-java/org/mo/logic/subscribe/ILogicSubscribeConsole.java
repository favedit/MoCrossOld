/*
 * @(#)ILogicSubscribeConsole.java
 *
 * Copyright 2008 microbject. All Rights Reserved.
 *
 */
package org.mo.logic.subscribe;

/**
 * <T>订阅线程的初始化和启动线程与创建订阅的控制接口类</T>
 * <P title='订阅线程的初始化'>初始化订阅线程并启动订阅线程</P>
 * <P title='创建订阅'>根据订阅的设置调用订阅的java代理包方法来创建订阅</P> 
 * 
 * @author YJHUA
 * @version 1.00 - 2008/10/29
 * @see <R link='jv-c:org.mo.logic.subscribe.FLogicSubscribeDeployThread'>FLogicSubscribeDeployThread</R>
 * @see <R link='jv-m:org.mo.logic.data.impl.FLgSubscribeDiImpl|doPublish'>FLgSubscribeDiImpl.doPublish</R>
 */
public interface ILogicSubscribeConsole
{
   /**
    * <T>实现订阅线程的初始化和启动订阅线程</T>
    */
   void initialize();

   /** 
    * <T>通过订阅包订阅</T>
    * <P>必须设好pack包</P>
    * 
    * @see <R link='ps-m:LG_SUBSCRIBE_DI|Do_Publish(logic_, user_id_, type_id_, link_id_, pack_)'>
    * LG_SUBSCRIBE_DI.Do_Publish</R>
    */
   void doSubscribe(ILogicSubscribeAction pack);

   /** 
    * <T>通过订阅包订阅</T>
    * <P>pack_打包方式为：<B/>
    *    事件标识1=参数1<B/>
    *    事件标识2=参数2<B/>
    *     ....    ....<B/>
    * </P>
    * 
    * @param pack pack包
    * @see <R link='ps-m:LG_SUBSCRIBE_DI|Do_Publish(logic_, user_id_, type_id_, link_id_, action_id_, pack_)'>
    * LG_SUBSCRIBE_DI.Do_Publish</R>
    */
   void doSubscribe(ILogicSubscribeEvent pack);

   /** 
    * <T>通过参数订阅</T>
    * 
    * @param parameters 订阅参数
    * @see <R link='ps-m:LG_SUBSCRIBE_DI|Do_Publish(logic_, user_id_, type_id_, link_id_, action_id_, event_id_, parameters)'>
    * LG_SUBSCRIBE_DI.Do_Publish</R>
    */
   void doSubscribe(String parameters);
}
