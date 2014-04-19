using MO.Common.Geom;
using MO.Common.Lang;
using MO.Content2d.Frame.Common;
using MO.Core.Forms.Geom;
using MO.Design2d.Frame.Common;
using MO.Direct2d.Device;
using MO.Direct2d.Draw;
using System.ComponentModel;
using System.Drawing;

namespace MO.Design2d.Frame.Core
{
   //============================================================
   // <T>界面选中控件。</T>
   //============================================================
   public class FUiControlDesigner : FObject
   {
      // 环境
      protected FUiContext _context;

      // 配置焦点
      protected bool _optionFocus = true;

      // 配置选中
      protected bool _optionSelect = true;

      // 设计焦点
      protected bool _designFocus = false;

      // 设计选中
      protected bool _designSelect = false;

      // 设计位置
      protected FIntPoint2 _designLocation = new FIntPoint2();

      // 设计大小
      protected FIntSize2 _designSize = new FIntSize2();

      // 坐标
      protected FIntPoint2 _location = new FIntPoint2();

      // 尺寸
      protected FIntSize2 _size = new FIntSize2();

      // 前景颜色
      protected FUiColor _selectForeColor = new FUiColor();

      // 背景颜色
      protected FUiColor _selectBackColor = new FUiColor();

      // 选择点前颜色
      protected FDxBrush _pointForeBrush;

      // 选择点前大小
      protected SIntSize2 _pointForeSize = new SIntSize2(5, 5);

      // 选择点后颜色
      protected FDxBrush _pointBackBrush;

      // 选择点后大小
      protected SIntSize2 _pointBackSize = new SIntSize2(5, 5);

      // 关联控件
      protected FUiControl _control;

      // 辅助点集合
      protected SUiControlPoint[] _points = new SUiControlPoint[(int)ERcAlign.Count];

      //============================================================
      // <T>构造界面资源按键。</T>
      //============================================================
      public FUiControlDesigner() {
      }

      //============================================================
      // <T>获得或设置配置焦点。</T>
      //============================================================
      [Browsable(false)]
      public bool OptionFocus {
         get { return _optionFocus; }
         set { _optionFocus = value; }
      }

      //============================================================
      // <T>获得或设置配置选中。</T>
      //============================================================
      [Browsable(false)]
      public bool OptionSelect {
         get { return _optionSelect; }
         set { _optionSelect = value; }
      }

      //============================================================
      // <T>获得或设置设计焦点。</T>
      //============================================================
      [Browsable(false)]
      public bool DesignFocus {
         get { return _designFocus; }
         set { _designFocus = value; }
      }

      //============================================================
      // <T>获得或设置设计选中。</T>
      //============================================================
      [Browsable(false)]
      public bool DesignSelect {
         get { return _designSelect; }
         set { _designSelect = value; }
      }

      //============================================================
      // <T>获得设计坐标。</T>
      //============================================================
      [Browsable(false)]
      public SIntPoint2 DesignLocation {
         get { return _designLocation; }
      }

      //============================================================
      // <T>获得设计尺寸。</T>
      //============================================================
      [Browsable(false)]
      public SIntSize2 DesignSize {
         get { return _designSize; }
      }

      //============================================================
      // <T>获得坐标。</T>
      //============================================================
      [Browsable(false)]
      public SIntPoint2 Location {
         get { return _location; }
      }

      //============================================================
      // <T>获得尺寸。</T>
      //============================================================
      [Browsable(false)]
      public SIntSize2 Size {
         get { return _size; }
      }

      //============================================================
      // <T>配置处理。</T>
      //
      // @param args 参数
      //============================================================
      public void Setup(SUiSetupArgs args) {
         _context = args.context;
         _pointForeBrush = _context.Device.CreateSolidBrush(Color.Black);
         _pointBackBrush = _context.Device.CreateSolidBrush(Color.White);
         _selectForeColor.brush = _context.Device.CreateSolidBrush(0.2f, 0.2f, 0.2f, 1.0f);
         _selectBackColor.brush = _context.Device.CreateSolidBrush(1.0f, 1.0f, 1.0f, 0.5f);
         // 创建辅助点
         for (int n = 0; n < (int)ERcAlign.Count; n++) {
            SUiControlPoint point = new SUiControlPoint();
            point.AlignCd = n;
            _points[n] = point;
         }
      }

