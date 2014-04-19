using System.Windows.Forms;
using MO.Common.Lang;

namespace MO.Core.Logic.Track
{
   //============================================================
   // <T>获得跟踪表单。</T>
   //============================================================
   public partial class QTrackForm : Form
   {
      // 跟踪表单
      protected static QTrackForm _instance;

      //============================================================
      // <T>获得跟踪表单单件。</T>
      //============================================================
      public static QTrackForm Instance {
         get {
            if(null == _instance) {
               _instance = new QTrackForm();
            }
            return _instance;
         }
      }

      //============================================================
      // <T>构造跟踪表单。</T>
      //============================================================
      public QTrackForm() {
         InitializeComponent();
      }

      //============================================================
      // <T>打开操作。</T>
      //============================================================
      public void Open() {
         timRefresh.Enabled = true;
      }

      //============================================================
      // <T>刷新器。</T>
      //============================================================
      private void timRefresh_Tick(object sender, System.EventArgs e) {
         FObjects<FTrack> tracks = RMoCore.TrackConsole.Tracks;
         FTrack[] trackItems = null;
         lock (tracks) {
            trackItems = tracks.ToArray();
            tracks.Clear();
         }
         if (trackItems.Length > 0) {
            lvwTracks.BeginUpdate();
            foreach (FTrack track in trackItems) {
               if (null != track) {
                  ListViewItem lviTrack = new ListViewItem();
                  lviTrack.Text = track.Datetime.ToString();
                  lviTrack.SubItems.Add(track.Sender);
                  lviTrack.SubItems.Add(track.Message);
                  lvwTracks.Items.Add(lviTrack);
               }
            }
            lvwTracks.EndUpdate();
            Show();
            BringToFront();
         }
      }

      //============================================================
      // <T>保存事件处理。</T>
      //============================================================
      private void tsbSave_Click(object sender, System.EventArgs e) {
      }

      //============================================================
      // <T>清除事件处理。</T>
      //============================================================
      private void tspClear_Click(object sender, System.EventArgs e) {
         lvwTracks.BeginUpdate();
         lvwTracks.Items.Clear();
         lvwTracks.EndUpdate();
      }

      //============================================================
      // <T>关闭前事件处理。</T>
      //============================================================
      private void QTrackForm_FormClosing(object sender, FormClosingEventArgs e) {
         e.Cancel = true;
         Hide();
      }

      //============================================================
      // <T>关闭事件处理。</T>
      //============================================================
      private void tsbClose_Click(object sender, System.EventArgs e) {
         Close();
      }
   }
}
