package org.mo.data.dataset;

import org.mo.com.collections.FDataset;
import org.mo.com.lang.FString;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.IAttributes;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.IXmlObject;
import org.mo.eng.data.common.FSqlSearchFields;
import org.mo.eng.store.IXmlConfigConsole;

public interface IDatasetConsole
      extends
         IXmlConfigConsole<IXmlObject>
{
   void build(FDatasetBuilderArgs builderArgs);

   void build(String packageName,
              FString lines,
              EDatasetSourceType type);

   FXmlNode buildConfig(IXmlObject xdataset);

   //void buildHelp(FDatasetBuilderArgs builderArgs);
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

   void dsDelete(String name,
                 IAttributes row);

   FDataset dsFetch(String name,
                    FStrings fields,
                    FSqlSearchFields searchs,
                    int page,
                    int pageSize);

   void dsInsert(String name,
                 IAttributes row);

   void dsUpdate(String name,
                 IAttributes row);

   String getLogicUnit();

   void rebuild();

   /**
    * <T>表同步处理</T>
    */
   void syncTable(IXmlObject xdataset);

   void tableForeignKeyDisableAll();

   void tableForeignKeyEnableAll();
}
