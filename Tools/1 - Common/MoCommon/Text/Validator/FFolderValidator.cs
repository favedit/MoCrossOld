using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Lang;
using MO.Common.Content;
using System.IO;
using MO.Common.IO;

namespace MO.Common.Text.Validator {

   public class FFolderValidator : FValidator {

      public const string MSG_NOT_EXISTS = "NOT_EXISTS";

      private static IResource _resource = RResource.Find(typeof(FFolderValidator));

      public override void LoadConfig(FXmlNode config) {
         base.LoadConfig(config);
      }

      public override bool DoCheck(FValidatorParameters parameters) {
         string dir = (string)parameters.Value;
         if (!RString.IsEmpty(dir)) {
            string fulldir = RFile.CurrentFileName(dir);
            if (!Directory.Exists(fulldir)) {
               parameters.Description = _resource.FindDisplay(MSG_NOT_EXISTS, fulldir);
               return false;
            }
         }
         return true;
      }
   }

}
