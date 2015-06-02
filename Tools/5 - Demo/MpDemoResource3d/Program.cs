using MO.Common;
using MO.Common.Content;
using MO.Common.Lang;
using MO.Common.System;
using MO.Content2d;
using MO.Content3d;
using MO.Core;
using MO.Core.Logic.Task;
using MO.Design3d.Forms;
using MO.DX.Core;
using System;
using System.Windows.Forms;

namespace Mp3d.Resource.Editor
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
         string environmentFileName = RSystem.Location(@"\..\Configuration\environment.eai.xml");
         RMoCommon.LoadEnvironment(environmentFileName);
         //............................................................
         // 加载配置文件
         string configFileName = RMoCommon.ParseEnvironment(@"${application.root}\Configuration\configuration.xml");
         using (FXmlDocument xdoc = new FXmlDocument(configFileName)) {
            FXmlNode xconfig = xdoc.Root;
            // 加载配置
            RMoCore.LoadConfig(xconfig);
            RDxCore.LoadConfig(xconfig);
            RContent2dManager.LoadConfig(xconfig);
            RContent3dManager.LoadConfig(xconfig);
            // 配置处理
            RMoCore.Setup();
            RContent3dManager.Open();
         }
         //............................................................
         try {
            // 打开跟踪表单
            //QTrackForm.Instance.Open();
            // 打开任务表单
            QTaskForm.Instance.Open();
            // 显示控制表单
            QDsConsoleForm.Instance.Open();
            Application.Run(QDsConsoleForm.Instance);
         } catch (FException e) {
            MessageBox.Show(e.Describe, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
         }
         //............................................................
         // 释放系统
         RContent2dManager.Release();
         RMoCore.Release();
         RMoCommon.Release();
      }
   }
}