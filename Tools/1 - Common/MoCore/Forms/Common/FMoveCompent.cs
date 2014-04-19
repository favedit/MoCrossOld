using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Drawing;

namespace MO.Core.Forms.Common
{
   public class FMoveCompent
   {
      // 当前坐标
      protected Point _point = new Point();

      protected object _tag;

      protected string _value;

      //============================================================
      public Point Location {
         get { return _point; }
         set { _point = value; }
      }

      //============================================================
      public object Tag {
         get { return _tag; }
         set { _tag = value; }
      }

      //============================================================
      public string Value {
         get { return _value; }
         set { _value = value; }
      }
   }
}
