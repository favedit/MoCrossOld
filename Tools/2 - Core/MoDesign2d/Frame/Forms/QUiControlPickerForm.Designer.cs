namespace MO.Design2d.Frame.Forms
{
   partial class QUiControlPickerForm
   {
      /// <summary>
      /// Required designer variable.
      /// </summary>
      private System.ComponentModel.IContainer components = null;

      /// <summary>
      /// Clean up any resources being used.
      /// </summary>
      /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
      protected override void Dispose(bool disposing) {
         if(disposing && (components != null)) {
            components.Dispose();
         }
         base.Dispose(disposing);
      }

      #region Windows Form Designer generated code

      /// <summary>
      /// Required method for Designer support - do not modify
      /// the contents of this method with the code editor.
      /// </summary>
      private void InitializeComponent() {
         this.components = new System.ComponentModel.Container();
         System.Windows.Forms.ListViewGroup listViewGroup1 = new System.Windows.Forms.ListViewGroup("公共", System.Windows.Forms.HorizontalAlignment.Left);
         System.Windows.Forms.ListViewGroup listViewGroup2 = new System.Windows.Forms.ListViewGroup("布局", System.Windows.Forms.HorizontalAlignment.Left);
         System.Windows.Forms.ListViewItem listViewItem1 = new System.Windows.Forms.ListViewItem(new string[] {
            "静态标签",
            "不可变更和不可编辑的静态文本。"}, "Label");
         System.Windows.Forms.ListViewItem listViewItem2 = new System.Windows.Forms.ListViewItem(new string[] {
            "标签",
            "可以变更但不可编辑的显示文本。"}, "Label");
         System.Windows.Forms.ListViewItem listViewItem3 = new System.Windows.Forms.ListViewItem(new string[] {
            "文本编辑框",
            "可以变更并可以编辑的动态文本。"}, "Edit", System.Drawing.Color.Empty, System.Drawing.Color.Empty, new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134))));
         System.Windows.Forms.ListViewItem listViewItem4 = new System.Windows.Forms.ListViewItem(new string[] {
            "按键",
            "鼠标单击完成处理的控件。"}, "Button", System.Drawing.Color.Empty, System.Drawing.Color.Empty, new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134))));
         System.Windows.Forms.ListViewItem listViewItem5 = new System.Windows.Forms.ListViewItem(new string[] {
            "单选按键",
            "单选框控件。"}, "Radio", System.Drawing.Color.Empty, System.Drawing.Color.Empty, new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134))));
         System.Windows.Forms.ListViewItem listViewItem6 = new System.Windows.Forms.ListViewItem(new string[] {
            "复选按键",
            "表示选中或未选中的控件。"}, "Check", System.Drawing.Color.Empty, System.Drawing.Color.Empty, new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134))));
         System.Windows.Forms.ListViewItem listViewItem7 = new System.Windows.Forms.ListViewItem(new string[] {
            "多行文本编辑框",
            "可以显示为多行的文本编辑框。"}, "RichText", System.Drawing.Color.Empty, System.Drawing.Color.Empty, new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134))));
         System.Windows.Forms.ListViewItem listViewItem8 = new System.Windows.Forms.ListViewItem(new string[] {
            "进度条",
            "进度表示控件。"}, "ProgressBar");
         System.Windows.Forms.ListViewItem listViewItem9 = new System.Windows.Forms.ListViewItem(new string[] {
            "插槽",
            "可以内部悬停一个控件。"}, "Slot", System.Drawing.Color.Empty, System.Drawing.Color.Empty, new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134))));
         System.Windows.Forms.ListViewItem listViewItem10 = new System.Windows.Forms.ListViewItem(new string[] {
            "图片",
            "显示一张图片。"}, "PictureBox");
         System.Windows.Forms.ListViewItem listViewItem11 = new System.Windows.Forms.ListViewItem(new string[] {
            "艺术字",
            "使用图片构成效果的控件。"}, "ArtFont");
         System.Windows.Forms.ListViewItem listViewItem12 = new System.Windows.Forms.ListViewItem(new string[] {
            "分组容器",
            "包含一组单选框，只允许其中一个被选中。"}, "RadioGroup");
         System.Windows.Forms.ListViewItem listViewItem13 = new System.Windows.Forms.ListViewItem(new string[] {
            "面板控件",
            "可以放入一组控件的面板。"}, "Panel");
         System.Windows.Forms.ListViewItem listViewItem14 = new System.Windows.Forms.ListViewItem(new string[] {
            "分页控件",
            "包含多个分页的控件。"}, "PageControl", System.Drawing.Color.Empty, System.Drawing.Color.Empty, new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134))));
         System.Windows.Forms.ListViewItem listViewItem15 = new System.Windows.Forms.ListViewItem(new string[] {
            "页控件",
            "分页控件下的一页。"}, "Page");
         System.Windows.Forms.ListViewItem listViewItem16 = new System.Windows.Forms.ListViewItem("动画控件", "Movie");
         System.Windows.Forms.ListViewItem listViewItem17 = new System.Windows.Forms.ListViewItem("Slider");
         System.Windows.Forms.ListViewItem listViewItem18 = new System.Windows.Forms.ListViewItem("Calendar");
         System.Windows.Forms.ListViewItem listViewItem19 = new System.Windows.Forms.ListViewItem("List");
         System.Windows.Forms.ListViewItem listViewItem20 = new System.Windows.Forms.ListViewItem("Item");
         System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(QUiControlPickerForm));
         this.lvwControls = new System.Windows.Forms.ListView();
         this.chdControlName = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
         this.chdControlNote = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
         this.imlMain = new System.Windows.Forms.ImageList(this.components);
         this.btnAccept = new System.Windows.Forms.Button();
         this.btnCancel = new System.Windows.Forms.Button();
         this.pnlControls = new System.Windows.Forms.Panel();
         this.pnlControls.SuspendLayout();
         this.SuspendLayout();
         // 
         // lvwControls
         // 
         this.lvwControls.AutoArrange = false;
         this.lvwControls.BorderStyle = System.Windows.Forms.BorderStyle.None;
         this.lvwControls.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.chdControlName,
            this.chdControlNote});
         this.lvwControls.Dock = System.Windows.Forms.DockStyle.Fill;
         this.lvwControls.FullRowSelect = true;
         listViewGroup1.Header = "公共";
         listViewGroup1.Name = "lvgNormal";
         listViewGroup2.Header = "布局";
         listViewGroup2.Name = "lvgContainer";
         this.lvwControls.Groups.AddRange(new System.Windows.Forms.ListViewGroup[] {
            listViewGroup1,
            listViewGroup2});
         listViewItem1.Group = listViewGroup1;
         listViewItem1.Tag = "StaticLabel";
         listViewItem2.Group = listViewGroup1;
         listViewItem2.Tag = "Label";
         listViewItem3.Group = listViewGroup1;
         listViewItem3.Tag = "Edit";
         listViewItem4.Group = listViewGroup1;
         listViewItem4.Tag = "Button";
         listViewItem5.Group = listViewGroup1;
         listViewItem5.Tag = "Radio";
         listViewItem6.Group = listViewGroup1;
         listViewItem6.Tag = "Check";
         listViewItem7.Group = listViewGroup1;
         listViewItem7.Tag = "RichText";
         listViewItem8.Group = listViewGroup1;
         listViewItem8.Tag = "ProgressBar";
         listViewItem9.Group = listViewGroup1;
         listViewItem9.Tag = "Slot";
         listViewItem10.Group = listViewGroup1;
         listViewItem10.Tag = "PictureBox";
         listViewItem11.Group = listViewGroup1;
         listViewItem11.Tag = "ArtFont";
         listViewItem12.Group = listViewGroup2;
         listViewItem12.Tag = "Group";
         listViewItem13.Group = listViewGroup2;
         listViewItem13.Tag = "Panel";
         listViewItem14.Group = listViewGroup2;
         listViewItem14.Tag = "PageControl";
         listViewItem15.Group = listViewGroup2;
         listViewItem15.Tag = "Page";
         listViewItem16.Group = listViewGroup1;
         listViewItem16.Tag = "Movie";
         listViewItem17.Group = listViewGroup1;
         listViewItem17.Tag = "Slider";
         listViewItem18.Group = listViewGroup1;
         listViewItem18.Tag = "Calendar";
         listViewItem19.Group = listViewGroup1;
         listViewItem19.Tag = "List";
         listViewItem20.Group = listViewGroup1;
         listViewItem20.Tag = "Item";
         this.lvwControls.Items.AddRange(new System.Windows.Forms.ListViewItem[] {
            listViewItem1,
            listViewItem2,
            listViewItem3,
            listViewItem4,
            listViewItem5,
            listViewItem6,
            listViewItem7,
            listViewItem8,
            listViewItem9,
            listViewItem10,
            listViewItem11,
            listViewItem12,
            listViewItem13,
            listViewItem14,
            listViewItem15,
            listViewItem16,
            listViewItem17,
            listViewItem18,
            listViewItem19,
            listViewItem20});
         this.lvwControls.LabelWrap = false;
         this.lvwControls.LargeImageList = this.imlMain;
         this.lvwControls.Location = new System.Drawing.Point(6, 6);
         this.lvwControls.MultiSelect = false;
         this.lvwControls.Name = "lvwControls";
         this.lvwControls.Scrollable = false;
         this.lvwControls.Size = new System.Drawing.Size(496, 541);
         this.lvwControls.SmallImageList = this.imlMain;
         this.lvwControls.TabIndex = 6;
         this.lvwControls.UseCompatibleStateImageBehavior = false;
         this.lvwControls.View = System.Windows.Forms.View.Details;
         this.lvwControls.DoubleClick += new System.EventHandler(this.lvwControls_DoubleClick);
         // 
         // chdControlName
         // 
         this.chdControlName.Text = "控件名称";
         this.chdControlName.Width = 147;
         // 
         // chdControlNote
         // 
         this.chdControlNote.Text = "说明";
         this.chdControlNote.Width = 400;
         // 
         // imlMain
         // 
         this.imlMain.ImageStream = ((System.Windows.Forms.ImageListStreamer)(resources.GetObject("imlMain.ImageStream")));
         this.imlMain.TransparentColor = System.Drawing.Color.Transparent;
         this.imlMain.Images.SetKeyName(0, "StaticLabel");
         this.imlMain.Images.SetKeyName(1, "Label");
         this.imlMain.Images.SetKeyName(2, "Radio");
         this.imlMain.Images.SetKeyName(3, "Check");
         this.imlMain.Images.SetKeyName(4, "Number");
         this.imlMain.Images.SetKeyName(5, "Edit");
         this.imlMain.Images.SetKeyName(6, "Button");
         this.imlMain.Images.SetKeyName(7, "Select");
         this.imlMain.Images.SetKeyName(8, "FontBox");
         this.imlMain.Images.SetKeyName(9, "ProcessBar");
         this.imlMain.Images.SetKeyName(10, "PictureBox");
         this.imlMain.Images.SetKeyName(11, "Panel");
         this.imlMain.Images.SetKeyName(12, "Page");
         this.imlMain.Images.SetKeyName(13, "PageControl");
         this.imlMain.Images.SetKeyName(14, "Grid");
         // 
         // btnAccept
         // 
         this.btnAccept.Location = new System.Drawing.Point(346, 520);
         this.btnAccept.Name = "btnAccept";
         this.btnAccept.Size = new System.Drawing.Size(75, 23);
         this.btnAccept.TabIndex = 7;
         this.btnAccept.Text = "Accept";
         this.btnAccept.UseVisualStyleBackColor = true;
         this.btnAccept.Visible = false;
         this.btnAccept.Click += new System.EventHandler(this.btnAccept_Click);
         // 
         // btnCancel
         // 
         this.btnCancel.DialogResult = System.Windows.Forms.DialogResult.Cancel;
         this.btnCancel.Location = new System.Drawing.Point(427, 520);
         this.btnCancel.Name = "btnCancel";
         this.btnCancel.Size = new System.Drawing.Size(75, 23);
         this.btnCancel.TabIndex = 8;
         this.btnCancel.Text = "Cancel";
         this.btnCancel.UseVisualStyleBackColor = true;
         this.btnCancel.Visible = false;
         this.btnCancel.Click += new System.EventHandler(this.btnCancel_Click);
         // 
         // pnlControls
         // 
         this.pnlControls.BackColor = System.Drawing.SystemColors.Window;
         this.pnlControls.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
         this.pnlControls.Controls.Add(this.btnCancel);
         this.pnlControls.Controls.Add(this.btnAccept);
         this.pnlControls.Controls.Add(this.lvwControls);
         this.pnlControls.Dock = System.Windows.Forms.DockStyle.Fill;
         this.pnlControls.Location = new System.Drawing.Point(0, 0);
         this.pnlControls.Name = "pnlControls";
         this.pnlControls.Padding = new System.Windows.Forms.Padding(6);
         this.pnlControls.Size = new System.Drawing.Size(512, 557);
         this.pnlControls.TabIndex = 9;
         // 
         // QUiControlPickerForm
         // 
         this.AcceptButton = this.btnAccept;
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.CancelButton = this.btnCancel;
         this.ClientSize = new System.Drawing.Size(512, 557);
         this.Controls.Add(this.pnlControls);
         this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow;
         this.Name = "QUiControlPickerForm";
         this.Text = "  控件选择器";
         this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.QUiControlPicker_FormClosing);
         this.pnlControls.ResumeLayout(false);
         this.ResumeLayout(false);

      }

      #endregion

      private System.Windows.Forms.ListView lvwControls;
      private System.Windows.Forms.ColumnHeader chdControlName;
      private System.Windows.Forms.ColumnHeader chdControlNote;
      private System.Windows.Forms.Button btnAccept;
      private System.Windows.Forms.Button btnCancel;
      private System.Windows.Forms.ImageList imlMain;
      private System.Windows.Forms.Panel pnlControls;
   }
}