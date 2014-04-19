using MO.Common.Content;
using MO.Common.IO;

namespace MO.Content3d.Resource.Template
{
   //============================================================
   public class FDrTemplateReference
   {
      protected string _src;

      protected FDrTemplate _template;

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void LoadConfig(FXmlNode xconfig) {
         _src = xconfig.Get("src");
      }

      //============================================================
      // <T>存储配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void SaveConfig(FXmlNode xconfig) {
         xconfig.Set("src", _src);
      }

      //============================================================
      public void Serialize(IOutput output) {
         output.WriteString(_src);
      }
   }
}
