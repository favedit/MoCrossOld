package org.mo.jfa.face.monitor.database;

import org.mo.com.collections.FAttributesList;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FObjectId;
import org.mo.com.lang.FObjects;

public class FDatabasePage
      extends FObjectId
{

   private final FAttributes _connectionConsole = new FAttributes();

   private final FAttributesList _connectionConsoles = new FAttributesList();

   private final FAttributesList _connections = new FAttributesList();

   public FAttributes getConnectionConsole(){
      return _connectionConsole;
   }

   public FAttributesList getConnectionConsoles(){
      return _connectionConsoles;
   }

   public FObjects<FAttributes> getConnections(){
      return _connections;
   }

}
