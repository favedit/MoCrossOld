/*
 * @(#)FEventAction.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.jfa.face.logic.project;

import org.mo.com.lang.IAttributes;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectAction;
import org.mo.jfa.face.apl.page.IPublicPage;
import org.mo.logic.data.ISyPropertyDi;
import org.mo.logic.project.ILogicProjectConsole;
import org.mo.logic.property.ILogicPropertyConsole;
import org.mo.logic.session.ILogicSessionConsole;
import org.mo.web.core.container.AContainer;
import org.mo.web.protocol.context.IWebContext;

/**
 * <T>业务事件树目录各种操作动作接口实体类。</T>
 * <P>提供对业务事件和业务事件用树目录形式进行增删改和同步等各种操作的接口。</P>
 * 
 * @author YJHUA
 * @version 1.00 - 2008/12/08
 */
public class FProjectAction
      extends FAbsXmlObjectAction<IXmlObject>
      implements
         IProjectAction
{

   @ALink
   protected ILogicProjectConsole _projectConsole;

   @ALink
   protected ILogicSessionConsole _sessionConsole;

   @ALink
   protected ILogicPropertyConsole _propertyConsole;

   public final static String PAGE_CATALOG = "Catalog";

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventAction#catalog(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.event.FEventPage)
    */
   @Override
   public String catalog(IWebContext context,
                         FProjectPage page){
      System.out.println(this + "Catalog----" + "调用了Seriver=====Catalog");
      return catalog(_projectConsole, context, page, PAGE_CATALOG);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventAction#delete(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.event.FEventPage)
    */
   @Override
   public String delete(IWebContext context,
                        FProjectPage page){
      return delete(_projectConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventAction#insert(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.event.FEventPage)
    */
   @Override
   public String insert(IWebContext context,
                        FProjectPage page){
      return insert(_projectConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventAction#list(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.event.FEventPage)
    */
   @Override
   public String list(IWebContext context,
                      FProjectPage page){
      return list(_projectConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventAction#update(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.event.FEventPage)
    */
   @Override
   public String update(IWebContext context,
                        FProjectPage page){
      return update(_projectConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventAction#sort(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.event.FEventPage)
    */
   @Override
   public String sort(IWebContext context,
                      FProjectPage page){
      return sort(_projectConsole, context, page, IPublicPage.XOBJECT_SORT);
   }

   /**
    * <T>遍历第二层所有子节点。</T>
    * 
    * @param xsearch 父节点
    * @param parentAttributes 父节点属性包
    * @param lgEventTypeDi 业务事件类型模块操作接口
    * @param lgEventTypeConfigDi 业务事件类型设置模块操作接口
    * @param lgEventGroupConfigDi 业务事件分组设置模块操作接口
    * @param syPropertyDi 系统属性模块操作接口
    */
   protected void searchOneChild(IXmlObject xsearch,
                                 IAttributes parentAttributes,
                                 ISyPropertyDi syPropertyDi){
      for(IXmlObject xds : xsearch.children()){
         //IAttributes attributes = xds.toSimpleAttributes();
         //         if(XCatalog.isInstance(xds)){
         //            attributes.set("project_name", parentAttributes.get("name"));
         //            attributes.set("parent_catalog", null);
         //            attributes.set("name", parentAttributes.get("name") + "." + xds.innerGet("name"));
         //            //lgProjectDi.doSyncCatalog(_sessionConsole.makeLogic(), attributes);
         //         }
         if(xds.hasChild()){
            //searchTwoChild(xds, attributes, pjProjectdi, syPropertyDi);
         }
      }
   }

   /**
    * <T>遍历第三层所有子节点。</T>
    * 
    * @param xsearch 父节点
    * @param parentAttributes 父节点属性包
    * @param lgEventTypeConfigDi 业务事件类型设置模块操作接口
    * @param syPropertyDi 系统属性模块操作接口
    */
   protected void searchTwoChild(IXmlObject xsearch,
                                 IAttributes parentAttributes,
                                 ISyPropertyDi syPropertyDi){
      //      for(IXmlObject xds : xsearch.children()){
      //         //IAttributes attributes = xds.toSimpleAttributes();
      //         // 同步子目录节点
      //         //         if(XCatalog.isInstance(xds)){
      //         //            attributes.set("project_name", parentAttributes.get("project_name"));
      //         //            attributes.set("parent_catalog", parentAttributes.get("name"));
      //         //            attributes.set("name", parentAttributes.get("name")+"."+attribute.get("name"));
      //         //            lgProjectDi.doSyncCatalog(_sessionConsole.makeLogic(), attributes);
      //         ////         }else if(XModule.isInstance(xds)){
      //         //              attributes.set("catalog_name", parentAttributes.get("name");
      //         //              attributes.set("project_name", parentAttributes.get("project_name"));
      //         //              lgProjectDi.doModule(_sessionConsole.makeLogic(), attributes);
      //         //           }
      //      }
   }

   /**
    * <T>遍历第四层所有子节点。</T>
    * 
    * @param xsearch 父节点
    * @param parentAttributes 父节点属性包
    * @param lgEventTypeConfigDi 业务事件类型设置模块操作接口
    * @param syPropertyDi 系统属性模块操作接口
    */
   protected void searchThreeChild(IXmlObject xsearch,
                                   IAttributes parentAttributes,
                                   ISyPropertyDi syPropertyDi){
      //      for(IXmlObject xds : xsearch.children()){
      //         //IAttributes attributes = xds.toSimpleAttributes();
      //         //         if(XPoint.isInstance(xds)){
      //         //            attributes.set("project_name", parentAttributes.get("project_name"));
      //         //            attributes.set("module_name", parentAttributes.get("name"));
      //         //            attributes.set("name", parentAttributes.get("name")+"."+attributes.get("name"));
      //         //            lgProjectDi.doSyncPoint(_sessionConsole.makeLogic(), attributes);
      //         //         }
      //      }
   }

   /**
    * <T>遍历第五层所有子节点。</T>
    * 
    * @param xsearch 父节点
    * @param parentAttributes 父节点属性包
    * @param lgEventTypeConfigDi 业务事件类型设置模块操作接口
    * @param syPropertyDi 系统属性模块操作接口
    */
   protected void searchFourChild(IXmlObject xsearch,
                                  IAttributes parentAttributes,
                                  ISyPropertyDi syPropertyDi){
      //      for(IXmlObject xds : xsearch.children()){
      //         //IAttributes attributes = xds.toSimpleAttributes();
      //         //         if(XCase.isInstance(xds)){
      //         //            attributes.set("project_name", parentAttributes.get("project_name"));
      //         //            attributes.set("point_name", parentAttributes.get("name"));
      //         //            attributes.set("name", parentAttributes.get("name")+"."+attributes.get("name"));
      //         //            lgProjectDi.doSyncCase(_sessionConsole.makeLogic(), attributes);
      //         //         }
      //      }
   }

   /**
    * <T>遍历第六层所有子节点。</T>
    * 
    * @param xsearch 父节点
    * @param parentAttributes 父节点属性包
    * @param lgEventTypeConfigDi 业务事件类型设置模块操作接口
    * @param syPropertyDi 系统属性模块操作接口
    */
   protected void searchFiveChild(IXmlObject xsearch,
                                  IAttributes parentAttributes,
                                  ISyPropertyDi syPropertyDi){
      //      for(IXmlObject xds : xsearch.children()){
      //         //IAttributes attributes = xds.toSimpleAttributes();
      //         //         if(XCase.isInstance(xds)){
      //         //            attributes.set("project_name", parentAttributes.get("project_name"));
      //         //            attributes.set("point_name", parentAttributes.get("point_name"));
      //         //            attributes.set("case_name", parentAttributes.get("name"));
      //         //            attributes.set("name", parentAttributes.get("name")+"."+attributes.get("name"));
      //         //            lgProjectDi.doSyncInvoke(_sessionConsole.makeLogic(), attributes);
      //         //         }
      //      }
   }

   /**
    * <T>查找系统的属性分组。</T>
    * 
    * @param datasetName 数据集名称
    * @return 系统属性分组名称
    */
   protected String searchPropertyGroup(String datasetName){
      for(IXmlObject xds : _propertyConsole.list()){
         if(datasetName.equals(xds.innerGet("dataset"))){
            return xds.innerGet("name");
         }
      }
      return null;
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventAction#syncEvent(org.mo.web.protocol.context.IWebContext, org.mo.logic.data.ILgEventGroupDi, org.mo.logic.data.ILgEventTypeDi, org.mo.logic.data.ILgEventTypeConfigDi, org.mo.logic.data.ILgEventGroupConfigDi, org.mo.logic.data.ISyPropertyDi, org.mo.jfa.face.logic.event.FEventPage)
    */
   @Override
   public String syncProject(IWebContext context,
                             ISyPropertyDi syPropertyDi,
                             @AContainer(name = "page") FProjectPage page){
      String name = page.getSelectCollection();
      IXmlObject xds = _projectConsole.find(name);
      //IAttributes attributes = xds.toSimpleAttributes();
      //pjProjectdi.doSyncProject(_sessionConsole.makeLogic(), attributes);
      //FSqlProcedure p = 
      //p.parameter("").asString()
      if(xds.hasChild()){
         //searchOneChild(xds, attributes, pjProjectdi, syPropertyDi);
         //         FAttributes groupConfigAttributes = new FAttributes();
         //         FAttributes typeAttributes = new FAttributes();
         //         FAttributes typeValueAttributes = new FAttributes();
         //         search(xds, sqlContext, node, groupConfigAttributes, typeAttributes, typeValueAttributes);
         //         System.out.println("***********groupConfigAttributes" + groupConfigAttributes.toString());
         //         System.out.println("***********typeAttributes" + typeAttributes.toString());
         //         System.out.println("***********typeValueAttributes" + typeValueAttributes.toString());
         //         executeType(sqlContext, typeAttributes);
         //         executeTypeValue(sqlContext, typeValueAttributes);
         //executeGroupConfig(sqlContext, groupConfigAttributes);
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventAction#allSyncEvent(org.mo.web.protocol.context.IWebContext, org.mo.logic.data.ILgEventGroupDi, org.mo.logic.data.ILgEventTypeDi, org.mo.logic.data.ILgEventTypeConfigDi, org.mo.logic.data.ILgEventGroupConfigDi, org.mo.logic.data.ISyPropertyDi, org.mo.jfa.face.logic.event.FEventPage)
    */
   @Override
   public String allSyncProject(IWebContext context,
                                ISyPropertyDi syPropertyDi,
                                @AContainer(name = "page") FProjectPage page){
      //      for(IXmlObject xds : _projectConsole.list()){
      //         //IAttributes attributes = xds.toSimpleAttributes();
      //         //lgProjectDi.doSyncProject(_sessionConsole.makeLogic(), attributes);
      //         if(xds.hasChild()){
      //            //searchOneChild(xds, attributes, pjProjectdi, syPropertyDi);
      //         }
      //      }
      return IPublicPage.PROCESS_SUCCESS;
   }
}
