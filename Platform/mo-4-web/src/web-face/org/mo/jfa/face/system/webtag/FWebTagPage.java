package org.mo.jfa.face.system.webtag;

import org.mo.com.lang.FString;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.IXmlObject;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectPage;

public class FWebTagPage
      extends FAbsXmlObjectPage<IXmlObject>
{

   private static final long serialVersionUID = 1L;

   private FString _source;

   private FXmlNode _node;

   private String _bulidType;

   public void setSource(FString source){
      _source = source;
   }

   public FString getSource(){
      return _source;
   }

   public void setNode(FXmlNode node){
      _node = node;
   }

   public FXmlNode getNode(){
      return _node;
   }

   public void setBulidType(String bulidType){
      _bulidType = bulidType;
   }

   public String getBulidType(){
      return _bulidType;
   }
}
