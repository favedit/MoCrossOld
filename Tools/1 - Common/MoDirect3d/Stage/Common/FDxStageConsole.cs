using MO.Common.Lang;
using MO.Common.System;

namespace MO.DX.Core.Stage.Common
{
   //============================================================
   public class FDxStageConsole : FConsole
   {
      protected FDxStage _activeStage;

      protected FObjects<FDxStage> _stages = new FObjects<FDxStage>();

      //============================================================
      public FDxStageConsole() {
      }

      //============================================================
      public FDxStage ActiveStage {
         get { return _activeStage; }
         set { _activeStage = value; }
      }

      //============================================================
      public void Register(FDxStage stage) {
         _stages.Push(stage);
      }

      //============================================================
      public void Unregister(FDxStage stage) {
         _stages.Remove(stage);
      }

      //============================================================
      public void Process() {
         if (null != _activeStage) {
            _activeStage.Process();
         }
      }
   }
}
