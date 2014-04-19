package org.mo.jfa.face.system.deploy;

import org.mo.com.xml.IXmlObject;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectPage;

public class FDeployPage
      extends FAbsXmlObjectPage<IXmlObject>
{

   private static final long serialVersionUID = 1L;

   private String _formName;

   @Override
   public String getFormName(){
      return _formName;
   }

   @Override
   public void setFormName(String formName){
      _formName = formName;
   }

}
