/*
 * @(#)ILogicEvent.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.event;

import org.mo.com.collections.FObjectDictionary;
import org.mo.com.collections.FRow;
import org.mo.com.lang.IAttributes;
import org.mo.com.lang.IComparable;
import org.mo.com.lang.type.TDate;
import org.mo.com.lang.type.TInteger;

/**
 * <T>事件对象的接口。</T>
 * <P>定义事件里可被访问的所有变量。<B/>
 * 对排序的支持。</P>
 * 
 * @author HYKUN
 * @version 1.00 - 2008/10/27
 * @see org.mo.com.lang.IComparable
 */
public interface ILogicEvent
      extends
         IComparable<FLogicEvent>
{
   /**
    * <T>添加事件结果信息。</T>
    * <P>设置结果信息(警告，信息)到结果列表中。</P>
    * 
    * @param result 结果对象
    */
   void addResult(ILogicEventResult result);

   /** 
    * <T>获得事件的开始日期。</T>
    * 
    * @return 开始日期
    */
   TDate beginDate();

   /**
    * <T>比较当前对象和指定对象的大小。</T>
    * <P>比较优先级和创建时间，结果有三种：
    * <UL>
    *    <L value='-1'>本身内容小于指定对象</L>
    *    <L value='0'>本身内容等于指定对象</L>
    *    <L value='1'>本身内容大于指定对象</L>
    * </UL>
    * </P>
    * 
    * @param item 比较对象
    * @param params 比较参数
    * @return 将比较结果返回
    */
   int compare(FLogicEvent item,
               Object... params);

   /** 
    * <T>返回事件的创建日期。</T>
    * 
    * @return 创建日期
    */
   TDate createDate();

   /** 
    * <T>返回事件的创建人标识。</T>
    * 
    * @return 创建人标识
    */
   TInteger createUserId();

   /** 
    * <T>获得事件的创建者名称。</T>
    * 
    * @return 创建者名称
    */
   String createUserName();

   /** 
    * <T>获得事件的结束日期。</T>
    * 
    * @return 结束日期
    */
   TDate endDate();

   /**
    * <T>返回事件的设置列表。</T>
    * 
    * @return 设置列表
    */
   IAttributes eventConfig();

   FObjectDictionary eventParameters();

   /**
    * <T>返回事件组的设置列表。</T>
    * 
    * @return 设置列表
    */
   IAttributes groupConfig();

   /** 
    * <T>返回事件的组标识。</T>
    * 
    * @return 组标识
    */
   TInteger groupId();

   /** 
    * <T>获得事件的组名称。</T>
    * 
    * @return 组名称
    */
   String groupName();

   /** 
    * <T>判断执行结果是否有内容。</T>
    * <P>如果没有任何内容，则认为执行成功。
    * <UL>
    *    <L value='true'>有</L>
    *    <L value='false'>无</L>
    * <UL>
    * </P>
    * 
    * @return 是否有值
    */
   boolean hasResult();

   /** 
    * <T>获得的事件标识。</T>
    * 
    * @return 事件标识
    */
   TInteger id();

   /**
    * <T>从行对象中加载事件设置。</T>
    * 
    * @param row 行对象
    */
   void loadConfig(FRow row);

   /**
    * <T>根据参数名称，获得事件的参数内容。</T>
    * 
    * @param name 参数名称
    * @return 参数内容
    */
   String parameter(String name);

   /**
    * <T>获得事件的参数列表。</T>
    * 
    * @return 参数列表
    */
   IAttributes parameters();

   /** 
    * <T>返回事件的优先级。</T>
    * 
    * @return 优先级
    */
   ELogicEventPriority priority();

   /** 
    * <T>返回处理的调用接口。</T>
    * 
    * @return 调用接口
    */
   String processInvoke();

   /** 
    * <T>返回执行的百分比。</T>
    * 
    * @return 百分比
    */
   TInteger processPercent();

   /** 
    * <T>获得处理类型。</T>
    * 
    * @return 处理类型
    */
   ELogicEventProcess processType();

   /** 
    * <T>获得执行者标识。</T>
    * 
    * @return 执行者标识
    */
   TInteger processUserId();

   /** 
    * <T>获得执行者名称。</T>
    * 
    * @return 执行者名称
    */
   String processUserName();

   /** 
    * <T>获得事件的接收者标识。</T>
    * 
    * @return 接收者标识
    */
   TInteger receiveUserId();

   /** 
    * <T>获得事件的接收者名称。</T>
    * 
    * @return 接收者名称
    */
   String receiveUserName();

   /** 
    * <T>获得结果列表。</T>
    * 
    * @return 结果列表
    */
   FLogicEventResults results();

   /**
    * <T>设置事件执行错误。</T>
    * <P>清空结果列表，设置错误到结果列表中。</P>
    * 
    * @param error 错误对象
    */
   void setError(ILogicEventResult error);

   /**
    * <T>设置事件执行结果超时。</T>
    * <P>清空结果列表，设置超时到结果列表中。</P>
    * 
    * @param timeout 超时对象
    */
   void setTimeout(ILogicEventResult timeout);

   /** 
    * <T>返回事件的执行状态。</T>
    * 
    * @return 执行状态
    */
   ELogicEventStatus status();

   /**
    * <T>获得事件的设置信息。</T>
    * 
    * @return 设置信息
    */
   IAttributes typeConfig();

   /** 
    * <T>返回事件的类型标识。</T>
    * 
    * @return 类型标识
    */
   TInteger typeId();

   /** 
    * <T>获得事件的类型名称。</T>
    * 
    * @return 类型名称
    */
   String typeName();
}
