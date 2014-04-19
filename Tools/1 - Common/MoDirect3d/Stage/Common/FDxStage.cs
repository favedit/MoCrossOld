using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MO.Common.Lang;
using MO.DX.Core.Stage.Scene;

namespace MO.DX.Core.Stage.Common
{
   //============================================================
   public class FDxStage : FObject
   {
      protected FDxScene _activeScene;

      //============================================================
      public FDxStage() {
      }

      //============================================================
      public FDxScene ActiveScene {
         get { return _activeScene; }
         set { _activeScene = value; }
      }

      //============================================================
      public void Process() {
         if (null != _activeScene) {
            _activeScene.Process();
         }
      }
   }
}
