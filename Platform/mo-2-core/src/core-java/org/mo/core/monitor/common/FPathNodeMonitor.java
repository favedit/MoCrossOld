/*
 * @(#)FPathNodeMonitor.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.core.monitor.common;

/**
 * <p>文件监视器基类</p>
 * <p>1. 监视指定文件和目录内的文件变动，进行相关处理</p>
 *
 * @author FEDT
 * @version 1.00 - 2005/02/18
 */
public abstract class FPathNodeMonitor
      extends FAbstractMonitor
{
   //   // 内容列表
   //   private FList m_oInfoList = new FList(Object.class);
   //
   //   // 文件列表
   //   private FStringList m_oFileList = new FStringList();
   //
   //   /**
   //    * <p>响应初始化文档</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sFileName 文件名称
   //    * @return TRUE：成功<br>FALSE：失败
   //    * @exception FException 应用程序例外
   //    */
   //   public boolean onInitializeFile(String sFileName)
   //         throws FException{
   //      return (sFileName != null);
   //   }
   //
   //   /**
   //    * <p>响应发生变动的文档</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sFileName 文件名称
   //    * @return TRUE：成功<br>FALSE：失败
   //    * @exception FException 应用程序例外
   //    */
   //   public boolean onFileChanged(String sFileName)
   //         throws FException{
   //      return (sFileName != null);
   //   }
   //
   //   /**
   //    * <p>文档过滤器</p>
   //    * <p>监视器只关心返回TRUE的文件</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sFileName 文件名称
   //    * @return TRUE：成功<br>FALSE：失败
   //    * @exception FException 应用程序例外
   //    */
   //   public boolean onFilterFile(String sFileName)
   //         throws FException{
   //      return (sFileName != null);
   //   }
   //
   //   /**
   //    * <p>文档过滤器</p>
   //    * <p>监视器只关心返回TRUE的文件</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sFileName 文件名称
   //    * @return TRUE：成功<br>FALSE：失败
   //    * @exception FException 应用程序例外
   //    */
   //   public boolean pushFile(String sFileName){
   //      File oFile = new File(sFileName);
   //      sFileName = oFile.getAbsolutePath();
   //      if (!m_oFileList.containsName(sFileName)) {
   //         m_oFileList.setValue(sFileName, Long.toString(oFile.lastModified()));
   //      }
   //      return true;
   //   }
   //
   //   // 查找一个文件
   //   protected boolean searchFile(String sFileName,
   //                                boolean bFireEvent)
   //         throws FException{
   //      File oFile = new File(sFileName);
   //      if (oFile.exists()) {
   //         boolean bHasChanged = false;
   //         sFileName = oFile.getAbsolutePath();
   //         if (!m_oFileList.containsName(sFileName)) {
   //            if (onFilterFile(sFileName)) {
   //               m_oFileList.add(sFileName, Long.toString(oFile.lastModified()));
   //               if (!bFireEvent) {
   //                  onInitializeFile(sFileName);
   //               }
   //               bHasChanged = true;
   //            }
   //         } else {
   //            long lLastModified = FLongUtil.toLong(m_oFileList.value(sFileName),
   //                  0);
   //            if (oFile.lastModified() != lLastModified) {
   //               m_oFileList.setValue(sFileName, Long.toString(oFile
   //                     .lastModified()));
   //               bHasChanged = true;
   //            }
   //         }
   //         if (bHasChanged && bFireEvent) {
   //            onFileChanged(sFileName);
   //         }
   //      }
   //      return true;
   //   }
   //
   //   // 查找指定的文件目录
   //   protected boolean searchFilePath(String sFilePath,
   //                                    boolean bFireEvent)
   //         throws FException{
   //      File oDirectory = new File(sFilePath);
   //      if (oDirectory.isDirectory()) {
   //         File oFile = null;
   //         File[] arFiles = oDirectory.listFiles();
   //         for (int n = 0; n < arFiles.length; n++) {
   //            oFile = arFiles[n];
   //            if (oFile.isDirectory()) {
   //               searchFilePath(oFile.getAbsolutePath(), bFireEvent);
   //            } else {
   //               searchFile(oFile.getAbsolutePath(), bFireEvent);
   //            }
   //         }
   //      }
   //      return true;
   //   }
   //
   //   // 查找指定的文件列表
   //   protected boolean searchFileList(FList oFileList,
   //                                    boolean bFireEvent)
   //         throws FException{
   //      synchronized (this) {
   //         if (oFileList != null) {
   //            //            FComponent oComponent = null;
   //            //            int nCount = oFileList.getCount();
   //            //            for (int n = 0; n < nCount; n++) {
   //            //               oComponent = (FComponent)oFileList.getItem(n);
   //            //               if (oComponent instanceof FPathInfo) {
   //            //                  searchFilePath(
   //            //                        ((FPathInfo)oComponent).getFilePath(), bFireEvent);
   //            //               } else if (oComponent instanceof FFileInfo) {
   //            //                  searchFile(
   //            //                        ((FFileInfo)oComponent).getFileName(), bFireEvent);
   //            //               }
   //            //            }
   //         }
   //         return true;
   //      }
   //   }
   //
   //   /**
   //    * <p>根据设置节点初始化文件列表</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param oConfigNode 设置节点
   //    * @return TRUE：成功<br>FALSE：失败
   //    * @exception FException 应用程序例外
   //    */
   //   public boolean initialize(FXmlNode oConfigNode)
   //         throws FException{
   //      //File oFile = null;
   //      //FXmlNode oNode = null;
   //      //      FPathInfo oPathInfo = null;
   //      //      FFileInfo oFileInfo = null;
   //      //      FXmlNodes oConfigNodes = oConfigNode.getNodes();
   //      //      int nCount = oConfigNodes.getCount();
   //      //      for (int n = 0; n < nCount; n++) {
   //      //         oNode = oConfigNodes.getNode(n);
   //      //         if (FString.equalsIgnoreCase(oNode.getName(), "Path")) {
   //      //            oFile = new File(oNode.getText());
   //      //            if (oFile.isDirectory()) {
   //      //               oPathInfo = new FPathInfo();
   //      //               oPathInfo.setFilePath(oFile.getAbsolutePath());
   //      //               m_oInfoList.push(oPathInfo);
   //      //            }
   //      //         } else if (FString.equalsIgnoreCase(oNode.getName(), "File")) {
   //      //            oFile = new File(oNode.getText());
   //      //            if (oFile.isFile()) {
   //      //               oFileInfo = new FFileInfo();
   //      //               oFileInfo.setFileName(oFile.getAbsolutePath());
   //      //               m_oInfoList.push(oFileInfo);
   //      //            }
   //      //         }
   //      //      }
   //      return true;
   //   }
   //
   //   /**
   //    * <p>查找文件的路径</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sFileName 文件名称
   //    * @return 文件的路径
   //    * @exception FException 应用程序例外
   //    */
   //   public String findFilePath(String sFileName)
   //         throws FException{
   //      File oFile = new File(sFileName);
   //      if (oFile.exists()) {
   //         sFileName = oFile.getAbsolutePath();
   //         //         FComponent oComponent = null;
   //         //         String sFilePath = null;
   //         //         int nCount = m_oInfoList.size();
   //         //         for (int n = 0; n < nCount; n++) {
   //         //            oComponent = (FComponent)m_oInfoList.getItem(n);
   //         //            if (oComponent instanceof FPathInfo) {
   //         //               sFilePath = ((FPathInfo)oComponent).getFilePath();
   //         //               if (sFileName.startsWith(sFilePath)) {
   //         //                  return sFilePath;
   //         //               }
   //         //            }
   //         //         }
   //      }
   //      return null;
   //   }
   //
   //   /**
   //    * <p>查找部分文件名的全路径</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sFileName 部分文件名
   //    * @return 文件名的全路径
   //    * @exception FException 应用程序例外
   //    */
   //   public String findFullFileName(String sFileName)
   //         throws FException{
   //      File oFile = new File(sFileName);
   //      if (oFile.exists()) {
   //         sFileName = oFile.getAbsolutePath();
   //         //         FComponent oComponent = null;
   //         //         int nCount = m_oInfoList.getCount();
   //         //         for (int n = 0; n < nCount; n++) {
   //         //            oComponent = (FComponent)m_oInfoList.getItem(n);
   //         //            if (oComponent instanceof FPathInfo) {
   //         //               return ((FPathInfo)oComponent).getFilePath();
   //         //            }
   //         //         }
   //      }
   //      return null;
   //   }
   //
   //   /**
   //    * <p>初始化监视器</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @return TRUE：成功<br>FALSE：失败
   //    * @exception FException 应用程序例外
   //    */
   //   public void initialize()
   //         throws FException{
   //      searchFileList(m_oInfoList, false);
   //   }
   //
   //   /**
   //    * <p>执行监视器的逻辑</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @return TRUE：成功<br>FALSE：失败
   //    * @exception FException 应用程序例外
   //    */
   //   public void execute()
   //         throws FException{
   //      searchFileList(m_oInfoList, true);
   //   }
}
