using System;
using System.Collections.Generic;
using System.Text;
using System.Reflection;
using MO.Common.Lang;
using MO.Common.Lang.Reflection;

namespace MO.Core.Aop.Component {

   public class FAopDescriptor {

      private FType _type;

      private FAopLinkers _linkers = new FAopLinkers();

      private FAopProperties _properties = new FAopProperties();

      public FAopDescriptor() {
      }

      public bool HasLinker {
         get { return !_linkers.IsEmpty; }
      }

      public bool HasProperty {
         get { return !_properties.IsEmpty; }
      }

      public FAopLinkers Linkers {
         get { return _linkers; }
      }

      public FAopProperties Properties {
         get { return _properties; }
      }

      public void Parse(FType type) {
         _type = type;
         // Parse fields
         BindingFlags flags = BindingFlags.Instance | BindingFlags.Public | BindingFlags.NonPublic;
         FField[] fields = type.GetAllFields(flags);
         foreach (FField field in fields) {
            // Build property
            object[] aproperties = field.Field.GetCustomAttributes(typeof(APropertyAttribute), true);
            if (aproperties.Length > 0) {
               FAopProperty property = new FAopProperty();
               property.Link(field.Field);
               if (_properties.Contains(property.Name)) {
                  throw new FFatalException("Duplicate property. (name={0})", property.Name);
               }
               _properties[property.Name] = property;
               continue;
            }
            // Build linker
            object[] alinkers = field.Field.GetCustomAttributes(typeof(ALinkerAttribute), true);
            if (alinkers.Length > 0) {
               FAopLinker linker = new FAopLinker();
               linker.Link(field.Field);
               if (_linkers.Contains(linker.Name)) {
                  throw new FFatalException("Duplicate linker. (name={0})", linker.Name);
               }
               _linkers[field.Name] = linker;
               continue;
            }
         }
      }

      public FAopMethod FindMethod(string name, Type[] types) {
         BindingFlags flags = BindingFlags.Instance | BindingFlags.Public | BindingFlags.NonPublic;
         FMethod[] methods = _type.GetAllMethods(flags);
         // Find by types
         foreach (FMethod method in methods) {
            if (method.IsMethod(name, types)) {
               FAopMethod aopMethod = new FAopMethod();
               aopMethod.Link(method.Method);
               return aopMethod;
            }
         }
         // Find by type length
         foreach (FMethod method in methods) {
            if (method.IsName(name) && method.ParameterCount == types.Length) {
               FAopMethod aopMethod = new FAopMethod();
               aopMethod.Link(method.Method);
               return aopMethod;
            }
         }
         return null;
      }

   }

}
