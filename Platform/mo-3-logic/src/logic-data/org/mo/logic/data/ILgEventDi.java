/*
 * @(#)ILgEventDi.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.data;

import org.mo.com.data.FSqlFunction;
import org.mo.com.data.FSqlProcedure;
import org.mo.data.logicunit.common.ILogicUnit;

/**
 * <T>数据库逻辑包(LG_EVENT_DI)的代理对象接口</T>
 *
 * @author MAOCY
 * @version 1.0.1
 */
public interface ILgEventDi
      extends
         ILogicUnit
{
   /**
    * <T>根据类型标识和属性名称查找属性标识</T>
    *
    * @param typeId 类型编号
    * @param name 属性名称
    * @return 属性标识
    */
   FSqlFunction getIdByParams(Object typeId,
                              Object name);

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
    * <T>根据对象全局标识(<C>OGID</C>)查找当前记录的编号</T>
    *
    * @param ogid 对象全局标识
    * @param exists 存在标志
    * @return 对象标识
    */
   FSqlFunction findIdByOgid(Object ogid,
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
    * <T>根据记录的编号查找分组标识(<C>GROUP_ID</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 分组标识
    */
   FSqlFunction getGroupId(Object objectId);

   /**
    * <T>根据记录的编号查找类型标识(<C>TYPE_ID</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 类型标识
    */
   FSqlFunction getTypeId(Object objectId);

   /**
    * <T>根据记录的编号查找网络标识(<C>NETWORK_ID</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 网络标识
    */
   FSqlFunction getNetworkId(Object objectId);

   /**
    * <T>根据记录的编号查找服务标识(<C>SERVER_ID</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 服务标识
    */
   FSqlFunction getServerId(Object objectId);

   /**
    * <T>根据记录的编号查找状态类型(<C>STATUS_CD</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 状态类型
    */
   FSqlFunction getStatusCd(Object objectId);

   /**
    * <T>根据记录的编号查找状态类型(<C>STATUS_CD</C>)的显示内容</T>
    *
    * @param objectId 记录的编号
    * @return 状态类型
    */
   FSqlFunction getStatusLabel(Object objectId);

   /**
    * <T>根据记录的编号查找优先级(<C>PRIORITY_CD</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 优先级
    */
   FSqlFunction getPriorityCd(Object objectId);

   /**
    * <T>根据记录的编号查找优先级(<C>PRIORITY_CD</C>)的显示内容</T>
    *
    * @param objectId 记录的编号
    * @return 优先级
    */
   FSqlFunction getPriorityLabel(Object objectId);

   /**
    * <T>根据记录的编号查找执行开始时间(<C>BEGIN_DATE</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 执行开始时间
    */
   FSqlFunction getBeginDate(Object objectId);

   /**
    * <T>根据记录的编号查找执行结束时间(<C>END_DATE</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 执行结束时间
    */
   FSqlFunction getEndDate(Object objectId);

   /**
    * <T>根据记录的编号查找执行用户(<C>PROCESS_USER_ID</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 执行用户
    */
   FSqlFunction getProcessUserId(Object objectId);

   /**
    * <T>根据记录的编号查找执行百分比(<C>PROCESS_PERCENT</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 执行百分比
    */
   FSqlFunction getProcessPercent(Object objectId);

   /**
    * <T>根据记录的编号查找结果类型(<C>RESULT_CD</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 结果类型
    */
   FSqlFunction getResultCd(Object objectId);

   /**
    * <T>根据记录的编号查找结果类型(<C>RESULT_CD</C>)的显示内容</T>
    *
    * @param objectId 记录的编号
    * @return 结果类型
    */
   FSqlFunction getResultLabel(Object objectId);

   /**
    * <T>根据记录的编号查找创建者标识(<C>CREATE_USER_ID</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 创建者标识
    */
   FSqlFunction getCreateUserId(Object objectId);

   /**
    * <T>根据记录的编号查找接收者标识(<C>RECEIVE_USER_ID</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 接收者标识
    */
   FSqlFunction getReceiveUserId(Object objectId);

   /**
    * <T>根据记录的编号查找创建时间(<C>CREATE_DATE</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 创建时间
    */
   FSqlFunction getCreateDate(Object objectId);

   /**
    * <T>根据记录的编号查找参数(<C>PARAMETERS</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 参数
    */
   FSqlFunction getParameters(Object objectId);

   /**
    * <T>根据记录的编号查找描述信息(<C>NOTE</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 描述信息
    */
   FSqlFunction getNote(Object objectId);

   /**
    * <T>将数据插入到事件表和设置表</T>
    *
    * @param logic
    * @param typeId
    * @param parameters
    */
   FSqlProcedure executeEvent(Object logic,
                              Object typeId,
                              Object parameters);

   /**
    * <T>将数据插入到事件表和设置表</T>
    *
    * @param logic 业务逻辑
    * @param eventId 事件编号
    * @param groupId 事件分组编号
    * @param typeId 事件类型编号
    * @param priorityCd 事件优先级
    * @param createUserId 事件的创建者
    * @param receiveUserId 事件的接收者
    * @param parameters 打包字符串
    * @param note 事件注释信息
    */
   FSqlProcedure doPublishById(Object logic,
                               Object eventId,
                               Object groupId,
                               Object typeId,
                               Object priorityCd,
                               Object createUserId,
                               Object receiveUserId,
                               Object parameters,
                               Object note);

   /**
    * <T>为某个事件设置参数内容。</T>
    *
    * @param logic 业务逻辑
    * @param eventId 事件编号
    * @param name 参数名称
    * @param dataValue 数据内容
    */
   FSqlProcedure doSetParameter(Object logic,
                                Object eventId,
                                Object name,
                                Object dataValue);

   /**
    * <T>事件出来没有开始时设置事件状态为等待状态</T>
    *
    * @param logic 业务逻辑
    * @param objectId
    */
   FSqlProcedure doWait(Object logic,
                        Object objectId);

   /**
    * <T>事件执行时设置事件状态并更新事件执行开始时间</T>
    *
    * @param logic 业务逻辑
    * @param objectId
    */
   FSqlProcedure doStart(Object logic,
                         Object objectId);

   /**
    * <T>事件结束时设置事件状态并更新事件执行开始时间</T>
    *
    * @param logic 业务逻辑
    * @param objectId
    * @param parameters 存放log信息的打包字符串
    */
   FSqlProcedure doStop(Object logic,
                        Object objectId,
                        Object parameters);

   /**
    * <T>设置事件执行百分比</T>
    *
    * @param logic 业务逻辑
    * @param objectId
    * @param percent 执行百分比
    */
   FSqlProcedure doProcessPrecent(Object logic,
                                  Object objectId,
                                  Object percent);

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
