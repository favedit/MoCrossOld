package org.mo.eng.persistence;

import org.mo.eng.persistence.common.XPersistence;
import org.mo.eng.store.IXmlConfigConsole;

public interface IPersistenceConsole
      extends
         IXmlConfigConsole<XPersistence>
{
   void build(FPersistenceBuilderArgs args);

   <V> V find(Class<V> clazz,
              String name);

   XPersistence synchronize(String name);
}
