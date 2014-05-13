using MO.Common.Lang;

namespace MO.Bridge.Core.Event
{
   //============================================================
   // <T>事件处理对象。</T>
   //
   // @history MAOCY 140513
   //============================================================
   public class FEventDispatcher : FObject
   {
      //============================================================
      // <T>注册事件。</T>
      //
      // @return 处理结果
      //============================================================
      public EResult ListenerRegister() {
         return EResult.Success;
      }

      //============================================================
      // <T>注销事件。</T>
      //
      // @return 处理结果
      //============================================================
      public EResult ListenerUnregister() {
         return EResult.Success;
      }
   }
}
