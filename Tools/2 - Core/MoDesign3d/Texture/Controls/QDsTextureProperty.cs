using MO.Common.Lang;
using MO.Content2d.Common;
using MO.Content3d.Resource.Texture;
using MO.Core.Forms.Controls;
using System;
using System.Drawing;
using System.Windows.Forms;

namespace MO.Design3d.Texture.Controls
{
   //============================================================
   // <T>纹理属性控件。</T>
   //============================================================
   public partial class QDsTextureProperty : UserControl
   {
      // 纹理对象
      protected FDrTexture _texture;

      // 缩放按键集合
      protected ToolStripButton[] scaleButtons;

      //============================================================
      // <T>构造纹理属性控件。</T>
      //============================================================
      public QDsTextureProperty() {
         InitializeComponent();
         scaleButtons = new ToolStripButton[] { tsbScalex1p8, tsbScalex1p4, tsbScalex1p2, tsbScalex1, tsbScalex2, tsbScalex4, tsbScalex8, tsbScaleAuto };
      }

      //============================================================
      // <T>获得关联资源纹理。</T>
      //
      // @return 资源纹理
      //============================================================
      public FDrTexture ResourceTexture {
         get { return _texture; }
      }

      //============================================================
      // <T>展示图片。</T>
      //
      // @prama index 行索引
      //============================================================
      private void UncheckScaleButtons() {
         foreach (ToolStripButton button in scaleButtons) {
            button.Checked = false;
            button.CheckState = CheckState.Unchecked;
         }
      }


      //============================================================
      // <T>获得当前选中缩放比率。</T>
      //============================================================
      private void RefreshScaleInfo() {
         foreach (ToolStripButton button in scaleButtons) {
            if (button.Checked) {
               string tag = button.Tag.ToString();
               if ("Auto" == tag) {
                  qpbViewer.ScaleMode = EPictureBoxMode.Auto;
               } else {
                  qpbViewer.ScaleMode = EPictureBoxMode.ScalePixel;
                  qpbViewer.ScaleValue = RFloat.Parse(tag);
               }
            }
         }
      }

      //============================================================
      // <T>加载光照纹理。</T>
      //
      // @prama texture 纹理对象
      //============================================================
      protected void LoadLightTexture(FDrColorTexture texture) {
         // 设置属性
         txtCode.Text = texture.Name;
         txtLabel.Text = texture.Label;
         // 创建数据行列表
         qgvTextures.Rows.Clear();
         for (int n = 1; n < EDrTexture.TextureCount; n++) {
            DataGridViewRow row = new DataGridViewRow();
            DataGridViewTextBoxCell cell = new DataGridViewTextBoxCell();
            cell.Value = (n - 1).ToString();
            row.Cells.Add(cell);
            qgvTextures.Rows.Add(row);
         }
         // 填充数据行信息
         DataGridViewRow firstRow = null;
         foreach (FDrTextureBitmap bitmap in _texture.Bitmaps) {
            if (bitmap.TypeCd == EDrTexture.None) {
               continue;
            }
            DataGridViewRow row = qgvTextures.Rows[bitmap.TypeCd - 1];
            // 设置首行
            if (null == firstRow) {
               firstRow = row;
            }
            // 设置数据行信息
            row.DefaultCellStyle.BackColor = Color.LightGreen;
            row.Tag = bitmap;
            // 设置单元格信息
            row.Cells[1].Value = bitmap.TypeName;
            row.Cells[2].Value = bitmap.Size.Width + "×" + bitmap.Size.Height;
            row.Cells[3].Value = bitmap.Length.ToString();
            row.Cells[4].Value = bitmap.Source;
         }
         // 默认显示第一张图片
         if (null != firstRow) {
            firstRow.Selected = true;
            SelectGridRow(firstRow);
         }
      }

