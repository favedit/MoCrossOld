using MO.Common.IO;
using MO.Common.Lang;

namespace MO.Content3d.Resource.Model.Animation
{
   //============================================================
   // <T>模型跟踪信息。</T>
   //============================================================
   public class FDrJoiner : FObject
   {
      // 日志输出接口
      private static ILogger _logger = RLogger.Find(typeof(FDrJoiner));

      // 模型
      protected FDrModel _model;

      // 名称
      protected string _name;

      // 跟踪
      protected FDrTrack _track;

      //============================================================
      // <T>构造模型跟踪信息。</T>
      //============================================================
      public FDrJoiner(FDrModel model) {
         _model = model;
      }

      //============================================================
      public FDrModel Model {
         get { return _model; }
      }

      //============================================================
      public string Name {
         get { return _name; }
         set { _name = value; }
      }

      //============================================================
      public FDrTrack Track {
         get { return _track; }
         set { _track = value; }
      }

      //============================================================
      public void Serialize(IOutput output) {
         output.WriteString(_name);
         output.WriteInt32(_track.AdjustId);
      }
   }
}
