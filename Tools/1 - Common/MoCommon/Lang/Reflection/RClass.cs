using System;
using System.Collections.Generic;
using System.Reflection;

namespace MO.Common.Lang.Reflection
{
   //============================================================
   // <T>类工具类。</T>
   //============================================================
   public static class RClass
   {
      // 类型集合
      private static Dictionary<string, FType> _types = new Dictionary<string, FType>();

      //============================================================
      // <T>判断指定名称的类是否存在。</T>
      //
      // @param name 名称
      // @return 是否存在
      //============================================================
      public static bool Exists(string name) {
         Type type = FindType(name);
         return (null != type);
      }

      //============================================================
      // <T>检查指定名称的类是否存在。</T>
      //
      // @param name 名称
      // @return 是否存在
      //============================================================
      public static void CheckExists(string name) {
         Type type = FindType(name);
         if (null == type) {
            throw new FFatalException("Can't find type ({0}).", name);
         }
      }

      //============================================================
      // <T>根据指定名称的查找类对象。</T>
      //
      // @param name 名称
      // @return 类对象
      //============================================================
      public static Type FindType(string name) {
         return FindType(name, null);
      }

      //============================================================
      // <T>在指定模块中，根据指定名称的查找类对象。</T>
      //
      // @param name 名称
      // @param assembly 模块名称
      // @return 类对象
      //============================================================
      public static Type FindType(string name, Assembly assembly) {
         Type type = null;
         if (null != name) {
            string[] names = name.Split('@');
            if (2 == names.Length) {
               Assembly loadAssembly = Assembly.Load(names[1]);
               type = loadAssembly.GetType(names[0]);
            } else {
               if (null == assembly) {
                  assembly = Assembly.GetEntryAssembly();
               }
               type = assembly.GetType(name);
            }
         }
         return type;
      }

      //============================================================
      // <T>根据名称查找类对象。</T>
      //
      // @param item 对象
      // @return 类对象
      //============================================================
      public static FType Find(object item) {
         return Find(item, null);
      }

      //============================================================
      // <T>在指定模块中，根据名称查找类对象。</T>
      //
      // @param item 对象
      // @param assembly 模块对象
      // @return 类对象
      //============================================================
      public static FType Find(object item, Assembly assembly) {
         // 获得类名称
         string name = null;
         Type ntype = null;
         if (item is string) {
            name = (string)item;
         } else if (item is Type) {
            ntype = (Type)item;
            name = ntype.FullName;
         } else {
            name = item.ToString();
         }
         // 查找类对象
         FType type = _types[name];
         if (null == type) {
            if (null == ntype) {
               ntype = FindType(name, assembly);
               if (null == ntype) {
                  return null;
               }
            }
            // 创建类对象
            type = new FType(ntype);
            _types[name] = type;
         }
         return type;
      }

      //============================================================
      // <T>获得指定对象的类对象。</T>
      //
      // @param item 对象
      // @return 类对象
      //============================================================
      public static FType Get(object item) {
         return Find(item, null);
      }

      //============================================================
      // <T>在指定模块中，获得指定对象的类对象。</T>
      //
      // @param item 对象
      // @param assembly 模块对象
      // @return 类对象
      //============================================================
      public static FType Get(object item, Assembly assembly) {
         FType type = Find(item, assembly);
         if (null == type) {
            throw new FFatalException("Can't find type ({0}).", item);
         }
         return type;
      }

      //============================================================
      // <T>创建指定类型的对象实例。</T>
      //
      // @param T 类型
      // @return 对象实例
      //============================================================
      public static T CreateInstance<T>() {
         try {
            return (T)Activator.CreateInstance(typeof(T));
         } catch (Exception e) {
            throw new FFatalException(e, "Create instance({0}) failure.", typeof(T));
         }
      }

      //============================================================
      // <T>创建指定类型名称的对象实例。</T>
      //
      // @param T 类型
      // @param type 类型名称
      // @return 对象实例
      //============================================================
      public static T CreateInstance<T>(string type, params object[] args) {
         try {
            return (T)CreateInstance(type, args);
         } catch (Exception e) {
            throw new FFatalException(e, "Create instance({0}) failure.", type);
         }
      }

      //============================================================
      // <T>创建指定类型的对象实例。</T>
      //
      // @param T 类型
      // @param type 类型
      // @return 对象实例
      //============================================================
      public static T CreateInstance<T>(Type type, params object[] args) {
         try {
            return (T)Activator.CreateInstance(type, args);
         } catch (Exception e) {
            throw new FFatalException(e, "Create instance({0}) failure.", type);
         }
      }

      //============================================================
      // <T>创建指定类型的对象实例。</T>
      //
      // @param type 类型
      // @param args 参数集合
      // @return 对象实例
      //============================================================
      public static object CreateInstance(Type type, params object[] args) {
         try {
            return Activator.CreateInstance(type, args);
         } catch (Exception e) {
            throw new FFatalException(e, "Create instance({0}) failure.", type);
         }
      }

      //============================================================
      // <T>创建指定类型名称的对象实例。</T>
      //
      // @param name 类型名称
      // @return 对象实例
      //============================================================
      public static object CreateInstance(string name, params object[] args) {
         // 查找类型
         Type type = FindType(name);
         if (null == type) {
            throw new FFatalException("Can't find type ({0}).", name);
         }
         // 创建实体
         try {
            return Activator.CreateInstance(type, args);
         } catch (Exception e) {
            throw new FFatalException(e, "Create instance({0}) failure.", name);
         }
      }

      //============================================================
      // <T>尝试创建指定类型名称的对象实例。</T>
      //
      // @param name 类型名称
      // @return 对象实例
      //============================================================
      public static object TryCreateInstance(string name, params object[] args) {
         // 查找类型
         Type type = FindType(name);
         // 创建实体
         if (null != type) {
            try {
               return Activator.CreateInstance(type, args);
            } catch {
               return null;
            }
         }
         return null;
      }

      //============================================================
      // <T>获得一个对象的转存信息。</T>
      //
      // @param value 对象
      // @return 转存信息
      //============================================================
      public static FString Dump(object value) {
         FString dump = new FString();
         Dump(dump, value);
         return dump;
      }

      //============================================================
      // <T>获得一个对象的转存信息。</T>
      //
      // @param dump 转存信息
      // @param value 对象
      // @return 转存信息
      //============================================================
      public static FString Dump(FString dump, object value) {
         if (null == value) {
            dump.Append("@null");
         } else {
            Type type = value.GetType();
            dump.Append(type.Name);
            dump.Append('@');
            dump.Append(value.GetHashCode().ToString("X8"));
         }
         return dump;
      }
   }
}
