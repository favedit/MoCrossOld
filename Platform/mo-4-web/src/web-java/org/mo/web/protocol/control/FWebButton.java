package org.mo.web.protocol.control;

public class FWebButton
{
   // public declare
   public final static int EDIT_NUMBER = 1;

   public final static int EDIT_STRING = 2;

   public final static int EDIT_DATETIME = 3;

   private String _caption;

   private String _action;

   private String _icon;

   private String m_sName;

   public FWebButton(){
   }

   public String action(){
      return _action;
   }

   public String caption(){
      return _caption;
   }

   public String html(){
      return "";
   }

   public String icon(){
      return _icon;
   }

   public String name(){
      return m_sName;
   }

   public void setAction(String sValue){
      _action = sValue;
   }

   public void setCaption(int nValue){
      _caption = Integer.toString(nValue);
   }

   public void setCaption(String sValue){
      _caption = sValue;
   }

   public void setIcon(String sValue){
      _icon = sValue;
   }

   public void setName(String name){
      m_sName = name;
   }
}
