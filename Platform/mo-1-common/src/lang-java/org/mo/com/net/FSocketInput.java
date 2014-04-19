package org.mo.com.net;

import java.io.InputStream;
import org.mo.com.io.FByteStream;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObject;
import org.mo.com.lang.FString;
import org.mo.com.lang.RInteger;

//============================================================
// <T>网络输入流。</T>
//============================================================
public class FSocketInput
      extends FObject
{
   private final int _blockSize = RInteger.SIZE_64K;

   // 网络链接
   protected FSocket _socket;

   // 输入流
   protected InputStream _inputStream;

   // 读取长度
   protected int _readLength;

   //============================================================
   // <T>构造网络输入流。</T>
   //
   // @param socket 网络链接
   //============================================================
   public FSocketInput(FSocket socket){
      try{
         _socket = socket;
         _inputStream = socket.nativeInputStream();
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   //============================================================
   // <T>获得网络链接。</T>
   //
   // @return 网络链接
   //============================================================
   public FSocket socket(){
      return _socket;
   }

   //============================================================
   // <T>读取一个字节。</T>
   //
   // @return 字节
   //============================================================
   public byte readByte(){
      try{
         _readLength++;
         return (byte)_inputStream.read();
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   //============================================================
   // <T>读取一个短整数。</T>
   //
   // @return 短整数
   //============================================================
   public short readShort(){
      try{
         int data = _inputStream.read();
         data |= _inputStream.read() << 8;
         return (short)data;
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   //============================================================
   // <T>读取一个整数。</T>
   //
   // @return 整数
   //============================================================
   public int readInteger(){
      try{
         int data = _inputStream.read();
         data |= _inputStream.read() << 8;
         data |= _inputStream.read() << 16;
         data |= _inputStream.read() << 24;
         return data;
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   //============================================================
   // <T>读取数据内容。</T>
   //
   // @param data 数据
   // @return 读取长度
   //============================================================
   public int read(byte[] data){
      try{
         int length = _inputStream.read(data);
         if(length != -1){
            _readLength += length;
         }
         return length;
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   //============================================================
   // <T>读取数据内容。</T>
   //
   // @param data 数据
   // @param length 长度
   // @return 读取长度
   //============================================================
   public int read(byte[] data,
                   int length){
      try{
         int read = _inputStream.read(data, 0, length);
         if(read != -1){
            _readLength += read;
         }
         return read;
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   //============================================================
   // <T>读取数据内容。</T>
   //
   // @param data 数据
   // @param offset 位置
   // @param length 长度
   // @return 读取长度
   //============================================================
   public int read(byte[] data,
                   int offset,
                   int length){
      try{
         int read = _inputStream.read(data, offset, length);
         if(read != -1){
            _readLength += read;
         }
         return read;
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   //============================================================
   // <T>读取数据内容。</T>
   //
   // @param length 长度
   // @return 数据
   //============================================================
   public byte[] read(int length){
      FByteStream bytes = new FByteStream();
      read(bytes, length);
      return bytes.toArray();
   }

   //============================================================
   // <T>读取数据内容。</T>
   //
   // @param stream 数据流
   // @param length 长度
   //============================================================
   public void read(FByteStream stream,
                    int length){
      try{
         int read = 0;
         int block = 0;
         int remain = length;
         byte[] memory = new byte[_blockSize];
         while(remain > 0){
            block = (remain > _blockSize) ? _blockSize : remain;
            read = _inputStream.read(memory, 0, block);
            stream.append(memory, 0, read);
            remain -= read;
         }
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   //============================================================
   // <T>读取一行文本内容。</T>
   //
   // @return 字符串
   //============================================================
   public FString readLine(){
      FString line = readLine(RInteger.SIZE_4M);
      if(line.length() == RInteger.SIZE_4M){
         throw new FFatalError("Read line max buffer.");
      }
      return line;
   }

   //============================================================
   // <T>读取一行文本内容。</T>
   //
   // @param limit 限制长度
   // @return 字符串
   //============================================================
   public FString readLine(int limit){
      FString line = new FString();
      try{
         for(int n = 0; n < limit; n++){
            char value = (char)_inputStream.read();
            if(value == '\r'){
               continue;
            }
            if(value == '\n'){
               break;
            }
            line.append(value);
         }
         return line;
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   //============================================================
   // <T>关闭输入流。</T>
   //============================================================
   public void close(){
      try{
         _inputStream.close();
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }
   //   public int readLength(){
   //      return _readLength;
   //   }
   //
   //   public String readString(int length){
   //      return readString(length, null);
   //   }
   //
   //   public String readString(int length,
   //                            String charset){
   //      try{
   //         byte[] data = new byte[length];
   //         int nReadLength = _inputStream.read(data);
   //         if(nReadLength != -1){
   //            _readLength += nReadLength;
   //         }
   //         return (charset == null) ? new String(data) : new String(data, charset);
   //      }catch(Exception e){
   //         throw new FFatalError(e);
   //      }
   //   }
}
