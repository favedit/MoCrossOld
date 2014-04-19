using System;
using System.Collections.Generic;
using System.Text;
using MO.Core.Window.Api;
using MO.Core.Window.Core;
using MO.Core.Window.Context;
using MO.Core.Window.Core.Hook;

namespace MO.Core.Window.Core {

   public class RHookApi {

      //delegate

      private static void HookApi(SHookInfo hook, FTrunkInfo trunk) {
         /*SMemoryBasicInformation mbi_thunk = new SMemoryBasicInformation();
         RKernel32.VirtualQuery(pRealThunk, &mbi_thunk, sizeof(MEMORY_BASIC_INFORMATION));
         RKernel32.VirtualProtect(mbi_thunk.BaseAddress, mbi_thunk.RegionSize, PAGE_READWRITE, &mbi_thunk.Protect);
         if (pHookApi->pOldProc == NULL) {
            pHookApi->pOldProc = (PROC)pRealThunk->u1.Function;
         }
         pRealThunk->u1.Function = (DWORD)pHookApi->pNewProc;
         DWORD dwOldProtect;
         RKernel32.VirtualProtect(mbi_thunk.BaseAddress, mbi_thunk.RegionSize, mbi_thunk.Protect, &dwOldProtect);*/
      }

      public static void Hook(SHookInfo hook) {
         Nullable<SModuleEntry32> module = RModule.Find(hook.Module);
         if (module.HasValue) {
            FTrunkInfo[] trunks = RModule.FetchTrunks(module.Value.hModule);
            foreach (FTrunkInfo trunk in trunks) {
               if (trunk.Name == hook.Function) {
                  HookApi(hook, trunk);
               }
            }
         }
      }

   }
}
