namespace MO.Design3d.Util.Normal
{
   partial class QDsNormalMapForm
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
         if (disposing && (components != null)) {
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
         this.timer1 = new System.Windows.Forms.Timer(this.components);
         this.SuspendLayout();
         // 
         // timer1
         // 
         this.timer1.Interval = 10;
         this.timer1.Tick += new System.EventHandler(this.timer1_Tick);
         // 
         // QNormalMapForm
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.ClientSize = new System.Drawing.Size(1008, 986);
         this.Name = "QNormalMapForm";
         this.Text = "QNormalMapForm";
         this.Load += new System.EventHandler(this.QNormalMapForm_Load);
         this.ResumeLayout(false);

      }

      #endregion

      private System.Windows.Forms.Timer timer1;
   }
}