/*
 * @(#)FWebFormDatasetConsole.java
 *
 * Copyright 2009 microbject, All Rights Reserved.
 *
 */
package org.mo.web.core.webform;

import org.mo.com.collections.FDataset;
import org.mo.com.collections.FDatasets;
import org.mo.com.collections.FRow;
import org.mo.com.data.ESqlDataDirection;
import org.mo.com.data.ESqlDataType;
import org.mo.com.data.FSql;
import org.mo.com.data.FSqlProcedure;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FString;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.IAttributes;
import org.mo.com.lang.IStringPair;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RInteger;
import org.mo.com.lang.RString;
import org.mo.com.lang.temp.RPack;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlNodeMap;
import org.mo.com.xml.FXmlNodes;
import org.mo.com.xml.FXmlObjects;
import org.mo.com.xml.IXmlObject;
import org.mo.com.xml.IXmlObjects;
import org.mo.com.xml.RXml;
import org.mo.core.aop.face.ALink;
import org.mo.data.dataset.IDatasetConsole;
import org.mo.data.dataset.common.XDataStore;
import org.mo.eng.data.IDatabaseConsole;
import org.mo.eng.data.common.FSqlOrderFields;
import org.mo.eng.data.common.FSqlQuery;
import org.mo.eng.data.common.FSqlSearchField;
import org.mo.eng.data.common.FSqlSearchFields;
import org.mo.eng.data.common.ISqlContext;
import org.mo.eng.list.IListConsole;
import org.mo.logic.data.ICpSession;
import org.mo.logic.data.impl.FCpSessionImpl;
import org.mo.web.core.process.FWebProcessArgs;
import org.mo.web.core.process.IWebProcessConsole;
import org.mo.web.core.webform.control.XBrowser;
import org.mo.web.core.webform.control.XBrowserControl;
import org.mo.web.core.webform.control.XFixGrid;
import org.mo.web.core.webform.control.XFixTable;
import org.mo.web.core.webform.control.XGrid;
import org.mo.web.core.webform.control.XPageSheet;
import org.mo.web.core.webform.control.XTable;
import org.mo.web.core.webform.control.XWebBrowser;
import org.mo.web.core.webform.control.XWebFixGrid;
import org.mo.web.core.webform.control.XWebFixTable;
import org.mo.web.core.webform.control.XWebForm;
import org.mo.web.core.webform.control.XWebGrid;
import org.mo.web.core.webform.control.XWebPicker;
import org.mo.web.core.webform.control.XWebTable;
import org.mo.web.protocol.common.IWebUser;

/**
 * <T>操作表单数据的控制台。</T>
 * <P>查询数据集。</P>
 * <P>完成数据的新建，更新，删除操作。</P>
 * 
 * @author MAOCY
 * @version 1.00 - 2009/02/27
 */
