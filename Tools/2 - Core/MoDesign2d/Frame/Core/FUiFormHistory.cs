using MO.Common.Lang;

namespace MO.Design2d.Frame.Core
{
   public class FUiFormHistory : FObject
   {
      protected FObjects<FUiFormHistoryStep> _steps = new FObjects<FUiFormHistoryStep>();

      public FUiFormHistory() {
      }

      public FObjects<FUiFormHistoryStep> Steps {
         get { return _steps; }
      }

      public FUiFormHistoryStep Last {
         get {
            if(!_steps.IsEmpty()) {
               return _steps.Last;
            }
            return null;
         }
      }
   }
}
