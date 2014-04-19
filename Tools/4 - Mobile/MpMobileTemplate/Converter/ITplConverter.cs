using MO.Common.Content;

namespace MpMobileTemplate.Converter
{
   public interface ITplConverter
   {
      void LoadExportListConfig(FXmlNode exportListConfig);

      void LoadConfig(FXmlNode config);

      void Process();
   }
}
