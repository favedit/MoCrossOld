using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MO.Common.System;
using MO.Common.Collection;
using MO.Common.Content;

namespace MO.DX.Core.Resource
{
   //============================================================
   public class FDxRsTextureConsole : FConsole
   {
      protected string _pathSource;

      protected string _pathExport;

      protected FDictionary<FDxRsTexturePack> _textures = new FDictionary<FDxRsTexturePack>();

      //============================================================
      public FDxRsTextureConsole() {
      }

      //============================================================
      public string MakeFileName(string code) {
         return _pathExport + @"\tx_" + code + ".swf";
      }

      //============================================================
      public FDxRsTexturePack Get(string code) {
         FDxRsTexturePack texture = _textures.Find(code);
         if(null == texture) {
            texture = new FDxRsTexturePack();
            string fileName = MakeFileName(code);
            texture.LoadFile(fileName);
            _textures.Set(code, texture);
         }
         return texture;
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
