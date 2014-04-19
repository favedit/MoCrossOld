using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MO.Common.System;

namespace MO.DX.Core.Stage.Scene
{
   //============================================================
   public class FDxSceneConsole : FConsole
   {
      protected FDxScene _activeScene;

      //============================================================
      public FDxSceneConsole() {
      }

      //============================================================
      public FDxScene ActiveScene {
         get { return _activeScene; }
         set { _activeScene = value; }
      }

      //============================================================
      public void Process() {
      }
   }
}
