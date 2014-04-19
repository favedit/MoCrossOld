using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MO.Core.Logic.Client {

   public class FInfoDebugClient : FAbsInfoClient{

      public const string PROTOCOL = "MOBJ-DBG";

      protected override void SendProtocol() {
         _socket.Output.Write(PROTOCOL);
      }

   }

}
