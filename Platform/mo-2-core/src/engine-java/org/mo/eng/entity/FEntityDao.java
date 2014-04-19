package org.mo.eng.entity;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import org.mo.com.collections.FDataset;
import org.mo.com.collections.FObjectDictionary;
import org.mo.com.collections.FRow;
import org.mo.com.data.FSql;
import org.mo.com.data.ISqlConnection;
import org.mo.com.lang.FMultiString;
import org.mo.com.lang.IAttributes;
import org.mo.com.lang.IInitialize;
import org.mo.com.lang.RArray;
import org.mo.com.lang.RString;
import org.mo.com.lang.reflect.FClass;
import org.mo.com.lang.reflect.FField;
import org.mo.com.lang.reflect.RClass;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.core.aop.face.ALink;
import org.mo.core.bind.IBindConsole;
import org.mo.eng.data.IDatabaseConsole;
import org.mo.eng.data.common.FSqlSearchFields;
import org.mo.eng.entity.face.AField;
import org.mo.eng.entity.face.AFieldAccess;
import org.mo.eng.entity.face.ATable;
import org.mo.eng.entity.face.EFieldType;

public abstract class FEntityDao<E extends IEntity>
      implements
         IInitialize,
         IEntityDao<E>
{
   private static ILogger _logger = RLogger.find(FEntityDao.class);

   @ALink
   protected IBindConsole _bindConsole;

   @ALink
   protected IDatabaseConsole _databaseConsole;

   protected Class<E> _entityClass;

   @ALink
   protected IEntityConsole _entityConsole;

   protected FEntityFieldInfos _entityInfos;

   protected FEntityPool<E> _pool;

   protected FEntityTableInfo _tableInfo;

   public FEntityDao(){
   }

   @Override
   public int clear(){
      // Delete
      FSql sql = new FSql();
      sql.append("DELETE FROM ");
      sql.append(_tableInfo.name());
      sqlExecute(sql);
      return 0;
   }

   @Override
   public void copyEntity(E source,
                          E target){
      // Check
      if(source == null){
         throw new NullPointerException("source");
      }
      if(target == null){
         throw new NullPointerException("target");
      }
      // Transfer
      FObjectDictionary sourceMap = source.values();
      FObjectDictionary targetMap = target.values();
      int count = _entityInfos.count();
      for(int n = 0; n < count; n++){
         String name = _entityInfos.name(n);
         if(sourceMap.contains(name)){
            Object value = sourceMap.get(name);
            FEntityFieldInfo info = _entityInfos.value(n);
            EFieldType type = info.type();
            if(type == EFieldType.MultiString){
               FMultiString mstring = (FMultiString)value;
               if(mstring != null){
                  //targetMap.set(name, mstring.copy());
               }
            }else{
               targetMap.set(name, value);
            }
         }
      }
   }

   protected E createEntity(){
      return RClass.newInstance(_entityClass);
   }

   @Override
   public E createEntity(E entity,
                         IAttributes row,
                         EEntityTransfer transfer){
      // Check
      if(row == null){
         throw new NullPointerException("row");
      }
      // Transfer
      E alloc = RClass.newInstance(_entityClass);
      if(entity != null){
         copyEntity(entity, alloc);
      }
      if(row != null){
         loadEntity(alloc, row, transfer);
      }
      return alloc;
   }

   protected E[] createEntityArray(int count){
      return RArray.newInstance(_entityClass, count);
   }

   @Override
   public IAttributes createValue(IAttributes row,
                                  E entity,
                                  EEntityTransfer transfer){
      // Check
      if(entity == null){
         throw new NullPointerException("row");
      }
      // Transfer
      //FRow alloc = new FRow();
      //if(row != null){
      //alloc.append(row);
      //}
      //      if(entity != null){
      //         saveEntity(entity, alloc, transfer);
      //      }
      //      return alloc;
      return null;
   }

   @Override
   public E doDelete(IEntityLogic logic,
                     E entity){
      // Check
      if(entity == null){
         throw new NullPointerException("row");
      }
      // Delete
      return doDelete(logic, entity.ouid());
   }

   public E doDelete(IEntityLogic logic,
                     E entity,
                     int ouid){
      // Check
      if(ouid < 0){
         ouid = entity.ouid();
      }
      // Find
      E entityOld = get(ouid);
      if(null != entityOld){
         if(onDeleteBefore(logic, entityOld, entity)){
            // Delete
            FSql sql = new FSql();
            sql.append("DELETE FROM ");
            sql.append(_tableInfo.name());
            sql.append(" WHERE OUID=");
            sql.append(Integer.toString(ouid));
            sqlExecute(sql);
            onDeleteAfter(logic, entityOld);
            _pool.doDelete(ouid, entity);
         }
      }
      return entityOld;
   }

   @Override
   public E doDelete(IEntityLogic logic,
                     int ouid){
      return doDelete(logic, null, ouid);
   }

   @Override
   public E doInsert(IEntityLogic logic,
                     E entity){
      entity.setOuid(_pool.nextId());
      // Find
      if(onInsertBefore(logic, entity)){
         FObjectDictionary map = entity.values();
         // Insert
         FSql sql = new FSql();
         sql.append("INSERT INTO ");
         sql.append(_tableInfo.name());
         sql.append("(");
         int count = _entityInfos.count();
         for(int n = 0; n < count; n++){
            FEntityFieldInfo info = _entityInfos.value(n);
            if(n > 0){
               sql.append(',');
            }
            sql.append(info.name());
         }
         sql.append(") VALUES(");
         for(int n = 0; n < count; n++){
            FEntityFieldInfo info = _entityInfos.value(n);
            String name = info.name();
            EFieldType type = info.type();
            Object value = map.get(name);
            if(n > 0){
               sql.append(',');
            }
            if(type == EFieldType.MultiString){
               FMultiString mstring = (FMultiString)value;
               if(mstring != null){
                  sql.appendFieldString(mstring.pack());
               }
            }else{
               sql.appendFieldString((String)value);
            }
         }
         sql.append(")");
         // Execute
         sqlExecute(sql);
         onInsertAfter(logic, entity);
         _pool.doInsert(entity);
      }
      return entity;
   }

   @Override
   public E doUpdate(IEntityLogic logic,
                     E entity){
      return doUpdate(logic, entity, -1);
   }

   @Override
   public E doUpdate(IEntityLogic logic,
                     E entity,
                     int ouid){
      // Check
      if(ouid < 0){
         ouid = entity.ouid();
      }
      // Update
      E entityOld = get(ouid);
      if(null != entityOld){
         if(onUpdateBefore(logic, entityOld, entity)){
            FObjectDictionary map = entity.values();
            // Delete
            FSql sql = new FSql();
            sql.append("UPDATE ");
            sql.append(_tableInfo.name());
            sql.append(" SET ");
            int count = _entityInfos.count();
            for(int n = 0; n < count; n++){
               FEntityFieldInfo info = _entityInfos.value(n);
               String name = info.name();
               EFieldType type = info.type();
               Object value = map.get(name);
               if(n > 0){
                  sql.append(',');
               }
               sql.append(name);
               sql.append("=");
               if(type == EFieldType.MultiString){
                  FMultiString mstring = (FMultiString)value;
                  if(mstring != null){
                     sql.appendFieldString(mstring.pack());
                  }
               }else{
                  sql.appendFieldString((String)value);
               }
            }
            sql.append(" WHERE OUID=", Integer.toString(ouid));
            sqlExecute(sql);
            onUpdateAfter(logic, entity);
            _pool.doUpdate(ouid, entity);
         }
      }
      return entity;
   }

   private IEntityContext entityContext(){
      IEntityContext context = _bindConsole.find(IEntityContext.class);
      if(null == context){
         context = new FEntityContext(_databaseConsole);
         _bindConsole.bind(IEntityContext.class, context);
      }
      return context;
   }

   @Override
   public boolean exists(int ouid){
      return true;
   }

   @Override
   public E[] fetch(IEntityLogic logic,
                    FSqlSearchFields fields){
      FSql sql = new FSql();
      sql.append("SELECT ");
      int count = _entityInfos.count();
      for(int n = 0; n < count; n++){
         FEntityFieldInfo info = _entityInfos.value(n);
         if(n > 0){
            sql.append(',');
         }
         sql.append(info.name());
      }
      sql.append(" FROM ");
      sql.append(_tableInfo.name());
      if(null != fields){
         fields.buildSearchSql(sql);
         //fields.buildOrderSql(sql);
      }
      FDataset dataset = sqlFetch(sql);
      if(null != dataset){
         int rowCount = dataset.count();
         E[] result = createEntityArray(rowCount);
         for(int n = 0; n < rowCount; n++){
            //result[n] = createEntity(null, dataset.get(n), EEntityTransfer.Store);
         }
         return result;
      }
      return null;
   }

   @Override
   public boolean get(E entity,
                      int ouid){
      return true;
   }

   @Override
   public E get(IEntityLogic logic,
                FSqlSearchFields fields){
      E[] rows = fetch(logic, fields);
      return (rows != null && rows.length > 0) ? rows[0] : null;
   }

   @Override
   public E get(int ouid){
      E entity = _pool.get(ouid);
      if(null == entity){
         FSql sql = new FSql();
         sql.append("SELECT * FROM ");
         sql.append(_tableInfo.name());
         sql.append(" WHERE OUID=");
         sql.append(Integer.toString(ouid));
         FRow row = sqlFind(sql);
         if(null != row){
            //entity = createEntity(null, row, EEntityTransfer.Store);
            _pool.set(ouid, entity);
         }
      }
      return entity;
   }

   @Override
   public E get(String ouid){
      return null;
   }

   public E get(String field,
                String value){
      if(!RString.isEmpty(value)){
         // Make sql
         FSql sql = new FSql();
         sql.append("SELECT * FROM ");
         sql.append(_tableInfo.name());
         sql.append(" WHERE ");
         sql.append(field);
         sql.append("='");
         sql.append(value);
         sql.append("'");
         // Find row
         FRow row = sqlFind(sql);
         if(null != row){
            //E entity = createEntity(null, row, EEntityTransfer.Store);
            //_pool.set(entity.ouid(), entity);
            //return entity;
         }
      }
      return null;
   }

   /**
    * <p>初始化操作</p>
    *
    */
   @Override
   @SuppressWarnings("unchecked")
   public void initialize(){
      // Class info
      FClass<?> clazz = RClass.find(getClass());
      // Table info
      Field table = RClass.findField(clazz, "TABLE");
      ATable atable = table.getAnnotation(ATable.class);
      _tableInfo = new FEntityTableInfo(atable);
      // Fields info
      _entityInfos = new FEntityFieldInfos();
      for(FField field : clazz.allDeclaredFields()){
         AField afield = field.getAnnotation(AField.class);
         AFieldAccess afieldAccess = field.getAnnotation(AFieldAccess.class);
         if(afield != null && afieldAccess != null){
            FEntityFieldInfo info = new FEntityFieldInfo(afield, afieldAccess);
            _entityInfos.set(info.name(), info);
         }
      }
      // Entity class
      _entityClass = (Class<E>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
      // Init pool
      ISqlConnection connection = null;
      try{
         FEntityPool<E> pool = new FEntityPool<E>(_entityClass);
         connection = _databaseConsole.alloc();
         pool.buildSequence(connection, _tableInfo.name());
         _pool = pool;
      }finally{
         _databaseConsole.free(connection);
      }
      _logger.debug(this, "initialize", "Build entity pool (sequence={0})", _pool.currentId());
   }

   @Override
   public void loadEntity(E entity,
                          IAttributes row,
                          EEntityTransfer transfer){
      // Check
      if(entity == null){
         throw new NullPointerException("entity");
      }
      if(row == null){
         throw new NullPointerException("row");
      }
      // Transfer
      FObjectDictionary map = entity.values();
      int count = _entityInfos.count();
      for(int n = 0; n < count; n++){
         String name = _entityInfos.name(n);
         if(row.contains(name)){
            String value = row.get(name);
            FEntityFieldInfo info = _entityInfos.value(n);
            EFieldType type = info.type();
            if(type == EFieldType.MultiString){
               FMultiString mstring = (FMultiString)map.get(name);
               if(mstring != null){
                  mstring.set(value);
               }
            }else{
               map.set(name, value);
            }
         }
      }
   }

   @Override
   public boolean lock(EEntityLock type){
      return true;
   }

   @Override
   public E lock(int ouid,
                 String over){
      return null;
   }

   protected void onDeleteAfter(IEntityLogic logic,
                                E entity){
   }

   protected boolean onDeleteBefore(IEntityLogic logic,
                                    E before,
                                    E after){
      return true;
   }

   protected void onInsertAfter(IEntityLogic logic,
                                E entity){
   }

   protected boolean onInsertBefore(IEntityLogic logic,
                                    E entity){
      return true;
   }

   protected void onPrepare(IEntityLogic logic,
                            E entity){
   }

   protected void onUpdateAfter(IEntityLogic logic,
                                E entity){
   }

   protected boolean onUpdateBefore(IEntityLogic logic,
                                    E before,
                                    E after){
      return true;
   }

   @Override
   public E prepare(IEntityLogic logic){
      E entity = createEntity();
      prepare(logic, entity);
      return entity;
   }

   @Override
   public void prepare(IEntityLogic logic,
                       E entity){
      onPrepare(logic, entity);
   }

   @Override
   public void saveEntity(E entity,
                          IAttributes row,
                          EEntityTransfer transfer){
      // Check
      if(entity == null){
         throw new NullPointerException("entity");
      }
      if(row == null){
         throw new NullPointerException("row");
      }
      // Transfer
      FObjectDictionary map = entity.values();
      int count = _entityInfos.count();
      for(int n = 0; n < count; n++){
         String name = _entityInfos.name(n);
         if(map.contains(name)){
            Object value = map.get(name);
            FEntityFieldInfo info = _entityInfos.value(n);
            EFieldType type = info.type();
            if(type == EFieldType.MultiString){
               FMultiString mstring = (FMultiString)value;
               if(mstring != null){
                  row.set(name, mstring.get());
               }
            }else{
               map.set(name, value);
            }
         }
      }
   }

   protected void sqlExecute(FSql sql){
      entityContext().activeConnection().executeSql(sql.toString());
   }

   protected FDataset sqlFetch(FSql sql){
      return entityContext().activeConnection().fetchDataset(sql.toString());
   }

   protected FRow sqlFind(FSql sql){
      return entityContext().activeConnection().find(sql.toString());
   }
}
