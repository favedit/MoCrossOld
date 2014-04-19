using MO.Common.IO;
using MO.DX.Core.Common;
using MO.Common.Geom;
using System.Drawing;
using System.Drawing.Imaging;

namespace MO.DX.Core.Resource
{
   //============================================================
   public class FDxRsTextureCubePack : FDxRsTextureBitmapPack
   {
      //============================================================
      public FDxRsTextureCubePack() {
      }

      //============================================================
      public override void Unserialize(IInput input) {
         _size.Width = input.ReadInt16();
         _size.Height = input.ReadInt16();
         _bitmap = new Bitmap(_size.Width, _size.Height, PixelFormat.Format32bppArgb);
         // 读取数据
         int count = input.ReadUint16();
         for(int n = 0; n < count; n++) {
            // 读取数据块
            int splitWidth = input.ReadUint16();
            int splitHeight = input.ReadUint16();
            int colorCount = input.ReadUint8();
            Color[] palette = new Color[colorCount];
            for(int i = 0; i < colorCount; i++) {
               palette[i] = Color.FromArgb(input.ReadInt32());
            }
            for(int y = 0; y < splitHeight; y++) {
               for(int x = 0; x < splitWidth; x++) {
                  Color color = palette[input.ReadUint8()];
                  Color resultColor = Color.FromArgb(input.ReadUint8(), color.R, color.G, color.B);
                  _bitmap.SetPixel(splitHeight * n + x, y, resultColor);
               }
            }
         }
      }
   }
}
