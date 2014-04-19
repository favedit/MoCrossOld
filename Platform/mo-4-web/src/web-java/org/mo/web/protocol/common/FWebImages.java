package org.mo.web.protocol.common;

import org.mo.com.lang.RString;

public class FWebImages
{

   public static final String IMAGE_ROOT = "/res/img/";

   public static final String NONE = "n";

   public static final String SYS_CLASS = "sys.cls";

   public static final String SYS_PROPERTY = "sys.property";

   public static final String SYS_INSERT = "sys.insert";

   public static final String SYS_EDIT = "sys.edit";

   public static final String SYS_SAVE = "sys.save";

   public static final String SYS_DELETE = "sys.delete";

   public static final String SYS_BACK = "sys.back";

   public static final String SYS_PACKAGEHEAD = "sys.pkghead";

   public static final String SYS_PACKAGEBODY = "sys.pkgbdy";

   public static final String SYS_INFO = "sys.info";

   public static final String SYS_DATA = "sys.data";

   public static final String SYS_SETUP = "sys.setup";

   public static final String SYS_TREEVIEW = "sys.tv";

   public static final String SYS_TREEVIEW_NODE = "sys.node";

   public static final String SYS_PACKAGE = "sys.pkg";

   public static final String SYS_HELP = "sys.help";

   public static final String SYS_CONFIG = "sys.cfg";

   public static final String SYS_DOCUMENT = "sys.doc";

   public static final String SYS_TABLE_CLEAR = "sys.delete";

   public static final String SYS_TABLE_IMPORT = "sys.pkghead";

   public static final String SYS_TABLE_EXPORT = "sys.pkgbdy";

   public static final String SYS_SEARCH = "sys.search";

   public static final String SYS_REFRESH = "sys.refresh";

   public static final String PUB_JA_WORD = "pub.ja.word";

   public static final String PUB_JA_GRAMMAR = "pub.ja.grammar";

   public static final String PUB_JA_TEXT = "pub.ja.text";

   public static final String H = "h";

   public static final String V = "n";

   public static final String makeHTML(){
      return makeHTML(NONE);
   }

   public static final String makeHTML(String sImage){
      return "<IMG align='absmiddle' src='" + IMAGE_ROOT + RString.replace(sImage, ".", "/") + ".gif' border='0'>";
   }

   public static final String makeHTML(String sImage,
                                       int nWidth,
                                       int nHeight){
      return "<IMG align='absmiddle' src='" + IMAGE_ROOT + RString.replace(sImage, ".", "/") + ".gif' width='" + Integer.toString(nWidth) + "' height='" + Integer.toString(nHeight) + "' border='0'>";
   }

   public static final String makeHTML(String sImage,
                                       String sAddition){
      return "<IMG " + sAddition + " align='absmiddle' src='" + IMAGE_ROOT + RString.replace(sImage, ".", "/") + ".gif' border='0'>";
   }

   public static final String makeIcon(String sContextPath,
                                       String sIcon){
      return "<IMG align='absmiddle' src='" + sContextPath + IMAGE_ROOT + RString.replace(sIcon, ".", "/") + ".gif' border='0'>";
   }

   public static final String makeIcon(String sContextPath,
                                       String sIcon,
                                       int nWidth,
                                       int nHeight){
      return "<IMG align='absmiddle' src='" + sContextPath + IMAGE_ROOT + RString.replace(sIcon, ".", "/") + ".gif' width='" + Integer.toString(nWidth) + "' height='" + Integer.toString(nHeight) + "' border='0'>";
   }
}

// ************************************************************
