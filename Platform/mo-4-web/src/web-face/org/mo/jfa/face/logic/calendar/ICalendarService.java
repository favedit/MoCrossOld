package org.mo.jfa.face.logic.calendar;

import org.mo.logic.session.ISqlSessionContext;
import org.mo.web.core.action.AWebLogin;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

@AWebLogin
public interface ICalendarService
{

   /**
    * <T>获取日历的信息。</T> 
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

}
