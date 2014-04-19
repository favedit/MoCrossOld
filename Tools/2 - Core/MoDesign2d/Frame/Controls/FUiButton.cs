using MO.Common.Geom;
using MO.Common.Lang;
using MO.Content2d.Frame.Common;
using MO.Content2d.Frame.Controls;
using MO.Design2d.Frame.Common;
using MO.Direct2d.Common;
using MO.Direct2d.Draw;

namespace MO.Design2d.Frame.Controls
{
   //============================================================
   // <T>控件声明。</T>
   //============================================================
   public class FUiButton : FUiControl
   {
      // 字体
      protected FUiFont _font = new FUiFont();

      // 背景资源
      protected FUiPicture _groundResource = new FUiPicture();

      //============================================================
      // <T>构造控件。</T>
      //============================================================
      public FUiButton(FUiFrameConsole console = null)
         : base(console) {
      }

      //============================================================
      // <T>获得资源。</T>
      //============================================================
      public FRcButton ButtonResource {
         get { return _resource as FRcButton; }
      }

      //============================================================
      // <T>获得或设置字体。</T>
      //============================================================
      public FUiFont Font {
         get { return _font; }
         set { _font = value; }
      }

      //============================================================
      // <T>获得或设置背景资源。</T>
      //============================================================
      public FUiPicture GroundResource {
         get { return _groundResource; }
         set { _groundResource = value; }
      }

      //============================================================
      // <T>配置处理。</T>
      //
      // @param args 参数
      //============================================================
      public override void OnSetup(SUiSetupArgs args){
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
         // 绘制文本
         FRcButton resource = ButtonResource;
         string text = resource.FormatDisplay();
         if (!RString.IsEmpty(text)) {
            SIntSize2 size = resource.Size;
            FDxTextFormat labelFormat = _context.DefaultTextFormat;
            labelFormat.AlignmentCd = EDxTextAlignment.Center;
            labelFormat.ParagraphAlignmentCd = EDxParagraphAlignment.Center;
            _context.Context.DrawText(text, _context.DefaultTextFormat, resource.Font.Color, new SIntRectangle(0, 0, size.Width, size.Height));
         }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置节点
      //============================================================
      public override void LoadResource(FRcObject resource) {
         base.LoadResource(resource);
         _groundResource.LoadResource(ButtonResource.GroundResource);
      }

      //============================================================
      // <T>释放资源。</T>
      //============================================================
      public override void OnDispose() {
         base.OnDispose();
         _groundResource.Dispose();
      }
   }
}
