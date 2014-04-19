package org.mo.com.lang;

import java.util.Arrays;

//============================================================
// <T>字符串管理器。</T>
//============================================================
public class RString
{
   // 空字符串
   public static String EMPTY = "";

   // 数字字符集合
   public static final char[] NUMBER_CHARS = "0123456789".toCharArray();

   // 数字字符串
   public static final String NUMBER_STR = "0123456789";

   // 小写字符集合
   public static final char[] LOWER_CHARS = "abcdefghijklmnopqrstuvwxyz".toCharArray();

   // 小写字符串
   public static final String LOWER_STR = "abcdefghijklmnopqrstuvwxyz";

   // 大写字符集合
   public static final char[] UPPER_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

   // 大写字符串
   public static final String UPPER_STR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

   //============================================================
   // <T>判断字符串是否为空。</T>
   //
   // @param value 字符串
   // @return 是否为空
   //============================================================
   public final static boolean isEmpty(String value){
      return (value == null) ? true : (0 == value.length());
   }

   //============================================================
   // <T>判断字符串集合是否含有空。</T>
   //
   // @param values 字符串集合
   // @return 是否为空
   //============================================================
   public final static boolean isEmpty(String... values){
      // 检查参数
      if(values == null){
         return true;
      }
      // 检查个数
      int count = values.length;
      if(count == 0){
         return true;
      }
      // 检查集合
      int n = -1;
      while(++n < count){
         String value = values[n];
         if(value == null){
            return true;
         }
         if(value.length() == 0){
            return true;
         }
      }
      return false;
   }

   //============================================================
   // <T>判断字符串是否非空。</T>
   //
   // @param value 字符串
   // @return 是否非空
   //============================================================
   public final static boolean isNotEmpty(String value){
      return (value == null) ? false : (0 != value.length());
   }

   //============================================================
   // <T>判断字符串集合是否为空。</T>
   //
   // @param values 字符串集合
   // @return 是否为空
   //============================================================
   public final static boolean isNotEmpty(String... values){
      // 检查参数
      if(values == null){
         return false;
      }
      // 检查总数
      int count = values.length;
      if(count == 0){
         return false;
      }
      // 检查集合
      int n = -1;
      while(++n < count){
         String value = values[n];
         if(null == value){
            return false;
         }
         if(value.length() == 0){
            return false;
         }
      }
      return true;
   }

   //============================================================
   // <T>判断字符串是否为空。</T>
   // <P>含有不可见字符的字符串也认为是空。</P>
   //
   // @param value 字符串
   // @return 是否为空
   //============================================================
   public final static boolean isBlank(String value){
      return (null == value) ? true : (0 == value.trim().length());
   }

   //============================================================
   // <T>判断字符串集合是否为空。</T>
   // <P>含有不可见字符的字符串也认为是空。</P>
   //
   // @param values 字符串集合
   // @return 是否为空
   //============================================================
   public final static boolean isBlank(String... values){
      int n = -1;
      int loop = values.length;
      while(++n < loop){
         String value = values[n];
         if(null != value){
            if(value.trim().length() > 0){
               return false;
            }
         }
      }
      return true;
   }

   //============================================================
   // <T>判断字符串是否为空。</T>
   // <P>含有不可见字符的字符串也认为是空。</P>
   //
   // @param value 字符串
   // @return 是否为空
   //============================================================
   public final static boolean isNotBlank(String value){
      return (null == value) ? false : (0 != value.trim().length());
   }

   //============================================================
   // <T>判断字符串集合是否为空。</T>
   // <P>含有不可见字符的字符串也认为是空。</P>
   //
   // @param values 字符串集合
   // @return 是否为空
   //============================================================
   public final static boolean isNotBlank(String... values){
      int n = -1;
      int loop = values.length;
      while(++n < loop){
         String value = values[n];
         if(null == value){
            return false;
         }
         if(value.trim().length() == 0){
            return false;
         }
      }
      return true;
   }

   //============================================================
   // <T>判断指定字符是否为小写字符。</T>
   //
   // @param value 指定字符
   // @return true：是<br>false：否
   //============================================================
   public final static boolean isLower(char value){
      return (RString.LOWER_STR.indexOf(value) >= 0);
   }

   //============================================================
   // <T>判断指定字符是否为小写字符。</T>
   //
   // @param value 指定字符
   // @return true：是<br>false：否
   //============================================================
   public final static boolean isLowerChar(String value){
      return (RString.LOWER_STR.indexOf(value) >= 0);
   }

   //============================================================
   // <T>判断指定字符是否为大写字符。</T>
   //
   // @param value 指定字符
   // @return true：是<br>false：否
   //============================================================
   public final static boolean isUpper(char value){
      return (RString.UPPER_STR.indexOf(value) >= 0);
   }

   //============================================================
   // <T>判断指定字符是否为大写字符。</T>
   //
   // @param value 指定字符
   // @return true：是<br>false：否
   //============================================================
   public final static boolean isUpperChar(String value){
      return (RString.UPPER_STR.indexOf(value) >= 0);
   }

   //============================================================
   // <T>指定字符串的所有字符是否都属于单字节的字符。</T>
   //
   // @param value 指定字符
   // @return true：是<br>false：否
   //============================================================
   public static boolean isAnsi(String value){
      if(!isEmpty(value)){
         char[] chars = value.toCharArray();
         int length = chars.length;
         for(int n = 0; n < length; n++){
            if(chars[n] > 255){
               return false;
            }
         }
      }
      return true;
   }

   //============================================================
   // <T>指定字符串的所有字符是否都属于双字节的字符。</T>
   //
   // @param value 指定字符
   // @return true：是<br>false：否
   //============================================================
   public static boolean isDbcs(String value){
      if(!isEmpty(value)){
         char[] chars = value.toCharArray();
         int length = chars.length;
         for(int n = 0; n < length; n++){
            if(chars[n] <= 255){
               return false;
            }
         }
      }
      return true;
   }

   //============================================================
   // <T>指定字符串的所有字符是否都属于模板内的字符。</T>
   //
   // @param value 指定字符串
   // @param partten 模板
   // @return true：是<br>false：否
   //============================================================
   public static boolean isPartten(String value,
                                   String partten){
      if(!isEmpty(value)){
         partten = partten.replaceAll("n", NUMBER_STR);
         partten = partten.replaceAll("a", LOWER_STR);
         partten = partten.replaceAll("A", UPPER_STR);
         char[] chars = value.toCharArray();
         int length = chars.length;
         for(int n = 0; n < length; n++){
            if(partten.indexOf(chars[n]) < 0){
               return false;
            }
         }
      }
      return true;
   }

   //============================================================
   // <T>将一个对象变换为字符串。</T>
   //
   // @param value 对象
   // @return 字符串
   //============================================================
   public static String parse(Object item){
      if(null != item){
         if(item instanceof String){
            return (String)item;
         }
         return item.toString();
      }
      return EMPTY;
   }

   //============================================================
   // <T>当两个指定字符串非空时，判断是否相等。</T>
   //
   // @param source 来源字符串
   // @param target 目标字符串
   // @return true：是<br>false：否
   //============================================================
   public final static boolean equals(String source,
                                      String target){
      if((null != source) && (null != target)){
         return source.equals(target);
      }
      return false;
   }

   //============================================================
   // <T>当两个指定字符串非空时，判断不关心大小写时是否相等。</T>
   //
   // @param source 来源字符串
   // @param target 目标字符串
   // @return true：是<br>false：否
   //============================================================
   public final static boolean equalsIgnoreCase(String source,
                                                String target){
      if((null != source) && (null != target)){
         return source.equalsIgnoreCase(target);
      }
      return false;
   }

   //============================================================
   // <T>判断来源对象和目标对象是否相等。</T>
   //
   // @param source 来源对象
   // @param target 目标对象
   // @return 是否相等
   //============================================================
   public final static boolean equals(Object source,
                                      Object target){
      if((null != source) && (null != target)){
         return source.toString().equals(target.toString());
      }
      return false;
   }

