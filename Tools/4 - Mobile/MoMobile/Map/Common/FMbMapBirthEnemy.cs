using MO.Common.Content;
using MO.Common.Lang;

namespace MoMobile.Map.Common
{
   //============================================================
   // <T>出生点敌机对象。</T>
   //============================================================
   public class FMbMapBirthEnemy
   {
      // 模版编号
      protected int _templateId;

      //============================================================
      // <T>获取或得到模版编号。</T>
      //============================================================
      public int TemplateId {
         get { return _templateId; }
         set { _templateId = value; }
      }

      //============================================================
      // <T>加载配置文件。</T>
      //============================================================
      public void LoadConfig(FXmlNode config) {
         if (config.Contains("template_id")) {
            _templateId = RInt.Parse(config.Get("template_id"));
         }
      }
   }
}
