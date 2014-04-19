package org.mo.eng.data.common;

import org.mo.com.data.ISqlConnect;
import org.mo.com.data.MSqlConnect;
import org.mo.com.data.RSql;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FString;
import org.mo.com.lang.IInitialize;
import org.mo.com.lang.RString;
import org.mo.eng.property.IPropertyConsole;

public abstract class FSqlCommand
      extends MSqlConnect
      implements
         IInitialize
{
   protected FAttributes _binds = new FAttributes();

   protected FString _command = new FString();

   protected FString _origin = new FString();

   protected IPropertyConsole _propertyConsole;

   public FSqlCommand(ISqlConnect connect){
      super(connect);
      initialize();
   }

   public FSqlCommand(ISqlConnect connect,
                      FString command){
      super(connect);
      initialize();
      setSqlCommand(command);
   }

   public void bindFloat(String name,
                         float value){
      _binds.set(name, Float.toString(value));
   }

   public void bindInteger(String name,
                           int value){
      _binds.set(name, Integer.toString(value));
   }

   public void bindLong(String name,
                        long value){
      _binds.set(name, Long.toString(value));
   }

   public void bindSql(String name,
                       String value){
      _binds.set(name, value);
   }

   public void bindString(String name,
                          String value){
      _binds.set(name, "'" + value + "'");
   }

   public void execute(){
      String command = makeCommand();
      activeConnection().executeSql(command.toString());
   }

   @Override
   public void initialize(){
      //_propertyConsole = RAop.find(IPropertyConsole.class);
   }

   public String makeCommand(){
      String command = _command.toString();
      //if(_propertyConsole.isProperty(command)){
      //command = _propertyConsole.property(command);
      //}
      if(RString.isEmpty(command)){
         command = _command.toString();
      }
      command = RString.removeChars(command, '\r');
      int nSize = _binds.count();
      for(int n = 0; n < nSize; n++){
         command = RString.replace(command, "{" + _binds.name(n) + "}", _binds.value(n));
      }
      return RSql.formatSql(command);
   }

   public void setSqlCommand(FString command){
      _command.assign(command);
      _origin.assign(command);
   }
}
