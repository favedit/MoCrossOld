package org.mo.jfa.core.property;

import org.mo.com.xml.FXmlNode;

public class FWebFormConsole
{
   protected FWebForm transToInstance(FXmlNode config){
      FWebForm webform = new FWebForm();
      //      RDescriptor.toObject(config.attributes(), webform, EWordFormat.JavaMethod);
      //      for(FXmlNode node : config.nodes()){
      //         FWebFormField field = new FWebFormField();
      //         RDescriptor.toObject(node.attributes(), field, EWordFormat.JavaMethod);
      //         webform.fileds().set(field.getName(), field);
      //      }
      return webform;
   }

   protected FXmlNode transToNode(FWebForm instance){
      //FWebForm webform = (FWebForm)instance;
      FXmlNode config = new FXmlNode("WebForm");
      //      RDescriptor.toPairs(webform, config.attributes(), EWordFormat.UnderlineFieldLower);
      //      for(FWebFormField field : webform.fileds().toObjects()){
      //         FXmlNode control = new FXmlNode("Control");
      //         RDescriptor.toPairs(field, control.attributes(), EWordFormat.UnderlineFieldLower);
      //         config.nodes().push(control);
      //      }
      return config;
   }

}
