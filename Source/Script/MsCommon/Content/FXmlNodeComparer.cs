using System;
using System.Collections.Generic;

namespace MS.Common.Content {
   public class FXmlNodeComparer : IComparer<FXmlNode> {

      protected string _name;
      
      public FXmlNodeComparer(string name) {
      }

      public int Compare(FXmlNode x, FXmlNode y) {
         throw new NotImplementedException();
      } 
   }
}
       