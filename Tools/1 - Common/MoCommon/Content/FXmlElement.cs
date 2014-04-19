using System;
using MO.Common.Collection;
using MO.Common.IO;
using MO.Common.Lang;

namespace MO.Common.Content
{
   //============================================================
   // <T>文档元素。</T>
   //============================================================
   public class FXmlElement : IDump
   {
      // 文档对象
      internal FXmlDocument _document;

      // 类型
      internal EXmlElementType _type;

      // 名称
      internal string _name;

      // 内容
      internal string _text;

      // 属性集合
      internal FAttributes _attributes;

      // 元素集合
      internal FXmlElements _elements;

      //============================================================
      // <T>构造文档元素。</T>
      //============================================================
      public FXmlElement() {
         _name = RXml.NODE_NAME;
      }

      //============================================================
      // <T>构造文档元素。</T>
      //
      // @param name 名称
      //============================================================
      public FXmlElement(string name) {
         _name = name;
      }

      //============================================================
      // <T>构造文档元素。</T>
      //
      // @param name 名称
      // @param attributes 属性集合
      //============================================================
      public FXmlElement(string name, FAttributes attributes) {
         _name = name;
         _attributes = attributes;
      }

      //============================================================
      // <T>获得文档对象。</T>
      //
      // @return 文档对象
      //============================================================
      public FXmlDocument Document {
         get { return _document; }
      }

      //============================================================
      // <T>获得类型。</T>
      //
      // @return 类型
      //============================================================
      public EXmlElementType Type {
         get { return _type; }
      }

      //============================================================
      // <T>是否为指定名称。</T>
      //
      // @return 是否
      //============================================================
      public bool IsName(string name) {
         return _name.Equals(name, StringComparison.CurrentCultureIgnoreCase);
      }

      //============================================================
      // <T>获得或设置名称。</T>
      //
      // @param value 名称
      // @return 名称
      //============================================================
      public string Name {
         get { return _name; }
         set { _name = value; }
      }

      //============================================================
      // <T>是否含有文本。</T>
      //
      // @return 是否含有
      //============================================================
      public bool HasText() {
         return !RString.IsEmpty(_text);
      }

      //============================================================
      // <T>获得或设置文本内容。</T>
      //
      // @param value 文本内容
      // @return 文本内容
      //============================================================
      public string Text {
         get { return _text; }
         set { _text = value; }
      }

      //============================================================
      // <T>获得两边没有空内容的文本内容。</T>
      //
      // @return 文本内容
      //============================================================
      public string TextTrim {
         get { return (null != _text) ? Text.Trim() : null; }
      }

      //============================================================
      // <T>判断对象名称和属性列表内容是否相等。</T>
      //
      // @param name 属性名称
      // @param args 属性列表[(属性名称，属性内容)...]
      // @return 属性内容
      //============================================================
      public bool Equals(string name, params string[] args) {
         // 检查名称
         if(null != name) {
            if(!_name.Equals(name, StringComparison.CurrentCultureIgnoreCase)) {
               return false;
            }
         }
         // 检查属性列表
         if(null != args) {
            int count = args.Length;
            if(1 == (count % 2)) {
               throw new FFatalException("Invalid parameter count. (count={0})", count);
            }
            for(int n = 0; n < count; n += 2) {
               if(!String.Equals(args[n + 1], Get(args[n]), StringComparison.CurrentCultureIgnoreCase)) {
                  return false;
               }
            }
         }
         return true;
      }

      //============================================================
      // <T>根据属性名称，获得或设置属性内容。</T>
      //
      // @param name 属性名称
      // @param value 属性内容
      // @return 属性内容
      //============================================================
      public string this[string name] {
         get { return Get(name); }
         set { Set(name, value); }
      }

      //============================================================
      // <T>是否含有属性。</T>
      //
      // @return 是否含有
      //============================================================
      public bool HasAttribute() {
         return (null != _attributes) ? (_attributes.Count > 0) : false;
      }

      //============================================================
      // <T>是否含有属性名称。</T>
      //
      // @param name 属性名称
      // @return 是否含有
      //============================================================
      public bool Contains(string name) {
         return (null != _attributes) ? _attributes.Contains(name) : false;
      }

      //============================================================
      // <T>获得属性列表。</T>
      //
      // @return 属性列表
      //============================================================
      public FAttributes Attributes {
         get {
            if(null == _attributes) {
               _attributes = new FAttributes();
            }
            return _attributes;
         }
      }

      //============================================================
      // <T>是否为指定属性内容。</T>
      //
      // @return 是否
      //============================================================
      public bool IsAttribute(string name, string value) {
         if(null != _attributes) {
            if(value.Equals(_attributes.Get(name))) {
               return true;
            }
         }
         return false;
      }

