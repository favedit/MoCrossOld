namespace MO.Common.Geom
{
   //============================================================
   // <T>矩形工具。</T>
   //============================================================
   public class RRectangle
   {
      //============================================================
      // <T>测试点是否在矩形范围内。</T>
      //
      // @param x 矩形横坐标
      // @param y 矩形纵坐标
      // @param width 矩形宽度
      // @param height 矩形高度
      // @param px 测试点横坐标
      // @param py 测试点纵坐标
      //============================================================
      public static bool ConstainsPoint(int x, int y, int width, int height, int px, int py) {
         if (px < x) {
            return false;
         }
         if (px > x + width) {
            return false;
         }
         if (py < y) {
            return false;
         }
         if (py > y + height) {
            return false;
         }
         return true;
      }
   }
}
