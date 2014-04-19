using MO.Common.Content;
using MO.Common.Geom;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content2d.Common;
using MO.Content2d.Content;
using MO.Content2d.Core;
using MO.Content2d.Resource.Common;
using MO.Core.Content.Drawing;
using System.ComponentModel;

namespace MO.Content2d.Resource.Picture
{
   //============================================================
   // <T>设计图片资源。</T>
   //============================================================
   public class FRsResourcePicture : FRsResource, IRsExport
   {
      // 日志输出接口
      private static ILogger _logger = RLogger.Find(typeof(FRsResourcePicture));

      // 是否支持透明
      protected bool _optionAlpha = true;

      // 是否支持空白
      protected bool _optionPadding = false;

      // 品质类型
      protected string _qualityCd = ERsResourceQuality.Middle;

      // 品质调色板
      protected int _qualityPalette = 256;

      // 品质透明
      protected int _qualityAlpha = 0;

      // 文件名称
      protected string _fileName;

      // 图片尺寸
      protected SIntSize _size = new SIntSize();

      // 有效范围
      protected SIntRectangle _validRectangle = new SIntRectangle();

      // 图片
      protected FBitmap _bitmap = new FBitmap();

      //============================================================
      // <T>构造设计图片资源。</T>
      //============================================================
      public FRsResourcePicture() {
         _typeCd = ERsResource.Picture;
         _typeName = ERsResource.PictureCode;
         _typeIcon = ERsResource.PictureName;
      }

      //============================================================
      // <T>获得或设置是否支持透明。</T>
      //============================================================
      [DefaultValue(true)]
      public bool OptionAlpha {
         get { return _optionAlpha; }
         set { _optionAlpha = value; }
      }

      //============================================================
      // <T>获得或设置是否支持透明。</T>
      //============================================================
      [DefaultValue(false)]
      public bool OptionPadding {
         get { return _optionPadding; }
         set { _optionPadding = value; }
      }

      //============================================================
      // <T>获得或设置品质类型。</T>
      //============================================================
      [DefaultValue("")]
      public string QualityCd {
         get { return _qualityCd; }
         set { _qualityCd = value; }
      }

      //============================================================
      // <T>获得或设置品质透明。</T>
      //============================================================
      [DefaultValue(false)]
      public int QualityPalette {
         get { return _qualityPalette; }
         set { _qualityPalette = value; }
      }

      //============================================================
      // <T>获得或设置品质透明。</T>
      //============================================================
      [DefaultValue(0)]
      public int QualityAlpha {
         get { return _qualityAlpha; }
         set { _qualityAlpha = value; }
      }
      
      //============================================================
      // <T>获得或设置文件名称。</T>
      //============================================================
      [DefaultValue("")]
      public string FileName {
         get { return _fileName; }
         set { _fileName = value; }
      }

      //============================================================
      // <T>获得图片尺寸。</T>
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
      // <T>获得图片类型。</T>
      //============================================================
      public string BitmapTypeCd {
         get{
            int count = 0;
            if(_optionPadding){
               count = _size.Square;
            }else{
               count = _validRectangle.Square;
            }
            int pixelCount = ERsResourceQuality.ParseBitmapPixel(_qualityCd);
            //return (count > pixelCount) ? ERsBitmap.Jpg : ERsBitmap.Index;
            return (count > pixelCount) ? ERsBitmap.Jpg : ERsBitmap.Index;
         }
      }
      
      //============================================================
      // <T>获得或设置图片。</T>
      //============================================================
      public FBitmap Bitmap {
         get { return _bitmap; }
      }

