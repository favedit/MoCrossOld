/*
 * @(#)FAbstractConfigConsole.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.eng.common;

import org.mo.com.lang.FError;
import org.mo.com.lang.RString;
import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.face.ALink;
import org.mo.core.aop.face.AProperty;

/**
 * <p>配置控制台</p>
 * <p>1. 提供操作指定目录下XML节点的功能</p>
 * <p>2. 读取和存储设置文件</p>
 * 
 * @author ALEX
 * @version 1.00 - 2005/10/25
 */
public abstract class FAbstractConfigConsole
{
   // 名称后缀
   @AProperty
   protected String _aftFix;

   @ALink
   protected FAbstractConfigConsole _dataConsole;

   // 类型过滤
   @AProperty
   protected String _filter;

   // 名称前缀
   @AProperty
   protected String _preFix;

   // 名称分隔符
   protected char _separator = '|';

   /**
    * <p>获得名称后缀</p>
    * <p>create date:2005/10/25</p>
    *
    * @return 名称后缀
    * @exception FError 应用程序例外
    */
   public String configAftFix(){
      return _aftFix;
   }

   /**
    * <p>获得类型过滤</p>
    * <p>create date:2005/10/25</p>
    *
    * @return 类型过滤
    * @exception FError 应用程序例外
    */
   public String configFilter(){
      return _filter;
   }

   /**
    * <p>获得名称前缀</p>
    * <p>create date:2005/10/25</p>
    *
    * @return 名称前缀
    * @exception FError 应用程序例外
    */
   public String configPreFix(){
      return _preFix;
   }

   protected FXmlNode findNode(String type,
                               String path,
                               String name,
                               String value){
      FXmlNode node = null;
      //FXmlDataset dataset = _dataConsole.find(type, path);
      //if(dataset != null){
      //         node = dataset.find(name, value);
      //      }
      return node;
   }

   // 获得项目内部参数
   protected String innerSource(String source){
      if(source != null){
         int prePos = 0;
         int aftPos = source.length();
         if(_preFix != null && source.startsWith(_preFix)){
            prePos = _preFix.length();
         }
         if(_aftFix != null && source.endsWith(_aftFix)){
            aftPos -= _aftFix.length();
         }
         if(prePos > 0 || aftPos > 0){
            return source.substring(prePos, aftPos);
         }
      }
      return source;
   }

   // 判断当前字符串是否为指定格式
   protected boolean isSource(String source){
      if(source != null){
         if(_preFix == null && _aftFix == null){
            return true;
         }else if(_preFix != null && _aftFix == null){
            return source.startsWith(_preFix);
         }else if(_preFix == null && _aftFix != null){
            return source.endsWith(_aftFix);
         }else if(_preFix != null && _aftFix != null){
            return (source.startsWith(_preFix) && source.endsWith(_aftFix));
         }
      }
      return false;
   }

