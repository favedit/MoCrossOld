/*
 * @(#)FTemplatePage.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.jfa.face.system.template;

import org.mo.com.xml.IXmlObject;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectPage;

/**
 * <T>设置模板页面参数</T>
 * <P>对模板代码设置对应的get和set方法</P>
 * @author YANGH
 * @version 1.00 - 2008/12/07
 */
public class FTemplatePage
      extends FAbsXmlObjectPage<IXmlObject>
{

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   /// 定义模板代码
   private String _source;

   /**
    * <T>得到source的内容</T>
    * 
    * @return source
    */
   public String getSource(){
      return _source;
   }

   /**
    * <T>设置source的值</T>
    * 
    * @param source source的内容
    */
   public void setSource(String source){
      _source = source;
   }

}
