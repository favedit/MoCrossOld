using MO.Common.Collection;
using MO.Content2d.Common;
using MO.Content3d.Common;
using MO.Content3d.Resource.Material;
using MO.Content3d.Resource.Model;
using MO.Content3d.Resource.Model.Animation;
using MO.Content3d.Resource.Model.Mesh;
using MO.Content3d.Resource.Texture;
using System.Windows.Forms;

namespace MO.Design3d.Model.Controls
{
   //============================================================
   // <T>模型设计控件。</T>
   //============================================================
   public partial class QDsModelProperty : UserControl
   {
      // 模块对象
      private FDrModel _model;

      // 纹理对象
      protected FDrTexture _texture;

      //============================================================
      // <T>构造模型设计控件。</T>
      //============================================================
      public QDsModelProperty() {
         InitializeComponent();
      }

      //============================================================
      // <T>获得模型对象。</T>
      //============================================================
      public FDrModel Model {
         get { return _model; }
      }

      //============================================================
      // <T>加载模型信息。</T>
      //
      // @param model 模型对象
      //============================================================
      public void LoadModel(FDrModel model) {
         model.Open();
         _model = model;
         //............................................................
         txtName.Text = _model.Name;
         txtLabel.Text = _model.Label;
         txtBoneCount.Text = _model.Skeleton.Bones.Count.ToString();
         //............................................................
         // 清空节点
         tvwCatalog.Nodes.Clear();
         tvwCatalog.BeginUpdate();
         //............................................................
         TreeNode materialsNode = new TreeNode("材质列表");
         materialsNode.ImageKey = "Floder";
         materialsNode.SelectedImageKey = "Floder";
         materialsNode.Tag = model;
         dgvMaterials.Rows.Clear();
         // 建立材质集合
         foreach(INamePair<FDrModelMaterial> pair in model.Materials.Names) {
            FDrModelMaterial material = pair.Value;
            // 建立树目录节点
            TreeNode materialNode = new TreeNode(material.Name);
            materialNode.ImageKey = "Material";
            materialNode.SelectedImageKey = "Material";
            materialNode.Tag = pair.Value;
            materialsNode.Nodes.Add(materialNode);
            // 建立数据行对象
            DataGridViewRow row = new DataGridViewRow();
            DataGridViewTextBoxCell cellName = new DataGridViewTextBoxCell();
            cellName.Value = material.Name;
            row.Cells.Add(cellName);
            DataGridViewTextBoxCell cellLabel = new DataGridViewTextBoxCell();
            if (null != material.Material) {
               cellLabel.Value = material.Material.Label;
            }
            row.Cells.Add(cellLabel);
            dgvMaterials.Rows.Add(row);
         }
         tvwCatalog.Nodes.Add(materialsNode);
         //............................................................
         FDrMesh mesh = model.Mesh;
         TreeNode geometriesNode = new TreeNode("网格列表");
         geometriesNode.ImageKey = "Floder";
         geometriesNode.SelectedImageKey = "Floder";
         geometriesNode.Tag = model;
         dgvGeometries.Rows.Clear();
         // 建立几何集合
         foreach(INamePair<FDrGeometry> pair in model.Mesh.GeometryDictionary) {
            FDrGeometry geometry = pair.Value;
            // 建立树目录节点
            string displayName = geometry.Name + " (" + geometry.MaterialName + ")";
            TreeNode geometryNode = new TreeNode(displayName);
            geometryNode.ImageKey = "Geometry";
            geometryNode.SelectedImageKey = "Geometry";
            geometryNode.Tag = geometry;
            geometriesNode.Nodes.Add(geometryNode);
            // 建立数据行对象
            DataGridViewRow row = new DataGridViewRow();
            DataGridViewTextBoxCell cellName = new DataGridViewTextBoxCell();
            cellName.Value = geometry.Name;
            row.Cells.Add(cellName);
            // 实体配置
            DataGridViewCheckBoxCell cellOptionInstanced = new DataGridViewCheckBoxCell();
            cellOptionInstanced.Value = EDrFlag.ToBoolean(geometry.OptionInstanced);
            row.Cells.Add(cellOptionInstanced);
            DataGridViewTextBoxCell cellInstanceCount = new DataGridViewTextBoxCell();
            cellInstanceCount.Value = geometry.InstanceCount;
            row.Cells.Add(cellInstanceCount);
            // 动态配置
            DataGridViewCheckBoxCell cellOptionDynamic = new DataGridViewCheckBoxCell();
            cellOptionDynamic.Value = EDrFlag.ToBoolean(geometry.OptionDynamic);
            row.Cells.Add(cellOptionDynamic);
            // 骨骼缩放
            DataGridViewCheckBoxCell cellOptionBoneScale = new DataGridViewCheckBoxCell();
            cellOptionBoneScale.Value = EDrFlag.ToBoolean(geometry.OptionBoneScale);
            row.Cells.Add(cellOptionBoneScale);
            DataGridViewTextBoxCell cellBoneCount = new DataGridViewTextBoxCell();
            cellBoneCount.Value = geometry.AdjustBones.Count;
            row.Cells.Add(cellBoneCount);
            DataGridViewTextBoxCell cellWeightCount = new DataGridViewTextBoxCell();
            cellWeightCount.Value = geometry.WeightMaxCount.ToString();
            row.Cells.Add(cellWeightCount);
            // 影子配置
            DataGridViewCheckBoxCell cellOptionShadow = new DataGridViewCheckBoxCell();
            cellOptionShadow.Value = EDrFlag.ToBoolean(geometry.OptionShadow);
            row.Cells.Add(cellOptionShadow);
            // 自影子配置
            DataGridViewCheckBoxCell cellOptionSelfShadow = new DataGridViewCheckBoxCell();
            cellOptionSelfShadow.Value = EDrFlag.ToBoolean(geometry.OptionSelfShadow);
            row.Cells.Add(cellOptionSelfShadow);
            // 法线配置
            DataGridViewCheckBoxCell cellOptionNormal = new DataGridViewCheckBoxCell();
            cellOptionNormal.Value = EDrFlag.ToBoolean(geometry.OptionNormalFull);
            row.Cells.Add(cellOptionNormal);
            // 双面配置
            DataGridViewCheckBoxCell cellOptionDouble = new DataGridViewCheckBoxCell();
            cellOptionDouble.Value = EDrFlag.ToBoolean(geometry.OptionDouble);
            row.Cells.Add(cellOptionDouble);
            // 选择配置
            DataGridViewCheckBoxCell cellOptionSelect = new DataGridViewCheckBoxCell();
            cellOptionSelect.Value = EDrFlag.ToBoolean(geometry.OptionSelect);
            row.Cells.Add(cellOptionSelect);
            // 材质名称
            DataGridViewTextBoxCell cellMaterialName = new DataGridViewTextBoxCell();
            cellMaterialName.Value = geometry.MaterialName;
            row.Cells.Add(cellMaterialName);
            // 顶点信息
            DataGridViewTextBoxCell cellVertexCount = new DataGridViewTextBoxCell();
            cellVertexCount.Value = geometry.VertexList.Count + "/" + geometry.AdjustVertexDictionary.Count;
            row.Cells.Add(cellVertexCount);
            // 面信息
            DataGridViewTextBoxCell cellFaceCount = new DataGridViewTextBoxCell();
            cellFaceCount.Value = geometry.FaceList.Count;
            row.Cells.Add(cellFaceCount);
            // 法线信息
            DataGridViewTextBoxCell cellNormalCount = new DataGridViewTextBoxCell();
            cellNormalCount.Value = geometry.NormalList.Count;
            row.Cells.Add(cellNormalCount);
            // 副法线信息
            DataGridViewTextBoxCell cellBinormalCount = new DataGridViewTextBoxCell();
            cellBinormalCount.Value = geometry.BinormalList.Count;
            row.Cells.Add(cellBinormalCount);
            // 切线信息
            DataGridViewTextBoxCell cellTangentCount = new DataGridViewTextBoxCell();
            cellTangentCount.Value = geometry.TangentList.Count;
            row.Cells.Add(cellTangentCount);
            row.Tag = geometry;
            dgvGeometries.Rows.Add(row);
         }
         tvwCatalog.Nodes.Add(geometriesNode);
         //............................................................
         TreeNode animationsNode = new TreeNode("动画列表");
         // 建立动画集合
         foreach(FDrMovie movie in model.Animation.Movies) {
            TreeNode animationNode = new TreeNode(movie.Name);
            animationNode.ImageKey = "Movie";
            animationNode.SelectedImageKey = "Movie";
            animationNode.Tag = movie;
            animationsNode.Nodes.Add(animationNode);
         }
         tvwCatalog.Nodes.Add(animationsNode);
         //............................................................
         tvwCatalog.EndUpdate();
         tvwCatalog.ExpandAll();
         // 选择项目
         SelectItem(null);
      }

