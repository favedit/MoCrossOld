using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Lang;

namespace MO.Common.Text.Validator {

   public class FValidatorParameters {

      private object _value;

      private bool _raiseException;

      private bool _result;

      private string _description;

      private IValidator _validator;

      private FDictionary<object> _parameters;

      public FValidatorParameters() {
         _parameters = new FDictionary<object>();
      }

      public FValidatorParameters(FDictionary<object> parameters) {
         _parameters = parameters;
      }

      public object Value {
         get { return _value; }
         set { _value = value; }
      }

      public bool RaiseException {
         get { return _raiseException; }
         set { _raiseException = value; }
      }

      public FDictionary<object> Parameters {
         get { return _parameters; }
      }

      public IValidator Validator {
         get { return _validator; }
         set { _validator = value; }
      }

      public string Description {
         get { return _description; }
         set { _description = value; }
      }

      public bool Result {
         get { return _result; }
         set { _result = value; }
      }

      public void Reset() {
         _value = null;
         _result = false;
         _description = null;
      }

   }

}
