using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Lang;
using MO.Common.Content;

namespace MO.Common.Text.Validator {

   public class FIpAddressValidator : FValidator {

      public const string MSG_INVALID = "invalid";

      public const string PTY_DESCRIPTION = "description";

      private static IResource _resource = RResource.Find(typeof(FIpAddressValidator));

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
         if(!RString.IsEmpty(value)) {
            string[] items = value.Split('.');
            if (items.Length != 4) {
               parameters.Description = _resource.FindDisplay(MSG_INVALID, _description);
               return false;
            }
            foreach (string item in items) {
               int result = 0;
               if (!Int32.TryParse(item, out result)) {
                  parameters.Description = _resource.FindDisplay(MSG_INVALID, _description);
                  return false;
               }
               if (result < 0 || result > 255) {
                  parameters.Description = _resource.FindDisplay(MSG_INVALID, _description);
                  return false;
               }
            }
         }
         return true;
      }
   }

}
