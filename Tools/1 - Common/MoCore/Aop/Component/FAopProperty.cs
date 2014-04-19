using System;
using System.Collections.Generic;
using System.Text;
using System.Reflection;
using MO.Common.Lang;

namespace MO.Core.Aop.Component {

   public class FAopProperty {

      private string _name;

      private FieldInfo _field;

      private Type _fieldType;

      public string Name {
         get { return _name; }
         set { _name = value; }
      }

      public FieldInfo Field {
         get { return _field; }
         set { _field = value; }
      }

      public Type FieldType {
         get { return _fieldType; }
         set { _fieldType = value; }
      }

      public void Link(FieldInfo field) {
         _name = field.Name;
         if (_name.StartsWith("_")) {
            _name = _name.Substring(1);
         }
         _field = field;
         _fieldType = field.FieldType;
      }

   }

}
