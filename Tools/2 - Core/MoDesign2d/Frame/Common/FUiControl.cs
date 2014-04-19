using MO.Common.Geom;
using MO.Content2d;
using MO.Content2d.Frame.Common;
using MO.Content2d.Resource.Picture;
using MO.Core.Forms.Geom;
using MO.Design2d.Frame.Core;
using MO.Direct2d.Device;
using MO.Direct2d.Draw;
using System.ComponentModel;

namespace MO.Design2d.Frame.Common
{
   //============================================================
   // <T>界面控件。</T>
   //
   // @history MAOCY 120707
   //============================================================
   public class FUiControl : FUiComponent
   {
      // 设计顶层
      protected bool _designTop;

      // 设计边框
      protected bool _designBorder;

      // 设计背景
      protected bool _designGround;

      // 设计坐标
      protected FIntPoint2 _designLocation = new FIntPoint2();

      // 设计尺寸
      protected FIntSize2 _designSize = new FIntSize2();

      // 设计可见性
      protected bool _designVisible = true;

      // 设备层
      protected FDxLayer _deviceLayer;

      // 码头类型
      protected ERcDock _dockCd = ERcDock.None;

      // 前景资源
      protected FUiPicture _foreResource = new FUiPicture();

      // 后景颜色
      protected FUiColor _backColor = new FUiColor();

      // 后景资源
      protected FUiPicture _backResource = new FUiPicture();

      // 绘制板
      protected FUiGraphics _graphics;

      // 控件设计器
      protected FUiControlDesigner _designer;

      //============================================================
      // <T>构造界面控件。</T>
      //
      // @param console 控制台
      //============================================================
      public FUiControl(FUiFrameConsole console = null)
         : base(console) {
      }

      //============================================================
      // <T>获得资源。</T>
      //============================================================
      [Browsable(false)]
      public FRcControl ControlResource {
         get { return _resource as FRcControl; }
      }

      //============================================================
      // <T>获得或设置设计顶层。</T>
      //============================================================
      [Browsable(false)]
      public bool DesignTop {
         get { return _designTop; }
         set { _designTop = value; }
      }

      //============================================================
      // <T>获得或设置设计边框。</T>
      //============================================================
      [Browsable(false)]
      public bool DesignBorder {
         get { return _designBorder; }
         set { _designBorder = value; }
      }

      //============================================================
      // <T>获得或设置设计背景。</T>
      //============================================================
      [Browsable(false)]
      public bool DesignGround {
         get { return _designGround; }
         set { _designGround = value; }
      }

      //============================================================
      // <T>获得或设置设计可见性。</T>
      //============================================================
      [Browsable(false)]
      public bool DesignVisible {
         get { return _designVisible; }
         set { _designVisible = value; }
      }

      //============================================================
      // <T>获得或设置显示类型。</T>
      //============================================================
      [Category("3-控件信息")]
      [Browsable(true)]
      [DefaultValue(ERcDock.None)]
      [Description("码头类型")]
      public ERcDock DockCd {
         get { return _dockCd; }
         set { _dockCd = value; }
      }

      //============================================================
      // <T>获得坐标。</T>
      //============================================================
      [Category("3-控件信息")]
      [Browsable(true)]
      [DefaultValue(typeof(FIntPoint2), "0,0")]
      [Description("坐标")]
      public SIntPoint2 Location {
         get { return ControlResource.Location; }
         set { ControlResource.Location.Assign(value); }
      }

      //============================================================
      // <T>获得横向中间坐标。</T>
      //============================================================
      [Browsable(false)]
      public int CenterX {
         get { return ControlResource.Location.X + (ControlResource.Size.Width >> 1); }
         set { ControlResource.Location.X = value - (ControlResource.Size.Width >> 1); }
      }

      //============================================================
      // <T>获得纵向中间坐标。</T>
      //============================================================
      [Browsable(false)]
      public int CenterY {
         get { return ControlResource.Location.Y + (ControlResource.Size.Height >> 1); }
         set { ControlResource.Location.Y = value - (ControlResource.Size.Height >> 1); }
      }

      //============================================================
      // <T>获得右坐标。</T>
      //============================================================
      [Browsable(false)]
      public int DesignRight {
         get { return ControlResource.Location.X + ControlResource.Size.Width; }
      }

