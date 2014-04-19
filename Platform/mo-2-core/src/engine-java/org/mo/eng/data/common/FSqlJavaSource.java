package org.mo.eng.data.common;

import org.mo.com.collections.FAttributesList;
import org.mo.com.data.ESqlDataType;
import org.mo.com.data.FSqlFunction;
import org.mo.com.data.FSqlParameter;
import org.mo.com.data.FSqlProcedure;
import org.mo.com.data.ISqlConnect;
import org.mo.com.data.MSqlConnect;
import org.mo.com.data.FSqlMethod;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FString;
import org.mo.com.lang.RBoolean;
import org.mo.com.text.RDatabaseFormat;
import org.mo.core.aop.RAop;
import org.mo.eng.template.ITemplateConsole;
import org.mo.eng.template.ITemplateParser;

public class FSqlJavaSource
      extends MSqlConnect
{
   public final static String DEFINE_BEGIN = "-------------------- Define Begin --------------------------";

   public final static String DEFINE_END = "-------------------- Define End ----------------------------";

   public final static String HISTORY_BEGIN = "-------------------- History Begin -------------------------";

   public final static String HISTORY_END = "-------------------- History End ---------------------------";

   protected FAttributes _item;

   protected FAttributesList _list;

   private String _packageName;

   private String _tableName;

   private String _viewName;

   //private String _sequenceName;
   public FSqlJavaSource(ISqlConnect connect,
                         String tableName){
      super(connect);
      onBeforeFetchInfo(tableName);
      fetchInfo();
   }

   protected void fetchInfo(){
      FSqlPackageParser parser = new FSqlPackageParser(this, _packageName);
      parser.parseHead();
      _item = new FAttributes();
      String logic = _packageName;
      if(logic.endsWith("_DI")){
         logic = logic.substring(0, logic.length() - 3);
      }
      _item.set("logic_name", logic);
      _list = new FAttributesList();
      // Functions
      for(FSqlFunction method : parser.functions()){
         FAttributes item = new FAttributes();
         item.set("type", "function");
         item.set("name", method.name());
         item.set("create", "FSqlFunction method = new FSqlFunction(\"" + _packageName + "." + method.name() + "\");");
         if(makeMethodSource(item, method)){
            //            String type = makeType(method.parameterReturn().type());
            //            if(type == null){
            //               type = "void";
            //            }
            //            item.setValue("return", type);
            item.set("return", "FSqlFunction");
            _list.push(item);
         }
      }
      // Functions
      for(FSqlProcedure method : parser.procedures().toObjects()){
         FAttributes item = new FAttributes();
         item.set("type", "procedure");
         item.set("name", method.name());
         item.set("create", "FSqlProcedure method = new FSqlProcedure(\"" + _packageName + "." + method.name() + "\");");
         if(makeMethodSource(item, method)){
            item.set("return", "FSqlProcedure");
            _list.push(item);
         }
      }
   }

   protected ITemplateParser findParser(String path,
                                        String name){
      ITemplateConsole tc = RAop.find(ITemplateConsole.class);
      return tc.findParser(path, name);
   }

   public FString makeLogicSource(){
      ITemplateParser parser = findParser("database.oracle", "java.logic");
      //parser.setItem(_item);
      //parser.setList(_list);
      return parser.parse();
   }

   public boolean makeMethodSource(FAttributes item,
                                   FSqlMethod method){
      FString params = new FString();
      FString body = new FString();
      int count = method.parameters().count();
      for(int n = 0; n < count; n++){
         FSqlParameter parameter = method.parameters().value(n);
         String javaParam = RDatabaseFormat.toJavaMethodName(parameter.name());
         if(n > 0){
            params.append(", ");
         }
         String type = makeType(parameter.type());
         if(type == null){
            return false;
         }
         params.append(type);
         params.append(" ");
         params.append(javaParam);
         if(n > 0){
            body.append("\n      ");
         }
         body.append("mps.create(\"");
         body.append(parameter.name());
         body.append("\", ");
         body.append(javaParam);
         body.append(", ESqlDataType.");
         body.append(parameter.type().toString());
         body.append(", ESqlDataDirection.");
         body.append(parameter.direction().toString());
         body.append(");");
      }
      item.set("params", params.toString());
      item.set("body", body.toString());
      item.set("has_params", RBoolean.toString(!method.parameters().isEmpty()));
      return true;
   }

   public String makeType(ESqlDataType type){
      if(type == ESqlDataType.String){
         return "String";
      }else if(type == ESqlDataType.Integer){
         return "int";
      }else if(type == ESqlDataType.Float){
         return "float";
      }
      return null;
   }

   protected void onBeforeFetchInfo(String sTableName){
      _tableName = sTableName.toUpperCase();
      _viewName = _tableName;
      if(_viewName.endsWith("_DS")){
         _viewName = _viewName.substring(0, _viewName.length() - 3);
      }
      if(_viewName.endsWith("_SI")){
         _viewName = _viewName.substring(0, _viewName.length() - 3);
      }
      if(_viewName.endsWith("_SQ")){
         _viewName = _viewName.substring(0, _viewName.length() - 3);
      }
      _packageName = _viewName + "_DI";
      //_sequenceName = _viewName + "_SI";
   }
}
