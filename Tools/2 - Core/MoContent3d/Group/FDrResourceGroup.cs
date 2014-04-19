using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content2d.Common;
using MO.Content3d.Resource.Common;
using MO.Resource.Data;
using System;

namespace MO.Content3d.Group
{
   //============================================================
   // <T>资源。</T>
   //============================================================
   public class FDrResourceGroup : FObject, IRsExport, IDisposable
   {
      // 日志输出接口
      private static ILogger _logger = RLogger.Find(typeof(FDrResourceGroup));

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
      protected FObjects<FDrResource> _resources = new FObjects<FDrResource>();

      // 附加数据
      protected object _tag;

      //============================================================
      // <T>构造资源。</T>
      //============================================================
      public FDrResourceGroup() {
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
      public FObjects<FDrResource> Resources {
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
      // <T>加载行信息。</T>
      //
      // @param row 行信息
      //============================================================
      public void LoadRow(FRsDataRow row) {
         _code = row.Get("tid");
         _label = row.Get("label");
      }

      //============================================================
      // <T>序列化资源信息。</T>
      //
      // @param output 流信息
      //============================================================
      public virtual void Serialize(IOutput output, FXmlNode config){
         // 写入编号
         output.WriteString(_code);
         //............................................................
         output.WriteInt16((short)_resources.Count);
         foreach (FDrResource resource in _resources) {
            resource.Open();
            // 序列化资源
            FByteStream stream = new FByteStream();
            resource.Serialize(stream);
            // 输出资源
            output.WriteInt32(stream.Length);
            output.WriteBytes(stream.Memory, 0, stream.Length);
            // 创建资源设置
            if (null != config) {
               FXmlNode xresource = config.CreateNode("Resource");
               xresource.Set("code", resource.Code);
               xresource.Set("label", resource.Label);
               xresource.Set("length", stream.Length);
            }
         }
      }

      //============================================================
      // <T>序列化资源信息。</T>
      //
      // @param output 流信息
      //============================================================
      public virtual void SerializeCompress(IOutput output, FXmlNode xconfig) {
         // 写入编号
         output.WriteString(_code);
         //............................................................
         output.WriteInt16((short)_resources.Count);
         foreach (FDrResource resource in _resources) {
            resource.Open();
            // 序列化资源
            FByteStream data = new FByteStream();
            FCompressFile stream = new FCompressFile();
            resource.Serialize(stream);
            stream.Compress(data);
            // 输出资源
            output.WriteInt32(data.Length);
            output.WriteBytes(data.Memory, 0, data.Length);
            //// 创建资源设置
            //if (null != xconfig) {
            //   FXmlNode xresources = xconfig.Sync("Resources");
            //   FXmlNode xresource = xresources.CreateNode("Resource");
            //   xresource.Set("code", resource.Code);
            //   xresource.Set("label", resource.Label);
            //   xresource.Set("type_name", resource.TypeName);
            //   xresource.Set("size", stream.Length);
            //   xresource.Set("size_compress", data.Length);
            //}
         }
      }
      
      //============================================================
      // <T>序列化信息文件。</T>
      //
      // @param fileName 路径
      //============================================================
      public void SerializeFile(string fileName) {
         FCompressFile file = new FCompressFile();
         Serialize(file, new FXmlNode());
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
      // <T>导出资源。</T>
      //
      // @author TYFNF 20120409
      //============================================================
      public virtual void ExportWithConfig(FXmlNode xconfig) {
         //int sizeLimit = RInt.SIZE_1K * 128;
         // 打开资源
         Open();
         // 获得导出路径
         //xconfig.Set("code", _code);
         //xconfig.Set("label", _label);
         //xconfig.Set("count", _resources.Count);
         //string exportDirectory = RContent3dManager.ResourceGroupConsole.ExportResourceDirectory;
         //string filePath = exportDirectory + "/r3/" + RString.PadLeft(_code, 6, '0');
         ////............................................................
         //FByteFile file = new FByteFile();
         //SerializeCompress(file, xconfig);
         //file.SaveFile(filePath + ".yrs");
         //xconfig.Set("size", file.Length);
         ////............................................................
         //// 分块存储
         //int count = file.Length / sizeLimit;
         //if (file.Length % sizeLimit != 0) {
         //   count++;
         //}
         //FXmlNode xblocks = xconfig.CreateNode("Blocks");
         //int size = file.Length / count;
         //for (var n = 0; n < count; n++) {
         //   int begin = size * n;
         //   int length = Math.Min(size * (n + 1), file.Length) - begin;
         //   FByteFile data = new FByteFile();
         //   data.Append(file.Memory, begin, length);
         //   data.SaveFile(filePath + "." + RInt.Pad(n + 1, 2) + ".yrs");
         //   // 创建节点
         //   FXmlNode xblock = xblocks.CreateNode("Block");
         //   xblock.Set("index", n);
         //   xblock.Set("offset", begin);
         //   xblock.Set("length", length);
         //}
         //_logger.Debug(this, "Export", "Export resource group. (group={0}, file_path={1})", _code, filePath);
      }

      //============================================================
      // <T>根据指定模式导出数据。</T>
      //
      // @param modeCd 导出模式
      //============================================================
      public virtual void Export(ERsExportMode modeCd){
         // 打开资源
         Open();
         //// 获得导出路径
         //string exportDirectory = RContent3dManager.ResourceGroupConsole.ExportResourceDirectory;
         //string fileName = exportDirectory + "/r3/" + RString.PadLeft(_code, 6, '0') + ".yrs";
         ////............................................................
         //FCompressFile file = new FCompressFile();
         //Serialize(file, null);
         //file.Compress(fileName);
         //_logger.Debug(this, "Export", "Export resource group. (group={0}, file_name={1})", _code, fileName);
      }

      //============================================================
      // <T>导出资源。</T>
      //============================================================
      public void ExportPack(IOutput output, string compressCd) {
         //string code = RString.PadLeft(Code, 6, '0');
         //string storageDirectory = null;
         //if (compressCd == ERsCompress.Lzma) {
         //   storageDirectory = RContent3dManager.ContentConsole.StorageLzmaDirectory + ".3d";
         //} else {
         //   storageDirectory = RContent3dManager.ContentConsole.StorageDeflateDirectory + ".3d";
         //}
         //string exportDirectory = null;
         //if (compressCd == ERsCompress.Lzma) {
         //   exportDirectory = RContent3dManager.ContentConsole.ExportLzmaDirectory + "\\g3";
         //} else {
         //   exportDirectory = RContent3dManager.ContentConsole.ExportDeflateDirectory + "\\g3";
         //}
         ////............................................................
         //using (FByteFile stream = new FByteFile()) {
         //   // 写入数据
         //   stream.WriteString(code);
         //   stream.WriteString(ERsResourceType.Group);
         //   // 写入资源集合
         //   stream.WriteInt16((short)_resources.Count);
         //   foreach (FDrResource resource in _resources) {
         //      string fileName = storageDirectory + "\\" + resource.TypeCode + "_" + resource.Code + ".stg";
         //      using (FByteFile file = new FByteFile(fileName)) {
         //         stream.WriteInt32(file.Length);
         //         stream.WriteBytes(file.Memory, 0, file.Length);
         //      }
         //   }
         //   // 存储压缩文件
         //   string exportName = exportDirectory + "\\" + code + ".swf";
         //   stream.SaveFile(exportName);
         //   // 写入数据
         //   output.WriteString(code);
         //   output.WriteInt32(stream.Length);
         //   output.WriteInt32(_resources.Count);
         //   foreach (FDrResource resource in _resources) {
         //      output.WriteString(resource.Code);
         //      output.WriteString(resource.TypeName);
         //   }
         //}
      }
      //============================================================
      // <T>存储设置文件。<T>      
      //============================================================
      public virtual void Store() {
      }

      //============================================================
      // <T>打开资源内容。</T>
      //============================================================
      public override string ToString() {
         return _label + " (" + _code + ")";
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
