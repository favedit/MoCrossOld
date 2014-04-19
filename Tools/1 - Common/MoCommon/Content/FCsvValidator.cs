using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Lang;
using MO.Common.System;

namespace MO.Common.Content {

   public class FCsvValidator {

      /*public const string TAG = "Validator";

      public const string PTY_TYPE = "type";

      public const string PTY_CLASS = "class";

      private FXmlNode _config;

      private ECsvWorkType _type;

      private IValidator _validator;

      public FXmlNode Config {
         get { return _config; }
         set { _config = value; }
      }

      public ECsvWorkType Type {
         get { return _type; }
         set { _type = value; }
      }

      public IValidator Validator {
         get { return _validator; }
         set { _validator = value; }
      }

      public void LoadConfig(FXmlNode config) {
         _config = config;
         // get type
         string type = config[PTY_TYPE];
         _type = (ECsvWorkType)REnum.ToValue(typeof(ECsvWorkType), type);
         // get validator
         string validator = config[PTY_CLASS];
         try {
            _validator = (IValidator)RClass.CreateInstance(validator);
         } catch (Exception e) {
            throw new FFatalException(e, "Create validator class failure. (class={0})", validator);
         }
         if (_validator == null) {
            throw new FFatalException("Create validator class failure. (class={0})", validator);
         }
      }

      public bool Check(FValidatorParameters parameters) {
         if (_validator != null) {
            return _validator.Check(parameters);
         }
         return false;
      }*/

   }

}
