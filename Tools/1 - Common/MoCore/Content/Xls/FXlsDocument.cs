using System;
using System.Runtime.InteropServices;
using Microsoft.Office.Interop.Excel;
using MO.Common.Collection;
using MO.Common.Content;
using MO.Common.Lang;
using Excel = Microsoft.Office.Interop.Excel;

// Workbooks.Open(FileName, UpdateLinks, ReadOnly, Format, Password, WriteResPassword, IgnoreReadOnlyRecommended, Origin, Delimiter, Editable, Notify, Converter, AddToMRU)   
//    expression     必选。该表达式返回一个   Workbooks   对象或   RecentFile   对象。   
//    FileName     String   类型，必选。要打开的工作簿文件名。   
//    UpdateLinks     Variant   类型，可选。指定文件中的链接的更新方式。如果省略本参数，则提示用户选择链接的更新方式。否则，该参数的取值应为下表的某个值。   
//       0   不更新任何引用。     
//       1   更新外部引用，但不更新远程引用。     
//       2   更新外部引用，但不更新外部引用。     
//       3   更新所有远程引用和外部引用。     
//       如果   Microsoft   Excel   正在打开以   WKS、WK1   或   WK3   格式存储的文件并且   UpdateLinks   参数设为   2，则   Microsoft   Excel   根据与该文件关联的图形创建图表。如果该参数设为   0，则不创建任何图表。   
//    ReadOnly     Variant   类型，可选。如果为   True   则以只读模式打开工作簿。   
//    Format     Variant   类型，可选。如果   Microsoft   Excel   正在打开一个文本文件，则该参数用于指定分隔字符，如下表所示。如果省略本参数，则使用当前的分隔字符。   
//       1   制表符     
//       2   逗号     
//       3   空格     
//       4   分号     
//       5   没有分隔符     
//       6   自定义字符（参阅   Delimiter   参数）     
//    Password     Variant   类型，可选。该字符串为用于打开一个受保护工作簿的密码。如果省略该参数并且指定工作簿已设置密码，则提示用户输入密码。   
//    WriteResPassword     Variant   类型，可选。该字符串为一个写保护工作簿的写入权密码。如果省略该参数并且指定工作簿已设置密码，则提示用户输入密码。   
//    IgnoreReadOnlyRecommended     Variant   类型，可选。如果为   True   则设置   Microsoft   Excel   不显示建议只读消息（如果该工作簿以“建议只读”选项保存）。   
//    Origin     Variant   类型，可选。如果该文件为文本文件，则该参数用于指示该文件的来源于何种操作系统（以便对代码页和回车/换行（CR/LF）进行正确映射）。可为下列   XlPlatform   常量之一：   xlMacintosh、xlWindows   或   xlMSDOS。如果省略本参数，则使用当前操作系统。   
//    Delimiter     Variant   类型，可选。如果该文件为文本文件并且   Format   参数设为   6，则此参数用于指定用作分隔符的字符。例如，可使用   Chr(9)   代表制表符，使用“,”代表逗号，使用“;”代表分号或者使用自定义字符。如果该参数为字符串，则只使用该字符串的第一个字符。   
//    Editable     Variant   类型，可选。如果该文件为   Microsoft   Excel   4.0   加载宏，则该参数为   True   时可在可见窗口中打开该加载宏。如果该参数设为   False   或者省略该参数，则该加载宏以隐藏方式打开，并且不能设为可见。本选项不能应用于由   Microsoft   Excel   5.0   或更高版本的   Microsoft   Excel   创建的加载宏。如果该文件是   Excel   模板，则参数为   True   时，会打开指定模板进行编辑。参数为   False   时，可根据指定模板打开新的工作簿。默认值为   False。   
//    Notify     Variant   类型，可选。如果该文件不能以可读写模式打开，则若该参数设为   True   可将该文件添加到文件通知列表。Microsoft   Excel   将以只读模式打开该文件并轮询文件通知列表，当文件通知列表中的该文件可用时则通知用户。如果该参数设为   False，或者省略该参数，则不请求任何通知，并且不能打开任何不可用的文件。   
//    Converter     Variant   类型，可选。打开文件时试用的第一个文件转换器的索引号。首先使用的是指定的文件转换器；如果该转换器不能识别此文件，则试用所有的转换器。转换器索引号由   FileConverters   方法所返回的转换器行号组成。     
//    AddToMru     Variant   类型，可选。如果为   True   则将该工作簿添加到最近使用文件列表中。默认值为   False。   
//       如果正在打开的工作簿包含   Auto_Open   宏，则若在   Visual   Basic   中打开该工作簿这些宏将不执行。如果要执行   Auto_Open   宏，必须使用   RunAutoMacros   方法。

