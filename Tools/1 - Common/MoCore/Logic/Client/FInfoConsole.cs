using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MO.Core.Aop;
using MO.Common.Lang;
using MO.Common.Lang.Reflection;

namespace MO.Core.Logic.Client {

   public class FInfoConsole : IInfoConsole {

      [AProperty]
      protected string _host;

      [AProperty]
      protected int _port;

      public T AllocClient<T>()
          where T : IInfoClient {
         T client = RClass.CreateInstance<T>();
         client.Connect(_host, _port);
         return client;
      }

   }

}
