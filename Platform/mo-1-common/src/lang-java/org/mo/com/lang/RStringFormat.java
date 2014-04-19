package org.mo.com.lang;

//============================================================
// <T>格式化描述器。</T>
//============================================================
public class RStringFormat
{
   //============================================================
   // <T>格式化字符串为下划线分割的字符串，可以使用前后缀字符串。</T>
   //
   // @param source 指定字符串
   // @param beginFix 前缀字符串
   // @param endFix 后缀字符串
   // @param isUpper 是否大写
   // @param isLower 是否小写
   // @return 格式化字符串
   //============================================================
   private final static String innerFormatLineWords(String source,
                                                    String beginFix,
                                                    String endFix,
                                                    boolean isUpper,
                                                    boolean isLower){
      if(null != source){
         int length = source.length();
         if(length > 0){
            source = RString.firstUpper(source);
            if((null != beginFix) && (beginFix.length() > 0)){
               source = RString.firstUpper(beginFix) + source;
            }
            if((null != endFix) && (endFix.length() > 0)){
               source += RString.firstUpper(endFix);
            }
            char chars[] = source.toCharArray();
            char charsChange[] = null;
            if(isUpper){
               charsChange = source.toUpperCase().toCharArray();
            }
            if(isLower){
               charsChange = source.toLowerCase().toCharArray();
            }
            // 格式化内容
            length = chars.length;
            StringBuffer result = new StringBuffer(length);
            for(int n = 0; n < length; n++){
               if(n == 0){
                  result.append(charsChange[n]);
               }else if(RString.isUpper(chars[n])){
                  result.append("_");
                  result.append(charsChange[n]);
               }else{
                  result.append(charsChange[n]);
               }
            }
            return result.toString();
         }
      }
      return RString.EMPTY;
   }

   //============================================================
   // <T>格式化字符串为下划线分割的字符串。</T>
   // <P>例：DisplayName -> Display_Name</P>
   // </P>
   //
   // @param source 指定字符串
   // @param beginFix 前缀字符串
   // @param endFix 后缀字符串
   // @return 数据库对象名字符串
   //============================================================
   public final static String formatLineWords(String source,
                                              String beginFix,
                                              String endFix){
      return innerFormatLineWords(source, beginFix, endFix, false, false);
   }

   //============================================================
   // <T>格式化字符串为下划线分割的小写字符串。</T>
   // <P>例：DisplayName -> display_name</P>
   //
   // @param source 指定字符串
   // @return 数据库对象名字符串
   //============================================================
   public final static String formatLineWordsLower(String source){
      return innerFormatLineWords(source, null, null, false, true);
   }

   //============================================================
   // <T>格式化字符串为下划线分割的小写字符串。</T>
   // <P>例：DisplayName -> display_name</P>
   //
   // @param source 指定字符串
   // @param beginFix 前缀字符串
   // @param endFix 后缀字符串
   // @return 数据库对象名字符串
   //============================================================
   public final static String formatLineWordsLower(String source,
                                                   String beginFix,
                                                   String endFix){
      return innerFormatLineWords(source, beginFix, endFix, false, true);
   }

   //============================================================
   // <T>格式化字符串为下划线分割的大写字符串。</T>
   // <P>例：DisplayName -> DISPLAY_NAME</P>
   //
   // @param source 指定字符串
   // @return 数据库对象名字符串
   //============================================================
   public final static String formatLineWordsUpper(String source){
      return innerFormatLineWords(source, null, null, true, false);
   }

   //============================================================
   // <T>格式化字符串为下划线分割的大写字符串。</T>
   // <P>例：DisplayName -> DISPLAY_NAME</P>
   //
   // @param source 指定字符串
   // @param beginFix 前缀字符串
   // @param endFix 后缀字符串
   // @return 数据库对象名字符串
   //============================================================
   public final static String formatLineWordsUpper(String source,
                                                   String beginFix,
                                                   String endFix){
      return innerFormatLineWords(source, beginFix, endFix, true, false);
   }

   //============================================================
   // <T>格式化下划线分割的字符串为单词，并且第一个字符为小写。</T>
   // <P>例：DISPLAY_NAME -> displayName</P>
   // <P>例：Display_Name -> displayName</P>
   // <P>例：display_name -> displayName</P>
   //
   // @param value 指定字符串
   // @return 格式化后字符串
   //============================================================
   public final static String formatFirstLower(String value){
      return formatFirstLower(value, null, null);
   }

   //============================================================
   // <T>格式化下划线分割的字符串为单词，并且第一个字符为小写。</T>
   // <P>例：DISPLAY_NAME -> displayName</P>
   // <P>例：Display_Name -> displayName</P>
   // <P>例：display_name -> displayName</P>
   //
   // @param value 指定字符串
   // @param begin 前缀字符串
   // @param end 后缀字符串
   // @return 格式化后字符串
   //============================================================
   public final static String formatFirstLower(String value,
                                               String begin,
                                               String end){
      StringBuilder result = new StringBuilder();
      if(null != value){
         int length = value.length();
         if(length > 0){
            if(begin != null && begin.length() > 0){
               value = begin + "_" + value;
            }
            if(end != null && end.length() > 0){
               value += "_" + end;
            }
            char chars[] = value.toCharArray();
            char charsLower[] = value.toLowerCase().toCharArray();
            char charsUpper[] = value.toUpperCase().toCharArray();
            length = chars.length;
            // 格式化内容
            for(int n = 0; n < length; n++){
               if(n == 0){
                  result.append(charsLower[n]);
               }else if(chars[n] == '_'){
                  n++;
                  if(n < length){
                     result.append(charsUpper[n]);
                  }
               }else{
                  result.append(charsLower[n]);
               }
            }
         }
      }
      return result.toString();
   }
}