namespace MO.Core.Content.Xls
{
   public class FXlsDocument : FObject, IDisposable
   {
      private static ILogger _logger = RLogger.Find(typeof(FXlsDocument));

      protected const int DATA_COL_MAXCNT = 1024;

      protected const int DATA_ROW_MAXCNT = 1024 * 64;

      protected const int DATA_NAME_ROW = 2;

      protected FDictionary<FXlsColumn> _columns = new FDictionary<FXlsColumn>();

      protected FObjects<FXlsRow> _rows = new FObjects<FXlsRow>();

      protected FObjects<FXmlNode> _nodes = new FObjects<FXmlNode>();

      protected string _fileName;

      //============================================================
      public FXlsDocument() {
      }

      //============================================================
      public void Dispose() {
      }

      //============================================================
      public FObjects<FXlsRow>  Rows{
        get{ return _rows;}
      }

      //============================================================
      public FDictionary<FXlsColumn> Colunm {
         get { return _columns; }
      }

      //============================================================
      public void XmlToExcel(string fileName, string excelName, string saveName) {
         FXmlDocument xml = new FXmlDocument();
         xml.LoadFile(fileName);
         FXmlNode root = xml.Root;
         Excel.Application app = new Excel.Application();
         Excel.Workbook workbook = app.Workbooks.Open(excelName,
            Type.Missing, Type.Missing, Type.Missing, Type.Missing, Type.Missing,
            Type.Missing, Type.Missing, Type.Missing, Type.Missing, Type.Missing,
            Type.Missing, Type.Missing, Type.Missing, Type.Missing);
         Excel.Worksheet columnsWorksheet = workbook.Worksheets["定义"];
         Excel.Worksheet rowsWorksheet = workbook.Worksheets["数据"];
         LoadFileColumns(columnsWorksheet);
         int rowCount = 3;
         foreach(FXmlNode node in root.Nodes) {
            // 找到列下标
            foreach(FXlsColumn column in _columns.Values) {
               string name = column.Name;
               int columnIndex = -1;
               for(int n = 1; n <= _columns.Count; n++) {
                  string excColumn = rowsWorksheet.Cells[2, n].Text;
                  if(name.Equals(excColumn)) {
                     columnIndex = n;
                     break;
                  }
               }
               // 将XML中的值附到相应的单元格上
               string value = null;
               if("type_cd" == name) {
                  value = node.Get("type");
                  if(columnIndex > 0) {
                     rowsWorksheet.Cells[rowCount, columnIndex] = value;
                  }
               }
                  //else if ("name" == name) {
                  //   value = node.Get("label");
                  //   if (columnIndex > 0) {
                  //      rowsWorksheet.Cells[rowCount, columnIndex] = value;
                  //   }
                  //} 
               else {
                  if(node.Contains(name)) {
                     value = node.Get(name);
                     if(columnIndex > 0) {
                        rowsWorksheet.Cells[rowCount, columnIndex] = value;
                     }
                  }
               }
            }
            rowCount++;
         }
         app.DisplayAlerts = false;
         rowsWorksheet.SaveAs(saveName,
            Type.Missing, Type.Missing, Type.Missing, Type.Missing, Type.Missing,
            Type.Missing, Type.Missing, Type.Missing, Type.Missing);
         app.Quit();
         System.GC.Collect();
      }

