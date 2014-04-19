package org.mo.mime.zip;

import java.util.zip.ZipEntry;

public class FZipEntry
      implements
         IZipEntry
{
   protected ZipEntry _entry;

   protected FZipEntry(String name){
      _entry = new ZipEntry(name);
   }

   protected FZipEntry(ZipEntry entry){
      _entry = entry;
   }

   public String comment(){
      return _entry.getComment();
   }

   public long compressedSize(){
      return _entry.getCompressedSize();
   }

   public long crc(){
      return _entry.getCrc();
   }

   public byte[] extra(){
      return _entry.getExtra();
   }

   public boolean isDirectory(){
      return _entry.isDirectory();
   }

   public int method(){
      return _entry.getMethod();
   }

   public String name(){
      return _entry.getName();
   }

   public void setComment(String comment){
      _entry.setComment(comment);
   }

   public void setCompressedSize(long compressedSize){
      _entry.setCompressedSize(compressedSize);
   }

   public void setCrc(long crc){
      _entry.setCrc(crc);
   }

   public void setExtra(byte[] extra){
      _entry.setExtra(extra);
   }

   public void setMethod(int method){
      _entry.setMethod(method);
   }

   public void setSize(long size){
      _entry.setSize(size);
   }

   public void setTime(long time){
      _entry.setTime(time);
   }

   public long size(){
      return _entry.getSize();
   }

   public long time(){
      return _entry.getTime();
   }
}
