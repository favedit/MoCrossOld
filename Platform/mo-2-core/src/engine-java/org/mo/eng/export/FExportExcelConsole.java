/*
 * @(#)FTemplateConsole.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.eng.export;

import org.mo.com.io.RFile;
import org.mo.com.lang.FObjects;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.AProperty;
import org.mo.eng.store.FXmlConfigConsole;

/**
 * <p>导出Excel控制台</p>
 * 
 * @author YJHUA
 */
public class FExportExcelConsole
      extends FXmlConfigConsole<IXmlObject>
      implements
         IExportExcelConsole
{

   @AProperty
   protected String _storePath;

   @AProperty
   protected String _tempPath;

   @Override
   protected FObjects<IXmlObject> createCollection(){
      return new FObjects<IXmlObject>(IXmlObject.class);
   }

   @Override
   public String getPath(){
      return _storePath;
   }

   @Override
   public String makeStorePath(String name){
      return RFile.makeFilename(_storePath, name);
   }

   @Override
   public String makeTempPath(String name){
      return RFile.makeFilename(_tempPath, name);
   }
}
