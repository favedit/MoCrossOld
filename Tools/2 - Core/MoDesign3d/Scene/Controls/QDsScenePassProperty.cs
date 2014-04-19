using MO.Common.Lang;
using MO.Content3d.Resource.Scene;
using System.Windows.Forms;

namespace MO.Design3d.Scene.Controls
{
   //============================================================
   // <T>场景渲染过程控件。</T>
   //============================================================
   public partial class QDsScenePassProperty : UserControl
   {
      // 光源信息
      protected FDrScenePass _pass;

      //============================================================
      // <T>构造场景渲染过程控件。</T>
      //============================================================
      public QDsScenePassProperty() {
         InitializeComponent();
      }

      //============================================================
      // <T>加载渲染过程信息。</T>
      //
      // @param pass 渲染过程
      //============================================================
      public void LoadPass(FDrScenePass pass) {
         _pass = pass;
         // 加载属性
         txtName.Text = pass.Name;
         // 加载设置
         txtRenderTargetWidth.Text = pass.RenderTargetSize.Width.ToString();
         txtRenderTargetHeight.Text = pass.RenderTargetSize.Height.ToString();
      }

      //============================================================
      // <T>存储渲染过程信息。</T>
      //============================================================
      public void SavePass() {
         // 存储属性
         _pass.Name = txtName.Text;
         // 存储设置
         _pass.RenderTargetSize.Width = RInt.Parse(txtRenderTargetWidth.Text);
         _pass.RenderTargetSize.Height = RInt.Parse(txtRenderTargetHeight.Text);
      }
   }
}
