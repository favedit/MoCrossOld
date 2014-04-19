/*
 * @(#)FNamingNode.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.eng.naming;

/**
 * <p>名称节点组件</p>
 *
 * @author ALEX
 * @version 1.00 - 2005/10/26
 */
public class FNamingNode
{
   //   // 序列化版本号
   //   private static final long serialVersionUID = 1L;
   //
   //   // 子节点哈希表
   //   private FNamingMap<Object> m_oChildrenMap = new FNamingMap<Object>();
   //
   //   // 内容哈希表
   //   private FNamingMap<Object> m_oValueMap = new FNamingMap<Object>();
   //
   //   /**
   //    * <p>创建名称节点组件的实例</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    */
   //   public FNamingNode(){
   //      super(null);
   //   }
   //
   //   /**
   //    * <p>创建名称节点组件的实例</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sName 名称
   //    */
   //   public FNamingNode(String sName){
   //      super(sName);
   //   }
   //
   //   /**
   //    * <p>获得子节点哈希表</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @return 子节点哈希表
   //    */
   //   public FNamingMap<Object> childrenMap(){
   //      return m_oChildrenMap;
   //   }
   //
   //   /**
   //    * <p>获得内容哈希表</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @return 内容哈希表
   //    */
   //   public FNamingMap<Object> valueMap(){
   //      return m_oValueMap;
   //   }
   //
   //   /**
   //    * <p>创建一个新的名称节点</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sName 名称
   //    * @return 名称节点
   //    */
   //   public FNamingNode createNamingNode(String sName){
   //      FNamingNode oNode = new FNamingNode(sName);
   //      m_oChildrenMap.put(sName, oNode);
   //      return oNode;
   //   }
   //
   //   /**
   //    * <p>根据指定的名称、查找一个名称节点</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sName 名称
   //    * @return 名称节点
   //    */
   //   public FNamingNode findNode(String sName){
   //      FNamingNode oNode = null;
   //      if(m_oChildrenMap.containsName(sName)){
   //         oNode = (FNamingNode) m_oChildrenMap.value(sName);
   //      }
   //      return oNode;
   //   }
   //
   //   /**
   //    * <p>根据指定的名称、移除一个名称的节点</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sName 名称
   //    * @return 名称节点
   //    */
   //   public boolean removeNode(String sName){
   //      if(m_oChildrenMap.containsName(sName)){
   //         m_oChildrenMap.remove(sName);
   //         return false;
   //      }
   //      return true;
   //   }
   //
   //   /**
   //    * <p>根据指定的名称、同步一个名称节点</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sName 名称
   //    * @return 名称节点
   //    */
   //   public FNamingNode syncNode(String sName){
   //      FNamingNode oNode = null;
   //      if(m_oChildrenMap.containsName(sName)){
   //         oNode = (FNamingNode) m_oChildrenMap.value(sName);
   //      }else{
   //         oNode = createNamingNode(sName);
   //      }
   //      return oNode;
   //   }
   //
   //   /**
   //    * <p>根据指定的名称、查找一个内容对象</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sName 名称
   //    * @return 内容对象
   //    */
   //   public Object lookup(String sName){
   //      return m_oValueMap.containsName(sName) ? ((FNamingValue) m_oValueMap.value(sName)).object()
   //            : null;
   //   }
   //
   //   /**
   //    * <p>根据指定的名称、绑定一个内容对象到节点上</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sName 名称
   //    * @param oValue 内容对象
   //    * @return TRUE：成功<br>FALSE：失败
   //    */
   //   public boolean bind(String sName,
   //                       Object oValue){
   //      FNamingValue oNamingValue = null;
   //      if(m_oValueMap.containsName(sName)){
   //         oNamingValue = (FNamingValue) m_oValueMap.value(sName);
   //      }else{
   //         oNamingValue = new FNamingValue();
   //         m_oValueMap.put(sName, oNamingValue);
   //      }
   //      return oNamingValue.setObject(oValue);
   //   }
   //
   //   /**
   //    * <p>列出当前节点下的所有内容对象</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @return 内容对象数组
   //    */
   //   public Object[] list(){
   //      return m_oValueMap.valueArray();
   //   }
   //
   //   /**
   //    * <p>根据指定的名称、解除对象绑定</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sName 名称
   //    * @return TRUE：成功<br>FALSE：失败
   //    */
   //   public boolean unbind(String sName){
   //      if(m_oValueMap.containsName(sName)){
   //         m_oValueMap.remove(sName);
   //         return true;
   //      }
   //      return false;
   //   }
   //
   //   // 获得当前实例运行时的信息
   //   protected boolean dump(FString sDump,
   //                          FNamingNode oNode,
   //                          String sPreFix){
   //      sDump.append(sPreFix);
   //      sDump.append(oNode.name());
   //      sDump.append(" ");
   //      sDump.append(FClassUtil.dump(oNode));
   //      sDump.append(" [ ");
   //      FNamingMap<Object> oValueMap = oNode.valueMap();
   //      Object oValue = null;
   //      Object[] arValues = oValueMap.valueArray();
   //      for(int n = 0; n < arValues.length; n++){
   //         oValue = arValues[n];
   //         if(n != 0){
   //            sDump.append(", ");
   //         }
   //         sDump.append(oValue);
   //         sDump.append("=");
   //         sDump.append(((FNamingValue) oValueMap.value((String) oValue)).object());
   //      }
   //      sDump.append(" ]\n");
   //      FNamingMap<Object> oChildrenMap = oNode.childrenMap();
   //      Object[] arChildren = oChildrenMap.valueArray();
   //      for(int n = 0; n < arChildren.length; n++){
   //         dump(sDump, (FNamingNode) arChildren[n], sPreFix + "   ");
   //      }
   //      return true;
   //   }
   //
   //   /**
   //    * <p>获得当前实例运行时的信息</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @return 运行时的信息
   //    * @exception FException 应用程序例外
   //    */
   //   public FString dump(){
   //      FString sDump = new FString();
   //      dump(sDump, this, "");
   //      return sDump;
   //   }
}
