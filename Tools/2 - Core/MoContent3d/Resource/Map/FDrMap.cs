using System.Drawing;
using MO.Common.Collection;
using MO.Common.Content;
using MO.Common.Geom;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Common.System;
using MO.Core.Forms.Core;
using MO.Content3d.Common;
using MO.Content3d.Resource.Material;

namespace MO.Content3d.Resource.Map
{
   //============================================================
   // <T>纹理信息</T>
   //============================================================
   public class FDrMap : FObject
   {
      private static ILogger _logger = RLogger.Find(typeof(FDrMap));

      // 标志
      protected bool _initialized;

      protected string _name;

      // 编号
      protected int _id;      
          
      // 名称
      protected string _label;

      // 路径 
      protected string _path;

      // 层数 
      protected int _layerCount;

      // 原图大小
      protected SIntSize _size = new SIntSize();

      // 切割尺寸
      protected SIntSize _range = new SIntSize();

      // 深度
      protected float _deep;

      // 导出路径
      protected string _exprotPath;

      // 加载路径
      protected string _configFileName;

      // 导出文件名称
      protected string _exportFileName;

      // 导出文件路径
      protected string _exportFilePath;

      // 材质图片
      protected string _textureColor;

      // 材质高度图片
      protected string _textureHeight;

      // 高度数据信息
      protected FByteFile _heightData = new FByteFile();

      // 颜色数据信息
      protected FBitmap _colorData = new FBitmap();

      // 切割间距
      protected int _incise;

      // 层数据信息
      protected FVector<FDrMapLayer> _layers = new FVector<FDrMapLayer>();

      // 所属对象
      protected object _tag;

      //============================================================
      public FDrMap() {
      }

      //============================================================
      public int Id {
         get { return _id; }
         set { _id = value; }
      }

      //============================================================
      public string Name {
         get { return _name; }
         set { _name = value; }
      }

      //============================================================
      public string Label {
         get { return _label; }
         set { _label = value; }
      }

      //============================================================
      public string Path {
         get { return _path; }
         set { _path = value; }
      }

      //============================================================
      public string ExprotPath {
         get { return _exprotPath; }
         set { _exprotPath = value; }
      }

      //============================================================
      public int LayerCount {
         get { return _layerCount; }
         set { _layerCount = value; }
      }

      //============================================================
      public SIntSize Size {
         get { return _size; }
      }

      //============================================================
      public SIntSize Range {
         get { return _range; }
      }

      //============================================================
      public float Deep {
         get { return _deep; }
         set { _deep = value; }
      }

      //============================================================
      public object Tag {
         get { return _tag; }
         set { _tag = value; }
      }

      //============================================================
      public int Incise {
         get { return _incise; }
         set { _incise = value; }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void LoadConfig(FXmlNode xconfig) {
         // 加载编号
         _id = xconfig.GetInteger("id");
         // 加载名称
         _name = xconfig.Get("name");
         // 加载层数
         _layerCount = xconfig.GetInteger("layer_count");
         // 加载尺寸
         _size.Parse(xconfig.Get("size"));
         // 加载切割尺寸
         _range.Parse(xconfig.Get("range"));
         // 加载深度
         _deep = xconfig.GetFloat("deep");

         _incise =RInt.Parse( xconfig.GetFloat("deep"));
         //............................................................
         // 加载高度纹理
         FXmlNode xheight = xconfig.Find("Height");
         if (null != xheight) {
            _textureHeight = xheight.Get("source");
         }
         // 加载颜色纹理
         FXmlNode xcolor = xconfig.Find("Color");
         if (null != xcolor) {
            _textureColor = xcolor.Get("source");
         }
         //............................................................
         // 加载层信息
         FXmlNode xlayers = xconfig.Find("Layers");
         if(null != xlayers) {
            foreach(FXmlNode xlayer in xlayers.Nodes){
               FDrMapLayer layer = new FDrMapLayer();
               layer.Index = _layers.Count;
               layer.LoadConfig(xlayer);
               _layers.Push(layer);
            }
         }
      }

      //============================================================
      // <T>存储配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void SaveConfig(FXmlNode config) {
         // 设置信息
         config.Set("id", _id);
         config.Set("name", _name);
         config.Set("layer_count", _layerCount);
         config.Set("size", _size.ToString());
         config.Set("range", _range.ToString());
         config.Set("deep", _deep);
         //............................................................
         // 存储高度纹理
         FXmlNode xheight = config.CreateNode("Height");
         xheight.Set("source", _textureHeight);
         // 存储颜色纹理
         FXmlNode xcolor = config.CreateNode("Color");
         xcolor.Set("source", _textureColor);
         //............................................................
         // 存储层信息
         FXmlNode xlayers = config.CreateNode("Layers");
         if(null != xlayers) {
            foreach(FDrMapLayer layer in _layers) {
               FXmlNode xlayer = xlayers.CreateNode("Layer");
               layer.SaveConfig(xlayer);
            }
         }
      }

