namespace MO.Common.Geom
{
   //============================================================
   // <T>矩形。</T>
   //============================================================
   public class SRectangle<T>
   {
      public T Left;

      public T Top;

      public T Width;

      public T Height;

      //============================================================
      // <T>构造矩形。</T>
      //============================================================
      public SRectangle() {
      }

      //============================================================
      // <T>构造矩形。</T>
      //
      // @param left 左位置
      // @param top 上位置
      // @param width 宽度
      // @param height 高度
      //============================================================
      public SRectangle(T left, T top, T width, T height) {
         Left = left;
         Top = top;
         Width = width;
         Height = height;
      }

      //============================================================
      // <T>接收矩形对象。</T>
      //
      // @param rectangle 矩形对象
      //============================================================
      public void Assign(SRectangle<T> rectangle) {
         Left = rectangle.Left;
         Top = rectangle.Top;
         Width = rectangle.Width;
         Height = rectangle.Height;
      }

      //============================================================
      // <T>设置矩形。</T>
      //
      // @param left 左位置
      // @param top 上位置
      // @param width 宽度
      // @param height 高度
      //============================================================
      public void Set(T left, T top, T width, T height) {
         Left = left;
         Top = top;
         Width = width;
         Height = height;
      }

      //============================================================
      // <T>格式化成字符串。</T>
      //
      // @return 字符串
      //============================================================
      public override string ToString() {
         return Left + "," + Top + "," + Width + "," + Height;
      }
   }
}
