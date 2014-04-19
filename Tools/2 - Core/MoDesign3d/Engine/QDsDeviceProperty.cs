using System;
using System.Windows.Forms;
using MO.DX.Core.Device;

namespace MO.Design3d.Engine
{
   //============================================================
   public partial class QDsDeviceProperty : UserControl
   {
      protected FDxDevice3D _device;

      //============================================================
      public QDsDeviceProperty() {
         InitializeComponent();
      }

      //============================================================
      public FDxDevice3D Device {
         get { return _device; }
      }

      //============================================================
      public void LoadDevice(FDxDevice3D device) {
         _device = device;
      }

      //============================================================
      private void chkWireFrame_CheckedChanged(object sender, EventArgs e) {
         // _device.ModeWireFrame = chkWireFrame.Checked;
      }
   }
}
