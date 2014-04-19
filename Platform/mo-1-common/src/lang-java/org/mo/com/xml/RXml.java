package org.mo.com.xml;

import java.util.List;
import org.jdom.Attribute;
import org.jdom.Content;
import org.jdom.Element;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.IInitialize;
import org.mo.com.lang.RInteger;
import org.mo.com.lang.RString;

//============================================================
// <T>XML文档工具。</T>
//
// @history 080731 MAOCY 创建
//============================================================
public class RXml
{
   // 文件后缀名
   public static final String EXTENSION = "xml";

   // 默认属性分隔符
   public final static String DEFAULT_ATTR_SPLITTER = "!";

   // 默认的编码方式
   public final static String DEFAULT_ENCODING = "UTF-8";

   // 默认的前置空白
   public final static String DEFAULT_INDENT = "  ";

   // 默认名称分隔符
   public final static String DEFAULT_NAME_SPLITTER = ".";

   // 默认节点名称
   public static final String DEFAULT_NODE_NAME = "Node";

   // 默认根节点名称
   public static final String DEFAULT_ROOT_NAME = "Configuration";

   // 节点名称分隔符
   public final static String NAME_SPLITTER = ".";

   // 节点名称分隔符（Regex）
   public final static String NAME_SPLITTER_REG = "\\.";

   //============================================================
   // <T>格式化XML文本字符串。</T>
   //
   // @param text 字符串
   // @return 字符串
   //============================================================
   public static String formatText(String text){
      if(null != text){
         StringBuilder xml = new StringBuilder();
         char[] chars = text.toCharArray();
         int count = chars.length;
         for(int n = 0; n < count; n++){
            char ch = chars[n];
            switch(ch){
               case '\n':
                  xml.append("\\n");
                  break;
               case '\r':
                  break;
               case '"':
                  xml.append("&quot;");
                  break;
               case '<':
                  xml.append("&lt;");
                  break;
               case '>':
                  xml.append("&gt;");
                  break;
               case '&':
                  xml.append("&amp;");
                  break;
               default:
                  xml.append(ch);
            }
         }
         return xml.toString();
      }
      return null;
   }

   //============================================================
   // <T>格式化XML节点字符串。</T>
   //
   // @param text 字符串
   // @return 字符串
   //============================================================
   public static String formatNote(String text){
      if(null != text){
         StringBuilder xml = new StringBuilder();
         char[] chars = text.toCharArray();
         int count = chars.length;
         for(int n = 0; n < count; n++){
            char ch = chars[n];
            switch(ch){
               case '\r':
                  break;
               case '"':
                  xml.append("&quot;");
                  break;
               case '<':
                  xml.append("&lt;");
                  break;
               case '>':
                  xml.append("&gt;");
                  break;
               case '&':
                  xml.append("&amp;");
                  break;
               default:
                  xml.append(ch);
            }
         }
         return xml.toString();
      }
      return null;
   }

   //============================================================
   // <T>将一段XML字符串信息转换成节点对象。</T>
   //
   // @param xml XML字符串
   // @return 节点
   //============================================================
   public static FXmlNode buildNode(String xml){
      FXmlDocument xdocument = new FXmlDocument();
      xdocument.loadString(xml);
      return xdocument.root();
   }

   /**
    * <p>获得当前节点下所有的节点的指定属性名称的属性内容列表</p>
    * <p>包含递归的子节点</p>
    * <p>create date:2005/10/15</p>
    * 
    * @param oNode 节点
    * @param sName 节点名称
    * @param sAttrName 属性名称
    * @return 属性内容列表
    */
   public static FStrings makeAllNodeAttributes(FXmlNode oNode,
                                                String sName,
                                                String sAttrName){
      FStrings oAttributes = new FStrings();
      if(oNode.hasNode()){
         makeAllNodeAttributes__(oNode, oAttributes, sName, sAttrName);
      }
      return oAttributes;
   }

