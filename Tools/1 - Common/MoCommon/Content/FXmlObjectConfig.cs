using MO.Common.Lang;
using MO.Common.Lang.Reflection;

namespace MO.Common.Content
{
   //============================================================
   // <T>文档对象设置。</T>
   //============================================================
   public class FXmlObjectConfig
   {
      // 标签名称
      public const string TAG = "Config";

      // 属性名称
      public const string PTY_NAME = "name";

      // 属性类型
      public const string PTY_TYPE = "type";

      // 名称
      protected string _name;

      // 类型名称
      protected string _typeName;

      // 类型
      protected FType _type;

      //============================================================
      // <T>获得或设置名称。</T>
      //
      // @param value 名称
      // @return 名称
      //============================================================
      public string Name {
         get { return _name; }
         set { _name = value; }
      }

      //============================================================
      // <T>获得或设置类名称。</T>
      //
      // @param value 名称
      // @return 名称
      //============================================================
      public string TypeName {
         get { return _typeName; }
         set { _typeName = value; }
      }

      //============================================================
      // <T>获得或设置类对象。</T>
      //
      // @param value 类对象
      // @return 类对象
      //============================================================
      public FType Type {
         get { return _type; }
         set { _type = value; }
      }

      //============================================================
      // <T>获得类名。</T>
      //
      // @param value 类名
      // @return 类名
      //============================================================
      public string ClassName {
         get {
            string[] items = _typeName.Split('#');
            if(2 == items.Length) {
               return items[1];
            }
            return _typeName;
         }
      }

      //============================================================
      // <T>加载设置信息。</T>
      //
      // @param config 设置信息
      //============================================================
      public void LoadConfig(FXmlNode config) {
         // 获得名称
         _name = config[PTY_NAME];
         RString.CheckEmpty(_name, "name");
         // 获得类型
         _typeName = config[PTY_TYPE];
         RString.CheckEmpty(_typeName, "type");
         // 获得类对象
         _type = RClass.Find(_typeName);
      }

      //============================================================
      // <T>创建对象实例。</T>
      //
      // @param T 类型
      // @return 对象实例
      //============================================================
      public T CreateInstance<T>()
            where T : IXmlObject {
         return RClass.CreateInstance<T>(_type.Type);
      }
   }
}
