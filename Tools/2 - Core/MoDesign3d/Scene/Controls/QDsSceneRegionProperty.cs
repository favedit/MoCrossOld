using MO.Common.Lang;
using MO.Content3d.Resource.Scene;
using System.Windows.Forms;

namespace MO.Design3d.Scene.Controls
{
   //============================================================
   // <T>场景区域属性控件。</T>
   //============================================================
   public partial class QDsSceneRegionProperty : UserControl
   {
      // 区域
      protected FDrSceneRegion _region;

      //============================================================
      // <T>构造场景区域属性控件。</T>
      //============================================================
      public QDsSceneRegionProperty() {
         InitializeComponent();
      }

      //============================================================
      // <T>获得区域信息。</T>
      //============================================================
      public FDrSceneRegion SceneRegion {
         get { return _region; }
      }

      //============================================================
      // <T>加载场景区域信息。</T>
      //
      // @param region 区域信息
      //============================================================
      public void LoadRegion(FDrSceneRegion region) {
         _region = region;
         // 读取属性
         qdcColor.LoadColor(_region.Color);
         qdcFogColor.LoadColor(_region.FogColor);
         txtFogNear.Text = _region.FogNear.ToString();
         txtFogFar.Text = _region.FogFar.ToString();
         txtFogRate.Text = _region.FogRate.ToString();
         txtFogAttenuation.Text = _region.FogAttenuation.ToString();
      }

      //============================================================
      // <T>存储场景区域信息。</T>
      //============================================================
      public void SaveRegion() {
         // 存储属性
         _region.Color.Assign(qdcColor.ColorValue);
         _region.FogColor.Assign(qdcFogColor.ColorValue);
         _region.FogNear = RFloat.Parse(txtFogNear.Text);
         _region.FogFar = RFloat.Parse(txtFogFar.Text);
         _region.FogRate = RFloat.Parse(txtFogRate.Text);
         _region.FogAttenuation = RFloat.Parse(txtFogAttenuation.Text);
      }
   }
}
