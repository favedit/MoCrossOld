/*
 * @(#)FTemplateConsole.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.eng.template;

import org.mo.com.lang.FObjects;
import org.mo.com.xml.IXmlObject;
import org.mo.eng.store.FXmlConfigConsole;

/**
 * <p>模板控制台</p>
 * 
 * @author maocy
 */
public class FTemplateConfigConsole
      extends FXmlConfigConsole<IXmlObject>
      implements
         ITemplateConfigConsole
{
   @Override
   protected FObjects<IXmlObject> createCollection(){
      return new FObjects<IXmlObject>(IXmlObject.class);
   }
}
