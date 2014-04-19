using MO.Content2d.Frame.Container;
using MO.Design2d.Frame.Common;

namespace MO.Design2d.Frame.Container
{
   //============================================================
   // <T>控件声明。</T>
   //============================================================
   public class FUiPanel : FUiContainer
   {
      //============================================================
      // <T>构造控件。</T>
      //============================================================
      public FUiPanel(FUiFrameConsole console = null) : base(console){
      }

      //============================================================
      // <T>获得资源。</T>
      //============================================================
      public FRcPanel PageControlResource {
         get { return _resource as FRcPanel; }
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
