package org.mo.jfa.face.logic.webform;

import org.mo.com.data.ESqlDataDirection;
import org.mo.com.data.ESqlDataType;
import org.mo.com.data.FSqlProcedure;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FStrings;
import org.mo.com.xml.FXmlNode;
import org.mo.logic.session.ISqlSessionContext;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public class FWebLogicService
      implements
         IWebLogicService
{

   @Override
   public void userPicker(IWebContext context,
                          ISqlSessionContext sqlContext,
                          IWebInput input,
                          IWebOutput output){
      FXmlNode configNode = input.config();
      if(configNode.hasNode()){
         FXmlNode dataNode = configNode.findNode("Data");
         if(null != dataNode){
            String info = dataNode.text();
            String methodName = "Make_Relation_Pack";
            FSqlProcedure procedure = new FSqlProcedure(methodName);
            procedure.setLogicName("PM_RELATION_MASTER_DI");
            procedure.createParameter("params_", info, ESqlDataType.String, ESqlDataDirection.InOut);
            procedure.createParameter("results_", null, ESqlDataType.String, ESqlDataDirection.InOut);
            sqlContext.activeConnection().execute(procedure);
            FStrings results = procedure.parameter("results_").asStrings();
            FXmlNode resultNode = new FXmlNode("Result");
            for(int n = 0; n < results.count(); n++){
               String str = results.get(n);
               FAttributes attrs = new FAttributes();
               attrs.unpack(str);
               String type = attrs.get("type");
               FXmlNode infoNode = new FXmlNode();
               if(type.equals("U")){
                  infoNode.setName("User");
                  infoNode.attributes().assign(attrs);
                  resultNode.push(infoNode);
               }else if(type.equals("O")){
                  infoNode.setName("Organization");
                  infoNode.attributes().assign(attrs);
                  resultNode.push(infoNode);
               }else if(type.equals("R")){
                  infoNode.setName("Role");
                  infoNode.attributes().assign(attrs);
                  resultNode.push(infoNode);
               }else if(type.equals("D")){
                  infoNode.setName("Duty");
                  infoNode.attributes().assign(attrs);
                  resultNode.push(infoNode);
               }else if(type.equals("E")){
                  infoNode.setName("Unknown");
                  infoNode.attributes().assign(attrs);
                  resultNode.push(infoNode);
               }
            }
            output.config().attributes().assign(input.config().attributes());
            output.config().push(resultNode);
         }
      }
   }
}
