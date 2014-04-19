using MO.Common.Content;
using MO.Common.IO;

namespace MO.Content2d.Style
{
   //============================================================
   // <T>模版颜色效果器。</T>
   //============================================================
   public class FTplColorMatrixFilter : FTplFilter
   {
      //============================================================
      // <T>构造模版颜色效果器。</T>
      //============================================================
      public FTplColorMatrixFilter() {
      }

      //============================================================
      // <T>加载设置节点。</T>
      //
      // @param xconfig 配置节点
      //============================================================
      public override void LoadConfig(FXmlNode xconfig) {
         base.LoadConfig(xconfig);
      }

      //============================================================
      // <T>存储设置节点。</T>
      //
      // @param xconfig 配置节点
      //============================================================
      public override void SaveConfig(FXmlNode xconfig) {
         base.SaveConfig(xconfig);
      }

      //============================================================
      // <T>序列化内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public override void Serialize(IOutput output) {
         base.Serialize(output);
      }
   }
}
