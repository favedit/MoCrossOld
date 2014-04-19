package org.mo.jfa.core.service;

import org.mo.com.xml.FXmlNode;
import org.mo.web.protocol.context.IWebContext;

public interface ITreeService
{

   public void process(IWebContext context,
                       FXmlNode input,
                       FXmlNode output);

}
