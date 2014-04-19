namespace MO.Design3d.Material.Controls
{
   partial class QDsMaterialInfoProperty
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
         this.labEmissive = new System.Windows.Forms.Label();
         this.labDiffuse = new System.Windows.Forms.Label();
         this.labSpecular = new System.Windows.Forms.Label();
         this.labAmbient = new System.Windows.Forms.Label();
         this.txtName = new System.Windows.Forms.TextBox();
         this.labName = new System.Windows.Forms.Label();
         this.colorShow = new System.Windows.Forms.ColorDialog();
         this.labRefractBack = new System.Windows.Forms.Label();
         this.labRefractFront = new System.Windows.Forms.Label();
         this.labOpacity = new System.Windows.Forms.Label();
         this.labReflect = new System.Windows.Forms.Label();
         this.labSpecularView = new System.Windows.Forms.Label();
         this.labDiffuseView = new System.Windows.Forms.Label();
         this.qdcDiffuseView = new MO.Core.Forms.Controls.QFloatColor4();
         this.qdcSpecularView = new MO.Core.Forms.Controls.QFloatColor4();
         this.qdcOpacity = new MO.Core.Forms.Controls.QFloatColor4();
         this.qdcRefractBack = new MO.Core.Forms.Controls.QFloatColor4();
         this.qdcRefractFront = new MO.Core.Forms.Controls.QFloatColor4();
         this.qdcReflect = new MO.Core.Forms.Controls.QFloatColor4();
         this.qdcEmissive = new MO.Core.Forms.Controls.QFloatColor4();
         this.qdcSpecular = new MO.Core.Forms.Controls.QFloatColor4();
         this.qdcDiffuse = new MO.Core.Forms.Controls.QFloatColor4();
         this.qdcAmbient = new MO.Core.Forms.Controls.QFloatColor4();
         this.labOptionAlpha = new System.Windows.Forms.Label();
         this.labAmbientShadow = new System.Windows.Forms.Label();
         this.txtAmbientShadow = new System.Windows.Forms.TextBox();
         this.labDiffuseShadow = new System.Windows.Forms.Label();
         this.txtDiffuseShadow = new System.Windows.Forms.TextBox();
         this.labDiffuseViewShadow = new System.Windows.Forms.Label();
         this.labSpecularShadow = new System.Windows.Forms.Label();
         this.txtDiffuseViewShadow = new System.Windows.Forms.TextBox();
         this.txtSpecularShadow = new System.Windows.Forms.TextBox();
         this.labSpecularViewShadow = new System.Windows.Forms.Label();
         this.txtSpecularViewShadow = new System.Windows.Forms.TextBox();
         this.txtAlphaBase = new System.Windows.Forms.TextBox();
         this.txtAlphaRate = new System.Windows.Forms.TextBox();
         this.labAlphaRate = new System.Windows.Forms.Label();
         this.SuspendLayout();
         // 
         // labEmissive
         // 
         this.labEmissive.AutoSize = true;
         this.labEmissive.Location = new System.Drawing.Point(481, 141);
         this.labEmissive.Name = "labEmissive";
         this.labEmissive.Size = new System.Drawing.Size(53, 12);
         this.labEmissive.TabIndex = 27;
         this.labEmissive.Text = "发光颜色";
         // 
         // labDiffuse
         // 
         this.labDiffuse.AutoSize = true;
         this.labDiffuse.Location = new System.Drawing.Point(18, 63);
         this.labDiffuse.Name = "labDiffuse";
         this.labDiffuse.Size = new System.Drawing.Size(53, 12);
         this.labDiffuse.TabIndex = 26;
         this.labDiffuse.Text = "散射颜色";
         // 
         // labSpecular
         // 
         this.labSpecular.AutoSize = true;
         this.labSpecular.Location = new System.Drawing.Point(481, 33);
         this.labSpecular.Name = "labSpecular";
         this.labSpecular.Size = new System.Drawing.Size(53, 12);
         this.labSpecular.TabIndex = 25;
         this.labSpecular.Text = "高光颜色";
         // 
         // labAmbient
         // 
         this.labAmbient.AutoSize = true;
         this.labAmbient.Location = new System.Drawing.Point(18, 36);
         this.labAmbient.Name = "labAmbient";
         this.labAmbient.Size = new System.Drawing.Size(53, 12);
         this.labAmbient.TabIndex = 23;
         this.labAmbient.Text = "环境颜色";
         // 
         // txtName
         // 
         this.txtName.BackColor = System.Drawing.SystemColors.Window;
         this.txtName.ForeColor = System.Drawing.SystemColors.GrayText;
         this.txtName.Location = new System.Drawing.Point(101, 6);
         this.txtName.Name = "txtName";
         this.txtName.ReadOnly = true;
         this.txtName.Size = new System.Drawing.Size(335, 21);
         this.txtName.TabIndex = 22;
         // 
         // labName
         // 
         this.labName.AutoSize = true;
         this.labName.Location = new System.Drawing.Point(18, 9);
         this.labName.Name = "labName";
         this.labName.Size = new System.Drawing.Size(53, 12);
         this.labName.TabIndex = 21;
         this.labName.Text = "材质名称";
         // 
         // labRefractBack
         // 
         this.labRefractBack.AutoSize = true;
         this.labRefractBack.Location = new System.Drawing.Point(18, 145);
         this.labRefractBack.Name = "labRefractBack";
         this.labRefractBack.Size = new System.Drawing.Size(65, 12);
         this.labRefractBack.TabIndex = 54;
         this.labRefractBack.Text = "后折射颜色";
         // 
         // labRefractFront
         // 
         this.labRefractFront.AutoSize = true;
         this.labRefractFront.Location = new System.Drawing.Point(18, 117);
         this.labRefractFront.Name = "labRefractFront";
         this.labRefractFront.Size = new System.Drawing.Size(65, 12);
         this.labRefractFront.TabIndex = 53;
         this.labRefractFront.Text = "前折射颜色";
         // 
         // labOpacity
         // 
         this.labOpacity.AutoSize = true;
         this.labOpacity.Location = new System.Drawing.Point(481, 117);
         this.labOpacity.Name = "labOpacity";
         this.labOpacity.Size = new System.Drawing.Size(65, 12);
         this.labOpacity.TabIndex = 52;
         this.labOpacity.Text = "不透明颜色";
         // 
         // labReflect
         // 
         this.labReflect.AutoSize = true;
         this.labReflect.Location = new System.Drawing.Point(481, 89);
         this.labReflect.Name = "labReflect";
         this.labReflect.Size = new System.Drawing.Size(53, 12);
         this.labReflect.TabIndex = 51;
         this.labReflect.Text = "反射颜色";
         // 
         // labSpecularView
         // 
         this.labSpecularView.AutoSize = true;
         this.labSpecularView.Location = new System.Drawing.Point(481, 60);
         this.labSpecularView.Name = "labSpecularView";
         this.labSpecularView.Size = new System.Drawing.Size(77, 12);
         this.labSpecularView.TabIndex = 62;
         this.labSpecularView.Text = "高光视角颜色";
         // 
         // labDiffuseView
         // 
         this.labDiffuseView.AutoSize = true;
         this.labDiffuseView.Location = new System.Drawing.Point(18, 90);
         this.labDiffuseView.Name = "labDiffuseView";
         this.labDiffuseView.Size = new System.Drawing.Size(77, 12);
         this.labDiffuseView.TabIndex = 69;
         this.labDiffuseView.Text = "散射视角颜色";
         // 
         // qdcDiffuseView
         // 
         this.qdcDiffuseView.Location = new System.Drawing.Point(101, 84);
         this.qdcDiffuseView.Name = "qdcDiffuseView";
         this.qdcDiffuseView.Size = new System.Drawing.Size(170, 21);
         this.qdcDiffuseView.TabIndex = 70;
         // 
         // qdcSpecularView
         // 
         this.qdcSpecularView.Location = new System.Drawing.Point(564, 54);
         this.qdcSpecularView.Name = "qdcSpecularView";
         this.qdcSpecularView.Size = new System.Drawing.Size(170, 21);
         this.qdcSpecularView.TabIndex = 68;
         // 
         // qdcOpacity
         // 
         this.qdcOpacity.Location = new System.Drawing.Point(564, 109);
         this.qdcOpacity.Name = "qdcOpacity";
         this.qdcOpacity.Size = new System.Drawing.Size(170, 21);
         this.qdcOpacity.TabIndex = 58;
         // 
         // qdcRefractBack
         // 
         this.qdcRefractBack.Location = new System.Drawing.Point(101, 138);
         this.qdcRefractBack.Name = "qdcRefractBack";
         this.qdcRefractBack.Size = new System.Drawing.Size(170, 21);
         this.qdcRefractBack.TabIndex = 57;
         // 
         // qdcRefractFront
         // 
         this.qdcRefractFront.Location = new System.Drawing.Point(101, 111);
         this.qdcRefractFront.Name = "qdcRefractFront";
         this.qdcRefractFront.Size = new System.Drawing.Size(170, 21);
         this.qdcRefractFront.TabIndex = 56;
         // 
         // qdcReflect
         // 
         this.qdcReflect.Location = new System.Drawing.Point(564, 82);
         this.qdcReflect.Name = "qdcReflect";
         this.qdcReflect.Size = new System.Drawing.Size(170, 21);
         this.qdcReflect.TabIndex = 55;
         // 
         // qdcEmissive
         // 
         this.qdcEmissive.Location = new System.Drawing.Point(564, 27);
         this.qdcEmissive.Name = "qdcEmissive";
         this.qdcEmissive.Size = new System.Drawing.Size(170, 21);
         this.qdcEmissive.TabIndex = 44;
         // 
         // qdcSpecular
         // 
         this.qdcSpecular.Location = new System.Drawing.Point(564, 136);
         this.qdcSpecular.Name = "qdcSpecular";
         this.qdcSpecular.Size = new System.Drawing.Size(170, 21);
         this.qdcSpecular.TabIndex = 43;
         // 
         // qdcDiffuse
         // 
         this.qdcDiffuse.Location = new System.Drawing.Point(101, 57);
         this.qdcDiffuse.Name = "qdcDiffuse";
         this.qdcDiffuse.Size = new System.Drawing.Size(170, 21);
         this.qdcDiffuse.TabIndex = 42;
         // 
         // qdcAmbient
         // 
         this.qdcAmbient.Location = new System.Drawing.Point(101, 30);
         this.qdcAmbient.Name = "qdcAmbient";
         this.qdcAmbient.Size = new System.Drawing.Size(170, 21);
         this.qdcAmbient.TabIndex = 41;
         // 
         // labOptionAlpha
         // 
         this.labOptionAlpha.AutoSize = true;
         this.labOptionAlpha.Location = new System.Drawing.Point(292, 114);
         this.labOptionAlpha.Name = "labOptionAlpha";
         this.labOptionAlpha.Size = new System.Drawing.Size(53, 12);
         this.labOptionAlpha.TabIndex = 23;
         this.labOptionAlpha.Text = "透明基础";
         // 
         // labAmbientShadow
         // 
         this.labAmbientShadow.AutoSize = true;
         this.labAmbientShadow.Location = new System.Drawing.Point(292, 36);
         this.labAmbientShadow.Name = "labAmbientShadow";
         this.labAmbientShadow.Size = new System.Drawing.Size(53, 12);
         this.labAmbientShadow.TabIndex = 23;
         this.labAmbientShadow.Text = "环境阴影";
         // 
         // txtAmbientShadow
         // 
         this.txtAmbientShadow.Location = new System.Drawing.Point(380, 30);
         this.txtAmbientShadow.Name = "txtAmbientShadow";
         this.txtAmbientShadow.Size = new System.Drawing.Size(56, 21);
         this.txtAmbientShadow.TabIndex = 77;
         // 
         // labDiffuseShadow
         // 
         this.labDiffuseShadow.AutoSize = true;
         this.labDiffuseShadow.Location = new System.Drawing.Point(292, 63);
         this.labDiffuseShadow.Name = "labDiffuseShadow";
         this.labDiffuseShadow.Size = new System.Drawing.Size(53, 12);
         this.labDiffuseShadow.TabIndex = 23;
         this.labDiffuseShadow.Text = "散射阴影";
         // 
         // txtDiffuseShadow
         // 
         this.txtDiffuseShadow.Location = new System.Drawing.Point(380, 57);
         this.txtDiffuseShadow.Name = "txtDiffuseShadow";
         this.txtDiffuseShadow.Size = new System.Drawing.Size(56, 21);
         this.txtDiffuseShadow.TabIndex = 77;
         // 
         // labDiffuseViewShadow
         // 
         this.labDiffuseViewShadow.AutoSize = true;
         this.labDiffuseViewShadow.Location = new System.Drawing.Point(292, 90);
         this.labDiffuseViewShadow.Name = "labDiffuseViewShadow";
         this.labDiffuseViewShadow.Size = new System.Drawing.Size(77, 12);
         this.labDiffuseViewShadow.TabIndex = 23;
         this.labDiffuseViewShadow.Text = "视角散射阴影";
         // 
         // labSpecularShadow
         // 
         this.labSpecularShadow.AutoSize = true;
         this.labSpecularShadow.Location = new System.Drawing.Point(755, 33);
         this.labSpecularShadow.Name = "labSpecularShadow";
         this.labSpecularShadow.Size = new System.Drawing.Size(53, 12);
         this.labSpecularShadow.TabIndex = 23;
         this.labSpecularShadow.Text = "高光阴影";
         // 
         // txtDiffuseViewShadow
         // 
         this.txtDiffuseViewShadow.Location = new System.Drawing.Point(380, 84);
         this.txtDiffuseViewShadow.Name = "txtDiffuseViewShadow";
         this.txtDiffuseViewShadow.Size = new System.Drawing.Size(56, 21);
         this.txtDiffuseViewShadow.TabIndex = 77;
         // 
         // txtSpecularShadow
         // 
         this.txtSpecularShadow.Location = new System.Drawing.Point(843, 27);
         this.txtSpecularShadow.Name = "txtSpecularShadow";
         this.txtSpecularShadow.Size = new System.Drawing.Size(56, 21);
         this.txtSpecularShadow.TabIndex = 77;
         // 
         // labSpecularViewShadow
         // 
         this.labSpecularViewShadow.AutoSize = true;
         this.labSpecularViewShadow.Location = new System.Drawing.Point(755, 60);
         this.labSpecularViewShadow.Name = "labSpecularViewShadow";
         this.labSpecularViewShadow.Size = new System.Drawing.Size(77, 12);
         this.labSpecularViewShadow.TabIndex = 23;
         this.labSpecularViewShadow.Text = "视角高光阴影";
         // 
         // txtSpecularViewShadow
         // 
         this.txtSpecularViewShadow.Location = new System.Drawing.Point(843, 54);
         this.txtSpecularViewShadow.Name = "txtSpecularViewShadow";
         this.txtSpecularViewShadow.Size = new System.Drawing.Size(56, 21);
         this.txtSpecularViewShadow.TabIndex = 77;
         // 
         // txtAlphaBase
         // 
         this.txtAlphaBase.Location = new System.Drawing.Point(380, 111);
         this.txtAlphaBase.Name = "txtAlphaBase";
         this.txtAlphaBase.Size = new System.Drawing.Size(56, 21);
         this.txtAlphaBase.TabIndex = 78;
         // 
         // txtAlphaRate
         // 
         this.txtAlphaRate.Location = new System.Drawing.Point(380, 138);
         this.txtAlphaRate.Name = "txtAlphaRate";
         this.txtAlphaRate.Size = new System.Drawing.Size(56, 21);
         this.txtAlphaRate.TabIndex = 78;
         // 
         // labAlphaRate
         // 
         this.labAlphaRate.AutoSize = true;
         this.labAlphaRate.Location = new System.Drawing.Point(292, 141);
         this.labAlphaRate.Name = "labAlphaRate";
         this.labAlphaRate.Size = new System.Drawing.Size(53, 12);
         this.labAlphaRate.TabIndex = 23;
         this.labAlphaRate.Text = "透明比率";
         // 
         // QDrMaterialInfoProperty
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.BackColor = System.Drawing.SystemColors.Control;
         this.Controls.Add(this.txtAlphaRate);
         this.Controls.Add(this.txtAlphaBase);
         this.Controls.Add(this.txtSpecularShadow);
         this.Controls.Add(this.txtDiffuseShadow);
         this.Controls.Add(this.txtSpecularViewShadow);
         this.Controls.Add(this.txtDiffuseViewShadow);
         this.Controls.Add(this.txtAmbientShadow);
         this.Controls.Add(this.qdcDiffuseView);
         this.Controls.Add(this.labDiffuseView);
         this.Controls.Add(this.qdcSpecularView);
         this.Controls.Add(this.labSpecularView);
         this.Controls.Add(this.qdcOpacity);
         this.Controls.Add(this.qdcRefractBack);
         this.Controls.Add(this.qdcRefractFront);
         this.Controls.Add(this.qdcReflect);
         this.Controls.Add(this.labRefractBack);
         this.Controls.Add(this.labRefractFront);
         this.Controls.Add(this.labOpacity);
         this.Controls.Add(this.labReflect);
         this.Controls.Add(this.qdcEmissive);
         this.Controls.Add(this.qdcSpecular);
         this.Controls.Add(this.qdcDiffuse);
         this.Controls.Add(this.qdcAmbient);
         this.Controls.Add(this.labEmissive);
         this.Controls.Add(this.labDiffuse);
         this.Controls.Add(this.labSpecular);
         this.Controls.Add(this.labAlphaRate);
         this.Controls.Add(this.labOptionAlpha);
         this.Controls.Add(this.labSpecularShadow);
         this.Controls.Add(this.labSpecularViewShadow);
         this.Controls.Add(this.labDiffuseShadow);
         this.Controls.Add(this.labDiffuseViewShadow);
         this.Controls.Add(this.labAmbientShadow);
         this.Controls.Add(this.labAmbient);
         this.Controls.Add(this.txtName);
         this.Controls.Add(this.labName);
         this.Name = "QDrMaterialInfoProperty";
         this.Size = new System.Drawing.Size(1072, 167);
         this.ResumeLayout(false);
         this.PerformLayout();

      }

      #endregion

      private System.Windows.Forms.Label labEmissive;
      private System.Windows.Forms.Label labDiffuse;
      private System.Windows.Forms.Label labSpecular;
      private System.Windows.Forms.Label labAmbient;
      private System.Windows.Forms.TextBox txtName;
      private System.Windows.Forms.Label labName;
      private System.Windows.Forms.ColorDialog colorShow;
      private MO.Core.Forms.Controls.QFloatColor4 qdcAmbient;
      private MO.Core.Forms.Controls.QFloatColor4 qdcDiffuse;
      private MO.Core.Forms.Controls.QFloatColor4 qdcSpecular;
      private MO.Core.Forms.Controls.QFloatColor4 qdcEmissive;
      private MO.Core.Forms.Controls.QFloatColor4 qdcOpacity;
      private MO.Core.Forms.Controls.QFloatColor4 qdcRefractBack;
      private MO.Core.Forms.Controls.QFloatColor4 qdcRefractFront;
      private MO.Core.Forms.Controls.QFloatColor4 qdcReflect;
      private System.Windows.Forms.Label labRefractBack;
      private System.Windows.Forms.Label labRefractFront;
      private System.Windows.Forms.Label labOpacity;
      private System.Windows.Forms.Label labReflect;
      private MO.Core.Forms.Controls.QFloatColor4 qdcSpecularView;
      private System.Windows.Forms.Label labSpecularView;
      private MO.Core.Forms.Controls.QFloatColor4 qdcDiffuseView;
      private System.Windows.Forms.Label labDiffuseView;
      private System.Windows.Forms.Label labOptionAlpha;
      private System.Windows.Forms.Label labAmbientShadow;
      private System.Windows.Forms.TextBox txtAmbientShadow;
      private System.Windows.Forms.Label labDiffuseShadow;
      private System.Windows.Forms.TextBox txtDiffuseShadow;
      private System.Windows.Forms.Label labDiffuseViewShadow;
      private System.Windows.Forms.Label labSpecularShadow;
      private System.Windows.Forms.TextBox txtDiffuseViewShadow;
      private System.Windows.Forms.TextBox txtSpecularShadow;
      private System.Windows.Forms.Label labSpecularViewShadow;
      private System.Windows.Forms.TextBox txtSpecularViewShadow;
      private System.Windows.Forms.TextBox txtAlphaBase;
      private System.Windows.Forms.TextBox txtAlphaRate;
      private System.Windows.Forms.Label labAlphaRate;
   }
}