      //============================================================
      // <T>加载剪辑纹理。</T>
      //
      // @prama texture 纹理对象
      //============================================================
      protected void LoadClipTexture(FDrClipTexture texture) {
         // 建立数据行集合
         txtCode.Text = texture.Name;
         txtLabel.Text = texture.Label;
         FDrTextureBitmap firstBitmap = null;
         qgvTextures.Rows.Clear();
         int index = 0;
         foreach (FDrTextureBitmap bitmap in _texture.Bitmaps) {
            // 建立行
            DataGridViewRow row = new DataGridViewRow();
            // 设置数据行信息
            row.DefaultCellStyle.BackColor = Color.LightGreen;
            row.Tag = bitmap;
            // 设置单元格信息
            DataGridViewTextBoxCell cellType = new DataGridViewTextBoxCell();
            cellType.Value = (++index).ToString();
            row.Cells.Add(cellType);
            DataGridViewTextBoxCell typeType = new DataGridViewTextBoxCell();
            typeType.Value = bitmap.TypeName;
            row.Cells.Add(typeType);
            DataGridViewTextBoxCell cellSize = new DataGridViewTextBoxCell();
            cellSize.Value = bitmap.Size.Width + "×" + bitmap.Size.Height;
            row.Cells.Add(cellSize);
            DataGridViewTextBoxCell cellLength = new DataGridViewTextBoxCell();
            cellLength.Value = bitmap.Length.ToString();
            row.Cells.Add(cellLength);
            DataGridViewTextBoxCell cellSource = new DataGridViewTextBoxCell();
            cellSource.Value = bitmap.Source;
            row.Cells.Add(cellSource);
            // 增加行
            qgvTextures.Rows.Add(row);
            // 设置首行
            if (null == firstBitmap) {
               row.Selected = true;
               firstBitmap = bitmap;
            }
         }
         // 默认显示第一张图片
         if (null != firstBitmap) {
            UncheckScaleButtons();
            tsbScaleAuto.Checked = true;
            tsbScaleAuto.CheckState = CheckState.Checked;
            RefreshScaleInfo();
            qpbViewer.LoadBitmap(firstBitmap.Image.Native);
         }
      }

      //============================================================
      // <T>加载剪辑纹理。</T>
      //
      // @prama texture 纹理对象
      //============================================================
      protected void LoadLayerTexture(FDrLayerTexture texture) {
         // 建立数据行集合
         txtCode.Text = texture.Name;
         txtLabel.Text = texture.Label;
         FDrTextureBitmap firstBitmap = null;
         qgvTextures.Rows.Clear();
         int index = 0;
         foreach (FDrTextureBitmap bitmap in _texture.Bitmaps) {
            // 建立行
            DataGridViewRow row = new DataGridViewRow();
            // 设置数据行信息
            row.DefaultCellStyle.BackColor = Color.LightGreen;
            row.Tag = bitmap;
            // 设置单元格信息
            DataGridViewTextBoxCell cellType = new DataGridViewTextBoxCell();
            cellType.Value = (++index).ToString();
            row.Cells.Add(cellType);
            DataGridViewTextBoxCell typeType = new DataGridViewTextBoxCell();
            typeType.Value = bitmap.TypeName;
            row.Cells.Add(typeType);
            DataGridViewTextBoxCell cellSize = new DataGridViewTextBoxCell();
            cellSize.Value = bitmap.Size.Width + "×" + bitmap.Size.Height;
            row.Cells.Add(cellSize);
            DataGridViewTextBoxCell cellLength = new DataGridViewTextBoxCell();
            cellLength.Value = bitmap.Length.ToString();
            row.Cells.Add(cellLength);
            DataGridViewTextBoxCell cellSource = new DataGridViewTextBoxCell();
            cellSource.Value = bitmap.Source;
            row.Cells.Add(cellSource);
            // 增加行
            qgvTextures.Rows.Add(row);
            // 设置首行
            if (null == firstBitmap) {
               row.Selected = true;
               firstBitmap = bitmap;
            }
         }
         // 默认显示第一张图片
         if (null != firstBitmap) {
            UncheckScaleButtons();
            tsbScaleAuto.Checked = true;
            tsbScaleAuto.CheckState = CheckState.Checked;
            RefreshScaleInfo();
            qpbViewer.LoadBitmap(firstBitmap.Image.Native);
         }
      }

