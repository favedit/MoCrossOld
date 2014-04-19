package org.mo.jfa.face.logic.webform;

import org.mo.eng.data.common.ISqlContext;
import org.mo.logic.session.ISqlSessionContext;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public interface IWebFormService
{

   void codeList(IWebContext context,
                 ISqlSessionContext sqlContext,
                 IWebInput input,
                 IWebOutput output);

   void loadDefine(IWebContext context,
                   ISqlContext sqlContext,
                   IWebInput input,
                   IWebOutput output);

   void process(IWebContext context,
                ISqlSessionContext sqlContext,
                IWebInput input,
                IWebOutput output);

}
