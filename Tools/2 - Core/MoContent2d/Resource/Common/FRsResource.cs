using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content2d.Common;
using System;

namespace MO.Content2d.Resource.Common
{
   //============================================================
   // <T>资源。</T>
   //============================================================
   public class FRsResource : FObject, IRsExport, IDisposable
   {
      // 文件夹
      protected FRsResourceFolder _folder;
      
      // 代码
      protected int _code;

      // 名称
      protected string _name;

      // 标签
      protected string _label;

      // 全标签
      protected string _fullLabel;

      // 关键字
      protected string _keyword;

      // 类型
      protected int _typeCd;

      // 类型名称
      protected string _typeName;

      // 类型图标
      protected string _typeIcon;

      // 超时类型
      protected string _timeoutCd = ERsTimeout.Middle;

      // 是否有效
      protected bool _optionValid;

      // 导出设置
      protected bool _optionExport;

      // 是否打开
      protected bool _statusOpen;

      // 字节总数
      protected int _totalBytes;

      // 目录名称
      protected string _directory;

      // 最后修改时刻
      protected long _lastUpdateTick;

      // 最后处理时刻
      protected long _lastExportTick;

      // 附加数据
      protected object _tag;

      //============================================================
      // <T>构造资源。</T>
      //============================================================
      public FRsResource() {
      }

      //============================================================
      // <T>获得或设置文件夹。</T>      
      //============================================================
      public FRsResourceFolder Folder {
         get { return _folder; }
         set { _folder = value; }
      }

      //============================================================
      // <T>获得或设置代码。</T>      
      //============================================================
      public int Code {
         get { return _code; }
         set { _code = value; }
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
      // <T>获得或设置全标签。</T>
      //============================================================
      public string FullLabel {
         get { return _fullLabel; }
         set { _fullLabel = value; }
      }

      //============================================================
      // <T>获得或设置关键字。</T>
      //============================================================
      public string Keyword {
         get { return _keyword; }
         set { _keyword = value; }
      }

      //============================================================
      // <T>获得或设置类型。</T>      
      //============================================================
      public int TypeCd {
         get { return _typeCd; }
         set { _typeCd = value; }
      }

      //============================================================
      // <T>获得类型名称。</T>
      //============================================================
      public string TypeName {
         get { return _typeName; }
      }

      //============================================================
      // <T>获得类型图标。</T>
      //============================================================
      public string TypeIcon {
         get { return _typeIcon; }
      }

      //============================================================
      // <T>获得或设置超时类型。</T>
      //============================================================
      public string TimeoutCd {
         get { return _timeoutCd; }
         set { _timeoutCd = value; }
      }

      //============================================================
      // <T>获得或设置是否有效。</T>
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
      // <T>获得或设置是否打开。</T>
      //============================================================
      public bool StatusOpen {
         get { return _statusOpen; }
         set { _statusOpen = value; }
      }

      //============================================================
      // <T>获得字节总数。</T>
      //============================================================
      public int TotalBytes {
         get { return _totalBytes; }
      }

      //============================================================
      // <T>读取或设置目录名称。</T>
      //============================================================
      public string Directory {
         get { return _directory; }
         set { _directory = value; }
      }

      //============================================================
      // <T>读取或设置最后修改时刻。</T>
      //============================================================
      public long LastUpdateTick {
         get { return _lastUpdateTick; }
         set { _lastUpdateTick = value; }
      }

      //============================================================
      // <T>读取或设置最后处理时刻。</T>
      //============================================================
      public long LastExportTick {
         get { return _lastExportTick; }
         set { _lastExportTick = value; }
      }

      //============================================================
      // <T>测试导出时间是否改变。</T>
      //============================================================
      public bool TestExportChanged() {
         return (_lastUpdateTick != _lastExportTick);
      }

      //============================================================
      // <T>读取或设置附加数据。</T>
      //============================================================
      public object Tag {
         get { return _tag; }
         set { _tag = value; }
      }

      //============================================================
      // <T>获得格式化内容。</T>
      //============================================================
      public string Format(){
         return _code + " " + _label;
      }

      //============================================================
      // <T>加载设置信息。</T>
      //
      // @param xconfig 设置节点
      //============================================================
      public virtual void LoadConfig(FXmlNode xconfig) {
         // 读取超时类型
         _timeoutCd = xconfig.Get("timeout_cd", _timeoutCd);
      }

      //============================================================
      // <T>加载设置文件。</T>
      //
      // @param fileName 文件路径
      //============================================================
      public virtual void LoadConfigFile(string fileName) {
         using(FXmlDocument document = new FXmlDocument(fileName)){
            LoadConfig(document.Root);
         }
      }

      //============================================================
      // <T>保存设置信息。<T>      
      // 
      // @param xconfig 设置节点
      //============================================================
      public virtual void SaveConfig(FXmlNode xconfig) {
         // 设置代码
         xconfig.SetNvl("code", _code);
         // 设置名称
         xconfig.SetNvl("name", _name);
         // 设置标签
         xconfig.SetNvl("label", _label);
         // 设置标志
         xconfig.SetNvl("option_valid", _optionValid);
         // 设置超时类型
         xconfig.SetNvl("timeout_cd", _timeoutCd);
      }

      //============================================================
      // <T>保存设置文件。<T>      
      //
      // @param fileName 保存路径
      //============================================================
      public virtual void SaveConfigFile(string fileName) {
         using (FXmlDocument document = new FXmlDocument()) {
            SaveConfig(document.Root);
            document.SaveFile(fileName);
         }
      }

      //============================================================
      // <T>序列化资源信息。</T>
      //
      // @param output 输出流
      //============================================================
      public virtual void Serialize(IOutput output) {
         // 写入类型
         output.WriteUint8((byte)_typeCd);
         // 写入编号
         output.WriteInt32(_code);
         // 写入超时
         output.WriteInt32(ERsTimeout.Parse2d(_timeoutCd));
      }

      //============================================================
      // <T>序列化信息文件。</T>
      //
      // @param fileName 路径
      //============================================================
      public void SerializeFile(string fileName) {
         using (FByteFile file = new FByteFile()) {
            Serialize(file);
            file.SaveFile(fileName);
         }
      }

      //============================================================
      // <T>反序列化资源信息。</T>
      //
      // @param input 输入流
      //============================================================
      public virtual void Unserialize(IInput input) {
         // 读取编号
         _code = input.ReadInt32();
         // 读取类型
         _typeCd = input.ReadInt8();
      }

      //============================================================
      // <T>解压文件信息。</T>
      //
      // @param fileName 路径
      //============================================================
      public void UnserializeFile(string fileName) {
         using (FByteFile file = new FByteFile(fileName)) {
            Unserialize(file);
         }
      }

      //============================================================
      // <T>扫描资源。</T>
      //============================================================
      public virtual void Scan() {
      }

      //============================================================
      // <T>打开资源。</T>
      //============================================================
      public virtual void Open() {
         _statusOpen = true;
      }

      //============================================================
      // <T>关闭资源。</T>
      //============================================================
      public virtual void Close() {
         _statusOpen = false;
      }

      //============================================================
      // <T>序列化内部数据到输出流中。</T>
      //
      // @param output 输出流
      //============================================================
      public byte[] CompressBytes() {
         Open();
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
      // <T>存储设置文件。<T>      
      //============================================================
      public virtual void Store() {
      }
      
      //============================================================
      // <T>释放资源。</T>
      //============================================================
      public virtual void Dispose() {
         Close();
      }
   }
}
