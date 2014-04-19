/*
 * @(#)IWebService.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.core.service.servlet;

import org.mo.com.xml.FXmlNode;
import org.mo.web.protocol.context.IWebContext;

/**
 * <p>网络服务接口</p>
 * 
 * @author ALEX
 */
public interface IWebService
{

   public void process(IWebContext context,
                       FXmlNode input,
                       FXmlNode output);

}
