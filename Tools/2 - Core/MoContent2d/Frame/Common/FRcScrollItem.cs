using MO.Common.Content;
using MO.Design2d.Face.Console;

namespace MO.Content2d.Frame.Common
{
   //============================================================
   // <T>控件声明。</T>
   //============================================================
   public class FRcScrollItem : FRcContainer
   {
      //============================================================
      // <T>构造控件。</T>
      //============================================================
      public FRcScrollItem(FRcFrameConsole console = null)
         : base(console) {
      }

      //============================================================
      // <T>加载设置信息。</T>
      //
      // @param xconfig 设置信息
      //============================================================
      public override void OnLoadConfig(FXmlNode xconfig){
         base.OnLoadConfig(xconfig);
      }

      //============================================================
      // <T>存储设置信息。</T>
      //
      // @param xconfig 设置信息
      //============================================================
      public override void OnSaveConfig(FXmlNode xconfig){
         base.OnSaveConfig(xconfig);
      }
   }
}
