using MO.Common.Lang;

namespace MO.Content2d.Frame.Common
{
   //============================================================
   // <T>界面组件集合。</T>
   //============================================================
   public class FRcComponentCollection : FObjects<FRcComponent>
   {
      //============================================================
      // <T>构造界面组件集合。</T>
      //============================================================
      public FRcComponentCollection() {
      }

      //============================================================
      // <T>获得有效子组件个数。</T>
      //
      // @return 子组件个数
      //============================================================
      public int CalculateValidCount() {
         int count = 0;
         foreach (FRcComponent component in this) {
            if (component.OptionValid) {
               count++;
            }
         }
         return count;
      }
   }
}
