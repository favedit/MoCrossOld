using MO.Common.IO;
using MO.Common.Lang;

namespace MO.DX.Core.Model
{
   //============================================================
   public class FDxMesh : FObject
   {
      protected string _name;

      protected FObjects<FDxGeometry> _geometries = new FObjects<FDxGeometry>();

      //============================================================
      public FDxMesh() {
      }

      //============================================================
      public void Unserialize(IInput input) {
      }
   }
}
