using MO.Common;
using MO.Common.Content;
using MO.Common.System;
using MO.Content2d;
using MO.Core;
using MO.Core.Logic.Task;
using MO.Core.Logic.Track;
using MO.Design2d;
using MO.Design2d.Forms;
using System;
using System.Windows.Forms;

namespace MpMobileResource
{
   static class Program
   {
      //============================================================
      // <T>应用程序的主入口点。</T>
      //============================================================
      [STAThread]
      static void Main() {
         // 设置应用程序
         Application.EnableVisualStyles();
         Application.SetCompatibleTextRenderingDefault(false);
         // 初始化系统
         RMoCommon.Initialize();
         RMoCore.Initialize();
         // 加载环境文件
         string environmentFileName = RSystem.Location(@"\..\Configuration\environment.mobile.xml");
         RMoCommon.LoadEnvironment(environmentFileName);
         //............................................................
         // 加载配置文件
         string configFileName = RMoCommon.ParseEnvironment(@"${application.root}\Configuration\configuration.xml");
         using (FXmlDocument xdoc = new FXmlDocument(configFileName)) {
            FXmlNode xconfig = xdoc.Root;
            RMoCore.LoadConfig(xconfig);
            RContent2dManager.LoadConfig(xconfig);
            RDesign2dManager.LoadConfig(xconfig);
         }
         //............................................................
         // 配置处理
         RMoCore.Setup();
         RContent2dManager.Setup();
         RDesign2dManager.Setup();
         //............................................................
         // 显示资源表单
         QTrackForm.Instance.Open();
         QTaskForm.Instance.Open();
         // 显示工作界面
         QDsDesignForm.Instance.Open();
         QDsDesignForm.Instance.Text += " - [" + configFileName + "]";
         Application.Run(QDsDesignForm.Instance);
         // 显示工作界面
         //QUiConsoleForm.Instance.Open();
         //QUiConsoleForm.Instance.Text += " - [" + configFileName + "]";
         //Application.Run(QUiConsoleForm.Instance);
         // 显示工作界面
         //QUiDesignForm form = RDesign2dManager.FrameConsole.OpenDesignForm("main.bar");
         //Application.Run(form);
         //QUiConsoleForm.Instance.Open();
         //QUiConsoleForm.Instance.Text += " - [" + configFileName + "] v131016";
         //Application.Run(QUiConsoleForm.Instance);
         //............................................................
         // 释放系统
         //RDesign2dManager.Release();
         //RContent2dManager.Release();
         RMoCore.Release();
         RMoCommon.Release();
      }
   }
}
