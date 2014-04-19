using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Lang;
using MO.Common.System;

namespace MO.Common.Text.Validator {

   public abstract class FValidator : IValidator {

      public const string NAME = "Validator";

      public abstract bool DoCheck(FValidatorParameters parameters);

      public virtual bool Check(FValidatorParameters parameters) {
         parameters.Validator = this;
         bool result = DoCheck(parameters);
         if (parameters.RaiseException && !result) {
            throw new FValidException(parameters.Description);
         }
         return result;
      }

   }

}