      //============================================================
      protected void LoadFileColumns(Excel.Worksheet worksheet) {
         // 读取定义
         int count = 1;
         // 列数
         int columnsNum = 0;
         // 行数
         int rowsNum = 0;
         // id所在列数
         int idcolumn = 0;
         // group所在列数
         int groupcolumn = 0;
         // label所在列数
         int labelcolumn = 0;
         // name所在列数
         int namecolumn = 0;
         // type所在列数
         int typecolumn = 0;
         // dataType所在列数
         int datacolumn = 0;
         // translate所在列数
         int translatecolumn = 0;
         // length所在列数
         int lengthcolumn = 0;
         // total所在列数
         int totalcolumn = 0;
         // note所在列数
         int notecolumn = 0;
         Excel.Range cells = worksheet.Cells;
         string cellText = cells[DATA_NAME_ROW, count].Text;
         // 找到各属性所在列
         while("" != cellText) {
            if("id" == cellText) {
               idcolumn = count;
            }
            if("group" == cellText) {
               groupcolumn = count;
            }
            if("label" == cellText) {
               labelcolumn = count;
            }
            if("name" == cellText) {
               namecolumn = count;
            }
            if("type" == cellText) {
               typecolumn = count;
            }
            if("data_type" == cellText) {
               datacolumn = count;
            }
            if ("translate" == cellText) {
               translatecolumn = count;
            }
            if("length" == cellText) {
               lengthcolumn = count;
            }
            if("total" == cellText) {
               totalcolumn = count;
            }
            if("note" == cellText) {
               notecolumn = count;
            }
            count++;
            cellText = cells[DATA_NAME_ROW, count].Text;
         }
         count--;
         columnsNum = count;
         count = 3;
         if(0 == idcolumn) {
            return;
         }
         // 获得行数
         cellText = cells[count, idcolumn].Text;
         while("" != cellText) {
            count++;
            cellText = cells[count, idcolumn].Text;
         }
         count--;
         rowsNum = count;
         // 获取数据
         for(int i = 3; i <= rowsNum; i++) {
            FXlsColumn column = new FXlsColumn();
            cellText = cells[i, groupcolumn].Text;
            if("" != cellText) {
               column.Group = cellText;
            }
            cellText = cells[i, idcolumn].Text;
            if("" != cellText) {
               column.Id = cellText;
            }
            cellText = cells[i, labelcolumn].Text;
            if("" != cellText) {
               column.Label = cellText;
            }
            cellText = cells[i, namecolumn].Text;
            if("" != cellText) {
               column.Name = cellText;
            }
            cellText = cells[i, typecolumn].Text;
            if("" != cellText) {
               column.Type = cellText;
            }
            if (datacolumn!=0){
               cellText = cells[i, datacolumn].Text;
               if("" != cellText) {
                  column.DataType = cellText;
               }
            }
            cellText = cells[i, translatecolumn].Text;
            if (""!=cellText) {
               column.Translate = cellText;
            }
            cellText = cells[i, lengthcolumn].Text;
            if("" != cellText) {
               column.Lenth = cellText;
            }
            cellText = cells[i, totalcolumn].Text;
            if("" != cellText) {
               column.Total = cellText;
            }
            cellText = cells[i, notecolumn].Text;
            if("" != cellText) {
               column.Note = cellText;
            }
            // 判断是否无数据
            if(null != column.Name) {
               _columns.Set(column.Name, column);
            }
         }
      }

      //============================================================
      protected string FormatValue(FXlsColumn column, string value) {
         string type = column.Type;
         // 如果是TUint32类型的将字符串中的“-”去掉
         if("TUint32" == column.Type) {
            if(value.StartsWith("-")) {
               value = value.Replace("-", "");
               value = "-" + value;
            } else {
               value = value.Replace("-", "");
            }
         }
         // 去掉字符串中的空格
         value = value.Trim();
         return value;
      }

