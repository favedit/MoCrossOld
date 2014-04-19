using MoMobile.Map.Common;

namespace MoMobile.Map.Designer
{
   //============================================================
   // <T>地图出生点对象。</T>
   //============================================================
   public class FDsMapBirth
   {
      // 资源对象
      protected FMbMapBirth _resource = new FMbMapBirth();

      //============================================================
      // <T>获取或得到资源。</T>
      //============================================================
      public FMbMapBirth Resource {
         get { return _resource; }
         set { _resource = value; }
      }
   }
}
