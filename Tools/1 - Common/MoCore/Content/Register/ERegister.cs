using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MO.Core.Content.Register
{
   public enum ERegister
   {
      // 定义文档的类型（或类）以及与那些类型关联的属性
      ClassesRoot,

      // 有关非用户特定的硬件的配置信息。
      CurrentConfig,

      // 有关当前用户首选项的信息。
      CurrentUser,

      // 动态注册表数据。
      DynData,

      // 本地计算机的配置数据。
      LocalMachine,

      // 软件组件的性能信息。
      PerformanceData,

      // 有关默认用户配置的信息。
      Users
   }
}
