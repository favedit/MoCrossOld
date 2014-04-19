using System.Drawing;
using System.Windows.Forms;
using MO.Common.Collection;
using MO.Common.Geom;
using MO.Common.Lang;
using MO.Content2d;
using MO.Content2d.Resource.Picture;
using MO.Direct2d.Device;
using MO.Direct2d.Draw;
using MoMobile.Map.Common;
using MoMobile.Template.Enemy;

namespace MoMobile.Map.Core
{
   //============================================================
   // <T>地图绘制类。</T>
   //============================================================
   public class FUiFormDesigner
   {
      // 环境
      protected FDxContext2d _context = new FDxContext2d();

      // 尺寸
      protected SIntSize2 _size = new SIntSize2();

      // 格子尺寸
      protected SIntSize2 _cellSize = new SIntSize2();

      // 格子尺寸
      protected SIntSize2 _cellCount = new SIntSize2();

      // 
      protected SIntPoint2 _location = new SIntPoint2();

      //
      protected FMap<string, FDxBitmap> _dxBitmapSet = new FMap<string, FDxBitmap>();

      // 设计底版
      protected Panel _designPanel;

      // 设计背景颜色
      protected Color _designBackColor = Color.Black;

      // 设计背景颜色色刷
      //protected SUiColor _designBackBrush;

      // 地图
      protected FMbMap _map;

      //============================================================
      // <T>获得或设置设备。</T>
      //============================================================
      public FDxDevice2d Device {
         get { return _context.Device; }
         set {
            // 设置设备
            _context.Device = value;
         }
      }

      //============================================================
      // <T>获得或设置设计底板。</T>
      //============================================================
      public Panel DesignPanel {
         get { return _designPanel; }
         set { _designPanel = value; }
      }

      //============================================================
      // <T>获得或设置大小。</T>
      //============================================================
      public SIntSize2 Size {
         get { return _size; }
         set { _size = value; }
      }

      //============================================================
      // <T>获得或设置地图。</T>
      //============================================================
      public FMbMap Map {
         get { return _map;}
         set { _map = value; }
      }

      //============================================================
      // <T>获得或设置偏移点。</T>
      //============================================================
      public SIntPoint2 Location {
         get { return _location; }
         set { _location = value; }
      }

      //============================================================
      // <T>获得或设置设计背景颜色。</T>
      //============================================================
      //public Color DesignBackColor {
      //   get { return _designBackColor; }
      //   set {
      //      _designBackColor = value;
      //      _designBackBrush = _context.BuildDesignColor(value);
      //      if (_designFrame != null) {
      //         _designFrame.DesignBackColor = value;
      //      }
      //   }
      //}
      
      //============================================================
      // <T>改变大小。</T>
      //
      // @param width 宽度
      // @param height 高度
      //============================================================
      public void Resize(int width, int height) {
         // 设置大小
         _size.Set(width, height);
         // 刷新数据
         Refresh();
         Paint();
      }

      //============================================================
      // <T>刷新处理。</T>
      //============================================================
      public void Refresh() {
         int left = (_size.Width - (int)(_map.Size.Width)) / 2;
         if (left < 0) {
            left = 0;
         }
         int top = (_size.Height - (int)(_map.Size.Height)) / 2;
         if (top < 0) {
            top = 0;
         }
      }

      //============================================================
      // <T>绘制线。</T>
      //============================================================
      public void DrawLine() {
         // 获取格子宽 高
         int cellWidth = _cellSize.Width;
         int cellHeight = _cellSize.Height;
         // 创建格子颜色
         FDxSolidBrush brush = _context.Device.CreateSolidBrush(Color.White);
         // 绘制竖线
         for (int n = 0; n <= _cellCount.Width; n++) {
            int beginLocationX = n * cellWidth;
            int beginLocationY = 0;
            int endLocationX = n * cellWidth;
            int endLocationY = _cellCount.Height * cellHeight;
            _context.DrawLine(brush, beginLocationX - _location.X, beginLocationY - _location.Y, endLocationX - _location.X, endLocationY - _location.Y);

         }
         // 绘制横线
         for (int n = 0; n <= _cellCount.Height; n++) {
            int beginLocationX = 0;
            int beginLocationY = n * cellHeight;
            int endLocationX = _cellCount.Width * cellWidth;
            int endLocationY = n * cellHeight;
            _context.DrawLine(brush, beginLocationX - _location.X, beginLocationY - _location.Y, endLocationX - _location.X, endLocationY - _location.Y);
         }
      }

