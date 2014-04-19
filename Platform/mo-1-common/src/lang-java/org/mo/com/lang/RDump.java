package org.mo.com.lang;

import java.util.Map;
import org.mo.com.lang.generic.TDumpInfo;

//============================================================
// <T>运行信息工具类。</T>
//============================================================
public class RDump
{
   // 级别集合
   private static FObjects<FString> _LEVELS = new FObjects<FString>(FString.class);

   // 分割线1级
   public static String LEVEL_SPACE = "   ";

   // 分割线1级
   public static String LINE_L1 = RString.repeat(".", 60);

   // 分割线2级
   public static String LINE_L2 = RString.repeat("-", 60);

   // 分割线3级
   public static String LINE_L3 = RString.repeat("=", 60);

   // 分割线4级
   public static String LINE_L4 = RString.repeat("*", 60);

   //============================================================
   // <T>根据级别生成字符串。</T>
   //
   // @param level 级别
   // @return 字符串
   //============================================================
   public static FString levelSpace(int level){
      FString space = _LEVELS.get(level);
      if(null == space){
         _LEVELS.ensureSize(level);
         space = new FString();
         space.appendRepeat(LEVEL_SPACE, level);
         _LEVELS.set(level, space);
      }
      return space;
   }

   //============================================================
   // <T>根据字符串生成行字符串。</T>
   //
   // @param value 字符串
   // @return 字符串
   //============================================================
   public static String lineString(String value){
      FString dump = new FString("---- ");
      dump.append(value);
      dump.append(" ");
      dump.append(RString.repeat("-", 60 - 5 - RString.nvl(value).length()));
      return dump.toString();
   }

   //============================================================
   // <T>生成对象的运行信息。</T>
   //
   // @param info 运行信息
   // @return 运行信息
   //============================================================
   public static TDumpInfo append(TDumpInfo info){
      return dump(info, info.instance(), info.typeCd(), 0);
   }

   //============================================================
   // <T>生成集合对象的运行信息。</T>
   //
   // @param map 集合对象
   // @return 运行信息
   //============================================================
   public static <N, V> String dump(Map<N, V> map){
      Object name = null;
      Object[] names = map.keySet().toArray();
      int count = names.length;
      FString dump = new FString();
      //RClass.dumpClass(dump, map);
      dump.append(" - " + count + "[\n");
      for(int n = 0; n < count; n++){
         name = names[n];
         dump.append(name, "=");
         Object value = map.get(name);
         if(value instanceof IDump){
            dump.append(((IDump)value).dump());
         }else{
            dump.append(value);
         }
         dump.append("\n");
      }
      dump.append("]");
      return dump.toString();
   }

   //============================================================
   // <T>生成字节数组的运行信息。</T>
   //
   // @param info 运行信息
   // @param data 字节数组
   // @param offset 开始位置
   // @param length 数据长度
   // @return 运行信息
   //============================================================
   public static TDumpInfo dump(TDumpInfo info,
                                byte[] data,
                                int offset,
                                int length){
      FString line = new FString();
      int lines = length / 16;
      if(length % 16 != 0){
         lines++;
      }
      info.append("----: -- -- -- -- -- -- -- -- | -- -- -- -- -- -- -- -- - -------- --------\n");
      info.append(RInteger.format(lines, 4));
      info.append(": 00 01 02 03 04 05 06 07 | 08 09 10 11 12 13 14 15 - 12345678 12345678\n");
      info.append("----: -- -- -- -- -- -- -- -- | -- -- -- -- -- -- -- -- - -------- --------\n");
      int n = offset - 1;
      int row = 0;
      while(++n < length){
         if(n != 0){
            if(n % 16 == 0){
               info.append(" - ");
               info.append(line);
               line.clear();
               info.append('\n');
               if(n + 1 < length){
                  info.append(RHex.format(row++, 4));
                  info.append(": ");
               }
            }else if(n % 8 == 0){
               info.append(" | ");
               line.append(' ');
            }else{
               info.append(' ');
            }
         }else{
            info.append("0000: ");
            row++;
         }
         char ch = (char)data[n];
         if(ch >= ' ' && ch < 'z'){
            line.append((char)data[n]);
         }else{
            line.append('.');
         }
         info.append(RByte.toHexChars(data[n]));
      }
      if(!line.isEmpty()){
         info.append(" - ");
         info.append(line);
      }
      return info;
   }

