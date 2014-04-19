using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using System;

namespace MO.Core.Content.Catalog
{
   //============================================================
   // <T>设置文件夹。</T>
   //============================================================
   public class FCfgFolder : FCfgObject
   {
      // 父目录
      protected FCfgFolder _parent;

      // 目录名称
      protected string _directory;

      // 子文件夹集合
      protected FObjects<FCfgFolder> _folders;

      // 子资源集合
      protected FObjects<FCfgComponent> _objects;

      //============================================================
      // <T>构造设置文件夹。</T>
      //============================================================
      public FCfgFolder() {
      }

      //============================================================
      // <T>获得或设置父目录。</T>
      //============================================================
      public FCfgFolder Parent {
         get { return _parent; }
         set { _parent = value; }
      }

      //============================================================
      // <T>获得或设置目录名称。</T>
      //============================================================
      public string Directory {
         get { return _directory; }
         set { _directory = value; }
      }

      //============================================================
      // <T>获得目录设置文件名称。</T>
      //============================================================
      public string ConfigFileName {
         get { return _directory + @"\config.xml"; }
      }

      //============================================================
      // <T>判断是否含有子目录。</T>
      //
      // @return 是否含有
      //============================================================
      public bool HasFolder() {
         return (null != _folders) ? !_folders.IsEmpty() : false;
      }

      //============================================================
      // <T>获得子目录个数。</T>
      //
      // @return 子目录个数
      //============================================================
      public int FolderCount {
         get { return (_folders != null) ? _folders.Count : 0; }
      }

      //============================================================
      // <T>获得子目录集合。</T>
      //
      // @return 子目录集合
      //============================================================
      public FObjects<FCfgFolder> Folders {
         get {
            if (_folders == null) {
               _folders = new FObjects<FCfgFolder>();
            }
            return _folders;
         }
      }

      //============================================================
      // <T>获得指定索引位置的子目录。</T>
      //
      // @param index 索引位置
      // @return 子目录
      //============================================================
      public FCfgFolder GetFolder(int index) {
         return (_folders != null) ? _folders.Get(index) : null;
      }

      //============================================================
      // <T>根据名称查找子目录。</T>
      //
      // @param name 名称
      // @return 子目录
      //============================================================
      public FCfgFolder FindFolder(string name) {
         foreach (FCfgFolder folder in _folders) {
            if (String.Equals(folder.Name, name, StringComparison.CurrentCultureIgnoreCase)) {
               return folder;
            }
         }
         return null;
      }

      //============================================================
      // <T>增加一个子目录。</T>
      //
      // @param folder 子目录
      //============================================================
      public void PushFolder(FCfgFolder folder) {
         if (folder != null) {
            folder.Parent = this;
            Folders.Push(folder);
         }
      }

      //============================================================
      // <T>获取顶层目录。</T>
      //
      // @return 目录
      //============================================================
      public FCfgFolder FetchFolderTop() {
         FCfgFolder find = this;
         while (find != null) {
            if (find.Parent == null) {
               return find;
            }
            find = find.Parent;
         }
         return null;
      }

      //============================================================
      // <T>获取相对目录。</T>
      //
      // @return 目录
      //============================================================
      public string FolderPath() {
         FCfgFolder folder = FetchFolderTop();
         return _directory.Substring(folder.Directory.Length);
      }

      //============================================================
      // <T>获取层级目录集合。</T>
      //
      // @return 目录集合
      //============================================================
      public FObjects<FCfgFolder> FetchFolderStack(bool constainsSelf = true) {
         FObjects<FCfgFolder> stack = new FObjects<FCfgFolder>();
         FCfgFolder find = this;
         while (find != null) {
            if (constainsSelf) {
               stack.Push(find);
            } else if (find != this) {
               stack.Push(find);
            }
            find = find.Parent;
         }
         stack.Reverse();
         return stack;
      }

      //============================================================
      // <T>判断是否含有子对象。</T>
      //
      // @return 是否含有
      //============================================================
      public bool HasObject() {
         return (_objects != null) ? !_objects.IsEmpty() : false;
      }

      //============================================================
      // <T>获得子对象个数。</T>
      //
      // @return 子对象个数
      //============================================================
      public int ObjectCount {
         get { return (_objects != null) ? _objects.Count : 0; }
      }

      //============================================================
      // <T>获得子对象集合。</T>
      //
      // @return 子对象集合
      //============================================================
      public FObjects<FCfgComponent> Objects {
         get {
            if (_objects == null) {
               _objects = new FObjects<FCfgComponent>();
            }
            return _objects;
         }
      }

      //============================================================
      // <T>获得指定索引位置的子对象。</T>
      //
      // @param index 索引位置
      // @return 子对象
      //============================================================
      public FCfgObject GetObject(int index) {
         return (_objects != null) ? _objects.Get(index) : null;
      }

      //============================================================
      // <T>根据编号查找子对象。</T>
      //
      // @param id 编号
      // @return 子对象
      //============================================================
      public FCfgObject FindObjectById(int id) {
         if (_objects != null) {
            foreach (FCfgComponent component in _objects) {
               if (component.Id == id) {
                  return component;
               }
            }
         }
         return null;
      }

