using MO.Content2d.Common;
using MO.Content3d.Resource.Material;
using MO.Content3d.Resource.Template;
using System;
using System.Diagnostics;
using System.Windows.Forms;

namespace MO.Design3d.Template.Controls
{
   //============================================================
   // <T>资源模板属性控件。</T>
   //============================================================
   public partial class QDsTemplateProperty : UserControl
   {
      // 资源模板
      private FDrTemplate _template;

      //============================================================
      // <T>构造资源模板属性控件。</T>
      //============================================================
      public QDsTemplateProperty() {
         InitializeComponent();
      }

      //============================================================
      // <T>获得资源模板。</T>
      //============================================================
      public FDrTemplate ResourceTemplate {
         get { return _template; }
      }

      //============================================================
      // <T>加载资源模板。</T>
      //
      // @param template 资源模板
      //============================================================
      public void LoadTemplate(FDrTemplate template) {
         template.Open();
         _template = template;
         // 设置属性
         qdrOptionLoad.DataValue = template.OptionLoaded;
         qdrOptionSelect.DataValue = template.OptionSelect;
         qdrOptionGround.DataValue = template.OptionGround;
         qdrOptionMergeVertex.DataValue = template.OptionMergeVertex;
         qdrOptionMergeMaterial.DataValue = template.OptionMergeMaterial;
         qdrOptionLightMap.DataValue = template.OptionLightMap;
         // 清空节点
         tvwCatalog.Nodes.Clear();
         //............................................................
         // 取得对象集合
         TreeNode materialsNode = new TreeNode("材质列表");
         materialsNode.ImageKey = "folder";
         materialsNode.SelectedImageKey = "folder";
         materialsNode.Tag = template;
         foreach(FDrMaterialGroup material in _template.Materials) {
            TreeNode materialNode = new TreeNode(material.Name);
            materialNode.ImageKey = "material";
            materialNode.SelectedImageKey = "material";
            materialNode.Tag = material;
            materialsNode.Nodes.Add(materialNode);
         }
         tvwCatalog.Nodes.Add(materialsNode);
         //............................................................
         // 取得对象集合
         TreeNode renderablesNode = new TreeNode("渲染列表");
         renderablesNode.ImageKey = "folder";
         renderablesNode.SelectedImageKey = "folder";
         renderablesNode.Tag = template;
         foreach(FDrTemplateRenderable renderable in _template.Renderables) {
            TreeNode renderableNode = new TreeNode(renderable.DisplayLabel);
            renderableNode.ImageKey = "renderable";
            renderableNode.SelectedImageKey = "renderable";
            renderableNode.Tag = renderable;
            renderablesNode.Nodes.Add(renderableNode);
         }
         tvwCatalog.Nodes.Add(renderablesNode);
         //............................................................
         TreeNode animationNode = new TreeNode("动画列表");
         animationNode.ImageKey = "folder";
         animationNode.SelectedImageKey = "folder";
         animationNode.Tag = template.Animation;
         foreach(FDrTemplateAnimationMovie movie in template.Animation.Movies) {
            string label = movie.Name + " (" + movie.FrameBegin + " - " + movie.FrameEnd + ") x " + movie.FrameRate;
            TreeNode movieNode = new TreeNode(label);
            movieNode.ImageKey = "movie";
            movieNode.SelectedImageKey = "movie";
            movieNode.Tag = movie;
            animationNode.Nodes.Add(movieNode);
         }
         // 指定图片
         tvwCatalog.Nodes.Add(animationNode);
         //............................................................
         // 全部展开
         tvwCatalog.ExpandAll();
         // 获得选中节点的资源
         tvwCatalog.SelectedNode = materialsNode;
      }

      //============================================================
      // <T>存储资源模板。</T>
      //
      // @param template 资源模板
      //============================================================
      public void SaveTemplate(FDrTemplate template) {
         _template.OptionLoaded = qdrOptionLoad.DataValue;
         _template.OptionSelect = qdrOptionSelect.DataValue;
         _template.OptionGround = qdrOptionGround.DataValue;
         _template.OptionMergeVertex = qdrOptionMergeVertex.DataValue;
         _template.OptionMergeMaterial = qdrOptionMergeMaterial.DataValue;
         _template.OptionLightMap = qdrOptionLightMap.DataValue;
      }

