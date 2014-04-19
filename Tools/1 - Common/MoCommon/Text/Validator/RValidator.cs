using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Lang;
using MO.Common.Content;
using MO.Common.System;

namespace MO.Common.Text.Validator {

   public class RValidator {

      private static IResource _resource = RResource.Find(typeof(RValidator));

      private static FValidatorConfigs _configs = new FValidatorConfigs();

      static RValidator() {
         FXmlNode config = _resource.Config.Find(FValidatorConfigs.TAG);
         if (config != null) {
            _configs.LoadConfig(config);
         }
      }

      public static IValidator Create(FXmlNode config) {
         // Create by class
         string clazz = config[FValidator.PTY_CLASS];
         if (!RString.IsEmpty(clazz)) {
            return (IValidator)RXmlObject.Create(config);
         }
         // Create by name
         string type = config[FValidator.PTY_TYPE];
         FValidatorConfig validatorConfig = _configs[type];
         if (validatorConfig != null) {
            IValidator validator = validatorConfig.CreateInstance();
            validator.LoadConfig(config);
            return validator;
         }
         return null;
      }
   }

}
