package org.mo.eng.entity;

import org.mo.com.lang.IAttributes;
import org.mo.eng.data.common.FSqlSearchFields;

public interface IEntityDao<E extends IEntity>
{
   E createEntity(E entity,
                  IAttributes row,
                  EEntityTransfer transfer);

   IAttributes createValue(IAttributes row,
                           E entity,
                           EEntityTransfer transfer);

   void loadEntity(E entity,
                   IAttributes row,
                   EEntityTransfer transfer);

   void saveEntity(E entity,
                   IAttributes row,
                   EEntityTransfer transfer);

   void copyEntity(E source,
                   E target);

   boolean exists(int ouid);

   E get(int ouid);

   E get(String ouid);

   boolean get(E entity,
               int ouid);

   E get(IEntityLogic logic,
         FSqlSearchFields fields);

   E[] fetch(IEntityLogic logic,
             FSqlSearchFields fields);

   E lock(int ouid,
          String over);

   boolean lock(EEntityLock type);

   E prepare(IEntityLogic logic);

   void prepare(IEntityLogic logic,
                E entity);

   E doInsert(IEntityLogic logic,
              E entity);

   E doUpdate(IEntityLogic logic,
              E entity);

   E doUpdate(IEntityLogic logic,
              E entity,
              int ouid);

   E doDelete(IEntityLogic logic,
              E entity);

   E doDelete(IEntityLogic logic,
              int ouid);

   int clear();
}
