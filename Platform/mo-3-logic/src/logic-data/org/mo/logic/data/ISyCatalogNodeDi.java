/*
 * @(#)ISyCatalogNodeDi.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.data;

import org.mo.com.data.FSqlFunction;
import org.mo.com.data.FSqlProcedure;
import org.mo.data.logicunit.common.ILogicUnit;

/**
 * <T>数据库逻辑包(SY_CATALOG_NODE_DI)的代理对象接口</T>
 *
 * @author MAOCY
 * @version 1.0.1
 */
public interface ISyCatalogNodeDi
      extends
         ILogicUnit
{
   /**
    * <T>获得目录节点的有效性</T>
    *
    * @param catalogId 目录编号
    * @param nodeId 目录节点编号
    * @return 有效性
    */
   FSqlFunction getCatalogNodeIsValid(Object catalogId,
                                      Object nodeId);

   /**
    * <T>得到子的个数</T>
    *
    * @param nodeId 目录节点编号
    * @return 子节点个数
    */
   FSqlFunction getChildCount(Object nodeId);

   /**
    * <T>得到关联角色的个数</T>
    *
    * @param nodeId 目录节点编号
    * @return 角色个数
    */
   FSqlFunction getRoleCount(Object nodeId);

   /**
    * <T>检索模块目录</T>
    *
    * @param params
    * @return 打包字符串
    */
   FSqlFunction getCatalogNodePack(Object params);

   /**
    * <T>检索模块目录</T>
    *
    * @param params
    * @return 打包字符串
    */
   FSqlFunction getCatalogNodeChildPack(Object params);

   /**
    * <T>根据节点标识获得角色标识的集合。</T>
    *
    * @param nodeId 节点标识
    * @return 角色标识的打包字符串
    */
   FSqlFunction getRoleIdsPack(Object nodeId);

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
    * @param catalogId 视角标识
    * @param name 英文名称
    * @return 是否含有指定编号
   @return 对象标识
    */
   FSqlFunction containsIdByName(Object catalogId,
                                 Object name);

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
    * <T>根据(<C>NAME</C>)获得当前记录的编号</T>
    * <UL>
    * <L>若存在则返回被查询的记录对象的唯一标识。</L>
    * <L>如果记录不存在，则产生记录不存在的例外。</L>
    * </UL>
    *
    * @param catalogId 视角标识
    * @param name 英文名称
    * @return 对象标识
    */
   FSqlFunction getIdByName(Object catalogId,
                            Object name);

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
    * <T>根据(<C>NAME</C>)查找当前记录的编号</T>
    *
    * @param catalogId 视角标识
    * @param name 英文名称
    * @param exists 存在标志
    * @return 对象标识
    */
   FSqlFunction findIdByName(Object catalogId,
                             Object name,
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
    * <T>根据记录的编号查找视角标识(<C>CATALOG_ID</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 视角标识
    */
   FSqlFunction getCatalogId(Object objectId);

   /**
    * <T>根据记录的编号查找类型标识(<C>TYPE_ID</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 类型标识
    */
   FSqlFunction getTypeId(Object objectId);

   /**
    * <T>根据记录的编号查找父标识(<C>PARENT_ID</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 父标识
    */
   FSqlFunction getParentId(Object objectId);

   /**
    * <T>根据记录的编号查找有效性(<C>IS_VALID</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 有效性
    */
   FSqlFunction getIsValid(Object objectId);

   /**
    * <T>根据记录的编号查找代码(<C>CODE</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 代码
    */
   FSqlFunction getCode(Object objectId);

   /**
    * <T>根据记录的编号查找英文名称(<C>NAME</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 英文名称
    */
   FSqlFunction getName(Object objectId);

   /**
    * <T>根据记录的编号查找中文名称(<C>LABEL</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 中文名称
    */
   FSqlFunction getLabel(Object objectId);

   /**
    * <T>根据记录的编号查找关联标识(<C>MODULE_ID</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 关联标识
    */
   FSqlFunction getModuleId(Object objectId);

   /**
    * <T>根据记录的编号查找含有子节点(<C>HAS_CHILD</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 含有子节点
    */
   FSqlFunction getHasChild(Object objectId);

   /**
    * <T>根据记录的编号查找可配置(<C>IS_CONFIG</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 可配置
    */
   FSqlFunction getIsConfig(Object objectId);

   /**
    * <T>根据记录的编号查找类型名称(<C>TYPE_NAME</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 类型名称
    */
   FSqlFunction getTypeName(Object objectId);

   /**
    * <T>根据记录的编号查找图标名称(<C>ICON_NAME</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 图标名称
    */
   FSqlFunction getIconName(Object objectId);

   /**
    * <T>根据记录的编号查找显示排序(<C>DISP_ORDER</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 显示排序
    */
   FSqlFunction getDispOrder(Object objectId);

   /**
    * <T>根据记录的编号查找关联目录树(<C>LINK_CATALOG</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 关联目录树
    */
   FSqlFunction getLinkCatalog(Object objectId);

   /**
    * <T>根据记录的编号查找属性内容(<C>PROPERTIES</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 属性内容
    */
   FSqlFunction getProperties(Object objectId);

   /**
    * <T>根据记录的编号查找描述信息(<C>NOTE</C>)的数据内容</T>
    *
    * @param objectId 记录的编号
    * @return 描述信息
    */
   FSqlFunction getNote(Object objectId);

   /**
    * <T>根据参数创建目录节点。</T>
    *
    * @param logic 业务逻辑
    * @param parameters 模块的打包字符串
    */
   FSqlProcedure doSyncNode(Object logic,
                            Object parameters);

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
