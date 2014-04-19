package org.mo.web.core.webform.control;

import org.mo.web.core.webform.base.XBaseTableButton;

public class XTableButton
      extends XBaseTableButton
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
      // TODO Auto-generated method stub

   }

   @Override
   public void setDispMode(String value){
      // TODO Auto-generated method stub

   }

}
