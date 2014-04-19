using System;
using System.Collections.Generic;
using System.Text;
using System.DirectoryServices;
using MO.Common.Lang;

namespace MO.Core.Window.Services {

   public class FAdObject {

      private ILogger _logger = RLogger.Find(typeof(FAdObject));

      internal FAdConnection _connection;

      internal DirectoryEntry _entry;

      private FAdObjects _children;

      public FAdObject() {
      }

      public FAdObject(FAdConnection connection) {
         _connection = connection;
      }

      /// <summary>
      /// 懏惈傪愝掕丄庢摼偡傞
      /// </summary>
      /// <param name="name">懏惈柤</param>
      /// <returns>懏惈抣</returns>
      public object this[string name] {
         get { return _entry.Properties[name].Value; }
         set {
            try {
               _entry.Properties[name].Value = value;
            } catch (Exception exception) {
               throw new FFatalException(exception, "Set property {0}={1}", name, value);
            }
         }
      }

      public FAdConnection Connection {
         get { return _connection; }
         set { _connection = value; }
      }

      public DirectoryEntry Entry {
         get { return _entry; }
         set { _entry = value; }
      }

      public FAdDirectory CreateChild(string name, string clazz) {
         FAdDirectory directory = new FAdDirectory(_connection);
         directory.Entry = _entry.Children.Add(name, clazz);
         return directory;
      }

      public void Invoke(string method, params object[] args) {
         _entry.Invoke(method, args);
      }

      /// <summary>
      /// 廋惓撪梕傪揮憲偡傞
      /// </summary>
      public void CommitChanges() {
         _entry.CommitChanges();
      }

      public FAdObjects Children {
         get { return GetChildren(false); }
      }

      public FAdObjects GetChildren(bool refresh) {
         if (!refresh && _children != null) {
            return _children;
         }
         _children = new FAdObjects();
         foreach (DirectoryEntry entry in _entry.Children) {
            FAdObject adObject = RAdManager.CreateObject(entry.SchemaClassName);
            adObject._connection = _connection;
            adObject._entry = entry;
            _children[entry.NativeGuid] = adObject;
         }
         return _children;
      }

      public FAdObject Clone() {
         FAdObject directory = new FAdObject();
         directory._connection = _connection;
         directory.Entry = _entry;
         return directory;
      }

   }

}
