using System;
using System.Windows.Forms;
using MO.Common;
using MO.Common.Content;
using MO.Common.System;
using MO.Content2d;
using MO.Core;
using MoMobile;
using MoMobile.Map.Forms;

namespace MpMobileMap
{
   static class Program
   {
      // 设置文件名称
      private static string _configFileName;

      //============================================================
      // <T>加载配置文件。</T>
      //============================================================
      public static void LoadDirectory(string directory) {
         _configFileName = directory + @"\Configuration\configuration.xml";
         // 记载配置文件
         FXmlDocument xdoc = new FXmlDocument();
         xdoc.LoadFile(_configFileName);
         FXmlNode xconfig = xdoc.Root;
         // 加载核心配置
         RMoCore.LoadConfig(xconfig);
         RMoCore.Setup();
         // 加载资源管理
         RMobileManager.Directory = directory;
         RMobileManager.LoadConfig(xconfig);

         RContent2dManager.LoadConfig(xconfig);
         RContent2dManager.Setup();
      }

      //============================================================
      // <T>应用程序的主入口点。</T>
      //============================================================
      [STAThread]
      static void Main() {
         // 设置应用程序
         Application.EnableVisualStyles();
         Application.SetCompatibleTextRenderingDefault(false);
         // 初始化系统
         string location = RSystem.Location();
         string configFileName = location + @"\..\Configuration\environment.mobile.xml";
         RMoCommon.Initialize();
         RMoCommon.LoadEnvironment(configFileName);
         // 加载配置信息
         string applicationRoot = RMoCommon.GetEnvironment("application.root");
         LoadDirectory(applicationRoot);


         //QMapDesignForm form = new QMapDesignForm();
         //FMbMap map = RMobileManager.MapConsole.FindById(101);
         //form.LoadMap(map);
         //Application.Run(form);

         QMapConsoleForm.Instance.Open();
         Application.Run(QMapConsoleForm.Instance);
         // 释放系统
         RMoCore.Release();
      }
   }
}
