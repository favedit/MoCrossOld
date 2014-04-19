using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using MO.Common.Content;
using MO.Core.Content.Xls;
using MpMobileTemplate.Converter;

namespace MpMobileTemplate
{
   public class FExcelExporter
   {

      //============================================================
      // <T>获得相应转化内容。</T>
      //
      // @param type 类型名称
      //============================================================ 
      public ITplConverter CreatConverter(string type) {


         return null;
      }

      //============================================================ 
      public void Process(string filename) {
         FXmlDocument xdoc = new FXmlDocument();
         FXmlNode nodeExportList = null;
         xdoc.LoadFile(filename);
         foreach (FXmlNode node in xdoc.Root.Nodes) {
            if ("ExcelExportList" == node.Name) {
               nodeExportList = node;
               string saveSource = node.Nvl("source_path");
               string saveTarget = node.Nvl("target_path");
               // 处理所有Excel文档
               using (FXlsDocument xlsDox = new FXlsDocument()) {
                  foreach (FXmlNode enode in node.Nodes) {
                     bool valid = enode.GetBoolean("valid");
                     if (valid) {
                        // 读取设置
                        string source = enode.Nvl("source");
                        string tag = enode.Nvl("tag");
                        string target = enode.Nvl("target");
                        string name = enode.Nvl("name");
                        // 转换文件
                        xlsDox.LoadFile(saveSource + source);
                        xlsDox.SaveXmlPath((saveTarget + target), tag);
                        foreach (FXmlNode cnode in enode.Nodes) {
                           string copy = cnode.Text;
                           if (null != copy) {
                              xlsDox.SaveXmlPath(copy, tag);
                           }
                        }
                     }
                  }
               }
            } else if ("ConverterExportList" == node.Name) {
               foreach (FXmlNode cnode in node.Nodes) {
                  bool valid = cnode.GetBoolean("valid");
                  if (valid) {
                     // 读取设置
                     string type = cnode.Nvl("type");
                     // 接口实现相应xml文件合并
                     ITplConverter ftpl = CreatConverter(type);
                     if (null != ftpl) {
                        ftpl.LoadExportListConfig(nodeExportList);
                        ftpl.LoadConfig(cnode);
                        ftpl.Process();
                     }
                  }
               }
            }
         }
      }
   }
}
