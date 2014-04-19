using System;

namespace MO.Core.Forms.Dialog
{
   public class RDialog
   {
      //============================================================
      public static void ShowException(object owner, Exception e, params object[] values) {
         QExceptionDialogForm qForm = new QExceptionDialogForm();
         qForm.ThrowOwner = owner;
         qForm.ThrowException = e;
         qForm.ThrowValues = values;
         qForm.ShowDialog();
      }
   }
}
