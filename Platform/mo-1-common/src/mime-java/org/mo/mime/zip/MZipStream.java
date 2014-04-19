package org.mo.mime.zip;

public abstract class MZipStream
{
   protected FZipEntries _entries = new FZipEntries();

   protected FZipEntry _entry;

   public FZipEntry currentEntry(){
      return _entry;
   }

   public IZipEntries entries(){
      return _entries;
   }
}
