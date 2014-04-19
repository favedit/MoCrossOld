package org.mo.eng.data;

import org.mo.com.data.FSqlConnections;
import org.mo.com.data.ISqlConnection;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.core.context.FContext;

public class FDataContext
      extends FContext
      implements
         IDataContext
{
   private static ILogger _logger = RLogger.find(FDataContext.class);

   private ISqlConnection _connection;

   private FSqlConnections _connections;

   private final IDatabaseConsole _databaseConsole;

   public FDataContext(IDatabaseConsole dbConsole){
      _databaseConsole = dbConsole;
   }

   public ISqlConnection activeConnection(){
      if(_connection == null){
         _connection = _databaseConsole.alloc();
      }
      return _connection;
   }

   public ISqlConnection activeConnection(String name){
      if(_connections == null){
         _connections = new FSqlConnections();
      }
      ISqlConnection cnn = _connections.get(name);
      if(cnn == null){
         cnn = _databaseConsole.alloc(name);
         if(cnn != null){
            _connections.set(name, cnn);
         }
      }
      return cnn;
   }

   public void release(){
      if(null != _connection){
         _logger.debug(this, "release", "Release data connection (connection={0})", _connection);
         _databaseConsole.free(_connection);
      }
      if(null != _connections){
         int count = _connections.count();
         for(int n = 0; n < count; n++){
            ISqlConnection connection = _connections.value(n);
            _logger.debug(this, "release", "Release data connection (connection={0})", connection);
            _databaseConsole.free(connection);
         }
      }
   }
}
