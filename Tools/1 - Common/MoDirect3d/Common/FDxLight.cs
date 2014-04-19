using MO.Common.Geom;
using MO.Common.Lang;

namespace MO.DX.Core.Common
{
   //============================================================
   public class FDxLight : FObject
   {
      protected SFloatPoint3 _position = new SFloatPoint3();

      //============================================================
      public SFloatPoint3 Position {
         get { return _position; }
      }
   }
}
