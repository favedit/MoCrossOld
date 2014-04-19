using MO.Common.Collection;
using MO.Common.Content;
using MO.Common.System;

namespace MO.DX.Core.Resource
{
   //============================================================
   public class FDxRsModelConsole : FConsole
   {
      protected string _pathSource;

      protected string _pathExport;

      protected FDictionary<FDxRsModel> _models = new FDictionary<FDxRsModel>();

      //============================================================
      public FDxRsModelConsole() {
      }

      //============================================================
      public string MakeFileName(string code) {
         return _pathExport + @"\md_" + code + ".swf";
      }

      //============================================================
      public FDxRsModel Get(string code) {
         FDxRsModel model = _models.Find(code);
         if(null == model) {
            model = new FDxRsModel();
            string fileName = MakeFileName(code);
            model.LoadFile(fileName);
            _models.Set(code, model);
         }
         return model;
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
                  case "export":
                     _pathExport = xnode.Text;
                     break;
               }
            }
         }
      }
   }
}
