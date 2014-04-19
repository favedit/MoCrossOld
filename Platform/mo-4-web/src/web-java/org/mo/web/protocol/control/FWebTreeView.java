package org.mo.web.protocol.control;

public class FWebTreeView
{

   public static final String NODE_TYPE = "type";

   public static final String NODE_CAPTION = "caption";

   public static final String NODE_CHILD = "child";

   public static final String NODE_ICON = "icon";

   public static final String ACTION_NODE_CLICK = "_click";

   public static final String ACTION_NODE_DELETE = "_delete";

   public static final String ACTION_NODE_REFRESH = "_refresh";

   public static final String ACTION_NODE_UPDATE = "_update";

   public FWebTreeView(){
   }

   //   private FTreeNodes m_oNodes = null;
   //
   //   public FTreeNodes getNodes() {
   //      if (m_oNodes == null) {
   //         m_oNodes = new FTreeNodes();
   //      }
   //      return m_oNodes;
   //   }
   //
   //   public boolean insertNode() {
   //      return true;
   //   }
   //
   //   public boolean insertChildNode() {
   //      return true;
   //   }
   //
   //   public boolean findNodeByUUID() {
   //      return true;
   //   }
   //
   //   public boolean deleteNodeByUUID() {
   //      return true;
   //   }
   //
   //   public boolean clearNodes() {
   //      return true;
   //   }
   //
   //   public String getHTML() {
   //      return "";
   //   }
   //
   //   private String m_sResourcePath = null;
   //
   //   public boolean setResourcePath(String sPath) {
   //      m_sResourcePath = sPath;
   //      return true;
   //   }
   //
   //   public String getChildrenHTML() {
   //      return getChildrenHTML(null);
   //   }
   //
   //   protected boolean makeChildrenScript(StringBuffer oScript,
   //                                        FTreeNodes oNodes,
   //                                        String sParent,
   //                                        int nLevel) {
   //      FTreeNode oNode = null;
   //      String sNodeCaption = null;
   //      int nNodeCount = oNodes.getCount();
   //      for (int n = 0; n < nNodeCount; n++) {
   //         oNode = oNodes.getNode(n);
   //         // ------------------------------------------------------------
   //         sNodeCaption = oNode.getCaptionFormat() ?
   //               oNode.getCaption() : oNode.getCaption();
   //         // ------------------------------------------------------------
   //         if (FString.isEmpty(sParent)) {
   //            oScript.append("   var oNode = oTreeView.insert(" +
   //                           "\"" + sNodeCaption + "\"," +
   //                           "\"" + oNode.getIcon() + "\"," +
   //                           oNode.getHasChildren() + "," +
   //                           "\"" + oNode.getAttr() + "\");\n");
   //         } else {
   //            oScript.append("   var oNode = oTreeView.insertChild(oParent," +
   //                           "\"" + sNodeCaption + "\"," +
   //                           "\"" + oNode.getIcon() + "\"," +
   //                           oNode.getHasChildren() + "," +
   //                           "\"" + oNode.getAttr() + "\");\n");
   //         }
   //         if (!oNode.isEmpty()) {
   //            oScript.append(
   //                  "   oStoreParent" + nLevel + " = oParent; oParent = oNode;\n");
   //            makeChildrenScript(oScript, oNode.getNodes(), sParent, nLevel + 1);
   //            oScript.append(
   //                  "   oParent.extend(false);\n" +
   //                  "   oParent = oStoreParent" + nLevel + ";\n");
   //         }
   //      }
   //      return true;
   //   }
   //
   //   public String getChildrenHTML(String sParent) {
   //      StringBuffer oScript = new StringBuffer();
   //      makeChildrenScript(oScript, getNodes(), sParent, 0);
   //      return oScript.toString();
   //   }
   //
   //   private String m_sHttpCatalogAction = null;
   //
   //   public String getHttpCatalogAction() {
   //      return m_sHttpCatalogAction;
   //   }
   //
   //   public boolean setHttpCatalogAction(String sValue) {
   //      m_sHttpCatalogAction = sValue;
   //      return true;
   //   }
   //
   //   private String m_sHttpBodyAction = null;
   //
   //   public String getHttpBodyAction() {
   //      return m_sHttpBodyAction;
   //   }
   //
   //   public boolean setHttpBodyAction(String sValue) {
   //      m_sHttpBodyAction = sValue;
   //      return true;
   //   }
   //
   //   private String m_sHttpToolbarAction = null;
   //
   //   public String getHttpToolbarAction() {
   //      return m_sHttpToolbarAction;
   //   }
   //
   //   public boolean setHttpToolbarAction(String sValue) {
   //      m_sHttpToolbarAction = sValue;
   //      return true;
   //   }
   //
   //   private String m_sHttpCatalogPage = null;
   //
   //   public String getHttpCatalogPage() {
   //      return m_sHttpCatalogPage;
   //   }
   //
   //   public boolean setHttpCatalogPage(String sValue) {
   //      m_sHttpCatalogPage = sValue;
   //      return true;
   //   }
   //
   //   private String m_sHttpToolbarPage = null;
   //
   //   public String getHttpToolbarPage() {
   //      return m_sHttpToolbarPage;
   //   }
   //
   //   public boolean setHttpToolbarPage(String sValue) {
   //      m_sHttpToolbarPage = sValue;
   //      return true;
   //   }
   //
   //   private String m_sHttpBodyPage = null;
   //
   //   public String getHttpBodyPage() {
   //      return m_sHttpBodyPage;
   //   }
   //
   //   public boolean setHttpBodyPage(String sValue) {
   //      m_sHttpBodyPage = sValue;
   //      return true;
   //   }
   //
   //   public boolean setHttpCatalog(String sAction, String sPage) {
   //      setHttpCatalogAction(sAction);
   //      setHttpCatalogPage(sPage);
   //      return true;
   //   }
   //
   //   public boolean setHttpToolbar(String sAction, String sPage) {
   //      setHttpToolbarAction(sAction);
   //      setHttpToolbarPage(sPage);
   //      return true;
   //   }
   //
   //   public boolean setHttpBody(String sAction, String sPage) {
   //      setHttpBodyAction(sAction);
   //      setHttpBodyPage(sPage);
   //      return true;
   //   }
   //
   //   public String getTVRefreshHTML(String sAction, String sLinkUUID) {
   //      StringBuffer oScript = new StringBuffer();
   //      oScript.append(
   //            "<SCRIPT language='javascript'>\n" +
   //            "function initPage(){\n");
   //      if (sAction.equals("extend")) {
   //         oScript.append(
   //               "   var oGlobalTV = oGlobals.getItemByKey('app_treeview');\n" +
   //               "   if(!oGlobalTV)\n" +
   //               "      return;\n" +
   //               "   var oParent = oGlobalTV.findNodeByUUID(\"" + sLinkUUID +
   //               "\");\n" +
   //               "   if(!oParent)\n" +
   //               "      return;\n" +
   //               getChildrenHTML(sLinkUUID) +
   //               "   oParent.extend(true);\n" +
   //               "   oGlobalTV.isLoading = false;\n" +
   //               "   oGlobalTV.onNodeClick(oParent);\n");
   //      }
   //      oScript.append(
   //            "}\n" +
   //            "</SCRIPT>\n");
   //      // ------------------------------------------------------------
   //      return oScript.toString();
   //   }
   //
   //   public String getInsertNodesScript(String sLinkUUID) {
   //      return
   //            "<SCRIPT language='javascript'>\n" +
   //            "   var oGlobalTV = oGlobals.getItemByKey('app_treeview');\n" +
   //            "   if(oGlobalTV){\n" +
   //            "      var oParent = oGlobalTV.findNodeByAttr(\"" + sLinkUUID +
   //            "\");\n" +
   //            "      if(oParent){\n" +
   //            getChildrenHTML(sLinkUUID) +
   //            "      oParent.extend(true);\n" +
   //            "      }\n" +
   //            "      oGlobalTV.isLoading = false;\n" +
   //            "   }\n" +
   //            "</SCRIPT>\n";
   //   }
   //
   //   public String getNavigatorHTML() {
   //      return "<TABLE width='100%' border='0' cellspacing='4' cellpadding='0' bgcolor='#FFFFFF'>\n" +
   //            "<TR><TD><DIV id=tvNavigator></DIV><TD></TR>\n" +
   //            "</TABLE>\n";
   //   }
   //
   //   public String dump() {
   //      StringBuffer oDump = new StringBuffer();
   //      FTreeNodes oNodes = getNodes();
   //      int nNodeCount = oNodes.getCount();
   //      FTreeNode oNode = null;
   //      for (int n = 0; n < nNodeCount; n++) {
   //         oNode = oNodes.getNode(n);
   //         oDump.append(oNode.dump() + "\n");
   //      }
   //      return oDump.toString();
   //   }
   //
   //   public boolean pushNode(FTreeNode oTreeNode) {
   //      return this.getNodes().push(oTreeNode);
   //   }
}

// ************************************************************
