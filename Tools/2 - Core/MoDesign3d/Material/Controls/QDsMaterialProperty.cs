using MO.Content3d.Resource.Material;
using MO.Core.Forms.Controls;
using System;
using System.Drawing;
using System.Windows.Forms;

namespace MO.Design3d.Material.Controls
{
   public partial class QDsMaterialProperty : UserControl
   {
      // 加载中
      protected bool _loading;

      // 材质对象
      protected FDrMaterialGroup _materialGroup;

      //============================================================
      // <T>构造材质属性控件。</T>
      //============================================================
      public QDsMaterialProperty() {
         InitializeComponent();
      }

      //============================================================
      // <T>获得材质对象。</T>
      //============================================================
      public FDrMaterialGroup MaterialGroup {
         get { return _materialGroup; }
      }

      //============================================================
      // <T>加载材质信息。</T>
      //
      // @prama material 材质名称
      //============================================================
      public void LoadMaterialGroup(FDrMaterialGroup materialGroup) {
         _loading = true;
         // 打开材质
         materialGroup.Open();
         _materialGroup = materialGroup;
         // 建立材质集合
         lbxThemes.Items.Clear();
         foreach (FDrMaterial material in materialGroup.Materials) {
            lbxThemes.Items.Add(material.ThemeCode);
         }
         lbxThemes.SelectedIndex = 0;
         // 设置属性
         qdrMaterialGroup.LoadMaterialGroup(materialGroup);
         qdrMaterialInfo.LoadMaterial(materialGroup.Materials.First);
         // 建立列表
         FDrMaterialTexture firstTexture = null;
         qgvTextures.Rows.Clear();
         foreach (FDrMaterialTexture texture in materialGroup.Textures) {
            // 创建数据行
            DataGridViewRow row = new DataGridViewRow();
            row.DefaultCellStyle.BackColor = Color.LightGreen;
            row.Tag = texture;
            // 创建类型单元格
            DataGridViewTextBoxCell cellType = new DataGridViewTextBoxCell();
            cellType.Value = texture.TypeName;
            row.Cells.Add(cellType);
            // 创建有效性单元格
            DataGridViewTextBoxCell cellValid = new DataGridViewTextBoxCell();
            cellValid.Value = texture.IsValid;
            row.Cells.Add(cellValid);
            // 创建来源单元格
            DataGridViewTextBoxCell cellSource = new DataGridViewTextBoxCell();
            cellSource.Value = texture.Source;
            row.Cells.Add(cellSource);
            // 创建来源类型单元格
            DataGridViewTextBoxCell cellSourceType = new DataGridViewTextBoxCell();
            cellSourceType.Value = texture.TypeName;
            row.Cells.Add(cellSourceType);
            // 创建来源索引单元格
            DataGridViewTextBoxCell cellSourceIndex = new DataGridViewTextBoxCell();
            cellSourceIndex.Value = texture.SourceIndex;
            row.Cells.Add(cellSourceIndex);
            // 创建尺寸单元格
            DataGridViewTextBoxCell cellSize = new DataGridViewTextBoxCell();
            if (null != texture.Bitmap) {
               cellSize.Value = texture.Bitmap.Size.Width + "X" + texture.Bitmap.Size.Height;
            }
            row.Cells.Add(cellSize);
            // 创建数据长度单元格
            DataGridViewTextBoxCell cellLength = new DataGridViewTextBoxCell();
            if (null != texture.Bitmap) {
               cellLength.Value = texture.Bitmap.Length;
            }
            row.Cells.Add(cellLength);
            qgvTextures.Rows.Add(row);
            // 设置首行
            if (null == firstTexture) {
               row.Selected = true;
               firstTexture = texture;
            }
         }
         // 默认显示第一张图片
         if (null != firstTexture) {
            qpbViewer.LoadBitmap(firstTexture.Bitmap.Image.Native);
         } else {
            qpbViewer.Clear();
         }
         _loading = false;
      }

      //============================================================
      // <T>存储材质信息。</T>
      //
      // @prama material 材质名称
      //============================================================
      public void SaveMaterial(FDrMaterialGroup materialGroup) {
         qdrMaterialGroup.SaveMaterialGroup(materialGroup);
         if (null != _materialGroup) {
            qdrMaterialInfo.SaveMaterial();
         }
      }

      //============================================================
      // <T>展示图片。</T>
      //
      // @prama index 行索引
      //============================================================
      private void lbxThemes_SelectedIndexChanged(object sender, EventArgs e) {
         int index = lbxThemes.SelectedIndex;
         FDrMaterial material = _materialGroup.Materials[index];
         qdrMaterialInfo.SaveMaterial();
         qdrMaterialInfo.LoadMaterial(material);
      }

