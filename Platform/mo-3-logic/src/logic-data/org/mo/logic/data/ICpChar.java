/*
 * @(#)ICpChar.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.data;

import org.mo.com.data.FSqlFunction;
import org.mo.com.data.FSqlProcedure;

/**
 * <T>数据库逻辑包(CP_CHAR)的代理对象接口</T>
 *
 * @author MAOCY
 * @version 1.0.1
 */
public interface ICpChar
{
   /**
    *
    * @param text
    * @return
    */
   FSqlFunction parse(Object text);

   /**
    * <p>判定两个键值是否相等</p>
    *
    * @param value1 比较值1
    * @param value2 比较值2
    * @return BOOLEAN
    */
   FSqlFunction equalsIgnoreCase(Object value1,
                                 Object value2);

   /**
    * <p>将value_按count_次合并成字符串</p>
    *
    * @param value 字符串
    * @param count 合并次数
    * @return BOOLEAN
    */
   FSqlFunction repeat(Object value,
                       Object count);

   /**
    * <p>从text_中返回value_的索引值，toffset_为索引位置carecase_控制索引大小写</p>
    *
    * @param text 字符串
    * @param value
    * @param toffset 索引起始位置1
    * @param carecase 索引字符个数
    * @return BOOLEAN
    */
   FSqlFunction indexOf(Object text,
                        Object value,
                        Object toffset,
                        Object carecase);

   /**
    * <p>判断索引是否为首位</p>
    *
    * @param text 字符串
    * @param prefix 被索引字符串
    * @param toffset 索引起始位置1
    * @param carecase 索引字符个数
    * @return BOOLEAN
    */
   FSqlFunction startsWith(Object text,
                           Object prefix,
                           Object toffset,
                           Object carecase);

   /**
    * <p>判断索引是否为尾部</p>
    *
    * @param text 字符串
    * @param suffix 被索引字符串
    * @param carecase 控制null参数
    * @return BOOLEAN
    */
   FSqlFunction endsWith(Object text,
                         Object suffix,
                         Object carecase);

   /**
    * <p>返回text_中最后的find_text_索引值</p>
    *
    * @param text 字符串
    * @param findText 被索引字符串
    * @return BOOLEAN
    */
   FSqlFunction lastIndexOf(Object text,
                            Object findText);

   /**
    * <p>提取text_中从begin_str_到end_str_的内容</p>
    *
    * @param text 字符串
    * @param beginStr 被提取字符转起始字符串
    * @param endStr 被提取字符转末尾字符串
    * @param carecase 控制null参数
    * @return BOOLEAN
    */
   FSqlFunction substring(Object text,
                          Object beginStr,
                          Object endStr,
                          Object carecase);

   /**
    * <p>判断value1_与value2_是否相等，相等返回case1_否则返回case2_</p>
    *
    * @param value1 比较值1
    * @param value2 比较值2
    * @param case1 返回值1
    * @param case2 返回值2
    * @return BOOLEAN
    */
   FSqlFunction caseValue(Object value1,
                          Object value2,
                          Object case1,
                          Object case2);

   /**
    * <p>判断char_是否为askii码内</p>
    *
    * @param value
    * @return BOOLEAN
    */
   FSqlFunction isCharacter(Object value);

   /**
    * <p>判断char_是否为字母</p>
    *
    * @param value 被判断字符
    * @return BOOLEAN
    */
   FSqlFunction isLetter(Object value);

   /**
    * <p>将value_格式化成为首字母大写，后面自负小写的格式</p>
    *
    * @param value 被格式化字符串
    * @return BOOLEAN
    */
   FSqlFunction initCap(Object value);

   /**
    * <p>将text_中的 {key_}<  替换为value_</p>
    *
    * @param text 字符串
    * @param key 被替换字符
    * @param value 替换字符
    */
   FSqlProcedure parse(Object text,
                       Object key,
                       Object value);

   /**
    * <p>将text_中的value_替换为''即删除</p>
    *
    * @param text 字符串
    * @param value 被替换字符
    */
   FSqlProcedure remove(Object text,
                        Object value);

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
