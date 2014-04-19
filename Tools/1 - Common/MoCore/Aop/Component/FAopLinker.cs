using System;
using System.Collections.Generic;
using System.Text;
using System.Reflection;
using MO.Common.Lang;
using MO.Common.Lang.Reflection;

namespace MO.Core.Aop.Component {

   public class FAopLinker {

      private string _name;

      private FieldInfo _field;

      private FType _face;

      public string Name {
         get { return _name; }
         set { _name = value; }
      }

      public FieldInfo Field {
         get { return _field; }
         set { _field = value; }
      }

      public FType Face {
         get { return _face; }
         set { _face = value; }
      }

      public void Link(FieldInfo field) {
         _name = field.Name;
         _field = field;
         _face = RClass.Find(field.FieldType);
         if (!_face.Type.IsInterface) {
            throw new FFatalException("Linker must is interface. ({0}={1})", _name, _face.Type.FullName);
         }
      }

   }

}
