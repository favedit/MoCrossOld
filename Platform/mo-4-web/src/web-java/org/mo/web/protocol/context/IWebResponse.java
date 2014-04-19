package org.mo.web.protocol.context;

import org.mo.com.lang.FAttributes;

public interface IWebResponse
{

   String head(String name);

   FAttributes heads();

   void setHead(String name,
                String value);

}
