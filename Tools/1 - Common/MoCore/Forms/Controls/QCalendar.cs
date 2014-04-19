using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Diagnostics;
using System.Linq;
using System.Text;

namespace MO.Core.Forms.Controls
{
   public partial class QCalendar : QControl
   {
      public QCalendar() {
         InitializeComponent();
      }

      public QCalendar(IContainer container) {
         container.Add(this);

         InitializeComponent();
      }
   }
}
