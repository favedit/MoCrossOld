using MO.Common.Content;
using MO.Common.Geom;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content2d.Common;
using MO.Content2d.Core;
using MO.Core.Content.Drawing;
using System;
using System.Drawing;
using System.Drawing.Imaging;
using System.IO;

namespace MO.Content2d.Resource.Animation
{
   //============================================================
   // <T>资源帧对象。</T>
   //============================================================
   public class FRsResourceFrame : FObject, IDisposable
   {
      // 日志输出接口
      private static ILogger _logger = RLogger.Find(typeof(FRsResourceFrame));

      // 资源动画
      protected FRsResourceAnimation _animation;

      // 资源剪辑
      protected FRsResourceClip _clip;

      // 是否已经打开
      protected bool _opened;

      // 文件名称
      protected string _fileName;

      // 索引位置
      protected int _index;

      // 延时
      protected int _delay = 200;

      // 图片大小
      protected SIntSize _size = new SIntSize();

      // 有效范围
      protected SIntRectangle _validRectangle = new SIntRectangle();

      // 有效区域内重心
      protected SIntPoint2 _validBarycenter  = new SIntPoint2();

      // 有效图像
      protected FBitmap _validBitmap;

      // 合并位置
      protected SIntPoint2 _mergeLocation = new SIntPoint2();

      // 有效图像
      protected FBitmap _bitmap;

      //============================================================
      // <T>资源帧对象。</T>
      //============================================================
      public FRsResourceFrame() {
      }

      //============================================================
      // <T>获得或设置资源动画。</T>
      //============================================================
      public FRsResourceAnimation Animation {
         get { return _animation; }
         set { _animation = value; }
      }
      
      //============================================================
      // <T>获得或设置资源剪辑。</T>
      //============================================================
      public FRsResourceClip Clip {
         get { return _clip; }
         set { _clip = value; }
      }

      //============================================================
      // <T>获得图形。</T>
      //============================================================
      public FBitmap Bitmap {
         get { return _bitmap; }
      }

      //============================================================
      // <T>获得有效图形。</T>
      //============================================================
      public Bitmap ValidBitmap {
         get { return _validBitmap.Native; }
      }

      //============================================================
      // <T>获得文件路径。</T>
      //============================================================
      public string FilePath {
         get { return _fileName.Substring(_fileName.LastIndexOf("\\") + 1); }
      }

      //============================================================
      // <T>获得文件名称。</T>
      //============================================================
      public string FileName {
         get { return _fileName; }
         set { _fileName = value; }
      }

      //============================================================
      // <T>获得或设置索引位置。</T>
      //============================================================
      public int Index {
         get { return _index; }
         set { _index = value; }
      }

      //============================================================
      // <T>获得或设置延时。</T>
      //============================================================
      public int Delay {
         get { return _delay; }
         set { _delay = value; }
      }

      //============================================================
      // <T>获得图片大小。</T>
      //============================================================
      public SIntSize Size {
         get { return _size; }
      }

      //============================================================
      // <T>获得有效范围。</T>
      //============================================================
      public SIntRectangle ValidRectangle {
         get { return _validRectangle; }
      }

      //============================================================
      // <T>获得有效重心。</T>
      //============================================================
      public SIntPoint2 ValidBarycenter {
         get { return _validBarycenter; }
      }

      //============================================================
      // <T>获得合并位置。</T>
      //============================================================
      public SIntPoint2 MergeLocation {
         get { return _mergeLocation; }
      }

      //============================================================
      // <T>获得图片类型。</T>
      //============================================================
      public string BitmapTypeCd {
         get {
            int count = 0;
            if (_animation.OptionPadding) {
               count = _size.Square;
            } else {
               count = _validRectangle.Square;
            }
            int pixelCount = ERsResourceQuality.ParseBitmapPixel(_animation.QualityCd);
            //return (count > pixelCount) ? ERsBitmap.Jpg : ERsBitmap.Index;
            return (count > pixelCount) ? ERsBitmap.BlockIndex : ERsBitmap.Index;
         }
      }

      //============================================================
      // <T>加载设置信息。</T>
      //
      // @param xconfig 设置节点
      //============================================================
      public void LoadConfig(FXmlNode config) {
         // 读取延时
         if(config.Contains("delay")) {
            _delay = config.GetInteger("delay");
         }
      }

      //============================================================
      // <T>保存设置信息。<T>      
      // 
      // @param xconfig 设置节点
      //============================================================
      public void SaveConfig(FXmlNode xconfig) {
         // 设置索引
         xconfig.Set("index", _index);
         // 设置延时
         xconfig.Set("delay", _delay);
         // 设置图片大小
         xconfig.Set("size", _size.ToString());
         // 设置有效大小
         xconfig.Set("valid_rectangle", _validRectangle.ToString());
         // 设置有效重心
         xconfig.Set("valid_barycenter", _validBarycenter.ToString());
         // 设置文件名称
         xconfig.Set("file_name", RFile.GetFileName(_fileName));
      }

      //============================================================
      // <T>序列化帧数据。</T>
      //
      // @param output 输出流
      //============================================================
      public void Serialize(IOutput output) {
         // 写入属性
         bool optionEmpty = _validRectangle.IsEmpty;
         output.WriteBool(optionEmpty);
         output.WriteInt32(_delay);
         // 写入位图
         if (!optionEmpty) {
            // 写入大小
            int width = _bitmap.Width;
            int height = _bitmap.Height;
            output.WriteUint16((ushort)width);
            output.WriteUint16((ushort)height);
            // 写入范围
            _validRectangle.Serialize16(output);
            // 写入有效重心点
            _validBarycenter.Serialize16(output);
            // 写入合并位置
            _mergeLocation.Serialize16(output);
         }
      }

      //============================================================
      // <T>反序列化数据信息。</T>
      //
      // @param input 输入流
      //============================================================
      public void Unserialize(IInput input) {
      }

      //============================================================
      // <T>打开资源。</T>
      //============================================================
      public void Open() {
         if(!_opened) {
            // 加载图片
            _bitmap = new FBitmap(_fileName);
            _size.Width = _bitmap.Width;
            _size.Height = _bitmap.Height;
            // 获取有效范围
            _bitmap.TestValidRectangle(_validRectangle, 8);
            // 计算有效重心
            _validBarycenter.X = (_size.Width / 2) - _validRectangle.Left;
            _validBarycenter.Y = (_size.Height / 2) - _validRectangle.Top;
            if(!_validRectangle.IsEmpty) {
               Bitmap nativeBitmap = _bitmap.Native.Clone(new Rectangle(_validRectangle.Left, _validRectangle.Top, _validRectangle.Width, _validRectangle.Height), PixelFormat.Format32bppArgb);
               _validBitmap = new FBitmap(nativeBitmap);
               int width = _validRectangle.Width;
               int height = _validRectangle.Height;
            }
            _opened = true;
         }
      }

      //============================================================
      // <T>释放资源。</T>
      //============================================================
      public void Dispose() {
         if(_opened) {
            _bitmap.Dispose();
            _validBitmap.Dispose();
            _opened = false;
         }
      }
   }
}
