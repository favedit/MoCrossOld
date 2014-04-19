using MO.Common.Content;
using MO.Common.Geom;
using MO.Common.IO;
using MO.Common.Lang;

namespace MO.Content3d.Resource.Scene
{
   //============================================================
   // <T>场景渲染。</T>
   //============================================================
   public class FDrSceneMovie : FObject
   {
      // 类型名称
      protected string _typeName;

      // 间隔时刻
      protected int _interval;

      // 旋转角度
      protected SFloatVector3 _rotation = new SFloatVector3();

      //============================================================
      // <T>构造场景渲染。</T>
      //============================================================
      public FDrSceneMovie() { 
      }

      //============================================================
      // <T>获得或设置类型名称。</T>
      //============================================================
      public string TypeName {
         get { return _typeName; }
         set { _typeName = value; }
      }

      //============================================================
      // <T>获得或设置间隔时刻。</T>
      //============================================================
      public int Interval {
         get { return _interval; }
         set { _interval = value; }
      }

      //============================================================
      // <T>获得旋转角度。</T>
      //============================================================
      public SFloatVector3 Rotation {
         get { return _rotation; }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void LoadOrignConfig(FXmlNode xconfig) {
         // 获得属性
         _typeName = xconfig.Get("type");
         _interval = xconfig.GetInteger("tick");
         _rotation.Parse(xconfig.Get("rotation"));
         _rotation.Mul(40);
      }
      
      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void LoadConfig(FXmlNode xconfig) {
         _typeName = xconfig.Get("type_name");
         _interval = xconfig.GetInteger("interval");
         _rotation.Parse(xconfig.Get("rotation"));
      }

      //============================================================
      // <T>存储配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void SaveConfig(FXmlNode xconfig) {
         xconfig.Set("type_name", _typeName);
         xconfig.Set("interval", _interval);
         xconfig.Set("rotation", _rotation.ToString());
      }

      //============================================================
      // <T>序列化内部数据到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public void Serialize(IOutput output) {
         output.WriteString(_typeName);
         output.WriteInt32(_interval);
         _rotation.Serialize(output);
      }

      //============================================================
      // <T>释放内容。</T>
      //============================================================
      public void Dispose() {
      }
   }
}

