using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content2d.Common;
using MO.Content2d.Resource.Music;
using MO.Content2d.Resource.Picture;
using MO.Content2d.Resource.Sound;
using System;

namespace MO.Content2d.Resource.Common
{
   //============================================================
   // <T>资源文件夹。<T>
   //============================================================
   public class FRsResourceFolder : FObject
   {
      // 标签
      protected string _label;

      // 全标签
      protected string _fullLabel;

      // 资源目录
      protected string _directory;

      // 设置文件
      protected string _configName;

      // 文件夹集合
      protected FObjects<FRsResourceFolder> _folders = new FObjects<FRsResourceFolder>();

      // 资源集合
      protected FObjects<FRsResource> _resources = new FObjects<FRsResource>();

      //============================================================
      // <T>构造资源文件夹。<T>
      //============================================================
      public FRsResourceFolder() {
      }

      //============================================================
      // <T>获得或设置标签。<T>
      //============================================================
      public string Label {
         get { return _label; }
         set { _label = value; }
      }

      //============================================================
      // <T>获得或设置全标签。<T>
      //============================================================
      public string FullLabel {
         get { return _fullLabel; }
         set { _fullLabel = value; }
      }

      //============================================================
      // <T>获得或设置文件夹目录。<T>
      //============================================================
      public string Directory {
         get { return _directory; }
         set { _directory = value; }
      }

      //============================================================
      // <T>获得文件夹集合。<T>
      //============================================================
      public FObjects<FRsResourceFolder> Folders {
         get { return _folders; }
      }

      //============================================================
      // <T>获得资源集合。<T>
      //============================================================
      public FObjects<FRsResource> Resources {
         get { return _resources; }
      }

      //============================================================
      // <T>根据编号，获得资源对象。<T>
      //
      // @param id 编号
      // @return 资源对象
      //============================================================
      public FRsResource FindResourceById(string id) {
         foreach (FRsResource resource in _resources) {
            if (resource.Name == id) {
               return resource;
            }
         }
         return null;
      }

      //============================================================
      // <T>根据代码，获得资源对象。<T>
      //
      // @param id 编号
      // @return 资源对象
      //============================================================
      public FRsResource FindResourceByCode(string code) {
         foreach (FRsResource resource in _resources) {
            if (resource.Name == code) {
               return resource;
            }
         }
         return null;
      }

