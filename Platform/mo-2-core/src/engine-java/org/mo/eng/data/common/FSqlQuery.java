package org.mo.eng.data.common;

import org.mo.com.collections.FDataset;
import org.mo.com.collections.FRow;
import org.mo.com.data.FSql;
import org.mo.com.data.ISqlConnect;
import org.mo.com.lang.FString;
import org.mo.com.resource.RResource;

public class FSqlQuery
      extends FSqlCommand
      implements
         ISqlQuery
{
   public FSqlQuery(Class<?> clazz,
                    String name){
      super(null, new FSql(RResource.find(clazz).findString(name)));
   }

   public FSqlQuery(ISqlConnect connect){
      super(connect);
   }

   public FSqlQuery(ISqlConnect connect,
                    Class<?> clazz,
                    String name){
      super(connect, new FSql(RResource.find(clazz).findString(name)));
   }

   public FSqlQuery(ISqlConnect connect,
                    FString command){
      super(connect, command);
   }

   public FSqlQuery(ISqlConnect connect,
                    String command){
      super(connect, new FSql(command));
   }

   public String executeScalar(){
      return activeConnection().executeScalar(makeCommand());
   }

   public FDataset fetchDataset(){
      return activeConnection().fetchDataset(makeCommand());
   }

   public FDataset fetchDataset(int nPageSize,
                                int nPage){
      return activeConnection().fetchDataset(makeCommand(), nPageSize, nPage);
   }

   public FRow fetchRow(){
      return activeConnection().find(makeCommand());
   }

   public boolean hasDataset(){
      return !activeConnection().fetchDataset(makeCommand()).isEmpty();
   }
}
