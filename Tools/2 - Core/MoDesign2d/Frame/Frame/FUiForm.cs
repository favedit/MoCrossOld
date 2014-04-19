using MO.Common.Content;
using MO.Design2d.Frame;
using MO.Design2d.Frame.Common;
using System.ComponentModel;

namespace MO.Design2d.Frame.Frame
{
   //============================================================
   // <T>控件声明。</T>
   //============================================================
   public class FUiForm : FUiFrame
   {
      // 类型名称
      public static string TYPE_NAME = "Form";

      //============================================================
      // <T>构造控件。</T>
      //============================================================
      public FUiForm(FUiFrameConsole console = null) : base(console){
      }

      //============================================================
      // <T>配置处理。</T>
      //
      // @param args 参数
      //============================================================
      public override void OnSetup(SUiSetupArgs args){
         base.OnSetup(args);
      }

      //============================================================
      // <T>开始绘制处理。</T>
      //
      // @param args 参数
      //============================================================
      public override void OnDrawBegin(SUiDrawArgs args){
         base.OnDrawBegin(args);
      }

      //============================================================
      // <T>结束绘制处理。</T>
      //
      // @param args 参数
      //============================================================
      public override void OnDrawAfter(SUiDrawArgs args){
         base.OnDrawAfter(args);
      }
   }
}
