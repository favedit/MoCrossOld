using System;
using System.Collections.Generic;
using System.Text;

namespace MO.Core.Window.Services {

   public enum EAppIsolated {
      /// <summary>
      /// 僾儘僙僗撪
      /// </summary>
      InProcess = 0,

      /// <summary>
      /// 僾儘僙僗奜
      /// </summary>
      OutProcess = 1,

      /// <summary>
      /// 僾乕儖偺僾儘僙僗
      /// </summary>
      PooledProcess = 2
   }
}
