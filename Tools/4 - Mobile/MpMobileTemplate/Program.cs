using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;
using MO.Common;
using MO.Common.System;
using MpMobileTemplate.Forms;

namespace MpMobileTemplate
{
   static class Program
   {
      /// <summary>
      /// 应用程序的主入口点。
      /// </summary>
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

         Application.Run(QTemplateExportForm.Instance);
      }
   }
}
