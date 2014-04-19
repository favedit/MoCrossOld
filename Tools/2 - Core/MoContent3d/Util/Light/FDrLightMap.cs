using System.Drawing;
using System.Drawing.Imaging;
using System.Windows.Forms;
using MO.Common.Collection;
using MO.Common.Geom;
using MO.Common.Lang;
using MO.DX.Core;
using MO.DX.Core.Common;
using MO.DX.Core.Device;
using MO.DX.Core.Program.Technique;
using MO.Content3d.Resource.Model;
using MO.Content3d.Resource.Model.Mesh;

namespace MO.Content3d.Util.Light
{
   //============================================================
   public class FDrLightMap : FObject
   {
      protected int _width = 512;

      protected int _height = 512;

      protected Bitmap _normalBitmap;

      //============================================================
      public FDrLightMap(){
         _normalBitmap = new Bitmap(_width, _height, PixelFormat.Format24bppRgb);
      }

      //============================================================
      public FDrLightMap(int width, int height) {
         _width = width;
         _height = height;
         _normalBitmap = new Bitmap(_width, _height, PixelFormat.Format24bppRgb);
      }

      //============================================================
      public void ExportMerge(FDrModel model) {
         SIntSize size = new SIntSize(_width, _height);
         Form form = new Form();
         FDxDevice3D device = RDxCore.Adapter.CreateDevice(form.Handle, size.Width, size.Height);
         device.ModeWireFrame = false;
         // 设置几何体
         FDxRegion region = new FDxRegion();
         // 设置目标
         FDxBufferedTexture texture = new FDxBufferedTexture();
         texture.Device = device;
         texture.Create(size.Width, size.Height);
         device.SetRenderTarget(texture);
         // 渲染几何体
         FDxTechnique technique = RDxCore.TechniqueConsole.Get(device, "light.map");
         int index = 0;
         foreach (INamePair<FDrGeometry> pair in model.Mesh.GeometryDictionary) {
            FDrLightGeometry geometry = new FDrLightGeometry();
            geometry.Device = device;
            geometry.LoadResource(pair.Value);
            region.Renderables.Clear();
            region.Renderables.Push(geometry);
            // 绘制结果
            technique.Draw(region);
            // 保存内容
            texture.SaveFile(model.Directory + "/" + RInt.Pad(index, 3) + ".png");
            index++;
         }
      }

      //============================================================
      public void Export3d(FDrModel model) {
         SIntSize size = new SIntSize(_width, _height);
         Form form = new Form();
         FDxDevice3D device = RDxCore.Adapter.CreateDevice(form.Handle, size.Width, size.Height);
         device.ModeWireFrame = false;
         // 设置几何体
         FDxRegion region = new FDxRegion();
         // 设置目标
         FDxBufferedTexture texture = new FDxBufferedTexture();
         texture.Device = device;
         texture.Create(size.Width, size.Height);
         device.SetRenderTarget(texture);
         // 渲染几何体
         FDxTechnique technique = RDxCore.TechniqueConsole.Get(device, "light.map");
         int index = 0;
         foreach (INamePair<FDrGeometry> pair in model.Mesh.GeometryDictionary) {
            FDrLightGeometry geometry = new FDrLightGeometry();
            geometry.Device = device;
            geometry.LoadResource(pair.Value);
            region.Renderables.Clear();
            region.Renderables.Push(geometry);
            // 绘制结果
            technique.Draw(region);
            // 保存内容
            string name = pair.Value.Name;
            string lightName = RString.Right(name, "|");
            texture.SaveFile(model.Directory + "/" + lightName + ".org.png");
            texture.SaveFile(model.Directory + "/" + lightName + ".png");
            index++;
         }
      }
   }
}
