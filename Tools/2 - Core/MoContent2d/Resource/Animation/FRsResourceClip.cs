using MO.Common.Content;
using MO.Common.Geom;
using MO.Common.IO;
using MO.Common.Lang;
using System;

namespace MO.Content2d.Resource.Animation
{
   //============================================================
   // <T>资源剪辑。</T>
   //============================================================
   public class FRsResourceClip : FObject, IDisposable
   {
      // 动画对象
      protected FRsResourceAnimation _animation;

      // 是否已经打开
      protected bool _opened;

      // 方向
      protected ERsDirection _directionCd;

      // 反转方向
      protected ERsDirection _reverseDirection;

      // 有效重心
      protected SIntPoint2 _validBarycenter = new SIntPoint2();

      // 血条位置
      protected SIntPoint2 _bloodPoint = new SIntPoint2();

      // 乘骑点
      protected SIntPoint2 _ridePoint = new SIntPoint2();

      // 帧集合
      protected FObjects<FRsResourceFrame> _frames = new FObjects<FRsResourceFrame>();

      // 有效范围
      protected SIntRectangle _validRectangle = new SIntRectangle();

      // 是否反转
      protected bool _isReversed;

      // 反转方式
      protected ERsReverse _reverseCd;

      // 默认帧速 (未定)
      protected int _frameDelay = 200;

      // 读取加载控件
      public ERsIsCheckd _isCheckd;

      //============================================================
      // <T>资源剪辑对象。</T>
      //============================================================
      public FRsResourceClip() {
      }

      //============================================================
      // <T>获得或设置资源动画。</T>
      //============================================================
      public FRsResourceAnimation Animation {
         get { return _animation; }
         set { _animation = value; }
      }

      //============================================================
      // <T>获得或设置方向。</T>
      //============================================================
      public ERsDirection DirectionCd {
         get { return _directionCd; }
         set { _directionCd = value; }
      }

      //============================================================
      // <T>获得或设置反转方向。</T>
      //============================================================
      public ERsDirection ReverseDirection {
         get { return _reverseDirection; }
         set { _reverseDirection = value; }
      }

      //============================================================
      // <T>获得或设置是否反转。</T>
      //============================================================
      public bool IsReversed {
         get { return _isReversed; }
         set { _isReversed = value; }
      }

      //============================================================
      // <T>获得反转方式。</T>
      //============================================================
      public ERsReverse ReverseCd {
         get { return _reverseCd; }
         set { _reverseCd = value; }
      }

      //============================================================
      // <T>获得有效尺寸。</T>
      //============================================================
      public SIntRectangle ValidRectangle {
         get { return _validRectangle; }
      }

      //============================================================
      // <T>读取或者设置有效重心。</T>
      //============================================================
      public SIntPoint2 ValidBarycenter {
         get { return _validBarycenter; }
      }
      
      //============================================================
      // <T>读取或者设置血条位置。</T>
      //============================================================
      public SIntPoint2 Blood {
         get { return _bloodPoint; }
      }

      //============================================================
      // <T>读取或者设置乘骑点位置。</T>
      //============================================================
      public SIntPoint2 RidePoint {
         get { return _ridePoint; }
      }

      //============================================================
      // <T>读取帧个数。</T>
      //============================================================
      public int FrameCount {
         get { return _frames.Count; }
      }

      //============================================================
      // <T>获得帧总时间。</T>
      //============================================================
      public int FrameDelayTotal {
         get {
            int delay = 0;
            foreach(FRsResourceFrame frame in _frames) {
               delay += frame.Delay;
            }
            return delay;
         }
      }

      //============================================================
      // <T>读取或者设置默认帧速。</T>
      //============================================================
      public int FrameDelay {
         set {
            foreach(FRsResourceFrame frame in _frames) {
               frame.Delay = value;
            }
         }
         get { return _frameDelay; }
      }

      //============================================================
      // <T>获得帧集合。</T>
      //============================================================
      public FObjects<FRsResourceFrame> Frames {
         get { return _frames; }
      }

      //==============================================================================
      // <T>当选中自定义控件帧时发生。</T>
      //
      // @autor DYWEN 20120604
      //==============================================================================
      public ERsIsCheckd Checkd {
         get { return _isCheckd; }
         set { _isCheckd = value; }
      }

      //============================================================
      // <T>增加帧。</T>
      //
      // @param frame 帧
      //============================================================
      public void PushFrame(FRsResourceFrame frame) {
         frame.Animation = _animation;
         frame.Clip = this;
         frame.Index = _frames.Count;
         _frames.Push(frame);
      }

      //============================================================
      // <T>加载设置信息。</T>
      //
      // @param xconfig 设置节点
      //============================================================
      public ERsDirection IntToDirction(int reverseDirection) {
         switch (reverseDirection) {
            case 0:
               return ERsDirection.All;
            case 1:
               return ERsDirection.RightDown;
            case 2:
               return ERsDirection.Down;
            case 3:
               return ERsDirection.LeftDown;
            case 4:
               return ERsDirection.Left;
            case 5:
               return ERsDirection.LeftUp;
            case 6:
               return ERsDirection.Up;
            case 7:
               return ERsDirection.RightUp;
            case 8:
               return ERsDirection.Right;
            default:
               return ERsDirection.All;
         }
      }

