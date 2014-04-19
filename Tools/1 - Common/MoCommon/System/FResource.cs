using System;
using MO.Common.Content;
using MO.Common.Lang;

namespace MO.Common.System
{
   ///============================================================
   /// <summary>资源对象。</summary>
   ///============================================================
   public class FResource : IResource
   {
      public const string TAG_RESOURCE = "Resource";

      public const string TAG_CONTENTS = "Contents";

      public const string TAG_CONTENT = "Content";

      public const string PTY_ID = "id";

      public const string FIX_MULTISTRING = "ms_";

      public char MESSAGE_FLAG = '|';

      protected FXmlDocument _document;

      protected FXmlNode _config;

      protected FXmlNodeMap _contents = new FXmlNodeMap();

      ///============================================================
      public void LoadResource(Type type) {
         _document = new FXmlDocument();
         _document.LoadResource(type);
         _config = _document.Root;
         // Build contents map
         FXmlNode resConfig = _config.Find(TAG_RESOURCE);
         if (resConfig != null) {
            FXmlNode ctsConfig = resConfig.Find(TAG_CONTENTS);
            if (ctsConfig != null) {
               RXml.BuildMap(ctsConfig, _contents, TAG_CONTENT, PTY_ID);
            }
         }
      }

      #region IResource members

      ///============================================================
      public FXmlDocument Document {
         get { return _document; }
      }

      ///============================================================
      public FXmlNode Config {
         get { return _config; }
      }

      ///============================================================
      public string FindString(string name) {
         if (_contents != null) {
            FXmlNode config = _contents[name];
            if (config != null) {
               return config.Text;
            }
         }
         return null;
      }

      ///============================================================
      public string FindDisplay(string name) {
         if (_contents != null) {
            FXmlNode config = _contents[name];
            if (config != null) {
               FXmlNode lanCfg = config.Find(RCulture.Language);
               string display = (lanCfg != null) ? lanCfg.Text : config[FIX_MULTISTRING + RCulture.Language];
               if (display != null) {
                  display = RString.TrimLines(display, MESSAGE_FLAG);
               }
               return display;
            }
         }
         return null;
      }

      ///============================================================
      public string FindDisplay(string name, params object[] args) {
         string display = FindDisplay(name);
         if (display != null && args != null) {
            return String.Format(display, args);
         }
         return null;
      }

      ///============================================================
      public int FindInt(string name) {
         throw new Exception("The method or operation is not implemented.");
      }

      ///============================================================
      public FXmlNode FindNode(string name) {
         throw new Exception("The method or operation is not implemented.");
      }

      ///============================================================
      public long FindLong(string name) {
         throw new Exception("The method or operation is not implemented.");
      }

      ///============================================================
      public bool FindBool(string name) {
         throw new Exception("The method or operation is not implemented.");
      }

      #endregion
   }
}
