using MO.Common.Lang;

namespace MO.Design2d.Frame.Common
{
   //============================================================
   // <T>界面组件集合。</T>
   //============================================================
   public class FUiComponentCollection : FObjects<FUiComponent>
   {
      //============================================================
      // <T>构造界面组件集合。</T>
      //============================================================
      public FUiComponentCollection() {
      }

      //============================================================
      // <T>获得有效子组件个数。</T>
      //
      // @return 子组件个数
      //============================================================
      public int CalculateValidCount() {
         int count = 0;
         foreach (FUiComponent component in this) {
            if (component.ComponentResource.OptionValid) {
               count++;
            }
         }
         return count;
      }
   }
}
