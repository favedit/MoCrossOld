package org.mo.jfa.face.logic.webform;

import org.mo.logic.session.ISqlSessionContext;
import org.mo.web.core.action.AWebLogin;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public interface IWebDatasetService
{

   @AWebLogin
   void chart(IWebContext context,
              ISqlSessionContext sqlContext,
              IWebInput input,
              IWebOutput output);

   void complete(IWebContext context,
                 ISqlSessionContext sqlContext,
                 IWebInput input,
                 IWebOutput output);

   /**
    * <T>获取数据集信息服务。</T> 
    * 
    * @param context 页面上下文信息
    * @param sqlContext 数据库上下文信息
    * @param input 上传节点
    * @param output 输出节点
    */
   @AWebLogin
   void fetch(IWebContext context,
              ISqlSessionContext sqlContext,
              IWebInput input,
              IWebOutput output);

   @AWebLogin
   void lov(IWebContext context,
            ISqlSessionContext sqlContext,
            IWebInput input,
            IWebOutput output);

   @AWebLogin
   void prepare(IWebContext context,
                ISqlSessionContext sqlContext,
                IWebInput input,
                IWebOutput output);

   @AWebLogin
   void scalar(IWebContext context,
               ISqlSessionContext sqlContext,
               IWebInput input,
               IWebOutput output);

   @AWebLogin
   void tableList(IWebContext context,
                  ISqlSessionContext sqlContext,
                  IWebInput input,
                  IWebOutput output);

   @AWebLogin
   void update(IWebContext context,
               ISqlSessionContext sqlContext,
               IWebInput input,
               IWebOutput output);

}
