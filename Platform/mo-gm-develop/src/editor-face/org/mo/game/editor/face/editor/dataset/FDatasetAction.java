package org.mo.game.editor.face.editor.dataset;

import org.mo.com.io.RFile;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FDictionary;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FString;
import org.mo.com.lang.INamePair;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.REnum;
import org.mo.com.lang.RString;
import org.mo.com.lang.cultrue.REncoding;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlNodes;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.eng.data.IDatabaseConsole;
import org.mo.eng.data.common.ISqlContext;
import org.mo.eng.template.ITemplateParser;
import org.mo.game.editor.core.dataset.EDatasetBuildAction;
import org.mo.game.editor.core.dataset.EDatasetSourceType;
import org.mo.game.editor.core.dataset.FDatasetBuilderArgs;
import org.mo.game.editor.core.dataset.IDatasetConsole;
import org.mo.game.editor.core.dataset.common.XDataStore;
import org.mo.game.editor.face.apl.page.IPublicPage;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectAction;
import org.mo.logic.session.ILogicSessionConsole;
import org.mo.web.core.format.ESyntax;
import org.mo.web.core.format.IFormatConsole;
import org.mo.web.core.format.ISyntaxMaker;
import org.mo.web.protocol.context.IWebContext;

public class FDatasetAction
      extends FAbsXmlObjectAction<IXmlObject>
      implements
         IDatasetAction
{
   private static ILogger _logger = RLogger.find(FDatasetAction.class);

   public final static String PACKAGE_BEGIN_DEFINE = "-------------------- Define Begin --------------------------\n";

   public final static String PACKAGE_END_DEFINE = "-------------------- Define End ----------------------------\n";

   public final static String PAGE_CATALOG = "Catalog";

   public final static String PAGE_SOURCE = "Source";

   public final static String PTY_TYPE = "type";

   @ALink
   protected IDatabaseConsole _databaseConsole;

   @ALink
   protected IDatasetConsole _datasetConsole;

   @ALink
   protected IFormatConsole _formatConsole;

   @ALink
   protected ILogicSessionConsole _sessionConsole;

   @Override
   public String build(IWebContext context,
                       ISqlContext sqlContext,
                       FDatasetPage page){
      // 获得用户选中的表单
      page.appachContext(context);
      String type = context.parameter(PTY_TYPE);
      EDatasetSourceType etype = REnum.parse(EDatasetSourceType.class, type);
      IXmlObject xdataset = getSelectCollection(_datasetConsole, page);
      // 获得内容
      FDatasetBuilderArgs args = new FDatasetBuilderArgs(sqlContext);
      args.setType(etype);
      args.setInstance(xdataset);
      args.setAction(EDatasetBuildAction.Query);
      // 生成代码
      _datasetConsole.build(args);
      // 为代码进行HTML上色
      ISyntaxMaker syntaxMaker = null;
      if(EDatasetSourceType.SqlTable == etype){
         syntaxMaker = _formatConsole.find(ESyntax.PlSql);
      }else{
         syntaxMaker = _formatConsole.find(ESyntax.Java);
      }
      page.setSource(syntaxMaker.format(args.source()));
      return PAGE_SOURCE;
   }

   @Override
   public String buildAll(IWebContext context,
                          ISqlContext sqlContext,
                          FDatasetPage page){
      String type = context.parameter(PTY_TYPE);
      EDatasetSourceType etype = REnum.parse(EDatasetSourceType.class, type);
      FDatasetBuilderArgs args = new FDatasetBuilderArgs(sqlContext);
      args.setType(etype);
      // 循环处理所有数据集
      for(IXmlObject xdataset : _datasetConsole.list()){
         // 检查有效性
         String isValid = xdataset.innerGet(XDataStore.PTY_IS_VALID);
         if(!RBoolean.parse(isValid)){
            continue;
         }
         // 检查处理类型
         String datasetType = xdataset.name();
         if((!"DataStore".equals(datasetType)) && (!"DataProperty".equals(datasetType))){
            continue;
         }
         // 检查有效性
         args.setInstance(xdataset);
         if(EDatasetSourceType.SqlTable == etype){
            // 向数据库反映数据表/数据视图
            args.setAction(EDatasetBuildAction.Query);
            _datasetConsole.build(args);
            sqlContext.activeConnection().executeSql(args.source());
         }
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   @Override
   public String buildAllSource(IWebContext context,
                                ISqlContext sqlContext,
                                FDatasetPage page){
      FDatasetBuilderArgs args = new FDatasetBuilderArgs(sqlContext);
      String type = context.parameter("type");
      EDatasetSourceType etype = REnum.parse(EDatasetSourceType.class, type);
      String path = _datasetConsole.sourceServerPath();
      // 生成有效的节点列表
      FXmlNodes datasets = new FXmlNodes();
      for(IXmlObject xdataset : _datasetConsole.list()){
         if(!RBoolean.parse(xdataset.innerGet(XDataStore.PTY_IS_VALID))){
            continue;
         }
         if(null != xdataset && XDataStore.NAME.equalsIgnoreCase(xdataset.name())){
            FXmlNode node = _datasetConsole.buildConfig(xdataset);
            datasets.push(node);
         }
      }
      datasets.sortByAttribute(XDataStore.PTY_NAME);
      // 生成代码
      FAttributes sourceCodes = new FAttributes();
      FDictionary<FString> sources = new FDictionary<FString>(FString.class);
      FDictionary<FXmlNode> sourceNodes = new FDictionary<FXmlNode>(FXmlNode.class);
      for(FXmlNode dataset : datasets){
         String dataGroup = RString.nvl(dataset.get("data_group", null), "Gsr");
         String factoryName = RString.nvl(dataset.get("data_factory", null), "Game.MoGameStorage");
         String filePath = path + factoryName.replace(".", "\\");
         IXmlObject xdataset = _datasetConsole.find(dataset.get(XDataStore.PTY_NAME, null));
         args.setInstance(xdataset);
         // 创建声明代码
         args.setSource(null);
         if((EDatasetSourceType.CppAll == etype) || (EDatasetSourceType.CppHead == etype)){
            args.setType(EDatasetSourceType.CppHead);
            _datasetConsole.build(args);
            FString defineSource = args.source();
            if(null != defineSource && !defineSource.isEmpty()){
               // 设置代码
               sourceCodes.set(factoryName, dataGroup);
               // 追加代码
               FString source = sources.get(factoryName, null);
               if(null == source){
                  source = new FString();
                  sources.set(factoryName, source);
               }
               source.appendLine(defineSource);
               // 追加节点
               FXmlNode sourceNode = sourceNodes.get(factoryName, null);
               if(null == sourceNode){
                  sourceNode = new FXmlNode();
                  sourceNodes.set(factoryName, sourceNode);
               }
               sourceNode.push(args.config());
            }
         }
         // 创建实现代码
         if((EDatasetSourceType.CppAll == etype) || (EDatasetSourceType.CppBody == etype)){
            args.setType(EDatasetSourceType.CppBody);
            _datasetConsole.build(args);
            FString implementSource = args.source();
            if(null != implementSource && !implementSource.isEmpty()){
               String fileName = "";
               String[] name = RString.split(RString.toLower(dataset.get(XDataStore.PTY_DATA_NAME, null)), "_");
               for(String childName : name){
                  fileName = fileName + RString.firstUpper(childName);
               }
               if(RString.isNotEmpty(fileName)){
                  fileName = fileName + ".cpp";
                  String filename = RFile.format(filePath + "/TDs" + fileName);
                  RFile.saveToFile(filename, implementSource, REncoding.GBK);
                  _logger.info(this, "buildAllSource", "Build cpp body source. (file={1})", fileName);
               }
            }
         }
         // 创建Java代码
         if(EDatasetSourceType.JavaAll == etype){
            args.setType(EDatasetSourceType.JavaAll);
            _datasetConsole.build(args);
         }
      }
      // 创建CPP定义代码
      if((EDatasetSourceType.CppAll == etype) || (EDatasetSourceType.CppDefine == etype)){
         for(INamePair<FString> pair : sources){
            if(!pair.value().isEmpty()){
               String dataGroup = RString.nvl(sourceCodes.get(pair.name(), null), "Gsr");
               String factoryName = RString.nvl(pair.name(), "Game.MoGameStorage");
               String filePath = path + factoryName.replace(".", "\\");
               args.setDataGroup(dataGroup);
               args.setType(EDatasetSourceType.CppDefine);
               args.setSource(pair.value());
               args.setConfig(sourceNodes.get(pair.name(), null));
               _datasetConsole.build(args);
               FString source = args.source();
               String fileName = RFile.format(filePath + "/" + "Mo" + dataGroup + "Dataset.h");
               source.appendLine();
               RFile.saveToFile(fileName, source, REncoding.GBK);
               _logger.info(this, "buildAllSource", "Build cpp head source. (file={1})", fileName);
            }
         }
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   protected void buildDataStoreSource(IWebContext context,
                                       ISqlContext sqlContext,
                                       IXmlObject xdataset,
                                       FDatasetPage page){
      // 获得内容
      String datasetType = xdataset.name();
      if(("DataStore".equals(datasetType)) && (RBoolean.parse(xdataset.innerGet("is_valid")))){
         FDatasetBuilderArgs args = new FDatasetBuilderArgs(sqlContext);
         args.setInstance(xdataset);
         // 检查数据表是否存在
         if(RBoolean.parse(page.getBuildTableDel())){
            args.setAction(EDatasetBuildAction.Query);
            args.setType(EDatasetSourceType.SqlTableDelete);
            _datasetConsole.build(args);
            sqlContext.activeConnection().executeSql(args.source());
         }
         // 建立数据表
         if(RBoolean.parse(page.getBuildTable())){
            args.setAction(EDatasetBuildAction.Store);
            args.setType(EDatasetSourceType.SqlTable);
            _datasetConsole.build(args);
            sqlContext.activeConnection().executeSqls(args.source());
         }
      }
   }

   @Override
   public String buildSource(IWebContext context,
                             ISqlContext sqlContext,
                             FDatasetPage page){
      String type = context.parameter("type");
      String pageType = context.parameter("page");
      if("execute".equals(type)){
         // 获得用户选中的表单
         page.appachContext(context);
         IXmlObject xdataset = getSelectCollection(_datasetConsole, page);
         // 获得对象类型
         String datasetType = xdataset.name();
         // 判断是数据存储类型
         if("DataStore".equals(datasetType)){
            buildDataStoreSource(context, sqlContext, xdataset, page);
            // 判断是数据工作类型
         }
         return IPublicPage.PROCESS_SUCCESS;
      }else if("NotHistory".equals(pageType)){
         // 实体表的初始值
         page.setBuildTable(RBoolean.TRUE_STR);
         page.setBuildTableDel(RBoolean.FALSE_STR);
         page.setBuildView(RBoolean.TRUE_STR);
         page.setBuildSequence(RBoolean.TRUE_STR);
         page.setBuildSequenceDel(RBoolean.FALSE_STR);
         page.setBuildPackageHead(RBoolean.TRUE_STR);
         page.setBuildPackageBody(RBoolean.TRUE_STR);
         page.setBuildStore(RBoolean.FALSE_STR);
         // 历史表的初始值
         page.setBuildHsRestore(RBoolean.FALSE_STR);
         page.setBuildHsTable(RBoolean.FALSE_STR);
         page.setBuildHsTableDel(RBoolean.FALSE_STR);
         page.setBuildHsView(RBoolean.FALSE_STR);
         page.setBuildHsSequence(RBoolean.FALSE_STR);
         page.setBuildHsSequenceDel(RBoolean.FALSE_STR);
         page.setBuildHsPackageHead(RBoolean.FALSE_STR);
         page.setBuildHsPackageBody(RBoolean.FALSE_STR);
         page.setBuildHsStore(RBoolean.FALSE_STR);
         page.setBuildHsRestore(RBoolean.FALSE_STR);
         return "SourceBuildNotHistory";
      }else{
         // 实体表的初始值
         page.setBuildTable(RBoolean.TRUE_STR);
         page.setBuildTableDel(RBoolean.FALSE_STR);
         page.setBuildView(RBoolean.TRUE_STR);
         page.setBuildSequence(RBoolean.TRUE_STR);
         page.setBuildSequenceDel(RBoolean.FALSE_STR);
         page.setBuildPackageHead(RBoolean.TRUE_STR);
         page.setBuildPackageBody(RBoolean.TRUE_STR);
         page.setBuildStore(RBoolean.FALSE_STR);
         // 历史表的初始值
         page.setBuildHsRestore(RBoolean.FALSE_STR);
         page.setBuildHsTable(RBoolean.TRUE_STR);
         page.setBuildHsTableDel(RBoolean.FALSE_STR);
         page.setBuildHsView(RBoolean.TRUE_STR);
         page.setBuildHsSequence(RBoolean.TRUE_STR);
         page.setBuildHsSequenceDel(RBoolean.FALSE_STR);
         page.setBuildHsPackageHead(RBoolean.TRUE_STR);
         page.setBuildHsPackageBody(RBoolean.TRUE_STR);
         page.setBuildHsStore(RBoolean.FALSE_STR);
         page.setBuildHsRestore(RBoolean.FALSE_STR);
      }
      return "SourceBuild";
   }

   @Override
   public String buildSystemSource(IWebContext context,
                                   ISqlContext sqlContext,
                                   FDatasetPage page){
      //      String source = _resource.findString("package.name");
      //      source = RString.parse(source, "package_type", "PACKAGE");
      //      source = RString.parse(source, "package_prefix", "CP_%");
      //      FSqlQuery query = new FSqlQuery(sqlContext, source);
      //      /// 取得资源记录集
      //      FDataset dataset = query.fetchDataset();
      //      for(FRow row : dataset){
      //         /// 获取包名
      //         String packageName = row.get("PACKAGE_NAME");
      //         String headSql = _resource.findString("package.text");
      //         headSql = RString.parse(headSql, "package_type", "PACKAGE");
      //         headSql = RString.parse(headSql, "package_name", packageName);
      //         // 类型包头
      //         FSqlQuery headSource = new FSqlQuery(sqlContext, headSql);
      //         FDataset headTextDataset = headSource.fetchDataset();
      //         FString headLines = new FString();
      //         for(FRow line : headTextDataset){
      //            if(line.get("TEXT").toString().startsWith("PACKAGE")){
      //               headLines.append("CREATE OR REPLACE ", line.get("TEXT"));
      //            }else{
      //               headLines.append(line.get("TEXT"));
      //            }
      //         }
      //         _datasetConsole.build(packageName, headLines, EDatasetSourceType.SystemHead);
      //         // 类型包体
      //         FString bodyLines = new FString();
      //         String bodySql = _resource.findString("package.text");
      //         bodySql = RString.parse(bodySql, "package_type", "PACKAGE BODY");
      //         bodySql = RString.parse(bodySql, "package_name", packageName);
      //         FSqlQuery bodySource = new FSqlQuery(sqlContext, bodySql);
      //         FDataset bodyTextDataset = bodySource.fetchDataset();
      //         for(FRow line : bodyTextDataset){
      //            if(line.get("TEXT").toString().startsWith("PACKAGE")){
      //               bodyLines.append("CREATE OR REPLACE ", line.get("TEXT"));
      //            }else{
      //               bodyLines.append(line.get("TEXT"));
      //            }
      //         }
      //         _datasetConsole.build(packageName, bodyLines, EDatasetSourceType.SystemBody);
      //         headLines.clear();
      //         bodyLines.clear();
      //      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   @Override
   public String buildTypeSource(IWebContext context,
                                 ISqlContext sqlContext,
                                 FDatasetPage page){
      //    String type = context.parameter(PTY_TYPE);
      //      EDatasetSourceType etype = REnum.parse(EDatasetSourceType.class, type);
      //      ISqlConnection sqlCon = null;
      //      try{
      //         sqlCon = _databaseConsole.alloc();
      //      String source = _resource.findString("package.name");
      //      source = RString.parse(source, "package_type", "TYPE");
      //      source = RString.parse(source, "package_prefix", "TP_%");
      //      FSqlQuery query = new FSqlQuery(sqlContext, source);
      //      /// 取得资源记录集
      //      FDataset dataset = query.fetchDataset();
      //      for(FRow row : dataset){
      //         /// 获取包名
      //         String packageName = row.get("PACKAGE_NAME");
      //         String headSql = _resource.findString("package.text");
      //         headSql = RString.parse(headSql, "package_type", "TYPE");
      //         headSql = RString.parse(headSql, "package_name", packageName);
      //         // 类型包头
      //         FSqlQuery headSource = new FSqlQuery(sqlContext, headSql);
      //         FDataset headTextDataset = headSource.fetchDataset();
      //         FString headLines = new FString();
      //         for(FRow line : headTextDataset){
      //            if(line.get("TEXT").toString().startsWith("TYPE")){
      //               headLines.append("CREATE OR REPLACE ", line.get("TEXT"));
      //            }else{
      //               headLines.append(line.get("TEXT"));
      //            }
      //         }
      //         _datasetConsole.build(packageName, headLines, EDatasetSourceType.TypeHead);
      //         // 类型包体
      //         FString bodyLines = new FString();
      //         String bodySql = _resource.findString("package.text");
      //         bodySql = RString.parse(bodySql, "package_type", "TYPE BODY");
      //         bodySql = RString.parse(bodySql, "package_name", packageName);
      //         FSqlQuery bodySource = new FSqlQuery(sqlContext, bodySql);
      //         FDataset bodyTextDataset = bodySource.fetchDataset();
      //         if(!bodyTextDataset.isEmpty()){
      //            for(FRow line : bodyTextDataset){
      //               if(line.get("TEXT").toString().startsWith("TYPE")){
      //                  bodyLines.append("CREATE OR REPLACE ", line.get("TEXT"));
      //               }else{
      //                  bodyLines.append(line.get("TEXT"));
      //               }
      //
      //            }
      //            _datasetConsole.build(packageName, bodyLines, EDatasetSourceType.TypeBody);
      //         }
      //         headLines.clear();
      //         bodyLines.clear();
      //      }
      //      }finally{
      //         if(null != sqlContext){
      //            _databaseConsole.free(sqlContext);
      //         }
      //      }
      //

      return IPublicPage.PROCESS_SUCCESS;
   }

   @Override
   public String buildViewSource(IWebContext context,
                                 FDatasetPage page){
      // 获得上传的数据
      page.appachContext(context);
      // 查找选中的XML集合对象和XML对象
      String collection = page.getSelectCollection();
      IXmlObject xcollection = _datasetConsole.get(collection);
      page.setCollection(xcollection);
      if(null == xcollection){
         throw new FFatalError("Xml view is not found. (collection={1}, component={2})", collection);
      }
      // 返回数据
      page.setViewSql(xcollection.innerText());
      return "ViewSql";
   }

   @Override
   public String catalog(IWebContext context,
                         FDatasetPage page){
      return catalog(_datasetConsole, context, page, PAGE_CATALOG);
   }

   @Override
   public String dataDownload(IWebContext context,
                              FDatasetPage page){
      return null;
   }

   @Override
   public String dataRestore(IWebContext context,
                             FDatasetPage page){
      String type = context.parameter("type");
      if("all".equals(type)){
         // 恢复全部数据集
         for(IXmlObject xdataset : _datasetConsole.list()){
            if(XDataStore.isInstance(xdataset)){
               if(RBoolean.parse(xdataset.innerGet(XDataStore.PTY_IS_VALID))){
                  _datasetConsole.dataRestore(xdataset);
               }
            }
         }
      }else{
         // 获得用户选中的表单
         page.appachContext(context);
         IXmlObject xdataset = getSelectCollection(_datasetConsole, page);
         // 恢复当前数据集
         if(RBoolean.parse(xdataset.innerGet(XDataStore.PTY_IS_VALID))){
            _datasetConsole.dataRestore(xdataset);
         }
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   @Override
   public String dataRestoreAll(IWebContext context,
                                FDatasetPage page){
      String type = context.parameter("type");
      if("all".equals(type)){
         // 恢复全部数据集
         for(IXmlObject xdataset : _datasetConsole.list()){
            if(RBoolean.parse(xdataset.innerGet(XDataStore.PTY_IS_VALID))){
               _datasetConsole.dataRestoreAll(xdataset);
            }
         }
      }else{
         // 获得用户选中的表单
         page.appachContext(context);
         IXmlObject xdataset = getSelectCollection(_datasetConsole, page);
         // 恢复当前数据集
         if(RBoolean.parse(xdataset.innerGet(XDataStore.PTY_IS_VALID))){
            _datasetConsole.dataRestoreAll(xdataset);
         }
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   @Override
   public String dataStore(IWebContext context,
                           FDatasetPage page){
      String type = context.parameter("type");
      if("all".equals(type)){
         // 存储全部数据集
         for(IXmlObject xdataset : _datasetConsole.list()){
            if(XDataStore.isInstance(xdataset)){
               if(RBoolean.parse(xdataset.innerGet(XDataStore.PTY_IS_VALID))){
                  _datasetConsole.dataStore(xdataset);
               }
            }
         }
      }else{
         // 获得用户选中的表单
         page.appachContext(context);
         IXmlObject xdataset = getSelectCollection(_datasetConsole, page);
         // 存储当前数据集
         if(RBoolean.parse(xdataset.innerGet(XDataStore.PTY_IS_VALID))){
            _datasetConsole.dataStore(xdataset);
         }
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   @Override
   public String dataUpload(IWebContext context,
                            FDatasetPage page){
      return null;
   }

   @Override
   public String delete(IWebContext context,
                        FDatasetPage page){
      return delete(_datasetConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String execute(IWebContext context,
                         ISqlContext sqlContext,
                         FDatasetPage page){
      // 获得用户选中的表单
      page.appachContext(context);
      String type = context.parameter(PTY_TYPE);
      EDatasetSourceType etype = REnum.parse(EDatasetSourceType.class, type);
      IXmlObject xdataset = getSelectCollection(_datasetConsole, page);
      // 检查数据集的有效性
      String isValid = xdataset.innerGet(XDataStore.PTY_IS_VALID);
      if(!RBoolean.parse(isValid)){
         throw new FFatalError("Dataset is invalid. (dataset={1})", xdataset.innerGet(XDataStore.PTY_NAME));
      }
      // 执行数据内容
      FDatasetBuilderArgs args = new FDatasetBuilderArgs(sqlContext);
      args.setInstance(xdataset);
      args.setType(etype);
      if(EDatasetSourceType.SqlTable == etype){
         // 向数据库反映数据内容
         args.setAction(EDatasetBuildAction.Query);
         _datasetConsole.build(args);
         sqlContext.activeConnection().executeSqls(args.source());
         args.setAction(EDatasetBuildAction.Store);
         _datasetConsole.build(args);
      }else{
         // 将内容存储到硬盘里
         args.setAction(EDatasetBuildAction.Store);
         _datasetConsole.build(args);
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   @Override
   public String help(IWebContext context,
                      FDatasetPage page){
      // 获得上传的数据
      page.appachContext(context);
      // 判断操作类型
      ITemplateParser parser = null;
      String type = page.getSelectType();
      if(TYPE_COLLECTION.equals(type)){
         parser = _templateConsole.findParser("helper.database.dataset.dataset");
      }else if(TYPE_COMPONENT.equals(type)){
         parser = _templateConsole.findParser("helper.database.dataset.field");
      }else{
         throw new FFatalError("Unknown select type. (type={1})", type);
      }
      parser.define("path", "/eUIS/hlp");
      return help(_datasetConsole, context, page, parser, IPublicPage.XOBJECT_HELP);
   }

   @Override
   public String helpBuild(IWebContext context,
                           FDatasetPage page){
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public String insert(IWebContext context,
                        FDatasetPage page){
      return insert(_datasetConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String list(IWebContext context,
                      FDatasetPage page){
      return list(_datasetConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String sort(IWebContext context,
                      FDatasetPage page){
      return sort(_datasetConsole, context, page, IPublicPage.XOBJECT_SORT);
   }

   @Override
   public String update(IWebContext context,
                        FDatasetPage page){
      return update(_datasetConsole, context, page, IPublicPage.XOBJECT_FORM);
   }
}
