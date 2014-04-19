using MO.Common.Geom;
using MO.Common.Lang;
using MO.Content2d;
using MO.Content2d.Frame.Common;
using MO.Core.Forms;
using MO.Core.Forms.Controls;
using MO.Design2d.Frame.Common;
using MO.Design2d.Frame.Core;
using MO.Direct2d.Device;
using System.Drawing;
using System.Windows.Forms;

namespace MO.Design2d.Frame.Forms
{
   //============================================================
   // <T>界面设计表单。</T>
   //============================================================
   public partial class QUiDesignForm : Form
   {
      // 设备
      protected FDxDevice2d _device = new FDxDevice2d();

      // 设计表单
      protected FUiFrame _frame;

      // 表单设计器
      protected FUiFormDesigner _designer = new FUiFormDesigner();

      // 选择控件集合
      protected ToolStripMenuItem[] _selectControlMenuItems;

      // 编辑中
      protected bool _editing = false;

      //============================================================
      // <T>构造界面设计表单。</T>
      //============================================================
      public QUiDesignForm() {
         InitializeComponent();
         _selectControlMenuItems = new ToolStripMenuItem[] {
               tsmiSelectControl1, tsmiSelectControl2, tsmiSelectControl3, 
               tsmiSelectControl4, tsmiSelectControl5, tsmiSelectControl6, 
               tsmiSelectControl7, tsmiSelectControl8, tsmiSelectControl9 };
         // 设置设备
         _device.Size.Set(pnlCanvas.Width, pnlCanvas.Height);
         _device.Handle = pnlCanvas.Handle;
         _device.Setup();
         // 设置环境
         _designer.Device = _device;
         _designer.DesignPanel = pnlCanvas;
         _designer.Setup();
         // 设置属性变更
         uppProperty.PropertyChanged += uppProperty_PropertyChanged;
      }

      //============================================================
      // <T>加载界面表单。</T>
      //
      // @param form 表单
      //============================================================
      protected void InnerBuildTree(FUiComponent component, TreeNodeCollection nodes) {
         // 建立控件节点
         TreeNode node = new TreeNode();
         node.Checked = component.ComponentResource.OptionValid;
         node.Text = component.Format();
         node.ImageKey = component.ComponentResource.TypeName;
         node.SelectedImageKey = component.ComponentResource.TypeName;
         node.Tag = component;
         nodes.Add(node);
         component.LinkerNode = node;
         // 建立控件子节点集合
         if (component.HasComponment()) {
            foreach (FUiComponent child in component.Components) {
               InnerBuildTree(child, node.Nodes);
            }
         }
      }

      //============================================================
      // <T>加载界面表单。</T>
      //
      // @param form 表单
      //============================================================
      protected void InnerBuildLayer() {
         // 建立层列表
         int layerIndex = 0;
         ListViewItem designLayer = new ListViewItem((++layerIndex).ToString());
         designLayer.Checked = true;
         designLayer.SubItems.Add("设计层");
         designLayer.Tag = _frame;
         lvwLayers.Items.Add(designLayer);
         // 建立层列表
         foreach (FUiControlLayer layer in _frame.PreviewLayers) {
            ListViewItem previewLayer = new ListViewItem((++layerIndex).ToString());
            previewLayer.Checked = true;
            previewLayer.SubItems.Add("背景层 - " + layer.Name);
            previewLayer.Tag = layer;
            lvwLayers.Items.Add(previewLayer);
         }
      }

      //============================================================
      // <T>加载界面表单。</T>
      //
      // @param form 表单
      //============================================================
      public void LoadFrame(FUiFrame form) {
         _frame = form;
         // 设置标题
         Text = "设计表单 [" + form.Format() + "]";
         // 加载表单
         SUiSetupArgs args = new SUiSetupArgs();
         args.context = _designer.Context;
         _frame.Setup(args);
         _frame.OpenLayers();
         _designer.DesignForm = _frame;
         ChangePreviewLayer(_frame.DesignLayers);
         // 建立目录
         InnerBuildTree(_frame, tvwCatalog.Nodes);
         tvwCatalog.ExpandAll();
         // 建立层列表
         InnerBuildLayer();
         // 刷新大小
         RefreshSize();
         tsbCellSize.Text = "4";
         _designer.CellSize.Set(4, 4);
      }

      //============================================================
      // <T>加载界面表单。</T>
      //
      // @param form 表单
      //============================================================
      public void SelectComponent(FUiComponent component, FUiComponent[] components = null) {
         // 设置目录选择
         TreeNode node = null;
         if (component != null) {
            // 在父中激活自己
            component.ActiveFromParent();
            // 获得选择节点
            node = component.LinkerNode as TreeNode;
         }
         // 设置焦点控件
         _designer.Selection.Clear();
         _designer.Selection.FocusControl = component as FUiControl;
         if(components != null) {
            foreach(FUiComponent item in components){
               _designer.Selection.AddSelectNotFocusControl(item as FUiControl);
            }
         }
         // 获得焦点资源
         if(components != null) {
            int count = components.Length;
            if (count == 1) {
               uppProperty.LoadResource(component.Resource);
            } else {
               object[] resources = new object[count];
               for (int n = 0; n < count; n++) {
                  resources[n] = components[n].Resource;
               }
               //pgdProperties.SelectedObjects = resources;
            }
         } else if (component != null) {
            uppProperty.LoadResource(component.Resource);
            //pgdProperties.SelectedObject = component.Resource;
         }
         // 刷新选择处理
         RefreshSelection();
         // 重绘处理
         _designer.Paint();
      }

