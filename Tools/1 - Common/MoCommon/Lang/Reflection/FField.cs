using System;
using System.Collections.Generic;
using System.Text;
using System.Reflection;

namespace MO.Common.Lang {

   public class FField {

      private string _name;

      private object _value;

      private FieldInfo _field;

      public FField() {
      }

      public FField(FieldInfo field) {
         Link(field);
      }

      public string Name {
         get { return _name; }
         set { _name = value; }
      }

      public object Value {
         get { return _value; }
         set { _value = value; }
      }

      public FieldInfo Field {
         get { return _field; }
      }

      public void Link(FieldInfo field) {
         _name = field.Name;
         _field = field;
      }

      public string GetInfo() {
         FString info = new FString();
         info.Append(_name);
         info.Append(" = ");
         if (_value != null) {
            info.Append(_value.ToString());
         } else {
            info.Append("@null");
         }
         return info.ToString();
      }

   }

}
