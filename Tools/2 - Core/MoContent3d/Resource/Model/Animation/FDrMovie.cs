using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content3d.Resource.Common;

namespace MO.Content3d.Resource.Model.Animation
{
   //============================================================
   // <T>动画信息。</T>
   //============================================================
   public class FDrMovie : FObject
   {
      // 名车
      protected string _name;

      // 播放方式
      protected int _playCd = EDrMoviePlay.Loop;

      // 帧开始
      protected int _frameBegin;

      // 帧结束
      protected int _frameEnd;

      // 比率
      protected float _rate;

      //============================================================
      // <T>获得或设置动画信息。</T>
      //============================================================
      public FDrMovie(){
      }

      //============================================================
      // <T>获得或设置名称。</T>
      //============================================================
      public string Name {
         get { return _name; }
         set { _name = value; }
      }

      //============================================================
      // <T>获得或设置播放方式。</T>
      //============================================================
      public int PlayCd {
         get { return _playCd; }
         set { _playCd = value; }
      }

      //============================================================
      // <T>获得或设置帧开始。</T>
      //============================================================
      public int FrameBegin {
         get { return _frameBegin; }
         set { _frameBegin = value; }
      }

      //============================================================
      // <T>获得或设置帧结束。</T>
      //============================================================
      public int FrameEnd {
         get { return _frameEnd; }
         set { _frameEnd = value; }
      }

      //============================================================
      // <T>获得或设置帧比率。</T>
      //============================================================
      public float Rate {
         get { return _rate; }
         set { _rate = value; }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param config 配置信息x
      //============================================================
      public void LoadConfig(FXmlNode config) {
         _name = config.Get("name");
         _playCd = config.GetInteger("play_cd", _playCd);
         _frameBegin = config.GetInteger("frame_begin");
         _frameEnd = config.GetInteger("frame_end");
         if (config.Contains("frame_rate")) {
            _rate = config.GetFloat("frame_rate");
         } else {
            _rate = config.GetFloat("rate");
         }
      }

      //============================================================
      // <T>存储配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void SaveConfig(FXmlNode config) {
         config.Set("name", _name);
         config.Set("play_cd", _playCd);
         config.Set("frame_begin", _frameBegin);
         config.Set("frame_end", _frameEnd);
         config.Set("rate", _rate);
      }

      //============================================================
      // <T>序列化数据内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public void Serialize(IOutput output) {
         // 输出设置信息
         //output.WriteString(_name);
         output.WriteInt8((sbyte)_playCd);
         output.WriteInt16((short)_frameBegin);
         output.WriteInt16((short)_frameEnd);
         output.WriteFloat(_rate);
      }
   }
}
