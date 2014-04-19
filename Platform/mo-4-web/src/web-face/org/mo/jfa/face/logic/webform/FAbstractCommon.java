package org.mo.jfa.face.logic.webform;

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

public class FAbstractCommon
{

   @ALink
   protected IValidatorConsole _validatorConsole;

   @ALink
   protected IWebFormConsole _webformConsole;

   protected boolean _envEnable = true;

   public void disableEnvOnce(){
      _envEnable = false;
   }

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

   public void setEnvironment(IWebContext context,
                              FWebFormPage page){
      if(!_envEnable){
         _envEnable = true;
         return;
      }
      String action = context.parameter("do");
      String formName = context.parameter("form_name");
      String formParent = context.parameter("form_parent");
      String formSearch = context.parameter("form_search");
      String formValue = context.parameter("form_value");
      String history = context.parameter("hs");
      // 设置数据容器
      page.setAction(action);
      page.setFormName(formName);
      page.setFormParent(formParent);
      page.setFormSearch(formSearch);
      page.setFormValue(formValue);
      page.setHistory(history);
      // 保存历史访问
      FWebFormInfos formInfos = page.formInfos();
      if("top".equals(history)){
         formInfos.reset();
      }
      FWebFormInfo formInfo = formInfos.findNext();
      page.storeInfo(formInfo);
      System.out.println(page.formInfos().dump());
      // 保存环境信息
      page.setEnv("form_name", formName);
   }
}
