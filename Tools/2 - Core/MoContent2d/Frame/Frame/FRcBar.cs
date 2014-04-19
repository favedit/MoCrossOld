using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content2d.Frame.Common;
using MO.Design2d.Face.Console;

namespace MO.Content2d.Frame.Frame
{
   //============================================================
   // <T>控件声明。</T>
   //============================================================
   public class FRcBar : FRcFrame
   {
      //============================================================
      // <T>构造控件。</T>
      //============================================================
      public FRcBar(FRcFrameConsole console = null) : base(console){
         _typeName = REnum.ToString<ERcComponent>(ERcComponent.Bar);
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

      //============================================================
      // <T>序列化内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public override void OnSerialize(IOutput output) {
         base.OnSerialize(output);
      }

      //============================================================
      // <T>释放资源。</T>
      //============================================================
      public override void OnDispose() {
         base.OnDispose();
      }
   }
}
