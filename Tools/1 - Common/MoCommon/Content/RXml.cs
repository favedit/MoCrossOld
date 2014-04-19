using System.Xml;
using MO.Common.Collection;
using MO.Common.Lang;

namespace MO.Common.Content
{
   //============================================================
   // <T>XML文档工具类。</T>
   //============================================================
   public class RXml
   {
      // 扩展名
      public const string EXT = "xml";

      // 全扩展名
      public const string EXTENSION = ".xml";

      // 默认根节点名称
      public const string ROOT_NAME = "Configuration";

      // 默认节点名称
      public const string NODE_NAME = "Node";

      //============================================================
      // <T>查找元素列表中的节点对象。</T>
      //
      // @param elements 元素列表
      // @return 节点对象
      //============================================================
      internal static FXmlNode FindNode(FXmlElements elements) {
         foreach(FXmlElement element in elements) {
            if(element is FXmlNode) {
               return (FXmlNode)element;
            }
         }
         return null;
      }

      //============================================================
      // <T>同步元素列表到文档元素中。</T>
      //
      // @param parent 元素对象
      // @param elements 元素列表
      //============================================================
      internal static void SyncNodeFromElements(FXmlElement parent, XmlNodeList elements) {
         if((null != parent) && (null != elements)) {
            // 同步节点
            foreach(XmlNode element in elements) {
               if(element.NodeType == XmlNodeType.Element) {
                  // 处理元素节点
                  string name = element.Name;
                  FXmlElement node = null;
                  FAttributes nodeAttrs = new FAttributes();
                  // 同步属性
                  XmlAttributeCollection eattrs = element.Attributes;
                  if(null != eattrs) {
                     foreach(XmlAttribute attr in eattrs) {
                        nodeAttrs[attr.Name] = attr.Value;
                     }
                  }
                  // 设置文本
                  bool hasChild = true;
                  if(1 == element.ChildNodes.Count) {
                     XmlNode child = element.FirstChild;
                     if(child.NodeType == XmlNodeType.Text) {
                        node = parent.CreateNode(name, nodeAttrs);
                        node._type = EXmlElementType.Node;
                        node.Text = child.Value;
                        hasChild = false;
                     } else if(child.NodeType == XmlNodeType.CDATA) {
                        node = parent.CreateNode(name, nodeAttrs);
                        node._type = EXmlElementType.Node;
                        node.Text = child.Value;
                        hasChild = false;
                     }
                  }
                  if(null == node) {
                     node = parent.CreateNode(name, nodeAttrs);
                  }
                  if(hasChild && (element.ChildNodes.Count > 0)) {
                     SyncNodeFromElements(node, element.ChildNodes);
                  }
               } else if(element.NodeType == XmlNodeType.Comment) {
                  // 处理注释节点
                  parent.CreateComment().Text = element.Value;
               }
            }
         }
      }

      //============================================================
      // <T>同步文档元素到元素对象中。</T>
      //
      // @param node 文档元素
      // @param element 元素对象
      //============================================================
      internal static void SyncElementFromNode(FXmlElement node, XmlNode element) {
         XmlDocument doc = element.OwnerDocument;
         // 设置元素内容
         if(node._type == EXmlElementType.Data) {
            element.AppendChild(doc.CreateTextNode(node.Text));
            return;
         } else if(!RString.IsEmpty(node.Text)) {
            element.AppendChild(doc.CreateTextNode(node.Text));
         }
         // 同步属性列表
         FAttributes attrs = node.Attributes;
         if(attrs != null) {
            foreach(IStringPair pair in attrs) {
               XmlAttribute xattr = doc.CreateAttribute(pair.Name);
               xattr.Value = pair.Value;
               element.Attributes.Append(xattr);
            }
         }
         // 同步元素列表
         if(node.HasElement()) {
            foreach(FXmlElement nodeElement in node.Elements) {
               if(nodeElement is FXmlComment) {
                  element.AppendChild(doc.CreateComment(nodeElement.Text));
               } else if(nodeElement._type == EXmlElementType.Data) {
                  element.AppendChild(doc.CreateCDataSection(nodeElement.Text));
               } else {
                  XmlNode celement = doc.CreateElement(nodeElement.Name);
                  element.AppendChild(celement);
                  SyncElementFromNode(nodeElement, celement);
               }
            }
         }
      }

      //============================================================
      // <T>从文档节点中建立属性表。</T>
      //
      // @param parent 文档元素
      // @param attributes 属性列表
      //============================================================
      public static void BuildAttributes(FXmlNode parent, FAttributes attributes) {
         if((parent != null) && parent.HasNode()) {
            foreach(FXmlNode node in parent.Nodes) {
               attributes[node.Name] = node.Text;
            }
         }
      }

      //============================================================
      // <T>从文档节点中建立哈希表。</T>
      //
      // @param parent 文档元素
      // @param map 文档节点哈希表
      //============================================================
      public static void BuildMap(FXmlNode parent, FXmlNodeMap map) {
         if((parent != null) && parent.HasNode()) {
            foreach(FXmlNode node in parent.Nodes) {
               map.Set(node.Name, node);
            }
         }
      }

      //============================================================
      // <T>从文档节点中建立哈希表。</T>
      //
      // @param parent 文档元素
      // @param map 文档节点哈希表
      // @param name 节点名称
      // @param attrName 属性名称
      //============================================================
      public static void BuildMap(FXmlNode parent, FXmlNodeMap map, string name, string attrName) {
         if(parent != null && parent.Nodes != null) {
            foreach(FXmlNode node in parent.Nodes) {
               if(node.IsName(name)) {
                  map.Set(node.Get(attrName), node);
               }
            }
         }
      }
   }
}
