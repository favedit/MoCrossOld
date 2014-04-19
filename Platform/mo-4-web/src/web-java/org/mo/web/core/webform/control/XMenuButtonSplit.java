package org.mo.web.core.webform.control;

import org.mo.web.core.webform.base.XBaseMenuButtonSplit;

public class XMenuButtonSplit
      extends XBaseMenuButtonSplit
{

   @Override
   public String getDispConfig(){
      return RPropertyMaker.getDispConfig(this);
   }

   @Override
   public String getDispMode(){
      return RPropertyMaker.getDispMode(this);
   }

   @Override
   public void setDispConfig(String value){
      RPropertyMaker.setDispConfig(this, value);
   }

   @Override
   public void setDispMode(String value){
      RPropertyMaker.setDispMode(this, value);
   }

}
