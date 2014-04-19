using MO.Content3d.Resource.Template;
using System.Windows.Forms;

namespace MO.Design3d.Template.Controls
{
   //============================================================
   // <T>模板动画属性控件。</T>
   //============================================================
   public partial class QDsTemplateMovieProperty : UserControl
   {
      // 模板动画信息
      protected FDrTemplateAnimationMovie _movie;

      //============================================================
      // <T>构造模板动画属性控件。</T>
      //============================================================
      public QDsTemplateMovieProperty() {
         InitializeComponent();
      }

      //============================================================
      // <T>加载模板动画信息。</T>
      //
      // @prama material 材质名称
      //============================================================
      public void LoadMovie(FDrTemplateAnimationMovie movie) {
         // 打开材质
         _movie = movie;
      }
   }
}