      //============================================================
      // <T>绘制出生点。</T>
      //============================================================
      public void DrawBirths() {
         FObjects<FMbMapBirth> births = _map.Births;
         if (!births.IsEmpty()) {
            int count = births.Count;
            for (int n = 0; n < count; n++) {
               FMbMapBirth birth = births[n];
               SIntPoint2 location = birth.Location;
               // 获取敌机集合
               FObjects<FMbMapBirthEnemy> enemys = birth.BirthEnemys;
               int enemyCount = enemys.Count;
               for (int x = 0; x < enemyCount; x++) {
                  FMbMapBirthEnemy birthEnemy = enemys[x];
                  int templateId = birthEnemy.TemplateId;
                  FMbTplEnemy enemy = RMobileManager.TemplateConsole.EnemyConsole.FingById(templateId);
                  int resourceRid = enemy.ResourceRid;
                  // 获取资源图片
                  FRsResourcePicture resource = RContent2dManager.ResourceConsole.FindOpen(resourceRid) as FRsResourcePicture;
                  Bitmap resourceMap = resource.Bitmap.Native;
                  // 创建绘制对象
                  FDxBitmap bitmap = null;
                  if (!_dxBitmapSet.Contains(resourceRid.ToString())) {
                     bitmap = _context.Device.CreateBitmap(resourceMap);
                     _dxBitmapSet.Set(resourceRid.ToString(), bitmap);
                  } else {
                     bitmap = _dxBitmapSet.Get(resourceRid.ToString());
                  }
                  _context.DrawBitmap(bitmap, location.X - _location.X, location.Y - _location.Y);
               }
            }
         }
      }

      //============================================================
      // <T>绘制瓦片。</T>
      //============================================================
      public void DrawMapTile(FMbMapTile mapTile,SIntPoint2 cellIndex) {
         
         Bitmap bm = mapTile.Resource;
         int cellIndexX = cellIndex.X;
         int cellIndexY = cellIndex.Y;

         int cellLocationX = cellIndexX * _cellSize.Width;
         int cellLocationY = cellIndexY * _cellSize.Height;

         FDxBitmap bitmap = null;
         // 创建图片资源
         if (!_dxBitmapSet.Contains(mapTile.Id.ToString())) {
            bitmap = _context.Device.CreateBitmap(mapTile.Resource);
            _dxBitmapSet.Set(mapTile.Id.ToString(), bitmap);
         } else {
            bitmap = _dxBitmapSet.Get(mapTile.Id.ToString());
         }
         _context.DrawBitmap(bitmap, cellLocationX - _location.X, cellLocationY - _location.Y);
      }

      //============================================================
      // <T>绘制层。</T>
      //============================================================
      public void DrawLayers() {
         FObjects<FMbMapLayer> layers = _map.Layers;
         if (!layers.IsEmpty()) {
            
            int count = layers.Count;
            for (int n = 0; n < count; n++) {
               FMbMapLayer layer = layers[n];
               if (layer.OptionValid) {
                  _cellSize = layer.CellSize;
                  _cellCount = layer.CellCount;

                  FObjects<FMbMapCell> cells = layer.MapCell;
                  int cellCount = cells.Count;
                  for (int x = 0; x < cellCount; x++) {
                     FMbMapCell cell = cells[x];
                     int resourceId = cell.ResourceId;
                     if (0 == resourceId) {
                        continue;
                     }
                     SIntPoint2 cellIndex = cell.Index;

                     FMbMapTile mapTile = RMobileManager.MapTileConsole.FindMapTile(resourceId);
                     if (null != mapTile) {
                        DrawMapTile(mapTile, cellIndex);
                     }
                  }
               }
               // 绘制方格
               DrawLine();
            }
         }
      }

      

      //============================================================
      // <T>绘制处理。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      public void Paint() {
         // 获得设备
         FDxDevice2d device = _context.Device;
         _context.TransformIdentity();
         // 开始绘制
         device.BeginDraw();
         // 清空目标
         _context.Clear();
         // 绘制层
         DrawLayers();
         // 绘制出生点
         DrawBirths();
         // 结束绘制
         device.EndDraw();
      }

      //============================================================
      // <T>释放处理。</T>
      //============================================================
      public void Dispose() {
         if (_context != null) {
            if (_context.Device != null) {
               _context.Device.Dispose();
               _context.Device = null;
            }
            _context = null;
         }
      }
   }
}
