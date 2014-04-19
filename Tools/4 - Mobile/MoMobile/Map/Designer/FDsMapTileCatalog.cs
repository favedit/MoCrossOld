using MoMobile.Map.Common;

namespace MoMobile.Map.Designer
{
   //============================================================
   // <T>地图瓦片组对象。</T>
   //============================================================
   public class FDsMapTileCatalog
   {
      // 资源定义
      protected FMbMapTileCatalog _resource = new FMbMapTileCatalog();

      //============================================================
      // <T>获取或得到资源。</T>
      //============================================================
      public FMbMapTileCatalog Resource {
         get { return _resource; }
         set { _resource = value; }
      }
   }
}
