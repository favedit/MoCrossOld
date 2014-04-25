using MO.Common.IO;
using MO.Common.Lang;

namespace MoScout.Core.Invoker
{
   public class FInvokerInfo : FObject
   {
      //============================================================
      // <T>序列化数据到输出流。</T>
      //
      // @param output 输出流
      // @return 处理结果
      //============================================================
      public EResult Serialize(IDataOutput output) {
         return EResult.Success;
      }

      //============================================================
      // <T>从输入流中反序列化数据。</T>
      //
      // @param input 输入流
      // @return 处理结果
      //============================================================
      public EResult Unserialize(IDataInput input) {
         return EResult.Success;
      }
   }
}
