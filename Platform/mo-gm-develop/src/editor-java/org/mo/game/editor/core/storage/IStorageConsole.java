package org.mo.game.editor.core.storage;

import org.mo.com.lang.FString;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlNodes;
import org.mo.com.xml.IXmlObject;
import org.mo.eng.store.IXmlConfigConsole;
import org.mo.game.editor.core.storage.common.XGroup;

public interface IStorageConsole
      extends
         IXmlConfigConsole<XGroup>{

   /**
    * <T>排序节点集</T>
    * 
    */
   void ascOrderNodes(FXmlNodes nodes,
                      String... attrNames);

   FXmlNode buildConfig(IXmlObject xentityGroup,
                        EXmlConfig type);

   FXmlNode buildConfig(String name);

   FString buildSource(FXmlNode entityGroup,
                       String buildType);

   FString buildSource(IXmlObject xentityGroup,
                       String buildType);

   FString buildSource(String source,
                       String templatePath,
                       String define);

   String buildStorePath(String fileName);

   String sourcePath();
}
