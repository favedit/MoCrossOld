using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Content;
using MO.Common.Lang;
using System.Reflection;

namespace MObj.Util.Config {

   public class XPropertyDocument : FXmlDocument {

      public XPropertyDocument() { }

      public XPropertyDocument(string filename)
         : base(filename) { }

      public XPropertyDocument(Assembly assembly, string name)
         : base(assembly, name) { }

      /*public override FXmlNode CreateNode(string name, FAttributes attributes) {
         FXmlNode node = null;
         if(name == XPropertyFolder.TAG){
            node = new XPropertyFolder();
         }else if(name == XPropertyItem.TAG){
            node = new XPropertyItem();
         }else{
            node = new FXmlNode();
         }
         //node.Name = name;
         //node.Attributes = attributes;
         //node.Document = this;
         return node;
      }*/

   }

}
