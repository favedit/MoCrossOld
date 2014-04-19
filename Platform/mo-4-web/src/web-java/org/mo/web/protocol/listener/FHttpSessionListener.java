/*
 * @(#)FHttpSessionListener.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.web.protocol.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;

/**
 * <p>
 * Http Session Listener
 * </p>
 * 
 * @author FEDT
 * @version 1.00 - 2005/02/18
 */
public class FHttpSessionListener
      implements
         HttpSessionListener
{

   private static int _count = 0;

   public static long sessionCount(){
      return _count;
   }

   private ILogger _logger = RLogger.find(FHttpSessionListener.class);

   public void sessionCreated(HttpSessionEvent event){
      _count++;
      _logger.debug(this, "sessionCreated", "Session created({0}): {1}", _count, event.getSession().getId());
   }

   public void sessionDestroyed(HttpSessionEvent event){
      _count++;
      _logger.debug(this, "sessionDestroyed", "Session destroyed({0}): {1}", _count, event.getSession().getId());
   }
}
