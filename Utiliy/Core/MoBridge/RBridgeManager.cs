using MO.Bridge.Core;
using System;
using System.Runtime.InteropServices;

namespace MO.Bridge
{
   public class RBridgeManager
   {
      [DllImport("MoBridgeD.dll")]
      public static extern int MoBridgeInitialize();

      [DllImport("MoBridgeD.dll")]
      public static extern int MoBridgeRelease();

      [DllImport("D:/ZW-MoCross/Build/Debug_x86/MoBridgeD.dll", EntryPoint = "RBridgeManager_CreateObject")]
      public static extern int CreateObject(out SBridgeLinker linker, string className);
   }
}
