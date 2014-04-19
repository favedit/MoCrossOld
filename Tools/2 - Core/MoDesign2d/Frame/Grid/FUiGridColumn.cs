using MO.Content2d.Frame.Common;
using MO.Content2d.Frame.Grid;
using MO.Design2d.Frame.Common;

namespace MO.Design2d.Frame.Grid
{
   //============================================================
   // <T>控件声明。</T>
   //============================================================
   public class FUiGridColumn : FUiControl
   {
      //============================================================
      // <T>构造控件。</T>
      //============================================================
      public FUiGridColumn(FUiFrameConsole console = null)
         : base(console) {
      }

      //============================================================
      // <T>获得资源。</T>
      //============================================================
      public FRcGridColumn GridColumnResource {
         get { return _resource as FRcGridColumn; }
      }

      //============================================================
      // <T>配置处理。</T>
      //
      // @param args 参数
      //============================================================
      public override void OnSetup(SUiSetupArgs args) {
         base.OnSetup(args);
      }

      //============================================================
      // <T>开始绘制处理。</T>
      //
      // @param args 参数
      //============================================================
      public override void OnDrawBegin(SUiDrawArgs args) {
         base.OnDrawBegin(args);
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置节点
      //============================================================
      public override void LoadResource(FRcObject resource) {
         base.LoadResource(resource);
      }

      //============================================================
      // <T>释放资源。</T>
      //============================================================
      public override void OnDispose() {
         base.OnDispose();
      }
   }
}