      //============================================================
      // <T>扫描文件夹。</T>
      //============================================================
      public void Scan() {
         // 处理所有子文件
         FStrings fileNames = RDirectory.ListFiles(_directory);
         foreach (string fileName in fileNames) {
            if (-1 != fileName.IndexOf(".svn")) {
               continue;
            }
            // 解析资源
            string shortName = fileName.Substring(fileName.LastIndexOf("\\") + 1);
            string name = shortName.Substring(3);
            // 解析图片资源
            if (shortName.StartsWith("RP-")) {
               string fullName = name;
               string label = name;
               int codeIndex = name.IndexOf("-");
               int labelIndex = name.IndexOf(".png");
               if (-1 != codeIndex) {
                  label = name.Substring(0, labelIndex);
                  fullName = name.Substring(codeIndex + 1, labelIndex - codeIndex);
                  name = name.Substring(0, codeIndex);
               }
               if (11 == name.Length) {
                  name = name.Replace(".", "");
                  // 创建图片资源
                  int code = RInt.Parse(name);
                  FRsResourcePicture picture = RContent2dManager.ResourceConsole.CreateResource(ERsResource.Picture, code) as FRsResourcePicture;
                  picture.Folder = this;
                  picture.Code = code;
                  picture.Name = name;
                  picture.Label = label;
                  picture.FullLabel = FullLabel + "\\" + label;
                  picture.Keyword = label.Replace(".", "");
                  picture.FileName = fileName;
                  picture.Directory = _directory;
                  picture.Scan();
                  _resources.Push(picture);
               }
            }
            // 解析音乐资源
            if (shortName.StartsWith("RM-")) {
               string fullName = name;
               string label = name;
               int codeIndex = name.IndexOf("-");
               int labelIndex = name.IndexOf(".mp3");
               if (-1 != codeIndex) {
                  label = name.Substring(0, labelIndex);
                  fullName = name.Substring(codeIndex + 1, labelIndex - codeIndex);
                  name = name.Substring(0, codeIndex);
               }
               if (11 == name.Length) {
                  name = name.Replace(".", "");
                  // 创建音乐资源
                  int code = RInt.Parse(name);
                  FRsResourceMusic music = RContent2dManager.ResourceConsole.CreateResource(ERsResource.Music, code) as FRsResourceMusic;
                  music.Folder = this;
                  music.Code = code;
                  music.Name = name;
                  music.Label = label;
                  music.FullLabel = FullLabel + "\\" + label;
                  music.Keyword = label.Replace(".", "");
                  music.FileName = fileName;
                  music.Directory = _directory;
                  music.Scan();
                  _resources.Push(music);
               }
            }
            // 解析声音资源
            if (shortName.StartsWith("RS-")) {
               string fullName = name;
               string label = name;
               int codeIndex = name.IndexOf("-");
               int labelIndex = name.IndexOf(".mp3");
               if (-1 != codeIndex) {
                  label = name.Substring(0, labelIndex);
                  fullName = name.Substring(codeIndex + 1, labelIndex - codeIndex);
                  name = name.Substring(0, codeIndex);
               }
               if (11 == name.Length) {
                  name = name.Replace(".", "");
                  // 创建声音资源
                  int code = RInt.Parse(name);
                  FRsResourceSound sound = RContent2dManager.ResourceConsole.CreateResource(ERsResource.Sound, code) as FRsResourceSound;
                  sound.Folder = this;
                  sound.Code = code;
                  sound.Name = name;
                  sound.Label = label;
                  sound.FullLabel = FullLabel + "\\" + label;
                  sound.Keyword = label.Replace(".", "");
                  sound.FileName = fileName;
                  sound.Directory = _directory;
                  sound.Scan();
                  _resources.Push(sound);
               }
            }
         }
         // 加载设置信息
         //_configName = _directory + "\\config.xml";
         //if (RFile.Exists(_configName)) {
         //   FXmlDocument xdoc = new FXmlDocument();
         //   xdoc.LoadFile(_configName);
         //   foreach (FXmlNode xnode in xdoc.Root.Nodes) {
         //      if (xnode.IsName("Object")) {
         //         string code = null;
         //         if (xnode.Contains("code")) {
         //            code = xnode.Get("code");
         //         } else {
         //            code = xnode.Get("id", String.Empty);
         //         }
         //         if ("" == code) {
         //         }
         //         //FDsResource resource = FindResourceById(code);
         //         //if(null != resource) {
         //         //   resource.LoadConfig(xnode);
         //         //}
         //      }
         //   }
         //}
      }

      //============================================================
      // <T>保存设置信息。<T>      
      // 
      // @param xconfig 设置节点
      // @author TYFNF 20120409
      //============================================================
      public virtual void SaveConfig(FXmlNode xconfig) {
         // 设置标签
         xconfig.Set("label", _label);
      }

      //============================================================
      // <T>存储设置信息。<T>
      //============================================================
      public void Store() {
         FXmlDocument xdoc = new FXmlDocument();
         FXmlNode xconfig = xdoc.Root;
         SaveConfig(xconfig);
         //foreach(FDsResource resource in _resources) {
         //   resource.Open();
         //   resource.SaveConfig(xconfig.CreateNode("Object"));
         //   resource.Dispose();
         //}
         //System.Windows.Forms.MessageBox.Show(_configName.ToString());
         xdoc.SaveFile(_configName);
      }
   }
}
