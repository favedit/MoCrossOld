package org.mo.com.net;

import java.io.OutputStream;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObject;

//============================================================
// <T>网络输出流。</T>
//============================================================
public class FSocketOutput
      extends FObject
{
   public static int BLOCK_SIZE = 8196;

   // 网络链接
   protected FSocket _socket;

   // 输出流
   protected OutputStream _outputStream;

   // 写入长度
   protected int _writeLength;

   //============================================================
   // <T>构造网络输出流。</T>
   //
   // @param socket 网络链接
   //============================================================
   public FSocketOutput(FSocket socket){
      try{
         _socket = socket;
         _outputStream = socket.nativeOutputStream();
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
   // <T>写入数据内容。</T>
   //
   // @param data 数据
   //============================================================
   public void write(byte[] data){
      try{
         if(data != null && data.length > 0){
            _writeLength += data.length;
            _outputStream.write(data);
         }
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   //============================================================
   // <T>写入数据内容。</T>
   //
   // @param data 数据
   // @param length 长度
   //============================================================
   public void write(byte[] data,
                     int length){
      try{
         if(data != null && data.length > 0){
            _writeLength += data.length;
            _outputStream.write(data, 0, length);
         }
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   //============================================================
   // <T>写入数据内容。</T>
   //
   // @param data 数据
   // @param offset 位置
   // @param length 长度
   //============================================================
   public void write(byte[] data,
                     int offset,
                     int length){
      try{
         if(data != null && data.length > 0){
            _writeLength += length;
            _outputStream.write(data, offset, length);
         }
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   //============================================================
   // <T>刷新数据流。</T>
   //============================================================
   public void flush(){
      try{
         _outputStream.flush();
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   //============================================================
   // <T>关闭输入流。</T>
   //============================================================
   public void close(){
      try{
         _outputStream.close();
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }
   //public boolean write(int nValue, int nDigits) {
   //return write(FStringUtil.leftPad(Integer.toString(nValue), nDigits, "0"));
   //}
   //
   //   public void write(int value){
   //      try{
   //         _writeLength += 4;
   //         _outputStream.write(value);
   //      }catch(Exception e){
   //         throw new FFatalError(e);
   //      }
   //   }
   //
   //   public boolean write(String value){
   //      try{
   //         if(value != null){
   //            byte[] bytes = value.getBytes();
   //            if(bytes.length > 0){
   //               _writeLength += bytes.length;
   //               _outputStream.write(bytes);
   //               return true;
   //            }
   //         }
   //         return false;
   //      }catch(Exception e){
   //         throw new FFatalError(e);
   //      }
   //   }
   //
   //   public boolean write(StringBuffer value){
   //      try{
   //         if(value != null){
   //            byte[] bytes = value.toString().getBytes();
   //            if(bytes.length > 0){
   //               _writeLength += bytes.length;
   //               _outputStream.write(bytes);
   //               return true;
   //            }
   //         }
   //         return false;
   //      }catch(Exception e){
   //         throw new FFatalError(e);
   //      }
   //   }
   //
   //   //   public boolean write(FInputStream oInputStream, int nLength) {
   //   //      try {
   //   //         if (oInputStream != null && nLength > 0) {
   //   //            int nReadLength = 0;
   //   //            int nTranslateLenth = 0;
   //   //            byte[] arCache = new byte[BLOCK_SIZE];
   //   //            while (true) {
   //   //               nReadLength = oInputStream.read(arCache);
   //   //               nTranslateLenth += nReadLength;
   //   //               if (nReadLength > 0) {
   //   //                  _outputStream.write(arCache, 0, nReadLength);
   //   //                  if (nTranslateLenth >= nLength) {
   //   //                     break;
   //   //                  }
   //   //               } else if (nReadLength == -1) {
   //   //                  break;
   //   //               }
   //   //            }
   //   //            _outputStream.flush();
   //   //            _writeLength += nLength;
   //   //            return true;
   //   //         }
   //   //         return false;
   //   //      } catch (Exception e) {
   //   //         throw new FFatalException(e);
   //   //      }
   //   //   }
   //
   //   public int writeLength(){
   //      return _writeLength;
   //   }
}
