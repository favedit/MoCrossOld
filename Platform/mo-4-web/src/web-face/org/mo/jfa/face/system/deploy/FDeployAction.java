package org.mo.jfa.face.system.deploy;

import org.mo.com.lang.RBoolean;
import org.mo.com.lang.REnum;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectAction;
import org.mo.jfa.face.apl.page.IPublicPage;
import org.mo.logic.deploy.EDeploy;
import org.mo.logic.deploy.EDeploySource;
import org.mo.logic.deploy.IDeployConsole;
import org.mo.logic.deploy.common.XDeploy;
import org.mo.web.protocol.context.IWebContext;

public class FDeployAction
      extends FAbsXmlObjectAction<IXmlObject>
      implements
         IDeployAction
{

   // 日志
   private final ILogger _logger = RLogger.find(FDeployAction.class);

   @ALink
   protected IDeployConsole _deployConsole;

   @Override
   public String catalog(IWebContext context,
                         FDeployPage page){
      return "Catalog";
   }

   @Override
   public String construct(IWebContext context,
                           FDeployPage page){
      // TODO Auto-generated method stub
      return null;
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventAction#delete(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.event.FEventPage)
    */
   @Override
   public String delete(IWebContext context,
                        FDeployPage page){
      return delete(_deployConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String execute(IWebContext context,
                         FDeployPage page){
      IXmlObject xds = _deployConsole.find(page.getSelectCollection());
      String component = context.parameter(PTY_SEL_COMPONENT);
      IXmlObject xdeploy = xds.search(component);
      EDeploy eDeploy = REnum.parse(EDeploy.class, xdeploy.innerGet("deployType"));
      try{

         if(EDeploy.Install == eDeploy){
            for(IXmlObject xml : xdeploy.children()){
               String isValid = xml.innerGet(PTY_IS_VALID);
               if(RBoolean.parse(isValid)){
                  EDeploySource processType = REnum.parse(EDeploySource.class, xml.innerGet("processType"));
                  _deployConsole.installAll(processType);
               }
            }
         }else{
            for(IXmlObject xml : xdeploy.children()){
               String isValid = xml.innerGet(PTY_IS_VALID);
               if(RBoolean.parse(isValid)){
                  EDeploySource processType = REnum.parse(EDeploySource.class, xml.innerGet("processType"));
                  _deployConsole.uninstallAll(processType);
               }
            }
         }
      }catch(Exception e){
         _logger.error(this, "FDeployAction execute()", e);
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   @Override
   public String executeProcess(IWebContext context,
                                FDeployPage page){
      IXmlObject xds = _deployConsole.find(page.getSelectCollection());
      String component = context.parameter(PTY_SEL_COMPONENT);
      IXmlObject xprocess = xds.search(component);
      String isValid = xprocess.innerGet(PTY_IS_VALID);
      XDeploy xdeploy = (XDeploy)xds.searchParent(component);
      EDeploySource processType = REnum.parse(EDeploySource.class, xprocess.innerGet("processType"));
      EDeploy eDeploy = REnum.parse(EDeploy.class, xdeploy.getDeploytype());
      try{
         if(RBoolean.parse(isValid)){

            if(EDeploy.Install == eDeploy){
               _deployConsole.install(processType);
            }else{
               _deployConsole.uninstall(processType);
            }
         }
      }catch(Exception e){
         _logger.error(this, "FDeployAction executeProcess()", e);
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventAction#insert(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.event.FEventPage)
    */
   @Override
   public String insert(IWebContext context,
                        FDeployPage page){
      return insert(_deployConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   /* (non-Javadoc)
   * @see org.mo.jfa.face.logic.event.IEventAction#list(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.event.FEventPage)
   */
   @Override
   public String list(IWebContext context,
                      FDeployPage page){
      return list(_deployConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String show(IWebContext context,
                      FDeployPage page){
      String formName = context.parameter("form_name");
      page.setFormName(formName);
      return "Form";
   }

   /* (non-Javadoc)
   * @see org.mo.jfa.face.logic.event.IEventAction#sort(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.event.FEventPage)
   */
   @Override
   public String sort(IWebContext context,
                      FDeployPage page){
      return sort(_deployConsole, context, page, IPublicPage.XOBJECT_SORT);
   }

   /* (non-Javadoc)
   * @see org.mo.jfa.face.logic.event.IEventAction#update(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.event.FEventPage)
   */
   @Override
   public String update(IWebContext context,
                        FDeployPage page){
      return update(_deployConsole, context, page, IPublicPage.XOBJECT_FORM);
   }
}
