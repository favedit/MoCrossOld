using System.Drawing;
using MO.Common.Content;
using MO.Common.Geom;
using MO.Common.Lang;

namespace MO.Core.Forms.Common
{
   public class FConfiguration
   {
      private FXmlNode _config;

      //============================================================
      public FConfiguration() {
      }

      //============================================================
      public FConfiguration(FXmlNode config) {
         _config = config;
      }

      //============================================================
      public FXmlNode Config {
         get { return _config; }
         set { _config = value; }
      }

      //============================================================
      public SIntRectangle GetRect(string key) {
         FXmlNode config = _config.Find(key);
         if(config != null) {
            return new SIntRectangle(config);
         }
         return null;
      }

      //============================================================
      public void SetRect(string key, SIntRectangle rect) {
         FXmlNode config = _config.Sync(key);
         rect.SaveConfig(config);
      }

      //============================================================
      public int GetInterger(string key) {
         return RInt.Parse(_config.Sync(key).Text);
      }

      //============================================================
      public void SetInterger(string key, int value) {
         _config.Sync(key).Text = value.ToString();
      }

      //============================================================
      public Font GetFont() {
         return null;
      }
   }
}