      //============================================================
      // <T>刷新选择处理。</T>
      //============================================================
      private void RefreshSelection() {
         lvwSelection.BeginUpdate();
         lvwSelection.Items.Clear();
         int index = 0;
         foreach(FUiControl control in _designer.Selection) {
            ListViewItem item = new ListViewItem((++index).ToString());
            item.SubItems.Add(control.Format());
            lvwSelection.Items.Add(item);
         }
         lvwSelection.EndUpdate();
      }

      //============================================================
      // <T>刷新大小处理。</T>
      //============================================================
      private void RefreshSize() {
         // 设置画板
         float scale = _designer.DesignScale;
         SIntSize2 size = _designer.DesignForm.Size;
         int width = (int)(size.Width * scale);
         if (width < pnlCanvasBox.Width) {
            width = pnlCanvasBox.Width;
         }
         int height = (int)(size.Height * scale);
         if (height < pnlCanvasBox.Height) {
            height = pnlCanvasBox.Height;
         }
         pnlCanvas.Width = width;
         pnlCanvas.Height = height;
         // 改变设备大小
         _device.Resize(width, height);
         // 改变大小
         _designer.Resize(width, height);
         _designer.Refresh();
      }

      //============================================================
      // <T>加载表单处理。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void QUiDesignForm_ApplicationIdle(object sender, System.EventArgs e) {
         int selectNodeCount = tvwCatalog.SelectedNodes.Count;
         int selectControlCount = _designer.Selection.Count;
         // 目录按键状态
         tsbCatalogLevelUp.Enabled = (selectNodeCount == 1);
         tsbCatalogLevelDown.Enabled = (selectNodeCount == 1);
         tsbCatalogUp.Enabled = (selectNodeCount == 1);
         tsbCatalogDown.Enabled = (selectNodeCount == 1);
         tsbCatalogDelete.Enabled = (selectNodeCount >= 1);
         // 调整按键状态
         tsbDesignAdjust.Enabled = (selectControlCount >= 1);
         tsbDesignAdjustLocation.Enabled = (selectControlCount >= 1);
         // 对齐按键状态
         tsbDesignAlignCenter.Enabled = (selectControlCount >= 2);
         tsbDesignAlignHLeft.Enabled = (selectControlCount >= 2);
         tsbDesignAlignHMiddle.Enabled = (selectControlCount >= 2);
         tsbDesignAlignHRight.Enabled = (selectControlCount >= 2);
         tsbDesignAlignVTop.Enabled = (selectControlCount >= 2);
         tsbDesignAlignVMiddle.Enabled = (selectControlCount >= 2);
         tsbDesignAlignVBottom.Enabled = (selectControlCount >= 2);
         // 对齐按键状态
         tsbDesignAlignHAvg.Enabled = (selectControlCount >= 3);
         tsbDesignAlignHAvgSize.Enabled = (selectControlCount >= 3);
         tsbDesignAlignVAvg.Enabled = (selectControlCount >= 3);
         tsbDesignAlignVAvgSize.Enabled = (selectControlCount >= 3);
      }