      //============================================================
      // <T>获得下坐标。</T>
      //============================================================
      [Browsable(false)]
      public int DesignBottom {
         get { return ControlResource.Location.Y + ControlResource.Size.Height; }
      }

      //============================================================
      // <T>获得尺寸。</T>
      //============================================================
      [Category("3-控件信息")]
      [Browsable(true)]
      [DefaultValue("")]
      [Description("大小")]
      public SIntSize2 Size {
         get { return ControlResource.Size; }
         set { ControlResource.Size.Assign(value); }
      }

      //============================================================
      // <T>设置边界。</T>
      //
      // @param x 横坐标
      // @param y 纵坐标
      // @param width 宽度
      // @param height 高度
      //============================================================
      public void SetBounds(int x, int y, int width, int height) {
         ControlResource.Location.Set(x, y);
         ControlResource.Size.Set(width, height);
      }

      //============================================================
      // <T>获得或设置前景资源。</T>
      //============================================================
      [Category("3-控件信息")]
      [Browsable(true)]
      [Description("前景资源")]
      public FUiPicture ForeResource {
         get { return _foreResource; }
         set { _foreResource.Assign(value); }
      }

      //============================================================
      // <T>获得或设置后景颜色。</T>
      //============================================================
      [Category("3-控件信息")]
      [Browsable(true)]
      [DefaultValue("")]
      [Description("后景颜色")]
      public FUiColor BackColor {
         get { return _backColor; }
         set { _backColor.Assign(value); }
      }

      //============================================================
      // <T>获得或设置背景资源。</T>
      //============================================================
      [Category("3-控件信息")]
      [Browsable(true)]
      [Description("后景资源")]
      public FUiPicture BackResourceId {
         get { return _backResource; }
         set { _backResource.Assign(value); }
      }

      //============================================================
      // <T>获得绘图板。</T>
      //============================================================
      protected FUiGraphics Graphics {
         get { 
            if(_graphics == null){
               _graphics = new FUiGraphics();
            }
            if (_context != null) {
               _graphics.Context = _context.Context;
            }
            return _graphics;
         }
      }

      //============================================================
      // <T>获得控件设计器。</T>
      //============================================================
      [Browsable(false)]
      public FUiControlDesigner Designer {
         get { return _designer; }
      }

      //============================================================
      // <T>计算全局坐标。</T>
      //
      // @param scale 缩放
      // @return 全局坐标
      //============================================================
      public virtual FIntPoint2 CalculatePosition() {
         FIntPoint2 point = new FIntPoint2();
         FUiObject find = this;
         while (find != null) {
            FUiControl control = find as FUiControl;
            if (control != null) {
               point.Add(control.Location);
            }
            find = find.Parent;
         }
         return point;
      }

      //============================================================
      // <T>计算显示坐标。</T>
      //
      // @return 显示坐标
      //============================================================
      public virtual FIntPoint2 CalculateDisplayPosition() {
         float scale = _context.Scale;
         FIntPoint2 point = new FIntPoint2();
         FUiObject find = this;
         while (find != null) {
            FUiControl control = find as FUiControl;
            if (control != null) {
               FRcControl controlResource = control.ControlResource;
               if (control.DesignTop) {
                  point.Add(control.Location);
                  point.X += (int)(controlResource.Margin.Left * scale);
                  point.Y += (int)(controlResource.Margin.Top * scale);
               } else {
                  point.Add(control.Location, scale);
                  if (control != this) {
                     point.X += (int)(controlResource.Margin.Left * scale);
                     point.Y += (int)(controlResource.Margin.Top * scale);
                  }
                  point.X += (int)(controlResource.Padding.Left * scale);
                  point.Y += (int)(controlResource.Padding.Top * scale);
               }
            }
            find = find.Parent;
         }
         return point;
      }

      //============================================================
      // <T>计算全局坐标。</T>
      //
      // @param scale 缩放
      // @return 全局坐标
      //============================================================
      public virtual SIntRectangle CalculateRange() {
         // 获得最大范围
         int width = ControlResource.Size.Width;
         int height = ControlResource.Size.Height;
         if (HasComponment()) {
            foreach (FUiComponent component in _components) {
               FUiControl control = component as FUiControl;
               if (control != null) {
                  int right = control.DesignRight;
                  if (right > width) {
                     width = right;
                  }
                  int bottom = control.DesignBottom;
                  if (bottom > height) {
                     height = bottom;
                  }
               }
            }
         }
         return new SIntRectangle(ControlResource.Location.X, ControlResource.Location.Y, width, height);
      }

