using MO.Common.Content;
using MO.Common.Geom;
using MO.Common.IO;
using MO.Common.Lang;

namespace MO.Content3d.Resource.Scene
{
   //============================================================
   // <T>场景相机信息。</T>
   //============================================================
   public class FDrSceneCamera : FObject
   {
      // 类型名称
      protected string _typeName;

      // 中心前距离
      protected float _centerFront;

      // 中心后距离
      protected float _centerBack;

      // 位置
      protected SFloatPoint3 _position = new SFloatPoint3();

      // 方向
      protected SFloatVector3 _direction = new SFloatVector3();

      // 近焦平面
      protected float _focalNear;

      // 远焦平面
      protected float _focalFar;

      // 视角
      protected FDrSceneViewport _viewport = new FDrSceneViewport();

      //============================================================
      // <T>构造场景相机信息。</T>
      //============================================================
      public FDrSceneCamera (){
      }

      //============================================================
      // <T>获得或设置类型名称。</T>
      //============================================================
      public string TypeName {
         get { return _typeName; }
         set { _typeName = value; }
      }

      //============================================================
      // <T>获得或设置中心前距离。</T>
      //============================================================
      public float CenterFront {
         get { return _centerFront; }
         set { _centerFront = value; }
      }

      //============================================================
      // <T>获得或设置中心后距离。</T>
      //============================================================
      public float CenterBack {
         get { return _centerBack; }
         set { _centerBack = value; }
      }

      //============================================================
      // <T>获得位置。</T>
      //============================================================
      public SFloatPoint3 Position {
         get { return _position; }
      }

      //============================================================
      // <T>获得方向。</T>
      //============================================================
      public SFloatVector3 Direction {
         get { return _direction; }
      }

      //============================================================
      // <T>获得或设置近焦平面。</T>
      //============================================================
      public float FocalNear {
         get { return _focalNear; }
         set { _focalNear = value; }
      }

      //============================================================
      // <T>获得或设置远焦平面。</T>
      //============================================================
      public float FocalFar {
         get { return _focalFar; }
         set { _focalFar = value; }
      }

      //============================================================
      // <T>获得视角。</T>
      //============================================================
      public FDrSceneViewport Viewport {
         get { return _viewport; }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void LoadOrignConfig(FXmlNode xconfig) {
         // 读取属性
         _typeName = xconfig.Get("type");
         if(xconfig.Contains("center_front")) {
            _centerFront = xconfig.GetFloat("center_front");
         }
         if(xconfig.Contains("center_back")) {
            _centerBack = xconfig.GetFloat("center_back");
         }
         // 读取节点集合
         foreach (FXmlNode xnode in xconfig.Nodes){
            // 读取位置
            if(xnode.IsName("Position")) {
               _position.LoadConfig(xnode);
            }
            // 读取方向
            if(xnode.IsName("Direction")) {
               _direction.LoadConfig(xnode);
            }
            // 读取焦平面
            if(xnode.IsName("Focal")) {
               _focalNear = xnode.GetFloat("near", _focalNear);
               _focalFar = xnode.GetFloat("far", _focalFar);
            }
            // 读取视角
            if(xnode.IsName("Viewport")) {
               _viewport.LoadOrignConfig(xnode);
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
         // 读取节点集合
         foreach(FXmlNode xnode in xconfig.Nodes) {
            // 读取中心
            if(xnode.IsName("Center")) {
               _centerFront = xnode.GetFloat("front");
               _centerBack = xnode.GetFloat("back");
            }
            // 读取位置
            if(xnode.IsName("Position")) {
               _position.LoadConfig(xnode);
            }
            // 读取方向
            if(xnode.IsName("Direction")) {
               _direction.LoadConfig(xnode);
            }
            // 读取目标
            if(xnode.IsName("Target")) {
               SFloatPoint3 target = new SFloatPoint3();
               target.LoadConfig(xnode);
               _direction.X = target.X - _position.X;
               _direction.Y = target.Y - _position.Y;
               _direction.Z = target.Z - _position.Z;
               _direction.Normalize();
            }
            // 读取焦平面
            if(xnode.IsName("Focal")) {
               _focalNear = xnode.GetFloat("near");
               _focalFar = xnode.GetFloat("far");
            }
            // 读取视角
            if(xnode.IsName("Viewport")) {
               _viewport.LoadConfig(xnode);
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
         // 存储中心
         FXmlNode xcenter = xconfig.CreateNode("Center");
         xcenter.Set("front", _centerFront);
         xcenter.Set("back", _centerBack);
         // 存储位置
         _position.SaveConfig(xconfig.CreateNode("Position"));
         // 存储方向
         _direction.SaveConfig(xconfig.CreateNode("Direction"));
         // 存储焦平面
         FXmlNode xfocal = xconfig.CreateNode("Focal");
         xfocal.Set("near", _focalNear);
         xfocal.Set("far", _focalFar);
         // 存储视角
         _viewport.SaveConfig(xconfig.CreateNode("Viewport"));
      }

      //============================================================
      // <T>序列化内部数据到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public void Serialize(IOutput output) {
         // 输出属性
         output.WriteString(_typeName);
         // 输出中心
         output.WriteFloat(_centerFront);
         output.WriteFloat(_centerBack);
         // 输出位置
         _position.Serialize(output);
         // 输出方向
         _direction.Serialize(output);
         // 输出焦平面
         output.WriteFloat(_focalNear);
         output.WriteFloat(_focalFar);
         // 输出视角
         _viewport.Serialize(output);
      }

      //============================================================
      // <T>释放内容。</T>
      //============================================================
      public void Dispose() {
         _viewport.Dispose();
      }
   }
}
