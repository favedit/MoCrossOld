package org.mo.web.core.servlet.common;

public interface IWebServletResponse
{

   void addHeader(String name,
                  String value);

   void setCharacterEncoding(String arg0);

   void setContentLength(int length);

   void setContentType(String type);

   void write(byte[] data);
}
