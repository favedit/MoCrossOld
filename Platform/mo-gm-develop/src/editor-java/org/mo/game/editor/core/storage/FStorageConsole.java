package org.mo.game.editor.core.storage;

import org.mo.com.io.RFile;
import org.mo.com.lang.FObjects;
import org.mo.com.lang.FString;
import org.mo.com.lang.RBoolean;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlNodes;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.core.aop.face.AProperty;
import org.mo.eng.store.FXmlConfigConsole;
import org.mo.eng.template.ITemplateConsole;
import org.mo.game.editor.core.storage.common.XGroup;
import org.mo.game.editor.core.storage.common.XItem;
import org.mo.game.editor.core.storage.common.XStorage;

public class FStorageConsole
      extends FXmlConfigConsole<XGroup>
      implements
         IStorageConsole{

   @ALink
   private ITemplateConsole _templateConsole;

   @AProperty
   private String _sourcepath;

   @Override
   public void ascOrderNodes(FXmlNodes nodes,
                             String... attrNames){
      nodes.sortByAttribute(true, attrNames);
   }

   @Override
   public FXmlNode buildConfig(IXmlObject xgroup,
                               EXmlConfig type){
      if(null != xgroup){
         FXmlNode config = xgroup.toNode(type);
         if(RBoolean.parse(config.get(XGroup.PTY_IS_VALID))){
            for(IXmlObject xstorage : xgroup.children()){
               if(RBoolean.parse(xstorage.innerGet(XStorage.PTY_IS_VALID))){
                  FXmlNode storageConfig = xstorage.toNode(type);
                  for(IXmlObject xitem : xstorage.children()){
                     if(RBoolean.parse(xitem.innerGet(XItem.PTY_IS_VALID))){
                        FXmlNode itemConfig = xitem.toNode(type);
                        storageConfig.push(itemConfig);
                     }
                  }
                  //setMessageFullLabel(storageConfig);
                  config.push(storageConfig);
               }
            }
            ascOrderNodes(config.nodes(), "full_name");
            return config;
         }
      }
      return null;
   }

   @Override
   public FXmlNode buildConfig(String name){
      IXmlObject xstorage = find(name);
      return buildConfig(xstorage, EXmlConfig.Simple);
   }

   @Override
   public FString buildSource(FXmlNode entityGroup,
                              String buildType){
      FStorageSourceBuilder sourceBuilder = new FStorageSourceBuilder(_templateConsole);
      FString source = null;
      if("head".equalsIgnoreCase(buildType)){
         source = sourceBuilder.makeCHead(entityGroup);
      }else if("body".equalsIgnoreCase(buildType)){
         source = sourceBuilder.makeCBody(entityGroup);
      }
      return source;
   }

   @Override
   public FString buildSource(IXmlObject xgroup,
                              String buildType){
      FXmlNode config = buildConfig(xgroup, EXmlConfig.Simple);
      FStorageSourceBuilder sourceBuilder = new FStorageSourceBuilder(_templateConsole);
      FString source = null;
      if("head".equalsIgnoreCase(buildType)){
         source = sourceBuilder.makeCHead(config);
      }else if("body".equalsIgnoreCase(buildType)){
         source = sourceBuilder.makeCBody(config);
      }
      return source;
   }

   @Override
   public FString buildSource(String source,
                              String templatePath,
                              String define){
      FStorageSourceBuilder builder = new FStorageSourceBuilder(_templateConsole);
      FString fsource = builder.makecSource(source, templatePath, define);
      return fsource;
   }

   @Override
   public String buildStorePath(String fileName){
      return RFile.makeFilename(_sourcepath, fileName);
   }

   @Override
   public FObjects<XGroup> createCollection(){
      return new FObjects<XGroup>(XGroup.class);
   }

   protected void setMessageFullLabel(FXmlNode config){
      if(config.isName("Message")){
         String name = config.get("name");
         String value = config.get("code_value");
         if(null != value){
            if(value.startsWith("0x")){
               value = value.substring(2);
            }
         }else{
            value = "";
         }
         config.set("full_name", value + "-" + name);
      }
   }

   public String sourcePath(){
      return RFile.format(_sourcepath);
   }
}
