package org.mo.web.core.webtree;

import org.mo.com.data.ISqlConnection;
import org.mo.com.lang.FObjects;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.face.ALink;
import org.mo.eng.data.IDatabaseConsole;
import org.mo.eng.persistence.common.FXmlPersistence;
import org.mo.eng.store.FXmlConfigConsole;
import org.mo.logic.data.ISyCatalogDi;
import org.mo.logic.data.impl.FSyCatalogImpl;
import org.mo.web.core.webtree.common.XTreeNode;
import org.mo.web.core.webtree.common.XTreeNodeType;
import org.mo.web.core.webtree.common.XTreeView;

public class FWebTreeConsole
      extends FXmlConfigConsole<XTreeView>
      implements
         IWebTreeConsole
{

   //private static ILogger _logger = RLogger.find(FWebTreeConsole.class);

   @ALink
   protected IDatabaseConsole _databaseConsole;

   @Override
   public FXmlNode buildSimple(String tree){
      XTreeView xtree = get(tree);
      FXmlPersistence persistence = _persistenceConsole.find(FXmlPersistence.class, _persistence);
      FXmlNode config = persistence.saveObject(xtree, EXmlConfig.Simple);
      // 创建输出节点
      FXmlNode resultNode = new FXmlNode(config.name());
      resultNode.attributes().append(config.attributes());
      // 查找是否包含父对象
      String parentName = xtree.innerGet("parent_name");
      if(!RString.isEmpty(parentName)){
         FXmlNode parentNode = buildSimple(parentName);
         for(FXmlNode childNode : parentNode){
            resultNode.push(childNode);
         }
      }
      // 处理所有子节点
      buildSimpleDeep(config, resultNode);
      return resultNode;
   }

   private void buildSimpleDeep(FXmlNode xNode,
                                FXmlNode node){
      if(xNode.hasNode()){
         for(FXmlNode xChild : xNode){
            if(RBoolean.parse(xChild.get("is_valid"))){
               // 检查节点权限
               if(xChild.isName(XTreeNode.NAME) || xChild.isName(XTreeNodeType.NAME)){
                  String attributes = xChild.get("attributes", null);
                  if(RString.isNotEmpty(attributes)){
                     xChild.removeAttribute("attributes");
                     xChild.attributes().split(attributes, "=", "\n", true);
                  }
               }
               // 创建节点
               FXmlNode child = new FXmlNode(xChild.name());
               child.attributes().append(xChild.attributes());
               buildSimpleDeep(xChild, child);
               node.push(child);
            }
         }
      }
   }

   @Override
   public FXmlNode buildSimpleWithPermission(String treeName,
                                             String userId){
      // 获得数目的数据库权限
      ISqlConnection cnn = null;
      FStrings permission = new FStrings();
      try{
         cnn = _databaseConsole.alloc();
         ISyCatalogDi syCatalogDi = new FSyCatalogImpl(cnn);
         String pack = syCatalogDi.getPermissionPack(userId, treeName).returnAsString();
         permission.unpack(pack);
      }finally{
         if(null != cnn){
            _databaseConsole.free(cnn);
         }
      }
      // 建立树节点
      FXmlNode xTree = buildSimple(treeName);
      FXmlNode tree = new FXmlNode(xTree.name());
      tree.attributes().append(xTree.attributes());
      buildSimpleWithPermissionDeep(xTree, tree, permission);
      return tree;
   }

   private void buildSimpleWithPermissionDeep(FXmlNode xNode,
                                              FXmlNode node,
                                              FStrings permission){
      if(xNode.hasNode()){
         for(FXmlNode xChild : xNode){
            if(xChild.isName("TreeNode")){
               // 检查节点权限
               if(RBoolean.parse(xChild.get("is_config"))){
                  String childName = xChild.get("name").toUpperCase();
                  if(!permission.contains(childName)){
                     continue;
                  }
               }
            }
            // 创建节点
            FXmlNode child = new FXmlNode(xChild.name());
            child.attributes().append(xChild.attributes());
            buildSimpleWithPermissionDeep(xChild, child, permission);
            // 检查子节点的访问性
            if(child.isName("TreeNode")){
               if(!child.hasNode() && !RBoolean.parse(child.get("is_config"))){
                  continue;
               }
            }
            node.push(child);
         }
      }
   }

   @Override
   protected FObjects<XTreeView> createCollection(){
      return new FObjects<XTreeView>(XTreeView.class);
   }
}
