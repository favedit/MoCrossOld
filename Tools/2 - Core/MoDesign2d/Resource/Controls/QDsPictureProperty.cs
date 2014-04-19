using MO.Common.Content;
using MO.Common.Lang;
using MO.Content2d.Common;
using MO.Content2d.Resource.Common;
using MO.Content2d.Resource.Picture;
using MO.Core.Forms.Controls;
using System;
using System.Windows.Forms;

namespace MO.Design2d.Resource.Controls
{
   //============================================================
   // <T>资源图片控件。</T>
   //============================================================
   public partial class QDsPictureProperty : QDsResource
   {
      protected FRsResourcePicture _resourcePicture;

      protected string groupTid;

      protected string resourceCode;

      // 缩放倍数
      protected float _scole = 1.0f;

      //============================================================
      public QDsPictureProperty() {
         InitializeComponent();
      }

      //============================================================
      // <T>加载资源信息。</T>
      //
      // @param resource 资源对象
      //============================================================
      public override void LoadResource(FRsResource resource) {
         base.LoadResource(resource);
         // 设置资源
         _resourcePicture = resource as FRsResourcePicture;
         // 设置信息
         //if (null != _resourcePicture.Group) {
         //   txtGroupName.Text = _resourcePicture.Group.Code;
         //}
         //resourceCode = _resourcePicture.Code;
         txtLabel.Text = _resourcePicture.Label;
         txtFileName.Text = _resourcePicture.FileName;
         cbxAlpha.Checked = _resourcePicture.OptionAlpha;
         chkPandding.Checked = _resourcePicture.OptionPadding;
         // 设置调色板类型
         switch (_resourcePicture.QualityPalette) {
            case 64:
               rbnPaletteColor64.Checked = true;
               break; ;
            case 128:
               rbnPaletteColor128.Checked = true;
               break; ;
            case 256:
               rbnPaletteColor256.Checked = true;
               break; ;
         }
         // 设置超时类型
         switch (_resourcePicture.TimeoutCd) {
            case "none":
               radNull.Checked = true;
               break;
            case "short":
               radShort.Checked = true;
               break;
            case "middle":
               radMiddle.Checked = true;
               break;
            case "long":
               radLong.Checked = true;
               break;
         }
         // 设置质量类型
         switch (_resourcePicture.QualityCd) {
            case ERsResourceQuality.Max:
               ranPalettePixel5.Checked = true;
               break;
            case ERsResourceQuality.Middle:
               ranPalettePixel3.Checked = true;
               break;
            case ERsResourceQuality.Lower:
               ranPalettePixel2.Checked = true;
               break;
            case ERsResourceQuality.High:
               ranPalettePixel4.Checked = true;
               break;
            case ERsResourceQuality.Min:
               ranPalettePixel1.Checked = true;
               break;
            default:
               break;
         }
         // 设置预览
         qpbImage.ScaleMode = EPictureBoxMode.ScalePixel;
         qpbImage.LoadBitmap(_resourcePicture.Bitmap.Native);
         //int count = RDsResource.PicturePixelSplit;
         //if (_resourcePicture.QualityCd == 0 || _resourcePicture.QualityCd == count) {
         //   _resourcePicture.QualityCd = count;
         //   ranPalettePixel3.Checked = true;
         //   return;
         //} 
         //if (_resourcePicture.QualityCd > count) {
         //   int sum = _resourcePicture.QualityCd / count;
         //   if (sum == 2) {
         //      ranPalettePixel2.Checked = true;
         //   }
         //   if (sum == 4) {
         //      ranPalettePixel1.Checked = true;
         //   }
         //}
         //if (_resourcePicture.QualityCd < count) {
         //   int sum = count / _resourcePicture.QualityCd;
         //   if (sum == 2) {
         //      ranPalettePixel4.Checked = true;
         //   }
         //   if (sum == 4) {
         //      ranPalettePixel5.Checked = true;
         //   }
         //}
      }

      //============================================================
      // <T>保存资源信息。</T>
      //
      // @param resource 资源对象
      //============================================================
      public override void SaveResource(FRsResource resource) {
         base.SaveResource(resource);
         //int count = RDsResource.PicturePixelSplit;
         //_resourcePicture.OptionAlpha = cbxAlpha.Checked;
         //_resourcePicture.OptionPadding = chkPandding.Checked;
         //if (rbnPaletteColor64.Checked) {
         //   _resourcePicture.QualityPalette = 64;
         //}
         //if (rbnPaletteColor128.Checked) {
         //   _resourcePicture.QualityPalette = 128;
         //}
         //if (rbnPaletteColor256.Checked) {
         //   _resourcePicture.QualityPalette = 256;
         //}
         //if (ranPalettePixel1.Checked) {
         //   _resourcePicture.QualityCd = ERsResourceQuality.Min;
         //}
         //if (ranPalettePixel2.Checked) {
         //   _resourcePicture.QualityCd = ERsResourceQuality.Lower;
         //}
         //if (ranPalettePixel3.Checked) {
         //   _resourcePicture.QualityCd = ERsResourceQuality.Middle;
         //}
         //if (ranPalettePixel4.Checked) {
         //   _resourcePicture.QualityCd = ERsResourceQuality.High;
         //}
         //if (ranPalettePixel5.Checked) {
         //   _resourcePicture.QualityCd = ERsResourceQuality.Max;
         //}
      }

