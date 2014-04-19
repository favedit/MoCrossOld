package org.mo.data.procedure;

import org.mo.com.data.ESqlDataDirection;
import org.mo.com.data.ESqlDataType;
import org.mo.com.data.FSqlProcedure;
import org.mo.com.data.ISqlConnection;
import org.mo.eng.data.FConnectionConsole;

public class RMySqlProcedureTest
{
   public static void main(String[] args){
      FConnectionConsole connectionConsole = new FConnectionConsole();
      connectionConsole.setPassport("game");
      connectionConsole.setPassword("game");
      connectionConsole.setDriverName("com.mysql.jdbc.Driver");
      connectionConsole.setDriverClass("org.mo.data.driver.FSqlMysqlConnection");
      connectionConsole.setUrl("jdbc:mysql://192.168.2.5:3306/analysis");
      ISqlConnection connection = connectionConsole.alloc();
      FSqlProcedure procedure = new FSqlProcedure("Analysis_ServerStatus");
      procedure.createParameter("ovld_", 1, ESqlDataType.Integer, ESqlDataDirection.In);
      connection.execute(procedure);
      connectionConsole.free(connection);
   }
}
