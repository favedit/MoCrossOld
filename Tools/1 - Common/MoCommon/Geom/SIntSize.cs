using MO.Common.IO;
using MO.Common.Lang;

namespace MO.Common.Geom
{
   //============================================================
   // <T>整数尺寸。</T>
   //============================================================
   public class SIntSize : SSize2<int>
   {
      //============================================================
      // <T>构造整数尺寸。</T>
      //============================================================
      public SIntSize() {
      }

      //============================================================
      // <T>构造整数尺寸。</T>
      //
      // @param value 字符串
      //============================================================
      public SIntSize(string value) {
         Parse(value);
      }

      //============================================================
      // <T>构造整数尺寸。</T>
      //
      // @param width 宽度
      // @param height 高度
      //============================================================
      public SIntSize(int width, int height) {
         Width = width;
         Height = height;
      }


      //============================================================
      // <T>计算面积。</T>
      //
      // @return 面积
      //============================================================
      public int Square {
         get {
            return Width * Height;
         }
      }

      //============================================================
      // <T>序列化内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public void Serialize(IOutput output) {
         output.WriteInt32(Width);
         output.WriteInt32(Height);
      }

      //============================================================
      // <T>序列化内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public void Serialize16(IOutput output) {
         output.WriteInt16((short)Width);
         output.WriteInt16((short)Height);
      }
      
      //============================================================
      // <T>解析字符串。</T>
      //
      // @param value 字符串
      //============================================================
      public virtual void Parse(string value) {
         string[] data = value.Split(',');
         if(2 == data.Length) {
            Width = RInt.Parse(data[0]);
            Height = RInt.Parse(data[1]);
         } else {
            throw new FFatalException("Invalid size format ({0})", value);
         }
      }
   }
}
