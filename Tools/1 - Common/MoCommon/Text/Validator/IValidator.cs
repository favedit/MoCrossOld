using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Content;
using MO.Common.System;

namespace MO.Common.Text.Validator {

   public interface IValidator : IXmlConfig {

      bool Check(FValidatorParameters parameters);

   }

}
