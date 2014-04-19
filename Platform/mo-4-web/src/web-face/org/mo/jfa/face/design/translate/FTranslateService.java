/*
 * @(#)FTranslateService.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.jfa.face.design.translate;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RInteger;
import org.mo.com.lang.RString;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.eng.translate.ITranslateConsole;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectService;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

/**
 * <T>系统翻译树目录各种操作服务接口实例。</T>
 * <P>提供对系统翻译和系统翻译用树目录形式进行增删改各种操作的接口实例。</P>
 * 
 * @author YJHUA
 * @version 1.00 - 2008/12/017
 */
public class FTranslateService
      extends FAbsXmlObjectService<IXmlObject>
      implements
         ITranslateService
{

   // 系统翻译控制台
   @ALink
   protected ITranslateConsole _translateConsole;

   /* (non-Javadoc)
    * @see org.mo.jfa.face.design.translate.ITranslateService#catalog(org.mo.web.protocol.context.IWebContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   @Override
   public void catalog(IWebContext context,
                       IWebInput input,
                       IWebOutput output){
      catalog(_translateConsole, context, input, output);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.design.translate.ITranslateService#delete(org.mo.web.protocol.context.IWebContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   @Override
   public void delete(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      delete(_translateConsole, context, input, output);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.design.translate.ITranslateService#insert(org.mo.web.protocol.context.IWebContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   @Override
   public void insert(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      FXmlNode node = input.config().node("Data");
      // 检查更新者和创建者编号是否是数字字符串
      if(("Content".equals(node.get("_type"))) || ("Translate".equals(node.get("_type")))){
         String createUserCode = node.get("create_user_code");
         String updateUserCode = node.get("update_user_code");
         if(!RString.isEmpty(createUserCode)){
            if(!RInteger.isInteger(createUserCode)){
               throw new FFatalError("Create user code is not number. (create_user_code={0})", createUserCode);
            }
         }
         if(!RString.isEmpty(updateUserCode)){
            if(!RInteger.isInteger(updateUserCode)){
               throw new FFatalError("Update user code is not number. (update_user_code={0})", updateUserCode);
            }
         }
      }
      insert(_translateConsole, context, input, output);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.design.translate.ITranslateService#list(org.mo.web.protocol.context.IWebContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   @Override
   public void list(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      list(_translateConsole, context, input, output);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.design.translate.ITranslateService#sort(org.mo.web.protocol.context.IWebContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   @Override
   public void sort(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      sort(_translateConsole, context, input, output);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.design.translate.ITranslateService#update(org.mo.web.protocol.context.IWebContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   @Override
   public void update(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      FXmlNode node = input.config().node("Data");
      // 检查更新者和创建者编号是否是数字字符串
      if(("Content".equals(node.get("_type"))) || ("Translate".equals(node.get("_type")))){
         String createUserCode = node.get("create_user_code");
         String updateUserCode = node.get("update_user_code");
         if(!RString.isEmpty(createUserCode)){
            if(!RInteger.isInteger(createUserCode)){
               throw new FFatalError("Create user code is not number. (create_user_code={0})", createUserCode);
            }
         }
         if(!RString.isEmpty(updateUserCode)){
            if(!RInteger.isInteger(updateUserCode)){
               throw new FFatalError("Update user code is not number. (update_user_code={0})", updateUserCode);
            }
         }
      }
      update(_translateConsole, context, input, output);
   }
}
