/*
 * @(#)RSqlTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.data.procedure.common;

import org.mo.com.lang.FString;
import org.mo.core.aop.RAop;
import org.mo.eng.format.ESyntax;
import org.mo.eng.format.IFormatConsole;
import org.mo.eng.format.ISyntaxMaker;

/**
 * <T>设置变量标识的类</T>
 * 
 * @author ZENGD
 * @version 1.0.1
 */
public class RSqlTag
{
   /**
    * <T>定义函数的标识</T>
    */
   public final static String FUNCTION = "FUNCTION";

   /**
    * <T>定义过程的标识</T>
    */
   public final static String PROCEDURE = "PROCEDURE";

   /**
    * <T>定义参数的标识</T>
    */
   public final static String PARAM = "@param";

   /**
    * <T>定义返回值的标识</T>
    */
   public final static String RETURN = "@return";

   /**
    * <T>定义属性的标识</T>
    */
   public final static String PROPERTY = "@property";

   /**
    * <T>定义包的标识</T>
    */
   public final static String PACKAGE = "@package";

   /**
    * <T>定义版本的标识</T>
    */
   public final static String VERSION = "@version";

   /**
    * <T>函数的标识</T>
    */
   public final static String AUTHOR = "@author";

   /**
    * <T>定义调用的标识</T>
    */
   public final static String SEE = "@see";

   /**
    * <T>格式化PLSQL代码</T>
    * 
    * @param source 表示PLSQL要进行格式化的代码
    * @return 格式化的结果
    */
   public final static String formatPlSQLScript(FString source){
      IFormatConsole formatConsole = RAop.find(IFormatConsole.class);
      ISyntaxMaker syntaxMaker = formatConsole.find(ESyntax.PlSql);
      return syntaxMaker.format(source).toString();
   }
}
