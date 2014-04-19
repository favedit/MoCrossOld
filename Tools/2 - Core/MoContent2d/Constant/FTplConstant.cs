using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;

namespace MO.Content2d.Constant
{
   //============================================================
   // <T>模版常量。</T>
   //============================================================
   public class FTplConstant : FObject
   {
      // 编号
      protected int _id;

      // 代码
      protected string _code;

      // 备注
      protected string _note;

      //============================================================
      // <T>构造模版常量。</T>
      //============================================================
      public FTplConstant() {
      }

      //============================================================
      // <T>获得或设置编号。</T>
      //============================================================
      public int Id {
         get { return _id; }
         set { _id = value; }
      }

      //============================================================
      // <T>获得或设置代码。</T>
      //============================================================
      public string Code {
         get { return _code; }
         set { _code = value; }
      }

      //============================================================
      // <T>获得或设置备注。</T>
      //============================================================
      public string Note {
         get { return _note; }
         set { _note = value; }
      }

      //============================================================
      // <T>加载设置节点。</T>
      //
      // @param xconfig 配置节点
      //============================================================
      public void LoadConfig(FXmlNode xconfig) {
         _id = xconfig.GetInteger("id");
         _code = xconfig.Get("code");
         _note = xconfig.Text;
      }

      //============================================================
      // <T>存储设置节点。</T>
      //
      // @param xconfig 配置节点
      //============================================================
      public void SaveConfig(FXmlNode xconfig) {
         xconfig.Set("id", _id);
         xconfig.Set("code", _code);
         xconfig.Text = _note;
      }

      //============================================================
      // <T>序列化内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public virtual void Serialize(IOutput output) {
         output.WriteString(_code);
         output.WriteUTFString(_note);
      }
   }
}
