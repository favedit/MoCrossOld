using MO.Common.Content;
using MoMobile.Map.Common;

namespace MoMobile.Map.Designer
{
   //============================================================
   // <T>地图格子对象。</T>
   //============================================================
   public class FDsMapCell
   {
      // 资源定义
      protected FMbMapCell _resouece = new FMbMapCell();

      //============================================================
      // <T>获取或得到资源。</T>
      //============================================================
      public FMbMapCell Resource {
         get { return _resouece; }
         set { _resouece = value; }
      }

      //============================================================
      // <T>保存配置文件。</T>
      //============================================================
      public void SaveConfig(FXmlNode config) {

      }
   }
}
