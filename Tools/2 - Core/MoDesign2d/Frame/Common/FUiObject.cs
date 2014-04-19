using MO.Common.Lang;
using MO.Content2d.Frame.Common;
using System.ComponentModel;
using System.Windows.Forms;

namespace MO.Design2d.Frame.Common
{
   //============================================================
   // <T>界面对象。</T>
   //
   // @history MAOCY 120707
   //============================================================
   public class FUiObject : FObject
   {
      // 控制台
      protected FUiFrameConsole _console;

      // 父对象
      protected FUiObject _parent;

      // 环境
      protected FUiContext _context;

      // 资源
      protected FRcObject _resource;

      // 树目录节点
      protected TreeNode _linkerNode;

      //============================================================
      // <T>构造界面对象。</T>
      //
      // @param console 控制台
      //============================================================
      public FUiObject(FUiFrameConsole console = null) {
         _console = console;
      }

      //============================================================
      // <T>获得或设置控制台。</T>
      //============================================================
      [Browsable(false)]
      public FUiFrameConsole Console {
         get { return _console; }
         set { _console = value; }
      }

      //============================================================
      // <T>获得或设置父对象。</T>
      //============================================================
      [Browsable(false)]
      public FUiObject Parent {
         get { return _parent; }
         set { _parent = value; }
      }

      //============================================================
      // <T>获得或设置环境。</T>
      //============================================================
      [Browsable(false)]
      public FUiContext Context {
         get { return _context; }
         set { _context = value; }
      }

      //============================================================
      // <T>获得或设置控制台。</T>
      //============================================================
      [Browsable(false)]
      public FRcObject Resource {
         get { return _resource; }
         set { _resource = value; }
      }

      //============================================================
      // <T>获得或设置附加数据。</T>
      //============================================================
      [Browsable(false)]
      public TreeNode LinkerNode {
         get { return _linkerNode; }
         set { _linkerNode = value; }
      }

      //============================================================
      // <T>获得格式化内容。</T>
      //
      // @return 格式化内容
      //============================================================
      public virtual string Format() {
         return (_resource != null) ? _resource.Format() : string.Empty;
      }

      //============================================================
      // <T>配置处理。</T>
      //
      // @param args 参数
      //============================================================
      public virtual void OnSetup(SUiSetupArgs args) {
         _context = args.context;
      }

      //============================================================
      // <T>配置处理。</T>
      //
      // @param args 参数
      //============================================================
      public virtual void Setup(SUiSetupArgs args) {
         OnSetup(args);
      }

      //============================================================
      // <T>命令开始处理。</T>
      //
      // @param action 参数
      //============================================================
      public virtual void OnActionBefore(SUiAction action) {
      }

      //============================================================
      // <T>命令结束处理。</T>
      //
      // @param action 参数
      //============================================================
      public virtual void OnActionAfter(SUiAction action) {
      }

      //============================================================
      // <T>命令处理。</T>
      //
      // @param action 参数
      //============================================================
      public virtual void Action(SUiAction action) {
         OnActionBefore(action);
         OnActionAfter(action);
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置节点
      //============================================================
      public virtual void LoadResource(FRcObject resource) {
         resource.LinkerNode = this;
         _resource = resource;
      }

      //============================================================
      // <T>回收资源。</T>
      //============================================================
      public virtual void OnFree() {
         _context = null;
      }

      //============================================================
      // <T>回收资源。</T>
      //============================================================
      public virtual void Free() {
         OnFree();
      }

      //============================================================
      // <T>释放资源。</T>
      //============================================================
      public virtual void OnDispose() {
      }

      //============================================================
      // <T>释放资源。</T>
      //============================================================
      public virtual void Dispose() {
         OnDispose();
      }
   }
}