      //============================================================
      // <T>加载模型信息。</T>
      //
      // @param model 模型对象
      //============================================================
      public void SaveModel() {
         // 隐藏几何体属性
         if(qdrModelGeometryProperty.Visible) {
            qdrModelGeometryProperty.SaveGeometry();
         }
         // 存储几何体信息
         foreach (DataGridViewRow row in dgvGeometries.Rows) {
            FDrGeometry geometry = row.Tag as FDrGeometry;
            if (null != geometry) {
               geometry.OptionInstanced = EDrFlag.FromBoolean((bool)row.Cells["dgcGeometryInstanced"].Value);
               geometry.OptionDynamic = EDrFlag.FromBoolean((bool)row.Cells["dgcGeometryDynamic"].Value);
               geometry.OptionShadow = EDrFlag.FromBoolean((bool)row.Cells["dgcGeometryShadow"].Value);
               geometry.OptionSelfShadow = EDrFlag.FromBoolean((bool)row.Cells["dgcGeometrySelfShadow"].Value);
               geometry.OptionNormalFull = EDrFlag.FromBoolean((bool)row.Cells["dgcGeometryNormal"].Value);
               geometry.OptionDouble = EDrFlag.FromBoolean((bool)row.Cells["dgcGeometryDouble"].Value);
               geometry.OptionSelect = EDrFlag.FromBoolean((bool)row.Cells["dgcGeometrySelect"].Value);
            }
         }
      }
      
