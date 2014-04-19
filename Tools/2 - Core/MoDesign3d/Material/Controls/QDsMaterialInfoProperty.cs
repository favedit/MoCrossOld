using MO.Common.Lang;
using MO.Content3d.Resource.Material;
using System.Windows.Forms;

namespace MO.Design3d.Material.Controls
{
   //============================================================
   // <T>材质信息属性控件。</T>
   //============================================================
   public partial class QDsMaterialInfoProperty : UserControl
   {
      // 材质对象
      protected FDrMaterial _material;

      //============================================================
      // <T>构造材质信息属性控件。</T>
      //============================================================
      public QDsMaterialInfoProperty() {
         InitializeComponent();
      }

      //============================================================
      // <T>获得或设置材质信息。</T>
      //============================================================
      public FDrMaterial Material {
         get { return _material; }
         set { _material = value; }
      }

      //============================================================
      // <T>加载材质信息。</T>
      //
      // @param material 材质信息
      //============================================================
      public void LoadMaterial(FDrMaterial material) {
         _material = material;
         if(material != null) {
            // 设置属性
            txtName.Text = material.Group.Code +"@" + material.ThemeCode;
            // 设置配置
            txtAlphaBase.Text = material.AlphaBase.ToString();
            txtAlphaRate.Text = material.AlphaRate.ToString();
            // 设置颜色
            qdcAmbient.LoadColor(material.AmbientColor);
            txtAmbientShadow.Text = material.AmbientShadow.ToString();
            qdcDiffuse.LoadColor(material.DiffuseColor);
            txtDiffuseShadow.Text = material.DiffuseShadow.ToString();
            qdcDiffuseView.LoadColor(material.DiffuseViewColor);
            txtDiffuseViewShadow.Text = material.DiffuseViewShadow.ToString();
            qdcSpecular.LoadColor(material.SpecularColor);
            txtSpecularShadow.Text = material.SpecularShadow.ToString();
            qdcSpecularView.LoadColor(material.SpecularViewColor);
            txtSpecularViewShadow.Text = material.SpecularViewShadow.ToString();
            qdcEmissive.LoadColor(material.EmissiveColor);
         }
      }

      //============================================================
      // <T>保存材质信息。</T>
      //
      // @param material 材质信息
      //============================================================
      public void SaveMaterial(FDrMaterial material = null) {
         // 修改默认材质
         if(material == null){
            material = _material;
         }
         if (material == null) {
            return;
         }
         // 存储配置
         material.AlphaBase = RFloat.Parse(txtAlphaBase.Text);
         material.AlphaRate = RFloat.Parse(txtAlphaRate.Text);
         // 存储颜色
         material.AmbientColor.Assign(qdcAmbient.ColorValue);
         material.AmbientShadow = RFloat.Parse(txtAmbientShadow.Text);
         material.DiffuseColor.Assign(qdcDiffuse.ColorValue);
         material.DiffuseShadow = RFloat.Parse(txtDiffuseShadow.Text);
         material.DiffuseViewColor.Assign(qdcDiffuseView.ColorValue);
         material.DiffuseViewShadow = RFloat.Parse(txtDiffuseViewShadow.Text);
         material.SpecularColor.Assign(qdcSpecular.ColorValue);
         material.SpecularShadow = RFloat.Parse(txtSpecularShadow.Text);
         material.SpecularViewColor.Assign(qdcSpecularView.ColorValue);
         material.SpecularViewShadow = RFloat.Parse(txtSpecularViewShadow.Text);
         material.EmissiveColor.Assign(qdcEmissive.ColorValue);
      }
   }
}
