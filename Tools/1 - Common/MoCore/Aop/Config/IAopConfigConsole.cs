using MO.Common.Content;
using MO.Common.Lang.Reflection;

namespace MO.Core.Aop.Config
{
   public interface IAopConfigConsole
   {
      void LoadDocument(string filename);

      void LoadDocument(FXmlDocument xdoc);

      void LoadConfig(FXmlNode config);

      FType FindType(string name);

      string Parse(string value);

      IAopNodes Find(string type);

      IAopNode Find(string type, string name);
   }
}
