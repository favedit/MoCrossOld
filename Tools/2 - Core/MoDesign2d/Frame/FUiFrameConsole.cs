using MO.Common.Collection;
using MO.Common.Lang;
using MO.Common.System;
using MO.Content2d;
using MO.Content2d.Frame.Common;
using MO.Design2d.Frame.Common;
using MO.Design2d.Frame.Forms;
using MO.Design2d.Frame.Controls;
using MO.Design2d.Frame.Grid;
using MO.Design2d.Frame.Frame;
using MO.Design2d.Frame.Container;

namespace MO.Design2d.Frame
{
   //============================================================
   // <T>界面控制台</T>
   //============================================================
   public class FUiFrameConsole : FConsole
   {
      // 界面容器字典
      protected FDictionary<FUiFrame> _frames = new FDictionary<FUiFrame>();

      // 界面设计器字典
      protected FDictionary<QUiDesignForm> _frameForms = new FDictionary<QUiDesignForm>();

      //============================================================
      // <T>构造界面控制台</T>
      //============================================================
      public FUiFrameConsole() {
         _name = "Design.Frame.Console";
      }

      //============================================================
      // <T>获得界面容器字典。</T>
      //============================================================
      public FDictionary<FUiFrame> Frames {
         get { return _frames; }
      }

      //============================================================
      // <T>获得界面设计器字典。</T>
      //============================================================
      public FDictionary<QUiDesignForm> FrameForms {
         get { return _frameForms; }
      }

      //============================================================
      // <T>根据类型名称创建界面组件。</T>
      //
      // @param typeName 类型名称
      //============================================================
      public FUiComponent CreateComponent(string typeName) {
         FUiComponent component = null;
         ERcComponent componentCd = REnum.ToValue<ERcComponent>(typeName);
         switch (componentCd) {
            case ERcComponent.StaticLabel:
               component = new FUiStaticLabel(this);
               break;
            case ERcComponent.Label:
               component = new FUiLabel(this);
               break;
            case ERcComponent.Radio:
               component = new FUiRadio(this);
               break;
            case ERcComponent.Check:
               component = new FUiCheck(this);
               break;
            case ERcComponent.Number:
               component = new FUiNumber(this);
               break;
            case ERcComponent.Edit:
               component = new FUiEdit(this);
               break;
            case ERcComponent.RichEdit:
               component = new FUiRichEdit(this);
               break;
            case ERcComponent.Button:
               component = new FUiButton(this);
               break;
            case ERcComponent.Slider:
               component = new FUiSlider(this);
               break;
            case ERcComponent.ScrollBar:
               component = new FUiScrollBar(this);
               break;
            case ERcComponent.ProgressBar:
               component = new FUiProgressBar(this);
               break;
            case ERcComponent.Calendar:
               component = new FUiLabel(this);
               break;
            case ERcComponent.PictureBox:
               component = new FUiPictureBox(this);
               break;
            case ERcComponent.FontBox:
               component = new FUiFontBox(this);
               break;
            case ERcComponent.MovieBox:
               component = new FUiMovieBox(this);
               break;
            case ERcComponent.SlotItem:
               component = new FUiSlotItem(this);
               break;
            case ERcComponent.Slot:
               component = new FUiSlot(this);
               break;
            case ERcComponent.Panel:
               component = new FUiPanel(this);
               break;
            case ERcComponent.Page:
               component = new FUiPage(this);
               break;
            case ERcComponent.PageControl:
               component = new FUiPageControl(this);
               break;
            case ERcComponent.SelectItem:
               component = new FUiSelectItem(this);
               break;
            case ERcComponent.Select:
               component = new FUiSelect(this);
               break;
            case ERcComponent.MenuItem:
               component = new FUiMenuItem(this);
               break;
            case ERcComponent.Menu:
               component = new FUiMenu(this);
               break;
            case ERcComponent.ListItem:
               component = new FUiListItem(this);
               break;
            case ERcComponent.ListView:
               component = new FUiListView(this);
               break;
            case ERcComponent.TreeNodeType:
               component = new FUiTreeNodeType(this);
               break;
            case ERcComponent.TreeNodeLevel:
               component = new FUiTreeNodeLevel(this);
               break;
            case ERcComponent.TreeNode:
               component = new FUiTreeNode(this);
               break;
            case ERcComponent.TreeView:
               component = new FUiTreeView(this);
               break;
            case ERcComponent.GridCell:
               component = new FUiGridCell(this);
               break;
            case ERcComponent.GridCellCheck:
               component = new FUiGridCellCheck(this);
               break;
            case ERcComponent.GridCellNumber:
               component = new FUiGridCellNumber(this);
               break;
            case ERcComponent.GridCellText:
               component = new FUiGridCellText(this);
               break;
            case ERcComponent.GridRow:
               component = new FUiGridRow(this);
               break;
            case ERcComponent.GridColumn:
               component = new FUiLabel(this);
               break;
            case ERcComponent.GridColumnCheck:
               component = new FUiGridColumnCheck(this);
               break;
            case ERcComponent.GridColumnNumber:
               component = new FUiGridColumnNumber(this);
               break;
            case ERcComponent.GridColumnText:
               component = new FUiGridColumnText(this);
               break;
            case ERcComponent.GridView:
               component = new FUiGridView(this);
               break;
            case ERcComponent.Widget:
               component = new FUiWidget(this);
               break;
            case ERcComponent.Bar:
               component = new FUiBar(this);
               break;
            case ERcComponent.Form:
               component = new FUiForm(this);
               break;
            case ERcComponent.Table:
               component = new FUiTable(this);
               break;
            case ERcComponent.Window:
               component = new FUiWindow(this);
               break;
         }
         if (component == null) {
            throw new FFatalException("Unknown componment type. (type_name={1})", typeName);
         }
         return component;
      }

      //============================================================
      // <T>根据名称打开界面。</T>
      //
      // @param name 名称
      // @return 界面
      //============================================================
      public FUiFrame OpenFrame(string name) {
         FUiFrame frame = _frames.Find(name);
         if (frame == null) {
            FRcFrame resource = RContent2dManager.FrameConsole.OpenFrame(name);
            frame = CreateComponent(resource.TypeName) as FUiFrame;
            frame.LoadResource(resource);
            _frames.Set(name, frame);
         }
         return frame;
      }

      //============================================================
      // <T>根据类型名称创建界面组件。</T>
      //
      // @param typeName 类型名称
      //============================================================
      public QUiDesignForm OpenDesignForm(string name) {
         // 弹出画面
         QUiDesignForm form = _frameForms.Find(name);
         if (form == null) {
            FUiFrame frame = OpenFrame(name);
            form = new QUiDesignForm();
            form.LoadFrame(frame);
            _frameForms.Set(name, form);
         }
         return form;
      }
   }
}
