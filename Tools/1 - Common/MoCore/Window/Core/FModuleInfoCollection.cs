using MO.Common.Collection;
using MO.Common.Lang;

namespace MO.Core.Window.Core {

   public class FModuleInfoCollection : FObjects<FModuleInfo> {

      private FProcessInfo _process;

      private FMap<uint, FModuleInfo> _mapAddress = new FMap<uint, FModuleInfo>();

      public FModuleInfoCollection() {
      }

      public FModuleInfoCollection(FProcessInfo process) {
         _process = process;
      }

      public FProcessInfo Process {
         get { return _process; }
         set { _process = value; }
      }

      public FModuleInfo FindByAddress(uint address) {
         return _mapAddress.Get(address);
      }

      public override void Push(FModuleInfo item) {
         item.Process = _process;
         // build base address map
         if (item.BaseAddress != 0) {
            _mapAddress.Set(item.BaseAddress, item);
         }
         // Push
         base.Push(item);
      }

   }

}
