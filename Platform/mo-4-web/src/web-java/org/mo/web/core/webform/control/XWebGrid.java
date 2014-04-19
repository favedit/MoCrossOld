package org.mo.web.core.webform.control;

import org.mo.web.core.webform.base.XBaseWebGrid;

public class XWebGrid
      extends XBaseWebGrid
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
   public String getEditConfig(){
      return RPropertyMaker.getEditConfig(this);
   }

   @Override
   public String getEditMode(){
      return RPropertyMaker.getEditMode(this);
   }

   @Override
   public String getPanelAccess(){
      return RPropertyMaker.getPanelAccess(this);
   }

   @Override
   public String getValidAccess(){
      return RPropertyMaker.getValidAccess(this);
   }

   @Override
   public void setDispConfig(String value){
      RPropertyMaker.setDispConfig(this, value);
   }

   @Override
   public void setDispMode(String value){
      RPropertyMaker.setDispMode(this, value);
   }

   @Override
   public void setEditConfig(String value){
      RPropertyMaker.setEditConfig(this, value);
   }

   @Override
   public void setEditMode(String value){
      RPropertyMaker.setEditMode(this, value);
   }

   @Override
   public void setPanelAccess(String value){
      RPropertyMaker.setPanelAccess(this, value);
   }

   @Override
   public void setValidAccess(String value){
      RPropertyMaker.setValidAccess(this, value);
   }

}