      //============================================================
      // <T>根据属性名称，获得属性内容。</T>
      //
      // @param name 属性名称
      // @return 属性内容
      //============================================================
      public string Nvl(string name) {
         return _attributes.Find(name);
      }

      //============================================================
      // <T>根据属性名称，获得属性内容。</T>
      //
      // @param name 属性名称
      // @return 属性内容
      //============================================================
      public string Get(string name) {
         return (null != _attributes) ? _attributes[name] : null;
      }

      //============================================================
      // <T>根据属性名称，获得属性内容。</T>
      //
      // @param name 属性名称
      // @return 属性内容
      //============================================================
      public string Get(string name, string defaultValue) {
         if(null != _attributes) {
            string value = _attributes.Find(name);
            if(!RString.IsEmpty(value)) {
               return value;
            }
         }
         return defaultValue;
      }

      //============================================================
      // <T>根据属性名称，获得布尔属性内容。</T>
      //
      // @param name 属性名称
      // @return 属性内容
      //============================================================
      public bool GetBoolean(string name) {
         return RBool.IsTrue(Get(name));
      }

      //============================================================
      // <T>根据属性名称，获得整数属性内容。</T>
      //
      // @param name 属性名称
      // @param defaultValue 默认内容
      // @return 属性内容
      //============================================================
      public bool GetBoolean(string name, bool defaultValue) {
         if(null != _attributes) {
            string value = _attributes.Find(name);
            if(!RString.IsEmpty(value)) {
               return RBool.IsTrue(value);
            }
         }
         return defaultValue;
      }

      //============================================================
      // <T>根据属性名称，获得整数属性内容。</T>
      //
      // @param name 属性名称
      // @return 属性内容
      //============================================================
      public int GetInteger(string name) {
         return RInt.Parse(Get(name));
      }

      //============================================================
      // <T>根据属性名称，获得整数属性内容。</T>
      //
      // @param name 属性名称
      // @return 属性内容
      //============================================================
      public int GetHex(string name, int defaultValue = 0) {
         return RInt.ParseHex(Get(name, null), defaultValue);
      }

      //============================================================
      // <T>根据属性名称，获得整数属性内容。</T>
      //
      // @param name 属性名称
      // @param defaultValue 默认内容
      // @return 属性内容
      //============================================================
      public int GetInteger(string name, int defaultValue) {
         if(null != _attributes) {
            string value = _attributes.Find(name);
            if(!RString.IsEmpty(value)) {
               return RInt.Parse(value);
            }
         }
         return defaultValue;
      }

      //============================================================
      // <T>根据属性名称，获得长整数属性内容。</T>
      //
      // @param name 属性名称
      // @return 属性内容
      //============================================================
      public long GetLong(string name) {
         return RLong.Parse(Get(name));
      }

      //============================================================
      // <T>根据属性名称，获得长整数属性内容。</T>
      //
      // @param name 属性名称
      // @param defaultValue 默认内容
      // @return 属性内容
      //============================================================
      public long GetLong(string name, long defaultValue) {
         if(_attributes != null) {
            string value = _attributes.Find(name);
            if(!RString.IsEmpty(value)) {
               return RLong.Parse(value);
            }
         }
         return defaultValue;
      }

      //============================================================
      // <T>根据属性名称，获得浮点属性内容。</T>
      //
      // @param name 属性名称
      // @return 属性内容
      //============================================================
      public float GetFloat(string name) {
         return RFloat.Parse(Get(name));
      }

      //============================================================
      // <T>根据属性名称，获得整数属性内容。</T>
      //
      // @param name 属性名称
      // @param defaultValue 默认内容
      // @return 属性内容
      //============================================================
      public float GetFloat(string name, float defaultValue) {
         if(null != _attributes) {
            string value = _attributes.Find(name);
            if(!RString.IsEmpty(value)) {
               return RFloat.Parse(value);
            }
         }
         return defaultValue;
      }

      //============================================================
      // <T>根据属性名称，设置布尔属性内容。</T>
      //
      // @param name 属性名称
      // @param value 属性内容
      // @return 属性内容
      //============================================================
      public void Set(string name, bool value) {
         Attributes.Set(name, RBool.ToString(value));
      }

      //============================================================
      // <T>根据属性名称，设置整数属性内容。</T>
      //
      // @param name 属性名称
      // @param value 属性内容
      // @return 属性内容
      //============================================================
      public void Set(string name, int value) {
         Attributes.Set(name, value.ToString());
      }

      //============================================================
      // <T>根据属性名称，设置长整数属性内容。</T>
      //
      // @param name 属性名称
      // @param value 属性内容
      // @return 属性内容
      //============================================================
      public void Set(string name, long value) {
         Attributes.Set(name, value.ToString());
      }