      //============================================================
      // <T>加载设置信息。</T>
      //
      // @param xconfig 设置节点
      // @author TYFNG 20120409
      //============================================================
      public override void LoadConfig(FXmlNode xconfig) {
         base.LoadConfig(xconfig);
         // 读取透明属性
         if (xconfig.Contains("option_alpha")) {
            _optionAlpha = xconfig.GetBoolean("option_alpha");
         }
         if (xconfig.Contains("alpha_cd")) {
            _optionAlpha = (xconfig.Get("alpha_cd") == "Able");
         }
         // 读取文件属性
         if (xconfig.Contains("option_padding")) {
            _optionPadding = xconfig.GetBoolean("option_padding");
         }
         // 读取品质类型
         _qualityCd = xconfig.Get("quality_cd", _qualityCd);
         // 读取品质调色板
         if (xconfig.Contains("quality_palette")) {
            _qualityPalette = xconfig.GetInteger("quality_palette");
         }
         // 读取品质透明
         if (xconfig.Contains("quality_alpha")) {
            _qualityAlpha = xconfig.GetInteger("quality_alpha");
         }
      }

      //============================================================
      // <T>保存设置信息。<T>      
      // 
      // @param xconfig 设置节点
      // @author TYFNG 20120409
      //============================================================
      public override void SaveConfig(FXmlNode xconfig) {
         base.SaveConfig(xconfig);
         // 设置图片尺寸
         xconfig.Set("size", _size.ToString());
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
         // 设置文件名称
         xconfig.Set("file_name", RFile.GetFileName(_fileName));
      }

      //============================================================
      // <T>序列化信息。</T>
      //
      // @param output 流信息
      // @author TYFNG 20120409
      //============================================================
      public override void Serialize(IOutput output) {
         // 序列化内容
         base.Serialize(output);
         // 写入属性
         output.WriteBool(_optionPadding);
         output.WriteBool(_optionAlpha);
         // 写入大小
         int width = _bitmap.Width;
         int height = _bitmap.Height;
         output.WriteUint16((ushort)width);
         output.WriteUint16((ushort)height);
         // 写入范围
         output.WriteUint16((ushort)_validRectangle.Left);
         output.WriteUint16((ushort)_validRectangle.Top);
         output.WriteUint16((ushort)_validRectangle.Width);
         output.WriteUint16((ushort)_validRectangle.Height);
         // 写入数据
         _bitmap.SerializeData(output);
      }

      //============================================================
      // <T>解压当前信息。</T>
      //
      // @param input 流
      //============================================================
      public override void Unserialize(IInput input) {
         base.Unserialize(input);
      }

      //============================================================
      // <T>扫描资源。</T>
      //============================================================
      public override void Scan() {
         base.Scan();
         _optionValid = true;
      }
      
      //============================================================
      // <T>打开资源。</T>
      //============================================================
      public override void Open() {
         if (!_statusOpen) {
            base.Open();
            // 打开图片资源
            _bitmap.LoadFile(_fileName);
            int length = (int)RFile.GetFileLength(_fileName);
            _size.Width = _bitmap.Width;
            _size.Height = _bitmap.Height;
            // 测试有效区域
            RBitmap.TestValidRectangle(_bitmap.Native, _validRectangle, _qualityAlpha);
         }
      }

      //============================================================
      // <T>根据指定模式导出数据。</T>
      //
      // @param modeCd 导出模式
      //============================================================
      public override void Export(ERsExportMode modeCd) {
         // 打开资源
         Open();
         //............................................................
         FRsResourceConsole resourceConsole = RContent2dManager.ResourceConsole;
         string exportDirectory = resourceConsole.ExportDirectory;
         string fileName = exportDirectory + "\\" + _code + ".ser";
         //............................................................
         // 序列化数据
         FByteFile file = new FByteFile();
         Serialize(file);
         file.SaveFile(fileName);
         //............................................................
         // 释放资源
         Dispose();
         _logger.Debug(this, "Export", "Export picture resource. (type_name={0}, code={1})", _typeName, _code);
      }

      //============================================================
      // <T>释放资源。</T>
      //
      // @author TYFNG 20120409
      //============================================================
      public override void Dispose() {
         if (_statusOpen) {
            _bitmap.Dispose();
            base.Dispose();
         }
      }

      //============================================================
      // <T>获得字符串信息。<T>
      //
      // @return 字符串信息
      // @author TYFNG 20120409
      //============================================================      
      public override string ToString() {
         return "Picture (code=" + _code + ")";
      }
   }
}
