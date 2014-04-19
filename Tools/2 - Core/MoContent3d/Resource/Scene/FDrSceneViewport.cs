using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;

namespace MO.Content3d.Resource.Scene
{
   //============================================================
   // <T>场景视角信息。</T>
   //============================================================
   public class FDrSceneViewport : FObject
   {
      // 夹角
      protected float _angle;

      // 近平面
      protected float _near;

      // 远平面
      protected float _far;

      //============================================================
      // <T>获得或设置夹角。</T>
      //============================================================
      public float Angle {
         get { return _angle; }
         set { _angle = value; }
      }

      //============================================================
      // <T>获得或设置近平面。</T>
      //============================================================
      public float Near {
         get { return _near; }
         set { _near = value; }
      }

      //============================================================
      // <T>获得或设置远平面。</T>
      //============================================================
      public float Far {
         get { return _far; }
         set { _far = value; }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void LoadOrignConfig(FXmlNode xconfig) {
         // 读取属性
         _angle = xconfig.GetFloat("angle");
         _near = xconfig.GetFloat("znear");
         _far = xconfig.GetFloat("zfar");
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void LoadConfig(FXmlNode xconfig) {
         // 读取属性
         _angle = xconfig.GetFloat("angle");
         _near = xconfig.GetFloat("near");
         _far = xconfig.GetFloat("far");
      }

      //============================================================
      // <T>存储配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void SaveConfig(FXmlNode xconfig) {
         // 读取属性
         xconfig.Set("angle", _angle);
         xconfig.Set("near", _near);
         xconfig.Set("far", _far);
      }

      //============================================================
      // <T>序列化内部数据到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public void Serialize(IOutput output) {
         output.WriteFloat(_angle);
         output.WriteFloat(_near);
         output.WriteFloat(_far);
      }

      //============================================================
      // <T>释放内容。</T>
      //============================================================
      public void Dispose() {
      }
   }
}