      //============================================================
      // <T>打开资源。</T>
      //============================================================
      public void SelectItem(object item) {
         // 存储改变
         SaveModel();
         // 隐藏属性控件
         qdrModelGeometryProperty.Visible = false;
         qdrMaterialProperty.Visible = false;
         pnlModelProperty.Visible = false;
         // 显示选中对象
         if(item is FDrMaterialGroup) {
            FDrMaterialGroup material = item as FDrMaterialGroup;
            qdrMaterialProperty.Dock = DockStyle.Fill;
            qdrMaterialProperty.Visible = true;
            qdrMaterialProperty.LoadMaterialGroup(material);
         } else if(item is FDrGeometry) {
            FDrGeometry geometry = item as FDrGeometry;
            qdrModelGeometryProperty.Dock = DockStyle.Fill;
            qdrModelGeometryProperty.Visible = true;
            qdrModelGeometryProperty.LoadGeometry(geometry);
         } else {
            pnlModelProperty.Visible = true;
            pnlModelProperty.Dock = DockStyle.Fill;
         }
      }

      //============================================================
      //<T>添加数值<T>
      //
      //@prama temp 模板对象
      //============================================================
      private void SetModelValue(TreeNode node, string type) {
         dgvGeometries.Rows.Clear();
         foreach(TreeNode modelNode in node.Nodes) {
            DataGridViewRow row = new DataGridViewRow();
            DataGridViewTextBoxCell modelCell = new DataGridViewTextBoxCell();
            modelCell.Value = modelNode.Text;
            row.Cells.Add(modelCell);
            // 增加类型名称
            DataGridViewTextBoxCell typeCell = new DataGridViewTextBoxCell();
            typeCell.Value = type;
            row.Cells.Add(typeCell);
            dgvGeometries.Rows.Add(row);
         }
      }

      //============================================================
      // <T>选中对象。</T>
      //============================================================
      private void tvwCatalog_AfterSelect(object sender, TreeViewEventArgs e) {
         SelectItem(e.Node.Tag);
         FDrModel model = e.Node.Tag as FDrModel;
         if(e.Node.Text == "材质列表") {
            SetModelValue(e.Node, "Material");
         }
         if(e.Node.Text == "网格列表") {
            SetModelValue(e.Node, "Geometrie");
         }
         if(e.Node.Text == "动画列表") {
            SetModelValue(e.Node, "Animation");
         }
      }

      //============================================================
      //<T>保存处理。</T>
      //============================================================
      private void tspSave_Click(object sender, System.EventArgs e) {
         tspMain.Focus();
         SaveModel();
         _model.Store();
         MessageBox.Show("已经保存成功。", "信息", MessageBoxButtons.OK, MessageBoxIcon.Information, MessageBoxDefaultButton.Button1);
      }

      //============================================================
      // <T>打开处理。</T>
      //============================================================
      private void tsbOpen_Click(object sender, System.EventArgs e) {
         System.Diagnostics.Process.Start(_model.Directory);
      }

      //============================================================  
      // <T>导出信息处理。</T>
      //============================================================
      private void tspExportInfo_Click(object sender, System.EventArgs e) {
         _model.ExportInfo();
         MessageBox.Show("已经导出信息成功。", "信息", MessageBoxButtons.OK, MessageBoxIcon.Information, MessageBoxDefaultButton.Button1);
      }

      //============================================================  
      // <T>导出资源处理。</T>
      //============================================================
      private void tspExport_Click(object sender, System.EventArgs e) {
         _model.Export(ERsExportMode.CompressDeflate);
         MessageBox.Show("已经导出成功。", "信息", MessageBoxButtons.OK, MessageBoxIcon.Information, MessageBoxDefaultButton.Button1);
      }
   }
}
