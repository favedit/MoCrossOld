using MO.Common.Lang;
using MO.Common.System;

namespace MoScout.Core
{
   //============================================================
   // <T>信息控制台基础类。</T>
   //============================================================
   public class FInfoConsole : FConsole
   {
      protected FApplicationInfo _activeInfo;

      protected FObjects<FApplicationInfo> _infos = new FObjects<FApplicationInfo>();

      //============================================================
      // <T>构造信息控制台基础类。</T>
      //============================================================
      public FInfoConsole() {
      }

      //============================================================
      public FApplicationInfo CreateInfo() {
         FApplicationInfo info = new FApplicationInfo();
         _activeInfo = info;
         _infos.Push(info);
         return info;
      }

      //============================================================
      public FApplicationInfo ActiveInfo {
         get { return _activeInfo; }
      }
   }
}
