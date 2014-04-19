using System;
using System.IO;
using MO.Common.Collection;
using MO.Common.IO;
using MO.Common.Lang;
using SevenZip;
using SevenZip.Compression.LZMA;
using MoCompress;

namespace MO.Core.IO
{
   //============================================================
   public class FLzmaFile : FByteFile
   {
      protected static object _locker = new object();

      static int dictionary = 1 << 23;

      static bool eos = false;

      static CoderPropID[] propIDs = {
         CoderPropID.DictionarySize,
			CoderPropID.PosStateBits,
			CoderPropID.LitContextBits,
			CoderPropID.LitPosBits,
			CoderPropID.Algorithm,
			CoderPropID.NumFastBytes,
			CoderPropID.MatchFinder,
			CoderPropID.EndMarker};

      static object[] properties = {
         (Int32)(dictionary),
			(Int32)(2),
			(Int32)(3),
			(Int32)(0),
			(Int32)(2),
			(Int32)(128),
			"bt4",
			eos};
      
      //============================================================
      public FLzmaFile() {
      }

      //============================================================
      // <T>构造字节文件。</T>
      //
      // @param fileName 文件名称
      //============================================================
      public FLzmaFile(string fileName)
         : base(fileName) {
      }

      //============================================================
      // <T>构造字节文件。</T>
      //
      // @param fileName 文件名称
      //============================================================
      public FLzmaFile(FBytes data){
         WriteBytes(data.Memory, 0, data.Length);
      }

      //============================================================
      // <T>构造字节文件。</T>
      //
      // @param fileName 文件名称
      //============================================================
      public FLzmaFile(byte[] data, int offset, int length) {
         WriteBytes(data, offset, length);
      }

      //============================================================
      // <T>压缩数据为字节数组。</T>
      //
      // @param data 数据
      // @param offset 位置
      // @param length 长度
      // @return 字节数组
      //============================================================
      protected byte[] InnerCompress(byte[] data, int offset, int length) {
         byte[] result = null;
         // 创建数据流
         using (MemoryStream inputStream = new MemoryStream(data, offset, length)) {
            using(MemoryStream outputStream = new MemoryStream()){
               // 压缩数据
               Encoder encoder = new Encoder();
               encoder.SetCoderProperties(propIDs, properties);
               encoder.WriteCoderProperties(outputStream);
               // 写入长度
               long size = length;
               for (int i = 0; i < 8; i++) {
                  outputStream.WriteByte((Byte)(size >> (8 * i)));
               }
               encoder.Code(inputStream, outputStream, -1, -1, null);
               // 写入文件
               result = outputStream.ToArray();
            }
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
      // <T>压缩数据保存为字节数据。</T>
      //
      // @return 字节数据
      //============================================================
      public byte[] CompressNative() {
         return InnerCompressNative(_memory, 0, _length);
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
         if (0 == blockSize) {
            throw new FFatalException("Block size is zero.");
         }
         // 计算分割块数
         int blockCount = _length / blockSize;
         if (0 != (_length % blockSize)) {
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
      // <T>压缩数据为字节数组。</T>
      //
      // @param data 数据
      // @param offset 位置
      // @param length 长度
      // @return 字节数组
      //============================================================
      protected byte[] InnerCompressNative(byte[] data, int offset, int length) {
         //RCompressLzma::Compress(array<System::Byte>^ output, int outputOffset, int outputLength, array<System::Byte>^ input, int inputOffset, int inputLength)
         int resultSize = length + 1024 * 16;
         byte[] result = new byte[resultSize];
         int compressLength = RCompressLzma.Compress(result, 0, resultSize, _memory, offset, length);
         byte[] resultData = new byte[compressLength];
         Array.Copy(result, resultData, compressLength);
         return resultData;
      }

      //============================================================
      // <T>分块压缩保存为字节数组。</T>
      //
      // @param blockSize 分块大小
      // @return 字节数组
      //============================================================
      public byte[] BlockCompressNative(int blockSize) {
         byte[] data = null;
         using (FByteStream stream = new FByteStream()) {
            BlockCompressNative(stream, blockSize);
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
      public void BlockCompressNative(IOutput output, int blockSize) {
         // 检查参数
         if (0 == blockSize) {
            throw new FFatalException("Block size is zero.");
         }
         // 计算分割块数
         int blockCount = _length / blockSize;
         if (0 != (_length % blockSize)) {
            blockCount++;
         }
         output.WriteInt32(blockCount);
         // 分段压缩数据
         int position = 0;
         int remain = _length;
         for (int n = 0; n < blockCount; n++) {
            byte[] data = InnerCompressNative(_memory, position, Math.Min(blockSize, remain));
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
         if (null == fileName) {
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
      public void NotCompress(int size) {    
         if(0 == size) {
            throw new FFatalException("Size is zero.");
         }
         // 构建缓冲数据
         byte[] bytes = new byte[_length + RInt.SIZE_16K];
         FByteFile file = new FByteFile();
         // 计算分割块数
         int splitCount = _length / size;
         if(0 != (_length % size)) {
            splitCount++;
         }
         // 写入压缩前数据总长度
         file.WriteInt32(_length);
         // 写入分割块数
         file.WriteInt32(splitCount);
         // 分段压缩数据
         //int position = 0;
         int remain = _length;
         for(int n = 0; n < splitCount; n++) {
            //int compressLength = RCompressLzma.Compress(bytes,0,bytes.Length,_memory,position,Math.Min(size,remain));
            //if(0 == compressLength) {
            //   throw new FFatalException("Compress failure. (compress_length={0})",compressLength);
            //}
            //position += size;
            //remain -= size;
            //// 输出压缩后数据
            //file.WriteInt32(compressLength);
            //file.WriteBytes(bytes,0,compressLength);
         }
         Clear();
         WriteBytes(file.ToArray(),0,file.Length);
      }

      //============================================================
      // <T>解压方法</T>
      //============================================================
      public void Uncompress(string fileName) {
         // 加载文件 
         LoadFile(fileName);
         // 读出文件 
         uint size = ReadUint32();
         // 设置大小
         size += RInt.SIZE_16K;
         byte[] bytes = new byte[size];
         // 解压 
         //int length = RCompressLzma.Uncompress(bytes, 0, bytes.Length, _memory, 0, _length);
         // 存储数据
         //Clear();
         //WriteBytes(bytes, 0, length);
         MemoryStream newInStream = new MemoryStream(_memory, 0, _length);
         newInStream.Position = 0;
         MemoryStream newOutStream = new MemoryStream();
         byte[] properties2 = new byte[5];
         if (newInStream.Read(properties2, 0, 5) != 5){
            throw (new Exception("input .lzma is too short"));
         }
         long outSize = 0;
         for (int i = 0; i < 8; i++) {
            int v = newInStream.ReadByte();
            if (v < 0)
               throw (new Exception("Can't Read 1"));
            outSize |= ((long)(byte)v) << (8 * i);
         }
         Decoder decoder = new Decoder();
         decoder.SetDecoderProperties(properties2);
         long compressedSize = newInStream.Length - newInStream.Position;
         decoder.Code(newInStream, newOutStream, compressedSize, outSize, null);
         byte[] result = newOutStream.ToArray();
         Clear();
         WriteBytes(result, 0, result.Length);
      }
   }
}