   //============================================================
   // <T>比较两个非空字符串是否相等。</T>
   //
   // @param source 来源字符串
   // @param target 目标字符串
   // @return true：是<br>false：否
   //============================================================
   public final static boolean equalsNvl(String source,
                                         String target){
      if((null == source) && (null == target)){
         return true;
      }else if((null == source) && ((null != target) && (0 == target.length()))){
         return true;
      }else if(((null != source) && (0 == source.length())) && (null != target)){
         return true;
      }else if((null != source) && (null != target)){
         return source.equals(target);
      }
      return false;
   }

   //============================================================
   // <T>当两个指定对象非空时，判断不关心大小写时是否相等。</T>
   //
   // @param source 来源对象
   // @param target 目标对象
   // @return true：是<br>false：否
   //============================================================
   public final static boolean equalsIgnoreCase(Object source,
                                                Object target){
      return ((null != source) && (null != target)) ? source.toString().equalsIgnoreCase(target.toString()) : false;
   }

   //============================================================
   // <T>判断指定字符串是否以指定子字符串开始。</T>
   //
   // @param value 指定字符串
   // @param search 指定子字符串
   // @return true：是<br>false：否
   //============================================================
   public final static boolean startsWith(String source,
                                          String value){
      return (source != null) && source.startsWith(value);
   }

   //============================================================
   // <T>判断指定字符串是否以指定子字符串结束。</T>
   //
   // @param source 指定字符串
   // @param value 指定子字符串
   // @return true：是<br>false：否
   //============================================================
   public final static boolean endsWith(String source,
                                        String value){
      return (null != source) && source.endsWith(value);
   }

   //============================================================
   // <T>判断字符串中是否包含指定字符。</T>
   //
   // @param value 字符串
   // @param search 字符
   // @return 是否包含
   //============================================================
   public static boolean contains(String value,
                                  char search){
      return (null == value) ? false : (-1 != value.indexOf(search));
   }

   //============================================================
   // <T>判断字符串中是否包含指定字符串。</T>
   //
   // @param value 字符串
   // @param search 字符串
   // @return 是否包含
   //============================================================
   public static boolean contains(String value,
                                  String search){
      if(null != value){
         if(null == search){
            return true;
         }
         return (-1 != value.indexOf(search));
      }
      return false;
   }

   //============================================================
   // <T>判断字符串中是否包含指定部分字符串。</T>
   //
   // @param value 字符串
   // @param search 字符串
   // @return 是否包含
   //============================================================
   public static boolean containsPart(String value,
                                      String search){
      if(null != value && null != search){
         value = "," + value + ",";
         search = "," + search + ",";
         return (value.indexOf(search) != -1);
      }
      return false;
   }

   //============================================================
   // <T>判断字符串中是否包含指定部分字符串。</T>
   //
   // @param value 字符串
   // @param search 字符串
   // @param startPart 开始部分
   // @param endPart 结束部分
   // @return 是否包含
   //============================================================
   public static boolean containsPart(String value,
                                      String search,
                                      String startPart,
                                      String endPart){
      if(null != value && null != search){
         value = startPart + value + endPart;
         search = startPart + search + endPart;
         return (value.indexOf(search) != -1);
      }
      return false;
   }

   //============================================================
   // <T>测试字符串是否在指定字符串集合中。</T>
   //
   // @param value 指定字符串
   // @param range 字符串内容
   // @return true：是<br>false：否
   //============================================================
   public static boolean inRange(String value,
                                 String... ranges){
      if(null != value){
         for(String item : ranges){
            if(value.equals(item)){
               return true;
            }
         }
      }
      return false;
   }

   //============================================================
   // <T>测试字符串是否在指定字符串分割集合中。</T>
   //
   // @param value 指定字符串
   // @param range 字符串内容
   // @param splitter 分割字符
   // @return true：是<br>false：否
   //============================================================
   public static boolean inRange(String value,
                                 String range,
                                 char splitter){
      if(null != value){
         String[] ranges = split(range, splitter);
         for(String item : ranges){
            if(value.equals(item)){
               return true;
            }
         }
      }
      return false;
   }

   //============================================================
   // <T>测试字符串是否在指定字符串分割集合中。</T>
   //
   // @param value 指定字符串
   // @param range 字符串内容
   // @param splitter 分割字符
   // @return true：是<br>false：否
   //============================================================
   public static boolean inRangeIgnoreCase(String value,
                                           String range,
                                           char splitter){
      if(null != value){
         String[] ranges = split(range, splitter);
         for(String item : ranges){
            if(value.equalsIgnoreCase(item)){
               return true;
            }
         }
      }
      return false;
   }

   //============================================================
   // <T>在指定字符串中是否含有目标字符。</T>
   //
   // @param value 指定字符串
   // @param chars 字符
   // @return true：是<br>false：否
   //============================================================
   public final static boolean hasChar(String value,
                                       String chars){
      if((null != value) && (null != chars)){
         char[] charArray = value.toCharArray();
         int length = charArray.length;
         for(int n = 0; n < length; n++){
            if(chars.indexOf(charArray[n]) != -1){
               return true;
            }
         }
      }
      return false;
   }

   //============================================================
   // <T>在指定字符串中是否含有全部的目标字符。</T>
   //
   // @param value 指定字符串
   // @param chars 字符
   // @return true：是<br>false：否
   //============================================================
   public final static boolean hasChars(String value,
                                        String chars){
      if((null != value) && (null != chars)){
         char[] charArray = value.toCharArray();
         int length = charArray.length;
         for(int n = 0; n < length; n++){
            if(chars.indexOf(charArray[n]) == -1){
               return false;
            }
         }
      }
      return true;
   }

   //============================================================
   // <T>获得字符串中指定字符出现的次数。</T>
   //
   // @param value 字符串
   // @param splitter 字符
   // @return 出现的次数
   //============================================================
   public static int count(String value,
                           char splitter){
      int count = 0;
      if(null != value){
         int length = value.length();
         for(int n = 0; n < length; n++){
            if(splitter == value.charAt(n)){
               count++;
            }
         }
      }
      return count;
   }

   //============================================================
   // <T>比较两个字符串的大小。</T>
   //
   // @param source 来源字符串
   // @param target 目标字符串
   // @param careCase 大小写敏感
   // @return 字符串大小
   //============================================================
   public static int compare(String source,
                             String target,
                             boolean careCase){
      if((null == source) && (null == target)){
         return 0;
      }else if((null != source) && (null == target)){
         return 1;
      }else if((null == source) && (null != target)){
         return -1;
      }
      return careCase ? source.compareTo(target) : source.compareToIgnoreCase(target);
   }

   //============================================================
   // <T>判断两个字符串是否匹配。</T>
   //
   // @param value 字符串
   // @param search 搜索字符串
   // @return 是否匹配
   //============================================================
   public static boolean matchString(String value,
                                     String search){
      if(null != value && null != search){
         boolean start = search.startsWith("*");
         boolean end = search.endsWith("*");
         if(start && end){
            return value.indexOf(search.substring(1, search.length() - 1)) > 0;
         }else if(start){
            return value.endsWith(search.substring(1));
         }else if(end){
            return value.startsWith(search.substring(0, search.length() - 1));
         }
         return value.equals(search);
      }
      return false;
   }

   //============================================================
   // <T>获得非空字符串。</T>
   //
   // @param value 字符串
   // @return 字符串
   //============================================================
   public static String nvl(String value){
      if(null != value){
         if(value.length() > 0){
            return value;
         }
      }
      return RString.EMPTY;
   }

   //============================================================
   // <T>获得非空字符串。</T>
   //
   // @param value 字符串
   // @param other 字符串
   // @return 字符串
   //============================================================
   public static String nvl(String value,
                            String other){
      if(null != value){
         if(value.length() > 0){
            return value;
         }
      }
      if(null != other){
         return other;
      }
      return RString.EMPTY;
   }

   //============================================================
   // <T>获得非空字符串。</T>
   //
   // @param value 字符串
   // @return 字符串
   //============================================================
   public static String nvl(String... values){
      if(null != values){
         int count = values.length;
         for(int n = 0; n < count; n++){
            String value = values[n];
            if(null != value){
               if(value.length() > 0){
                  return value;
               }
            }
         }
      }
      return RString.EMPTY;
   }

   //============================================================
   // <T>获得非空字符串。</T>
   //
   // @param value 字符串
   // @return 字符串
   //============================================================
   public static String nvl(Object value,
                            Object other){
      if(null != value){
         String result = value.toString();
         if(result.length() > 0){
            return result;
         }
      }
      if(null != other){
         return other.toString();
      }
      return RString.EMPTY;
   }

