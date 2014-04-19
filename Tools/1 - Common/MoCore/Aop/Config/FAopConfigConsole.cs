using MO.Common.Collection;
using MO.Common.Content;
using MO.Common.Lang;
using MO.Common.Lang.Reflection;

namespace MO.Core.Aop.Config {

   public class FAopConfigConsole : IAopConfigConsole{

      private XAopConfigCollection _configs = new XAopConfigCollection();

      private XAopReferenceCollection _references = new XAopReferenceCollection();

      private XAopDefineCollection _defines;

      private XAopNodesCollection _nodesCollection = new XAopNodesCollection();

      private FAttributes _environment = new FAttributes();

      //============================================================
      public FAopConfigConsole() {
         _nodesCollection[XAopConfig.TAG] = _configs;
         _nodesCollection[XAopReference.TAG] = _references;
      }

      //============================================================
      public FAttributes Environment {
         get { return _environment; }
      }

      //============================================================
      public FType FindType(string name) {
         return _references.FindType(name);
      }

      //============================================================
      public void LoadDocument(string filename) {
         LoadDocument(new FXmlDocument(filename));
      }

      //============================================================
      public void LoadDocument(FXmlDocument xdoc) {
         //xdoc.Formater.Formater = this;
         LoadConfig(xdoc.Root);
      }

      //============================================================
      public void LoadConfig(FXmlNode config) {
         if (config != null && config.HasNode()) {
            foreach (FXmlNode node in config.Nodes) {
               if (node.IsName(XAopConfig.TAG)) {
                  // Register config node
                  XAopConfig aopConfig = new XAopConfig();
                  aopConfig.Console = this;
                  aopConfig.LoadConfig(node);
                  _configs.Push(aopConfig);
               }else if (node.IsName(XAopReference.TAG)) {
                  // Register reference node
                  XAopReference aopReference = new XAopReference();
                  aopReference.Console = this;
                  aopReference.LoadConfig(node);
                  _references.Push(aopReference);
               } else {
                  // Find node type
                  XAopConfig aopConfig = (XAopConfig)_configs.Find(node.Name);
                  if (null == config) {
                     throw new FFatalException("Node({1}) is not be defined.", node.Name);
                  }
                  // Sync node collection
                  IAopNodes configs = _nodesCollection[node.Name];
                  if (null == configs) {
                     configs = aopConfig.CreateCollection<IAopNodes>();
                     _nodesCollection[node.Name] = configs;
                  }
                  // Create node instance
                  IAopNode aopCfg = aopConfig.CreateInstance<IAopNode>();
                  aopCfg.Console = this;
                  aopCfg.LoadConfig(node);
                  configs.Push(aopCfg);
               }
            }
         }
      }

      //============================================================
      public string Parse(string value) {
         if (_defines == null) {
            _defines = (XAopDefineCollection)_nodesCollection[XAopDefine.TAG];
         }
         return (_defines != null) ? _defines.Parse(value) : value;
      }

      //============================================================
      public IAopNodes Find(string type) {
         return _nodesCollection[type];
      }

      //============================================================
      public IAopNode Find(string type, string name) {
         IAopNodes configs = _nodesCollection[type];
         if (configs != null) {
            return configs.Find(name);
         }
         return null;
      }

      #region IAttributeFormat member

      //============================================================
      public string FormatDisplay(string value) {
         //return Parse(RString.Parse(value, _environment));
         return null;
      }

      //============================================================
      public string FormatValue(string value) {
         return value;
      }

      #endregion
   }

}
