using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MO.Common.Content {
   public class FXmlNodeComparer : IComparer<FXmlNode> {

      protected string _name;
      
      public FXmlNodeComparer(string name) {
      }

      public int Compare(FXmlNode x, FXmlNode y) {
         throw new NotImplementedException();
      } 
   }
}
       