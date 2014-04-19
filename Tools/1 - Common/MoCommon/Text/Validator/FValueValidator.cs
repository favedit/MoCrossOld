using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Lang;
using MO.Common.Content;

namespace MO.Common.Text.Validator {

   public class FValueValidator : FValidator {

      public const string MSG_LENGTH_LAGER = "length.lager";

      public const string MSG_LENGTH_LOWER = "length.lower";

      public const string PTY_MIN_LENGTH = "min";

      public const string PTY_MAX_LENGTH = "max";

      public const string PTY_MIN_EQUALS = "minEquals";

      public const string PTY_MAX_EQUALS = "maxEquals";

      public const string PTY_DESCRIPTION = "description";

      private static IResource _resource = RResource.Find(typeof(FValueValidator));

      protected int _minLength;

      protected int _maxLength;

      protected bool _minEquals;

      protected bool _maxEquals;

      private string _description;

      public int MinLength {
         get { return _minLength; }
         set { _minLength = value; }
      }

      public int MaxLength {
         get { return _maxLength; }
         set { _maxLength = value; }
      }

      public bool MinEquals {
         get { return _minEquals; }
         set { _minEquals = value; }
      }

      public bool MaxEquals {
         get { return _maxEquals; }
         set { _maxEquals = value; }
      }

      public string Description {
         get { return _description; }
         set { _description = value; }
      }

      public override void LoadConfig(FXmlNode config) {
         base.LoadConfig(config);
         if (config.Contains(PTY_MIN_LENGTH)) {
            _minLength = config.GetInteger(PTY_MIN_LENGTH);
         }
         if (config.Contains(PTY_MAX_LENGTH)) {
            _maxLength = config.GetInteger(PTY_MAX_LENGTH);
         }
         if (config.Contains(PTY_MIN_EQUALS)) {
            _minEquals = config.GetBoolean(PTY_MIN_EQUALS);
         }
         if (config.Contains(PTY_MAX_EQUALS)) {
            _maxEquals = config.GetBoolean(PTY_MAX_EQUALS);
         }
         _description = config[PTY_DESCRIPTION];
      }

      public override bool DoCheck(FValidatorParameters parameters) {
         string value = (string)parameters.Value;
         if (RString.IsEmpty(value)) {
            return false;
         }
         return true;
      }
   }

}
