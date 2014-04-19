using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MO.Common.System;
using MO.Common.Collection;
using MO.Common.IO;
using MO.Common.Content;
using MO.Common.Lang;

namespace MO.Resource.Data
{
   //============================================================
   // <T>数据控制台。</T>
   //============================================================
   public class FRsDataConsole : FConsole
   {
      // 日志输出接口
      private static ILogger _logger = RLogger.Find(typeof(FRsDataConsole));

      // 目录
      protected string _directory;

      // 数据集合
      protected FDictionary<FRsDataset> _datasets = new FDictionary<FRsDataset>();

      //============================================================
      // <T>构造数据控制台。</T>
      //============================================================
      public FRsDataConsole() {
         _name = "design.data.console";
      }

      //============================================================
      // <T>获得指定代码的数据集。</T>
      //============================================================
      public FRsDataset FetchDataset(string code) {
         FRsDataset dataset = null;
         if (_datasets.Contains(code)) {
            dataset = _datasets.Get(code);
            dataset.Open();
         }
         return dataset;
      }

      //============================================================
      // <T>获得数据集。</T>
      //============================================================
      public FDictionary<FRsDataset> Datasets {
         get { return _datasets; }
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
                  case "directory":
                     _directory = xnode.Text;
                     break;
               }
            }
         }
      }
      
      //============================================================
      // <T>扫描资源控制台。</T>
      //
      // @author TYFNG 20120409
      //============================================================
      public void Scan() {
         // 检查文件存在性
         if (!RDirectory.Exists(_directory)) {
            return;
         }
         // 扫描文件
         FStrings fileNames = RDirectory.ListFiles(_directory);
         foreach (string fileName in fileNames) {
            // 检查后缀
            if (!fileName.EndsWith(".xml")) {
               continue;
            }
            // 解析资源
            string name = fileName.Substring(fileName.LastIndexOf("\\") + 1);
            string code = name.Substring(0, name.Length - 4);
            FRsDataset dataset = new FRsDataset();
            dataset.Code = code;
            dataset.FileName = fileName;
            _datasets.Set(code, dataset);
         }
      }
   }
}
