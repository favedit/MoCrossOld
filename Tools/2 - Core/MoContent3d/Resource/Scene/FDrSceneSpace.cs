using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;

namespace MO.Content3d.Resource.Scene
{
   //============================================================
   // <T>构造场景空间。</T>
   //============================================================
   public class FDrSceneSpace : FDrSceneDrawable
   {
      // 场景
      protected FDrScene _scene;

      // 显示集合
      protected FObjects<FDrSceneDisplay> _displays = new FObjects<FDrSceneDisplay>();

      // 节点集合
      protected FObjects<FDrSceneNode> _nodes = new FObjects<FDrSceneNode>();

      //============================================================
      // <T>构造场景空间。</T>
      //============================================================
      public FDrSceneSpace() {
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
      // <T>获得节点集合。</T>
      //============================================================
      public FObjects<FDrSceneNode> Nodes {
         get { return _nodes; }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void LoadOrignConfig(FXmlNode xconfig) {
         _nodes.Clear();
         _displays.Clear();
         // 读取所有信息
         foreach(FXmlNode xnode in xconfig.Nodes) {
            if(xnode.IsName("Node")) {
               FDrSceneNode sceneNode = new FDrSceneNode();
               sceneNode.Parent = this;
               sceneNode.Scene = _scene;
               sceneNode.LoadOrignConfig(xnode);
               _nodes.Push(sceneNode);
            }
         }
         // 获得所有显示对象
         foreach(FDrSceneNode node in _nodes){
            node.FilterDisplays(_displays);
         }
         foreach (FDrSceneDisplay display in _displays) {
            display.UpdateMatrix();
         }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void LoadConfig(FXmlNode xconfig) {
         _nodes.Clear();
         _displays.Clear();
         // 读取所有信息
         foreach (FXmlNode xnode in xconfig.Nodes) {
            // 创建节点对象
            if (xnode.IsName("Node")) {
               FDrSceneNode nodeNode = new FDrSceneNode();
               nodeNode.Parent = this;
               nodeNode.Scene = _scene;
               nodeNode.LoadConfig(xnode);
               _displays.Append(nodeNode.Displays);
               _nodes.Push(nodeNode);
            }
            // 创建显示对象
            if(xnode.IsName("Display")) {
               FDrSceneDisplay display = new FDrSceneDisplay();
               display.Parent = this;
               display.Scene = _scene;
               display.LoadConfig(xnode);
               _displays.Push(display);
            }
         }
         // 获得所有显示对象
         foreach (FDrSceneNode node in _nodes) {
            node.FilterDisplays(_displays);
         }
         foreach (FDrSceneDisplay display in _displays) {
            display.UpdateMatrix();
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
         output.WriteString(_scene.Name + ":space");
         output.WriteString("normal");
         output.WriteInt32(_displays.Count);
         foreach(FDrSceneDisplay display in _displays) {
            display.Serialize(output);
         }
      }

      //============================================================
      // <T>释放内容。</T>
      //============================================================
      public void Dispose() {
      }
   }
}
