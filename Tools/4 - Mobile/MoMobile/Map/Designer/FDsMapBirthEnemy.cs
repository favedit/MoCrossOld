using MoMobile.Map.Common;

namespace MoMobile.Map.Designer
{
   //============================================================
   // <T>地图出生点敌机对象。</T>
   //============================================================
   public class FDsMapBirthEnemy
   {
      // 资源对象
      protected FMbMapBirthEnemy _resource = new FMbMapBirthEnemy();

      //============================================================
      // <T>获取或得到资源。</T>
      //============================================================
      public FMbMapBirthEnemy Resource {
         get { return _resource; }
         set { _resource = value; }
      }
   }
}