public class FWebFormDatasetConsole
      implements
         IWebFormDatasetConsole
{

   private static ILogger _logger = RLogger.find(FWebFormDatasetConsole.class);

   public static final String NAM_RADIO = "Radio";

   public static final String PTY_ACTION_DELETE = "action_delete";

   public static final String PTY_ACTION_INSERT = "action_insert";

   public static final String PTY_ACTION_UPDATE = "action_update";

   public static final String PTY_DATA_DEFAULT = "data_default";

   public static final String PTY_DATA_FETCH = "data_fetch";

   public static final String PTY_DATA_NAME = "data_name";

   public static final String PTY_DATA_PREPARE = "data_prepare";

   public static final String PTY_DATA_TOTAL = "data_total";

   public static final String PTY_DATA_VALUE = "data_value";

   public static final String PTY_DATASET_GROUP = "dataset_group";

   public static final String PTY_DATASET_ORDER = "dataset_order";

   public static final String PTY_DATASET_SEARCH = "dataset_search";

   public static final String PTY_NAME = "name";

   // 表单对象
   public static final String[] FORM_NAMES = new String[]{"FixGrid", "WebFixGrid", "Grid", "WebGrid", "FixTable", "WebFixTable", "Table", "WebTable", "Form", "WebForm", "Picker", "WebPicker", "Browser", "WebBrowser"};

   @ALink
   protected IDatabaseConsole _databaseConsole;

   @ALink
   protected IDatasetConsole _datasetConsole;

   @ALink
   protected IWebFormConsole _formConsole;

   @ALink
   protected IListConsole _listConsole;

   @ALink
   protected IWebProcessConsole _processConsole;

   /**
    * <T>获得当前节点下所有数据控件组成的集合</T>
    * 
    * @param map 数据控件集合
    * @param xroot 根节点
    */
   protected void buildDataFields(FXmlNodeMap map,
                                  FXmlNode xroot){
      if(xroot.hasNode()){
         for(FXmlNode xnode : xroot){
            // 不处理含有表单的控件
            //if(RStrings.inRangeIgnoreCase(xnode.name(), FORM_NAMES)){
            //   continue;
            //}
            // 递归处理所有子节点
            if(xnode.hasNode()){
               buildDataFields(map, xnode);
            }
            // 当前数据是否可查询
            if(!RBoolean.parse(xnode.get(PTY_DATA_FETCH))){
               continue;
            }
            // 存储数据名称对应的控件
            String dataName = xnode.get(PTY_DATA_NAME);
            if(RString.isNotEmpty(dataName)){
               map.set(dataName, xnode);
            }
         }
      }
   }

   @Override
   public void delete(FWebFormDatasetArgs args){
      ISqlContext context = args.sqlContext();
      IXmlObject xform = args.form();
      IAttributes logic = args.logic();
      IAttributes values = args.values();
      // 获得新建数据时的logic参数
      String datasetDeleteParameters = xform.innerGet(XWebForm.PTY_DATASET_DELETE_PARAMETERS);
      if(RString.isNotEmpty(datasetDeleteParameters)){
         logic.set("name", datasetDeleteParameters);
      }
      // 获得数据集对象
      IXmlObject xdataset = findFormDataset(args.form());
      String dataLogic = xdataset.innerGet(XDataStore.PTY_DATA_LOGIC);
      if(RString.isEmpty(dataLogic)){
         throw new FFatalError("Data logic is null. (logic={0})", dataLogic);
      }
      if(_logger.debugAble()){
         _logger.debug(this, "doDelete", "Delete data (form={0}, dataset={1}, value={2})", xform.innerGet(PTY_NAME), xdataset.innerGet(XDataStore.PTY_NAME), values.dump());
      }
      // 执行处理前操作
      String action = xform.innerGet(PTY_ACTION_DELETE);
      if(RString.isNotEmpty(action)){
         processAction(action, EWebFormAction.Before, args);
      }
      // 创建存储过程对象
      String dsDelete = xform.innerGet("dataset_delete");
      String processName = RString.nvl(dsDelete, "Do_Delete");
      FSqlProcedure procedure = new FSqlProcedure(processName);
      procedure.setLogicName(dataLogic);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("params_", values, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("object_uid_", values.get("ouid"), ESqlDataType.String, ESqlDataDirection.In);
      // 执行存储过程
      context.activeConnection().execute(procedure);
      String params = procedure.parameter("params_").asString();
      // 获取输出数据
      values.clear();
      if(RString.isNotEmpty(params)){
         //values.unpack(params);
      }
      // 执行处理后操作
      if(RString.isNotEmpty(action)){
         processAction(action, EWebFormAction.After, args);
      }
   }

   /* (non-Javadoc)
    * @see org.mo.web.core.webform.IWebFormDatasetConsole#fetch(org.mo.web.core.webform.FWebFormDatasetArgs)
    */
   @Override
   public FDatasets fetch(FWebFormDatasetArgs args){
      String formName = args.formName();
      // 获得表单定义
      FXmlNode formConfig = _formConsole.findConfig(new TWebFormArgs(formName, EXmlConfig.Value, args.sqlContext()));
      String path = args.controlPath();
      if(!RString.isEmpty(path)){
         if(path.startsWith("/")){
            path = path.substring(1);
         }
         if(!RString.isEmpty(path)){
            formConfig = formConfig.findPath(path, '/', "name");
            if(null == formConfig){
               throw new FFatalError("Can't find control. (path={0})", args.controlPath());
            }
         }
      }
      // 查询数据
      FDatasets datasets = new FDatasets();
      fetchDatasetDeep(datasets, formConfig, args, args.getSearch(), args.getOrder(), args.values(), EWebFormMode.Fetch);
      return datasets;
   }

   protected void fetchAllDeep(FXmlNode resultNode,
                               FWebFormDatasetArgs args,
                               FXmlNode config){
      IAttributes values = args.values();
      // 查找可以查询数据的结果集
      if(XWebForm.isName(config.name())){
         // 数据表单的情况
         FXmlNode dsNode = fetchNode(args, config);
         if(1 == dsNode.nodeCount()){
            IAttributes data = dsNode.node(0).attributes();
            values.append(data);
            if(config.hasNode()){
               // 处理所有对象的当前值
               for(FXmlNode childNode : config.nodes()){
                  String name = childNode.get(PTY_NAME);
                  String dataName = childNode.get(PTY_DATA_NAME);
                  if(RString.isNotEmpty(name, dataName)){
                     values.set(name, data.get(dataName));
                  }
               }
            }
         }
         resultNode.push(dsNode);
      }else if(XPageSheet.isName(config.name())){
         // 分页空间中表单引用的情况
         String formRefer = config.get("form_refer");
         if(RString.isNotEmpty(formRefer)){
            FXmlNode childConfig = _formConsole.findConfig(new TWebFormArgs(formRefer, EXmlConfig.Value, args.sqlContext()));
            // 取得表单关联
            String formLink = config.get("form_link");
            FSqlSearchFields search = args.search();
            search.clear();
            IAttributes packs = RPack.unpackLink(formLink);
            if(null != packs){
               int count = packs.count();
               for(int n = 0; n < count; n++){
                  String value = packs.value(n);
                  if(RString.isNotEmpty(value)){
                     String name = packs.name(n);
                     FXmlNode childNode = childConfig.findNode(PTY_NAME, name);
                     if(null == childNode){
                        throw new FFatalError("Build parse error. (name={0})", name);
                     }
                     String searchName = childNode.get(PTY_DATA_NAME);
                     if(value.startsWith("${") && value.endsWith("}")){
                        value = value.substring(2, value.length() - 1);
                        value = values.get(value);
                     }
                     search.push(new FSqlSearchField(searchName, value));
                  }
               }
            }
            // 获取表单数据
            FXmlNode dsChildNode = fetchNode(args, childConfig);
            dsChildNode.set("control_name", config.get(PTY_NAME));
            resultNode.push(dsChildNode);
            config = childConfig;
         }
      }else if(XTable.isName(config.name())){
         // 分页空间中表单引用的情况
         String formRefer = config.get("form_refer");
         if(RString.isNotEmpty(formRefer)){
            // 表格是数据引用的情况
            FXmlNode childConfig = _formConsole.buildConfig(new TWebFormArgs(formRefer, EXmlConfig.Value, args.sqlContext()));
            // 取得表单关联
            String formLink = config.get("form_link");
            FSqlSearchFields search = args.search();
            search.clear();
            IAttributes packs = RPack.unpackLink(formLink);
            if(null != packs){
               int count = packs.count();
               for(int n = 0; n < count; n++){
                  String value = packs.value(n);
                  if(RString.isNotEmpty(value)){
                     String name = packs.name(n);
                     FXmlNode childNode = childConfig.findNode(PTY_NAME, name);
                     if(null == childNode){
                        throw new FFatalError("Build parse error. (name={0})", name);
                     }
                     String searchName = childConfig.get(PTY_DATA_NAME);
                     if(value.startsWith("${") && value.endsWith("}")){
                        value = value.substring(2, value.length() - 1);
                        value = values.get(value);
                     }
                     search.push(new FSqlSearchField(searchName, value));
                  }
               }
            }
            // 获取表单数据
            FXmlNode dsChildNode = fetchNode(args, childConfig);
            dsChildNode.set("control_name", config.get(PTY_NAME));
            resultNode.push(dsChildNode);
            config = childConfig;
         }else{
            // 获取表单数据
            FXmlNode dsChildNode = fetchNode(args, config);
            dsChildNode.set("control_name", config.get(PTY_NAME));
            resultNode.push(dsChildNode);
         }
      }
      // 递归查找子结果集
      if(config.hasNode()){
         for(FXmlNode childNode : config.nodes()){
            fetchAllDeep(resultNode, args, childNode);
         }
      }
   }

   /**
    * <T>递归获得表单内所有子节点上的数据集。</T>
    * 
    * @param config 设置节点信息
    * @param args 参数对象
    * @param values 当前值列表
    * @param searchs 条件列表
    * @param orders 排序列表
    * @param values 初始数据集
    */
   protected FDataset fetchDataset(FXmlNode config,
                                   FWebFormDatasetArgs args,
                                   FSqlSearchFields searchs,
                                   FSqlOrderFields orders,
                                   IAttributes values){
      FDataset dataset = new FDataset();
      try{
         // 获得调用数据
         makeParameters(config, args, values);
         // 获得数据集对应的数据
         ISqlContext sqlContext = args.sqlContext();
         String datasetProcedure = config.get("dataset_procedure");
         if(!RString.isEmpty(datasetProcedure)){
            // 生成执行参数
            IAttributes params = new FAttributes();
            params.append(values);
            params.set("page_size", Integer.toString(args.pageSize()));
            params.set("page", Integer.toString(args.page()));
            // 获得数据集对应的数据处理
            FSqlProcedure procedure = new FSqlProcedure(datasetProcedure);
            procedure.createParameter("logic_", null, ESqlDataType.String, ESqlDataDirection.InOut);
            procedure.createParameter("params_", params, ESqlDataType.String, ESqlDataDirection.InOut);
            procedure.createParameter("dataset_", null, ESqlDataType.String, ESqlDataDirection.InOut);
            sqlContext.activeConnection().execute(procedure);
            // 生成结果数据集
            FStrings datasetStrings = procedure.parameter("dataset_").asStrings();
            dataset = new FDataset();
            for(String datasetString : datasetStrings){
               FRow row = dataset.createRow();
               row.unpack(datasetString);
            }
         }else{
            // 获得数据集对应的数据名称
            String datasetName = config.get("dataset");
            FXmlNode sqlNode = config.findNode("Sql");
            if(RString.isEmpty(datasetName) && null == sqlNode){
               // 建立可查询对象的集合
               FXmlNodeMap fieldMap = new FXmlNodeMap();
               buildDataFields(fieldMap, config);
               // 设置所有字段信息
               dataset = new FDataset();
               FRow row = new FRow();
               int count = fieldMap.count();
               for(int n = 0; n < count; n++){
                  FXmlNode child = fieldMap.value(n);
                  String dataName = child.get(PTY_DATA_NAME);
                  // 替换数据内容中的变量
                  String dataValue = child.get(PTY_DATA_VALUE);
                  if(RString.isNotEmpty(dataValue)){
                     dataValue = RPack.replaceLink(dataValue, values).toString();
                     row.set(dataName, dataValue);
                  }
               }
               dataset.push(row);
            }else{
               // 查询数据库，生成结果集
               FSql sql = makeFetchSql(args, config, searchs, orders, values, args.isResearch(), false);
               if(!sql.isEmpty()){
                  // 获得分页大小
                  int pageSize = args.pageSize();
                  if(config.contains("disp_count")){
                     pageSize = RInteger.parse(config.get("disp_count"), pageSize);
                  }
                  // 获得数据
                  dataset = sqlContext.activeConnection().fetchDataset(sql.toString(), pageSize, args.page());
                  // 获得统计数据
                  String panelTotal = config.attributes().get("panel_total");
                  if(RBoolean.parse(panelTotal)){
                     sql = makeFetchSql(args, config, searchs, orders, values, args.isResearch(), true);
                     FRow row = sqlContext.activeConnection().find(sql.toString());
                     row.set("_type", "total");
                     dataset.push(row);
                  }
               }
            }
         }
      }catch(Exception e){
         _logger.error(this, "fetchDataset", e);
      }
      dataset.setCode(config.fullPath("name", '/'));
      return dataset;
   }

   /**
    * <T>递归获得表单内所有子节点上的数据集。</T>
    * <P>查询数据时，条件信息自父向子进行逐层传递。</P>
    * 
    * @param datasets 输出数据集
    * @param config 设置节点信息
    * @param args 参数对象
    * @param values 当前值列表
    * @param searchs 条件列表
    * @param orders 排序列表
    */
   protected void fetchDatasetDeep(FDatasets datasets,
                                   FXmlNode config,
                                   FWebFormDatasetArgs args,
                                   FSqlSearchFields searchs,
                                   FSqlOrderFields orders,
                                   IAttributes values,
                                   EWebFormMode mode){
      // 查找可以查询数据的结果集
      String type = config.name();
      if(XWebForm.isName(type)){
         if(testFetchDatasetAble(config, mode)){
            // 数据表单的情况
            FDataset dataset = fetchDataset(config, args, searchs, orders, values);
            if(!dataset.isEmpty()){
               FRow row = dataset.get(0);
               values = new FAttributes(values);
               for(IStringPair pair : row){
                  String value = pair.value();
                  // 解包控件打包数据的情况
                  if(RString.startsWith(value, "C#")){
                     IAttributes pack = new FAttributes();
                     //pack.unpack(value.substring(2));
                     if(pack.contains("OUID")){
                        value = pack.get("OUID");
                     }else if(pack.contains("U")){
                        value = pack.get("U");
                     }
                  }
                  values.set(pair.name(), value);
               }
            }
            datasets.push(dataset);
            searchs.clear();
         }
      }else if(XPageSheet.isName(type)){
         if(testFetchDatasetAble(config, mode)){
            // 分页时的处理
            String formRefer = config.get("form_refer");
            // 分页空间中表单引用的情况
            if(config.hasNode() && RString.isNotEmpty(formRefer)){
               FXmlNode childConfig = config.node(0);
               // 获取表单数据
               FDataset dataset = fetchDataset(childConfig, args, searchs, orders, values);
               datasets.push(dataset);
               // 设置递归子节点
               config = childConfig;
            }
         }
      }else if(XWebPicker.isName(type)){
         // 获取表单数据
         if(testFetchDatasetAble(config, mode)){
            FDataset dataset = fetchDataset(config, args, searchs, orders, values);
            datasets.push(dataset);
         }
         return;
      }else if(XTable.isName(type) || XWebTable.isName(type) || XFixTable.isName(type) || XWebFixTable.isName(type)){
         // 获取表单数据
         if(testFetchDatasetAble(config, mode)){
            FDataset dataset = fetchDataset(config, args, searchs, orders, values);
            datasets.push(dataset);
         }
         return;
      }else if(XGrid.isName(type) || XWebGrid.isName(type) || XFixGrid.isName(type) || XWebFixGrid.isName(type)){
         // 获取表单数据
         if(testFetchDatasetAble(config, mode)){
            FDataset dataset = fetchDataset(config, args, searchs, orders, values);
            datasets.push(dataset);
            return;
         }
      }else if(XBrowserControl.isName(type) || XBrowser.isName(type) || XWebBrowser.isName(type)){
         // 获取表单数据
         if(testFetchDatasetAble(config, mode)){
            FDataset dataset = fetchDataset(config, args, searchs, orders, values);
            datasets.push(dataset);
         }
         return;
      }
      // 递归查找子结果集
      if(config.hasNode()){
         for(FXmlNode childNode : config.nodes()){
            fetchDatasetDeep(datasets, childNode, args, searchs, orders, values, mode);
         }
      }
   }

   /* (non-Javadoc)
    * @see org.mo.web.core.webform.IWebFormDatasetConsole#fetch(org.mo.web.core.webform.FWebFormDatasetArgs)
    */
   public FXmlNode fetchNode(FWebFormDatasetArgs args){
      ISqlContext context = args.sqlContext();
      String formName = args.formName();
      if(RString.isEmpty(formName)){
         IXmlObject xform = args.form();
         formName = xform.innerGet(PTY_NAME);
      }
      // 生成查询用的SQL
      FXmlNode formConfig = _formConsole.findConfig(new TWebFormArgs(formName, EXmlConfig.Value, args.sqlContext()));
      FSql sql = makeFetchSql(args, formConfig, args.getSearch(), args.getOrder(), args.values(), false, false);
      // 查询数据库，生成结果集
      FDataset dataset = context.activeConnection().fetchDataset(sql.toString(), args.pageSize(), args.page());
      // 创建输出对象
      FXmlNode dsNode = new FXmlNode(FDataset.NAME);
      dsNode.set(PTY_NAME, formName);
      dsNode.set("page", dataset.page());
      dsNode.set("page_size", dataset.pageSize());
      dsNode.set("page_count", dataset.pageCount());
      dsNode.set("total", dataset.total());
      if(null != dataset){
         for(FRow row : dataset){
            dsNode.createNode(FRow.NAME).attributes().append(row);
         }
      }
      return dsNode;
   }

   protected FXmlNode fetchNode(FWebFormDatasetArgs args,
                                FXmlNode config){
      ISqlContext context = args.sqlContext();
      // 生成查询用的SQL
      FSql sql = makeFetchSql(args, config, args.getSearch(), args.getOrder(), args.values(), false, false);
      // 查询数据库，生成结果集
      FDataset dataset = context.activeConnection().fetchDataset(sql.toString(), args.pageSize(), args.page());
      // 创建输出对象
      FXmlNode dsNode = new FXmlNode(FDataset.NAME);
      dsNode.set(PTY_NAME, config.get(PTY_NAME));
      dsNode.set("page", dataset.page());
      dsNode.set("page_size", dataset.pageSize());
      dsNode.set("page_count", dataset.pageCount());
      dsNode.set("total", dataset.total());
      if(null != dataset){
         for(FRow row : dataset){
            dsNode.createNode(FRow.NAME).attributes().append(row);
         }
      }
      return dsNode;
   }

   public FXmlNode fetchNodeAll(FWebFormDatasetArgs args){
      FXmlNode resultNode = new FXmlNode(RXml.DEFAULT_ROOT_NAME);
      FXmlNode formConfig = _formConsole.findConfig(new TWebFormArgs(args.formName(), EXmlConfig.Value, args.sqlContext()));
      fetchAllDeep(resultNode, args, formConfig);
      return resultNode;
   }

   @Override
   public FXmlNode find(FWebFormDatasetArgs args){
      FXmlNode formConfig = _formConsole.findConfig(new TWebFormArgs(args.formName(), EXmlConfig.Value, args.sqlContext()));
      FSql sql = makeFetchSql(args, formConfig, args.getSearch(), args.getOrder(), args.values(), false, false);
      // Sql
      FXmlNode resultNode = new FXmlNode();
      FRow row = args.sqlContext().activeConnection().find(sql.toString());
      if(null != row){
         FXmlNode node = new FXmlNode(FRow.NAME);
         node.attributes().append(row);
         resultNode.push(node);
      }
      // Find link data
      IXmlObjects xchildren = new FXmlObjects();
      findChildren(xchildren, args.form());
      int count = xchildren.count();
      for(int n = 0; n < count; n++){
         IXmlObject xchild = xchildren.get(n);
         if(XPageSheet.NAME.equals(xchild.name())){
            String formName = xchild.innerGet("form_name");
            if(RString.isNotEmpty(formName)){
               IXmlObject xchildForm = _formConsole.find(formName);
               FWebFormDatasetArgs innerArgs = args.clone();
               args.setForm(xchildForm);
               FXmlNode dsNode = fetchNode(innerArgs);
               resultNode.push(dsNode);
            }
         }
      }
      return resultNode;
   }

   protected FSql findAllDataComponents(FXmlNode xform){
      return null;
   }

   protected void findChildren(IXmlObjects xobjects,
                               IXmlObject xobject){
      int count = xobject.children().count();
      for(int n = 0; n < count; n++){
         IXmlObject xchild = xobject.children().get(n);
         xobjects.push(xchild);
         findChildren(xobjects, xchild);
      }
   }

   protected IXmlObject findField(IXmlObjects fields,
                                  String name,
                                  String value){
      int count = fields.count();
      for(int n = 0; n < count; n++){
         IXmlObject xobject = fields.get(n);
         if(value.equals(xobject.innerGet(name))){
            return xobject;
         }
      }
      return null;
   }

   protected IXmlObject findFormDataset(IXmlObject xform){
      // 检查表单关联的数据集的存在性
      String datasetName = xform.innerGet("dataset");
      if(RString.isEmpty(datasetName)){
         throw new FFatalError("Form dataset is null. (form={0})", xform.innerGet(PTY_NAME));
      }
      IXmlObject xdataset = _datasetConsole.find(datasetName);
      if(null == xdataset){
         throw new FFatalError("Dataset is null. (name={0})", datasetName);
      }
      return xdataset;
   }

   /* (non-Javadoc)
    * @see org.mo.web.core.webform.IWebFormDatasetConsole#insert(org.mo.web.core.webform.FWebFormDatasetArgs)
    */
   @Override
   public void insert(FWebFormDatasetArgs args){
      ISqlContext context = args.sqlContext();
      IXmlObject xform = args.form();
      IAttributes logic = args.logic();
      IAttributes values = args.values();
      // 获得新建数据时的logic参数
      String datasetInsertParameters = xform.innerGet(XWebForm.PTY_DATASET_INSERT_PARAMETERS);
      if(RString.isNotEmpty(datasetInsertParameters)){
         logic.set("name", datasetInsertParameters);
      }
      // 执行处理前操作
      String action = xform.innerGet(PTY_ACTION_INSERT);
      if(RString.isNotEmpty(action)){
         processAction(action, EWebFormAction.Before, args);
      }
      // 获得数据集对象
      IXmlObject xdataset = findFormDataset(xform);
      String dataLogic = xdataset.innerGet(XDataStore.PTY_DATA_LOGIC);
      if(RString.isEmpty(dataLogic)){
         throw new FFatalError("Data logic is null. (logic={0})", dataLogic);
      }
      if(_logger.debugAble()){
         _logger.debug(this, "doInsert", "Insert data (form={0}, dataset={1}, value={2})", xform.innerGet(PTY_NAME), xdataset.innerGet(XDataStore.PTY_NAME), values.dump());
      }
      // 创建存储过程对象
      String dsInsert = xform.innerGet("dataset_insert");
      String processName = RString.nvl(dsInsert, "Do_Insert");
      FSqlProcedure procedure = new FSqlProcedure(processName);
      procedure.setLogicName(dataLogic);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("params_", values, ESqlDataType.String, ESqlDataDirection.InOut);
      // 执行存储过程
      context.activeConnection().execute(procedure);
      String params = procedure.parameter("params_").asString();
      // 获取输出数据
      values.clear();
      if(RString.isNotEmpty(params)){
         //values.unpack(params);
      }
      // 执行处理前操作
      if(RString.isNotEmpty(action)){
         processAction(action, EWebFormAction.After, args);
      }
   }

   protected FSql makeCommonSql(FWebFormDatasetArgs args,
                                String sql,
                                IAttributes values){
      // 分解DataValue内的信息(${name}中取出name名称)
      IAttributes sqlValues = new FAttributes();
      sqlValues.append(values);
      IWebUser user = args.webContext().session().user();
      if(null != user){
         sqlValues.set("session.user_id", user.userId());
      }
      FSql result = new FSql();
      RPack.replaceLink(result, sql, sqlValues);
      return result;
   }

   protected FSql makeFetchSql(FWebFormDatasetArgs args,
                               FXmlNode xform,
                               FSqlSearchFields searchFields,
                               FSqlOrderFields orderFields,
                               IAttributes values,
                               boolean isResearch,
                               boolean isTotal){
      // 获得数据查询定义中的查询命令
      FSql sql = new FSql();
      FXmlNode sqlNode = xform.findNode("Sql");
      if(null != sqlNode){
         sql.append("SELECT * FROM ( ");
         sql.append(sqlNode.text());
         sql.append(")");
      }
      // 建立可查询对象的集合
      FXmlNodeMap fieldMap = new FXmlNodeMap();
      buildDataFields(fieldMap, xform);
      int count = fieldMap.count();
      // 获得数据集对应的数据名称
      if(sql.isEmpty()){
         String datasetName = xform.get("dataset");
         if(RString.isEmpty(datasetName)){
            throw new FFatalError("Form dataset is null. (form={0})", xform.get(PTY_NAME));
         }
         IXmlObject xdataset = _datasetConsole.get(datasetName);
         String tableName = xdataset.innerGet(XDataStore.PTY_DATA_NAME);
         // 检查表单关联的数据集的存在性
         sql.append("SELECT ");
         if(isTotal){
            // 生成集合字段
            boolean isFirst = true;
            for(int n = 0; n < count; n++){
               FXmlNode child = fieldMap.value(n);
               // 生成字段查询信息
               String dataName = child.get(PTY_DATA_NAME);
               String dataTotal = child.get(PTY_DATA_TOTAL);
               if(RString.isNotEmpty(dataTotal)){
                  if(!isFirst){
                     isFirst = false;
                     sql.append(',');
                  }
                  sql.append(dataTotal, '(', dataName, ')', ' ', dataName);
               }
            }
         }else{
            sql.append("*");
         }
         sql.append(" FROM ( SELECT ");
         // 生成所有字段查询信息
         boolean hasField = false;
         for(int n = 0; n < count; n++){
            FXmlNode child = fieldMap.value(n);
            // 生成字段分隔
            if(n > 0){
               sql.append(',');
            }
            // 生成字段查询信息（DataValue DataName）
            String dataValue = child.get(PTY_DATA_VALUE);
            if(RString.isNotEmpty(dataValue)){
               sql.append(dataValue);
               sql.append(' ');
            }
            sql.append(child.get(PTY_DATA_NAME));
            hasField = true;
         }
         if(!hasField){
            sql.clear();
            return sql;
         }
         sql.append(" FROM ");
         sql.append(tableName);
         // 建立内部查询条件
         if(!isResearch){
            String dsSearch = xform.get(PTY_DATASET_SEARCH);
            if(!RString.isEmpty(dsSearch)){
               sql.append(" WHERE ", dsSearch);
            }
         }
         // 建立内部分组代码
         String dsGroup = xform.get(PTY_DATASET_GROUP);
         if(!RString.isEmpty(dsGroup)){
            sql.append(" GROUP BY ", dsGroup);
         }
         // 建立内部排序条件
         String dsOrder = xform.get(PTY_DATASET_ORDER);
         if(!RString.isEmpty(dsOrder)){
            sql.append(" ORDER BY ", dsOrder);
         }
         sql.append(" )");

      }
      // 建立外部查询代码
      FString searchSql = new FString();
      if(null != searchFields && searchFields.hasSearch()){
         count = searchFields.count();
         for(int n = 0; n < count; n++){
            if(!searchSql.isEmpty()){
               searchSql.append(" AND ");
            }
            // 建立字段查询信息
            FSqlSearchField field = searchFields.get(n);
            field.makeSearchSql(searchSql);
         }
      }
      if(!searchSql.isEmpty()){
         sql.append(" WHERE ", searchSql);
      }
      // 建立外部排序代码
      FString orderSql = new FString();
      if(null != orderFields && orderFields.hasOrder()){
         count = orderFields.count();
         for(int n = 0; n < count; n++){
            if(!orderSql.isEmpty()){
               orderSql.append(", ");
            }
            // 建立字段排序信息
            orderFields.get(n).makeSql(orderSql);
         }
      }
      if(!orderSql.isEmpty()){
         sql.append(" ORDER BY ");
         sql.append(orderSql);
      }
      // 分解DataValue内的信息(${name}中取出name名称)
      return makeCommonSql(args, sql.toString(), values);
   }

   /**
    * <T>递归获得表单内所有子节点上的数据集。</T>
    * 
    * @param config 设置节点信息
    * @param args 参数对象
    * @param values 初始数据集
    */
   protected void makeParameters(FXmlNode config,
                                 FWebFormDatasetArgs args,
                                 IAttributes values){
      ISqlContext sqlContext = args.sqlContext();
      // 获得准备数据
      String parameters = config.get("dataset_parameters");
      if(!RString.isEmpty(parameters)){
         FSql result = new FSql();
         RPack.replaceLink(result, parameters, values);
         String[] params = RString.split(result.toString(), ';');
         if(null != parameters){
            for(int i = 0; i < params.length; i++){
               String sql = params[i];
               if(!RString.isEmpty(sql)){
                  // 查询数据，将结果放入查询变量中
                  FSqlQuery query = new FSqlQuery(sqlContext, params[i]);
                  FRow row = query.fetchRow();
                  if(null != row){
                     values.append(row);
                  }
               }
            }
         }
      }
   }

   protected FSql makePrepareSql(FWebFormDatasetArgs args,
                                 FXmlNode formNode,
                                 IAttributes values){
      boolean hasField = false;
      FSql sql = new FSql("SELECT ");
      // 生成所有字段查询信息
      boolean first = true;
      FXmlNodes children = formNode.nodes();
      int count = children.count();
      for(int n = 0; n < count; n++){
         FXmlNode child = children.get(n);
         String dataPrepare = child.get(PTY_DATA_PREPARE);
         if(!RString.isEmpty(dataPrepare)){
            // 增加数据内容
            if(first){
               first = false;
            }else{
               sql.append(',');
            }
            hasField = true;
            sql.append(dataPrepare);
            String dataName = child.get(PTY_DATA_NAME);
            if(!dataPrepare.equalsIgnoreCase(dataName)){
               sql.append(' ');
               sql.append(dataName);
            }
         }
      }
      sql.append(" FROM DUAL");
      // 替换字段信息
      if(hasField){
         return makeCommonSql(args, sql.toString(), values);
      }
      return null;
   }

   /* (non-Javadoc)
    * @see org.mo.web.core.webform.IWebFormDatasetConsole#prepare(org.mo.web.core.webform.FWebFormDatasetArgs)
    */
   @Override
   public FDatasets prepare(FWebFormDatasetArgs args){
      ISqlContext context = args.sqlContext();
      String formName = args.formName();
      FXmlNode formConfig = _formConsole.findConfig(new TWebFormArgs(formName, EXmlConfig.Value, args.sqlContext()));
      IAttributes logic = args.logic();
      IAttributes values = args.values();
      // 生成新建记录的默认数据
      FAttributes prepares = new FAttributes();
      prepares.append(values);
      args.setPrepares(prepares);
      // 获得数据集对象
      String datasetName = formConfig.get("dataset");
      FXmlNode datasetNode = _datasetConsole.buildConfig(datasetName);
      // 检查表单关联的数据集的存在性
      String dataLogic = datasetNode.get(XDataStore.PTY_DATA_LOGIC);
      if(!RString.isEmpty(dataLogic)){
         if(_logger.debugAble()){
            _logger.debug(this, "prepare", "Prepare data (form={0}, dataset={1}, value={2})", formName, datasetNode.get(XDataStore.PTY_NAME), prepares.dump());
         }
         // 执行处理前操作
         String action = formConfig.get("action_prepare");
         if(RString.isNotEmpty(action)){
            processAction(action, EWebFormAction.Before, args);
         }
         // 创建存储过程对象
         String dsUpdate = formConfig.get("dataset_prepare");
         String processName = RString.nvl(dsUpdate, "Prepare");
         FSqlProcedure procedure = new FSqlProcedure(processName);
         procedure.setLogicName(dataLogic);
         procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.InOut);
         procedure.createParameter("params_", prepares, ESqlDataType.String, ESqlDataDirection.InOut);
         // 执行存储过程
         context.activeConnection().execute(procedure);
         String params = procedure.parameter("params_").asString();
         // 获取输出数据
         if(RString.isNotEmpty(params)){
            //prepares.unpackNotEmpty(params, true);
            // 设置记录的默认值
            FXmlNodes children = formConfig.nodes();
            int count = children.count();
            for(int n = 0; n < count; n++){
               FXmlNode child = children.get(n);
               // 去掉单选框的情况
               if(child.isName(NAM_RADIO)){
                  continue;
               }
               // 设置默认值
               String dataName = child.get(PTY_DATA_NAME);
               String dataDefault = child.get(PTY_DATA_DEFAULT);
               if(RString.isNotEmpty(dataName) && RString.isNotEmpty(dataDefault) && RString.isEmpty(values.get(dataName))){
                  prepares.set(dataName, dataDefault);
               }
            }
         }
         // 获得调用数据
         makeParameters(formConfig, args, prepares);
         // 获取其它相关值
         FString sql = makePrepareSql(args, formConfig, prepares);
         if(null != sql){
            FRow row = context.activeConnection().find(sql.toString());
            prepares.append(row);
         }
         // 设置为返回值
         values.assign(prepares);
         // 执行处理后操作
         if(RString.isNotEmpty(action)){
            // 查询数据
            processAction(action, EWebFormAction.After, args);
         }
      }
      // 查询当前表单内所有子表单的数据
      FDatasets datasets = new FDatasets();
      for(FXmlNode childConfig : formConfig){
         fetchDatasetDeep(datasets, childConfig, args, args.getSearch(), args.getOrder(), args.values(), EWebFormMode.Prepare);
      }
      return datasets;
   }

   public void processAction(String action,
                             EWebFormAction type,
                             FWebFormDatasetArgs args){
      FWebProcessArgs processArgs = new FWebProcessArgs();
      processArgs.setWebContext(args.webContext());
      processArgs.define(EWebFormAction.class, type);
      processArgs.define(FWebFormDatasetArgs.class, args);
      _processConsole.execute(action, processArgs);
   }

   /* (non-Javadoc)
    * @see org.mo.web.core.webform.IWebFormDatasetConsole#sessionLink(org.mo.web.core.webform.FWebFormDatasetArgs)
    */
   @Override
   public void sessionLink(FWebFormDatasetArgs args){
      // 构建登录参数
      IAttributes logic = new FAttributes();
      IAttributes parameters = args.sessionParameters();
      parameters.set("session_id", args.getSessionConnectId());
      // 用户登录系统
      ISqlContext sqlContext = args.sqlContext();
      ICpSession cpSession = new FCpSessionImpl(sqlContext);
      FSqlProcedure procedure = cpSession.link(logic, parameters);
      parameters.append(procedure.parameter("parameters_").asAttributes());
   }

   /* (non-Javadoc)
    * @see org.mo.web.core.webform.IWebFormDatasetConsole#sessionUnlink(org.mo.web.core.webform.FWebFormDatasetArgs)
    */
   @Override
   public void sessionUnlink(FWebFormDatasetArgs args){
      // 构建登录参数
      IAttributes logic = new FAttributes();
      IAttributes parameters = new FAttributes();
      parameters.set("session_id", args.getSessionConnectId());
      // 用户退出系统
      ICpSession cpSession = new FCpSessionImpl(args.sqlContext());
      FSqlProcedure procedure = cpSession.unlink(logic, parameters, null);
      // 输出所有日志
      if(_logger.debugAble()){
         FStrings lines = new FStrings();
         FAttributes line = new FAttributes();
         lines.unpack(procedure.parameter("logger_").asString());
         for(String lineString : lines){
            line.unpack(lineString);
            String logicName = line.get("L");
            String methodName = line.get("M");
            String message = line.get("S");
            message = RString.format(message, line.get("1"), line.get("2"), line.get("3"), line.get("4"), line.get("5"));
            _logger.debug(logicName, methodName, message);
         }
      }
   }

   protected boolean testFetchDatasetAble(FXmlNode config,
                                          EWebFormMode mode){
      if(EWebFormMode.Prepare == mode){
         // 数据准备时，测试是否可以查询。
         return RBoolean.parse(config.get("query_prepare"));
      }
      return true;
   }

   /* (non-Javadoc)
    * @see org.mo.web.core.webform.IWebFormDatasetConsole#update(org.mo.web.core.webform.FWebFormDatasetArgs)
    */
   @Override
   public void update(FWebFormDatasetArgs args){
      String formName = args.formName();
      String formPath = args.formPath();
      ISqlContext context = args.sqlContext();
      IAttributes logic = args.logic();
      IAttributes values = args.values();
      // 获得表单关联信息
      FXmlNode formConfig = _formConsole.findConfig(new TWebFormArgs(formName, EXmlConfig.Value, args.sqlContext()));
      formConfig = formConfig.findPath(formPath, '/', "name");
      // 获得更新数据时的logic参数
      String datasetUpdateParameters = formConfig.get(XWebForm.PTY_DATASET_UPDATE_PARAMETERS);
      if(RString.isNotEmpty(datasetUpdateParameters)){
         logic.set("name", datasetUpdateParameters);
      }
      String processName = formConfig.get(XWebForm.PTY_DATASET_UPDATE);
      // 获得表单关联的数据集
      String datasetName = formConfig.get(XWebForm.PTY_DATASET);
      if(RString.isEmpty(datasetName)){
         throw new FFatalError("Form dataset is null. (form={0})", formName);
      }
      IXmlObject xdataset = _datasetConsole.get(datasetName);
      String dataLogic = xdataset.innerGet(XDataStore.PTY_DATA_LOGIC);
      if(RString.isEmpty(dataLogic) && RString.isEmpty(processName)){
         throw new FFatalError("Data logic and dataset update is null. (form={0}, dataset={1})", formName, datasetName);
      }
      // 检查表单关联的数据集的存在性
      processName = RString.nvl(processName, "Do_Update");
      if(_logger.debugAble()){
         _logger.debug(this, "doUpdate", "Update data (form={0}, dataset={1}, process={2}, value={3})", formName, dataLogic, processName, values.dump());
      }
      // 执行处理前操作 
      String action = formConfig.get(PTY_ACTION_UPDATE);
      if(RString.isNotEmpty(action)){
         processAction(action, EWebFormAction.Before, args);
      }
      // 创建存储过程对象
      FSqlProcedure procedure = new FSqlProcedure(processName);
      if(RString.isNotEmpty(dataLogic)){
         procedure.setLogicName(dataLogic);
      }
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("params_", values, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("object_uid_", values.get("ouid"), ESqlDataType.String, ESqlDataDirection.In);
      // 执行存储过程
      context.activeConnection().execute(procedure);
      String paramsPack = procedure.parameter("params_").asString();
      if(RString.isNotEmpty(paramsPack)){
         IAttributes params = new FAttributes();
         //params.unpack(paramsPack);
         values.append(params);
      }
      // 建立一条表单的完整数据
      FSqlSearchFields searchFields = args.search();
      searchFields.clear();
      String ouid = values.get("ouid");
      if(!RString.isEmpty(ouid)){
         searchFields.push(new FSqlSearchField("ouid", ouid));
      }
      String over = values.get("over");
      if(!RString.isEmpty(over)){
         searchFields.push(new FSqlSearchField("over", over));
      }
      // 查询数据库，生成结果集
      FSql sql = makeFetchSql(args, formConfig, args.getSearch(), args.getOrder(), args.values(), false, false);
      FDataset dataset = context.activeConnection().fetchDataset(sql.toString(), args.pageSize(), args.page());
      // 创建输出对象
      if(null != dataset && !dataset.isEmpty()){
         values.assign(dataset.get(0));
      }
      // 执行处理后操作
      if(RString.isNotEmpty(action)){
         processAction(action, EWebFormAction.Before, args);
      }
   }
}