      //============================================================
      // <T>展示图片。</T>
      //
      // @prama index 行索引
      //============================================================
      private void UncheckScaleButtons() {
         ToolStripButton[] buttons = new ToolStripButton[] { tsbScalex1p4, tsbScalex1p2, tsbScalex1, tsbScalex2, tsbScalex4, tsbScaleAuto };
         foreach (ToolStripButton tsb in buttons) {
            tsb.Checked = false;
            tsb.CheckState = CheckState.Unchecked;
         }
      }

      //============================================================
      // <T>保存按键按下处理。</T>
      //============================================================
      private void tspSave_Click(object sender, EventArgs e) {
         SaveMaterial(_materialGroup);
         _materialGroup.Store();
         MessageBox.Show("已经保存成功。", "信息", MessageBoxButtons.OK, MessageBoxIcon.Information, MessageBoxDefaultButton.Button1);
      }

      //============================================================
      // <T>打开按键按下处理。</T>
      //============================================================
      private void tsbOpen_Click(object sender, EventArgs e) {
         if (null != _materialGroup) {
            System.Diagnostics.Process.Start(_materialGroup.Directory);
         }
      }

      //============================================================
      // <T>缩放资源设置。</T>
      //============================================================
      private void tsbScale_Click(object sender, EventArgs e) {
         UncheckScaleButtons();
         ToolStripButton button = sender as ToolStripButton;
         float scale = 0;
         if (null != button) {
            string scaleName = button.Tag.ToString();
            if (scaleName == "Auto") {
               qpbViewer.ScaleMode = EPictureBoxMode.Auto;
               tsbScaleAuto.Checked = true;
               tsbScaleAuto.CheckState = CheckState.Checked;
            } else {
               scale = float.Parse(((ToolStripButton)sender).Tag.ToString());
               if (scale < 1.0f) {
                  qpbViewer.ScaleMode = EPictureBoxMode.Scale;
                  qpbViewer.ScaleValue = scale;
               } else {
                  qpbViewer.ScaleMode = EPictureBoxMode.ScalePixel;
                  qpbViewer.ScaleValue = (float)((int)scale);
               }
               ((ToolStripButton)sender).Checked = true;
               ((ToolStripButton)sender).CheckState = CheckState.Checked;
            }
         }
      }

      //============================================================
      // <T>导出按键按下处理。</T>
      //============================================================
      private void tspExport_Click(object sender, EventArgs e) {
         _materialGroup.Export();
         MessageBox.Show("已经导出成功。", "信息", MessageBoxButtons.OK, MessageBoxIcon.Information, MessageBoxDefaultButton.Button1);
      }

      //============================================================
      // <T>获得当前选中缩放比率。</T>
      //============================================================
      private float GetBitmapScale() {
         ToolStripButton[] buttons = new ToolStripButton[] { tsbScalex1p4, tsbScalex1p2, tsbScalex1, tsbScalex2, tsbScalex4 };
         foreach (ToolStripButton tsb in buttons) {
            if (tsb.Checked) {
               string tagInfo = tsb.Tag.ToString();
               if ("Auto" == tagInfo) {
                  qpbViewer.ScaleMode = EPictureBoxMode.Auto;
                  return float.NaN;
               }
               qpbViewer.ScaleMode = EPictureBoxMode.Scale;
               return float.Parse(tsb.Tag.ToString());
            }
         }
         qpbViewer.ScaleMode = EPictureBoxMode.Auto;
         return 1.0f;
      }

      //============================================================
      // <T>单击显示图片。</T>
      //============================================================
      private void qgvTextures_RowEnter(object sender, DataGridViewCellEventArgs e) {
         if (!_loading) {
            int rowIndex = e.RowIndex;
            if (-1 != rowIndex) {
               DataGridViewRow row = qgvTextures.Rows[rowIndex];
               FDrMaterialTexture texture = row.Tag as FDrMaterialTexture;
               if (null == texture) {
                  qpbViewer.Clear();
                  qpbViewer.Visible = false;
                  return;
               } else {
                  texture.Open();
                  if (null != texture.Bitmap) {
                     qpbViewer.LoadBitmap(texture.Bitmap.Image.Native);
                  }
                  qpbViewer.Visible = true;
               }
            }
         }
      }

      //============================================================
      // <T>清除表格的值。</T>
      //============================================================
      public void ClearValue() {
         qgvTextures.Rows.Clear();
         qpbViewer.Clear();
      }
   }
}
