package org.mo.mime.zip;

public interface IZipOutput
{
   void close();

   FZipEntry currentEntry();

   void finish();

   void flush();

   FZipEntry openEntry(String name);

   void setComment(String comment);

   void write(byte[] data);

   void write(byte[] data,
              int offset,
              int length);
}
