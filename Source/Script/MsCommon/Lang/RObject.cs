namespace MS.Common.Lang
{
   //============================================================
   // <T>对象工具类。</T>
   //
   // @history MAOCY 140414
   //============================================================
   public class RObject : RTypes<object>
   {
      // 对象容量
      public const int CAPABILITY = 12;

      //============================================================
      // <T>获得非空对象。</T>
      //
      // @param values 对象列表
      // @return 对象
      //============================================================
      public static V Nvl<V>(params V[] values) {
         if(null != values) {
            foreach(V value in values) {
               if(null != value) {
                  return value;
               }
            }
         }
         return default(V);
      }

      //============================================================
      // <T>获得非空对象。</T>
      //
      // @param values 对象列表
      // @return 对象
      //============================================================
      public static object Nvl(params object[] values) {
         if(null != values) {
            foreach(object value in values) {
               if(null != value) {
                  return value;
               }
            }
         }
         return null;
      }
   }
}
