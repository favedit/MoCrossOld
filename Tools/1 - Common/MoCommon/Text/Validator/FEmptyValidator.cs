using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Lang;
using MO.Common.Content;

namespace MO.Common.Text.Validator {

   public class FEmptyValidator : FValidator {

      public const string MSG_IS_EMPTY = "IS_EMPTY";

      public const string PTY_DESCRIPTION = "description";

      private static IResource _resource = RResource.Find(typeof(FEmptyValidator));

      private string _description;

      public string Description {
         get { return _description; }
         set { _description = value; }
      }

      public override void LoadConfig(FXmlNode config) {
         base.LoadConfig(config);
         _description = config[PTY_DESCRIPTION];
      }

      public override bool DoCheck(FValidatorParameters parameters) {
         string value = RString.Nvl(parameters.Value);
         if (RString.IsEmpty(value)) {
            parameters.Description = _resource.FindDisplay(MSG_IS_EMPTY, _description);
            return false;
         }
         return true;
      }
   }

}