   //============================================================
   // <T>获得非空字符串。</T>
   //
   // @param value 字符串
   // @return 字符串
   //============================================================
   public static FString nvlStr(FString value){
      return (null != value) ? value : new FString();
   }

   //============================================================
   // <T>获得首字母为小写字符的字符串。</T>
   //
   // @param source 指定字符串
   // @return 首字母为小写字符的字符串
   //============================================================
   public final static String firstLower(String source){
      if(null != source){
         if(source.length() > 1){
            return source.substring(0, 1).toLowerCase() + source.substring(1);
         }
         return source.toLowerCase();
      }
      return null;
   }

   //============================================================
   // <T>获得首字母为大写字符的字符串。</T>
   //
   // @param source 指定字符串
   // @return 首字母为大写字符的字符串
   //============================================================
   public final static String firstUpper(String source){
      if(null != source){
         if(source.length() > 1){
            return source.substring(0, 1).toUpperCase() + source.substring(1);
         }
         return source.toUpperCase();
      }
      return null;
   }

   //============================================================
   // <T>将一个字符串变为小写字符串。</T>
   // <P>如果字符串为空则不处理，返回空字符串。</P>
   //
   // @param value 字符串
   // @return 小写字符串
   //============================================================
   public static String toLower(String value){
      return (null != value) ? value.toLowerCase() : null;
   }

   //============================================================
   // <T>将一个字符串变为小写非空字符串。</T>
   //
   // @param value 字符串
   // @return 小写字符串
   //============================================================
   public static String toNvlLower(String value){
      return (null != value) ? value.toLowerCase() : EMPTY;
   }

   //============================================================
   // <T>将一个字符串变为大写字符串。</T>
   // <P>如果字符串为空则不处理，返回空字符串。</P>
   //
   // @param value 字符串
   // @return 大写字符串
   //============================================================
   public static String toUpper(String value){
      return (null != value) ? value.toUpperCase() : null;
   }

   //============================================================
   // <T>将一个字符串变为大写非空字符串。</T>
   //
   // @param value 字符串
   // @return 小写字符串
   //============================================================
   public static String toNvlUpper(String value){
      return (value != null) ? value.toUpperCase() : EMPTY;
   }

   //============================================================
   // <T>截掉指定字符串左边的空格。</T>
   //
   // @param source 指定字符串
   // @param trims 过滤的字符集合
   // @return 截掉后的字符串
   //============================================================
   public static String trimLeft(String source){
      return trimLeft(source, " ");
   }

   //============================================================
   // <T>截掉指定字符串左边的空格。</T>
   //
   // @param source 指定字符串
   // @param trims 过滤的字符集合
   // @return 截掉后的字符串
   //============================================================
   public static String trimLeft(String source,
                                 char... trims){
      if(null != source){
         int length = source.length();
         if(length > 0){
            for(int n = 0; n < length; n++){
               if(!RChar.contains(trims, source.charAt(n))){
                  return source.substring(n);
               }
            }
         }
         return source;
      }
      return null;
   }

   //============================================================
   // <T>截掉指定字符串左边的空格。</T>
   //
   // @param source 指定字符串
   // @param partten 模板
   // @return 截掉后的字符串
   //============================================================
   public static String trimLeft(String source,
                                 String partten){
      if(null != source){
         int nLength = source.length();
         if(nLength > 0){
            int nPosition = 0;
            for(int n = 0; n < nLength; n++){
               if(source.charAt(n) > ' '){
                  nPosition = n;
                  break;
               }
            }
            return (nPosition != (nLength - 1)) ? source.substring(nPosition + 1) : source;
         }
      }
      return "";
   }

   //============================================================
   // <T>截掉指定字符串集合左边的空格。</T>
   //
   // @param values 字符串集合
   // @return 截掉后的字符串
   //============================================================
   public static String[] trimLeft(String[] values){
      if(null != values){
         return trimLeft(values, 0, values.length);
      }
      return values;
   }

   //============================================================
   // <T>截掉指定字符串集合右边的空格。</T>
   //
   // @param values 字符串集合
   // @param offset 索引位置
   // @param count 总数
   //============================================================
   public static String[] trimLeft(String[] values,
                                   int offset,
                                   int count){
      if(null != values){
         int n = offset - 1;
         int loop = offset + count;
         while(n < loop){
            String line = values[n];
            if(null != line){
               int i = 0;
               int length = line.length();
               while(i < length){
                  if(' ' != line.charAt(i)){
                     break;
                  }
                  i++;
               }
               line = line.substring(i);
               values[i] = line;
            }
         }
      }
      return values;
   }

   //============================================================
   // <T>截掉指定字符串右边的空格。</T>
   //
   // @param source 指定字符串
   // @return 截掉后的字符串
   //============================================================
   public static String trimRight(String value){
      return trimRight(value, " ");
   }

   //============================================================
   // <T>截掉指定字符串右边的空格。</T>
   //
   // @param source 指定字符串
   // @param partten 模板
   // @return 截掉后的字符串
   //============================================================
   public static String trimRight(String value,
                                  String partten){
      if(null != value){
         int length = value.length();
         if(length > 0){
            int position = length - 1;
            for(int n = length - 1; n >= 0; n--){
               if(value.charAt(n) > ' '){
                  position = n;
                  break;
               }
            }
            return (position != (length - 1)) ? value.substring(0, position + 1) : value;
         }
      }
      return "";
   }

   //============================================================
   // <T>截掉指定字符串集合右边的空格。</T>
   //
   // @param values 字符串集合
   // @return 截掉后的字符串
   //============================================================
   public static String[] trimRight(String[] values){
      return trimRight(values, 0, values.length);
   }

   //============================================================
   // <T>截掉指定字符串集合右边的空格。</T>
   //
   // @param values 字符串集合
   // @param offset 索引位置
   // @param count 总数
   //============================================================
   public static String[] trimRight(String[] values,
                                    int offset,
                                    int count){
      if(null != values){
         int n = offset - 1;
         int loop = offset + count;
         while(++n < loop){
            String line = values[n];
            if(null != line){
               int length = line.length();
               if(length > 0){
                  int i = line.length() - 1;
                  while(i >= 0){
                     if(' ' != line.charAt(i)){
                        break;
                     }
                     --i;
                  }
                  line = line.substring(0, i + 1);
                  values[n] = line;
               }
            }
         }
      }
      return values;
   }

   //============================================================
   // <T>截掉指定字符串两边的空格。</T>
   //
   // @param value 字符串
   // @return 字符串
   //============================================================
   public static String trim(String value){
      return (null != value) ? value.trim() : null;
   }

   //============================================================
   // <T>截掉指定字符串集合两边的空格。</T>
   //
   // @param values 字符串集合
   // @return 字符串集合
   //============================================================
   public static String[] trim(String[] value){
      if(null != value){
         int count = value.length;
         for(int n = 0; n < count; ++n){
            String line = value[n];
            if(null != line){
               line = line.trim();
               value[n] = line;
            }
         }
      }
      return value;
   }

   //============================================================
   // <T>截掉指定字符串集合两边的空格。</T>
   //
   // @param values 字符串集合
   // @param offset 位置
   // @param count 个数
   // @return 字符串集合
   //============================================================
   public static String[] trim(String[] value,
                               int offset,
                               int count){
      if(null != value){
         int loop = offset + count;
         while(offset++ < loop){
            String line = value[offset];
            if(null != line){
               value[offset] = line.trim();
            }
         }
      }
      return value;
   }

   //============================================================
   // <T>截掉指定字符串集合两边的空格，去掉完全空的数据。</T>
   //
   // @param values 字符串集合
   // @return 字符串集合
   //============================================================
   public static String[] trimNoEmpty(String[] values){
      FStrings results = new FStrings();
      for(String value : values){
         if(null != value){
            value = value.trim();
            int length = value.length();
            if(length > 0){
               results.push(value.trim());
            }
         }
      }
      return results.toObjects();
   }

   //============================================================
   // <T>截掉指定字符串集合两边的空格，去掉完全空的数据。</T>
   //
   // @param source 来源字符串
   // @param splitter 分割字符
   // @return 字符串集合
   //============================================================
   public static String trimSplit(String source,
                                  char splitter){
      StringBuffer result = new StringBuffer(source.length());
      String[] strings = split(source, splitter);
      for(int n = 0; n < strings.length; n++){
         if(null != strings[n]){
            result.append(strings[n].trim());
         }
      }
      return result.toString();
   }

