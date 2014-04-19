using MO.Common.Lang;
using MO.Content2d.Common;
using MO.Content2d.Resource.Animation;
using MO.Content2d.Resource.Common;
using MO.Content2d.Resource.Picture;
using System;
using System.Windows.Forms;

namespace MO.Design2d.Frame.Forms
{
   //============================================================
   // <T>界面资源拾取器。</T>
   //============================================================
   public partial class QUiResourcePicker : UserControl
   {
      // 资源双击事件
      public event EventHandler ResourceDoubleClick;

      //============================================================
      // <T>构造界面资源拾取器。</T>
      //============================================================
      public QUiResourcePicker() {
         InitializeComponent();
         // 初始化控件
         qrsClipViewer.Dock = DockStyle.Fill;
         qrsClipViewer.Visible = false;
         qrsPictureViewer.Dock = DockStyle.Fill;
         qrsPictureViewer.Visible = false;
      }

      //============================================================
      // <T>获得或设置显示类型。</T>
      //============================================================
      public int ShowCd {
         get { return qrsResourceList.ShowCd; }
         set { qrsResourceList.ShowCd = value; }
      }

      //============================================================
      public FRsResource SelectedResource {
         get { return qrsResourceList.SelectedResource; }
      }

      //============================================================
      // <T>过滤数据。</T>
      //============================================================
      protected void DoFilter() {
         qrsResourceList.FilterValue = txtFilterValue.Text;
         qrsResourceList.DoFilter();
         // 显示结果
         int resultCount = qrsResourceList.ResourceCount;
         lblResult.Text = "共有" + resultCount + "个资源。";
      }

      //============================================================
      // <T>清除过滤数据。</T>
      //============================================================
      protected void DoFilterClear() {
         if(qrsResourceList.FilterValue != "") {
            txtFilterValue.Text = "";
            DoFilter();
         }
      }

      //============================================================
      public void LoadFolder(FObjects<FRsResourceFolder> folder) {
         qrsResourceList.LoadFolder(folder);
      }

      //============================================================
      // <T>选择资源。</T>
      //
      // @param resource 资源
      //============================================================
      public void SelectResource(FRsResource resource) {
         // 打开资源
         resource.Open();
         // 显示资源
         qrsClipViewer.Visible = false;
         qrsPictureViewer.Visible = false;
         // 显示动画
         if(resource is FRsResourceAnimation) {
            FRsResourceAnimation animation = resource as FRsResourceAnimation;
            FRsResourceClip clip = animation.FristClip;
            qrsClipViewer.LoadClip(clip);
            qrsClipViewer.DoPlay();
            qrsClipViewer.Visible = true;
         }
         // 显示动画
         if (resource is FRsResourcePicture) {
            FRsResourcePicture picture = resource as FRsResourcePicture;
            qrsPictureViewer.LoadPicture(picture);
            qrsPictureViewer.Visible = true;
         }
      }
      
      //============================================================
      // <T>通过资源编号选择资源。</T>
      //
      // @param id 资源编号
      //============================================================
      public void SelectResourceById(int id) {
         // 清空资源
         txtFilterValue.Text = id.ToString();
         DoFilter();
         // 选取资源
         FRsResource resource = qrsResourceList.SelectResourceById(id);
         if(resource != null) {
            SelectResource(resource);
         }
      }

      //============================================================
      // <T>通过资源代码选择资源。</T>
      //
      // @param code 资源代码
      //============================================================
      public void SelectResourceByCode(string code) {
         // 清空资源
         txtFilterValue.Text = code;
         DoFilter();
         // 选取资源
         //FRsResource resource = qrsResourceList.SelectResourceByCode(code);
         //if(resource != null) {
         //   SelectResource(resource);
         //}
      }

      //============================================================
      private void txtId_KeyDown(object sender, KeyEventArgs e) {
         if(e.KeyCode == Keys.Enter) {
            DoFilter();
         }
      }

      //============================================================
      private void txtLabel_KeyDown(object sender, KeyEventArgs e) {
         if(e.KeyCode == Keys.Enter) {
            DoFilter();
         }
      }

      //============================================================
      // <T>搜索数据事件。</T>
      //============================================================
      private void btnSearch_Click(object sender, EventArgs e) {
         if(qrsResourceList.FilterValue != txtFilterValue.Text) {
            DoFilter();
         }
      }

      //============================================================
      // <T>搜索清空事件。</T>
      //============================================================
      private void btnClear_Click(object sender, EventArgs e) {
         DoFilterClear();
      }

      //============================================================
      // <T>资源点击事件。</T>
      //============================================================
      private void qrsResourceList_ResourceClick(object sender, EventArgs e) {
         FRsResource resource = qrsResourceList.SelectedResource;
         if(null != resource) {
            // 打开资源
            resource.Open();
            // 显示资源
            SuspendLayout();
            qrsClipViewer.Visible = false;
            qrsPictureViewer.Visible = false;
            // 显示动画
            if (resource is FRsResourceAnimation) {
               FRsResourceAnimation animation = resource as FRsResourceAnimation;
               FRsResourceClip clip = animation.FristClip;
               qrsClipViewer.LoadClip(clip);
               qrsClipViewer.DoPlay();
               qrsClipViewer.Visible = true;
            }
            // 显示图片
            if (resource is FRsResourcePicture) {
               FRsResourcePicture picture = resource as FRsResourcePicture;
               qrsPictureViewer.LoadPicture(picture);
               qrsPictureViewer.Visible = true;
               labInfo.Text = picture.Format();
            }
            ResumeLayout();
         }
      }

      //============================================================
      // <T>资源双击事件。</T>
      //============================================================
      private void qrsResourceList_ResourceDoubleClick(object sender, EventArgs e) {
         if(ResourceDoubleClick != null) {
            ResourceDoubleClick(sender, e);
         }
      }
   }
}
