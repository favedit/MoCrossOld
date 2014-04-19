/*
 * @(#)FSystemConsole.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.eng.system;

/**
 * <p>系统控制台</p>
 * <p>1. 创建和配置其他管理器对象</p>
 * <p>2. 根据配置文件信息，初始化管理器对象</p>
 * <p>3. 系统关闭时,根据配置文件信息，关闭所有管理器</p>
 * 
 * @author ALEX
 * @version 1.00 - 2005/11/03
 */
public class FSystemConsole
{
   //
   //   // 环境关闭字符串
   //   private static final String DESTORY_CONTEXT = "-- Destory context -----------------------------------------";
   //
   //   // 环境启动字符串
   //   private static final String INITIALIZE_CONTEXT = "-- Initialize context --------------------------------------";
   //
   //   // 序列化标志
   //   private static final long serialVersionUID = 1L;
   //
   //   // 停止标志
   //   private boolean m_bStopFlag = false;
   //
   //   // 启动节点
   //   private FXmlNode m_oBootupNode = null;
   //
   //   // 对象实例列表
   //   private FNamingObjectList m_oInstances = new FNamingObjectList();
   //
   //   // 关闭节点
   //   private FXmlNode m_oShutdownNode = null;
   //
   //   // 关闭线程
   //   private FShutdownThread m_oShutdownThread = null;
   //
   //   /**
   //    * <p>获得启动配置节点</p>
   //    * <p>create date:2005/11/03</p>
   //    *
   //    * @return 配置节点
   //    */
   //   public FXmlNode bootupNode(){
   //      return m_oBootupNode;
   //   }
   //
   //   /**
   //    * <p>释放环境内容</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    */
   //   public void contextDestroyed(){
   //      contextDestroyed(false);
   //   }
   //
   //   /**
   //    * <p>释放环境内容</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param bShutdownThread 停止线程标志
   //    */
   //   public synchronized void contextDestroyed(boolean bShutdownThread){
   //      // 非正常启动或已经执行过
   //      if(m_oShutdownThread == null){
   //         return;
   //      }
   //      // 释放所有环境信息
   //      FLogger.info(FSystemManager.class, "contextDestroyed", DESTORY_CONTEXT);
   //      try{
   //         // 执行停止配置脚本
   //         for(FXmlNode oStepNode : shutdownNode().nodes()){
   //            try{
   //               if(oStepNode.isName("Action")){
   //                  dispatchAction(oStepNode, "release");
   //               }else{
   //                  FString sMsg = new FString();
   //                  sMsg.append("Unknown action. [");
   //                  sMsg.append(oStepNode.dump());
   //                  sMsg.append("]");
   //                  throw new FFatalException(this, "contextDestroyed", sMsg);
   //               }
   //               if(m_bStopFlag){
   //                  break;
   //               }
   //            }catch(Exception oException){
   //               FLogger.error(this, "contextDestroyed", oException);
   //            }
   //         }
   //         // 通知所有线程停止运行
   //         RThread.stopAll();
   //         // 如果不是线程运行,则移除关闭线程
   //         if(!bShutdownThread){
   //            Runtime.getRuntime().removeShutdownHook(m_oShutdownThread);
   //         }
   //         m_oShutdownThread = null;
   //      }catch(Exception oException){
   //         FLogger.error(this, "contextDestroyed", oException);
   //      }
   //   }
   //
   //   /**
   //    * <p>初始化环境内容</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    */
   //   public synchronized void contextInitialized(){
   //      try{
   //         // 修正系统管理器到实例列表
   //         m_oInstances.add("system", FSystemManager.instance());
   //         // 处理启动设置节点
   //         for(FXmlNode oStepNode : bootupNode().nodes()){
   //            try{
   //               if(oStepNode.isName("Instance")){
   //                  dispatchInstance(oStepNode);
   //               }else if(oStepNode.isName("Action")){
   //                  dispatchAction(oStepNode, "initialize");
   //               }else{
   //                  FString sMsg = new FString();
   //                  sMsg.append("Unknown action. [");
   //                  sMsg.append(oStepNode.dump());
   //                  sMsg.append("]");
   //                  throw new FFatalException(this, "contextInitialized", sMsg);
   //               }
   //               if(m_bStopFlag){
   //                  break;
   //               }
   //            }catch(Exception oException){
   //               FLogger.error(this, "contextInitialized", oException);
   //            }
   //         }
   //         if(m_bStopFlag){
   //            contextDestroyed();
   //         }else{
   //            // 布置关闭时的操作线程
   //            m_oShutdownThread = new FShutdownThread(this);
   //            Runtime.getRuntime().addShutdownHook(m_oShutdownThread);
   //            FLogger.info(FSystemManager.class, "contextInitialized", INITIALIZE_CONTEXT);
   //         }
   //      }catch(Exception oException){
   //         FLogger.error(this, "contextInitialized", oException);
   //      }
   //   }
   //
   //   /**
   //    * <p>纷发命令处理</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param oNode 配置信息
   //    */
   //   protected void dispatchAction(FXmlNode oNode,
   //                                 String sAction)
   //         throws Exception{
   //      String sName = oNode.attribute("name");
   //      Object oInstance = m_oInstances.get(sName);
   //      if(oInstance == null){
   //         FString sMsg = new FString();
   //         sMsg.append("Instance is null. [");
   //         sMsg.append(oNode.dump());
   //         sMsg.append("]");
   //         throw new FFatalException(this, "dispatchAction", sMsg);
   //      }
   //      invokeAction(oInstance, oNode, sAction);
   //   }
   //
   //   /**
   //    * <p>纷发实例处理</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param oNode 配置信息
   //    */
   //   public void dispatchInstance(FXmlNode oNode)
   //         throws Exception{
   //      String sName = oNode.attribute("name");
   //      if(!m_oInstances.containsName(sName)){
   //         // 创建一个启动节点的实例
   //         String sType = RString.nvl(oNode.attribute("type")).toLowerCase();
   //         if(sType.equals("manager")){
   //            dispatchInstanceManager(oNode);
   //         }else if(sType.equals("console")){
   //            dispatchInstanceConsole(oNode);
   //         }
   //      }
   //   }
   //
   //   // 纷发控制台实例处理
   //   protected void dispatchInstanceConsole(FXmlNode oNode)
   //         throws Exception{
   //      String sName = oNode.attribute("name");
   //      String sClassName = oNode.attribute("class");
   //      if(FLogger.canDebug()){
   //         FString sMsg = new FString();
   //         sMsg.append("Create console - ");
   //         sMsg.append(sName);
   //         sMsg.append(" <");
   //         sMsg.append(sClassName);
   //         sMsg.append(">");
   //         FLogger.debug(this, "dispatchInstanceConsole", sMsg);
   //      }
   //      // 创建管理器实例
   //      FConsole oInstance = RClass.newInstance(sClassName);
   //      // 设置配置信息节点
   //      FXmlNode oConfigNode = configNode().findNode("name", sName);
   //      oInstance.setConfigNode(oConfigNode);
   //      // 记录新建实例
   //      m_oInstances.add(sName, oInstance);
   //   }
   //
   //   // 纷发管理器实例处理
   //   protected void dispatchInstanceManager(FXmlNode oNode)
   //         throws Exception{
   //      String sName = oNode.attribute("name");
   //      String sClassName = oNode.attribute("class");
   //      if(FLogger.canDebug()){
   //         FString sMsg = new FString();
   //         sMsg.append("Create manager - ");
   //         sMsg.append(sName);
   //         sMsg.append(" <");
   //         sMsg.append(sClassName);
   //         sMsg.append(">");
   //         FLogger.debug(this, "dispatchInstanceManager", sMsg);
   //      }
   //      // 创建管理器实例
   //      FManager oInstance = RClass.newInstance(sClassName);
   //      // 将管理器实例放入相应的管理器的类中
   //      Class oClass = RClass.findClass(sClassName);
   //      if(oClass == null){
   //         throw new NoClassDefFoundError(sClassName);
   //      }
   //      Method oMethod = RClass.findMethod(oClass, "setInstance", false);
   //      if(oMethod == null){
   //         throw new NoSuchMethodException("setInstance");
   //      }
   //      oMethod.invoke(oClass, new Object[] { oInstance });
   //      // 建立管理器内部其他实例
   //      oInstance.buildInstance(oNode);
   //      // 设置配置信息节点
   //      FXmlNode oConfigNode = configNode().findNode("name", sName);
   //      oInstance.setConfigNode(oConfigNode);
   //      // 记录新建实例
   //      m_oInstances.add(sName, oInstance);
   //   }
   //
   //   // 响应命令函数处理
   //   protected void invokeAction(Object oItem,
   //                               FXmlNode oNode,
   //                               String sMethodAction)
   //         throws FException, Exception{
   //      String sActionList = oNode.attribute("Action");
   //      String[] arAction = RString.split(sActionList, ',');
   //      for(String sAction : arAction){
   //         if(!RString.isEmpty(sAction)){
   //            if(oItem instanceof FManager){
   //               FManager oManager = (FManager) oItem;
   //               if(sMethodAction.equalsIgnoreCase("initialize")){
   //                  oManager.initialize(sAction);
   //               }else if(sMethodAction.equalsIgnoreCase("release")){
   //                  oManager.release(sAction);
   //               }
   //            }else if(oItem instanceof FConsole){
   //               FConsole oConsole = (FConsole) oItem;
   //               if(sMethodAction.equalsIgnoreCase("initialize")){
   //                  oConsole.initialize(sAction);
   //               }else if(sMethodAction.equalsIgnoreCase("release")){
   //                  oConsole.release(sAction);
   //               }
   //            }else{
   //               String sMethod = sMethodAction + sAction;
   //               Method oMethod = RClass.findMethod(oItem, sMethod);
   //               if(oMethod == null){
   //                  throw new NoSuchMethodException(sMethod);
   //               }
   //               oMethod.invoke(oItem, new Object[] {});
   //            }
   //         }
   //      }
   //   }
   //
   //   /**
   //    * <p>纷发处理过程</p>
   //    * <p>create date:2005/11/03</p>
   //    *
   //    * @param oNode 配置信息
   //    */
   //   public void process(String sAction,
   //                       Object oParam)
   //         throws FException{
   //      for(Object oItem : m_oInstances.values()){
   //         if(oItem instanceof IProcess){
   //            ((IProcess) oItem).process(sAction, oParam);
   //         }
   //      }
   //   }
   //
   //   // 解析节点内容
   //   protected String reviseEntironment(String sValue){
   //      sValue = sValue.trim();
   //      sValue = sValue.replaceAll("\\{home\\}", FSystemManager.HOMT_DIRECTORY);
   //      return sValue;
   //   }
   //
   //   // 循环解析全部节点
   //   private void reviseNodeEntironment(FXmlNode oNode){
   //      if(!RString.isEmpty(oNode.text())){
   //         oNode.setText(reviseEntironment(oNode.text()));
   //      }
   //      if(oNode.hasAttribute()){
   //         FStringList oAttribute = oNode.attributes();
   //         int nSize = oAttribute.size();
   //         for(int n = 0; n < nSize; n++){
   //            oAttribute.set(n, reviseEntironment(oAttribute.get(n)));
   //         }
   //      }
   //      if(oNode.hasNode()){
   //         for(FXmlNode oChildNode : oNode.nodes()){
   //            reviseNodeEntironment(oChildNode);
   //         }
   //      }
   //   }
   //
   //   /**
   //    * <p>设置启动配置节点</p>
   //    * <p>create date:2005/11/03</p>
   //    *
   //    * @param oBootupNode 启动节点
   //    * @return 配置节点
   //    */
   //   public void setBootupNode(FXmlNode oBootupNode){
   //      m_oBootupNode = oBootupNode;
   //   }
   //
   //   /**
   //    * <p>设置配置节点</p>
   //    * <p>create date:2005/11/03</p>
   //    *
   //    * @param oNode 配置信息
   //    */
   //   public void setConfigNode(FXmlNode oNode)
   //         throws FException{
   //      reviseNodeEntironment(oNode);
   //      super.setConfigNode(oNode);
   //   }
   //
   //   /**
   //    * <p>设置关闭配置节点</p>
   //    * <p>create date:2005/11/03</p>
   //    *
   //    * @param oShutdownNode 关闭节点
   //    * @return 配置节点
   //    */
   //   public void setShutdownNode(FXmlNode oShutdownNode){
   //      m_oShutdownNode = oShutdownNode;
   //   }
   //
   //   /**
   //    * <p>获得关闭配置节点</p>
   //    * <p>create date:2005/11/03</p>
   //    *
   //    * @return 配置节点
   //    */
   //   public FXmlNode shutdownNode(){
   //      return m_oShutdownNode;
   //   }
}
