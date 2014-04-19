/*
 * @(#)ILgSubscribeDi.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.data;

import org.mo.com.data.FSqlFunction;
import org.mo.com.data.FSqlProcedure;
import org.mo.data.logicunit.common.ILogicUnit;

/**
 * <T>数据库逻辑包(LG_SUBSCRIBE_DI)的代理对象接口</T>
 *
 * @author MAOCY
 * @version 1.0.1
 */
public interface ILgSubscribeDi
      extends
         ILogicUnit
{
   /**
    * <T>查询当前数据集中是否含有指定的编号</T>
    *
    * @param objectId 对象的编号
    * @return 是否含有指定编号
    */
   FSqlFunction contains(Object objectId);

   /**
    * <T>根据记录编号查找一条记录后将记录的所有字段数据打包成一个字符串</T>
    *
    * @param objectId 对象编号
    * @return 打包字符串
    */
   FSqlFunction pack(Object objectId);

   /**
    * <T>获得当前数据集对应的序列的编号</T>
    *
    * @return 序列的编号
    */
   FSqlFunction currentId();

   /**
    * <T>获得当前数据集对应的序列的下一个编号</T>
    *
    * @return 序列的编号
    */
   FSqlFunction nextId();

   /**
    * <T>获得对象的信息。</T>
    *
    * @param objectId 对象的编号
    * @return 对象信息
    */
   FSqlFunction getOinf(Object objectId);

   /**
    * <T>查询当前数据集中是否含有指定的编号。</T>
    *
    * @param ogid 对象全局标识
    * @return 是否含有指定编号
   @return 对象标识
    */
   FSqlFunction containsIdByOgid(Object ogid);

   /**
    * <T>查询当前数据集中是否含有指定的编号。</T>
    *
    * @param userId 订阅者标识
    * @param typeId 类型标识
    * @param linkId 关联标识
    * @return 是否含有指定编号
   @return 对象标识
    */
   FSqlFunction containsIdByLink(Object userId,
                                 Object typeId,
                                 Object linkId);

   /**
    * <T>根据对象全局标识(<C>OGID</C>)获得当前记录的编号</T>
    * <UL>
    * <L>若存在则返回被查询的记录对象的唯一标识。</L>
    * <L>如果记录不存在，则产生记录不存在的例外。</L>
    * </UL>
    *
    * @param ogid 对象全局标识
    * @return 对象标识
    */
   FSqlFunction getIdByOgid(Object ogid);

   /**
    * <T>根据(<C>LINK</C>)获得当前记录的编号</T>
    * <UL>
    * <L>若存在则返回被查询的记录对象的唯一标识。</L>
    * <L>如果记录不存在，则产生记录不存在的例外。</L>
    * </UL>
    *
    * @param userId 订阅者标识
    * @param typeId 类型标识
    * @param linkId 关联标识
    * @return 对象标识
    */
   FSqlFunction getIdByLink(Object userId,
                            Object typeId,
                            Object linkId);

   /**
    * <T>根据对象全局标识(<C>OGID</C>)查找当前记录的编号</T>
    *
    * @param ogid 对象全局标识
    * @param exists 存在标志
    * @return 对象标识
    */
   FSqlFunction findIdByOgid(Object ogid,
                             Object exists);

   /**
    * <T>根据(<C>LINK</C>)查找当前记录的编号</T>
    *
    * @param userId 订阅者标识
    * @param typeId 类型标识
    * @param linkId 关联标识
    * @param exists 存在标志
    * @return 对象标识
    */
   FSqlFunction findIdByLink(Object userId,
                             Object typeId,
                             Object linkId,
                             Object exists);

   /**
    * <T>根据记录的编号查找对象全局标识(<C>OGID</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 对象全局标识
    */
   FSqlFunction getOgid(Object objectId);

   /**
    * <T>根据记录的编号查找对象版本(<C>OVER</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 对象版本
    */
   FSqlFunction getOver(Object objectId);

   /**
    * <T>根据记录的编号查找数据有效性(<C>OVLD</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 数据有效性
    */
   FSqlFunction getOvld(Object objectId);

   /**
    * <T>根据记录的编号查找订阅者标识(<C>USER_ID</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 订阅者标识
    */
   FSqlFunction getUserId(Object objectId);

   /**
    * <T>根据记录的编号查找类型标识(<C>TYPE_ID</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 类型标识
    */
   FSqlFunction getTypeId(Object objectId);

   /**
    * <T>根据记录的编号查找关联标识(<C>LINK_ID</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 关联标识
    */
   FSqlFunction getLinkId(Object objectId);

   /**
    * <T>根据记录的编号查找创建时间(<C>CREATE_DATE</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 创建时间
    */
   FSqlFunction getCreateDate(Object objectId);

   /**
    * <T>根据记录的编号查找描述信息(<C>NOTE</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 描述信息
    */
   FSqlFunction getNote(Object objectId);

   /**
    * <T>解析打包字符串(<C9>pack_</C9>)成订阅</T>
    * <P>pack_打包方式为：<B/>
    * 动作标识1=<B/>
    * 事件标识1=参数1<B/>
    * 事件标识2=参数2<B/>
    * ....    ....<B/>
    * 动作标识2=<B/>
    * 事件标识1=参数1<B/>
    * 事件标识2=参数2<B/>
    * ....    ...<B/>
    * ....          ....
    * </P>
    *
    * @param logic 业务逻辑
    * @param userId 用户标识
    * @param typeId 订阅类型标识
    * @param linkId 关联标识
    * @param pack 动作和动作对应的事件包
    */
   FSqlProcedure doSubscribe(Object logic,
                             Object userId,
                             Object typeId,
                             Object linkId,
                             Object pack);

   /**
    * <T>解析pack_包生成订阅</T>
    * <P>pack_打包方式为：<B/>
    * 事件标识1=参数1<B/>
    * 事件标识2=参数2<B/>
    * ....    ....
    * </P>
    *
    * @param logic 业务逻辑
    * @param userId 用户标识
    * @param typeId 订阅类型标识
    * @param linkId 关联标识
    * @param actionId 类型动作标识
    * @param pack 事件和事件的参数包
    */
   FSqlProcedure doSubscribe(Object logic,
                             Object userId,
                             Object typeId,
                             Object linkId,
                             Object actionId,
                             Object pack);

   /**
    * <T>根据参数生成订阅以及动作和事件</T>
    *
    * @param logic 业务逻辑
    * @param userId 用户标识
    * @param typeId 订阅类型标识
    * @param linkId 关联标识
    * @param actionId 类型动作标识
    * @param actionEventId 类型动作事件标识
    * @param parameters 参数包
    */
   FSqlProcedure doSubscribe(Object logic,
                             Object userId,
                             Object typeId,
                             Object linkId,
                             Object actionId,
                             Object actionEventId,
                             Object parameters);

   /**
    * <T>根据参数判断是否订阅如果订阅发布该订阅</T>
    *
    * @param logic 业务逻辑
    * @param userId 用户标识
    * @param typeName 订阅类型名称
    * @param actionName 订阅类型动作名称
    * @param linkId 关联标识
    * @param parameters 参数包
    */
   FSqlProcedure doPublish(Object logic,
                           Object userId,
                           Object typeName,
                           Object actionName,
                           Object linkId,
                           Object parameters);

   /**
    * 从LG_SUBSCRIBE_MESSAGE表读出数据并根据订阅动作事件产生对应的事件
    *
    * @param logic 业务逻辑
    */
   FSqlProcedure doDeploy(Object logic);

   /**
    * <T>根据对象编号锁定一条记录</T>
    * <UL>
    * <L>如果记录不存在，则产生记录不存在的例外</L>
    * <L>如果其他用户已经锁定该记录，则产生锁定失败的例外。</L>
    * </UL>
    *
    * @param objectId 对象编号
    */
   FSqlProcedure lockRecord(Object objectId);

   /**
    * <T>锁定当前数据集防止任何对数据的非法操作</T>
    * <UL>
    * <L><C9>ROW SHARE</C9>: 行记录共享模式</L>
    * <L><C9>ROW EXCLUSIVE</C9>: 行记录排他模式</L>
    * <L><C9>SHARE UPDATE</C9>: 行更新模式</L>
    * <L><C9>SHARE</C9>: 共享模式</L>
    * <L><C9>SHARE ROW EXCLUSIVE</C9>: 共享行排他模式</L>
    * <L><C9>EXCLUSIVE</C9>: 排他模式</L>
    * <L>默认为排他模式</L>
    * </UL>
    *
    * @param mode 锁定的类型
    */
   FSqlProcedure lockTable(Object mode);

   /**
    * <T>新建一条记录的时对数据对象进行初始化</T>
    *
    * @param logic 业务逻辑
    * @param params 打包字符串
    */
   FSqlProcedure prepare(Object logic,
                         Object params);

   /**
    * <T>在数据集内新建一条记录</T>
    *
    * @param logic 业务逻辑
    * @param params 打包字符串
    * @param check 检查标志
    */
   FSqlProcedure doInsert(Object logic,
                          Object params,
                          Object check);

   /**
    * <T>根据记录的编号修改一条记录</T>
    *
    * @param logic 业务逻辑
    * @param params 打包字符串
    * @param objectId 记录的编号
    * @param check 检查标志
    */
   FSqlProcedure doUpdate(Object logic,
                          Object params,
                          Object objectId,
                          Object check);

   /**
    * <T>根据记录的编号和数据集同步一条记录</T>
    * <UL>
    * <L>如果指定的记录不存在，进行新建处理。</L>
    * <L>如果指定的记录存在，进行更新处理。</L>
    * </UL>
    *
    * @param logic 业务逻辑
    * @param params 打包字符串
    * @param objectId 记录的编号
    * @param check 检查标志
    */
   FSqlProcedure doSync(Object logic,
                        Object params,
                        Object objectId,
                        Object check);

   /**
    * <T>根据记录的编号删除一条记录</T>
    *
    * @param logic 业务逻辑
    * @param params 打包字符串
    * @param objectId 记录的编号
    * @param check 检查标志
    */
   FSqlProcedure doDelete(Object logic,
                          Object params,
                          Object objectId,
                          Object check);

   /**
    * <T>清空当前数据集的全部记录</T>
    *
    */
   FSqlProcedure doClear();

   /**
    * <T>在数据集内新建一条记录</T>
    *
    * @param logic_ 业务逻辑
    * @param params_ 打包字符串
    */
   FSqlProcedure doInsert(Object logic,
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
    * <T>根据记录的编号删除一条记录</T>
    *
    * @param logic_ 业务逻辑
    * @param params_ 打包字符串
    */
   FSqlProcedure doDelete(Object logic,
                          Object params);
}
