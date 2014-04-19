using System;

namespace MO.Core.Forms.Design
{
   public class RDesigner
   {
      //============================================================
      public static void Throw(object owner, Exception e, params object[] values) {
         QDesignErrorForm qForm = new QDesignErrorForm();
         qForm.ThrowOwner = owner;
         qForm.ThrowException = e;
         qForm.ThrowValues = values;
         qForm.ShowDialog();
      }
   }
}
