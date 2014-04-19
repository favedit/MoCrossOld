using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MO.Core.Logic.Client {

   public class FInfoCommandClient : FAbsInfoClient{

      public const string PROTOCOL = "MOBJ-CMD";

      protected override void SendProtocol() {
         _socket.Output.Write(PROTOCOL);
      }

   }

}
