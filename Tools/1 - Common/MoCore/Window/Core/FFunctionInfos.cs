using System;
using System.Collections.Generic;
using MO.Common.Collection;
using MO.Common.Lang;

namespace MO.Core.Window.Core {

   public class FFunctionInfos : FObjects<FFunctionInfo>, IComparer<FFunctionInfo> {

      private FDictionary<FFunctionInfo> _names = new FDictionary<FFunctionInfo>();

      public FFunctionInfos() {
      }

      public override void Sort() {
         //Array.Sort(_memory, 0, _count, this);
      }

      public FFunctionInfo FindByName(string name) {
         return _names[name];
      }

      public override void Push(FFunctionInfo item) {
         if(!RString.IsEmpty(item.Name)) {
            _names[item.Name] = item;
         }
         base.Push(item);
      }

      #region IComparer<FFunctionInfo> implements

      public int Compare(FFunctionInfo x, FFunctionInfo y) {
         if (x != null && y != null) {
            return x.FunctionIndex - y.FunctionIndex;
         }
         return 0;
      }

      #endregion
   }

}
