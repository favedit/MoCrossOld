using MO.Common;
using MO.Common.Content;
using MO.Common.Geom;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Design2d.Face.Console;
using System.ComponentModel;
using System.Drawing;

namespace MO.Content2d.Frame.Common
{
   //============================================================
   // <T>界面表单。</T>
   //
   // @history MAOCY 120707
   //============================================================
   public class FRcFrame : FRcContainer
   {
      // 代码
      protected int _code;

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

      // 预览层集合
      //protected FUiControlLayers _previewLayers = new FUiControlLayers();

      //============================================================
      // <T>构造界面表单。</T>
      //
      // @param console 控制台
      //============================================================
      public FRcFrame(FRcFrameConsole console = null)
         : base(console) {
      }

      //============================================================
      // <T>获得或设置编号。</T>
      //============================================================
      public int Code {
         get { return _code; }
         set { _code = value; }
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
      // <T>获得格式化内容。</T>
      //
      // @return 格式化内容
      //============================================================
      public override string Format() {
         string result = _code + " - " + _name;
         if(!RString.IsEmpty(_label)) {
            result += " [" + _label + "]";
         } else if(!RString.IsEmpty(_hint)) {
            result += " [" + _hint + "]";
         }
         if(RString.IsEmpty(result)) {
            result = _typeName;
         }
         return result;
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
      //// <T>获得预览层集合。</T>
      ////============================================================
      //[Browsable(false)]
      //public FUiControlLayers PreviewLayers {
      //   get { return _previewLayers; }
      //}

      //============================================================
      // <T>加载设置信息</T>
      //
      // @param xconfig 设置信息
      //============================================================
      public override void OnLoadConfig(FXmlNode xconfig) {
         base.OnLoadConfig(xconfig);
         // 加载设计信息
         if(xconfig.Contains("design_layers")) {
            _designLayers = xconfig.GetBoolean("design_layers");
         }
         if(xconfig.Contains("design_back")) {
            _designBack = xconfig.GetBoolean("design_back");
         }
         if(xconfig.Contains("design_back_color")) {
            SColor color = new SColor();
            color.ParseHex(xconfig.Get("design_back_color"));
            _designBackColor = Color.FromArgb(color.A, color.R, color.G, color.B);
         }
      }

      //============================================================
      // <T>存储设置信息</T>
      //
      // @param xconfig 设置信息
      //============================================================
      public override void OnSaveConfig(FXmlNode xconfig) {
         // 保存背景资源
         xconfig.SetNvl("code", _code);
         // 保存设置信息
         base.OnSaveConfig(xconfig);
         // 保存属性
         xconfig.SetNvl("hint", _hint);
         xconfig.SetNvl("note", _note);
         // 保存设计信息
         xconfig.SetNvl("design_layers", _designLayers);
         xconfig.SetNvl("design_back", _designBack);
         xconfig.SetNvl("design_back_color", new SColor(_designBackColor.R, _designBackColor.G, _designBackColor.B, _designBackColor.A).ToString16());
      }

      //============================================================
      // <T>序列化内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public override void OnSerialize(IOutput output) {
         base.OnSerialize(output);
         output.WriteInt32(_code);
      }

      //============================================================
      // <T>加载配置文件。</T>
      //
      // @param fileName 文件名称
      //============================================================
      public void LoadFile(string fileName) {
         string name = _name;
         using (FXmlDocument xdocument = new FXmlDocument(fileName)) {
            _fileName = fileName;
            LoadConfig(xdocument.Root);
         }
         _name = name;
      }

      //============================================================
      // <T>存储配置文件。</T>
      //
      // @param fileName 文件名称
      //============================================================
      public void SaveFile(string fileName) {
         using (FXmlDocument xdocument = new FXmlDocument()) {
            // 创建控件
            SaveConfig(xdocument.Root);
            // 存储文件
            xdocument.SaveFile(fileName);
         }
      }

      //============================================================
      // <T>打开内容。</T>
      //============================================================
      public void Open() {
         if (!_opened) {
            LoadFile(_fileName);
            _opened = true;
         }
      }

      //============================================================
      // <T>打开分层内容。</T>
      //============================================================
      public void OpenLayers() {
         //if(!_openedLayers) {
         //   string path = RFile.GetDirectory(_fileName);
         //   string sourceSubPath = RMoCommon.ParseEnvironment(@"${resource.ui.root}");
         //   string targetSubPath = RMoCommon.ParseEnvironment(@"${resource.root}\Resource.UI");
         //   path = path.Replace(sourceSubPath, targetSubPath);
         //   if (RDirectory.Exists(path)) {
         //      FStrings fileNames = RDirectory.ListFiles(path);
         //      fileNames.Sort();
         //      foreach (string fileName in fileNames) {
         //         string name = RFile.GetFileName(fileName);
         //         if(name.StartsWith("layer_") && (name.EndsWith(".jpg") || name.EndsWith(".png"))) {
         //            FUiControlLayer layer = new FUiControlLayer();
         //            layer.Name = name;
         //            using (Bitmap bitmap = new Bitmap(fileName)) {
         //               layer.Bitmap = _context.Device.CreateBitmap(bitmap);
         //            }
         //            _previewLayers.Push(layer);
         //         }
         //      }
         //   }
         //   _openedLayers = true;
         //}
      }

      //============================================================
      // <T>存储内容。</T>
      //============================================================
      public void Store() {
         if (_opened) {
            SaveFile(_fileName);
         }
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
