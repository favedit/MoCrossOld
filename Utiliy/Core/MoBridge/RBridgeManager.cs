using MO.Bridge.Core;
using System;
using System.Runtime.InteropServices;

namespace MO.Bridge
{
   public class RBridgeManager
   {
      [DllImport("MoBridgeD.dll", EntryPoint="MoBridgeInitialize")]
      public static extern int Initialize();

      [DllImport("MoBridgeD.dll", EntryPoint="MoBridgeRelease")]
      public static extern int Release();

      [DllImport("D:/ZW-MoCross/Build/Debug_x86/MoBridgeD.dll", EntryPoint="RBridgeManager_CreateObject")]
      public static extern int CreateObject(out SBridgeLinker linker, string className);

      [DllImport("D:/ZW-MoCross/Build/Debug_x86/MoBridgeD.dll", EntryPoint = "RBridgeManager_Invoke")]
      public static extern int Invoke(out SBridgeLinker linker, string method);
   }
}
