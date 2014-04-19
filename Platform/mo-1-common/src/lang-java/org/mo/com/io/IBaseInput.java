package org.mo.com.io;

public interface IBaseInput<T>
{
   T read();

   int read(T[] data,
            int offset,
            int length);
}
