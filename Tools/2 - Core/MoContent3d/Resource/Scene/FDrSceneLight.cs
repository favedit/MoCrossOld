using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content3d.Common;

namespace MO.Content3d.Resource.Scene
{
   //============================================================
   // <T>场景光源信息。</T>
   //============================================================
   public class FDrSceneLight : FObject
   {
      // 类型
      protected string _typeName;

      // 配置跟踪
      protected int _optionTrack = EDrFlag.Inherit;

      // 影子1
      protected FDrSceneLightShadow _shadow1 = new FDrSceneLightShadow();

      // 影子2
      protected FDrSceneLightShadow _shadow2 = new FDrSceneLightShadow();

      // 影子3
      protected FDrSceneLightShadow _shadow3 = new FDrSceneLightShadow();

      // 阴影环境最小
      protected float _shadowAmbientMin;

      // 阴影环境最大
      protected float _shadowAmbientMax;

      // 阴影环境厚度
      protected float _shadowAmbientThick;

      // 阴影环境范围
      protected float _shadowAmbientRange;

      // 阴影融合1基础
      protected float _shadowMerge1Base;

      // 阴影融合1比率
      protected float _shadowMerge1Rate;

      // 阴影融合2基础
      protected float _shadowMerge2Base;

      // 阴影融合2基础
      protected float _shadowMerge2Rate;

      // 场景材质
      protected FDrSceneMaterial _material = new FDrSceneMaterial();

      // 场景相机
      protected FDrSceneCamera _camera = new FDrSceneCamera();

      //============================================================
      // <T>构造场景光源信息。</T>
      //============================================================
      public FDrSceneLight() {
      }

      //============================================================
      // <T>获得或设置类型名称。</T>
      //============================================================
      public string TypeName {
         get { return _typeName; }
         set { _typeName = value; }
      }

      //============================================================
      // <T>获得或设置配置跟踪。</T>
      //============================================================
      public int OptionTrack {
         get { return _optionTrack; }
         set { _optionTrack = value; }
      }

      //============================================================
      // <T>获得影子1。</T>
      //============================================================
      public FDrSceneLightShadow Shadow1 {
         get { return _shadow1; }
      }

      //============================================================
      // <T>获得影子2。</T>
      //============================================================
      public FDrSceneLightShadow Shadow2 {
         get { return _shadow2; }
      }

      //============================================================
      // <T>获得影子3。</T>
      //============================================================
      public FDrSceneLightShadow Shadow3 {
         get { return _shadow3; }
      }

      //============================================================
      // <T>获得或设置阴影环境最小。</T>
      //============================================================
      public float ShadowAmbientMin {
         get { return _shadowAmbientMin; }
         set { _shadowAmbientMin = value; }
      }

      //============================================================
      // <T>获得或设置阴影环境最大。</T>
      //============================================================
      public float ShadowAmbientMax {
         get { return _shadowAmbientMax; }
         set { _shadowAmbientMax = value; }
      }

      //============================================================
      // <T>获得或设置阴影环境厚度。</T>
      //============================================================
      public float ShadowAmbientThick {
         get { return _shadowAmbientThick; }
         set { _shadowAmbientThick = value; }
      }

      //============================================================
      // <T>获得或设置阴影环境范围。</T>
      //============================================================
      public float ShadowAmbientRange {
         get { return _shadowAmbientRange; }
         set { _shadowAmbientRange = value; }
      }

      //============================================================
      // <T>获得或设置阴影融合1基础。</T>
      //============================================================
      public float ShadowMerge1Base {
         get { return _shadowMerge1Base; }
         set { _shadowMerge1Base = value; }
      }

      //============================================================
      // <T>获得或设置阴影融合1比率。</T>
      //============================================================
      public float ShadowMerge1Rate {
         get { return _shadowMerge1Rate; }
         set { _shadowMerge1Rate = value; }
      }

      //============================================================
      // <T>获得或设置阴影融合2基础。</T>
      //============================================================
      public float ShadowMerge2Base {
         get { return _shadowMerge2Base; }
         set { _shadowMerge2Base = value; }
      }

      //============================================================
      // <T>获得或设置阴影融合2基础。</T>
      //============================================================
      public float ShadowMerge2Rate {
         get { return _shadowMerge2Rate; }
         set { _shadowMerge2Rate = value; }
      }

      //============================================================
      // <T>获得材质。</T>
      //============================================================
      public FDrSceneMaterial Material {
         get { return _material; }
      }

