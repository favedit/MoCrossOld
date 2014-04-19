using System;
using System.Collections.Generic;
using System.Text;

namespace MO.Core.Window.Services {

   public class FAdUser : FAdObject {

      public const string CLASS_NAME = "user";

      public FAdUser() {
      }

      public FAdUser(FAdConnection connection)
         : base(connection) {
      }

   }

}
