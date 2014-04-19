using MO.Common.Lang;

namespace MO.DX.Core.Scene.Terrain
{
   //============================================================
   public class FDxTerrain : FObject
   {
      protected FDxTerrainMaterial _material = new FDxTerrainMaterial();

      protected FObjects<FDxTerrainTile> _tiles = new FObjects<FDxTerrainTile>();

      //============================================================
      public FDxTerrainMaterial Material {
         get { return _material; }
      }

      //============================================================
      public FObjects<FDxTerrainTile> Tiles {
         get { return _tiles; }
      }
   }
}