      //============================================================
      // <T>加载表单处理。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void QUiDesignForm_Load(object sender, System.EventArgs e) {
         Application.Idle += new System.EventHandler(QUiDesignForm_ApplicationIdle);
         // 选择空对象
         SelectComponent(null);
      }

      //============================================================
      // <T>表单关闭处理。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void QUiDesignForm_FormClosing(object sender, FormClosingEventArgs e) {
         e.Cancel = true;
         Hide();
      }

      //============================================================
      // <T>关闭表单处理。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void QUiDesignForm_FormClosed(object sender, FormClosedEventArgs e) {
         Application.Idle -= QUiDesignForm_ApplicationIdle;
         _designer.Dispose();
      }

      //============================================================
      // <T>测试游标是否可以移动。</T>
      //
      // @param 是否可以移动
      //============================================================
      private bool TestCursorMoveAble() {
         if(_editing) {
            return false;
         }
         return true;
      }

      //============================================================
      // <T>按键按下处理。</T>
      //
      // @param sender 发送者
      // @param e 事件
      //============================================================
      private void QUiDesignForm_KeyDown(object sender, KeyEventArgs e) {
         bool changed = false;
         // 按键处理
         RKeyboard.DoKeyDown(e);
         // 是否按下F3按键
         switch (e.KeyCode) {
            case Keys.F2:
               tsbDesignAdjustAll_Click(null, null);
               break;
            case Keys.F3:
               tsbDesignOneFocus_Click(null, null);
               break;
            case Keys.F4:
               tsbDesignBorder_Click(null, null);
               break;
            case Keys.F5:
               tsbCatalogInsert_Click(null, null);
               break;
            case Keys.F6:
               tsbCatalogCopy_Click(null, null);
               break;
            case Keys.F7:
               tspCatalogDelete_Click(null, null);
               break;
            case Keys.F9:
               tsbDesignAdjust_Click(null, null);
               break;
            case Keys.F10:
               tsbDesignAdjustLocation_Click(null, null);
               break;
         }
         // 是否按下Control按键
         if(e.Control && TestCursorMoveAble()) {
            switch (e.KeyCode) {
               case Keys.Left:
                  _designer.Selection.DesignMove(EUiDesignMove.Left, 1);
                  e.Handled = true;
                  changed = true;
                  break;
               case Keys.Up:
                  _designer.Selection.DesignMove(EUiDesignMove.Up, 1);
                  e.Handled = true;
                  changed = true;
                  break;
               case Keys.Right:
                  _designer.Selection.DesignMove(EUiDesignMove.Right, 1);
                  e.Handled = true;
                  changed = true;
                  break;
               case Keys.Down:
                  _designer.Selection.DesignMove(EUiDesignMove.Down, 1);
                  e.Handled = true;
                  changed = true;
                  break;
               case Keys.S:
                  tsbStore_Click(null, null);
                  e.Handled = true;
                  break;
               case Keys.W:
                  tsbClose_Click(null, null);
                  e.Handled = true;
                  break;
            }
         }
         // 是否按下Shift按键
         if(e.Shift && TestCursorMoveAble()) {
            switch(e.KeyCode) {
               case Keys.Left:
                  _designer.Selection.DesignMove(EUiDesignMove.Left, 10);
                  e.Handled = true;
                  changed = true;
                  break;
               case Keys.Up:
                  _designer.Selection.DesignMove(EUiDesignMove.Up, 10);
                  e.Handled = true;
                  changed = true;
                  break;
               case Keys.Right:
                  _designer.Selection.DesignMove(EUiDesignMove.Right, 10);
                  e.Handled = true;
                  changed = true;
                  break;
               case Keys.Down:
                  _designer.Selection.DesignMove(EUiDesignMove.Down, 10);
                  e.Handled = true;
                  changed = true;
                  break;
            }
         }
         // 变更后绘制
         if(changed) {
            _designer.Paint();
         }
      }

      //============================================================
      // <T>按键抬起处理。</T>
      //
      // @param sender 发送者
      // @param e 事件
      //============================================================
      private void QUiDesignForm_KeyUp(object sender, KeyEventArgs e) {
         RKeyboard.DoKeyUp(e);
      }

      //============================================================
      // <T>存储配置处理。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void tsbStore_Click(object sender, System.EventArgs e) {
         uppProperty.SaveResource();
         _designer.Store();
         MessageBox.Show("存储界面设置成功。");
      }

      //============================================================
      // <T>全部导出处理。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void tsbExportAll_Click(object sender, System.EventArgs e) {
         //Enabled = false;
         //RDesign2dFace.UiConsole.ExportAll();
         //Enabled = true;
         //MessageBox.Show("导出全部界面成功。");
      }

      //============================================================
      // <T>全部打包处理。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void tsbPackAll_Click(object sender, System.EventArgs e) {
         //Enabled = false;
         //new FDsConfigurationMerger().Merge();
         //Enabled = true;
         //MessageBox.Show("打包全部界面成功。");
      }

      //============================================================
      // <T>选择背景颜色。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void tsbBackColor_Click(object sender, System.EventArgs e) {
         //dlgColor.Color = _designer.DesignBackColor;
         //if(dlgColor.ShowDialog() == DialogResult.OK) {
         //   _designer.DesignBack = true;
         //   _designer.DesignBackColor = dlgColor.Color;
         //}
      }

      //============================================================
      // <T>使用背景透明。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void tsbBackAlpha_Click(object sender, System.EventArgs e) {
         _designer.DesignBack = false;
      }

      //============================================================
      // <T>格子尺寸调整。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void tsbCellSize_Click(object sender, System.EventArgs e) {
         int cellSize = RInt.Parse(tsbCellSize.Text);
         _designer.CellSize.Set(cellSize, cellSize);
      }

      //============================================================
      // <T>调整全部控件。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void tsbDesignAdjustAll_Click(object sender, System.EventArgs e) {
         Enabled = false;
         _designer.DesignForm.DoActionAdjust();
         _designer.Paint();
         Enabled = true;
         MessageBox.Show("调整全部界面成功。");
      }

      //============================================================
      // <T>唯一模式处理。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void tsbDesignOneFocus_Click(object sender, System.EventArgs e) {
         tsbDesignOneFocus.Checked = !tsbDesignOneFocus.Checked;
         _designer.Context.OptionOneFocus = tsbDesignOneFocus.Checked;
      }

      //============================================================
      // <T>显示边框配置处理。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void tsbDesignBorder_Click(object sender, System.EventArgs e) {
         tsbDesignBorder.Checked = !tsbDesignBorder.Checked;
         _designer.DoActionBorder(tsbDesignBorder.Checked);
         _designer.Paint();
      }

      //============================================================
      // <T>显示目录配置处理。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void tsbCatalog_Click(object sender, System.EventArgs e) {
         tsbCatalog.Checked = !tsbCatalog.Checked;
         spcCatalog.Panel1Collapsed = !tsbCatalog.Checked;
      }

      //============================================================
      // <T>显示属性配置处理。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void tsbProperty_Click(object sender, System.EventArgs e) {
         tsbProperty.Checked = !tsbProperty.Checked;
         spcProperty.Panel2Collapsed = !tsbProperty.Checked;
      }

      //============================================================
      // <T>预览层显示配置处理。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void ChangePreviewLayer(bool value) {
         tsbPreviewLayer.Checked = value;
         foreach(ListViewItem listItem in lvwLayers.Items) {
            object item = listItem.Tag;
            if(item is FUiControlLayer) {
               FUiControlLayer layer = item as FUiControlLayer;
               listItem.Checked = tsbPreviewLayer.Checked;
               layer.OptionVisible = tsbPreviewLayer.Checked;
            }
         }
         _designer.DesignLayers = tsbPreviewLayer.Checked;
         _designer.Paint();
      }

      //============================================================
      // <T>预览层显示配置处理。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void tsbPreviewLayer_Click(object sender, System.EventArgs e) {
         ChangePreviewLayer(!tsbPreviewLayer.Checked);
      }

      //============================================================
      // <T>显示属性交换处理。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void tsbPropertySwap_Click(object sender, System.EventArgs e) {
         tsbPropertySwap.Checked = !tsbPropertySwap.Checked;
         int width = tbcProperty.Width;
         spcProperty.SuspendLayout();
         if(tsbPropertySwap.Checked) {
            spcProperty.FixedPanel = FixedPanel.Panel1;
            spcProperty.SplitterDistance = width;
            spcProperty.Panel1.Controls.Add(tbcProperty);
            spcProperty.Panel2.Controls.Add(pnlBody);
         } else {
            spcProperty.FixedPanel = FixedPanel.Panel2;
            spcProperty.SplitterDistance = spcProperty.Width - width;
            spcProperty.Panel1.Controls.Add(pnlBody);
            spcProperty.Panel2.Controls.Add(tbcProperty);
         }
         spcProperty.ResumeLayout();
      }

      //============================================================
      // <T>关闭处理。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void tsbClose_Click(object sender, System.EventArgs e) {
         Hide();
      }

      //============================================================
      // <T>控件目录树级别上移。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void tspCatalogLevelUp_Click(object sender, System.EventArgs e) {
         TreeNode selectNode = tvwCatalog.CurrentNode;
         if (selectNode == null) {
            return;
         }
         TreeNode parentNode = selectNode.Parent;
         if (parentNode == null) {
            MessageBox.Show("不存在父控件。");
            return;
         }
         TreeNode parentParentNode = parentNode.Parent;
         if (parentParentNode == null) {
            MessageBox.Show("不存在父控件的父控件。");
            return;
         }
         //............................................................
         FUiComponent selectComponent = selectNode.Tag as FUiComponent;
         FUiComponent parentComponent = parentNode.Tag as FUiComponent;
         FUiComponent parentParentComponent = parentParentNode.Tag as FUiComponent;
         if ((parentComponent != null) && (selectComponent != null) && (parentParentComponent != null)) {
            if (!selectComponent.TestParent(parentParentComponent)) {
               MessageBox.Show("当前控件无法放入父控件内。");
               return;
            }
            // 移动控件
            parentComponent.Remove(selectComponent);
            parentParentComponent.InsertBefore(selectComponent, parentComponent);
            // 移动节点
            parentNode.Nodes.Remove(selectNode);
            parentParentNode.Nodes.Insert(parentNode.Index, selectNode);
            tvwCatalog.SelectionSingle(selectNode);
         }
      }

      //============================================================
      // <T>控件目录树级别下移。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void tspCatalogLevelDown_Click(object sender, System.EventArgs e) {
         TreeNode selectNode = tvwCatalog.CurrentNode;
         if (selectNode == null) {
            return;
         }
         //............................................................
         TreeNode parentNode = selectNode.Parent;
         if (parentNode == null) {
            MessageBox.Show("不存在父控件。");
            return;
         }
         //............................................................
         TreeNode containerNode = selectNode.NextNode;
         FUiComponent containerComponent = null;
         while (containerNode != null) {
            FUiContainer findComponent = containerNode.Tag as FUiContainer;
            if (findComponent != null) {
               containerComponent = findComponent;
               break;
            }
            containerNode = containerNode.NextNode;
         }
         if (containerComponent == null) {
            MessageBox.Show("不存在子容器控件。");
            return;
         }
         //............................................................
         FUiComponent selectComponent = selectNode.Tag as FUiComponent;
         FUiComponent parentComponent = parentNode.Tag as FUiComponent;
         if ((parentComponent != null) && (selectComponent != null) && (containerComponent != null)) {
            if (!selectComponent.TestParent(containerComponent)) {
               MessageBox.Show("当前控件无法放入父控件内。");
               return;
            }
            // 移动控件
            parentComponent.Remove(selectComponent);
            containerComponent.Push(selectComponent);
            // 移动节点
            parentNode.Nodes.Remove(selectNode);
            containerNode.Nodes.Add(selectNode);
            tvwCatalog.SelectionSingle(selectNode);
         }
      }

      //============================================================
      // <T>控件目录树上移。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void tspCatalogUp_Click(object sender, System.EventArgs e) {
         TreeNode selectNode = tvwCatalog.CurrentNode;
         if (selectNode != null) {
            TreeNode parentNode = selectNode.Parent;
            TreeNode priorNode = selectNode.PrevNode;
            if ((parentNode != null) && (priorNode != null)) {
               FUiComponent parentComponent = parentNode.Tag as FUiComponent;
               FUiComponent selectComponent = selectNode.Tag as FUiComponent;
               FUiComponent priorComponent = priorNode.Tag as FUiComponent;
               if ((parentComponent != null) && (selectComponent != null) && (priorComponent != null)) {
                  // 移动控件
                  parentComponent.InsertBefore(selectComponent, priorComponent);
                  // 移动节点
                  parentNode.Nodes.Remove(selectNode);
                  parentNode.Nodes.Insert(priorNode.Index, selectNode);
                  tvwCatalog.SelectionSingle(selectNode);
               }
            }
         }
      }

      //============================================================
      // <T>控件目录树下移。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void tspCatalogDown_Click(object sender, System.EventArgs e) {
         TreeNode selectNode = tvwCatalog.CurrentNode;
         if (selectNode != null) {
            TreeNode parentNode = selectNode.Parent;
            TreeNode nextNode = selectNode.NextNode;
            if ((parentNode != null) && (nextNode != null)) {
               FUiComponent parentComponent = parentNode.Tag as FUiComponent;
               FUiComponent selectComponent = selectNode.Tag as FUiComponent;
               FUiComponent nextComponent = nextNode.Tag as FUiComponent;
               if ((parentComponent != null) && (selectComponent != null) && (nextComponent != null)) {
                  // 移动控件
                  parentComponent.InsertAfter(selectComponent, nextComponent);
                  // 移动节点
                  parentNode.Nodes.Remove(selectNode);
                  parentNode.Nodes.Insert(nextNode.Index + 1, selectNode);
                  tvwCatalog.SelectionSingle(selectNode);
               }
            }
         }
      }

      //============================================================
      // <T>插入控件事件。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void tsbCatalogInsert_Click(object sender, System.EventArgs e) {
         // 获得容器控件
         FUiContainer selectContainer = null;
         TreeNode parentNode = null;
         TreeNode selecteNode = tvwCatalog.CurrentNode;
         if(selecteNode == null) {
            MessageBox.Show("请选中目录树节点。");
            return;
         }
         FUiComponent selectComponent = selecteNode.Tag as FUiComponent;
         if(selectComponent is FUiContainer) {
            selectContainer = selectComponent as FUiContainer;
         } else {
            parentNode = selecteNode.Parent;
            FUiContainer parentContainer = parentNode.Tag as FUiContainer;
            if(parentContainer != null) {
               selectContainer = parentContainer;
            }
         }
         if(selectContainer == null) {
            MessageBox.Show("当前位置无法新建控件。");
            return;
         }
         FRcContainer selectContainerResource = selectContainer.Resource as FRcContainer;
         if (selectContainerResource == null) {
            MessageBox.Show("当前位置无法新建控件。");
            return;
         }
         // 选择控件表单
         if (QUiControlPickerForm.Instance.ShowDialog() == DialogResult.OK) {
            string controlName = QUiControlPickerForm.Instance.SelectControlName;
            if (RString.IsEmpty(controlName)) {
               MessageBox.Show("无法创建未知控件。");
               return;
            }
            // 创建控件
            FUiComponent component = RDesign2dManager.FrameConsole.CreateComponent(controlName);
            FRcComponent componentResource = RContent2dManager.FrameConsole.CreateComponent(controlName);
            component.LoadResource(componentResource);
            if (!component.TestParent(selectContainer)) {
               MessageBox.Show("新建控件无法放在父容器下。");
               return;
            }
            _designer.SetupComponent(component);
            selectContainer.InsertAfter(component, selectComponent);
            // 建立控件节点
            TreeNode node = new TreeNode();
            node.Checked = componentResource.OptionValid;
            node.Text = component.Format();
            node.ImageKey = componentResource.TypeName;
            node.SelectedImageKey = componentResource.TypeName;
            node.Tag = component;
            component.LinkerNode = node;
            if (parentNode == null) {
               selecteNode.Nodes.Add(node);
            } else {
               parentNode.Nodes.Insert(selecteNode.Index + 1, node);
            }
            tvwCatalog.SelectionSingle(node);
         }
         // 前面显示
         BringToFront();
      }

      //============================================================
      // <T>控件节点复制。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void tsbCatalogCopy_Click(object sender, System.EventArgs e) {
         // 获得选中节点
         TreeNode selectNode = tvwCatalog.CurrentNode;
         if(selectNode == null) {
            MessageBox.Show("未选中有效的节点。");
            return;
         }
         // 获得选中父节点
         TreeNode parentNode = selectNode.Parent;
         if(parentNode == null) {
            MessageBox.Show("不存在有效的父节点。");
            return;
         }
         // 获得选中控件
         FUiControl selectControl = selectNode.Tag as FUiControl;
         if(selectControl == null) {
            MessageBox.Show("未选中有效的控件。");
            return;
         }
         // 检查父控件
         FUiControl parentControl = selectControl.Parent as FUiControl;
         if(parentControl == null) {
            MessageBox.Show("不存在有效的父控件。");
            return;
         }
         //// 创建控件
         //FUiComponent component = RDesign2dFace.UiConsole.CreateComponent(selectControl.TypeName);
         //FXmlNode xconfig = new FXmlNode();
         //selectControl.SaveConfig(xconfig);
         //component.LoadConfig(xconfig);
         //_designer.SetupComponent(component);
         //parentControl.InsertAfter(component, selectControl);
         //// 建立控件节点
         //TreeNode node = new TreeNode();
         //node.Checked = component.OptionValid;
         //node.Text = component.Format();
         //node.ImageKey = component.TypeName;
         //node.SelectedImageKey = component.TypeName;
         //node.Tag = component;
         //component.LinkerNode = node;
         //parentNode.Nodes.Insert(selectNode.Index + 1, node);
      }

      //============================================================
      // <T>控件节点删除。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void tspCatalogDelete_Click(object sender, System.EventArgs e) {
         // 获得选中节点
         int count = tvwCatalog.SelectedNodes.Count;
         if(count == 0) {
            MessageBox.Show("未选中有效的节点。");
            return;
         }
         // 删除控件警告
         if(MessageBox.Show("是否删除选中的控件. (count=" + count + ").", "删除警告", MessageBoxButtons.OKCancel) == DialogResult.OK) {
            foreach(TreeNode node in tvwCatalog.SelectedNodes) {
               FUiControl selectControl = node.Tag as FUiControl;
               if(selectControl == null) {
                  MessageBox.Show("未选中有效的控件。");
                  return;
               }
               // 删除控件
               selectControl.RemoveFromParent();
               node.Remove();
            }
         }
      }

      //============================================================
      // <T>节点选取变更。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void tvwCatalog_TreeNodeChanged(object sender, System.EventArgs args) {
         // 获得当前节点
         TreeNode node = tvwCatalog.CurrentNode;
         if(node != null) {
            FUiComponent component = node.Tag as FUiComponent;
            // 选择当前节点
            _designer.Selection.Clear();
            int count = tvwCatalog.SelectedNodes.Count;
            FUiComponent[] components = new FUiComponent[count];
            for(int n = 0; n < count; n++ ) {
               TreeNode selectedNode = tvwCatalog.SelectedNodes[n];
               components[n] = selectedNode.Tag as FUiComponent;
            }
            // 选择组件
            SelectComponent(component, components);
         }
      }

      //============================================================
      // <T>控件目录复选处理。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void tvwCatalog_AfterCheck(object sender, TreeViewEventArgs e) {
         TreeNode node = e.Node;
         FUiComponent component = node.Tag as FUiComponent;
         component.ComponentResource.OptionValid = node.Checked;
         _designer.Paint();
      }

      //============================================================
      // <T>控件目录双击开始编辑处理。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void tvwCatalog_NodeMouseDoubleClick(object sender, TreeNodeMouseClickEventArgs e) {
         TreeNode selectNode = e.Node;
         if(selectNode == null) {
            return;
         }
         FUiComponent selectComponent = selectNode.Tag as FUiComponent;
         if(selectComponent == null) {
            return;
         }
         FRcComponent componentResource = selectComponent.ComponentResource;
         tvwCatalog.LabelEdit = true;
         tvwCatalog.SelectionSingle(selectNode);
         selectNode.Text = componentResource.Name;
         selectNode.BeginEdit();
      }

      //============================================================
      // <T>控件目录结束编辑处理。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void tvwCatalog_AfterLabelEdit(object sender, NodeLabelEditEventArgs e) {
         TreeNode selectNode = e.Node;
         if(selectNode == null) {
            return;
         }
         FUiComponent selectComponent = selectNode.Tag as FUiComponent;
         if(selectComponent == null) {
            return;
         }
         selectNode.EndEdit(true);
         if(!RString.IsEmpty(e.Label)) {
            selectComponent.ComponentResource.Name = e.Label;
         }
         selectNode.Text = selectComponent.Format();
         //pgdProperties.Refresh();
         tvwCatalog.SelectionSingle(selectNode);
         tvwCatalog.LabelEdit = false;
         e.CancelEdit = true;
      }

      //============================================================
      // <T>目录开始拖拽事件处理。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void tvwCatalog_DragEnter(object sender, DragEventArgs e) {
         e.Effect = DragDropEffects.Move;
      }

      //============================================================
      // <T>目录项目拖拽事件处理。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void tvwCatalog_ItemDrag(object sender, ItemDragEventArgs e) {
         DoDragDrop(e.Item, DragDropEffects.Move);
      }

      //============================================================
      // <T>目录控件拖拽事件处理。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void tvwCatalog_DragOver(object sender, DragEventArgs e) {
         // 获得目录树
         QTreeView treeView = sender as QTreeView;
         if (treeView == null) {
            return;
         }
         // 获取被拖动的节点
         if (!e.Data.GetDataPresent("System.Windows.Forms.TreeNode", false)) {
            return;
         }
         TreeNode sourceNode = e.Data.GetData("System.Windows.Forms.TreeNode") as TreeNode;
         if (sourceNode == null) {
            return;
         }
         // 设置目标节点为焦点
         Point point = treeView.PointToClient(new Point(e.X, e.Y));
         TreeNode targetNode = treeView.GetNodeAt(point);
         treeView.SelectionSingle(targetNode);
      }

      //============================================================
      // <T>目录完成拖拽事件处理。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void tvwCatalog_DragDrop(object sender, DragEventArgs e) {
         // 获得目录树
         QTreeView treeView = sender as QTreeView;
         if (treeView == null) {
            return;
         }
         // 获取被拖动的节点
         if (!e.Data.GetDataPresent("System.Windows.Forms.TreeNode", false)) {
            return;
         }
         TreeNode sourceNode = e.Data.GetData("System.Windows.Forms.TreeNode") as TreeNode;
         if (sourceNode == null) {
            return;
         }
         // 获取目标节点
         Point point = treeView.PointToClient(new Point(e.X, e.Y));
         TreeNode targetNode = treeView.GetNodeAt(point);
         // 判断拖动的节点与目标节点是否是同一个,同一个不予处理
         if (sourceNode != targetNode) {
            // 检查是否跨层拖拽
            if (sourceNode.Parent != targetNode.Parent) {
               MessageBox.Show("禁止跨控件容器拖拽。");
               return;
            }
            // 插入目标结点前
            treeView.BeginUpdate();
            TreeNode parentNode = targetNode.Parent;
            if (parentNode != null) {
               // 移动控件
               FUiComponent parentComponent = parentNode.Tag as FUiComponent;
               FUiComponent sourceComponent = sourceNode.Tag as FUiComponent;
               FUiComponent targetComponent = targetNode.Tag as FUiComponent;
               if ((parentComponent != null) || (sourceComponent != null) || (targetComponent != null)) {
                  // 移动控件
                  parentComponent.InsertBefore(sourceComponent, targetComponent);
                  // 移除来源节点，放入新位置
                  sourceNode.Remove();
                  parentNode.Nodes.Insert(targetNode.Index, sourceNode);
               }
            }
            treeView.SelectionSingle(sourceNode);
            treeView.EndUpdate();
         }
      }

      //============================================================
      // <T>显示控件属性。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void tspPropertyShow_Click(object sender, System.EventArgs e) {
         tsbProperty.Checked = true;
         spcProperty.Panel2Collapsed = false;
      }

      //============================================================
      // <T>缩放点击处理。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void tsbZoom_Click(object sender, System.EventArgs e) {
         ToolStripMenuItem item = sender as ToolStripMenuItem;
         if (item != null) {
            // 设置设计缩放
            float scale = RFloat.Parse((string)item.Tag);
            _designer.DesignScale = scale;
            // 刷新大小
            RefreshSize();
         }
      }

      //============================================================
      // <T>绘制处理。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void pnlCanvas_Paint(object sender, PaintEventArgs e) {
         _designer.Paint();
      }

      //============================================================
      // <T>改变画板大小。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void pnlCanvasBox_Resize(object sender, System.EventArgs e) {
         RefreshSize();
      }

      //============================================================
      // <T>画板鼠标落下处理。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void pnlCanvas_MouseDown(object sender, MouseEventArgs e) {
         bool leftKey = (e.Button == MouseButtons.Left);
         // 设计开始
         bool ctrlPressed = RKeyboard.IsCtrlPressed();
         SUiTestArgs testArgs = new SUiTestArgs();
         _designer.DesignBegin(e.X, e.Y, leftKey, ctrlPressed, testArgs);
         // 选择组件
         SelectComponent(_designer.FocusControl, _designer.Selection.ToArray());
         // 鼠标左键处理
         if (e.Button == MouseButtons.Left) {
            tvwCatalog.SelectionClear();
            if(_designer.FocusControl != null) {
               foreach(FUiControl control in _designer.Selection) {
                  TreeNode node = control.LinkerNode as TreeNode;
                  tvwCatalog.SelectionPush(node);
               }
               TreeNode focusNode = _designer.FocusControl.LinkerNode as TreeNode;
               focusNode.EnsureVisible();
            }
         }
         // 鼠标右键处理
         if (e.Button == MouseButtons.Right) {
            // 建立控件列表
            int testCount = testArgs.Controls.Count;
            int menuCount = _selectControlMenuItems.Length;
            for (int n = 0; n < menuCount; n++) {
               ToolStripMenuItem menuItem = _selectControlMenuItems[n];
               if (n < testCount) {
                  FUiControl testControl = testArgs.Controls[testCount - n - 1];
                  string typeName = testControl.ComponentResource.TypeName;
                  Image typeImage = imlMain.Images[typeName];
                  menuItem.Image = typeImage;
                  menuItem.Text = testControl.Format();
                  menuItem.Tag = testControl;
                  menuItem.Visible = true;
               } else {
                  menuItem.Visible = false;
               }
            }
            // 显示弹出菜单
            Point screenPoint = pnlCanvas.PointToScreen(new Point(e.X, e.Y));
            cmsCanvas.Show(screenPoint);
         }
      }

      //============================================================
      // <T>关闭处理。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void pnlCanvas_MouseMove(object sender, MouseEventArgs e) {
         // 设计更新
         _designer.DesignUpdate(e.X, e.Y);
         // pgdProperties.Refresh();
         // 绘制界面
         _designer.Paint();
      }

      //============================================================
      // <T>关闭处理。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void pnlCanvas_MouseUp(object sender, MouseEventArgs e) {
         // 设计结束
         _designer.DesignEnd(e.X, e.Y);
         // 刷新历史
         RefreshHistory();
      }

      //============================================================
      // <T>层选中变更。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void lvwLayers_ItemChecked(object sender, ItemCheckedEventArgs e) {
         bool check = e.Item.Checked;
         object item = e.Item.Tag;
         if (item is FUiFrame) {
            FUiFrame container = item as FUiFrame;
            if (container.DesignVisible != check) {
               container.DesignVisible = check;
               _designer.Paint();
            }
         } else if (item is FUiControlLayer) {
            FUiControlLayer layer = item as FUiControlLayer;
            if (layer.OptionVisible != check) {
               layer.OptionVisible = check;
               _designer.Paint();
            }
         }
      }

      //============================================================
      // <T>自动调整控件。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void tsbDesignAdjust_Click(object sender, System.EventArgs e) {
         FUiControl control = _designer.FocusControl;
         if (control != null) {
            control.Adjust();
            _designer.Paint();
         }
      }

      //============================================================
      // <T>调整全部控件。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void tsbDesignAdjustLocation_Click(object sender, System.EventArgs e) {
         FUiControl control = _designer.FocusControl;
         if(control != null) {
            //if (control.GroundResource != null) {
            //   control.GroundResource.Location.Reset();
            //   _designer.Paint();
            //}
         }
      }

      //============================================================
      // <T>设计对齐控件。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void tsbDesignAlign_Click(object sender, System.EventArgs e) {
         // 获得对齐类型
         string tag = null;
         if (sender is ToolStripButton) {
            ToolStripButton button = sender as ToolStripButton;
            tag = (string)button.Tag;
         } else if (sender is ToolStripMenuItem) {
            ToolStripMenuItem item = sender as ToolStripMenuItem;
            tag = (string)item.Tag;
         }
         // 对齐处理
         if (!RString.IsEmpty(tag)) {
            int alignCd = RInt.Parse(tag);
            if (alignCd > 0) {
               int step = 0;
               if ((alignCd == EUiDesignAlign.HorizontalAvgSize) || (alignCd == EUiDesignAlign.VerticalAvgSize)) {
                  string inputValue = QInputForm.Popup("输入间隔尺寸。", "0");
                  if (inputValue == null) {
                     return;
                  }
                  step = RInt.Parse(inputValue);
               }
               _designer.Selection.DesignAlign(alignCd, step);
               _designer.Paint();
            }
         }
      }

      //============================================================
      // <T>选中控件处理。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      private void tsmiSelectControl_Click(object sender, System.EventArgs e) {
         ToolStripMenuItem menuItem = sender as ToolStripMenuItem;
         if (menuItem == null) {
            return;
         }
         FUiControl control = menuItem.Tag as FUiControl;
         if (control == null) {
            return;
         }
         // 选择组件
         _designer.FocusControl = control;
         SelectComponent(control);
      }

      //============================================================
      // <T>属性变更事件处理。</T>
      //
      // @param sender 发送者
      // @param resource 资源对象
      // @param name 名称
      //============================================================
      private void uppProperty_PropertyChanged(object sender, object resource, string name) {
         if ((name == "name") || (name == "label")) {
            FRcObject objectResource = resource as FRcObject;
            FUiObject linker = objectResource.LinkerNode as FUiObject;
            if (linker != null) {
               linker.LinkerNode.Text = objectResource.Format();
            }
         }
         _designer.Paint();
      }

      //============================================================
      // <T>控件属性进入处理。</T>
      //============================================================
      private void pgdProperties_Enter(object sender, System.EventArgs e) {
         _editing = true;
      }

      //============================================================
      // <T>控件属性离开处理。</T>
      //============================================================
      private void pgdProperties_Leave(object sender, System.EventArgs e) {
         _editing = false;
      }

      //============================================================
      // <T>控件属性改变修改控件显示。</T>
      //============================================================
      private void pgdProperties_PropertyValueChanged(object s, PropertyValueChangedEventArgs e) {
         //bool changed = false;
         //// 单个控件
         //FUiComponent component = pgdProperties.SelectedObject as FUiComponent;
         //if(component != null) {
         //   TreeNode node = component.LinkerNode as TreeNode;
         //   if(node != null) {
         //      node.Checked = component.ComponentResource.OptionValid;
         //      node.Text = component.Format();
         //      changed = true;
         //   }
         //}
         //// 多个控件
         //foreach(object value in pgdProperties.SelectedObjects){
         //   FUiComponent item = value as FUiComponent;
         //   if(item != null) {
         //      TreeNode node = item.LinkerNode as TreeNode;
         //      if(node != null) {
         //         node.Checked = item.ComponentResource.OptionValid;
         //         node.Text = item.Format();
         //         changed = true;
         //      }
         //   }
         //}
         //// 重新绘制
         //if(changed) {
         //   _designer.Paint();
         //}
      }

      //============================================================
      // <T>自动调整控件。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      protected int _historyCount = 0;

      private void RefreshHistory() {
         FObjects<FUiFormHistoryStep> steps = _designer.History.Steps;
         int count = steps.Count;
         if (count != _historyCount) {
            int n = 0;
            lvwHistory.BeginUpdate();
            lvwHistory.Items.Clear();
            foreach (FUiFormHistoryStep step in steps) {
               n++;
               ListViewItem item = new ListViewItem();
               item.Text = "Step " + n;
               item.Tag = step;
               lvwHistory.Items.Add(item);
            }
            lvwHistory.EndUpdate();
            _historyCount = count;
         }
      }

      private void lvwHistory_DoubleClick(object sender, System.EventArgs e) {
         if (lvwHistory.SelectedItems.Count == 1) {
            ListViewItem item = lvwHistory.SelectedItems[0];
            FUiFormHistoryStep step = item.Tag as FUiFormHistoryStep;
            if (step != null) {
               _designer.HistoryLoad(step);
            }
         }
      }

      private void tsbDesignPropertySave_Click(object sender, System.EventArgs e) {
         uppProperty.SaveResource();
      }
   }
}