      //============================================================
      // <T>保存按键按下处理。</T>
      //============================================================
      private void tsbSave_Click(object sender, EventArgs e) {
         SaveTemplate(_template);
         _template.Store();
         MessageBox.Show("已经保存成功。", "信息", MessageBoxButtons.OK, MessageBoxIcon.Information, MessageBoxDefaultButton.Button1);
      }

      //============================================================
      // <T>打开文件夹。</T>
      //============================================================
      private void tsbOpen_Click(object sender, EventArgs e) {
         System.Diagnostics.Process.Start(_template.Directory);
      }

      //============================================================
      // <T>查看。</T>
      //============================================================
      private void tsbView_Click(object sender, EventArgs e) {
         string url = "http://localhost/3d/RTemplate3D.html?ts=" + _template.Name;
         Process.Start(new ProcessStartInfo(url));
      }

      //============================================================
      //<T>模板导入<T>
      //============================================================
      private void tsbImport_Click(object sender, EventArgs e) {
         _template.Import();
         _template.Export(ERsExportMode.CompressDeflate);
         MessageBox.Show("已经导入成功。", "信息", MessageBoxButtons.OK, MessageBoxIcon.Information, MessageBoxDefaultButton.Button1);
      }

      //============================================================
      // <T>导出。</T>
      //============================================================
      private void tsbExport_Click(object sender, EventArgs e) {
         _template.Export(ERsExportMode.CompressDeflate);
         MessageBox.Show("已经导出成功。", "信息", MessageBoxButtons.OK, MessageBoxIcon.Information, MessageBoxDefaultButton.Button1);
      }

      //============================================================
      // <T>添加数值<T>
      //
      // @prama temp 模板对象
      //============================================================
      private void SetTemplateValue(FDrTemplate temp) {
         qgvGeometries.Rows.Clear();
         foreach(FDrTemplateRenderable render in temp.Renderables) {
            DataGridViewRow row = new DataGridViewRow();
            // 增加模块名称
            DataGridViewTextBoxCell modelCell = new DataGridViewTextBoxCell();
            modelCell.Value = render.ModelName;
            row.Cells.Add(modelCell);
            // 增加网格名称
            DataGridViewTextBoxCell geometryCell = new DataGridViewTextBoxCell();
            geometryCell.Value = render.GeometryName;
            row.Cells.Add(geometryCell);
            // 增加材质名称
            DataGridViewTextBoxCell materialCell = new DataGridViewTextBoxCell();
            materialCell.Value = render.MaterialName;
            row.Cells.Add(materialCell);
            qgvGeometries.Rows.Add(row);
         }
      }

      //============================================================
      // <T>选中显示项目。</T>
      //
      // @param item 显示项目
      //============================================================
      public void SelectItem(object item) {
         qdrMaterialProperty.Visible = false;
         qdrTemplateRenderableProperty.Visible = false;
         qdrTemplateMovieProperty.Visible = false;
         // 显示材质
         if(item is FDrMaterialGroup) {
            FDrMaterialGroup material = item as FDrMaterialGroup;
            qdrMaterialProperty.Dock = DockStyle.Fill;
            qdrMaterialProperty.LoadMaterialGroup(material);
            qdrMaterialProperty.Visible = true;
         }
         // 显示渲染
         if(item is FDrTemplateRenderable) {
            FDrTemplateRenderable renderable = item as FDrTemplateRenderable;
            qdrTemplateRenderableProperty.LoadRenderable(renderable);
            qdrTemplateRenderableProperty.Dock = DockStyle.Fill;
            qdrTemplateRenderableProperty.Visible = true;
         }
         // 显示动画
         if(item is FDrTemplateAnimationMovie) {
            FDrTemplateAnimationMovie movie = item as FDrTemplateAnimationMovie;
            qdrTemplateMovieProperty.LoadMovie(movie);
            qdrTemplateMovieProperty.Dock = DockStyle.Fill;
            qdrTemplateMovieProperty.Visible = true;
         }
      }

      //============================================================
      // <T>选中节点后触发事件<T>
      //============================================================
      private void tvwDetails_AfterSelect(object sender, TreeViewEventArgs e) {
         SelectItem(e.Node.Tag);
      }

      //============================================================
      // <T>选中节点后触发事件<T>
      //============================================================
      private void tspExportLight_Click(object sender, EventArgs e) {
         _template.ExportLight();
      }

      //============================================================
      // <T>选中节点后触发事件<T>
      //============================================================
      private void tspExportLightResource_Click(object sender, EventArgs e) {
         _template.ExportLightResource();
      }
   }
}
