package org.mo.com.data;

import org.mo.com.lang.FObject;

public class MSqlConnect
      extends FObject
      implements
         ISqlConnect
{
   private ISqlConnect _connect;

   public MSqlConnect(){
   }

   public MSqlConnect(ISqlConnect connect){
      _connect = connect;
   }

   @Override
   public ISqlConnection activeConnection(){
      return _connect.activeConnection();
   }

   @Override
   public ISqlConnection activeConnection(String name){
      return _connect.activeConnection(name);
   }

   public void linkConnect(ISqlConnect connect){
      _connect = connect;
   }
}
