using System;
using System.Reflection;
using MO.Common.Lang;

namespace MO.Common.Content
{
   //============================================================
   // <T>对象文档。</T>
   //============================================================
   public class FXmlObjectDocument : IXmlObjectDocument
   {
      // 日志对象
      private static ILogger _logger = RLogger.Find(typeof(FXmlObjectDocument));

      // 对象环境
      private FXmlObjectContext _context = new FXmlObjectContext();

      // 对象设置集合
      private FXmlObjectConfigs _configs = new FXmlObjectConfigs();

      //============================================================
      // <T>构造对象文档。</T>
      //============================================================
      public FXmlObjectDocument() {
         _context._document = this;
      }

      //============================================================
      // <T>获得环境对象。</T>
      //
      // @return 环境对象
      //============================================================
      public FXmlObjectContext Context {
         get { return _context; }
      }

      //============================================================
      // <T>获得对象设置集合。</T>
      //
      // @return 对象设置集合
      //============================================================
      public FXmlObjectConfigs Configs {
         get { return _configs; }
      }

      //============================================================
      // <T>加载设置信息。</T>
      //
      // @param config 加载设置信息
      //============================================================
      public void LoadConfigNode(FXmlNode config) {
         if(config.HasNode()) {
            foreach(FXmlNode node in config.Nodes) {
               if(node.IsName(FXmlObjectConfig.TAG)) {
                  FXmlObjectConfig nodeConfig = new FXmlObjectConfig();
                  nodeConfig.LoadConfig(node);
                  _configs.Push(nodeConfig);
               }
            }
         }
      }

      //============================================================
      // <T>加载设置文件。</T>
      //
      // @param fileName 文件名称
      //============================================================
      public void LoadConfigFile(string fileName) {
         FXmlDocument document = new FXmlDocument();
         document.LoadFile(fileName);
         LoadConfigNode(document.Root);
      }

      //============================================================
      // <T>加载模块资源。</T>
      //
      // @param assembly 模块对象
      // @param name 资源名称
      //============================================================
      public void LoadConfigResource(Assembly assembly, string name) {
         FXmlDocument document = new FXmlDocument();
         document.LoadResource(assembly, name);
         LoadConfigNode(document.Root);
      }

      //============================================================
      // <T>加载类型资源。</T>
      //
      // @param type 类型对象
      //============================================================
      public void LoadConfigResource(Type type) {
         FXmlDocument document = new FXmlDocument();
         document.LoadResource(type);
         LoadConfigNode(document.Root);
      }

      //============================================================
      // <T>加载对象节点设置。</T>
      //
      // @param parent 父对象
      // @param nodes 节点列表
      //============================================================
      protected virtual void LoadNodes(IXmlObject parent, FXmlNodes nodes) {
         if((null != nodes) && !nodes.IsEmpty()) {
            foreach(FXmlNode node in nodes) {
               FXmlObjectConfig config = _configs[node.Name];
               if(null != config) {
                  // 创建实例
                  IXmlObject instance = config.CreateInstance<IXmlObject>();
                  _context._parent = parent;
                  _context._config = node;
                  instance.LoadConfig(_context);
                  if(null != parent) {
                     parent.Push(instance);
                  }
                  // 加载子节点
                  if(node.HasNode()) {
                     LoadNodes(instance, node.Nodes);
                  }
               }
            }
         }
      }

      //============================================================
      // <T>加载节点成为一个文档对象。</T>
      //
      // @param T 对象类型
      // @param node 设置节点
      // @return 文档对象
      //============================================================
      public T LoadNode<T>(FXmlNode node)
            where T : IXmlObject {
         FXmlObjectConfig config = _configs[node.Name];
         if(null != config) {
            // 创建实例
            T instance = config.CreateInstance<T>();
            _context._parent = null;
            _context._config = node;
            instance.LoadConfig(_context);
            // 加载子节点
            if(node.HasNode()) {
               LoadNodes(instance, node.Nodes);
            }
            return instance;
         }
         return default(T);
      }

      //============================================================
      // <T>加载文件成为一个文档对象。</T>
      //
      // @param T 对象类型
      // @param fileName 文件名称
      // @return 文档对象
      //============================================================
      public T LoadFile<T>(string fileName)
            where T : IXmlObject {
         FXmlDocument document = new FXmlDocument();
         document.LoadFile(fileName);
         return LoadNode<T>(document.Root);
      }

      //============================================================
      // <T>为一个文档对象加载文件。</T>
      //
      // @param top 文档对象
      // @param fileName 文件名称
      //============================================================
      public void LoadFile(IXmlObject top, string filename) {
         if(_logger.DebugAble) {
            _logger.Debug(this, "LoadFile", "Load {0}={1}", top, filename);
         }
         FXmlDocument document = new FXmlDocument();
         _context._top = top;
         document.LoadFile(filename);
         LoadNodes(top, document.Root.Nodes);
      }

      //============================================================
      // <T>加载资源成为一个文档对象。</T>
      //
      // @param T 对象类型
      // @param assembly 模块对象
      // @param name 资源名称
      // @return 文档对象
      //============================================================
      public T LoadResource<T>(Assembly assembly, string name)
            where T : IXmlObject {
         FXmlDocument document = new FXmlDocument();
         document.LoadResource(assembly, name);
         return LoadNode<T>(document.Root);
      }


      //============================================================
      // <T>保存对象为节点。</T>
      //
      // @param parent 父对象
      // @param node 设置节点
      //============================================================
      protected virtual void SaveNodes(IXmlObject parent, FXmlNode node) {
         /*if (null != parent.Children) {
            foreach (IXmlObject child in parent.Children) {
               FXmlObjectConfig config = _configs.FindByType(child);
               FXmlNode childNode = node.CreateNode(config.Name);
               // Instance
               _context._parent = parent;
               _context._config = childNode;
               child.SaveConfig(_context);
               // Children
               if (child.Children != null) {
                  SaveNodes(child, childNode);
               }
            }
         }*/
      }

      //============================================================
      // <T>保存对象为文件。</T>
      //
      // @param top 顶层对象
      // @param fileName 文件名称
      //============================================================
      public void SaveFile(IXmlObject top, string fileName) {
         if(_logger.DebugAble) {
            _logger.Debug(this, "SaveFile", "Save {0}={1}", top, fileName);
         }
         _context._top = top;
         // 保存文件
         FXmlDocument document = new FXmlDocument();
         document.Root.Name = "Configuration";
         SaveNodes(top, document.Root);
         document.SaveFile(fileName);
      }
   }
}
