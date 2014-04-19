package org.mo.jfa.face.logic.webform;

import org.mo.com.data.ESqlDataDirection;
import org.mo.com.data.ESqlDataType;
import org.mo.com.data.FSqlProcedure;
import org.mo.com.io.RFile;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.REnum;
import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.face.ALink;
import org.mo.logic.session.ISqlSessionContext;
import org.mo.logic.store.EStorePlace;
import org.mo.logic.store.IStoreConsole;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public class FWebStoreService
      implements
         IWebStoreService
{

   @ALink
   IStoreConsole _storeConsole;

   @SuppressWarnings("unused")
   @Override
   public void fetch(IWebContext context,
                     ISqlSessionContext sqlContext,
                     IWebInput input,
                     IWebOutput output){
      FXmlNode config = input.config();
      FXmlNode pl = config.node(0);
      String placeStr = pl.get("place");
      EStorePlace place = REnum.parse(EStorePlace.class, placeStr);
      int group = pl.getInt("group");
      String name = pl.get("name");
      String fileName = pl.get("fileName");
      //String filePath = _storeConsole.store(place, group, name, fileName);
   }

   @SuppressWarnings("unused")
   @Override
   public void remove(IWebContext context,
                      ISqlSessionContext sqlContext,
                      IWebInput input,
                      IWebOutput output){
      FXmlNode config = input.config();
      FXmlNode pl = config.node(0);
      EStorePlace place = EStorePlace.UserResource;
      String name = pl.get("path");
      String type = pl.get("FILE_TYPE");
      String file = _storeConsole.userPath() + "/" + context.session().user().userId() + "/" + name + "." + type;
      System.out.println("===============================" + file);
      RFile.delete(file);
      FAttributes params = new FAttributes(pl.attributes());
      String size = params.get("DATA_SIZE");
      //params.set("DATA_SIZE", RString.removeString(size, "KB"));
      String methodName = "Do_Delete_File";
      FSqlProcedure procedure = new FSqlProcedure(methodName);
      procedure.setLogicName("SY_ATTACHMENT_DI");
      procedure.createParameter("logic_", null, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("params_", params.pack(), ESqlDataType.String, ESqlDataDirection.InOut);
      sqlContext.activeConnection().execute(procedure);
   }
}
