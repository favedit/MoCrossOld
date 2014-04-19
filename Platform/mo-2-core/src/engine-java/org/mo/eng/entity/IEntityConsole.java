package org.mo.eng.entity;

import org.mo.com.lang.FObjects;
import org.mo.eng.entity.info.ISqlFieldInfo;

public interface IEntityConsole
{
   <V> V delete(Class<?> clazz,
                String logic,
                int ouid);

   <V> void fetch(Class<V> clazz,
                  FObjects<V> results,
                  Object entity,
                  ISqlFieldInfo[] fields);

   <V> V get(Class<V> clazz,
             int ouid);

   <V> V insert(Class<V> clazz,
                V entity);

   <V> V prepare(Class<V> clazz);

   void register(Class<?> clsAccess,
                 Class<?> clsEntity);

   <V> V update(Class<V> clazz,
                V entity,
                int ouid);
}
