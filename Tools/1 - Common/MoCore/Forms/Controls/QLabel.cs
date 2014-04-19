using System.ComponentModel;

namespace MO.Core.Forms.Controls
{
   public partial class QLabel : Component
   {
      public QLabel() {
         InitializeComponent();
      }

      public QLabel(IContainer container) {
         container.Add(this);

         InitializeComponent();
      }
   }
}
