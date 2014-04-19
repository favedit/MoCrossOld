package org.mo.jfa.face.database.codelist;

import org.mo.com.data.FSqlProcedure;
import org.mo.com.lang.EStringCase;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.IAttributes;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.REnum;
import org.mo.com.lang.RString;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.IXmlObject;
import org.mo.com.xml.RXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.data.codelist.ECodeListBuildAction;
import org.mo.data.codelist.ECodeListSource;
import org.mo.data.codelist.FCodeListBuilderArgs;
import org.mo.data.codelist.ICodeListConsole;
import org.mo.data.codelist.common.XCodeList;
import org.mo.data.dataset.common.XDataStore;
import org.mo.eng.data.common.ISqlContext;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectAction;
import org.mo.jfa.face.apl.page.IPublicPage;
import org.mo.logic.data.ICmCodeDi;
import org.mo.logic.data.ICmCodeListDi;
import org.mo.logic.session.ILogicSessionConsole;
import org.mo.web.core.format.ESyntax;
import org.mo.web.core.format.IFormatConsole;
import org.mo.web.core.format.ISyntaxMaker;
import org.mo.web.core.webtree.common.XTreeView;
import org.mo.web.protocol.context.IWebContext;

public class FCodeListAction
      extends FAbsXmlObjectAction<IXmlObject>
      implements
         ICodeListAction
{

   public final static String PAGE_CATALOG = "Catalog";

   public final static String PAGE_SOURCE = "Source";

   @ALink
   protected ICodeListConsole _codelistConsole;

   @ALink
   protected IFormatConsole _formatConsole;

   @ALink
   protected ILogicSessionConsole _sessionConsole;

   @Override
   public String build(IWebContext context,
                       ISqlContext sqlContext,
                       FCodeListPage page){
      // 获得用户选中的表单
      page.appachContext(context);
      String type = context.parameter("type");
      ECodeListSource etype = REnum.parse(ECodeListSource.class, type);
      IXmlObject xcodelist = getSelectCollection(_codelistConsole, page);
      // 获得内容
      FCodeListBuilderArgs args = new FCodeListBuilderArgs();
      args.setType(etype);
      args.setInstance(xcodelist);
      if(ECodeListSource.All == etype){
         args.setAction(ECodeListBuildAction.Store);
         _codelistConsole.build(args);
         page.setSource(null);
         return IPublicPage.PROCESS_SUCCESS;
      }else{
         // 生成代码
         args.setAction(ECodeListBuildAction.Query);
         _codelistConsole.build(args);
         // 为代码进行HTML上色
         ISyntaxMaker syntaxMaker = null;
         if(ECodeListSource.PackageHead == etype || ECodeListSource.PackageBody == etype){
            syntaxMaker = _formatConsole.find(ESyntax.PlSql);
         }else{
            syntaxMaker = _formatConsole.find(ESyntax.Java);
         }
         page.setSource(syntaxMaker.format(args.source()));
      }
      return PAGE_SOURCE;
   }

   @SuppressWarnings("unused")
   @Override
   public String buildAll(IWebContext context,
                          ISqlContext sqlContex,
                          FCodeListPage page){
      // 获得用户选中的表单
      String type = context.parameter("type");
      ECodeListSource etype = REnum.parse(ECodeListSource.class, type);
      int sqlCount = 0;
      for(IXmlObject xcodelist : _codelistConsole.list()){
         // 获得内容
         if(RBoolean.parse(xcodelist.innerGet("is_valid"))){
            // 检查数据名称有效性
            String dataName = xcodelist.innerGet("data_name");
            if(RString.isEmpty(dataName)){
               throw new FFatalError("Code list data name is empty. (name={0})", xcodelist.innerGet("name"));
            }
            // 生成代码
            FCodeListBuilderArgs args = new FCodeListBuilderArgs(xcodelist);
            args.setType(etype);
            args.setAction(ECodeListBuildAction.Execute);
            if(ECodeListSource.AllPackageHead == etype){
               _codelistConsole.build(args);
               sqlContex.activeConnection().executeSql(args.source());
               args.setAction(ECodeListBuildAction.Store);
               _codelistConsole.build(args);
               sqlCount++;
            }else if(ECodeListSource.AllPackageBody == etype){
               _codelistConsole.build(args);
               sqlContex.activeConnection().executeSql(args.source());
               args.setAction(ECodeListBuildAction.Store);
               _codelistConsole.build(args);
               sqlCount++;
            }
         }
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   @Override
   public String catalog(IWebContext context,
                         FCodeListPage page){
      return PAGE_CATALOG;
   }

   @Override
   public String commit(IWebContext context,
                        ISqlContext sqlContext,
                        ICmCodeListDi cmCodeListDi,
                        ICmCodeDi cmCodeDi,
                        FCodeListPage page){
      // 获得用户选中的表单
      page.appachContext(context);
      IXmlObject xcodelist = getSelectCollection(_codelistConsole, page);
      cmCodeListDi.doSyncCodeListBegin(_sessionConsole.makeLogic(), xcodelist.innerGet("name"));
      if(RBoolean.parse(xcodelist.innerGet("is_valid"))){
         FXmlNode config = RXmlObject.convertDeepToNode(xcodelist, EXmlConfig.Value);
         commitStore(config, new FAttributes(), cmCodeListDi, cmCodeDi);
      }
      cmCodeListDi.doSyncCodeListEnd(_sessionConsole.makeLogic(), xcodelist.innerGet("name"));
      return IPublicPage.PROCESS_SUCCESS;
   }

   @Override
   public String commitAll(IWebContext context,
                           ISqlContext sqlContext,
                           ICmCodeListDi cmCodeListDi,
                           ICmCodeDi cmCodeDi,
                           FCodeListPage page){
      cmCodeListDi.doSyncCodeListBegin(_sessionConsole.makeLogic(), null);
      // 获得所有的codeList
      for(IXmlObject xcodelist : _codelistConsole.list()){
         String isValid = xcodelist.innerGet(XTreeView.PTY_IS_VALID);
         if(RBoolean.parse(isValid)){
            FXmlNode config = RXmlObject.convertDeepToNode(xcodelist, EXmlConfig.Value);
            FAttributes attributes = new FAttributes();
            attributes.setNameCase(EStringCase.Lower);
            commitStore(config, attributes, cmCodeListDi, cmCodeDi);
         }
      }
      cmCodeListDi.doSyncCodeListEnd(_sessionConsole.makeLogic(), null);
      return IPublicPage.PROCESS_SUCCESS;
   }

   protected void commitStore(FXmlNode config,
                              IAttributes values,
                              ICmCodeListDi cmCodeListDi,
                              ICmCodeDi cmCodeDi){
      // 同步codeList
      if(config.isName("CodeList")){
         values.append(config.attributes());
         values.set("command", "S");
         FSqlProcedure procedure = cmCodeListDi.doSyncCodeList(_sessionConsole.makeLogic(), values);
         String pack = procedure.parameter("params_").asString();
         values.unpack(pack);
         values.set("list_id", values.get("list_id"));
      }
      // 同步codeList下的code
      int order = 0;
      for(FXmlNode node : config.nodes()){
         if(node.getBoolean("is_valid", false)){
            order++;
            IAttributes attributes = node.attributes();
            attributes.set("list_id", values.get("list_id"));
            attributes.set("disp_order", String.valueOf(order));
            cmCodeDi.doSyncCode(_sessionConsole.makeLogic(), attributes);
         }
      }
   }

   @Override
   public String delete(IWebContext context,
                        FCodeListPage page){
      return delete(_codelistConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String execute(IWebContext context,
                         ISqlContext sqlContext,
                         FCodeListPage page){
      // 获得用户选中的表单
      page.appachContext(context);
      String type = context.parameter("type");
      ECodeListSource etype = REnum.parse(ECodeListSource.class, type);
      IXmlObject xcodelist = getSelectCollection(_codelistConsole, page);
      // 检查数据集的有效性
      String isValid = xcodelist.innerGet(XCodeList.PTY_IS_VALID);
      if(!RBoolean.parse(isValid)){
         throw new FFatalError("Dataset is invalid. (dataset={0})", xcodelist.innerGet(XDataStore.PTY_NAME));
      }
      // 执行数据内容
      FCodeListBuilderArgs args = new FCodeListBuilderArgs(xcodelist);
      args.setType(etype);
      if(ECodeListSource.PackageHead == etype || ECodeListSource.PackageBody == etype){
         // 向数据库反映Package和存储
         makePackage(sqlContext, xcodelist, etype);
      }else{
         // 将内容存储到硬盘里
         args.setAction(ECodeListBuildAction.Store);
         _codelistConsole.build(args);
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   @Override
   public String insert(IWebContext context,
                        FCodeListPage page){
      return insert(_codelistConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String list(IWebContext context,
                      FCodeListPage page){
      return list(_codelistConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   public void makePackage(ISqlContext sqlContext,
                           IXmlObject xcodelist,
                           ECodeListSource etype){
      // 创建参数
      FCodeListBuilderArgs args = new FCodeListBuilderArgs();
      args.setInstance(xcodelist);
      args.setType(etype);
      // 向数据库反映Package内容
      args.setAction(ECodeListBuildAction.Execute);
      _codelistConsole.build(args);
      sqlContext.activeConnection().executeSql(args.source());
      // 将Package内容存储到硬盘里
      args.setAction(ECodeListBuildAction.Store);
      _codelistConsole.build(args);
   }

   @Override
   public String sort(IWebContext context,
                      FCodeListPage page){
      return sort(_codelistConsole, context, page, IPublicPage.XOBJECT_SORT);
   }

   @Override
   public String update(IWebContext context,
                        FCodeListPage page){
      return update(_codelistConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

}
