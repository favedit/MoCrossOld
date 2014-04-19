/*
 * @(#)FWebRedirectConsole.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.web.core.redirect;

/**
 * <p>请求转向控制台</p>
 * <p>1. 提供对URI内容的转向</p>
 *
 * @author ALEX
 * @version 1.00 - 2005/10/27
 */
public class FWebRedirectConsole
      implements
         IWebRedirectConsole
{

   //   // 序列化标志
   //   private static final long serialVersionUID = 1L;
   //
   //   // 转向监视器
   //   private FWebRedirectMonitor m_oMonitor = null;
   //
   //   // 转向缓冲
   //   private FNamingStringMap m_oRedirectMap = null;
   //
   //   // 工作文件
   //   private String m_sFileName = null;
   //
   //   /**
   //    * <p>创建别称组件的实例</p>
   //    * <p>create date:2005/02/18</p>
   //    */
   //   public FWebRedirectConsole(){
   //   }
   //
   //   /**
   //    * <p>初始化前操作</p>
   //    * <p>设置初始化的参数</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @exception FException 应用程序例外
   //    */
   //   public void initializeConfig()
   //         throws FException{
   //      m_sFileName = configNode().nodeText("File");
   //   }
   //
   //   /**
   //    * <p>初始化操作</p>
   //    * <p>建立请求转向监视器</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @exception FException 应用程序例外
   //    */
   //   public void initializeMonitor()
   //         throws FException{
   //      m_oMonitor = new FWebRedirectMonitor(this, m_sFileName);
   //      FMonitorManager.console().push(m_oMonitor);
   //   }
   //
   //   /**
   //    * <p>将指定的文件录入到别称缓冲中</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sFileName 指定文件
   //    * @exception FException 应用程序例外
   //    */
   //   public void loadRedirect()
   //         throws FException{
   //      try{
   //         m_oRedirectMap = new FNamingStringMap();
   //         File oFile = new File(m_sFileName);
   //         if(oFile.exists()){
   //            String sFromURI = null;
   //            String sToURI = null;
   //            FXmlDocument oDocument = new FXmlDocument(m_sFileName);
   //            for(FXmlNode oNode : oDocument.rootNode().nodes()){
   //               sFromURI = oNode.nodeText("from");
   //               sToURI = oNode.nodeText("to");
   //               if(!RString.isEmpty(sFromURI) && !RString.isEmpty(sToURI)){
   //                  m_oRedirectMap.put(sFromURI, sToURI);
   //               }
   //            }
   //            if(FLogger.canDebug()){
   //               FString sMsg = new FString();
   //               sMsg.append("Load redirect : [ ");
   //               sMsg.append(m_sFileName);
   //               sMsg.append("]");
   //               FLogger.debug(this, "loadRedirect", sMsg);
   //            }
   //         }
   //      }catch(Exception oException){
   //         FString sMsg = new FString();
   //         sMsg.append("FileName[");
   //         sMsg.append(m_sFileName);
   //         sMsg.append("]");
   //         throw new FFatalException("loadRedirect", oException, sMsg);
   //      }
   //   }
   //
   //   /**
   //    * <p>获得指定名称的转向内容</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sRedirect 指定名称
   //    * @return 转向内容
   //    * @exception FException 应用程序例外
   //    */
   //   public String redirect(String sRedirect)
   //         throws FException{
   //      if(m_oRedirectMap == null){
   //         loadRedirect();
   //      }
   //      if(m_oRedirectMap.containsName(sRedirect)){
   //         return m_oRedirectMap.get(sRedirect);
   //      }
   //      return null;
   //   }

}
