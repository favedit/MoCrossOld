using MO.Common.Lang;
using MO.Core.Window.Api;
using MO.Core.Window.Context;

namespace MO.Core.Window.Core {

   public class FMemoryInfo {

      public uint BaseAddress;

      public uint AllocationBase;

      public int AllocationProtect;

      public uint RegionSize;

      public int State;

      public int Protect;

      public int Type;

      public FModuleInfo Module;

      public string AddressRange {
         get { return BaseAddress.ToString("X8") + "-" + (BaseAddress + RegionSize).ToString("X8"); }
      }

      public string AllocationProtectDesc {
         get { return REnum.ToString(typeof(EPage), AllocationProtect); }
      }

      public string StateDesc {
         get { return REnum.ToString(typeof(EMem), State); }
      }

      public string ProtectDesc {
         get { return REnum.ToString(typeof(EPage), Protect); }
      }

      public string TypeDesc {
         get { return REnum.ToString(typeof(EMem), Type); }
      }

   }

}
