package org.mo.com.io;

public class ROutput
{
   public static int BLOCK_SIZE = 1024;
   //   public static void write(OutputStream oOutputStream,
   //                            byte[] arData)
   //         throws FException{
   //      try{
   //         int nPosition = 0;
   //         int nBlock = arData.length / BLOCK_SIZE;
   //         int nRemain = arData.length % BLOCK_SIZE;
   //         for(int n = 0; n < nBlock; n++){
   //            oOutputStream.write(arData, nPosition, BLOCK_SIZE);
   //            nPosition += BLOCK_SIZE;
   //         }
   //         if(nRemain > 0){
   //            oOutputStream.write(arData, nPosition, nRemain);
   //         }
   //      }catch (Exception oException){
   //         throw new FFatalException(FOutputUtil.class, "write", oException);
   //      }
   //   }
}
