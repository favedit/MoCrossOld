using System;
using System.Drawing;
using System.Windows.Forms;
using MO.Common.Geom;
using MO.Common.Lang;
using MO.Content2d;
using MO.Content2d.Resource.Picture;
using MO.Direct2d.Device;
using MoMobile.Map.Common;
using MoMobile.Map.Core;
using MoMobile.Map.Designer;
using MoMobile.Template.Enemy;

namespace MoMobile.Map.Forms
{
   //============================================================
   // <T>地图设计窗体。</T>
   //============================================================
   public partial class QMapDesignForm : Form
   {
      // 实例化对象
      protected static QMapDesignForm _instance;

      //
      protected FDsMap _map = new FDsMap();

      // 设备
      protected FDxDevice2d _device;

      // 表单设计器
      protected FUiFormDesigner _designer = new FUiFormDesigner();

      // 当前层
      protected FDsMapLayer _mapLayer = new FDsMapLayer();

      // 格子定义
      protected FMbMapCell _mapCell = new FMbMapCell();

      // 瓦片定义
      protected FMbMapTile _mapTile = new FMbMapTile();

      // 瓦片组定义
      protected FMbMapTileCatalog _mapTileCatalog = new FMbMapTileCatalog();

      // 当前激活层
      protected ListViewItem _selectedListView;

      //============================================================
      // <T>构造界面设计表单。</T>
      //============================================================
      public QMapDesignForm() {
         InitializeComponent();
         // 设置设备
         _device = new FDxDevice2d();
         _device.Size.Set(pnlCanvas.Width, pnlCanvas.Height);
         _device.Handle = pnlCanvas.Handle;
         _device.Setup();
         // 设置环境
         _designer.Device = _device;
         _designer.DesignPanel = pnlCanvas;

      }

      //============================================================
      // <T>实例化对象。</T>
      //============================================================
      public static QMapDesignForm Instance{
         get{
            if(null == _instance){
               _instance = new QMapDesignForm();
            }
            return _instance;
         }
      }

      //============================================================
      // <T>记载地图。</T>
      //============================================================
      public void LoadMap(FMbMap map) {
         _map.Resource = map;
         _designer.Map = _map.Resource;
         _designer.Paint();
         InnerBuildLayer();
         InnerBuildTileCatalog();
         InnerBuildEnemy();
         InnerBuildBirth();
         // 初始化层
         if (0 < _map.Resource.Layers.Count) {
            _mapLayer.Resource = _map.Resource.Layers[0];
            lvwLayers.Items[0].BackColor = Color.BurlyWood;
            _selectedListView = lvwLayers.Items[0];
            lvwLayers.Items[0].Selected = false;
         }
         RefreshSize();
      }

      //============================================================
      // <T>加载出生点列表。</T>
      //============================================================
      public void InnerBuildBirth() {
         int index = 0;
         // 清空
         tvwCatalog.Nodes.Clear();
         foreach (FMbMapBirth birth in _map.Resource.Births) {
            SIntPoint2 location = birth.Location;
            TreeNode birthNode = new TreeNode();
            birthNode.Tag = birth;
            birthNode.Text = "出生点" + (++index) + ":" + location.ToString();
            foreach (FMbMapBirthEnemy enemy in birth.BirthEnemys) {
               TreeNode enemyNode = new TreeNode();
               enemyNode.Tag = enemy;
               enemyNode.Text = "模版编号：" + enemy.TemplateId;
               birthNode.Nodes.Add(enemyNode);
            }
            tvwCatalog.Nodes.Add(birthNode);
         }
         tvwCatalog.ExpandAll();
      }

      //============================================================
      // <T>加载层列表。</T>
      //
      // @param form 表单
      //============================================================
      protected void InnerBuildLayer() {
         // 建立层列表
         int layerIndex = 0;
         lvwLayers.Items.Clear();
         foreach (FMbMapLayer layer in _map.Resource.Layers) {
            if (0 == layer.CellSize.Width || 0 == layer.CellSize.Height) {
               continue;
            }
            ListViewItem designLayer = new ListViewItem((++layerIndex).ToString());
            designLayer.Checked = true;
            designLayer.SubItems.Add("设计层" + layerIndex);
            designLayer.SubItems.Add(layer.TypeCd.ToString());
            designLayer.SubItems.Add(layer.WrapCd.ToString());
            designLayer.SubItems.Add(layer.ScrollCd.ToString());
            designLayer.SubItems.Add(layer.ScrollSpeed.ToString());
            designLayer.SubItems.Add(layer.CellCount.Width + "*" + layer.CellCount.Height);
            designLayer.SubItems.Add(layer.CellSize.Width + "*" + layer.CellSize.Height);
            designLayer.Tag = layer;
            lvwLayers.Items.Add(designLayer);
         }
         if (_map.Resource.Layers.Count > 0) {
            lvwLayers.Items[0].Selected = true;
         }
      }