      //============================================================
      protected bool LoadFileRows(Excel.Worksheet worksheet) {
         // 检查列表
         if(_columns.IsEmpty) {
            return false;
         }
         Excel.Range cells = worksheet.Cells;
         // 读取头数据
         int columnIndex = 0;
         string code = _columns.Value(0).Name;
         string id = _columns[0].Name;
         for(int n = 1; n < DATA_COL_MAXCNT; n++) {
            string name = cells[DATA_NAME_ROW, n].Text;
            if(RString.IsEmpty(name)) {
               break;
            }
            if(_columns.Contains(name)) {
               FXlsColumn column = _columns.Get(name);
               column.Index = n;
               if(name == code){
                  columnIndex = n;
               }
            }
         }
         // 计算最大行数和列数
         int count = _columns.Count;
         Range rowRange = worksheet.Rows[1];
         object[,] rowValues = rowRange.get_Value();
         int columnCount = 1;
         for (; columnCount < DATA_COL_MAXCNT; columnCount++) {
            if (rowValues[1, columnCount] == null) {
               break; ;
            }
         }
         string columnCode = "";
         int ch = columnCount / 26;
         int cl = columnCount % 26;
         if (ch > 0) {
            columnCode += (char)('A' + ch - 1);
         }
         columnCode += (char)('A' + cl);
         Range columnRange = worksheet.Columns[columnIndex];
         object[,] columnValues = columnRange.get_Value();
         int rowCount = 1;
         for (; rowCount < DATA_ROW_MAXCNT; rowCount++) {
            if (columnValues[rowCount, 1] == null) {
               break; ;
            }
         }
         // 获得数据
         Range range = worksheet.get_Range("A3:" + columnCode + rowCount);
         object[,] values = range.get_Value();
         // 转换数据
         for (int r = 1; r <= rowCount; r++) {
            FXlsRow row = new FXlsRow();
            for (int n = 0; n < count; n++) {
               FXlsColumn column = _columns.Value(n);
               int index = column.Index;
               if (index > 0) {
                  object value = values[r, index];
                  string valueStr = String.Empty;
                  if (null != value) {
                     valueStr = value.ToString();
                     if (!RString.IsEmpty(valueStr)) {
                        valueStr = FormatValue(column, valueStr);
                     }
                  }
                  row.Set(column.Name, valueStr);
               }
            }
            //for(int n = 0; n < count; n++) {
            //   FXlsColumn column = _columns.Value(n);
            //   int index = column.Index;
            //   if(index > 0) {
            //      string value = cells[r, index].Text;
            //      if(!RString.IsEmpty(value)) {
            //         value = FormatValue(column, value);
            //      }
            //      row.Set(column.Name, value);
            //   }
            //}
            if (!row.Contains(code)) {
               break;
            }
            if(RString.IsEmpty(row.Get(code))) {
               break;
            }
            _rows.Push(row);
         }
         return true;
      }

      //============================================================
      public void LoadFile(string fileName) {
         // 设置初始数据
         _fileName = fileName;
         _columns.Clear();
         _rows.Clear();
         // 打开文档
         Excel.Application application = null;
         Excel.Workbook workbook = null;
         // 读取定义
         try{
            application = new Excel.Application();
            application.UserControl = false;
            application.ScreenUpdating = false;
            workbook = application.Workbooks.Open(fileName,
                0, true, Type.Missing, Type.Missing, Type.Missing,
                Type.Missing, Type.Missing, Type.Missing, Type.Missing, Type.Missing,
                Type.Missing, Type.Missing, Type.Missing, Type.Missing);
            Excel.Worksheet columnsWorksheet = workbook.Worksheets["定义"];
            Excel.Worksheet rowsWorksheet = workbook.Worksheets["数据"];
            // 读取文件定义
            LoadFileColumns(columnsWorksheet);
            // 读取数据
            LoadFileRows(rowsWorksheet);
         } finally {
            // 关闭文档
            if (null != workbook) {
               workbook.Close(false);
               Marshal.ReleaseComObject(workbook);
               workbook = null;
            }
            if(null != application){
               // 释放组件
               application.Quit();
               Marshal.ReleaseComObject(application);
               // 必须垃圾回收才能释放
               int generation = System.GC.GetGeneration(application);
               application = null;
               System.GC.Collect(generation);
            }
         }
         workbook = null;
         application = null;
      }

