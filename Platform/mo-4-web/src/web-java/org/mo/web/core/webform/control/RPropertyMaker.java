package org.mo.web.core.webform.control;

import org.mo.com.lang.RBoolean;
import org.mo.com.xml.IXmlObject;

public class RPropertyMaker
{

   public static final String DATE_DAY = "D";

   public static final String DATE_HOUR = "H";

   public static final String DATE_MINUTE = "T";

   public static final String DATE_MONTH = "M";

   public static final String DATE_SECOND = "S";

   public static final String DATE_YEAR = "Y";

   public static final String DISP_AUTO = "A";

   public static final String DISP_DRAG = "D";

   public static final String DISP_FIXED = "F";

   public static final String DISP_LIST = "L";

   public static final String DISP_PRINT = "P";

   public static final String DISP_SIZE = "S";

   public static final String EDIT_COPY = "C";

   public static final String EDIT_SEARCH = "S";

   public static final String MODE_DELETE = "D";

   public static final String MODE_DESIGN = "F";

   public static final String MODE_DISPLAY = "P";

   public static final String MODE_INSERT = "I";

   public static final String MODE_SEARCH = "S";

   public static final String MODE_UPDATE = "U";

   public static final String MODE_ZOOM = "Z";

   public static final String PANEL_HEAD = "H";

   public static final String PANEL_HINT = "N";

   public static final String PANEL_SEARCH = "S";

   public static final String PANEL_TITLE = "T";

   public static final String PANEL_TOTAL = "A";

   public static final String VALID_DELETE = "D";

   public static final String VALID_INSERT = "I";

   public static final String VALID_UPDATE = "U";

   public static String convertBoolean(IXmlObject xobject,
                                       String fieldName,
                                       String flag){
      return RBoolean.toString(xobject.innerGet(fieldName), flag, "");
   }

   public static String getDispConfig(IXmlObject xobject){
      String access = "";
      access += convertBoolean(xobject, "disp_list", DISP_LIST);
      access += convertBoolean(xobject, "disp_fixed", DISP_FIXED);
      access += convertBoolean(xobject, "disp_auto", DISP_AUTO);
      access += convertBoolean(xobject, "disp_size", DISP_SIZE);
      access += convertBoolean(xobject, "disp_drag", DISP_DRAG);
      access += convertBoolean(xobject, "disp_print", DISP_PRINT);
      return access;
   }

   public static String getDispMode(IXmlObject xobject){
      String access = "";
      access += convertBoolean(xobject, "disp_display", MODE_DISPLAY);
      access += convertBoolean(xobject, "disp_search", MODE_SEARCH);
      access += convertBoolean(xobject, "disp_insert", MODE_INSERT);
      access += convertBoolean(xobject, "disp_update", MODE_UPDATE);
      access += convertBoolean(xobject, "disp_delete", MODE_DELETE);
      access += convertBoolean(xobject, "disp_zoom", MODE_ZOOM);
      return access;
   }

   public static String getEditConfig(IXmlObject xobject){
      String access = "";
      access += convertBoolean(xobject, "edit_search", EDIT_SEARCH);
      access += convertBoolean(xobject, "edit_copy", EDIT_COPY);
      return access;
   }

   public static String getEditDate(IXmlObject xobject){
      String editDate = "";
      editDate += RBoolean.toString(xobject.innerGet("edit_year"), DATE_YEAR, "");
      editDate += RBoolean.toString(xobject.innerGet("edit_month"), DATE_MONTH, "");
      editDate += RBoolean.toString(xobject.innerGet("edit_day"), DATE_DAY, "");
      return editDate;
   }

   public static String getEditMode(IXmlObject xobject){
      String access = "";
      access += convertBoolean(xobject, "edit_insert", MODE_INSERT);
      access += convertBoolean(xobject, "edit_update", MODE_UPDATE);
      access += convertBoolean(xobject, "edit_delete", MODE_DELETE);
      access += convertBoolean(xobject, "edit_zoom", MODE_ZOOM);
      return access;
   }

   public static String getEditTime(IXmlObject xobject){
      String editDate = "";
      editDate += RBoolean.toString(xobject.innerGet("edit_hour"), DATE_HOUR, "");
      editDate += RBoolean.toString(xobject.innerGet("edit_minute"), DATE_MINUTE, "");
      editDate += RBoolean.toString(xobject.innerGet("edit_second"), DATE_SECOND, "");
      return editDate;
   }

