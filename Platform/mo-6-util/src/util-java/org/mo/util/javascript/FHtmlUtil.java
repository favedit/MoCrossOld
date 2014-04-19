/*
 * @(#)FHtmlUtil.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.util.javascript;

import org.mo.com.lang.RString;

public class FHtmlUtil{

   public static String makeLabel(String type,
                                  String label,
                                  String desc){
      if(RString.equalsIgnoreCase(label, desc)){
         return label;
      }
      if(RString.isEmpty(desc)){
         return label;
      }
      if(type.equalsIgnoreCase("user")){
         return desc;
      }
      return label + " <FONT color='green'>[ " + desc + " ]</FONT>";
   }

   public static String nodeLabel(String name,
                                  String label){
      if(name != null && label != null && label.length() > 0 && !name.equals(label)){
         return name + " <FONT color='green'>[ " + label + " ]</FONT>";
      }
      return name;
   }

}
