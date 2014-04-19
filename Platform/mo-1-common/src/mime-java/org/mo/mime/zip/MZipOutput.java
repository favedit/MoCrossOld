package org.mo.mime.zip;

import java.util.zip.ZipOutputStream;
import org.mo.com.lang.FFatalError;

public abstract class MZipOutput
      extends MZipStream
      implements
         IZipOutput
{
   protected ZipOutputStream _output;

   public void close(){
      try{
         _output.close();
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   public void finish(){
      try{
         _output.finish();
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   public void flush(){
      try{
         _output.flush();
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   public FZipEntry openEntry(String name){
      try{
         _entry = new FZipEntry(name);
         _output.putNextEntry(_entry._entry);
         return _entry;
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   public void setComment(String comment){
      _output.setComment(comment);
   }

   public void write(byte[] data){
      try{
         _output.write(data);
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   public void write(byte[] data,
                     int offset,
                     int length){
      try{
         _output.write(data, offset, length);
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }
}
