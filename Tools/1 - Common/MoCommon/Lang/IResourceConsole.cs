using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MO.Common.Lang
{
   //============================================================
   // <T>资源管理接口。</T>
   //============================================================
   public interface IResourceConsole
   {
      //============================================================
      // <T>创建资源对象。</T>
      //
      // @param type 类型
      // @return 资源对象
      //============================================================
      IResource CreateResource(Type type);
   }
}
