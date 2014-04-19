using MO.DX.Core.Common;
using MO.DX.Core.Model;

namespace MO.DX.Core
{
   //============================================================
   public class FDxFactory
   {
      //============================================================
      public virtual T Create<T>(EDxObject objectCd) {
         object value = null;
         switch(objectCd){
            case EDxObject.ModelGeometry:
               value = new FDxGeometry();
               break;
            case EDxObject.ModelSpatial:
               value = new FDxSpatial();
               break;
         }
         return (T)value;
      }
   }
}
