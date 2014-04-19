package org.mo.util.data;

import org.mo.com.data.ISqlConnection;
import org.mo.com.lang.FString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.RAop;
import org.mo.data.dataset.IDatasetConsole;
import org.mo.eng.data.IDatabaseConsole;

public class RGuidFix{

   public static void main(String[] args){
      // 加载设置
      String path = "D:/Workspace/eUIS/src/eUIS-config";
      RAop.configConsole().loadFile(path + "/application-develop.xml");
      // 收集数据库链接
      IDatabaseConsole databaseConsole = RAop.find(IDatabaseConsole.class);
      ISqlConnection cnn = databaseConsole.alloc("EUISDP");

      IDatasetConsole dsConsole = RAop.find(IDatasetConsole.class);
      for(IXmlObject xobj : dsConsole.list()){
         if(RBoolean.parse(xobj.innerGet("is_valid"))){
            String logicName = xobj.innerGet("data_name");
            if(RString.isNotBlank(logicName)){
               if("DataStore".equalsIgnoreCase(xobj.name())){
                  System.out.println(logicName);
                  cnn.executeSql(new FString("UPDATE " + logicName + "_DS SET OGID = TRIM(OGID), OVER=TRIM(OVER)"));
                  cnn.executeSql(new FString("UPDATE " + logicName + "_HS SET OGID = TRIM(OGID), OVER=TRIM(OVER)"));
               }
               if("DataWork".equalsIgnoreCase(xobj.name())){
                  System.out.println(logicName);
                  cnn.executeSql(new FString("UPDATE " + logicName + "_DS SET OGID = TRIM(OGID), OVER=TRIM(OVER)"));
               }
            }
         }
      }
      databaseConsole.free(cnn);
      RAop.release();
   }
}
