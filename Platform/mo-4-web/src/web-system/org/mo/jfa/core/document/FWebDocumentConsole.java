package org.mo.jfa.core.document;

public class FWebDocumentConsole
{
   //   private String m_sDocumentPath = null;
   //
   //   private String m_sHistoryPath = null;
   //
   //   private String m_sWorkPath = null;
   //
   //   private String m_sURI = null;
   //
   //   public String documentPath(){
   //      return configPath() + "root/";
   //   }
   //
   //   private String m_sTemplateIndex = null;
   //
   //   private String m_sTemplateCatalog = null;
   //
   //   public void initializeConfig()
   //         throws FException{
   //      super.initializeConfig();
   //      m_sDocumentPath = configNode().nodeText("DocumentPath");
   //      FFileUtil.makedir(m_sDocumentPath);
   //      m_sHistoryPath = configNode().nodeText("HistoryPath");
   //      FFileUtil.makedir(m_sHistoryPath);
   //      m_sWorkPath = configNode().nodeText("WorkPath");
   //      FFileUtil.makedir(m_sWorkPath);
   //      m_sURI = configNode().nodeText("URI");
   //      m_sTemplateIndex = configNode().findNode("Template").nodeText("Index");
   //      m_sTemplateCatalog = configNode().findNode("Template")
   //            .nodeText("Catalog");
   //   }
   //
   //   public FXmlNode createDirNode(File oFile)
   //         throws FException{
   //      String sItemPath = oFile.getAbsolutePath().substring(
   //            documentPath().length());
   //      sItemPath = FStringUtil.replace(sItemPath, '\\', '/');
   //      FXmlNode oNode = super.createDirNode(oFile);
   //      oNode.setAttribute("type", "ctl.doc.dir");
   //      oNode.setAttribute("path", sItemPath);
   //      oNode.setAttribute("caption", oFile.getName());
   //      oNode.setAttribute("icon", "sys.ctl.lf");
   //      oNode.setAttribute("child", FBooleanUtil
   //            .toString(oFile.list().length > 0));
   //      return oNode;
   //   }
   //
   //   public FXmlNode createFileNode(File oFile)
   //         throws FException{
   //      String sItemPath = oFile.getAbsolutePath().substring(
   //            documentPath().length());
   //      sItemPath = FStringUtil.replace(sItemPath, '\\', '/');
   //      if (sItemPath.endsWith(".zip")) {
   //         sItemPath = sItemPath.substring(0, sItemPath.length() - 4);
   //      }
   //      FXmlNode oNode = super.createFileNode(oFile);
   //      oNode.setAttribute("type", "ctl.doc.itm");
   //      oNode.setAttribute("path", sItemPath);
   //      oNode.setAttribute("caption", FFileUtil.fileShortName(oFile.getName()));
   //      oNode.setAttribute("icon", "wfa.doc.doc");
   //      oNode.setAttribute("child", "N");
   //      return oNode;
   //   }
   //
   //   public void parse(IWebContext iContext,
   //                     String sDocumentName)
   //         throws FException{
   //      String sTempPath = FFileUtil.makeFileName(m_sWorkPath, FFileUtil
   //            .fileName(sDocumentName));
   //      FZipUtil.extract(sDocumentName, sTempPath);
   //      String sConfigName = FFileUtil.makeFileName(sTempPath, "config.xml");
   //      FDocumentParser oParser = new FDocumentParser();
   //      oParser.parse(sConfigName);
   //      // Procedure
   //      FStringList oParams = new FStringList();
   //      oParams.setValue("node_id", oParser.nodeId());
   //      oParams.setValue("version_id", oParser.version());
   //      FSqlProcedure oProcedure = new FSqlProcedure(
   //            "DOC_NODE_DOCUMENT_DI.Page_Insert");
   //      oProcedure.createParameter("logic_", null, ISqlType.STRING,
   //            ISqlDirection.INPUT);
   //      oProcedure.createParameter("params_", oParams.pack().toString(),
   //            ISqlType.STRING, ISqlDirection.INPUT_OUTPUT);
   //      iContext.activeConnection().execute(oProcedure);
   //      // Copy
   //      String sHisName = FFileUtil.makeFileName(m_sHistoryPath, oParser
   //            .documentId(), "zip");
   //      String sDocPath = FFileUtil.makeFileName(m_sDocumentPath, oParser
   //            .documentId());
   //      FFileUtil.copyFile(sDocumentName, sHisName);
   //      FZipUtil.extract(sHisName, sDocPath);
   //      FFileUtil.rmdirs(sTempPath);
   //      // Make Index
   //      String sIndex = FFileUtil.loadFromFileAsString(m_sTemplateIndex);
   //      FString sCatalog = FFileUtil.loadFromFileAsFString(m_sTemplateCatalog);
   //      sIndex = FStringUtil.parse(sIndex, "context_path", iContext
   //            .contextPath());
   //      sIndex = FStringUtil.parse(sIndex, "home", oParser.homeUri());
   //      String sIndexFile = FFileUtil.makeFileName(sDocPath, "Index.html");
   //      FFileUtil.saveToFile(sIndexFile, sIndex);
   //   }
   //
   //   public FXmlNodes listPath(String sPath)
   //         throws FException{
   //      // 获得当前路径
   //      if (!FStringUtil.isEmpty(sPath)) {
   //         sPath = documentPath() + sPath;
   //      } else {
   //         sPath = documentPath();
   //      }
   //      sPath = FFileUtil.formatDirectoryName(sPath, true);
   //      File oDir = new File(sPath);
   //      FXmlNodes oNodes = new FXmlNodes();
   //      if (oDir.isDirectory()) {
   //         // 查询路径下的设置文件
   //         FXmlNodes oDirList = new FXmlNodes();
   //         FStringArray oConfigIds = new FStringArray();
   //         String sCatalogFile = sPath + "_catalog_.xml";
   //         if (FFileUtil.exists(sCatalogFile)) {
   //            FXmlDocument oDocument = new FXmlDocument();
   //            oDocument.loadFromFile(sCatalogFile);
   //            FXmlNode oTvNode = null;
   //            String sId = null;
   //            for (FXmlNode oNode : oDocument.rootNode().nodes()) {
   //               if (oNode.name().equalsIgnoreCase("Node")) {
   //                  sId = oNode.attribute("id");
   //                  oConfigIds.push(sId);
   //                  oTvNode = new FStdTreeNode("ctl.doc.dir", oNode
   //                        .attribute("label"), true, "sys.ctl.lf");
   //                  oTvNode.setAttribute("path", sPath.substring(documentPath()
   //                        .length())
   //                        + "/" + sId);
   //                  oDirList.push(oTvNode);
   //               }
   //            }
   //         }
   //         for (File oFile : oDir.listFiles()) {
   //            if (oFile.isDirectory()) {
   //               if (!oConfigIds.contains(FFileUtil
   //                     .fileShortName(oFile.getName()))) {
   //                  oDirList.push(createDirNode(oFile));
   //               }
   //            }
   //         }
   //         // 查询路径下的所有文件
   //         FXmlNodes oFileList = new FXmlNodes();
   //         for (File oFile : oDir.listFiles()) {
   //            if (oFile.isFile()) {
   //               if (FFileUtil.isExtension(oFile, "zip")) {
   //                  oFileList.push(createFileNode(oFile));
   //               }
   //            }
   //         }
   //         oNodes.append(oDirList);
   //         oNodes.append(oFileList);
   //      }
   //      return oNodes;
   //   }
   //
}
