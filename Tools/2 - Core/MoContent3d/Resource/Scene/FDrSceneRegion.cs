using MO.Common.Content;
using MO.Common.Geom;
using MO.Common.IO;
using MO.Common.Lang;

namespace MO.Content3d.Resource.Scene
{
   //============================================================
   // <T>场景区域。</T>
   //============================================================
   public class FDrSceneRegion : FObject
   {
      // 颜色
      protected SFloatColor4 _color = new SFloatColor4();

      // 颜色级别
      protected SFloatColor4 _colorLevel = new SFloatColor4();

      // 雾化近平面
      protected float _fogNear;

      // 雾化远平面
      protected float _fogFar;

      // 雾化比率
      protected float _fogRate;

      // 雾化衰减
      protected float _fogAttenuation;

      // 雾化颜色
      protected SFloatColor4 _fogColor = new SFloatColor4();

      // 边线颜色
      protected SFloatColor4 _edgeColor = new SFloatColor4();

      // 边线比率
      protected float _edgeRate;

      // 边线级别
      protected float _edgeLevel;

      // 边线宽度
      protected float _edgeWidth;

      // 平面范围
      protected float _faceRange;

      // 平面限制
      protected float _faceLimit;

      // 平面比率
      protected float _faceRate;

      // 主相机
      protected FDrSceneCamera _camera = new FDrSceneCamera();

      // 主光源
      protected FDrSceneLight _light = new FDrSceneLight();

      //============================================================
      // <T>构造场景区域。</T>
      //============================================================
      public FDrSceneRegion() {
      }

      //============================================================
      // <T>获得颜色。</T>
      //============================================================
      public SFloatColor4 Color {
         get { return _color; }
      }

      //============================================================
      // <T>获得颜色级别。</T>
      //============================================================
      public SFloatColor4 ColorLevel {
         get { return _colorLevel; }
      }

      //============================================================
      // <T>获得雾化颜色。</T>
      //============================================================
      public SFloatColor4 FogColor {
         get { return _fogColor; }
      }

      //============================================================
      // <T>获得或设置雾化近平面。</T>
      //============================================================
      public float FogNear {
         get { return _fogNear; }
         set { _fogNear = value; }
      }

      //============================================================
      // <T>获得或设置雾化远平面。</T>
      //============================================================
      public float FogFar {
         get { return _fogFar; }
         set { _fogFar = value; }
      }

      //============================================================
      // <T>获得或设置雾化比率。</T>
      //============================================================
      public float FogRate {
         get { return _fogRate; }
         set { _fogRate = value; }
      }

      //============================================================
      // <T>获得或设置雾化衰减。</T>
      //============================================================
      public float FogAttenuation {
         get { return _fogAttenuation; }
         set { _fogAttenuation = value; }
      }

      //============================================================
      // <T>获得边线颜色。</T>
      //============================================================
      public SFloatColor4 EdgeColor {
         get { return _edgeColor; }
      }

      //============================================================
      // <T>获得或设置边线比率。</T>
      //============================================================
      public float EdgeRate {
         get { return _edgeRate; }
         set { _edgeRate = value; }
      }

      //============================================================
      // <T>获得或设置边线级别。</T>
      //============================================================
      public float EdgeLevel {
         get { return _edgeLevel; }
         set { _edgeLevel = value; }
      }

      //============================================================
      // <T>获得或设置边线宽度。</T>
      //============================================================
      public float EdgeWidth {
         get { return _edgeWidth; }
         set { _edgeWidth = value; }
      }

      //============================================================
      // <T>获得或设置平面范围。</T>
      //============================================================
      public float FaceRange {
         get { return _faceRange; }
         set { _faceRange = value; }
      }

      //============================================================
      // <T>获得或设置平面限制。</T>
      //============================================================
      public float FaceLimit {
         get { return _faceLimit; }
         set { _faceLimit = value; }
      }

      //============================================================
      // <T>获得或设置平面比率。</T>
      //============================================================
      public float FaceRate {
         get { return _faceRate; }
         set { _faceRate = value; }
      }

      //============================================================
      // <T>获得主相机。</T>
      //============================================================
      public FDrSceneCamera Camera {
         get { return _camera; }
      }

