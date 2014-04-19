package org.mo.eng.property;

import org.mo.com.lang.FFatalError;
import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.face.AProperty;
import org.mo.eng.common.FAbstractConfigConsole;

public class FPropertyConsole
      extends FAbstractConfigConsole
      implements
         IPropertyConsole
{
   protected String _dataName = "Property";

   @AProperty
   protected String _type;

   public void initializeRegister(){
      //_dataConsole.registerType(_type);
   }

   @Override
   public boolean isProperty(String source){
      return isSource(source);
   }

   @Override
   public String property(String source){
      String[] items = splitSource(source);
      if(items.length == 2){
         FXmlNode node = findNode(_type, items[0], _dataName, items[1]);
         if(node != null){
            return node.text();
         }
      }else{
         throw new FFatalError("Source invalid.({0})", source);
      }
      return null;
   }
}
