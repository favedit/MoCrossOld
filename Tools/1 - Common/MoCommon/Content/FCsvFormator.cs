using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Lang;
using MO.Common.System;

namespace MO.Common.Content
{
   public class FCsvFormator
   {
      public const string TAG = "Formator";

      public const string PTY_TYPE = "type";

      public const string PTY_CLASS = "class";

      private FXmlNode _config;

      private ECsvWorkType _type;

      public FXmlNode Config {
         get { return _config; }
         set { _config = value; }
      }

      public ECsvWorkType Type {
         get { return _type; }
         set { _type = value; }
      }

      public void LoadConfig(FXmlNode config) {
         _config = config;
         // get type
         string type = config[PTY_TYPE];
         _type = (ECsvWorkType)REnum.ToValue(typeof(ECsvWorkType), type);
         // get validator
         string formator = config[PTY_CLASS];
         try {
            //_formator = (IFormator)RClass.CreateInstance(formator);
         } catch(Exception e) {
            throw new FFatalException(e, "Create formator class failure. (class={0})", formator);
         }
      }

      /*public bool Format(FFormatorParameters parameters) {
         if (_formator != null) {
            return _formator.Format(parameters);
         }
         return false;
      }*/
   }
}