   //============================================================
   // <T>生成对象的运行信息。</T>
   //
   // @param info 运行信息
   // @param name 名称
   // @param item 对象
   // @return 运行信息
   //============================================================
   public static TDumpInfo dump(TDumpInfo info,
                                String name,
                                Object item){
      info.appendLine();
      info.append(levelSpace(info.level()));
      info.append(name);
      info.append(" = ");
      if(item instanceof IDump){
         ((IDump)item).dump(info);
      }else if(item != null){
         dump(info, item);
      }else{
         info.append("null");
      }
      return info;
   }

   //============================================================
   // <T>生成对象的运行信息。</T>
   //
   // @param info 运行信息
   // @param item 对象
   // @param typeCd 类型
   // @param level 级别
   // @return 运行信息
   //============================================================
   public static TDumpInfo dump(TDumpInfo info,
                                Object item,
                                EDumpLevel typeCd,
                                int level){
      if(null == item){
         // 空处理
         info.append("<null>");
      }else if(item instanceof Class<?>){
         // 类处理
         String value = ((Class<?>)item).getName();
         int index = value.lastIndexOf('.');
         if(index != -1){
            info.append(value.substring(index + 1));
         }else{
            info.append(value);
         }
         info.append("@<static>");
      }else if(item instanceof String){
         // 字符串处理
         info.append((String)item);
      }else{
         // 对象处理
         String value = item.getClass().getName();
         int index = value.lastIndexOf('.');
         if(index == -1){
            info.append(value);
         }else{
            info.append(value.substring(index + 1));
         }
         info.append('@');
         String hash = Integer.toHexString(item.hashCode());
         int loop = 8 - hash.length();
         while(loop-- > 0){
            info.append('0');
         }
         info.append(hash.toUpperCase());
      }
      info.append(levelSpace(level));
      return info;
   }

   //============================================================
   // <T>生成对象的运行信息。</T>
   //
   // @param info 运行信息
   // @param item 对象
   // @return 运行信息
   //============================================================
   public static TDumpInfo dump(TDumpInfo info,
                                Object item){
      return dump(info, item, EDumpLevel.Normal, 0);
   }

   //============================================================
   // <T>生成对象的运行信息。</T>
   //
   // @param item 对象
   // @return 运行信息
   //============================================================
   public static TDumpInfo dump(Object item){
      return dump(new TDumpInfo(item), item, EDumpLevel.Normal, 0);
   }

