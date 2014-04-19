using MO.Common.IO;
using MO.Common.Lang;
using MO.Content2d.Common;
using MO.Content3d.Common;
using System;
using System.Collections.Generic;

namespace MO.Content3d.Resource.Common
{
   //============================================================
   // <T>资源信息。</T>
   //============================================================
   public class FDrResource : FObject, IComparer<FDrResource>, IRsExport, IDisposable
   {
      // 日志输出接口
      private static ILogger _logger = RLogger.Find(typeof(FDrResource));

      // 索引
      protected int _index;

      // 名称
      protected string _name;

      // 标签
      protected string _label;

      // 类型代码
      protected string _typeCode;

      // 类型名称
      protected string _typeName;

      // 类型标签
      protected string _typeLabel;

      // 是否打开标志
      protected bool _opened;

      // 有效设置
      protected bool _optionValid;

      // 导出设置
      protected bool _optionExport;

      // 品质类型
      protected string _qualityCd = ERsResourceQuality.Middle;

      // 超时类型
      protected string _timeoutCd = ERsTimeout.Middle;

      // 文件夹
      protected FDrFolder _folder;
      
      // 路径
      protected string _directory;

      // 设置文件名
      protected string _configFileName;

      // 导出路径
      protected string _directoryExprot;

      // 附加数据
      protected object _tag;

      //============================================================
      // <T>构造资源信息。</T>
      //============================================================
      public FDrResource() {
      }

      //============================================================
      // <T>获得或设置索引。</T>
      //============================================================
      public int Index {
         get { return _index; }
         set { _index = value; }
      }

      //============================================================
      // <T>获得代码信息。</T>
      //============================================================
      public string Code {
         get { return RDrUtil.FormatPathToCode(_name); }
      }

      //============================================================
      // <T>获得代码数字。</T>
      //============================================================
      public int CodeNumber {
         get {
            string code = RDrUtil.FormatPathToCode(_name);
            code = code.Replace(".", "");
            return RInt.Parse(code);
         }
      }

      //============================================================
      // <T>获得或设置名称。</T>
      //============================================================
      public string Name {
         get { return _name; }
         set { _name = value; }
      }

      //============================================================
      // <T>获得或设置标签。</T>
      //============================================================
      public string Label {
         get { return _label; }
         set { _label = value; }
      }

      //============================================================
      // <T>获得或设置类型代码。</T>
      //============================================================
      public string TypeCode {
         get { return _typeCode; }
      }

      //============================================================
      // <T>获得或设置类型名称。</T>
      //============================================================
      public string TypeName {
         get { return _typeName; }
         set { _typeName = value; }
      }

      //============================================================
      // <T>获得或设置类型标签。</T>
      //============================================================
      public string TypeLabel {
         get { return _typeLabel; }
      }

      //============================================================
      // <T>获得或设置有效性。</T>
      //============================================================
      public bool OptionValid {
         get { return _optionValid; }
         set { _optionValid = value; }
      }

      //============================================================
      // <T>获得或设置导出配置。</T>
      //============================================================
      public bool OptionExport {
         get { return _optionExport; }
         set { _optionExport = value; }
      }

      //============================================================
      // <T>获得或设置品质类型。</T>
      //============================================================
      public string QualityCd {
         get { return _qualityCd; }
         set { _qualityCd = value; }
      }

      //============================================================
      // <T>获得或设置超时类型。</T>
      //============================================================
      public string TimeoutCd {
         get { return _timeoutCd; }
         set { _timeoutCd = value; }
      }

      //============================================================
      // <T>获得或设置文件夹。</T>
      //============================================================
      public FDrFolder Folder {
         get { return _folder; }
         set { _folder = value; }
      }

      //============================================================
      // <T>获得或设置路径。</T>
      //============================================================
      public string Directory {
         get { return _directory; }
         set { _directory = value; }
      }

      //============================================================
      // <T>获得或设置设置文件名。</T>
      //============================================================
      public string ConfigFileName {
         get { return _configFileName; }
         set { _configFileName = value; }
      }

      //============================================================
      // <T>获得或设置导出路径。</T>
      //============================================================
      public string DirectoryExprot {
         get { return _directoryExprot; }
         set { _directoryExprot = value; }
      }

      //============================================================
      // <T>获得或设置附加数据。</T>
      //============================================================
      public object Tag {
         get { return _tag; }
         set { _tag = value; }
      }
      
      //============================================================
      // <T>比较资源顺序。</T>
      //============================================================
      public int Compare(FDrResource target, FDrResource source) {
         return target.Name.CompareTo(source.Name);
      }
      
      //============================================================
      // <T>扫描资源内容。</T>
      //============================================================
      public virtual void Scan() {
         _configFileName = _directory + "\\config.xml";
      }
      
      //============================================================
      // <T>打开资源内容。</T>
      //============================================================
      public virtual void Open() {
         _opened = true;
      }

      //============================================================
      // <T>序列化内部数据到输出流中。</T>
      //
      // @param output 输出流
      //============================================================
      public virtual void Serialize(IOutput output) {
         output.WriteString(Code);
         //output.WriteString(_typeName);
         //output.WriteInt32(0);
         //output.WriteUTFString(_label);
      }

      //============================================================
      // <T>从输入流中反序列化内部数据。</T>
      //
      // @param input 输入流
      //============================================================
      public virtual void Unserialize(IInput input) {
         _name = input.ReadString();
         _typeName = input.ReadString();
         _label = input.ReadUTFString();
         input.ReadInt32();
      }

      //============================================================
      // <T>序列化内部数据到输出流中。</T>
      //
      // @param output 输出流
      //============================================================
      public byte[] ExportBytes() {
         Open();
         // 导出数据
         FByteStream stream = new FByteStream();
         FCompressFile compress = new FCompressFile();
         Serialize(compress);
         compress.Compress(stream);
         return stream.ToArray();
      }

      //============================================================
      // <T>根据指定模式导出数据。</T>
      //
      // @param modeCd 导出模式
      //============================================================
      public virtual void Export(ERsExportMode modeCd) {
      }

      //============================================================
      // <T>根据指定模式导出数据到文件。</T>
      //
      // @param modeCd 导出模式
      // @param fileName 文件名称
      //============================================================
      public void ExportFile(ERsExportMode modeCd, string fileName) {
         FCompressFile file = new FCompressFile();
         Serialize(file);
         file.Compress(fileName);
         _logger.Debug(this, "Export", "Export model success. (file_name={0})", fileName);
      }
      
      //============================================================
      // <T>释放资源内容。</T>
      //============================================================
      public virtual void Dispose() {
         _opened = false;
      }

      //============================================================
      // <T>打开资源内容。</T>
      //============================================================
      public override string ToString() {
         return _label + " (" + _name + ")";
      }
   }
}
