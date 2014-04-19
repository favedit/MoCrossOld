using MO.Common.Content;
using MO.Common.Lang;
using MO.DX.Core.Common;
using MO.DX.Core.Program.Pass;

namespace MO.DX.Core.Program.Technique
{
   //============================================================
   public class FDxTechnique : FDxContextObject
   {
      public bool _initialized;

      public int _total;

      public string _name;

      public string _space;

      public bool _changed;

      public FDxProgramBinder _binder;

      public FDxTechniqueContext _context = new FDxTechniqueContext();

      public FObjects<FDxPass> _passes = new FObjects<FDxPass>();

      //============================================================
      public FDxTechnique() {
      }

      //============================================================
      // <T>设置技术信息。</T>
      //============================================================
      public virtual void Setup() {
         _initialized = true;
         int count = _passes.Count;
         for (int n = 0; n < count; n++) {
            // 设置渲染器
            FDxPass pass = _passes[n];
            pass.Device = _device;
            pass.Setup();
         }
      }

      //============================================================
      // <T>更新技术信息。</T>
      //============================================================
      public void RegisterPass(FDxPass pass) {
         _passes.Push(pass);
      }

      //============================================================
      public virtual void delayUpdate(FDxRegion region) {
      }

      //============================================================
      // <T>更新技术信息。</T>
      //============================================================
      public virtual void update(FDxRegion region) {
      }

      //============================================================
      // <T>绘制区域内所有节点集合。</T>
      //
      // @param region 区域
      // @param nodes 节点集合
      //============================================================
      public virtual void Draw(FDxRegion region) {
         _total++;
         int count = _passes.Count;
         for(int n = 0; n < count; n++) {
            // 获得渲染过程
            FDxPass pass = _passes[n];
            // 渲染当前过程
            pass.Draw(region);
         }
      }

      //============================================================
      // <T>显示画面。</T>
      //============================================================
      public virtual void Present() {
         _device.Present();
         _changed = false;
      }

      //============================================================
      public virtual void LoadConfig(FXmlNode xconfig) {
         //var c:int = px.nodeCount;
         //for(var n:int = 0; n < c; n++){
         //   var xp:FXmlNode = px.node(n);
         //   if(xp.isName("Pass")){
         //   }
         //}
      }

      //============================================================
      public virtual void SaveConfig(FXmlNode xconfig) {
         //var c:int = px.nodeCount;
         //for(var n:int = 0; n < c; n++){
         //   var xp:FXmlNode = px.node(n);
         //   if(xp.isName("Pass")){
         //   }
         //}
      }

      //============================================================
      // <T>获得信息内容。</T>
      //
      // @return 内容
      //============================================================
      public string toString() {
         return _name;
      }
   }
}
