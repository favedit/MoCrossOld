package org.mo.core.persistent.common;

public interface IPersistentContext
{
   String path();

   int nameAlloc();

   int nameStart();

   int nameBlock();

   int dataStart();

   int dataBlock();
}
