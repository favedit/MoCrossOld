using MO.Common.Geom;
using MO.Common.Lang;
using MO.Content3d.Resource.Model.Mesh;
using MO.DX.Core;
using MO.DX.Core.Common;
using MO.DX.Core.Device;
using MO.DX.Core.Program.Technique;
using System;
using System.Drawing;
using System.Drawing.Imaging;
using System.Windows.Forms;

namespace MO.Design3d.Util.Normal
{
   //============================================================
   public class FDsNormalMap : FObject
   {
      protected int _width = 1024;

      protected int _height = 1024;

      protected Bitmap _normalBitmap;

      //============================================================
      public FDsNormalMap(){
         _normalBitmap = new Bitmap(_width, _height, PixelFormat.Format24bppRgb);
      }

      //============================================================
      public FDsNormalMap(int width, int height){
         _width = width;
         _height = height;
         _normalBitmap = new Bitmap(_width, _height, PixelFormat.Format24bppRgb);
      }

      //============================================================
      public void Export3d2(FDrGeometry geometry, string filename) {
         SIntSize size = new SIntSize(1024, 1024);
         Form form = new Form();
         FDxDevice3D device = RDxCore.Adapter.CreateDevice(form.Handle, size.Width, size.Height);
         device.ModeWireFrame = false;
         // 设置几何体
         FDxRegion region = new FDxRegion();
         FDsNormalGeometry normalGeometry = new FDsNormalGeometry();
         normalGeometry.Device = device;
         normalGeometry.LoadResource(geometry);
         region.Renderables.Push(normalGeometry);
         // 设置目标
         FDxBufferedTexture texture = new FDxBufferedTexture();
         texture.Device = device;
         texture.Create(size.Width, size.Height);
         device.SetRenderTarget(texture);
         // 绘制结果
         FDxTechnique technique = RDxCore.TechniqueConsole.Get(device, "normal.map");
         technique.Draw(region);
         // 保存内容
         texture.SaveFile(filename);
         // 创建设备
         // QNormalMapForm form = new QNormalMapForm();
         // form.geometry = geometry;
         // Application.Run(form);
      }
      
      //============================================================
      public void Export3d(FDrGeometry geometry, string filename) {
         string directory = geometry.Model.Directory;
         Bitmap low = new Bitmap(directory + "/ms_001.low.png");
         Bitmap high = new Bitmap(directory + "/ms_001.high.png");
         // 创建设备
         Bitmap normal = new Bitmap(1024, 1024, PixelFormat.Format32bppArgb);
         for (int y = 0; y < 1024; y++) {
            for (int x = 0; x < 1024; x++) {
               Color lc = low.GetPixel(x, y);
               Color hc = high.GetPixel(x, y);
               SFloatVector3 n = new SFloatVector3();
               n.X = (float)(hc.R - lc.R) / 128;
               n.Y = (float)(hc.G - lc.G) / 128;
               n.Z = (float)(hc.B - lc.B) / 256;
               //n.Normalize();
               int r = (int)((n.X * 0.5 + 0.5) * 255);
               r = Math.Max(Math.Min(r, 255), 0);
               int g = (int)((n.Y * 0.5 + 0.5) * 255);
               g = Math.Max(Math.Min(g, 255), 0);
               int b = (int)(((1 - n.Z) * 0.5 + 0.5) * 255);
               b = Math.Max(Math.Min(b, 255), 0);
               normal.SetPixel(x, y, Color.FromArgb(r, g, b));
            }
         }
         normal.Save(directory + "/ms_001.normal.png");
         //QNormalMapForm form = new QNormalMapForm();
         //form.geometry = geometry;
         //Application.Run(form);
      }
   }
}
