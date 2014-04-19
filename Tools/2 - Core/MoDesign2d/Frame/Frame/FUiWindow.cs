using MO.Common.Geom;
using MO.Common.Lang;
using MO.Content2d.Frame.Frame;
using MO.Design2d.Frame.Common;
using MO.Direct2d.Common;
using MO.Direct2d.Draw;

namespace MO.Design2d.Frame.Frame
{
   //============================================================
   // <T>控件声明。</T>
   //============================================================
   public class FUiWindow : FUiFrame
   {
      //============================================================
      // <T>构造控件。</T>
      //============================================================
      public FUiWindow(FUiFrameConsole console = null)
         : base(console) {
      }

      //============================================================
      // <T>获得资源。</T>
      //============================================================
      public FRcWindow WindowResource {
         get { return _resource as FRcWindow; }
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
      public override void OnDrawBegin(SUiDrawArgs args) {
         base.OnDrawBegin(args);
         // 绘制处理
         if (TestVisible()) {
            // 绘制背景资源
            DrawResource(_groundResource);
         }
         _context.Context.FillRectangle(0x7F888800, 2, 2, _designSize.Width - 4, 24);
         // 绘制文本
         FRcWindow resource = WindowResource;
         string text = resource.Label;
         if (!RString.IsEmpty(text)) {
            SIntSize2 size = resource.Size;
            FDxTextFormat labelFormat = _context.DefaultTextFormat;
            labelFormat.AlignmentCd = EDxTextAlignment.Center;
            labelFormat.ParagraphAlignmentCd = EDxParagraphAlignment.Center;
            _context.Context.DrawText(text, _context.DefaultTextFormat, 0, new SIntRectangle(0, 0, size.Width, size.Height));
         }
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
