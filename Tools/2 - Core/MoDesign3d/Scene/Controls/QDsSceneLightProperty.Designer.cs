namespace MO.Design3d.Scene.Controls
{
   partial class QDsSceneLightProperty
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
         this.labDiffuseView = new System.Windows.Forms.Label();
         this.labSpecularView = new System.Windows.Forms.Label();
         this.labRefractBack = new System.Windows.Forms.Label();
         this.labRefractFront = new System.Windows.Forms.Label();
         this.labOpacity = new System.Windows.Forms.Label();
         this.labReflect = new System.Windows.Forms.Label();
         this.labEmissive = new System.Windows.Forms.Label();
         this.labDiffuse = new System.Windows.Forms.Label();
         this.labSpecular = new System.Windows.Forms.Label();
         this.labAmbient = new System.Windows.Forms.Label();
         this.txtName = new System.Windows.Forms.TextBox();
         this.labName = new System.Windows.Forms.Label();
         this.labSpecularShadow = new System.Windows.Forms.Label();
         this.labSpecularViewShadow = new System.Windows.Forms.Label();
         this.labSpecularBase = new System.Windows.Forms.Label();
         this.labSpecularViewBase = new System.Windows.Forms.Label();
         this.labSpecularViewAverage = new System.Windows.Forms.Label();
         this.labMinColor = new System.Windows.Forms.Label();
         this.labSpecularViewRate = new System.Windows.Forms.Label();
         this.labAmbientShadow = new System.Windows.Forms.Label();
         this.labMaxColor = new System.Windows.Forms.Label();
         this.labDiffuseShadow = new System.Windows.Forms.Label();
         this.labSpecularRate = new System.Windows.Forms.Label();
         this.labSpecularAverage = new System.Windows.Forms.Label();
         this.qdcDiffuseView = new MO.Core.Forms.Controls.QFloatColor4();
         this.qdcSpecularView = new MO.Core.Forms.Controls.QFloatColor4();
         this.qdcOpacityColor = new MO.Core.Forms.Controls.QFloatColor4();
         this.qdcRefractBack = new MO.Core.Forms.Controls.QFloatColor4();
         this.qdcRefractFront = new MO.Core.Forms.Controls.QFloatColor4();
         this.qdcReflect = new MO.Core.Forms.Controls.QFloatColor4();
         this.qdcEmissive = new MO.Core.Forms.Controls.QFloatColor4();
         this.qdcSpecular = new MO.Core.Forms.Controls.QFloatColor4();
         this.qdcDiffuse = new MO.Core.Forms.Controls.QFloatColor4();
         this.qdcAmbient = new MO.Core.Forms.Controls.QFloatColor4();
         this.labDeffuseViewShadow = new System.Windows.Forms.Label();
         this.txtMinColor = new System.Windows.Forms.TextBox();
         this.txtMaxColor = new System.Windows.Forms.TextBox();
         this.txtAmbientShadow = new System.Windows.Forms.TextBox();
         this.txtDeffuseShadow = new System.Windows.Forms.TextBox();
         this.txtDeffuseViewShadow = new System.Windows.Forms.TextBox();
         this.txtSpecularBase = new System.Windows.Forms.TextBox();
         this.txtSpecularRate = new System.Windows.Forms.TextBox();
         this.txtSpecularAverage = new System.Windows.Forms.TextBox();
         this.txtSpecularShadow = new System.Windows.Forms.TextBox();
         this.txtSpecularViewBase = new System.Windows.Forms.TextBox();
         this.txtSpecularViewRate = new System.Windows.Forms.TextBox();
         this.txtSpecularViewAverage = new System.Windows.Forms.TextBox();
         this.txtSpecularViewShadow = new System.Windows.Forms.TextBox();
         this.btnReset = new System.Windows.Forms.Button();
         this.qdrOptionTrack = new MO.Design3d.Controls.QDsFlag();
         this.labOptionTrack = new System.Windows.Forms.Label();
         this.SuspendLayout();
         // 
         // labDiffuseView
         // 
         this.labDiffuseView.AutoSize = true;
         this.labDiffuseView.Location = new System.Drawing.Point(19, 96);
         this.labDiffuseView.Name = "labDiffuseView";
         this.labDiffuseView.Size = new System.Drawing.Size(77, 12);
         this.labDiffuseView.TabIndex = 103;
         this.labDiffuseView.Text = "散射视角颜色";
         // 
         // labSpecularView
         // 
         this.labSpecularView.AutoSize = true;
         this.labSpecularView.Location = new System.Drawing.Point(19, 177);
         this.labSpecularView.Name = "labSpecularView";
         this.labSpecularView.Size = new System.Drawing.Size(77, 12);
         this.labSpecularView.TabIndex = 101;
         this.labSpecularView.Text = "高光视角颜色";
         // 
         // labRefractBack
         // 
         this.labRefractBack.AutoSize = true;
         this.labRefractBack.Location = new System.Drawing.Point(20, 285);
         this.labRefractBack.Name = "labRefractBack";
         this.labRefractBack.Size = new System.Drawing.Size(65, 12);
         this.labRefractBack.TabIndex = 96;
         this.labRefractBack.Text = "后折射颜色";
         // 
         // labRefractFront
         // 
         this.labRefractFront.AutoSize = true;
         this.labRefractFront.Location = new System.Drawing.Point(20, 258);
         this.labRefractFront.Name = "labRefractFront";
         this.labRefractFront.Size = new System.Drawing.Size(65, 12);
         this.labRefractFront.TabIndex = 95;
         this.labRefractFront.Text = "前折射颜色";
         // 
         // labOpacity
         // 
         this.labOpacity.AutoSize = true;
         this.labOpacity.Location = new System.Drawing.Point(20, 312);
         this.labOpacity.Name = "labOpacity";
         this.labOpacity.Size = new System.Drawing.Size(65, 12);
         this.labOpacity.TabIndex = 94;
         this.labOpacity.Text = "不透明颜色";
         // 
         // labReflect
         // 
         this.labReflect.AutoSize = true;
         this.labReflect.Location = new System.Drawing.Point(20, 231);
         this.labReflect.Name = "labReflect";
         this.labReflect.Size = new System.Drawing.Size(53, 12);
         this.labReflect.TabIndex = 93;
         this.labReflect.Text = "反射颜色";
         // 
         // labEmissive
         // 
         this.labEmissive.AutoSize = true;
         this.labEmissive.Location = new System.Drawing.Point(20, 339);
         this.labEmissive.Name = "labEmissive";
         this.labEmissive.Size = new System.Drawing.Size(53, 12);
         this.labEmissive.TabIndex = 87;
         this.labEmissive.Text = "发光颜色";
         // 
         // labDiffuse
         // 
         this.labDiffuse.AutoSize = true;
         this.labDiffuse.Location = new System.Drawing.Point(19, 69);
         this.labDiffuse.Name = "labDiffuse";
         this.labDiffuse.Size = new System.Drawing.Size(53, 12);
         this.labDiffuse.TabIndex = 86;
         this.labDiffuse.Text = "散射颜色";
         // 
         // labSpecular
         // 
         this.labSpecular.AutoSize = true;
         this.labSpecular.Location = new System.Drawing.Point(19, 123);
         this.labSpecular.Name = "labSpecular";
         this.labSpecular.Size = new System.Drawing.Size(53, 12);
         this.labSpecular.TabIndex = 85;
         this.labSpecular.Text = "高光颜色";
         // 
         // labAmbient
         // 
         this.labAmbient.AutoSize = true;
         this.labAmbient.Location = new System.Drawing.Point(19, 42);
         this.labAmbient.Name = "labAmbient";
         this.labAmbient.Size = new System.Drawing.Size(53, 12);
         this.labAmbient.TabIndex = 82;
         this.labAmbient.Text = "环境颜色";
         // 
         // txtName
         // 
         this.txtName.BackColor = System.Drawing.SystemColors.Window;
         this.txtName.ForeColor = System.Drawing.SystemColors.GrayText;
         this.txtName.Location = new System.Drawing.Point(100, 12);
         this.txtName.Name = "txtName";
         this.txtName.ReadOnly = true;
         this.txtName.Size = new System.Drawing.Size(405, 21);
         this.txtName.TabIndex = 78;
         // 
         // labName
         // 
         this.labName.AutoSize = true;
         this.labName.Location = new System.Drawing.Point(19, 15);
         this.labName.Name = "labName";
         this.labName.Size = new System.Drawing.Size(53, 12);
         this.labName.TabIndex = 77;
         this.labName.Text = "材质名称";
         // 
         // labSpecularShadow
         // 
         this.labSpecularShadow.AutoSize = true;
         this.labSpecularShadow.Location = new System.Drawing.Point(322, 123);
         this.labSpecularShadow.Name = "labSpecularShadow";
         this.labSpecularShadow.Size = new System.Drawing.Size(53, 12);
         this.labSpecularShadow.TabIndex = 109;
         this.labSpecularShadow.Text = "高光阴影";
         // 
         // labSpecularViewShadow
         // 
         this.labSpecularViewShadow.AutoSize = true;
         this.labSpecularViewShadow.Location = new System.Drawing.Point(322, 177);
         this.labSpecularViewShadow.Name = "labSpecularViewShadow";
         this.labSpecularViewShadow.Size = new System.Drawing.Size(77, 12);
         this.labSpecularViewShadow.TabIndex = 109;
         this.labSpecularViewShadow.Text = "视角高光阴影";
         // 
         // labSpecularBase
         // 
         this.labSpecularBase.AutoSize = true;
         this.labSpecularBase.Location = new System.Drawing.Point(97, 148);
         this.labSpecularBase.Name = "labSpecularBase";
         this.labSpecularBase.Size = new System.Drawing.Size(53, 12);
         this.labSpecularBase.TabIndex = 109;
         this.labSpecularBase.Text = "高光基础";
         // 
         // labSpecularViewBase
         // 
         this.labSpecularViewBase.AutoSize = true;
         this.labSpecularViewBase.Location = new System.Drawing.Point(97, 201);
         this.labSpecularViewBase.Name = "labSpecularViewBase";
         this.labSpecularViewBase.Size = new System.Drawing.Size(77, 12);
         this.labSpecularViewBase.TabIndex = 109;
         this.labSpecularViewBase.Text = "视角高光基础";
         // 
         // labSpecularViewAverage
         // 
         this.labSpecularViewAverage.AutoSize = true;
         this.labSpecularViewAverage.Location = new System.Drawing.Point(539, 201);
         this.labSpecularViewAverage.Name = "labSpecularViewAverage";
         this.labSpecularViewAverage.Size = new System.Drawing.Size(77, 12);
         this.labSpecularViewAverage.TabIndex = 109;
         this.labSpecularViewAverage.Text = "视角高光平均";
         // 
         // labMinColor
         // 
         this.labMinColor.AutoSize = true;
         this.labMinColor.Location = new System.Drawing.Point(539, 44);
         this.labMinColor.Name = "labMinColor";
         this.labMinColor.Size = new System.Drawing.Size(53, 12);
         this.labMinColor.TabIndex = 109;
         this.labMinColor.Text = "最小颜色";
         // 
         // labSpecularViewRate
         // 
         this.labSpecularViewRate.AutoSize = true;
         this.labSpecularViewRate.Location = new System.Drawing.Point(319, 206);
         this.labSpecularViewRate.Name = "labSpecularViewRate";
         this.labSpecularViewRate.Size = new System.Drawing.Size(77, 12);
         this.labSpecularViewRate.TabIndex = 109;
         this.labSpecularViewRate.Text = "视角高光比率";
         // 
         // labAmbientShadow
         // 
         this.labAmbientShadow.AutoSize = true;
         this.labAmbientShadow.Location = new System.Drawing.Point(322, 42);
         this.labAmbientShadow.Name = "labAmbientShadow";
         this.labAmbientShadow.Size = new System.Drawing.Size(53, 12);
         this.labAmbientShadow.TabIndex = 109;
         this.labAmbientShadow.Text = "环境阴影";
         // 
         // labMaxColor
         // 
         this.labMaxColor.AutoSize = true;
         this.labMaxColor.Location = new System.Drawing.Point(539, 69);
         this.labMaxColor.Name = "labMaxColor";
         this.labMaxColor.Size = new System.Drawing.Size(53, 12);
         this.labMaxColor.TabIndex = 109;
         this.labMaxColor.Text = "最大颜色";
         // 
         // labDiffuseShadow
         // 
         this.labDiffuseShadow.AutoSize = true;
         this.labDiffuseShadow.Location = new System.Drawing.Point(322, 69);
         this.labDiffuseShadow.Name = "labDiffuseShadow";
         this.labDiffuseShadow.Size = new System.Drawing.Size(53, 12);
         this.labDiffuseShadow.TabIndex = 109;
         this.labDiffuseShadow.Text = "散射阴影";
         // 
         // labSpecularRate
         // 
         this.labSpecularRate.AutoSize = true;
         this.labSpecularRate.Location = new System.Drawing.Point(319, 152);
         this.labSpecularRate.Name = "labSpecularRate";
         this.labSpecularRate.Size = new System.Drawing.Size(53, 12);
         this.labSpecularRate.TabIndex = 109;
         this.labSpecularRate.Text = "高光比率";
         // 
         // labSpecularAverage
         // 
         this.labSpecularAverage.AutoSize = true;
         this.labSpecularAverage.Location = new System.Drawing.Point(540, 155);
         this.labSpecularAverage.Name = "labSpecularAverage";
         this.labSpecularAverage.Size = new System.Drawing.Size(53, 12);
         this.labSpecularAverage.TabIndex = 109;
         this.labSpecularAverage.Text = "高光平均";
         // 
         // qdcDiffuseView
         // 
         this.qdcDiffuseView.Location = new System.Drawing.Point(99, 90);
         this.qdcDiffuseView.Name = "qdcDiffuseView";
         this.qdcDiffuseView.Size = new System.Drawing.Size(200, 21);
         this.qdcDiffuseView.TabIndex = 104;
         // 
         // qdcSpecularView
         // 
         this.qdcSpecularView.Location = new System.Drawing.Point(99, 171);
         this.qdcSpecularView.Name = "qdcSpecularView";
         this.qdcSpecularView.Size = new System.Drawing.Size(200, 21);
         this.qdcSpecularView.TabIndex = 102;
         // 
         // qdcOpacity
         // 
         this.qdcOpacityColor.Location = new System.Drawing.Point(100, 306);
         this.qdcOpacityColor.Name = "qdcOpacity";
         this.qdcOpacityColor.Size = new System.Drawing.Size(200, 21);
         this.qdcOpacityColor.TabIndex = 100;
         // 
         // qdcRefractBack
         // 
         this.qdcRefractBack.Location = new System.Drawing.Point(100, 279);
         this.qdcRefractBack.Name = "qdcRefractBack";
         this.qdcRefractBack.Size = new System.Drawing.Size(200, 21);
         this.qdcRefractBack.TabIndex = 99;
         // 
         // qdcRefractFront
         // 
         this.qdcRefractFront.Location = new System.Drawing.Point(100, 252);
         this.qdcRefractFront.Name = "qdcRefractFront";
         this.qdcRefractFront.Size = new System.Drawing.Size(200, 21);
         this.qdcRefractFront.TabIndex = 98;
         // 
         // qdcReflect
         // 
         this.qdcReflect.Location = new System.Drawing.Point(100, 225);
         this.qdcReflect.Name = "qdcReflect";
         this.qdcReflect.Size = new System.Drawing.Size(200, 21);
         this.qdcReflect.TabIndex = 97;
         // 
         // qdcEmissive
         // 
         this.qdcEmissive.Location = new System.Drawing.Point(99, 117);
         this.qdcEmissive.Name = "qdcEmissive";
         this.qdcEmissive.Size = new System.Drawing.Size(200, 21);
         this.qdcEmissive.TabIndex = 92;
         // 
         // qdcSpecular
         // 
         this.qdcSpecular.Location = new System.Drawing.Point(100, 333);
         this.qdcSpecular.Name = "qdcSpecular";
         this.qdcSpecular.Size = new System.Drawing.Size(200, 21);
         this.qdcSpecular.TabIndex = 91;
         // 
         // qdcDiffuse
         // 
         this.qdcDiffuse.Location = new System.Drawing.Point(99, 63);
         this.qdcDiffuse.Name = "qdcDiffuse";
         this.qdcDiffuse.Size = new System.Drawing.Size(200, 21);
         this.qdcDiffuse.TabIndex = 90;
         // 
         // qdcAmbient
         // 
         this.qdcAmbient.Location = new System.Drawing.Point(99, 36);
         this.qdcAmbient.Name = "qdcAmbient";
         this.qdcAmbient.Size = new System.Drawing.Size(200, 21);
         this.qdcAmbient.TabIndex = 89;
         // 
         // labDeffuseViewShadow
         // 
         this.labDeffuseViewShadow.AutoSize = true;
         this.labDeffuseViewShadow.Location = new System.Drawing.Point(322, 96);
         this.labDeffuseViewShadow.Name = "labDeffuseViewShadow";
         this.labDeffuseViewShadow.Size = new System.Drawing.Size(77, 12);
         this.labDeffuseViewShadow.TabIndex = 80;
         this.labDeffuseViewShadow.Text = "视角散射阴影";
         // 
         // txtMinColor
         // 
         this.txtMinColor.Location = new System.Drawing.Point(626, 39);
         this.txtMinColor.Name = "txtMinColor";
         this.txtMinColor.Size = new System.Drawing.Size(100, 21);
         this.txtMinColor.TabIndex = 110;
         // 
         // txtMaxColor
         // 
         this.txtMaxColor.Location = new System.Drawing.Point(626, 66);
         this.txtMaxColor.Name = "txtMaxColor";
         this.txtMaxColor.Size = new System.Drawing.Size(100, 21);
         this.txtMaxColor.TabIndex = 110;
         // 
         // txtAmbientShadow
         // 
         this.txtAmbientShadow.Location = new System.Drawing.Point(405, 39);
         this.txtAmbientShadow.Name = "txtAmbientShadow";
         this.txtAmbientShadow.Size = new System.Drawing.Size(100, 21);
         this.txtAmbientShadow.TabIndex = 110;
         // 
         // txtDeffuseShadow
         // 
         this.txtDeffuseShadow.Location = new System.Drawing.Point(405, 66);
         this.txtDeffuseShadow.Name = "txtDeffuseShadow";
         this.txtDeffuseShadow.Size = new System.Drawing.Size(100, 21);
         this.txtDeffuseShadow.TabIndex = 110;
         // 
         // txtDeffuseViewShadow
         // 
         this.txtDeffuseViewShadow.Location = new System.Drawing.Point(405, 93);
         this.txtDeffuseViewShadow.Name = "txtDeffuseViewShadow";
         this.txtDeffuseViewShadow.Size = new System.Drawing.Size(100, 21);
         this.txtDeffuseViewShadow.TabIndex = 110;
         // 
         // txtSpecularBase
         // 
         this.txtSpecularBase.Location = new System.Drawing.Point(199, 144);
         this.txtSpecularBase.Name = "txtSpecularBase";
         this.txtSpecularBase.Size = new System.Drawing.Size(100, 21);
         this.txtSpecularBase.TabIndex = 110;
         // 
         // txtSpecularRate
         // 
         this.txtSpecularRate.Location = new System.Drawing.Point(405, 148);
         this.txtSpecularRate.Name = "txtSpecularRate";
         this.txtSpecularRate.Size = new System.Drawing.Size(100, 21);
         this.txtSpecularRate.TabIndex = 110;
         // 
         // txtSpecularAverage
         // 
         this.txtSpecularAverage.Location = new System.Drawing.Point(626, 149);
         this.txtSpecularAverage.Name = "txtSpecularAverage";
         this.txtSpecularAverage.Size = new System.Drawing.Size(100, 21);
         this.txtSpecularAverage.TabIndex = 110;
         // 
         // txtSpecularShadow
         // 
         this.txtSpecularShadow.Location = new System.Drawing.Point(405, 120);
         this.txtSpecularShadow.Name = "txtSpecularShadow";
         this.txtSpecularShadow.Size = new System.Drawing.Size(100, 21);
         this.txtSpecularShadow.TabIndex = 110;
         // 
         // txtSpecularViewBase
         // 
         this.txtSpecularViewBase.Location = new System.Drawing.Point(199, 198);
         this.txtSpecularViewBase.Name = "txtSpecularViewBase";
         this.txtSpecularViewBase.Size = new System.Drawing.Size(100, 21);
         this.txtSpecularViewBase.TabIndex = 110;
         // 
         // txtSpecularViewRate
         // 
         this.txtSpecularViewRate.Location = new System.Drawing.Point(405, 201);
         this.txtSpecularViewRate.Name = "txtSpecularViewRate";
         this.txtSpecularViewRate.Size = new System.Drawing.Size(100, 21);
         this.txtSpecularViewRate.TabIndex = 110;
         // 
         // txtSpecularViewAverage
         // 
         this.txtSpecularViewAverage.Location = new System.Drawing.Point(626, 198);
         this.txtSpecularViewAverage.Name = "txtSpecularViewAverage";
         this.txtSpecularViewAverage.Size = new System.Drawing.Size(100, 21);
         this.txtSpecularViewAverage.TabIndex = 110;
         // 
         // txtSpecularViewShadow
         // 
         this.txtSpecularViewShadow.Location = new System.Drawing.Point(405, 174);
         this.txtSpecularViewShadow.Name = "txtSpecularViewShadow";
         this.txtSpecularViewShadow.Size = new System.Drawing.Size(100, 21);
         this.txtSpecularViewShadow.TabIndex = 110;
         // 
         // btnReset
         // 
         this.btnReset.Location = new System.Drawing.Point(626, 330);
         this.btnReset.Name = "btnReset";
         this.btnReset.Size = new System.Drawing.Size(100, 21);
         this.btnReset.TabIndex = 111;
         this.btnReset.Text = "重置";
         this.btnReset.UseVisualStyleBackColor = true;
         this.btnReset.Click += new System.EventHandler(this.btnReset_Click);
         // 
         // qdrOptionTrack
         // 
         this.qdrOptionTrack.Location = new System.Drawing.Point(626, 10);
         this.qdrOptionTrack.Name = "qdrOptionTrack";
         this.qdrOptionTrack.Size = new System.Drawing.Size(140, 23);
         this.qdrOptionTrack.TabIndex = 113;
         // 
         // labOptionTrack
         // 
         this.labOptionTrack.AutoSize = true;
         this.labOptionTrack.Location = new System.Drawing.Point(540, 15);
         this.labOptionTrack.Name = "labOptionTrack";
         this.labOptionTrack.Size = new System.Drawing.Size(53, 12);
         this.labOptionTrack.TabIndex = 112;
         this.labOptionTrack.Text = "跟踪设置";
         // 
         // QDrSceneLightProperty
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.Controls.Add(this.qdrOptionTrack);
         this.Controls.Add(this.labOptionTrack);
         this.Controls.Add(this.btnReset);
         this.Controls.Add(this.txtSpecularShadow);
         this.Controls.Add(this.txtSpecularAverage);
         this.Controls.Add(this.txtSpecularBase);
         this.Controls.Add(this.txtDeffuseShadow);
         this.Controls.Add(this.txtMaxColor);
         this.Controls.Add(this.txtSpecularViewShadow);
         this.Controls.Add(this.txtSpecularViewAverage);
         this.Controls.Add(this.txtSpecularViewRate);
         this.Controls.Add(this.txtSpecularViewBase);
         this.Controls.Add(this.txtSpecularRate);
         this.Controls.Add(this.txtDeffuseViewShadow);
         this.Controls.Add(this.txtAmbientShadow);
         this.Controls.Add(this.txtMinColor);
         this.Controls.Add(this.labSpecularViewAverage);
         this.Controls.Add(this.labSpecularViewBase);
         this.Controls.Add(this.labSpecularBase);
         this.Controls.Add(this.labSpecularAverage);
         this.Controls.Add(this.labSpecularViewShadow);
         this.Controls.Add(this.labSpecularRate);
         this.Controls.Add(this.labSpecularShadow);
         this.Controls.Add(this.labDiffuseShadow);
         this.Controls.Add(this.labMaxColor);
         this.Controls.Add(this.labSpecularViewRate);
         this.Controls.Add(this.labAmbientShadow);
         this.Controls.Add(this.labMinColor);
         this.Controls.Add(this.qdcDiffuseView);
         this.Controls.Add(this.labDiffuseView);
         this.Controls.Add(this.qdcSpecularView);
         this.Controls.Add(this.labSpecularView);
         this.Controls.Add(this.qdcOpacityColor);
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
         this.Controls.Add(this.labDeffuseViewShadow);
         this.Controls.Add(this.labAmbient);
         this.Controls.Add(this.txtName);
         this.Controls.Add(this.labName);
         this.Name = "QDrSceneLightProperty";
         this.Size = new System.Drawing.Size(788, 450);
         this.ResumeLayout(false);
         this.PerformLayout();

      }

      #endregion

      private MO.Core.Forms.Controls.QFloatColor4 qdcDiffuseView;
      private System.Windows.Forms.Label labDiffuseView;
      private MO.Core.Forms.Controls.QFloatColor4 qdcSpecularView;
      private System.Windows.Forms.Label labSpecularView;
      private MO.Core.Forms.Controls.QFloatColor4 qdcOpacityColor;
      private MO.Core.Forms.Controls.QFloatColor4 qdcRefractBack;
      private MO.Core.Forms.Controls.QFloatColor4 qdcRefractFront;
      private MO.Core.Forms.Controls.QFloatColor4 qdcReflect;
      private System.Windows.Forms.Label labRefractBack;
      private System.Windows.Forms.Label labRefractFront;
      private System.Windows.Forms.Label labOpacity;
      private System.Windows.Forms.Label labReflect;
      private MO.Core.Forms.Controls.QFloatColor4 qdcEmissive;
      private MO.Core.Forms.Controls.QFloatColor4 qdcSpecular;
      private MO.Core.Forms.Controls.QFloatColor4 qdcDiffuse;
      private MO.Core.Forms.Controls.QFloatColor4 qdcAmbient;
      private System.Windows.Forms.Label labEmissive;
      private System.Windows.Forms.Label labDiffuse;
      private System.Windows.Forms.Label labSpecular;
      private System.Windows.Forms.Label labAmbient;
      private System.Windows.Forms.TextBox txtName;
      private System.Windows.Forms.Label labName;
      private System.Windows.Forms.Label labSpecularShadow;
      private System.Windows.Forms.Label labSpecularViewShadow;
      private System.Windows.Forms.Label labSpecularBase;
      private System.Windows.Forms.Label labSpecularViewBase;
      private System.Windows.Forms.Label labSpecularViewAverage;
      private System.Windows.Forms.Label labMinColor;
      private System.Windows.Forms.Label labSpecularViewRate;
      private System.Windows.Forms.Label labAmbientShadow;
      private System.Windows.Forms.Label labMaxColor;
      private System.Windows.Forms.Label labDiffuseShadow;
      private System.Windows.Forms.Label labSpecularRate;
      private System.Windows.Forms.Label labSpecularAverage;
      private System.Windows.Forms.Label labDeffuseViewShadow;
      private System.Windows.Forms.TextBox txtMinColor;
      private System.Windows.Forms.TextBox txtMaxColor;
      private System.Windows.Forms.TextBox txtAmbientShadow;
      private System.Windows.Forms.TextBox txtDeffuseShadow;
      private System.Windows.Forms.TextBox txtDeffuseViewShadow;
      private System.Windows.Forms.TextBox txtSpecularBase;
      private System.Windows.Forms.TextBox txtSpecularRate;
      private System.Windows.Forms.TextBox txtSpecularAverage;
      private System.Windows.Forms.TextBox txtSpecularShadow;
      private System.Windows.Forms.TextBox txtSpecularViewBase;
      private System.Windows.Forms.TextBox txtSpecularViewRate;
      private System.Windows.Forms.TextBox txtSpecularViewAverage;
      private System.Windows.Forms.TextBox txtSpecularViewShadow;
      private System.Windows.Forms.Button btnReset;
      private MO.Design3d.Controls.QDsFlag qdrOptionTrack;
      private System.Windows.Forms.Label labOptionTrack;
   }
}
