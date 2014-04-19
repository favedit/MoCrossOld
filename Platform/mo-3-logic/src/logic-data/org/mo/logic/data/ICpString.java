/*
 * @(#)ICpString.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.data;

import org.mo.com.data.FSqlFunction;
import org.mo.com.data.FSqlProcedure;

/**
 * <T>数据库逻辑包(CP_STRING)的代理对象接口</T>
 *
 * @author MAOCY
 * @version 1.0.1
 */
public interface ICpString
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
    * 检查一个字符串是否为空，如果为空则返回真，否则为假。
    * 值为NULL或长度为0的字符串，或只含有空格的字符串都被认为空。
    *
    * @param value 被判断字符
    * @return TRUE:为空<b/>FALSE:不为空
    */
   FSqlFunction isEmpty(Object value);

   /**
    * <p>将value_格式化成为首字母大写，后面自负小写的格式</p>
    *
    * @param value 被格式化字符串
    * @return BOOLEAN
    */
   FSqlFunction initCap(Object value);

   /**
    * <T>检查字符串是否为空。</T>
    * <P>判断传入的参数是否为空，如果为空。
    * </P>
    *
    * @param value 项目名称的值
    * @return 如果值为空，返回true，否则返回false
    */
   FSqlFunction testNull(Object value);

   /**
    * <T>检查字符串长度是否是指定的长度。</T>
    * <P>指定的字符串长度如果和给定的长度不相等。
    * </P>
    *
    * @param value 项目名称的值
    * @param length
    * @return 如果相等，返回true，否则返回false
    */
   FSqlFunction testLength(Object value,
                           Object length);

   /**
    * <T>检查字符串长度是小于指定的长度。</T>
    * <P>指定的字符串长度如果比给定的最小长度还小。
    * </P>
    *
    * @param value 项目名称的值
    * @param length
    * @return 如果比给定的最小长度小，返回true，否则返回false
    */
   FSqlFunction testLengthMin(Object value,
                              Object length);

   /**
    * <T>检查字符串长度是否大于指定的长度。</T>
    * <P>指定的字符串长度如果比给定的最大长度还大。
    * </P>
    *
    * @param value 项目名称的值
    * @param length
    * @return 如果比指定的长度大，返回true，否则返回false
    */
   FSqlFunction testLengthMax(Object value,
                              Object length);

   /**
    * <T>检查字符串是否是大写。</T>
    * <P>把字符串里的所有字符都变成大写，和原字符串比较，值相等，则说明原字符串就全是大写字符。
    * </P>
    *
    * @param value 项目名称的值
    * @return 如果是大写，返回true，否则返回false
    */
   FSqlFunction testUpper(Object value);

   /**
    * <T>检查字符串是否是大写字母。</T>
    * <P>把字符串里的所有字符都变成大写，和原字符串比较，值相等，则说明原字符串就全是大写字符。<B/>
    * 否则弹出错误提示信息：<B/>
    * 错误格式为：“The content of {0} must be all in upper case.”
    * <UL>
    * <L value='{0}'>项目名称</L>
    * </UL>
    * </P>
    *
    * @param value 项目名称的值
    * @return
    */
   FSqlFunction testUpperAscii(Object value);

   /**
    * <T>检查字符串是否是小写。</T>
    * <P>把字符串里的所有字符都变成小写，和原字符串比较，值相等，则说明原字符串就全是小写字符。
    * </P>
    *
    * @param value 项目名称的值
    * @return 如果是小写，返回true，否则返回false
    */
   FSqlFunction testLower(Object value);

   /**
    * <T>检查字符串是否是大写字母。</T>
    * <P>把字符串里的所有字符都变成大写，和原字符串比较，值相等，则说明原字符串就全是大写字符。<B/>
    * 否则弹出错误提示信息：<B/>
    * 错误格式为：“The content of {0} must be all in upper case.”
    * <UL>
    * <L value='{0}'>项目名称</L>
    * </UL>
    * </P>
    *
    * @param value 项目名称的值
    * @return
    */
   FSqlFunction testLowerAscii(Object value);

   /**
    * <T>检查字符串是否都是半角字符。</T>
    * <P>把字符串里的所有字符都变成半角字符，然后和原字符串比较，如果值相等的话则说明原字符串就是半角字符。
    * </P>
    *
    * @param value 项目名称的值
    * @return 如果是半角，返回true，否则返回false
    */
   FSqlFunction testAnsi(Object value);

   /**
    * <T>判断字符串是否都是全角字符。</T>
    * <P>把字符串里的所有字符都变成全角字符，然后和原字符串比较，如果值相等的话则说明原字符串就是全角字符。
    * </P>
    *
    * @param value 项目名称的值
    * @return 如果是全角，返回true，否则返回false
    */
   FSqlFunction testDbcs(Object value);

   /**
    * <T>判断字符串是否全是字母。</T>
    * <P>遍历字符串中的每一个字符，如果所有字符都是字母，则继续执行，否则直接结束函数。
    * </P>
    *
    * @param value 项目名称的值
    * @return 如果是字母，返回true，否则返回false
    */
   FSqlFunction testAscii(Object value);

   /**
    * <T>判断字符串是否全是汉字。</T>
    * <P>遍历字符串中的每一个字符，如果所有字符都是汉字，则继续执行，否则直接结束函数。
    * </P>
    *
    * @param value 项目名称的值
    * @return 如果是汉字，返回true，否则返回false
    */
   FSqlFunction testChinese(Object value);

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
    * 检查一个字符串是否为空，如果为空则产生校验例外。
    * 值为NULL或长度为0的字符串都被认为空。
    *
    * @param value 字符串
    * @param code 例外编号
    * @param message 例外消息
    * @param param1 参数1
    * @param param2 参数2
    * @param param3 参数3
    * @param param4 参数4
    * @param param5 参数5
    * @param param6 参数6
    */
   FSqlProcedure checkNull(Object value,
                           Object code,
                           Object message,
                           Object param1,
                           Object param2,
                           Object param3,
                           Object param4,
                           Object param5,
                           Object param6);

   /**
    * 检查一个字符串是否为空，如果为空则产生校验例外。
    * 值为NULL或长度为0的字符串，或只含有空格的字符串都被认为空。
    *
    * @param value 字符串
    * @param code 例外编号
    * @param message 例外消息
    * @param param1 参数1
    * @param param2 参数2
    * @param param3 参数3
    * @param param4 参数4
    * @param param5 参数5
    * @param param6 参数6
    */
   FSqlProcedure checkEmpty(Object value,
                            Object code,
                            Object message,
                            Object param1,
                            Object param2,
                            Object param3,
                            Object param4,
                            Object param5,
                            Object param6);

   /**
    * <T>检查字符串是否为空。</T>
    * <P>判断传入的参数是否为空，如果为空，则弹出错误消息提示。<B/>
    * 错误格式为：“{0} is a null string.”
    * <UL>
    * <L value='{0}'>项目名称</L>
    * </UL>
    * </P>
    *
    * @param value 项目名称的值
    * @param logic 业务逻辑
    * @param field 项目名称
    */
   FSqlProcedure checkNull(Object value,
                           Object logic,
                           Object field);

   /**
    * <T>检查字符串长度是否是指定的长度。</T>
    * <P>指定的字符串长度如果和给定的长度不相等，则弹出错误消息提示。<B/>
    * 错误格式为：“The length of {0} must be {1}.”
    * <UL>
    * <L value='{0}'>项目名称</L>
    * <L value='{1}'>值的长度</L>
    * </UL>
    * </P>
    *
    * @param value 项目名称的值
    * @param length
    * @param logic 业务逻辑
    * @param field 项目名称
    */
   FSqlProcedure checkLength(Object value,
                             Object length,
                             Object logic,
                             Object field);

   /**
    * <T>检查字符串长度是小于指定的长度。</T>
    * <P>指定的字符串长度如果比给定的最小长度还小，则消息提示。<B/>
    * 错误格式为：“The length of {0} must be not less than {1}.”
    * <UL>
    * <L value='{0}'>项目名称</L>
    * <L value='{1}'>值的长度</L>
    * </UL>
    * </P>
    *
    * @param value 项目名称的值
    * @param length
    * @param logic 业务逻辑
    * @param field 项目名称
    */
   FSqlProcedure checkLengthMin(Object value,
                                Object length,
                                Object logic,
                                Object field);

   /**
    * <T>检查字符串长度是否大于指定的长度。</T>
    * <P>指定的字符串长度如果比给定的最大长度还大，则消息提示。<B/>
    * 错误格式为：“The length of {0} must be not more than {1}.”
    * <UL>
    * <L value='{0}'>项目名称</L>
    * <L value='{1}'>值的长度</L>
    * </UL>
    * </P>
    *
    * @param value 项目名称的值
    * @param length
    * @param logic 业务逻辑
    * @param field 项目名称
    */
   FSqlProcedure checkLengthMax(Object value,
                                Object length,
                                Object logic,
                                Object field);

   /**
    * <T>检查字符串是否是大写。</T>
    * <P>把字符串里的所有字符都变成大写，和原字符串比较，值相等，则说明原字符串就全是大写字符。<B/>
    * 否则弹出错误提示信息：<B/>
    * 错误格式为：“The content of {0} must be all in upper case.”
    * <UL>
    * <L value='{0}'>项目名称</L>
    * </UL>
    * </P>
    *
    * @param value 项目名称的值
    * @param logic 业务逻辑
    * @param field 项目名称
    */
   FSqlProcedure checkUpper(Object value,
                            Object logic,
                            Object field);

   /**
    * <T>检查字符串是否是大写字母。</T>
    * <P>把字符串里的所有字符都变成大写，和原字符串比较，值相等，则说明原字符串就全是大写字符。<B/>
    * 否则弹出错误提示信息：<B/>
    * 错误格式为：“The content of {0} must be all in upper case.”
    * <UL>
    * <L value='{0}'>项目名称</L>
    * </UL>
    * </P>
    *
    * @param value 项目名称的值
    * @param logic 业务逻辑
    * @param field 项目名称
    */
   FSqlProcedure checkUpperAscii(Object value,
                                 Object logic,
                                 Object field);

   /**
    * <T>检查字符串是否是小写。</T>
    * <P>把字符串里的所有字符都变成小写，和原字符串比较，值相等，则说明原字符串就全是小写字符。<B/>
    * 否则弹出错误提示信息：<B/>
    * 错误格式为：“The content of {0} must be all in lower case.”
    * <UL>
    * <L value='{0}'>项目名称</L>
    * </UL>
    * </P>
    *
    * @param value 项目名称的值
    * @param logic 业务逻辑
    * @param field 项目名称
    */
   FSqlProcedure checkLower(Object value,
                            Object logic,
                            Object field);

   /**
    * <T>检查字符串是否是大写字母。</T>
    * <P>把字符串里的所有字符都变成大写，和原字符串比较，值相等，则说明原字符串就全是大写字符。<B/>
    * 否则弹出错误提示信息：<B/>
    * 错误格式为：“The content of {0} must be all in upper case.”
    * <UL>
    * <L value='{0}'>项目名称</L>
    * </UL>
    * </P>
    *
    * @param value 项目名称的值
    * @param logic 业务逻辑
    * @param field 项目名称
    */
   FSqlProcedure checkLowerAscii(Object value,
                                 Object logic,
                                 Object field);

   /**
    * <T>检查字符串是否都是半角字符。</T>
    * <P>把字符串里的所有字符都变成半角字符，然后和原字符串比较，如果值相等的话则说明原字符串就是半角字符。<B/>
    * 否则弹出错误提示信息：<B/>
    * 错误格式为：“The content of {0} must be ansi.”
    * <UL>
    * <L value='{0}'>项目名称</L>
    * </UL>
    * </P>
    *
    * @param value 项目名称的值
    * @param logic 业务逻辑
    * @param field 项目名称
    */
   FSqlProcedure checkAnsi(Object value,
                           Object logic,
                           Object field);

   /**
    * <T>判断字符串是否都是全角字符。</T>
    * <P>把字符串里的所有字符都变成全角字符，然后和原字符串比较，如果值相等的话则说明原字符串就是全角字符。<B/>
    * 否则弹出错误提示信息：<B/>
    * 错误格式为：“The content of {0} must be dbcs.”
    * <UL>
    * <L value='{0}'>项目名称</L>
    * </UL>
    * </P>
    *
    * @param value 项目名称的值
    * @param logic 业务逻辑
    * @param field 项目名称
    */
   FSqlProcedure checkDbcs(Object value,
                           Object logic,
                           Object field);

   /**
    * <T>判断字符串是否全是字母。</T>
    * <P>遍历字符串中的每一个字符，如果所有字符都是字母，则继续执行，否则直接结束函数。<B/>
    * 否则弹出错误提示信息：<B/>
    * 错误格式为：“the content of {0} must be a assii character.”
    * <UL>
    * <L value='{0}'>项目名称</L>
    * </UL>
    * </P>
    *
    * @param value 项目名称的值
    * @param logic 业务逻辑
    * @param field 项目名称
    */
   FSqlProcedure checkAscii(Object value,
                            Object logic,
                            Object field);

   /**
    * <T>判断字符串是否全是汉字。</T>
    * <P>遍历字符串中的每一个字符，如果所有字符都是汉字，则继续执行，否则直接结束函数。<B/>
    * 否则弹出错误提示信息：<B/>
    * 错误格式为：“The content of {0} must be a chinese.”
    * <UL>
    * <L value='{0}'>项目名称</L>
    * </UL>
    * </P>
    *
    * @param value 项目名称的值
    * @param logic 业务逻辑
    * @param field 项目名称
    */
   FSqlProcedure checkChinese(Object value,
                              Object logic,
                              Object field);

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
