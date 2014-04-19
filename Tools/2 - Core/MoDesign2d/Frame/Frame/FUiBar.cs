using MO.Content2d.Frame.Frame;
using MO.Design2d.Frame.Common;

namespace MO.Design2d.Frame.Frame
{
   //============================================================
   // <T>控件声明。</T>
   //============================================================
   public class FUiBar : FUiFrame
   {
      //============================================================
      // <T>构造控件。</T>
      //============================================================
      public FUiBar(FUiFrameConsole console = null) : base(console){
      }

      //============================================================
      // <T>获得资源。</T>
      //============================================================
      public FRcBar BarResource {
         get { return _resource as FRcBar; }
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

      //============================================================
      // <T>释放资源。</T>
      //============================================================
      public override void OnDispose() {
         base.OnDispose();
      }
   }
}
