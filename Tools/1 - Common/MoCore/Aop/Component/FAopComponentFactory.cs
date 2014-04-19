using System;
using MO.Common.Lang;
using MO.Common.Lang.Reflection;
using MO.Core.Aop.Config;

namespace MO.Core.Aop.Component {

   public class FAopComponentFactory {

      private static ILogger _logger = RLogger.Find(typeof(FAopComponentFactory));

      internal FAopConfigConsole _configs;

      internal FAopComponentConsole _components;

      public FAopComponentFactory() {
      }

      public object CreateInstance(XAopComponent xcomponent) {
         FAopComponentBuilder builder = new FAopComponentBuilder(xcomponent);
         if (xcomponent.HasConstructor) {
            object[] args = BuildParameters(builder, xcomponent.Constructor.Parameters);
            builder.Instance = xcomponent.CreateInstance(args);
         } else {
            builder.Instance = xcomponent.CreateInstance();
         }
         // Build proxy
         // Builder
         builder.Faces[xcomponent.LinkFace.FullName] = builder.Instance;
         LinkProperties(builder);
         LinkInterfaces(builder);
         ProcessInitializes(builder);
         return builder.Instance;
      }

      public object BuildParameter(FAopComponentBuilder builder, XAopParameter parameter) {
         if (parameter.DataType == EAopParameterType.String) {
            return parameter.DataValue;
         }
         throw new FFatalException("Unknown parameter type({0}).", parameter.DataType.ToString());
      }

      public object[] BuildParameters(FAopComponentBuilder builder, XAopParameters parameters) {
         int count = parameters.Count;
         object[] args = new object[count];
         for (int n = 0; n < count; n++) {
            args[n] = BuildParameter(builder, parameters[n]);
         }
         return args;
      }

      public void LinkProperties(FAopComponentBuilder builder) {
         XAopComponent xcomponent = builder.Component;
         if (xcomponent.HasProperty) {
            FAopDescriptor descriptor = xcomponent.Descriptor;
            foreach (XAopProperty xproperty in xcomponent.Properties) {
               FAopProperty property = descriptor.Properties[xproperty.Name];
               if (property == null) {
                  throw new FFatalException("Config has property. but instance has'nt this property({0}).", xproperty.Name);
               }
               if (_logger.DebugAble) {
                  _logger.Debug(this, "LinkProperties", "Set property {0} = {1}", xproperty.Name, xproperty.Value);
               }
               object fieldValue = Convert.ChangeType(xproperty.Value, property.FieldType);
               property.Field.SetValue(builder.Instance, fieldValue);
            }
         }
      }

      public void LinkInterfaces(FAopComponentBuilder builder) {
         XAopComponent xcomponent = builder.Component;
         FAopDescriptor descriptor = xcomponent.Descriptor;
         if (descriptor.HasLinker) {
            foreach (FAopLinker linker in descriptor.Linkers.Values) {
               // Find
               object find = null;
               if (builder.Faces.Contains(linker.Face.FullName)) {
                  find = builder.Faces[linker.Face.FullName];
               } else {
                  find = _components.Find(linker.Face.Type);
                  builder.Faces[linker.Face.FullName] = find;
               }
               // Check
               if (find == null) {
                  if (_logger.WarnAble) {
                     _logger.Warn(this, "LinkInterfaces", "Linker instance is null. {0}", linker.Name);
                  }
               } else {
                  if (_logger.DebugAble) {
                     _logger.Debug(this, "LinkInterfaces", "Set linker {0} = {1}", linker.Name, RClass.Dump(find));
                  }
                  linker.Field.SetValue(builder.Instance, find);
               }
            }
         }
      }

      public void ProcessMethod(FAopComponentBuilder builder, XAopMethod xmethod) {
         FAopDescriptor descriptor = builder.Component.Descriptor;
         Type[] types = xmethod.GetParameterTypes();
         FAopMethod method = descriptor.FindMethod(xmethod.Method, types);
         if (method == null) {
            throw new FFatalException("Config has method. but instance has'nt this method({0}).", xmethod.Method);
         }
         object[] args = BuildParameters(builder, xmethod.Parameters);
         if (_logger.DebugAble) {
            _logger.Debug(this, "ProcessMethod", "Call {0}({1})", xmethod.Method, args);
         }
         method.Invoke(builder.Instance, args);
      }

      public void ProcessInitializes(FAopComponentBuilder builder) {
         XAopComponent xcomponent = builder.Component;
         if (xcomponent.HasInitialize) {
            foreach (XAopMethod xmethod in xcomponent.Initializes) {
               ProcessMethod(builder, xmethod);
            }
         }
      }

      public void ProcessReleases(FAopComponentBuilder builder) {
         XAopComponent xcomponent = builder.Component;
         if (xcomponent.HasRelease) {
            foreach (XAopMethod xmethod in xcomponent.Releases) {
               ProcessMethod(builder, xmethod);
            }
         }
      }

   }

}
