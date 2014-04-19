package org.mo.com.io;

import java.io.RandomAccessFile;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObject;

public class FFileStream
      extends FObject
{
   protected RandomAccessFile m_oRaFile = null;

   public FFileStream(){
      //m_oRaFile.writeChars();.writeBytes("");
   }

   public FFileStream(String sFileName){
      try{
         m_oRaFile = new RandomAccessFile(sFileName, "rws");
      }catch(Exception exception){
         throw new FFatalError(exception);
      }
   }
   //   public void fetchByAddess(FFileAddress oAddress,
   //                             IPersistent piPersistent)
   //         throws FException {
   //      FMemoryStream piStream = new FMemoryStream();
   //      try {
   //         m_oRaFile.seek(oAddress.getAddress());
   //         byte[] oLenLength = new byte[1];
   //         m_oRaFile.read(oLenLength);
   //         int nLenLength = FIntegerUtil.getInteger(oLenLength);
   //         byte[] oLength = new byte[nLenLength];
   //         m_oRaFile.read(oLength);
   //         int nLength = FIntegerUtil.getInteger(oLength);
   //         piStream.setSize(nLength);
   //         m_oRaFile.read(piStream.getMemory(), 0, nLength);
   //         piPersistent.loadFromStream(piStream);
   //      } catch (Exception oException) {
   //         throw new FFatalException(this, oException);
   //      }
   //      piPersistent.loadFromStream(piStream);
   //   }
   //
   //   public void doInsert(FFileAddress oAddress,
   //                        IPersistent piPersistent)
   //         throws FException {
   //      FMemoryStream piStream = new FMemoryStream();
   //      piPersistent.saveToStream(piStream);
   //      try {
   //         oAddress.setAddress(m_oRaFile.length());
   //         m_oRaFile.seek(oAddress.getAddress());
   //         byte[] oLength = FIntegerUtil.getBytes(piStream.getSize(), 0);
   //         byte[] oLenLength = new byte[] {(byte) oLength.length};
   //         m_oRaFile.write(oLenLength);
   //         m_oRaFile.write(oLength);
   //         m_oRaFile.write(piStream.getMemory(), 0, piStream.getSize());
   //      } catch (Exception oException) {
   //         throw new FFatalException(this, oException);
   //      }
   //   }
   //
   //   public void doUpdate(FFileAddress oAddress,
   //                        IPersistent piPersistent) {
   //   }
   //
   //   public void doDelete(FFileAddress oAddress,
   //                        IPersistent piPersistent) {
   //   }
   //
   //   public boolean next(FFileAddress oAddress,
   //                       IPersistent piPersistent) {
   //      return true;
   //   }
   //
   //   public void close()
   //         throws FException {
   //      try {
   //         m_oRaFile.close();
   //      } catch (Exception oException) {
   //         throw new FFatalException(this, oException);
   //      }
   //      super.close();
   //   }
}
