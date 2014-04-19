package org.mo.core.persistent;

import org.mo.com.lang.FDictionary;
import org.mo.core.persistent.loader.FPersistentDescriptor;

public interface IPersistentConsole
{
   Object persist(Class<?> clazz,
                  Object item);

   Object modify(Class<?> clazz,
                 Object item);

   Object remove(Class<?> clazz,
                 Object item);

   FSegment findSegment(Class<?> clazz);

   FSegment syncSegment(Class<?> clazz);

   Object find(Class<?> clazz,
               int oid);

   Object find(Class<?> clazz,
               String name);

   boolean exists(Class<?> clazz,
                  String name);

   FDictionary<?> list(Class<?> clazz);

   FPersistentDescriptor descriptor(Class<?> clazz);
}
