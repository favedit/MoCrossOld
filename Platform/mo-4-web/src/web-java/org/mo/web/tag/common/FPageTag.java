package org.mo.web.tag.common;

import org.mo.com.lang.FError;
import org.mo.com.lang.RString;

public class FPageTag
      extends FAbstractTag
{

   private String m_sOldId = null;

   // 指定集合对象
   private String m_sId = null;

   /**
    * <p>获得集合对象</p>
    * <p>create date:2005/02/18</p>
    *
    * @retrun 集合对象
    */
   public String id(){
      return m_sId;
   }

   public int onEnd() throws FError{
      if(!RString.isEmpty(m_sId)){
         _context.setAttribute(ITagEnvironment.MULTI_TEXT_ID, m_sOldId);
      }
      return EVAL_PAGE;
   }

   /**
    * <p>开始标签的逻辑</p>
    * <p>create date:2005/02/18</p>
    *
    * @param oContext 环境对象
    * @return 逻辑执行后的状态
    * @exception FError 逻辑例外
    */
   public int onStart(){
      if(!RString.isEmpty(m_sId)){
         m_sOldId = (String)_context.attribute(ITagEnvironment.MULTI_TEXT_ID);
         _context.setAttribute(ITagEnvironment.MULTI_TEXT_ID, m_sId);
      }
      return EVAL_BODY_INCLUDE;
   }

   /**
    * <p>设置集合对象</p>
    * <p>create date:2005/02/18</p>
    *
    * @param sValue 集合对象
    */
   public void setId(String sValue){
      m_sId = sValue;
   }
}
