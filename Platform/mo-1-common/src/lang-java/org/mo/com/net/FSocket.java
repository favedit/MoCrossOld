package org.mo.com.net;

import java.io.InputStream;
import java.io.OutputStream;
import org.mo.com.lang.FObject;
import org.mo.com.lang.FUnsupportMethodError;

//============================================================
// <T>网络链接。</T>
//============================================================
public class FSocket
      extends FObject
{
   public static final int CACHE_SIZE = 8192;

   public static final int CONNECT_TRIES = 32;

   public static final String LOCALHOST_IP = "127.0.0.1";

   // 网络输入流
   protected FSocketInput _input;

   // 网络输出流
   protected FSocketOutput _output;

   //============================================================
   // <T>构造网络链接。</T>
   //============================================================
   public FSocket(){
   }

   //============================================================
   // <T>获得原生输入流。</T>
   //
   // @return 输入流
   //============================================================
   public InputStream nativeInputStream(){
      throw new FUnsupportMethodError();
   }

   //============================================================
   // <T>获得原生输出流。</T>
   //
   // @return 输出流
   //============================================================
   public OutputStream nativeOutputStream(){
      throw new FUnsupportMethodError();
   }

   //============================================================
   // <T>获得网络输入流。</T>
   //
   // @return 网络输入流
   //============================================================
   public FSocketInput input(){
      if(null == _input){
         _input = new FSocketInput(this);
      }
      return _input;
   }

   //============================================================
   // <T>获得网络输出流。</T>
   //
   // @return 网络输出流
   //============================================================
   public FSocketOutput output(){
      if(null == _output){
         _output = new FSocketOutput(this);
      }
      return _output;
   }

   //============================================================
   // <T>关闭网络链接。</T>
   //============================================================
   public void close(){
   }
   //   public boolean sendFile(String sFileName)
   //         throws FException{
   //      try {
   //         File oFile = new File(sFileName);
   //         BufferedInputStream oFileIS = new BufferedInputStream(
   //               new FileInputStream(oFile));
   //         FOutputStream oSocketOS = getOutputStream();
   //         int nFileSize = 0;
   //         int nRemainSize = (int) oFile.length();
   //         byte[] arCache = new byte[CACHE_SIZE];
   //         while (true) {
   //            //Thread.currentThread().sleep(50);
   //            nFileSize = (nRemainSize >= CACHE_SIZE) ? CACHE_SIZE : nRemainSize;
   //            oFileIS.read(arCache, 0, nFileSize);
   //            if (!oSocketOS.write(arCache, 0, nFileSize)) {
   //               break;
   //            }
   //            nRemainSize -= nFileSize;
   //            if (nRemainSize == 0) {
   //               break;
   //            }
   //         }
   //         oFileIS.close();
   //         return true;
   //      } catch (Exception oException) {
   //         throw new FFatalException(this, "sendFile", oException);
   //      }
   //   }
   //   public boolean receiveFile(String sFileName)
   //         {
   //      try {
   //         File oFile = new File(sFileName);
   //         BufferedOutputStream oFileOS = new BufferedOutputStream(
   //               new FileOutputStream(oFile));
   //         FInputStream oSocketIS = getInputStream();
   //         int nReadSize = 0;
   //         int nRemainSize = (int) oFile.length();
   //         byte[] arCache = new byte[CACHE_SIZE];
   //         while (true) {
   //            nReadSize = (nRemainSize >= CACHE_SIZE) ? CACHE_SIZE : nRemainSize;
   //            if(oSocketIS.read(arCache, 0, nReadSize) != nReadSize){
   //               break;
   //            }
   //            oFileOS.write(arCache, 0, nReadSize);
   //            nRemainSize -= nReadSize;
   //            if (nRemainSize == 0) {
   //               break;
   //            }
   //         }
   //         return true;
   //      } catch (Exception oException) {
   //         throw new FFatalException(this, oException);
   //      }
   //   }
   //
   //   public boolean checkStream()
   //         {
   //      try {
   //         if (m_oSocket.getInputStream().hashCode() > 0 &&
   //             m_oSocket.getOutputStream().hashCode() > 0) {
   //            return true;
   //         }
   //         return false;
   //      } catch (Exception oException) {
   //         throw new FFatalException(this, oException);
   //      }
   //   }
}
