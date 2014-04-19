package org.mo.jfa.face.design.webtree;

import org.mo.com.lang.FAttributes;
import org.mo.com.lang.IAttributes;
import org.mo.com.lang.RBoolean;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.IXmlObject;
import org.mo.com.xml.RXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectAction;
import org.mo.jfa.face.apl.page.IPublicPage;
import org.mo.logic.data.ISyCatalogDi;
import org.mo.logic.data.ISyCatalogNodeDi;
import org.mo.logic.session.ILogicSessionConsole;
import org.mo.web.core.webtree.IWebTreeConsole;
import org.mo.web.core.webtree.common.XTreeView;
import org.mo.web.protocol.context.IWebContext;

public class FWebTreeAction
      extends FAbsXmlObjectAction<XTreeView>
      implements
         IWebTreeAction
{

   public final static String PAGE_CATALOG = "Catalog";

   // 业务控制台对象
   @ALink
   protected ILogicSessionConsole _sessionConsole;

   @ALink
   protected IWebTreeConsole _webtreeConsole;

   @Override
   public String catalog(IWebContext context,
                         FWebTreePage page){
      return PAGE_CATALOG;
   }

   @Override
   public String delete(IWebContext context,
                        FWebTreePage page){
      return delete(_webtreeConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String insert(IWebContext context,
                        FWebTreePage page){
      return insert(_webtreeConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String list(IWebContext context,
                      FWebTreePage page){
      return list(_webtreeConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String sort(IWebContext context,
                      FWebTreePage page){
      return sort(_webtreeConsole, context, page, IPublicPage.XOBJECT_SORT);
   }

   @Override
   public String syncCatalog(IWebContext context,
                             ISyCatalogDi syCatalogDi,
                             ISyCatalogNodeDi syCatalogNodeDi,
                             FWebTreePage page){
      String treeName = context.parameter(PTY_SEL_COLLECTION);
      IXmlObject webtree = _webtreeConsole.get(treeName);
      FXmlNode config = RXmlObject.convertDeepToNode(webtree, EXmlConfig.Value);
      if(RBoolean.parse(config.get(XTreeView.PTY_IS_VALID))){
         IAttributes values = new FAttributes();
         values.set("code", config.get(XTreeView.PTY_NAME));
         // 同步树目录之前操作
         syCatalogDi.doSyncCatalogBefore(_sessionConsole.makeLogic(), values);
         // 同步树目录操作
         syncCatalogStore(config, values, syCatalogDi, syCatalogNodeDi);
         // 同步树目录之后操作
         syCatalogDi.doSyncCatalogAfter(_sessionConsole.makeLogic(), values);
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   @Override
   public String syncCatalogAll(IWebContext context,
                                ISyCatalogDi syCatalogDi,
                                ISyCatalogNodeDi syCatalogNodeDi,
                                FWebTreePage page){
      IAttributes values = new FAttributes();
      // 同步树目录之前操作
      syCatalogDi.doSyncCatalogBefore(_sessionConsole.makeLogic(), values);
      for(IXmlObject webtree : _webtreeConsole.list()){
         String isValid = webtree.innerGet(XTreeView.PTY_IS_VALID);
         if(RBoolean.parse(isValid)){
            FXmlNode config = RXmlObject.convertDeepToNode(webtree, EXmlConfig.Value);
            syncCatalogStore(config, new FAttributes(), syCatalogDi, syCatalogNodeDi);
         }
      }
      // 同步树目录之后操作
      syCatalogDi.doSyncCatalogAfter(_sessionConsole.makeLogic(), values);
      return IPublicPage.PROCESS_SUCCESS;
   }

   protected void syncCatalogStore(FXmlNode config,
                                   IAttributes values,
                                   ISyCatalogDi syCatalogDi,
                                   ISyCatalogNodeDi syCatalogNodeDi){
      // 091116修改 YJHUA
      // 配置有效的同步
      if(RBoolean.parse(config.get("is_valid"))){
         // 更新树目录的根节点
         if(config.isName("TreeView")){
            values.set("is_valid", config.get("is_valid"));
            values.append(config.attributes());
            values.set("command", "S");
            values.set("code", config.get("name"));
            String pack = syCatalogDi.doSyncCatalog(_sessionConsole.makeLogic(), values).parameter("parameters_").asString();
            values.unpack(pack);
            values.set("parent_id", "0");
         }
         // 更新所有子节点
         int order = 0;
         for(FXmlNode node : config.nodes()){
            if(node.isName("TreeNode") && RBoolean.parse(node.get("is_valid"))){
               order++;
               IAttributes nodeValues = new FAttributes(values);
               nodeValues.set("child", "N");
               nodeValues.set("is_config", "N");
               // 获取属性集合
               IAttributes properties = new FAttributes();
               properties.split(node.get("attributes"), "=", "\n");
               // 设置节点信息
               nodeValues.append(node.attributes());
               nodeValues.set("disp_order", Integer.toString(order));
               nodeValues.set("link_catalog", node.get("link_tree"));
               nodeValues.set("attributes", properties.pack().toString());
               nodeValues.set("is_valid", node.get(XTreeView.PTY_IS_VALID));
               String pack = syCatalogNodeDi.doSyncNode(_sessionConsole.makeLogic(), nodeValues).parameter("parameters_").asString();
               nodeValues.unpack(pack);
               // 同步子节点
               syncCatalogStore(node, nodeValues, syCatalogDi, syCatalogNodeDi);
            }
         }
         // 更新树目录的根节点
         // 091116YJHUA注掉
         //         if(config.isName("TreeView")){
         //            values.set("is_valid", config.get("is_valid"));
         //            values.append(config.attributes());
         //            values.set("command", "C");
         //            syCatalogDi.doSyncCatalog(_sessionConsole.makeLogic(), values);
         //         }
      }
   }

   @Override
   public String update(IWebContext context,
                        FWebTreePage page){
      return update(_webtreeConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

}
