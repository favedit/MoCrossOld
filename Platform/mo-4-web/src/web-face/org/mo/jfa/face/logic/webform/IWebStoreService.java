package org.mo.jfa.face.logic.webform;

import org.mo.logic.session.ISqlSessionContext;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public interface IWebStoreService
{

   /********************************************************
    * <T>获取文件信息。</T>
    *******************************************************/
   void fetch(IWebContext context,
              ISqlSessionContext sqlContext,
              IWebInput input,
              IWebOutput output);

   /********************************************************
    * <T>删除文件。</T>
    *******************************************************/
   void remove(IWebContext context,
               ISqlSessionContext sqlContext,
               IWebInput input,
               IWebOutput output);

}
