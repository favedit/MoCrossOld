/*
 * @(#)FEnvironmentConsole.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.eng.config;

import org.mo.com.lang.FObjects;
import org.mo.com.xml.IXmlObject;
import org.mo.eng.store.FXmlConfigConsole;

/**
 * 全局环境配置控制台
 * 
 * @author maocy
 */
public class FConfigConsole
      extends FXmlConfigConsole<IXmlObject>
      implements
         IConfigConsole
{
   @Override
   protected FObjects<IXmlObject> createCollection(){
      return new FObjects<IXmlObject>(IXmlObject.class);
   }
}
