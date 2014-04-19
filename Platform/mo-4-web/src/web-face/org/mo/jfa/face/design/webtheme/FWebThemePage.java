package org.mo.jfa.face.design.webtheme;

import org.mo.com.lang.FString;
import org.mo.com.xml.FXmlNode;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectPage;
import org.mo.web.core.webtheme.common.XWebTheme;

public class FWebThemePage
      extends FAbsXmlObjectPage<XWebTheme>
{

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   private FString _source;

   private FXmlNode _node;

   public void setSource(FString source){
      _source = source;
   }

   public FString getSource(){
      return _source;
   }

   public void setFXmlNode(FXmlNode newNode){
      _node = newNode;
   }

   public FXmlNode getFXmlNode(){
      return _node;
   }
}
