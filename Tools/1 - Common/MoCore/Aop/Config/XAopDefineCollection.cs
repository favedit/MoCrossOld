using System;
using System.Collections.Generic;
using System.Text;
using System.Reflection;
using MO.Common.Content;
using MO.Common.Lang;

namespace MO.Core.Aop.Config {

   public class XAopDefineCollection : XAopNodeCollection<XAopDefine> {

      public const string TAG = "Defines";

      public XAopDefineCollection() {
         _duplicate = false;
      }

      public string Parse(string value) {
         if (value != null) {
            while (true) {
               int start = value.LastIndexOf("${");
               if (start == -1) {
                  break;
               }
               int end = value.IndexOf("}", start);
               if (end == -1) {
                  break;
               }
               string name = value.Substring(start + 2, end - start - 2);
               // Find value
               XAopDefine xdefine = _ids[name];
               if (xdefine != null) {
                  value = value.Replace("${" + name + "}", xdefine.Value);
               } else {
                  value = value.Replace("${" + name + "}", "");
               }
            }
         }
         return value;
      }


   }

}
