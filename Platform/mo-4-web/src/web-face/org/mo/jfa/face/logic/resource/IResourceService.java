package org.mo.jfa.face.logic.resource;

import org.mo.eng.data.common.ISqlContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public interface IResourceService
{

   void catalog(ISqlContext context,
                IWebInput input,
                IWebOutput output);

   void listNode(ISqlContext context,
                 IWebInput input,
                 IWebOutput output);

}
