using System;
using System.Collections.Generic;
using System.Text;

namespace MO.Common.Lang.Reflection {

   public class FEnumItem {

      public const string ITEM_PREFIX = "item.";

      private IResource _resource;

      private Type _type;

      private object _item;

      public FEnumItem(object item) {
         _item = item;
         _type = item.GetType();
         _resource = RResource.Find(_type);
      }

      public FEnumItem(Type type, object item) {
         _item = item;
         _type = type;
         _resource = RResource.Find(_type);
      }

      public IResource Resource {
         get { return _resource; }
      }

      public object Item {
         get { return _item; }
         set { _item = value; }
      }

      public T GetItem<T>() {
         return (T)_item;
      }

      public bool EqualsItem(object item) {
         return item.Equals(_item);
      }

      public override string ToString() {
         return _resource.FindDisplay(ITEM_PREFIX + _item.ToString());
      }
   }

}
