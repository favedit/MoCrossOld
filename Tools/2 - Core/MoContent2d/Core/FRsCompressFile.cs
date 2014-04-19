using MO.Common.IO;
using MO.Common.Lang;
using MO.Content2d.Common;
using MO.Core.IO;

namespace MO.Content2d.Core
{
   //============================================================
   // <T>资源压缩文件。</T>
   //============================================================
   public class FRsCompressFile : FByteFile
   {
      // 压缩类型
      protected string _compressCd;

      // 数据长度
      protected int _dataLength;

      // 校验代码
      protected int _vertifyCode;

      // 分块大小
      protected int _blockSize;

      // 分块总数
      protected int _blockCount;

      //============================================================
      // <T>构造资源压缩文件。</T>
      //============================================================
      public FRsCompressFile() {
      }
      
      //============================================================
      // <T>构造资源压缩文件。</T>
      //
      // @param fileName 文件名称
      //============================================================
      public FRsCompressFile(string fileName) {
         LoadCompress(fileName);
      }

      //============================================================
      // <T>构造资源压缩文件。</T>
      //
      // @param compressCd 压缩类型
      // @param data 数据
      // @param blockSize 分块大小
      //============================================================
      public FRsCompressFile(string compressCd, FTypes<byte> data, int blockSize = 0) {
         _compressCd = compressCd;
         Assign(data);
         _blockSize = blockSize;
      }

      //============================================================
      // <T>获得或设置压缩类型。</T>
      //============================================================
      public string CompressCd {
         get { return _compressCd; }
         set { _compressCd = value; }
      }

      //============================================================
      // <T>获得或设置数据长度。</T>
      //============================================================
      public int DataLength {
         get { return _dataLength; }
         set { _dataLength = value; }
      }

      //============================================================
      // <T>获得或设置数据长度。</T>
      //============================================================
      public int BlockSize {
         get { return _blockSize; }
         set { _blockSize = value; }
      }

      //============================================================
      // <T>获得分块数目。</T>
      //============================================================
      public int BlockCount {
         get { return _blockCount; }
      }

      //============================================================
      // <T>获得校验代码。</T>
      //============================================================
      public int VertifyCode {
         get { return _vertifyCode; }
      }

      //============================================================
      // <T>加载压缩文件。</T>
      //
      // @param fileName 文件名称
      //============================================================
      public void LoadCompress(string fileName) {
         LoadFile(fileName);
         _position = 0;
         _compressCd = ERsCompress.ToString(ReadUint8());
         _dataLength = ReadInt32();
         _vertifyCode = ReadInt32();
         _blockCount = ReadInt32();
      }

      //============================================================
      // <T>压缩字节数据。</T>
      //
      // @return 字节数据
      //============================================================
      public byte[] CompressBytes() {
         byte[] data = null;
         // Deflate压缩
         string compressCd = _compressCd;
         switch(compressCd) {
            case ERsCompress.Deflate:
               using (FCompressFile file = new FCompressFile()) {
                  file.Append(_memory, 0, _length);
                  if (_blockSize > 0) {
                     data = file.BlockCompress(_blockSize);
                  } else {
                     data = file.Compress();
                  }
               }
               break;
            case ERsCompress.Lzma:
               using (FLzmaFile file = new FLzmaFile()) {
                  file.Append(_memory, 0, _length);
                  if (_blockSize > 0) {
                     data = file.BlockCompress(_blockSize);
                  } else {
                     data = file.Compress();
                  }
               }
               break;
            case ERsCompress.LzmaAlchemy:
               using (FLzmaFile file = new FLzmaFile()) {
                  file.Append(_memory, 0, _length);
                  if (_blockSize > 0) {
                     data = file.BlockCompressNative(_blockSize);
                  } else {
                     data = file.CompressNative();
                  }
               }
               break;
         }
         // 检查大小，如果压缩后更大，则不压缩数据
         if(data.Length > _length) {
            data = ToArray();
            compressCd = ERsCompress.None;
         }
         // 计算效验码
         _vertifyCode = RByte.ComputeHash32(data, 0, data.Length);
         // 写入文件
         byte[] result = null;
         using (FByteStream stream = new FByteStream()) {
            // 写入信息
            stream.WriteUint8((byte)ERsCompress.Parse(compressCd));
            stream.WriteInt32(_length);
            stream.WriteInt32(_vertifyCode);
            stream.WriteInt32(_blockSize);
            // 写入数据
            if((ERsCompress.None != compressCd) && (_blockSize > 0)) {
               stream.WriteBytes(data, 0, data.Length);
            } else {
               stream.WriteInt32(1);
               stream.WriteInt32(data.Length);
               stream.WriteBytes(data, 0, data.Length);
            }
            result = stream.ToArray();
         }
         return result;
      }

      //============================================================
      // <T>压缩数据文件。</T>
      //
      // @param fileName 文件名称
      //============================================================
      public void Compress(string fileName) {
         // 写入文件
         using (FByteFile file = new FByteFile()) {
            byte[] data = CompressBytes();
            file.Append(data);
            file.SaveFile(fileName);
         }
      }
   }
}
