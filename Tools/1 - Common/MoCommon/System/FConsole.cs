using MO.Common.Content;
using MO.Common.Lang;

namespace MO.Common.System
{
   //============================================================
   // <T>控制台基础类。</T>
   //============================================================
   public class FConsole : FObject
   {
      // 名称
      protected string _name;

      // 标签
      protected string _label;

      // 初始化标志
      protected bool _initialize;

      //============================================================
      // <T>获得或设置名称。</T>
      //============================================================
      public string Name {
         get { return _name; }
         set { _name = value; }
      }

      //============================================================
      // <T>获得或设置标签。</T>
      //============================================================
      public string Label {
         get { return _label; }
         set { _label = value; }
      }

      //============================================================
      // <T>获得是否初始化。</T>
      //============================================================
      public bool IsInitialized {
         get { return _initialize; }
      }

      //============================================================
      // <T>加载设置信息</T>
      //
      // @param xconfig 设置信息
      //============================================================
      public virtual void LoadConfig(FXmlNode xconfig) {
         _name = xconfig.Get("name", null);
         _label = xconfig.Get("label", null);
      }

      //============================================================
      // <T>存储设置信息</T>
      //
      // @param xconfig 设置信息
      //============================================================
      public virtual void SaveConfig(FXmlNode xconfig) {
         xconfig.Set("name", _name);
         xconfig.Set("label", _label);
      }

      //============================================================
      // <T>获得信息字符串。</T>
      //============================================================
      public override string ToString() {
         return "Console(" + _name + ")";
      }
   }
}
