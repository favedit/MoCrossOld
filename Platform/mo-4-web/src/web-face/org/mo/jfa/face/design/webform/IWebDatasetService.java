package org.mo.jfa.face.design.webform;

import org.mo.eng.data.common.ISqlContext;
import org.mo.logic.session.ISqlSessionContext;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public interface IWebDatasetService
{

   /**
    * <T>自动补完内容服务。</T>
    * 
    * @param context
    * @param sqlContext
    * @param input
    * @param output
    */
   void complete(IWebContext context,
                 ISqlSessionContext sqlContext,
                 IWebInput input,
                 IWebOutput output);

   void delete(IWebContext context,
               ISqlContext sqlContext,
               IWebInput input,
               IWebOutput output);

   void fetch(IWebContext context,
              ISqlContext sqlContext,
              IWebInput input,
              IWebOutput output);

   void insert(IWebContext context,
               ISqlContext sqlContext,
               IWebInput input,
               IWebOutput output);

   void search(IWebContext context,
               ISqlContext sqlContext,
               IWebInput input,
               IWebOutput output);

   void update(IWebContext context,
               ISqlSessionContext sqlContext,
               IWebInput input,
               IWebOutput output);

}
