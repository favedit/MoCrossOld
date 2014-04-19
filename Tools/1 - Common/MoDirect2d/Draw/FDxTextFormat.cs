using MO.Common.Lang;
using MO.Direct2d.Common;
using SlimDX.Direct2D;
using SlimDX.DirectWrite;

namespace MO.Direct2d.Draw
{
   //============================================================
   // <T>色刷。</T>
   //
   // @history MAOCY 120223
   //============================================================
   public class FDxTextFormat : FDxObject2d
   {
      // 本地对象
      protected TextFormat _native;

      // 字体名称
      protected string _fontFamilyName;

      // 字体粗细
      protected string _fontWeight = "Normal";

      // 字体样式
      protected string _fontStyle = "Normal";

      // 字体拉伸
      protected string _fontStretch = "Normal";

      // 字体大小
      protected float _fontSize;

      // 本地名称
      protected string _localName;

      //============================================================
      // <T>构造色刷。</T>
      //============================================================
      public FDxTextFormat() {
      }
   
      //============================================================
      // <T>获得本地对象。</T>
      //============================================================
      public TextFormat Native {
         get { return _native; }
      }

      //============================================================
      // <T>获得或设定字体名称。</T>
      //============================================================
      public string FontFamilyName {
         get { return _fontFamilyName; }
         set { _fontFamilyName = value; }
      }

      //============================================================
      // <T>获得或设定字体粗细。</T>
      //============================================================
      public string FontWeight {
         get { return _fontWeight; }
         set { _fontWeight = value; }
      }

      //============================================================
      // <T>获得或设定字体样式。</T>
      //============================================================
      public string FontStyle {
         get { return _fontStyle; }
         set { _fontStyle = value; }
      }

      //============================================================
      // <T>获得或设定字体拉伸。</T>
      //============================================================
      public string FontStretch {
         get { return _fontStretch; }
         set { _fontStretch = value; }
      }

      //============================================================
      // <T>获得或设定字体大小。</T>
      //============================================================
      public float FontSize {
         get { return _fontSize; }
         set { _fontSize = value; }
      }

      //============================================================
      // <T>获得或设定本地名称。</T>
      //============================================================
      public string LocalName {
         get { return _localName; }
         set { _localName = value; }
      }

      //============================================================
      // <T>获得或设定文本对齐方式。</T>
      //============================================================
      public EDxTextAlignment AlignmentCd {
         get { return (EDxTextAlignment)_native.TextAlignment; }
         set { _native.TextAlignment = (TextAlignment)value; }
      }

      //============================================================
      // <T>获得或设定段落对齐方式。</T>
      //============================================================
      public EDxParagraphAlignment ParagraphAlignmentCd {
         get { return (EDxParagraphAlignment)_native.ParagraphAlignment; }
         set { _native.ParagraphAlignment = (ParagraphAlignment)value; }
      }

      //============================================================
      // <T>设置处理。</T>
      //============================================================
      public override void Setup() {
         FontWeight fontWeight = (FontWeight)REnum.ToValue(typeof(FontWeight), _fontWeight);
         FontStyle fontStyle = (FontStyle)REnum.ToValue(typeof(FontStyle), _fontStyle);
         FontStretch fontStretch = (FontStretch)REnum.ToValue(typeof(FontStretch), _fontStretch);
         _native = _device.WriteFactory.CreateTextFormat(_fontFamilyName, fontWeight, fontStyle, fontStretch, _fontSize, _localName);
      }

      //============================================================
      // <T>释放资源。</T>
      //============================================================
      public override void Dispose() {
         if(_native != null) {
            _native.Dispose();
            _native = null;
         }
         base.Dispose();
      }
   }
}
