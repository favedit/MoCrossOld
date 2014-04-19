using MO.Common.Content;
using MO.Common.Geom;
using MO.Common.IO;
using MO.Common.Lang;
using MoMobile.Map.Designer;

namespace MoMobile.Map.Common
{
   //============================================================
   // <T>地图对象。</T>
   //============================================================
   public class FMbMap : FObject
   {
      // 模板编号
      protected int _tid;

      // 名称
      protected string _name;

      // 标签
      protected string _label;

      // 大小
      protected SIntSize2 _size = new SIntSize2();

      // 出生点
      protected SIntPoint2 _birthLocation = new SIntPoint2();

      // 层集合
      protected FObjects<FMbMapLayer> _layers = new FObjects<FMbMapLayer>();

      // 层节点
      protected FXmlNode _layerListNode = new FXmlNode();

      // 出生点集合
      protected FObjects<FMbMapBirth> _births = new FObjects<FMbMapBirth>();

      // 打开标志
      protected bool _opened;

      // 出生点节点
      protected FXmlNode _birthListNode = new FXmlNode();
      
      // 目录
      protected string _directory;

      //============================================================
      // <T>获取或设置模板编号。</T>
      //============================================================
      public int Tid {
         get { return _tid; }
         set { _tid = value; }
      }

      //============================================================
      // <T>获取或设置名称。</T>
      //============================================================
      public string Name {
         get { return _name; }
         set { _name = value; }
      }

      //============================================================
      // <T>获取或设置标签。</T>
      //============================================================
      public string Label {
         get { return _label; }
         set { _label = value; }
      }

      //============================================================
      // <T>获取大小。</T>
      //============================================================
      public SIntSize2 Size {
         get { return _size; }
      }

      //============================================================
      // <T>获取出生点。</T>
      //============================================================
      public SIntPoint2 BirthLocation {
         get { return _birthLocation; }
      }

      //============================================================
      // <T>获取层集合。</T>
      //============================================================
      public FXmlNode LayerListNode {
         get { return _layerListNode; }
      }

      //============================================================
      // <T>获取层集合。</T>
      //============================================================
      public FObjects<FMbMapLayer> Layers {
         get { return _layers; }
      }
      //============================================================
      // <T>获取出生点集合。</T>
      //============================================================
      public FObjects<FMbMapBirth> Births {
         get { return _births; }
      }

      //============================================================
      // <T>获取或设置目录。</T>
      //============================================================
      public string Directory {
         get { return _directory; }
         set { _directory = value; }
      }

      //============================================================
      // <T>根据坐标找到出生点。</T>
      //============================================================
      public FMbMapBirth FindByLocation(SIntPoint2 point) {
         foreach (FMbMapBirth birth in _births) {
            SIntPoint2 location = birth.Location;
            if (point.ToString() == location.ToString()) {
               return birth;
            }
         }
         return null;
      }

      //============================================================
      // <T>加载配置文件。</T>
      //============================================================
      public void LoadConfig(FXmlNode config) {
         // 加载基本设置
         _tid = config.GetInteger("tid", _tid);
         _name = config.Get("name", _name);
         _label = config.Get("label", _label);
         if (config.Contains("size")) {
            _size.Parse(config.Get("size"));
         }
         if (config.Contains("birth_location")) {
            _birthLocation.Parse(config.Get("birth_location"));
         }

         // 加载层
         _layerListNode = config.Find("Layers");
         if (null == _layerListNode) {
            return;
         }
         foreach (FXmlNode layerNode in _layerListNode.Nodes) {
            FDsMapLayer layer = new FDsMapLayer();
            layer.Resource.LoadConfig(layerNode);
            _layers.Push(layer.Resource);
         }

         // 加载出生点
         _birthListNode = config.Find("Births");
         if (null == _birthListNode) {
            return;
         }
         foreach (FXmlNode birthNode in _birthListNode.Nodes) {
            FDsMapBirth birth = new FDsMapBirth();
            birth.Resource.LoadConfig(birthNode);
            _births.Push(birth.Resource);
         }
      }

      //============================================================
      // <T>加载配置文件。</T>
      //============================================================
      public void LoadDirectory() {
         // 获得文件名称
         string fileName = _directory + @"\config.xml";
         if (!RFile.Exists(fileName)) {
            return;
         }
         // 加载设置
         using (FXmlDocument xmldoc = new FXmlDocument(fileName)) {
            LoadConfig(xmldoc.Root);
         }
      }

      //============================================================
      // <T>存储配置文件。</T>
      //============================================================
      public void SaveConfig(FXmlNode node) {
      }

      //============================================================
      // <T>序列化。</T>
      //============================================================
      public void Serialize(IOutput output) {
         // 输出属性
         output.WriteInt32(_tid);
         output.WriteUTFString(_label);
         _size.Serialize16(output);
         _birthLocation.Serialize16(output);
         // 输出层集合
         output.WriteInt16((short)_layers.Count);
         foreach (FMbMapLayer layer in _layers) {
            layer.Serialize(output);
         }
      }

      //============================================================
      // <T>打开内容。</T>
      //============================================================
      public void Open() {
         if (!_opened) {
         }
      }
   }
}
