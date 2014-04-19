package org.mo.mime.zip;

import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.mo.com.lang.FFatalError;

public abstract class MZipInput
      extends MZipStream
      implements
         IZipInput
{
   protected ZipInputStream _input;

   protected FZipEntry _nextEntry;

   public void close(){
      try{
         _input.close();
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   public boolean hasNext(){
      return (null != _nextEntry);
   }

   public IZipEntry nextEntry(){
      try{
         ZipEntry entry = _input.getNextEntry();
         if(null != entry){
            _entry = new FZipEntry(entry);
            return _entry;
         }
         return null;
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   protected void open(){
      try{
         ZipEntry entry = _input.getNextEntry();
         if(null != entry){
            _entry = new FZipEntry(entry);
         }
         _nextEntry = null;
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   public int read(){
      try{
         return _input.read();
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   public int read(byte[] data){
      try{
         return _input.read(data);
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   public int read(byte[] data,
                   int offset,
                   int length){
      try{
         return _input.read(data, offset, length);
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }
}
