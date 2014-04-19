using MO.Common.Content;
using MO.Common.Lang;

namespace MO.Resource.Data
{
   //============================================================
   // <T>数据集合。</T>
   //============================================================
   public class FRsDataset : FObject
   {
      // 代码
      public string _code;

      // 文件名称
      public string _fileName;

      // 打开标志
      public bool _opened;

      // 行集合
      protected FObjects<FRsDataRow> _rows = new FObjects<FRsDataRow>();

      //============================================================
      // <T>构造数据集合。</T>
      //============================================================
      public FRsDataset() {
      }

      //============================================================
      // <T>获得或设置数据行。</T>
      //============================================================
      public string Code {
         get { return _code; }
         set { _code = value; }
      }

      //============================================================
      // <T>获得或设置文件名称。</T>
      //============================================================
      public string FileName {
         get { return _fileName; }
         set { _fileName = value; }
      }

      //============================================================
      // <T>获得行集合。</T>
      //============================================================
      public FObjects<FRsDataRow> Rows {
         get { return _rows; }
      }

      //============================================================
      // <T>构造数据集合。</T>
      //============================================================
      public void Open() {
         if (!_opened) {
            FXmlDocument xdocument = new FXmlDocument(_fileName);
            foreach (FXmlNode xnode in xdocument.Root.Nodes) {
               FRsDataRow row = new FRsDataRow();
               row.LoadConfig(xnode);
               _rows.Push(row);
            }
            _opened = true;
         }
      }
   }
}
