using MO.Common.Content;
using MO.Common.Lang;

namespace MO.Content2d.Theme
{
   //============================================================
   // <T>主题样式属性。</T>
   //============================================================
   public class FTplThemeStyleProperty : FObject
   {
      // 名称
      protected string _name;

      // 类型名称
      protected string _typeName;

      // 配置信息
      protected FXmlNode _config;

      //============================================================
      // <T>构造主题样式属性。</T>
      //============================================================
      public FTplThemeStyleProperty() {
      }

      //============================================================
      // <T>获得或设置名称。</T>
      //============================================================
      public string Name {
         get { return _name; }
         set { _name = value; }
      }

      //============================================================
      // <T>获得或设置类型名称。</T>
      //============================================================
      public string TypeName {
         get { return _typeName; }
         set { _typeName = value; }
      }

      //============================================================
      // <T>根据名称获得布尔设置。</T>
      //
      // @param name 名称
      // @param defaultValue 缺省内容
      // @return 布尔
      //============================================================
      public bool GetBoolean(string name = "value", bool defaultValue = false) {
         return _config.GetBoolean(name, defaultValue);
      }

      //============================================================
      // <T>根据名称获得整数设置。</T>
      //
      // @param name 名称
      // @param defaultValue 缺省内容
      // @return 整数
      //============================================================
      public int GetInteger(string name = "value", int defaultValue = 0) {
         return _config.GetInteger(name, defaultValue);
      }

      //============================================================
      // <T>根据名称获得整数设置。</T>
      //
      // @param name 名称
      // @param defaultValue 缺省内容
      // @return 整数
      //============================================================
      public int GetHex(string name = "value", int defaultValue = 0) {
         return _config.GetHex(name, defaultValue);
      }

      //============================================================
      // <T>根据名称获得字符串设置。</T>
      //
      // @param name 名称
      // @param defaultValue 缺省内容
      // @return 字符串
      //============================================================
      public string GetString(string name = "value", string defaultValue = null) {
         return _config.Get(name, defaultValue);
      }

      //============================================================
      // <T>加载设置节点。</T>
      //
      // @param xconfig 配置节点
      //============================================================
      public void LoadConfig(FXmlNode xconfig) {
         _config = xconfig;
         _name = xconfig.Get("name");
         _typeName = xconfig.Get("type", "string");
      }

      //============================================================
      // <T>存储设置节点。</T>
      //
      // @param xconfig 配置节点
      //============================================================
      public void SaveConfig(FXmlNode xconfig) {
         xconfig.Attributes.Assign(_config.Attributes);
         xconfig.Set("name", _name);
         xconfig.Set("type", _typeName);
      }

      //============================================================
      // <T>存储设置节点。</T>
      //
      // @param xconfig 配置节点
      //============================================================
      public override string ToString() {
         return (_config != null) ? _config.Xml : "";
      }
   }
}