      //============================================================
      // <T>打开材质信息。</T>
      //============================================================
      public void Open() {
         if (!_initialized) {
            _configFileName = _path + "\\config.xml";
            _exportFileName = _exprotPath + "\\mp_" + _name + ".swf";
            // 加载设置文件
            if(RFile.Exists(_configFileName)){
               LoadConfigFile(_configFileName);
            }
            // 设置输出路径
            _exportFilePath = _exprotPath + "\\" + RInt.Pad(_id, 4);
            RDirectory.MakeDirectories(_exportFilePath);
            _initialized = true;
         }
      }

      //============================================================
      public void LoadConfigFile(string fileName) {
         FXmlDocument xdoc = new FXmlDocument(fileName);
         FXmlNode xroot = xdoc.Root;
         FXmlNode xmap = xroot.Find("Map");
         if (null != xmap) {
            LoadConfig(xmap);
         }
      }

      //============================================================
      public void SaveConfigFile(string fileName) {
         FXmlDocument xdoc = new FXmlDocument();
         FXmlNode xroot = xdoc.Root;
         SaveConfig(xroot.CreateNode("Map"));
         xdoc.SaveFile(fileName);
         _logger.Debug(this, "SaveConfigFile", "Save map config success. (file_name={0})", fileName);
      }

      //============================================================
      public void Store() {
         SaveConfigFile(_configFileName);
      }

      //============================================================
      // <T>序列化数据。</T>
      //============================================================
      public void ExportRange(int cx, int cy) {
         FCompressFile file = new FCompressFile();
         // 存储编号
         file.WriteUint32((uint)_id);
         // 存储层数
         file.WriteUint16((ushort)_layerCount);
         // 当前地图的宽度和高度
         file.WriteUint16((ushort)_size.Width);
         file.WriteUint16((ushort)_size.Height);
         // 当前范围的宽度和高度
         file.WriteUint16((ushort)_range.Width);
         file.WriteUint16((ushort)_range.Height);
         file.WriteFloat(_deep);
         // 当前范围的位置坐标
         file.WriteUint16((ushort)cx);
         file.WriteUint16((ushort)cy);
         // 计算开始坐标
         int offsetX = _range.Width * cx;
         int offsetY = _range.Height * cy;
         // 输出数据
         int width = _range.Width;
         int height = _range.Height;         
         for(int y = 0; y <= height; y++) {
            for(int x = 0; x <= width; x++) {
               //............................................................
               // 计算坐标位置
               int positionX = offsetX + x;
               if(positionX >= _size.Width) {
                  positionX = _size.Width - 1;
               }
               int positionY = _size.Height - (offsetY + y) - 1;
               if(positionY < 0) {
                  positionY = 0;
               }else if(positionY >= _size.Height) {
                  positionY = _size.Height - 1;
               }
               int positionHeight = 2 * (_size.Width * positionY + positionX);
               float h = _heightData[positionHeight] + _heightData[positionHeight + 1] * 256;
               // 计算点高度(1, 0)
               SFloatVector3 tangent = new SFloatVector3(1, 0, 0);
               if(positionX < _size.Width - 1) {
                  int tangentPosition = 2 * (_size.Width * positionY + (positionX) + 1);
                  float h1 = _heightData[tangentPosition] + _heightData[tangentPosition + 1] * 256;
                  float dx = (h1 - h) / 65535 * _deep;
                  tangent.Set(1, dx, 0);
               }
               tangent.Normalize();
               // 计算点高度(0, 1)
               SFloatVector3 binormal = new SFloatVector3(0, 0, 1);
               if(positionY < _size.Height - 1) {
                  int binormalPosition = 2 * (_size.Width * (positionY + 1) + positionX);
                  float h2 = _heightData[binormalPosition] + _heightData[binormalPosition + 1] * 256;
                  float dy = (h2 - h) / 65535 * _deep;
                  binormal.Set(0, dy, 1);
               }
               binormal.Normalize();
               // 计算法线
               SFloatVector3 normal = new SFloatVector3();
               binormal.Cross(tangent, normal);
               normal.Normalize();
               //............................................................
               // 输出像素的高度
               file.WriteUint8(_heightData[positionHeight]);
               file.WriteUint8(_heightData[positionHeight + 1]);
               // 输出像素的颜色
               Color color = _colorData.Bitmap.GetPixel(positionX, positionY);
               file.WriteUint8(color.R);
               file.WriteUint8(color.G);
               file.WriteUint8(color.B);
               // 输出像素的法线
               file.WriteUint8((byte)((normal.X + 1) * 0.5 * 255));
               file.WriteUint8((byte)((normal.Y + 1) * 0.5 * 255));
               file.WriteUint8((byte)((normal.Z + 1) * 0.5 * 255));
               // 输出像素的层数据
               for(int n = 0; n < _layerCount; n++) {
                  FBitmap layerData = _layers[n].Bitmap;
                  Color layerColor = layerData.Bitmap.GetPixel(positionX, positionY);
                  file.WriteUint8(layerColor.R);
               }               
            }            
         }
         // 输出文件
         string rangeFile = "tile_" + RInt.Pad(cx, 2) + "_" + RInt.Pad(cy, 2) + ".swf";         
         file.SaveFile(_exportFilePath + "\\" + rangeFile);
      }

