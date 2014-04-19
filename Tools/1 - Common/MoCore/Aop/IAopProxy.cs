using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.Remoting.Messaging;

namespace MO.Core.Aop {

   public interface IAopProxy {

      bool IsProxy { get; set;}

      MarshalByRefObject ProxyObject { get; }

      void ProcessBefore(IMethodCallMessage request);

      void ProcessAfter(IMethodCallMessage request, IMethodReturnMessage response);
   }

}