   //============================================================
   // <T>去掉首个回车符。</T>
   //
   // @param value 字符串
   // @return 字符串
   //============================================================
   public static String trimFirstEnter(String value){
      if(null != value){
         if(value.startsWith("\n")){
            value = value.substring(1);
         }else if(value.startsWith("\r\n")){
            value = value.substring(2);
         }else if(value.startsWith("\r")){
            value = value.substring(1);
         }
      }
      return value;
   }

   //============================================================
   // <T>左补齐字符到指定长度。</T>
   //
   // @param value 字符串
   // @param length 长度
   // @return 字符串
   //============================================================
   public final static String leftPad(String value,
                                      int length){
      return leftPad(value, length, ' ');
   }

   //============================================================
   // <T>左补齐字符串到指定长度。</T>
   //
   // @param value 字符串
   // @param length 长度
   // @param pad 补齐字符
   // @return 字符串
   //============================================================
   public final static String leftPad(String value,
                                      int length,
                                      char pad){
      if(null != value){
         int loop = length - value.length();
         if(loop > 0){
            int n = -1;
            char[] result = new char[length];
            while(++n < loop){
               result[n] = pad;
            }
            value.getChars(0, value.length(), result, n);
            return new String(result);
         }
      }
      return value;
   }

   //============================================================
   // <T>左补齐字符串到指定长度。</T>
   //
   // @param value 字符串
   // @param length 长度
   // @param pad 补齐字符串
   // @return 字符串
   //============================================================
   public final static String leftPad(String value,
                                      int length,
                                      String pad){
      if(null != value){
         int loop = length - value.length();
         if(loop > 0){
            int n = -1;
            FString result = new FString(length);
            while(++n < loop){
               result.append(pad);
            }
            result.append(value);
            return result.toString();
         }
      }
      return value;
   }

   //============================================================
   // <T>将指定字符串中格式化为定长(Byte)的字符串，不足的在字符串左边补足指定字符。</T>
   //
   // @param value 指定字符串
   // @param length 格式化后最短长度(Byte)
   // @return 格式化后的字符串
   //============================================================
   public final static String leftBytePad(String value,
                                          int length){
      return leftBytePad(value, length, " ");
   }

   //============================================================
   // <T>将指定字符串中格式化为定长(Byte)的字符串，不足的在字符串左边补足指定字符。</T>
   //
   // @param value 指定字符串
   // @param length 格式化后最短长度(Byte)
   // @param chars 补足字符
   // @return 格式化后的字符串
   //============================================================
   public final static String leftBytePad(String value,
                                          int length,
                                          String chars){
      if(null == value){
         return "";
      }
      int loop = length - value.getBytes().length;
      if(loop > 0){
         StringBuffer result = new StringBuffer();
         for(int n = 0; n < loop; n++){
            result.append(chars);
         }
         result.append(value);
         return result.toString();
      }
      return value;
   }

   //============================================================
   // <T>右补齐字符串到指定长度。</T>
   //
   // @param value 字符串
   // @param length 长度
   // @return 字符串
   //============================================================
   public final static String rightPad(String value,
                                       int length){
      return rightPad(value, length, ' ');
   }

   //============================================================
   // <T>右补齐字符串到指定长度。</T>
   //
   // @param value 字符串
   // @param length 长度
   // @param pad 补齐字符
   // @return 字符串
   //============================================================
   public final static String rightPad(String value,
                                       int length,
                                       char pad){
      if(value != null && length > 0){
         int vl = value.length();
         int loop = length - vl;
         if(loop > 0){
            char[] result = new char[length];
            value.getChars(0, vl, result, 0);
            Arrays.fill(result, vl, length, pad);
            return new String(result);
         }
      }
      return value;
   }

   //============================================================
   // <T>右补齐字符串到指定长度。</T>
   //
   // @param value 字符串
   // @param length 长度
   // @param pad 补齐字符串
   // @return 字符串
   //============================================================
   public final static String rightPad(String value,
                                       int length,
                                       String pad){
      if(null == value){
         value = "";
      }
      int loop = length - value.length();
      if(loop > 0){
         StringBuffer result = new StringBuffer();
         result.append(value);
         for(int n = 0; n < loop; n++){
            result.append(pad);
         }
         return result.toString();
      }
      return value;
   }

   //============================================================
   // <T>将指定字符串中格式化为定长(Byte)的字符串，不足的在字符串右边补足指定字符。</T>
   //
   // @param value 字符串
   // @param length 长度
   // @return 字符串
   //============================================================
   public final static String rightBytePad(String value,
                                           int length){
      return rightBytePad(value, length, " ");
   }

   //============================================================
   // <T>将指定字符串中格式化为定长(Byte)的字符串，不足的在字符串右边补足指定字符。</T>
   //
   // @param value 字符串
   // @param length 长度
   // @param pad 补齐字符串
   // @return 字符串
   //============================================================
   public final static String rightBytePad(String value,
                                           int length,
                                           String chars){
      if(null == value){
         value = EMPTY;
      }
      int loop = length - value.getBytes().length;
      if(loop > 0){
         StringBuffer result = new StringBuffer();
         result.append(value);
         for(int n = 0; n < loop; n++){
            result.append(chars);
         }
         return result.toString();
      }
      return value;
   }

   //============================================================
   // <T>截取指定字符串左面定长的字符串。</T>
   //
   // @param value 指定字符串
   // @param search 搜索字符串
   // @return 截取后的字符串
   //============================================================
   public final static String left(String value,
                                   int length){
      if(null != value){
         if(value.length() > length){
            return value.substring(0, length);
         }
         return value;
      }
      return null;
   }

   //============================================================
   // <T>截取指定字符串左面到搜索字符串之间的字符串。</T>
   // <P>如果没有找到，则返回原先字符串。</P>
   //
   // @param value 指定字符串
   // @param search 搜索字符串
   // @return 截取后的字符串
   //============================================================
   public final static String left(String value,
                                   String search){
      if(null != value){
         int index = value.indexOf(search);
         if(index >= 0){
            return value.substring(0, index);
         }
         return value;
      }
      return null;
   }

   //============================================================
   // <T>截取指定字符串右面定长的字符串。</T>
   //
   // @param value 指定字符串
   // @param search 搜索字符串
   // @return 截取后的字符串
   //============================================================
   public final static String right(String value,
                                    int length){
      if(null != value){
         int valueLength = value.length();
         if(valueLength > length){
            return value.substring(valueLength - length);
         }
         return value;
      }
      return null;
   }

   //============================================================
   // <T>截取指定字符串右面到搜索字符串之间的字符串。</T>
   // <P>如果没有找到，则返回原先字符串。</P>
   //
   // @param value 指定字符串
   // @param search 搜索字符串
   // @return 截取后的字符串
   //============================================================
   public final static String right(String value,
                                    String search){
      if(null != value){
         int index = value.lastIndexOf(search);
         if(index >= 0){
            return value.substring(index + search.length());
         }
         return value;
      }
      return null;
   }

   //============================================================
   // <T>格式化参数字符串。</T>
   //
   // @param source 字符串
   // @param params 参数集合
   // @return 格式化字符串
   //============================================================
   public final static String format(String source,
                                     Object... params){
      if(null != params){
         int count = params.length;
         for(int n = 0; n < count; n++){
            String info = null;
            Object param = params[n];
            if(null != param){
               if(param instanceof Object[]){
                  info = RDump.dump((Object[])param).toString();
               }else{
                  info = param.toString();
               }
            }else{
               info = RString.EMPTY;
            }
            source = RString.replace(source, "{" + (n + 1) + "}", info);
         }
      }
      return source;
   }

   //============================================================
   // <T>将一个字符重复指定次数，生成新的字符串。</T>
   //
   // @param value 字符
   // @param count 总数
   // @return 字符串
   //============================================================
   public final static String repeat(char value,
                                     int count){
      StringBuffer result = new StringBuffer();
      for(int n = 0; n < count; n++){
         result.append(value);
      }
      return result.toString();
   }

