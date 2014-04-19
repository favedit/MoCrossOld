using System.IO;
using System.Reflection;
using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;

namespace MO.Core.Forms.Common
{

   public class FApplicationConfig{

      public const string TYPE_EXE = ".exe";

      public const string TYPE_DLL = ".dll";

      public const string TYPE_XML = ".xml";

      public const string PTY_ID = "id";

      private FXmlNode _config;

      private string _path;

      private FXmlNodeMap _map = new FXmlNodeMap();

      private bool _exists;

      public FApplicationConfig() {
         LoadConfig(null);
      }

      public FApplicationConfig(string name) {
         LoadConfig(name);
      }

      public bool Exists {
         get { return _exists; }
      }

      protected void LoadConfig(string name) {
         Assembly assembly = Assembly.GetEntryAssembly();
         FFileInfo info = new FFileInfo(assembly.Location);
         _path = info.DirectoryName;
         // Make file name
         string file = null;
         if (name != null && name.IndexOf(':') != -1) {
            file = name;
         } else {
            AssemblyName aname = assembly.GetName();
            if (name == null) {
               file = RFile.MakeFileName(_path, aname.Name);
            } else {
               file = RFile.MakeFileName(_path, name);
            }
            if (!file.EndsWith(TYPE_XML)) {
               file += TYPE_XML;
            }
         }
         // Load config
         FXmlDocument xdoc = new FXmlDocument();
         //xdoc.Formater.Formater = this;
         _exists = File.Exists(file);
         if (_exists) {
            _exists = true;
            xdoc.LoadFile(file);
         }
         // Set config
         _config = xdoc.Root;
         BuildConfigMap(_config);
      }

      public string Parse(string value) {
         if (value != null) {
            value = value.Replace("${app.path}", _path);
         }
         return value;
      }

      public FXmlNode Config {
         get { return _config; }
      }

      public bool ContainsNode(string name) {
         return _config.ContainsNode(name);
      }

      public FXmlNode Find(string name) {
         return _config.Find(name);
      }

      public string FindText(string name) {
         return _config.FindText(name);
      }

      public FXmlNode FindConfig(string id) {
         return _map[id];
      }

      private void BuildConfigMap(FXmlNode config) {
         string id = config[PTY_ID];
         if (!RString.IsEmpty(id)) {
            _map[id] = config;
         }
         if (config.HasNode()) {
            foreach (FXmlNode node in config.Nodes) {
               BuildConfigMap(node);
            }
         }
      }

      #region IAttributeFormat implements

      public string FormatDisplay(string value) {
         return Parse(value);
      }

      public string FormatValue(string value) {
         return value;
      }

      #endregion
   }

}