   //============================================================
   // <T>获得一个对象数组的内容信息。</T>
   //
   // @param items 对象数组
   // @return 内容信息
   // @history 050218 MAOCY 创建
   //============================================================
   public final static FString dump(Object[] items){
      FString dump = new FString();
      if(items != null){
         int nLength = items.length;
         dump.append("[", nLength, ": ");
         for(int n = 0; n < nLength; n++){
            if(n != 0){
               dump.append(", ");
            }
            Object item = items[n];
            if(item != null){
               if(item instanceof String){
                  dump.append('"', item, '"');
               }else{
                  dump.append(items[n].toString());
               }
            }else{
               dump.append("null");
            }
         }
         dump.append("]");
         return dump;
      }else{
         dump.append("[ null ]");
      }
      return dump;
   }
   //   public static String SPLITTER = "------------------------------------------------------------";
   //
   //   /**
   //    * <p>获得一个字符串数组的内容信息</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param arData 字符串数组
   //    * @return 内容信息
   //    */
   //   public final static FString dump(String[] arData){
   //      if(arData != null){
   //         int nLength = arData.length;
   //         FString sDump = new FString();
   //         sDump.append(" [ ");
   //         sDump.append(nLength + " : ");
   //         for(int n = 0; n < nLength; n++){
   //            if(n != 0){
   //               sDump.append(", ");
   //            }
   //            if(!FStringUtil.isEmpty(arData[n])){
   //               sDump.append(arData[n]);
   //            }
   //         }
   //         sDump.append(" ]");
   //         return sDump;
   //      }
   //      return new FString("[ null ]");
   //   }
   //
   //   /**
   //    * <p>获得二维字符串数组的内容信息</p>
   //    * <p>create date:2005/12/15</p>
   //    *
   //    * @param arData 字符串数组
   //    * @return 内容信息
   //    */
   //   public final static FString dump(String[][] arData){
   //      if(arData != null){
   //         int nLength2 = 0;
   //         int nLength = arData.length;
   //         if(nLength > 0){
   //            nLength2 = arData[0].length;
   //         }
   //         FString sDump = new FString();
   //         sDump.append(FClassUtil.dump(arData));
   //         sDump.append(" ");
   //         sDump.append(nLength);
   //         sDump.append(":");
   //         sDump.append(nLength2);
   //         sDump.append("\n");
   //         for(int j = 0; j < nLength; j++){
   //            sDump.append("[");
   //            for(int i = 0; i < nLength2; i++){
   //               if(i != 0){
   //                  sDump.append(", ");
   //               }
   //               if(!FStringUtil.isEmpty(arData[j][i])){
   //                  sDump.append(arData[j][i]);
   //               }
   //            }
   //            sDump.append("]\n");
   //         }
   //         return sDump;
   //      }
   //      return new FString("[ null ]");
   //   }
   //
   //   /**
   //    * <p>获得一个字节数组的内容信息</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param arData 字节数组
   //    * @return 内容信息
   //    */
   //   public final static FString dump(byte[] arData){
   //      if(arData != null){
   //         int nLength = arData.length;
   //         FString sDump = new FString();
   //         sDump.append(" [ ");
   //         sDump.append(nLength + " : ");
   //         String sValue = null;
   //         for(int n = 0; n < nLength; n++){
   //            if(nLength > 32 && n % 32 == 0){
   //               sDump.append("\n");
   //               sDump.append(FStringUtil.leftPad(Integer.toHexString(n)
   //                     .toUpperCase(), 4, "0"));
   //               sDump.append(":");
   //               if(n == 0){
   //                  sDump.append(" ");
   //               }
   //            }
   //            if(n != 0){
   //               sDump.append(" ");
   //            }
   //            sValue = Integer.toHexString(arData[n]).toUpperCase();
   //            if(sValue.length() == 1){
   //               sDump.append("0");
   //               sDump.append(sValue);
   //            }else if(sValue.length() > 2){
   //               sDump.append(sValue.substring(sValue.length() - 2));
   //            }else{
   //               sDump.append(sValue);
   //            }
   //         }
   //         sDump.append(" ]");
   //         return sDump;
   //      }
   //      return new FString("[ null ]");
   //   }
   //
   //   /**
   //    * <p>获得一个字节数组的内容信息</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param arData 字节数组
   //    * @return 内容信息
   //    */
   //   public final static FString dump(byte[] arData,
   //                                    boolean bFormat){
   //      if(arData != null){
   //         int nLength = arData.length;
   //         FString sDump = new FString();
   //         sDump.append(" [ ");
   //         sDump.append(nLength + " :");
   //         String sValue = null;
   //         for(int n = 0; n < nLength; n++){
   //            if(bFormat && nLength > 32 && n % 32 == 0){
   //               sDump.append("\n");
   //               sDump.append(FStringUtil.leftPad(Integer.toHexString(n)
   //                     .toUpperCase(), 4, "0"));
   //               sDump.append(":");
   //               if(n == 0){
   //                  sDump.append(" ");
   //               }
   //            }
   //            if(n != 0){
   //               sDump.append(" ");
   //            }
   //            sValue = Integer.toHexString(arData[n]).toUpperCase();
   //            if(sValue.length() == 1){
   //               sDump.append("0");
   //               sDump.append(sValue);
   //            }else if(sValue.length() > 2){
   //               sDump.append(sValue.substring(sValue.length() - 2));
   //            }else{
   //               sDump.append(sValue);
   //            }
   //         }
   //         sDump.append(" ]");
   //         return sDump;
   //      }
   //      return new FString("[ null ]");
   //   }
   //
   //   /**
   //    * <p>获得一个字节数组的内容信息</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param arData 字节数组
   //    * @return 内容信息
   //    */
   //   public final static FString dump(char[] arData){
   //      if(arData != null){
   //         int nLength = arData.length;
   //         FString sDump = new FString();
   //         sDump.append(" [ ");
   //         sDump.append(nLength + " : ");
   //         String sValue = null;
   //         for(int n = 0; n < nLength; n++){
   //            if(nLength > 32 && n % 32 == 0){
   //               sDump.append("\n");
   //               sDump.append(FStringUtil.leftPad(Integer.toHexString(n)
   //                     .toUpperCase(), 4, "0"));
   //               sDump.append(":");
   //               if(n == 0){
   //                  sDump.append(" ");
   //               }
   //            }
   //            if(n != 0){
   //               sDump.append(" ");
   //            }
   //            sValue = Integer.toHexString(arData[n]).toUpperCase();
   //            if(sValue.length() == 1){
   //               sDump.append("0");
   //               sDump.append(sValue);
   //            }else if(sValue.length() > 2){
   //               sDump.append(sValue.substring(sValue.length() - 2));
   //            }else{
   //               sDump.append(sValue);
   //            }
   //         }
   //         sDump.append(" ]");
   //         return sDump;
   //      }
   //      return new FString("[ null ]");
   //   }
   //
   //   /**
   //    * <p>获得一个布尔数组的内容信息</p>
   //    * <p>create date:2005/12/15</p>
   //    *
   //    * @param arData 布尔数组
   //    * @return 内容信息
   //    */
   //   public final static FString dump(boolean[] arData){
   //      if(arData != null){
   //         int nLength = arData.length;
   //         FString sDump = new FString();
   //         sDump.append(" [ ");
   //         sDump.append(nLength + " : ");
   //         for(int n = 0; n < nLength; n++){
   //            if(nLength > 32 && n % 32 == 0){
   //               sDump.append("\n");
   //               sDump.append(FStringUtil.leftPad(Integer.toHexString(n)
   //                     .toUpperCase(), 4, "0"));
   //               sDump.append(":");
   //               if(n == 0){
   //                  sDump.append(" ");
   //               }
   //            }
   //            if(n != 0){
   //               sDump.append(" ");
   //            }
   //            sDump.append(FBooleanUtil.toString(arData[n]));
   //         }
   //         sDump.append(" ]");
   //         return sDump;
   //      }
   //      return new FString("[ null ]");
   //   }
   //
   //   /**
   //    * <p>获得一个整数数组的内容信息</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param arData 整数数组
   //    * @return 内容信息
   //    */
   //   public final static FString dump(int[] arData){
   //      if(arData != null){
   //         int nLength = arData.length;
   //         FString sDump = new FString();
   //         sDump.append(" [ ");
   //         sDump.append(nLength + " : ");
   //         String sValue = null;
   //         for(int n = 0; n < nLength; n++){
   //            if(nLength > 32 && n % 32 == 0){
   //               sDump.append("\n");
   //               sDump.append(FStringUtil.leftPad(Integer.toHexString(n)
   //                     .toUpperCase(), 4, "0"));
   //               sDump.append(":");
   //               if(n == 0){
   //                  sDump.append(" ");
   //               }
   //            }
   //            if(n != 0){
   //               sDump.append(" ");
   //            }
   //            sValue = Integer.toHexString(arData[n]).toUpperCase();
   //            if(sValue.length() < 4){
   //               sDump.append(FStringUtil.leftPad(sValue, 4, "0"));
   //            }else if(sValue.length() > 4){
   //               sDump.append(sValue.substring(sValue.length() - 4));
   //            }else{
   //               sDump.append(sValue);
   //            }
   //         }
   //         sDump.append(" ]");
   //         return sDump;
   //      }
   //      return new FString("[ null ]");
   //   }
   //
   //   /**
   //    * <p>输出一个对象的内容信息</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param oData 指定对象
   //    * @return 内容信息
   //    * @throws FException 
   //    */
   //   public final static FString dump(Object oItem)
   //         throws FException{
   //      if(oItem instanceof IDump){
   //         return ((IDump) oItem).dump();
   //      }else if(oItem instanceof boolean[]){
   //         return dump((boolean[]) oItem);
   //      }else if(oItem instanceof byte[]){
   //         return dump((byte[]) oItem);
   //      }else if(oItem instanceof char[]){
   //         return dump((char[]) oItem);
   //      }else if(oItem instanceof int[]){
   //         return dump((int[]) oItem);
   //      }else if(oItem instanceof String[][]){
   //         return dump((String[][]) oItem);
   //      }else if(oItem instanceof String[]){
   //         return dump((String[]) oItem);
   //      }else if(oItem instanceof Object[]){
   //         return dump((Object[]) oItem);
   //      }else if(oItem != null){
   //         FString sDump = new FString();
   //         sDump.append(FClassUtil.dump(oItem));
   //         sDump.append(" ");
   //         sDump.append(oItem.toString());
   //         return sDump;
   //      }
   //      return new FString("[null]");
   //   }
}
