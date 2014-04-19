using System;
using MO.Common.Content;
using MO.Common.Lang;
using MO.Common.IO;

namespace MO.Common.Geom
{
   //============================================================
   // <T>整数矩形。</T>
   //============================================================
   public class SIntRectangle : SRectangle<int>
   {
      //============================================================
      // <T>构造整数矩形。</T>
      //============================================================
      public SIntRectangle() {
      }

      //============================================================
      // <T>构造整数矩形。</T>
      //
      // @param location 位置
      // @param size 大小
      //============================================================
      public SIntRectangle(SIntPoint2 location, SIntSize2 size){
         Left = location.X;
         Top = location.Y;
         Width = size.Width;
         Height = size.Height;
      }
      
      //============================================================
      // <T>构造整数矩形。</T>
      //
      // @param left 左位置
      // @param top 上位置
      // @param width 宽度
      // @param height 高度
      //============================================================
      public SIntRectangle(int left, int top, int width, int height)
         : base(left, top, width, height) {
      }

      //============================================================
      // <T>构造整数矩形。</T>
      //
      // @param config 设置信息
      //============================================================
      public SIntRectangle(FXmlNode config) {
         LoadConfig(config);
      }

      //============================================================
      // <T>测试是否为空。</T>
      //============================================================
      public bool IsEmpty {
         get { return (0 == Width) || (0 == Height); }
      }

      //============================================================
      // <T>获得或设置右位置。</T>
      //============================================================
      public int Right {
         get { return Left + Width; }
         set {
            if(value < Left) {
               throw new FFatalException("Invalid value.");
            }
            Width = value - Left;
         }
      }

      //============================================================
      // <T>获得或设置底位置。</T>
      //============================================================
      public int Bottom {
         get { return Top + Height; }
         set {
            if(value < Top) {
               throw new FFatalException("Invalid value.");
            }
            Height = value - Top;
         }
      }

      //============================================================
      // <T>计算面积。</T>
      //
      // @return 面积
      //============================================================
      public int Square{
         get{
            return Width * Height;
         }
      }
      
      //============================================================
      // <T>序列化帧数据。</T>
      //
      // @param output 输出流
      //============================================================
      public void Serialize(IOutput output) {
         output.WriteInt32(Left);
         output.WriteInt32(Top);
         output.WriteInt32(Width);
         output.WriteInt32(Height);
      }

      //============================================================
      // <T>序列化帧数据。</T>
      //
      // @param output 输出流
      //============================================================
      public void Serialize16(IOutput output) {
         output.WriteUint16((ushort)Left);
         output.WriteUint16((ushort)Top);
         output.WriteUint16((ushort)Width);
         output.WriteUint16((ushort)Height);
      }
   
      //============================================================
      // <T>加载设置信息。</T>
      //
      // @param config 设置信息
      //============================================================
      public void LoadConfig(FXmlNode config) {
         Left = Int32.Parse(config["left"]);
         Top = Int32.Parse(config["top"]);
         Width = Int32.Parse(config["width"]);
         Height = Int32.Parse(config["height"]);
      }

      //============================================================
      // <T>存储设置信息。</T>
      //
      // @param config 设置信息
      //============================================================
      public void SaveConfig(FXmlNode config) {
         config["left"] = Left.ToString();
         config["top"] = Top.ToString();
         config["width"] = Width.ToString();
         config["height"] = Height.ToString();
      }

      //============================================================
      // <T>解析字符串。</T>
      //
      // @param value 字符串
      //============================================================
      public void Parse(string value) {
         if(null == value) {
            Left = Top = Width = Height = 0;
         } else {
            string[] items = value.Split(',');
            if(4 == items.Length) {
               Left = RInt.Parse(items[0]);
               Top = RInt.Parse(items[1]);
               Width = RInt.Parse(items[2]);
               Height = RInt.Parse(items[3]);
            } else {
               throw new FFatalException("Invalid string.");
            }
         }
      }
   }
}
