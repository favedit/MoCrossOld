package org.mo.com.io;

public interface IBaseOutput<T>
{
   public void write(T value);

   public void write(T[] data,
                     int offset,
                     int count);
}
