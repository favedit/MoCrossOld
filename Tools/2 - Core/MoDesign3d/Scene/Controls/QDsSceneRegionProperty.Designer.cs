namespace MO.Design3d.Scene.Controls
{
   partial class QDsSceneRegionProperty
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
         this.qdcFogColor = new MO.Core.Forms.Controls.QFloatColor4();
         this.qdcColor = new MO.Core.Forms.Controls.QFloatColor4();
         this.labFogColor = new System.Windows.Forms.Label();
         this.labColor = new System.Windows.Forms.Label();
         this.txtFogFar = new System.Windows.Forms.TextBox();
         this.txtFogNear = new System.Windows.Forms.TextBox();
         this.labFogFar = new System.Windows.Forms.Label();
         this.labFogNear = new System.Windows.Forms.Label();
         this.labFogAttenuation = new System.Windows.Forms.Label();
         this.txtFogAttenuation = new System.Windows.Forms.TextBox();
         this.labFogRate = new System.Windows.Forms.Label();
         this.txtFogRate = new System.Windows.Forms.TextBox();
         this.SuspendLayout();
         // 
         // qdcFogColor
         // 
         this.qdcFogColor.Location = new System.Drawing.Point(90, 30);
         this.qdcFogColor.Name = "qdcFogColor";
         this.qdcFogColor.Size = new System.Drawing.Size(200, 21);
         this.qdcFogColor.TabIndex = 102;
         // 
         // qdcColor
         // 
         this.qdcColor.Location = new System.Drawing.Point(90, 3);
         this.qdcColor.Name = "qdcColor";
         this.qdcColor.Size = new System.Drawing.Size(200, 21);
         this.qdcColor.TabIndex = 101;
         // 
         // labFogColor
         // 
         this.labFogColor.AutoSize = true;
         this.labFogColor.Location = new System.Drawing.Point(12, 34);
         this.labFogColor.Name = "labFogColor";
         this.labFogColor.Size = new System.Drawing.Size(53, 12);
         this.labFogColor.TabIndex = 100;
         this.labFogColor.Text = "雾化颜色";
         // 
         // labColor
         // 
         this.labColor.AutoSize = true;
         this.labColor.Location = new System.Drawing.Point(12, 7);
         this.labColor.Name = "labColor";
         this.labColor.Size = new System.Drawing.Size(29, 12);
         this.labColor.TabIndex = 99;
         this.labColor.Text = "颜色";
         // 
         // txtFogFar
         // 
         this.txtFogFar.Location = new System.Drawing.Point(90, 84);
         this.txtFogFar.Name = "txtFogFar";
         this.txtFogFar.Size = new System.Drawing.Size(89, 21);
         this.txtFogFar.TabIndex = 113;
         // 
         // txtFogNear
         // 
         this.txtFogNear.Location = new System.Drawing.Point(90, 57);
         this.txtFogNear.Name = "txtFogNear";
         this.txtFogNear.Size = new System.Drawing.Size(89, 21);
         this.txtFogNear.TabIndex = 114;
         // 
         // labFogFar
         // 
         this.labFogFar.AutoSize = true;
         this.labFogFar.Location = new System.Drawing.Point(12, 87);
         this.labFogFar.Name = "labFogFar";
         this.labFogFar.Size = new System.Drawing.Size(65, 12);
         this.labFogFar.TabIndex = 111;
         this.labFogFar.Text = "雾化远平面";
         // 
         // labFogNear
         // 
         this.labFogNear.AutoSize = true;
         this.labFogNear.Location = new System.Drawing.Point(12, 60);
         this.labFogNear.Name = "labFogNear";
         this.labFogNear.Size = new System.Drawing.Size(65, 12);
         this.labFogNear.TabIndex = 112;
         this.labFogNear.Text = "雾化近平面";
         // 
         // labFogAttenuation
         // 
         this.labFogAttenuation.AutoSize = true;
         this.labFogAttenuation.Location = new System.Drawing.Point(12, 141);
         this.labFogAttenuation.Name = "labFogAttenuation";
         this.labFogAttenuation.Size = new System.Drawing.Size(53, 12);
         this.labFogAttenuation.TabIndex = 111;
         this.labFogAttenuation.Text = "雾化衰减";
         // 
         // txtFogAttenuation
         // 
         this.txtFogAttenuation.Location = new System.Drawing.Point(90, 138);
         this.txtFogAttenuation.Name = "txtFogAttenuation";
         this.txtFogAttenuation.Size = new System.Drawing.Size(89, 21);
         this.txtFogAttenuation.TabIndex = 113;
         // 
         // labFogRate
         // 
         this.labFogRate.AutoSize = true;
         this.labFogRate.Location = new System.Drawing.Point(12, 114);
         this.labFogRate.Name = "labFogRate";
         this.labFogRate.Size = new System.Drawing.Size(53, 12);
         this.labFogRate.TabIndex = 111;
         this.labFogRate.Text = "雾化比率";
         // 
         // txtFogRate
         // 
         this.txtFogRate.Location = new System.Drawing.Point(90, 111);
         this.txtFogRate.Name = "txtFogRate";
         this.txtFogRate.Size = new System.Drawing.Size(89, 21);
         this.txtFogRate.TabIndex = 113;
         // 
         // QDrSceneRegionProperty
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.Controls.Add(this.txtFogRate);
         this.Controls.Add(this.txtFogAttenuation);
         this.Controls.Add(this.labFogRate);
         this.Controls.Add(this.txtFogFar);
         this.Controls.Add(this.labFogAttenuation);
         this.Controls.Add(this.txtFogNear);
         this.Controls.Add(this.labFogFar);
         this.Controls.Add(this.labFogNear);
         this.Controls.Add(this.qdcFogColor);
         this.Controls.Add(this.qdcColor);
         this.Controls.Add(this.labFogColor);
         this.Controls.Add(this.labColor);
         this.Name = "QDrSceneRegionProperty";
         this.Size = new System.Drawing.Size(612, 284);
         this.ResumeLayout(false);
         this.PerformLayout();

      }

      #endregion

      private MO.Core.Forms.Controls.QFloatColor4 qdcFogColor;
      private MO.Core.Forms.Controls.QFloatColor4 qdcColor;
      private System.Windows.Forms.Label labFogColor;
      private System.Windows.Forms.Label labColor;
      private System.Windows.Forms.TextBox txtFogFar;
      private System.Windows.Forms.TextBox txtFogNear;
      private System.Windows.Forms.Label labFogFar;
      private System.Windows.Forms.Label labFogNear;
      private System.Windows.Forms.Label labFogAttenuation;
      private System.Windows.Forms.TextBox txtFogAttenuation;
      private System.Windows.Forms.Label labFogRate;
      private System.Windows.Forms.TextBox txtFogRate;
   }
}
