package org.mo.game.editor.core.dataset;

import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.IXmlObject;
import org.mo.eng.store.IXmlConfigConsole;

public interface IDatasetConsole
      extends
         IXmlConfigConsole<IXmlObject>{

   void build(FDatasetBuilderArgs builderArgs);

   FXmlNode buildConfig(IXmlObject xdataset);

   FXmlNode buildConfig(IXmlObject xdataset,
                        EXmlConfig type);

   FXmlNode buildConfig(String name);

   FXmlNode buildConfig(String name,
                        EXmlConfig type);

   void dataDelete(IXmlObject xdataset);

   void dataDelete(IXmlObject xdataset,
                   boolean disableKey);

   void dataRestore(IXmlObject xdataset);

   void dataRestore(IXmlObject xdataset,
                    boolean disableKey);

   void dataRestoreAll(IXmlObject xdataset);

   void dataStore(IXmlObject xdataset);

   String getLogicUnit();

   void rebuild();

   String sourceServerPath();
}
