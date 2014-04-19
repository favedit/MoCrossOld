package org.mo.util.data;

import org.mo.com.data.FSql;
import org.mo.com.data.ISqlConnection;
import org.mo.com.lang.FAttributes;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.FXmlDocument;
import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.RAop;
import org.mo.eng.data.IDatabaseConsole;

public class RDataImport
{

   private final static ILogger _logger = RLogger.find(RDataImport.class);

   public static void main(String[] args){
      try{
         String config = "D:/Workspace/eUIS/src/eUIS-config/application.xml";
         RAop.configConsole().loadFile(config);

         IDatabaseConsole dc = RAop.find(IDatabaseConsole.class);
         ISqlConnection sqlCnn = dc.alloc();
         // Load data
         FAttributes labels = new FAttributes();
         FXmlDocument xdoc = new FXmlDocument();
         xdoc.loadFile("D:/Workspace/cm_spell.xml");
         for(FXmlNode rowNode : xdoc.root().nodes()){
            if(rowNode.isName("Row")){
               String label = rowNode.nodeText("CHN_CHAR");
               if(labels.contains(label)){
                  _logger.debug(null, "main", "Skip code " + rowNode.attributes().dump());
               }else{
                  labels.set(label, label);
                  FSql sql = new FSql();
                  sql.append("INSERT INTO CM_SPELL_DS(OUID,LANGUAGE_ID,TYPE,CODE,IS_VALID,LABEL,SPELL) VALUES(");
                  sql.append("CM_SPELL_DI.Next_Oid,1,'S',");
                  sql.append("'" + rowNode.nodeText("CHAR_ID") + "',");
                  sql.append("'Y',");
                  sql.append("'" + label + "',");
                  sql.append("'" + rowNode.nodeText("SPELL") + "'");
                  sql.append(")");
                  //_logger.debug(null, "main", sql.toString());
                  sqlCnn.executeSql(sql);
               }
            }
         }
         dc.free(sqlCnn);

         RAop.release();
      }catch(Exception e){
         _logger.error(null, "testLoadConfig", e);
      }
   }
}
