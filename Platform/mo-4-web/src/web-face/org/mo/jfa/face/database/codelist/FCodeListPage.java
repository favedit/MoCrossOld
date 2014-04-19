package org.mo.jfa.face.database.codelist;

import org.mo.com.lang.FString;
import org.mo.com.xml.IXmlObject;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectPage;

public class FCodeListPage
      extends FAbsXmlObjectPage<IXmlObject>
{

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   private FString _source;

   public FString getSource(){
      return _source;
   }

   public void setSource(FString source){
      _source = source;
   }
}
