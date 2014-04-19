package org.mo.jfa.face.database.dataset;

import org.mo.com.collections.FDataset;
import org.mo.com.collections.FRow;
import org.mo.com.data.ESqlDataDirection;
import org.mo.com.data.ESqlDataType;
import org.mo.com.data.FSqlProcedure;
import org.mo.com.io.RFile;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FMultiString;
import org.mo.com.lang.FString;
import org.mo.com.lang.IAttributes;
import org.mo.com.lang.IStringPair;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.REnum;
import org.mo.com.lang.RString;
import org.mo.com.lang.cultrue.REncoding;
import org.mo.com.resource.IResource;
import org.mo.com.resource.RResource;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.data.dataset.EDatasetBuildAction;
import org.mo.data.dataset.EDatasetSourceType;
import org.mo.data.dataset.FDatasetBuilderArgs;
import org.mo.data.dataset.IDatasetConsole;
import org.mo.data.dataset.common.XDataStore;
import org.mo.eng.data.IDatabaseConsole;
import org.mo.eng.data.common.FSqlPackageParser;
import org.mo.eng.data.common.FSqlQuery;
import org.mo.eng.data.common.ISqlContext;
import org.mo.eng.template.ITemplateParser;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectAction;
import org.mo.jfa.face.apl.page.IPublicPage;
import org.mo.logic.data.ISyRecordTypeDi;
import org.mo.logic.data.ISyTranslateDi;
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

   private static IResource _resource = RResource.find(FDatasetAction.class);

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

   protected void allBuildDataStoreSource(IWebContext context,
                                          ISqlContext sqlContext,
                                          FDatasetPage page){
      // 获得用户选中的表单
      for(IXmlObject xdataset : _datasetConsole.list()){
         // 获得用户选中的表单
         page.appachContext(context);
         // 获得对象类型
         String datasetType = xdataset.name();
         // 判断是数据存储类型
         if("DataStore".equals(datasetType)){
            buildDataStoreSource(context, sqlContext, xdataset, page);
            // 判断是数据工作类型
         }else if("DataWork".equals(datasetType)){
            buildDataWorkSource(context, sqlContext, xdataset, page);

            // 判断是数据缓冲类型
         }else if("DataCache".equals(datasetType)){
            buildDataCacheSource(context, sqlContext, xdataset, page);
            // 数据视图
         }else if("DataView".equals(datasetType)){
            buildDataViewSource(context, sqlContext, xdataset, page);
            // 属性视图
         }
      }
      // 获得用户选中的表单
      for(IXmlObject xdataset : _datasetConsole.list()){
         // 获得对象类型
         String datasetType = xdataset.name();
         if("DataProperty".equals(datasetType)){
            // 判断是数据属性类型
            buildDataPropertySource(context, sqlContext, xdataset, page);
         }
      }
   }

   @Override
   public String allBuildSource(IWebContext context,
                                ISqlContext sqlContext,
                                FDatasetPage page){
      String type = context.parameter("type");
      if("execute".equals(type)){
         // 执行所有的数据集
         allBuildDataStoreSource(context, sqlContext, page);
         return IPublicPage.PROCESS_SUCCESS;
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
      return "AllSourceBuild";
   }

   @Override
   public String allCheck(IWebContext context,
                          ISqlContext sqlContex,
                          FDatasetPage page){
      for(IXmlObject xds : _datasetConsole.list()){
         if(RBoolean.parse(xds.innerGet("is_valid"))){
            _datasetConsole.buildConfig(xds);
         }
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   @Override
   public String allSyncFKey(IWebContext context,
                             ISqlContext sqlContext,
                             FDatasetPage page){
      IResource resource = RResource.find(FDatasetAction.class);
      /// 获得数据集的所有节点
      for(IXmlObject xdataset : _datasetConsole.list()){
         // 如果该数据集是有效的则继续
         if((RBoolean.parse(xdataset.innerGet("is_valid"))) && (("DataStore".equals(xdataset.name())) || ("DataWork".equals(xdataset.name()))) || ("DataCache".equals(xdataset.name()))){
            // 获得数据集名称
            String datasetName = xdataset.innerGet("name");
            // 根据名称建立数据集Xml节点集
            FXmlNode config = _datasetConsole.buildConfig(datasetName, EXmlConfig.Simple);
            FXmlNode node = config.findNode("Fields");
            // 如果该数据集有别称则取别称，如果没有则取数据名称
            String tableName = RString.nvl(config.get("data_alias"), config.get("data_name"));
            String dataName = config.get("data_name");
            // 读取配置文件中的sql文
            String dataReferIg = resource.findString("dataRefer.judge");
            String dataReferIn = "";
            String dataReferDelete = "";
            dataReferIn = resource.findString("dataRefer.insert");
            dataReferDelete = resource.findString("dataRefer.delete");
            // 循环获得该数据集的字段
            FString inLine = new FString();
            FString delLine = new FString();
            boolean isFK = false;
            for(FXmlNode field : node.nodes()){
               if(RBoolean.parse(field.get("is_valid")) && field.isName("Field")){
                  if("INTEGER".equals(field.get("data_type"))){
                     // 如果数据引用不为空的话继续
                     if(null != field.get("data_refer")){
                        // 如果该字段有别称则获取该字段的别称，否则获取数据名称
                        String fkFieldName = RString.nvl(field.get("data_alias"), field.get("data_name"));
                        String fkName = tableName + "_FK_" + RString.left(fkFieldName, "_ID");
                        // 把读来的sql文中的constraint替换成对应的外键名称
                        String sDataReferIg = RString.parse(dataReferIg, "constraint", fkName);
                        String sFKIsOpen = RString.parse(dataReferIg, "constraint", fkName);
                        // 执行SQL文返回执行结果
                        String judge = sqlContext.activeConnection().executeScalar(new FString(sDataReferIg));
                        // 读取配置文件中的新建外键SQL文
                        String subValue = dataName + "_DS";
                        String sdataReferIn = RString.parse(dataReferIn, "table_name", subValue);
                        sdataReferIn = RString.parse(sdataReferIn, "fk_name", fkName);
                        sdataReferIn = RString.parse(sdataReferIn, "field_name", field.get("data_name"));
                        // 创建配置文件中的删除外键SQL文
                        String sdataReferDelete = RString.parse(dataReferDelete, "table_name", subValue);
                        sdataReferDelete = RString.parse(sdataReferDelete, "fk_name", fkName);
                        // 生成XML
                        FXmlNode referNode = _datasetConsole.buildConfig(field.get("data_refer"));
                        sdataReferIn = RString.parse(sdataReferIn, "refer_name", referNode.get("data_name") + "_DS");
                        inLine.appendLine(sdataReferIn + ";");
                        delLine.appendLine(sdataReferDelete + ";");
                        isFK = true;
                        String isOpen = sqlContext.activeConnection().executeScalar(new FString(sFKIsOpen));
                        // 判断该外键是否已添加，如果添加则循环下一个字段，否则新建外键
                        if("1".equals(judge)){
                           if(!RBoolean.parse(isOpen)){
                              FSqlProcedure produrce = new FSqlProcedure("Table_Foreign_Key_Enable");
                              produrce.setLogicName("CP_SQL");
                              produrce.createParameter("table_name_", tableName + "_DS", ESqlDataType.String, ESqlDataDirection.In);
                              sqlContext.activeConnection().execute(produrce);
                           }
                           continue;
                        }
                        // 执行SQL文
                        sqlContext.activeConnection().executeSql(new FString(sdataReferIn));

                     }
                  }
               }
            }
            // 建立外键的代码
            if(isFK){
               inLine.setLength(inLine.length() - 1);
               String installFileName = RFile.format(_datasetConsole.getLogicUnit() + datasetName + "/install_foreign.sql");
               RFile.saveToFile(installFileName, inLine, REncoding.UTF8);
               inLine.clear();
               delLine.setLength(delLine.length() - 1);
               String uninstallFileName = RFile.format(_datasetConsole.getLogicUnit() + datasetName + "/uninstall_foreign.sql");
               RFile.saveToFile(uninstallFileName, delLine, REncoding.UTF8);
               delLine.clear();
            }
         }
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   @Override
   public String allSyncTranslate(IWebContext context,
                                  ISyTranslateDi syTranslateDi,
                                  FDatasetPage page){
      /// 获得所有节点，并同步所有节点
      for(IXmlObject xdata : _datasetConsole.list()){
         if(RBoolean.parse(xdata.innerGet("is_valid"))){
            FXmlNode xdataset = _datasetConsole.buildConfig(xdata.innerGet("name"), EXmlConfig.Full);
            IAttributes attributes = xdataset.attributes();
            String datasetName = xdataset.get("name");
            attributes.set("group_name", "database.dataset");
            attributes.set("type_cd", "D");
            attributes.set("code", datasetName);
            // 同步翻译列表
            syTranslateDi.doSyncList(_sessionConsole.makeLogic(), attributes);
            syncTranslateData(xdataset, syTranslateDi, "collection", xdataset.get("name"), datasetName);
            for(FXmlNode xfield : xdataset.allNodes()){
               if(null == xfield.get("name") || !RBoolean.parse(xfield.get("is_valid"))){
                  continue;
               }
               // 循环获得子节点同步子节点
               String fieldName = xdataset.get("name") + "|" + xfield.get("name");
               syncTranslateData(xfield, syTranslateDi, "component", fieldName, datasetName);
            }
         }
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   @Override
   public String allSyncType(IWebContext context,
                             ISyRecordTypeDi syRecordType,
                             FDatasetPage page){
      // 列出所有的数据集
      int count = 0;
      for(IXmlObject xds : _datasetConsole.list()){
         if(RBoolean.parse(xds.innerGet("is_valid"))){
            count++;
            IAttributes attributes = xds.toSimpleAttributes();
            attributes.set("disp_order", String.valueOf(count));
            syRecordType.doSyncType(_sessionConsole.makeLogic(), attributes);
         }
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

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
      if(EDatasetSourceType.All == etype || EDatasetSourceType.AllProperty == etype){
         args.setAction(EDatasetBuildAction.Store);
         _datasetConsole.build(args);
         page.setSource(null);
         return IPublicPage.PROCESS_SUCCESS;
      }else{
         args.setAction(EDatasetBuildAction.Query);
         // 获取Package内用户定义的内容
         if(EDatasetSourceType.PackageHead == etype || EDatasetSourceType.PackagePropertyHead == etype || EDatasetSourceType.PackageHeadWork == etype || EDatasetSourceType.PackageHeadCache == etype){
            String dataLogic = xdataset.innerGet(XDataStore.PTY_DATA_LOGIC);
            FSqlPackageParser parser = new FSqlPackageParser(sqlContext, dataLogic);
            FString define = parser.fetchUserHeadDefine();
            args.attributes().set(FSqlPackageParser.USER_DEFINE, define);
         }else if(EDatasetSourceType.PackageBody == etype || EDatasetSourceType.PackagePropertyBody == etype || EDatasetSourceType.PackageBodyWork == etype || EDatasetSourceType.PackageBodyCache == etype){
            String dataLogic = xdataset.innerGet(XDataStore.PTY_DATA_LOGIC);
            FSqlPackageParser parser = new FSqlPackageParser(sqlContext, dataLogic);
            FString define = parser.fetchUserBodyDefine();
            args.attributes().set(FSqlPackageParser.USER_DEFINE, define);
         }
         // 生成代码
         _datasetConsole.build(args);
         // 为代码进行HTML上色
         ISyntaxMaker syntaxMaker = null;
         if(EDatasetSourceType.SqlTable == etype || EDatasetSourceType.SqlView == etype || EDatasetSourceType.SqlSequence == etype || EDatasetSourceType.HisSqlTable == etype || EDatasetSourceType.HisSqlView == etype
               || EDatasetSourceType.HisSqlSequence == etype || EDatasetSourceType.SqlViewProperty == etype || EDatasetSourceType.SqlTableWork == etype || EDatasetSourceType.SqlViewWork == etype || EDatasetSourceType.SqlSequenceWork == etype
               || EDatasetSourceType.SqlTableCache == etype || EDatasetSourceType.SqlViewCache == etype || EDatasetSourceType.SqlSequenceCache == etype || EDatasetSourceType.SqlViewView == etype){
            syntaxMaker = _formatConsole.find(ESyntax.Sql);
         }else if(EDatasetSourceType.PackageHead == etype || EDatasetSourceType.PackageBody == etype || EDatasetSourceType.HisPackageHead == etype || EDatasetSourceType.HisPackageBody == etype || EDatasetSourceType.PackagePropertyHead == etype
               || EDatasetSourceType.PackagePropertyBody == etype || EDatasetSourceType.PackageHeadWork == etype || EDatasetSourceType.PackageBodyWork == etype || EDatasetSourceType.PackageHeadCache == etype || EDatasetSourceType.PackageBodyCache == etype){
            syntaxMaker = _formatConsole.find(ESyntax.PlSql);
         }else{
            syntaxMaker = _formatConsole.find(ESyntax.Java);
         }
         page.setSource(syntaxMaker.format(args.source()));
      }
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
         if(EDatasetSourceType.SqlTable == etype || EDatasetSourceType.SqlView == etype){
            // 向数据库反映数据表/数据视图
            args.setAction(EDatasetBuildAction.Query);
            _datasetConsole.build(args);
            sqlContext.activeConnection().executeSql(args.source());
         }else if(EDatasetSourceType.PackageHead == etype || EDatasetSourceType.PackageBody == etype){
            // 判断是否是属性视图
            if("DataProperty".equals(datasetType)){
               if(EDatasetSourceType.PackageBody == etype){
                  makePackage(sqlContext, xdataset, EDatasetSourceType.PackagePropertyBody);
               }else{
                  makePackage(sqlContext, xdataset, EDatasetSourceType.PackagePropertyHead);
               }
            }else{
               makePackage(sqlContext, xdataset, etype);
            }
         }
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   @Override
   public String buildAllProperty(IWebContext context,
                                  ISqlContext sqlContext,
                                  FDatasetPage page){
      String type = context.parameter(PTY_TYPE);
      EDatasetSourceType etype = REnum.parse(EDatasetSourceType.class, type);
      IXmlObject xdataset = getSelectCollection(_datasetConsole, page);
      // 获得内容
      // 查看是否属性视图创建视图，包头，包体
      if(EDatasetSourceType.AllProperty == etype){
         FDatasetBuilderArgs args = new FDatasetBuilderArgs(sqlContext);
         args.setInstance(xdataset);
         // 创建属性视图的视图
         args.setAction(EDatasetBuildAction.Query);
         args.setType(EDatasetSourceType.SqlViewProperty);
         _datasetConsole.build(args);
         sqlContext.activeConnection().executeSql(args.source());
         // 创建属性视图的包头
         //args.setSource(null);
         makePackage(sqlContext, xdataset, EDatasetSourceType.PackagePropertyHead);
         // 创建属性视图的包体
         //args.setSource(null);
         makePackage(sqlContext, xdataset, EDatasetSourceType.PackagePropertyBody);
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   protected void buildDataCacheSource(IWebContext context,
                                       ISqlContext sqlContext,
                                       IXmlObject xdataset,
                                       FDatasetPage page){
      // 获得内容
      String datasetType = xdataset.name();
      if(("DataCache".equals(datasetType)) && (RBoolean.parse(xdataset.innerGet("is_valid")))){
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
            args.setType(EDatasetSourceType.SqlTableCache);
            _datasetConsole.build(args);
            sqlContext.activeConnection().executeSqls(args.source());
         }
         // 检查序列是否存在
         if(RBoolean.parse(page.getBuildSequenceDel())){
            args.setAction(EDatasetBuildAction.Query);
            args.setType(EDatasetSourceType.SqlSequenceDelete);
            _datasetConsole.build(args);
            sqlContext.activeConnection().executeSql(args.source());
         }
         // 建立序列
         if(RBoolean.parse(page.getBuildSequence())){
            args.setAction(EDatasetBuildAction.Query);
            args.setType(EDatasetSourceType.SqlSequence);
            _datasetConsole.build(args);
            sqlContext.activeConnection().executeSql(args.source());
         }
         // 建立数据视图
         if(RBoolean.parse(page.getBuildView())){
            args.setAction(EDatasetBuildAction.Query);
            args.setType(EDatasetSourceType.SqlView);
            _datasetConsole.build(args);
            sqlContext.activeConnection().executeSql(args.source());
         }
         // 建立Package包头
         if(RBoolean.parse(page.getBuildPackageHead())){
            makePackage(sqlContext, xdataset, EDatasetSourceType.PackageHead);
         }
         // 建立Package包体
         if(RBoolean.parse(page.getBuildPackageBody())){
            makePackage(sqlContext, xdataset, EDatasetSourceType.PackageBody);
         }
      }
   }

   protected void buildDataPropertySource(IWebContext context,
                                          ISqlContext sqlContext,
                                          IXmlObject xdataset,
                                          FDatasetPage page){
      // 获得内容
      String datasetType = xdataset.name();
      if(("DataProperty".equals(datasetType)) && (RBoolean.parse(xdataset.innerGet("is_valid")))){
         FDatasetBuilderArgs args = new FDatasetBuilderArgs(sqlContext);
         args.setInstance(xdataset);
         // 建立数据视图
         if(RBoolean.parse(page.getBuildView())){
            args.setAction(EDatasetBuildAction.Query);
            args.setType(EDatasetSourceType.SqlViewProperty);
            _datasetConsole.build(args);
            sqlContext.activeConnection().executeSql(args.source());
         }
         // 建立Package包头
         if(RBoolean.parse(page.getBuildPackageHead())){
            makePackage(sqlContext, xdataset, EDatasetSourceType.PackagePropertyHead);
         }
         // 建立Package包体
         if(RBoolean.parse(page.getBuildPackageBody())){
            makePackage(sqlContext, xdataset, EDatasetSourceType.PackagePropertyBody);
         }
      }
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
         // 建立数据视图
         if(RBoolean.parse(page.getBuildView())){
            args.setAction(EDatasetBuildAction.Query);
            args.setType(EDatasetSourceType.SqlView);
            _datasetConsole.build(args);
            sqlContext.activeConnection().executeSql(args.source());
         }
         // 检查序列是否存在
         if(RBoolean.parse(page.getBuildSequenceDel())){
            args.setAction(EDatasetBuildAction.Query);
            args.setType(EDatasetSourceType.SqlSequenceDelete);
            _datasetConsole.build(args);
            sqlContext.activeConnection().executeSql(args.source());
         }
         // 建立序列
         if(RBoolean.parse(page.getBuildSequence())){
            args.setAction(EDatasetBuildAction.Query);
            args.setType(EDatasetSourceType.SqlSequence);
            _datasetConsole.build(args);
            sqlContext.activeConnection().executeSql(args.source());
         }
         // 检查数据表是否存在(历史)
         if(RBoolean.parse(page.getBuildHsTableDel())){
            args.setAction(EDatasetBuildAction.Query);
            args.setType(EDatasetSourceType.HisSqlTableDelete);
            _datasetConsole.build(args);
            sqlContext.activeConnection().executeSql(args.source());
         }
         // 建立数据表(历史)
         if(RBoolean.parse(page.getBuildHsTable())){
            args.setAction(EDatasetBuildAction.Store);
            args.setType(EDatasetSourceType.HisSqlTable);
            _datasetConsole.build(args);
            sqlContext.activeConnection().executeSqls(args.source());
         }
         // 建立数据视图(历史)
         if(RBoolean.parse(page.getBuildHsView())){
            args.setAction(EDatasetBuildAction.Query);
            args.setType(EDatasetSourceType.HisSqlView);
            _datasetConsole.build(args);
            sqlContext.activeConnection().executeSql(args.source());
         }
         // 检查序列是否存在(历史)
         if(RBoolean.parse(page.getBuildHsSequenceDel())){
            args.setAction(EDatasetBuildAction.Query);
            args.setType(EDatasetSourceType.HisSqlSequenceDelete);
            _datasetConsole.build(args);
            sqlContext.activeConnection().executeSql(args.source());
         }
         // 建立序列(历史)
         if(RBoolean.parse(page.getBuildHsSequence())){
            args.setAction(EDatasetBuildAction.Query);
            args.setType(EDatasetSourceType.HisSqlSequence);
            _datasetConsole.build(args);
            sqlContext.activeConnection().executeSql(args.source());
         }
         // 建立Package包头
         if(RBoolean.parse(page.getBuildPackageHead())){
            makePackage(sqlContext, xdataset, EDatasetSourceType.PackageHead);
         }
         // 建立Package包头(历史)
         if(RBoolean.parse(page.getBuildHsPackageHead())){
            makePackage(sqlContext, xdataset, EDatasetSourceType.HisPackageHead);
         }
         // 建立Package包体
         if(RBoolean.parse(page.getBuildPackageBody())){
            makePackage(sqlContext, xdataset, EDatasetSourceType.PackageBody);
         }
         // 建立Package包体(历史)
         if(RBoolean.parse(page.getBuildHsPackageBody())){
            makePackage(sqlContext, xdataset, EDatasetSourceType.HisPackageBody);
         }
      }
   }

   protected void buildDataViewSource(IWebContext context,
                                      ISqlContext sqlContext,
                                      IXmlObject xdataset,
                                      FDatasetPage page){
      // 获得内容
      String datasetType = xdataset.name();
      if(("DataView".equals(datasetType)) && (RBoolean.parse(xdataset.innerGet("is_valid")))){
         FDatasetBuilderArgs args = new FDatasetBuilderArgs(sqlContext);
         args.setInstance(xdataset);
         // 建立数据视图
         if(RBoolean.parse(page.getBuildView())){
            args.setAction(EDatasetBuildAction.Query);
            args.setType(EDatasetSourceType.SqlViewView);
            _datasetConsole.build(args);
            sqlContext.activeConnection().executeSql(args.source());
         }
      }
   }

   protected void buildDataWorkSource(IWebContext context,
                                      ISqlContext sqlContext,
                                      IXmlObject xdataset,
                                      FDatasetPage page){
      // 获得内容
      String datasetType = xdataset.name();
      if(("DataWork".equals(datasetType)) && (RBoolean.parse(xdataset.innerGet("is_valid")))){
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
            args.setType(EDatasetSourceType.SqlTableWork);
            _datasetConsole.build(args);
            sqlContext.activeConnection().executeSqls(args.source());
         }
         // 检查序列是否存在
         if(RBoolean.parse(page.getBuildSequenceDel())){
            args.setAction(EDatasetBuildAction.Query);
            args.setType(EDatasetSourceType.SqlSequenceDelete);
            _datasetConsole.build(args);
            sqlContext.activeConnection().executeSql(args.source());
         }
         // 建立序列
         if(RBoolean.parse(page.getBuildSequence())){
            args.setAction(EDatasetBuildAction.Query);
            args.setType(EDatasetSourceType.SqlSequenceWork);
            _datasetConsole.build(args);
            sqlContext.activeConnection().executeSql(args.source());
         }
         // 建立数据视图
         if(RBoolean.parse(page.getBuildView())){
            args.setAction(EDatasetBuildAction.Query);
            args.setType(EDatasetSourceType.SqlViewWork);
            _datasetConsole.build(args);
            sqlContext.activeConnection().executeSql(args.source());
         }
         // 建立Package包头
         if(RBoolean.parse(page.getBuildPackageHead())){
            makePackage(sqlContext, xdataset, EDatasetSourceType.PackageHeadWork);
         }
         // 建立Package包体
         if(RBoolean.parse(page.getBuildPackageBody())){
            makePackage(sqlContext, xdataset, EDatasetSourceType.PackageBodyWork);
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
         }else if("DataWork".equals(datasetType)){
            buildDataWorkSource(context, sqlContext, xdataset, page);

            // 判断是数据缓冲类型
         }else if("DataCache".equals(datasetType)){
            buildDataCacheSource(context, sqlContext, xdataset, page);
            // 判断是数据属性类型
         }else if("DataProperty".equals(datasetType)){
            buildDataPropertySource(context, sqlContext, xdataset, page);
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
      String source = _resource.findString("package.name");
      source = RString.parse(source, "package_type", "PACKAGE");
      source = RString.parse(source, "package_prefix", "CP_%");
      FSqlQuery query = new FSqlQuery(sqlContext, source);
      /// 取得资源记录集
      FDataset dataset = query.fetchDataset();
      for(FRow row : dataset){
         /// 获取包名
         String packageName = row.get("PACKAGE_NAME");
         String headSql = _resource.findString("package.text");
         headSql = RString.parse(headSql, "package_type", "PACKAGE");
         headSql = RString.parse(headSql, "package_name", packageName);
         // 类型包头
         FSqlQuery headSource = new FSqlQuery(sqlContext, headSql);
         FDataset headTextDataset = headSource.fetchDataset();
         FString headLines = new FString();
         for(FRow line : headTextDataset){
            if(line.get("TEXT").toString().startsWith("PACKAGE")){
               headLines.append("CREATE OR REPLACE ", line.get("TEXT"));
            }else{
               headLines.append(line.get("TEXT"));
            }
         }
         _datasetConsole.build(packageName, headLines, EDatasetSourceType.SystemHead);
         // 类型包体
         FString bodyLines = new FString();
         String bodySql = _resource.findString("package.text");
         bodySql = RString.parse(bodySql, "package_type", "PACKAGE BODY");
         bodySql = RString.parse(bodySql, "package_name", packageName);
         FSqlQuery bodySource = new FSqlQuery(sqlContext, bodySql);
         FDataset bodyTextDataset = bodySource.fetchDataset();
         for(FRow line : bodyTextDataset){
            if(line.get("TEXT").toString().startsWith("PACKAGE")){
               bodyLines.append("CREATE OR REPLACE ", line.get("TEXT"));
            }else{
               bodyLines.append(line.get("TEXT"));
            }
         }
         _datasetConsole.build(packageName, bodyLines, EDatasetSourceType.SystemBody);
         headLines.clear();
         bodyLines.clear();
      }
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
      String source = _resource.findString("package.name");
      source = RString.parse(source, "package_type", "TYPE");
      source = RString.parse(source, "package_prefix", "TP_%");
      FSqlQuery query = new FSqlQuery(sqlContext, source);
      /// 取得资源记录集
      FDataset dataset = query.fetchDataset();
      for(FRow row : dataset){
         /// 获取包名
         String packageName = row.get("PACKAGE_NAME");
         String headSql = _resource.findString("package.text");
         headSql = RString.parse(headSql, "package_type", "TYPE");
         headSql = RString.parse(headSql, "package_name", packageName);
         // 类型包头
         FSqlQuery headSource = new FSqlQuery(sqlContext, headSql);
         FDataset headTextDataset = headSource.fetchDataset();
         FString headLines = new FString();
         for(FRow line : headTextDataset){
            if(line.get("TEXT").toString().startsWith("TYPE")){
               headLines.append("CREATE OR REPLACE ", line.get("TEXT"));
            }else{
               headLines.append(line.get("TEXT"));
            }
         }
         _datasetConsole.build(packageName, headLines, EDatasetSourceType.TypeHead);
         // 类型包体
         FString bodyLines = new FString();
         String bodySql = _resource.findString("package.text");
         bodySql = RString.parse(bodySql, "package_type", "TYPE BODY");
         bodySql = RString.parse(bodySql, "package_name", packageName);
         FSqlQuery bodySource = new FSqlQuery(sqlContext, bodySql);
         FDataset bodyTextDataset = bodySource.fetchDataset();
         if(!bodyTextDataset.isEmpty()){
            for(FRow line : bodyTextDataset){
               if(line.get("TEXT").toString().startsWith("TYPE")){
                  bodyLines.append("CREATE OR REPLACE ", line.get("TEXT"));
               }else{
                  bodyLines.append(line.get("TEXT"));
               }

            }
            _datasetConsole.build(packageName, bodyLines, EDatasetSourceType.TypeBody);
         }
         headLines.clear();
         bodyLines.clear();
      }
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
         throw new FFatalError("Xml view is not found. (collection={0},component={1})", collection);
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
         throw new FFatalError("Dataset is invalid. (dataset={0})", xdataset.innerGet(XDataStore.PTY_NAME));
      }
      // 执行数据内容
      FDatasetBuilderArgs args = new FDatasetBuilderArgs(sqlContext);
      args.setInstance(xdataset);
      args.setType(etype);
      if(EDatasetSourceType.SqlTable == etype || EDatasetSourceType.SqlView == etype || EDatasetSourceType.SqlSequence == etype || EDatasetSourceType.HisSqlTable == etype || EDatasetSourceType.HisSqlView == etype
            || EDatasetSourceType.HisSqlSequence == etype || EDatasetSourceType.SqlViewProperty == etype || EDatasetSourceType.SqlTableWork == etype || EDatasetSourceType.SqlViewWork == etype || EDatasetSourceType.SqlSequenceWork == etype
            || EDatasetSourceType.SqlTableCache == etype || EDatasetSourceType.SqlViewCache == etype || EDatasetSourceType.SqlSequenceCache == etype || EDatasetSourceType.SqlViewView == etype){
         // 向数据库反映数据内容
         args.setAction(EDatasetBuildAction.Query);
         _datasetConsole.build(args);
         sqlContext.activeConnection().executeSqls(args.source());
         args.setAction(EDatasetBuildAction.Store);
         _datasetConsole.build(args);
      }else if(EDatasetSourceType.PackageHead == etype || EDatasetSourceType.PackageBody == etype || EDatasetSourceType.HisPackageHead == etype || EDatasetSourceType.HisPackageBody == etype || EDatasetSourceType.PackagePropertyBody == etype
            || EDatasetSourceType.PackagePropertyHead == etype || EDatasetSourceType.PackageHeadWork == etype || EDatasetSourceType.PackageBodyWork == etype || EDatasetSourceType.PackageHeadCache == etype || EDatasetSourceType.PackageBodyCache == etype){
         // 向数据库反映Package和存储
         makePackage(sqlContext, xdataset, etype);
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
         throw new FFatalError("Unknown select type. (type={0})", type);
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

   public void makePackage(ISqlContext sqlContext,
                           IXmlObject xdataset,
                           EDatasetSourceType etype){
      // 获取数据库包的内容
      String dataLogic = xdataset.innerGet(XDataStore.PTY_DATA_LOGIC);
      FSqlPackageParser parser = new FSqlPackageParser(sqlContext, dataLogic);
      FString source = null;
      // 创建参数
      FDatasetBuilderArgs args = new FDatasetBuilderArgs(sqlContext);
      args.setInstance(xdataset);
      args.setType(etype);
      if(EDatasetSourceType.PackageHead == etype || EDatasetSourceType.PackagePropertyHead == etype || EDatasetSourceType.PackageHeadWork == etype || EDatasetSourceType.PackageHeadCache == etype){
         // 获取包内用户自定义的内容
         FString define = parser.fetchUserHeadDefine();
         args.attributes().set(FSqlPackageParser.USER_DEFINE, define);
         // 获得全部内容
         source = new FString("CREATE OR REPLACE " + parser.fetchLines());
         source.trim();
      }else if(EDatasetSourceType.PackageBody == etype || EDatasetSourceType.PackagePropertyBody == etype || EDatasetSourceType.PackageBodyWork == etype || EDatasetSourceType.PackageBodyCache == etype){
         // 获取包内用户自定义的内容
         FString define = parser.fetchUserBodyDefine();
         args.attributes().set(FSqlPackageParser.USER_DEFINE, define);
         // 获得全部内容
         source = new FString("CREATE OR REPLACE " + parser.fetchLines());
         source.trim();
      }
      // 向数据库反映Package内容
      args.setAction(EDatasetBuildAction.Execute);
      _datasetConsole.build(args);
      FString argsSource = args.source();
      argsSource.trim();
      if(!argsSource.equals(source)){
         sqlContext.activeConnection().executeSql(argsSource);
      }
      // 将包内容存储到硬盘里
      args.setAction(EDatasetBuildAction.Store);
      _datasetConsole.build(args);
   }

   @Override
   public String sort(IWebContext context,
                      FDatasetPage page){
      return sort(_datasetConsole, context, page, IPublicPage.XOBJECT_SORT);
   }

   @Override
   public String syncModule(IWebContext context,
                            FDatasetPage page){
      return IPublicPage.PROCESS_SUCCESS;
   }

   @Override
   public String syncTranslate(IWebContext context,
                               ISyTranslateDi syTranslateDi,
                               FDatasetPage page){
      /// 获得当前节点，并同步当前节点
      IXmlObject xdata = getSelectCollection(_datasetConsole, page);
      if(RBoolean.parse(xdata.innerGet("is_valid"))){
         FXmlNode xdataset = _datasetConsole.buildConfig(xdata.innerGet("name"), EXmlConfig.Full);
         IAttributes attributes = xdataset.attributes();
         String datasetName = xdataset.get("name");
         attributes.set("group_name", "database.dataset");
         attributes.set("type_cd", "P");
         attributes.set("code", datasetName);
         // 同步翻译列表
         syTranslateDi.doSyncList(_sessionConsole.makeLogic(), attributes);
         //syncTranslateData(xdataset, syTranslateDi, "collection", xdataset.get("name"), datasetName);
         for(FXmlNode xfield : xdataset.allNodes()){
            if(null == xfield.get("name") || !RBoolean.parse(xfield.get("is_valid"))){
               continue;
            }
            // 循环获得子节点同步子节点
            String fieldName = xdataset.get("name") + "|" + xfield.get("name");
            syncTranslateData(xfield, syTranslateDi, "component", fieldName, datasetName);
         }
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   /**
    * <T>根据参数同步翻译数据</T>
    * 
    * @param xdataset 父节点
    * @param syTranslateDi 系统翻译模块操作接口
    * @param type 系统所有属性模块操作接口
    * @param name 系统所有属性模块操作接口
    * @param datasetName 系统所有属性模块操作接口
    */
   private void syncTranslateData(FXmlNode xdataset,
                                  ISyTranslateDi syTranslateDi,
                                  String type,
                                  String name,
                                  String datasetName){
      IAttributes attributes = xdataset.attributes();
      attributes.set("group_name", "database.dataset");
      attributes.set("list_name", datasetName);
      attributes.set("type_cd", "D");
      attributes.set("status_cd", "A");
      attributes.set("content", xdataset.get("name"));
      String translateCode;
      /// 根据类型去生成不通的code号，如果是collection（数据集）怎生成P|DATASET|+datasetName
      /// 如果是component（字段）则生成P|DATASET.FIELD|datasetName|fieldName
      //      if("collection".equals(type)){
      //         translateCode = "D|DATASET|" + name;
      //      }else{
      //         translateCode = "D|DATASET.FIELD|" + name;
      //      }
      translateCode = "D|" + name;
      attributes.set("code", translateCode);
      /// 同步翻译
      syTranslateDi.doSyncTranslate(_sessionConsole.makeLogic(), attributes);
      /// 根据label的语言代码循环生成翻译对应的内容
      attributes.set("translate_code", translateCode);
      /// 获得XML中的存储的多国语言字符串并解包
      FMultiString content = new FMultiString();
      /// 把label字符串包解包
      content.unpack(xdataset.get("label"));
      for(IStringPair pair : content){
         // name为语言代码编号例如：CN代表中文，value为该语言下的内容
         if("cn".equals(pair.name())){
            attributes.set("language_name", "SC");
         }else{
            attributes.set("language_name", RString.toUpper(pair.name()));
         }
         attributes.set("content", pair.value());
         syTranslateDi.doSyncContent(_sessionConsole.makeLogic(), attributes);
      }

   }

   @Override
   public String update(IWebContext context,
                        FDatasetPage page){
      return update(_datasetConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String viewSourceSave(IWebContext context,
                                FDatasetPage page){
      // 获得上传的数据
      page.appachContext(context);
      // 查找选中的XML集合对象和XML对象
      String collection = page.getSelectCollection();
      IXmlObject xcollection = page.getCollection();
      if(null == xcollection){
         throw new FFatalError("Xml component is not found. (collection={0},component={1})", collection, xcollection);
      }
      // 返回数据
      String viewSql = page.getViewSql();
      xcollection.setInnerText(viewSql);
      _datasetConsole.store(xcollection);
      return IPublicPage.PROCESS_SUCCESS;
   }
}
