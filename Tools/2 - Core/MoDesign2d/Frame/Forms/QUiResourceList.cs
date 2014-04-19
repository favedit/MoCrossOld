using MO.Common.Collection;
using MO.Common.Lang;
using MO.Content2d.Common;
using MO.Content2d.Resource.Common;
using System;
using System.Drawing;
using System.Windows.Forms;

namespace MO.Design2d.Frame.Forms
{
   //============================================================
   // <T>界面资源集合。</T>
   //============================================================
   public partial class QUiResourceList : UserControl
   {
      // 显示类型
      protected int _showCd;

      // 过滤内容
      protected string _filterValue;
      
      // 资源集合
      protected FDictionary<FRsResource> _resources = new FDictionary<FRsResource>();

      // 资源点击事件
      public event EventHandler ResourceClick;

      // 资源双击事件
      public event EventHandler ResourceDoubleClick;

      //============================================================
      // <T>构造界面资源集合。</T>
      //============================================================
      public QUiResourceList() {
         InitializeComponent();
      }

      //============================================================
      // <T>获得或设置显示类型。</T>
      //============================================================
      public int ShowCd {
         get { return _showCd; }
         set { _showCd = value; }
      }

      //============================================================
      // <T>获得或设置过滤内容。</T>
      //============================================================
      public string FilterValue {
         get { return _filterValue; }
         set { _filterValue = value; }
      }

      //============================================================
      // <T>过滤数据。</T>
      //============================================================
      public void DoFilter() {
         bool filterValue = !RString.IsBlank(_filterValue);
         // 加载子目录
         lvResources.BeginUpdate();
         lvResources.Items.Clear();
         //foreach(INamePair<FRsResource> pair in _resources) {
         //   FRsResource resource = pair.Value;
         //   //SDsResourceTag tag = resource.Tag as SDsResourceTag;
         //   //if(tag.item == null) {
         //   //   continue;
         //   //}
         //   //ListViewItem qItem = tag.item as ListViewItem;
         //   //// 是否含有内容
         //   //if(filterValue) {
         //   //   if(!tag.key.Contains(_filterValue)) {
         //   //      continue;
         //   //   }
         //   //}
         //   // 增加内容
         //   lvResources.Items.Add(qItem);
         //}
         lvResources.EndUpdate();
      }

      //============================================================
      // <T>获得选择资源。</T>
      //============================================================
      public FRsResource SelectedResource {
         get {
            FRsResource resource = null;
            if(1 == lvResources.SelectedItems.Count) {
               ListViewItem qItem = lvResources.SelectedItems[0];
               resource = qItem.Tag as FRsResource;
            }
            return resource;
         }
      }

      //============================================================
      // <T>获得资源总数。</T>
      //============================================================
      public int ResourceCount {
         get { return lvResources.Items.Count; }
      }

      //============================================================
      // <T>加载子目录。</T>
      //============================================================
      protected bool LoadSubFolder(FDictionary<FRsResource> resources, FRsResourceFolder folder) {
         // 判断是否为空
         if(null == folder) {
            return false;
         }
         // 加载子目录
         foreach(FRsResourceFolder subFolder in folder.Folders) {
            if(null != subFolder) {
               LoadSubFolder(resources, subFolder as FRsResourceFolder);
            }
         }
         foreach (FRsResource cfgObject in folder.Resources) {
            if(null != cfgObject) {
               //cfgObject.Tag = new SDsResourceTag();
               //resources.Set(cfgObject.Code, cfgObject);
            }
         }
         return true;
      }

