using MoMobile.Map.Common;

namespace MoMobile.Map.Designer
{
   //============================================================
   // <T>地图瓦片对象。</T>
   //============================================================
   public class FDsMapTile
   {
      // 资源定义
      protected FMbMapTile _resource = new FMbMapTile();

      //============================================================
      // <T>获取或得到资源。</T>
      //============================================================
      public FMbMapTile Resource {
         get { return _resource; }
         set { _resource = value; }
      }
   }
}
