using MO.Common.Content;
using MO.Common.Lang;
using MO.Common.IO;

namespace MO.Content3d.Resource.Model.Animation
{
   //============================================================
   // <T>帧信息。</T>
   //============================================================
   public class FDrFrameInfo : FObject
   {
      // 索引
      protected int _index;

      // 透明度
      protected int _alpha;

      //============================================================
      public int Index {
         get { return _index; }
         set { _index = value; }
      }

      //============================================================
      public int Alpha {
         get { return _alpha; }
         set { _alpha = value; }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param config 配置信息x
      //============================================================
      public void LoadConfig(FXmlNode config) {
         _index = config.GetInteger("index");
         _alpha = config.GetInteger("alpha");
      }

      //============================================================
      public void Serialize(IOutput output) {
         output.WriteInt16((short)_index);
         output.WriteInt16((short)_alpha);
      }
   }
}
