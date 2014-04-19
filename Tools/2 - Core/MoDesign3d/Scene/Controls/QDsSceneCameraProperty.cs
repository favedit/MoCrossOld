using MO.Content3d.Resource.Scene;
using System.Windows.Forms;

namespace MO.Design3d.Scene.Controls
{
   public partial class QDsSceneCameraProperty : UserControl
   {

      public QDsSceneCameraProperty() {
         InitializeComponent();
      }

      public void LoadCamera(FDrSceneCamera camera) {
         txtDirectionX.Text = camera.Direction.X.ToString().Trim();
         txtDirectionY.Text = camera.Direction.Y.ToString().Trim();
         txtDirectionZ.Text = camera.Direction.Z.ToString().Trim();
         txtPositionX.Text = camera.Position.X.ToString().Trim();
         txtPositionY.Text = camera.Position.Y.ToString().Trim();
         txtPositionZ.Text = camera.Position.Z.ToString().Trim();
         txtViewPort.Text = camera.Viewport.Angle.ToString().Trim();
         txtTypeName.Text = camera.TypeName;       
         txtFocalFar.Text = camera.FocalFar.ToString();
         txtFocalNear.Text = camera.FocalNear.ToString();
         txtCenterFront.Text = camera.CenterFront.ToString();
         txtCenterBack.Text = camera.CenterBack.ToString();
         
      }

      public void SaveCamera(FDrSceneCamera camera) {
         camera.Direction.X = float.Parse(txtDirectionX.Text);
         camera.Direction.Y = float.Parse(txtDirectionY.Text);
         camera.Direction.Z = float.Parse(txtDirectionZ.Text);
         camera.Position.X = float.Parse(txtPositionX.Text);
         camera.Position.Y = float.Parse(txtPositionY.Text);
         camera.Position.Z = float.Parse(txtPositionZ.Text);
         camera.Viewport.Angle = float.Parse(txtViewPort.Text);
         camera.TypeName = txtTypeName.Text;
         camera.FocalFar = float.Parse(txtFocalFar.Text);
         camera.FocalNear = float.Parse(txtFocalNear.Text);
         camera.CenterFront = float.Parse(txtCenterFront.Text);
         camera.CenterBack = float.Parse(txtCenterBack.Text);
      }
   }
}
