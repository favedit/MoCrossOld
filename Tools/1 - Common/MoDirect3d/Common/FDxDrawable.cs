using MO.DX.Core.Common.Geom;

namespace MO.DX.Core.Common
{
   //============================================================
   public class FDxDrawable : FDxContextObject
   {
      protected static int SeedNextId = 0;

      protected int _id;

      protected string _name;

      protected bool _isVisible = true;

      protected bool _isLine = true;

      protected SDxMatrix _modelMatrix = new SDxMatrix();

      protected SDxMatrix _matrix = new SDxMatrix();

      //============================================================
      public FDxDrawable() {
         _id = ++SeedNextId;
      }

      //============================================================
      public int Id{
         get { return _id; }
         set { _id = value; }
      }

      //============================================================
      public string Name {
         get { return _name; }
         set { _name = value; }
      }

      //============================================================
      public bool IsVisible {
         get { return _isVisible; }
         set { _isVisible = value; }
      }

      //============================================================
      public bool IsLine {
         get { return _isLine; }
         set { _isLine = value; }
      }

      //============================================================
      public SDxMatrix Matrix {
         get { return _matrix; }
      }

      //============================================================
      public SDxMatrix ModelMatrix {
         get { return _modelMatrix; }
      }
   }
}
