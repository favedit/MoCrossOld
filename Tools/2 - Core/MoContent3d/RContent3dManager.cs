using MO.Common.Content;
using MO.Common.IO;
using MO.Content3d.Resource.Camera;
using MO.Content3d.Resource.Map;
using MO.Content3d.Resource.Material;
using MO.Content3d.Resource.Model;
using MO.Content3d.Resource.Scene;
using MO.Content3d.Resource.Template;
using MO.Content3d.Resource.Texture;
using MO.Content3d.Resource.Theme;
using System;
using System.Runtime.InteropServices;

namespace MO.Content3d
{
   //============================================================
   // <T>资源管理器。</T>
   //============================================================
   public class RContent3dManager
   {  
      // 初始化目录
      protected static string _directory;

      // 临时目录
      protected static string _tempDirectory;

      // 资源目录
      protected static string _resourceDirectory;

      // 资源数据目录
      protected static string _resourceDataDirectory;

      // 导出目录
      protected static string _exportDirectory;

      // 主题控制台
      protected static FDrThemeConsole _themeConsole = new FDrThemeConsole();

      // 纹理控制台
      protected static FDrTextureConsole _textureConsole = new FDrTextureConsole();

      // 材质控制台
      protected static FDrMaterialConsole _materialConsole = new FDrMaterialConsole();

      // 模型控制台
      protected static FDrModelConsole _modelConsole = new FDrModelConsole();

      // 模板控制台
      protected static FDrTemplateConsole _templateConsole = new FDrTemplateConsole();

      // 相机控制台
      protected static FDrCameraConsole _cameraConsole = new FDrCameraConsole();

      // 地图控制台
      protected static FDrMapConsole _mapConsole = new FDrMapConsole();

      // 场景控制台
      protected static FDrSceneConsole _senceConsole = new FDrSceneConsole();

      // 工作线程函数
      [DllImport("kernel32.dll", EntryPoint = "SetProcessWorkingSetSize")]
      public static extern int SetProcessWorkingSetSize(IntPtr process, int minSize, int maxSize);

      //============================================================
      // <T>获得工作目录。</T>
      //============================================================
      public static string Directory {
         get { return _directory; }
      }

      //============================================================
      // <T>获得临时目录。</T>
      //============================================================
      public static string TempDirectory {
         get { return _tempDirectory; }
      }

      //============================================================
      // <T>获得资源目录。</T>
      //============================================================
      public static string ResourceDirectory {
         get { return _resourceDirectory; }
      }

      //============================================================
      // <T>获得资源数据目录。</T>
      //============================================================
      public static string ResourceDataDirectory {
         get { return _resourceDataDirectory; }
      }

      //============================================================
      // <T>获得导出目录。</T>
      //============================================================
      public static string ExportDirectory {
         get { return _exportDirectory; }
      }

      //============================================================
      // <T>获得纹理控制台。</T>
      //============================================================
      public static FDrTextureConsole TextureConsole {
         get { return _textureConsole; }
      }

      //============================================================
      // <T>获得材质控制台。</T>
      //============================================================
      public static FDrMaterialConsole MaterialConsole {
         get { return _materialConsole; }
      }

      //============================================================
      // <T>获得主题控制台。</T>
      //============================================================
      public static FDrThemeConsole ThemeConsole {
         get { return _themeConsole; }
      }

      //============================================================
      // <T>获得模型控制台。</T>
      //============================================================
      public static FDrModelConsole ModelConsole {
         get { return _modelConsole; }
      }

      //============================================================
      // <T>获得模板控制台。</T>
      //============================================================
      public static FDrTemplateConsole TemplateConsole {
         get { return _templateConsole; }
      }

      //============================================================
      // <T>获得相机控制台。</T>
      //============================================================
      public static FDrCameraConsole CameraConsole {
         get { return _cameraConsole; }
      }

      //============================================================
      // <T>获得场景控制台。</T>
      //============================================================
      public static FDrSceneConsole SenceConsole {
         get { return _senceConsole; }
      }

      //============================================================
      // <T>获得地图控制台。</T>
      //============================================================
      public static FDrMapConsole MapConsole {
         get { return _mapConsole; }
      }

      //============================================================
      // <T>加载设置信息。</T>
      //
      // @param xconfig 设置信息
      //============================================================
      public static void LoadConfig(FXmlNode xconfig) {
         // 加载设置
         foreach(FXmlNode xnode in xconfig.Nodes) {
            string name = xnode.Get("name");
            if (xnode.IsName("Property")) {
               // 设置临时路径
               if(name == "temp.directory") {
                  _tempDirectory = xnode.Text;
               }
               // 设置资源路径
               if(name == "resource.directory") {
                  _resourceDirectory = xnode.Text;
               }
               // 设置资源数据路径
               if (name == "resource3d.data.directory") {
                  _resourceDataDirectory = xnode.Text;
               }
               // 设置导出路径
               if(name == "export.directory"){
                  _exportDirectory = xnode.Text;
               }
            } else if(xnode.IsName("Console")) {
               // 读取纹理设置
               if(name == _textureConsole.Name) {
                  _textureConsole.LoadConfig(xnode);
               }
               // 读取材质设置
               if(name == _materialConsole.Name) {
                  _materialConsole.LoadConfig(xnode);
               }
               // 读取材质设置
               if (name == _themeConsole.Name) {
                  _themeConsole.LoadConfig(xnode);
               }
               // 读取模型设置
               if(name == _modelConsole.Name) {
                  _modelConsole.LoadConfig(xnode);
               }
               // 读取模板设置
               if(name == _templateConsole.Name) {
                  _templateConsole.LoadConfig(xnode);
               }
               // 读取相机设置
               if (name == _cameraConsole.Name) {
                  _cameraConsole.LoadConfig(xnode);
               }
               // 读取场景设置
               if(name == _senceConsole.Name) {
                  _senceConsole.LoadConfig(xnode);
               }
            }
         }
      }

      //============================================================
      // <T>加载路径。</T>
      //
      // @param directory 路径
      //============================================================
      public static void LoadDirectory(string directory) {
         _directory = directory;
         // 加载应用信息
         string appFile = _directory + @"\Tools\Configuration\application.xml";
         if (RFile.Exists(appFile)) {
            FXmlDocument xdoc = new FXmlDocument();
            // 加载文件
            xdoc.LoadFile(appFile);
            LoadConfig(xdoc.Root);
         }
      }

      //============================================================
      // <T>打开处理。</T>
      //============================================================
      public static void Open() {
         // 打开处理
         _themeConsole.Open();
         _textureConsole.Open();
         _materialConsole.Open();
         _modelConsole.Open();
         _templateConsole.Open();
         _senceConsole.Open();
         // 材质建立
         // _materialConsole.ScanTextures();
      }

      //============================================================
      // <T>关闭处理。</T>
      //============================================================
      public static void Close() {
      }

      //============================================================
      // <T>强制回收垃圾<T>
      //============================================================
      public static void ClearMemory() {
         GC.Collect();
         GC.WaitForPendingFinalizers();
         if (Environment.OSVersion.Platform == PlatformID.Win32NT) {
            SetProcessWorkingSetSize(System.Diagnostics.Process.GetCurrentProcess().Handle, -1, -1);
         }
      }
   }
}
