using MO.Common.IO;
using MO.Common.Lang;

namespace MO.Common.Geom
{
   //============================================================
   // <T>整数边界。</T>
   //
   // @history MAOCY 121025
   //============================================================
   public class SIntPadding
   {
      // 左边界
      public int Left;

      // 上边界
      public int Top;

      // 右边界
      public int Right;

      // 下边界
      public int Bottom;

      //============================================================
      // <T>构造整数边界。</T>
      //============================================================
      public SIntPadding(int left = 0, int top = 0, int right = 0, int bottom = 0) {
         Left = left;
         Top = top;
         Right = right;
         Bottom = bottom;
      }

      //============================================================
      // <T>判定是否为空。</T>
      //
      // @return 是否为空
      //============================================================
      public bool IsEmpty() {
         return (0 == Left) && (0 == Top) && (0 == Right) && (0 == Bottom);
      }

      //============================================================
      // <T>接收对象内容。</T>
      //
      // @param padding 对象内容
      //============================================================
      public void Assign(SIntPadding padding) {
         if(padding != null) {
            Left = padding.Left;
            Top = padding.Top;
            Right = padding.Right;
            Bottom = padding.Bottom;
         }
      }
      
      //============================================================
      // <T>重置数据。</T>
      //============================================================
      public void Reset() {
         Left = 0;
         Top = 0;
         Right = 0;
         Bottom = 0;
      }

      //============================================================
      // <T>解析字符串内容。</T>
      //
      // @param value 字符串内容
      //============================================================
      public bool Parse(string value) {
         if (!RString.IsEmpty(value)) {
            string[] items = value.Split(',');
            if(4 == items.Length) {
               Left = RInt.Parse(items[0]);
               Top = RInt.Parse(items[1]);
               Right = RInt.Parse(items[2]);
               Bottom = RInt.Parse(items[3]);
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
      public void Serialize8(IOutput output) {
         output.WriteUint8((byte)Left);
         output.WriteUint8((byte)Top);
         output.WriteUint8((byte)Right);
         output.WriteUint8((byte)Bottom);
      }

      //============================================================
      // <T>序列化内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public void Serialize16(IOutput output) {
         output.WriteUint16((ushort)Left);
         output.WriteUint16((ushort)Top);
         output.WriteUint16((ushort)Right);
         output.WriteUint16((ushort)Bottom);
      }

      //============================================================
      // <T>序列化内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public void Serialize(IOutput output) {
         output.WriteInt32(Left);
         output.WriteInt32(Top);
         output.WriteInt32(Right);
         output.WriteInt32(Bottom);
      }

      //============================================================
      // <T>获得字符串内容。</T>
      //
      // @return 字符串内容
      //============================================================
      public override string ToString() {
         return Left + "," + Top + "," + Right + "," + Bottom;
      }
   }
}