      //============================================================
      // <T>加载目录。</T>
      //============================================================
      public bool LoadFolder(FObjects<FRsResourceFolder> folder) {
         // 判断是否为空
         if(null == folder) {
            return false;
         }
         string directory = folder[0].Directory;
         // 获得所有组件
         _resources.Clear();
         LoadSubFolder(_resources, folder[0]);
         // 加载子目录
         lvResources.BeginUpdate();
         lvResources.Items.Clear();
         foreach (INamePair<FRsResource> Pair in _resources) {
            FRsResource resource = Pair.Value;
            //if(_showCd == ERsResource.Picture) {
            //   // 获得资源信息
            //   string typeName = resource.TypeName;
            //   string folderDirectory = resource.Directory;
            //   if(folderDirectory.StartsWith(directory)) {
            //      folderDirectory = folderDirectory.Substring(directory.Length);
            //   }
            //   int id = RInt.Parse(resource.Code);
            //   string label = resource.Label;
            //   bool valid = resource.IsValid;
            //   // 创建列表项目
            //   ListViewItem qItem = new ListViewItem();
            //   qItem.ImageKey = typeName;
            //   qItem.Text = " " + id;
            //   if(valid) {
            //      qItem.ForeColor = Color.Black;
            //      qItem.SubItems.Add("O");
            //   } else {
            //      qItem.ForeColor = Color.Gray;
            //      qItem.SubItems.Add("");
            //   }
            //   qItem.SubItems.Add(label);
            //   qItem.Tag = resource;
            //   lvResources.Items.Add(qItem);
            //   // 设置附加内容
            //   //SDsResourceTag tag = resource.Tag as SDsResourceTag;
            //   //tag.item = qItem;
            //   //tag.key = resource.Code + " " + resource.Label;
            //} else if(_showCd == ERsResource.Animation) {
            //   // 获得资源信息
            //   string typeName = resource.TypeName;
            //   string folderDirectory = resource.Directory;
            //   if(folderDirectory.StartsWith(directory)) {
            //      folderDirectory = folderDirectory.Substring(directory.Length);
            //   }
            //   int id = RInt.Parse(resource.Code);
            //   string label = resource.Label;
            //   bool valid = resource.IsValid;
            //   // 创建列表项目
            //   ListViewItem qItem = new ListViewItem();
            //   qItem.ImageKey = typeName;
            //   qItem.Text = " " + id;
            //   if(valid) {
            //      qItem.ForeColor = Color.Black;
            //      qItem.SubItems.Add("O");
            //   } else {
            //      qItem.ForeColor = Color.Gray;
            //      qItem.SubItems.Add("");
            //   }
            //   qItem.SubItems.Add(label);
            //   qItem.Tag = resource;
            //   lvResources.Items.Add(qItem);
            //   // 设置附加内容
            //   //SRsResourceTag tag = resource.Tag as SRsResourceTag;
            //   //tag.item = qItem;
            //   //tag.key = resource.Id + " " + resource.Code + " " + resource.Label;
            //}
         }
         lvResources.EndUpdate();
         return true;
      }

      //============================================================
      // <T>根据资源编号获得资源。</T>
      //
      // @param resourceId 资源编号
      // @return 资源
      //============================================================
      public FRsResource GetResourceById(int resourceId) {
         foreach (FRsResource resource in _resources) {
            if(resource.Code == resourceId) {
               return resource;
            }
         }
         return null;
      }

      //============================================================
      // <T>通过编号选择资源。</T>
      //
      // @param id 编号
      // @return 资源
      //============================================================
      public FRsResource SelectResourceById(int id) {
         foreach(ListViewItem item in lvResources.Items) {
            FRsResource resource = item.Tag as FRsResource;
            if(resource.Code == id) {
               item.Selected = true;
               return resource;
            }
         }
         return null;
      }

      //============================================================
      // <T>资源点击事件。</T>
      //============================================================
      private void lvResources_SelectedIndexChanged(object sender, EventArgs e) {
         if(ResourceClick != null) {
            ResourceClick(sender, e);
         }
      }

      //============================================================
      // <T>资源双击事件。</T>
      //============================================================
      private void lvResources_DoubleClick(object sender, EventArgs e) {
         if(ResourceDoubleClick != null) {
            ResourceDoubleClick(sender, e);
         }
      }
   }
}
