package org.mo.jfa.common.page;

import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlNodes;

public class FAbstractNavigatorPage
      extends FAbstractFormPage
{

   private static final long serialVersionUID = 1L;

   private FXmlNodes _examineFinalNodes;

   private FXmlNode _examineManagerNode;

   private FXmlNodes _examineNodes;

   public FXmlNodes examineFinalNodes(){
      return _examineFinalNodes;
   }

   public FXmlNode examineManagerNode(){
      return _examineManagerNode;
   }

   public FXmlNodes examineNodes(){
      return _examineNodes;
   }

   public void setExamineFinalNodes(FXmlNodes _examineFinalNodes){
      this._examineFinalNodes = _examineFinalNodes;
   }

   public void setExamineManagerNode(FXmlNode _examineManagerNode){
      this._examineManagerNode = _examineManagerNode;
   }

   public void setExamineNodes(FXmlNodes _examineNodes){
      this._examineNodes = _examineNodes;
   }

}
