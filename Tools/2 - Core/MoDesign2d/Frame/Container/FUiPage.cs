using MO.Content2d.Frame.Common;
using MO.Content2d.Frame.Container;
using MO.Design2d.Frame.Common;

namespace MO.Design2d.Frame.Container
{
   //============================================================
   // <T>控件声明。</T>
   //============================================================
   public class FUiPage : FUiContainer
   {
      // 背景资源
      protected FUiPicture _groundResource = new FUiPicture();

      //============================================================
      // <T>构造控件。</T>
      //============================================================
      public FUiPage(FUiFrameConsole console = null)
         : base(console) {
      }

      //============================================================
      // <T>获得资源。</T>
      //============================================================
      public FRcPage PageResource {
         get { return _resource as FRcPage; }
      }

      //============================================================
      // <T>测试是否可以放入父控件内。</T>
      //
      // @param parent 父组件
      // @return 是否可以放入
      //============================================================
      public override bool TestParent(FUiComponent parent) {
         if (parent is FUiPageControl) {
            return true;
         }
         return false;
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置节点
      //============================================================
      public override void LoadResource(FRcObject resource) {
         base.LoadResource(resource);
         _groundResource.LoadResource(PageResource.GroundResource);
      }

      //============================================================
      // <T>配置处理。</T>
      //
      // @param args 参数
      //============================================================
      public override void OnSetup(SUiSetupArgs args) {
         base.OnSetup(args);
         SetupResource(_groundResource);
      }

      //============================================================
      // <T>开始绘制处理。</T>
      //
      // @param args 参数
      //============================================================
      public override void OnDrawBegin(SUiDrawArgs args) {
         base.OnDrawBegin(args);
         // 绘制处理
         if (TestVisible()) {
            // 绘制背景资源
            DrawResource(_groundResource);
         }
      }

      //============================================================
      // <T>结束绘制处理。</T>
      //
      // @param args 参数
      //============================================================
      public override void OnDrawAfter(SUiDrawArgs args) {
         base.OnDrawAfter(args);
      }
   }
}
