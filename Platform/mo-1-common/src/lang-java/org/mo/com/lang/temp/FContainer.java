/*
 * @(#)FContainer.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.com.lang.temp;

import org.mo.com.lang.RString;

/**
 * <p>数据容器基类</p>
 * <p>1. 对各种数据类型属性的访问</p>
 * <p>2. 同步支持</p>
 * 
 * @author FEDT
 * @version 1.00 - 2005/02/18
 */
public class FContainer
//      extends MNamedObjects<INamePair<Object>, Object>
//implements IPack
{
   // 当前属性的总数
   private final int _count = 0;

   // 节点名称
   private String _name = null;

   // 数据指针位置
   private int _position = 0;

   /**
    * <p>
    * 创建数据容器组件的实例
    * </p>
    * <p>
    * create date:2005/02/18
    * </p>
    */
   public FContainer(){
      //super(Object.class);
   }

   public boolean isEmpty(){
      return false;
   }

   public int compareName(FContainer oItem){
      return RString.compare(_name, oItem.name(), false);
   }

   /**
    * <p>根据默认指针位置，获得一个属性</p>
    * <p>create date:2005/02/18</p>
    * 
    * @return 属性对象
    */
   //   public Object current(){
   //      return value(_position);
   //   }
   /**
    * <p>
    * 移动当前的数据位置到第一个属性
    * </p>
    * <p>
    * create date:2005/02/18
    * </p>
    * 
    * @return TRUE：成功<br>FALSE：失败
    */
   public void first(){
      //_position = (_count > 0) ? 0 : -1;
   }

   /**
    * <p>
    * 当前的数据是否在第一个属性之前
    * </p>
    * <p>
    * create date:2005/02/18
    * </p>
    * 
    * @return true:是 <br>
    *         false:否
    */
   public boolean isBOF(){
      //return (_count > 0) ? (_position < 0) : true;
      return true;
   }

   /**
    * <p>
    * 当前的数据是否在最后一个属性之后
    * </p>
    * <p>
    * create date:2005/02/18
    * </p>
    * 
    * @return true:是 <br>
    *         false:否
    */
   public boolean isEOF(){
      //return (_count > 0) ? (_position >= _count) : true;
      return true;
   }

   /**
    * <p>
    * 当前的数据是否在第一个属性上
    * </p>
    * <p>
    * create date:2005/02/18
    * </p>
    * 
    * @return true:是 <br>
    *         false:否
    */
   public boolean isFirst(){
      //return (_count > 0) ? (_position == 0) : true;
      return true;
   }

   /**
    * <p>
    * 当前的数据是否在最后一个属性上
    * </p>
    * <p>
    * create date:2005/02/18
    * </p>
    * 
    * @return true:是 <br>
    *         false:否
    */
   public boolean isLast(){
      //return (_count > 0) ? (_position == (_count - 1)) : true;
      return true;
   }

   /**
    * <p>
    * 移动当前的数据位置到最后一个属性
    * </p>
    * <p>
    * create date:2005/02/18
    * </p>
    * 
    * @return TRUE：成功 <br>
    *         FALSE：失败
    */
   public void last(){
      //_position = (_count > 0) ? _count : -1;
   }

   /**
    * <p>
    * 获得节点名称
    * </p>
    * <p>
    * create date:2005/02/18
    * </p>
    * 
    * @return 节点名称
    */
   public String name(){
      return _name;
   }

   /**
    * <p>
    * 移动当前的数据位置到下一个属性
    * </p>
    * <p>
    * create date:2005/02/18
    * </p>
    * 
    * @return TRUE：成功 <br>
    *         FALSE：失败
    */
   public void next(){
      //      if(_count > 0){
      //         _position++;
      //         _position = _count;
      //      }else{
      //         _position = -1;
      //      }
   }

   // 获得数据容器子项目的哈希表
   // private boolean __getChildrenHashMap(Map oMap, FContainer oContainer) {
   // if (oContainer != null) {
   // int nCount = oContainer.getCount();
   // oMap.put(oContainer.getObjectId(), oContainer);
   // for (int n = 0; n < nCount; n++) {
   // if (oContainer.testItemClass(n, FContainer.class)) {
   // __getChildrenHashMap(oMap, (FContainer) oContainer.getItem(n));
   // }
   // }
   // return true;
   // }
   // return false;
   // }
   //
   // /**
   // * <p>
   // * 获得当前数据容器下所有对象的哈希表
   // * </p>
   // * <p>
   // * create date:2005/02/18
   // * </p>
   // *
   // * @param oMap 哈希表
   // * @return TRUE：成功 <br>
   // * FALSE：失败
   // */
   // public boolean makeChildrenMap(Map oMap) {
   // if (super.makeChildrenMap(oMap)) {
   // int nCount = getCount();
   // Object oItem = null;
   // for (int n = 0; n < nCount; n++) {
   // oItem = getItem(n);
   // if (oItem instanceof FCollection) {
   // ((FCollection) oItem).makeChildrenMap(oMap);
   // }
   // }
   // return true;
   // }
   // return false;
   // }
   // /**
   // * <p>
   // * 获得当前数据容器下所有对象的哈希表
   // * </p>
   // * <p>
   // * create date:2005/02/18
   // * </p>
   // *
   // * @param sProperty 属性名称
   // * @return HashMap
   // */
   // public Map getChildrenMap() {
   // if (m_piSyncObject == null) {
   // Map oMap = new HashMap();
   // if (super.makeChildrenMap(oMap)) {
   // int nCount = getCount();
   // Object oItem = null;
   // for (int n = 0; n < nCount; n++) {
   // oItem = getItem(n);
   // if (oItem instanceof FCollection) {
   // ((FCollection) oItem).makeChildrenMap(oMap);
   // }
   // }
   // }
   // return oMap;
   // }
   // synchronized (m_piSyncObject) {
   // Map oMap = new HashMap();
   // oMap.put(getObjectId(), this);
   // int nCount = getCount();
   // Object oItem = null;
   // for (int n = 0; n < nCount; n++) {
   // oItem = getItem(n);
   // if (oItem instanceof FCollection) {
   // ((FCollection) oItem).makeChildrenMap(oMap);
   // }
   // }
   // return oMap;
   // }
   // }
   /**
    * <p>
    * 将当前列表打包为字符串内容
    * </p>
    * <p>
    * create date:2005/02/18
    * </p>
    * 
    * @return 打包字符串
    */
   //   @Override
   //   public FString pack(){
   //      FString sPack = new FString();
   //      /*FString sValue = null;
   //       for (FNamedPair oPair : this) {
   //       if (!FStringUtil.isEmpty(oPair.name())) {
   //       sPack.append(oPair.name().length());
   //       sPack.append("_");
   //       sPack.append(oPair.name());
   //       if (oPair.value() == null) {
   //       sPack.append("S0_");
   //       } else {
   //       if (oPair.value() instanceof FDataset) {
   //       sPack.append("D");
   //       // sValue = ((FDataset) oPair.value()).pack();
   //       } else if (oPair.value() instanceof FUnit) {
   //       sPack.append("U");
   //       // sValue = ((FUnit) oPair.value()).pack();
   //       } else if (oPair.value() instanceof String) {
   //       sPack.append("S");
   //       // sValue = (String) oPair.value();
   //       } else {
   //       sPack.append("S");
   //       sValue = new FString(oPair.value().toString());
   //       }
   //       sPack.append(sValue.length());
   //       sPack.append("_");
   //       sPack.append(sValue);
   //       }
   //       }
   //       }*/
   //      return sPack;
   //   }
   /**
    * <p>
    * 获得当前的数据位置
    * </p>
    * <p>
    * create date:2005/02/18
    * </p>
    * 
    * @return 数据位置
    */
   public int position(){
      return _position;
   }

   /**
    * <p>
    * 移动当前的数据位置到前一个属性
    * </p>
    * <p>
    * create date:2005/02/18
    * </p>
    * 
    * @return TRUE：成功 <br>
    *         FALSE：失败
    */
   public void prior(){
      //_position = (_count > 0) ? _position - 1 : -1;
   }

   /**
    * <p>
    * 设置当前的数据位置为最初位置
    * </p>
    * <p>
    * create date:2005/02/18
    * </p>
    * 
    * @return TRUE：成功 <br>
    *         FALSE：失败
    */
   public void reset(){
      _position = -1;
   }

   /**
    * <p>
    * 设置当前的数据位置
    * </p>
    * <p>
    * create date:2005/02/18
    * </p>
    * 
    * @param nValue 数据位置
    * @return TRUE：成功 <br>
    *         FALSE：失败
    */
   public void seek(int nValue){
      if(nValue >= -1 && nValue <= _count){
         _position = nValue;
      }
   }

   /**
    * <p>
    * 设置节点名称
    * </p>
    * <p>
    * create date:2005/02/18
    * </p>
    * 
    * @param value 节点名称
    * @return TRUE：成功 <br>
    *         FALSE：失败
    */
   public void setName(String value){
      _name = value;
   }
   //   public void setValue(String sName, int nValue){
   //      super.set(sName, Integer.toString(nValue));
   //   }
   //
   //   public void setValue(String sName, String sValue){
   //      super.set(sName, sValue);
   //   }
   //   public void unpack(FString packString){
   //      unpack(packString.toString(), false);
   //   }
   /**
    * <p>
    * 测试一个属性的类型
    * </p>
    * <p>
    * create date:2005/02/18
    * </p>
    * 
    * @param nIndex 索引
    * @param oClass 类型
    * @return TRUE：成功 <br>
    *         FALSE：失败
    */
   //   public boolean testItemClass(int nIndex,
   //                                Class oClass){
   //      try{
   //         return (indexOf(nIndex) != -1) ? FClassUtil.isInherit(value(nIndex), oClass) : false;
   //      }catch(Exception oException){
   //         return false;
   //      }
   //   }
   /**
    * <p>
    * 测试一个属性的类型
    * </p>
    * <p>
    * create date:2005/02/18
    * </p>
    * 
    * @param sKey 主键
    * @param oClass 类型
    * @return TRUE：成功 <br>
    *         FALSE：失败
    */
   //   public boolean testItemClass(String sKey,
   //                                Class oClass){
   //      int nIndex = indexOf(sKey);
   //      try{
   //         return (nIndex != -1) ? FClassUtil.isInherit(value(nIndex), oClass) : false;
   //      }catch(Exception e){
   //         return false;
   //      }
   //   }
   //   /**
   //    * <p>
   //    * 将打包字符串内容,反解到当前列表中
   //    * </p>
   //    * <p>
   //    * create date:2005/02/18
   //    * </p>
   //    * 
   //    * @param packString 打包字符串
   //    * @return TRUE：成功 <br>
   //    *         FALSE：失败
   //    */
   //   @Override
   //   public void unpack(String packString){
   //      unpack(packString, false);
   //   }
   //
   //   /**
   //    * <p>
   //    * 将打包字符串内容,反解到当前列表中
   //    * </p>
   //    * <p>
   //    * create date:2005/02/18
   //    * </p>
   //    * 
   //    * @param packString 打包字符串
   //    * @param mergeFlag 合并标志
   //    * @return TRUE：成功 <br>
   //    *         FALSE：失败
   //    */
   //   public boolean unpack(String sPackString, boolean bMergeFlag) throws FError{
   //      if(!bMergeFlag){
   //         clear();
   //      }
   //      if(RString.isEmpty(sPackString)){
   //         return true;
   //      }
   //      // 初始化变量
   //      int nPosition = 0;
   //      int nFind = -1;
   //      int nItemLength = 0;
   //      String sItemType = "";
   //      String sItemName = "";
   //      String sItemPack = "";
   //      FRow oUnit = null;
   //      FDataset oDataset = null;
   //      // 分解压缩字符串
   //      try{
   //         while(true){
   //            // 分解名称
   //            nFind = sPackString.indexOf("_", nPosition);
   //            if(nFind >= 0){
   //               nItemLength = Integer.parseInt(sPackString.substring(nPosition, nFind));
   //               sItemName = sPackString.substring(nFind + 1, nFind + 1 + nItemLength);
   //            }else{
   //               break;
   //            }
   //            nPosition = nFind + 1 + nItemLength;
   //            // 分解类型
   //            sItemType = sPackString.substring(nPosition, nPosition + 1);
   //            nPosition++;
   //            // 分解内容
   //            nFind = sPackString.indexOf("_", nPosition);
   //            if(nFind >= 0){
   //               nItemLength = Integer.parseInt(sPackString.substring(nPosition, nFind));
   //               sItemPack = sPackString.substring(nFind + 1, nFind + 1 + nItemLength);
   //            }else{
   //               break;
   //            }
   //            nPosition = nFind + 1 + nItemLength;
   //            // 新建子项目
   //            if(sItemType.equalsIgnoreCase("S")){
   //               setValue(sItemName, sItemPack);
   //            }else if(sItemType.equalsIgnoreCase("U")){
   //               oUnit = new FRow();
   //               oUnit.unpack(new FString(sItemPack));
   //               set(sItemName, oUnit);
   //            }else if(sItemType.equalsIgnoreCase("D")){
   //               oDataset = new FDataset();
   //               oDataset.unpack(sItemPack);
   //               set(sItemName, oDataset);
   //            }
   //         }
   //      }catch(Exception e){
   //         throw new FFatalError(e);
   //      }
   //      return true;
   //   }
   //
   //   public String valueAsString(String sName){
   //      return (String)get(sName);
   //   }
}
