package org.mo.com.io;

public interface IStream<T>
      extends
         IBaseInput<T>,
         IBaseOutput<T>
{
   public static enum EOffset{
      BEGINNING,
      BOF,
      CURRENT,
      END,
      EOF;
   };

   public long position();

   public void seek(EOffset eOffset);

   public void seek(EOffset eOffset,
                    long lPosition);

   public void seek(long lPosition);

   public long size();
}
