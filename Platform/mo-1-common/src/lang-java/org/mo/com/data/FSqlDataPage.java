/*
 * @(#)FDataset.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.com.data;

import org.mo.com.collections.FRow;
import org.mo.com.lang.FError;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObjects;
import org.mo.com.lang.FString;
import org.mo.com.lang.RDump;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.TDumpInfo;

//============================================================
// <T>数据集合列表组件。</T>
//
// @param 051012 MAOCY 创建
//============================================================
public class FSqlDataPage
      extends FObjects<FRow>
{
   // 总页数
   private int m_nPageCount = 0;

   // 页号
   private int m_nPagePosition = 0;

   // 分页记录数
   private int m_nPageSize = 20;

   // 总记录数
   private int m_nTotalCount = 0;

   public FSqlDataPage(){
      super(FRow.class);
   }

   //============================================================
   // <T>生成运行信息。</T>
   //
   // @param info 运行信息
   // @return 运行信息
   //============================================================
   @Override
   public TDumpInfo dump(TDumpInfo info){
      RDump.dump(info, this);
      info.append(" Size:" + _count + "/" + pageSize() + "/" + totalCount() + " Page:" + pagePosition() + "/" + pageCount());
      if(!isEmpty()){
         FString sLeftSpace = new FString(RString.repeat(RDump.LEVEL_SPACE, info.level() + 1));
         info.append(" {\n");
         for(FRow oValue : this){
            info.append(sLeftSpace);
            info.append(oValue.dump());
            info.append("\n");
         }
         info.append(RString.repeat("   ", info.level()));
         info.append("}");
      }
      return info;
   }

   /**
    * <p>查找执行名称和内容的数据单元</p>
    * <p>create date:2005/02/14</p>
    *
    * @param sName 指定名称
    * @param sValue 指定内容
    * @return 数据单元
    */
   public FRow find(String sName,
                    String sValue){
      for(FRow oValue : this){
         if(RString.equalsIgnoreCase(oValue.get(sName), sValue)){
            return oValue;
         }
      }
      return null;
   }

   /**
    * <p>将当前列表打包为字符串内容</p>
    * <p>create date:2005/02/14</p>
    *
    * @return 打包字符串
    */
   public FString pack() throws FError{
      FString sPack = new FString();
      for(FRow oValue : this){
         String sValuePack = oValue.pack();
         sPack.appendInt(sValuePack.length());
         sPack.append("_");
         sPack.append(sValuePack);
      }
      return sPack;
   }

   /**
    * <p>获得总页数</p>
    * <p>create date:2005/02/14</p>
    *
    * @return 总页数
    */
   public int pageCount(){
      return m_nPageCount;
   }

   /**
    * <p>获得页号</p>
    * <p>create date:2005/02/14</p>
    *
    * @return 页号
    */
   public int pagePosition(){
      return m_nPagePosition;
   }

   /**
    * <p>获得分页记录数</p>
    * <p>create date:2005/02/14</p>
    *
    * @return 分页记录数
    */
   public int pageSize(){
      return m_nPageSize;
   }

   /**
    * <p>设置总页数</p>
    * <p>create date:2005/02/14</p>
    *
    * @param nValue 总页数
    * @return TRUE：成功<br>FALSE：失败
    */
   public void setPageCount(int nValue){
      m_nPageCount = nValue;
   }

   /**
    * <p>设置页号</p>
    * <p>create date:2005/02/14</p>
    *
    * @param nValue 页号
    * @return TRUE：成功<br>FALSE：失败
    */
   public void setPagePosition(int nValue){
      m_nPagePosition = nValue;
   }

   /**
    * <p>设置分页记录数</p>
    * <p>create date:2005/02/14</p>
    *
    * @param nValue 分页记录数
    * @return TRUE：成功 <br>FALSE：失败
    */
   public void setPageSize(int nValue){
      m_nPageSize = nValue;
   }

   /**
    * <p>设置总记录数</p>
    * <p>create date:2005/02/14</p>
    *
    * @param nValue 总记录数
    * @return TRUE：成功 <br>FALSE：失败
    */
   public void setTotalCount(int nValue){
      m_nTotalCount = nValue;
   }

   /**
    * <p>按照节点指定属性以数字方式进行排序</p>
    * <p>create date:2005/02/14</p>
    *
    * @param sKey 属性名称
    * @return TRUE：成功 <br>FALSE：失败
    */
   public boolean sortAsInteger(String sKey){
      return sortAsInteger(sKey, true);
   }

   /**
    * <p>按照节点指定属性以数字方式进行排序</p>
    * <p>create date:2005/02/14</p>
    *
    * @param sKey 属性名称
    * @param bAsc 是否按照升序排列
    * @return TRUE：成功 <br>FALSE：失败
    */
   public boolean sortAsInteger(String sKey,
                                boolean bAsc){
      //      int nValue1 = 0;
      //      int nValue2 = 0;
      //      boolean bCompare = false;
      //      int nCount = size();
      //      for(int j = 0; j < nCount; j++){
      //         for(int i = 0; i < nCount - 1 - j; i++){
      //            nValue1 = FIntegerUtil.toInteger(value(i).value(sKey), 0);
      //            nValue2 = FIntegerUtil.toInteger(value(i + 1).value(sKey), 0);
      //            bCompare = nValue1 > nValue2;
      //            if(!bAsc){
      //               bCompare = !bCompare;
      //            }
      //            if(bCompare){
      //               swap(i, i + 1);
      //            }
      //         }
      //      }
      return true;
   }

   public void sortByValue(String sName){
      //FObjectUtil.sort(memory(), 0, size(), sName, true);
   }

   /**
    * <p>获得总记录数</p>
    * <p>create date:2005/02/14</p>
    *
    * @return 总记录数
    */
   public int totalCount(){
      return m_nTotalCount;
   }

   /**
    * <p>将打包字符串内容,反解到当前列表中</p>
    * <p>create date:2005/02/14</p>
    *
    * @param packString 打包字符串
    * @return TRUE：成功 <br>
    *         FALSE：失败
    */
   public boolean unpack(String packString) throws FError{
      return unpack(packString, false);
   }

   /**
    * <p>将打包字符串内容,反解到当前列表中</p>
    * <p>create date:2005/02/14</p>
    *
    * @param packString 打包字符串
    * @param mergeFlag 合并标志
    * @return TRUE：成功 <br>FALSE：失败
    */
   public boolean unpack(String sPackString,
                         boolean bMergeFlag) throws FError{
      if(!bMergeFlag){
         clear();
      }
      if(RString.isEmpty(sPackString)){
         return true;
      }
      // 初始化变量
      int nPosition = 0;
      int nFind = -1;
      int nItemLength = 0;
      FRow oValue = null;
      String sItemPack = "";
      // 反解打包字符串
      try{
         while(true){
            nFind = sPackString.indexOf("_", nPosition);
            if(nFind >= 0){
               nItemLength = Integer.parseInt(sPackString.substring(nPosition, nFind));
               sItemPack = sPackString.substring(nFind + 1, nFind + 1 + nItemLength);
            }else{
               break;
            }
            nPosition = nFind + 1 + nItemLength;
            oValue = new FRow();
            oValue.unpack(sItemPack);
            push(oValue);
         }
      }catch(Exception e){
         throw new FFatalError(e);
      }
      return true;
   }
}
