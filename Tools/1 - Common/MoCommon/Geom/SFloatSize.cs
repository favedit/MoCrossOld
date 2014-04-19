using MO.Common.Lang;

namespace MO.Common.Geom
{
   //============================================================
   // <T>整数尺寸。</T>
   //============================================================
   public class SFloatSize : SSize2<float>
   {
      //============================================================
      // <T>构造整数尺寸。</T>
      //============================================================
      public SFloatSize() {
      }

      //============================================================
      // <T>构造整数尺寸。</T>
      //
      // @param value 字符串
      //============================================================
      public SFloatSize(string value) {
         Parse(value);
      }

      //============================================================
      // <T>构造整数尺寸。</T>
      //
      // @param width 宽度
      // @param height 高度
      //============================================================
      public SFloatSize(int width, int height) {
         Width = width;
         Height = height;
      }

      //============================================================
      // <T>判断是否为空。</T>
      //
      // @return 是否为空
      //============================================================
      public bool IsEmpty() {
         return (Width == 0) && (Height == 0);
      }

      //============================================================
      // <T>解析字符串。</T>
      //
      // @param value 字符串
      //============================================================
      public virtual void Parse(string value) {
         string[] data = value.Split(',');
         if(2 == data.Length) {
            Width = RFloat.Parse(data[0]);
            Height = RFloat.Parse(data[1]);
         } else {
            throw new FFatalException("Invalid size format ({0})", value);
         }
      }
   }
}
