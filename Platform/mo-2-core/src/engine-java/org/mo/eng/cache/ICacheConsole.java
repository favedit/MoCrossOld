package org.mo.eng.cache;

public interface ICacheConsole
{
   ICache find(Class<?> clazz,
               String name);

   void register(Class<?> clazz,
                 String name,
                 ICache cache);
}
