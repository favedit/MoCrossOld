package org.mo.jfa.face.design.property;

import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public interface IPropertyService
{

   public void listFields(IWebContext context,
                          IWebInput input,
                          IWebOutput output);

   public void listForms(IWebContext context,
                         IWebInput input,
                         IWebOutput output);

}
