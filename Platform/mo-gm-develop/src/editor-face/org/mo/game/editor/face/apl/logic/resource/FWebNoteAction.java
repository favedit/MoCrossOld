package org.mo.game.editor.face.apl.logic.resource;

import org.mo.com.lang.RUuid;
import org.mo.core.aop.face.ALink;
import org.mo.eng.data.common.ISqlContext;
import org.mo.web.core.webform.IWebFormDatasetConsole;
import org.mo.web.protocol.common.FWebUploadFile;
import org.mo.web.protocol.context.IWebContext;

public class FWebNoteAction
      extends FAbstractWebResource
      implements
         IWebNoteAction
{

   @ALink
   protected IWebFormDatasetConsole _formDatasetConsole;

   //private ILogger _logger = RLogger.find(FWebNoteAction.class);

   public final String PAGE_FORM = "Form";

   public final String PAGE_DIALOG = "Dialog";

   public final String ROW_STATUS = "_os";

   @Override
   public String construct(IWebContext context,
                           ISqlContext sqlContext,
                           FWebResourcePage page){
      //      String resourceId = page.getResourceId();
      //      FXmlPackParser packParser = new FXmlPackParser();
      //      FSqlFunction function = rsResourceDi.getModuleCatalogPack(resourceId, 0);
      //      FXmlNode moduleCatalogNode = packParser.parse(function.returnAsString());
      //      if(_logger.debugAble()){
      //         _logger.debug(this, "construct", "Get module catalog.\n{0}", moduleCatalogNode.xml());
      //      }
      //      page.setModuleCatalogNode(moduleCatalogNode);
      return "Note";
   }

   @Override
   public String upload(IWebContext context,
                        ISqlContext sqlContext,
                        FWebResourcePage page){
      return "StoreUpload";
   }

   @Override
   public String uploadSave(IWebContext context,
                            ISqlContext sqlContext,
                            FWebResourcePage page){
      if(context.hasFile()){
         for(FWebUploadFile file : context.files()){
            file.move("D:/" + RUuid.simpleUuid());
         }
      }
      return null;
   }
}