   //============================================================
   // <T>将一个字符串重复指定次数，生成新的字符串。</T>
   //
   // @param value 字符串
   // @param count 总数
   // @return 字符串
   //============================================================
   public final static String repeat(String value,
                                     int count){
      if(null != value){
         StringBuffer result = new StringBuffer();
         for(int n = 0; n < count; n++){
            result.append(value);
         }
         return result.toString();
      }
      return RString.EMPTY;
   }

   //============================================================
   // <T>获得指定字符串中从左字符串到右字符串之间的字符串。</T>
   // <p>若左字符串为NULL或没有找到：获得从开始到右字符串之间的字符串</p>
   // <p>若右字符串为NULL或没有找到：获得从左字符串到末尾之间的字符串</p>
   // <p>若左右字符串为NULL或没有找到：获得指定字符串</p>
   //
   // @param source 指定字符串
   // @param left 左字符串
   // @param right 右字符串
   // @return 中间字符串
   //============================================================
   public final static String mid(String source,
                                  String left,
                                  String right){
      if(null != source){
         int nLeft = -1;
         int nLeftLength = 0;
         if(null != left){
            nLeft = source.indexOf(left);
            nLeftLength = left.length();
         }
         int nRight = -1;
         if(null != right){
            if(nLeft < 0){
               nRight = source.indexOf(right);
            }else{
               nRight = source.indexOf(right, nLeft + nLeftLength);
            }
         }
         if((nLeft >= 0) && (nRight >= 0)){
            return source.substring(nLeft + nLeftLength, nRight);
         }else if((nLeft >= 0) && (nRight < 0)){
            return source.substring(nLeft + nLeftLength);
         }else if((nLeft < 0) && (nRight >= 0)){
            return source.substring(0, nRight);
         }else{
            return source;
         }
      }
      return null;
   }

   //============================================================
   // <T>获得指定字符串中从左字符串到右字符串之间的匹配字符串。</T>
   //
   // @param source 指定字符串
   // @param find 查找字符
   // @return 中间字符串
   //============================================================
   public final static String midMatch(String source,
                                       char find){
      if(null != source){
         FString result = new FString();
         char[] chars = source.toCharArray();
         boolean inRange = false;
         int count = chars.length;
         for(int n = 0; n < count; n++){
            char ch = chars[n];
            // 略过转意字符
            if(inRange && (ch == '\\')){
               result.append(ch);
               result.append(chars[++n]);
               continue;
            }
            // 进入范围
            if(!inRange && (ch == find)){
               inRange = true;
               continue;
            }
            // 离开范围
            if(inRange && (ch == find)){
               return result.toString();
            }
            // 追加自负
            if(inRange){
               result.append(ch);
            }
         }
      }
      return null;
   }

   //============================================================
   // <T>获得指定字符串中从左字符串到右字符串之间的匹配字符串。</T>
   //
   // @param source 指定字符串
   // @param left 左字符
   // @param right 右字符
   // @return 中间字符串
   //============================================================
   public final static String midMatch(String source,
                                       char left,
                                       char right){
      if(null != source){
         FString result = new FString();
         char[] chars = source.toCharArray();
         int level = 0;
         int count = chars.length;
         for(int n = 0; n < count; n++){
            char ch = chars[n];
            if(ch == left){
               if(level > 0){
                  result.append(ch);
               }
               level++;
            }else if(ch == right){
               level--;
               if(level > 0){
                  result.append(ch);
               }
               if(0 == level){
                  return result.toString();
               }
            }else if(level > 0){
               result.append(ch);
            }
         }
      }
      return null;
   }

   //============================================================
   // <T>将一个字符串的内容合并为一个字符串。</T>
   //
   // @param left 左侧字符串
   // @param splitter 分割符
   // @param right 右侧字符串
   // @return 合并字符串
   //============================================================
   public final static String join(String left,
                                   String splitter,
                                   String right){
      if(isEmpty(left) && isEmpty(right)){
         return RString.EMPTY;
      }else if(!isEmpty(left) && isEmpty(right)){
         return left;
      }else if(isEmpty(left) && !isEmpty(right)){
         return right;
      }
      return left + splitter + right;
   }

   //============================================================
   // <T>将一个字符串集合的内容合并为一个字符串。</T>
   //
   // @param values 字符串集合
   // @return 合并字符串
   //============================================================
   public final static String join(String[] values){
      return join(values, RString.EMPTY);
   }

   //============================================================
   // <T>将一个字符串集合的内容合并为一个字符串。</T>
   //
   // @param values 字符串集合
   // @param splitter 分割符
   // @return 合并字符串
   //============================================================
   public final static String join(String[] values,
                                   char splitter){
      if(null != values){
         int count = values.length;
         StringBuffer result = new StringBuffer();
         for(int n = 0; n < count; n++){
            if(0 != n){
               result.append(splitter);
            }
            if(!isEmpty(values[n])){
               result.append(values[n]);
            }
         }
         return result.toString();
      }
      return null;
   }

   //============================================================
   // <T>将一个字符串集合的内容合并为一个字符串。</T>
   //
   // @param values 字符串集合
   // @param splitter 分割符
   // @return 合并字符串
   //============================================================
   public final static String join(String[] values,
                                   String splitter){
      if(null != values){
         int count = values.length;
         StringBuffer result = new StringBuffer();
         for(int n = 0; n < count; n++){
            if(0 != n){
               result.append(splitter);
            }
            if(!isEmpty(values[n])){
               result.append(values[n]);
            }
         }
         return result.toString();
      }
      return null;
   }

   //============================================================
   // <T>将一个字符串集合的内容合并为一个字符串。</T>
   //
   // @param values 字符串集合
   // @param splitter 分割符
   // @param left 左侧字符串
   // @param right 右侧字符串
   // @return 合并字符串
   //============================================================
   public final static String join(String[] values,
                                   String splitter,
                                   String left,
                                   String right){
      if(null != values){
         int count = values.length;
         StringBuffer result = new StringBuffer();
         for(int n = 0; n < count; n++){
            if(0 != n){
               result.append(splitter);
            }
            if(!isEmpty(values[n])){
               result.append(left);
               result.append(values[n]);
               result.append(right);
            }
         }
         return result.toString();
      }
      return null;
   }

   //============================================================
   // <T>将一个字符串集合的内容合并为一个字符串。</T>
   //
   // @param values 字符串集合
   // @param offset 开始位置
   // @param count 总数
   // @param splitter 分割符
   // @return 合并字符串
   //============================================================
   public static String join(String[] values,
                             int offset,
                             int count,
                             char splitter){
      int n = offset - 1;
      int loop = offset + count;
      StringBuilder result = new StringBuilder();
      while(++n < loop){
         if(n != offset){
            result.append(splitter);
         }
         result.append(values[n]);
      }
      return result.toString();
   }

   //============================================================
   // <T>将一个字符串集合的内容合并为一个字符串。</T>
   //
   // @param values 字符串集合
   // @param offset 开始位置
   // @param count 总数
   // @param splitter 分割符
   // @param left 左侧字符串
   // @param right 右侧字符串
   // @return 合并字符串
   //============================================================
   public static String join(String[] values,
                             int offset,
                             int count,
                             String splitter,
                             String left,
                             String right){
      int n = offset - 1;
      int loop = offset + count;
      StringBuilder result = new StringBuilder();
      while(++n < loop){
         if((0 != n) && (null != splitter)){
            result.append(splitter);
         }
         if(null != left){
            result.append(left);
         }
         result.append(values[n]);
         if(null != right){
            result.append(right);
         }
      }
      return result.toString();
   }

   //============================================================
   // <T>替换字符串中一个字符为另一个字符。</T>
   //
   // @param value 字符串
   // @param from 一个字符
   // @param to 另一个字符
   // @return 字符串
   //============================================================
   public final static String replace(String value,
                                      char from,
                                      char to){
      if(null != value){
         char[] chars = value.toCharArray();
         for(int n = chars.length - 1; n >= 0; n--){
            if(chars[n] == from){
               chars[n] = to;
            }
         }
         return new String(chars);
      }
      return null;
   }

   //============================================================
   // <T>替换字符串中一个字符串为另一个字符串。</T>
   //
   // @param value 字符串
   // @param from 一个字符串
   // @param to 另一个字符串
   // @return 字符串
   //============================================================
   public final static String replace(String value,
                                      String from,
                                      String to){
      return replace(value, from, to, true, -1);
   }

