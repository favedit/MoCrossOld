package org.mo.jfa.face.logic.webform;

import org.mo.com.collections.FDataset;
import org.mo.com.collections.FDatasets;
import org.mo.com.collections.FRow;
import org.mo.com.data.ESqlDataDirection;
import org.mo.com.data.ESqlDataType;
import org.mo.com.data.FSqlFunction;
import org.mo.com.data.FSqlProcedure;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.IAttributes;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlNodes;
import org.mo.com.xml.IXmlObject;
import org.mo.com.xml.RXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.data.chart.IChartConsole;
import org.mo.data.dataset.FDatasetConsole;
import org.mo.data.dataset.IDatasetConsole;
import org.mo.eng.data.IDatabaseConsole;
import org.mo.eng.data.common.FSqlOrderField;
import org.mo.eng.data.common.FSqlQuery;
import org.mo.eng.data.common.FSqlSearchField;
import org.mo.logic.session.ISqlSessionContext;
import org.mo.web.core.webform.FWebFormDatasetArgs;
import org.mo.web.core.webform.IWebFormConsole;
import org.mo.web.core.webform.IWebFormDatasetConsole;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public class FWebDatasetService
      implements
         IWebDatasetService
{

   private static ILogger _logger = RLogger.find(FDatasetConsole.class);

   private final static String ACTION_DELETE = "D";

   private final static String ACTION_INSERT = "I";

   private final static String ACTION_UPDATE = "U";

   private final static String NODE_CONTROL = "Control";

   private final static String NODE_DATASET = "Dataset";

   private final static String NODE_FORM = "Form";

   private final static String NODE_ORDER = "Order";

   private final static String NODE_SEARCH = "Search";

   private final static String NODE_VALUES = "Values";

   private final static String PTY_CONTROL = "control";

   private final static String PTY_DATA_NAME = "data_name";

   private final static String PTY_DATASET = "dataset";

   private final static String PTY_FORM = "form";

   private final static String PTY_FORMAT = "format";

   private final static String PTY_ITEM = "Item";

   private final static String PTY_NAME = "name";

   private final static String PTY_OPERATION = "_status";

   private final static String PTY_ORDERITEM = "OrderItem";

   private final static String PTY_PAGE = "page";

   private final static String PTY_PAGE_SIZE = "page_Size";

   private final static String PTY_PATH = "path";

   private final static String PTY_RESEARCH = "research";

   private final static String PTY_ROW = "Row";

   private final static String PTY_SEARCHITEM = "SearchItem";

   private final static String PTY_TYPE = "type";

   private final static String PTY_VALUE = "value";

   @ALink
   protected IChartConsole _chartConsole;

   @ALink
   protected IDatabaseConsole _databaseConsole;

   @ALink
   protected IDatasetConsole _datasetConsole;

   @ALink
   protected IWebFormConsole _formConsole;

   @ALink
   protected IWebFormDatasetConsole _formDatasetConsole;

   @Override
   public void chart(IWebContext context,
                     ISqlSessionContext sqlContext,
                     IWebInput input,
                     IWebOutput output){
      FXmlNode chartNode = input.config().findNode("Chart");
      String chartName = chartNode.get("name");
      String params_ = chartNode.get("params");
      IXmlObject xChart = _chartConsole.get(chartName);
      // 执行数据处理
      String dataLogic = xChart.innerGet("data_logic");
      FSqlProcedure procedure = new FSqlProcedure(dataLogic);
      procedure.createParameter("logic_", null, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("params_", params_, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("datas_pack_", null, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("dataset_pack_", null, ESqlDataType.String, ESqlDataDirection.InOut);
      sqlContext.activeConnection().execute(procedure);
      // 获得返回值
      IAttributes datasList = procedure.parameter("datas_pack_").asAttributes();
      IAttributes datasetList = procedure.parameter("dataset_pack_").asAttributes();
      // 建立输出 
      FXmlNode config = RXmlObject.convertDeepToNode(xChart, EXmlConfig.Simple);
      for(FXmlNode node : config.nodes()){
         if(node.isName("Set")){
            String name = node.get("name");
            node.set("value", datasList.get(name));
            System.out.println("xmlNode:" + name + " :" + datasList.get(name));
         }else if(node.isName("Dataset")){
            String sName = node.get("seriesName");
            IAttributes attributes = new FAttributes();
            String setList = datasetList.get(sName);
            attributes.unpack(setList);
            for(FXmlNode setNode : node.nodes()){
               if(setNode.isName("Set")){
                  String name = setNode.get("name");
                  String value = attributes.get(name);
                  setNode.set("value", value);
               }
            }
         }
      }
      output.config().push(config);
   }

   @Override
   public void complete(IWebContext context,
                        ISqlSessionContext sqlContext,
                        IWebInput input,
                        IWebOutput output){
      FXmlNode controlNode = input.config().node(NODE_CONTROL);
      if(null == controlNode){
         throw new FFatalError("Control node is null.");
      }
      String formName = controlNode.get(PTY_FORM);
      String controlName = controlNode.get(PTY_CONTROL);
      String controlValue = controlNode.get(PTY_VALUE);
      //
      FXmlNode formNode = _formConsole.build(formName, EXmlConfig.Value);
      String dsName = formNode.get(PTY_DATASET);
      IXmlObject xdataset = _datasetConsole.get(dsName);
      String tableName = xdataset.innerGet(PTY_DATA_NAME);
      FXmlNode ctlNode = formNode.findNode(PTY_NAME, controlName);
      String ctlDataValue = ctlNode.get(PTY_DATA_NAME);
      // 连接用户线程
      FWebFormDatasetArgs args = new FWebFormDatasetArgs(context, sqlContext);
      args.setSessionConnectId(context.session().connectId());
      String sqlCmd = "SELECT {0} DATA, COUNT({0}) COUNT FROM {1} WHERE {0} LIKE '{2}%' GROUP BY {0}";
      String sql = RString.format(sqlCmd, ctlDataValue, tableName, controlValue);
      FSqlQuery query = new FSqlQuery(sqlContext, sql);
      FDataset dataset = query.fetchDataset();
      output.config().set("action", input.config().get("action"));
      FXmlNode outNode = output.config().createNode("Control");
      outNode.attributes().assign(controlNode.attributes());
      if(null != dataset){
         for(FRow row : dataset){
            FXmlNode node = outNode.createNode(PTY_ITEM);
            node.attributes().append(row);
         }
      }
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.webform.IWebDatasetService#fetch(org.mo.web.protocol.context.IWebContext, org.mo.eng.data.common.ISqlContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   @Override
   public void fetch(IWebContext context,
                     ISqlSessionContext sqlContext,
                     IWebInput input,
                     IWebOutput output){
      // 创建数据集参数对象。
      FWebFormDatasetArgs args = new FWebFormDatasetArgs(context, sqlContext);
      // 获取上传的数据信息。
      for(FXmlNode dsNode : input.config().nodes()){
         if(dsNode.isName(NODE_FORM)){
            // 如果包含重新查询命令，则设置查询参数
            if(RBoolean.parse(dsNode.get(PTY_RESEARCH))){
               args.setResearch(true);
            }
            // 如果包含页面大小的信息，将页面大小信息加入到参数对象中。
            if(dsNode.contains(PTY_PAGE_SIZE)){
               args.setPageSize(dsNode.getInt(PTY_PAGE_SIZE));
            }
            // 如果包含页面大小的信息，将页面大小信息加入到参数对象中。
            if(dsNode.contains(PTY_PAGE)){
               args.setPage(dsNode.getInt(PTY_PAGE));
            }
            if(dsNode.hasNode()){
               for(FXmlNode node : dsNode.nodes()){
                  // 将查询条件加入到参数对象中。
                  if(node.isName(NODE_SEARCH)){
                     for(FXmlNode searchNode : node.nodes()){
                        if(searchNode.isName(PTY_SEARCHITEM)){
                           String name = searchNode.get(PTY_NAME);
                           String value = searchNode.get(PTY_VALUE);
                           String type = searchNode.get(PTY_TYPE);
                           String format = searchNode.get(PTY_FORMAT);
                           FSqlSearchField sqlSearch = new FSqlSearchField(name, value, type, format);
                           args.search().push(sqlSearch);
                        }
                     }
                  }else if(node.isName(NODE_ORDER)){
                     // 将排序条件加入到参数对象中。
                     for(FXmlNode orderNode : node.nodes()){
                        if(orderNode.isName(PTY_ORDERITEM)){
                           String name = orderNode.get(PTY_NAME);
                           String order = orderNode.get(PTY_TYPE);
                           FSqlOrderField sqlOrder = new FSqlOrderField(name, order);
                           args.order().push(sqlOrder);
                        }
                     }
                  }else if(node.isName(NODE_VALUES)){
                     // 将内容加入到参数对象中。 
                     args.values().append(node.attributes());
                  }
               }
            }
            // 获得表单的定义对象。
            String formName = dsNode.get(PTY_NAME);
            String path = dsNode.get("path");
            args.setControlPath(path);
            _logger.debug(this, "fetch", "fetch form datasets (name={0})", formName);
            if(!RString.isEmpty(formName)){
               // 设置表单消息到参数对象中。
               args.setFormName(formName);
               // 查询数据库，得到数据集的集合。
               FDatasets datasets = _formDatasetConsole.fetch(args);
               // 创建根节点。
               output.config().set("action", input.config().get("action"));
               FXmlNode outputNode = output.config().createNode(NODE_FORM);
               outputNode.attributes().assign(dsNode.attributes());
               // 遍历数据集集合，为每个数据集创建输出节点对象。
               for(FDataset dataset : datasets){
                  outputNode.push(dataset.toXmlNode());
               }
            }else{
               throw new FFatalError("Can't find form. (name={0})", formName);
            }
         }
      }
   }

   @SuppressWarnings("unused")
   public void fusionChart(IWebContext context,
                           ISqlSessionContext sqlContext,
                           IWebInput input,
                           IWebOutput output){
      FXmlNode chartNode = input.config().findNode("Chart");
      String chartName = chartNode.get("name");
      // 获取xml对象
      IXmlObject xChart = _chartConsole.get(chartName);
      // 执行数据处理
      String dataLogic = xChart.innerGet("data_logic");
      FSqlProcedure procedure = new FSqlProcedure(dataLogic);
      procedure.createParameter("logic_", null, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("params_", null, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("chart_pack_", null, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("header_pack_", null, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("axis_pack_", null, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("datas_pack_", null, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("footer_pack_", null, ESqlDataType.String, ESqlDataDirection.InOut);
      sqlContext.activeConnection().execute(procedure);
      // 获得返回值
      String graphValue = procedure.parameter("chart_pack_").asString();
      String headerValue = procedure.parameter("header_pack_").asString();
      IAttributes axisList = procedure.parameter("axis_pack_").asAttributes();
      IAttributes datasList = procedure.parameter("datas_pack_").asAttributes();
      // 建立输出
      FXmlNode config = RXmlObject.convertDeepToNodeSkipInvalid(xChart, EXmlConfig.Simple);

      // 设置Graph属性
      if(!RString.isEmpty(graphValue)){
         IAttributes graph = new FAttributes();
         graph.unpack(graphValue);
         config.attributes().append(graph);
      }
      for(FXmlNode node : config.allNodes()){
         if(node.isName("Data")){
            // 数据数据集的情况
            String dataset = datasList.get(node.get("data_name"));
            if(!RString.isEmpty(dataset)){
               // 加载数据集
               FStrings rows = new FStrings();
               IAttributes row = new FAttributes();
               rows.unpack(dataset);
               for(String rowString : rows){
                  row.clear();
                  row.unpack(rowString);
                  // 创建输出节点
                  String rowName = row.get("name");
                  if(RString.isEmpty(rowName)){
                     // 普通节点的生成
                     FXmlNode valueNode = node.createNode("Set");
                     valueNode.attributes().append(row);
                  }else{
                     // 如果存在名称的话，则按照名称设置属性内容
                     FXmlNode valueNode = node.findNode("Value", "name", rowName);
                     if(null == valueNode){
                        throw new FFatalError("Unknown value node. (name={0})", rowName);
                     }
                     valueNode.attributes().append(row);
                  }
               }
            }
         }
      }
      output.config().push(config);
   }

   @Override
   public void lov(IWebContext context,
                   ISqlSessionContext sqlContext,
                   IWebInput input,
                   IWebOutput output){
      FXmlNode inputNode = input.config().findNode(NODE_FORM);
      // 建立选择列表的表单
      String formName = inputNode.get(PTY_NAME);
      FXmlNode formNode = _formConsole.buildLov(formName);
      // 建立数据参数
      FWebFormDatasetArgs args = new FWebFormDatasetArgs(context, sqlContext);
      args.setFormName(formName);
      FXmlNode dsNode = _formDatasetConsole.fetchNode(args);
      if(dsNode.hasNode()){
         for(FXmlNode row : dsNode){
            row.set("_id", row.get("id_rownum"));
         }
      }
      dsNode.set("_id", dsNode.get("id_rownum"));
      // 设置返回内容
      output.config().set("action", input.config().get("action"));
      FXmlNode outputNode = output.config().createNode(NODE_FORM);
      outputNode.attributes().assign(dsNode.attributes());
      outputNode.push(formNode);
      outputNode.push(dsNode);
   }

   @Override
   public void prepare(IWebContext context,
                       ISqlSessionContext sqlContext,
                       IWebInput input,
                       IWebOutput output){

      // 创建数据集参数对象。
      FWebFormDatasetArgs args = new FWebFormDatasetArgs(context, sqlContext);
      args.setLogic(null);
      IAttributes values = new FAttributes();
      // 获取上传的数据信息。
      FXmlNode inputNode = input.config();
      FXmlNode formNode = inputNode.findNode(NODE_FORM);
      args.setFormName(formNode.get(PTY_NAME));
      FXmlNode datasetNode = formNode.findNode(NODE_DATASET);
      FXmlNode valuesNode = null;
      if(null != datasetNode && formNode.hasNode()){
         datasetNode = formNode.findNode(NODE_DATASET);
         if(null != datasetNode && datasetNode.hasNode()){
            valuesNode = datasetNode.findNode(NODE_VALUES);
            values.append(valuesNode.attributes());
            args.setValues(values);
         }
      }
      // 获得准备数据
      FDatasets datasets = null;
      if(null != datasetNode){
         datasets = _formDatasetConsole.prepare(args);
      }
      // 创建准备数据
      FXmlNode outputNode = output.config();
      outputNode.attributes().append(inputNode.attributes());
      FXmlNode outputFormNode = outputNode.syncNode("Form");
      outputFormNode.attributes().append(formNode.attributes());
      FXmlNode outputDatasetNode = outputFormNode.syncNode("Dataset");
      outputDatasetNode.set("name", "/");
      outputDatasetNode.attributes().append(datasetNode.attributes());
      FXmlNode outputRowNode = outputDatasetNode.syncNode("Row");
      outputRowNode.attributes().append(args.values());
      // 遍历数据集集合，为每个数据集创建输出节点对象。
      if(null != datasets){
         for(FDataset dataset : datasets){
            outputFormNode.push(dataset.toXmlNode());
         }
      }
   }

   @Override
   public void scalar(IWebContext context,
                      ISqlSessionContext sqlContext,
                      IWebInput input,
                      IWebOutput output){
      FXmlNode controlNode = input.config().node(NODE_CONTROL);
      if(null == controlNode){
         throw new FFatalError("Control node is null.");
      }
      // 查找表单对象
      String formName = controlNode.get(PTY_FORM);
      FXmlNode xform = _formConsole.build(formName, EXmlConfig.Value);
      // 查找控件对象
      String controlPath = controlNode.get(PTY_CONTROL);
      FXmlNode xcontrol = xform.findPath(controlPath, '/', "name");
      if(null == xcontrol){
         throw new FFatalError("Can't find control. (form={0},control={1})", formName, controlPath);
      }
      // 获取数据
      String ouid = controlNode.get("ouid");
      String parameters = controlNode.get("parameters");
      String dataValue = xcontrol.get("data_value");
      String functionName = RString.left(dataValue, "(");
      if(RString.isEmpty(functionName)){
         throw new FFatalError("Parse function failure. (data_value={0})", dataValue);
      }
      FSqlFunction function = new FSqlFunction(functionName);
      function.createParameter("ouid_", ouid, ESqlDataType.Integer, ESqlDataDirection.In);
      function.createParameter("params_", parameters, ESqlDataType.String, ESqlDataDirection.In);
      sqlContext.activeConnection().execute(function);
      String result = function.returnAsString();
      // 返回数据
      output.config().set("action", input.config().get("action"));
      FXmlNode outNode = output.config().createNode("Control");
      outNode.attributes().assign(controlNode.attributes());
      outNode.set("result", result);
   }

   @Override
   public void tableList(IWebContext context,
                         ISqlSessionContext sqlContext,
                         IWebInput input,
                         IWebOutput output){
      FXmlNode node = input.config();
      FXmlNode form = node.findNode("Form");
      FXmlNode r = null;
      if(form != null){
         FXmlNode rowPath = form.findNode("RowPath");
         if(rowPath != null){
            r = rowPath.findNode("Row");
         }
      }
      FXmlNode data = new FXmlNode("Data");
      for(int n = 0; n < 3; n++){
         FXmlNode row = new FXmlNode("Row");
         row.attributes().assign(r.attributes());
         data.push(row);
      }
      form.push(data);
      output.config().push(form);
   }

   @Override
   public void update(IWebContext context,
                      ISqlSessionContext sqlContext,
                      IWebInput input,
                      IWebOutput output){
      FXmlNodes formNodes = input.config().nodes();
      if(!formNodes.isEmpty()){
         // 创建更新参数。
         FWebFormDatasetArgs args = new FWebFormDatasetArgs(context, sqlContext);
         // 设置用户信息。
         args.setSessionConnectId(context.session().connectId());
         for(FXmlNode formNode : formNodes){
            if(formNode.isName(NODE_FORM)){
               FXmlNode dsNode = formNode.findNode(NODE_DATASET);
               if(null != dsNode){
                  String formName = formNode.get(PTY_NAME);
                  String formPath = formNode.get(PTY_PATH);
                  IXmlObject xform = _formConsole.find(formName);
                  if(null == xform){
                     throw new FFatalError("Can't find form. (name={0})", formName);
                  }
                  // 开始数据处理
                  if(dsNode.hasNode()){
                     args.setFormName(formName);
                     args.setFormPath(formPath);
                     args.setForm(xform);
                     for(FXmlNode node : dsNode.nodes()){
                        if(node.isName(PTY_ROW)){
                           String flag = node.get(PTY_OPERATION);
                           String status = node.get("_status");
                           String id = node.get("_id");
                           if(ACTION_INSERT.equals(flag)){
                              // 新建一条记录
                              args.setLogic(null);
                              args.setValues(node.attributes());
                              _formDatasetConsole.insert(args);
                              node.set(PTY_OPERATION, status);
                              node.set("_id", id);
                              node.removeAttribute("id_rownum");
                           }else if(ACTION_UPDATE.equals(flag)){
                              // 修改一条记录
                              args.setLogic(null);
                              args.setValues(node.attributes());
                              _logger.debug(this, "update", "update form data(name={0})", formName);
                              _formDatasetConsole.update(args);
                              node.set(PTY_OPERATION, status);
                              node.set("_id", id);
                              node.removeAttribute("id_rownum");
                           }else if(ACTION_DELETE.equals(flag)){
                              // 删除一条记录
                              args.setLogic(null);
                              args.setValues(node.attributes());
                              _formDatasetConsole.delete(args);
                              node.set(PTY_OPERATION, status);
                              node.set("_id", id);
                              node.removeAttribute("id_rownum");
                           }else{
                              throw new FFatalError("Unknown row action. (action={0})", flag);
                           }
                        }
                     }
                  }
               }
            }
         }
      }
      // 输出修改获得记录
      output.config().set("action", input.config().get("action"));
      output.config().nodes().append(formNodes);
   }
}
