package org.mo.jfa.face.database.procedure;

import org.mo.com.collections.FDataset;
import org.mo.com.collections.FRow;
import org.mo.com.lang.REnum;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.RXml;
import org.mo.core.aop.face.ALink;
import org.mo.data.datainfo.IDataInfoConsole;
import org.mo.data.dataset.EDatasetBuildAction;
import org.mo.data.dataset.EDatasetSourceType;
import org.mo.data.dataset.FDatasetBuilderArgs;
import org.mo.eng.data.common.FSqlQuery;
import org.mo.eng.data.common.ISqlContext;
import org.mo.jfa.face.apl.page.IPublicPage;
import org.mo.web.core.format.ESyntax;
import org.mo.web.core.format.IFormatConsole;
import org.mo.web.core.format.ISyntaxMaker;
import org.mo.web.protocol.context.IWebContext;

public class FPackageAction
      extends FAbstractCommon
      implements
         IPackageAction
{

   protected static boolean isInterface = false;

   protected static String packageName = "";

   @ALink
   protected IDataInfoConsole _dataInfoConsole;

   @ALink
   protected IFormatConsole _formatConsole;

   //private static ILogger _logger = RLogger.find(FDatasetConsole.class);

   @Override
   public String build(ISqlContext sqlContext,
                       IWebContext webContext,
                       FProcedurePage page){
      String type = webContext.parameter("type");
      String packageName = webContext.parameter("select_package");
      EDatasetSourceType etype = REnum.parse(EDatasetSourceType.class, type);
      FDatasetBuilderArgs args = new FDatasetBuilderArgs(sqlContext);
      args.setName(packageName);
      args.setType(etype);
      args.setAction(EDatasetBuildAction.Query);
      _datasetConsole.build(args);
      // 为代码进行HTML上色
      ISyntaxMaker syntaxMaker = null;
      if(EDatasetSourceType.JavaPackage == etype || EDatasetSourceType.JavaPackageFace == etype){
         syntaxMaker = _formatConsole.find(ESyntax.Java);
      }
      page.setSource(syntaxMaker.format(args.source()));
      return "Source";
   }

   public String buildAll(ISqlContext sqlContext,
                          IWebContext webContext,
                          FProcedurePage page){
      String type = webContext.parameter("type");
      EDatasetSourceType etype = REnum.parse(EDatasetSourceType.class, type);
      FDatasetBuilderArgs args = new FDatasetBuilderArgs(sqlContext);
      args.setAction(EDatasetBuildAction.Store);
      args.setType(etype);
      // 处理所有包
      FSqlQuery query = new FSqlQuery(sqlContext, FProcedureService.class, "package.list");
      FDataset dataset = query.fetchDataset();
      for(FRow row : dataset){
         String packageName = row.get("package_name");
         if(packageName.endsWith("_DI")){
            args.setName(row.get("package_name"));
            _datasetConsole.build(args);
         }
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   @Override
   public String catalog(IWebContext context,
                         FProcedurePage page){
      return "Catalog";
   }

   public String execute(IWebContext context,
                         ISqlContext sqlContext,
                         FProcedurePage page){
      String type = context.parameter("type");
      String packageName = context.parameter("select_package");
      EDatasetSourceType etype = REnum.parse(EDatasetSourceType.class, type);
      FDatasetBuilderArgs args = new FDatasetBuilderArgs(sqlContext);
      args.setAction(EDatasetBuildAction.Store);
      args.setType(etype);
      args.setName(packageName);
      _datasetConsole.build(args);
      return IPublicPage.PROCESS_SUCCESS;
   }

   @Override
   public String list(IWebContext context,
                      FProcedurePage page){
      // 获得上传的数据
      page.appachContext(context);
      // 获得列表数据
      FXmlNode config = new FXmlNode(RXml.DEFAULT_ROOT_NAME);
      //      FXmlNode dsNode = config.createNode(FDataset.NAME);
      //      dsNode.set("name", page.getFormName());
      //      for(IXmlObject item : console.list()){
      //         FXmlNode rowNode = dsNode.createNode();
      //         item.saveConfig(rowNode, EXmlConfig.Simple);
      //         rowNode.setName(FRow.NAME);
      //      }
      page.setFormName("database.procedure.PackageList");
      page.setFormValue(config.xml());
      return "PackageList";
   }

   @Override
   public String update(IWebContext context,
                        ISqlContext sqlContext,
                        FProcedurePage page){
      // Environment
      setEnvironment(context, page);
      // Attribute
      String sql = "SELECT * FROM USER_OBJECTS WHERE OBJECT_TYPE = 'PACKAGE' AND OBJECT_NAME = '" + page.getSelectPackage() + "'";
      FRow row = sqlContext.activeConnection().find(sql);
      if(null != row){
         FXmlNode node = new FXmlNode();
         node.attributes().append(row);
         page.setFormValue(node.xml());
      }
      return "Package";
   }
}
