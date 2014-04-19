using MO.Common.Lang;
using MO.Content2d.Frame.Common;
using MO.Core.Forms.Common;
using System.ComponentModel;
using System.Drawing;
using System.Windows.Forms;

namespace MO.Design2d.Frame.Property
{
   //============================================================
   // <T>字体资源编辑器。</T>
   //============================================================
   public partial class QUiFontEditor : UserControl
   {
      // 属性改变事件
      public event HUiPropertyChanged PropertyChanged;

      // 资源对象
      protected FRcFont _font;

      //============================================================
      // <T>构造字体资源编辑器。</T>
      //============================================================
      public QUiFontEditor() {
         InitializeComponent();
      }

      //============================================================
      // <T>内部设置大小尺寸。</T>
      //
      // @param width 宽度
      // @param height 高度
      //============================================================
      protected void InnerSetSize(int width, int height) {
         // 设置尺寸
         Width = width;
         Height = height = 20;
      }

      //============================================================
      // <T>获取或设定尺寸大小。</T>
      //============================================================
      [Browsable(true)]
      public new Size Size {
         get { return base.Size; }
         set { InnerSetSize(value.Width, value.Height); }
      }

      //============================================================
      // <T>加载资源。</T>
      //
      // @param resource 资源
      //============================================================
      public void LoadResource(FRcFont font) {
         _font = font;
         cboFontName.Text = _font.FontName;
         txtColor.Text = RColor.FormatHtml(_font.Color);
         txtSize.Text = _font.Size.ToString();
         chkBold.Checked = _font.Bold;
         chkItalic.Checked = _font.Italic;
         chkUnderline.Checked = _font.Underline;
         chkStrikeout.Checked = _font.Strikeout;
      }

      //============================================================
      // <T>保存资源。</T>
      //============================================================
      public void SaveResource() {
         _font.FontName = cboFontName.Text;
         _font.Color = RColor.ParseHex(txtColor.Text);
         _font.Size = RInt.Parse(txtSize.Text);
         _font.Bold = chkBold.Checked;
         _font.Italic = chkItalic.Checked;
         _font.Underline = chkUnderline.Checked;
         _font.Strikeout = chkStrikeout.Checked;
      }

      //============================================================
      // <T>字体大小变更事件。</T>
      //============================================================
      private void txtSize_TextChanged(object sender, System.EventArgs e) {
         _font.Size = RInt.Parse(txtSize.Text);
         if (PropertyChanged != null) {
            PropertyChanged(this, _font, "font.size");
         }
      }
   }
}
