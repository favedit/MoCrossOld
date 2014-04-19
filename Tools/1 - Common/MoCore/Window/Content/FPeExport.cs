using MO.Core.Window.Api;
using MO.Core.Window.Core;

namespace MO.Core.Window.Context {
   
   public class FPeExport {

      private string _name;

      internal SImageExportDirectory _data;

      internal FFunctionInfos _functions = new FFunctionInfos();

      public string Name {
         get { return _name; }
         set { _name = value; }
      }

      public SImageExportDirectory Data {
         get { return _data; }
         set { _data = value; }
      }

      public FFunctionInfos Functions {
         get { return _functions; }
      }

      public void Clear() {
         _name = null;
         _functions.Clear();
      }

   }

}
