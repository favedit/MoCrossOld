using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MO.Common.Content;
using MO.DX.Core;

namespace MO.Design3d
{
   public class RDesign3dManager
   {
      //============================================================
      public static void LoadConfig(FXmlNode xconfig) {
         foreach(FXmlNode xnode in xconfig.Nodes) {
            string name = xnode["name"];
            switch(name) {
               case "resource.texture":
                  //_textureResourceConsole.LoadConfig(xnode);
                  break;
               case "resource.model":
                  //_modelResourceConsole.LoadConfig(xnode);
                  break;
               case "resource.template":
                  //_templateResourceConsole.LoadConfig(xnode);
                  break;
               case "resource.effect":
                  //_effectConsole.LoadConfig(xnode);
                  break;
            }
         }
      }

      //============================================================
      public static void Setup() {
         RDxCore.Factory = new FDxDesignFactory();
      }
   }
}