      //============================================================
      // <T>获得主光源。</T>
      //============================================================
      public FDrSceneLight Light {
         get { return _light; }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void LoadOrignConfig(FXmlNode xconfig) {
         foreach(FXmlNode xnode in xconfig.Nodes) {
            if(xnode.IsName("Color")) {
               // 加载颜色
               _color.LoadConfig(xnode);
               _colorLevel.LoadConfig(xnode, "level_r", "level_g", "level_b", "level_power");
            }else if(xnode.IsName("Fog")) {
               // 加载雾化
               _fogColor.LoadConfig(xnode);
               _fogNear = xnode.GetFloat("near", _fogNear);
               _fogFar = xnode.GetFloat("far", _fogFar);
               _fogAttenuation = xnode.GetFloat("attenuation", _fogAttenuation);
            } else if (xnode.IsName("Edge")) {
               // 加载边界
               _edgeColor.LoadConfig(xnode);
               _edgeRate = xnode.GetFloat("rate");
               _edgeLevel = xnode.GetFloat("level");
               _edgeWidth = xnode.GetFloat("width");
            } else if (xnode.IsName("Face")) {
               // 加载平面
               _faceRange = xnode.GetFloat("range");
               _faceLimit = xnode.GetFloat("limit");
               _faceRate = xnode.GetFloat("rate");
            } else if (xnode.IsName("Camera")) {
               // 加载相机
               _camera.LoadOrignConfig(xnode);
            } else if(xnode.IsName("Light")) {
               // 加载光源
               _light.LoadOrignConfig(xnode);
            }
         }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void LoadConfig(FXmlNode xconfig) {
         foreach(FXmlNode xnode in xconfig.Nodes) {
            if(xnode.IsName("Color")) {
               // 加载颜色
               _color.LoadConfigPower(xnode);
            } else if (xnode.IsName("ColorLevel")) {
               // 加载颜色级别
               _colorLevel.LoadConfigPower(xnode);
            } else if(xnode.IsName("Fog")) {
               // 加载雾化
               _fogNear = xnode.GetFloat("near");
               _fogFar = xnode.GetFloat("far");
               _fogRate = xnode.GetFloat("rate");
               _fogAttenuation = xnode.GetFloat("attenuation");
               _fogColor.LoadConfigPower(xnode);
            } else if (xnode.IsName("Edge")) {
               // 加载边界
               _edgeRate = xnode.GetFloat("rate");
               _edgeLevel = xnode.GetFloat("level");
               _edgeWidth = xnode.GetFloat("width");
               _edgeColor.LoadConfigPower(xnode);
            } else if (xnode.IsName("Face")) {
               // 加载平面
               _faceRange = xnode.GetFloat("range");
               _faceLimit = xnode.GetFloat("limit");
               _faceRate = xnode.GetFloat("rate");
            } else if (xnode.IsName("Camera")) {
               // 加载相机信息
               _camera.LoadConfig(xnode);
            } else if(xnode.IsName("Light")) {
               // 加载光源信息
               _light.LoadConfig(xnode);
            }
         }
      }
   
      //============================================================
      // <T>存储配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void SaveConfig(FXmlNode xconfig) {
         // 存储颜色
         FXmlNode xcolor = xconfig.CreateNode("Color");
         _color.SaveConfigPower(xcolor);
         // 存储颜色级别
         FXmlNode xcolorlevel = xconfig.CreateNode("ColorLevel");
         _colorLevel.SaveConfigPower(xcolorlevel);
         // 存储雾化信息
         FXmlNode xfog = xconfig.CreateNode("Fog");
         xfog.Set("near", _fogNear);
         xfog.Set("far", _fogFar);
         xfog.Set("rate", _fogRate);
         xfog.Set("attenuation", _fogAttenuation);
         _fogColor.SaveConfigPower(xfog);
         // 存储边界信息
         FXmlNode xedge = xconfig.CreateNode("Edge");
         xedge.Set("rate", _edgeRate);
         xedge.Set("level", _edgeLevel);
         xedge.Set("width", _edgeWidth);
         _edgeColor.SaveConfigPower(xedge);
         // 存储平面信息
         FXmlNode xface = xconfig.CreateNode("Face");
         xface.Set("range", _faceRange);
         xface.Set("limit", _faceLimit);
         xface.Set("rate", _faceRate);
         // 存储相机信息
         _camera.SaveConfig(xconfig.CreateNode("Camera"));
         // 存储光源信息
         _light.SaveConfig(xconfig.CreateNode("Light"));
      }
   
      //============================================================
      // <T>序列化内部数据到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public void Serialize(IOutput output) {
         // 存储颜色
         _color.Serialize(output);
         // 存储颜色级别
         _colorLevel.Serialize(output);
         // 存储雾化
         output.WriteFloat(_fogNear);
         output.WriteFloat(_fogFar);
         output.WriteFloat(_fogRate);
         output.WriteFloat(_fogAttenuation);
         _fogColor.Serialize(output);
         // 存储边界
         output.WriteFloat(_edgeRate);
         output.WriteFloat(_edgeLevel);
         output.WriteFloat(_edgeWidth);
         _edgeColor.Serialize(output);
         // 存储平面
         output.WriteFloat(_faceRange);
         output.WriteFloat(_faceLimit);
         output.WriteFloat(_faceRate);
         // 存储相机
         _camera.Serialize(output);
         // 存储光源
         _light.Serialize(output);
      }

      //============================================================
      // <T>释放内容。</T>
      //============================================================
      public void Dispose() {
         _camera.Dispose();
         _light.Dispose();
      }
   }
}
