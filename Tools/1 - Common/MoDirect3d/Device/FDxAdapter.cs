using System;
using MO.Common.Lang;

namespace MO.DX.Core.Device
{
   //============================================================
   public class FDxAdapter : FObject
   {
      protected FObjects<FDxDevice3D> _devices = new FObjects<FDxDevice3D>();

      //============================================================
      public FDxAdapter() {
      }

      //============================================================
      public FObjects<FDxDevice3D> Devices {
         get { return _devices; }
      }

      //============================================================
      public FDxDevice3D CreateDevice(int width, int height) {
         FDxDevice3D device = new FDxDevice3D();
         device.Adapter = this;
         device.Setup(width, height);
         _devices.Push(device);
         return device;
      }
   
      //============================================================
      public FDxDevice3D CreateDevice(IntPtr handle, int width, int height) {
         FDxDevice3D device = new FDxDevice3D();
         device.Adapter = this;
         device.Setup(handle, width, height);
         _devices.Push(device);
         return device;
      }
   }
}
