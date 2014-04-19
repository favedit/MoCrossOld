using MO.Common;
using MO.Common.Content;
using MO.Common.Geom;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content2d.Common;
using MO.Content2d.Content;
using MO.Content2d.Resource.Common;
using MO.Core;
using MO.Core.Content.Drawing;
using System.Drawing;

namespace MO.Content2d.Resource.Animation
{
   //============================================================
   // <T>资源动画。</T>
   //============================================================
   public class FRsResourceAnimation : FRsResource
   {
      // 日志输出接口
      private static ILogger _logger = RLogger.Find(typeof(FRsResourceAnimation));

      // 是否支持空白
      protected bool _optionPadding;

      // 是否支持透明
      protected bool _optionAlpha;

      // 品质类型
      protected string _qualityCd = ERsResourceQuality.Middle;

      // 品质调色板
      protected int _qualityPalette = 256;

      // 品质透明
      protected int _qualityAlpha = 0;

      // 调色板颜色个数 (存储)
      protected int _paletteColorCount = 256;

      // 尺寸
      protected SIntSize _size = new SIntSize();

      // 有效重心
      protected SIntPoint2 _validBarycenter = new SIntPoint2();

      // 是否打开
      protected bool _opened;

      // 默认帧速
      protected int _frameDelay = 200;

      // 合并文件
      protected string _mergeFileName;

      // 合并尺寸
      protected SIntSize _mergeSize = new SIntSize();

      // 剪辑集合
      FObjects<FRsResourceClip> _clips = new FObjects<FRsResourceClip>();

      //============================================================
      // <T>构造资源动画。</T>
      //============================================================
      public FRsResourceAnimation() {
         _typeCd = ERsResource.Animation;
         _typeName = ERsResource.AnimationCode;
         _typeIcon = ERsResource.AnimationName;
         _clips.SetCount((int)ERsDirection.Count);
      }

      //============================================================
      // <T>获得或设置是否支持透明。</T>
      //============================================================
      public bool OptionPadding {
         get { return _optionPadding; }
         set { _optionPadding = value; }
      }

      //============================================================
      // <T>获得或设置是否支持透明。</T>
      //============================================================
      public bool OptionAlpha {
         get { return _optionAlpha; }
         set { _optionAlpha = value; }
      }

      //============================================================
      // <T>获得或设置品质类型。</T>
      //============================================================
      public string QualityCd {
         get { return _qualityCd; }
         set { _qualityCd = value; }
      }

      //============================================================
      // <T>获得或设置品质透明。</T>
      //============================================================
      public int QualityPalette {
         get { return _qualityPalette; }
         set { _qualityPalette = value; }
      }

      //============================================================
      // <T>获得或设置品质透明。</T>
      //============================================================
      public int QualityAlpha {
         get { return _qualityAlpha; }
         set { _qualityAlpha = value; }
      }

      //============================================================
      // <T>获得或设置所有帧的延迟。</T>
      //============================================================
      public int FrameDelay {
         get { return _frameDelay; }
         set {
            _frameDelay = value;
            foreach (FRsResourceClip clip in _clips) {
               if (clip != null) {
                  clip.FrameDelay = value;
               }
            }
         }
      }

      //============================================================
      // <T>获得剪辑总数。</T>
      //
      // @return 剪辑总数
      //============================================================
      public int ClipCount {
         get {
            int count = 0;
            for (int n = 0; n < (int)ERsDirection.Count; n++) {
               FRsResourceClip clip = _clips[n];
               if (null != clip) {
                  count++;
               }
            }
            return count;
         }
      }

      //============================================================
      // <T>获得剪辑总数。</T>
      //
      // @return 剪辑总数
      //============================================================
      public int ClipNotEmptyCount {
         get {
            int count = 0;
            for (int n = 0; n < (int)ERsDirection.Count; n++) {
               FRsResourceClip clip = _clips[n];
               if (null != clip) {
                  if (!clip.Frames.IsEmpty()) {
                     count++;
                  }
               }
            }
            return count;
         }
      }

      //============================================================
      // <T>获得首个剪辑。</T>
      //
      // @return 剪辑
      //============================================================
      public FRsResourceClip FristClip {
         get {
            for (int n = 0; n < (int)ERsDirection.Count; n++) {
               FRsResourceClip clip = _clips[n];
               if (null != clip) {
                  return clip;
               }
            }
            return null;
         }
      }

      //============================================================
      // <T>获得帧剪辑集合。</T>            
      //============================================================
      public FObjects<FRsResourceClip> Clips {
         get { return _clips; }
         set { _clips = value; }
      }

      //============================================================
      // <T>根据方向获得帧剪辑。</T>
      //
      // @param direction 方向
      // @return 剪辑
      //============================================================
      public FRsResourceClip SyncClip(int direction) {
         FRsResourceClip clip = _clips[direction];
         if (clip == null) {
            clip = new FRsResourceClip();
            clip.Animation = this;
            clip.DirectionCd = (ERsDirection)direction;
            _clips[direction] = clip;
         }
         return clip;
      }

