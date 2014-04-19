using MO.Common;
using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content2d.Frame.Common;
using MO.Design2d.Frame.Core;
using MO.Design2d.Frame;
using System.ComponentModel;
using System.Drawing;
using MO.Common.Geom;

namespace MO.Design2d.Frame.Common
{
   //============================================================
   // <T>界面表单。</T>
   //
   // @history MAOCY 120707
   //============================================================
   public class FUiFrame : FUiContainer
   {
      // 是否打开
      protected bool _opened;

      // 是否打开参考层
      protected bool _openedLayers;

      // 设计使用背景
      protected bool _designLayers = false;

      // 设计背景
      protected bool _designBack = false;

      // 设计背景颜色
      protected Color _designBackColor = Color.Black;
      
      // 文件路径
      protected string _directory;

      // 文件名称
      protected string _fileName;

      protected FUiPicture _groundResource = new FUiPicture();

      // 预览层集合
      protected FUiControlLayers _previewLayers = new FUiControlLayers();

      //============================================================
      // <T>构造界面表单。</T>
      //
      // @param console 控制台
      //============================================================
      public FUiFrame(FUiFrameConsole console = null)
         : base(console) {
      }

      //============================================================
      // <T>获得资源。</T>
      //============================================================
      [Browsable(false)]
      public FRcFrame FrameResource {
         get { return _resource as FRcFrame; }
      }

      //============================================================
      // <T>获得或设置显示参考层。</T>
      //============================================================
      public bool DesignLayers {
         get { return _designLayers; }
         set { _designLayers = value; }
      }

      //============================================================
      // <T>获得或设置设计背景。</T>
      //============================================================
      public bool DesignBack {
         get { return _designBack; }
         set { _designBack = value; }
      }

      //============================================================
      // <T>获得或设置设计背景颜色。</T>
      //============================================================
      public Color DesignBackColor {
         get { return _designBackColor; }
         set { _designBackColor = value; }
      }
      
      //============================================================
      // <T>获得或设置文件路径。</T>
      //============================================================
      [Browsable(false)]
      public string Directory {
         get { return _directory; }
         set { _directory = value; }
      }

      //============================================================
      // <T>获得或设置文件名称。</T>
      //============================================================
      [Browsable(false)]
      public string FileName {
         get { return _fileName; }
         set { _fileName = value; }
      }

      //============================================================
      // <T>获得预览层集合。</T>
      //============================================================
      [Browsable(false)]
      public FUiControlLayers PreviewLayers {
         get { return _previewLayers; }
      }

      //============================================================
      // <T>配置处理。</T>
      //
      // @param args 参数
      //============================================================
      public override void OnSetup(SUiSetupArgs args) {
         base.OnSetup(args);
         SetupResource(_groundResource);
      }

      //============================================================
      // <T>开始绘制处理。</T>
      //
      // @param args 参数
      //============================================================
      public override void OnDrawBegin(SUiDrawArgs args) {
         base.OnDrawBegin(args);
         // 绘制处理
         if (TestVisible()) {
            // 绘制背景资源
            DrawResource(_groundResource);
         }
      }

      //============================================================
      // <T>结束绘制处理。</T>
      //
      // @param args 参数
      //============================================================
      public override void OnDrawAfter(SUiDrawArgs args) {
         base.OnDrawAfter(args);
      }

      //============================================================
      // <T>自动调整处理。</T>
      //============================================================
      public override void OnAdjust() {
         // 调整大小
         base.OnAdjust();
         if(!_previewLayers.IsEmpty()) {
            foreach(FUiControlLayer layer in _previewLayers){
              //_size.InnerMax(layer.Size);
            }
         }
      }

      //============================================================
      // <T>打开分层内容。</T>
      //============================================================
      public void OpenLayers() {
         if(!_openedLayers) {
            //string path = RFile.GetDirectory(_fileName);
            //string sourceSubPath = RMoCommon.ParseEnvironment(@"${resource.ui.root}");
            //string targetSubPath = RMoCommon.ParseEnvironment(@"${resource.root}\Resource.UI");
            //path = path.Replace(sourceSubPath, targetSubPath);
            //if (RDirectory.Exists(path)) {
            //   FStrings fileNames = RDirectory.ListFiles(path);
            //   fileNames.Sort();
            //   foreach (string fileName in fileNames) {
            //      string name = RFile.GetFileName(fileName);
            //      if(name.StartsWith("layer_") && (name.EndsWith(".jpg") || name.EndsWith(".png"))) {
            //         FUiControlLayer layer = new FUiControlLayer();
            //         layer.Name = name;
            //         using (Bitmap bitmap = new Bitmap(fileName)) {
            //            layer.Bitmap = _context.Device.CreateBitmap(bitmap);
            //         }
            //         _previewLayers.Push(layer);
            //      }
            //   }
            //}
            _openedLayers = true;
         }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置节点
      //============================================================
      public override void LoadResource(FRcObject resource) {
         base.LoadResource(resource);
      }

      //============================================================
      // <T>关闭内容。</T>
      //============================================================
      public void Close() {
         if (_opened) {
            _opened = false;
         }
      }

      //============================================================
      // <T>释放资源。</T>
      //============================================================
      public override void OnDispose() {
         base.OnDispose();
      }
   }
}
