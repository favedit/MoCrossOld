using MO.Content3d.Resource.Model.Mesh;
using System.Windows.Forms;

namespace MO.Design3d.Model.Controls
{
   //============================================================
   // <T>几何体属性控件。</T>
   //============================================================
   public partial class QDsModelGeometryProperty : UserControl
   {
      // 几何体
      protected FDrGeometry _geometry;

      //============================================================
      // <T>构造几何体属性控件。</T>
      //============================================================
      public QDsModelGeometryProperty() {
         InitializeComponent();
      }

      //============================================================
      // <T>获得几何体。</T>
      //============================================================
      public FDrGeometry Geometry {
         get { return _geometry; }
      }

      //============================================================
      // <T>加载几何体信息。</T>
      //
      // @prama geometry 几何体
      //============================================================
      public void LoadGeometry(FDrGeometry geometry) {
         _geometry = geometry;
         // 设置属性
         txtGeometryName.Text = _geometry.Name;
         txtMaterialName.Text = _geometry.MaterialName;
         // 设置选项
         qdrOptionInstanced.DataValue = _geometry.OptionInstanced;
         qdrOptionDynamic.DataValue = _geometry.OptionDynamic;
         qdrOptionBoneScale.DataValue = _geometry.OptionBoneScale;
         qdrOptionShadow.DataValue = _geometry.OptionShadow;
         qdrOptionSelfShadow.DataValue = _geometry.OptionSelfShadow;
         qdrOptionNormal.DataValue = _geometry.OptionNormalFull;
         qdrOptionDouble.DataValue = _geometry.OptionDouble;
         qdrOptionSymmetry.DataValue = _geometry.OptionSymmetry;
         qdrOptionTransmittance.DataValue = _geometry.OptionTransmittance;
         qdrOptionSelect.DataValue = _geometry.OptionSelect;
         qdrOptionGround.DataValue = _geometry.OptionGround;
      }

      //============================================================
      // <T>存储几何体信息。</T>
      //
      // @prama geometry 几何体
      //============================================================
      public void SaveGeometry() {
         // 保存选项
         _geometry.OptionInstanced = qdrOptionInstanced.DataValue;
         _geometry.OptionDynamic = qdrOptionDynamic.DataValue;
         _geometry.OptionBoneScale = qdrOptionBoneScale.DataValue;
         _geometry.OptionShadow = qdrOptionShadow.DataValue;
         _geometry.OptionSelfShadow = qdrOptionSelfShadow.DataValue;
         _geometry.OptionNormalFull = qdrOptionNormal.DataValue;
         _geometry.OptionDouble = qdrOptionDouble.DataValue;
         _geometry.OptionSymmetry = qdrOptionSymmetry.DataValue;
         _geometry.OptionTransmittance = qdrOptionGround.DataValue;
         _geometry.OptionSelect = qdrOptionSelect.DataValue;
         _geometry.OptionGround = qdrOptionGround.DataValue;
      }
   }
}
