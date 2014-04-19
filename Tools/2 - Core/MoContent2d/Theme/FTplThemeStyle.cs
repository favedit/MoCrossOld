using MO.Common.Collection;
using MO.Common.Content;
using MO.Common.Lang;

namespace MO.Content2d.Theme
{
   //============================================================
   // <T>主题样式。</T>
   //============================================================
   public class FTplThemeStyle : FObject
   {
      // 名称
      protected string _name;

      // 属性集合
      public FDictionary<FTplThemeStyleProperty> _properties = new FDictionary<FTplThemeStyleProperty>();

      //============================================================
      // <T>构造主题样式。</T>
      //============================================================
      public FTplThemeStyle() { 
      }

      //============================================================
      // <T>获得或设置名称。</T>
      //============================================================
      public string Name {
         get { return _name; }
         set { _name = value; }
      }

      //============================================================
      // <T>获得属性集合。</T>
      //============================================================
      public FDictionary<FTplThemeStyleProperty> Styles {
         get { return _properties; }
      }

      //============================================================
      // <T>根据名称获得属性。</T>
      //
      // @param name 名称
      // @return 属性
      //============================================================
      public FTplThemeStyleProperty FindProperty(string name) {
         return _properties.Find(name);
      }

      //============================================================
      // <T>加载设置节点。</T>
      //
      // @param xconfig 配置节点
      //============================================================
      public void LoadConfig(FXmlNode xconfig) {
         _name = xconfig.Get("name");
         // 加载属性集合
         foreach (FXmlNode xnode in xconfig.Nodes) {
            if (xnode.IsName("Property")) {
               FTplThemeStyleProperty property = new FTplThemeStyleProperty();
               property.LoadConfig(xnode);
               _properties.Set(property.Name, property);
            }
         }
      }

      //============================================================
      // <T>存储设置节点。</T>
      //
      // @param xconfig 配置节点
      //============================================================
      public void SaveConfig(FXmlNode xconfig) {
         xconfig.Set("name", _name);
         // 保存属性集合
         foreach (INamePair<FTplThemeStyleProperty> pair in _properties) {
            pair.Value.SaveConfig(xconfig.CreateNode("Style"));
         }
      }
   }
}
