using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Core;
using MO.Content3d.Resource.Template;
using MO.Content3d.Common;

namespace MO.Content3d.Resource.Scene
{
   //============================================================
   // <T>场景显示。</T>
   //============================================================
   public class FDrSceneDisplay : FDrSceneDrawable
   {
      // 场景
      protected FDrScene _scene;

      // 来源
      protected string _source;

      // 模板
      protected FDrTemplate _template;

      // 动画集合
      protected FObjects<FDrSceneMovie> _movies = new FObjects<FDrSceneMovie>();

      // 材质集合
      protected FObjects<FDrSceneMaterial> _materials = new FObjects<FDrSceneMaterial>();

      // 材质集合
      protected FObjects<FDrSceneRenderable> _renderables = new FObjects<FDrSceneRenderable>();
      
      //============================================================
      // <T>构造场景节点。</T>
      //============================================================
      public FDrSceneDisplay() {
      }

      //============================================================
      // <T>获得或设置场景。</T>
      //============================================================
      public FDrScene Scene {
         get { return _scene; }
         set { _scene = value; }
      }

      //============================================================
      // <T>获得或设置来源。</T>
      //============================================================
      public string Source {
         get { return _source; }
         set { _source = value; }
      }

      //============================================================
      // <T>获得或设置来源。</T>
      //============================================================
      public FDrTemplate Template {
         get { return _template; }
      }

      //============================================================
      // <T>获得动画集合。</T>
      //============================================================
      public FObjects<FDrSceneMovie> Movies {
         get { return _movies; }
      }

      //============================================================
      // <T>获得材质集合。</T>
      //============================================================
      public FObjects<FDrSceneMaterial> Materials {
         get { return _materials; }
      }

      //============================================================
      // <T>获得渲染集合。</T>
      //============================================================
      public FObjects<FDrSceneRenderable> Renderables {
         get { return _renderables; }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void LoadOrignConfig(FXmlNode xconfig) {
         // 获得属性
         _source = xconfig.Get("src");
         _template = RContent3dManager.TemplateConsole.Find(_source);
         if (null == _template) {
            RMoCore.TrackConsole.Write(this, "LoadOrignConfig", "Template is not exists. (scene={0}, template={1})", _scene.Name, _source);
         }
         // 获得节点集合
         foreach(FXmlNode xnode in xconfig.Nodes) {
            // 加载矩阵
            if(xnode.IsName("Matrix")) {
               _modelMatrix.LoadSingleConfig(xnode);
            }
            // 加载动画集合
            if (xnode.IsName("Movies")) {
               foreach (FXmlNode xmovie in xnode.Nodes) {
                  if (xmovie.IsName("Movie")) {
                     FDrSceneMovie movie = new FDrSceneMovie();
                     movie.LoadOrignConfig(xmovie);
                     _movies.Push(movie);
                  }
               }
            }
            // 加载材质集合
            if(xnode.IsName("Materials")) {
               foreach(FXmlNode xmaterial in xnode.Nodes) {
                  if(xmaterial.IsName("Material")) {
                     FDrSceneMaterial material = new FDrSceneMaterial();
                     material.Scene = _scene;
                     material.LoadOrignConfig(xmaterial);
                     _materials.Push(material);
                  }
               }
            }
            // 加载渲染集合
            if(xnode.IsName("Renderables")) {
               foreach(FXmlNode xrenderable in xnode.Nodes) {
                  if(xrenderable.IsName("Renderables")) {
                     FDrSceneRenderable renderable = new FDrSceneRenderable();
                     renderable.LoadOrignConfig(xrenderable);
                     _renderables.Push(renderable);
                  }
               }
            }
         }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void LoadConfig(FXmlNode xconfig) {
         // 获得属性
         _source = xconfig.Get("source");
         _template = RContent3dManager.TemplateConsole.Find(_source);
         if(null == _template) {
            RMoCore.TrackConsole.Write(this, "LoadConfig", "Template is not exists. (scene={0}, template={1})", _scene.Name, _source);
         }
         // 获得节点集合
         foreach(FXmlNode xnode in xconfig.Nodes) {
            // 加载矩阵
            if(xnode.IsName("Matrix")) {
               _modelMatrix.LoadSingleConfig(xnode);
            }
            // 加载动画集合
            if(xnode.IsName("Movies")) {
               foreach(FXmlNode xmovie in xnode.Nodes) {
                  if (xmovie.IsName("Movie")) {
                     FDrSceneMovie movie = new FDrSceneMovie();
                     movie.LoadConfig(xmovie);
                     _movies.Push(movie);
                  }
               }
            }
            // 加载材质集合
            if (xnode.IsName("Materials")) {
               foreach (FXmlNode xmaterial in xnode.Nodes) {
                  if (xmaterial.IsName("Material")) {
                     FDrSceneMaterial material = new FDrSceneMaterial();
                     material.Scene = _scene;
                     material.LoadConfig(xmaterial);
                     _materials.Push(material);
                  }
               }
            }
            // 加载渲染集合
            if(xnode.IsName("Renderables")) {
               foreach(FXmlNode xrenderable in xnode.Nodes) {
                  if(xrenderable.IsName("Renderables")) {
                     FDrSceneRenderable renderable = new FDrSceneRenderable();
                     renderable.LoadConfig(xrenderable);
                     _renderables.Push(renderable);
                  }
               }
            }
         }
      }

      //============================================================
      // <T>存储配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void SaveConfig(FXmlNode xconfig) {
         // 材质属性
         xconfig.Set("source", _source);
         _matrix.Parse();
         if (_matrix.IsChanged) {
            _matrix.SaveSingleConfig(xconfig.CreateNode("Matrix"));
         }
         // 存储动画集合
         if (!_movies.IsEmpty()) {
            FXmlNode xmovies = xconfig.CreateNode("Movies");
            foreach(FDrSceneMovie movie in _movies) {
               movie.SaveConfig(xmovies.CreateNode("Movie"));
            }
         }
         // 存储材质集合
         if (!_materials.IsEmpty()) {
            FXmlNode xmaterials = xconfig.CreateNode("Materials");
            foreach (FDrSceneMaterial material in _materials) {
               material.SaveConfig(xmaterials.CreateNode("Material"));
            }
         }
         // 存储渲染集合
         if (!_renderables.IsEmpty()) {
            FXmlNode xrenderables = xconfig.CreateNode("Renderables");
            foreach(FDrSceneRenderable renderable in _renderables) {
               renderable.SaveConfig(xrenderables.CreateNode("Renderable"));
            }
         }
      }

      //============================================================
      // <T>序列化内部数据到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public void Serialize(IOutput output) {
         // 存储属性
         output.WriteString(RDrUtil.FormatPathToCode(_source));
         output.WriteInt8((sbyte)_template.OptionMergeVertex);
         output.WriteInt8((sbyte)_template.OptionMergeMaterial);
         // 存储矩阵
         //_matrix.SerializeData(output);
         _matrix.Serialize(output);
         // 存储动画集合
         output.WriteInt32(_movies.Count);
         foreach (FDrSceneMovie movie in _movies) {
            movie.Serialize(output);
         }
         // 存储材质集合
         output.WriteInt32(_materials.Count);
         foreach(FDrSceneMaterial material in _materials) {
            material.Serialize(output);
         }
         // 存储渲染集合
         output.WriteInt32(_renderables.Count);
         foreach(FDrSceneRenderable renderable in _renderables) {
            renderable.Serialize(output);
         }
      }
   }
}
