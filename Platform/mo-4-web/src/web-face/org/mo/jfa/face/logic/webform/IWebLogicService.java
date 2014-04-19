package org.mo.jfa.face.logic.webform;

import org.mo.logic.session.ISqlSessionContext;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public interface IWebLogicService
{

   void userPicker(IWebContext context,
                   ISqlSessionContext sqlContext,
                   IWebInput input,
                   IWebOutput output);
}
