using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Lang;
using MO.Common.Content;

namespace MO.Core.Aop.Config
{
   public interface IAopNodes
   {
      IAopNode Find(string id);

      void Push(IAopNode config);
   }
}
