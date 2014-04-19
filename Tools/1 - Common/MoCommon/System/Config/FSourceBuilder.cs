using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Lang;
using MO.Common.Content;
using System.IO;

namespace MObj.Util.Config {

   public class FSourceBuilder {

      private XPropertyDocument _document;

      private string _nameSpace;

      private string _className = "XConfiguration";

      private FString _source;

      public FSourceBuilder() {
      }

      public FSourceBuilder(XPropertyDocument document) {
         _document = document;
      }

      public string NameSpace {
         get { return _nameSpace; }
         set { _nameSpace = value; }
      }

      public string ClassName {
         get { return _className; }
         set { _className = value; }
      }

      private string MakeClassName(string name) {
         return "XConfig" + name;
      }

      private void BuildNodeSource(FXmlNode config, string path) {
         if (config.HasNode) {
            // Build private
            foreach (FXmlNode node in config.Nodes) {
               string clsName = MakeClassName(path + config["name"] + node["name"]);
               string varname = "_" + RString.FirstLower(node["name"]);
               _source.Append("      private ");
               if (node.IsName("Folder")) {
                  _source.Append(clsName + " " + varname + " = new ");
                  _source.AppendLine(clsName + "();");
               } else if (node.IsName("Property")) {
                  _source.AppendLine("FStringValue " + varname + " = new FStringValue();");
               }
            }
            // Build property
            foreach (FXmlNode node in config.Nodes) {
               string clsName = MakeClassName(path + config["name"] + node["name"]);
               string varname = "_" + RString.FirstLower(node["name"]);
               string ptyname = RString.FirstUpper(node["name"]);
               _source.Append("      public ");
               if (node.IsName("Folder")) {
                  string scName = path + node["name"];
                  _source.Append(clsName + " " + ptyname + " {");
                  _source.AppendLine(" get { return " + varname + "; } }");
               } else if (node.IsName("Property")) {
                  _source.Append("FStringValue " + ptyname + " {");
                  _source.AppendLine(" get { return " + varname + "; } }");
               }
            }
         }
      }

      private void BuildNode(FXmlNode config, string path) {
         // Build child
         if (config.HasNode) {
            foreach (FXmlNode node in config.Nodes) {
               BuildNode(node, path + config["name"]);
            }
         }
         // Build Parent
         if (config.IsName("Folder")) {
            string clsName = MakeClassName(path + config["name"]);
            _source.AppendLine("   public class " + clsName + " {");
            BuildNodeSource(config, path);
            _source.AppendLine("   }");
         }
      }

      private void BuildRootNode(FXmlNode config) {
         _source.AppendLine("   public class " + _className + "{");
         if (config.HasNode) {
            BuildNodeSource(config, null);
            _source.AppendLine("   }");
         }
      }

      public FString Parse() {
         _source = new FString();
         _source.AppendLine("using CommonMO.Common.Lang;");
         _source.AppendLine();
         _source.AppendLine("namespace " + _nameSpace + " {");
         // Build class
         BuildNode(_document.Root, null);
         // Build root
         BuildRootNode(_document.Root);
         _source.AppendLine("}");
         return _source;
      }

      public void SaveFile(string filename) {
         FString source = Parse();
         File.WriteAllText(filename, source.ToString());
         // Encoding.UTF8
      }

   }
}
