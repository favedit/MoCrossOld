using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;

namespace MO.Content3d.Resource.Scene
{
   //============================================================
   // <T>场景渲染。</T>
   //============================================================
   public class FDrSceneTechnique : FObject
   {
      // 名称
      protected string _name;

      // 场景渲染过程集合
      protected FObjects<FDrScenePass> _passes = new FObjects<FDrScenePass>();

      //============================================================
      // <T>构造场景渲染。</T>
      //============================================================
      public FDrSceneTechnique() {
      }

      //============================================================
      // <T>获得或设置名称。</T>
      //============================================================
      public string Name {
         get { return _name; }
         set { _name = value; }
      }

      //============================================================
      // <T>获得渲染过程集合。</T>
      //============================================================
      public FObjects<FDrScenePass> Passes {
         get { return _passes; }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void LoadOrignConfig(FXmlNode xconfig) {
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void LoadConfig(FXmlNode xconfig) {
         _name = xconfig.Get("name");
         // 读取过程集合
         foreach (FXmlNode xnode in xconfig.Nodes) {
            if (xnode.IsName("Pass")) {
               FDrScenePass pass = new FDrScenePass();
               pass.LoadConfig(xnode);
               _passes.Push(pass);
            }
         }
      }

      //============================================================
      // <T>存储配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void SaveConfig(FXmlNode xconfig) {
         xconfig.Set("name", _name);
         // 读取过程集合
         if (!_passes.IsEmpty()) {
            foreach (FDrScenePass pass in _passes) {
               pass.SaveConfig(xconfig.CreateNode("Pass"));
            }
         }
      }

      //============================================================
      // <T>序列化内部数据到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public void Serialize(IOutput output) {
         output.WriteString(_name);
         // 读取过程集合
         output.WriteInt32(_passes.Count);
         foreach (FDrScenePass pass in _passes) {
            pass.Serialize(output);
         }
      }

      //============================================================
      // <T>释放内容。</T>
      //============================================================
      public void Dispose() {
      }
   }
}
