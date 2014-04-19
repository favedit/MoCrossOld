using MO.Common.Content;
using MO.Common.Geom;
using MO.Common.IO;
using MO.Common.Lang;

namespace MO.Content3d.Resource.Scene
{
   //============================================================
   // <T>场景渲染。</T>
   //============================================================
   public class FDrScenePass : FObject
   {
      // 名称
      protected string _name;

      // 渲染目标尺寸
      protected SIntSize _renderTargetSize = new SIntSize(1024, 1024);

      //============================================================
      // <T>构造场景渲染。</T>
      //============================================================
      public FDrScenePass() { 
      }

      //============================================================
      // <T>获得或设置名称。</T>
      //============================================================
      public string Name {
         get { return _name; }
         set { _name = value; }
      }

      //============================================================
      // <T>获得或设置渲染目标尺寸。</T>
      //============================================================
      public SIntSize RenderTargetSize {
         get { return _renderTargetSize; }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void LoadConfig(FXmlNode xconfig) {
         _name = xconfig.Get("name");
         if (xconfig.Contains("render_target_size")) {
            _renderTargetSize.Parse(xconfig.Get("render_target_size"));
         }
      }

      //============================================================
      // <T>存储配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void SaveConfig(FXmlNode xconfig) {
         xconfig.Set("name", _name);
         xconfig.Set("render_target_size", _renderTargetSize.ToString());
      }

      //============================================================
      // <T>序列化内部数据到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public void Serialize(IOutput output) {
         output.WriteString(_name);
         output.WriteInt32(_renderTargetSize.Width);
         output.WriteInt32(_renderTargetSize.Height);
      }

      //============================================================
      // <T>释放内容。</T>
      //============================================================
      public void Dispose() {
      }
   }
}

