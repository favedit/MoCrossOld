using MO.Content3d.Resource.Template;
using System.Windows.Forms;

namespace MO.Design3d.Template.Controls
{
   //============================================================
   // <T>模板渲染属性控件。</T>
   //============================================================
   public partial class QDsTemplateRenderableProperty : UserControl
   {
      //============================================================
      // <T>构造模板渲染属性控件。</T>
      //============================================================
      public QDsTemplateRenderableProperty() {
         InitializeComponent();
      }

      //============================================================
      // <T>加载模板渲染信息。</T>
      //
      // @prama renderable 模板渲染信息
      //============================================================
      public void LoadRenderable(FDrTemplateRenderable renderable) {
         // 设置属性
         txtModelName.Text = renderable.ModelName;
         txtGeometryName.Text = renderable.GeometryName;
         txtMaterialName.Text = renderable.MaterialName;
         // 设置选项
         qdrOptionSelect.DataValue = renderable.OptionSelect;
         qdrOptionVisible.DataValue = renderable.OptionVisible;
         qdrOptionGround.DataValue = renderable.OptionGround;
      }

      //============================================================
      // <T>保存模板渲染信息。</T>
      //
      // @prama renderable 模板渲染信息
      //============================================================
      public void SaveRenderable(FDrTemplateRenderable renderable) {
         // 保存选项
         renderable.OptionSelect = qdrOptionSelect.DataValue;
         renderable.OptionVisible = qdrOptionVisible.DataValue;
         renderable.OptionGround = qdrOptionGround.DataValue;
      }
   }
}
