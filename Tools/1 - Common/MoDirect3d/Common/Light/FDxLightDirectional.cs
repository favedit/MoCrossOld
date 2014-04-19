using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MO.DX.Core.Common.Light
{
   //============================================================
   public class FDxLightDirectional : FDxLight
   {
      public FDxCamera _camera = new FDxCamera();

      //============================================================
      public FDxLightDirectional() {
      }

      //============================================================
      public FDxCamera Camera {
         get { return _camera; }
      }
   }
}
