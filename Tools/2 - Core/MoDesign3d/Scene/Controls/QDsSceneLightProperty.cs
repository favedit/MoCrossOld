using MO.Common.Lang;
using MO.Content3d.Resource.Scene;
using System.Windows.Forms;

namespace MO.Design3d.Scene.Controls
{
   //============================================================
   // <T>场景光源属性控件。</T>
   //============================================================
   public partial class QDsSceneLightProperty : UserControl
   {
      // 光源信息
      protected FDrSceneLight _light;

      //============================================================
      // <T>构造场景光源属性控件。</T>
      //============================================================
      public QDsSceneLightProperty() {
         InitializeComponent();
      }

      //============================================================
      // <T>加载光源信息。</T>
      //
      // @param light 光源信息
      //============================================================
      public void LoadLight(FDrSceneLight light) {
         _light = light;
         // 加载属性
         txtName.Text = light.TypeName;
         // 加载设置
         qdrOptionTrack.DataValue = light.OptionTrack;
         // 加载材质
         txtMinColor.Text = light.Material.ColorMin.ToString();
         txtMaxColor.Text = light.Material.ColorMax.ToString();
         qdcAmbient.LoadColor(light.Material.AmbientColor);
         txtAmbientShadow.Text = light.Material.AmbientShadow.ToString();
         qdcDiffuse.LoadColor(light.Material.DiffuseColor);
         txtDeffuseShadow.Text = light.Material.DiffuseShadow.ToString();
         qdcDiffuseView.LoadColor(light.Material.DiffuseViewColor);
         txtDeffuseViewShadow.Text = light.Material.DiffuseViewShadow.ToString();
         qdcSpecular.LoadColor(light.Material.SpecularColor);
         txtSpecularBase.Text = light.Material.SpecularBase.ToString();
         txtSpecularRate.Text = light.Material.SpecularRate.ToString();
         txtSpecularAverage.Text = light.Material.SpecularAverage.ToString();
         txtSpecularShadow.Text = light.Material.SpecularShadow.ToString();
         qdcSpecularView.LoadColor(light.Material.SpecularViewColor);
         txtSpecularViewBase.Text = light.Material.SpecularViewBase.ToString();
         txtSpecularViewRate.Text = light.Material.SpecularViewRate.ToString();
         txtSpecularViewAverage.Text = light.Material.SpecularViewAverage.ToString();
         txtSpecularViewShadow.Text = light.Material.SpecularViewShadow.ToString();
         qdcOpacityColor.LoadColor(light.Material.OpacityColor);
         qdcReflect.LoadColor(light.Material.ReflectColor);
         qdcRefractFront.LoadColor(light.Material.RefractFrontColor);
         qdcRefractBack.LoadColor(light.Material.RefractBackColor);
         qdcEmissive.LoadColor(light.Material.EmissiveColor);
      }

      //============================================================
      // <T>存储光源信息。</T>
      //
      // @param light 光源信息
      //============================================================
      public void SaveLight() {
         // 存储属性
         _light.TypeName = txtName.Text;
         // 存储设置
         _light.OptionTrack = qdrOptionTrack.DataValue;
         // 存储材质
         _light.Material.ColorMin = RFloat.Parse(txtMinColor.Text.Trim());
         _light.Material.ColorMax = RFloat.Parse(txtMaxColor.Text.Trim());
         _light.Material.AmbientColor.Assign(qdcAmbient.ColorValue);
         _light.Material.AmbientShadow = RFloat.Parse(txtAmbientShadow.Text.Trim());
         _light.Material.DiffuseColor.Assign(qdcDiffuse.ColorValue);
         _light.Material.DiffuseShadow = RFloat.Parse(txtDeffuseShadow.Text.Trim());
         _light.Material.DiffuseViewColor.Assign(qdcDiffuseView.ColorValue);
         _light.Material.DiffuseViewShadow = RFloat.Parse(txtDeffuseViewShadow.Text.Trim());
         _light.Material.SpecularColor.Assign(qdcSpecular.ColorValue);
         _light.Material.SpecularBase = RFloat.Parse(txtSpecularBase.Text.Trim());
         _light.Material.SpecularRate = RFloat.Parse(txtSpecularRate.Text.Trim());
         _light.Material.SpecularShadow = RFloat.Parse(txtSpecularShadow.Text.Trim());
         _light.Material.SpecularAverage = RFloat.Parse(txtSpecularAverage.Text.Trim());
         _light.Material.SpecularViewColor.Assign(qdcSpecular.ColorValue);
         _light.Material.SpecularViewBase = RFloat.Parse(txtSpecularViewBase.Text.Trim());
         _light.Material.SpecularViewRate = RFloat.Parse(txtSpecularViewRate.Text.Trim());
         _light.Material.SpecularViewAverage = RFloat.Parse(txtSpecularViewAverage.Text.Trim());
         _light.Material.SpecularViewShadow = RFloat.Parse(txtSpecularViewShadow.Text.Trim());
         _light.Material.OpacityColor.Assign(qdcOpacityColor.ColorValue);
         _light.Material.ReflectColor.Assign(qdcReflect.ColorValue);
         _light.Material.RefractFrontColor.Assign(qdcRefractFront.ColorValue);
         _light.Material.RefractBackColor.Assign(qdcRefractBack.ColorValue);
         _light.Material.EmissiveColor.Assign(qdcEmissive.ColorValue);
      }

      //============================================================
      // <T>重置光源信息。</T>
      //============================================================
      private void btnReset_Click(object sender, System.EventArgs e) {
         _light.Reset();
         LoadLight(_light);
      }
   }
}
