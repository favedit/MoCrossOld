package org.mo.web.protocol.context;

public interface IPageStatus
{

   String parse(String status,
                String def);

   void setStatus(String status);

   String status();

}