   //============================================================
   // <T>替换字符串中一个字符串为另一个字符串。</T>
   //
   // @param value 字符串
   // @param from 一个字符串
   // @param to 另一个字符串
   // @param careCase 大小写敏感
   // @return 字符串
   //============================================================
   public final static String replace(String value,
                                      String from,
                                      String to,
                                      boolean careCase){
      return replace(value, from, to, careCase, -1);
   }

   //============================================================
   // <T>替换字符串中一个字符串为另一个字符串。</T>
   //
   // @param value 字符串
   // @param from 一个字符串
   // @param to 另一个字符串
   // @param careCase 大小写敏感
   // @param count 替换总数
   // @return 字符串
   //============================================================
   public final static String replace(String value,
                                      String from,
                                      String to,
                                      boolean careCase,
                                      int count){
      if(value != null && from != null){
         if(to == null){
            to = "";
         }
         int pos = 0;
         int find = -1;
         int fromLength = from.length();
         StringBuffer result = new StringBuffer();
         if(careCase){
            while((find = value.indexOf(from, pos)) != -1){
               result.append(value.substring(pos, find));
               result.append(to);
               pos = find + fromLength;
               if(count > 0){
                  count--;
                  if(count == 0){
                     break;
                  }
               }
            }
            result.append(value.substring(pos));
         }else{
            String tmpSource = value.toLowerCase();
            String tmpFrom = from.toLowerCase();
            while((find = tmpSource.indexOf(tmpFrom, pos)) != -1){
               result.append(value.substring(pos, find));
               result.append(to);
               pos = find + fromLength;
               if(count > 0){
                  count--;
                  if(count == 0){
                     break;
                  }
               }
            }
            result.append(value.substring(pos));
         }
         return result.toString();
      }
      return null;
   }

   //============================================================
   // <T>将指定字符串中的指定字符替换为目标字符。</T>
   //
   // @param value 字符串
   // @param chars 源字符
   // @return 替换后的字符串
   //============================================================
   public final static String replaceChars(String value,
                                           char... replaces){
      if(value != null && replaces != null){
         int rl = replaces.length;
         if(rl % 2 != 0){
            throw new IllegalArgumentException("Must pairs.");
         }
         int n;
         int p = -1;
         char[] chars = value.toCharArray();
         int length = value.length();
         while(++p < length){
            n = -2;
            while((n = n + 2) < rl){
               if(chars[p] == replaces[n]){
                  chars[p] = replaces[n + 1];
                  break;
               }
            }
         }
         return new String(chars);
      }
      return value;
   }

   //============================================================
   // <T>将指定字符串中的指定字符集合替换为目标字符集合。</T>
   //
   // @param value 字符串
   // @param sourceChars 来源字符集合
   // @param targetChars 目标字符集合
   // @return 字符串
   //============================================================
   public final static String replaceChars(String value,
                                           String sourceChars,
                                           String targetChars){
      if((null != value) && (null != sourceChars)){
         if(null == targetChars){
            targetChars = EMPTY;
         }
         StringBuffer result = new StringBuffer();
         char[] chars = value.toCharArray();
         int count = chars.length;
         for(int n = 0; n < count; n++){
            if(-1 == sourceChars.indexOf(chars[n])){
               result.append(chars[n]);
            }else{
               result.append(targetChars);
            }
         }
         return result.toString();
      }
      return null;
   }

   //============================================================
   // <T>将指定字符串中搜索目标字符串，在目标字符串两边加入左字符串和右字符串。</T>
   // <P>例如：replaceSearch("It is a test.", "Test", "{", "}", false, -1);</P>
   // <P>替换后为：It is a {test}.</P>
   //
   // @param source 指定字符串
   // @param search 目标字符串
   // @param left 左字符串
   // @param right 右字符串
   // @return 替换后的字符串
   //============================================================
   public final static String replaceSearch(String source,
                                            String search,
                                            String left,
                                            String right){
      return replaceSearch(source, search, left, right, true, -1);
   }

   //============================================================
   // <T>将指定字符串中搜索目标字符串，在目标字符串两边加入左字符串和右字符串。</T>
   // <P>例如：replaceSearch("It is a test.", "Test", "{", "}", false, -1);</P>
   // <P>替换后为：It is a {test}.</P>
   //
   // @param source 指定字符串
   // @param search 目标字符串
   // @param left 左字符串
   // @param right 右字符串
   // @param careCase 替换时大小写是否敏感
   // @return 替换后的字符串
   //============================================================
   public final static String replaceSearch(String source,
                                            String search,
                                            String left,
                                            String right,
                                            boolean careCase){
      return replaceSearch(source, search, left, right, careCase, -1);
   }

   //============================================================
   // <T>将指定字符串中搜索目标字符串，在目标字符串两边加入左字符串和右字符串。</T>
   // <P>例如：replaceSearch("It is a test.", "Test", "{", "}", false, -1);</P>
   // <P>替换后为：It is a {test}.</P>
   //
   // @param source 指定字符串
   // @param search 目标字符串
   // @param left 左字符串
   // @param right 右字符串
   // @param careCase 替换时大小写是否敏感
   // @param replaceCount 替换的次数，如果为负数或零则表示全部替换
   // @return 替换后的字符串
   //============================================================
   public final static String replaceSearch(String source,
                                            String search,
                                            String left,
                                            String right,
                                            boolean careCase,
                                            int replaceCount){
      // 替换前有效性检查
      if(source != null && search != null){
         if(null == left){
            left = EMPTY;
         }
         if(null == right){
            right = EMPTY;
         }
         // 数据初始化
         int position = 0;
         int findPosition = -1;
         int searchLength = search.length();
         StringBuffer result = new StringBuffer();
         if(careCase){
            // 大小写敏感时
            while(true){
               findPosition = source.indexOf(search, position);
               if(-1 == findPosition){
                  break;
               }
               result.append(left);
               result.append(source.substring(position, findPosition));
               result.append(right);
               position = findPosition + searchLength;
               // 替换次数的判断
               if(replaceCount > 0){
                  replaceCount--;
                  if(replaceCount == 0){
                     break;
                  }
               }
            }
            result.append(source.substring(position));
         }else{
            // 大小写不敏感
            String sTmpValue = source.toLowerCase();
            String sTmpFrom = search.toLowerCase();
            while(true){
               findPosition = sTmpValue.indexOf(sTmpFrom, position);
               if(findPosition == -1){
                  break;
               }
               result.append(left);
               result.append(source.substring(position, findPosition));
               result.append(right);
               position = findPosition + searchLength;
               // 替换次数的判断
               if(replaceCount > 0){
                  replaceCount--;
                  if(replaceCount == 0){
                     break;
                  }
               }
            }
            result.append(source.substring(position));
         }
         return result.toString();
      }
      return null;
   }

   //============================================================
   // <T>使用分割字符将字符串分割为字符串集合。</T>
   //
   // @param value 字符串
   // @param split 分割字符
   // @return 字符串集合
   //============================================================
   public static String[] split(String value,
                                char split){
      if(null == value){
         return new String[0];
      }
      // 计算出现次数
      int length = value.length();
      char[] chars = new char[length];
      value.getChars(0, length, chars, 0);
      int blockSize = 1;
      for(int n = 0; n < length; n++){
         if(chars[n] == split){
            blockSize++;
         }
      }
      // 分割字符串
      String[] blocks = new String[blockSize];
      if(blockSize > 1){
         blockSize = 0;
         int pos = 0;
         for(int n = 0; n < length; n++){
            if(chars[n] == split){
               blocks[blockSize++] = new String(chars, pos, n - pos);
               pos = n + 1;
            }
         }
         if(pos < length){
            blocks[blockSize] = new String(chars, pos, length - pos);
         }
      }else{
         blocks[0] = value;
      }
      return blocks;
   }

