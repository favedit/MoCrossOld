package org.mo.com.text;

import org.mo.com.lang.RString;

/**
 * <p>格式化Java类型到指定类型的工具类</p>
 * <p>Java String Optional Util</p>
 *
 * @author FEDT
 * @version 1.00 - 2005/02/18
 */
public class RJavaFormat
{
   /**
    * <p>格式化Java用字符串为数据库函数名字符串。</p>
    * <p>
    * 例如：　类名称 DisplayName -> Display_Name<br>
    * 例如：函数名称 displayName -> Display_Name<br>
    * 例如：变量名称 displayName -> Display_Name
    * </p>
    * <p>create date:2005/02/18</p>
    *
    * @param sValue 指定字符串
    * @return 数据库函数名字符串
    */
   public final static String toDatabaseMethodName(String sValue){
      return toDatabaseMethodName(sValue, null, null);
   }

   /**
    * <p>格式化Java用字符串为数据库函数名字符串，可以使用前缀字符串。</p>
    * <p>
    * 例如：　类名称 前缀(get) DisplayName -> Get_Display_Name<br>
    * 例如：函数名称 前缀(get) displayName -> Get_Display_Name<br>
    * 例如：变量名称 前缀(get) displayName -> Get_Display_Name
    * </p>
    * <p>create date:2005/02/18</p>
    *
    * @param sValue 指定字符串
    * @param sBeginFix 前缀字符串
    * @return 数据库函数名字符串
    */
   public final static String toDatabaseMethodName(String sValue,
                                                   String sBeginFix){
      return toDatabaseMethodName(sValue, sBeginFix, null);
   }

   /**
    * <p>格式化Java用字符串为数据库函数名字符串，可以使用前后缀字符串。</p>
    * <p>
    * 例如：　类名称 前缀(get)后缀(local) DisplayName -> Get_Display_Name_Local<br>
    * 例如：函数名称 前缀(get)后缀(local) displayName -> Get_Display_Name_Local<br>
    * 例如：变量名称 前缀(get)后缀(local) displayName -> Get_Display_Name_Local
    * </p>
    * <p>create date:2005/02/18</p>
    *
    * @param sValue 指定字符串
    * @param sBeginFix 前缀字符串
    * @param sEndFix 后缀字符串
    * @return 数据库函数名字符串
    */
   public final static String toDatabaseMethodName(String sValue,
                                                   String sBeginFix,
                                                   String sEndFix){
      return toDatabaseMethodName(sValue, sBeginFix, sEndFix, "_");
   }

   public final static String toDatabaseMethodName(String sValue,
                                                   String sBeginFix,
                                                   String sEndFix,
                                                   String sSplit){
      if(sValue != null){
         int nLength = sValue.length();
         if(nLength > 0){
            sValue = RString.firstUpper(sValue);
            if(sBeginFix != null && sBeginFix.length() > 0){
               sValue = RString.firstUpper(sBeginFix) + sValue;
            }
            if(sEndFix != null && sEndFix.length() > 0){
               sValue += RString.firstUpper(sEndFix);
            }
            char ch[] = sValue.toCharArray();
            char chLower[] = sValue.toLowerCase().toCharArray();
            char chUpper[] = sValue.toUpperCase().toCharArray();
            nLength = ch.length;
            // 格式化内容
            StringBuffer oResult = new StringBuffer(nLength);
            for(int n = 0; n < nLength; n++){
               if(n == 0){
                  oResult.append(chUpper[n]);
               }else if(RString.isUpper(ch[n])){
                  oResult.append(sSplit);
                  oResult.append(chUpper[n]);
               }else{
                  oResult.append(chLower[n]);
               }
            }
            return oResult.toString();
         }
      }
      return "";
   }

   /**
    * <p>格式化Java用字符串为数据库对象名字符串。</p>
    * <p>
    * 例如：　类名称 DisplayName -> DISPLAY_NAME<br>
    * 例如：函数名称 displayName -> DISPLAY_NAME<br>
    * 例如：变量名称 displayName -> DISPLAY_NAME
    * </p>
    * <p>create date:2005/02/18</p>
    *
    * @param sValue 指定字符串
    * @return 数据库对象名字符串
    */
   public final static String toDatabaseObjectName(String sValue){
      return toDatabaseObjectName(sValue, null, null);
   }

   /**
    * <p>格式化Java用字符串为数据库对象名字符串，可以使用前缀字符串。</p>
    * <p>
    * 例如：　类名称 前缀(get) DisplayName -> GET_DISPLAY_NAME<br>
    * 例如：函数名称 前缀(get) displayName -> GET_DISPLAY_NAME<br>
    * 例如：变量名称 前缀(get) displayName -> GET_DISPLAY_NAME
    * </p>
    * <p>create date:2005/02/18</p>
    *
    * @param sValue 指定字符串
    * @param sBeginFix 前缀字符串
    * @return 数据库对象名字符串
    */
   public final static String toDatabaseObjectName(String sValue,
                                                   String sBeginFix){
      return toDatabaseObjectName(sValue, sBeginFix, null);
   }

