package org.mo.game.editor.core.processor;

import org.mo.com.lang.FString;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlNodes;
import org.mo.com.xml.IXmlObject;
import org.mo.eng.store.IXmlConfigConsole;
import org.mo.game.editor.core.processor.common.XProject;

public interface IProcessorConsole
      extends
         IXmlConfigConsole<XProject>{

   /**
    * <T>排序节点集</T>
    * 
    */
   void ascOrderNodes(FXmlNodes nodes,
                      String... attrNames);

   void buildAllSource();

   FXmlNode buildConfig(IXmlObject xentityGroup,
                        EXmlConfig type);

   FXmlNode buildConfig(String name);

   FString buildSource(FXmlNode entityGroup,
                       String buildType);

   FString buildSource(IXmlObject xentityGroup,
                       String buildType);

   String buildStorePath(String fileName);

   String sourcePath();
}
