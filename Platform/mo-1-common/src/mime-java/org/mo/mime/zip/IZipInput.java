package org.mo.mime.zip;

public interface IZipInput
{
   void close();

   boolean hasNext();

   IZipEntry nextEntry();

   int read();

   int read(byte[] data);

   int read(byte[] data,
            int offset,
            int length);
}