      //============================================================
      // <T>序列化数据。</T>
      //============================================================
      public void Export() {
         // 加载高度图
         _heightData.LoadFile(_path + "/texture/" + _textureHeight);
         _colorData.LoadFile(_path + "/texture/" + _textureColor);
         for (int n = 1; n <= _layerCount; n++) {
            FBitmap layerBitmap = _layers[n - 1].Bitmap;
            layerBitmap.LoadFile(_path + "/texture/level-" + n + ".jpg");
         }
         //............................................................
         FCompressFile file = new FCompressFile();
         // 存储编号
         file.WriteUint32((uint)_id);
         // 存储层数
         file.WriteUint16((ushort)_layerCount);
         // 当前地图的宽度和高度
         file.WriteUint16((ushort)_size.Width);
         file.WriteUint16((ushort)_size.Height);
         // 当前范围的宽度和高度
         file.WriteUint16((ushort)_range.Width);
         file.WriteUint16((ushort)_range.Height);
         file.WriteFloat(_deep);
         // 存储材质列表
         file.WriteInt16((short)_layers.Count);
         foreach(FDrMapLayer layer in _layers) {
            string materialCode = RDrUtil.FormatPathToCode(layer.MaterialName);
            file.WriteInt16((short)layer.Index);
            file.WriteString(materialCode);
            FDrMaterialGroup material = RContent3dManager.MaterialConsole.FindGroup(layer.MaterialName);
            material.Serialize(file);
         }
         // 输出文件
         file.SaveFile(_exportFilePath + "\\config.swf");
         //............................................................
         // 导出地图块
         int cx = _size.Width / _range.Width;
         int cy = _size.Height / _range.Height;         
         for (int y = 0; y < cy; y++) {
            for(int x = 0; x < cx; x++) {
               ExportRange(x, y);
            }
         }
        // RangeIncise();
         _logger.Debug(this, "Export", "Export template success. (file_name={0})", _exportFileName);
      }

      //============================================================
      public void RangeIncise() {
         FCompressFile file = new FCompressFile();
         int height = _size.Height;
         int width = _size.Width;
         for (int y = 0; y <= height; y++) {
            for (int x = 0; x <= width; x++) {
               //............................................................
               // 计算坐标位置
               int positionX = x;
               if (positionX >= _size.Width) {
                  positionX = _size.Width - 1;
               }
               int positionY =  y;
               if (positionY >= _size.Height) {
                  positionY = _size.Height - 1;
               }
               int positionHeight = 2 * (_size.Width * positionY + positionX);
               float h = _heightData[positionHeight] + _heightData[positionHeight + 1] * 256;
               // 计算点高度(1, 0)
               SFloatVector3 tangent = new SFloatVector3(1, 0, 0);
               if (positionX < _size.Width - 1) {
                  int tangentPosition = 2 * (_size.Width * positionY + (positionX) + 1);
                  float h1 = _heightData[tangentPosition] + _heightData[tangentPosition + 1] * 256;
                  float dx = (h1 - h) / 65535 * _deep;
                  tangent.Set(1, dx, 0);
               }
               tangent.Normalize();
               // 计算点高度(0, 1)
               SFloatVector3 binormal = new SFloatVector3(0, 0, 1);
               if (positionY < _size.Height - 1) {
                  int binormalPosition = 2 * (_size.Width * (positionY + 1) + positionX);
                  float h2 = _heightData[binormalPosition] + _heightData[binormalPosition + 1] * 256;
                  float dy = (h2 - h) / 65535 * _deep;
                  binormal.Set(0, dy, 1);
               }
               binormal.Normalize();
               // 计算法线
               SFloatVector3 normal = new SFloatVector3();
               binormal.Cross(tangent, normal);
               normal.Normalize();
               //............................................................
               // 输出像素的高度
               file.WriteUint8(_heightData[positionHeight]);
               file.WriteUint8(_heightData[positionHeight + 1]);
               // 输出像素的颜色
               Color color = _colorData.Bitmap.GetPixel(positionX, positionY);
               file.WriteUint8(color.R);
               file.WriteUint8(color.G);
               file.WriteUint8(color.B);
               // 输出像素的法线
               file.WriteUint8((byte)((normal.X + 1) * 0.5 * 255));
               file.WriteUint8((byte)((normal.Y + 1) * 0.5 * 255));
               file.WriteUint8((byte)((normal.Z + 1) * 0.5 * 255));
               // 输出像素的层数据
               for (int n = 0; n < _layerCount; n++) {
                  FBitmap layerData = _layers[n].Bitmap;
                  Color layerColor = layerData.Bitmap.GetPixel(positionX, positionY);
                  file.WriteUint8(layerColor.R);
               }
               x += 8;
            }
            y += 8;
         }
         /*string rangeFile = "tile_" + RInt.Pad(x, 2) + "_" + RInt.Pad(cy, 2) + ".swf";         */
      }

      //============================================================
      public void Close() {
         _initialized = false;
         RSystem.Collect();
      }
   }
}
