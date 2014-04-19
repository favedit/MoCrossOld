package org.mo.com.message;

import org.mo.com.xml.FXmlNode;

public interface IMessage
{
   FXmlNode convertToNode();

   String description();

   void loadConfig(FXmlNode config);

   String message();

   String name();

   Object[] params();

   void saveConfig(FXmlNode config);

   void setDescription(String sValue);

   String type();
}
