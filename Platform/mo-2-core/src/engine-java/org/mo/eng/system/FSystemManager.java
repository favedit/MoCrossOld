/*
 * @(#)FSystemManager.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.eng.system;

/**
 * <p>系统管理器</p>
 * <p>1. 读取配置文件</p>
 * <p>2. 创建系统管理器的实例</p>
 * 
 * @author ALEX
 * @version 1.00 - 2005/10/15
 */
public class FSystemManager
{
   // 系统设置文件
   public static String CONFIG_DIRECTORY = null;

   // 系统目录
   public static String HOMT_DIRECTORY = null;

   // 控制台  
   public static FSystemConsole m_oConsole = null;

   // 当前对象实例
   private static FSystemManager m_oInstance = null;

   /**
    * <p>获得控制台的实例</p>
    * <p>create date:2005/10/20</p>
    *
    * @return 控制台
    */
   public static FSystemConsole console(){
      if(m_oConsole == null){
         //m_oConsole = instance().baseConsole();
      }
      return m_oConsole;
   }

   /**
    * <p>获得当前对象的实例</p>
    * <p>create date:2005/02/18</p>
    *
    * @return 当前对象的实例
    */
   public static FSystemManager instance(){
      return m_oInstance;
   }

   /**
    * <p>设置环境变量</p>
    * <p>create date:2005/10/15</p>
    *
    * @return 当前对象的实例
    */
   public static void setContext(String sHome,
                                 String sConfig){
      HOMT_DIRECTORY = sHome;
      CONFIG_DIRECTORY = sConfig;
   }

   /**
    * <p>设置当前对象的实例</p>
    * <p>create date:2005/02/18</p>
    *
    * @param oInstance 当前对象的实例
    */
   public static void setInstance(FSystemManager oInstance){
      m_oInstance = oInstance;
   }

   /**
    * <p>关闭系统</p>
    * <p>create date:2005/11/03</p>
    *
    */
   public static void shutdown(){
      //console().contextDestroyed();
      //FLogger.info(FSystemManager.class, "shutdown", "Shutdown system.");
   }

   /**
    * <p>启动系统</p>
    * <p>create date:2005/11/03</p>
    *
    */
   public static void startup(){
      //      try{
      //         FLogger.info(FSystemManager.class, "startup", "Start system.");
      //         // 打开启动文件
      //         FXmlDocument oBootupDoc = new FXmlDocument();
      //         oBootupDoc.loadFromFile(CONFIG_DIRECTORY + "/bootup.xml");
      //         // 生成系统管理器实例
      //         String sClassName = null;
      //         FXmlNode oBootupNode = oBootupDoc.rootNode();
      //         FXmlNode oSysNode = oBootupNode.findNode("Instance", "name", "system");
      //         if(oSysNode != null){
      //            sClassName = oSysNode.attribute("class");
      //         }
      //         if(RString.isEmpty(sClassName)){
      //            sClassName = FSystemManager.class.getName();
      //         }else{
      //            if(!RClass.hasClass(sClassName)){
      //               throw new NoClassDefFoundError(sClassName);
      //            }
      //         }
      //         Class oSysClass = RClass.findClass(sClassName);
      //         Method oSysMethod = RClass.findMethod(oSysClass, "setInstance");
      //         if(oSysMethod == null){
      //            throw new NoSuchMethodException("setInstance");
      //         }
      //         FSystemManager oSystemManager = RClass.newInstance(oSysClass);
      //         oSysMethod.invoke(oSysClass, new Object[] { oSystemManager });
      //         // 生成系统控制台实例
      //         oSystemManager.buildInstance(oSysNode);
      //         FSystemConsole oSystemConsole = FSystemManager.console();
      //         // 设置启动信息
      //         oSystemConsole.setBootupNode(oBootupNode);
      //         // 设置配置信息
      //         FXmlDocument oConfigDoc = new FXmlDocument();
      //         oConfigDoc.loadFromFile(CONFIG_DIRECTORY + "/config.xml");
      //         oSystemConsole.setConfigNode(oConfigDoc.rootNode());
      //         // 设置关闭信息
      //         FXmlDocument oShutdownDoc = new FXmlDocument();
      //         oShutdownDoc.loadFromFile(CONFIG_DIRECTORY + "/shutdown.xml");
      //         oSystemConsole.setShutdownNode(oShutdownDoc.rootNode());
      //         // 根据启动文件执行启动过程.
      //         oSystemConsole.contextInitialized();
      //      }catch(FException oException){
      //         FLogger.error(FSystemManager.class, "startup", oException);
      //      }catch(Exception oException){
      //         FLogger.error(FSystemManager.class, "startup", oException);
      //      }
   }

   // 创建一个控制台实例
   protected FSystemConsole createConsole(){
      return new FSystemConsole();
   }
}
