using System;
using System.Collections.Generic;
using System.Text;
using System.Reflection;

namespace MO.Common.Lang {

   public class FMethod {

      private string _name;

      private MethodInfo _method;

      public FMethod() {
      }

      public FMethod(MethodInfo method) {
         Link(method);
      }

      public bool IsMethod(string name, Type[] types) {
         return IsName(name) ? IsTypes(types) : false;
      }

      public bool IsName(string name) {
         return _name.Equals(name, StringComparison.CurrentCultureIgnoreCase);
      }

      public bool IsTypes(Type[] types) {
         ParameterInfo[] pinfos = _method.GetParameters();
         int count = pinfos.Length;
         if (count == types.Length) {
            for (int n = 0; n < count; n++) {
               if (types[n] != pinfos[n].ParameterType) {
                  return false;
               }
            }
            return true;
         }
         return false;
      }

      public int ParameterCount {
         get { return _method.GetParameters().Length; }
      }

      public string Name {
         get { return _name; }
         set { _name = value; }
      }

      public MethodInfo Method {
         get { return _method; }
      }

      public void Link(MethodInfo method) {
         _name = method.Name;
         _method = method;
      }

      public string GetInfo() {
         FString info = new FString();
         info.Append(_name);
         info.Append(" = ");
         return info.ToString();
      }

   }

}
