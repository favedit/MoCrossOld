package org.mo.com.lang.temp;

import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FString;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.IAttributes;
import org.mo.com.lang.IStringPairs;

public class RPack
{
   public static String FIX_BEGIN = "${";

   public static String FIX_END = "}";

   public static FString pack(String[] names,
                              String[] values,
                              int offset,
                              int count){
      //int n = offset - 1;
      //int loop = offset + count;
      FString pack = new FString();
      //      while(++n < loop){
      //         String name = names[n];
      //         if(name != null){
      //            String value = values[n];
      //            String length = Integer.toString(name.length());
      //            pack.appendInt(length.length()).append(length).append(name);
      //            if(null == value){
      //               pack.append("0");
      //            }else{
      //               length = Integer.toString(value.length());
      //               pack.appendInt(length.length()).append(length).append(value);
      //            }
      //         }
      //      }
      return pack;
   }

   public static String parseLink(IAttributes attributes,
                                  String value){
      if(null != attributes && null != value){
         if(value.startsWith(FIX_BEGIN) && value.endsWith(FIX_END)){
            return attributes.get(value.substring(2, value.length() - 1));
         }
      }
      return value;
   }

   public static String[] parseLinks(String value){
      int postion = 0;
      FStrings result = null;
      while(true){
         int start = value.indexOf(FIX_BEGIN, postion);
         if(-1 == start){
            return (null != result) ? result.toObjects() : null;
         }else if(null == result){
            result = new FStrings();
         }
         int end = value.indexOf(FIX_END, start + FIX_BEGIN.length());
         if(-1 == end){
            throw new FFatalError("Value format is invalid. ({0})", value);
         }
         result.push(value.substring(start + FIX_BEGIN.length(), end));
         postion = end + 1;
      }
   }

   public static void replaceLink(FString result,
                                  String link,
                                  IAttributes attributes){
      int position = 0;
      int length = link.length();
      while(position < length){
         // 获得代码内容
         int start = link.indexOf(FIX_BEGIN, position);
         if(-1 == start){
            break;
         }
         int end = link.indexOf(FIX_END, start);
         if(-1 == end){
            throw new FFatalError("Invalid link format. ({0})", link);
         }
         // 替换信息
         String code = link.substring(start + FIX_BEGIN.length(), end);
         result.append(link.substring(position, start));
         //if(attributes.contains(code)){
         result.append(attributes.get(code));
         //         }else{
         //            result.append(FIX_BEGIN);
         //            result.append(code);
         //            result.append(FIX_END);
         //         }
         position = end + FIX_END.length();
      }
      // 追加尾部
      if(position < length){
         result.append(link.substring(position, length));
      }
   }

   public static FString replaceLink(String link,
                                     IAttributes attributes){
      int position = 0;
      int length = link.length();
      FString result = new FString();
      while(position < length){
         // 获得代码内容
         int start = link.indexOf(FIX_BEGIN, position);
         if(-1 == start){
            break;
         }
         int end = link.indexOf(FIX_END, start);
         if(-1 == end){
            throw new FFatalError("Invalid link format. ({0})", link);
         }
         // 替换信息
         String code = link.substring(start + FIX_BEGIN.length(), end);
         result.append(link.substring(position, start));
         if(attributes.contains(code)){
            result.append(attributes.get(code));
         }else{
            result.append(FIX_BEGIN);
            result.append(code);
            result.append(FIX_END);
         }
         position = end + FIX_END.length();
      }
      // 追加尾部
      if(position < length){
         result.append(link.substring(position, length));
      }
      return result;
   }

   // 根据属性替换link，如果没有替换则返回link
   public static void replaceParameters(FString result,
                                        String link,
                                        IAttributes attributes){
      int position = 0;
      int length = link.length();
      while(position < length){
         // 获得代码内容
         int start = link.indexOf(FIX_BEGIN, position);
         if(-1 == start){
            break;
         }
         int end = link.indexOf(FIX_END, start);
         if(-1 == end){
            throw new FFatalError("Invalid link format. ({0})", link);
         }
         // 替换信息
         String code = link.substring(start + FIX_BEGIN.length(), end);
         result.append(link.substring(position, start));
         if(null != attributes && attributes.contains(code)){
            result.append(attributes.get(code));
         }else{
            result.append(FIX_BEGIN);
            result.append(code);
            result.append(FIX_END);
         }
         position = end + FIX_END.length();
      }
      // 追加尾部
      if(position < length){
         result.append(link.substring(position, length));
      }
   }

   public static void unpack(IAttributes map,
                             FString pack){
      if(pack != null && !pack.isEmpty()){
         try{
            String name;
            int len, length, n = 0;
            int total = pack.length();
            char[] chars = pack.memory();
            while(n < total){
               // Name
               len = chars[n++] - '0';
               length = Integer.parseInt(new String(chars, n, len));
               n += len;
               name = new String(chars, n, length);
               n += length;
               // Value
               len = chars[n++] - '0';
               if(len > 0){
                  length = Integer.parseInt(new String(chars, n, len));
                  n += len;
                  map.set(name, new String(chars, n, length));
                  n += length;
               }else{
                  map.set(name, null);
               }
            }
         }catch(Exception e){
            throw new FFatalError(e);
         }
      }
   }

   public static void unpack(IStringPairs list,
                             FString pack){
      if(pack != null && !pack.isEmpty()){
         try{
            String name;
            int len, length, n = 0;
            int total = pack.length();
            char[] chars = pack.memory();
            while(n < total){
               // Name
               len = chars[n++] - '0';
               length = Integer.parseInt(new String(chars, n, len));
               n += len;
               name = new String(chars, n, length);
               n += length;
               // Value
               len = chars[n++] - '0';
               if(len > 0){
                  length = Integer.parseInt(new String(chars, n, len));
                  n += len;
                  list.set(name, new String(chars, n, length));
                  n += length;
               }else{
                  list.set(name, null);
               }
            }
         }catch(Exception e){
            throw new FFatalError(e);
         }
      }
   }

   public static void unpackLink(IAttributes result,
                                 String link,
                                 IAttributes attributes){
      //      if(null != link){
      //         String[] subLinks = RString.split(link, ',');
      //         for(String subLink : subLinks){
      //            if(!RString.isEmpty(subLink)){
      //               String[] subs = RString.split(subLink, '=');
      //               if(2 != subs.length){
      //                  throw new FFatalError("Invalid link info. (invalid={0},link={1})", subLink, link);
      //               }
      //               result.set(subs[0].trim(), parseLink(attributes, subs[1].trim()));
      //            }
      //         }
      //      }
   }

   public static IAttributes unpackLink(String link){
      IAttributes result = null;
      if(null != link){
         result = new FAttributes();
         unpackLink(result, link, null);
      }
      return result;
   }

   public static IAttributes unpackLink(String link,
                                        IAttributes attributes){
      IAttributes result = null;
      if(null != link){
         result = new FAttributes();
         unpackLink(result, link, attributes);
      }
      return result;
   }
}
