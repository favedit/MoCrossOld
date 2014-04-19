using MO.Common.Content;
using MO.Design2d.Frame;

namespace MO.Design2d.Frame.Common
{
   //============================================================
   // <T>控件声明。</T>
   //============================================================
   public class FUiScrollItem : FUiContainer
   {
      //============================================================
      // <T>构造控件。</T>
      //============================================================
      public FUiScrollItem(FUiFrameConsole console = null)
         : base(console) {
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
         // 绘制背景
         //DrawResource(_groundResource);
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
