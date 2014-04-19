using System;
using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Common.System;

namespace MO.Core.Logic.Track
{
   //============================================================
   // <T>跟踪控制台。</T>
   //============================================================
   public class FTrackConsole : FConsole
   {
      protected FStringFile _file = new FStringFile();

      protected FObjects<FTrack> _tracks = new FObjects<FTrack>();

      //============================================================
      // <T>构造跟踪控制台。</T>
      //============================================================
      public FTrackConsole() {
      }

      //============================================================
      public FObjects<FTrack> Tracks {
         get { return _tracks; }
      }

      //============================================================
      // <T>加载设置信息。</T>
      //
      // @param xconfig 设置节点
      //============================================================
      public override void LoadConfig(FXmlNode xconfig) {
      }

      //============================================================
      // <T>输出日志信息。</T>
      //
      // @param message 信息
      // @param parameters 参数集合
      //============================================================
      public void Write(object sender, string method, string message, params object[] parameters) {
         string line = String.Format(message, parameters);
         // 放入跟踪
         FTrack track = new FTrack();
         track.Datetime = DateTime.Now;
         track.Sender = sender + "." + method;
         track.Message = line;
         lock (_tracks) {
            _tracks.Push(track);
         }
         // 放入文件
         _file.AppendLine(line);
      }

      //============================================================
      // <T>关闭日志。</T>
      //============================================================
      public void Close() {
         _file.SaveFile("D:\\track.txt");
      }
   }
}
