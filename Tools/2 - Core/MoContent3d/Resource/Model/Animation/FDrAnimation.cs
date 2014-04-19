using System;
using MO.Common.Collection;
using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;

namespace MO.Content3d.Resource.Model.Animation
{
   //============================================================
   // <T>模型动画。</T>
   //============================================================
   public class FDrAnimation : FObject, IDisposable
   {
      protected FDrModel _model;

      protected int _frameTick;

      protected int _frameCount;

      protected int _frameStart;

      protected int _frameEnd;

      protected int _boneCount;

      protected FVector<FDrFrameInfo> _frameInfos = new FVector<FDrFrameInfo>();

      protected FVector<FDrJoiner> _joiners = new FVector<FDrJoiner>();

      protected FVector<FDrTrack> _tracks = new FVector<FDrTrack>();

      protected FVector<FDrMovie> _movies = new FVector<FDrMovie>();

      //============================================================
      // <T>构造模型动画。</T>
      //============================================================
      public FDrAnimation(FDrModel model) {
         _model = model;
      }

      //============================================================
      public int FrameTick {
         get { return _frameTick; }
      }

      //============================================================
      public int FrameCount {
         get { return _frameCount; }
      }

      //============================================================
      public int FrameStart {
         get { return _frameStart; }
      }

      //============================================================
      public int FrameEnd {
         get { return _frameEnd; }
      }

      //============================================================
      public int BoneCount {
         get { return _boneCount; }
      }
      
      //============================================================
      public FDrTrack FindTrack(int boneId) {
         foreach (FDrTrack track in _tracks) {
            if (track.Bone.AdjustId == boneId) {
               return track;
            }
         }
         return null;
      }

      //============================================================
      public FVector<FDrJoiner> Joiners {
         get { return _joiners; }
      }

      //============================================================
      public FVector<FDrTrack> Tracks {
         get { return _tracks; }
      }

      //============================================================
      public FVector<FDrMovie> Movies {
         get { return _movies; }
      }

      //============================================================
      // <T>测试动画是否有缩放。</T>
      //============================================================
      public bool TestScale() {
         foreach (FDrTrack track in _tracks) {
            if (track.TestScale()) {
               return true;
            }
         }
         return false;
      }
      
      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void LoadModelConfig(FXmlNode config) {
         // 获得属性内容
         _frameCount = config.GetInteger("frame_count");
         _frameTick = config.GetInteger("frame_tick");
         _frameStart = config.GetInteger("frame_start");
         _frameEnd = config.GetInteger("frame_end");
         // 获得跟踪列表
         foreach (FXmlNode node in config.Nodes) {
            if (node.IsName("Track")) {
               // 加载跟踪
               FDrTrack track = new FDrTrack(_model);
               track.LoadModelConfig(node);
               _tracks.Push(track);
               // 判断是否为关联器
               if (track.Bone.Name.StartsWith("jn_")) {
                  FDrJoiner joiner = new FDrJoiner(_model);
                  joiner.Name = track.Bone.Name;
                  joiner.Track = track;
                  _joiners.Push(joiner);
               }
            }
         }
         // 根据内容排序
         _tracks.Sort(new FDrTrack(_model));
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void LoadConfig(FXmlNode config) {
         foreach (FXmlNode node in config.Nodes) {
            if (node.IsName("Frames")) {
               foreach (FXmlNode xframe in node.Nodes) {
                  if (xframe.IsName("Frame")) {
                     FDrFrameInfo frame = new FDrFrameInfo();
                     frame.LoadConfig(xframe);
                     _frameInfos.Push(frame);
                  }
               }
            }else if (node.IsName("Movie")) {
               FDrMovie movie = new FDrMovie();
               movie.LoadConfig(node);
               _movies.Push(movie);
            }
         }
      }

      //============================================================
      // <T>存储配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void SaveConfig(FXmlNode config) {
         foreach (FDrMovie movie in _movies) {
            movie.SaveConfig(config.CreateNode("Movie"));
         }
      }
      
      //============================================================
      public void SaveSimpleConfig(FXmlNode config) {
         config.Set("frame_tick", _frameTick);
         config.Set("frame_start", _frameStart);
         config.Set("frame_end", _frameEnd);
         // 存储跟踪列表
         foreach (FDrTrack track in _tracks) {
            FXmlNode trackNode = config.CreateNode("Track");
            track.SaveSimpleConfig(trackNode);
         }
      }
      
      //============================================================
      public void Serialize(IOutput output) {
         // 输出设置信息
         output.WriteInt16((short)(_frameCount + 1));
         output.WriteInt16((short)_frameTick);
         output.WriteInt32(_frameEnd - _frameStart);
         // 输出帧信息列表
         //output.WriteInt16((short)_frameInfos.Count);
         //foreach (FDrFrameInfo frame in _frameInfos) {
         //   frame.Serialize(output);
         //}
         // 输出跟踪列表
         //output.WriteInt16((short)_joiners.Count);
         //foreach (FDrJoiner joiner in _joiners) {
         //   joiner.Serialize(output);
         //}
         // 输出跟踪列表
         output.WriteInt16((short)_tracks.Count);
         foreach (FDrTrack track in _tracks) {
            track.Serialize(output);
         }
         // 输出动画列表
         output.WriteInt16((short)_movies.Count);
         foreach (FDrMovie movie in _movies) {
            movie.Serialize(output);
         }
      }

      //============================================================
      // <T>释放内容。</T>
      //============================================================
      public void Dispose() {
         _tracks.Clear();
         _movies.Clear();
      }
   }
}
