using MO.Common.Collection;
using MO.Common.Lang;

namespace MO.Core.Aop.Config {

   public class XAopNodeCollection<T> : FObjects<T>, IAopNodes
         where T : IAopNode {

      protected bool _duplicate = true;

      protected FDictionary<T> _ids = new FDictionary<T>();

      #region IAopConfigs members

      public IAopNode Find(string id) {
         return _ids[id];
      }

      public void Push(IAopNode config) {
         if (!_duplicate && _ids.Contains(config.Id)) {
            throw new FFatalException("Has contains node(id={0})", config.Id);
         }
         base.Push((T)config);
         if(!RString.IsEmpty(config.Id)) {
            _ids[config.Id] = (T)config;
         }
      }

      #endregion

   }

}
