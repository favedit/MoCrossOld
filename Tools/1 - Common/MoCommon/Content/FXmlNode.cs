using MO.Common.Collection;
using MO.Common.Lang;

namespace MO.Common.Content
{
   //============================================================
   // <T>文档节点。</T>
   //============================================================
   public class FXmlNode : FXmlElement
   {
      // 节点对象
      internal FXmlNodes _nodes;

      //============================================================
      // <T>构造文档节点。</T>
      //============================================================
      public FXmlNode() {
         _name = RXml.NODE_NAME;
         _type = EXmlElementType.Node;
      }

      //============================================================
      // <T>构造文档节点。</T>
      //
      // @param name 节点名称
      //============================================================
      public FXmlNode(string name)
         : base(name) {
      }

      //============================================================
      // <T>构造文档节点。</T>
      //
      // @param name 节点名称
      // @param attributes 属性列表
      //============================================================
      public FXmlNode(string name, FAttributes attributes)
         : base(name, attributes) {
      }

      //============================================================
      // <T>是否含有文档节点。</T>
      //
      // @return 是否含有
      //============================================================
      public bool HasNode() {
         return (_nodes != null) ? (_nodes.Count > 0) : false;
      }

      //============================================================
      // <T>是否含有节点名称。</T>
      //
      // @param name 节点名称
      // @return 是否含有
      //============================================================
      public bool ContainsNode(string name) {
         return (null != _nodes) ? _nodes.Contains(name) : false;
      }

      //============================================================
      // <T>获得文档节点列表。</T>
      //
      // @return 文档节点列表
      //============================================================
      public FXmlNodes Nodes {
         get {
            if(null == _nodes) {
               _nodes = new FXmlNodes();
               _nodes._document = _document;
            }
            return _nodes;
         }
      }

      //============================================================
      // <T>查找所有子节点。</T>
      //
      // @param nodes 子节点列表
      // @param node 子节点
      // @param name 节点名称
      // @param args 属性列表
      //============================================================
      protected void InnerFindAllNodes(FXmlNodes nodes, FXmlNode node, string name, params string[] args) {
         foreach(FXmlNode child in node.Nodes) {
            if(null != child) {
               if(child.Equals(name, args)) {
                  nodes.Push(child);
               }
               if(child.HasNode()) {
                  InnerFindAllNodes(nodes, child, name, args);
               }
            }
         }
      }

      //============================================================
      // <T>获得所有子节点。</T>
      //
      // @return 子节点列表
      //============================================================
      public FXmlNodes AllNodes() {
         return AllNodes(null, null);
      }

      //============================================================
      // <T>获得所有子节点。</T>
      //
      // @param name 节点名称
      // @return 子节点列表
      //============================================================
      public FXmlNodes AllNodes(string name) {
         return AllNodes(name, null);
      }

      //============================================================
      // <T>获得所有子节点。</T>
      //
      // @param name 节点名称
      // @param args 属性列表
      // @return 子节点列表
      //============================================================
      public FXmlNodes AllNodes(string name, params string[] args) {
         FXmlNodes nodes = new FXmlNodes();
         if(HasNode()) {
            InnerFindAllNodes(nodes, this, name, args);
         }
         return nodes;
      }

      //============================================================
      // <T>在当前节点下，查找子节点。</T>
      //
      // @param name 节点名称
      // @param args 属性列表
      // @return 子节点
      //============================================================
      public FXmlNode Find(string name, params string[] args) {
         return (null != _nodes) ? _nodes.Find(name, args) : null;
      }

      //============================================================
      // <T>在当前节点下，查找指定路径的子节点。</T>
      //
      // @param path 路径
      // @param args 属性列表
      // @return 子节点
      //============================================================
      public FXmlNode FindPath(string path, params string[] args) {
         return (null != _nodes) ? _nodes.FindPath(path, args) : null;
      }

      //============================================================
      // <T>在当前节点下，查找指定节点名称的布尔内容。</T>
      //
      // @param name 节点名称
      // @param args 属性列表
      // @return 节点内容
      //============================================================
      public bool FindBoolean(string name) {
         return RBool.IsTrue(FindText(name));
      }

      //============================================================
      // <T>在当前节点下，查找指定节点名称的整数内容。</T>
      //
      // @param name 节点名称
      // @param args 属性列表
      // @return 节点内容
      //============================================================
      public int FindInteger(string name) {
         return RInt.Parse(FindText(name));
      }