      //============================================================
      // <T>根据属性名称，设置浮点属性内容。</T>
      //
      // @param name 属性名称
      // @param value 属性内容
      // @return 属性内容
      //============================================================
      public void Set(string name, float value) {
         Attributes.Set(name, value.ToString());
      }

      //============================================================
      // <T>根据属性名称，设置属性内容。</T>
      //
      // @param name 属性名称
      // @param value 属性内容
      // @return 属性内容
      //============================================================
      public void Set(string name, string value) {
         Attributes.Set(name, value);
      }

      //============================================================
      // <T>根据属性名称，设置非空属性内容。</T>
      //
      // @param name 属性名称
      // @param value 属性内容
      // @param defaultValue 默认内容
      // @return 属性内容
      //============================================================
      public void SetNvl(string name, bool value, bool defaultValue = false) {
         if (value != defaultValue) {
            Attributes.Set(name, value);
         }
      }

      //============================================================
      // <T>根据属性名称，设置非空属性内容。</T>
      //
      // @param name 属性名称
      // @param value 属性内容
      // @param defaultValue 默认内容
      // @return 属性内容
      //============================================================
      public void SetNvl(string name, int value, int defaultValue = 0) {
         if (value != defaultValue) {
            Attributes.Set(name, value);
         }
      }

      //============================================================
      // <T>根据属性名称，设置非空属性内容。</T>
      //
      // @param name 属性名称
      // @param value 属性内容
      // @param defaultValue 默认内容
      // @return 属性内容
      //============================================================
      public void SetNvl(string name, float value, float defaultValue = 0.0f) {
         if (value != defaultValue) {
            Attributes.Set(name, value);
         }
      }

      //============================================================
      // <T>根据属性名称，设置非空属性内容。</T>
      //
      // @param name 属性名称
      // @param value 属性内容
      // @param defaultValue 默认内容
      // @return 属性内容
      //============================================================
      public void SetNvl(string name, string value, string defaultValue = null) {
         if (!RString.IsEmpty(value)) {
            if (value != defaultValue) {
               Attributes.Set(name, value);
            }
         }
      }

      //============================================================
      // <T>是否含有元素。</T>
      //
      // @return 是否含有
      //============================================================
      public bool HasElement() {
         return (null != _elements) ? (_elements.Count > 0) : false;
      }

      //============================================================
      // <T>是否含有元素名称。</T>
      //
      // @param name 元素名称
      // @return 是否含有
      //============================================================
      public bool ContainsElement(string name) {
         return (_elements != null) ? (FindElement(name) != null) : false;
      }

      //============================================================
      // <T>获得元素列表。</T>
      //
      // @return 元素列表
      //============================================================
      public FXmlElements Elements {
         get {
            if(_elements == null) {
               _elements = new FXmlElements();
               _elements._document = Document;
            }
            return _elements;
         }
      }

      //============================================================
      // <T>统计指定类型的元素个数。</T>
      //
      // @return 元素个数
      //============================================================
      public int ElementCount(EXmlElementType typeCd) {
         int count = 0;
         if(null != _elements) {
            foreach(FXmlElement element in _elements) {
               if(element.Type == typeCd) {
                  count++;
               }
            }
         }
         return count;
      }

      //============================================================
      // <T>在当前元素下，查找一个指定名称的元素对象。</T>
      //
      // @param name 元素名称
      // @return 元素对象
      //============================================================
      public FXmlElement FindElement(string name) {
         return (null != _elements) ? _elements.Find(name) : null;
      }

      //============================================================
      // <T>在当前元素下，创建一个文档节点对象。</T>
      //
      // @return 节点对象
      //============================================================
      public FXmlNode CreateNode() {
         return CreateNode("Node");
      }
      
      //============================================================
      // <T>在当前元素下，创建一个文档节点对象。</T>
      //
      // @param name 节点名称
      // @return 节点对象
      //============================================================
      public virtual FXmlNode CreateNode(string name) {
         FXmlNode node = null;
         if (_document == null) {
            node = new FXmlNode(name);
         } else {
            node = _document.NodeFactory.CreateNode(name, null);
         }
         Elements.Push(node);
         return node;
      }

      //============================================================
      // <T>在当前元素下，创建一个节点对象。</T>
      //
      // @param name 节点名称
      // @param attributes 属性列表
      // @return 节点对象
      //============================================================
      public virtual FXmlNode CreateNode(string name, FAttributes attributes) {
         FXmlNode node = _document.NodeFactory.CreateNode(name, attributes);
         Elements.Push(node);
         return node;
      }