      //============================================================
      // <T>打开文件夹处理。</T>
      //
      // @param sender 发送者
      // @param e 事件
      //============================================================
      private void tsbFolder_Click(object sender, EventArgs e) {
         string directory = _resourcePicture.Directory;
         if (!RString.IsEmpty(directory)) {
            System.Diagnostics.Process.Start(directory);
         }
      }

      //============================================================
      // <T>打开内容处理。</T>
      //
      // @param sender 发送者
      // @param e 事件
      //============================================================
      private void tsbOpen_Click(object sender, EventArgs e) {
         string fileName = _resourcePicture.FileName;
         if (!RString.IsEmpty(fileName)) {
            System.Diagnostics.Process.Start(fileName);
         }
      }

      //============================================================      
      // <T>缩放内容处理。</T>
      //
      // @param sender 发送者
      // @param e 事件
      //============================================================
      private void tsbScale_Click(object sender, EventArgs e) {
         foreach (ToolStripItem item in tsResource.Items) {
            if (item is ToolStripButton) {
               ToolStripButton button = item as ToolStripButton;
               if (!button.Equals(sender)) {
                  button.Checked = false;
               } else {
                  button.Checked = true;
               }
            }
         }
         if (null != qpbImage.Bitmap) {
            ToolStripButton button = sender as ToolStripButton;
            string typeString = button.Tag.ToString();
            if (typeString.Equals("Auto")) {
               qpbImage.ScaleMode = EPictureBoxMode.Auto;
               return;
            }
            float scale = RFloat.Parse(button.Tag.ToString());
            if (scale > 1) {
               qpbImage.ScaleMode = EPictureBoxMode.ScalePixel;
            } else { qpbImage.ScaleMode = EPictureBoxMode.Scale; }
            qpbImage.ScaleValue = scale;
         }
      }

      //============================================================      
      // <T>保存资源事件。</T>
      //
      // @param sender 发送者
      // @param e 事件
      //============================================================
      private void tsbSave_Click(object sender, System.EventArgs e) {
         SaveResource(_resourcePicture);
         _resourcePicture.Store();
         MessageBox.Show("存储文件完成。");
      }

      //============================================================      
      // <T>导出资源事件。</T>
      //
      // @param sender 发送者
      // @param e 事件
      //============================================================
      private void tsbExport_Click(object sender, System.EventArgs e) {
         if (_resourcePicture.OptionValid) {
            SaveResource(_resource);
            _resource.Export(ERsExportMode.Data);
            MessageBox.Show("导出图片文件完成。");
         }
      }

      //============================================================
      // <T>透明选中事件。</T>
      //
      // @param sender 事件产生者
      // @param e 数据对象
      //============================================================
      private void cbxAlpha_CheckedChanged(object sender, EventArgs e) {
         _resourcePicture.OptionAlpha = cbxAlpha.Checked;
      }

      //============================================================
      // <T>调色板颜色数选中事件。</T>
      //
      // @param sender 事件产生者
      // @param e 数据对象
      //============================================================
      private void rbnPaletteColor_CheckedChanged(object sender, EventArgs e) {
         RadioButton button = sender as RadioButton;
         if ((null != button) && (null != button.Tag)) {
            _resourcePicture.QualityPalette = RInt.Parse(button.Tag.ToString());
         }
      }

      //============================================================      
      // <T>设置切图色素标准</T>
      //
      // @param sender 事件产生者
      // @param e      数据对象
      //============================================================
      private void cmbAlphaVelue_TextChanged(object sender, EventArgs e) {
         //_resourcePicture.AlphaVelue = RInt.Parse(cmbAlphaVelue.Text);
      }

      //============================================================
      // <T>设置切割图块的像素。</T>
      //
      // @param sender 事件产生者
      // @param e      数据对象
      //============================================================
      private void ranPalettePixel_CheckedChanged(object sender, EventArgs e) {
         RadioButton button = sender as RadioButton;
         if ((null != button) && (null != button.Tag)) {
            _resourcePicture.QualityCd = button.Tag.ToString();
         }
      }

      //============================================================      
      // <T>设置是否保留空白</T>
      //
      // @param sender 事件产生者
      // @param e      数据对象
      //============================================================
      private void chkPandding_CheckedChanged(object sender, EventArgs e) {
         _resourcePicture.OptionPadding = chkPandding.Checked;
      }

      //============================================================
      // <T>设置超时类型。</T>
      //============================================================
      private void CheckedChanged(object sender, EventArgs e) {
         RadioButton button = sender as RadioButton;
         if ((null != button) && (null != button.Tag)) {
            _resourcePicture.TimeoutCd = button.Tag.ToString();
         }
      }

      //============================================================
      // <T>编辑资源组。</T>
      //============================================================
      private void btnEditGroup_Click(object sender, EventArgs e){
          groupTid = this.txtGroupName.Text;
          string path = @"D:\WP-Resource\Configuration\style.resource.xml";
          FXmlDocument ResourceXml = new FXmlDocument(path);
          foreach (FXmlNode fxmlNode in ResourceXml.Root.Nodes){
              if (fxmlNode.Get("code").Equals(resourceCode)){
                  fxmlNode.Set("group_tid", groupTid);
              }
          }
          ResourceXml.SaveFile(path);
          MessageBox.Show("资源组已修改！"); 
      }
   }
}
