/*
 * @(#)FFormatConsole.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.eng.format;

import org.mo.com.io.RFile;
import org.mo.com.lang.FDictionary;
import org.mo.com.lang.REnum;
import org.mo.com.xml.FXmlDocument;
import org.mo.core.aop.face.AProperty;

public class FFormatConsole
      implements
         IFormatConsole
{
   private final FDictionary<ISyntaxMaker> _makers = new FDictionary<ISyntaxMaker>(ISyntaxMaker.class);

   @AProperty
   private String _workPath;

   @Override
   public ISyntaxMaker find(ESyntax type){
      String typeName = type.toString().toLowerCase();
      ISyntaxMaker maker = _makers.get(typeName);
      if(null == maker){
         String file = RFile.makeFilename(_workPath, typeName + ".xml");
         FXmlDocument xdoc = new FXmlDocument();
         xdoc.loadFile(file);
         // Maker
         if(ESyntax.Sql == type || ESyntax.PlSql == type){
            FSyntaxPlSqlMaker newMaker = new FSyntaxPlSqlMaker();
            newMaker.initialize(xdoc.root());
            _makers.set(typeName, newMaker);
            maker = newMaker;
         }else{
            FSyntaxMaker newMaker = new FSyntaxMaker();
            newMaker.initialize(xdoc.root());
            _makers.set(typeName, newMaker);
            maker = newMaker;
         }
      }
      return maker;
   }

   @Override
   public ISyntaxMaker find(String type){
      return find(REnum.parse(ESyntax.class, type));
   }
}
