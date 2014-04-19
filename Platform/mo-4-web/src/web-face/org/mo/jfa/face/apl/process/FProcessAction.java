/*
 * @(#)EndProcessAction.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.jfa.face.apl.process;

import java.io.UnsupportedEncodingException;
import org.mo.com.data.ESqlDataDirection;
import org.mo.com.data.ESqlDataType;
import org.mo.com.data.FSqlProcedure;
import org.mo.com.lang.RString;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.eng.data.common.ISqlContext;
import org.mo.jfa.face.apl.page.IPublicPage;
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
         IProcessAction
{

   @ALink
   protected IWebFormDatasetConsole _datasetConsole;

   @ALink
   protected IWebFormConsole _formConsole;

   @Override
   public String construct(IWebContext context,
                           ISqlContext sqlContext){
      // 关联数据库线程
      FWebFormDatasetArgs args = new FWebFormDatasetArgs(context, sqlContext);
      args.setSessionConnectId(context.session().connectId());
      _datasetConsole.sessionLink(args);
      // 执行指定处理
      String formName = context.parameter("form_name");
      String control = context.parameter("control");
      String formPack = context.parameter("form_pack");
      try{
         formPack = new String(formPack.getBytes("iso-8859-1"), "utf-8");
      }catch(UnsupportedEncodingException e){
         e.printStackTrace();
      }
      IXmlObject xds = _formConsole.find(formName);
      IXmlObject xControl = xds.search("name", control);
      String executes = xControl.innerGet("execute");
      if(!RString.isEmpty(executes)){
         String[] execute = RString.split(executes, "\n");
         for(int i = 0; i < execute.length; i++){
            String pack = RString.left(execute[i], ".");
            String executeRight = RString.right(execute[i], ".");
            String produrce = RString.left(executeRight, "(");
            excute(sqlContext, pack, produrce, formPack);
         }
      }
      return IPublicPage.PROCESS_SUCCESS;
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