   //============================================================
   // <T>使用分割字符将字符串分割为字符串集合。</T>
   //
   // @param value 字符串
   // @param split 分割字符
   // @param limit 限制次数
   // @return 字符串集合
   //============================================================
   public static String[] split(String value,
                                char split,
                                int limit){
      if(null == value){
         return new String[0];
      }
      // 计算出现次数
      int length = value.length();
      char[] memory = new char[length];
      value.getChars(0, length, memory, 0);
      int blockSize = 1;
      for(int n = 0; n < length; n++){
         if(memory[n] == split){
            blockSize++;
            if(blockSize >= limit){
               break;
            }
         }
      }
      // 分割字符串
      String[] blocks = new String[blockSize];
      if(blockSize > 1){
         blockSize = 0;
         int pos = 0;
         for(int n = 0; n < length; n++){
            if(memory[n] == split){
               if(blockSize >= limit - 1){
                  break;
               }
               blocks[blockSize++] = new String(memory, pos, n - pos);
               pos = n + 1;
            }
         }
         if(pos < length){
            blocks[blockSize] = new String(memory, pos, length - pos);
         }
      }else{
         blocks[0] = value;
      }
      return blocks;
   }

   //============================================================
   // <T>使用分割字符将字符串分割为字符串集合。</T>
   //
   // @param value 字符串
   // @param splitters 分割字符集合
   // @return 字符串集合
   //============================================================
   public static String[] split(String source,
                                char[] splitters){
      if(null == source){
         return new String[0];
      }
      // 计算出现次数
      int length = source.length();
      char[] arMemory = new char[length];
      source.getChars(0, length, arMemory, 0);
      int blockSize = 1;
      for(int n = 0; n < length; n++){
         if(RChar.contains(splitters, arMemory[n])){
            blockSize++;
         }
      }
      String[] blocks = new String[blockSize];
      if(blockSize > 1){
         blockSize = 0;
         int nPosition = 0;
         for(int n = 0; n < length; n++){
            if(RChar.contains(splitters, arMemory[n])){
               blocks[blockSize] = new String(arMemory, nPosition, n - nPosition);
               blockSize++;
               nPosition = n + 1;
            }
         }
         if(nPosition < length - 1){
            blocks[blockSize] = new String(arMemory, nPosition, length - nPosition);
         }
      }else{
         blocks[0] = source;
      }
      return blocks;
   }

   //============================================================
   // <T>分割字符串成为字符串集合。</T>
   //
   // @param value 字符串
   // @param split 分割字符串
   // @return 字符串集合
   //============================================================
   public final static String[] split(String value,
                                      String split){
      return split(value, split, -1);
   }

   //============================================================
   // <T>分割字符串成为字符串集合。</T>
   //
   // @param value 字符串
   // @param split 分割字符串
   // @param limit 限制个数
   // @return 字符串集合
   //============================================================
   public final static String[] split(String value,
                                      String split,
                                      int limit){
      if((null != value) && (null != split)){
         if(limit > 0){
            return value.split(split, limit);
         }
         return value.split(split);
      }
      return new String[0];
   }

   //============================================================
   // <T>分割字符串成为两个字符串。</T>
   //
   // @param source 字符串
   // @param split 分割字符
   // @return 字符串集合
   //============================================================
   public static String[] splitTwo(String source,
                                   char split){
      if(source != null){
         int index = source.indexOf(split);
         if(index != -1){
            String[] result = new String[2];
            result[0] = source.substring(0, index);
            result[1] = source.substring(index + 1);
            return result;
         }
      }
      return null;
   }

   //============================================================
   // <T>分割字符串成为两个字符串。</T>
   //
   // @param source 字符串
   // @param split 分割字符
   // @param trim 是否去掉两边的空字符
   // @return 字符串集合
   //============================================================
   public static String[] splitTwo(String source,
                                   char split,
                                   boolean trim){
      if(source != null){
         int index = source.indexOf(split);
         if(index != -1){
            String[] result = new String[2];
            result[0] = source.substring(0, index);
            result[1] = source.substring(index + 1);
            if(trim){
               result[0] = result[0].trim();
               result[1] = result[1].trim();
            }
            return result;
         }
      }
      return null;
   }

   //============================================================
   // <T>分割字符串成为两个字符串。</T>
   //
   // @param source 字符串
   // @param split 分割字符串
   // @return 字符串集合
   //============================================================
   public static String[] splitTwo(String source,
                                   String split){
      return splitTwo(source, split, false);
   }

   //============================================================
   // <T>分割字符串成为两个字符串。</T>
   //
   // @param source 字符串
   // @param split 分割字符串
   // @param trim 是否去掉两边的空字符
   // @return 字符串集合
   //============================================================
   public static String[] splitTwo(String source,
                                   String split,
                                   boolean trim){
      if(null != source){
         int index = source.indexOf(split);
         if(-1 != index){
            String[] result = new String[2];
            result[0] = source.substring(0, index);
            result[1] = source.substring(index + split.length());
            if(trim){
               result[0] = result[0].trim();
               result[1] = result[1].trim();
            }
            return result;
         }
      }
      return null;
   }

   //============================================================
   // <T>从尾部分割字符串成为两个字符串。</T>
   //
   // @param source 字符串
   // @param split 分割字符串
   // @return 字符串集合
   //============================================================
   public static String[] splitLastTwo(String source,
                                       char split){
      int index = source.lastIndexOf(split);
      if(index != -1){
         String[] result = new String[2];
         result[0] = source.substring(0, index);
         result[1] = source.substring(index + 1);
         return result;
      }
      return null;
   }

   //============================================================
   // <T>将一个字符串按长度分割为数组。</T>
   //
   // @param source 字符串
   // @param length 长度
   // @return 字符串集合
   //============================================================
   public final static String[] splitLength(String source,
                                            int length){
      if(isEmpty(source)){
         return null;
      }
      int nValueLength = source.length();
      int nSize = nValueLength / length;
      if(nValueLength % length != 0){
         nSize++;
      }
      String[] arResult = new String[nSize];
      int nPosition = 0;
      for(int n = 0;; n++){
         if(nPosition + length > nValueLength){
            if(nPosition < nValueLength){
               arResult[n] = source.substring(nPosition);
            }
            break;
         }else{
            arResult[n] = source.substring(nPosition, nPosition + length);
         }
         nPosition += length;
      }
      return arResult;
   }

   //============================================================
   // <T>将一个字符串按字符集合分割为数组。</T>
   //
   // @param source 字符串
   // @param remain 保留
   // @param splits 分割长度
   // @return 字符串集合
   //============================================================
   public static String[] splitChars(String source,
                                     boolean remain,
                                     char... splits){
      int length = source.length();
      char[] memory = source.toCharArray();
      int blockSize = 1;
      for(int n = 0; n < length; n++){
         if(RChar.indexOf(splits, memory[n]) != -1){
            blockSize++;
         }
      }
      String[] blocks = new String[blockSize];
      if(blockSize > 1){
         blockSize = 0;
         int pos = 0;
         for(int n = 0; n < length; n++){
            if(-1 != RChar.indexOf(splits, memory[n])){
               if(remain){
                  blocks[blockSize++] = new String(memory, pos, n - pos + 1);
               }else{
                  blocks[blockSize++] = new String(memory, pos, n - pos);
               }
               pos = n + 1;
            }
         }
         if(pos < length){
            blocks[blockSize] = new String(memory, pos, length - pos);
         }
      }else{
         blocks[0] = source;
      }
      return blocks;
   }

   //============================================================
   // <T>将一个字符串内的“{内容}”替换为指定的内容。</T>
   //
   // @param source 指定字符串
   // @param code 替换内容
   // @param value 数字目标内容
   // @return 替换后的字符串
   //============================================================
   public final static String parse(String source,
                                    String code,
                                    int value){
      return replace(source, "{" + code.toLowerCase() + "}", Integer.toString(value));
   }

   //============================================================
   // <T>将一个字符串内的“{内容}”替换为指定的内容。</T>
   //
   // @param source 指定字符串
   // @param code 替换内容
   // @param value 数字目标内容
   // @return 替换后的字符串
   //============================================================
   public final static String parse(String source,
                                    String code,
                                    String value){
      return replace(source, "{" + code.toLowerCase() + "}", value);
   }

   //============================================================
   // <T>将一个字符串内的“{内容}”替换为指定的内容。</T>
   //
   // @param source 指定字符串
   // @param params 参数集合
   // @return 替换后的字符串
   //============================================================
   public final static String parseParams(String source,
                                          String... params){
      if(null != params){
         int count = params.length;
         for(int n = 0; n < count; n++){
            source = replace(source, "{" + (n + 1) + "}", params[n]);
         }
      }
      return source;
   }

