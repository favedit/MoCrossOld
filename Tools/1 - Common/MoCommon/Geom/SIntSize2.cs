using MO.Common.Lang;
using MO.Common.IO;

namespace MO.Common.Geom
{
   //============================================================
   // <T>整数尺寸。</T>
   //============================================================
   public class SIntSize2 : SSize2<int>
   {
      //============================================================
      // <T>构造整数尺寸。</T>
      //============================================================
      public SIntSize2() {
      }

      //============================================================
      // <T>构造整数尺寸。</T>
      //
      // @param value 字符串
      //============================================================
      public SIntSize2(string value) {
         Parse(value);
      }

      //============================================================
      // <T>构造整数尺寸。</T>
      //
      // @param width 宽度
      // @param height 高度
      //============================================================
      public SIntSize2(int width, int height) {
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
      // <T>接收尺寸对象。</T>
      //
      // @param size 尺寸对象
      // @param scale 缩放
      //============================================================
      public void Assign(SIntSize2 size, float scale) {
         if(size != null) {
            Width = (int)(size.Width * scale);
            Height = (int)(size.Height * scale);
         }
      }
      
      //============================================================
      // <T>设置尺寸。</T>
      //
      // @param width 宽度
      // @param height 高度
      // @param scale 缩放
      //============================================================
      public void Set(int width, int height, float scale) {
         Width = (int)(width * scale);
         Height = (int)(height * scale);
      }
      
      //============================================================
      // <T>内部最大化。</T>
      //
      // @param size 尺寸
      //============================================================
      public void InnerMax(SIntSize2 size) {
         if(Width < size.Width) {
            Width = size.Width;
         }
         if(Height < size.Height) {
            Height = size.Height;
         }
      }

      //============================================================
      // <T>解析字符串。</T>
      //
      // @param value 字符串
      //============================================================
      public virtual bool Parse(string value) {
         if(!RString.IsEmpty(value)) {
            string[] data = value.Split(',');
            if(data.Length == 2) {
               Width = RInt.Parse(data[0]);
               Height = RInt.Parse(data[1]);
               return true;
            }
         }
         return false;
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
      // <T>重置内容。</T>
      //============================================================
      public void Reset() {
         Width = 0;
         Height = 0;
      }
   }
}
