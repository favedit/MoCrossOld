package org.mo.jfa.face.design.webform;

import org.mo.com.collections.FDataset;
import org.mo.com.collections.FRow;
import org.mo.com.data.ISqlConnection;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FString;
import org.mo.com.lang.RString;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlNodes;
import org.mo.com.xml.IXmlObject;
import org.mo.com.xml.IXmlObjects;
import org.mo.core.aop.face.ALink;
import org.mo.data.dataset.IDatasetConsole;
import org.mo.eng.data.IDatabaseConsole;
import org.mo.eng.data.common.FSqlQuery;
import org.mo.eng.data.common.FSqlSearchField;
import org.mo.eng.data.common.FSqlSearchFields;
import org.mo.eng.data.common.ISqlContext;
import org.mo.logic.session.ISqlSessionContext;
import org.mo.web.core.webform.FWebFormDatasetArgs;
import org.mo.web.core.webform.IWebFormConsole;
import org.mo.web.core.webform.IWebFormDatasetConsole;
import org.mo.web.core.webform.control.XColumnEdit;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public class FWebDatasetService
      implements
         IWebDatasetService
{

   private final static String ACTION_DELETE = "D";

   private final static String ACTION_INSERT = "I";

   private final static String ACTION_UPDATE = "U";

   private final static String PTY_OPERATION = "_os";

   @ALink
   protected IDatabaseConsole _databaseConsole;

   @ALink
   protected IDatasetConsole _datasetConsole;

   @ALink
   protected IWebFormConsole _formConsole;

   @ALink
   protected IWebFormDatasetConsole _formDatasetConsole;

   /* (non-Javadoc)
    * @see org.mo.jfa.face.design.webform.IWebDatasetService#complete(org.mo.web.protocol.context.IWebContext, org.mo.eng.data.common.ISqlContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   @Override
   public void complete(IWebContext context,
                        ISqlSessionContext sqlContext,
                        IWebInput input,
                        IWebOutput output){
      FXmlNode controlNode = input.config().node("Control");
      if(null == controlNode){
         throw new FFatalError("Control node is null.");
      }
      String formName = controlNode.get("form");
      String controlName = controlNode.get("control");
      String controlValue = controlNode.get("value");
      //
      FXmlNode formNode = _formConsole.build(formName, EXmlConfig.Value);
      String dsName = formNode.get("dataset");
      IXmlObject xdataset = _datasetConsole.get(dsName);
      String tableName = xdataset.innerGet("data_name");
      FXmlNode ctlNode = formNode.findNode("name", controlName);
      String ctlDataValue = ctlNode.get("data_name");
      // 连接用户线程
      FWebFormDatasetArgs args = new FWebFormDatasetArgs(context, sqlContext);
      args.setSessionConnectId(context.session().connectId());
      String sqlCmd = "SELECT {0} DATA, COUNT({0}) COUNT FROM {1} WHERE {0} LIKE '{2}%' GROUP BY {0}";
      String sql = RString.format(sqlCmd, ctlDataValue, tableName, controlValue);
      FSqlQuery query = new FSqlQuery(sqlContext, sql);
      FDataset dataset = query.fetchDataset();
      if(null != dataset){
         for(FRow row : dataset){
            FXmlNode node = output.config().createNode("Item");
            node.attributes().append(row);
         }
      }
   }

   @Override
   public void delete(IWebContext context,
                      ISqlContext sqlContext,
                      IWebInput input,
                      IWebOutput output){
      FXmlNode envNode = input.config().findNode("Environment");
      FXmlNode dataNode = input.config().findNode("Data");
      String formName = envNode.get("form_name");
      IXmlObject xform = _formConsole.find(formName);
      FWebFormDatasetArgs args = new FWebFormDatasetArgs(context, sqlContext);
      if(null != xform){
         String formType = xform.name();
         if("WebTable".equals(formType)){
         }else if("WebForm".equals(formType)){
            args.setForm(xform);
            args.setLogic(null);
            args.setValues(dataNode.attributes());
            _formDatasetConsole.delete(args);
         }
      }
   }

   public void deleteTableData(IXmlObject form,
                               FXmlNode rowNode){
      FString sql = new FString();
      sql.append("DELETE FROM ");
      sql.append(form.innerGet("table_name"));
      sql.append(" WHERE OUID=");
      sql.append(rowNode.get("ouid"));
      // Sql
      ISqlConnection sqlCnn = null;
      try{
         sqlCnn = _databaseConsole.alloc();
         sqlCnn.executeSql(sql.toString());
      }finally{
         _databaseConsole.free(sqlCnn);
      }
   }

   public void doSave(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      FXmlNode env = input.config().findNode("Environment");
      FXmlNode data = input.config().findNode("Data");
      if(null != env && null != data){
         String formName = env.get("select_form");
         IXmlObject form = _formConsole.find(formName);
         if(null != form){
            String controlUid = env.get("select_control");
            IXmlObject field = form.children().get(controlUid);
            if(null != field){
               field.loadConfig(data, EXmlConfig.Full);
               _formConsole.store(form);
            }
         }
      }
   }

   @Override
   public void fetch(IWebContext context,
                     ISqlContext sqlContext,
                     IWebInput input,
                     IWebOutput output){
      FWebFormDatasetArgs args = new FWebFormDatasetArgs(context, sqlContext);
      // 获取上传的数据信息
      FXmlNode dsNode = input.config().findNode("Dataset");
      if(dsNode.contains("page_size")){
         args.setPageSize(dsNode.getInt("page_size"));
      }
      if(dsNode.contains("page")){
         args.setPage(dsNode.getInt("page"));
      }
      FXmlNode searchNode = dsNode.findNode("Search");
      if(null != searchNode){
         args.search().appendSearch(searchNode.attributes());
      }
      FXmlNode orderNode = dsNode.findNode("Order");
      if(null != orderNode){
         args.order().appendOrder(orderNode.attributes());
      }
      // 获得表单的定义对象
      String formName = dsNode.get("name");
      IXmlObject xform = _formConsole.find(formName);
      if(null != xform){
         args.setForm(xform);
         FXmlNode outDsNode = _formDatasetConsole.fetchNode(args);
         output.config().nodes().push(outDsNode);
      }
   }

   @Override
   public void insert(IWebContext context,
                      ISqlContext sqlContext,
                      IWebInput input,
                      IWebOutput output){
      FXmlNode envNode = input.config().findNode("Environment");
      FXmlNode dataNode = input.config().findNode("Data");
      String formName = envNode.get("form_name");
      IXmlObject xform = _formConsole.find(formName);
      FWebFormDatasetArgs args = new FWebFormDatasetArgs(context, sqlContext);
      if(null != xform){
         String formType = xform.name();
         if("WebTable".equals(formType)){
         }else if("WebForm".equals(formType)){
            args.setForm(xform);
            args.setLogic(null);
            args.setValues(dataNode.attributes());
            _formDatasetConsole.insert(args);
         }
      }
   }

   public void insertTableData(IXmlObject form,
                               FXmlNode rowNode){
      FString sql = new FString();
      FString fields = new FString();
      FString values = new FString();
      IXmlObjects children = form.children();
      int count = children.count();
      boolean first = true;
      for(int n = 0; n < count; n++){
         IXmlObject child = children.get(n);
         if(child instanceof XColumnEdit){
            if(first){
               first = false;
            }else{
               fields.append(",");
               values.append(",");
            }
            String dataName = child.innerGet("data_name");
            fields.append(dataName);
            values.append("'", rowNode.get(dataName), "'");
         }
      }
      sql.append("INSERT INTO ");
      sql.append(form.innerGet("table_name"));
      sql.append("(", fields, ")");
      sql.append(" VALUES(", values, ")");
      // Sql
      ISqlConnection sqlCnn = null;
      try{
         sqlCnn = _databaseConsole.alloc();
         sqlCnn.executeSql(sql.toString());
      }finally{
         _databaseConsole.free(sqlCnn);
      }
   }

   @Override
   public void search(IWebContext context,
                      ISqlContext sqlContext,
                      IWebInput input,
                      IWebOutput output){
      // TODO Auto-generated method stub
      FWebFormDatasetArgs args = new FWebFormDatasetArgs(context, sqlContext);
      // 获取上传的数据信息
      FXmlNode dsNode = input.config().findNode("Dataset");
      FXmlNode searchNode = dsNode.findNode("Search");
      if(null != searchNode){
         int count = searchNode.nodeCount();
         FSqlSearchFields att = new FSqlSearchFields();
         for(int n = 0; n < count; n++){
            FSqlSearchField a = new FSqlSearchField();
            String type = searchNode.node(n).get("search_type");
            a.setName(searchNode.node(n).get("data_name"));
            a.setValue(searchNode.node(n).get("data_value"));
            a.setType(type);
            att.push(a);
         }
         args.search().append(att);
      }
      FXmlNode orderNode = dsNode.findNode("Order");
      if(null != orderNode){
         args.order().appendOrder(orderNode.attributes());
      }
      String formName = dsNode.get("name");
      IXmlObject xform = _formConsole.find(formName);
      if(null != xform){
         args.setForm(xform);
         FXmlNode outDsNode = _formDatasetConsole.fetchNode(args);
         output.config().nodes().push(outDsNode);
      }

   }

   @Override
   public void update(IWebContext context,
                      ISqlSessionContext sqlContext,
                      IWebInput input,
                      IWebOutput output){
      FXmlNodes datasetNodes = input.config().nodes();
      // 处理所有记录集
      if(!datasetNodes.isEmpty()){
         // 连接用户线程
         FWebFormDatasetArgs args = new FWebFormDatasetArgs(context, sqlContext);
         args.setSessionConnectId(context.session().connectId());
         for(FXmlNode dsNode : datasetNodes){
            if(dsNode.isName("Dataset")){
               String formName = dsNode.get("name");
               IXmlObject xform = _formConsole.find(formName);
               if(null == xform){
                  throw new FFatalError("Can't find form. (name={0})", formName);
               }
               // 开始数据处理
               if(dsNode.hasNode()){
                  args.setForm(xform);
                  for(FXmlNode node : dsNode.nodes()){
                     if(node.isName("Row")){
                        String flag = node.get(PTY_OPERATION);
                        if(ACTION_INSERT.equals(flag)){
                           // 新建一条记录
                           args.setLogic(null);
                           args.setValues(node.attributes());
                           _formDatasetConsole.insert(args);
                        }else if(ACTION_UPDATE.equals(flag)){
                           // 修改一条记录
                           args.setLogic(null);
                           args.setValues(node.attributes());
                           _formDatasetConsole.update(args);
                        }else if(ACTION_DELETE.equals(flag)){
                           // 删除一条记录
                           args.setLogic(null);
                           args.setValues(node.attributes());
                           _formDatasetConsole.delete(args);
                        }else{
                           throw new FFatalError("Unknown row action. (action={0})", flag);
                        }
                     }
                  }
               }
            }
         }
      }
      // 输出修改获得记录
      output.config().nodes().append(datasetNodes);
   }

   public void updateTableData(IXmlObject form,
                               FXmlNode rowNode){
      FString sql = new FString();
      sql.append("UPDATE ");
      sql.append(form.innerGet("table_name"));
      sql.append(" SET ");
      IXmlObjects children = form.children();
      int count = children.count();
      boolean first = true;
      for(int n = 0; n < count; n++){
         IXmlObject child = children.get(n);
         if(child instanceof XColumnEdit){
            if(first){
               first = false;
            }else{
               sql.append(",");
            }
            String dataName = child.innerGet("data_name");
            sql.append(dataName);
            sql.append("='", rowNode.get(dataName), "'");
         }
      }
      sql.append(" WHERE OUID=");
      sql.append(rowNode.get("ouid"));
      // Sql
      ISqlConnection sqlCnn = null;
      try{
         sqlCnn = _databaseConsole.alloc();
         sqlCnn.executeSql(sql.toString());
      }finally{
         _databaseConsole.free(sqlCnn);
      }
   }

}
