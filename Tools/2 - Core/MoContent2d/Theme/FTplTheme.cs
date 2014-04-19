using MO.Common.Collection;
using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;

namespace MO.Content2d.Theme
{
   //============================================================
   // <T>模板主题。</T>
   //============================================================
   public class FTplTheme : FObject
   {
      // 名称
      protected string _name;

      // 文件名称
      protected string _fileName;

      // 样式集合
      protected FDictionary<FTplThemeStyle> _styles = new FDictionary<FTplThemeStyle>();

      //============================================================
      // <T>构造模板主题。</T>
      //============================================================
      public FTplTheme() { 
      }

      //============================================================
      // <T>获得或设置名称。</T>
      //============================================================
      public string Name {
         get { return _name; }
         set { _name = value; }
      }

      //============================================================
      // <T>获得或设置文件名称。</T>
      //============================================================
      public string FileName {
         get { return _fileName; }
         set { _fileName = value; }
      }

      //============================================================
      // <T>获得样式集合。</T>
      //============================================================
      public FDictionary<FTplThemeStyle> Styles {
         get { return _styles; }
      }

      //============================================================
      // <T>根据名称获得样式。</T>
      //
      // @param name 名称
      // @return 样式
      //============================================================
      public FTplThemeStyle FindStyle(string name) {
         return _styles.Find(name);
      }

      //============================================================
      // <T>加载设置节点。</T>
      //
      // @param xconfig 配置节点
      //============================================================
      public void LoadConfig(FXmlNode xconfig) {
         _name = xconfig.Get("name");
         // 加载样式集合
         foreach (FXmlNode xnode in xconfig.Nodes) {
            if (xnode.IsName("Style")) {
               FTplThemeStyle style = new FTplThemeStyle();
               style.LoadConfig(xnode);
               _styles.Set(style.Name, style);
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
         // 保存样式集合
         foreach (INamePair<FTplThemeStyle> pair in _styles) {
            pair.Value.SaveConfig(xconfig.CreateNode("Style"));
         }
      }

      //============================================================
      // <T>序列化内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public virtual void Serialize(IOutput output) {
         output.WriteUTFString(_name);
      }
   }
}
