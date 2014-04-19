package org.mo.eng.entity;

import org.mo.com.data.ISqlConnection;
import org.mo.com.lang.FDictionary;
import org.mo.com.lang.FSet;
import org.mo.com.lang.FString;
import org.mo.com.lang.RInteger;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.eng.entity.descriptor.FEntityDescriptor;
import org.mo.eng.entity.info.FSqlFieldInfo;
import org.mo.eng.entity.info.FSqlTableInfo;

public class FEntityPool<E extends IEntity>
{
   @SuppressWarnings("unused")
   private static ILogger _logger = RLogger.find(FEntityPool.class);

   @SuppressWarnings("unused")
   private final int _cacheCount = 100;

   @SuppressWarnings("unused")
   private FEntityDescriptor _descriptor;

   @SuppressWarnings("unused")
   private final FSet<E> _entities;

   private final FDictionary<FSqlFieldInfo> _fields = new FDictionary<FSqlFieldInfo>(FSqlFieldInfo.class);

   private int _sequence;

   private final FSqlTableInfo _table = new FSqlTableInfo();

   public FEntityPool(Class<E> clazz){
      _entities = new FSet<E>(clazz);
   }

   public void buildSequence(ISqlConnection connection,
                             String table){
      FString sql = new FString("SELECT MAX(OUID) OUID FROM " + table);
      _sequence = RInteger.parse(connection.executeScalar(sql.toString()));
   }

   public synchronized int currentId(){
      return _sequence;
   }

   public void doDelete(int ouid,
                        IEntity entity){
      //_entities.set(ouid, null);
   }

   public void doInsert(E entity){
      //_entities.set(entity.ouid(), entity);
   }

   public void doUpdate(int ouid,
                        E entity){
      //_entities.set(ouid, entity);
   }

   public FString dump(){
      FString dump = new FString();
      dump.appendLine(this);
      dump.appendLine(_table.dump());
      for(FSqlFieldInfo field : _fields.toObjects()){
         dump.appendLine("   ", field.dump());
      }
      return dump;
   }

   public E get(int ouid){
      //return _entities.get(ouid);
      return null;
   }

   public E get(String ouid){
      //return _entities.get(Integer.parseInt(ouid));
      return null;
   }

   public synchronized int nextId(){
      return ++_sequence;
   }

   public String nextIdString(){
      return Integer.toString(nextId());
   }

   public void set(int oid,
                   E entity){
      //_entities.set(oid, entity);
   }

   public void set(String ouid,
                   E entity){
      //_entities.set(Integer.parseInt(ouid), entity);
   }
}
