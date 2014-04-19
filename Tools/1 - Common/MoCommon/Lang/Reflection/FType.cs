using System;
using System.Collections.Generic;
using System.Text;
using System.Reflection;

namespace MO.Common.Lang.Reflection {

   public class FType {

      //private FField[] _fields;

      private Type _type;

      public FType(Type type) {
         _type = type;
      }

      public string Name {
         get { return _type.Name; }
      }

      public string FullName {
         get { return _type.FullName; }
      }

      public Type Type {
         get { return _type; }
      }

      public FField[] GetAllFields(BindingFlags flags) {
         FObjects<FField> fields = new FObjects<FField>();
         Type type = _type;
         while (type != null) {
            foreach (FieldInfo fieldInfo in type.GetFields(flags)) {
               fields.Push(new FField(fieldInfo));
            }
            type = type.BaseType;
         }
         return fields.ToArray();
      }

      public FMethod[] GetAllMethods(BindingFlags flags) {
         FObjects<FMethod> methods = new FObjects<FMethod>();
         Type type = _type;
         while (type != null) {
            foreach (MethodInfo methodInfo in type.GetMethods(flags)) {
               methods.Push(new FMethod(methodInfo));
            }
            type = type.BaseType;
         }
         return methods.ToArray();
      }

      /*public FFieldInfo[] GetFields(object value) {
               FObjects<FFieldInfo> infos = new FObjects<FFieldInfo>();
               if (value != null) {
                  Type type = value.GetType();
                  FieldInfo[] fields = type.GetFields();
                  foreach (FieldInfo field in fields) {
                     FFieldInfo fieldInfo = new FFieldInfo();
                     fieldInfo.Name = field.Name;
                     fieldInfo.Value = field.GetValue(value);
                     collection.Push(fieldInfo);
                  }
               }
               return collection;
            }*/

      public static FField[] GetFields(object value) {
         FObjects<FField> infos = new FObjects<FField>();
         if(value != null) {
            Type type = value.GetType();
            FieldInfo[] fields = type.GetFields();
            foreach(FieldInfo field in fields) {
               FField fieldInfo = new FField();
               fieldInfo.Name = field.Name;
               fieldInfo.Value = field.GetValue(value);
               infos.Push(fieldInfo);
            }
         }
         return infos.ToArray();
      }
   }

}
