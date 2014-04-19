using MO.DX.Core.Device;

namespace MO.DX.Core.Common
{
   //============================================================
   public class FDxContextObject : FDxObject
   {
      protected FDxDevice3D _device;

      //============================================================
      public FDxContextObject() {
      }

      //============================================================
      public FDxDevice3D Device {
         get { return _device; }
         set { _device = value; }
      }
   }
}
