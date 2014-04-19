using System;
using System.Collections.Generic;
using System.Text;

namespace MO.Core.Aop {

   public interface IAopProxyFactory {

      IAopProxy CreateProxyInstance(MarshalByRefObject marshalObject, Type type);

   }

}
