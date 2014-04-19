using System;
using System.IO;
using System.IO.Compression;
using MO.Common.Lang;
using MO.Common.Collection;

namespace MO.Common.IO
{
   //============================================================
   // <T>压缩文件。</T>
   //
   // @class
   //============================================================
   public class FCompressFile : FByteFile
   {
      //============================================================
      // <T>构造压缩文件。</T>
      //============================================================
      public FCompressFile() {
      }

      //============================================================
      // <T>构造压缩文件。</T>
      //============================================================
      public FCompressFile(string fileName)
         : base(fileName) {
      }

      //============================================================
      // <T>构造压缩文件。</T>
      //============================================================
      public FCompressFile(FBytes data) {
         Assign(data.Memory, 0, data.Length);
      }

      //============================================================
      // <T>构造压缩文件。</T>
      //============================================================
      public FCompressFile(byte[] data, int offset, int length){
         Assign(data, offset, length);
      }

      //============================================================
      // <T>压缩数据为字节数组。</T>
      //
      // @param data 数据
      // @param offset 位置
      // @param length 长度
      // @return 字节数组
      //============================================================
      protected byte[] InnerCompress(byte[] data, int offset, int length){
         byte[] result = null;
         using (MemoryStream stream = new MemoryStream()){
            using (DeflateStream deflate = new DeflateStream(stream, CompressionMode.Compress, true)){
               deflate.Write(data, offset, length);
            }
            result = stream.ToArray();
         }
         return result;
      }
      
      //============================================================
      // <T>压缩数据保存为字节数据。</T>
      //
      // @return 字节数据
      //============================================================
      public byte[] Compress() {
         return InnerCompress(_memory, 0, _length);
      }

      //============================================================
      // <T>压缩数据保存为输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public void Compress(IOutput output) {
         byte[] data = InnerCompress(_memory, 0, _length);
         output.WriteBytes(data, 0, data.Length);
      }
      
      //============================================================
      // <T>压缩数据保存为指定文件。</T>
      //
      // @param fileName 文件名称
      //============================================================
      public void Compress(string fileName) {
         // 建立目录
         RDirectory.MakeDirectoriesForFile(fileName);
         // 输出文件
         byte[] data = InnerCompress(_memory, 0, _length);
         using (FByteFile file = new FByteFile()) {
            file.Assign(data, 0, data.Length);
            file.SaveFile(fileName);
         }
      }

      //============================================================
      // <T>分块压缩保存为字节数组。</T>
      //
      // @param blockSize 分块大小
      // @return 字节数组
      //============================================================
      public byte[] BlockCompress(int blockSize) {
         byte[] data = null;
         using (FByteStream stream = new FByteStream()) {
            BlockCompress(stream, blockSize);
            data = stream.ToArray();
         }
         return data;
      }
      
      //============================================================
      // <T>分块压缩保存为输出流。</T>
      //
      // @param output 输出流
      // @param blockSize 分块大小
      //============================================================
      public void BlockCompress(IOutput output, int blockSize) {
         // 检查参数
         if(0 == blockSize) {
            throw new FFatalException("Block size is zero.");
         }
         // 计算分割块数
         int blockCount = _length / blockSize;
         if(0 != (_length % blockSize)) {
            blockCount++;
         }
         output.WriteInt32(blockCount);
         // 分段压缩数据
         int position = 0;
         int remain = _length;
         for (int n = 0; n < blockCount; n++) {
            byte[] data = InnerCompress(_memory, position, Math.Min(blockSize, remain));
            position += blockSize;
            remain -= blockSize;
            // 输出压缩后数据
            output.WriteInt32(data.Length);
            output.WriteBytes(data, 0, data.Length);
         }
      }

      //============================================================
      // <T>分块压缩保存为字节数组指定文件。</T>
      //
      // @param fileName 文件名称
      // @param blockSize 分块大小
      //============================================================
      public void BlockCompress(string fileName, int blockSize) {
         // 检查参数
         if(null == fileName) {
            throw new FFatalException("File name is null.");
         }
         // 存储文件
         using (FByteFile file = new FByteFile()) {
            BlockCompress(file, blockSize);
            file.SaveFile(fileName);
         }
      }

      //============================================================
      // <T>压缩方法</T>
      //============================================================
      public void NotBlockCompress(int size) {
         if(0 == size) {
            throw new FFatalException("Size is zero.");
         }
         FByteFile file = new FByteFile();
         // 计算分割块数
         int splitCount = _length / size;
         if(0 != (_length % size)) {
            splitCount++;
         }
         // 写入压缩前的长度
         file.WriteUint32((UInt32)_length);
         // 写入分割块数
         file.WriteUint32((UInt32)splitCount);
         // 分段压缩数据
         int position = 0;
         int remain = _length;
         for(int n = 0; n < splitCount; n++) {
            MemoryStream stream = new MemoryStream();
            DeflateStream deflate = new DeflateStream(stream, CompressionMode.Compress, true);
            deflate.Write(_memory, position, Math.Min(size, remain));
            position += size;
            remain -= size;
            deflate.Close();
            // 输出压缩后数据
            int compressLength = (int)stream.Length;
            file.WriteUint32((UInt32)compressLength);
            file.WriteBytes(stream.ToArray(), 0, compressLength);
            stream.Close();
         }
         Clear();
         WriteBytes(file.ToArray(), 0, file.Length);
      }

      //============================================================
      // <T>从指定压缩文件中解压。</T>
      //
      // @param fileName 文件名称
      //============================================================
      public void Decompress(string fileName) {
         int bufferLength = RInt.SIZE_16K;
         byte[] buffer = new byte[bufferLength];
         using(FileStream stream = new FileStream(fileName, FileMode.Open)) {
            using(DeflateStream deflate = new DeflateStream(stream, CompressionMode.Decompress, true)) {
               while(true) {
                  int readed = deflate.Read(buffer, 0, bufferLength);
                  if(0 == readed) {
                     break;
                  }
                  Append(buffer, 0, readed);
                  if(readed != bufferLength) {
                     break;
                  }
               }
            }
         }
      }

      //============================================================
      // <T>解压缩方法</T>
      //
      // @param fileName 文件名称
      //============================================================
      public void BlockDecompress(string fileName) {
         // 检查参数
         if(null == fileName) {
            throw new FFatalException("File name is null.");
         }
         FByteFile file = new FByteFile();
         file.LoadFile(fileName);
         // 读取出原来的长度
         int length = file.ReadInt32();
         byte[] buffer = new byte[length];
         // 读取出分割的块数 
         int spiltCount = file.ReadInt32();
         int remain = file.Position;
         for(int i = 0; i < spiltCount; i++) {
            using(MemoryStream stream = new MemoryStream()) {
               int blockLength = file.ReadInt32();
               remain += 4;
               // 定长的数据
               stream.Write(file.Memory, remain, blockLength);
               file.Position += blockLength;
               remain += blockLength;
               stream.Position = 0;
               using(DeflateStream deflate = new DeflateStream(stream, CompressionMode.Decompress, true)) {
                  int readed = deflate.Read(buffer, 0, length);
                  if(readed == 0) {
                     throw new FFatalException("");
                  }
                  Append(buffer, 0, readed);
               }
            }
         }
      }
   }
}
