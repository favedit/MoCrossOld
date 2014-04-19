/*
 * @(#)ILgTaskDi.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.data;

import org.mo.com.data.FSqlFunction;
import org.mo.com.data.FSqlProcedure;
import org.mo.data.logicunit.common.ILogicUnit;

/**
 * <T>数据库逻辑包(LG_TASK_DI)的代理对象接口</T>
 *
 * @author MAOCY
 * @version 1.0.1
 */
public interface ILgTaskDi
      extends
         ILogicUnit
{
   /**
    * <T>对事件的所需参数进行打包。</T>
    * <P>模块包的打包方式为：
    * <UL>
    * <L>流程标识=''</L>
    * <L>任务标识=''</L>
    * <L>属性名称1=数据内容1</L>
    * <L>属性名称2=数据内容2</L>
    * </UL>
    * </P>
    *
    * @param taskId 任务对象
    * @param eventId 事件标志
    * @return
    */
   FSqlFunction findValuePack(Object taskId,
                              Object eventId);

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
    * <T>根据记录的编号查找流程标志(<C>PROCESS_ID</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 流程标志
    */
   FSqlFunction getProcessId(Object objectId);

   /**
    * <T>根据记录的编号查找类型标识(<C>TYPE_ID</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 类型标识
    */
   FSqlFunction getTypeId(Object objectId);

   /**
    * <T>根据记录的编号查找任务状态(<C>STATUS_CD</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 任务状态
    */
   FSqlFunction getStatusCd(Object objectId);

   /**
    * <T>根据记录的编号查找任务状态(<C>STATUS_CD</C>)的显示内容</T>
    *
    * @param objectId 记录的编号
    * @return 任务状态
    */
   FSqlFunction getStatusLabel(Object objectId);

   /**
    * <T>根据记录的编号查找执行开始时间(<C>BEGIN_DATE</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 执行开始时间
    */
   FSqlFunction getBeginDate(Object objectId);

   /**
    * <T>根据记录的编号查找实际结束时间(<C>REAL_END_DATE</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 实际结束时间
    */
   FSqlFunction getRealEndDate(Object objectId);

   /**
    * <T>根据记录的编号查找预计结束时间(<C>EXPECT_END_DATE</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 预计结束时间
    */
   FSqlFunction getExpectEndDate(Object objectId);

   /**
    * <T>根据记录的编号查找发起者标识(<C>PROCESS_USER_ID</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 发起者标识
    */
   FSqlFunction getProcessUserId(Object objectId);

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
    * <T>根据记录的编号查找描述信息(<C>NOTE</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 描述信息
    */
   FSqlFunction getNote(Object objectId);

   /**
    * <T>发行事件</T>
    * <P>根据参数设置发行事件。
    * <UL>
    * <L value = 'Buffer'>缓冲执行，生成事件记录</L>
    * <L value = 'Immediately'>缓冲执行，生成事件记录</L>
    * </UL>
    * <P>
    *
    * @param logic 业务逻辑
    * @param taskId 任务标识
    * @param eventId 事件标识
    */
   FSqlProcedure doDeployEvent(Object logic,
                               Object taskId,
                               Object eventId);

   /**
    * <T>根据条件查找所有的当前条件下的事件。</T>
    *
    * @param logic 业务逻辑
    * @param taskId 任务标识
    * @param conditionId 条件标识
    * @param result 检查结果
    */
   FSqlProcedure findConditionEvent(Object logic,
                                    Object taskId,
                                    Object conditionId,
                                    Object result);

   /**
    * <T>根据所给条件类型查找所有的当前任务类型下的该类型条件。</T>
    *
    * @param logic 业务逻辑
    * @param typeId 条件类型标识
    * @param taskId 任务标识
    * @param conditionType 条件类型
    */
   FSqlProcedure findTypeCondition(Object logic,
                                   Object typeId,
                                   Object taskId,
                                   Object conditionType);

   /**
    * <T>任务开始。</T>
    * <P>根据传入的类型名称，查找任务类型，如果类型不存在，则创建类型，并执行任务在起始条件下所执行的事件。</P>
    *
    * @param logic 业务逻辑
    * @param params 回调参数
    */
   FSqlProcedure cbStart(Object logic,
                         Object params);

   /**
    * <T>任务确认。</T>
    * <P>任务需要确认时，当经过确认后回调本函数，并查找该任务确认条件下的所有事件部署执行。</P>
    *
    * @param logic 业务逻辑
    * @param params 回调参数
    */
   FSqlProcedure cbConfirm(Object logic,
                           Object params);

   /**
    * <T>任务确认。</T>
    * <P>任务需要确认时，当经过确认后回调本函数，并查找该任务确认条件下的所有事件部署执行。</P>
    *
    * @param logic 业务逻辑
    * @param params 回调参数
    */
   FSqlProcedure doConfirm(Object logic,
                           Object params);

   /**
    * <T>任务结束。</T>
    * <P>当任务需要在结束时做一定事件时，查找任务的结束条件下的所有事件并部署执行。</P>
    *
    * @param logic 业务逻辑
    * @param params 回调参数
    */
   FSqlProcedure cbStop(Object logic,
                        Object params);

   /**
    * <T>任务结束事件的部署。</T>
    * <P>发行一个计划，该计划部署在某个时间点执行任务结束函数。</P>
    *
    * @param logic 业务逻辑
    * @param beginDatetime
    * @param endDatetime
    * @param executeDatetime
    * @param params 回调参数
    */
   FSqlProcedure cbStopDeploy(Object logic,
                              Object beginDatetime,
                              Object endDatetime,
                              Object executeDatetime,
                              Object params);

   /**
    * <T>任务取消。</T>
    * <P>任务需要确认时，当确认未通过后回调本函数，并查找该任务确认未通过条件下的所有事件部署执行。</P>
    *
    * @param logic 业务逻辑
    * @param params 回调参数
    */
   FSqlProcedure cbCancel(Object logic,
                          Object params);

   /**
    * <T>任务取消。</T>
    * <P>任务需要确认时，当确认未通过后回调本函数，并查找该任务确认未通过条件下的所有事件部署执行。</P>
    *
    * @param logic 业务逻辑
    * @param params 回调参数
    */
   FSqlProcedure doCancel(Object logic,
                          Object params);

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