   // 获得当前节点下所有的节点的指定属性名称的内容列表
   private static void makeAllNodeAttributes__(FXmlNode oNode,
                                               FStrings oArray,
                                               String sName,
                                               String sAttrName){
      if(oNode.hasNode()){
         for(FXmlNode oItemNode : oNode.nodes()){
            if(RString.equalsIgnoreCase(oItemNode.name(), sName)){
               oArray.push(oItemNode.get(sAttrName));
            }
            if(oItemNode.hasNode()){
               makeAllNodeAttributes__(oItemNode, oArray, sName, sAttrName);
            }
         }
      }
   }

   /**
    * <p>获得当前节点下的节点的指定属性名称的属性内容列表</p>
    * <p>create date:2005/10/15</p>
    * 
    * @param oNode 节点
    * @param sAttrName 属性名称
    * @return 属性内容列表
    */
   public static FStrings makeNodeAttributes(FXmlNode oNode,
                                             String sAttrName){
      FStrings oAttributes = new FStrings();
      if(oNode.hasNode()){
         for(FXmlNode oItemNode : oNode.nodes()){
            oAttributes.push(oItemNode.get(sAttrName));
         }
      }
      return oAttributes;
   }

   /**
    * <p>获得当前节点下的节点的指定属性名称的属性内容列表</p>
    * <p>create date:2005/10/15</p>
    * 
    * @param oNode 节点
    * @param sName 节点名称
    * @param sAttrName 属性名称
    * @return 属性内容列表
    */
   public static FStrings makeNodeAttributes(FXmlNode oNode,
                                             String sName,
                                             String sAttrName){
      FStrings oAttributes = new FStrings();
      if(oNode.hasNode()){
         for(FXmlNode oItemNode : oNode.nodes()){
            if(RString.equalsIgnoreCase(oItemNode.name(), sName)){
               oAttributes.push(oItemNode.get(sAttrName));
            }
         }
      }
      return oAttributes;
   }

   /**
    * <p>获得节点的哈希表</p>
    * <p>create date:2005/02/18</p>
    *
    * @param oNode 节点
    * @param sNameSplitter 名称分隔符
    * @return 哈希表
    */
   public static FXmlNodeDictionary makeNodeMap(FXmlNode oNode){
      FXmlNodeDictionary oMap = new FXmlNodeDictionary();
      makeNodeMap__(oMap, oNode, 0, "", DEFAULT_NAME_SPLITTER);
      return oMap;
   }

   /**
    * <p>获得节点的哈希表</p>
    * <p>create date:2005/02/18</p>
    *
    * @param oNode 节点
    * @param sNameSplitter 名称分隔符
    * @return 哈希表
    */
   public static FXmlNodeDictionary makeNodeMap(FXmlNode oNode,
                                                String sNameSplitter){
      FXmlNodeDictionary oMap = new FXmlNodeDictionary();
      makeNodeMap__(oMap, oNode, 0, "", sNameSplitter);
      return oMap;
   }

   // 同步FXmlNode数据到FMap节点对象中
   private static void makeNodeMap__(FXmlNodeDictionary oMap,
                                     FXmlNode oNode,
                                     int nLevel,
                                     String sPath,
                                     String sNameSplitter){
      if(oMap == null || oNode == null){
         return;
      }
      // 计算节点的名称路径
      if(!RString.isEmpty(sPath)){
         sPath += ".";
      }
      if(nLevel > 0){
         sPath += oNode.name();
      }
      oMap.set(sPath.toLowerCase(), oNode);
      // 设置子节点的属性
      if(oNode.hasNode()){
         for(FXmlNode oChildNode : oNode.nodes()){
            makeNodeMap__(oMap, oChildNode, nLevel + 1, sPath, sNameSplitter);
         }
      }
   }

   /**
    * <p>获得节点内容信息的哈希表</p>
    * <p>create date:2005/02/18</p>
    *
    * @param oNode 节点
    * @return 哈希表
    */
   public static FAttributes makeValueMap(FXmlNode oNode){
      FAttributes oMap = new FAttributes();
      makeValueMap__(oMap, oNode, 0, "", DEFAULT_NAME_SPLITTER, DEFAULT_ATTR_SPLITTER);
      return oMap;
   }

