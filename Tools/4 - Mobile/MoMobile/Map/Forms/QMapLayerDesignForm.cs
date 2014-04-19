using System.Windows.Forms;
using MO.Common.Lang;
using MoMobile.Map.Common;

namespace MoMobile.Map.Forms
{
   //============================================================
   // <T>层编辑窗体。</T>
   //============================================================
   public partial class QMapLayerDesignForm : Form
   {

      // 实例化对象
      protected static QMapLayerDesignForm _instance;

      // 层对象
      protected FMbMapLayer _layer = new FMbMapLayer();

      //============================================================
      // <T>实例化对象。</T>
      //============================================================
      public static QMapLayerDesignForm Instance {
         get {
            if (null == _instance) {
               _instance = new QMapLayerDesignForm();
            }
            return _instance;
         }
      }

      //============================================================
      // <T>获得或设置层对象。</T>
      //============================================================
      public FMbMapLayer Layer {
         get { return _layer; }
         set { _layer = value; }
      }

      //============================================================
      // <T>构造窗体。</T>
      //============================================================
      public QMapLayerDesignForm() {
         InitializeComponent();
         // 初始化下拉列表
         cbxScrollCd.Items.Clear();
         cbxTypeCd.Items.Clear();
         cbxWrapCd.Items.Clear();
         cbxScrollCd.Items.AddRange(REnum.ToStringLines<EMapLayerScroll>());
         cbxTypeCd.Items.AddRange(REnum.ToStringLines<EMapLayerType>());
         cbxWrapCd.Items.AddRange(REnum.ToStringLines<EMapLayerWrap>());
      }

      //============================================================
      // <T>加载层信息。</T>
      //============================================================
      public void LoadLayer() {
         txtCellCountWidth.Text = _layer.CellCount.Width.ToString();
         txtCellCountHeight.Text = _layer.CellCount.Height.ToString();
         txtCellSizeHeight.Text = _layer.CellSize.Width.ToString();
         txtCellSizeWidth.Text = _layer.CellSize.Height.ToString();
         txtSpeed.Text = _layer.ScrollSpeed.ToString();
         cbxScrollCd.Text = _layer.ScrollCd.ToString();
         cbxTypeCd.Text = _layer.TypeCd.ToString();
         cbxWrapCd.Text = _layer.WrapCd.ToString();
      }

      //============================================================
      // <T>确定按钮事件处理。</T>
      //============================================================
      private void btnAccept_Click(object sender, System.EventArgs e) {
         _layer.ScrollCd = REnum.ToValue<EMapLayerScroll>(cbxScrollCd.Text);
         _layer.TypeCd = REnum.ToValue<EMapLayerType>(cbxTypeCd.Text);
         _layer.ScrollSpeed = RFloat.Parse(txtSpeed.Text);
         _layer.WrapCd = REnum.ToValue<EMapLayerWrap>(cbxWrapCd.Text);
         Close();
      }

      //============================================================
      // <T>关闭窗体。</T>
      //============================================================
      private void btnCancel_Click(object sender, System.EventArgs e) {
         Close();
      }
   }
}
