using MO.Bridge.Core;
using MO.Common.Lang;
using System.Runtime.CompilerServices;
using System.Runtime.InteropServices;

namespace MO.Bridge
{
   public class RBridgeManager
   {
      //============================================================
      // <T>桥接管理器初始化。</T>
      //============================================================
      [DllImport("MoBridge.dll", EntryPoint = "MoBridgeInitialize")]
      [MethodImplAttribute(MethodImplOptions.InternalCall)]
      public static extern EResult Initialize();

      //============================================================
      // <T>桥接管理器释放。</T>
      //============================================================
      [DllImport("MoBridge.dll", EntryPoint = "MoBridgeRelease")]
      [MethodImplAttribute(MethodImplOptions.InternalCall)]
      public static extern EResult Release();

      //============================================================
      // <T>创建对象。</T>
      //============================================================
      [DllImport("MoBridge.dll", EntryPoint = "RBridgeManager_CreateObject")]
      [MethodImplAttribute(MethodImplOptions.InternalCall)]
      public static extern EResult CreateObject(out SBridgeLinker linker, string className);

      //============================================================
      // <T>创建对象。</T>
      //============================================================
      [DllImport("MoBridge.dll", EntryPoint = "RBridgeManager_CreateObject")]
      [MethodImplAttribute(MethodImplOptions.InternalCall)]
      public static extern EResult FreeObject(out SBridgeLinker linker, string className);

      //============================================================
      // <T>调用函数。</T>
      //============================================================
      [DllImport("MoBridge.dll", EntryPoint = "RBridgeManager_Invoke")]
      [MethodImplAttribute(MethodImplOptions.InternalCall)]
      public static extern EResult Invoke(out SBridgeLinker linker, string method);
   }
}
