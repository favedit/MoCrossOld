using System;

namespace MO.Bridge.Core
{
   //============================================================
   // <T>脚本对象。</T>
   //
   // @history MAOCY 140512
   //============================================================
   public class FBridgeObject : IDisposable
   {
      // 关联指针
      protected SBridgeLinker _bridgeLinker;

      //============================================================
      // <T>构造脚本对象。</T>
      //============================================================
      public FBridgeObject() {
         int result = RBridgeManager.CreateObject(out _bridgeLinker, "Instance");
      }

      //============================================================
      // <T>构造脚本对象。</T>
      //============================================================
      public int ReferCount() {
         return RBridgeManager.Invoke(out _bridgeLinker, "ReferCount");
      }

      //============================================================
      // <T>析构脚本对象。</T>
      //============================================================
      public void Dispose() { 
      }
   }
}
