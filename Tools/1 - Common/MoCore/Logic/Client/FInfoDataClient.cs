using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MO.Core.Logic.Client {

   public class FInfoDataClient : FAbsInfoClient{

      public const string PROTOCOL = "MOBJ-DAT";

      protected override void SendProtocol() {
         _socket.Output.Write(PROTOCOL);
      }

   }

}