   /**
    * <p>获得节点内容信息的哈希表</p>
    * <p>create date:2005/02/18</p>
    *
    * @param oNode 节点
    * @param sNameSplitter 名称分隔符
    * @param sAttrSplitter 属性分隔符
    * @return 哈希表
    */
   public static FAttributes makeValueMap(FXmlNode oNode,
                                          String sNameSplitter,
                                          String sAttrSplitter){
      FAttributes oMap = new FAttributes();
      makeValueMap__(oMap, oNode, 0, "", sNameSplitter, sAttrSplitter);
      return oMap;
   }

   // 同步FXmlNode数据到FMap节点对象中
   private static void makeValueMap__(FAttributes oMap,
                                      FXmlNode oNode,
                                      int nLevel,
                                      String sPath,
                                      String sNameSplitter,
                                      String sAttrSplitter){
      if(oMap == null || oNode == null){
         return;
      }
      // 计算节点的名称路径
      if(!RString.isEmpty(sPath)){
         sPath += ".";
      }
      if(nLevel > 0){
         sPath += oNode.name();
      }
      if(!RString.isEmpty(oNode.text())){
         oMap.set(sPath.toLowerCase(), oNode.text());
      }
      // 设置节点的属性
      if(oNode.hasAttribute()){
         String sAttributeName = null;
         FAttributes oNodeAttribute = oNode.attributes();
         int nAttributeCount = oNodeAttribute.count();
         if(nAttributeCount > 0){
            for(int n = 0; n < nAttributeCount; n++){
               sAttributeName = oNodeAttribute.name(n);
               oMap.set(String.valueOf(sPath + sAttrSplitter + sAttributeName).toLowerCase(), oNodeAttribute.value(n));
            }
         }
      }
      // 设置子节点的属性
      if(oNode.hasNode()){
         for(FXmlNode oChildNode : oNode.nodes()){
            makeValueMap__(oMap, oChildNode, nLevel, sPath, sNameSplitter, sAttrSplitter);
         }
      }
   }

   /**
    * <p>获得节点下子节点文本内容的列表</p>
    * <p>create date:2005/10/15</p>
    *
    * @param oNode 节点
    * @param sAttrName 属性名称
    * @return 列表
    */
   public static FAttributes nodeTexts(FXmlNode oNode){
      FAttributes oTexts = new FAttributes();
      for(FXmlNode oItemNode : oNode.nodes()){
         oTexts.set(oItemNode.name(), oItemNode.text());
      }
      return oTexts;
   }

   /**
    * <p>获得节点指定属性名称和属性内容的列表</p>
    * <p>create date:2005/10/15</p>
    *
    * @param oNode 节点
    * @param sAttrName 属性名称
    * @return 列表
    */
   public static FAttributes nodeTexts(FXmlNode oNode,
                                       String sAttrName){
      FAttributes oTexts = new FAttributes();
      for(FXmlNode oItemNode : oNode.nodes()){
         oTexts.set(oItemNode.name(), oItemNode.get(sAttrName));
      }
      return oTexts;
   }

   /**
    * <p>将节点指定属性的属性名称转换为数字，进行排序</p>
    * <p>create date:2005/10/15</p>
    *
    * @param oNode 节点
    * @param sAttrName 属性名称
    * @param bAsc 排序方式
    * @return 节点
    */
   public static void sortIntegerName(FXmlNode oNode,
                                      String sAttrName){
      sortIntegerName(oNode, sAttrName, true);
   }

   /**
    * <p>将节点指定属性的属性名称转换为数字，进行排序</p>
    * <p>create date:2005/10/15</p>
    *
    * @param oNode 节点
    * @param sAttrName 属性名称
    * @param bAsc 排序方式
    * @return 节点
    */
   public static void sortIntegerName(FXmlNode oNode,
                                      String sAttrName,
                                      boolean bAsc){
      int nValue1 = 0;
      int nValue2 = 0;
      boolean bCompare = false;
      int nSize = oNode.nodes().count();
      for(int j = 0; j < nSize; j++){
         for(int i = 0; i < nSize - 1 - j; i++){
            nValue1 = RInteger.parse(oNode.node(i).get(sAttrName), 0);
            nValue2 = RInteger.parse(oNode.node(i + 1).get(sAttrName), 0);
            bCompare = (nValue1 > nValue2);
            if(!bAsc){
               bCompare = !bCompare;
            }
            if(bCompare){
               oNode.nodes().swap(i, i + 1);
            }
         }
      }
   }

