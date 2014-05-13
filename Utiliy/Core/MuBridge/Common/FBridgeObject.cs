using MO.Common.Lang;
using System;

namespace MO.Bridge.Core
{
   //============================================================
   // <T>脚本对象。</T>
   //
   // @history MAOCY 140512
   //============================================================
   public class FBridgeObject : FObject, IDisposable
   {
      // 关联指针
      protected SBridgeLinker _bridgeLinker;

      //============================================================
      // <T>构造脚本对象。</T>
      //============================================================
      public FBridgeObject() {
         EResult result = RBridgeManager.CreateObject(out _bridgeLinker, "Instance");
      }

      //============================================================
      // <T>构造脚本对象。</T>
      //============================================================
      public int ReferCount() {
         return (int)RBridgeManager.Invoke(out _bridgeLinker, "ReferCount");
      }

      //============================================================
      // <T>析构脚本对象。</T>
      //============================================================
      public void Dispose() { 
      }
   }
}
