package org.mo.game.editor.web.session;

import org.mo.com.data.ESqlDataDirection;
import org.mo.com.data.ESqlDataType;
import org.mo.com.data.FSqlProcedure;
import org.mo.com.data.ISqlConnect;
import org.mo.com.lang.IAttributes;

public class FUserSessionDi{

   public void login(ISqlConnect sqlConnect,
                     IAttributes logic,
                     IAttributes params){
      FSqlProcedure procedure = new FSqlProcedure("Login");
      procedure.setLogicName("CP_SESSION");
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("params_", params, ESqlDataType.String, ESqlDataDirection.InOut);
      sqlConnect.activeConnection().execute(procedure);
      if(null != logic){
         logic.unpack(procedure.parameter("logic_").asString());
      }
      if(null != params){
         params.unpack(procedure.parameter("params_").asString());
      }
   }

}