      //============================================================
      // <T>根据名称查找子对象。</T>
      //
      // @param name 名称
      // @return 子对象
      //============================================================
      public FCfgObject FindObjectByName(string name) {
         if (_objects != null) {
            foreach (FCfgObject component in _objects) {
               if (String.Equals(component.Name, name, StringComparison.CurrentCultureIgnoreCase)) {
                  return component;
               }
            }
         }
         return null;
      }

      //============================================================
      // <T>增加一个子对象。</T>
      //
      // @param component 子对象
      //============================================================
      public void PushObject(FCfgComponent component) {
         if (component != null) {
            Objects.Push(component);
         }
      }

      //============================================================
      // <T>创建一个子对象。</T>
      //
      // @param name 名称
      // @return 子对象
      //============================================================
      public virtual FCfgFolder CreateFolder(string name) {
         // 创建目录
         FCfgFolder folder = new FCfgFolder();
         folder.Name = name;
         folder.Directory = _directory + "\\" + name;
         PushFolder(folder);
         // 创建文件目录
         System.IO.Directory.CreateDirectory(folder.Directory);
         return folder;
      }

      //============================================================
      // <T>加载设置信息。</T>
      //
      // @param xconfig 设置节点
      //============================================================
      public override void LoadConfig(FXmlNode xconfig) {
         // 加载设置
         base.LoadConfig(xconfig);
         // 加载资源设置
         foreach (FXmlNode xnode in xconfig.Nodes) {
            if (xnode.IsName("Object")) {
               // 查找指定名称的对象
               if (xnode.Contains("id")) {
                  int id = xnode.GetInteger("id");
                  FCfgObject configObject = FindObjectById(id);
                  // 加载设置
                  if (configObject != null) {
                     configObject.LoadConfig(xnode);
                  }
               }
            }
         }
      }

      //============================================================
      // <T>保存设置信息。</T>
      //
      // @param xconfig 设置节点
      //============================================================
      public override void SaveConfig(FXmlNode xconfig) {
         base.SaveConfig(xconfig);
         // 存储资源列表
         if (_objects != null) {
            foreach (FCfgComponent component in _objects) {
               FXmlNode node = xconfig.CreateNode("Object");
               component.SaveConfig(node);
            }
         }
      }

      //============================================================
      // <T>加载目录信息。</T>
      //
      // @param path 目录
      //============================================================
      public virtual void OnLoadFolder(string path) {
         // 重设名称
         string name = path.Substring(path.LastIndexOf("\\") + 1);
         // 加载子目录
         FCfgFolder floder = CreateFolder(name);
         floder.Name = name;
         floder.Directory = path;
         floder.LoadFolderAll();
         // 增加到列表
         PushFolder(floder);
      }

      //============================================================
      // <T>加载所有目录。</T>
      //============================================================
      public virtual void LoadFolderAll() {
         // 加载所有子目录
         foreach (string path in RDirectory.ListDirectories(_directory)) {
            // 跳过SVN目录
            if (path.IndexOf(".svn") != -1) {
               continue;
            }
            // 加载子目录
            OnLoadFolder(path);
         }
         if (_folders != null) {
            _folders.Sort();
         }
         // 加载配置
         if (RFile.Exists(ConfigFileName)) {
            FXmlDocument xdoc = new FXmlDocument();
            try {
               xdoc.LoadFile(ConfigFileName);
            } catch (Exception e) {
               throw new FFatalException(e, "Open config file error. (file_name={0})", ConfigFileName);
            }
            LoadConfig(xdoc.Root);
         }
      }

      //============================================================
      // <T>加载全部处理。</T>
      //============================================================
      public virtual void LoadAll() {
         // 清空集合
         if (_folders != null) {
            _folders.Clear();
         }
         if (_objects != null) {
            _objects.Clear();
         }
         // 加载所有子目录
         foreach (string path in RDirectory.ListDirectories(_directory)) {
            // 跳过SVN目录
            if (path.IndexOf(".svn") != -1) {
               continue;
            }
            // 加载子目录
            OnLoadFolder(path);
         }
         if (_folders != null) {
            _folders.Sort();
         }
         // 加载配置
         if (RFile.Exists(ConfigFileName)) {
            FXmlDocument xdoc = new FXmlDocument();
            xdoc.LoadFile(ConfigFileName);
            LoadConfig(xdoc.Root);
         }
      }

      //============================================================
      // <T>保存处理。</T>
      //============================================================
      public void Save() {
         FXmlDocument xdoc = new FXmlDocument();
         SaveConfig(xdoc.Root);
         xdoc.SaveFile(ConfigFileName);
      }

      //============================================================
      // <T>保存所有处理。</T>
      //============================================================
      public void SaveAll() {
         // 存储信息
         Save();
         // 存储子目录信息
         if (_folders != null) {
            foreach (FCfgFolder folder in _folders) {
               folder.SaveAll();
            }
         }
      }

      //============================================================
      // <T>删除目录。</T>
      //============================================================
      public void Delete() {
         System.IO.Directory.Delete(_directory, true);
      }
   }
}
