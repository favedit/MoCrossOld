package org.mo.jfa.face.system.environment;

import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.IXmlObject;
import org.mo.com.xml.RXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.eng.environment.IEnvironmentConsole;
import org.mo.eng.environment.common.XEnvironment;
import org.mo.eng.environment.common.XProperty;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectAction;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectPage;
import org.mo.jfa.face.apl.page.IPublicPage;
import org.mo.web.protocol.context.IWebContext;

public class FEnvironmentAction
      extends FAbsXmlObjectAction<XEnvironment>
      implements
         IEnvironmentAction
{

   public final String PAGE_CATALOG = "Catalog";

   public final String PAGE_LIST = "List";

   @ALink
   protected IEnvironmentConsole _envConsole;

   @Override
   public String catalog(IWebContext context,
                         FEnvironmentPage page){
      return PAGE_CATALOG;
   }

   @Override
   public String delete(IWebContext context,
                        FEnvironmentPage page){
      return delete(_envConsole, context, page, null);
   }

   @Override
   public String insert(IWebContext context,
                        FEnvironmentPage page){
      return insert(_envConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String list(IWebContext context,
                      FEnvironmentPage page){
      return list(_envConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String refresh(IWebContext context,
                         FEnvironmentPage page){
      _envConsole.reset();
      return IPublicPage.PROCESS_SUCCESS;
   }

   @Override
   protected void setFormValue(FAbsXmlObjectPage<XEnvironment> page,
                               IXmlObject xobject){
      // 如果是环境属性的时候，生成数据提示内容
      FXmlNode config = RXmlObject.convertToNode(xobject, EXmlConfig.Value);
      if(XProperty.isInstance(xobject)){
         config.set("data_hint", _envConsole.parse(config.get("data_value")));
      }
      page.setFormValue(config.xml());
   }

   @Override
   public String update(IWebContext context,
                        FEnvironmentPage page){
      return update(_envConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

}
