/*
 * @(#)FPLSQLSyntax.java
 *
 * Copyright 2004 Java Frame Studio Corporation.
 * All Rights Reserved.
 *
 */
package org.mo.web.core.format;

/**
 * <p>PLSQL代码格式化工具类</p>
 *
 * @author FEDT
 * @version 1.00 - 2005/02/18
 */
public class RPlSqlSyntax
{

   // 字符串分隔符
   public final static String SPLITTER = " ,.!@#$%^&*`~'\":;()[]<>{}-=\\+|\n";

   // 关键字列表
   public final static String KEY_STRING = " ADD ALTER AND AS" + " BEGIN BETWEEN BODY BY" + " CLOSE CLUSTER COMMENT COMMIT COST CREATE CURSOR" + " DATABASE DECLARE DEFAULT DEFINE DELETE DROP" + " EACH ELSE ELSIF END ERROR EXCEPTION EXCLUSIVE EXISTS EXIT"
         + " FALSE FETCH FOR FROM FUNCTION" + " IF IN INDEX INITIAL INSERT INTO IS" + " LOCK LOOP" + " KEY" + " MODE MODIFY" + " NOT NOWAIT NULL" + " OF ON OPEN OR OTHERS OUT" + " PACKAGE PARTITION REVERSE ROW PRAGMA PRIMARY PROCEDURE PROFILE PROMPT"
         + " RAISE RENAME REPLACE RESOURCE RETURN ROLE ROLLBACK" + " SAVEPOINT SEGMENT SELECT SEQUENCE SESSION SET SHARE SHOW SIZE SQLCODE SQLERRM" + " TABLE THEN TO TRUE" + " UNDEFINE UPDATE" + " VALUES" + " WHEN WHERE WHILE WITH ";

   // 数据类型列表
   public final static String TYPE_STRING = " BINARY_INTEGER BOOLEAN" + " CHAR" + " DATE" + " LONG" + " NUMBER" + " ROWTYPE" + " TYPE" + " VARCHAR2 ";

   // 函数列表
   public final static String FUNCTION_STRING = " ABS ACOS ADD_MONTHS ASCII ASIN ATAN AVG" + " BFILENAME" + " CEIL CHARTOROWID CHR CONCAT CONVERT COS COSH COUNT" + " DEREF DUMP" + " EMPTY_BLOB EMPTY_CLOB" + " EXP" + " FLOOR FOUND" + " GREATEST"
         + " HEXTORAW" + " INITCAP INSTR INSTRB" + " LAST_DAY LEAST LENGTH LENGTHB" + " LN LOG LOWER LPAD LTRIM" + " MAKE_REF MAX MIN MOD MONTHS_BETWEEN"
         + " NEW_TIME NEXT_DAY NLS_CHARSET_DECL_LEN NLS_CHARSET_ID NLS_CHARSET_NAME NLS_INITCAP NLS_LOWER NLS_UPPER NLSSORT NOTFOUND NVL" + " POWER" + " RAWTOHEX REFTOHEX REPLACE ROUND ROWIDTOCHAR RPAD RTRIM"
         + " SIGN SIN SINH SOUNDEX SQRT STDDEV SUBSTR SUBSTRB SUM SYSDATE" + " TAN TANH TO_CHAR TO_DATE TO_MULTI_BYTE TO_NUMBER TO_SINGLE_BYTE TRANSLATE TRANSLATE TRUNC" + " USING UID UPPER USER USERENV" + " VARIANCE VSIZE ";

   /**
    * <p>格式化JAVA代码</p>
    * <p>create date:2005/02/18</p>
    *
    * @param sValue 格式化内容
    * @return 格式化后的内容
    */
   public static String format(String sValue){
      StringBuffer oValue = new StringBuffer();
      String sSub = "";
      char[] chBuffer = sValue.toCharArray();
      char ch;
      char chPre = 0;
      int nLength = chBuffer.length;
      boolean bInString = false;
      boolean bInComment = false;
      boolean bInCommentLine = false;
      for(int i = 0; i < nLength; i++){
         ch = chBuffer[i];
         // 注释开始处理
         if(SPLITTER.indexOf(ch) >= 0){
            if(!bInComment){
               if(chPre == '-' && ch == '-'){
                  oValue.append("<FONT color='#008080'>");
                  bInComment = true;
                  bInCommentLine = true;
               }
               if(ch == '/'){
                  if(chBuffer[i + 1] == '*'){
                     oValue.append("<FONT color='#008080'>/*");
                     bInComment = true;
                     i++;
                     continue;
                  }
               }
               // 字符串处理
               if(!bInString){
                  if(ch == '\''){
                     oValue.append("\'<FONT color='#808080'>");
                     bInString = true;
                     continue;
                  }
               }else{
                  if(ch == '\''){
                     oValue.append(sSub + "</FONT>\'");
                     bInString = false;
                     sSub = "";
                     continue;
                  }
               }
            }else{
               if(chPre == '*' && ch == '/'){
                  oValue.append("/</FONT>");
                  bInComment = false;
                  continue;
               }
            }
            // 注释关闭处理
            if(!bInComment && !bInString){
               if(sSub.length() > 0){
                  replaceFormat(oValue, sSub);
               }
            }else{
               oValue.append(sSub);
            }
            // 特殊字符的处理
            if(ch == '\n'){
               if(bInComment && bInCommentLine){
                  oValue.append("</FONT>");
                  bInComment = false;
                  bInCommentLine = false;
               }
               oValue.append("<br>\n");
            }else if(ch == ' '){
               oValue.append("&nbsp;");
            }else if(ch == '&'){
               oValue.append("&amp;");
            }else if(ch == '<'){
               oValue.append("&lt;");
            }else if(ch == '>'){
               oValue.append("&gt;");
            }else if(ch == '\"'){
               oValue.append("&quot;");
            }else if(ch == '\t'){
               oValue.append("&quot;&quot;&quot;");
            }else{
               oValue.append(ch);
            }
            // 字符处理
            chPre = ch;
            sSub = "";
         }else{
            sSub += chBuffer[i];
         }
      }
      return oValue.toString();
   }

   // 格式替换函数
   private static boolean replaceFormat(StringBuffer oString,
                                        String sSub){
      if(KEY_STRING.indexOf(" " + sSub + " ") >= 0){
         oString.append("<FONT color='blue'>" + sSub + "</FONT>");
         return true;
      }else if(TYPE_STRING.indexOf(" " + sSub + " ") >= 0){
         oString.append("<FONT color='#FF8000'>" + sSub + "</FONT>");
         return true;
      }else if(FUNCTION_STRING.indexOf(" " + sSub + " ") >= 0){
         oString.append("<FONT color='green'>" + sSub + "</FONT>");
         return true;
      }else{
         oString.append(sSub);
      }
      return true;
   }
}
