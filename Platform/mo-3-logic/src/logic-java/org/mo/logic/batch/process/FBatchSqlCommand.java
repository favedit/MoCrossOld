package org.mo.logic.batch.process;

import org.mo.com.collections.FDataset;
import org.mo.com.collections.FRow;
import org.mo.com.data.ISqlConnection;
import org.mo.com.lang.FStrings;
import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.face.ALink;
import org.mo.eng.data.IDatabaseConsole;

public class FBatchSqlCommand
{
   @ALink
   protected IDatabaseConsole _databaseConsole;

   public final static String TYPE_SQL = "sql";

   public final static String TYPE_ALL = "All";

   public final static String NAME_RANGE = "Range";

   public final static String NAME_REFERENCE = "Reference";

   public final static String NAME_ITEM = "Item";

   public FStrings fetchRangeItem(FXmlNode config){
      FStrings items = new FStrings();
      // 处理所有范围节点
      for(FXmlNode rangeNode : config){
         if(rangeNode.isName(NAME_RANGE)){
            String type = rangeNode.get("type");
            if(TYPE_ALL.equalsIgnoreCase(type)){
               items.push("All");
            }else if(TYPE_SQL.equalsIgnoreCase(type)){
               // 数据库选取方式
               ISqlConnection connection = _databaseConsole.alloc();
               try{
                  FDataset dataset = connection.fetchDataset(rangeNode.text());
                  for(FRow row : dataset){
                     if(!row.isEmpty()){
                        items.push(row.value(0));
                     }
                  }
               }finally{
                  _databaseConsole.free(connection);
               }
            }else{
               // 项目方式
               for(FXmlNode itemNode : rangeNode){
                  if(itemNode.isName(NAME_ITEM)){
                     items.push(itemNode.text());
                  }
               }
            }
         }
      }
      return items;
   }
}
