using MO.Common.Lang;
using MO.Content3d.Resource.Material;
using System.Windows.Forms;

namespace MO.Design3d.Material.Controls
{
   //============================================================
   // <T>材质信息属性控件。</T>
   //============================================================
   public partial class QDsMaterialGroupProperty : UserControl
   {
      // 材质对象
      protected FDrMaterialGroup _materialGroup;

      //============================================================
      // <T>构造材质信息属性控件。</T>
      //============================================================
      public QDsMaterialGroupProperty() {
         InitializeComponent();
      }

      //============================================================
      // <T>获得或设置材质信息。</T>
      //============================================================
      public FDrMaterialGroup MaterialGroup {
         get { return _materialGroup; }
         set { _materialGroup = value; }
      }

      //============================================================
      // <T>加载材质信息。</T>
      //
      // @param material 材质信息
      //============================================================
      public void LoadMaterialGroup(FDrMaterialGroup materialGroup) {
         _materialGroup = materialGroup;
         if (null != materialGroup) {
            // 设置属性
            txtName.Text = materialGroup.Name + "(" + materialGroup.Label + ")";
            if (RString.IsEmpty(materialGroup.EffectName)) {
               cboEffectName.Text = cboEffectName.Items[0].ToString();
            } else {
               cboEffectName.Text = materialGroup.EffectName;
            }
            if (RString.IsEmpty(materialGroup.TransformName)) {
               cboTransformName.Text = cboTransformName.Items[0].ToString();
            } else {
               cboTransformName.Text = materialGroup.TransformName;
            }
            // 设置配置
            qdrOptionLight.DataValue = materialGroup.OptionLight;
            qdrOptionMerge.DataValue = materialGroup.OptionMerge;
            qdrOptionSort.DataValue = materialGroup.OptionSort;
            nudSortLevel.Value = materialGroup.SortLevel;
            qdrOptionAlpha.DataValue = materialGroup.OptionAlpha;
            qdrOptionDepth.DataValue = materialGroup.OptionDepth;
            cboOptionCompare.Text = materialGroup.OptionCompare;
            qdrOptionDouble.DataValue = materialGroup.OptionDouble;
            qdrOptionShadow.DataValue = materialGroup.OptionShadow;
            qdrOptionShadowSelf.DataValue = materialGroup.OptionShadowSelf;
            qdrOptionDynamic.DataValue = materialGroup.OptionDynamic;
            qdrOptionTransmittance.DataValue = materialGroup.OptionTransmittance;
            qdrOptionOpacity.DataValue = materialGroup.OptionOpacity;
         }
      }

      //============================================================
      // <T>保存材质信息。</T>
      //
      // @param material 材质信息
      //============================================================
      public void SaveMaterialGroup(FDrMaterialGroup materialGroup = null) {
         // 修改默认材质
         if (null == materialGroup) {
            materialGroup = _materialGroup;
         }
         if (null == materialGroup) {
            return;
         }
         // 存储属性
         materialGroup.EffectName = cboEffectName.Text;
         materialGroup.TransformName = cboTransformName.Text;
         // 存储配置
         materialGroup.OptionLight = qdrOptionLight.DataValue;
         materialGroup.OptionMerge = qdrOptionMerge.DataValue;
         materialGroup.OptionSort = qdrOptionSort.DataValue;
         materialGroup.SortLevel = (int)nudSortLevel.Value;
         materialGroup.OptionAlpha = qdrOptionAlpha.DataValue;
         materialGroup.OptionDepth = qdrOptionDepth.DataValue;
         materialGroup.OptionCompare = cboOptionCompare.Text;
         materialGroup.OptionDouble = qdrOptionDouble.DataValue;
         materialGroup.OptionShadow = qdrOptionShadow.DataValue;
         materialGroup.OptionShadowSelf = qdrOptionShadowSelf.DataValue;
         materialGroup.OptionDynamic = qdrOptionDynamic.DataValue;
         materialGroup.OptionTransmittance = qdrOptionTransmittance.DataValue;
         materialGroup.OptionOpacity = qdrOptionOpacity.DataValue;
         // 刷新材质
         materialGroup.RefreshMaterials();
      }
   }
}
