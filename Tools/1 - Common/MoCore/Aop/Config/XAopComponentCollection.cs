using MO.Common.Collection;
using MO.Common.Lang;

namespace MO.Core.Aop.Config {

   public class XAopComponentCollection : FObjects<IAopNode>, IAopNodes {

      private FDictionary<XAopComponent> _ids = new FDictionary<XAopComponent>();

      private FDictionary<XAopComponent> _faces = new FDictionary<XAopComponent>();

      public XAopComponentCollection() {
      }

      public XAopComponent FindById(string id) {
         return _ids[id];
      }

      public XAopComponent FindByFace(string face) {
         return _faces[face];
      }

      #region IAopConfigs members

      public IAopNode Find(string id) {
         return _ids[id];
      }

      public override void Push(IAopNode config) {
         base.Push(config);
         // Set id
         XAopComponent xcomponent = (XAopComponent)config;
         if(!RString.IsEmpty(xcomponent.Id)) {
            if (_ids.Contains(xcomponent.Id)) {
               throw new FFatalException("Component id({1}) is exists.", xcomponent.Id);
            }
            _ids[xcomponent.Id] = xcomponent;
         }
         // Set face
         if(!RString.IsEmpty(xcomponent.FaceName)) {
            if (_faces.Contains(xcomponent.FaceName)) {
               throw new FFatalException("Component face name({1}) is exists.", xcomponent.FaceName);
            }
            _faces[xcomponent.FaceName] = xcomponent;
         }
      }

      #endregion

   }

}
