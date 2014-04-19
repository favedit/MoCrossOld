package org.mo.jfa.face.design.persistence;

import org.mo.com.lang.FString;
import org.mo.eng.persistence.common.XPersistence;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectPage;

public class FPersistencePage
      extends FAbsXmlObjectPage<XPersistence>
{
   private static final long serialVersionUID = 1L;

   private FString _source;

   public FString getSource(){
      return _source;
   }

   public void setSource(FString source){
      _source = source;
   }
}
