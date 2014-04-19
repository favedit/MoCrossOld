using System.Drawing;
using MO.Common.Collection;
using MO.Common.Geom;
using MO.Common.Lang;
using MO.Design2d.Frame.Core;
using MO.Design2d.Frame.Common;
using MO.Direct2d.Common;
using MO.Direct2d.Device;
using MO.Direct2d.Draw;

namespace MO.Design2d.Frame.Common
{
   //============================================================
   // <T>界面环境。</T>
   //============================================================
   public class FUiContext : FObject
   {
      // 设备
      protected FDxDevice2d _device;

      // 环境
      protected FDxContext2d _context;

      // 配置设计
      protected bool _optionDesign = true;

      // 唯一焦点模式
      protected bool _optionOneFocus;

      // 选择区域
      protected FUiControlSelection _selection;

      // 缩放
      protected float _scale = 1.0f;

      // 设计浅颜色
      protected FUiColor _designLightColor = new FUiColor();

      // 设计深颜色
      protected FUiColor _designDarkColor = new FUiColor();

      // 设计边框颜色
      protected FUiColor _designBorderForeColor = new FUiColor();

      // 设计边框颜色
      protected FUiColor _designBorderBackColor = new FUiColor();

      // 设计边框线样式
      protected FDxStrokeStyle _designLineStrokeStyle;

      // 设计背景颜色
      protected FUiColor _designGroundColor = new FUiColor();

      // 设计颜色集合
      protected FDictionary<FUiColor> _designColors = new FDictionary<FUiColor>();

      // 默认文本格式
      protected FDxTextFormat _defaultTextFormat;

      // 顶层容器
      protected FUiFrame _topContainer;

      //============================================================
      // <T>构造界面环境。</T>
      //============================================================
      public FUiContext() {
      }

      //============================================================
      // <T>获得或设置设备。</T>
      //============================================================
      public FDxDevice2d Device {
         get { return _device; }
         set { _device = value; }
      }

      //============================================================
      // <T>获得或设置环境。</T>
      //============================================================
      public FDxContext2d Context {
         get { return _context; }
         set { _context = value; }
      }

      //============================================================
      // <T>获得或设置配置设计。</T>
      //============================================================
      public bool OptionDesign {
         get { return _optionDesign; }
         set { _optionDesign = value; }
      }

      //============================================================
      // <T>获得或设置唯一焦点模式。</T>
      //============================================================
      public bool OptionOneFocus {
         get { return _optionOneFocus; }
         set { _optionOneFocus = value; }
      }

      //============================================================
      // <T>获得或设置选择区域。</T>
      //============================================================
      public FUiControlSelection Selection {
         get { return _selection; }
         set { _selection = value; }
      }

      //============================================================
      // <T>获得或设置缩放。</T>
      //============================================================
      public float Scale {
         get { return _scale; }
         set { _scale = value; }
      }

      //============================================================
      // <T>获得设计浅颜色。</T>
      //============================================================
      public FUiColor DesignLightColor {
         get { return _designLightColor; }
      }

      //============================================================
      // <T>获得设计深颜色。</T>
      //============================================================
      public FUiColor DesignDarkColor {
         get { return _designDarkColor; }
      }

      //============================================================
      // <T>获得设计边框颜色。</T>
      //============================================================
      public FUiColor DesignBorderForeColor {
         get { return _designBorderForeColor; }
      }

      //============================================================
      // <T>获得设计边框颜色。</T>
      //============================================================
      public FUiColor DesignBorderBackColor {
         get { return _designBorderBackColor; }
      }

      //============================================================
      // <T>获得设计边框线样式。</T>
      //============================================================
      public FDxStrokeStyle DesignLineStrokeStyle {
         get { return _designLineStrokeStyle; }
      }

      //============================================================
      // <T>获得设计背景颜色。</T>
      //============================================================
      public FUiColor DesignGroundColor {
         get { return _designGroundColor; }
      }

      //============================================================
      // <T>获得文本格式。</T>
      //============================================================
      public FDxTextFormat DefaultTextFormat {
         get { return _defaultTextFormat; }
      }

      //============================================================
      // <T>获得或设置顶层容器。</T>
      //============================================================
      public FUiFrame TopContainer {
         get { return _topContainer; }
         set { _topContainer = value; }
      }

      //============================================================
      // <T>获得或设置顶层坐标。</T>
      //============================================================
      public SIntPoint2 TopLocation {
         get { return _topContainer.Location; }
      }

      //============================================================
      // <T>生成设计颜色。</T>
      //
      // @param color 颜色
      // @return 颜色
      //============================================================
      public FUiColor BuildDesignColor(int color) {
         return BuildDesignColor(Color.FromArgb(color));
      }

      //============================================================
      // <T>生成设计颜色。</T>
      //
      // @param color 颜色
      // @return 颜色
      //============================================================
      public FUiColor BuildDesignColor(Color color) {
         string code = color.ToArgb().ToString();
         FUiColor result = _designColors.Find(code);
         if(result == null) {
            result = new FUiColor();
            result.Set(color.R, color.G, color.B, color.A);
            result.brush = _context.Device.CreateSolidBrush(result);
            _designColors.Set(code, result);
         }
         return result;
      }

      //============================================================
      // <T>生成设计颜色。</T>
      //
      // @param color 颜色
      // @return 颜色
      //============================================================
      public FUiColor BuildDesignColor(SColor color) {
         string code = color.ToValue().ToString();
         FUiColor result = _designColors.Find(code);
         if(result == null) {
            result = new FUiColor();
            result.Set(color.R, color.G, color.B, color.A);
            result.brush = _context.Device.CreateSolidBrush(result);
            _designColors.Set(code, result);
         }
         return result;
      }

      //============================================================
      // <T>设置处理。</T>
      //============================================================
      public void Setup() {
         if(!_designLightColor.HasBrush()) {
            _designLightColor.Set(255, 255, 255, 255);
            _designLightColor.brush = _context.Device.CreateSolidBrush(_designLightColor);
         }
         if(!_designDarkColor.HasBrush()) {
            _designDarkColor.Set(0, 0, 0, 255);
            _designDarkColor.brush = _context.Device.CreateSolidBrush(_designDarkColor);
         }
         if(!_designBorderForeColor.HasBrush()) {
            _designBorderForeColor.Set(255, 255, 255, 255);
            _designBorderForeColor.brush = _context.Device.CreateSolidBrush(_designBorderForeColor);
         }
         if(!_designBorderBackColor.HasBrush()) {
            _designBorderBackColor.Set(0, 0, 0, 255);
            _designBorderBackColor.brush = _context.Device.CreateSolidBrush(_designBorderBackColor);
         }
         _designLineStrokeStyle = _context.Device.CreateStrokeStyle(EDxCapStyle.Flat, EDxCapStyle.Flat, EDxCapStyle.Flat, EDxDashStyle.Custom, new float[] { 6, 1 });
         if(!_designGroundColor.HasBrush()) {
            _designGroundColor.Set(255, 255, 255, 255);
            _designGroundColor.brush = _context.Device.CreateSolidBrush(_designGroundColor);
         }
         // 设置文本格式
         _defaultTextFormat = _context.Device.CreateTextFormat("NSimSun", "Normal", "Normal", "Normal", 12.0f, "NSimSun");
      }
   }
}
