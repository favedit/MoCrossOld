using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;

namespace MO.Content3d.Resource.Scene
{
   //============================================================
   // <T>场景渲染。</T>
   //============================================================
   public class FDrSceneRenderable : FObject
   {
      // 名称
      protected string _name;

      //============================================================
      // <T>构造场景渲染。</T>
      //============================================================
      public FDrSceneRenderable() { 
      }

      //============================================================
      // <T>获得或设置名称。</T>
      //============================================================
      public string Name {
         get { return _name; }
         set { _name = value; }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void LoadOrignConfig(FXmlNode xconfig) {
         // 获得属性
         _name = xconfig.Get("name");
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void LoadConfig(FXmlNode xconfig) {
         // 获得属性
         _name = xconfig.Get("name");
      }
      
      //============================================================
      // <T>存储配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void SaveConfig(FXmlNode xconfig) {
         // 存储属性
         xconfig.Set("name", _name);
      }

      //============================================================
      // <T>序列化内部数据到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public void Serialize(IOutput output) {
         output.WriteString(_name);
      }
   }
}
