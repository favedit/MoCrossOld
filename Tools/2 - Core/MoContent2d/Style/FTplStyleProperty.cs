using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;

namespace MO.Content2d.Style
{
   //============================================================
   // <T>样式属性。</T>
   //============================================================
   public class FTplStyleProperty : FObject
   {
      // 名称
      protected string _name;

      // 内容
      protected string _value;

      //============================================================
      // <T>构造样式属性。</T>
      //============================================================
      public FTplStyleProperty() {
      }

      //============================================================
      // <T>获得或设置名称。</T>
      //============================================================
      public string Name {
         get { return _name; }
         set { _name = value; }
      }

      //============================================================
      // <T>获得或设置属性。</T>
      //============================================================
      public string Value {
         get { return _value; }
         set { _value = value; }
      }

      //============================================================
      // <T>加载设置节点。</T>
      //
      // @param xconfig 配置节点
      //============================================================
      public virtual void LoadConfig(FXmlNode xconfig) {
         _name = xconfig.Get("name");
         _value = xconfig.Get("value");
      }

      //============================================================
      // <T>存储设置节点。</T>
      //
      // @param xconfig 配置节点
      //============================================================
      public virtual void SaveConfig(FXmlNode xconfig) {
         xconfig.Set("name", _name);
         xconfig.Set("value", _value);
      }

      //============================================================
      // <T>序列化内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public virtual void Serialize(IOutput output) {
         output.WriteString(_name);
         output.WriteString(_value);
      }
   }
}
