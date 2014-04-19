using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MO.Common.Content {
   public class FXmlNodeAttributeComparer : IComparer<FXmlElement> {

      protected string _attributeName;

      public FXmlNodeAttributeComparer(string attributeName) {
         _attributeName = attributeName;
      }

      public int Compare(FXmlElement source, FXmlElement target) {
         return String.Compare(source.Get(_attributeName), target.Get(_attributeName));
      }
   }
}
