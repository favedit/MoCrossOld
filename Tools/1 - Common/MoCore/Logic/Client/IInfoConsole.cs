using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MO.Core.Aop;

namespace MO.Core.Logic.Client {

    public interface IInfoConsole {

       T AllocClient<T>()
          where T : IInfoClient;

    }

}
