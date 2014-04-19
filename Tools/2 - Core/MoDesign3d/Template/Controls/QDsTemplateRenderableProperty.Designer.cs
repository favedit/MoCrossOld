namespace MO.Design3d.Template.Controls
{
   partial class QDsTemplateRenderableProperty
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
         if (disposing && (components != null)) {
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
         this.qdrOptionGround = new MO.Design3d.Controls.QDsFlag();
         this.qdrOptionSelect = new MO.Design3d.Controls.QDsFlag();
         this.txtModelName = new System.Windows.Forms.TextBox();
         this.labModelName = new System.Windows.Forms.Label();
         this.txtGeometryName = new System.Windows.Forms.TextBox();
         this.labGeometryName = new System.Windows.Forms.Label();
         this.txtMaterialName = new System.Windows.Forms.TextBox();
         this.labMaterialName = new System.Windows.Forms.Label();
         this.labOptionSelect = new System.Windows.Forms.Label();
         this.labOptionGround = new System.Windows.Forms.Label();
         this.qdrOptionVisible = new MO.Design3d.Controls.QDsFlag();
         this.labOptionVisible = new System.Windows.Forms.Label();
         this.SuspendLayout();
         // 
         // qdrOptionGround
         // 
         this.qdrOptionGround.Location = new System.Drawing.Point(98, 144);
         this.qdrOptionGround.Name = "qdrOptionGround";
         this.qdrOptionGround.Size = new System.Drawing.Size(140, 23);
         this.qdrOptionGround.TabIndex = 0;
         // 
         // qdrOptionSelect
         // 
         this.qdrOptionSelect.Location = new System.Drawing.Point(98, 86);
         this.qdrOptionSelect.Name = "qdrOptionSelect";
         this.qdrOptionSelect.Size = new System.Drawing.Size(140, 23);
         this.qdrOptionSelect.TabIndex = 1;
         // 
         // txtModelName
         // 
         this.txtModelName.BackColor = System.Drawing.SystemColors.Window;
         this.txtModelName.ForeColor = System.Drawing.SystemColors.GrayText;
         this.txtModelName.Location = new System.Drawing.Point(98, 5);
         this.txtModelName.Name = "txtModelName";
         this.txtModelName.ReadOnly = true;
         this.txtModelName.Size = new System.Drawing.Size(357, 21);
         this.txtModelName.TabIndex = 2;
         // 
         // labModelName
         // 
         this.labModelName.AutoSize = true;
         this.labModelName.Location = new System.Drawing.Point(8, 8);
         this.labModelName.Name = "labModelName";
         this.labModelName.Size = new System.Drawing.Size(53, 12);
         this.labModelName.TabIndex = 3;
         this.labModelName.Text = "模型名称";
         // 
         // txtGeometryName
         // 
         this.txtGeometryName.BackColor = System.Drawing.SystemColors.Window;
         this.txtGeometryName.ForeColor = System.Drawing.SystemColors.GrayText;
         this.txtGeometryName.Location = new System.Drawing.Point(98, 32);
         this.txtGeometryName.Name = "txtGeometryName";
         this.txtGeometryName.ReadOnly = true;
         this.txtGeometryName.Size = new System.Drawing.Size(357, 21);
         this.txtGeometryName.TabIndex = 2;
         // 
         // labGeometryName
         // 
         this.labGeometryName.AutoSize = true;
         this.labGeometryName.Location = new System.Drawing.Point(8, 35);
         this.labGeometryName.Name = "labGeometryName";
         this.labGeometryName.Size = new System.Drawing.Size(65, 12);
         this.labGeometryName.TabIndex = 3;
         this.labGeometryName.Text = "几何体名称";
         // 
         // txtMaterialName
         // 
         this.txtMaterialName.BackColor = System.Drawing.SystemColors.Window;
         this.txtMaterialName.ForeColor = System.Drawing.SystemColors.GrayText;
         this.txtMaterialName.Location = new System.Drawing.Point(98, 59);
         this.txtMaterialName.Name = "txtMaterialName";
         this.txtMaterialName.ReadOnly = true;
         this.txtMaterialName.Size = new System.Drawing.Size(357, 21);
         this.txtMaterialName.TabIndex = 2;
         // 
         // labMaterialName
         // 
         this.labMaterialName.AutoSize = true;
         this.labMaterialName.Location = new System.Drawing.Point(8, 62);
         this.labMaterialName.Name = "labMaterialName";
         this.labMaterialName.Size = new System.Drawing.Size(53, 12);
         this.labMaterialName.TabIndex = 3;
         this.labMaterialName.Text = "材质名称";
         // 
         // labOptionSelect
         // 
         this.labOptionSelect.AutoSize = true;
         this.labOptionSelect.Location = new System.Drawing.Point(8, 91);
         this.labOptionSelect.Name = "labOptionSelect";
         this.labOptionSelect.Size = new System.Drawing.Size(53, 12);
         this.labOptionSelect.TabIndex = 3;
         this.labOptionSelect.Text = "选择设置";
         // 
         // labOptionGround
         // 
         this.labOptionGround.AutoSize = true;
         this.labOptionGround.Location = new System.Drawing.Point(8, 149);
         this.labOptionGround.Name = "labOptionGround";
         this.labOptionGround.Size = new System.Drawing.Size(53, 12);
         this.labOptionGround.TabIndex = 3;
         this.labOptionGround.Text = "地面设置";
         // 
         // qdrOptionVisible
         // 
         this.qdrOptionVisible.Location = new System.Drawing.Point(98, 115);
         this.qdrOptionVisible.Name = "qdrOptionVisible";
         this.qdrOptionVisible.Size = new System.Drawing.Size(140, 23);
         this.qdrOptionVisible.TabIndex = 1;
         // 
         // labOptionVisible
         // 
         this.labOptionVisible.AutoSize = true;
         this.labOptionVisible.Location = new System.Drawing.Point(8, 120);
         this.labOptionVisible.Name = "labOptionVisible";
         this.labOptionVisible.Size = new System.Drawing.Size(53, 12);
         this.labOptionVisible.TabIndex = 3;
         this.labOptionVisible.Text = "选择设置";
         // 
         // QDrTemplateRenderableProperty
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.Controls.Add(this.labOptionGround);
         this.Controls.Add(this.labOptionVisible);
         this.Controls.Add(this.labOptionSelect);
         this.Controls.Add(this.labMaterialName);
         this.Controls.Add(this.labGeometryName);
         this.Controls.Add(this.labModelName);
         this.Controls.Add(this.txtMaterialName);
         this.Controls.Add(this.txtGeometryName);
         this.Controls.Add(this.txtModelName);
         this.Controls.Add(this.qdrOptionVisible);
         this.Controls.Add(this.qdrOptionSelect);
         this.Controls.Add(this.qdrOptionGround);
         this.Name = "QDrTemplateRenderableProperty";
         this.Size = new System.Drawing.Size(793, 417);
         this.ResumeLayout(false);
         this.PerformLayout();

      }

      #endregion

      private MO.Design3d.Controls.QDsFlag qdrOptionGround;
      private System.Windows.Forms.TextBox txtModelName;
      private System.Windows.Forms.Label labModelName;
      private System.Windows.Forms.TextBox txtGeometryName;
      private System.Windows.Forms.Label labGeometryName;
      private System.Windows.Forms.TextBox txtMaterialName;
      private System.Windows.Forms.Label labMaterialName;
      private System.Windows.Forms.Label labOptionSelect;
      private System.Windows.Forms.Label labOptionGround;
      private MO.Design3d.Controls.QDsFlag qdrOptionSelect;
      private MO.Design3d.Controls.QDsFlag qdrOptionVisible;
      private System.Windows.Forms.Label labOptionVisible;
   }
}
