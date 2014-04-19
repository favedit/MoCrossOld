using MO.DX.Core;
using MO.DX.Core.Common;
using MO.DX.Core.Model;
using MO.Design3d.Core.Model;

namespace MO.Design3d
{
   //============================================================
   public class FDxDesignFactory : FDxFactory
   {
      //============================================================
      public override T Create<T>(EDxObject objectCd) {
         object value = null;
         switch(objectCd) {
            case EDxObject.ModelGeometry:
               value = new FDxDesignGeometry();
               break;
            case EDxObject.ModelSpatial:
               value = new FDxSpatial();
               break;
         }
         if(null == value) {
            value = base.Create<T>(objectCd);
         }
         return (T)value;
      }
   }
}