      //============================================================
      // <T>计算显示尺寸。</T>
      //
      // @return 显示尺寸
      //============================================================
      public virtual FIntSize2 CalculateDisplaySize() {
         float scale = _context.Scale;
         return new FIntSize2((int)(ControlResource.Size.Width * scale), (int)(ControlResource.Size.Height * scale));
      }

      //============================================================
      // <T>增加一个子组件。</T>
      //
      // @param componment 子组件
      //============================================================
      public override void Push(FUiComponent componment) {
         base.Push(componment);
         FUiControl control = componment as FUiControl;
         if (control != null) {
            control.Context = _context;
         }
      }

      //============================================================
      // <T>配置颜色处理。</T>
      //
      // @param color 颜色
      //============================================================
      public void SetupColor(FUiColor color) {
         if (color == null) {
            return;
         }
         if (!color.Valid) {
            return;
         }
         if (color.brush == null) {
            color.brush = _context.Device.CreateSolidBrush(color);
         } else {
            FDxSolidBrush brush = color.brush as FDxSolidBrush;
            if (brush.Color.ToValue() != color.ToValue()) {
               color.brush = _context.Device.CreateSolidBrush(color);
            }
         }
      }

      //============================================================
      // <T>配置资源处理。</T>
      //
      // @param resource 资源
      //============================================================
      public void SetupResource(FUiPicture resource) {
         if (!resource.IsValid()) {
            resource.resource = null;
            return;
         }
         // 释放旧资源
         if (resource.bitmap != null) {
            resource.bitmap.Dispose();
            resource.bitmap = null;
         }
         // 选择新资源
         FRsResourcePicture picture = RContent2dManager.ResourceConsole.FindOpen(resource.Code) as FRsResourcePicture;
         if (picture != null) {
            resource.bitmap = _context.Device.CreateBitmap(picture.Bitmap.Native);
            resource.resource = picture;
            // 设置属性
            //string padding = RString.SubStringLast(picture.Label, "(", ")");
            //if (!RString.IsEmpty(padding)) {
            //   if(_groundResource.Padding.Parse(padding)) {
            //      _groundResource.AlignCd = ERcPictureAlign.Square;
            //   }
            //}
         }
      }

      //============================================================
      // <T>配置处理。</T>
      //
      // @param args 参数
      //============================================================
      public override void OnSetup(SUiSetupArgs args) {
         base.OnSetup(args);
         // 配置设计器
         if (args.context.OptionDesign) {
            _designer = new FUiControlDesigner();
            _designer.Setup(args);
            _designer.Control = this;
         }
         // 配置层
         _deviceLayer = _context.Device.CreateLayer();
         // 配置前景
         SetupResource(_foreResource);
         // 配置背景
         //SetupResource(_groundResource);
         // 配置后景
         SetupResource(_backResource);
      }

      //============================================================
      // <T>命令处理。</T>
      //
      // @param action 参数
      //============================================================
      public override void OnActionBefore(SUiAction action) {
         base.OnActionBefore(action);
         // 命令处理
         switch (action.actionCd) {
            case EUiAction.DisplayBorder:
               // 显示边框处理
               if (!_designTop) {
                  _designBorder = (bool)action.value;
               }
               break;
         }
      }

      //============================================================
      // <T>命令处理。</T>
      //
      // @param action 参数
      //============================================================
      public override void OnActionAfter(SUiAction action) {
         base.OnActionAfter(action);
         // 命令处理
         switch (action.actionCd) {
            case EUiAction.Adjust:
               // 调整处理
               if (TestAdjustAble()) {
                  OnAdjust();
               }
               break;
         }
      }

      //============================================================
      // <T>结束绘制处理。</T>
      //
      // @param args 参数
      //============================================================
      public virtual bool OnTest(SUiTestArgs args) {
         float scale = _context.Scale;
         SIntPoint2 position = CalculateDisplayPosition();
         bool test = RRectangle.ConstainsPoint(position.X, position.Y, (int)(ControlResource.Size.Width * scale), (int)(ControlResource.Size.Height * scale), args.Point.X, args.Point.Y);
         if (test) {
            args.Push(this);
         }
         return test;
      }

