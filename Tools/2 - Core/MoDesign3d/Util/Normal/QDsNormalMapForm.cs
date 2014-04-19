using MO.Content3d.Resource.Model.Mesh;
using MO.DX.Core;
using MO.DX.Core.Common;
using MO.DX.Core.Device;
using MO.DX.Core.Program.Technique;
using System;
using System.Windows.Forms;

namespace MO.Design3d.Util.Normal
{
   public partial class QDsNormalMapForm : Form
   {
      public FDrGeometry geometry;

      public FDxDevice3D device;

      public FDxRegion region;

      public FDxTechnique technique;

      public QDsNormalMapForm() {
         InitializeComponent();
      }

      private void QNormalMapForm_Load(object sender, EventArgs e) {
         device = RDxCore.Adapter.CreateDevice(Handle, 1024, 1024);
         // 设置几何体
         region = new FDxRegion();
         FDsNormalGeometry normalGeometry = new FDsNormalGeometry();
         normalGeometry.Device = device;
         normalGeometry.LoadResource(geometry);
         region.Renderables.Push(normalGeometry);
         // 设置目标
         FDxBufferedTexture texture = new FDxBufferedTexture();
         texture.Device = device;
         texture.Create(1024, 1024);
         //device.SetRenderTarget(texture);
         // 绘制结果
         technique = RDxCore.TechniqueConsole.Get(device, "normal.map");
         // 保存内容
         //texture.SaveFile(filename);
         timer1.Enabled = true;
      }

      private void timer1_Tick(object sender, EventArgs e) {
         device.SetRenderTarget();
         technique.Draw(region);
         technique.Present();
      }
   }
}
