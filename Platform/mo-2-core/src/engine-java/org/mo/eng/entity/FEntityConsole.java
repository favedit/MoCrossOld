package org.mo.eng.entity;

import org.mo.com.lang.FDictionary;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObjects;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.core.aop.face.ALink;
import org.mo.core.aop.face.AProperty;
import org.mo.core.bind.IBindConsole;
import org.mo.eng.entity.descriptor.FEntityLoader;
import org.mo.eng.entity.info.ISqlFieldInfo;

public class FEntityConsole
      implements
         IEntityConsole
{
   @SuppressWarnings("unused")
   private static ILogger _logger = RLogger.find(FEntityPool.class);

   @ALink
   protected IBindConsole _bindConsole;

   @AProperty
   protected int _cacheCount = 100;

   protected FEntityLoader _loader = new FEntityLoader(this);

   @SuppressWarnings("rawtypes")
   private final FDictionary<IEntityDao> _map = new FDictionary<IEntityDao>(IEntityDao.class);

   @SuppressWarnings("rawtypes")
   @Override
   public <V> V delete(Class<?> clazz,
                       String logic,
                       int ouid){
      IEntityDao pool = _map.get(clazz.getName());
      if(null == pool){
         throw new FFatalError("Unregister entity (class={0})", clazz.getName());
      }
      //return (V) pool.doDeleteRow(logic, null, ouid);
      return null;
   }

   @Override
   public <V> void fetch(Class<V> clazz,
                         FObjects<V> results,
                         Object entity,
                         ISqlFieldInfo[] fields){
      //      FEntityPool pool = _map.get(clazz.getName());
      //      if(null == pool){
      //         throw new FFatalError("Unregister entity (class={0})", clazz.getName());
      //      }
      //      pool.fetch(results, (IEntity) entity, fields);
   }

   @Override
   public <V> V get(Class<V> clazz,
                    int ouid){
      //      FEntityPool pool = _map.get(clazz.getName());
      //      if(null == pool){
      //         throw new FFatalError("Unregister entity (class={0})", clazz.getName());
      //      }
      //      return (V) pool.get(ouid);
      return null;
   }

   @Override
   public <V> V insert(Class<V> clazz,
                       V entity){
      //      FEntityPool pool = _map.get(clazz.getName());
      //      if(null == pool){
      //         throw new FFatalError("Unregister entity (class={0})", clazz.getName());
      //      }
      //      return (V) pool.doInsert((IEntity) entity);
      return null;
   }

   @Override
   public <V> V prepare(Class<V> clazz){
      //      FEntityPool pool = _map.get(clazz.getName());
      //      if(null == pool){
      //         throw new FFatalError("Unregister entity (class={0})", clazz.getName());
      //      }
      //      return (V) pool.newEntity();
      return null;
   }

   @Override
   public void register(Class<?> clsAccess,
                        Class<?> clsEntity){
      //      String entityName = clsEntity.getName();
      //      FEntityPool pool = _map.get(entityName);
      //      if(null == pool){
      //         pool = new FEntityPool(_loader, entityName);
      //         pool.setCacheCount(_cacheCount);
      //         _map.set(entityName, pool);
      //      }
      //      pool.parse(clsAccess, clsEntity);
      //      _logger.debug("Register entity (class={0})", entityName);
   }

   @Override
   public <V> V update(Class<V> clazz,
                       V entity,
                       int ouid){
      //      FEntityPool pool = _map.get(clazz.getName());
      //      if(null == pool){
      //         throw new FFatalError("Unregister entity (class={0})", clazz.getName());
      //      }
      //      return (V) pool.doUpdate((IEntity) entity, ouid);
      return null;
   }
}
