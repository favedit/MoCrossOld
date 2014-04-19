using MO.Common.IO;
using MO.DX.Core.Common;
using MO.Common.Geom;
using System.Drawing;
using System.Drawing.Imaging;
using System.Runtime.InteropServices;
using System;

namespace MO.DX.Core.Resource
{
   //============================================================
   public class FDxRsTextureBitmapPack : FDxObject
   {
      protected SIntSize _size = new SIntSize();

      protected string _source;

      protected Bitmap _bitmap;

      //============================================================
      public FDxRsTextureBitmapPack() {
      }

      //============================================================
      public SIntSize Size {
         get { return _size; }
      }

      //============================================================
      public string Source {
         get { return _source; }
      }

      //============================================================
      public Bitmap Bitmap {
         get { return _bitmap; }
      }

      //============================================================
      public virtual void Unserialize(IInput input) {
         _size.Width = input.ReadInt16();
         _size.Height = input.ReadInt16();
         // 创建图片
         _bitmap = new Bitmap(_size.Width, _size.Height, PixelFormat.Format32bppArgb);
         BitmapData bitData = _bitmap.LockBits(Rectangle.FromLTRB(0, 0, _size.Width, _size.Height), ImageLockMode.WriteOnly, PixelFormat.Format32bppArgb);
         // 读取值
         unsafe {
            byte* pData = (byte*)bitData.Scan0.ToPointer();
            // 读取数据
            int count = input.ReadUint16();
            for(int n = 0; n < count; n++) {
               // 读取数据块
               int splitWidth = input.ReadUint16();
               int splitHeight = input.ReadUint16();
               // 读取调色板
               int colorCount = input.ReadUint8();
               Color[] palette = new Color[colorCount];
               for(int i = 0; i < colorCount; i++) {
                  palette[i] = Color.FromArgb(input.ReadInt32());
               }
               for(int y = 0; y < splitHeight; y++) {
                  int* pWrite = (int*)pData;
                  for(int x = 0; x < splitWidth; x++) {
                     Color color = palette[input.ReadUint8()];
                     Color resultColor = Color.FromArgb(input.ReadUint8(), color.R, color.G, color.B);
                     *pWrite++ = resultColor.ToArgb();
                  }
                  pData += bitData.Stride;
               }
            }
         }
         _bitmap.UnlockBits(bitData);
      }
   }
}
