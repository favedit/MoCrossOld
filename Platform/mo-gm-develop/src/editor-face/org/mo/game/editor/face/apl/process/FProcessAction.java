/*
 * @(#)EndProcessAction.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.game.editor.face.apl.process;

import org.mo.com.data.ESqlDataDirection;
import org.mo.com.data.ESqlDataType;
import org.mo.com.data.FSqlProcedure;
import org.mo.com.lang.RString;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.eng.data.common.ISqlContext;
import org.mo.game.editor.face.apl.page.IPublicPage;
import org.mo.logic.session.ISqlSessionContext;
import org.mo.web.core.webform.FWebFormDatasetArgs;
import org.mo.web.core.webform.IWebFormConsole;
import org.mo.web.core.webform.IWebFormDatasetConsole;
import org.mo.web.protocol.context.IWebContext;

/**
 * <p>处理结束页面</p>
 * 
 * @author ALEX
 */
public class FProcessAction
      implements
         IProcessAction{

   @ALink
   protected IWebFormDatasetConsole _datasetConsole;

   @ALink
   protected IWebFormConsole _formConsole;

   @Override
   public String construct(IWebContext context,
                           ISqlSessionContext sqlContext){
      String formName = context.parameter("form_name");
      String control = context.parameter("control");
      String formPack = context.parameter("form_pack");
      if(RString.isNotEmpty(formPack)){
         try{
            // TODO: 文字编码问题
            formPack = new String(formPack.getBytes("iso-8859-1"), "utf-8");
         }catch(Exception e){
            e.printStackTrace();
         }
      }
      // 执行PLSQL处理
      IXmlObject xds = _formConsole.find(formName);
      IXmlObject xControl = xds.search("name", control);
      String executeString = xControl.innerGet("execute");
      if(!RString.isEmpty(executeString)){
         // 连接用户线程
         FWebFormDatasetArgs args = new FWebFormDatasetArgs(context, sqlContext);
         args.setSessionConnectId(context.session().connectId());
         _datasetConsole.sessionLink(args);
         // 执行多个处理过程
         String[] executes = RString.split(executeString, '\n');
         for(int n = 0; n < executes.length; n++){
            String execute = RString.trim(executes[n]);
            if(RString.isNotEmpty(execute)){
               // 执行单个处理过程
               String pack = RString.left(execute, '.');
               String executeRight = RString.right(execute, '.');
               String produrce = RString.left(executeRight, '(');
               excute(sqlContext, pack, produrce, formPack);
            }
         }
         // 退出用户线程
         _datasetConsole.sessionUnlink(args);
      }
      return IPublicPage.DIALOG_PROCESS_SUCCESS;
   }

   protected boolean excute(ISqlContext sqlContext,
                            String pack,
                            String produrce,
                            String formPack){
      FSqlProcedure procedure = new FSqlProcedure(produrce);
      procedure.setLogicName(pack);
      procedure.createParameter("logic_", null, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("form_pack", formPack, ESqlDataType.String, ESqlDataDirection.InOut);
      sqlContext.activeConnection().execute(procedure);
      return true;
   }

}
