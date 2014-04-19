using MO.Common.Lang;
using MO.Content2d.Frame.Common;
using MO.Design2d.Frame.Common;
using System.Windows.Forms;

namespace MO.Design2d.Frame.Property
{
   //============================================================
   // <T>边框编辑窗体。</T>
   //============================================================
   public partial class QUiBorderPropertyForm : Form
   {

      // 实例化对象
      protected static QUiBorderPropertyForm _instance;

      // 边框对象
      protected FRcBorder _border = new FRcBorder();

      //============================================================
      // <T>实例化对象。</T>
      //============================================================
      public static QUiBorderPropertyForm Instance {
         get {
            if (null == _instance) {
               _instance = new QUiBorderPropertyForm();
            }
            return _instance;
         }
      }

      //============================================================
      // <T>获得或设置边框对象。</T>
      //============================================================
      public FRcBorder Border {
         get { return _border; }
         set { _border = value; }
      }

      //============================================================
      // <T>构造窗体。</T>
      //============================================================
      public QUiBorderPropertyForm() {
         InitializeComponent();
         // 初始化下拉框
         cbxLStyle.Items.Clear();
         cbxTStyle.Items.Clear();
         cbxRStyle.Items.Clear();
         cbxBStyle.Items.Clear();
         cbxLStyle.Items.AddRange(REnum.ToStringLines<EUiLineStyle>());
         cbxTStyle.Items.AddRange(REnum.ToStringLines<EUiLineStyle>());
         cbxRStyle.Items.AddRange(REnum.ToStringLines<EUiLineStyle>());
         cbxBStyle.Items.AddRange(REnum.ToStringLines<EUiLineStyle>());
      }

      //============================================================
      // <T>加载边框信息。</T>
      //============================================================
      public void LoadBorder() {
         // 左边线
         qcpLColor.SelectColorValue = _border.Left.Color;
         txtLWidth.Text = _border.Left.Width.ToString();
         cbxLStyle.Text = REnum.ToString<EUiLineStyle>(_border.Left.Style);
         // 上边线
         qcpTColor.SelectColorValue = _border.Top.Color;
         txtTWidth.Text = _border.Top.Width.ToString();
         cbxTStyle.Text = REnum.ToString<EUiLineStyle>(_border.Top.Style);
         // 右边线
         qcpRColor.SelectColorValue = _border.Right.Color;
         txtRWidth.Text = _border.Right.Width.ToString();
         cbxRStyle.Text = REnum.ToString<EUiLineStyle>(_border.Right.Style);
         // 下边线
         qcpBColor.SelectColorValue = _border.Bottom.Color;
         txtBWidth.Text = _border.Bottom.Width.ToString();
         cbxBStyle.Text = REnum.ToString<EUiLineStyle>(_border.Bottom.Style);
      }

      //============================================================
      // <T>确定按钮事件处理。</T>
      //============================================================
      private void btnAccept_Click(object sender, System.EventArgs e) {
         // 左
         _border.Left.Color = qcpLColor.SelectColorValue;
         _border.Left.Width = RInt.Parse(txtLWidth.Text);
         _border.Left.Style = REnum.ToValue<ERcLineStyle>(cbxLStyle.Text);
         // 上
         _border.Top.Color = qcpTColor.SelectColorValue;
         _border.Top.Width = RInt.Parse(txtTWidth.Text);
         _border.Top.Style = REnum.ToValue<ERcLineStyle>(cbxTStyle.Text);
         // 右
         _border.Right.Color = qcpRColor.SelectColorValue;
         _border.Right.Width = RInt.Parse(txtRWidth.Text);
         _border.Right.Style = REnum.ToValue<ERcLineStyle>(cbxRStyle.Text);
         // 下
         _border.Bottom.Color = qcpBColor.SelectColorValue;
         _border.Bottom.Width = RInt.Parse(txtBWidth.Text);
         _border.Bottom.Style = REnum.ToValue<ERcLineStyle>(cbxBStyle.Text);
         // 完成后隐藏窗体
         Hide();
      }

      //============================================================
      // <T>取消按钮事件处理。</T>
      //============================================================
      private void btnCancel_Click(object sender, System.EventArgs e) {
         Hide();
      }
   }
}
