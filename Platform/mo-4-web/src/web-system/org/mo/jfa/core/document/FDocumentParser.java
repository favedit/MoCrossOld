package org.mo.jfa.core.document;

public class FDocumentParser
{

   //   private FStringList m_oImageList = new FStringList();
   //
   //   private FXmlNodes m_oIndexList = new FXmlNodes();
   //
   //   private FString m_sInfo = new FString();
   //
   //   public FDocumentParser() {
   //      super();
   //   }
   //
   //   public FString parse(String sConfigFile) throws FException {
   //      if (FFileUtil.exists(sConfigFile)) {
   //         parseDocument(loadDocument(sConfigFile));
   //      }
   //      return m_sInfo;
   //   }
   //
   //   public FXmlNode loadDocument(String sFileName) throws FException {
   //      FXmlDocument oDocument = new FXmlDocument();
   //      oDocument.loadFromFile(sFileName);
   //      FXmlNode oNode = oDocument.rootNode();
   //      oNode.setName(sFileName);
   //      m_oIndexList.push(oNode);
   //      return oNode;
   //   }
   //
   //   private String m_sDocumentId = null;
   //
   //   public String documentId() {
   //      return m_sDocumentId;
   //   }
   //
   //   private String m_sVersion = null;
   //
   //   public String version() {
   //      return m_sVersion;
   //   }
   //
   //   private String m_sNodeId = null;
   //
   //   public String nodeId() {
   //      return m_sNodeId;
   //   }
   //
   //   private String m_sHomeUri = null;
   //
   //   public String homeUri() {
   //      return m_sHomeUri;
   //   }
   //
   //   public void parseDocument(FXmlNode oCfgNode) {
   //      m_sNodeId = oCfgNode.attribute("id");
   //      m_sVersion = oCfgNode.attribute("version");
   //      m_sDocumentId = m_sNodeId + "-" + m_sVersion;
   //      for (FXmlNode oNode : oCfgNode.nodes()) {
   //         if (oNode.name().equalsIgnoreCase("Catalog")) {
   //         } else if (oNode.name().equalsIgnoreCase("Head")) {
   //            parseHead(oNode);
   //         } else if (oNode.name().equalsIgnoreCase("Refrence")) {
   //            parseRefrence(oNode);
   //         } else if (oNode.name().equalsIgnoreCase("Home")) {
   //            parseHome(oNode);
   //         }
   //      }
   //   }
   //
   //   public void parseRefrence(FXmlNode oCfgNode) {
   //      for (FXmlNode oNode : oCfgNode.nodes()) {
   //         if (oNode.name().equalsIgnoreCase("Img")) {
   //            parseRefrenceImage(oNode);
   //         } else if (oNode.name().equalsIgnoreCase("Text")) {
   //            //parseBodyText(oNode);
   //         }
   //      }
   //   }
   //
   //   public void parseRefrenceImage(FXmlNode oCfgNode) {
   //      m_oImageList.add(oCfgNode.attribute("id"), "<IMG src=" + oCfgNode.text()
   //            + ">");
   //   }
   //
   //   public void parseHead(FXmlNode oCfgNode) {
   //      for (FXmlNode oNode : oCfgNode.nodes()) {
   //         if (oNode.name().equalsIgnoreCase("Title")) {
   //            parseHeadTitle(oNode);
   //         } else if (oNode.name().equalsIgnoreCase("Text")) {
   //            parseBodyText(oNode);
   //         }
   //      }
   //   }
   //
   //   public void parseHeadTitle(FXmlNode oCfgNode) {
   //      m_sInfo.append("<SPAN class='docTitle'>");
   //      m_sInfo.append(oCfgNode.text());
   //      m_sInfo.append("</SPAN>");
   //      m_sInfo.append("<HR style='height:1px'>");
   //   }
   //
   //   public void parseHome(FXmlNode oCfgNode) {
   //      m_sHomeUri = oCfgNode.text();
   //   }
   //
   //   public void parseBodyParagraph(FXmlNode oCfgNode) {
   //      m_sInfo.append("<B>");
   //      m_sInfo.append(oCfgNode.attribute("label"));
   //      m_sInfo.append("</B><br>");
   //      m_sInfo.append(oCfgNode.text());
   //      m_sInfo.append("<br><br>");
   //   }
   //
   //   public void parseBodyText(FXmlNode oCfgNode) {
   //      m_sInfo.append(oCfgNode.text());
   //   }
   //
   //   public void parseBodyImage(FXmlNode oCfgNode) {
   //      m_sInfo.append(m_oImageList.value(oCfgNode.attribute("id")));
   //   }
}
