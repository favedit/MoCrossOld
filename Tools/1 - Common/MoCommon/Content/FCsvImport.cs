using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Content;

namespace MO.Common.Content {

   public abstract class FCsvImport{

      public FCsvImport() {
      }

      /*public FCsvImport(string filename)
         : base(filename) {
      }

      public abstract void OnBefore();

      public abstract void OnReadLine(FCsvLine line);

      public abstract void OnAfter();

      public void LoadConfig(FXmlNode config) {
         _heads.Clear();
         FXmlNodes nodes = config.Nodes;
         if (nodes != null) {
            foreach (FXmlNode node in config.Nodes) {
               if (node.IsName(FCsvHead.HEAD_TYPE)) {
                  FCsvHead head = new FCsvHead();
                  head.LoadConfig(node);
                  _heads[head.Name] = head;
               }
            }
         }
      }

      public virtual void Process() {
         OnBefore();
         while (HasNext()) {
            FCsvLine line = ReadLine();
            OnReadLine(line);
         }
         OnAfter();
      }*/

   }

}
