using MS.Common.IO;
using MS.Common.Lang;
using MS.Common.Reflection;
using MS.Common.System;
using System;
using System.IO;
using System.Reflection;
using System.Xml;

namespace MS.Common.Content
{
   //============================================================
   // <T>文档。</T>
   //
   // @history MAOCY 140414
   //============================================================
   public class FXmlDocument : FObject, IDisposable
   {
      // 日志输出接口
      private static ILogger _logger = RLogger.Find(typeof(FXmlDocument));

      // 文件名
      internal string _fileName;

      // 节点工厂
      internal IXmlNodeFactory _nodeFactory;

      // 根元素
      internal FXmlElement _element;

      // 根节点
      internal FXmlNode _root;

      //============================================================
      // <T>构造文档。</T>
      //============================================================
      public FXmlDocument() {
         InnerConstruct();
      }

      //============================================================
      // <T>构造文档。</T>
      //
      // @param filename 文件名称
      //============================================================
      public FXmlDocument(string fileName) {
         InnerConstruct();
         LoadFile(fileName);
      }

      //============================================================
      // <T>构造文档。</T>
      //
      // @param assembly 模块对象
      // @param name 资源名称
      //============================================================
      public FXmlDocument(Assembly assembly, string name) {
         InnerConstruct();
         LoadResource(assembly, name);
      }

      //============================================================
      // <T>构造文档。</T>
      //
      // @param assembly 模块对象
      // @param name 资源名称
      //============================================================
      protected void InnerConstruct() {
         // 构造根节点
         _root = new FXmlNode(RXml.ROOT_NAME);
         _root._document = this;
         // 构造根元素
         _element = new FXmlElement();
         _element._document = this;
         _element.Push(_root);
      }

      //============================================================
      // <T>获得或设置节点类工厂。</T>
      //
      // @param value 节点类工厂
      // @return 节点类工厂
      //============================================================
      public IXmlNodeFactory NodeFactory {
         get {
            if(null == _nodeFactory) {
               _nodeFactory = new FXmlNodeFactory(this);
            }
            return _nodeFactory;
         }
         set { _nodeFactory = value; }
      }

      //============================================================
      // <T>获得或设置文件名。</T>
      //
      // @param value 文件名
      // @return 文件名
      //============================================================
      public string FileName {
         get { return _fileName; }
         set { _fileName = value; }
      }

      //============================================================
      // <T>获得或设置根节点。</T>
      //============================================================
      public FXmlNode Root {
         get { return _root; }
         set {
            value._document = this;
            // 设置根节点
            _root = value;
            // 设置根元素
            _element.Clear();
            _element.Push(value);
         }
      }

      //============================================================
      // <T>获得根元素。</T>
      //
      // @return 根元素
      //============================================================
      public FXmlElement Element {
         get { return _element; }
      }

      //============================================================
      // <T>获得元素集合。</T>
      //
      // @return 元素集合
      //============================================================
      public FXmlElements Elements {
         get { return _element.Elements; }
      }

      //============================================================
      // <T>加载文件。</T>
      // 
      // @param fileName 文件名
      //============================================================
      public void LoadFile(string fileName) {
         _fileName = fileName;
         // 加载文件
         try{
            XmlDocument xdoc = new XmlDocument();
            xdoc.Load(_fileName);
            LoadDocument(xdoc);
         }catch (Exception ex){
            _logger.Error(this, "LoadFile", ex, "Load xml document failure. (file_name={0})", fileName);
            throw new FFatalException(ex);
         }
      }

      //============================================================
      // <T>加载资源。</T>
      //
      // @param assembly 模块名称
      // @param name 资源名称
      //============================================================
      public void LoadResource(Assembly assembly, string name) {
         LoadStream(RAssembly.LoadResource(assembly, name));
      }

      //============================================================
      // <T>加载资源。</T>
      //
      // @param type 类型对象
      //============================================================
      public void LoadResource(Type type) {
         LoadStream(RAssembly.LoadTypeResource(type));
      }

      //============================================================
      // <T>加载文档。</T>
      //
      // @param xdoc 文档对象
      //============================================================
      public void LoadDocument(XmlDocument xdoc) {
         _element.Clear();
         RXml.SyncNodeFromElements(_element, xdoc.ChildNodes);
         _root = RXml.FindNode(_element.Elements);
      }

      //============================================================
      // <T>加载数据流。</T>
      //
      // @param stream 数据流
      //============================================================
      public void LoadStream(Stream stream) {
         XmlDocument xdoc = new XmlDocument();
         xdoc.Load(stream);
         LoadDocument(xdoc);
      }

      //============================================================
      // <T>保存为文件。</T>
      //
      // @param fileName 文件名
      //============================================================
      public void SaveFile(string fileName) {
         XmlDocument doc = new XmlDocument();
         doc.AppendChild(doc.CreateXmlDeclaration("1.0", "utf-8", null));
         // 追加节点
         foreach(FXmlElement element in _element.Elements) {
            if(element is FXmlComment) {
               doc.AppendChild(doc.CreateComment(element.Text));
            } else {
               doc.AppendChild(doc.CreateElement(_root.Name));
               RXml.SyncElementFromNode(element, doc.DocumentElement);
            }
         }
         // 保存文件
         doc.Save(fileName);
      }

      //============================================================
      // <T>保存。</T>
      //============================================================
      public void Store() {
         SaveFile(_fileName);
      }

      //============================================================
      // <T>序列化到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public void SerializeOutput(IDataOutput output) {
         // 追加节点
         foreach(FXmlElement element in _element.Elements) {
            if(element is FXmlNode) {
               element.Serialize(output);
            }
         }
      }

      //============================================================
      // <T>释放资源。</T>
      //============================================================
      public void Dispose() {
      }
   }
}