      //============================================================
      // <T>加载瓦片组列表。</T>
      //============================================================
      protected void InnerBuildTileCatalog() {
         foreach (FMbMapTileCatalog tileCatalog in RMobileManager.MapTileConsole.MapTileCatalogs) {
            cbxTileCatalog.Items.Add(tileCatalog.FullName);
         }
         cbxTileCatalog.SelectedItem = cbxTileCatalog.Items[0];
      }

      //============================================================
      // <T>加载敌机列表。</T>
      //============================================================
      protected void InnerBuildEnemy() {
         // 找到敌机控制台
         FMbEnemyConsole enemyConsole = RMobileManager.TemplateConsole.EnemyConsole;
         int index = 0;
         imageEnemyList.Images.Clear();
         lvwFly.BeginUpdate();
         lvwFly.Items.Clear();
         foreach (FMbTplEnemy enemy in enemyConsole.Enemys) {
            int resourceRid = enemy.ResourceRid;
            FRsResourcePicture resource = RContent2dManager.ResourceConsole.FindOpen(resourceRid) as FRsResourcePicture;
            Bitmap resourceMap = resource.Bitmap.Native;
            imageEnemyList.Images.Add(resourceMap);
            ListViewItem listViewItem = new ListViewItem();
            listViewItem.Text = resourceRid.ToString();
            listViewItem.ImageIndex = index;
            listViewItem.Tag = enemy;
            lvwFly.Items.Add(listViewItem);
            index++;
         }
         lvwFly.EndUpdate();
      }

      //============================================================
      // <T>加载图片。</T>
      //============================================================
      protected void InnerBuildTile(int id,int index) {
         ListViewItem listViewItem = new ListViewItem();
         listViewItem.Text = id.ToString();
         listViewItem.ImageIndex = index;
         listViewItem.Tag = id;
         lvwTile.Items.Add(listViewItem);
      }

      //============================================================
      // <T>刷新大小处理。</T>
      //============================================================
      private void RefreshSize() {
         // 设置画板
         SIntSize2 size = _designer.Map.Size;
         int width = (int)size.Width;
         if (width < pnlCanvasBox.Width) {
            width = pnlCanvasBox.Width;
         }
         int height = (int)(size.Height);
         if (height < pnlCanvasBox.Height) {
            height = pnlCanvasBox.Height;
         }
         pnlCanvas.Width = width - heightScrollBar.Width;
         pnlCanvas.Height = height - widthScrollBar.Height;
         // 改变设备大小
         _device.Resize(width, height);
         // 改变大小
         _designer.Resize(width, height);
         _designer.Refresh();
         // 设置滚动条
         widthScrollBar.Location = new Point(0, height - widthScrollBar.Height);
         widthScrollBar.Width = width - widthScrollBar.Height;
         
         heightScrollBar.Location = new Point(width - heightScrollBar.Width, 0);
         heightScrollBar.Height = height - heightScrollBar.Width;

         widthScrollBar.Maximum = _mapLayer.Resource.CellSize.Width * _mapLayer.Resource.CellCount.Width - pnlCanvas.Width;
         heightScrollBar.Maximum = _mapLayer.Resource.CellSize.Height * _mapLayer.Resource.CellCount.Height - pnlCanvas.Height;

         _designer.Location = new SIntPoint2(widthScrollBar.Value, heightScrollBar.Value);
         _designer.Paint();
      }

      //============================================================
      // <T>绘制处理。</T>
      //============================================================
      private void pnlCanvas_Paint(object sender, PaintEventArgs e) {
         _designer.Paint();
      }

      //============================================================
      // <T>改变画板大小。</T>
      //============================================================
      private void pnlCanvasBox_Resize(object sender, EventArgs e) {
         RefreshSize();
      }

      //============================================================
      // <T>保存时间处理。</T>
      //============================================================
      private void tsbStore_Click(object sender, EventArgs e) {
         _map.SaveFile();
         MessageBox.Show("保存成功！");
      }

      //============================================================
      // <T>鼠标落下事件处理。</T>
      //============================================================
      private void pnlCanvas_MouseDown(object sender, MouseEventArgs e) {
         if (e.Button == MouseButtons.Right) {
            FMbMapCell cell = _mapLayer.Find(e.X+_designer.Location.X, e.Y+_designer.Location.Y);
            if (null == cell) {
               return;
            }
            cell.ResourceId = 0;
            _designer.Paint();
         }
         // 绘制图片
         if (e.Button == MouseButtons.Left) {
            if (0 == tbcProperty.SelectedIndex) {
               // 获取选中图片资源编号
               if (1 != lvwTile.SelectedItems.Count) {
                  return;
               }
               ListViewItem lv = lvwTile.SelectedItems[0];
               // 获取格子
               FMbMapCell cell = _mapLayer.Find(e.X + _designer.Location.X, e.Y + _designer.Location.Y);
               cell.ResourceId = RInt.Parse(lv.Tag);
            } else {
               if (1 != lvwFly.SelectedItems.Count) {
                  return;
               }
               ListViewItem lv = lvwFly.SelectedItems[0];
               // 设置出生点
               FMbMapBirth birth = new FMbMapBirth();
               birth.Location = new SIntPoint2(e.X+_designer.Location.X,e.Y+_designer.Location.Y);
               // 设置敌机信息
               FMbMapBirthEnemy enemy = new FMbMapBirthEnemy();
               enemy.TemplateId = (lv.Tag as FMbTplEnemy).Id;
               birth.BirthEnemys.Add(enemy);
               _map.Resource.Births.Add(birth);
            }
            _designer.Paint();
         }
      }

