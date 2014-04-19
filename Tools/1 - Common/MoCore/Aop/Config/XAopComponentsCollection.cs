using MO.Common.Collection;
using MO.Common.Lang;

namespace MO.Core.Aop.Config {

   public class XAopComponentsCollection : FObjects<IAopNode>, IAopNodes {

      private FDictionary<XAopComponents> _faces = new FDictionary<XAopComponents>();

      public XAopComponentsCollection() {
      }

      #region IAopConfigs members

      public IAopNode Find(string id) {
         return _faces[id];
      }

      public override void Push(IAopNode config) {
         base.Push(config);
         XAopComponents xcomponent = config as XAopComponents;
         if (xcomponent != null) {
            // Set face
            if(!RString.IsEmpty(xcomponent.FaceName)) {
               if (_faces.Contains(xcomponent.FaceName)) {
                  throw new FFatalException("Components face name({1}) is exists.", xcomponent.FaceName);
               }
               _faces[xcomponent.FaceName] = xcomponent;
            }
         }
      }

      #endregion

   }

}
