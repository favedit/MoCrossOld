using MO.Common.Lang;
using MO.Content2d.Common;
using MO.Content2d.Resource.Common;
using System;
using System.Windows.Forms;

namespace MO.Design2d.Frame.Forms
{
   //============================================================
   // <T>资源表单。</T>
   //============================================================
   public partial class QUiResourcePickerForm : Form
   {
      // 资源实例
      protected static QUiResourcePickerForm _instance;

      //============================================================
      // <T>获得资源实例。</T>
      //============================================================
      public static QUiResourcePickerForm Instance() {
         if (null == _instance) {
            // 创建表单
            _instance = new QUiResourcePickerForm();
            // 加载资源目录
            //_instance.ResourceFolder = RDsResource.ResourceConsole.Folders;
            _instance.loadFolder();
         }
         return _instance;
      }

      // 资源
      protected FRsResource _selectedResource;

      // 资源目录集合
      protected FObjects<FRsResourceFolder> _resourceFolder;

      //============================================================
      // <T>构造资源表单。</T>
      //============================================================
      public QUiResourcePickerForm() {
         InitializeComponent();
      }

      //============================================================
      // <T>获得或设置显示类型。</T>
      //============================================================
      public int ShowCd {
         get { return qrpResourcePicker.ShowCd; }
         set { qrpResourcePicker.ShowCd = value; }
      }

      //============================================================
      // <T>获得选中资源。</T>
      //============================================================
      public FRsResource SelectedResource {
         get { return _selectedResource; }
      }

      //============================================================
      // <T>获得或设置资源目录集合。</T>
      //============================================================
      public FObjects<FRsResourceFolder> ResourceFolder {
         get { return _resourceFolder; }
         set { _resourceFolder = value; }
      }

      //============================================================
      public void loadFolder() {
         //qrpResourcePicker.LoadFolder(_resourceFolder);
      }

      //============================================================
      // <T>通过资源编号选择资源。</T>
      //
      // @param id 资源编号
      //============================================================
      public void SelectResourceById(int id) {
         qrpResourcePicker.SelectResourceById(id);
      }

      //============================================================
      // <T>通过资源代码选择资源。</T>
      //
      // @param code 资源代码
      //============================================================
      public void SelectResourceByCode(string code) {
         qrpResourcePicker.SelectResourceByCode(code);
      }

      //============================================================
      // <T>选择处理。a</T>
      //============================================================
      private void tsbSelect_Click(object sender, EventArgs e) {
         _selectedResource = qrpResourcePicker.SelectedResource;
         DialogResult = DialogResult.OK;
         Close();
      }

      //============================================================
      // <T>关闭选择。</T>
      //============================================================
      private void tsbClose_Click(object sender, EventArgs e) {
         DialogResult = DialogResult.Cancel;
         Close();
      }
   }
}
