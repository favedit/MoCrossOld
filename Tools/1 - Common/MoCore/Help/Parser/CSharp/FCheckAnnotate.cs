using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MO.Core.Help.Common;
using MO.Common.Lang;

namespace MO.Core.Help.Parser.CSharp
{
   public class FCheckAnnotate:FCrHelpNode
   {

      public bool IsExistInAnnotate(FStrings annotates, string name) {
         for(int n = 0; n < annotates.Count; n++) {
            if(annotates[n].Contains("name")) { return true; }
         }
         return false;
      }

      public bool IsClassAnnotateFine(FCsClass csClass) {
         FStrings classAnnos = GetAnnotate(csClass.StrLine, csClass.BeginIndex);
         bool bLabelFine = true, bAuthorfine = true, bParentfine = true, bImplementFine = true;
         bLabelFine = IsExistInAnnotate(classAnnos, "<T>");
         bAuthorfine = IsExistInAnnotate(classAnnos, "@author");
         if(csClass.ParentsName != null && csClass.ParentsName != "") {
            bParentfine = IsExistInAnnotate(classAnnos, "@exist") && IsExistInAnnotate(classAnnos, csClass.ParentsName);
         }
         if(csClass.Interface != null && csClass.Interface.Length != 0) {
            for(int n = 0; n < csClass.Interface.Length; n++) {
               if(!IsExistInAnnotate(classAnnos, csClass.Interface[n])) {
                  bImplementFine = false;
               }
            }
         }
         return bLabelFine && bAuthorfine && bParentfine && bImplementFine;
      }

      public bool IsFieldAnnotateFine(FCsField csField) {
         return true;
      }
   }
}
