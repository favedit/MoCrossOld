using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Content;
using MO.Common.Lang;
using MO.Common.Lang.Reflection;

namespace MO.Core.Aop.Config {

   public class XAopConfig : XAopNode {

      public const string TAG = "Config";

      public const string PTY_NAME = "name";

      public const string PTY_TYPE = "type";

      public const string PTY_COLLECTION = "collection";

      private string _name;

      private string _typeName;

      private FType _linkType;

      private string _collectionName;

      private FType _linkCollection;

      public override string Id {
         get { return _name; }
         set { _name = value; }
      }
      public string Name {
         get { return _name; }
         set { _name = value; }
      }

      public string TypeName {
         get { return _typeName; }
         set { _typeName = value; }
      }

      public FType LinkType {
         get { return _linkType; }
         set { _linkType = value; }
      }

      public string CollectionName {
         get { return _collectionName; }
         set { _collectionName = value; }
      }

      public FType LinkCollection {
         get { return _linkCollection; }
         set { _linkCollection = value; }
      }

      public override void LoadConfig(FXmlNode config) {
         // Name
         _name = config[PTY_NAME];
         RString.CheckEmpty(_name, "name");
         // Type
         _typeName = config[PTY_TYPE];
         RString.CheckEmpty(_typeName, "typeName");
         _linkType = _console.FindType(_typeName);
         // Collection
         if (config.Contains(PTY_COLLECTION)) {
            _collectionName = config[PTY_COLLECTION];
            RString.CheckEmpty(_collectionName, "collectionName");
            _linkCollection = _console.FindType(_collectionName);
         } else {
            _linkCollection = RClass.Find(typeof(XAopNodeCollection<IAopNode>));
            _collectionName = _linkCollection.Type.FullName;
         }
      }

      public T CreateInstance<T>() {
         return (T)RClass.CreateInstance(_linkType.Type);
      }

      public T CreateCollection<T>() {
         return (T)RClass.CreateInstance(_linkCollection.Type);
      }

   }

}
