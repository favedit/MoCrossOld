package org.mo.com.io;

public class RStream
{
   //   public static int AVAILABLE_TRIES = 32;
   //
   //   public static int BLOCK_SIZE = 1024;
   //
   //   public static boolean compare(byte[] arData1,
   //                                 byte[] arData2){
   //      if (arData1 != null && arData2 != null){
   //         if (arData1.length == arData2.length){
   //            for(int n = 0; n < arData1.length; n++){
   //               if (arData1[n] != arData2[n]){
   //                  return false;
   //               }
   //            }
   //            return true;
   //         }
   //      }
   //      return false;
   //   }
   //
   //   public byte[] replace(byte[] arSource,
   //                         byte[] arSub,
   //                         byte[] arDes){
   //      return null;
   //   }
   //
   //   public static FByteStream readBytes(InputStream oStream)
   //         throws FException{
   //      try{
   //         int nReadLength = 0;
   //         byte[] arBuffer = new byte[BLOCK_SIZE];
   //         FByteStream oBuffer = new FByteStream();
   //         while(true){
   //            nReadLength = oStream.read(arBuffer);
   //            if (nReadLength == arBuffer.length){
   //               oBuffer.append(arBuffer);
   //            }else{
   //               if (nReadLength > 0){
   //                  oBuffer.append(arBuffer, 0, nReadLength);
   //               }
   //               break;
   //            }
   //         }
   //         return oBuffer;
   //      }catch(Exception oException){
   //         throw new FFatalException(FStreamUtil.class, "readBytes", oException);
   //      }
   //   }
   //
   //   public static StringBuffer readAsString(InputStream oInputStream)
   //         throws FException{
   //      try{
   //         int nReadLength = 0;
   //         byte[] arInput = new byte[BLOCK_SIZE];
   //         StringBuffer oBuffer = new StringBuffer();
   //         while(true){
   //            nReadLength = oInputStream.read(arInput);
   //            if (nReadLength == arInput.length){
   //               oBuffer.append(new String(arInput));
   //            }else{
   //               if (nReadLength > 0){
   //                  oBuffer.append(new String(arInput, 0, nReadLength));
   //               }
   //               break;
   //            }
   //         }
   //         return oBuffer;
   //      }catch(Exception oException){
   //         throw new FFatalException(FStream.class, "readStreamAsString",
   //               oException);
   //      }
   //   }
   //
   //   public static byte[] readStream(InputStream oInputStream)
   //         throws FException{
   //      try{
   //         int nReadLength = 0;
   //         byte[] arInput = new byte[BLOCK_SIZE];
   //         StringBuffer oBuffer = new StringBuffer();
   //         while(true){
   //            nReadLength = oInputStream.read(arInput);
   //            if (nReadLength == arInput.length){
   //               oBuffer.append(new String(arInput));
   //            }else{
   //               if (nReadLength > 0){
   //                  oBuffer.append(new String(arInput, 0, nReadLength));
   //               }
   //               break;
   //            }
   //         }
   //         return oBuffer.toString().getBytes();
   //      }catch(Exception oException){
   //         throw new FFatalException(FStream.class, "readStream", oException);
   //      }
   //   }
   //
   //   public static boolean writeStream(OutputStream oOutputStream,
   //                                     byte arBuffer[])
   //         throws FException{
   //      try{
   //         oOutputStream.write(arBuffer);
   //         return true;
   //      }catch(Exception oException){
   //         throw new FFatalException(FStream.class, "writeStream", oException);
   //      }
   //   }
   //
   //   public static boolean pipeStream(InputStream oInputStream,
   //                                    OutputStream oOutputStream,
   //                                    int nLength)
   //         throws FException{
   //      try{
   //         if (nLength > 0){
   //            int nReadLength = 0;
   //            int nTranslateLenth = 0;
   //            byte[] arCache = new byte[BLOCK_SIZE];
   //            while(true){
   //               nReadLength = oInputStream.read(arCache);
   //               nTranslateLenth += nReadLength;
   //               if (nReadLength > 0){
   //                  oOutputStream.write(arCache, 0, nReadLength);
   //                  oOutputStream.flush();
   //                  if (nTranslateLenth == nLength){
   //                     break;
   //                  }
   //               }else{
   //                  break;
   //               }
   //            }
   //         }
   //         return true;
   //      }catch(Exception oException){
   //         throw new FFatalException(FStream.class, "pipeStream", oException);
   //      }
   //   }
   //
   //   public static boolean skipAvailable(InputStream oInputStream)
   //         throws FException{
   //      try{
   //         int nTryCount = 0;
   //         int nAvailable = oInputStream.available();
   //         while(nAvailable > 0 && nTryCount++ < AVAILABLE_TRIES){
   //            oInputStream.skip(nAvailable);
   //            nAvailable = oInputStream.available();
   //         }
   //      }catch(Exception oException){
   //         throw new FFatalException(FStream.class, "skipAvailable", oException);
   //      }
   //      return true;
   //   }
}
