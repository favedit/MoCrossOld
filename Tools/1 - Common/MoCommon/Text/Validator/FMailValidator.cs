using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Lang;
using MO.Common.Content;

namespace MO.Common.Text.Validator {

   public class FMailValidator : FValidator {

      public const string MSG_MAX_COUNT = "MAX_COUNT";

      public const string MSG_INVALID = "INVALID";

      public const string MSG_NOT_FIND_AT = "NOT_FIND_AT";

      public const string MSG_NOT_FIND_DOT = "NOT_FIND_DOT";

      public const string PTY_MAX_COUNT = "max_count";

      private static IResource _resource = RResource.Find(typeof(FMailValidator));

      private int _maxCount;

      public int MaxCount {
         get { return _maxCount; }
         set { _maxCount = value; }
      }

      public override void LoadConfig(FXmlNode config) {
         base.LoadConfig(config);
         if (config.Contains(PTY_MAX_COUNT)) {
            _maxCount = config.GetInteger(PTY_MAX_COUNT);
         }
      }

      public bool CheckMail(FValidatorParameters parameters, string mail) {
         if (!RString.IsEmpty(mail)) {
            mail = mail.Trim();
            // Check @
            int at = mail.IndexOf('@');
            if (at == -1) {
               parameters.Result = false;
               parameters.Description = _resource.FindDisplay(MSG_NOT_FIND_AT, mail);
               return false;
            }
            // Check .
            int dot = mail.IndexOf('.', at + 1);
            if (dot == -1) {
               parameters.Result = false;
               parameters.Description = _resource.FindDisplay(MSG_NOT_FIND_DOT, mail);
               return false;
            }
            // Check space
            string[] items = mail.Split('@', '.');
            foreach (string item in items) {
               if (RString.IsEmpty(item)) {
                  parameters.Result = false;
                  parameters.Description = _resource.FindDisplay(MSG_INVALID, mail);
                  return false;
               }
            }
         }
         return true;
      }

      public override bool DoCheck(FValidatorParameters parameters) {
         string mail = (string)parameters.Value;
         if (!RString.IsEmpty(mail)) {
            string[] lines = RString.TrimLines(mail.Split('\n'), false);
            if (_maxCount > 0) {
               if (lines.Length > _maxCount) {
                  parameters.Description = _resource.FindDisplay(MSG_MAX_COUNT, _maxCount);
                  return false;
               }
            }
            foreach (string line in lines) {
               if (!CheckMail(parameters, line)) {
                  return false;
               }
            }
         }
         return true;
      }
   }

}
