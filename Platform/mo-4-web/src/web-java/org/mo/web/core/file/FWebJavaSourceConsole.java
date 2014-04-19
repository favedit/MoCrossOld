/*
 * Created on 2005/01/26
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package org.mo.web.core.file;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
//public class FWebJavaSourceConsole extends FDirectoryConsole{
public class FWebJavaSourceConsole
{

   //   private String m_sClassPath = null;
   //
   //   private String m_sSourcePath = null;
   //
   //   private String m_sHelpPath = null;
   //
   //   public FString loadSource(String sClassName)
   //         throws FException{
   //      String sFilePath = FFileUtil.makeFileName(m_sSourcePath, FStringUtil
   //            .replace(sClassName, ".", "/"), "java");
   //      if (FFileUtil.exists(sFilePath)) {
   //         return new FString(FFileUtil.loadFromFileAsString(sFilePath, "UTF-8"));
   //      }
   //      return new FString("[ Not Find Source File ]");
   //   }
   //
   //   public FString loadHelp(String sClassName)
   //         throws FException{
   //      String sFilePath = FFileUtil.makeFileName(m_sHelpPath, FStringUtil
   //            .replace(sClassName, ".", "/"), "htm");
   //      if (FFileUtil.exists(sFilePath)) {
   //         return new FString(FFileUtil.loadFromFileAsString(sFilePath, "GB2312"));
   //      }
   //      return new FString("[ Not Find Help File ]");
   //   }
   //
   //   public String makeConstructorDisplay(Constructor oConstructor){
   //      String sParameters = "";
   //      Class[] oParameters = oConstructor.getParameterTypes();
   //      Class oParameter = null;
   //      int nLength = oParameters.length;
   //      for (int n = 0; n < nLength; n++) {
   //         oParameter = oParameters[n];
   //         if (!FStringUtil.isEmpty(sParameters)) {
   //            sParameters += ", ";
   //         }
   //         sParameters += oParameter.getName();
   //      }
   //      // ------------------------------------------------------------
   //      String sExceptions = "";
   //      Class[] oExceptions = oConstructor.getExceptionTypes();
   //      Class oException = null;
   //      nLength = oExceptions.length;
   //      for (int n = 0; n < nLength; n++) {
   //         oException = oExceptions[n];
   //         if (!FStringUtil.isEmpty(sExceptions)) {
   //            sExceptions += ", ";
   //         }
   //         sExceptions += oException.getName();
   //      }
   //      // ------------------------------------------------------------
   //      String sDisplay = "";
   //      if (FStringUtil.isEmpty(sParameters)) {
   //         sDisplay += "<FONT color='#0000E0'>" + oConstructor.getName()
   //               + "</FONT>()";
   //      } else {
   //         sDisplay += "<FONT color='#0000E0'>" + oConstructor.getName()
   //               + "</FONT>(" + sParameters + ")";
   //      }
   //      if (!FStringUtil.isEmpty(sExceptions)) {
   //         sDisplay += " throws <FONT color='#FF4444'>" + sExceptions + "</FONT>";
   //      }
   //      return sDisplay;
   //   }
   //
   //   public String makeMethodDisplay(Method oMethod){
   //      String sParameters = "";
   //      Class[] oParameters = oMethod.getParameterTypes();
   //      Class oParameter = null;
   //      Class oReturn = oMethod.getReturnType();
   //      int nLength = oParameters.length;
   //      for (int n = 0; n < nLength; n++) {
   //         oParameter = oParameters[n];
   //         if (!FStringUtil.isEmpty(sParameters)) {
   //            sParameters += ", ";
   //         }
   //         sParameters += oParameter.getName();
   //      }
   //      // ------------------------------------------------------------
   //      String sExceptions = "";
   //      Class[] oExceptions = oMethod.getExceptionTypes();
   //      Class oException = null;
   //      nLength = oExceptions.length;
   //      for (int n = 0; n < nLength; n++) {
   //         oException = oExceptions[n];
   //         if (!FStringUtil.isEmpty(sExceptions)) {
   //            sExceptions += ", ";
   //         }
   //         sExceptions += oException.getName();
   //      }
   //      // ------------------------------------------------------------
   //      String sDisplay = "";
   //      if (FStringUtil.isEmpty(sParameters)) {
   //         sDisplay += oReturn.getName() + " <FONT color='#0000E0'>"
   //               + oMethod.getName() + "</FONT>()";
   //      } else {
   //         sDisplay += oReturn.getName() + " <FONT color='#0000E0'>"
   //               + oMethod.getName() + "</FONT>(" + sParameters + ")";
   //      }
   //      if (!FStringUtil.isEmpty(sExceptions)) {
   //         sDisplay += " throws <FONT color='#FF4444'>" + sExceptions + "</FONT>";
   //      }
   //      return sDisplay;
   //   }
   //
   //   public void makeClassInfo(IWebContext oContext,
   //                             FString sInheritHtml,
   //                             FString sClassHtml,
   //                             String sClassName)
   //         throws FException{
   //      if (sClassName.startsWith(".")) {
   //         sClassName = sClassName.substring(1);
   //      }
   //      Class oClass = FClassUtil.findClass(sClassName);
   //      FContainer oInheritContainer = FClassUtil.getInheritMethods(oClass);
   //      // ------------------------------------------------------------
   //      String sInheritClassName = null;
   //      Class oInheritClass = null;
   //      int nClassCount = oInheritContainer.size();
   //      for (int nClass = nClassCount - 1; nClass >= 0; nClass--) {
   //         sInheritClassName = oInheritContainer.name(nClass);
   //         if (nClass == (nClassCount - 1)) {
   //            sInheritHtml.append(FWebResImages.makeIcon(oContext.contextPath(),
   //                  "cls.cls")
   //                  + "&nbsp;<A href='#"
   //                  + sInheritClassName
   //                  + "'>"
   //                  + sInheritClassName + "</A><br>");
   //         } else {
   //            sInheritHtml.append(FStringUtil.repeat(FWebResImages.makeIcon(
   //                  oContext.contextPath(), FWebResImages.NONE, 16, 16),
   //                  2 * (nClassCount - nClass - 2))
   //                  + FWebResImages.makeIcon(oContext.contextPath(), "cls.lv")
   //                  + FWebResImages.makeIcon(oContext.contextPath(), "cls.h")
   //                  + FWebResImages.makeIcon(oContext.contextPath(), "cls.cls")
   //                  + "&nbsp;<A href='#"
   //                  + sInheritClassName
   //                  + "'>"
   //                  + sInheritClassName + "</A><br>");
   //         }
   //      }
   //      // ------------------------------------------------------------
   //      Field oField = null;
   //      Field[] arFields = oClass.getFields();
   //      sClassHtml
   //            .append("<TABLE width='100%' border='0' cellspacing='0' cellpadding='2'>");
   //      for (int n = 0; n < arFields.length; n++) {
   //         oField = arFields[n];
   //         sClassHtml.append("<TR><TD nowrap>"
   //               + FWebResImages.makeIcon(oContext.contextPath(), "cls.var")
   //               + "&nbsp;" + oField.getName() + " = "
   //               + FClassUtil.invokeFieldAsString(oClass, oField.getName())
   //               + "</TD></TR>");
   //      }
   //      sClassHtml.append("</TABLE>");
   //      // ------------------------------------------------------------
   //      sClassHtml
   //            .append("<TABLE width='100%' border='0' cellspacing='0' cellpadding='2'>");
   //      FContainer oClassContainer = null;
   //      for (int nClass = 0; nClass < nClassCount; nClass++) {
   //         sInheritClassName = oInheritContainer.name(nClass);
   //         oInheritClass = FClassUtil.findClass(sInheritClassName);
   //         if (oInheritClass.isInterface()) {
   //            sClassHtml
   //                  .append("<TR bgcolor='#E0E0E0'><TD colspan='2' width='120px' nowrap>"
   //                        + FWebResImages.makeIcon(oContext.contextPath(),
   //                              "cls.cls")
   //                        + "&nbsp;<I><A name='"
   //                        + oInheritClass.getName()
   //                        + "'>"
   //                        + oInheritClass.getName() + "</A></I></TD></TR>");
   //         } else {
   //            sClassHtml
   //                  .append("<TR bgcolor='#E0E0E0'><TD colspan='2' width='120px' nowrap>"
   //                        + FWebResImages.makeIcon(oContext.contextPath(),
   //                              "cls.cls")
   //                        + "&nbsp;<A name='"
   //                        + oInheritClass.getName()
   //                        + "'>"
   //                        + oInheritClass.getName() + "</A></TD></TR>");
   //         }
   //         oClassContainer = (FContainer) oInheritContainer.value(nClass);
   //         Object oMethodItem = null;
   //         Method oMethod = null;
   //         Constructor oConstructor = null;
   //         int nMethodCount = oClassContainer.size();
   //         for (int nMethod = 0; nMethod < nMethodCount; nMethod++) {
   //            oMethodItem = oClassContainer.value(nMethod);
   //            if (oMethodItem instanceof Constructor) {
   //               oConstructor = (Constructor) oMethodItem;
   //               sClassHtml.append("<TR><TD nowrap width='120px'><B>"
   //                     + FWebResImages.makeIcon(oContext.contextPath(),
   //                           "cls.func") + "&nbsp;" + oConstructor.getName()
   //                     + "</B></TD><TD><B>"
   //                     + makeConstructorDisplay(oConstructor) + "</B></TD></TR>");
   //            } else if (oMethodItem instanceof Method) {
   //               oMethod = (Method) oMethodItem;
   //               sClassHtml.append("<TR><TD nowrap width='120px'>"
   //                     + FWebResImages.makeIcon(oContext.contextPath(),
   //                           "cls.func") + "&nbsp;" + oMethod.getName()
   //                     + "</TD><TD>" + makeMethodDisplay(oMethod) + "</TD></TR>");
   //            }
   //         }
   //      }
   //      sClassHtml.append("</TABLE>");
   //   }
   //
   //   public FXmlNodes listPath(String sPackage)
   //         throws FException{
   //      FXmlNodes oNodes = new FXmlNodes();
   //      String sClassPath = m_sClassPath;
   //      String sClassSourcePath = m_sSourcePath + "/";
   //      if (!FStringUtil.isEmpty(sPackage)) {
   //         String sPackagePath = FStringUtil.replace(sPackage, ".",
   //               File.separator)
   //               + File.separator;
   //         sClassPath += sPackagePath;
   //         sClassSourcePath += sPackagePath;
   //      }
   //      File oDir = new File(sClassPath);
   //      if (oDir.isDirectory()) {
   //         FStringList oParams = new FStringList();
   //         // ------------------------------------------------------------
   //         String sFileName = null;
   //         String sNodeCaption = null;
   //         String sPackageName = null;
   //         File oFile = null;
   //         String[] sSubFiles = null;
   //         int nSubFileCount = 0;
   //         String sSubFileName = null;
   //         // ------------------------------------------------------------
   //         // Add Class Packages
   //         FXmlNodes oNodesPackages = new FXmlNodes();
   //         FXmlNodes oNodesFiles = new FXmlNodes();
   //         // ------------------------------------------------------------
   //         String[] sFileNames = oDir.list();
   //         boolean bHasChildren = false;
   //         int nFileCount = sFileNames.length;
   //         for (int nFile = 0; nFile < nFileCount; nFile++) {
   //            sFileName = sFileNames[nFile];
   //            oFile = new File(sClassPath + File.separator + sFileName);
   //            // ------------------------------------------------------------
   //            if (oFile.isDirectory()) {
   //               sNodeCaption = sFileName;
   //               sPackageName = sFileName;
   //               sSubFiles = oFile.list();
   //               bHasChildren = false;
   //               nSubFileCount = sSubFiles.length;
   //               for (int nSubFile = 0; nSubFile < nSubFileCount; nSubFile++) {
   //                  sSubFileName = sSubFiles[nSubFile];
   //                  if (sSubFileName.endsWith(".class")) {
   //                     bHasChildren = true;
   //                     break;
   //                  }
   //                  if (FFileUtil.isDirectory(sClassPath + sFileName + "/"
   //                        + sSubFileName)) {
   //                     bHasChildren = true;
   //                     break;
   //                  }
   //               }
   //               // ------------------------------------------------------------
   //               if (bHasChildren) {
   //                  FXmlNode oDirNode = new FXmlNode("Package");
   //                  oDirNode.setAttribute("type", "src.java.package");
   //                  if (FStringUtil.isEmpty(sPackage)) {
   //                     oDirNode.setAttribute("package", sPackageName);
   //                  } else {
   //                     oDirNode.setAttribute("package", sPackage + "."
   //                           + sPackageName);
   //                  }
   //                  oDirNode.setAttribute("caption", sPackageName);
   //                  oDirNode.setAttribute("icon", "cls.pkg");
   //                  oDirNode.setAttribute("child", "Y");
   //                  oNodesPackages.push(oDirNode);
   //               }
   //            }
   //            if (sFileName.endsWith(".class") && oFile.isFile()) {
   //               sNodeCaption = FStringUtil.left(sFileName, ".class");
   //               if (sNodeCaption.indexOf("$") == -1) {
   //                  // ------------------------------------------------------------
   //                  FXmlNode oClsNode = new FXmlNode("Class");
   //                  oClsNode.setAttribute("type", "src.java.class");
   //                  oClsNode.setAttribute("package", sPackage);
   //                  oClsNode.setAttribute("class", sNodeCaption);
   //                  if (FFileUtil.exists(sClassSourcePath + sNodeCaption
   //                        + ".java")) {
   //                     sNodeCaption = "[ " + sNodeCaption + " ]";
   //                  }
   //                  oClsNode.setAttribute("caption", sNodeCaption);
   //                  oClsNode.setAttribute("icon", "cls.cls");
   //                  oClsNode.setAttribute("child", "N");
   //                  oNodesFiles.push(oClsNode);
   //               }
   //            }
   //         }
   //         oNodesPackages.sortByAttribute("caption");
   //         oNodesFiles.sortByAttribute("caption");
   //         oNodes.append(oNodesPackages);
   //         oNodes.append(oNodesFiles);
   //      }
   //      return oNodes;
   //   }
   //
   //   public void initializeConfig()
   //         throws FException{
   //      super.initializeConfig();
   //      m_sClassPath = configNode().nodeText("ClassPath");
   //      m_sSourcePath = configNode().nodeText("SourcePath");
   //      m_sHelpPath = configNode().nodeText("HelpPath");
   //   }
   //
}
