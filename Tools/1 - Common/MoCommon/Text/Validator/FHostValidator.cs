using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Lang;
using MO.Common.Content;

namespace MO.Common.Text.Validator {

   public class FHostValidator : FValidator {

      public const string MSG_MAX_COUNT = "MAX_COUNT";

      public const string PTY_DESCRIPTION = "description";

      private static IResource _resource = RResource.Find(typeof(FHostValidator));

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
         string host = (string)parameters.Value;
         if(!RString.IsEmpty(host)) {
            /*string[] items = host.Split('.');
            if (items.Length == 4) {
               foreach (string item in items) {
                  int result=0;
                  if (!Int32.TryParse(item, result)) {
                     parameters.Description = _resource.FindDisplay(MSG_MAX_COUNT, _maxCount);
                     return false;
                  }
               }
            }*/
         }
         return true;
      }
   }

}
