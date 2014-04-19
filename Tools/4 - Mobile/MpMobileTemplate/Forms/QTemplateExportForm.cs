using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using MO.Common;
using MO.Common.Content;
using MO.Core.Content.Xls;

namespace MpMobileTemplate.Forms
{
   public partial class QTemplateExportForm : Form
   {

      // 实例对象
      protected static QTemplateExportForm _instance;
      
      // 模版原路径地址
      protected string _templateSource;

      // 模版导出路径
      protected string _templateTarget;

      // 合并目标地址
      protected string _mergerTarget;

      //============================================================
      // <T>获得表单实例对象。<T>
      //============================================================
      public static QTemplateExportForm Instance {
         get {
            if (null == _instance) {
               _instance = new QTemplateExportForm();
            }
            return _instance;
         }
      }

      //============================================================
      // <T>构造窗体。<T>
      //============================================================
      public QTemplateExportForm() {
         InitializeComponent();
         LoadConfig();
      }

      //============================================================
      // <T>加载模版分页。<T>
      //============================================================
      public void LoadTemplate(FXmlNode config) {
         lvwTemplate.BeginUpdate();
         lvwTemplate.Items.Clear();
         foreach (FXmlNode node in config.Nodes) {
            string valid = node.Get("valid");
            string name = node.Get("name");
            string tag = node.Get("tag");
            string source = node.Get("source");
            string target = node.Get("target");
            ListViewItem listViewItem = new ListViewItem();
            listViewItem.Tag = node;
            listViewItem.Checked = true;
            listViewItem.Text = tag;
            listViewItem.SubItems.Add(name);
            listViewItem.SubItems.Add(target);
            listViewItem.SubItems.Add(source);
            lvwTemplate.Items.Add(listViewItem);
         }
         lvwTemplate.EndUpdate();
         labTemplate.Text = config.Nodes.Count.ToString();
      }

      //============================================================
      // <T>加载合并分页。<T>
      //============================================================
      public void LoadMerger(FXmlNode config) {
         lvwMerger.BeginUpdate();
         lvwMerger.Items.Clear();
         foreach (FXmlNode node in config.Nodes) {
            string valid = node.Get("valid");
            string type = node.Get("type");
            string target = node.Get("target");
            ListViewItem listViewItem = new ListViewItem();
            listViewItem.Tag = node;
            listViewItem.Checked = true;
            listViewItem.Text = valid;
            listViewItem.SubItems.Add(type);
            listViewItem.SubItems.Add(target);
            lvwMerger.Items.Add(listViewItem);
         }
         lvwMerger.EndUpdate();
         labMerger.Text = config.Nodes.Count.ToString();
      }

      //============================================================
      // <T>加载复制分页。<T>
      //============================================================
      public void LoadCopy(FXmlNode config) {
         lvwCopy.BeginUpdate();
         lvwCopy.Items.Clear();
         foreach (FXmlNode node in config.Nodes) {
            string type = node.Get("type");
            string from = node.Get("from");
            string target = node.Get("target");
            ListViewItem listViewItem = new ListViewItem();
            listViewItem.Tag = node;
            listViewItem.Checked = true;
            listViewItem.Text = type;
            listViewItem.SubItems.Add(from);
            listViewItem.SubItems.Add(target);
            lvwCopy.Items.Add(listViewItem);
         }
         lvwCopy.EndUpdate();
         labCopy.Text = config.Nodes.Count.ToString();
      }

      //============================================================
      // <T>加载窗体信息。<T>
      //============================================================ 
      public void LoadConfig() {
         string applicationConfig = RMoCommon.GetEnvironment("application.root") + "\\Configuration\\excel.export.xml";
         FXmlDocument xmldoc = new FXmlDocument();
         xmldoc.LoadFile(applicationConfig);
         foreach (FXmlNode node in xmldoc.Root.Nodes) {
            if ("ExcelExportList" == node.Name) {
               _templateSource = node.Get("source_path");
               _templateTarget = node.Get("target_path");
               LoadTemplate(node);
            }
            if ("ConverterExportList" == node.Name) {
               _mergerTarget = node.Get("target_path");
               LoadMerger(node);
            }
            if ("CommandList" == node.Name) {
               LoadCopy(node);
            }
         }
      }

      //============================================================
      // <T>执行模版分页导出。<T>
      //============================================================
      public void SaveTemplate() {
         using (FXlsDocument xlsDoc = new FXlsDocument()) {
            int itemCount = lvwTemplate.CheckedItems.Count;
            for (int i = 0; i < itemCount; i++) {
               ListViewItem template = lvwTemplate.Items[i];
               FXmlNode node = template.Tag as FXmlNode;
               if (template.Checked) {
                  string source = node.Get("source");
                  string tag = node.Get("tag");
                  string target = node.Get("target");
                  string name = node.Get("name");
                  xlsDoc.LoadFile(_templateSource + source);
                  xlsDoc.SaveXmlPath((_templateTarget + target), tag);
               }
            }
         }
      }

      //============================================================
      // <T>执行模版合并。<T>
      //============================================================
      public void SaveMerger() {

      }

      //============================================================
      // <T>执行复制命令。<T>
      //============================================================
      public void SaveCopy() {
         int count = lvwCopy.CheckedItems.Count;
         if (0 != count) {
            FXmlDocument xmldoc = new FXmlDocument();
            for (int i = 0; i < count; i++) {
               ListViewItem listViewItem = lvwCopy.Items[i];
               FXmlNode node = listViewItem.Tag as FXmlNode;
               string from = node.Get("from");
               string target = node.Get("target");
               xmldoc.LoadFile(from);
               xmldoc.SaveFile(target);
            }
         }
      }

      //============================================================
      // <T>导出按钮处理。<T>
      //============================================================
      private void Tsbonly_Click(object sender, EventArgs e) {
         // 导出模版
         SaveTemplate();
         // 合并文件
         SaveMerger();
         // 复制模版
         SaveCopy();
         MessageBox.Show("导出成功！");
      }

      //============================================================
      // <T>关闭按钮处理。<T>
      //============================================================
      private void TlsClose_Click(object sender, EventArgs e) {
         Close();
      }
   }
}
