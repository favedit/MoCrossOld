package org.mo.data.reader;

import org.mo.com.collections.FRow;
import org.mo.com.data.ISqlConnection;
import org.mo.com.data.ISqlDatasetReader;
import org.mo.eng.data.FConnectionConsole;

public class RDatasetReaderTest
{
   public static void main(String[] args){
      FConnectionConsole connectionConsole = new FConnectionConsole();
      connectionConsole.setPassport("game");
      connectionConsole.setPassword("game");
      connectionConsole.setDriverName("com.mysql.jdbc.Driver");
      connectionConsole.setDriverClass("org.mo.data.driver.FSqlMysqlConnection");
      connectionConsole.setUrl("jdbc:mysql://192.168.2.5:3306/domain");
      ISqlConnection connection = connectionConsole.alloc();

      String sql = "SELECT * FROM DM_SYNCHRONIZER_UNIT";
      try(ISqlDatasetReader reader = connection.fetchReader(sql)){
         for(FRow row : reader){
            System.out.println(row);
         }
      }

      connectionConsole.free(connection);
   }
}
