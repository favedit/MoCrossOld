using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Lang;

namespace MO.Common.Content {
   
   public class FXmlContext : IXmlContext {

      private IXmlObjectDocument _document;

      private IXmlObject _top;

      private IXmlObject _parent;

      private FXmlNode _config;

      public FXmlContext() {
      }

      #region IXmlContext members

      public IXmlObjectDocument Document {
         get { return _document; }
         set { _document = value; }
      }

      public IXmlObject Top {
         get { return _top; }
         set { _top = value; }
      }

      public IXmlObject Parent {
         get { return _parent; }
         set { _parent = value; }
      }

      public FXmlNode Config {
         get { return _config; }
         set { _config = value; }
      }

      #endregion

   }

}