      //============================================================
      public void SaveXmlFile(string fileName) {
         if(0 == _rows.Count) {
            return;
         }
         FXmlDocument xml = new FXmlDocument();
         FXmlNode root = xml.Root;
         foreach(FXlsRow row in _rows) {
            root.CreateNode("Row").Attributes.Append(row);
         }
         xml.SaveFile(fileName);
         _logger.Debug(this, "SaveXmlFile", "Save xml file. (file={0}, rows={1})", fileName, _rows.Count);
      }

      //=============================================================
      public void SaveXmlPath(string fileName, string rootname) {
         FXmlDocument xml = new FXmlDocument();
         FXmlNode root = xml.Root;
         root.Name = rootname + "List";
         string id = _columns[0].Name;
         string rid = null;
         foreach(FXlsRow row in _rows) {
            FXmlNode node = root.CreateNode("node");
            foreach(string name in row.Names) {
               string excel = name;
               string value = row[excel].ToString();
               if (id == excel) {
                  rid = value;
               }
               node.Set(excel, value);
               node.Name = rootname;
            }
         }
         xml.SaveFile(fileName);
         _logger.Debug(this, "SaveXmlPath", "Save xml path. (file={0}, rows={1})", fileName, _rows.Count);
      }

      //=============================================================
      public void SaveDefineXmlPath(string fileName,string rootname) {
         FXmlDocument xml = new FXmlDocument();
         FXmlNode root = xml.Root;
         FXmlNode defineNode = root.CreateNode("Define");
         foreach (FXlsColumn value in _columns.Values) {
            FXmlNode node = defineNode.CreateNode("Field");
            node.GetInteger("");
            node.SetNvl("id", value.Id);
            node.SetNvl("label", value.Label);
            node.SetNvl("name", value.Name);
            node.SetNvl("type", value.Type);
            node.SetNvl("data_type", value.DataType);
            node.SetNvl("translate", value.Translate);
            node.SetNvl("length", value.Lenth);
            node.SetNvl("total", value.Total);
            node.SetNvl("note", value.Note);
         }
         FXmlNode dataNode = root.CreateNode(rootname+"List");
         string id = _columns[0].Name;
         string rid = null;
         foreach (FXlsRow row in _rows) {
            FXmlNode node = dataNode.CreateNode("node");
            foreach (string name in row.Names) {
               string excel = name;
               string value = row[excel].ToString();
               if (id == excel) {
                  rid = value;
               }
               node.Set(excel, value);
               node.Name = rootname;
            }
         }
         xml.SaveFile(fileName);
         _logger.Debug(this, "SaveXmlPath", "Save xml path. (file={0}, rows={1})", fileName, _rows.Count);
      }

      //=============================================================
      public FVector<string> toInsertSql() {
         FVector<string> sqls = new FVector<string>();
         foreach(FXlsRow row in _rows) {
            string names = null;
            string values = null;
            foreach(FXlsColumn colum in _columns.Values) {
               string name = colum.Name;
               names += name;
               string value = row.Get(name);
               if("" == value) {
                  values += "'" + '1' + "'";
               } else {
                  values += "'" + value + "'";
               }
               if(colum.Index == _columns.Count) {
                  break;
               }
               names += ",";
               values += ",";
            }
            string sql = "insert into pp (" + names + ")" + "values(" + values + ")";
            sqls.Push(sql);
         }
         return sqls;
      }
   }
}
