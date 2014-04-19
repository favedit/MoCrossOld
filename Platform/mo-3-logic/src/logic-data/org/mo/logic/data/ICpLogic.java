/*
 * @(#)ICpLogic.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.data;

import org.mo.com.data.FSqlFunction;
import org.mo.com.data.FSqlProcedure;

/**
 * <T>数据库逻辑包(CP_LOGIC)的代理对象接口</T>
 *
 * @author MAOCY
 * @version 1.0.1
 */
public interface ICpLogic
{
   /**
    * <T>获得对象的信息。</T>
    *
    * @param ouid
    * @param ovld
    * @param over
    * @return 对象信息
    */
   FSqlFunction makeObjectInformation(Object ouid,
                                      Object ovld,
                                      Object over);

   /**
    * <T>测试处理是否成功。</T>
    * @retrun
    * <L value='true'>成功</L>
    * <L value='false'>失败</L>
    *
    * @return
    */
   FSqlFunction isSucceed();

   /**
    * <T>测试处理是否失败。</T>
    * @retrun
    * <L value='true'>失败</L>
    * <L value='false'>成功</L>
    *
    * @return
    */
   FSqlFunction isFailed();

   /**
    * <T>获得结果。</T>
    * @retrun 结果内容
    *
    * @return
    */
   FSqlFunction getResult();

   /**
    * <p>获得Session_Owner（用户登录信息）的位置</p>
    *
    * @return ???????
    */
   FSqlFunction getObjectOwner();

   /**
    * <p>获得系统时间</p>
    *
    * @return ???????
    */
   FSqlFunction getObjectVersion();

   /**
    * <p>检测是否为Data.Delete模块（检测Data.Delete的值是否为D）<p>
    * @retrun TRUE??<BR>FALSE??
    *
    * @return
    */
   FSqlFunction isDataDeleteMode();

   /**
    *
    * @param links
    * @return
    */
   FSqlFunction getUserLinksInfo(Object links);

   /**
    *
    * @param text
    * @param recordTypeName
    * @param recordId
    * @return
    */
   FSqlFunction getUserPickerPack(Object text,
                                  Object recordTypeName,
                                  Object recordId);

   /**
    * <p>重置</p>
    *
    */
   FSqlProcedure reset();

   /**
    * <T>设置结果。</T>
    *
    * @param result 结果
    */
   FSqlProcedure setResult(Object result);

   /**
    * <p>result__ := SUCCEED</p>
    *
    */
   FSqlProcedure setSucceed();

   /**
    * <p> result__ := FAILED</p>
    *
    */
   FSqlProcedure setFailed();

   /**
    * <p>result__ := END_PAGE</p>
    *
    */
   FSqlProcedure setEndPage();

   /**
    * <p>把procedure_logic_ 作为SQL语句执行</p>
    *
    * @param procedureLogic 被执行语句
    */
   FSqlProcedure executeLogic(Object procedureLogic);

   /**
    *
    * @param logic
    * @param recordTypeId
    * @param recordId
    * @param link
    * @param resultLink
    */
   FSqlProcedure storeUserLink(Object logic,
                               Object recordTypeId,
                               Object recordId,
                               Object link,
                               Object resultLink);

   /**
    *
    * @param logic
    * @param recordName
    * @param recordId
    * @param links
    * @param resultLinks
    */
   FSqlProcedure storeUserLinks(Object logic,
                                Object recordName,
                                Object recordId,
                                Object links,
                                Object resultLinks);

   /**
    *
    * @param recordName
    * @param recordId
    */
   FSqlProcedure removeUserLinks(Object recordName,
                                 Object recordId);

   /**
    *
    * @param params
    * @param results
    */
   FSqlProcedure findAllInformation(Object params,
                                    Object results);

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
