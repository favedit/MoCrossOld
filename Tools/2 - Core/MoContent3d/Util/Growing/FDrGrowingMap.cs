using System.Drawing;
using System.IO;
using System.Windows.Forms;
using MO.Common.Geom;
using MO.Common.Lang;
using MO.DX.Core;
using MO.DX.Core.Common;
using MO.DX.Core.Device;
using MO.DX.Core.Program.Technique;

namespace MO.Content3d.Util.Light
{
   //============================================================
   public class FDrGrowingMap : FObject
   {
      protected SIntSize _size = new SIntSize(1024, 1024);

      protected int _borderCount = 32;

      protected Bitmap _normalBitmap;

      //============================================================
      public FDrGrowingMap(){
      }

      //============================================================
      public int BorderCount {
         get { return _borderCount; }
         set { _borderCount = value; }
      }

      //============================================================
      public SIntSize Size {
         get { return _size; }
      }

      //============================================================
      public FDxBufferedTexture ExportTexture(string importFileName) {
         // 获得图片大小
         Bitmap bmp = new Bitmap(importFileName);
         _size.Set(bmp.Width, bmp.Height);
         bmp.Dispose();
         // 生成目标
         Form form = new Form();
         FDxDevice3D device = RDxCore.Adapter.CreateDevice(form.Handle, _size.Width, _size.Height);
         device.ModeWireFrame = false;
         // 设置几何体
         FDxRegion region = new FDxRegion();
         // 渲染几何体
         FDxTechnique technique = RDxCore.TechniqueConsole.Get(device, "growing");
         FDrGrowingGeometry geometry = new FDrGrowingGeometry();
         geometry.Device = device;
         geometry.LoadResource();
         // 绘制结果
         region.Renderables.Push(geometry);
         // 设置目标
         FDxBufferedTexture texture = new FDxBufferedTexture();
         texture.Device = device;
         texture.Create(_size.Width, _size.Height);
         // 绘制目标
         FDxTexture originTexture = new FDxTexture();
         originTexture.Device = device;
         originTexture.LoadFile(importFileName);
         geometry.Material.Textures.Push(originTexture);
         // 绘制目标
         FDxTexture importTexture = new FDxTexture();
         importTexture.Device = device;
         importTexture.LoadFile(importFileName);
         geometry.Material.Textures.Push(importTexture);
         MemoryStream stream = new MemoryStream();
         for (int n = 0; n < _borderCount; n++) {
            device.SetRenderTarget(texture);
            // 绘制目标
            technique.Draw(region);
            device.SetRenderTarget();
            // 复制渲染目标为开始
            //importTexture.CopyFrom(texture);
            texture.SaveStream(stream);
            importTexture.LoadStream(stream);
         }
         // 保存内容
         return texture;
      }
      
      //============================================================
      public void Export(string importFileName, string exportFileName) {
         FDxBufferedTexture texture = ExportTexture(importFileName);
         texture.SaveFile(exportFileName);
      }

      //============================================================
      public void ExportStream(string importFileName, Stream exportStream) {
         FDxBufferedTexture texture = ExportTexture(importFileName);
         // 保存内容
         exportStream.Position = 0;
         texture.SaveStream(exportStream);
      }
   }
}
