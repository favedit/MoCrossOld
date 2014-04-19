using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MO.Common.IO
{
   public interface IPipe
   {
      bool Test(byte[] memory, int offset, int length);

      bool Read(byte[] memory, int offset, int length);
      
      bool Write(byte[] memory, int offset, int length);
   }
}
