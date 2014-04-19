using MO.Common;
using MO.Common.Collection;
using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Common.System;
using MO.Content2d.Frame.Common;
using MO.Content2d.Frame.Container;
using MO.Content2d.Frame.Controls;
using MO.Content2d.Frame.Frame;
using MO.Content2d.Frame.Grid;

namespace MO.Design2d.Face.Console
{
   //============================================================
   // <T>界面控制台</T>
   //============================================================
   public class FRcFrameConsole : FConsole
   {
      // 配置路径
      protected string _configDirectory;

      // 导出文件
      protected string _exportFileName;

      // 界面字典
      protected FDictionary<FRcFrame> _frames = new FDictionary<FRcFrame>();

      //============================================================
      // <T>构造界面控制台</T>
      //============================================================
      public FRcFrameConsole() {
         _name = "Content.Frame.Console";
      }

      //============================================================
      // <T>获得或设置配置路径。</T>
      //============================================================
      public string ConfigDirectory {
         get { return _configDirectory; }
         set { _configDirectory = value; }
      }

      //============================================================
      // <T>获得或设置导出文件。</T>
      //============================================================
      public string ExportFileName {
         get { return _exportFileName; }
         set { _exportFileName = value; }
      }

      //============================================================
      // <T>获得界面字典。</T>
      //============================================================
      public FDictionary<FRcFrame> Frames {
         get { return _frames; }
      }

      //============================================================
      // <T>根据名称查找界面资源。</T>
      //
      // @param name 名称
      // @return 界面资源
      //============================================================
      public FRcFrame FindFrame(string name) {
         return _frames.Find(name);
      }

      //============================================================
      // <T>根据名称打开界面资源。</T>
      //
      // @param name 名称
      // @return 界面资源
      //============================================================
      public FRcFrame OpenFrame(string name) {
         FRcFrame frame = _frames.Find(name);
         frame.Open();
         return frame;
      }

      //============================================================
      // <T>加载设置信息。</T>
      //
      // @param xconfig 设置节点
      //============================================================
      public override void LoadConfig(FXmlNode xconfig) {
         foreach (FXmlNode xnode in xconfig.Nodes) {
            if (xnode.IsName("Property")) {
               string name = xnode.Get("name");
               // 创建资源文件夹
               switch (name) {
                  case "config_directory":
                     _configDirectory = RMoCommon.ParseEnvironment(xnode.Text);
                     break;
                  case "export_file":
                     _exportFileName = RMoCommon.ParseEnvironment(xnode.Text);
                     break;
               }
            }
         }
      }

      //============================================================
      // <T>根据类型名称创建界面组件。</T>
      //
      // @param typeName 类型名称
      //============================================================
      public FRcComponent CreateComponent(string typeName) {
         ERcComponent componentCd = REnum.ToValue<ERcComponent>(typeName);
         FRcComponent component = null;
         // 创建对象
         switch (componentCd) {
            case ERcComponent.StaticLabel:
               component = new FRcStaticLabel(this);
               break;
            case ERcComponent.Label:
               component = new FRcLabel(this);
               break;
            case ERcComponent.Radio:
               component = new FRcRadio(this);
               break;
            case ERcComponent.Check:
               component = new FRcCheck(this);
               break;
            case ERcComponent.Number:
               component = new FRcNumber(this);
               break;
            case ERcComponent.Edit:
               component = new FRcEdit(this);
               break;
            case ERcComponent.RichEdit:
               component = new FRcRichEdit(this);
               break;
            case ERcComponent.Button:
               component = new FRcButton(this);
               break;
            case ERcComponent.Slider:
               component = new FRcSlider(this);
               break;
            case ERcComponent.ScrollBar:
               component = new FRcScrollBar(this);
               break;
            case ERcComponent.ProgressBar:
               component = new FRcProgressBar(this);
               break;
            case ERcComponent.Calendar:
               component = new FRcLabel(this);
               break;
            case ERcComponent.PictureBox:
               component = new FRcPictureBox(this);
               break;
            case ERcComponent.FontBox:
               component = new FRcFontBox(this);
               break;
            case ERcComponent.MovieBox:
               component = new FRcMovieBox(this);
               break;
            case ERcComponent.SlotItem:
               component = new FRcSlotItem(this);
               break;
            case ERcComponent.Slot:
               component = new FRcSlot(this);
               break;
            case ERcComponent.Panel:
               component = new FRcPanel(this);
               break;
            case ERcComponent.Page:
               component = new FRcPage(this);
               break;
            case ERcComponent.PageControl:
               component = new FRcPageControl(this);
               break;
            case ERcComponent.SelectItem:
               component = new FRcSelectItem(this);
               break;
            case ERcComponent.Select:
               component = new FRcSelect(this);
               break;
            case ERcComponent.MenuItem:
               component = new FRcMenuItem(this);
               break;
            case ERcComponent.Menu:
               component = new FRcMenu(this);
               break;
            case ERcComponent.ListItem:
               component = new FRcListItem(this);
               break;
            case ERcComponent.ListView:
               component = new FRcListView(this);
               break;
            case ERcComponent.TreeNodeType:
               component = new FRcTreeNodeType(this);
               break;
            case ERcComponent.TreeNodeLevel:
               component = new FRcTreeNodeLevel(this);
               break;
            case ERcComponent.TreeNode:
               component = new FRcTreeNode(this);
               break;
            case ERcComponent.TreeView:
               component = new FRcTreeView(this);
               break;
            case ERcComponent.GridCell:
               component = new FRcGridCell(this);
               break;
            case ERcComponent.GridCellCheck:
               component = new FRcGridCellCheck(this);
               break;
            case ERcComponent.GridCellNumber:
               component = new FRcGridCellNumber(this);
               break;
            case ERcComponent.GridCellText:
               component = new FRcGridCellText(this);
               break;
            case ERcComponent.GridRow:
               component = new FRcGridRow(this);
               break;
            case ERcComponent.GridColumn:
               component = new FRcLabel(this);
               break;
            case ERcComponent.GridColumnCheck:
               component = new FRcGridColumnCheck(this);
               break;
            case ERcComponent.GridColumnNumber:
               component = new FRcGridColumnNumber(this);
               break;
            case ERcComponent.GridColumnText:
               component = new FRcGridColumnText(this);
               break;
            case ERcComponent.GridView:
               component = new FRcGridView(this);
               break;
            case ERcComponent.Widget:
               component = new FRcWidget(this);
               break;
            case ERcComponent.Bar:
               component = new FRcBar(this);
               break;
            case ERcComponent.Form:
               component = new FRcForm(this);
               break;
            case ERcComponent.Table:
               component = new FRcTable(this);
               break;
            case ERcComponent.Window:
               component = new FRcWindow(this);
               break;
         }
         // 设置内容
         if (component != null) {
            component.LoadStyle();
         } else {
            throw new FFatalException("Unknown componment type. (type_name={1})", typeName);
         }
         // 返回对象
         return component;
      }