      //============================================================
      // <T>在当前节点下，查找指定节点名称的浮点内容。</T>
      //
      // @param name 节点名称
      // @param args 属性列表
      // @return 节点内容
      //============================================================
      public float FindFloat(string name) {
         return RFloat.Parse(FindText(name));
      }

      //============================================================
      // <T>在当前节点下，查找指定节点名称的节点内容。</T>
      //
      // @param name 节点名称
      // @param args 属性列表
      // @return 节点内容
      //============================================================
      public string FindText(string name, params string[] args) {
         if(null != _nodes) {
            FXmlNode node = _nodes.Find(name, args);
            if(null != node) {
               return node.Text;
            }
         }
         return null;
      }

      //============================================================
      // <T>在当前节点下，查找指定节点名称的节点内容。</T>
      //
      // @param name 节点名称
      // @param args 属性列表
      // @return 节点内容
      //============================================================
      public string FindTextTrim(string name, params string[] args) {
         return RString.Trim(FindText(name));
      }
      
      //============================================================
      // <T>在当前元素下，创建一个文档节点对象。</T>
      //
      // @param name 节点名称
      // @return 节点对象
      //============================================================
      public override FXmlNode CreateNode(string name) {
         FXmlNode node = base.CreateNode(name);
         Nodes.Push(node);
         return node;
      }

      //============================================================
      // <T>在当前元素下，创建一个节点对象。</T>
      //
      // @param name 节点名称
      // @param attributes 属性列表
      // @return 节点对象
      //============================================================
      public override FXmlNode CreateNode(string name, FAttributes attributes) {
         FXmlNode node = base.CreateNode(name, attributes);
         Nodes.Push(node);
         return node;
      }

      //============================================================
      // <T>在元素列表尾部追加一个元素对象。</T>
      //
      // @param element 元素对象
      //============================================================
      public override void Push(FXmlElement element) {
         base.Push(element);
         if(element is FXmlNode) {
            Nodes.Push((FXmlNode)element);
         }
      }

      //============================================================
      public void SortByAttribute(string attributeName) {
         if (null != _elements) {
            _elements.Sort(new FXmlNodeAttributeComparer(attributeName));
         }
         if (null != _nodes) {
            _nodes.Sort(new FXmlNodeAttributeComparer(attributeName));
         }
      }

      //============================================================
      // <T>查找指定节点，如果找不到则追加一个节点对象。</T>
      //
      // @param name 节点名称
      // @param args 属性列表
      // @return 节点对象
      //============================================================
      public FXmlNode Sync(string name, params string[] args) {
         FXmlNode node = Find(name, args);
         if(node == null) {
            node = CreateNode(name);
            // 检查属性列表
            if(null != args) {
               int count = args.Length;
               if(count > 0) {
                  if(1 == (count % 2)) {
                     throw new FFatalException("Invalid parameter count. (count={0})", count);
                  }
                  for(int n = 0; n < count; n += 2) {
                     node[args[n]] = args[n + 1];
                  }
               }
            }
         }
         return node;
      }

      //============================================================
      // <T>清除所有子节点。</T>
      //============================================================
      public override void NodeClear() {
         base.NodeClear();
         if (null != _nodes) {
            _nodes.Clear();
         }
      }
      
      //============================================================
      // <T>清除所有内容。</T>
      //============================================================
      public override void Clear() {
         base.Clear();
         if(_nodes != null) {
            _nodes.Clear();
         }
      }

      //============================================================
      // <T>获得调试转储信息。</T>
      //
      // @param info 转储信息
      // @return 转储信息
      //============================================================
      public override FDumpInfo Dump(FDumpInfo info) {
         RDump.StartDump(info);
         FXmlNode parent = (FXmlNode)info.Instance;
         // 转存名称
         info.Append(parent.Name);
         // 转存属性列表
         if(parent.HasAttribute()) {
            info.Append(" [");
            foreach(IPair<string, string> pair in parent.Attributes) {
               if(!pair.IsFirst()) {
                  info.Append(", ");
               }
               info.Append(pair.Name, "=", pair.Value);
            }
            info.Append("]");
         }
         // 转存内容
         if(parent.HasText()) {
            info.Append("{");
            info.Append(parent.Text);
            info.Append("}");
         }
         // 转存元素列表
         if(parent.HasElement()) {
            info.AppendLine();
            foreach(FXmlElement element in parent.Elements) {
               info.IncreaseDeep(element);
               Dump(info);
               info.DecreaseDeep();
            }
         }
         return info;
      }
   }
}
