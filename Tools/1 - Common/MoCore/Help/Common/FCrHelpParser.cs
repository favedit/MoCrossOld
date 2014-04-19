using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml;
using MO.Core.Help.Parser.CSharp;
using System.IO;

namespace MO.Core.Help.Common
{
   public class FCrHelpParser : ICrHelpParser
   {
      public virtual void ParseDirectory(string directory) {
      }


      public void SavaInXML(FCsParser parser, FileInfo fiSave) {
         //   XmlDocument doc = new XmlDocument ( );
         //   XmlDeclaration dec = doc.CreateXmlDeclaration ( "1.0", "utf-8", "no" );
         //   doc.AppendChild ( dec );
         //   XmlElement rootSpace = doc.CreateElement ( "Namespace" );
         //   XmlElement spaceName = doc.CreateElement ( "NamespaceStr" );
         //   spaceName.InnerText = parser.Space.SpaceStr;
         //   rootSpace.AppendChild ( spaceName );
         //   doc.AppendChild ( rootSpace );
         //   if (parser.Space.Class != null) {
         //      for (int n = 0; n < parser.Space.Class.Length; n++) {
         //         XmlNode xmlClass = doc.CreateElement ( "Class" );
         //         XmlElement xmlClassName = doc.CreateElement ( "ClassName" );
         //         xmlClassName.InnerText = parser.Space.Class[n].ClassName;
         //         xmlClass.AppendChild ( xmlClassName );
         //         if (parser.Space.Class[n].Method != null) {
         //            for (int i = 0; i < parser.Space.Class[n].Method.Length; i++) {
         //               XmlElement xmlMethod = doc.CreateElement ( "Method" );
         //               XmlElement xmlmethodName = doc.CreateElement ( "MethodName" );
         //               xmlmethodName.InnerText = parser.Space.Class[n].Method[i].MethodName;
         //               XmlElement xmlAnnotate = doc.CreateElement ( "Annotate" );
         //               xmlAnnotate.InnerText = parser.Space.Class[n].Method[i].Annotate;
         //               xmlMethod.AppendChild ( xmlAnnotate );
         //               xmlMethod.AppendChild ( xmlmethodName );
         //               if (parser.Space.Class[n].Method[i].Parameter != null) {
         //                  for (int j = 0; j < parser.Space.Class[n].Method[i].Parameter.Length; j++) {
         //                     XmlElement xmleleName = doc.CreateElement ( "Param" );
         //                     xmleleName.InnerText = parser.Space.Class[n].Method[i].Parameter[j].ParameterName;
         //                     xmlMethod.AppendChild ( xmleleName );
         //                  }
         //               }
         //               xmlClass.AppendChild ( xmlClassName );
         //               xmlClass.AppendChild ( xmlMethod );
         //            }
         //         }
         //         rootSpace.AppendChild ( xmlClass );
         //      }
         //   }
         //   doc.Save ( fiSave.Name + ".xml" );
      }
   }
}
