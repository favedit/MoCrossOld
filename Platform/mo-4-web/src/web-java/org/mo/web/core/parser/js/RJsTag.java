package org.mo.web.core.parser.js;

import org.mo.com.lang.FString;
import org.mo.core.aop.RAop;
import org.mo.web.core.format.ESyntax;
import org.mo.web.core.format.IFormatConsole;
import org.mo.web.core.format.ISyntaxMaker;

public class RJsTag
{

   public final static String CM_FUNCTION = "function";

   public final static String CM_EMPTY = "RMethod.empty";

   public final static String REFERENCE = "@reference";

   public final static String ENUMERATE = "@enum";

   public final static String CLASS = "@class";

   public final static String MANGER = "@manger";

   public final static String METHOD = "@method";

   public final static String PARAM = "@param";

   public final static String RETURN = "@return";

   public final static String FACE = "@face";

   public final static String SCOPE = "@scope";

   public final static String AUTHOR = "@author";

   public final static String VERSION = "@version";

   public final static String EMPTY = "@empty";

   public final static String VIRTUAL = "@virtual";

   public final static String PROPERTY = "@property";

   public final static String ATTRIBUTE = "@attribute";

   public final static String STYLE = "@style";

   public final static String HTML = "@html";

   public final static String EVENT = "@event";

   public final static String SEE = "@see";

   public final static String T = "<T>";

   public final static String P = "<P/>";

   public final static String B = "<BR/>";

   public final static String D = "<D>";

   public final static String R = "<R>";

   public final static String U = "<U>";

   public final static String L = "<L>";

   public final static String formatJavaScript(FString source){
      IFormatConsole formatConsole = RAop.find(IFormatConsole.class);
      ISyntaxMaker syntaxMaker = formatConsole.find(ESyntax.Javascript);
      return syntaxMaker.format(source).toString();
   }

}