   /**
    * <p>格式化Java用字符串为数据库对象名字符串，可以使用前后缀字符串。</p>
    * <p>
    * 例如：　类名称 前缀(get)后缀(local) DisplayName -> GET_DISPLAY_NAME_LOCAL<br>
    * 例如：函数名称 前缀(get)后缀(local) displayName -> GET_DISPLAY_NAME_LOCAL<br>
    * 例如：变量名称 前缀(get)后缀(local) displayName -> GET_DISPLAY_NAME_LOCAL
    * </p>
    * <p>create date:2005/02/18</p>
    *
    * @param sValue 指定字符串
    * @param sBeginFix 前缀字符串
    * @param sEndFix 后缀字符串
    * @return 数据库对象名字符串
    */
   public final static String toDatabaseObjectName(String sValue,
                                                   String sBeginFix,
                                                   String sEndFix){
      if(sValue != null){
         int nLength = sValue.length();
         if(nLength > 0){
            sValue = RString.firstUpper(sValue);
            if(sBeginFix != null && sBeginFix.length() > 0){
               sValue = RString.firstUpper(sBeginFix) + sValue;
            }
            if(sEndFix != null && sEndFix.length() > 0){
               sValue += RString.firstUpper(sEndFix);
            }
            char ch[] = sValue.toCharArray();
            char chUpper[] = sValue.toUpperCase().toCharArray();
            nLength = ch.length;
            // 格式化内容
            StringBuffer oResult = new StringBuffer(nLength);
            for(int n = 0; n < nLength; n++){
               if(n == 0){
                  oResult.append(chUpper[n]);
               }else if(RString.isUpper(ch[n])){
                  oResult.append("_");
                  oResult.append(chUpper[n]);
               }else{
                  oResult.append(chUpper[n]);
               }
            }
            return oResult.toString();
         }
      }
      return "";
   }

   /**
    * <p>格式化Java用字符串为数据库变量名字符串。</p>
    * <p>
    * 例如：　类名称 DisplayName -> display_name_<br>
    * 例如：函数名称 displayName -> display_name_<br>
    * 例如：变量名称 displayName -> display_name_
    * </p>
    * <p>create date:2005/02/18</p>
    *
    * @param sValue 指定字符串
    * @return 数据库变量名字符串
    */
   public final static String toDatabaseVariableName(String sValue){
      return toDatabaseVariableName(sValue, null, null);
   }

   /**
    * <p>格式化Java用字符串为数据库变量名字符串，可以使用前缀字符串。</p>
    * <p>
    * 例如：　类名称 前缀(get) DisplayName -> get_display_name_<br>
    * 例如：函数名称 前缀(get) displayName -> get_display_name_<br>
    * 例如：变量名称 前缀(get) displayName -> get_display_name_
    * </p>
    * <p>create date:2005/02/18</p>
    *
    * @param sValue 指定字符串
    * @param sBeginFix 前缀字符串
    * @return 数据库变量名字符串
    */
   public final static String toDatabaseVariableName(String sValue,
                                                     String sBeginFix){
      return toDatabaseVariableName(sValue, sBeginFix, null);
   }

   /**
    * <p>格式化Java用字符串为数据库变量名字符串，可以使用前后缀字符串。</p>
    * <p>
    * 例如：　类名称 前缀(get)后缀(local) DisplayName -> get_display_name_local_<br>
    * 例如：函数名称 前缀(get)后缀(local) displayName -> get_display_name_local_<br>
    * 例如：变量名称 前缀(get)后缀(local) displayName -> get_display_name_local_
    * </p>
    * <p>create date:2005/02/18</p>
    *
    * @param sValue 指定字符串
    * @param sBeginFix 前缀字符串
    * @param sEndFix 后缀字符串
    * @return 数据库变量名字符串
    */
   public final static String toDatabaseVariableName(String sValue,
                                                     String sBeginFix,
                                                     String sEndFix){
      if(sValue != null){
         int nLength = sValue.length();
         if(nLength > 0){
            sValue = RString.firstUpper(sValue);
            if(sBeginFix != null && sBeginFix.length() > 0){
               sValue = RString.firstUpper(sBeginFix) + sValue;
            }
            if(sEndFix != null && sEndFix.length() > 0){
               sValue += RString.firstUpper(sEndFix);
            }
            char ch[] = sValue.toCharArray();
            char chLower[] = sValue.toLowerCase().toCharArray();
            nLength = ch.length;
            // 格式化内容
            StringBuffer oResult = new StringBuffer(nLength);
            for(int n = 0; n < nLength; n++){
               if(n == 0){
                  oResult.append(chLower[n]);
               }else if(RString.isUpper(ch[n])){
                  oResult.append("_");
                  oResult.append(chLower[n]);
               }else{
                  oResult.append(chLower[n]);
               }
            }
            oResult.append("_");
            return oResult.toString();
         }
      }
      return "";
   }

   /**
    * <p>格式化数据库用字符串为显示用的字符串</p>
    * <p>
    * 例如：　类名称 DisplayName -> Display Name<br>
    * 例如：函数名称 displayName -> Display Name<br>
    * 例如：变量名称 displayname_ -> Display Name
    * </p>
    * <p>create date:2005/02/18</p>
    *
    * @param sValue 指定字符串
    * @return 显示用的字符串
    */
   public final static String toDisplayName(String sValue){
      if(sValue != null){
         int nLength = sValue.length();
         if(nLength > 0){
            char ch[] = sValue.toCharArray();
            char chLower[] = sValue.toLowerCase().toCharArray();
            char chUpper[] = sValue.toUpperCase().toCharArray();
            // 格式化内容
            StringBuffer oResult = new StringBuffer(nLength);
            for(int n = 0; n < nLength; n++){
               if(n == 0){
                  oResult.append(chUpper[n]);
               }else if(RString.isUpper(ch[n])){
                  oResult.append(" ");
                  oResult.append(chUpper[n]);
               }else{
                  oResult.append(chLower[n]);
               }
            }
            return oResult.toString();
         }
      }
      return "";
   }
}