      //============================================================
      // <T>加载文件夹信息，取得地址。</T>
      // <P>加载文件下文件信息，取得文件地址。</P>
      //
      // @param directory 文件路径。
      //============================================================
      public void LoadDirectory(string rootDirectory) {
         // 加载给定路径下面的文件夹.
         FStrings directorys = RDirectory.ListDirectories(rootDirectory);
         foreach(string directory in directorys) {
            // 检查文件夹
            if(directory.Contains(".svn")) {
               continue;
            }
            // 取得文件夹类型名称。
            string fileName = directory.Substring(directory.LastIndexOf("\\") + 1);
            int codeIndex = fileName.IndexOf('-');
            string code = fileName.Substring(0, codeIndex);
            int nameBeginIndex = fileName.IndexOf('(');
            int nameEndIndex = fileName.LastIndexOf(')');
            string name = fileName.Substring(nameBeginIndex + 1, nameEndIndex - nameBeginIndex - 1).Trim();
            string typeName = RString.FirstUpper(name.Substring(name.LastIndexOf('.') + 1));
            string label = fileName.Substring(codeIndex + 1, nameBeginIndex - codeIndex - 1).Trim();
            // 文件夹的全路径地址。
            FRcFrame frame = CreateComponent(typeName) as FRcFrame;
            if(frame != null) {
               // 设置属性
               frame.Code = RInt.Parse(code);
               frame.Name = name;
               frame.Label = label;
               // 设置信息
               frame.FileName = directory + @"\config.xml";
               frame.Directory = directory;
               // 放入集合
               _frames.Set(name, frame);
            }
         }
      }

      //============================================================
      // <T>加载文件夹信息</T>
      //
      // @param config 文件路径。
      //============================================================
      public void Open() {
         if (!RDirectory.Exists(_configDirectory)) {
            return;
         }
         LoadDirectory(_configDirectory);
      }

      //============================================================
      // <T>加载文件夹信息</T>
      //
      // @param config 文件路径。
      //============================================================
      public void ExportAll() {
         // 加载文件
         FByteFile file = new FByteFile();
         file.WriteInt32(_frames.Count);
         foreach (INamePair<FRcFrame> pair in _frames) {
            FRcFrame frame = pair.Value;
            // 打开顶层容器
            frame.Open();
            // 序列化顶层容器
            frame.Serialize(file);
         }
         // 保存文件
         file.SaveFile(_exportFileName);
      }

      //============================================================
      public void SaveAll() {         
         //foreach(FUiFrame contatiner in _containers) {
         //   //contatiner.Save();
         //}
         //MessageBox.Show("保存完毕！");
      }     
   }
}