      //============================================================
      // <T>下拉框选择改变事件处理。</T>
      //============================================================
      private void cbxTileCatalog_SelectedIndexChanged(object sender, EventArgs e) {
         // 获取组对应编号
         string text = cbxTileCatalog.Text;
         int catalogId = RInt.Parse(text.Substring(0, 1));
         // 找到对应组
         FMbMapTileCatalog tileCatalog = RMobileManager.MapTileConsole.FindMapTileCatalog(catalogId);
         // 填充瓦片集合
         imageTileList.Images.Clear();
         
         int imageIndex = 0;
         // 清空
         lvwTile.BeginUpdate();
         lvwTile.Items.Clear();
         foreach (FMbMapTile mapTile in tileCatalog.MapTiles) {
            Bitmap image = mapTile.Resource;
            imageTileList.Images.Add(image);
            InnerBuildTile(mapTile.Id, imageIndex);
            imageIndex++;
         }
         lvwTile.EndUpdate();
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
         FMbMapLayer layer = item as FMbMapLayer;
         if (layer.OptionValid != check) {
            layer.OptionValid = check;
            _designer.Paint();
         }
      }

      //============================================================
      // <T>鼠标移动事件处理。</T>
      //============================================================
      private void pnlCanvas_MouseMove(object sender, MouseEventArgs e) {
         // 计算当前坐标
         tspLabTxtLocation.Text = (e.X + _designer.Location.X) + "，" + (e.Y + _designer.Location.Y);
         // 计算当前格子索引
         int indexX = (e.X + _designer.Location.X) / (_mapLayer.Resource.CellSize.Width);
         int indexY = (e.Y + _designer.Location.Y) / (_mapLayer.Resource.CellSize.Height);
         tspTxtIndex.Text = indexX + "," + indexY;

         if (e.Button == MouseButtons.Left) {
            if (1 == lvwTile.SelectedItems.Count) {
               ListViewItem lv = lvwTile.SelectedItems[0];
               // 获取格子
               FMbMapCell cell = _mapLayer.Find(e.X + _designer.Location.X, e.Y + _designer.Location.Y);
               cell.ResourceId = RInt.Parse(lv.Tag);
               _designer.Paint();
            }
         }
      }

      //============================================================
      // <T>横向滑块移动事件处理。</T>
      //============================================================
      private void widthScrollBar_Scroll(object sender, ScrollEventArgs e) {
         _designer.Location = new SIntPoint2(widthScrollBar.Value, heightScrollBar.Value);
         _designer.Paint();
      }

      //============================================================
      // <T>竖向滑块移动事件处理。</T>
      //============================================================
      private void heightScrollBar_Scroll(object sender, ScrollEventArgs e) {
         _designer.Location = new SIntPoint2(widthScrollBar.Value, heightScrollBar.Value);
         _designer.Paint();
      }

      //============================================================
      // <T>关闭按钮事件处理。</T>
      //============================================================
      private void tsbClose_Click(object sender, EventArgs e) {
         Hide();
      }

      //============================================================
      // <T>窗体关闭处理。</T>
      //============================================================
      private void QMapDesignForm_FormClosed(object sender, FormClosedEventArgs e) {
         _designer.Dispose();
      }

      //============================================================
      // <T>窗体关闭时处理。</T>
      //============================================================
      private void QMapDesignForm_FormClosing(object sender, FormClosingEventArgs e) {
         e.Cancel = true;
         Hide();
      }

      private void tsbBackColor_Click(object sender, EventArgs e) {
         //dlgColor.Color = _designer.DesignBackColor;
         //if (dlgColor.ShowDialog() == DialogResult.OK) {
         //   _designer.DesignBack = true;
         //   _designer.DesignBackColor = dlgColor.Color;
         //}
      }

      //============================================================
      // <T>删除按钮事件处理。</T>
      //============================================================
      private void tsmDelete_Click(object sender, EventArgs e) {
         QMapLayerDesignForm.Instance.Layer = _mapLayer.Resource;
         QMapLayerDesignForm.Instance.LoadLayer();
         QMapLayerDesignForm.Instance.ShowDialog();
         _mapLayer.Resource = QMapLayerDesignForm.Instance.Layer;
      }

      //============================================================
      // <T>激活按钮处理。</T>
      //============================================================
      private void tsmActive_Click(object sender, EventArgs e) {
         _selectedListView.BackColor = Color.White;
         ListViewItem lvLayer = lvwLayers.SelectedItems[0];
         _mapLayer.Resource = lvLayer.Tag as FMbMapLayer;
         lvLayer.BackColor = Color.BurlyWood;
         _selectedListView = lvLayer;
         lvLayer.Selected = false;
         RefreshSize();
      }
   }
}
