using MO.Common.Content;
using MO.Common.Lang;

namespace MO.Design2d.Frame.Core
{
   public class FUiFormHistoryStep : FObject
   {
      protected FXmlDocument _document = new FXmlDocument();

      protected int _index;

      protected FXmlNode _config;

      public FUiFormHistoryStep() {
         _config = _document.Root;
      }

      public int Index {
         get { return _index; }
         set { _index = value; }
      }

      public FXmlNode Config {
         get { return _config; }
      }
   }
}
