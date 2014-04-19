using MO.Common.Collection;
using MO.Common.IO;
using MO.Common.Lang;

namespace MO.DX.Core.Resource
{
   //============================================================
   public class FDxRsMesh : FObject
   {
      protected string _name;

      protected FDictionary<FDxRsGeometry> _geometries = new FDictionary<FDxRsGeometry>();

      //============================================================
      public FDxRsMesh() {
      }

      //============================================================
      public string Name {
         get { return _name; }
      }
      
      //============================================================
      public FDictionary<FDxRsGeometry> Geometries {
         get { return _geometries; }
      }

      //============================================================
      public void Unserialize(IInput input) {
         int count = input.ReadInt16();
         for(int n = 0; n < count; n++) {
            FDxRsGeometry geometry = new FDxRsGeometry();
            geometry.Unserialize(input);
            _geometries.Set(geometry.Name, geometry);
         }
      }
   }
}