   //   protected FXmlDataset findDataset(String type,
   //                                     String path){
   //      return _dataConsole.find(type, path);
   //   }
   // 分解项目参数
   protected String[] splitSource(String source){
      String[] items = RString.split(innerSource(source), _separator);
      items[0] = items[0].toLowerCase();
      if(items.length > 2){
         items[1] = items[1].toLowerCase();
      }
      return items;
   }
   //   /**
   //    * <p>获得设置路径</p>
   //    * <p>create date:2005/10/25</p>
   //    *
   //    * @return 设置路径
   //    * @exception FException 应用程序例外
   //    */
   //   public String configDirectory(){
   //      //return _directory;
   //   }
   //   /**
   //    * <p>清空所有内容</p>
   //    * <p>create date:2005/10/25</p>
   //    *
   //    * @exception FException 应用程序例外
   //    */
   //   public void clear(){
   //      synchronized(m_oDocumentMap){
   //         m_oDocumentMap.clear();
   //      }
   //      synchronized(m_oNodeMapMap){
   //         m_oNodeMapMap.clear();
   //      }
   //      m_oMonitor.clear();
   //   }
   //
   //   /**
   //    * <p>获得名称分隔符</p>
   //    * <p>create date:2005/10/25</p>
   //    *
   //    * @return 名称分隔符
   //    * @exception FException 应用程序例外
   //    */
   //   public char configSeparator(){
   //      return _separator;
   //   }
   //
   //   /**
   //    * <p>创建指定节点路径的设置文档</p>
   //    * <p>create date:2005/10/25</p>
   //    *
   //    * @param sNodePath 节点路径
   //    * @return 设置文档
   //    * @exception FException 应用程序例外
   //    */
   //   public FXmlDocument createDocument(String sNodePath)
   //         throws FException{
   //      if(FStringUtil.isEmpty(sNodePath)){
   //         throw new FFatalException(this, "createDocument", "Node path is empty");
   //      }
   //      String sFileName = makeFileName(sNodePath);
   //      FXmlDocument oDocument = new FXmlDocument();
   //      oDocument.setFileName(sFileName);
   //      synchronized(m_oDocumentMap){
   //         m_oDocumentMap.put(sNodePath, oDocument);
   //      }
   //      synchronized(m_oNodeMapMap){
   //         m_oNodeMapMap.put(sNodePath, new FXmlNodeMap());
   //      }
   //      m_oMonitor.push(sFileName);
   //      if(FLogger.canDebug()){
   //         FString sDebug = new FString();
   //         sDebug.append("Build empty config [ ");
   //         sDebug.append(sNodePath);
   //         sDebug.append(" ] ");
   //         sDebug.append(sFileName);
   //         FLogger.debug(this, "createDocument", sDebug);
   //      }
   //      return oDocument;
   //
   //   }
   //
   //   /**
   //    * <p>删除指定节点路径的设置文档</p>
   //    * <p>create date:2005/10/25</p>
   //    *
   //    * @param sNodePath 节点路径
   //    * @exception FException 应用程序例外
   //    */
   //   public void deleteDocument(String sNodePath)
   //         throws FException{
   //      removeNode(sNodePath);
   //      String sFileName = makeFileName(sNodePath);
   //      m_oMonitor.remove(sFileName);
   //      if((new File(sFileName)).isFile()){
   //         FFileUtil.delete(sFileName);
   //      }
   //      String sFilePath = makeFilePath(sNodePath);
   //      File oFile = new File(sFilePath);
   //      if(oFile.isDirectory()){
   //         if(oFile.list().length == 0){
   //            oFile.delete();
   //         }
   //      }
   //   }
   //
   //   /**
   //    * <p>响应发生变动的文档</p>
   //    * <p>create date:2005/11/07</p>
   //    *
   //    * @param sFileName 文件名称
   //    * @exception FException 应用程序例外
   //    */
   //   public void doFileChange(String sFileName)
   //         throws FException{
   //      loadDocumentFile(sFileName);
   //   }
   //
   //   /**
   //    * <p>查找指定节点路径的设置文档</p>
   //    * <p>create date:2005/10/25</p>
   //    *
   //    * @param sNodePath 节点路径
   //    * @return 设置文档
   //    * @exception FException 应用程序例外
   //    */
   //   public FXmlDocument findDocument(String sNodePath)
   //         throws FException{
   //      try{
   //         if(!m_oDocumentMap.containsName(sNodePath)){
   //            loadDocument(sNodePath);
   //         }
   //         return m_oDocumentMap.value(sNodePath);
   //      }catch(Exception oException){
   //         throw new FFatalException(this, "findDocument", oException, "[" + sNodePath + "]");
   //      }
   //   }
   //
   //   /**
   //    * <p>查找指定节点路径的设置节点</p>
   //    * <p>create date:2005/10/25</p>
   //    *
   //    * @param sNodePath 节点路径
   //    * @return 设置节点
   //    * @exception FException 应用程序例外
   //    */
   //   public FXmlNode findDocumentNode(String sNodePath)
   //         throws FException{
   //      return findDocument(sNodePath).rootNode();
   //   }
   //
   //   /**
   //    * <p>查找指定路径的设置节点</p>
   //    * <p>create date:2005/10/25</p>
   //    *
   //    * @param sPathName 路径
   //    * @return 设置节点
   //    * @exception FException 应用程序例外
   //    */
   //   protected FXmlNode findNode(String sPathName)
   //         throws FException{
   //      return findNode(splitSource(sPathName));
   //   }
   //
   //   /**
   //    * <p>查找指定路径和项目的设置节点</p>
   //    * <p>create date:2005/10/25</p>
   //    *
   //    * @param sPath 路径
   //    * @param sItem 项目
   //    * @return 设置节点
   //    * @exception FException 应用程序例外
   //    */
   //   protected FXmlNode findNode(String sPath,
   //                               String sItem)
   //         throws FException{
   //      try{
   //         // 检查请求的属性是否可以从缓冲内获得
   //         FXmlNodeMap oNodeMap = null;
   //         if(!m_oNodeMapMap.containsName(sPath)){
   //            loadDocument(sPath);
   //         }
   //         oNodeMap = m_oNodeMapMap.value(sPath);
   //         if(oNodeMap.containsName(sItem)){
   //            return oNodeMap.value(sItem);
   //         }
   //         return null;
   //      }catch(Exception oException){
   //         FString sMsg = new FString();
   //         sMsg.append("Path[");
   //         sMsg.append(sPath);
   //         sMsg.append("]");
   //         throw new FFatalException(this, "findNode", oException, sMsg);
   //      }
   //   }
   //
   //   /**
   //    * <p>查找指定路径和名称及属性名称内容的设置节点</p>
   //    * <p>create date:2005/10/25</p>
   //    *
   //    * @param sPath 路径
   //    * @param sName 名称
   //    * @param sAttrName 属性名称
   //    * @param sAttrValue 属性内容
   //    * @return 设置节点
   //    * @exception FException 应用程序例外
   //    */
   //   protected FXmlNode findNode(String sPath,
   //                               String sName,
   //                               String sAttrName,
   //                               String sAttrValue)
   //         throws FException{
   //      FXmlDocument oDoc = findDocument(sPath);
   //      return oDoc.rootNode().findNode(sName, sAttrName, sAttrValue);
   //   }
   //
   //   // 查找指定路径和项目的设置节点
   //   protected FXmlNode findNode(String[] arPath)
   //         throws FException{
   //      try{
   //         // 分析入口参数，转换为相应的格式
   //         if(arPath.length >= 2){
   //            // 检查请求的属性是否可以从缓冲内获得
   //            FXmlNodeMap oNodeMap = null;
   //            if(!m_oNodeMapMap.containsName(arPath[0])){
   //               loadDocument(arPath[0]);
   //            }
   //            oNodeMap = m_oNodeMapMap.value(arPath[0]);
   //            String sNode = arPath[1].toLowerCase();
   //            if(oNodeMap.containsName(sNode)){
   //               return oNodeMap.value(sNode);
   //            }
   //         }
   //         return null;
   //      }catch(Exception oException){
   //         FString sMsg = new FString();
   //         sMsg.append("PathName[");
   //         for(String sPath : arPath){
   //            sMsg.append(sPath);
   //            sMsg.append(',');
   //         }
   //         sMsg.append("]");
   //         throw new FFatalException(this, "findNode", oException, sMsg);
   //      }
   //   }
   //
   //   /**
   //    * <p>设置初始化的参数</p>
   //    * <p>create date:2005/10/25</p>
   //    *
   //    * @exception FException 应用程序例外
   //    */
   //   public void initializeConfig()
   //         throws FException{
   //      _filter = configNode().nodeText("Filter");
   //      String sSeparator = configNode().nodeText("Separator");
   //      if(!FStringUtil.isEmpty(sSeparator)){
   //         _separator = configNode().nodeText("Separator").charAt(0);
   //      }
   //      _preFix = configNode().nodeText("PreFix");
   //      if(FStringUtil.isEmpty(_preFix)){
   //         _preFix = null;
   //      }
   //      _aftFix = configNode().nodeText("AftFix");
   //      if(FStringUtil.isEmpty(_aftFix)){
   //         _aftFix = null;
   //      }
   //      _directory = FFileUtil.formatDirectory(configNode().nodeText("Directory"), true);
   //   }
   //
   //   /**
   //    * <p>加载指定节点路径的设置文档</p>
   //    * <p>create date:2005/10/25</p>
   //    *
   //    * @param sNodePath 节点路径
   //    * @exception FException 应用程序例外
   //    */
   //   public void loadDocument(String sNodePath)
   //         throws FException{
   //      loadDocumentFile(makeFileName(sNodePath));
   //   }
   //
   //   /**
   //    * <p>加载指定文件路径的设置文档</p>
   //    * <p>create date:2005/10/25</p>
   //    *
   //    * @param sFileName 文件路径
   //    * @exception FException 应用程序例外
   //    */
   //   public void loadDocumentFile(String sFileName)
   //         throws FException{
   //      // 检查是否为文件
   //      if(!FFileUtil.isFile(sFileName)){
   //         FString sMsg = new FString();
   //         sMsg.append("File is not exist. [");
   //         sMsg.append(sFileName);
   //         sMsg.append("]");
   //         throw new FFatalException(this, "loadDocument", sMsg);
   //      }
   //      // 获得节点信息
   //      String sNodePath = makeNodePath(sFileName);
   //      FXmlDocument oDocument = new FXmlDocument(sFileName);
   //      m_oDocumentMap.put(sNodePath, oDocument);
   //      FXmlNodeMap oNodeMap = FXmlUtil.makeNodeMap(oDocument.rootNode());
   //      synchronized(m_oNodeMapMap){
   //         m_oNodeMapMap.put(sNodePath, oNodeMap);
   //      }
   //      m_oMonitor.push(sFileName);
   //      if(FLogger.canDebug()){
   //         FString sDebug = new FString();
   //         sDebug.append("Build config [ ");
   //         sDebug.append(sNodePath);
   //         sDebug.append(" ] ");
   //         sDebug.append(sFileName.substring(_directory.length()));
   //         FLogger.debug(this, "loadDocument", sDebug);
   //      }
   //   }
   //
   //   // 根据节点路径建立文件名称
   //   protected String makeFileName(String sNodePath){
   //      FString sResult = new FString();
   //      sResult.append(_directory);
   //      sResult.append(FStringUtil.replace(sNodePath, '.', '/'));
   //      sResult.append(_filter);
   //      return sResult.toString();
   //   }
   //
   //   // 根据节点路径建立文件路径
   //   protected String makeFilePath(String sNodePath){
   //      return _directory + FStringUtil.replace(sNodePath, '.', '/');
   //   }
   //
   //   // 根据文件名称建立节点路径
   //   protected String makeNodePath(String sFileName){
   //      FString sNodePath = new FString();
   //      if(sFileName.startsWith(_directory)){
   //         int nStart = _directory.length();
   //         int nEnd = sFileName.length() - _filter.length();
   //         sNodePath.assign(sFileName.substring(nStart, nEnd).toLowerCase());
   //         sNodePath.replace('/', '.');
   //         sNodePath.replace('\\', '.');
   //      }
   //      return sNodePath.toString();
   //   }
   //
   //   // 监视器保存文件信息
   //   protected void monitorSaveDocument(String sFileName)
   //         throws FException{
   //      String sNodePath = makeNodePath(sFileName);
   //      m_oDocumentMap.value(sNodePath).saveToFile();
   //      if(FLogger.canDebug()){
   //         FString sDebug = new FString();
   //         sDebug.append("save document [");
   //         sDebug.append(sNodePath);
   //         sDebug.append(" ] ");
   //         sDebug.append(sFileName.substring(_directory.length()));
   //         FLogger.debug(this, "monitorSaveDocument", sDebug);
   //      }
   //   }
   //
   //   public void removeDocument(String sNodePath)
   //         throws FException{
   //      removeNode(sNodePath);
   //   }
   //
   //   /**
   //    * <p>移除指定文件名称的设置文档</p>
   //    * <p>create date:2005/10/25</p>
   //    *
   //    * @param sFileName 文件名称
   //    * @exception FException 应用程序例外
   //    */
   //   public void removeDocumentFile(String sFileName)
   //         throws FException{
   //      removeNode(makeNodePath(sFileName));
   //   }
   //
   //   /**
   //    * <p>移除指定节点路径的设置节点</p>
   //    * <p>create date:2005/10/25</p>
   //    *
   //    * @param sNodePath 节点路径
   //    * @exception FException 应用程序例外
   //    */
   //   public void removeNode(String sNodePath)
   //         throws FException{
   //      synchronized(m_oDocumentMap){
   //         if(m_oDocumentMap.containsName(sNodePath)){
   //            m_oDocumentMap.remove(sNodePath);
   //         }
   //      }
   //      synchronized(m_oNodeMapMap){
   //         if(m_oNodeMapMap.containsName(sNodePath)){
   //            m_oNodeMapMap.remove(sNodePath);
   //         }
   //      }
   //      if(FLogger.canDebug()){
   //         FString sDebug = new FString();
   //         sDebug.append("Remove node [");
   //         sDebug.append(sNodePath);
   //         sDebug.append("]");
   //         FLogger.debug(this, "removeNode", sDebug);
   //      }
   //   }
   //
   //   /**
   //    * <p>保存指定的设置文档</p>
   //    * <p>create date:2005/10/25</p>
   //    *
   //    * @param oDocument 设置文档
   //    * @exception FException 应用程序例外
   //    */
   //   public void saveDocument(FXmlDocument oDocument)
   //         throws FException{
   //      saveDocument(oDocument, false);
   //   }
   //
   //   /**
   //    * <p>保存指定的设置文档</p>
   //    * <p>create date:2005/10/25</p>
   //    *
   //    * @param oDocument 设置文档
   //    * @exception FException 应用程序例外
   //    */
   //   public void saveDocument(FXmlDocument oDocument,
   //                            boolean bImmediately)
   //         throws FException{
   //      if(bImmediately){
   //         monitorSaveDocument(oDocument.fileName());
   //      }else{
   //         m_oMonitor.save(oDocument.fileName());
   //      }
   //   }
   //
   //   /**
   //    * <p>保存指定节点路径的设置文档</p>
   //    * <p>create date:2005/10/25</p>
   //    *
   //    * @param sNodePath 节点路径
   //    * @exception FException 应用程序例外
   //    */
   //   public void saveDocument(String sNodePath)
   //         throws FException{
   //      m_oMonitor.save(m_oDocumentMap.value(sNodePath).fileName());
   //   }
   //
   //   /**
   //    * <p>保存指定文件名称的设置文档</p>
   //    * <p>create date:2005/10/25</p>
   //    *
   //    * @param sFileName 文件名称
   //    * @exception FException 应用程序例外
   //    */
   //   public void saveDocumentFile(String sFileName)
   //         throws FException{
   //      m_oMonitor.save(sFileName);
   //   }
   //
   //   /**
   //    * <p>同步指定节点路径的设置文档</p>
   //    * <p>create date:2005/10/25</p>
   //    *
   //    * @param sNodePath 节点路径
   //    * @return 设置文档
   //    * @exception FException 应用程序例外
   //    */
   //   public FXmlDocument syncDocument(String sNodePath)
   //         throws FException{
   //      if(!m_oDocumentMap.containsName(sNodePath)){
   //         String sFileName = makeFileName(sNodePath);
   //         if(FFileUtil.isFile(sFileName)){
   //            loadDocument(sNodePath);
   //         }else{
   //            createDocument(sNodePath);
   //         }
   //      }
   //      return m_oDocumentMap.value(sNodePath);
   //   }
}
