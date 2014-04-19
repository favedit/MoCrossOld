using System;
using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content3d.Resource.Common;

namespace MO.Content3d.Resource.Template
{
   //============================================================
   // <T>模板动画动作。</T>
   //============================================================
   public class FDrTemplateAnimationMovie : FObject
   {
      // 名称
      protected string _name = String.Empty;

      // 播放方式
      protected int _playCd = EDrMoviePlay.Loop;

      // 开始帧
      protected int _frameBegin;

      // 结束帧
      protected int _frameEnd;

      // 帧速
      protected float _frameRate = 1.0f;

      //============================================================
      // <T>构造模板动画动作。</T>
      //============================================================
      public FDrTemplateAnimationMovie() {
      }

      //============================================================
      // <T>获得或者设置名称。</T>
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
      // <T>获得或者设置开始帧。</T>
      //============================================================
      public int FrameBegin {
         get { return _frameBegin; }
         set { _frameBegin = value; }
      }

      //============================================================
      // <T>获得或者设置结束帧。</T>
      //============================================================
      public int FrameEnd {
         get { return _frameEnd; }
         set { _frameEnd = value; }
      }

      //============================================================
      // <T>获得或者设置帧速。</T>
      //============================================================
      public float FrameRate {
         get { return _frameRate; }
         set { _frameRate = value; }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void LoadConfig(FXmlNode xconfig) {
         _name = xconfig.Nvl("name");
         _playCd = xconfig.GetInteger("play_cd", _playCd);
         _frameBegin = xconfig.GetInteger("frame_begin");
         _frameEnd = xconfig.GetInteger("frame_end");
         if (xconfig.Contains("rate")) {
            _frameRate = xconfig.GetFloat("rate");
         }
         if (xconfig.Contains("frame_rate")) {
            _frameRate = xconfig.GetFloat("frame_rate");
         }
      }

      //============================================================
      // <T>存储配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void SaveConfig(FXmlNode xconfig) {
         xconfig.Set("name", _name);
         xconfig.Set("play_cd", _playCd);
         xconfig.Set("frame_begin", _frameBegin);
         xconfig.Set("frame_end", _frameEnd);
         xconfig.Set("frame_rate", _frameRate);
      }

      //============================================================
      // <T>序列化内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public void Serialize(IOutput output) {
         output.WriteString(_name);
         output.WriteInt32(_playCd);
         output.WriteInt32(_frameBegin);
         output.WriteInt32(_frameEnd);
         output.WriteFloat(_frameRate);
      }
   }
}
