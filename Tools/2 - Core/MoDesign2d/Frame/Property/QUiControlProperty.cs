using MO.Common.Lang;
using MO.Content2d.Frame.Common;
using System.Windows.Forms;

namespace MO.Design2d.Frame.Property
{
   //============================================================
   // <T>控件属性编辑器</T>
   //============================================================
   public partial class QUiControlProperty : UserControl, IUiPropertyEditor
   {
      // 资源对象
      protected FRcControl _resource;

      // 属性变更事件
      public event HUiPropertyChanged PropertyChanged;

      //============================================================
      // <T>构造控件属性编辑器</T>
      //============================================================
      public QUiControlProperty() {
         InitializeComponent();
      }

      //============================================================
      // <T>配置处理。</T>
      //============================================================
      public void Setup() {
         cbxDockCd.Items.AddRange(REnum.ToStringLines<ERcDock>());
      }

      //============================================================
      // <T>获得分组名称。</T>
      //
      // @return 分组名称
      //============================================================
      public string GroupLabel() {
         return "控件属性";
      }

      //============================================================
      // <T>加载资源。</T>
      //
      // @param resource 资源
      //============================================================
      public void LoadResource(FRcObject resource) {
         // 检查参数
         _resource = resource as FRcControl;
         if (_resource == null) {
            return;
         }
         // 属性设置
         chkOptionEnable.Checked = _resource.OptionEnable;
         chkOptionVisible.Checked = _resource.OptionVisible;
         cbxDockCd.Text = REnum.ToString<ERcDock>(_resource.DockCd);
         txtLocation.Text = _resource.Location.ToString();
         txtSize.Text = _resource.Size.ToString();
         txtMargin.Text = _resource.Margin.ToString();
         txtPadding.Text = _resource.Padding.ToString();
         qcpForeColor.SelectColorValue = _resource.ForeColor;
         qpeForeResource.LoadResource(_resource.ForeResource);
         qcpBackColor.SelectColorValue = _resource.BackColor;
         qpeBackResource.LoadResource(_resource.BackResourceId);
         qUiInnerBorderProperty.LoadResource(_resource.BorderInner);
         qUiOuterBorderProperty.LoadResource(_resource.BorderOuter);
         // 事件设置
         txtClick.Text = _resource.OnClick;
         txtDoubleClick.Text = _resource.OnDoubleClick;
         txtMouseDown.Text = _resource.OnMouseDown;
         txtMouseUp.Text = _resource.OnMouseUp;
         txtMouseMove.Text = _resource.OnMouseMove;
         txtMouseEnter.Text = _resource.OnMouseEnter;
         txtMouseLeave.Text = _resource.OnMouseLeave;
      }

      //============================================================
      // <T>保存资源。</T>
      //============================================================
      public void SaveResource() {
         // 检查参数
         if (_resource == null) {
            return;
         }
         // 保存属性
         _resource.OptionEnable = chkOptionEnable.Checked;
         _resource.OptionVisible = chkOptionVisible.Checked;
         _resource.DockCd = REnum.ToValue<ERcDock>(cbxDockCd.Text);
         _resource.Margin.Parse(txtMargin.Text);
         _resource.Padding.Parse(txtPadding.Text);
         _resource.ForeColor = qcpForeColor.SelectColorValue;
         qpeForeResource.SaveResource();
         _resource.BackColor = qcpBackColor.SelectColorValue;
         qpeBackResource.SaveResource();
         qUiInnerBorderProperty.SaveResource();
         qUiOuterBorderProperty.SaveResource();
         // 保存事件
         _resource.OnClick = txtClick.Text;
         _resource.OnDoubleClick = txtDoubleClick.Text;
         _resource.OnMouseDown = txtMouseDown.Text;
         _resource.OnMouseUp = txtMouseUp.Text;
         _resource.OnMouseMove = txtMouseMove.Text;
         _resource.OnMouseEnter = txtMouseEnter.Text;
         _resource.OnMouseLeave = txtMouseLeave.Text;
      }

      //============================================================
      // <T>坐标内容变更。</T>
      //
      // @param sender 发送者
      // @param e 事件
      //============================================================
      private void txtLocation_TextChanged(object sender, System.EventArgs e) {
         if (_resource.Location.Parse(txtLocation.Text)) {
            if (PropertyChanged != null) {
               PropertyChanged(this, _resource, null);
            }
         }
      }

      //============================================================
      // <T>尺寸内容变更。</T>
      //
      // @param sender 发送者
      // @param e 事件
      //============================================================
      private void txtSize_TextChanged(object sender, System.EventArgs e) {
         if (_resource.Size.Parse(txtSize.Text)) {
            if (PropertyChanged != null) {
               PropertyChanged(this, _resource, null);
            }
         }
      }
   }
}
