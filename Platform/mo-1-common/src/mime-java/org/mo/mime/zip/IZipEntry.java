package org.mo.mime.zip;

public interface IZipEntry
{
   String comment();

   long compressedSize();

   long crc();

   byte[] extra();

   boolean isDirectory();

   int method();

   String name();

   long size();

   long time();
}
