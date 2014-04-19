using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;

namespace MO.Content2d.Style
{
   //============================================================
   // <T>模版效果器。</T>
   //============================================================
   public class FTplFilter : FObject
   {
      // 类型名称
      protected string _typeName;

      //============================================================
      // <T>构造效果器。</T>
      //============================================================
      public FTplFilter() {
      }

      //============================================================
      // <T>获得或设置类型名称。</T>
      //============================================================
      public string TypeName {
         get { return _typeName; }
         set { _typeName = value; }
      }

      //============================================================
      // <T>加载设置节点。</T>
      //
      // @param xconfig 配置节点
      //============================================================
      public virtual void LoadConfig(FXmlNode xconfig) {
         _typeName = xconfig.Get("type_name");
      }

      //============================================================
      // <T>存储设置节点。</T>
      //
      // @param xconfig 配置节点
      //============================================================
      public virtual void SaveConfig(FXmlNode xconfig) {
         xconfig.Set("type_name", _typeName);
      }

      //============================================================
      // <T>序列化内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public virtual void Serialize(IOutput output) {
         output.WriteString(_typeName);
      }
   }
}
