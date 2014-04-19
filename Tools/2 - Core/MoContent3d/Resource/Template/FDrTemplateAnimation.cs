using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content3d.Resource.Model;

namespace MO.Content3d.Resource.Template
{
   //============================================================
   // <T>模板动画。</T>
   //============================================================
   public class FDrTemplateAnimation : FObject
   {
      protected FDrModel _model;

      protected int _frameTick;

      protected int _frameCount;

      protected int _frameStart;

      protected int _frameEnd;
      
      // 动作
      protected FObjects<FDrTemplateAnimationMovie> _movies = new FObjects<FDrTemplateAnimationMovie>();

      //============================================================
      // <T>构造模板动画。</T>
      //============================================================
      public FDrTemplateAnimation() {
      }

      //============================================================
      public FDrModel Model{
         get { return _model; }
         set { _model = value; }
      }

      //============================================================
      public int FrameTick {
         get { return _frameTick; }
         set { _frameTick = value; }
      }

      //============================================================
      public int FrameCount {
         get { return _frameCount; }
         set { _frameCount = value; }
      }

      //============================================================
      public int FrameStart {
         get { return _frameStart; }
         set { _frameStart = value; }
      }

      //============================================================
      public int FrameEnd {
         get { return _frameEnd; }
         set { _frameEnd = value; }
      }
      
      //============================================================
      // <T>获得是否为空。</T>
      //============================================================
      public bool IsEmpty {
         get { return _movies.IsEmpty(); }
      }

      //============================================================
      // <T>获得动作集合。</T>
      //============================================================
      public FObjects<FDrTemplateAnimationMovie> Movies {
         get { return _movies; }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void LoadConfig(FXmlNode xconfig) {
         foreach(FXmlNode xnode in xconfig.Nodes) {
            if(xnode.IsName("Movie")) {
               FDrTemplateAnimationMovie movie = new FDrTemplateAnimationMovie();
               movie.LoadConfig(xnode);
               _movies.Push(movie);
            }
         }
      }

      //============================================================
      // <T>存储配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void SaveConfig(FXmlNode xconfig) {
         foreach(FDrTemplateAnimationMovie movie in _movies) {
            movie.SaveConfig(xconfig.CreateNode("Movie"));
         }
      }

      //============================================================
      // <T>序列化内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public void Serialize(IOutput output) {
         if (null != _model) {
            _model.Open();
            if (null != _model.Animation) {
               _frameCount = _model.Animation.FrameCount;
               _frameStart = _model.Animation.FrameStart;
               _frameEnd = _model.Animation.FrameEnd;
               _frameTick = _model.Animation.FrameTick;
            }
         }
         output.WriteInt32(_frameCount);
         output.WriteInt32(_frameTick);
         output.WriteInt32(_movies.Count);
         foreach(FDrTemplateAnimationMovie movie in _movies) {
            movie.Serialize(output);
         }
      }
   }
}
