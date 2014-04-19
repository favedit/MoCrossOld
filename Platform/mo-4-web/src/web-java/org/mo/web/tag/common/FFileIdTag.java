/*
 * Created on 2005/01/18
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package org.mo.web.tag.common;

import org.mo.com.lang.RString;

public class FFileIdTag
      extends FAbstractTag
{

   // 指定集合对象
   private String _name = null;

   //指定格式
   private String _value = null;

   /**
    * <p>获得集合对象</p>
    * <p>create date:2005/02/18</p>
    *
    * @retrun 集合对象
    */
   public String name(){
      return _name;
   }

   @Override
   public int onEnd(){
      return EVAL_PAGE;
   }

   @Override
   public int onStart(){
      if(!RString.isEmpty(_name) && !RString.isEmpty(_value)){
         _context.setAttribute(_name, _value);
      }
      return SKIP_BODY;
   }

   /**
    * <p>设置集合对象</p>
    * <p>create date:2005/02/18</p>
    *
    * @param sValue 集合对象
    */
   public void setName(String sValue){
      _name = sValue;
   }

   /**
    * <p>设置格式对象</p>
    * <p>create date:2005/02/18</p>
    *
    * @param sValue 格式对象
    */

   public void setValue(String sValue){
      _value = sValue;
   }

   /**
    * <p>获得格式对象</p>
    * <p>create date:2005/02/18</p>
    *
    * @retrun 格式对象
    */

   public String value(){
      return _value;
   }

}
