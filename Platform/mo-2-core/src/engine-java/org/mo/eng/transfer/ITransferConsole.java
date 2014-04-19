package org.mo.eng.transfer;

import org.mo.eng.store.IXmlConfigConsole;
import org.mo.eng.store.IXmlConfigConvert;
import org.mo.eng.transfer.common.XCsvImport;

public interface ITransferConsole
      extends
         IXmlConfigConsole<XCsvImport>,
         IXmlConfigConvert
{
   FCsvImport findCsvImport(String name,
                            String columnLabel[],
                            String columnName[]);

   String makeStorePath(String name);

   String makeUploadPath(String name);
}