   public static String getPanelAccess(IXmlObject xobject){
      String access = "";
      access += RBoolean.toString(xobject.innerGet("panel_title"), PANEL_TITLE, "");
      access += RBoolean.toString(xobject.innerGet("panel_head"), PANEL_HEAD, "");
      access += RBoolean.toString(xobject.innerGet("panel_search"), PANEL_SEARCH, "");
      access += RBoolean.toString(xobject.innerGet("panel_hint"), PANEL_HINT, "");
      access += RBoolean.toString(xobject.innerGet("panel_total"), PANEL_TOTAL, "");
      return access;
   }

   public static String getValidAccess(IXmlObject xobject){
      String access = "";
      access += RBoolean.toString(xobject.innerGet("valid_insert"), VALID_INSERT, "");
      access += RBoolean.toString(xobject.innerGet("valid_update"), VALID_UPDATE, "");
      access += RBoolean.toString(xobject.innerGet("valid_delete"), VALID_DELETE, "");
      return access;
   }

   public static String hasAccess(String access,
                                  String value){
      return RBoolean.toString((null != access) ? access.contains(value) : false);
   }

   public static void setDispConfig(IXmlObject xobject,
                                    String value){
      xobject.innerSet("disp_list", hasAccess(value, DISP_LIST));
      xobject.innerSet("disp_fixed", hasAccess(value, DISP_FIXED));
      xobject.innerSet("disp_auto", hasAccess(value, DISP_AUTO));
      xobject.innerSet("disp_size", hasAccess(value, DISP_SIZE));
      xobject.innerSet("disp_drag", hasAccess(value, DISP_DRAG));
      xobject.innerSet("disp_print", hasAccess(value, DISP_PRINT));
   }

   public static void setDispMode(IXmlObject xobject,
                                  String value){
      xobject.innerSet("disp_display", hasAccess(value, MODE_DISPLAY));
      xobject.innerSet("disp_search", hasAccess(value, MODE_SEARCH));
      xobject.innerSet("disp_insert", hasAccess(value, MODE_INSERT));
      xobject.innerSet("disp_update", hasAccess(value, MODE_UPDATE));
      xobject.innerSet("disp_delete", hasAccess(value, MODE_DELETE));
      xobject.innerSet("disp_zoom", hasAccess(value, MODE_ZOOM));
   }

   public static void setEditConfig(IXmlObject xobject,
                                    String value){
      xobject.innerSet("edit_search", hasAccess(value, EDIT_SEARCH));
      xobject.innerSet("edit_copy", hasAccess(value, EDIT_COPY));
   }

   public static void setEditDate(IXmlObject xobject,
                                  String value){
      xobject.innerSet("edit_year", hasAccess(value, DATE_YEAR));
      xobject.innerSet("edit_month", hasAccess(value, DATE_MONTH));
      xobject.innerSet("edit_day", hasAccess(value, DATE_DAY));
   }

   public static void setEditMode(IXmlObject xobject,
                                  String value){
      xobject.innerSet("edit_insert", hasAccess(value, MODE_INSERT));
      xobject.innerSet("edit_update", hasAccess(value, MODE_UPDATE));
      xobject.innerSet("edit_delete", hasAccess(value, MODE_DELETE));
      xobject.innerSet("edit_zoom", hasAccess(value, MODE_ZOOM));
   }

   public static void setEditTime(IXmlObject xobject,
                                  String value){
      xobject.innerSet("edit_hour", hasAccess(value, DATE_HOUR));
      xobject.innerSet("edit_minute", hasAccess(value, DATE_MINUTE));
      xobject.innerSet("edit_second", hasAccess(value, DATE_SECOND));
   }

   public static void setPanelAccess(IXmlObject xobject,
                                     String value){
      xobject.innerSet("panel_title", hasAccess(value, PANEL_TITLE));
      xobject.innerSet("panel_head", hasAccess(value, PANEL_HEAD));
      xobject.innerSet("panel_search", hasAccess(value, PANEL_SEARCH));
      xobject.innerSet("panel_hint", hasAccess(value, PANEL_HINT));
   }

   public static void setValidAccess(IXmlObject xobject,
                                     String value){
      xobject.innerSet("valid_insert", hasAccess(value, VALID_INSERT));
      xobject.innerSet("valid_update", hasAccess(value, VALID_UPDATE));
      xobject.innerSet("valid_delete", hasAccess(value, VALID_DELETE));
   }

}
