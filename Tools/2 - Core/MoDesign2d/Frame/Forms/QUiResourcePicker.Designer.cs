
namespace MO.Design2d.Frame.Forms
{
   partial class QUiResourcePicker
   {
      /// <summary> 
      /// 必需的设计器变量。
      /// </summary>
      private System.ComponentModel.IContainer components = null;

      /// <summary> 
      /// 清理所有正在使用的资源。
      /// </summary>
      /// <param name="disposing">如果应释放托管资源，为 true；否则为 false。</param>
      protected override void Dispose(bool disposing) {
         if(disposing && (components != null)) {
            components.Dispose();
         }
         base.Dispose(disposing);
      }

      #region 组件设计器生成的代码

      /// <summary> 
      /// 设计器支持所需的方法 - 不要
      /// 使用代码编辑器修改此方法的内容。
      /// </summary>
      private void InitializeComponent() {
         MO.Content2d.Resource.Picture.FRsResourcePicture fDsResourcePicture1 = new MO.Content2d.Resource.Picture.FRsResourcePicture();
         this.btnSearch = new System.Windows.Forms.Button();
         this.pnlResources = new System.Windows.Forms.Panel();
         this.pnlPreviewer = new System.Windows.Forms.Panel();
         this.qrsPictureViewer = new MO.Design2d.Resource.Controls.QDsResourceClipViewer();
         this.qrsClipViewer = new MO.Design2d.Resource.Controls.QDsResourceClipProperty();
         this.panel1 = new System.Windows.Forms.Panel();
         this.splBody = new System.Windows.Forms.Splitter();
         this.qrsResourceList = new MO.Design2d.Frame.Forms.QUiResourceList();
         this.pnlSearch = new System.Windows.Forms.Panel();
         this.btnClear = new System.Windows.Forms.Button();
         this.lblFilterValue = new System.Windows.Forms.Label();
         this.txtFilterValue = new System.Windows.Forms.TextBox();
         this.pnlResult = new System.Windows.Forms.Panel();
         this.lblResult = new System.Windows.Forms.Label();
         this.labInfo = new System.Windows.Forms.Label();
         this.pnlResources.SuspendLayout();
         this.pnlPreviewer.SuspendLayout();
         this.panel1.SuspendLayout();
         this.pnlSearch.SuspendLayout();
         this.pnlResult.SuspendLayout();
         this.SuspendLayout();
         // 
         // btnSearch
         // 
         this.btnSearch.Location = new System.Drawing.Point(515, 2);
         this.btnSearch.Name = "btnSearch";
         this.btnSearch.Size = new System.Drawing.Size(80, 23);
         this.btnSearch.TabIndex = 3;
         this.btnSearch.Text = "搜索";
         this.btnSearch.UseVisualStyleBackColor = true;
         this.btnSearch.Click += new System.EventHandler(this.btnSearch_Click);
         // 
         // pnlResources
         // 
         this.pnlResources.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
         this.pnlResources.Controls.Add(this.pnlPreviewer);
         this.pnlResources.Controls.Add(this.panel1);
         this.pnlResources.Controls.Add(this.splBody);
         this.pnlResources.Controls.Add(this.qrsResourceList);
         this.pnlResources.Dock = System.Windows.Forms.DockStyle.Fill;
         this.pnlResources.Location = new System.Drawing.Point(0, 30);
         this.pnlResources.Name = "pnlResources";
         this.pnlResources.Size = new System.Drawing.Size(1020, 528);
         this.pnlResources.TabIndex = 8;
         // 
         // pnlPreviewer
         // 
         this.pnlPreviewer.Controls.Add(this.qrsPictureViewer);
         this.pnlPreviewer.Controls.Add(this.qrsClipViewer);
         this.pnlPreviewer.Dock = System.Windows.Forms.DockStyle.Fill;
         this.pnlPreviewer.Location = new System.Drawing.Point(507, 0);
         this.pnlPreviewer.Name = "pnlPreviewer";
         this.pnlPreviewer.Size = new System.Drawing.Size(509, 494);
         this.pnlPreviewer.TabIndex = 5;
         // 
         // qrsPictureViewer
         // 
         this.qrsPictureViewer.AutoScroll = true;
         this.qrsPictureViewer.BackColor = System.Drawing.SystemColors.Window;
         this.qrsPictureViewer.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
         this.qrsPictureViewer.Location = new System.Drawing.Point(140, 4);
         this.qrsPictureViewer.Name = "qrsPictureViewer";
         this.qrsPictureViewer.Size = new System.Drawing.Size(128, 128);
         this.qrsPictureViewer.TabIndex = 4;
         // 
         // qrsClipViewer
         // 
         this.qrsClipViewer.AutoPlay = false;
         this.qrsClipViewer.AutoScale = false;
         this.qrsClipViewer.AutoSizeMode = System.Windows.Forms.AutoSizeMode.GrowAndShrink;
         this.qrsClipViewer.BackColor = System.Drawing.Color.White;
         this.qrsClipViewer.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
         this.qrsClipViewer.ClipScale = 1F;
         this.qrsClipViewer.FrameCurrent = 0;
         this.qrsClipViewer.IsMain = false;
         this.qrsClipViewer.Location = new System.Drawing.Point(6, 4);
         this.qrsClipViewer.Name = "qrsClipViewer";
         fDsResourcePicture1.Directory = null;
         fDsResourcePicture1.FileName = null;
         fDsResourcePicture1.Folder = null;
         fDsResourcePicture1.FullLabel = null;
         fDsResourcePicture1.Keyword = null;
         fDsResourcePicture1.Label = null;
         fDsResourcePicture1.LastExportTick = ((long)(0));
         fDsResourcePicture1.LastUpdateTick = ((long)(0));
         fDsResourcePicture1.OptionAlpha = false;
         fDsResourcePicture1.OptionExport = false;
         fDsResourcePicture1.OptionPadding = false;
         fDsResourcePicture1.OptionValid = false;
         fDsResourcePicture1.QualityAlpha = 0;
         fDsResourcePicture1.QualityCd = "middle";
         fDsResourcePicture1.QualityPalette = 256;
         fDsResourcePicture1.Tag = null;
         fDsResourcePicture1.TimeoutCd = "none";
         this.qrsClipViewer.Picture = fDsResourcePicture1;
         this.qrsClipViewer.ShowBarycenter = false;
         this.qrsClipViewer.ShowBloodPoint = false;
         this.qrsClipViewer.ShowOrnamentsPoint = false;
         this.qrsClipViewer.ShowRidePoint = false;
         this.qrsClipViewer.Size = new System.Drawing.Size(128, 128);
         this.qrsClipViewer.TabIndex = 3;
         // 
         // panel1
         // 
         this.panel1.Controls.Add(this.labInfo);
         this.panel1.Dock = System.Windows.Forms.DockStyle.Bottom;
         this.panel1.Location = new System.Drawing.Point(507, 494);
         this.panel1.Name = "panel1";
         this.panel1.Size = new System.Drawing.Size(509, 30);
         this.panel1.TabIndex = 9;
         // 
         // splBody
         // 
         this.splBody.Location = new System.Drawing.Point(503, 0);
         this.splBody.Name = "splBody";
         this.splBody.Size = new System.Drawing.Size(4, 524);
         this.splBody.TabIndex = 8;
         this.splBody.TabStop = false;
         // 
         // qrsResourceList
         // 
         this.qrsResourceList.Dock = System.Windows.Forms.DockStyle.Left;
         this.qrsResourceList.FilterValue = null;
         this.qrsResourceList.Location = new System.Drawing.Point(0, 0);
         this.qrsResourceList.Name = "qrsResourceList";
         this.qrsResourceList.Size = new System.Drawing.Size(503, 524);
         this.qrsResourceList.TabIndex = 0;
         this.qrsResourceList.ResourceClick += new System.EventHandler(this.qrsResourceList_ResourceClick);
         this.qrsResourceList.ResourceDoubleClick += new System.EventHandler(this.qrsResourceList_ResourceDoubleClick);
         // 
         // pnlSearch
         // 
         this.pnlSearch.Controls.Add(this.btnClear);
         this.pnlSearch.Controls.Add(this.btnSearch);
         this.pnlSearch.Controls.Add(this.lblFilterValue);
         this.pnlSearch.Controls.Add(this.txtFilterValue);
         this.pnlSearch.Dock = System.Windows.Forms.DockStyle.Top;
         this.pnlSearch.Location = new System.Drawing.Point(0, 0);
         this.pnlSearch.Name = "pnlSearch";
         this.pnlSearch.Size = new System.Drawing.Size(1020, 30);
         this.pnlSearch.TabIndex = 6;
         // 
         // btnClear
         // 
         this.btnClear.Location = new System.Drawing.Point(601, 2);
         this.btnClear.Name = "btnClear";
         this.btnClear.Size = new System.Drawing.Size(80, 23);
         this.btnClear.TabIndex = 3;
         this.btnClear.Text = "清空";
         this.btnClear.UseVisualStyleBackColor = true;
         this.btnClear.Click += new System.EventHandler(this.btnClear_Click);
         // 
         // lblFilterValue
         // 
         this.lblFilterValue.AutoSize = true;
         this.lblFilterValue.Location = new System.Drawing.Point(9, 7);
         this.lblFilterValue.Name = "lblFilterValue";
         this.lblFilterValue.Size = new System.Drawing.Size(53, 12);
         this.lblFilterValue.TabIndex = 2;
         this.lblFilterValue.Text = "查找内容";
         // 
         // txtFilterValue
         // 
         this.txtFilterValue.Location = new System.Drawing.Point(68, 3);
         this.txtFilterValue.Name = "txtFilterValue";
         this.txtFilterValue.Size = new System.Drawing.Size(437, 21);
         this.txtFilterValue.TabIndex = 1;
         this.txtFilterValue.KeyDown += new System.Windows.Forms.KeyEventHandler(this.txtId_KeyDown);
         // 
         // pnlResult
         // 
         this.pnlResult.BackColor = System.Drawing.SystemColors.ButtonShadow;
         this.pnlResult.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
         this.pnlResult.Controls.Add(this.lblResult);
         this.pnlResult.Dock = System.Windows.Forms.DockStyle.Bottom;
         this.pnlResult.Location = new System.Drawing.Point(0, 558);
         this.pnlResult.Name = "pnlResult";
         this.pnlResult.Size = new System.Drawing.Size(1020, 32);
         this.pnlResult.TabIndex = 7;
         // 
         // lblResult
         // 
         this.lblResult.AutoSize = true;
         this.lblResult.Location = new System.Drawing.Point(13, 8);
         this.lblResult.Name = "lblResult";
         this.lblResult.Size = new System.Drawing.Size(53, 12);
         this.lblResult.TabIndex = 2;
         this.lblResult.Text = "查询结果";
         // 
         // labInfo
         // 
         this.labInfo.AutoSize = true;
         this.labInfo.Location = new System.Drawing.Point(7, 9);
         this.labInfo.Name = "labInfo";
         this.labInfo.Size = new System.Drawing.Size(53, 12);
         this.labInfo.TabIndex = 3;
         this.labInfo.Text = "查询结果";
         // 
         // QUiResourcePicker
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.Controls.Add(this.pnlResources);
         this.Controls.Add(this.pnlSearch);
         this.Controls.Add(this.pnlResult);
         this.Name = "QUiResourcePicker";
         this.Size = new System.Drawing.Size(1020, 590);
         this.pnlResources.ResumeLayout(false);
         this.pnlPreviewer.ResumeLayout(false);
         this.panel1.ResumeLayout(false);
         this.panel1.PerformLayout();
         this.pnlSearch.ResumeLayout(false);
         this.pnlSearch.PerformLayout();
         this.pnlResult.ResumeLayout(false);
         this.pnlResult.PerformLayout();
         this.ResumeLayout(false);

      }

      #endregion

      private System.Windows.Forms.Button btnSearch;
      private System.Windows.Forms.Panel pnlResources;
      private System.Windows.Forms.Splitter splBody;
      private System.Windows.Forms.Panel pnlPreviewer;
      private System.Windows.Forms.Panel pnlSearch;
      private System.Windows.Forms.Label lblFilterValue;
      private System.Windows.Forms.TextBox txtFilterValue;
      private System.Windows.Forms.Panel pnlResult;
      private System.Windows.Forms.Label lblResult;
      private QUiResourceList qrsResourceList;
      private Resource.Controls.QDsResourceClipProperty qrsClipViewer;
      private Resource.Controls.QDsResourceClipViewer qrsPictureViewer;
      private System.Windows.Forms.Button btnClear;
      private System.Windows.Forms.Panel panel1;
      private System.Windows.Forms.Label labInfo;
   }
}
