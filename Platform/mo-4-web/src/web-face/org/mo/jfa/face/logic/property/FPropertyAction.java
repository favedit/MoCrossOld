/*
 * @(#)FPropertyAction.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.jfa.face.logic.property;

import org.mo.com.lang.IAttributes;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.data.dataset.IDatasetConsole;
import org.mo.eng.data.common.ISqlContext;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectAction;
import org.mo.jfa.face.apl.page.IPublicPage;
import org.mo.logic.data.ISyPropertyDi;
import org.mo.logic.data.ISyPropertyGroupDi;
import org.mo.logic.property.ILogicPropertyConsole;
import org.mo.logic.session.ILogicSessionConsole;
import org.mo.web.protocol.context.IWebContext;

/**
 * <T>属性树目录的各种操作实体类。</T>
 * <P>该类提供了对属性节点各种操作的方法的实现。</P>
 * 
 * @author HYKUN
 * @version 1.00 - 2008/12/06
 */
public class FPropertyAction
      extends FAbsXmlObjectAction<IXmlObject>
      implements
         IPropertyAction
{

   // 页面名称
   public final static String PAGE_CATALOG = "Catalog";

   // 名称字段
   public final static String PTY_NAME = "name";

   // 数据集字段
   public final static String PTY_DATASET = "dataset";

   // 数据名称字段
   public final static String PTY_DATA_NAME = "data_name";

   // 表名
   public final static String PTY_TABLE_NAME = "table_name";

   // 属性分组名
   public final static String PTY_GROUP_NAME = "group_name";

   // 属性类型名
   public final static String PTY_TYPE_NAME = "type_name";

   // 属性控制台对象。
   @ALink
   protected ILogicPropertyConsole _propertyConsole;

   // 业务控制台对象。
   @ALink
   protected ILogicSessionConsole _sessionConsole;

   // 数据集控制台对象。
   @ALink
   protected IDatasetConsole _formDatasetConsole;

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.property.IPropertyAction#catalog(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.property.FPropertyPage)
    */
   @Override
   public String catalog(IWebContext context,
                         FPropertyPage page){
      return catalog(_propertyConsole, context, page, PAGE_CATALOG);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.property.IPropertyAction#delete(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.property.FPropertyPage)
    */
   @Override
   public String delete(IWebContext context,
                        FPropertyPage page){
      return delete(_propertyConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.property.IPropertyAction#syncProperty(org.mo.web.protocol.context.IWebContext, org.mo.logic.data.ISyPropertyGroupDi, org.mo.logic.data.ISyPropertyDi, org.mo.jfa.face.logic.property.FPropertyPage)
    */
   @Override
   public String doSynchronize(IWebContext context,
                               ISqlContext sqlcontext,
                               ISyPropertyGroupDi syPropertyGroupDi,
                               ISyPropertyDi syPropertyDi,
                               FPropertyPage page){
      String name = page.getSelectCollection();
      IXmlObject xds = _propertyConsole.get(name);
      syPropertyGroupDi.doSyncGroup(_sessionConsole.makeLogic(), xds.toSimpleAttributes());
      // 如果含有字节点，搜索字节点。
      if(xds.hasChild()){
         doSyncType(xds, sqlcontext, syPropertyGroupDi, syPropertyDi);
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.property.IPropertyAction#doSynchronizeAll(org.mo.web.protocol.context.IWebContext, org.mo.logic.data.ISyPropertyGroupDi, org.mo.logic.data.ISyPropertyDi, org.mo.jfa.face.logic.property.FPropertyPage)
    */
   @Override
   public String doSynchronizeAll(IWebContext context,
                                  ISqlContext sqlcontext,
                                  ISyPropertyGroupDi syPropertyGroupDi,
                                  ISyPropertyDi syPropertyDi,
                                  FPropertyPage page){
      // 执行查询
      //      FSqlQuery query = new FSqlQuery(sqlcontext, getClass(), "group.list");
      //      FDataset dataset = query.fetchDataset();
      for(IXmlObject xds : _propertyConsole.list()){
         //         FRow row = dataset.find("name", xds.innerGet("name"));
         //         if(null != row){
         //            dataset.set(dataset.indexOf(row), null);
         //         }
         syPropertyGroupDi.doSyncGroup(_sessionConsole.makeLogic(), xds.toSimpleAttributes());
         if(xds.hasChild()){
            doSyncType(xds, sqlcontext, syPropertyGroupDi, syPropertyDi);
         }
      }
      //      for(int n = 0; n < dataset.count(); n++){
      //         FRow row = dataset.get(n);
      //         if(null != row){
      //            syPropertyDi.doDelete(_sessionConsole.makeLogic(), row);
      //         }
      //      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   /**
    * <T>同步属性到数据库对应表。<T>
    * <P>如果记录已存在，则执行更新操作，否则执行新建操作。</P>
    * 
    * @param xsearch 父节点xml对象。
    * @param sqlcontext 数据库环境对象。
    * @param syPropertyGroupDi 属性分组代理对象。
    * @param syPropertyDi 属性代理对象。
    * @param groupName 属性分组。
    * @param tableName 属性分组对应的表名。
    */
   protected void doSyncProperty(IXmlObject xsearch,
                                 ISqlContext sqlcontext,
                                 ISyPropertyGroupDi syPropertyGroupDi,
                                 ISyPropertyDi syPropertyDi,
                                 String groupName,
                                 String tableName){
      // TODO: 属性删除操作，暂时先不删除
      // 执行查询
      //      FSqlQuery query = new FSqlQuery(sqlcontext, getClass(), "property.list");
      //      query.bindString("group_name", groupName);
      //      FDataset dataset = query.fetchDataset();
      for(IXmlObject xds : xsearch.children()){
         //         FRow row = dataset.find("name", xds.innerGet("name"));
         //         if(null != row){
         //            dataset.set(dataset.indexOf(row), null);
         //         }
         IAttributes attributes = xds.toSimpleAttributes();
         // 将获得的表名和类型名追加到属性中，传回plsql执行
         attributes.set(PTY_TABLE_NAME, tableName);
         attributes.set(PTY_GROUP_NAME, groupName);
         attributes.set(PTY_TYPE_NAME, xsearch.innerGet(PTY_NAME));
         syPropertyDi.doSyncProperty(_sessionConsole.makeLogic(), attributes);
      }
      //      for(int n = 0; n < dataset.count(); n++){
      //         FRow row = dataset.get(n);
      //         if(null != row){
      //            syPropertyDi.doDelete(_sessionConsole.makeLogic(), row);
      //         }
      //      }
   }

   /**
    * <T>查找类型节点。</T>
    * <P>查找到父节点数据集对应的表名，和属性分组名，并和自己的类型名称一块传入同步属性方法</P>
    * 
    * @param xsearch 父xml对象。
    * @param sqlcontext 数据库环境对象。
    * @param propertyGroup 属性分组的plsql代理对象。
    * @param property 属性的plsql代理对象。
    */
   protected void doSyncType(IXmlObject xsearch,
                             ISqlContext sqlcontext,
                             ISyPropertyGroupDi propertyGroup,
                             ISyPropertyDi property){
      for(IXmlObject xds : xsearch.children()){
         // 取出数据集的名称
         String groupName = xsearch.innerGet(PTY_NAME);
         String datasetName = xsearch.innerGet(PTY_DATASET);
         // 获得数据集的xml对象
         IXmlObject outDsNode = _formDatasetConsole.get(datasetName);
         // 根据数据名称取得表名
         String tableName = outDsNode.innerGet(PTY_DATA_NAME);
         if(xds.hasChild()){
            doSyncProperty(xds, sqlcontext, propertyGroup, property, groupName, tableName);
         }
      }
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.property.IPropertyAction#insert(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.property.FPropertyPage)
    */
   @Override
   public String insert(IWebContext context,
                        FPropertyPage page){
      return insert(_propertyConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.property.IPropertyAction#list(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.property.FPropertyPage)
    */
   @Override
   public String list(IWebContext context,
                      FPropertyPage page){
      return list(_propertyConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.property.IPropertyAction#sort(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.property.FPropertyPage)
    */
   @Override
   public String sort(IWebContext context,
                      FPropertyPage page){
      return sort(_propertyConsole, context, page, IPublicPage.XOBJECT_SORT);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.property.IPropertyAction#update(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.property.FPropertyPage)
    */
   @Override
   public String update(IWebContext context,
                        FPropertyPage page){
      return update(_propertyConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

}
