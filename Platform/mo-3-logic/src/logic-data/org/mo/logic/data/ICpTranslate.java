/*
 * @(#)ICpTranslate.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.data;

import org.mo.com.data.FSqlFunction;
import org.mo.com.data.FSqlProcedure;

/**
 * <T>数据库逻辑包(CP_TRANSLATE)的代理对象接口</T>
 *
 * @author MAOCY
 * @version 1.0.1
 */
public interface ICpTranslate
{
   /**
    * <T>根据一个翻译代号去查找当前环境下，对应的语言翻译内容。</T>
    * <P>如果语言标识为空，则取当前线程默认的语言标识。</P>
    *
    * @param code 翻译代号
    * @param languageId 语言标识
    * @param param1 埋入参数1
    * @param param2 埋入参数2
    * @param param3 埋入参数3
    * @param param4 埋入参数4
    * @param param5 埋入参数5
    * @param param6 埋入参数6
    * @return 翻译后内容
    */
   FSqlFunction find(Object code,
                     Object languageId,
                     Object param1,
                     Object param2,
                     Object param3,
                     Object param4,
                     Object param5,
                     Object param6);

   /**
    * <T>根据一个翻译代号去查找当前环境下，对应的语言翻译内容。</T>
    *
    * @param logicName 逻辑名称
    * @param code 翻译代号
    * @param param1 埋入参数1
    * @param param2 埋入参数2
    * @param param3 埋入参数3
    * @param param4 埋入参数4
    * @param param5 埋入参数5
    * @param param6 埋入参数6
    * @return 翻译后内容
    */
   FSqlFunction translatePlsql(Object logicName,
                               Object code,
                               Object param1,
                               Object param2,
                               Object param3,
                               Object param4,
                               Object param5,
                               Object param6);

   /**
    * <T>根据数据集的逻辑名称，获的对应语言的翻译内容。</T>
    *
    * @param logicName 逻辑名称
    * @param languageId 语言标识
    * @return 翻译内容
    */
   FSqlFunction translateDataset(Object logicName,
                                 Object languageId);

   /**
    * <T>根据数据集的逻辑名称，获的指定字段的对应语言的翻译内容。</T>
    *
    * @param logicName 逻辑名称
    * @param field 字段名称
    * @param languageId 语言标识
    * @return 翻译后内容
    */
   FSqlFunction translateDatasetField(Object logicName,
                                      Object field,
                                      Object languageId);

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
