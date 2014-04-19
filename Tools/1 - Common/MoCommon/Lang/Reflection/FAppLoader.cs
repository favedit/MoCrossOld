using System;
using System.Collections.Generic;
using System.Text;
using System.Reflection;

namespace MO.Common.Lang.Reflection {

   public class FAppLoader : MarshalByRefObject {

      private Assembly _assembly;

      public void LoadAssembly(string assemblyName) {
         _assembly = Assembly.LoadFile(assemblyName);
      }

      public void LoadCOM(string progID) {
         //tmpType = Type.GetTypeFromProgID(progID);
         //tmpClass = Activator.CreateInstance(tmpType);
      }

      public object InvokeMember(string name, BindingFlags invokeAttr, object[] args) {
         /*if (tmpClass != null && tmpType != null) {
            return tmpType.InvokeMember(name,
                invokeAttr | BindingFlags.IgnoreCase | BindingFlags.Public | BindingFlags.Instance,
                null, tmpClass, args);
         } else {
            return null;
         }*/
         return null;
      }

      public bool Invoke(string typeName, string methodName, out object result, params Object[] args) {
         result = null;
         if (_assembly == null) {
            return false;
         }
         Type type = _assembly.GetType(typeName);
         if (type == null) {
            return false;
         }
         MethodInfo method = type.GetMethod(methodName);
         if (method == null) {
            return false;
         }
         Object instance = Activator.CreateInstance(type);
         result = method.Invoke(instance, args);
         //return obj.GetType().InvokeMember(name, BindingFlags.Public | BindingFlags.Instance | BindingFlags.InvokeMethod, null, obj, paras);
         return true;
      }
   }

}