      //============================================================
      // <T>在当前元素下，创建一个描述对象。</T>
      //
      // @return 描述对象
      //============================================================
      public FXmlComment CreateComment() {
         FXmlComment comment = _document.NodeFactory.CreateComment();
         Elements.Push(comment);
         return comment;
      }

      //============================================================
      // <T>在元素列表尾部追加一个元素对象。</T>
      //
      // @param element 元素对象
      //============================================================
      public virtual void Push(FXmlElement element) {
         Elements.Push(element);
      }

      //============================================================
      // <T>生成XML字符串。</T>
      //
      // @param xml 字符串对象
      // @param element 元素对象
      //============================================================
      protected void InnerMakeXml(FString xml, FXmlElement element) {
         xml.Append("<");
         xml.Append(element.Name);
         if(element.HasAttribute()) {
            foreach(IStringPair pair in element.Attributes) {
               xml.Append(" ");
               xml.Append(pair.Name);
               xml.Append("=\"");
               xml.Append(pair.Value);
               xml.Append("\"");
            }
         }
         if(element.HasElement()) {
            xml.Append(">");
            xml.Append(element.Text);
            foreach(FXmlElement child in element.Elements) {
               InnerMakeXml(xml, child);
            }
            xml.Append("</");
            xml.Append(element.Name);
            xml.Append(">");
         } else {
            if(element.HasText()) {
               xml.Append(">");
               xml.Append(element.Text);
               xml.Append("</");
               xml.Append(element.Name);
               xml.Append(">");
            } else {
               xml.Append("/>");
            }
         }
      }

      //============================================================
      // <T>获得XML字符串。</T>
      //
      // @param xml 字符串对象
      //============================================================
      public void GetXml(FString xml) {
         InnerMakeXml(xml, this);
      }

      //============================================================
      // <T>获得XML字符串。</T>
      //
      // @return 字符串
      //============================================================
      public FString Xml {
         get {
            FString xml = new FString();
            InnerMakeXml(xml, this);
            return xml;
         }
      }

      //============================================================
      // <T>序列化内容到输出流内。</T>
      // <P>只处理结点类型数据。</P>
      //
      // @param output 输出流
      // @param element 元素对象
      //============================================================
      protected void InnerSerialize(IOutput output, FXmlElement element) {
         if(element.Type == EXmlElementType.Node){
            output.WriteUTFString(element.Name);
            output.WriteUTFString(element.Text);
            if(element.HasAttribute()) {
               output.WriteInt16((short)element.Attributes.Count);
               foreach(IStringPair pair in element.Attributes) {
                  output.WriteUTFString(pair.Name);
                  output.WriteUTFString(pair.Value);
               }
            } else {
               output.WriteInt16(0);
            }
            int nodeCount = element.ElementCount(EXmlElementType.Node);
            output.WriteInt16((short)nodeCount);
            if(nodeCount > 0) {
               foreach(FXmlElement child in element.Elements) {
                  if(child.Type == EXmlElementType.Node) {
                     InnerSerialize(output, child);
                  }
               }
            }
         }
      }

      //============================================================
      // <T>序列化内容到输出流内。</T>
      //
      // @param output 输出流
      //============================================================
      public void Serialize(IOutput output) {
         InnerSerialize(output, this);
      }

      //============================================================
      // <T>清除子节点。</T>
      //============================================================
      public virtual void NodeClear() {
         // 清除元素列表
         if (null != _elements) {
            _elements.Clear();
         }
      }
      
      //============================================================
      // <T>清除所有内容。</T>
      //============================================================
      public virtual void Clear() {
         _text = null;
         // 清除属性列表
         if(null != _attributes) {
            _attributes.Clear();
         }
         // 清除元素列表
         if(null != _elements) {
            _elements.Clear();
         }
      }

      //============================================================
      // <T>生成字符串。</T>
      //
      // @return 字符串
      //============================================================
      public override string ToString() {
         return Xml.ToString();
      }

      #region IDump implements

      //============================================================
      // <T>获得调试转储信息。</T>
      //
      // @return 转储信息
      //============================================================
      public FDumpInfo Dump() {
         return Dump(new FDumpInfo(this));
      }

      //============================================================
      // <T>获得调试转储信息。</T>
      //
      // @param info 转储信息
      // @return 转储信息
      //============================================================
      public virtual FDumpInfo Dump(FDumpInfo info) {
         RDump.StartDump(info);
         FXmlElement parent = (FXmlElement)info.Instance;
         if(parent.HasElement()) {
            foreach(FXmlElement element in parent.Elements) {
               info.IncreaseDeep(element);
               Dump(info);
               info.DecreaseDeep();
            }
         }
         return info;
      }

      #endregion
   }
}
