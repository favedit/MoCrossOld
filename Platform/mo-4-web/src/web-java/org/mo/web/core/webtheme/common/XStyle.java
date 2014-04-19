package org.mo.web.core.webtheme.common;

import org.mo.web.core.webtheme.base.XBaseStyle;

public class XStyle
      extends XBaseStyle
{

   @Override
   public String getBorder(){
      // TODO Auto-generated method stub
      String border;
      if(this.getBorderWidth() != null){
         border = this.getBorderWidth();
         if(this.getBorderStyle() != null){
            border = border + " " + this.getBorderStyle();
         }
         if(this.getBorderColor() != null){
            border = border + " " + this.getBorderColor();
         }
         return border;
      }
      return this.getBorder();
   }

   @Override
   public String getBorderBottom(){
      // TODO Auto-generated method stub
      String borderBottom;
      if(this.getBorderBottomWidth() != null){
         borderBottom = this.getBorderBottomWidth();
         if(this.getBorderBottomStyle() != null){
            borderBottom = borderBottom + " " + this.getBorderBottomStyle();
         }
         if(this.getBorderBottomColor() != null){
            borderBottom = borderBottom + " " + this.getBorderBottomColor();
         }
         return borderBottom;
      }
      return null;
   }

   @Override
   public String getBorderLeft(){
      // TODO Auto-generated method stub
      String borderLeft;
      if(this.getBorderLeftWidth() != null){
         borderLeft = this.getBorderLeftWidth();
         if(this.getBorderLeftStyle() != null){
            borderLeft = borderLeft + " " + this.getBorderLeftStyle();
         }
         if(this.getBorderLeftColor() != null){
            borderLeft = borderLeft + " " + this.getBorderLeftColor();
         }
         return borderLeft;
      }
      return null;
   }

   @Override
   public String getBorderRight(){
      // TODO Auto-generated method stub
      String borderRight;
      if(this.getBorderRightWidth() != null){
         borderRight = this.getBorderRightWidth();
         if(this.getBorderRightStyle() != null){
            borderRight = borderRight + " " + this.getBorderRightStyle();
         }
         if(this.getBorderRightColor() != null){
            borderRight = borderRight + " " + this.getBorderRightColor();
         }
         return borderRight;
      }
      return null;
   }

   @Override
   public String getBorderTop(){
      // TODO Auto-generated method stub
      String borderTop;
      if(this.getBorderTopWidth() != null){
         borderTop = this.getBorderTopWidth();
         if(this.getBorderTopStyle() != null){
            borderTop = borderTop + " " + this.getBorderTopStyle();
         }
         if(this.getBorderTopColor() != null){
            borderTop = borderTop + " " + this.getBorderTopColor();
         }
         return borderTop;
      }
      return null;
   }

   @Override
   public void setBorder(String value){
      // TODO Auto-generated method stub

   }

   @Override
   public void setBorderBottom(String value){
      // TODO Auto-generated method stub

   }

   @Override
   public void setBorderLeft(String value){
      // TODO Auto-generated method stub

   }

   @Override
   public void setBorderRight(String value){
      // TODO Auto-generated method stub

   }

   @Override
   public void setBorderTop(String value){
      // TODO Auto-generated method stub

   }

}
