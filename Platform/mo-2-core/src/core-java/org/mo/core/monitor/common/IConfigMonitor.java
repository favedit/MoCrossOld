package org.mo.core.monitor.common;

import org.mo.com.xml.FXmlNode;

public interface IConfigMonitor
      extends
         IMonitor
{
   void construct(FXmlNode config);
}