      //============================================================
      // <T>获得或设置控件。</T>
      //============================================================
      public FUiControl Control {
         get { return _control; }
         set {
            _control = value;
            _location.Assign(value.CalculateDisplayPosition());
            _size.Assign(_control.Size, _context.Scale);
         }
      }

      //============================================================
      // <T>测试对齐方式。</T>
      //
      // @param x 横坐标
      // @param y 纵坐标
      // @return 
      //============================================================
      public int TestAlignCd(int x, int y) {
         foreach(SUiControlPoint point in _points) {
            if(point.Constains(x, y)) {
               return point.AlignCd;
            }
         }
         return (int)ERcAlign.None;
      }

      //============================================================
      // <T>计算辅助点集合。</T>
      //============================================================
      public void CalculatePoints() {
         // 计算数据
         float scale = _context.Scale;
         int pointX = _location.X;
         int pointY = _location.Y;
         int width = _size.Width;
         int height = _size.Height;
         int centerX = width / 2;
         int centerY = height / 2;
         // 更新内容
         _points[(int)ERcAlign.Center].Update(pointX + centerX, pointY + centerY);
         _points[(int)ERcAlign.Left].Update(pointX, pointY + centerY);
         _points[(int)ERcAlign.LeftTop].Update(pointX, pointY);
         _points[(int)ERcAlign.Top].Update(pointX + centerX, pointY);
         _points[(int)ERcAlign.RightTop].Update(pointX + width - 1, pointY);
         _points[(int)ERcAlign.Right].Update(pointX + width - 1, pointY + centerY);
         _points[(int)ERcAlign.RightBottom].Update(pointX + width - 1, pointY + height);
         _points[(int)ERcAlign.Bottom].Update(pointX + centerX, pointY + height);
         _points[(int)ERcAlign.LeftBottom].Update(pointX, pointY + height);
      }

      //============================================================
      // <T>绘制处理点。</T>
      //
      // @param args 参数
      //============================================================
      public void DrawPoint(SUiControlPoint point) {
         int x = point.Point.X - 2;
         int y = point.Point.Y - 2;
         // 绘制边线
         FDxContext2d context = _context.Context;
         context.FillRectangle(_pointBackBrush, x, y, _pointForeSize.Width, _pointForeSize.Height);
         context.DrawRectangle(_pointForeBrush, x, y, _pointBackSize.Width, _pointBackSize.Height);
      }

      //============================================================
      // <T>开始绘制处理。</T>
      //
      // @param args 参数
      //============================================================
      public void Draw(SUiDrawArgs args) {
         FDxContext2d context = _context.Context;
         context.TransformIdentity();
         // 计算坐标
         _location.Assign(_control.CalculateDisplayPosition());
         _size.Assign(_control.CalculateDisplaySize());
         // 绘制选择区域
         if(_designSelect) {
            // 绘制底版
            context.FillRectangle(_selectBackColor.brush, _location.X, _location.Y, _size.Width, _size.Height);
            // 绘制边线
            context.DrawRectangle(_selectForeColor.brush, _location.X, _location.Y, _size.Width, _size.Height);
         }
         // 绘制辅助点区域
         if(_designFocus) {
            CalculatePoints();
            foreach(SUiControlPoint point in _points) {
               DrawPoint(point);
            }
         }
      }

      //============================================================
      // <T>存储边界内容。</T>
      //============================================================
      public void BoundsStore() {
         _designLocation.Assign(_control.Location);
         _designSize.Assign(_control.Size);
      }

      //============================================================
      // <T>修正边界坐标内容。</T>
      //
      // @param x 横向移动位置
      // @param y 纵向移动位置
      //============================================================
      public void BoundsLocation(int x, int y) {
         _control.Location.Set(_designLocation.X + x, _designLocation.Y + y);
      }

      //============================================================
      // <T>修正边界尺寸内容。</T>
      //
      // @param width 宽度
      // @param height 高度
      //============================================================
      public void BoundsSize(int width, int height) {
         _control.Size.Set(_designSize.Width + width, _designSize.Height + height);
      }
   }
}
