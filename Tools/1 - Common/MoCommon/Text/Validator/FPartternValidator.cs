using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Lang;
using MO.Common.Content;

namespace MO.Common.Text.Validator {

   public class FPartternValidator : FValidator {

      public const string MSG_INVALID_PARTTERN = "invalid_parttern";

      public const string PTY_PARTTERN = "parttern";

      public const string PTY_DESCRIPTION = "description";

      public const string PTN_FILENAME = "!#$%&'()-=^~[]{}_., uln";

      private static IResource _resource = RResource.Find(typeof(FPartternValidator));

      private string _description;

      private string _parttern;

      private char[] _partternChars;

      public string Description {
         get { return _description; }
         set { _description = value; }
      }

      public string Parttern {
         get { return _parttern; }
         set { _parttern = value; }
      }

      public override void LoadConfig(FXmlNode config) {
         base.LoadConfig(config);
         _description = config[PTY_DESCRIPTION];
         _parttern = config[PTY_PARTTERN];
         if ("{filename}".EndsWith(_parttern, StringComparison.CurrentCultureIgnoreCase)) {
            _parttern = PTN_FILENAME;
         }
         if(!RString.IsEmpty(_parttern)) {
            _partternChars = RString.MakePattern(_parttern);
         }
      }

      public override bool DoCheck(FValidatorParameters parameters) {
         string value = RString.Nvl(parameters.Value);
         if(!RString.IsEmpty(value)) {
            if (!RString.CheckPattern(value, _partternChars)) {
               parameters.Description = _resource.FindDisplay(MSG_INVALID_PARTTERN, _description);
               return false;
            }
         }
         return true;
      }

   }

}
