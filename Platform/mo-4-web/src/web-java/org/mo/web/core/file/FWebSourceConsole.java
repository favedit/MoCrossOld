package org.mo.web.core.file;

//public class FWebSourceConsole extends FDirectoryConsole {
public class FWebSourceConsole
{

   //   private String m_sHomePath = null;
   //
   //   public void initializeConfig() throws FException {
   //      super.initializeConfig();
   //      m_sHomePath = FFileUtil.formatDirectoryName(configNode().nodeText(
   //            "HomePath"), true);
   //   }
   //
   //   public String makeFileName(String sFileName) throws FException {
   //      return FFileUtil.formatFileName(m_sHomePath + sFileName);
   //   }
   //
   //   public FString makeFileText(String sFileName) throws FException {
   //      String sFileFullName = FFileUtil.formatFileName(m_sHomePath + sFileName);
   //      return FFileUtil.loadFromFileAsFString(sFileFullName);
   //   }
   //
   //   public FString makeFileSource(String sFileName) throws FException {
   //      String sFileFullName = FFileUtil.formatFileName(m_sHomePath + sFileName);
   //      String sExtension = FFileUtil.extension(sFileName);
   //      FString sSource = FFileUtil.loadFromFileAsFString(sFileFullName);
   //      return FSyntaxManager.instance().format(sExtension, sSource);
   //   }
   //
   //   public FXmlNodes listPath(String sPath) throws FException {
   //      // 获得当前路径
   //      sPath = FFileUtil.makeDirectoryName(m_sHomePath, sPath);
   //      sPath = FFileUtil.formatDirectoryName(sPath, true);
   //      // 查询路径下的所有文件
   //      FXmlNodes oNodes = new FXmlNodes();
   //      File oDir = new File(sPath);
   //      if (oDir.isDirectory()) {
   //         FStringList oParams = new FStringList();
   //         // ------------------------------------------------------------
   //         String sFileName = null;
   //         String sNodeCaption = null;
   //         String sPackageName = null;
   //         String[] sSubFiles = null;
   //         int nSubFileCount = 0;
   //         String sSubFileName = null;
   //         // ------------------------------------------------------------
   //         FXmlNodes oDirList = new FXmlNodes();
   //         FXmlNodes oFileList = new FXmlNodes();
   //         // ------------------------------------------------------------
   //         for (File oFile : oDir.listFiles()) {
   //            if (oFile.isDirectory()) {
   //               FXmlNode oDirNode = new FXmlNode("Dir");
   //               oDirNode.setAttribute("type", "src.web.dir");
   //               oDirNode.setAttribute("path", oFile.getAbsolutePath().substring(
   //                     m_sHomePath.length()).replace('\\', '/'));
   //               oDirNode.setAttribute("caption", oFile.getName());
   //               oDirNode.setAttribute("icon", "sys.floder");
   //               oDirNode.setAttribute("child", FBooleanUtil.toString(oFile
   //                     .list().length > 0));
   //               oDirList.push(oDirNode);
   //            }
   //            if (oFile.isFile()) {
   //               FXmlNode oFileNode = new FXmlNode("File");
   //               oFileNode.setAttribute("type", "src.web.file");
   //               oFileNode.setAttribute("file", oFile.getAbsolutePath()
   //                     .substring(m_sHomePath.length()).replace('\\', '/'));
   //               oFileNode.setAttribute("caption", oFile.getName());
   //               oFileNode.setAttribute("icon", "sys.item");
   //               oFileNode.setAttribute("child", "N");
   //               oFileList.push(oFileNode);
   //            }
   //         }
   //         oDirList.sortByAttribute("caption");
   //         oFileList.sortByAttribute("caption");
   //         oNodes.append(oDirList);
   //         oNodes.append(oFileList);
   //      }
   //      return oNodes;
   //   }
}