      //============================================================
      // <T>序列化剪辑的帧间隔信息。</T>
      //
      // @param output 输出流
      //============================================================
      public void SerializeDelay(IOutput output) {
         foreach (FRsResourceFrame frame in _frames) {
            output.WriteUint16((ushort)frame.Delay);
         }
      }

      //============================================================
      // <T>序列化剪辑数据。</T>
      //
      // @param output 输出流
      //============================================================
      public void Serialize(IOutput output) {
         // 写入属性
         output.WriteUint8((byte)_directionCd);
         output.WriteUint16((ushort)_validRectangle.Width);
         output.WriteUint16((ushort)_validRectangle.Height);
         _validBarycenter.Serialize16(output);
         output.WriteInt32(FrameDelayTotal);
         // 写入帧总数
         int frameCount = _frames.Count;
         output.WriteUint8((byte)frameCount);
         // 写入帧集合
         foreach (FRsResourceFrame frame in _frames) {
            frame.Serialize(output);
         }
      }

      //============================================================
      // <T>反序列化数据信息。</T>
      //
      // @param input 输入流
      //============================================================
      public void Unserialize(IInput input) {
         // 读取帧集合
         _frames.Clear();
         int frameCount = input.ReadUint16();
         for (int n = 0; n < frameCount; n++) {
         }
      }

      //============================================================
      // <T>加载设置信息。</T>
      //
      // @param xconfig 设置节点
      //============================================================
      public void LoadConfig(FXmlNode xconfig) {
         // 读取属性
         _isReversed = xconfig.GetBoolean("is_reversed", _isReversed);
         if (xconfig.Contains("reverse_direction")) {
            int reverseDirection = xconfig.GetInteger("reverse_direction");
            _reverseDirection = IntToDirction(reverseDirection);
         }
         _frameDelay = xconfig.GetInteger("frame_delay", _frameDelay);
         // 读取剪辑集合
         FXmlNode xframes = xconfig.Find("Frames");
         if(xframes != null) {
            foreach(FXmlNode xnode in xframes.Nodes) {
               if(xnode.IsName("Frame")) {
                  int index = xnode.GetInteger("index");
                  FRsResourceFrame frame = _frames.Get(index, null);
                  if(frame != null) {
                     frame.LoadConfig(xnode);
                  }
               }
            }
         }
      }

      //============================================================
      // <T>保存设置信息。<T>      
      // 
      // @param xconfig 设置节点
      //============================================================
      public void SaveConfig(FXmlNode xconfig) {
         xconfig.Set("is_reversed",_isReversed);
         // 设置剪辑反转方向
         xconfig.Set("reverse_direction", (int)_reverseDirection);
         // 设置方向
         xconfig.Set("direction_cd", (int)_directionCd);
         // 设置有效重心
         xconfig.Set("valid_barycenter", _validBarycenter.ToString());
         // 设置延时
         xconfig.Set("frame_delay", _frameDelay);
         //设置相关信息
         xconfig.Set("blood", _bloodPoint.ToString());
         xconfig.Set("ride_point", _ridePoint.ToString());
         xconfig.Set("is_check", _isCheckd.ToString());
         // 读取帧数
         FXmlNode xframes = xconfig.CreateNode("Frames");
         xframes.Set("count", _frames.Count);
         // 读取剪辑集合
         foreach(FRsResourceFrame frame in _frames) {
            frame.SaveConfig(xframes.CreateNode("Frame"));
         }
      }

      //============================================================
      // <T>打开资源。</T>
      //============================================================
      public void Open() {
         if(!_opened) {
            // 打开所有帧
            foreach(FRsResourceFrame frame in _frames) {
               frame.Open();
            }
            // 获得最小左 上点和最大右下点
            int left = int.MaxValue;
            int top = int.MaxValue;
            int right = 0;
            int bottom = 0;
            foreach(FRsResourceFrame frame in _frames) {
               SIntRectangle rect = frame.ValidRectangle;
               if(!rect.IsEmpty) {
                  if(rect.Left < left) {
                     left = rect.Left;
                  }
                  if(rect.Top < top) {
                     top = rect.Top;
                  }
                  if(rect.Right > right) {
                     right = rect.Right;
                  }
                  if(rect.Bottom > bottom) {
                     bottom = rect.Bottom;
                  }
               }
            }
            _validRectangle.Set(left, top, right - left, bottom - top);
            // 获得剪辑重心
            _validBarycenter.X = _frames[0].Bitmap.Width / 2 - left;
            _validBarycenter.Y = _frames[0].Bitmap.Height / 2 - top;
            _opened = true;
         }
      }

      //============================================================
      // <T>释放资源。</T>
      //============================================================
      public void Dispose() {
         if(_opened) {
            foreach(FRsResourceFrame frame in _frames) {
               frame.Dispose();
            }
            _opened = false;
         }
      }
   }
}