      //============================================================
      // <T>获得材质。</T>
      //============================================================
      public FDrSceneCamera Camera {
         get { return _camera; }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void LoadOrignConfig(FXmlNode xconfig) {
         // 读取属性
         _typeName = xconfig.Get("type");
         // 读取节点集合
         foreach(FXmlNode xnode in xconfig.Nodes) {
            // 读取阴影
            if (xnode.IsName("Shadow")) {
               if (xnode.Contains("mode")) {
                  string mode = xnode.Get("mode");
                  if (mode == "auto") {
                     _optionTrack = EDrFlag.Yes;
                  }
               }
               _shadow1.LoadOrignConfig(xnode, "1");
               _shadow2.LoadOrignConfig(xnode, "2");
               _shadow3.LoadOrignConfig(xnode, "3");
            }
            // 读取材质
            if(xnode.IsName("Material")) {
               _material.LoadOrignConfig(xnode);
            }
            // 读取相机
            if(xnode.IsName("Camera")) {
               _camera.LoadOrignConfig(xnode);
            }
         }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void LoadConfig(FXmlNode xconfig) {
         // 读取属性
         _typeName = xconfig.Get("type_name");
         // 读取配置
         if (xconfig.Contains("option_track")) {
            _optionTrack = xconfig.GetInteger("option_track");
         }
         // 读取节点集合
         foreach(FXmlNode xnode in xconfig.Nodes) {
            // 读取阴影
            if(xnode.IsName("Shadow")) {
               _shadow1.LoadConfig(xnode, "1");
               _shadow2.LoadConfig(xnode, "2");
               _shadow3.LoadConfig(xnode, "3");
               _shadowAmbientMin = xnode.GetFloat("ambient_min", _shadowAmbientMin);
               _shadowAmbientMax = xnode.GetFloat("ambient_max", _shadowAmbientMax);
               _shadowAmbientThick = xnode.GetFloat("ambient_thick", _shadowAmbientThick);
               _shadowAmbientRange = xnode.GetFloat("ambient_range", _shadowAmbientRange);
               _shadowMerge1Base = xnode.GetFloat("merge1_base", _shadowMerge1Base);
               _shadowMerge1Rate = xnode.GetFloat("merge1_rate", _shadowMerge1Rate);
               _shadowMerge2Base = xnode.GetFloat("merge2_base", _shadowMerge2Base);
               _shadowMerge2Rate = xnode.GetFloat("merge2_rate", _shadowMerge2Rate);
            }
            // 读取材质
            if(xnode.IsName("Material")) {
               _material.LoadConfig(xnode);
            }
            // 读取相机
            if(xnode.IsName("Camera")) {
               _camera.LoadConfig(xnode);
            }
         }
      }

      //============================================================
      // <T>存储配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void SaveConfig(FXmlNode xconfig) {
         // 存储属性
         xconfig.Set("type_name", _typeName);
         // 存储配置
         xconfig.Set("option_track", _optionTrack);
         // 存储阴影
         FXmlNode xshadow = xconfig.CreateNode("Shadow");
         _shadow1.SaveConfig(xshadow, "1");
         _shadow2.SaveConfig(xshadow, "2");
         _shadow3.SaveConfig(xshadow, "3");
         xshadow.Set("ambient_min", _shadowAmbientMin);
         xshadow.Set("ambient_max", _shadowAmbientMax);
         xshadow.Set("ambient_thick", _shadowAmbientThick);
         xshadow.Set("ambient_range", _shadowAmbientRange);
         xshadow.Set("merge1_base", _shadowMerge1Base);
         xshadow.Set("merge1_rate", _shadowMerge1Rate);
         xshadow.Set("merge2_base", _shadowMerge2Base);
         xshadow.Set("merge2_rate", _shadowMerge2Rate);
         // 存储材质
         _material.SaveConfig(xconfig.CreateNode("Material"));
         // 存储相机
         _camera.SaveConfig(xconfig.CreateNode("Camera"));
      }

      //============================================================
      // <T>序列化内部数据到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public void Serialize(IOutput output) {
         // 输出属性
         output.WriteString(_typeName);
         // 输出配置
         output.WriteInt32(_optionTrack);
         // 输出阴影
         _shadow1.Serialize(output);
         _shadow2.Serialize(output);
         _shadow3.Serialize(output);
         output.WriteFloat(_shadowAmbientMin);
         output.WriteFloat(_shadowAmbientMax);
         output.WriteFloat(_shadowAmbientThick);
         output.WriteFloat(_shadowAmbientRange);
         output.WriteFloat(_shadowMerge1Base);
         output.WriteFloat(_shadowMerge1Rate);
         output.WriteFloat(_shadowMerge2Base);
         output.WriteFloat(_shadowMerge2Rate);
         // 输出材质
         _material.Serialize(output);
         // 输出相机
         _camera.Serialize(output);
      }

      //============================================================
      // <T>重置内容。</T>
      //============================================================
      public void Reset() {
         _material.Reset();
      }

      //============================================================
      // <T>释放内容。</T>
      //============================================================
      public void Dispose() {
         _material.Dispose();
         _camera.Dispose();
      }
   }
}