      //============================================================
      // <T>测试处理。</T>
      //
      // @param args 参数
      //============================================================
      public virtual void Test(SUiTestArgs args) {
         // 检查有效性
         if (!ComponentResource.OptionValid) {
            return;
         }
         // 开始测试
         bool result = OnTest(args);
         // 子控件测试
         if (result) {
            if (_components != null) {
               foreach (FUiComponent componment in _components) {
                  FUiControl control = componment as FUiControl;
                  if (control != null) {
                     control.Test(args);
                  }
               }
            }
         }
      }

      //============================================================
      // <T>测试是否可见。</T>
      //
      // @return 是否可见
      //============================================================
      public virtual bool TestVisible() {
         // 检查设计可见
         if (!_designVisible) {
            return false;
         }
         // 检查唯一焦点
         if (_context.OptionOneFocus && (_context.Selection.FocusControl != this)) {
            return false;
         }
         return true;
      }

      //============================================================
      // <T>测试是否焦点。</T>
      //
      // @return 是否焦点
      //============================================================
      public virtual bool TestFocus() {
         // 测试当前控件焦点
         if (_designer.DesignFocus) {
            return true;
         }
         // 测试子节点焦点
         if (_components != null) {
            foreach (FUiComponent component in _components) {
               FUiControl control = component as FUiControl;
               if (control != null) {
                  if (control.TestFocus()) {
                     return true;
                  }
               }
            }
         }
         return false;
      }

      //============================================================
      // <T>绘制资源。</T>
      //
      // @param resource 资源
      // @param x 横坐标
      // @param y 纵坐标
      //============================================================
      public void DrawResource(FUiPicture resource, int x = 0, int y = 0) {
         if (resource.HasBitmap()) {
            // 获得坐标
            float scale = _context.Scale;
            int cx = resource.Location.X + x;
            int cy = resource.Location.Y + y;
            // 绘制资源
            FDxContext2d context = _context.Context;
            if (resource.AlignCd == ERcPictureAlign.Square) {
               context.DrawBitmapPadding(resource.bitmap, cx, cy, ControlResource.Size.Width, ControlResource.Size.Height, resource.Padding, scale);
            } else {
               SIntSize2 size = resource.bitmap.Size;
               context.DrawBitmap(resource.bitmap, cx, cy, size.Width, size.Height, scale);
            }
         }
      }

      //============================================================
      // <T>绘制资源。</T>
      //
      // @param resource 资源
      // @param rectangle 范围
      //============================================================
      public void DrawResource(FUiPicture resource, SIntRectangle rectangle) {
         if (resource.HasBitmap()) {
            // 获得坐标
            float scale = _context.Scale;
            int x = resource.Location.X;
            int y = resource.Location.Y;
            // 绘制资源
            FDxContext2d context = _context.Context;
            if (resource.AlignCd == ERcPictureAlign.Square) {
               context.DrawBitmapPadding(resource.bitmap, rectangle, x, y, ControlResource.Size.Width, ControlResource.Size.Height, resource.Padding, scale);
            } else {
               SIntSize2 size = resource.bitmap.Size;
               context.DrawBitmap(resource.bitmap, rectangle, x, y, size.Width, size.Height, scale);
            }
         }
      }

