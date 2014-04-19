using System;
using System.Windows.Forms;
using MO.Common.Geom;

namespace MO.Core.Forms.Controls
{
   //============================================================
   // <T>浮点颜色控件。</T>
   //============================================================
   public partial class QFloatColor4 : UserControl
   {
      // 浮点颜色
      protected SFloatColor4 _color = new SFloatColor4();

      //============================================================
      // <T>构造浮点颜色控件。</T>
      //============================================================
      public QFloatColor4() {
         InitializeComponent();
      }

      //============================================================
      // <T>获得浮点颜色。</T>
      //============================================================
      public SFloatColor4 ColorValue {
         get { return _color; }
      }

      //============================================================
      // <T>加载浮点颜色。</T>
      //============================================================
      public void LoadColor(SFloatColor4 color) {
         _color.Assign(color);
         // 获得透明度
         int alpha = (int)(color.A * 100);
         if(alpha < 0) {
            alpha = 0;
         } else if(alpha > 100) {
            alpha = 100;
         }
         // 设置内容
         qcpColorRgb.SelectColorText = color.ToHexString3();
         tkbColorAlpha.Value = alpha;
         txtColorAlpha.Text = alpha.ToString();
      }

      //============================================================
      // <T>移动滑块时显示数值。</T>
      //============================================================
      private void tkbColorAlpha_Scroll(object sender, EventArgs e) {
         txtColorAlpha.Text = tkbColorAlpha.Value.ToString();
      }
   }
}
