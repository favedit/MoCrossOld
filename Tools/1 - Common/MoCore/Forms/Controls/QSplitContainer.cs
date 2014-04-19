using System.Windows.Forms;

namespace MO.Core.Forms.Controls
{

   public class QSplitContainer : SplitContainer{

      public const string PTY_SPLITTERDISTANCE = "splitter_distance";

      public QSplitContainer() {
      }

      #region IControlConfig members

      //public void ControlLoadConfig(FControlConfig config) {
      //   if (config.Config.Contains(PTY_SPLITTERDISTANCE)) {
      //      int sd = config.Config.GetInteger(PTY_SPLITTERDISTANCE);
      //      if (sd > 0) {
      //         SplitterDistance = sd;
      //      }
      //   }
      //}

      //public void ControlSaveConfig(FControlConfig config) {
      //   config.Config[PTY_SPLITTERDISTANCE] = SplitterDistance.ToString();
      //}

      #endregion
   }

}
