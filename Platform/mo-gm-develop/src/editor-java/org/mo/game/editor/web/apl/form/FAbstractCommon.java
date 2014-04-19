package org.mo.game.editor.web.apl.form;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RString;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.eng.data.common.ISqlContext;
import org.mo.eng.validator.IValidatorConsole;
import org.mo.web.core.webform.IWebFormConsole;
import org.mo.web.protocol.context.IWebContext;

public class FAbstractCommon{

   public static final String PTY_HISTORY = "hs";

   public static final String PTY_HISTORY_ID = "hi";

   @ALink
   protected IValidatorConsole _validatorConsole;

   @ALink
   protected IWebFormConsole _webformConsole;

   public IXmlObject findControl(String webform,
                                 String webcontrol){
      IXmlObject xform = findForm(webform);
      if(null != xform){
         if(RString.isNotEmpty(webcontrol)){
            return xform.children().search(webcontrol);
         }
      }
      return null;
   }

   public FXmlNode findForm(ISqlContext sqlContext,
                            String formName){
      FXmlNode xform = null;
      if(RString.isNotEmpty(formName)){
         xform = _webformConsole.build(formName, EXmlConfig.Simple);
         if(null == xform){
            throw new FFatalError("Show form is null. (name={0})", formName);
         }
      }
      return xform;
   }

   public IXmlObject findForm(String formName){
      IXmlObject xform = null;
      if(RString.isNotEmpty(formName)){
         xform = _webformConsole.find(formName);
         if(null == xform){
            throw new FFatalError("Show form is null. (name={0})", formName);
         }
      }
      return xform;
   }

   /**
    * <T>设置环境数据</T>
    * 
    * @param context 环境对象
    * @param page 容器对象
    */
   public void setEnvironment(IWebContext context,
                              FAbstractWebFormPage page){
      // 设置数据容器 
      page.setAction(context.parameter("do"));
      page.setFormName(context.parameter("form_name"));
      page.setFormAction(context.parameter("form_action"));
      page.setFormSearch(context.parameter("form_search"));
      page.setFormOrder(context.parameter("form_order"));
      page.setFormValue(context.parameter("form_value"));
      page.setFormPack(context.parameter("form_pack"));
   }
}
