/*
 * @(#)ICpDataIn.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.data;

import org.mo.com.data.FSqlFunction;
import org.mo.com.data.FSqlProcedure;

/**
 * <T>数据库逻辑包(CP_DATA_IN)的代理对象接口</T>
 *
 * @author MAOCY
 * @version 1.0.1
 */
public interface ICpDataIn
{
   /**
    * <p>获得session_user的值</p>
    *
    * @return
    */
   FSqlFunction getSessionOwner();

   /**
    * <p>获得unit_array__的个数</p>
    *
    * @return
    */
   FSqlFunction getUnitCount();

   /**
    * <p>获得dataset_array__的计数器值</p>
    *
    * @return
    */
   FSqlFunction getDatasetCount();

   /**
    * <p>获得与name_相同的dataset_array__的索引值</p>
    *
    * @param name 查询名称
    * @return
    */
   FSqlFunction indexOfDataset(Object name);

   /**
    * <p>获得container_array__的计数器值</p>
    *
    * @return
    */
   FSqlFunction getContainerCount();

   /**
    *
    */
   FSqlProcedure reset();

   /**
    * <p>获得pack_数据包的unit_</p>
    *
    * @param pack 数据包
    */
   FSqlProcedure unpackSession(Object pack);

   /**
    * <p>在unit_array__后面添加pack_包中的name_项</p>
    *
    * @param name 被添加项目名称
    * @param pack 被添加项目数据包
    */
   FSqlProcedure unpackUnit(Object name,
                            Object pack);

   /**
    * <p>在dataset_array__后面添加pack_包中的name_项</p>
    *
    * @param name 被添加项目名称
    * @param pack 被添加项目数据包
    */
   FSqlProcedure unpackDataset(Object name,
                               Object pack);

   /**
    * <p>在container_array__后面添加pack_包中的name_项</p>
    *
    * @param name 被添加项目名称
    * @param pack 被添加项目数据包
    */
   FSqlProcedure unpackContainer(Object name,
                                 Object pack);

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