   //============================================================
   // <T>移除字符串中的字符集合。</T>
   //
   // @param source 字符串
   // @param chars 字符集合
   // @return 字符串
   //============================================================
   public static String removeChars(String source,
                                    char... chars){
      if((null != source) && (null != chars)){
         StringBuffer result = new StringBuffer();
         int length = chars.length;
         char[] data = source.toCharArray();
         for(char value : data){
            int n = -1;
            boolean exists = false;
            while(++n < length){
               if(chars[n] == value){
                  exists = true;
                  break;
               }
            }
            if(!exists){
               result.append(value);
            }
         }
         return result.toString();
      }
      return source;
   }

   //============================================================
   // <T>移除字符串中的字符集合。</T>
   //
   // @param source 字符串
   // @param chars 字符集合
   // @return 字符串
   //============================================================
   public final static String removeChars(String source,
                                          String chars){
      if((null != source) && (null != chars)){
         StringBuffer result = new StringBuffer();
         char[] data = source.toCharArray();
         int length = data.length;
         for(int n = 0; n < length; n++){
            if(chars.indexOf(data[n]) < 0){
               result.append(data[n]);
            }
         }
         return result.toString();
      }
      return null;
   }

   //============================================================
   // <T>从指定的字符串中删除字符串。</T>
   //
   // @param source 指定字符串
   // @param value 删除字符串
   // @return 删除字符串的字符串
   //============================================================
   public final static String removeString(String source,
                                           String value){
      if(source == null || value == null){
         return null;
      }
      int find = source.indexOf(value);
      if(find != -1){
         int length = value.length();
         return source.substring(0, find) + source.substring(find + length);
      }
      return null;
   }

   //============================================================
   // <T>格式化字符串为一行。</T>
   //
   // @param source 字符串
   // @return 字符串
   //============================================================
   public static String singleLine(String source){
      if(null != source){
         source = RString.replaceChars(source, '\r', ' ', '\n', ' ');
         while(-1 != source.indexOf("  ")){
            source = RString.replace(source, "  ", " ");
         }
      }
      return source;
   }

   //============================================================
   // <T>获得对象的字符串。</T>
   //
   // @param item 对象
   // @return 字符串
   //============================================================
   public static String toString(Object item){
      if(null != item){
         if(item instanceof String){
            return (String)item;
         }
         return item.toString();
      }
      return null;
   }
   //
   //   public final static String format(String source, int maxParamLength, Object... params){
   //      if(null != params){
   //         int count = params.length;
   //         for(int n = 0; n < count; n++){
   //            String info = null;
   //            Object param = params[n];
   //            if(null != param){
   //               if(param instanceof Object[]){
   //                  info = RDump.dump((Object[])param).toString();
   //               }else{
   //                  info = param.toString();
   //               }
   //            }else{
   //               info = RString.EMPTY;
   //            }
   //            if(maxParamLength > 0 && info.length() > maxParamLength){
   //               info = info.substring(0, maxParamLength) + "...";
   //            }
   //            source = RString.replace(source, "{" + n + "}", info);
   //         }
   //      }
   //      return source;
   //   }
   //
   //
   //   /**
   //    * <p>在指定字符串中是否含有目标字符串</p>
   //    *
   //    * @param value 指定字符串
   //    * @param search 目标字符串
   //    * @return true：是<br>false：否
   //    */
   //   public final static boolean hasString(String value, String search){
   //      return (null != value && null != search) ? value.indexOf(search) > 0 : false;
   //   }
   //
   //   public static int indexOf(String value, char first, String serach, char last){
   //      if(null != value && null != serach){
   //         char[] va = value.toCharArray();
   //         char[] sa = serach.toCharArray();
   //         return RChar.indexOf(va, 0, va.length, first, sa, 0, sa.length, last);
   //      }
   //      return -1;
   //   }
   //
   //
   //   /**
   //    * <p>如果指定字符串为空,则返回默认值</p>
   //    * <p>create date:2005/02/14</p>
   //    *
   //    * @param sValue 指定字符串
   //    * @param sDefault 默认值
   //    * @return 字符串
   //    */
   //   public final static String makeDefaultValue(String sValue, String sDefault){
   //      return isNotEmpty(sValue) ? sValue : sDefault;
   //   }
   //
   //
   //   public static String notEmptyValue(Object oValue){
   //      return (oValue != null) ? oValue.toString() : "";
   //   }
   //
   //   public static String notEmptyValue(Object oValue, Object oDefault){
   //      if(oValue == null){
   //         return oDefault.toString();
   //      }
   //      String sValue = oValue.toString();
   //      return (sValue.length() > 0) ? sValue : oDefault.toString();
   //   }
   //
   //   public static String notEmptyValue(String sValue, String sDefault){
   //      return (sValue != null) ? ((sValue.length() > 0) ? sValue : sDefault) : sDefault;
   //   }
   //
   //
   //   public final static void repeat(FString source, char value, int count){
   //      if(null != source){
   //         while(count-- > 0){
   //            source.append(value);
   //         }
   //      }
   //   }
   //
   //
   //
   //   //   /**
   //   //    * <p>截取指定字符串左面定长(Byte)的字符串</p>
   //   //    * <p>create date:2005/02/14</p>
   //   //    *
   //   //    * @param sValue 指定字符串
   //   //    * @param nLength 截取长度(Byte)
   //   //    * @param sCharset 字符串
   //   //    * @return 截取后的字符串
   //   //    */
   //   //   public final static String leftb(String sValue, int nLength, String sCharset) {
   //   //      if (sValue != null) {
   //   //         try {
   //   //            byte[] arByteValue = sValue.getBytes(sCharset);
   //   //            int nTempLength = arByteValue.length;
   //   //            if (nLength > 0 && nTempLength > nLength) {
   //   //               String sResult = new String(arByteValue, 0, nLength, sCharset);
   //   //               if (sResult.length() == 0) {
   //   //                  sResult = new String(arByteValue, 0, nLength - 1, sCharset);
   //   //               }
   //   //               return sResult;
   //   //            }
   //   //         } catch (UnsupportedEncodingException ex) {
   //   //         }
   //   //         return "";
   //   //      }
   //   //      return null;
   //   //   }
   //   //
   //
   //   /**
   //    * <p>截取指定字符串右面定长(Byte)的字符串</p>
   //    *
   //    * @param sValue 指定字符串
   //    * @param nLength 截取长度(Byte)
   //    * @param sCharset 字符串
   //    * @return 截取后的字符串
   //    */
   //   public final static String rightb(String sValue, int nLength, String sCharset){
   //      if(sValue != null){
   //         try{
   //            byte[] arByteValue = sValue.getBytes(sCharset);
   //            int nTempLength = arByteValue.length;
   //            if(nLength > 0 && nTempLength > nLength){
   //               String sResult = new String(arByteValue, nTempLength - nLength, nLength, sCharset);
   //               if(sResult.length() == 0){
   //                  sResult = new String(arByteValue, nTempLength - nLength + 1, nLength, sCharset);
   //               }
   //               return sResult;
   //            }
   //         }catch(UnsupportedEncodingException ex){
   //         }
   //         return "";
   //      }
   //      return null;
   //   }
   //
   //   /**
   //    * <p>获得样板的部分内容对应的字符串</p>
   //    *
   //    * @param sValue 字符串
   //    * @param sPartten 样板
   //    * @param sSubPartten 样板内容
   //    * @return 对应的字符串
   //    */
   //   public static String subParttenValue(String sValue, String sPartten, String sSubPartten){
   //      if(sValue == null || sPartten == null || sSubPartten == null){
   //         return null;
   //      }
   //      int nFind = sPartten.indexOf(sSubPartten);
   //      return (nFind != -1) ? sValue.substring(nFind, nFind + sSubPartten.length()) : null;
   //   }
   //
   //   //============================================================
   //   // <T>转变字符串为指定编码的16进制字符串。</T>
   //   //
   //   // @return 编码定义
   //   //============================================================
   //   public static String toHexString(String value){
   //      FString result = new FString();
   //      for(byte mb : value.getBytes()){
   //         result.append('%');
   //         RByte.toHexChars(result, mb);
   //      }
   //      return result.toString();
   //   }
}
