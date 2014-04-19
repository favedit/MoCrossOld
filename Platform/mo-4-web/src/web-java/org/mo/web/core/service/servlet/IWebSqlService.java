/*
 * @(#)IWebSqlService.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.core.service.servlet;

import org.mo.com.xml.FXmlNode;
import org.mo.eng.data.common.ISqlContext;
import org.mo.web.protocol.context.IWebContext;

/**
 * <p>网络服务接口</p>
 * 
 * @author ALEX
 */
public interface IWebSqlService
{

   public void process(IWebContext web,
                       ISqlContext sql,
                       FXmlNode input,
                       FXmlNode output);

}
