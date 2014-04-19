package org.mo.jfa.face.design.webform;

import org.mo.com.xml.IXmlObject;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectPage;

public class FWebFormPage
      extends FAbsXmlObjectPage<IXmlObject>
{

   private static final long serialVersionUID = 1L;

   private String _sqlSource;

   public String getSqlSource(){
      return _sqlSource;
   }

   public void setSqlSource(String sqlSource){
      _sqlSource = sqlSource;
   }

}
