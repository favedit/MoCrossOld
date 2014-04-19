using MO.Common.Content;
using MO.Common.Geom;
using MO.Common.IO;
using MO.Common.Lang;

namespace MO.Content3d.Resource.Scene
{
   //============================================================
   // <T>场景节点。</T>
   //============================================================
   public class FDrSceneNode : FDrSceneDrawable
   {
      // 场景
      protected FDrScene _scene;

      // 显示集合
      protected FObjects<FDrSceneDisplay> _displays = new FObjects<FDrSceneDisplay>();

      //============================================================
      // <T>构造场景节点。</T>
      //============================================================
      public FDrSceneNode() {
      }

      //============================================================
      // <T>获得或设置场景。</T>
      //============================================================
      public FDrScene Scene {
         get { return _scene; }
         set { _scene = value; }
      }

      //============================================================
      // <T>获得显示集合。</T>
      //============================================================
      public FObjects<FDrSceneDisplay> Displays {
         get { return _displays; }
      }

      //============================================================
      // <T>过滤所有显示对象集合。</T>
      //============================================================
      public void FilterDisplays(FObjects<FDrSceneDisplay> displays){
         displays.Append(_displays);
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void LoadOrignConfig(FXmlNode xconfig) {
         foreach(FXmlNode xnode in xconfig.Nodes) {
            if(xnode.IsName("Matrix")) {
               // 读取矩阵
               _modelMatrix.LoadSingleConfig(xnode);
            } else if(xnode.IsName("Display")) {
               // 读取显示信息
               FDrSceneDisplay display = new FDrSceneDisplay();
               display.Parent = this;
               display.Scene = _scene;
               display.LoadOrignConfig(xnode);
               _displays.Push(display);
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
            if(xnode.IsName("Display")) {
               FDrSceneDisplay display = new FDrSceneDisplay();
               display.Parent = this;
               display.LoadConfig(xnode);
               _displays.Push(display);
            }
         }
      }

      //============================================================
      // <T>存储配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void SaveConfig(FXmlNode xconfig) {
         foreach(FDrSceneDisplay display in _displays) {
            display.SaveConfig(xconfig.CreateNode("Display"));
         }
      }

      //============================================================
      // <T>序列化内部数据到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public void Serialize(IOutput output) {
         output.WriteInt32(_displays.Count);
         foreach(FDrSceneDisplay display in _displays) {
            display.Serialize(output);
         }
      }
   }
}
