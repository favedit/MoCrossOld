using MO.Common.IO;
using MO.Common.Lang;
using MO.Content2d.Common;
using System;

namespace MO.Content2d.Resource.Common
{
   //============================================================
   // <T>资源。</T>
   //============================================================
   public class FRsResourceGroup : FObject, IRsExport, IDisposable
   {
      // 日志输出接口
      private static ILogger _logger = RLogger.Find(typeof(FRsResourceGroup));

      // 代码
      protected string _code;

      // 标签
      protected string _label;

      // 关键字
      protected string _keyword;

      // 校验值
      protected string _sha;

      // 是否有效
      protected bool _isValid;

      // 是否打开
      protected bool _isOpened;

      // 字节总数
      protected int _totalBytes;

      // 附加数据
      protected object _tag;

      // 附加数据
      protected FObjects<FRsResource> _resources = new FObjects<FRsResource>();

      //============================================================
      // <T>构造资源。</T>
      //============================================================
      public FRsResourceGroup() {
      }

      //============================================================
      // <T>获得或设置代码。</T>      
      //============================================================
      public string Code {
         get { return _code; }
         set { _code = value; }
      }

      //============================================================
      // <T>获得或设置标签。</T>
      //============================================================
      public string Label {
         get { return _label; }
         set { _label = value; }
      }

      //============================================================
      // <T>获得或设置关键字。</T>
      //============================================================
      public string Keyword {
         get { return _keyword; }
         set { _keyword = value; }
      }

      //============================================================
      // <T>获得或设置校验值。</T>      
      //============================================================
      public string Sha {
         get { return _sha; }
         set { _sha = value; }
      }

      //============================================================
      // <T>获得或设置是否有效。</T>
      //============================================================
      public bool IsValid {
         get { return _isValid; }
         set { _isValid = value; }
      }
      
      //============================================================
      // <T>获得或设置是否打开。</T>
      //============================================================
      public bool IsOpened {
         get { return _isOpened; }
         set { _isOpened = value; }
      }

      //============================================================
      // <T>获得字节总数。</T>
      //============================================================
      public int TotalBytes {
         get { return _totalBytes; }
      }

      //============================================================
      // <T>读取资源组。</T>
      //============================================================
      public FObjects<FRsResource> Resources {
         get { return _resources; }
      }

      //============================================================
      // <T>读取或设置附加数据。</T>
      //============================================================
      public object Tag {
         get { return _tag; }
         set { _tag = value; }
      }

      //============================================================
      // <T>序列化资源信息。</T>
      //
      // @param output 流信息
      //============================================================
      public virtual void Serialize(IOutput output) {
         // 写入编号
         output.WriteString(_code);
         //............................................................
         output.WriteInt16((short)_resources.Count);
         foreach (FRsResource resource in _resources) {
            resource.Open();
            // 序列化资源
            FCompressFile file = new FCompressFile();
            resource.Serialize(file);
            FByteStream stream = new FByteStream();
            file.Compress(stream);
            // 输出资源
            output.WriteInt32(stream.Length);
            output.WriteBytes(stream.Memory, 0, stream.Length);
         }
      }

      //============================================================
      // <T>序列化信息文件。</T>
      //
      // @param fileName 路径
      //============================================================
      public void SerializeFile(string fileName) {
         FCompressFile file = new FCompressFile();
         Serialize(file);
         file.BlockCompress(fileName,RInt.SIZE_256K);
      }

      //============================================================
      // <T>反序列化资源信息。</T>
      //
      // @param input 流
      //============================================================
      public virtual void Unserialize(IInput input) {
         // 读取编号
         _code = input.ReadString();
      }

      //============================================================
      // <T>解压文件信息。</T>
      //
      // @param fileName 路径
      //============================================================
      public void UnserializeFile(string fileName) {
         FCompressFile file = new FCompressFile();
         file.Decompress(fileName);
         Unserialize(file);
      }

      //============================================================
      // <T>扫描资源。</T>
      //
      // @author TYFNF 20120409
      //============================================================
      public virtual void Scan() {
      }

      //============================================================
      // <T>打开资源。</T>
      //
      // @author TYFNF 20120409
      //============================================================
      public virtual void Open() {
         _isOpened = true;
      }

      //============================================================
      // <T>根据指定模式导出数据。</T>
      //
      // @param modeCd 导出模式
      //============================================================
      public virtual void Export(ERsExportMode modeCd) {
      }

      //============================================================
      // <T>导出资源。</T>
      //============================================================
      public virtual void ExportPack(IOutput output, string compressCd) {
      }
      
      //============================================================
      // <T>存储设置文件。<T>      
      //============================================================
      public virtual void Store() {
      }
      
      //============================================================
      // <T>释放资源。</T>
      //
      // @author TYFNF 20120409
      //============================================================
      public virtual void Dispose() {
         _isOpened = false;
      }
   }
}