      //============================================================
      // <T>加载设置信息。</T>
      //
      // @param xconfig 设置节点
      //============================================================
      public override void LoadConfig(FXmlNode xconfig) {
         base.LoadConfig(xconfig);
         // 读取属性
         _optionAlpha = xconfig.GetBoolean("option_alpha", _optionAlpha);
         _optionPadding = xconfig.GetBoolean("option_padding", _optionPadding);
         _qualityCd = xconfig.Get("quality_cd", _qualityCd);
         _qualityPalette = xconfig.GetInteger("quality_palette", _qualityPalette);
         _qualityAlpha = xconfig.GetInteger("quality_alpha", _qualityAlpha);
         _frameDelay = xconfig.GetInteger("frame_delay", _frameDelay);
         // 读取剪辑集合
         FXmlNode xclips = xconfig.Find("Clips");
         if (xclips != null) {
            foreach (FXmlNode xnode in xclips.Nodes) {
               if (xnode.IsName("Clip")) {
                  // 检查参数
                  if (!xnode.Contains("direction_cd")) {
                     continue;
                  }
                  // 加载剪辑信息
                  int directionCd = xnode.GetInteger("direction_cd");
                  FRsResourceClip clip = _clips[directionCd];
                  if (clip != null) {
                     clip.LoadConfig(xnode);
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
      public override void SaveConfig(FXmlNode xconfig) {
         base.SaveConfig(xconfig);
         // 设置文件属性
         xconfig.Set("option_alpha", _optionAlpha);
         // 设置支持空白 
         xconfig.Set("option_padding", _optionPadding);
         // 设置品质类型
         xconfig.SetNvl("quality_cd", _qualityCd);
         // 设置品质调色板
         xconfig.Set("quality_palette", _qualityPalette);
         // 设置品质透明
         xconfig.Set("quality_alpha", _qualityAlpha);
         // 存储帧延时
         xconfig.Set("frame_delay", _frameDelay);
         // 存储剪辑集合
         FXmlNode xclip = xconfig.CreateNode("Clips");
         foreach (FRsResourceClip clip in _clips) {
            if (clip != null) {
               clip.SaveConfig(xclip.CreateNode("Clip"));
            }
         }
      }

      //============================================================
      // <T>序列化动画数据。</T>
      //
      // @param output 输出流
      //============================================================
      public override void Serialize(IOutput output) {
         base.Serialize(output);
         // 写入属性
         FRsResourceClip firstClip = FristClip;
         _size.Serialize16(output);
         _validBarycenter.Serialize16(output);
         _mergeSize.Serialize16(output);
         // 写入帧延迟
         if (firstClip != null) {
            output.WriteUint16((ushort)firstClip.FrameCount);
         } else {
            output.WriteUint16((ushort)0);
         }
         if (firstClip != null) {
            firstClip.SerializeDelay(output);
         }
         // 写入剪辑集合
         int clipCount = ClipCount;
         output.WriteUint8((byte)clipCount);
         foreach (FRsResourceClip clip in _clips) {
            if (clip != null) {
               clip.Serialize(output);
            }
         }
         // 序列化位图
         using(FBitmap bitmap = new FBitmap(_mergeFileName)){
            bitmap.SerializeData(output);
         }
      }

      //============================================================
      // <T>反序列化动画信息。</T>
      //
      // @param input 输入流
      //============================================================
      public override void Unserialize(IInput input) {
         base.Unserialize(input);
      }

      //============================================================
      // <T>扫描资源。</T>
      //============================================================
      public override void Scan() {
         base.Scan();
         // 扫描所有文件         
         _optionValid = true;
         FStrings fileNames = RDirectory.ListFiles(_directory);
         fileNames.Sort();
         foreach (string fileName in fileNames) {
            // 文件是否图片
            string name = fileName.Substring(fileName.LastIndexOf("\\") + 1);
            if (name.EndsWith(".png")) {
               name = name.Substring(0, name.Length - 4);
               // 是否符合命名标准
               if (5 == name.Length) {
                  int direction = RInt.Parse(name.Substring(0, 1));
                  int frameIndex = RInt.Parse(name.Substring(1)) - 1;
                  // 同步剪辑
                  FRsResourceClip clip = SyncClip(direction);
                  FRsResourceFrame frame = new FRsResourceFrame();
                  frame.FileName = fileName;
                  clip.PushFrame(frame);
                  // 设置有效
                  _optionValid = true;
               } else {
                  RMoCore.TrackConsole.Write(this, "Scan", "Invalid picture define. (file_name={0})", fileName);
               }
            }
         }
         // 检查剪辑帧数相等
         FRsResourceClip firstClip = FristClip;
         if (null != firstClip) {
            foreach (FRsResourceClip clip in _clips) {
               if (clip != null) {
                  if (firstClip.FrameCount != clip.FrameCount) {
                     RMoCore.TrackConsole.Write(this, "Scan", "Animation clip frame is differenty. (first_frames={0}, clip_frames={1})",
                        firstClip.FrameCount, clip.FrameCount);
                  }
               }
            }
         }
         string _configName = _directory + "\\config.xml";
         if (RFile.Exists(_configName)) {
            FXmlDocument xdoc = new FXmlDocument();
            xdoc.LoadFile(_configName);
            LoadConfig(xdoc.Root);
         }
      }
      

      //============================================================
      // <T>打开资源。</T>
      //============================================================
      public override void Open() {
         if (!_opened) {
            base.Open();
            foreach (FRsResourceClip clip in _clips) {
               if (null != clip) {
                  if (0 != clip.FrameCount) {
                     clip.Open();
                  }
               }
            }
            // 中心点
            SIntPoint2 center = new SIntPoint2();
            foreach (FRsResourceClip clip in _clips) {
               if (null != clip) {
                  center.X = clip.Frames[0].Size.Width / 2;
                  center.Y = clip.Frames[0].Size.Height / 2;
                  break;
               }
            }
            // 计算边界
            int left = int.MaxValue;
            int top = int.MaxValue;
            int right = 0;
            int bottom = 0;
            foreach (FRsResourceClip clip in _clips) {
               if (null != clip && !clip.ValidRectangle.IsEmpty) {
                  // 未反转过的边界
                  SIntRectangle rect = clip.ValidRectangle;
                  if (rect.Left < left) {
                     left = rect.Left;
                  }
                  if (rect.Top < top) {
                     top = rect.Top;
                  }
                  if (rect.Right > right) {
                     right = rect.Right;
                  }
                  if (rect.Bottom > bottom) {
                     bottom = rect.Bottom;
                  }
               }
            }
            int reverseLeft = center.X * 2 - right;
            int reverseRight = center.X * 2 - left;
            if (reverseLeft < left) {
               left = reverseLeft;
            }
            if (reverseRight > right) {
               right = reverseRight;
            }
            _size.Width = right - left;
            _size.Height = bottom - top;
            foreach (FRsResourceClip clip in _clips) {
               if (null != clip) {
                  _validBarycenter.X = center.X - left;
                  _validBarycenter.Y = center.Y - top;
                  break;
               }
            }
         }
         _opened = true;
      }

      //============================================================
      // <T>导出资源。</T>
      //============================================================
      public void Merge() {
         // 计算范围
         int clipCount = ClipNotEmptyCount;
         FRsResourceClip firstClip = FristClip;
         int frameCount = 0;
         if (null != firstClip) {
            frameCount = firstClip.FrameCount;
         } else {
            RMoCore.TrackConsole.Write(this, "Merge", "Animatioin is valid, first clip is empty. (code={0})", Code);
            return;
         }
         int width = _size.Width * frameCount;
         int height = _size.Height * clipCount;
         // 计算是否合并
         using (Bitmap bitmap = new Bitmap(width, height)) {
            // 合并图片
            int y = 0;
            foreach (FRsResourceClip clip in _clips) {
               if (clip != null) {
                  if (!clip.Frames.IsEmpty()) {
                     for (int x = 0; x < frameCount; x++) {
                        FRsResourceFrame frame = clip.Frames[x];
                        int cx = _size.Width * x;
                        int cy = _size.Height * y;
                        if (frame.ValidBitmap != null) {
                           frame.MergeLocation.Set(cx, cy);
                           RBitmap.Copy(frame.ValidBitmap, new SIntRectangle(0, 0, frame.ValidBitmap.Width, frame.ValidBitmap.Height), bitmap, frame.MergeLocation);
                        }
                     }
                     y++;
                  }
               }
            }
            // 存储图片
            _mergeFileName = RContent2dManager.ResourceConsole.MergeDirectory + "\\" + Code + ".png";
            RDirectory.MakeDirectoriesForFile(_mergeFileName);
            bitmap.Save(_mergeFileName);
            _mergeSize.Set(bitmap.Width, bitmap.Height);
         }
      }

      //============================================================
      // <T>导出资源。</T>
      //============================================================
      public override void Export(ERsExportMode modeCd) {
         // 打开资源
         Open();
         //............................................................
         FRsResourceConsole resourceConsole = RContent2dManager.ResourceConsole;
         string exportDirectory = resourceConsole.ExportDirectory;
         string fileName = exportDirectory + "\\" + _code + ".ser";
         //............................................................
         // 合并图形
         Merge();
         //............................................................
         // 序列化数据
         FByteFile file = new FByteFile();
         Serialize(file);
         file.SaveFile(fileName);
         //............................................................
         // 释放资源
         Dispose();
         _logger.Debug(this, "Export", "Export animation resource. (type_name={0}, code={1})", _typeName, _code);
      }

      //============================================================
      // <T>释放资源。</T>
      //============================================================
      public override void Dispose() {
         if (_opened) {
            foreach (FRsResourceClip clip in _clips) {
               if (null != clip) {
                  clip.Dispose();
               }
            }
            base.Dispose();
         }
      }

      //============================================================
      public override void Store() {
         base.Store();
         string fileName = _directory + @"\config.xml";
         FXmlDocument document = new FXmlDocument();
         SaveConfig(document.Root);
         document.SaveFile(fileName);
      }

      //============================================================
      // <T>获得字符串信息。<T>
      //
      // @return 字符串信息
      //============================================================      
      public override string ToString() {
         return "Animation (code=" + _code + ")";
      }
   }
}
