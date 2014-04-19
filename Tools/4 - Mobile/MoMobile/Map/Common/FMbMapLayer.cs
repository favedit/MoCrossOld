using MO.Common.Content;
using MO.Common.Geom;
using MO.Common.IO;
using MO.Common.Lang;
using MoMobile.Map.Designer;

namespace MoMobile.Map.Common
{
   //============================================================
   // <T>地图层对象。</T>
   //============================================================
   public class FMbMapLayer
   {
      // 有效性
      protected bool _optionValid;

      // 格子数量
      protected SIntSize2 _cellCount = new SIntSize2();

      // 格子大小
      protected SIntSize2 _cellSize = new SIntSize2();

      // 瓦片节点
      protected FXmlNode _tileNode = new FXmlNode();

      // 卷动方式
      protected EMapLayerScroll _scrollCd;

      // 层类型
      protected EMapLayerType _typeCd;

      // 回卷方式
      protected EMapLayerWrap _wrapCd;

      // 卷动速度
      protected float _scrollSpeed;

      // 格子集合
      protected FObjects<FMbMapCell> _mapCell = new FObjects<FMbMapCell>();

      //============================================================
      // <T>获取或得到有效性。</T>
      //============================================================
      public bool OptionValid {
         get { return _optionValid; }
         set { _optionValid = value; }
      }

      //============================================================
      // <T>获取或得到格子数量。</T>
      //============================================================
      public SIntSize2 CellCount {
         get { return _cellCount; }
         set { _cellCount = value; }
      }

      //============================================================
      // <T>获取或得到格子大小。</T>
      //============================================================
      public SIntSize2 CellSize {
         get { return _cellSize; }
         set { _cellSize = value; }
      }

      //============================================================
      // <T>获取或得到卷动方式。</T>
      //============================================================
      public EMapLayerScroll ScrollCd {
         get { return _scrollCd; }
         set { _scrollCd = value; }
      }

      //============================================================
      // <T>获取或得到层类型。</T>
      //============================================================
      public EMapLayerType TypeCd {
         get { return _typeCd; }
         set { _typeCd = value; }
      }

      //============================================================
      // <T>获取或得到回卷方式。</T>
      //============================================================
      public EMapLayerWrap WrapCd {
         get { return _wrapCd; }
         set { _wrapCd = value; }
      }

      //============================================================
      // <T>获取或得到卷动速度。</T>
      //============================================================
      public float ScrollSpeed {
         get { return _scrollSpeed; }
         set { _scrollSpeed = value; }
      }

      //============================================================
      // <T>获取格子集合。</T>
      //============================================================
      public FObjects<FMbMapCell> MapCell {
         get { return _mapCell; }
      }

      //============================================================
      // <T>瓦片节点集合。</T>
      //============================================================
      public FXmlNode TileNode {
         get { return _tileNode; }
      }

      public FMbMapCell FingCellByIndex(int x,int y) {
         int index = (_cellCount.Width) * y + x;
         return _mapCell[index];
      }

      //============================================================
      // <T>加载配置文件。</T>
      //============================================================
      public void LoadConfig(FXmlNode config) {
         if (config.Contains("cell_count")) {
            _cellCount.Parse(config.Get("cell_count"));
         }
         if (config.Contains("cell_size")) {
            _cellSize.Parse(config.Get("cell_size"));
         }
         if (config.Contains("type_cd")) {
            _typeCd = REnum.ToValue<EMapLayerType>(config.Get("type_cd"));
         }
         if (config.Contains("scroll_cd")) {
            _scrollCd = REnum.ToValue<EMapLayerScroll>(config.Get("scroll_cd"));

         }
         if (config.Contains("wrap_cd")) {
            _wrapCd = REnum.ToValue<EMapLayerWrap>(config.Get("wrap_cd"));
         }
         if (config.Contains("scroll_speed")) {
            _scrollSpeed = config.GetFloat("scroll_speed");
         }
         _tileNode = config.Find("Tiles");
         if (null == _tileNode) {
            return;
         }
         // 创建所有格子
         for (int m = 0; m < _cellCount.Height; m++) {
            for (int n = 0; n < _cellCount.Width; n++) {
               FMbMapCell cell = new FMbMapCell();
               cell.Index = new SIntPoint2(n, m);
               _mapCell.Push(cell);
            }
         }

         // 加载格子资源图片编号
         foreach (FXmlNode cellNode in _tileNode.Nodes) {
            FDsMapCell cell = new FDsMapCell();
            cell.Resource.LoadConfig(cellNode);
            SIntPoint2 cellIndex = cell.Resource.Index;
            FMbMapCell c = FingCellByIndex(cellIndex.X, cellIndex.Y);
            c.ResourceId = cell.Resource.ResourceId;
         }  
      }

      //============================================================
      // <T>序列化。</T>
      //============================================================
      public void Serialize(IOutput output) {
         output.WriteInt8((sbyte)(int)_typeCd);
         output.WriteInt8((sbyte)(int)_wrapCd);
         output.WriteInt8((sbyte)(int)_scrollCd);
         output.WriteFloat(_scrollSpeed);
         output.WriteUint16((ushort)_cellCount.Width);
         output.WriteUint16((ushort)_cellCount.Height);
         output.WriteUint16((ushort)_cellSize.Width);
         output.WriteUint16((ushort)_cellSize.Height);

         int mapCellCount = 0;
         for (int i = 0; i < _mapCell.Count; i++) {
            FMbMapCell cell = _mapCell[i];
            if (0 != cell.ResourceId) {
               mapCellCount++;
            }
         }
         output.WriteInt32(mapCellCount);
         foreach (FMbMapCell cell in _mapCell) {
            if (0!=cell.ResourceId) {
               cell.Serialize(output);
            }
         }
      }
   }
}