   // 同步FXmlNode数据到Element节点对象中
   public static void syncElementFromNode(Element element,
                                          FXmlNode node){
      if(node == null || element == null){
         return;
      }
      // 设置元素（Element）的名称和内容
      element.setName(node.name());
      element.setText(RString.nvl(node.text()));
      // 设置元素（Element）的属性内容
      if(node.hasAttribute()){
         List<?> elmAttrs = element.getAttributes();
         if(elmAttrs != null){
            String attrName = null;
            String attrValue = null;
            FAttributes nodeAttrs = node.attributes();
            int count = nodeAttrs.count();
            for(int n = 0; n < count; n++){
               attrName = nodeAttrs.name(n);
               attrValue = nodeAttrs.value(n);
               // 修正属性内容
               if(attrValue != null){
                  if(attrValue.indexOf("\n") != -1){
                     attrValue = attrValue.replaceAll("\r", "");
                     attrValue = attrValue.replaceAll("\n", "\\\\n");
                  }
                  element.setAttribute(attrName, attrValue);
               }
            }
         }
      }
      // 设置元素（Element）的子节点
      if(node.hasNode()){
         Element child = null;
         FXmlNodes nodes = node.nodes();
         int count = nodes.count();
         for(int n = 0; n < count; n++){
            child = new Element(node.name());
            element.addContent(child);
            syncElementFromNode(child, nodes.get(n));
         }
      }
   }

   // 同步Element数据到FXmlNode节点对象中
   public static void syncNodeFromElement(FXmlNode node,
                                          Element element){
      if(node == null || element == null){
         return;
      }
      // 设置节点的名称和内容
      node.setName(element.getName());
      node.setText(element.getTextTrim());
      // 设置节点的属性内容
      List<?> elmAttrs = element.getAttributes();
      if(!elmAttrs.isEmpty()){
         String attrValue = null;
         Attribute elmAttr = null;
         int count = elmAttrs.size();
         FAttributes nodeAttrs = node.attributes();
         for(int n = 0; n < count; n++){
            elmAttr = (Attribute)elmAttrs.get(n);
            attrValue = elmAttr.getValue();
            if(attrValue.indexOf("\\n") != -1){
               attrValue = attrValue.replaceAll("\\\\n", "\n");
            }
            nodeAttrs.set(elmAttr.getName(), attrValue);
         }
      }
      // 设置节点的子节点
      List<?> contents = element.getContent();
      if(!contents.isEmpty()){
         Content content = null;
         FXmlNode child = null;
         int count = contents.size();
         FXmlNodes nodes = node.nodes();
         for(int n = 0; n < count; n++){
            content = (Content)contents.get(n);
            if(content instanceof Element){
               child = new FXmlNode(((Element)content).getName());
               nodes.push(child);
               syncNodeFromElement(child, (Element)content);
            }
         }
      }
      if(node instanceof IInitialize){
         ((IInitialize)node).initialize();
      }
   }

   /**
    * <p>根据路径建立一个节点</p>
    * <p>路径以.进行分隔名称，每个名称是一个节点，如果不存在，就建立新的节点。</p>
    * <p>create date:2005/02/18</p>
    *
    * @param oNode 节点
    * @param sPath 节点路径
    * @return 节点
    */
   public static FXmlNode syncPathNode(FXmlNode oNode,
                                       String sPath){
      if(!RString.isEmpty(sPath)){
         String[] arPaths = sPath.split("\\.");
         int nCount = arPaths.length;
         for(int i = 0; i < nCount; i++){
            oNode = oNode.syncNode(arPaths[i]);
         }
      }
      return oNode;
   }
   //   /**
   //    * <p>从一个数据集转换为XML节点结构</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param oDataset 数据集
   //    * @return 节点
   //    */
   //   public static FXmlNodes buildNodes(FDataset oDataset)
   //         throws FException{
   //      FXmlNode oNode = null;
   //      FXmlNodes oNodes = new FXmlNodes();
   //      for(FUnit oUnit : oDataset){
   //         oNode = oNodes.create("Unit");
   //         oNode.attributes().append(oUnit);
   //      }
   //      return oNodes;
   //   }
}
