using MO.Common.Collection;
using MO.Common.Content;
using MO.Common.Lang;

namespace MO.Core.Window.Services {

   public class RAdManager {

      public const string PROTOCOL = "LDAP";

      public const string TYPE_ALL = "*";

      private static IResource _resource = RResource.Find(typeof(RAdManager));

      private static FDictionary<FAdObjectConfig> _configs;

      static RAdManager() {
         _configs = new FDictionary<FAdObjectConfig>();
         /*foreach(FXmlNode node in _resource.Config.Nodes){
            if (node.IsName(FAdObjectConfig.TAG)) {
               FAdObjectConfig config = new FAdObjectConfig();
               config.LoadConfig(node);
               _configs[config.Name] = config;
            }
         }*/
      }

      public static IAdConnection Connect(FAdConnectInfo info) {
         FAdConnection connection = new FAdConnection();
         connection.ConnectInfo.Assign(info);
         connection.Connect();
         return connection;
      }

      internal static FAdObject CreateObject(string type) {
         FAdObjectConfig config = _configs[type];
         if (config == null) {
            config = _configs[TYPE_ALL];
            if (config == null) {
               throw new FFatalException("Unknown object type={0}", type);
            }
         }
         return config.CreateInstance();
      }

   }

}
