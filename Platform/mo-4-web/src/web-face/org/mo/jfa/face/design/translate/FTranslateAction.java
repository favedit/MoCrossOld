/*
 * @(#)FTranslateAction.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.jfa.face.design.translate;

import org.mo.com.lang.IAttributes;
import org.mo.com.lang.RBoolean;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.eng.translate.ITranslateConsole;
import org.mo.eng.translate.common.XContent;
import org.mo.eng.translate.common.XList;
import org.mo.eng.translate.common.XTranslate;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectAction;
import org.mo.jfa.face.apl.page.IPublicPage;
import org.mo.logic.data.ISyPropertyDi;
import org.mo.logic.data.ISyTranslateDi;
import org.mo.logic.property.ILogicPropertyConsole;
import org.mo.logic.session.ILogicSessionConsole;
import org.mo.web.protocol.context.IWebContext;

/**
 * <T>系统翻译树目录各种操作动作接口实体类。</T>
 * <P>提供对系统翻译和系统翻译用树目录形式进行增删改和同步等各种操作的接口实例。</P>
 * 
 * @author YJHUA
 * @version 1.00 - 2008/12/017
 */
public class FTranslateAction
      extends FAbsXmlObjectAction<IXmlObject>
      implements
         ITranslateAction
{

   @ALink
   protected ITranslateConsole _translateConsole;

   @ALink
   protected ILogicSessionConsole _sessionConsole;

   @ALink
   protected ILogicPropertyConsole _propertyConsole;

   public final static String PAGE_CATALOG = "Catalog";

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventAction#allSyncEvent(org.mo.web.protocol.context.IWebContext, org.mo.logic.data.ILgEventGroupDi, org.mo.logic.data.ILgEventTypeDi, org.mo.logic.data.ILgEventTypeConfigDi, org.mo.logic.data.ILgEventGroupConfigDi, org.mo.logic.data.ISyPropertyDi, org.mo.jfa.face.logic.event.FEventPage)
    */
   @Override
   public String allSyncTranslate(IWebContext context,
                                  ISyTranslateDi syTranslateDi,
                                  ISyPropertyDi syPropertyDi,
                                  FTranslatePage page){
      /// 循环获得根节点，并同步根结点
      for(IXmlObject xds : _translateConsole.list()){
         if(!RBoolean.parse(xds.innerGet("is_valid"))){
            continue;
         }
         IAttributes attributes = xds.toSimpleAttributes();
         syTranslateDi.doSyncGroup(_sessionConsole.makeLogic(), attributes);
         /// 判断是否有子节点，如果有则遍历子节点并同步子节点
         if(xds.hasChild()){
            searchOneChild(xds, attributes, syTranslateDi, syPropertyDi);
         }
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventAction#catalog(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.event.FEventPage)
    */
   @Override
   public String catalog(IWebContext context,
                         FTranslatePage page){
      return catalog(_translateConsole, context, page, PAGE_CATALOG);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventAction#delete(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.event.FEventPage)
    */
   @Override
   public String delete(IWebContext context,
                        FTranslatePage page){
      return delete(_translateConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventAction#insert(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.event.FEventPage)
    */
   @Override
   public String insert(IWebContext context,
                        FTranslatePage page){
      return insert(_translateConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventAction#list(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.event.FEventPage)
    */
   @Override
   public String list(IWebContext context,
                      FTranslatePage page){
      return list(_translateConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   /**
    * <T>遍历并同步第一层所有子节点。</T>
    * 
    * @param xsearch 父节点
    * @param parentAttributes 父节点属性包
    * @param syTranslateDi 系统翻译模块操作接口
    * @param syPropertyDi 系统所有属性模块操作接口
    */
   protected void searchOneChild(IXmlObject xsearch,
                                 IAttributes parentAttributes,
                                 ISyTranslateDi syTranslateDi,
                                 ISyPropertyDi syPropertyDi){
      /// 循环获得第一层所有子节点
      for(IXmlObject xds : xsearch.children()){
         IAttributes attributes = xds.toSimpleAttributes();
         if(XList.isInstance(xds)){
            attributes.set("group_name", parentAttributes.get("name"));
            // 判断该节点的代码类型是否为空，如果为空则等于父类的代码类型属性
            if(null == attributes.get("type_cd")){
               attributes.set("type_cd", parentAttributes.get("type_cd"));
            }
            /// 为该节点的子节点获得code编码
            String newCode = null;
            if(null != parentAttributes.get("code")){
               newCode = parentAttributes.get("code");
            }else{
               newCode = parentAttributes.get("name");
            }
            if(null != attributes.get("code")){
               newCode = newCode + "." + attributes.get("code");
            }else{
               newCode = newCode + "." + attributes.get("name");
               attributes.set("code", attributes.get("name"));
            }
            attributes.set("new_code", newCode);
            // 同步该节点
            syTranslateDi.doSyncList(_sessionConsole.makeLogic(), attributes);
         }
         /// 判断是否有子节点，如果有则遍历所有子节点
         if(xds.hasChild()){
            searchTwoChild(xds, attributes, syTranslateDi, syPropertyDi);
         }
      }
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

   /**
    * <T>遍历并同步第三层所有子节点。</T>
    * 
    * @param xsearch 父节点
    * @param parentAttributes 父节点属性包
    * @param syTranslateDi 系统翻译模块操作接口
    * @param syPropertyDi 系统所有属性模块操作接口
    */
   protected void searchThreeChild(IXmlObject xsearch,
                                   IAttributes parentAttributes,
                                   ISyTranslateDi syTranslateDi,
                                   ISyPropertyDi syPropertyDi){
      /// 循环获得子节点
      for(IXmlObject xds : xsearch.children()){
         IAttributes attributes = xds.toSimpleAttributes();
         /// 判断该节点的类型是否为XConten类型，如果是则同步该节点，否则同步XParameter类型
         if(XContent.isInstance(xds)){
            attributes.set("translate_code", parentAttributes.get("code"));
            String languageName = attributes.get("name");
            attributes.set("language_name", languageName);
            if(null == attributes.get("content")){
               if("ZH".equals(languageName)){
                  attributes.set("content", parentAttributes.get("zh_content"));
               }else if("JA".equals(languageName)){
                  attributes.set("content", parentAttributes.get("ja_content"));
               }
            }
            syTranslateDi.doSyncContent(_sessionConsole.makeLogic(), attributes);
         }else{
            attributes.set("translate_code", parentAttributes.get("code"));
            syTranslateDi.doSyncParameter(_sessionConsole.makeLogic(), attributes);
         }
      }
   }

   /**
    * <T>遍历并同步第二层所有子节点。</T>
    * 
    * @param xsearch 父节点
    * @param parentAttributes 父节点属性包
    * @param syTranslateDi 系统翻译模块操作接口
    * @param syPropertyDi 系统所有属性模块操作接口
    */
   protected void searchTwoChild(IXmlObject xsearch,
                                 IAttributes parentAttributes,
                                 ISyTranslateDi syTranslateDi,
                                 ISyPropertyDi syPropertyDi){
      /// 循环获得所有子节点
      for(IXmlObject xds : xsearch.children()){
         IAttributes attributes = xds.toSimpleAttributes();
         if(XTranslate.NAME.equals(xds.name())){
            attributes.set("group_name", parentAttributes.get("group_name"));
            attributes.set("list_name", parentAttributes.get("name"));
            if(null == attributes.get("type_cd")){
               attributes.set("type_cd", parentAttributes.get("type_cd"));
            }
            /// 根据自己的code号与父节点的code号有规律的结合生成自己的code号
            String code = attributes.get("type_cd") + "|" + parentAttributes.get("new_code") + "|" + attributes.get("name");
            attributes.set("code", code);
            /// 同步该节点
            syTranslateDi.doSyncTranslate(_sessionConsole.makeLogic(), attributes);
            if(null != attributes.get("zh_content")){
               attributes.set("translate_code", attributes.get("code"));
               attributes.set("language_name", "SC");
               attributes.set("content", attributes.get("zh_content"));
               syTranslateDi.doSyncContent(_sessionConsole.makeLogic(), attributes);
            }
            if(null != attributes.get("ja_content")){
               attributes.set("translate_code", attributes.get("code"));
               attributes.set("language_name", "JA");
               attributes.set("content", attributes.get("ja_content"));
               syTranslateDi.doSyncContent(_sessionConsole.makeLogic(), attributes);
            }
         }
         /// 判断是否有子节点，如果有则遍历子节点
         if(xds.hasChild()){
            searchThreeChild(xds, attributes, syTranslateDi, syPropertyDi);
         }
      }
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventAction#sort(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.event.FEventPage)
    */
   @Override
   public String sort(IWebContext context,
                      FTranslatePage page){
      return sort(_translateConsole, context, page, IPublicPage.XOBJECT_SORT);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventAction#syncEvent(org.mo.web.protocol.context.IWebContext, org.mo.logic.data.ILgEventGroupDi, org.mo.logic.data.ILgEventTypeDi, org.mo.logic.data.ILgEventTypeConfigDi, org.mo.logic.data.ILgEventGroupConfigDi, org.mo.logic.data.ISyPropertyDi, org.mo.jfa.face.logic.event.FEventPage)
    */
   @Override
   public String syncTranslate(IWebContext context,
                               ISyTranslateDi syTranslateDi,
                               ISyPropertyDi syPropertyDi,
                               FTranslatePage page){
      /// 获得当前节点，并同步当前节点
      String name = page.getSelectCollection();
      IXmlObject xds = _translateConsole.find(name);
      if(RBoolean.parse(xds.innerGet("is_valid"))){
         IAttributes attributes = xds.toSimpleAttributes();
         syTranslateDi.doSyncGroup(_sessionConsole.makeLogic(), attributes);
         /// 判断是否有子节点，如果有则遍历所以子节点并同步子节点
         if(xds.hasChild()){
            searchOneChild(xds, attributes, syTranslateDi, syPropertyDi);
         }
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventAction#update(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.event.FEventPage)
    */
   @Override
   public String update(IWebContext context,
                        FTranslatePage page){
      return update(_translateConsole, context, page, IPublicPage.XOBJECT_FORM);
   }
}