      //============================================================
      // <T>绘制边框。</T>
      //
      // @param border 边框描述
      //============================================================
      public void DrawBorder(FRcBorder innerBorder, FRcBorder outerBorder) {
         int outerLeft = outerBorder.Left.Width;
         int outerTop = outerBorder.Top.Width;
         int outerRight = outerBorder.Right.Width;
         int outerBottom = outerBorder.Bottom.Width;
         int innerLeft = innerBorder.Left.Width;
         int innerTop = innerBorder.Top.Width;
         int innerRight = innerBorder.Right.Width;
         int innerBottom = innerBorder.Bottom.Width;
         // 外左边框
         if (0 < outerLeft) {
            FUiColor borderColor = _context.BuildDesignColor(outerBorder.Left.Color);
            _context.Context.DrawLine(borderColor.brush, 0, 0, 0, ControlResource.Size.Height, outerLeft);
         }
         // 内左边框
         if (0 < innerLeft) {
            float x1 = outerLeft / 2 + innerLeft / 2 + innerLeft % 2;
            float y1 = outerTop / 2;
            float x2 = outerLeft / 2 + innerLeft / 2 + innerLeft % 2;
            float y2 = ControlResource.Size.Height - outerBottom / 2;
            FUiColor borderColor = _context.BuildDesignColor(innerBorder.Left.Color);
            _context.Context.DrawLine(borderColor.brush, x1, y1, x2, y2, innerLeft);
         }
         // 外上边框
         if (0 < outerTop) {
            FUiColor borderColor = _context.BuildDesignColor(outerBorder.Top.Color);
            _context.Context.DrawLine(borderColor.brush, 0, 0, ControlResource.Size.Width, 0, outerTop);
         }
         // 内上边框
         if (0 < innerTop) {
            float x1 = outerLeft / 2;
            float y1 = outerTop / 2 + innerTop / 2 + innerTop % 2;
            float x2 = ControlResource.Size.Width - (outerRight / 2 + outerRight % 2);
            float y2 = outerTop / 2 + innerTop / 2 + innerTop % 2;
            FUiColor borderColor = _context.BuildDesignColor(innerBorder.Top.Color);
            _context.Context.DrawLine(borderColor.brush, x1, y1, x2, y2, innerLeft);
         }
         // 外右边框
         if (0 < outerRight) {
            FUiColor borderColor = _context.BuildDesignColor(outerBorder.Right.Color);
            _context.Context.DrawLine(borderColor.brush, ControlResource.Size.Width, 0, ControlResource.Size.Width, ControlResource.Size.Height, outerRight);
         }
         // 内右边框
         if (0 < innerRight) {
            float x1 = ControlResource.Size.Width - (outerRight / 2 + outerRight % 2 + innerRight / 2);
            float y1 = outerTop / 2;
            float x2 = ControlResource.Size.Width - (outerRight / 2 + outerRight % 2 + innerRight / 2);
            float y2 = ControlResource.Size.Height - (outerBottom / 2 + outerBottom % 2);
            FUiColor borderColor = _context.BuildDesignColor(innerBorder.Right.Color);
            _context.Context.DrawLine(borderColor.brush, x1, y1, x2, y2, innerLeft);
         }
         // 外下边框
         if (0 < outerBottom) {
            FUiColor borderColor = _context.BuildDesignColor(outerBorder.Bottom.Color);
            _context.Context.DrawLine(borderColor.brush, 0, ControlResource.Size.Height, ControlResource.Size.Width, ControlResource.Size.Height, outerBottom);
         }
         // 内下边框
         if (0 < innerBottom) {
            float x1 = outerLeft / 2;
            float y1 = ControlResource.Size.Height - (outerBottom / 2 + outerBottom % 2 + innerBottom / 2);
            float x2 = ControlResource.Size.Width - (outerRight / 2 + outerRight % 2);
            float y2 = ControlResource.Size.Height - (outerBottom / 2 + outerBottom % 2 + innerBottom / 2);
            FUiColor borderColor = _context.BuildDesignColor(innerBorder.Bottom.Color);
            _context.Context.DrawLine(borderColor.brush, x1, y1, x2, y2, innerLeft);
         }
      }

      //============================================================
      // <T>开始绘制处理。</T>
      //
      // @param args 参数
      //============================================================
      public virtual void OnDrawBegin(SUiDrawArgs args) {
         FDxContext2d context = _context.Context;
         FRcControl resource = ControlResource;
         // 绘制设计背景
         if (_designGround) {
            context.FillRectangle(_context.DesignGroundColor.brush, 0, 0, _designSize.Width, _designSize.Height);
         }
         // 绘制后景颜色
         if (resource.BackColor != 0) {
            context.FillRectangle(resource.BackColor, 0, 0, _designSize.Width, _designSize.Height);
         }
         // 绘制后景
         DrawResource(_backResource);
      }

