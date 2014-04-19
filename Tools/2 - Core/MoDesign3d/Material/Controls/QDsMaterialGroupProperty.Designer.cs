namespace MO.Design3d.Material.Controls
{
   partial class QDsMaterialGroupProperty
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
         this.cboEffectName = new System.Windows.Forms.ComboBox();
         this.labEffectName = new System.Windows.Forms.Label();
         this.txtName = new System.Windows.Forms.TextBox();
         this.labName = new System.Windows.Forms.Label();
         this.labOptionDouble = new System.Windows.Forms.Label();
         this.labOptionOpacity = new System.Windows.Forms.Label();
         this.labOptionShadow = new System.Windows.Forms.Label();
         this.labOptionShadowSelf = new System.Windows.Forms.Label();
         this.labOptionAlpha = new System.Windows.Forms.Label();
         this.labOptionTransmittance = new System.Windows.Forms.Label();
         this.labOptionDepth = new System.Windows.Forms.Label();
         this.labSortLevel = new System.Windows.Forms.Label();
         this.labTransformName = new System.Windows.Forms.Label();
         this.cboTransformName = new System.Windows.Forms.ComboBox();
         this.labOptionMerge = new System.Windows.Forms.Label();
         this.cboOptionCompare = new System.Windows.Forms.ComboBox();
         this.qdrOptionTransmittance = new MO.Design3d.Controls.QDsFlag();
         this.qdrOptionShadowSelf = new MO.Design3d.Controls.QDsFlag();
         this.qdrOptionShadow = new MO.Design3d.Controls.QDsFlag();
         this.qdrOptionOpacity = new MO.Design3d.Controls.QDsFlag();
         this.qdrOptionMerge = new MO.Design3d.Controls.QDsFlag();
         this.qdrOptionDepth = new MO.Design3d.Controls.QDsFlag();
         this.qdrOptionAlpha = new MO.Design3d.Controls.QDsFlag();
         this.qdrOptionDouble = new MO.Design3d.Controls.QDsFlag();
         this.labOptionDynamic = new System.Windows.Forms.Label();
         this.qdrOptionDynamic = new MO.Design3d.Controls.QDsFlag();
         this.labOptionLight = new System.Windows.Forms.Label();
         this.qdrOptionLight = new MO.Design3d.Controls.QDsFlag();
         this.labOptionCompare = new System.Windows.Forms.Label();
         this.labOptionSort = new System.Windows.Forms.Label();
         this.qdrOptionSort = new MO.Design3d.Controls.QDsFlag();
         this.nudSortLevel = new System.Windows.Forms.NumericUpDown();
         ((System.ComponentModel.ISupportInitialize)(this.nudSortLevel)).BeginInit();
         this.SuspendLayout();
         // 
         // cboEffectName
         // 
         this.cboEffectName.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
         this.cboEffectName.FormattingEnabled = true;
         this.cboEffectName.Items.AddRange(new object[] {
            "automatic",
            "color",
            "parallax",
            "skin",
            "special",
            "layer",
            "water",
            "ocean",
            "skeleton.4",
            "fur",
            "fur.skeleton.4"});
         this.cboEffectName.Location = new System.Drawing.Point(376, 6);
         this.cboEffectName.MaxDropDownItems = 12;
         this.cboEffectName.Name = "cboEffectName";
         this.cboEffectName.Size = new System.Drawing.Size(160, 20);
         this.cboEffectName.TabIndex = 30;
         // 
         // labEffectName
         // 
         this.labEffectName.AutoSize = true;
         this.labEffectName.Location = new System.Drawing.Point(315, 10);
         this.labEffectName.Name = "labEffectName";
         this.labEffectName.Size = new System.Drawing.Size(53, 12);
         this.labEffectName.TabIndex = 24;
         this.labEffectName.Text = "效果名称";
         // 
         // txtName
         // 
         this.txtName.BackColor = System.Drawing.SystemColors.Window;
         this.txtName.ForeColor = System.Drawing.SystemColors.GrayText;
         this.txtName.Location = new System.Drawing.Point(76, 7);
         this.txtName.Name = "txtName";
         this.txtName.ReadOnly = true;
         this.txtName.Size = new System.Drawing.Size(219, 21);
         this.txtName.TabIndex = 22;
         // 
         // labName
         // 
         this.labName.AutoSize = true;
         this.labName.Location = new System.Drawing.Point(21, 10);
         this.labName.Name = "labName";
         this.labName.Size = new System.Drawing.Size(41, 12);
         this.labName.TabIndex = 21;
         this.labName.Text = "组名称";
         // 
         // labOptionDouble
         // 
         this.labOptionDouble.AutoSize = true;
         this.labOptionDouble.Location = new System.Drawing.Point(317, 69);
         this.labOptionDouble.Name = "labOptionDouble";
         this.labOptionDouble.Size = new System.Drawing.Size(53, 12);
         this.labOptionDouble.TabIndex = 23;
         this.labOptionDouble.Text = "双面绘制";
         // 
         // labOptionOpacity
         // 
         this.labOptionOpacity.AutoSize = true;
         this.labOptionOpacity.Location = new System.Drawing.Point(575, 127);
         this.labOptionOpacity.Name = "labOptionOpacity";
         this.labOptionOpacity.Size = new System.Drawing.Size(53, 12);
         this.labOptionOpacity.TabIndex = 23;
         this.labOptionOpacity.Text = "不透明度";
         // 
         // labOptionShadow
         // 
         this.labOptionShadow.AutoSize = true;
         this.labOptionShadow.Location = new System.Drawing.Point(317, 98);
         this.labOptionShadow.Name = "labOptionShadow";
         this.labOptionShadow.Size = new System.Drawing.Size(53, 12);
         this.labOptionShadow.TabIndex = 23;
         this.labOptionShadow.Text = "光源阴影";
         // 
         // labOptionShadowSelf
         // 
         this.labOptionShadowSelf.AutoSize = true;
         this.labOptionShadowSelf.Location = new System.Drawing.Point(315, 127);
         this.labOptionShadowSelf.Name = "labOptionShadowSelf";
         this.labOptionShadowSelf.Size = new System.Drawing.Size(53, 12);
         this.labOptionShadowSelf.TabIndex = 23;
         this.labOptionShadowSelf.Text = "自身阴影";
         // 
         // labOptionAlpha
         // 
         this.labOptionAlpha.AutoSize = true;
         this.labOptionAlpha.Location = new System.Drawing.Point(17, 127);
         this.labOptionAlpha.Name = "labOptionAlpha";
         this.labOptionAlpha.Size = new System.Drawing.Size(53, 12);
         this.labOptionAlpha.TabIndex = 23;
         this.labOptionAlpha.Text = "纹理透明";
         // 
         // labOptionTransmittance
         // 
         this.labOptionTransmittance.AutoSize = true;
         this.labOptionTransmittance.Location = new System.Drawing.Point(575, 98);
         this.labOptionTransmittance.Name = "labOptionTransmittance";
         this.labOptionTransmittance.Size = new System.Drawing.Size(53, 12);
         this.labOptionTransmittance.TabIndex = 23;
         this.labOptionTransmittance.Text = "光源透射";
         // 
         // labOptionDepth
         // 
         this.labOptionDepth.AutoSize = true;
         this.labOptionDepth.Location = new System.Drawing.Point(317, 40);
         this.labOptionDepth.Name = "labOptionDepth";
         this.labOptionDepth.Size = new System.Drawing.Size(53, 12);
         this.labOptionDepth.TabIndex = 23;
         this.labOptionDepth.Text = "深度写入";
         // 
         // labSortLevel
         // 
         this.labSortLevel.AutoSize = true;
         this.labSortLevel.Location = new System.Drawing.Point(220, 98);
         this.labSortLevel.Name = "labSortLevel";
         this.labSortLevel.Size = new System.Drawing.Size(29, 12);
         this.labSortLevel.TabIndex = 23;
         this.labSortLevel.Text = "级别";
         // 
         // labTransformName
         // 
         this.labTransformName.AutoSize = true;
         this.labTransformName.Location = new System.Drawing.Point(575, 10);
         this.labTransformName.Name = "labTransformName";
         this.labTransformName.Size = new System.Drawing.Size(53, 12);
         this.labTransformName.TabIndex = 24;
         this.labTransformName.Text = "变换名称";
         // 
         // cboTransformName
         // 
         this.cboTransformName.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
         this.cboTransformName.FormattingEnabled = true;
         this.cboTransformName.Items.AddRange(new object[] {
            "automatic",
            "flat",
            "billboard"});
         this.cboTransformName.Location = new System.Drawing.Point(634, 6);
         this.cboTransformName.MaxDropDownItems = 12;
         this.cboTransformName.Name = "cboTransformName";
         this.cboTransformName.Size = new System.Drawing.Size(160, 20);
         this.cboTransformName.TabIndex = 30;
         // 
         // labOptionMerge
         // 
         this.labOptionMerge.AutoSize = true;
         this.labOptionMerge.Location = new System.Drawing.Point(17, 69);
         this.labOptionMerge.Name = "labOptionMerge";
         this.labOptionMerge.Size = new System.Drawing.Size(53, 12);
         this.labOptionMerge.TabIndex = 23;
         this.labOptionMerge.Text = "网格合并";
         // 
         // cboOptionCompare
         // 
         this.cboOptionCompare.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
         this.cboOptionCompare.FormattingEnabled = true;
         this.cboOptionCompare.Items.AddRange(new object[] {
            "always",
            "greater",
            "greaterEqual",
            "less",
            "lessEqual",
            "never",
            "notEqual"});
         this.cboOptionCompare.Location = new System.Drawing.Point(634, 37);
         this.cboOptionCompare.MaxDropDownItems = 12;
         this.cboOptionCompare.Name = "cboOptionCompare";
         this.cboOptionCompare.Size = new System.Drawing.Size(160, 20);
         this.cboOptionCompare.TabIndex = 30;
         // 
         // qdrOptionTransmittance
         // 
         this.qdrOptionTransmittance.Location = new System.Drawing.Point(634, 92);
         this.qdrOptionTransmittance.Name = "qdrOptionTransmittance";
         this.qdrOptionTransmittance.Size = new System.Drawing.Size(140, 23);
         this.qdrOptionTransmittance.TabIndex = 76;
         // 
         // qdrOptionShadowSelf
         // 
         this.qdrOptionShadowSelf.Location = new System.Drawing.Point(376, 121);
         this.qdrOptionShadowSelf.Name = "qdrOptionShadowSelf";
         this.qdrOptionShadowSelf.Size = new System.Drawing.Size(140, 23);
         this.qdrOptionShadowSelf.TabIndex = 76;
         // 
         // qdrOptionShadow
         // 
         this.qdrOptionShadow.Location = new System.Drawing.Point(376, 92);
         this.qdrOptionShadow.Name = "qdrOptionShadow";
         this.qdrOptionShadow.Size = new System.Drawing.Size(140, 23);
         this.qdrOptionShadow.TabIndex = 76;
         // 
         // qdrOptionOpacity
         // 
         this.qdrOptionOpacity.Location = new System.Drawing.Point(634, 121);
         this.qdrOptionOpacity.Name = "qdrOptionOpacity";
         this.qdrOptionOpacity.Size = new System.Drawing.Size(140, 23);
         this.qdrOptionOpacity.TabIndex = 76;
         // 
         // qdrOptionMerge
         // 
         this.qdrOptionMerge.Location = new System.Drawing.Point(76, 63);
         this.qdrOptionMerge.Name = "qdrOptionMerge";
         this.qdrOptionMerge.Size = new System.Drawing.Size(140, 23);
         this.qdrOptionMerge.TabIndex = 76;
         // 
         // qdrOptionDepth
         // 
         this.qdrOptionDepth.Location = new System.Drawing.Point(376, 34);
         this.qdrOptionDepth.Name = "qdrOptionDepth";
         this.qdrOptionDepth.Size = new System.Drawing.Size(140, 23);
         this.qdrOptionDepth.TabIndex = 76;
         // 
         // qdrOptionAlpha
         // 
         this.qdrOptionAlpha.Location = new System.Drawing.Point(76, 121);
         this.qdrOptionAlpha.Name = "qdrOptionAlpha";
         this.qdrOptionAlpha.Size = new System.Drawing.Size(140, 23);
         this.qdrOptionAlpha.TabIndex = 76;
         // 
         // qdrOptionDouble
         // 
         this.qdrOptionDouble.Location = new System.Drawing.Point(376, 63);
         this.qdrOptionDouble.Name = "qdrOptionDouble";
         this.qdrOptionDouble.Size = new System.Drawing.Size(140, 23);
         this.qdrOptionDouble.TabIndex = 76;
         // 
         // labOptionDynamic
         // 
         this.labOptionDynamic.AutoSize = true;
         this.labOptionDynamic.Location = new System.Drawing.Point(575, 69);
         this.labOptionDynamic.Name = "labOptionDynamic";
         this.labOptionDynamic.Size = new System.Drawing.Size(53, 12);
         this.labOptionDynamic.TabIndex = 23;
         this.labOptionDynamic.Text = "动态材质";
         // 
         // qdrOptionDynamic
         // 
         this.qdrOptionDynamic.Location = new System.Drawing.Point(634, 63);
         this.qdrOptionDynamic.Name = "qdrOptionDynamic";
         this.qdrOptionDynamic.Size = new System.Drawing.Size(140, 23);
         this.qdrOptionDynamic.TabIndex = 76;
         // 
         // labOptionLight
         // 
         this.labOptionLight.AutoSize = true;
         this.labOptionLight.Location = new System.Drawing.Point(17, 40);
         this.labOptionLight.Name = "labOptionLight";
         this.labOptionLight.Size = new System.Drawing.Size(53, 12);
         this.labOptionLight.TabIndex = 23;
         this.labOptionLight.Text = "光源影响";
         // 
         // qdrOptionLight
         // 
         this.qdrOptionLight.Location = new System.Drawing.Point(76, 34);
         this.qdrOptionLight.Name = "qdrOptionLight";
         this.qdrOptionLight.Size = new System.Drawing.Size(140, 23);
         this.qdrOptionLight.TabIndex = 76;
         // 
         // labOptionCompare
         // 
         this.labOptionCompare.AutoSize = true;
         this.labOptionCompare.Location = new System.Drawing.Point(575, 40);
         this.labOptionCompare.Name = "labOptionCompare";
         this.labOptionCompare.Size = new System.Drawing.Size(53, 12);
         this.labOptionCompare.TabIndex = 23;
         this.labOptionCompare.Text = "深度比较";
         // 
         // labOptionSort
         // 
         this.labOptionSort.AutoSize = true;
         this.labOptionSort.Location = new System.Drawing.Point(17, 98);
         this.labOptionSort.Name = "labOptionSort";
         this.labOptionSort.Size = new System.Drawing.Size(53, 12);
         this.labOptionSort.TabIndex = 23;
         this.labOptionSort.Text = "空间排序";
         // 
         // qdrOptionSort
         // 
         this.qdrOptionSort.Location = new System.Drawing.Point(76, 92);
         this.qdrOptionSort.Name = "qdrOptionSort";
         this.qdrOptionSort.Size = new System.Drawing.Size(140, 23);
         this.qdrOptionSort.TabIndex = 76;
         // 
         // nudSortLevel
         // 
         this.nudSortLevel.Location = new System.Drawing.Point(255, 94);
         this.nudSortLevel.Name = "nudSortLevel";
         this.nudSortLevel.Size = new System.Drawing.Size(40, 21);
         this.nudSortLevel.TabIndex = 78;
         this.nudSortLevel.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
         // 
         // QDrMaterialGroupProperty
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.BackColor = System.Drawing.SystemColors.Control;
         this.Controls.Add(this.nudSortLevel);
         this.Controls.Add(this.qdrOptionTransmittance);
         this.Controls.Add(this.qdrOptionLight);
         this.Controls.Add(this.qdrOptionDynamic);
         this.Controls.Add(this.qdrOptionShadowSelf);
         this.Controls.Add(this.qdrOptionShadow);
         this.Controls.Add(this.qdrOptionOpacity);
         this.Controls.Add(this.qdrOptionMerge);
         this.Controls.Add(this.qdrOptionDepth);
         this.Controls.Add(this.qdrOptionSort);
         this.Controls.Add(this.qdrOptionAlpha);
         this.Controls.Add(this.qdrOptionDouble);
         this.Controls.Add(this.cboOptionCompare);
         this.Controls.Add(this.cboTransformName);
         this.Controls.Add(this.cboEffectName);
         this.Controls.Add(this.labOptionLight);
         this.Controls.Add(this.labOptionTransmittance);
         this.Controls.Add(this.labOptionDynamic);
         this.Controls.Add(this.labTransformName);
         this.Controls.Add(this.labOptionShadowSelf);
         this.Controls.Add(this.labOptionMerge);
         this.Controls.Add(this.labEffectName);
         this.Controls.Add(this.labOptionCompare);
         this.Controls.Add(this.labOptionDepth);
         this.Controls.Add(this.labOptionSort);
         this.Controls.Add(this.labOptionShadow);
         this.Controls.Add(this.labOptionAlpha);
         this.Controls.Add(this.labSortLevel);
         this.Controls.Add(this.labOptionOpacity);
         this.Controls.Add(this.labOptionDouble);
         this.Controls.Add(this.txtName);
         this.Controls.Add(this.labName);
         this.Name = "QDrMaterialGroupProperty";
         this.Size = new System.Drawing.Size(944, 153);
         ((System.ComponentModel.ISupportInitialize)(this.nudSortLevel)).EndInit();
         this.ResumeLayout(false);
         this.PerformLayout();

      }

      #endregion

      private System.Windows.Forms.ComboBox cboEffectName;
      private System.Windows.Forms.Label labEffectName;
      private System.Windows.Forms.TextBox txtName;
      private System.Windows.Forms.Label labName;
      private System.Windows.Forms.Label labOptionDouble;
      private MO.Design3d.Controls.QDsFlag qdrOptionDouble;
      private MO.Design3d.Controls.QDsFlag qdrOptionOpacity;
      private MO.Design3d.Controls.QDsFlag qdrOptionShadow;
      private System.Windows.Forms.Label labOptionOpacity;
      private System.Windows.Forms.Label labOptionShadow;
      private System.Windows.Forms.Label labOptionShadowSelf;
      private MO.Design3d.Controls.QDsFlag qdrOptionShadowSelf;
      private System.Windows.Forms.Label labOptionAlpha;
      private MO.Design3d.Controls.QDsFlag qdrOptionAlpha;
      private System.Windows.Forms.Label labOptionTransmittance;
      private MO.Design3d.Controls.QDsFlag qdrOptionTransmittance;
      private System.Windows.Forms.Label labOptionDepth;
      private MO.Design3d.Controls.QDsFlag qdrOptionDepth;
      private System.Windows.Forms.Label labSortLevel;
      private System.Windows.Forms.Label labTransformName;
      private System.Windows.Forms.ComboBox cboTransformName;
      private System.Windows.Forms.Label labOptionMerge;
      private MO.Design3d.Controls.QDsFlag qdrOptionMerge;
      private System.Windows.Forms.ComboBox cboOptionCompare;
      private System.Windows.Forms.Label labOptionDynamic;
      private MO.Design3d.Controls.QDsFlag qdrOptionDynamic;
      private System.Windows.Forms.Label labOptionLight;
      private MO.Design3d.Controls.QDsFlag qdrOptionLight;
      private System.Windows.Forms.Label labOptionCompare;
      private System.Windows.Forms.Label labOptionSort;
      private MO.Design3d.Controls.QDsFlag qdrOptionSort;
      private System.Windows.Forms.NumericUpDown nudSortLevel;
   }
}
