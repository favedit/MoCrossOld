package org.mo.com.io;

public interface IPersistentIo
{
   void saveToStream();

   void setUniqueKey(int key);

   void setUniqueName(String uniqueName);

   int uniqueKey();

   String uniqueName();
}
