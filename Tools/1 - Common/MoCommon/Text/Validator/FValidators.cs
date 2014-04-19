using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Lang;
using MO.Common.Content;
using MO.Common.System;

namespace MO.Common.Text.Validator
{
   public class FValidators<T>
         where T : IValidator
   {
      /*public const string NAME = "Validators";

      public override void LoadChildConfig(FXmlNode config) {
         if (config.IsName(FValidator.NAME)) {
            Push((T)RValidator.Create(config));
         }
      }

      public bool Check(FValidatorParameters parameters) {
         foreach (IValidator validator in this) {
            if (!validator.Check(parameters)) {
               return false;
            }
         }
         return true;
      }

      public bool Check(string type, FValidatorParameters parameters) {
         foreach (IValidator validator in this) {
            if (validator.Type == type) {
               if (!validator.Check(parameters)) {
                  return false;
               }
            }
         }
         return true;
      }*/
   }
}
