using MO.Common.Lang;
using MO.Content2d.Frame.Common;
using System.ComponentModel;
using System.Drawing;
using System.Windows.Forms;

namespace MO.Design2d.Frame.Property
{
   //============================================================
   // <T>图片资源编辑器</T>
   //============================================================
   public partial class QUiPictureEditor : UserControl
   {
      // 属性改变事件
      public event HUiPropertyChanged PropertyChanged;

      // 资源对象
      protected FRcPicture _resource;

      //============================================================
      // <T>构造图片资源编辑器</T>
      //============================================================
      public QUiPictureEditor() {
         InitializeComponent();
         cboAlignCd.Items.AddRange(REnum.ToStringLines<ERcPictureAlign>());
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
      public void LoadResource(FRcPicture resource) {
         _resource = resource;
         txtCode.Text = _resource.Code.ToString();
         cboAlignCd.Text = REnum.ToString<ERcPictureAlign>(_resource.AlignCd);
         txtLocation.Text = _resource.Location.ToString();
         txtPadding.Text = _resource.Padding.ToString();
      }

      //============================================================
      // <T>保存资源。</T>
      //============================================================
      public void SaveResource() {
         _resource.Code = RInt.Parse(txtCode.Text);
         _resource.AlignCd = REnum.ToValue<ERcPictureAlign>(cboAlignCd.Text);
         _resource.Location.Parse(txtLocation.Text);
         _resource.Padding.Parse(txtPadding.Text);
      }

      //============================================================
      // <T>代码变更事件。</T>
      //============================================================
      private void txtCode_TextChanged(object sender, System.EventArgs e) {
         _resource.Code = RInt.Parse(txtCode.Text);
         if (PropertyChanged != null) {
            PropertyChanged(this, _resource, "picture.code");
         }
      }
   }
}
