using MO.Common.Geom;
using MO.Common.Lang;
using MO.Content2d.Frame.Controls;
using MO.Design2d.Frame.Common;
using MO.Direct2d.Common;
using MO.Direct2d.Draw;

namespace MO.Design2d.Frame.Controls
{
   //============================================================
   // <T>控件声明。</T>
   //============================================================
   public class FUiLabel : FUiDataControl
   {
      // 类型名称
      public static string TYPE_NAME = "Label";

      //============================================================
      // <T>构造控件。</T>
      //============================================================
      public FUiLabel(FUiFrameConsole console = null)
         : base(console) {
      }

      //============================================================
      // <T>获得资源。</T>
      //============================================================
      public FRcLabel LabelResource {
         get { return _resource as FRcLabel; }
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
         // 绘制文本
         string label = ControlResource.Label;
         SIntSize2 size = ControlResource.Size;
         if (!RString.IsEmpty(label)) {
            FDxTextFormat labelFormat = _context.DefaultTextFormat;
            labelFormat.AlignmentCd = EDxTextAlignment.Leading;
            labelFormat.ParagraphAlignmentCd = EDxParagraphAlignment.Center;
            _context.Context.DrawText(label, _context.DefaultTextFormat, LabelResource.Font.Color, new SIntRectangle(0, 0, size.Width, size.Height));
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

      //============================================================
      // <T>释放资源。</T>
      //============================================================
      public override void OnDispose() {
         base.OnDispose();
      }
   }
}