      //============================================================
      // <T>结束绘制处理。</T>
      //
      // @param args 参数
      //============================================================
      public virtual void OnDrawAfter(SUiDrawArgs args) {
         FDxContext2d context = _context.Context;
         // 绘制前景
         DrawResource(_foreResource);
         // 绘制边框
         DrawBorder(ControlResource.BorderInner, ControlResource.BorderOuter);
         //if (_optionBorder) {
         //   SetupColor(_borderColor);
         //   if (_borderColor.Valid) {
         //      context.DrawRectangle(_borderColor.brush, 0, 0, _designSize.Width, _designSize.Height);
         //   }
         //}
         // 绘制设计边框
         if (_designBorder) {
            context.DrawRectangle(_context.DesignBorderBackColor.brush, 0, 0, _designSize.Width, _designSize.Height, 2);
            context.DrawRectangle(_context.DesignBorderForeColor.brush, 0, 0, _designSize.Width, _designSize.Height);
         }
      }

      //============================================================
      // <T>绘制处理。</T>
      //
      // @param args 参数
      //============================================================
      public virtual void Draw(SUiDrawArgs args) {
         // 检查有效性
         if (!ComponentResource.OptionValid) {
            return;
         }
         // 设置数据
         bool visible = TestVisible();
         SIntPoint2 position = CalculatePosition();
         args.Position.Assign(position);
         // 计算数据
         _designLocation.Assign(CalculateDisplayPosition());
         _designSize.Assign(CalculateDisplaySize());
         // 开始层处理
         _deviceLayer.Begin(_designLocation.X, _designLocation.Y, _designSize.Width, _designSize.Height);
         // 检查可见性
         if (visible) {
            OnDrawBegin(args);
         }
         //............................................................
         // 子控件测试
         if (_components != null) {
            foreach (FUiComponent componment in _components) {
               FUiControl control = componment as FUiControl;
               if (control != null) {
                  control.Draw(args);
               }
            }
         }
         //............................................................
         // 结束绘制
         if (visible) {
            _context.Context.TransformLocation(_designLocation.X, _designLocation.Y);
            OnDrawAfter(args);
         }
         // 结束层处理
         _deviceLayer.End();
      }

      //============================================================
      // <T>设计绘制处理。</T>
      //
      // @param args 参数
      //============================================================
      public virtual void DrawDesign(SUiDrawArgs args) {
         // 子控件设计绘制处理
         if (_components != null) {
            foreach (FUiComponent componment in _components) {
               FUiControl control = componment as FUiControl;
               if (control != null) {
                  control.DrawDesign(args);
               }
            }
         }
         // 设计绘制处理
         if (_designer != null) {
            _designer.Draw(args);
         }
      }

      //============================================================
      // <T>测试一下是否自动调整处理。</T>
      //
      // @return 是否自动调整处理
      //============================================================
      public virtual bool TestAdjustAble() {
         //if(_groundResource.AlignCd == ERcPictureAlign.Square) {
         //   return false;
         //}
         return true;
      }

      //============================================================
      // <T>自动调整处理。</T>
      //============================================================
      public virtual void OnAdjust() {
         // 计算背景图片关联的大小
         //if(_groundResource.HasBitmap()) {
         //   _size.Set(_groundResource.Size.Width, _groundResource.Size.Height);
         //   _size.Width += _groundResource.Location.X;
         //   _size.Height += _groundResource.Location.Y;
         //}
         // 计算容器大小
         SIntRectangle rectangle = CalculateRange();
         if (rectangle.Width > ControlResource.Size.Width) {
            ControlResource.Size.Width = rectangle.Width;
         }
         if (rectangle.Height > ControlResource.Size.Height) {
            ControlResource.Size.Height = rectangle.Height;
         }
      }

      //============================================================
      // <T>调整当前控件。</T>
      //============================================================
      public void Adjust() {
         if (TestAdjustAble()) {
            OnAdjust();
         }
      }

      //============================================================
      // <T>调整全部处理。</T>
      //============================================================
      public void DoActionAdjust() {
         SUiAction action = new SUiAction();
         action.actionCd = EUiAction.Adjust;
         Action(action);
      }

      //============================================================
      // <T>回收资源。</T>
      //============================================================
      public override void OnFree() {
         base.OnFree();
         _deviceLayer.Dispose();
         //if(_groundResource.bitmap != null) {
         //   _groundResource.bitmap.Dispose();
         //   _groundResource.bitmap = null;
         //}
      }

      //============================================================
      // <T>释放资源。</T>
      //============================================================
      public override void OnDispose() {
         base.OnDispose();
         _deviceLayer.Dispose();
      }
   }
}
