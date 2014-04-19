using MO.Common.Collection;
using MO.Common.Content;
using MO.Common.System;

namespace MO.DX.Core.Resource
{
   //============================================================
   public class FDxRsTemplateConsole : FConsole
   {
      protected string _pathSource;

      protected string _pathExport;

      protected FDictionary<FDxRsTemplate> _templates = new FDictionary<FDxRsTemplate>();

      //============================================================
      public FDxRsTemplateConsole() {
      }

      //============================================================
      public string MakeFileName(string code) {
         return _pathExport + @"\tp_" + code + ".swf";
      }

      //============================================================
      public FDxRsTemplate Get(string code) {
         FDxRsTemplate template = _templates.Find(code);
         if(null == template) {
            template = new FDxRsTemplate();
            string fileName = MakeFileName(code);
            template.LoadFile(fileName);
            _templates.Set(code, template);
         }
         return template;
      }

      //============================================================
      public override void LoadConfig(FXmlNode xconfig) {
         foreach (FXmlNode xnode in xconfig.Nodes){
            if(xnode.IsName("Property")){
               string name = xnode["name"];
               switch(name){
                  case "path":
                     _pathSource = xnode.Text;
                     break;
                  case "export.template":
                     _pathExport = xnode.Text;
                     break;
               }
            }
         }
      }
   }
}