      //============================================================
      // <T>加载纹理。</T>
      //
      // @prama texture 纹理对象
      //============================================================
      public void LoadTexture(FDrTexture texture) {
         texture.Open();
         _texture = texture;
         // 加载光照纹理
         if (_texture is FDrColorTexture) {
            LoadLightTexture(_texture as FDrColorTexture);
         }
         // 加载剪辑纹理
         if (_texture is FDrClipTexture) {
            LoadClipTexture(_texture as FDrClipTexture);
         }
         // 加载分层纹理
         if (_texture is FDrLayerTexture) {
            LoadLayerTexture(_texture as FDrLayerTexture);
         }
         Invalidate();
      }

      //============================================================
      // <T>缩放资源设置。</T>
      //============================================================
      private void tsbScale_Click(object sender, EventArgs e) {
         UncheckScaleButtons();
         ToolStripButton button = sender as ToolStripButton;
         float scale = 0;
         if (null != button) {
            string tag = button.Tag.ToString();
            if (tag == "Auto") {
               qpbViewer.ScaleMode = EPictureBoxMode.Auto;
               tsbScaleAuto.Checked = true;
               tsbScaleAuto.CheckState = CheckState.Checked;
            } else {
               scale = RFloat.Parse(tag);
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
      // <T>选中图片行记录。</T>
      //============================================================
      private void SelectGridRow(DataGridViewRow row) {
         qpbViewer.Clear();
         qpbViewer.Visible = true;
         if (null != row) {
            FDrTextureBitmap bitmap = row.Tag as FDrTextureBitmap;
            if (null != bitmap) {
               qpbViewer.LoadBitmap(bitmap.Image.Native);
               RefreshScaleInfo();
            }
         }
      }

      //============================================================
      // <T>保存资源设置。</T>
      //============================================================
      private void tspSave_Click(object sender, EventArgs e) {
         _texture.Store();
         MessageBox.Show("已经保存成功。", "信息", MessageBoxButtons.OK, MessageBoxIcon.Information, MessageBoxDefaultButton.Button1);
      }

      //============================================================
      // <T>打开资源所在文件夹。</T>
      //============================================================
      private void tsbOpen_Click(object sender, EventArgs e) {
         if (_texture != null) {
            System.Diagnostics.Process.Start(_texture.Directory);
         }
      }

      //============================================================
      // <T>导出合并数据。</T>
      //============================================================
      private void tspExportMerge_Click(object sender, EventArgs e) {
         _texture.ExportMerge();
         MessageBox.Show("已经导出合并成功。", "信息", MessageBoxButtons.OK, MessageBoxIcon.Information, MessageBoxDefaultButton.Button1);
      }

      //============================================================
      // <T>导出资源数据。</T>
      //============================================================
      private void tspExportResource_Click(object sender, EventArgs e) {
         _texture.Export(ERsExportMode.CompressDeflate);
         MessageBox.Show("已经导出资源成功。", "信息", MessageBoxButtons.OK, MessageBoxIcon.Information, MessageBoxDefaultButton.Button1);
      }

      //============================================================
      // <T>选中图片行记录。</T>
      //============================================================
      private void qgvTextures_RowEnter(object sender, DataGridViewCellEventArgs e) {
         int rowIndex = e.RowIndex;
         if (rowIndex >= 0) {
            DataGridViewRow row = qgvTextures.Rows[rowIndex];
            SelectGridRow(row);
         }
      }

      //============================================================
      // <T>清空所有能数值。</T>
      //============================================================
      public void ClearValue() {
         qgvTextures.Rows.Clear();
         qpbViewer.Clear();
      }
   }
}
