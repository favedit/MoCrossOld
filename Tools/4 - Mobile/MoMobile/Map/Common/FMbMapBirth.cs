using MO.Common.Content;
using MO.Common.Geom;
using MO.Common.Lang;
using MoMobile.Map.Designer;

namespace MoMobile.Map.Common
{
   //============================================================
   // <T>出生点对象。</T>
   //============================================================
   public class FMbMapBirth
   {
      // 出生点
      protected SIntPoint2 _location = new SIntPoint2();

      // 敌机集合
      protected FObjects<FMbMapBirthEnemy> _birthEnemys = new FObjects<FMbMapBirthEnemy>();

      //============================================================
      // <T>获取或得到出生点。</T>
      //============================================================
      public SIntPoint2 Location {
         get { return _location; }
         set { _location = value; }
      }

      //============================================================
      // <T>获取敌机集合。</T>
      //============================================================
      public FObjects<FMbMapBirthEnemy> BirthEnemys {
         get { return _birthEnemys; }
      }

      //============================================================
      // <T>加载配置文件。</T>
      //============================================================
      public void LoadConfig(FXmlNode config) {
         if (config.Contains("location")) {
            _location.Parse(config.Get("location"));
         }
         foreach (FXmlNode node in config.Nodes) {
            FDsMapBirthEnemy birthEnemy = new FDsMapBirthEnemy();
            birthEnemy.Resource.LoadConfig(node);
            _birthEnemys.Push(birthEnemy.Resource);
         }
      }
   }
}
